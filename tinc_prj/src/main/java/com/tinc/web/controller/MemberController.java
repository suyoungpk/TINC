package com.tinc.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tinc.web.entity.BlackList;
import com.tinc.web.entity.FriendsList;
import com.tinc.web.entity.Member;
import com.tinc.web.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MemberService service;
	
	@GetMapping("friendList")
	public String friendList(Principal principal, Model model) {

		String id = principal.getName();
		System.out.println(id);

		model.addAttribute("myprofile", service.getMyProfile(id));
		model.addAttribute("friendsProfile", service.getFriendsProfile(id));
		model.addAttribute("friendListCount", service.getFriendsListCount(id));
	
		return "member/friendList";
	}
	
	@GetMapping("friendSetting")
	public String friendSetting(Principal principal, Model model) {
		
		String memberId = principal.getName();
		System.out.println(memberId);
		model.addAttribute("userIhaveblocked", service.getListOfUserIhaveblocked(memberId));
		model.addAttribute("userWhoHaveAddedMe", service.getListOfUserWhoHaveAddedMe(memberId));
		
		return "member/friendSetting";
	}
	
	@PostMapping("friendSetting")
	public String friendSetting(
			@RequestParam(name = "memberId" , required = false) String memberId, 
			@RequestParam(name = "friendsId" , required = false) String friendsId,
			@RequestParam(name = "cmd" , required = false) String cmd,
			FriendsList friendList, 
			BlackList blackList) 
		{
		System.out.println(memberId+","+friendsId);
		String blackId = friendsId;
		System.out.println(blackId);
		
		friendList.setMemberId(memberId);
		friendList.setFriendsId(friendsId);
		blackList.setMemberId(memberId);
		blackList.setBlackId(blackId);
		
		System.out.println(cmd);
		switch (cmd) {
		case "userIhaveblocked-add":
			service.unblockUser(blackList);
			service.addFriend(friendList);
			break;
		case "userIhaveblocked-unblock":
			service.unblockUser(blackList);
			break;
		case "userWhoHaveAddedMe-add":
			service.addFriend(friendList);
			break;
		case "userWhoHaveAddedMe-block":
			service.unblockUser(blackList);
			break;
		}
		
		return "member/friendSetting";
		}
	

	@GetMapping("addFriend")
	   public String addFriend() {
	      
	      return "member/addFriend";
	   }
	   
	   @ResponseBody
	   @RequestMapping(value="addFriend", method = RequestMethod.POST)
	   public String addFriend(
	         Principal principal, Model model, FriendsList friendsList,
	         @RequestParam(name = "friendsId" , required = false) String friendsId,
	         @RequestParam(name = "searchwords", required = false) String query)
	      {
	      String id = "qqqqq";
	      friendsList.setMemberId(id);
	      friendsList.setFriendsId(friendsId);
	      System.out.println("friendsId"+friendsId);
	      System.out.println("쿼리값"+query);
	      Map<String, String> item = new HashMap<String, String>();
	      item.put("item1", id); 
	      item.put("item2", id);
	      item.put("item3", id);
	      item.put("item4", id);
	      item.put("item5", query);
	      model.addAttribute("id", "user1");
	      List<Member> list = service.searchFriendsforAdding(item);
	      //System.out.println(principal.getName());
	      System.out.println("서비스결과"+list);
	      Gson gson = new Gson();
	      String searchwords = gson.toJson(list);
	      System.out.println(searchwords);
	      if(friendsId !=null)
	      service.addFriend(friendsList);
	      
	      return searchwords;
	   }
	
	@GetMapping("join")
	public String join() {
		
		
		
		
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(Member member) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String encPassword = scpwd.encode(member.getPassword());
		member.setPassword(encPassword);
		service.joinMember(member);   
		return "redirect:friendList";
	}
	
	@GetMapping("idCheck")
	@ResponseBody
	public String idCheck(@RequestParam(name = "id") String id) {
		System.out.println(id);
		System.out.println(service.isDuplicatedId(id));
		return service.isDuplicatedId(id);
	}
	
	@GetMapping("login")
	public String login() {
		
		return "member/login";
	}

	@GetMapping("logout")
	public String logout() {
		
		return "member/logout";
	}
	
	@GetMapping("agree")
	public String agree() {
		return "member/agree";
	}

//	@PostMapping("agree")
//	public String agree(Boolean agree, HttpServletResponse response) {
//		//Cookie agreeCookie = new Cookie("agree", agree.toString());
//		response.addCookie(agreeCookie);
//		return "redirect:join"; 
//	}

	@GetMapping("find")
	public String find() {
		return "member/find";
	}
	
	@PostMapping("find")
	public String find(@RequestParam(name="email")String email, @RequestParam(name="id")String id, Model model)  throws MailException, MessagingException {
		System.out.println(service.findId(email));
		model.addAttribute("user", service.findId(email));
		
		StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("<body>");
		html.append("<h1>"+id+"</h1>");		
		//html.append("<img src=\"http://www.newlecture.com/resource/images/logo.png\">");
		//html.append("<a href=\"http://www.newlecture.com/member/reset-pwd?id=newlec\">비번 재설정");
		html.append("</body>");
		html.append("</html>");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setFrom("yupddok@gmail.com");
		helper.setTo(email);
		helper.setText(html.toString(), true); //true안하면 utf8로안감
		helper.setSubject("[TINC] 비밀번호 재설정 메일");
		
		mailSender.send(message);  //얘 객체생성하는 config파일 있어야됨 
		
		return "member/find";
	}
	
}
