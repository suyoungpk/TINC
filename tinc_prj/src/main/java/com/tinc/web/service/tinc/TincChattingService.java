package com.tinc.web.service.tinc;

import java.util.ArrayList;
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
		ChattingRoom chatroom = chattingRoomDao.getByOwner(chatId); // 방장정보가져오기
		
		if(memberId.equals(chatroom.getMemberId())) { // 방장이라면 모두 나가기
			List<Member> members = chattingRoomDao.getMembers(chatId);// 채팅멤버 정보 가져오기
			for (Member m : members)
				chattingRoomDao.delete(chatId, m.getId()); 
			//System.out.println("방 폭파 완료");
			return 1;
		}
		return chattingRoomDao.delete(chatId, memberId);
	}
	
	@Override
	public int rejectandexit(int chatId, String memberId) {// 채팅방 거부후 나가기
		int ret = chattingRoomDao.delete(chatId, memberId);
		//System.out.println(ret);	
		int ret1 = chattingRoomOptionDao.insert(chatId,memberId);
		//System.out.println(ret1);
		return 1;
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
	public int saveLast(int chatId, String memberId, String meg) {
		return chattingRoomDao.updateLast(chatId,memberId,meg);
	}
	
	@Override
	public int chgStatus(int chatId, String memberId) {
		return chattingRoomDao.updateStatus(chatId, memberId);
	}
	
	@Override
	public int isDuplicatedRoom(String userId, String memberId) {
		List<Integer> roomList = chattingRoomDao.getPersonalRooms();
		
		for (Integer roomId : roomList) {
			List<Member> memberList = chattingRoomDao.getMembers(roomId);
			List<String> list = new ArrayList<>();
			for (Member m : memberList) 
				if(m.getId().equals(userId) || m.getId().equals(memberId))
					list.add(m.getId());
			
			if(list.size() == 2)
				return roomId;
		}
		return 0;
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
