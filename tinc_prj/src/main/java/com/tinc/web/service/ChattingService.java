package com.tinc.web.service;

import java.util.List;

import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;

public interface ChattingService {
	List<ChattingRoom> getList(String memberId);//채팅 리스트 by memberId 
	List<Member> getMembers(int chatId);//채팅방에 참여한 회원들 by chatroomId
	ChattingRoom get(int chatId,String memberId);//채팅방 정보  by memberId
	
	void delAuth(int chatId,String memberId);//권한 위임
	int addInviteAuth(int chatId,String memberId);//초대 권한 추가
	int delInviteAuth(int chatId,String memberId);//초대 권한 제거
	
	int createChattingRoom(ChattingRoom chatroom);//채팅 방 만들기
	int getChattingRoomId(String ownerId); // 방장이 만든 채팅방 정보 가져오기
	int inviteMember(int chatId,String memberId);//채팅방 회원 추가 (초대 수락)
	int exit(int chatId,String memberId); // 채팅방 나가기 
	int rejectandexit(int chatId,String memberId); // 채팅방 나가기 
	int chgTitle(int chatId,String memberId,String title);//방제 수정
	int saveLast(int chatId,String memberId,String meg);// 마지막 메시지 저장
	
	boolean isRejectList(int chatId,String memberId);// 거부 리스트 조회
	int insertRejectList(int chatId,String memberId);// 채팅방 설정 추가(초대 거부)
	int isDuplicatedRoom(String userId,String memberId); // 1:1 채팅방 여부 
}
