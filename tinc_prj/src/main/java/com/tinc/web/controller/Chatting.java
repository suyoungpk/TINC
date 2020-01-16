package com.tinc.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;
import com.tinc.web.service.ChattingService;

@Controller
@RequestMapping("/chat/")
public class Chatting {
	@Autowired
	ChattingService ChattingService;
	
	/*@Autowired 
	 private MemberService service;
	*/
	@GetMapping("list")
	public String chat(Principal principal,Model model) {
		//String userId = principal.getName();
		String userId = "user2";
		List<ChattingRoom> list = ChattingService.getList(userId);
		
		List<Map<String,String>> type = new ArrayList<>();
		//채팅 타입 분리
		for (ChattingRoom r : list) {
			Map<String,String> map = new HashMap<>();
			List<Member> ms = ChattingService.getMembers(r.getId());
			if(ms.size() > 2) {
				map.put("type", "그룹");
				map.put("img", "");
			}else {
				map.put("type", "개인");
				map.put("img", "");
				/*for (Member m : ms) 
					if(!m.getId().equals(userId))
						map.put("img", m.getProfileImg()); //대화상대 이미지 가져오기
					*/
			}
			type.add(map);
		}
		model.addAttribute("list", list); // 채팅목록 전달 
		model.addAttribute("type", type); // 채팅타입 전달 
		return "chatting/chat-list";
	}
	@GetMapping("add")
	public String chatAdd() {
		return "chatting/chat-add";
	}
	@PostMapping("add")
	public String chatAdd(Principal principal, String memberIds, String title,HttpServletRequest request) {
		//String userId = principal.getName();
		String userId = "user2";
		String[] members = memberIds.split(",");
		//System.out.println(title);
		int result = ChattingService.createChattingRoom(new ChattingRoom(userId,title));// 방장 먼저 개설
		
		if(result == 1) {
			int chatId = ChattingService.getChattingRoomId(userId); // 개설한 채팅 아이디가져오기
			mkfiles(userId,chatId,request);
			//System.out.println(chatId);
			for (int i = 0; i < members.length; i++) { 
				ChattingService.inviteMember(chatId, members[i]); // 초대 완료 
				mkfiles(members[i],chatId,request);
			}
		}
		
		
		return "redirect:list";
	}
	public void mkfiles(String userId,int chatId,HttpServletRequest request) {
		//파일 만들기 
		String filePath = "/WEB-INF/storage/chat";
		String fileName = userId+chatId+".json";
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath(filePath);		
		try {
			File file = new File(realPath);
			
			if(!file.exists())
				file.mkdirs();
			
			FileWriter fos = new FileWriter(realPath+File.separator+fileName);
			//System.out.println(realPath+File.separator+fileName);
		} catch (IOException e) {}
	}
	@GetMapping("{id}")
	public String chat(@PathVariable("id") Integer id, Principal principal,Model model) {
		//String userId = principal.getName();
		String userId = "user2";		
		//System.out.println("id"+id);
		//System.out.println("TITLE"+tincChattingService.get(id, userId).getTitle());
		ChattingRoom chattingroom = ChattingService.get(id, userId);	
		model.addAttribute("title",chattingroom.getTitle());
		model.addAttribute("chatId",id);
		model.addAttribute("isOwner",chattingroom.isOwner());
		model.addAttribute("member",new Member(userId, "홍길동", "", "", "", "",
		         "https://imgnews.pstatic.net/image/056/2019/11/20/0010765789_001_20191120163804442.jpg?type=w647", 0, 0, 0, 0, 0));
		
		return "chatting/chatting";
	}
	@GetMapping("{id}/setting")	
	public String chatSetting(@PathVariable Integer id, Principal principal,Model model) {
		List<Member> list = ChattingService.getMembers(id);
		model.addAttribute("list",list);
		return "chatting/setting";
	}
	@GetMapping("{id}/get")
	@ResponseBody
	public String getChat(@PathVariable Integer id, Principal principal,HttpServletRequest request) throws FileNotFoundException, IOException, ParseException { // 파일 데이터 가져오기
		//String userId = principal.getName();
		String userId = "user2";		
		String filePath = "/WEB-INF/storage/chat";
		String fileName = userId+id+".json";
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath(filePath);	
		String fileRealLink = realPath+File.separator+fileName;
		System.out.println(realPath);
		JSONParser parser = new JSONParser();
		FileReader filecheck = new FileReader(fileRealLink);
		
		JSONArray jo = new JSONArray();
		if(filecheck.read() != -1) {
			Object obj = parser.parse(new FileReader(fileRealLink));
			jo = (JSONArray) obj;
		}
		String json = new Gson().toJson(jo);
		return json;
	}
	
	@PostMapping("{id}/save")
	@ResponseBody
	public void saveChat(@PathVariable Integer id, Principal principal,HttpServletRequest request,String data) throws FileNotFoundException, IOException, ParseException { // 파일 데이터 가져오기
		//String userId = principal.getName();        
		String userId = "user2";		
		String filePath = "/WEB-INF/storage/chat";
		String fileName = userId+id+".json";
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(filePath);
		String fileRealLink = realPath+File.separator+fileName;
		//System.out.println(data);
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(data);
		JSONObject jobj = (JSONObject) obj;
		System.out.println(jobj);
		
		 obj = parser.parse(new FileReader(fileRealLink));
		 JSONArray jo = (JSONArray) obj;
		 jo.add(jobj);
		 FileWriter writer =  null;
		try {
			 writer = new FileWriter(fileRealLink,false);
			 writer.write(jo.toJSONString());		
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
			if (writer != null)
				writer.close();
       }	
	}
	@PostMapping("{id}/rename")
	public void rename(@PathVariable Integer id, Principal principal,String title) {
		//String userId = principal.getName();
		String userId = "user2";		
		ChattingService.chgTitle(id, userId, title);
	}
	@PostMapping("del")
	public String exit(Principal principal,Integer chatId) {
		//String userId = principal.getName();
		String userId = "user2";		
		ChattingService.exit(chatId, userId);		
		return "redirect:list";
	}
	
	@PostMapping("searchFriend")
	@ResponseBody
	public String searchFriend(String key) {
		JSONArray list = new JSONArray();
		JSONObject a = new JSONObject();
		a.put("memberId","user7");
		a.put("profileImg", "https://imgnews.pstatic.net/image/056/2019/11/20/0010765789_001_20191120163804442.jpg?type=w647");
		a.put("status","울적한날");
		JSONObject b = new JSONObject();
		b.put("memberId" , "user10");
		b.put("profileImg","");
		b.put("status","싄나싄나 하하하하하하하");
		list.add(a);
		list.add(b);		
		String json = new Gson().toJson(list);
		return json;
	}
}
