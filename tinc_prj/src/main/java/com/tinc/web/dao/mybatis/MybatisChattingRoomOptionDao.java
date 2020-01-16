package com.tinc.web.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.ChattingRoomOptionDao;

@Repository
public class MybatisChattingRoomOptionDao implements ChattingRoomOptionDao {

	private SqlSession sqlSession;
	private ChattingRoomOptionDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(ChattingRoomOptionDao.class);
	}
	
	@Override
	public boolean isonList(int chatId, String memberId) {
		return mapperDao.isonList(chatId, memberId);
	}

	@Override
	public int insert(int chatId, String memberId) {
		return mapperDao.insert(chatId, memberId);
	}

}
