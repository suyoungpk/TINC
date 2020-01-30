package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.ChattingRoomDao;
import com.tinc.web.entity.ChattingRoom;
import com.tinc.web.entity.Member;

@Repository
public class MybatisChattingRoomDao implements ChattingRoomDao{
	
	private SqlSession sqlSession;
	private ChattingRoomDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(ChattingRoomDao.class);
	}
	
	@Override
	public List<ChattingRoom> get(String memberId) {
		return mapperDao.get(memberId);
	}

	@Override
	public List<Member> getMembers(int chatId) {
		return mapperDao.getMembers(chatId);
	}

	@Override
	public ChattingRoom getByMember(int chatId, String memberId) {
		System.out.println("chatId"+chatId+", memberId:"+memberId);
		return mapperDao.getByMember(chatId, memberId);
	}

	@Override
	public ChattingRoom getByOwner(int chatId) {
		return mapperDao.getByOwner(chatId);
	}

	@Override
	public int delete(int chatId, String memberId) {
		return mapperDao.delete(chatId, memberId);
	}

	@Override
	public int update(ChattingRoom chatroom) {
		return mapperDao.update(chatroom);
	}

	@Override
	public int createRoom(ChattingRoom chatroom) {
		return mapperDao.createRoom(chatroom);
	}

	@Override
	public int inviteRoom(ChattingRoom chatroom) {
		return mapperDao.inviteRoom(chatroom);
	}

	@Override
	public int getChattingRoomId(String ownerId) {
		return mapperDao.getChattingRoomId(ownerId);
	}

	@Override
	public int updateLast(int chatId, String memberId, String meg) {
		return mapperDao.updateLast(chatId,memberId,meg);
	}
	
	@Override
	public int updateStatus(int chatId, String memberId) {
		return mapperDao.updateStatus(chatId,memberId);
	}
	
	@Override
	public List<Integer> getPersonalRooms() {
		return mapperDao.getPersonalRooms();
	}

	

}
