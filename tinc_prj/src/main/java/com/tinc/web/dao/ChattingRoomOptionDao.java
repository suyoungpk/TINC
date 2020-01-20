package com.tinc.web.dao;

import org.apache.ibatis.annotations.Param;

public interface ChattingRoomOptionDao
{
	boolean isonList(@Param("id") int chatId,@Param("memberId") String memberId);// 거부 여부 조회
	int insert(@Param("id") int chatId,@Param("memberId") String memberId);// 채팅방 설정 추가(초대 거부)
}
