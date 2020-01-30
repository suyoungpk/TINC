package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.CheckListDao;
import com.tinc.web.entity.CheckList;

@Repository
public class MybatisCheckListDao implements CheckListDao
{
	private SqlSession sqlSession;
	private CheckListDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(CheckListDao.class);
	}

	@Override
	public List<CheckList> getList()
	{
		// TODO Auto-generated method stub
		return mapperDao.getList();
	}
	
	@Override
	public List<CheckList> getListByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getListByCardId(cardId);
	}


	@Override
	public CheckList get(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.get(id);
	}

	
	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.delete(id);
	}

	@Override
	public int insert(CheckList checkList)
	{
		// TODO Auto-generated method stub
		return mapperDao.insert(checkList);
	}

	@Override
	public int update(CheckList checkList)
	{
		// TODO Auto-generated method stub
		return mapperDao.update(checkList);
	}

	@Override
	public CheckList getNewCheckListByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getNewCheckListByCardId(cardId);
	}

	@Override
	public int getNewClIdByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getNewClIdByCardId(cardId);
	}

	

	
}
