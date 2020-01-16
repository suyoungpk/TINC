package com.tinc.web.dao;

public interface ChattingRoomOptionDao
{
	boolean isonList(int chatId,String memberId);// 거부 여부 조회
	int insert(int chatId,String memberId);// 채팅방 설정 추가(초대 거부)
}
