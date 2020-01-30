package com.tinc.web.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.DueDateDao;
import com.tinc.web.entity.DueDate;

@Repository
public class MybatisDueDateDao implements DueDateDao
{
	private SqlSession sqlSession;
	private DueDateDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(DueDateDao.class);
	}
	
	
	@Override
	public DueDate getByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getByCardId(cardId);
	}

	@Override
	public DueDate getById(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.getById(id);
	}

	@Override
	public int insert(DueDate dueDate)
	{
		// TODO Auto-generated method stub
		return mapperDao.insert(dueDate);
	}

	@Override
	public int update(DueDate dueDate)
	{
		// TODO Auto-generated method stub
		return mapperDao.update(dueDate);
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.delete(id);
	}


	@Override
	public int deleteByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return mapperDao.deleteByCardId(cardId);
	}
	
}
