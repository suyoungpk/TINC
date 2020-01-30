package com.tinc.web.controller.JSON;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tinc.web.service.MemberService;

@Controller
@RequestMapping("/jsonTinc/member/")
public class MemberJSON {
	
	@Autowired
	private MemberService service;
	
	@ResponseBody
	@GetMapping("friendList")
	public String friendList(Principal principal) {
//		String id = principal.getName();
		String id = "user1";
		
		Gson gson = new Gson();
		
		List<String> list = new ArrayList<String>();
		list.add(gson.toJson(service.getMyProfile(id)));
		list.add(gson.toJson(service.getFriendsProfile(id)));
		list.add(gson.toJson(service.getFriendsListCount(id)));
		list.add(gson.toJson(id));
		
		String fList = list.toString();
		
		return fList;
	}
	
	@ResponseBody
	@GetMapping("friendSetting")
	public String friendSetting(Principal principal) {
		
//		String memberId = principal.getName();
		String memberId = "user1";

		Gson gson = new Gson();
		List<String> list = new ArrayList<String>();
		list.add(gson.toJson(service.getListOfUserIhaveblocked(memberId)));
		list.add(gson.toJson(service.getListOfUserWhoHaveAddedMe(memberId)));
		
		String fSetting = list.toString();
		
		return fSetting;
	}
	
}
