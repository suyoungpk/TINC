package com.tinc.web.controller.JSON;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;
import com.tinc.web.service.ChattingService;

@Controller
@RequestMapping("/jsonTinc/chat/")
public class ChatJSON {

	@Autowired
	ChattingService chattingService;
	
	@GetMapping("list")
	public String chat(Principal principal) {
		
		List<String> _list = new ArrayList<String>();
		Gson gson = new Gson();
		
		String cList = "";
		
		//String userId = principal.getName();
		String userId = "user2";
		List<ChattingRoom> list = chattingService.getList(userId);
		
		List<Map<String,String>> type = new ArrayList<>();
		//채팅 타입 분리
		for (ChattingRoom r : list) {
			Map<String,String> map = new HashMap<>();
			List<Member> ms = chattingService.getMembers(r.getId());
			if(ms.size() > 2) {
				map.put("type", "그룹");
				map.put("img", "");
			}else {
				map.put("type", "개인");
				//map.put("img", "");
				for (Member m : ms) 
					if(!m.getId().equals(userId))
						map.put("img", m.getProfileImg()); //대화상대 이미지 가져오기
					
			}
			type.add(map);
		}
		
		_list.add(gson.toJson(list));
		_list.add(gson.toJson(type));
		
		cList = _list.toString();
		
		return cList;
	}
}
