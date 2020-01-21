package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.GroupShareViewDao;
import com.tinc.web.entity.GroupShareView;

@Repository
public class MyBatisGroupShareViewDao implements GroupShareViewDao
{
	
	private SqlSession sqlSession;
	private GroupShareViewDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(GroupShareViewDao.class);
	}

	@Override
	public List<GroupShareView> getList(String id)
	{
		// TODO Auto-generated method stub
		return mapperDao.getList(id);
	}

}
