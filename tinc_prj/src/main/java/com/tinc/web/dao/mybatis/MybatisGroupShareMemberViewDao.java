package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.GroupShareMemberViewDao;
import com.tinc.web.entity.GroupShareMemberView;

@Repository
public class MybatisGroupShareMemberViewDao implements GroupShareMemberViewDao
{
	
	private SqlSession sqlSession;
	private GroupShareMemberViewDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(GroupShareMemberViewDao.class);
	}

	@Override
	public List<GroupShareMemberView> getList()
	{
		// TODO Auto-generated method stub
		return mapperDao.getList();
	}

}
