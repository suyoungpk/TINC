package com.tinc.web.service.tinc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.ChattingRoomDao;
import com.tinc.web.dao.ChattingRoomOptionDao;
import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;
import com.tinc.web.service.ChattingService;

@Service
public class TincChattingService implements ChattingService{

	@Autowired
	private ChattingRoomDao chattingRoomDao;
	
	@Autowired
	private ChattingRoomOptionDao chattingRoomOptionDao;

	public void createChatFile(ChattingRoom chatroom) {		
		String fileName = chatroom.getChatfile();
		String urlPath = "/WEB-INF/storage/chat";
		
		File file1 = new File(urlPath+File.separator+fileName);
		
		if(!file1.exists())
			file1.mkdirs();		
	}
	
	@Override
	public List<ChattingRoom> getList(String memberId) {// 채팅 리스트
		
		return chattingRoomDao.get(memberId);
	}

	@Override
	public List<Member> getMembers(int chatId) {// 채팅멤버리스트
		return chattingRoomDao.getMembers(chatId);
	}

	@Override
	public ChattingRoom get(int chatId, String memberId) { // 채팅방 정보 
		return chattingRoomDao.getByMember(chatId, memberId);
	}

	@Override
	public int createChattingRoom(ChattingRoom chatroom) {
		return chattingRoomDao.createRoom(chatroom);
	}

	@Override
	public int inviteMember(int chatId, String memberId) { // 채팅멤버 추가
		ChattingRoom chatroom = chattingRoomDao.getByOwner(chatId);
		ChattingRoom invitedchatroom = new ChattingRoom(chatId,memberId, chatroom.getTitle(),false,"","","");
		return chattingRoomDao.inviteRoom(invitedchatroom);
	}

	@Override
	public int exit(int chatId, String memberId) {// 채팅방 나가기
		ChattingRoom chatroom = chattingRoomDao.getByOwner(chatId);
		
		if(memberId.equals(chatroom.getId())) {
			List<Member> members = chattingRoomDao.getMembers(chatId);
			for (Member m : members) {// 멤버들 다 내보내기 
				//ChattingRoom mChatroom = chatroomDao.get(m.getId());
				//mChatroom.getChatfile();
				//chatroomDao.delete(chatId, m.getId()); 
			}
		   return 1;
		}
		return chattingRoomDao.delete(chatId, memberId);
	}

	@Override
	public void delAuth(int chatId, String memberId) { // 방장 위임
		ChattingRoom chatRoomOwner = chattingRoomDao.getByOwner(chatId); // 방장정보
		ChattingRoom chatRoomMember = chattingRoomDao.getByMember(chatId,memberId); // 위임할 멤버 정보
		if(!chatRoomOwner.getMemberId().equals(memberId)) {
			chatRoomOwner.setOwner(false);
			chatRoomMember.setOwner(true);
			chattingRoomDao.update(chatRoomOwner);
			chattingRoomDao.update(chatRoomMember);
		}
	}
	@Override
	public int addInviteAuth(int chatId, String memberId) {
		ChattingRoom chatRoom = chattingRoomDao.getByMember(chatId,memberId); // 위임할 멤버 정보
		chatRoom.setAuthority("초대");
		return chattingRoomDao.update(chatRoom);
	}
	@Override
	public int delInviteAuth(int chatId, String memberId) {
		ChattingRoom chatRoom = chattingRoomDao.getByMember(chatId,memberId); // 수정할 멤버 정보
		chatRoom.setAuthority(null);
		return chattingRoomDao.update(chatRoom);
	}
	@Override
	public int chgTitle(int chatId, String memberId, String title) {
		ChattingRoom chatRoom = chattingRoomDao.getByMember(chatId,memberId); // 멤버 채팅방 정보
		chatRoom.setTitle(title);
		return chattingRoomDao.update(chatRoom);
	}
	@Override
	public int getChattingRoomId(String ownerId) {
		return chattingRoomDao.getChattingRoomId(ownerId);
	}
	@Override
	public boolean isRejectList(int chatId, String memberId) { // 거부한 이력조회
		return chattingRoomOptionDao.isonList(chatId, memberId);
	}

	@Override
	public int insertRejectList(int chatId, String memberId) { // 초대거부
		return chattingRoomOptionDao.insert(chatId, memberId);
	}
	
}
