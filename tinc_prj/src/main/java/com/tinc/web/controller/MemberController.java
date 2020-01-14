package com.tinc.web.controller;

import java.security.Principal;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;

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
	public String friendList(Model model) {
		//String id = principal.getName();
		String id = "user1";
		model.addAttribute("myprofile", service.getMyProfile(id));
		model.addAttribute("friendsProfile", service.getFriendsProfile(id));
		model.addAttribute("friendListCount", service.getFriendsListCount(id));
		String tmpStr = null;
		if (service.getMyProfile(id).getProfileImg() != null) {
			tmpStr = service.getMyProfile(id).getProfileImg();
			StringBuilder sb = new StringBuilder("http://localhost:8080/resource/upload/member/");
			sb.append(tmpStr);
			model.addAttribute("imgs", sb);
		}
//		String tmpStr = null;
//		if (tradeViewService.getTrade(id).getImg() != null) {
//			tmpStr = tradeViewService.getTrade(id).getImg().replace("\\", "/");
//			String[] imgs = new String[] { "" };
//			if (tmpStr != null && !tmpStr.equals("")) {
//				if (tmpStr.indexOf(",") != -1) {
//					imgs = tmpStr.split(",");
//				} else {
//					imgs[0] = tmpStr;
//				}
//
//				for (String img : imgs) {
//					ServletContext application = request.getServletContext();
//					String realPath = application.getRealPath(img);
//					img = realPath;
//					System.out.println("img" + img);
//				}
//			}
//			request.setAttribute("imgs", imgs);
//		}
		
		return "member/friendList";
	}
	
	@PostMapping("friendList")
	public String friendList(String id) {
		
		return "member/friendList";
	}
	
	@GetMapping("friendSetting")
	public String friendSetting(Model model) {
		String memberId = "user1";
		model.addAttribute("userIhaveblocked", service.getListOfUserIhaveblocked(memberId));
		model.addAttribute("userWhoHaveAddedMe", service.getListOfUserWhoHaveAddedMe(memberId));
		return "member/friendSetting";
	}
	
	@PostMapping("friendSetting")
	public String friendSetting(@RequestParam(value = "addFriend") String addBtn) {
		System.out.println("dd");
		System.out.println(addBtn);
		String memberId = "user1";
		String blackId = "user1";
//		service.unblockUser(memberId, blackId);
		return "redirect:friendList";
	}
	
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(Member member) {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String id = "user1";
		System.out.println("암호화전"+service.toString());
		String encPassword = scpwd.encode(service.get(id).getPassword());
		service.get(id).setPassword(encPassword);
		System.out.println("암호화후"+service.toString());
		
		
		service.joinMember(member);
		
		return "redirect:friendList";
	}
	
	@GetMapping("login")
	public String login() {
		System.out.println("dd");
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(@RequestParam(name = "id") String id, @RequestParam(name = "password")String password) {
		service.isValidMember(id, password);
		System.out.println(id);
		return "redirect:friendList";
	}
	
	@GetMapping("agree")
	public String agree() {
		return "member/agree";
	}

	@PostMapping("agree")
	public String agree(Boolean agree, HttpServletResponse response) {
		Cookie agreeCookie = new Cookie("agree", agree.toString());
		response.addCookie(agreeCookie);
		return "redirect:join"; 
	}
	
	@GetMapping("logout")
	public String logout() {
		
		return "member/main";
	}
	
	@GetMapping("find")
	public String find() {
		return "member/find";
	}
	
	@PostMapping("find")
	public String find(@RequestParam(name="email")String email, @RequestParam(name="id")String id, Model model)  throws MailException, MessagingException {
		service.findId(email);
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
