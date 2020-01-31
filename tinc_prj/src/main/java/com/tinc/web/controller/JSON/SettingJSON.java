package com.tinc.web.controller.JSON;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tinc.web.entity.Member;
import com.tinc.web.service.MemberService;

@Controller
@RequestMapping("/jsonTinc/setting")
public class SettingJSON {

	@Autowired
	private MemberService service;

	@ResponseBody
	@GetMapping("")
	public String setting(Principal principal, Member member) {
		String id = "user1";
//	      String id = principal.getName();
		System.out.println(id);

		List<String> list = new ArrayList<String>();
		Gson gson = new Gson();

		String sList = "";

		list.add(gson.toJson(service.get(id)));

		String tmpStr = null;
		if (service.getMyProfile(id).getProfileImg() != null) {
			tmpStr = service.getMyProfile(id).getProfileImg();
			StringBuilder sb = new StringBuilder("http://localhost:8080/resource/upload/member/");
			sb.append(tmpStr);
			
			list.add(gson.toJson(sb));
		}

		sList = list.toString();
		
		return sList;
	}
}
