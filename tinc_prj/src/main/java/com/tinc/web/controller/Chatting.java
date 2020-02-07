package com.tinc.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tinc.web.entity.BlackList;
import com.tinc.web.entity.ChatMessage;
import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.FriendsList;
import com.tinc.web.entity.GroupMemoList;
import com.tinc.web.entity.Member;
import com.tinc.web.entity.MemoCard;
import com.tinc.web.entity.UploadFiles;
import com.tinc.web.service.ChattingService;
import com.tinc.web.service.GroupMemoListService;
import com.tinc.web.service.MemberService;
import com.tinc.web.service.MemoCardService;
import com.tinc.web.service.UploadFilesService;

@Controller
@RequestMapping("/chat/")
public class Chatting {
	@Autowired
	ChattingService chattingService;
	
	@Autowired 
	 private MemberService memberService;
	
	@Autowired
	UploadFilesService uploadFilesService;
	
	@Autowired
	private GroupMemoListService groupMemoListService;
	
	@Autowired
	private MemoCardService memoCardService;
	
	@GetMapping("list")
	public String chat(Principal principal,Model model) {
		String userId = principal.getName();
		//String userId = "user2";
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
				for (Member m : ms) 
					if(!m.getId().equals(userId))
						map.put("img", m.getProfileImg()); //대화상대 이미지 가져오기
			}
			type.add(map);
		}
		model.addAttribute("list", list); // 채팅목록 전달 
		model.addAttribute("type", type); // 채팅타입 전달 
//		return "chatting/chat-list";
		return "main";
	}
	
	@GetMapping("add")
	public String chatAdd() {
		return "chatting/chat-add";
	}
	
	@PostMapping("add")
	public String chatAdd(Principal principal, String memberIds, String title,HttpServletRequest request) {
		String userId = principal.getName();
		//String userId = "user2";
		String[] members = memberIds.split(",");
		//System.out.println(title);
		int result = chattingService.createChattingRoom(new ChattingRoom(userId,title));// 방장 먼저 개설		
		if(result == 1) {
			int chatId = chattingService.getChattingRoomId(userId); // 개설한 채팅 아이디가져오기
			mkFile(userId,chatId,request);
			groupMemoListService.insert(new GroupMemoList(title,chatId,userId));
			//System.out.println(chatId);
			for (int i = 0; i < members.length; i++) { 
				chattingService.inviteMember(chatId, members[i]); // 초대 완료 
				mkFile(members[i],chatId,request);
				groupMemoListService.insert(new GroupMemoList(title,chatId,members[i]));
			}
			
		}
		
		return "redirect:list";
	}
	
	@GetMapping("{id}")
	public String chat(@PathVariable("id") Integer id, Principal principal,Model model) {
		String userId = principal.getName();
		//String userId = "user2";		
		//System.out.println("id"+id);
		//System.out.println("TITLE"+tincChattingService.get(id, userId).getTitle());
		ChattingRoom chattingroom = chattingService.get(id, userId);	
		List<Member> list = chattingService.getMembers(id);
		String type = "";
		if(list.size()>2) type="G";
		else type="P";
		chattingService.chgStatus(id, userId); // 자동 읽음 표시
		model.addAttribute("id",id);
		model.addAttribute("roomType",type);
		model.addAttribute("title",chattingroom.getTitle());
		model.addAttribute("isOwner",chattingroom.isOwner());
		model.addAttribute("member",memberService.get(userId));
		
		return "chatting/chatting";
	}
	
	@PostMapping("{id}")
	public String chat(@PathVariable("id") Integer id, String memberId,
			@RequestParam(required = false) UploadFiles uploadFiles, @RequestParam("file") MultipartFile file,
			Principal principal, HttpServletRequest request) {
//		@RequestParam(required=false) 
		String fileNames = "";
		String fileName = file.getOriginalFilename();
		fileNames += (fileName + ",");

		ServletContext application = request.getServletContext();
		String urlPath = "/upload";
		String realPath = application.getRealPath(urlPath);

		File file1 = new File(realPath);
		if (!file1.exists()) {
			file1.mkdirs();
		} else {
			System.out.println("경로존재함");
		}

		InputStream fis;
		try {
			fis = file.getInputStream();

			FileOutputStream fos = new FileOutputStream(realPath + File.separator + fileName);

			byte[] buf = new byte[1024];

			int size = 0;
			while ((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();

		} catch (IOException e) {
			return "redirect:/error?code=500";
		}
		uploadFiles.setChattingRoomId(id);
		uploadFiles.setMemberId(memberId);
		uploadFiles.setFiles(file.getOriginalFilename());
		uploadFilesService.uploadFiles(uploadFiles);

		return "redirect:chatting/chatting";
	}

	@PostMapping("upload")
	@ResponseBody
	public String upload(int id, String memberId, UploadFiles uploadFiles, @RequestParam("file") MultipartFile file,
			HttpServletRequest request) {

		String fileName = file.getOriginalFilename();

		Date date = new Date();

		System.out.println(date.getTime());

		ServletContext application = request.getServletContext();
		String urlPath = "/resource/upload";
		String realPath = application.getRealPath(urlPath);

		File file1 = new File(realPath);
		if (!file1.exists()) {
			file1.mkdirs();
		} else {
//			System.out.println("경로존재함");
		}

		InputStream fis;
		try {
			fis = file.getInputStream();

			FileOutputStream fos = new FileOutputStream(realPath + File.separator + date.getTime() + "_" + fileName);

			byte[] buf = new byte[1024];
			int size = 0;
			while ((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();

		} catch (IOException e) {
			return "redirect:/error?code=500";
		}

		uploadFiles.setChattingRoomId(id);
		uploadFiles.setMemberId(memberId);
		uploadFiles.setFiles(file.getOriginalFilename());
		uploadFilesService.uploadFiles(uploadFiles);

		return date.getTime() + "_" + fileName;
	}
	
	@GetMapping("{id}/setting")	 
	public String chatSetting(@PathVariable Integer id, Principal principal,Model model) {
		String userId = principal.getName();
		//String userId = "user2";	
		ChattingRoom chatroom  = chattingService.get(id, userId);
		List<Member> list = chattingService.getMembers(id);
		for (Member m : list) 
			if(m.getId().equals(userId)) {
				list.remove(m);
				break;
			}
		model.addAttribute("auth",chatroom.getAuthority());		
		model.addAttribute("list",list);
		return "chatting/setting";
	}
	@GetMapping("{id}/inviteMenu")	 
	public String inviteMenu() {
		return "chatting/chat-invite";
	}
	
	@PostMapping("{id}/invite")	 
	@ResponseBody
	public String inviteMember(@PathVariable Integer id, Principal principal,Model model, String memberIds,HttpServletRequest request) {
		String userId = principal.getName();
		//String userId = "user2";	
		String[] members = memberIds.split(",");
		//System.out.println("inviteMember:"+members[0]);
		String title = chattingService.get(id,userId).getTitle();
		JSONArray list = new JSONArray();
		for (int i = 0; i < members.length; i++) { 
			chattingService.inviteMember(id, members[i]); // 초대 완료 
			mkFile(members[i],id,request);
			groupMemoListService.insert(new GroupMemoList(title,id,members[i]));
			Member m = memberService.get(members[i]);
			JSONObject oj = new JSONObject();
			oj.put("memberId", m.getId());
			oj.put("nickName", m.getNickName());
			//String oj = gson.toJson(m);
			list.add(oj);
		}
		//System.out.println(list.toJSONString());
		return list.toJSONString();
	}
	@GetMapping("{id}/get") // 대화내용 가져오기
	@ResponseBody
	public String getChat(@PathVariable Integer id, Principal principal,HttpServletRequest request) throws FileNotFoundException, IOException, ParseException { // 파일 데이터 가져오기
		String userId = principal.getName();
		//String userId = "user2";		
		String filePath = "/WEB-INF/storage/chat";
		String fileName = userId+id+".json";
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath(filePath);	
		String fileRealLink = realPath+File.separator+fileName;
		//System.out.println(realPath);
		JSONParser parser = new JSONParser();
		File file = new File(fileRealLink);
	    if(!file.exists()) {
	    	mkFile(userId,id,request);
	    }
		FileReader filecheck = new FileReader(fileRealLink);
		
		JSONArray jo = new JSONArray();
		if(filecheck.read() != -1) {
			Object obj = parser.parse(new FileReader(fileRealLink));
			jo = (JSONArray) obj;
		}
		String json = new Gson().toJson(jo);
		return json;
	}
	
	@PostMapping("{id}/save") // 대화 저장
	@ResponseBody
	public void saveChat(@PathVariable Integer id, Principal principal,HttpServletRequest request,String data) throws FileNotFoundException, IOException, ParseException { // 파일 데이터 가져오기
		String userId = principal.getName();        
		//String userId = "user2";
		String filePath = "/WEB-INF/storage/chat";
		String fileName = userId+id+".json";
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(filePath);
		String fileRealLink = realPath+File.separator+fileName;
		//System.out.println(data);
		
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(data);
		JSONObject jobj = (JSONObject) obj;
		//System.out.println(jobj);
		String lastMeg = "";
		switch (jobj.get("type").toString()) {
		case "img":
		case "file":
			lastMeg = "공유된 파일";
			break;
		default:
			lastMeg = jobj.get("content").toString();
			break;
		}
		chattingService.saveLast(id,userId,lastMeg);
		
		JSONArray jo = null;
		 if(new FileReader(fileRealLink).ready()) {
			 obj = parser.parse(new FileReader(fileRealLink));
			 jo = (JSONArray) obj;
		 } else {
			 jo = new JSONArray();
		 }
		 jo.add(jobj);
		 FileWriter writer =  null;
		   try {
				 writer = new FileWriter(fileRealLink);
				 writer.write(jo.toJSONString());		
	       } catch (IOException e) {
	           e.printStackTrace();
	       } finally {
				if (writer != null)
					writer.close();
				writer = null;
	       }	
	}
	
	@PostMapping("{id}/getmember") // 멤버정보 가져오기
	@ResponseBody
	public String getMember(@PathVariable Integer id, Principal principal, String member) { 
		String userId = principal.getName();
		//String userId = "user2";		
		Member m = memberService.get(member);
		ChattingRoom mroom = chattingService.get(id, member);
		
		List<Member> list = chattingService.getMembers(id);
		String type = "";
		if(list.size()>2) type="G";
		else type="P";
		
		JSONObject obj = new JSONObject();
		obj.put("chatType", type);
		obj.put("nickName", m.getNickName());//닉네임
		obj.put("profileImg", m.getProfileImg());// 프로필사진
		obj.put("status", m.getStatusMsg());//상태메시지
		obj.put("blocked", false); 
		obj.put("friend", false);
		obj.put("hasAuth", false);
		if(mroom.getAuthority() != null && mroom.getAuthority().equals("초대"))
			obj.put("hasAuth", true);
		List<Member> blockedList = memberService.getListOfUserIhaveblocked(userId);
		for (Member bm : blockedList)
			if(bm.getId().equals(member))
				obj.put("blocked", true); //차단상태
		
		List<Member> friendList = memberService.getFriendsProfile(userId);
		for (Member bm : friendList)
			if(bm.getId().equals(member))
				obj.put("friend", true); 
		
		return obj.toJSONString();
	}
	
	@PostMapping("{id}/add") // 1:1 채팅
	@ResponseBody
	public String personalChat(@PathVariable Integer id, Principal principal,String member,HttpServletRequest request) {
		String userId = principal.getName();
		//String userId = "user2";
		//기존의 채팅방 여부 검색 
		int dupliRoomId = chattingService.isDuplicatedRoom(userId,member);
		if(dupliRoomId != 0)
			return String.valueOf(dupliRoomId);
		else {
			int chatId = 0;
			Member my = memberService.get(userId);
			Member m = memberService.get(member);
			String title = my.getNickName()+", "+m.getNickName();
			int result = chattingService.createChattingRoom(new ChattingRoom(userId,title));// 방장 먼저 개설	
			
			if(result == 1) {
				chatId = chattingService.getChattingRoomId(userId); // 개설한 채팅 아이디가져오기
				chattingService.inviteMember(chatId, member); // 초대 완료 
				groupMemoListService.insert(new GroupMemoList(title,chatId,userId));
				groupMemoListService.insert(new GroupMemoList(title,chatId,member));
				mkFile(userId,chatId,request);
				mkFile(member,chatId,request);
			}
			return String.valueOf(chatId);
		}
	}
	
	@PostMapping("{id}/reject")// 차단 
	public void reject(@PathVariable Integer id, Principal principal,String member) {
		String userId = principal.getName();
		//String userId = "user2";		
		memberService.blockUser(new BlackList(userId,member));
	}
	
	@PostMapping("{id}/addfriend")// 친구추가
	public void addfriend(@PathVariable Integer id, Principal principal,String member) {
		String userId = principal.getName();
		//String userId = "user2";		
		memberService.addFriend(new FriendsList(userId,member));
	}
	
	
	@PostMapping("{id}/clear") // 대화내용 삭제
	public void clear(@PathVariable Integer id, Principal principal,HttpServletRequest request) {
		String userId = principal.getName();
		//String userId = "user2";		
		clearFile(userId,id,request);
	}
	
	@GetMapping("{id}/exit") // 채팅방 나가기
	public String exit(@PathVariable Integer id, Principal principal,HttpServletRequest request) {
		String userId = principal.getName();
		//String userId = "user2";		
		chattingService.exit(id, userId);
		delFile(userId,id,request);
		return "redirect:../list";
	}
	
	@GetMapping("{id}/rejectandexit") // 초대 거부후 나가기
	public String rejectandexit(@PathVariable Integer id, Principal principal,HttpServletRequest request) {
		String userId = principal.getName();
		//String userId = "user7";		
		chattingService.rejectandexit(id, userId);	
		delFile(userId,id,request);
		return "redirect:../list";
	}	
	// 방장 권한
	@PostMapping("{id}/giveinviteright") // 초대권한부여
	public void giveinviteright(@PathVariable Integer id, Principal principal,String member) {
		String userId = principal.getName();
		//String userId = "user2";
		chattingService.addInviteAuth(id, member);
	}
	
	@PostMapping("{id}/withdrawinviteright") // 초대권한박탈
	public void withdrawinviteright(@PathVariable Integer id, Principal principal,String member) {
		String userId = principal.getName();
		//String userId = "user2";		
		chattingService.delInviteAuth(id, member);
	}
	
	@PostMapping("{id}/delauth") // 방장위임
	public void delauth(@PathVariable Integer id, String member) {
		chattingService.delAuth(id, member);
	}
	
	@PostMapping("{id}/ban")// 추방
	public void ban(@PathVariable Integer id, Principal principal,String member,HttpServletRequest request) {
		String userId = principal.getName();
		//String userId = "user2";		
		chattingService.exit(id, member);
		delFile(userId,id,request);
	}
	
	@GetMapping("{id}/chatlist") // 멤버정보 가져오기
	@ResponseBody
	public String getchatlist(@PathVariable Integer id, Principal principal) { 
		String userId = principal.getName();
		List<ChattingRoom> list = chattingService.getList(userId);
		String json="";
		//System.out.println(list.size());
		for (ChattingRoom r : list) 
			if(r.getId() == id) {
				list.remove(r); 
				break;
			}
		//System.out.println(list.size());
		json = new Gson().toJson(list);
		return json;
	}
	
	@PostMapping("{id}/memolist") // 멤버정보 가져오기
	@ResponseBody
	public String getmemolist(@PathVariable Integer id, Principal principal,Integer memo) { 
		String userId = principal.getName();
		GroupMemoList gl = groupMemoListService.get(memo,userId);
		List<MemoCard> list = memoCardService.getGroupMemoCardList(gl.getId());
		//System.out.println(gl.toString());
		String json = "[]";
		if(list.size()>1)
			 json = new Gson().toJson(list);
		System.out.println("json:"+json);
		return json;
	}
	
	/*
	 * @PostMapping("{id}/shareMemotochat") // 멤버정보 가져오기
	 * 
	 * @ResponseBody public String shareMemo(@PathVariable Integer id, Principal
	 * principal,String memo) { String userId = principal.getName(); //GroupMemoList
	 * gl = groupMemoListService.get(memo,userId); //List<MemoCard> list =
	 * memoCardService.getGroupMemoCardList(gl.getId()); ObjectMapper objectMapper =
	 * new ObjectMapper(); MemoCard memoCard = objectMapper.readValue(memo,
	 * MemoCard.class); //System.out.println(gl.toString()); // String json = ""; //
	 * if(list.size()>1) // json = new Gson().toJson(list);
	 * //System.out.println("json:"+json); return json; }
	 */
	
	@GetMapping("searchFriend")
	@ResponseBody
	public String searchFriend(Principal principal) {
		String userId = principal.getName();
		List<Member> list = memberService.getFriendsProfile(userId);
		String json = new Gson().toJson(list);
		return json;
	}
	
	@PostMapping("searchFriend")
	@ResponseBody
	public String searchFriend(Principal principal,String key) {
		String userId = principal.getName();
		Map map = new HashMap();
		map.put("item1", userId);
		map.put("item2", key);
		List<Member> list = memberService.searchFriendsToAddToTheChat(map);
		
		String json = new Gson().toJson(list);
		return json;
	}
	
	@GetMapping("{id}/searchFriend")
	@ResponseBody
	public String searchFriendInChat(@PathVariable Integer id,Principal principal) {
		String userId = principal.getName();

		List<Member> list = memberService.getFriendsProfile(userId);
		//System.out.println(list);
		List<Member> chatMembers = chattingService.getMembers(id);
		for (Member cm : chatMembers) {
			 for (Iterator<Member> it = list.iterator(); it.hasNext();) {
				 	Member m = it.next();
				 	if(list.size() < 1) break;
					if(cm.getId().equals(m.getId()))
						it.remove();	
			 }
		}
				
		String json = new Gson().toJson(list);
		return json;
	}
	
	@PostMapping("{id}/searchFriend")
	@ResponseBody
	public String searchFriendInChat(@PathVariable Integer id,Principal principal,String key) {
		String userId = principal.getName();
		Map map = new HashMap();
		map.put("item1", userId);
		map.put("item2", key);
		List<Member> list = memberService.searchFriendsToAddToTheChat(map);
		//System.out.println(list);
		List<Member> chatMembers = chattingService.getMembers(id);
		for (Member cm : chatMembers) {
			 for (Iterator<Member> it = list.iterator(); it.hasNext();) {
				 	Member m = it.next();
				 	if(list.size() < 1) break;
					if(cm.getId().equals(m.getId()))
						it.remove();	
			 }
		}
		String json = new Gson().toJson(list);
		return json;
	}
	
	public void mkFile(String userId,int chatId,HttpServletRequest request) {
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
			fos.close();
			//System.out.println(realPath+File.separator+fileName);
		} catch (IOException e) {}
	}
	
	public void clearFile(String userId,int chatId,HttpServletRequest request) {
		//파일 만들기 
		String filePath = "/WEB-INF/storage/chat";
		String fileName = userId+chatId+".json";
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath(filePath);		
		try {
			FileWriter fos = new FileWriter(realPath+File.separator+fileName);
			fos.close();
		} catch (IOException e) {}
	}
	public void delFile(String userId,int chatId,HttpServletRequest request) {
		//파일 만들기 
		String filePath = "/WEB-INF/storage/chat";
		String fileName = userId+chatId+".json";
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath(filePath);		
		File file = new File(realPath);
		if(file.exists())
			file.delete();
	}
}
