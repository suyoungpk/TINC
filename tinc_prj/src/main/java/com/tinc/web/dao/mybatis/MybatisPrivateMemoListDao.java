package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.PrivateMemoListDao;
import com.tinc.web.entity.PrivateMemoList;



@Repository
public class MybatisPrivateMemoListDao implements PrivateMemoListDao {
	private SqlSession sqlSession;
	private PrivateMemoListDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(PrivateMemoListDao.class);
	}

	@Override
	public List<PrivateMemoList> getList(String mId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getList(mId);
	}

	@Override
	public PrivateMemoList get(int id, String mId)
	{
		// TODO Auto-generated method stub
		return mapperDao.get(id, mId);
	}

	@Override
	public int insert(PrivateMemoList privateMemoList)
	{
		// TODO Auto-generated method stub
		return mapperDao.insert(privateMemoList);
	}

	@Override
	public int update(PrivateMemoList privateMemoList)
	{
		// TODO Auto-generated method stub
		return mapperDao.update(privateMemoList);
	}

	@Override
	public int delete(int id, String mId)
	{
		// TODO Auto-generated method stub
		return mapperDao.delete(id, mId);
	}

	@Override
	public PrivateMemoList getByMemberId(String mId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getByMemberId(mId);
	}
	
	
}
