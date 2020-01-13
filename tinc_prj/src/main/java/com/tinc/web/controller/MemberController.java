package com.tinc.web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	private MemberService service;
	
	@GetMapping("friendList")
	public String friendList(Model model) {
		String id = "user1";
		String memberId = "user1";
		model.addAttribute("myprofile", service.getMyProfile(id));
		model.addAttribute("friendsProfile", service.getFriendsProfile(memberId));
		model.addAttribute("friendListCount", service.getFriendsListCount(memberId));
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
		String friendsId = "user1";
		model.addAttribute("userIhaveblocked", service.getListOfUserIhaveblocked(memberId));
		model.addAttribute("userWhoHaveAddedMe", service.getListOfUserWhoHaveAddedMe(friendsId));
		return "member/friendSetting";
	}
	
	@PostMapping("friendSetting")
	public String friendSetting(Member member) {
		
		
		return "redirect:friendList";
	}
	
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(Member member) {
		
		service.joinMember(member);
		System.out.println(member.toString());
		
		return "redirect:friendList";
	}
	
	@GetMapping("login")
	public String login() {
		System.out.println("afa");
		
		return "member/main";
	}
	
	@PostMapping("login")
	public String login(@RequestParam(name = "id") String id, @RequestParam(name = "password")String password) {
		System.out.println("dd");
		service.isValidMember(id, password);
		
		return "redirect:friendList";
	}
	
	
}
