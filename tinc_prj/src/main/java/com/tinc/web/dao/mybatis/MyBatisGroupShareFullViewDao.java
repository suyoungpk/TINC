package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.GroupShareFullViewDao;
import com.tinc.web.entity.GroupShareFullView;

@Repository
public class MyBatisGroupShareFullViewDao implements GroupShareFullViewDao
{
	private SqlSession sqlSession;
	private GroupShareFullViewDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(GroupShareFullViewDao.class);
	}

	@Override
	public List<GroupShareFullView> getListById(String id)
	{
		// TODO Auto-generated method stub
		return mapperDao.getListById(id);
	}	

}
