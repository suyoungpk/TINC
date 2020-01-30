package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.CheckListItemDao;
import com.tinc.web.entity.CheckList;
import com.tinc.web.entity.CheckListItem;

@Repository
public class MybatisCheckListItemDao implements CheckListItemDao
{
	private SqlSession sqlSession;
	private CheckListItemDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(CheckListItemDao.class);
	}
	
	@Override
	public List<CheckListItem> getList()
	{
		// TODO Auto-generated method stub
		return mapperDao.getList();
	}
	
	@Override
	public CheckListItem getById(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.getById(id);
	}
	
	@Override
	public int insert(CheckListItem checkListItem)
	{
		// TODO Auto-generated method stub
		return mapperDao.insert(checkListItem);
	}
	
	@Override
	public int update(CheckListItem checkListItem)
	{
		// TODO Auto-generated method stub
		return mapperDao.update(checkListItem);
	}
	
	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return mapperDao.delete(id);
	}

	@Override
	public int getNewItemId(int checkListId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getNewItemId(checkListId);
	}

	@Override
	public List<CheckListItem> getListByCheckListId(int clId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getListByCheckListId(clId);
	}
	
	
}
