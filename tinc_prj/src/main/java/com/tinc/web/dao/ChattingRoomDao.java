package com.tinc.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;

public interface ChattingRoomDao
{
	List<ChattingRoom> get(@Param("memberId") String memberId);//채팅 리스트 by memberId 
	List<Member> getMembers(@Param("chatId") int chatId);//채팅방에 참여한 회원들 by chatroomId
	ChattingRoom getByMember(@Param("chatId") int chatId,@Param("memberId") String memberId);//나의 채팅방 정보
	ChattingRoom getByOwner(@Param("id") int chatId); // 방장의 채팅방 정보 for getting title
	//일단 방장의 초대로 들어오면 방장의 제목을 따르고, 방장이 아니면 초대한 사람 아이디 붙여서 "??가 초대한 그룹"을 제목으로
	
	int getChattingRoomId(@Param("memberId") String ownerId); // 방장이 만든 채팅방 정보 가져오기
	int createRoom(ChattingRoom chatroom);//방장 채팅 방 만들기
	int inviteRoom(ChattingRoom chatroom);//초대한 회원 채팅 추가
	int delete(@Param("id") int chatId,@Param("memberId") String memberId); // 채팅방 나가기 
	int update(ChattingRoom chatroom);//권한 위임,방제 수정
	int updateLast(@Param("id") int chatId, @Param("memberId") String memberId, @Param("meg") String meg);// 마지막 채팅 저장
}
