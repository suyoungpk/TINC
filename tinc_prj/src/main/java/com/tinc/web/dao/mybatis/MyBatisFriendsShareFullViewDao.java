package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.FriendsShareFullViewDao;
import com.tinc.web.entity.FriendsShareFullView;

@Repository
public class MyBatisFriendsShareFullViewDao implements FriendsShareFullViewDao
{
	private SqlSession sqlSession;
	private FriendsShareFullViewDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(FriendsShareFullViewDao.class);
	}
	
	@Override
	public List<FriendsShareFullView> getList(String id)
	{
		// TODO Auto-generated method stub
		return mapperDao.getList(id);
	}

}
