package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.GroupMemoListDao;
import com.tinc.web.entity.GroupMemoList;

@Repository
public class MybatisGroupMemoListDao implements GroupMemoListDao{
	
	private SqlSession sqlSession;
	private GroupMemoListDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(GroupMemoListDao.class);
	}
	
	@Override
	public List<GroupMemoList> getList(String mId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getList(mId);
	}

	@Override
	public GroupMemoList get(int gsId, String mId)
	{
		// TODO Auto-generated method stub
		return mapperDao.get(gsId, mId);
	}

	@Override
	public int insert(GroupMemoList groupMemoList)
	{
		// TODO Auto-generated method stub
		return mapperDao.insert(groupMemoList);
	}

	@Override
	public int update(GroupMemoList groupMemoList)
	{
		// TODO Auto-generated method stub
		return mapperDao.update(groupMemoList);
	}

	@Override
	public int delete(int id, String mId)
	{
		// TODO Auto-generated method stub
		return mapperDao.delete(id, mId);
	}

	
}
