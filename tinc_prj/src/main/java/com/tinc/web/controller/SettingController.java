package com.tinc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tinc.web.service.MemberService;

@Controller
@RequestMapping("/setting/")
public class SettingController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("")
	public String setting(Model model) {
		String id = "user1"; // 정윤이꺼 고칠때 고치겟움
		model.addAttribute("myprofile", service.getMyProfile(id));
		model.addAttribute("friendsProfile", service.getFriendsProfile(id));
		model.addAttribute("friendListCount", service.getFriendsListCount(id));
		
		model.addAttribute("id-checkbox", service.idOpen(id));
		model.addAttribute("phone-checkbox", service.phoneNumOpen(id));
		model.addAttribute("email-checkbox", service.emailOpen(id));
		
		model.addAttribute("chatting-checkbox", service.chattingAlarm(id));
		model.addAttribute("memo-checkbox", service.memoAlarm(id));
		
		String tmpStr = null;
		if (service.getMyProfile(id).getProfileImg() != null) {
			tmpStr = service.getMyProfile(id).getProfileImg();
			StringBuilder sb = new StringBuilder("http://localhost:8080/resource/upload/member/");
			sb.append(tmpStr);
			model.addAttribute("imgs", sb);
		}
		return "setting/setting";
	}
	
	@GetMapping("change-pwd")
	public String changepwd(Model model, @RequestParam( name = "password")String password) {
		String id = "user1";
		if(service.get(id).getPassword() == password) {
			// 비번 바꿀수 있움
		}else {
			// 비밀번호가 다름
		}
		
		return "setting/change-pwd";
	}
	
	@GetMapping("logout")
	public String logout() {
		return "setting/logout";
	}
	
	@GetMapping("withdraw")
	public String withdraw() {
		
		return "setting/withdraw";
	}
}
