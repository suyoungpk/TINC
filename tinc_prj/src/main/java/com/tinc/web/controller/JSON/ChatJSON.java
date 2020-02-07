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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;
import com.tinc.web.service.ChattingService;
import com.tinc.web.service.MemberService;

@Controller
@RequestMapping("/jsonTinc/chat/")
public class ChatJSON {

	@Autowired
	ChattingService chattingService;
	@Autowired 
	private MemberService memberService;
	
	@ResponseBody
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
		_list.add(gson.toJson(userId));
		
		cList = _list.toString();
		
		return cList;
	}
	
//	@ResponseBody
//	@GetMapping("{id}")
//	public String chat(@PathVariable("id") Integer id, Principal principal) {
//		
//		List<String> _list = new ArrayList<String>();
//		Gson gson = new Gson();
//		
//		String cList = "";
//		
//		//String userId = principal.getName();
//		String userId = "user2";
//		//String userId = "user2";		
//		//System.out.println("id"+id);
//		//System.out.println("TITLE"+tincChattingService.get(id, userId).getTitle());
//		ChattingRoom chattingroom = chattingService.get(id, userId);	
//		List<Member> list = chattingService.getMembers(id);
//		String type = "";
//		if(list.size()>2) type="G";
//		else type="P";
//		chattingService.chgStatus(id, userId); // 자동 읽음 표시
//		
//		_list.add(gson.toJson(id));
//		_list.add(gson.toJson(type));
//		_list.add(gson.toJson(chattingroom));
//		_list.add(gson.toJson(list));
//		_list.add(gson.toJson(memberService.get(userId)));
//	
//		cList = _list.toString();
//		
//		return cList;
//	}
}
