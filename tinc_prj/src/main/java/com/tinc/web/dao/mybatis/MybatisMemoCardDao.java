package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.MemoCardDao;
import com.tinc.web.entity.MemoCard;

@Repository
public class MybatisMemoCardDao implements MemoCardDao{
	
	SqlSession sqlSession;
	MemoCardDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(MemoCardDao.class);
	}

	@Override
	public List<MemoCard> getList()
	{
		// TODO Auto-generated method stub
		return mapperDao.getList();
	}
	
	@Override
	public List<MemoCard> getPrivateMemoCardList(int privateListId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getPrivateMemoCardList(privateListId);
	}

	@Override
	public List<MemoCard> getGroupMemoCardList(int groupListId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getGroupMemoCardList(groupListId);
	}

	

	@Override
	public int insert(MemoCard memoCard)
	{
		// TODO Auto-generated method stub
		return mapperDao.insert(memoCard);
	}

	
	@Override
	public MemoCard getById(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.getById(id);
	}

	@Override
	public int update(MemoCard memoCard)
	{
		// TODO Auto-generated method stub
		return mapperDao.update(memoCard);
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.delete(id);
	}


	@Override
	public int getNewMcId()
	{
		// TODO Auto-generated method stub
		return mapperDao.getNewMcId();
	}


	
}
