package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.FriendsShareViewDao;
import com.tinc.web.entity.FriendsShareView;

@Repository
public class MyBatisFriendsShareViewDao implements FriendsShareViewDao
{
	
	private SqlSession sqlSession;
	private FriendsShareViewDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(FriendsShareViewDao.class);
	}
	
	@Override
	public List<FriendsShareView> getList(String id)
	{
		// TODO Auto-generated method stub
		return mapperDao.getList(id);
	}

}
