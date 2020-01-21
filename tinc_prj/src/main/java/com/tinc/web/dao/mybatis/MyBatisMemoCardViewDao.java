package com.tinc.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tinc.web.dao.MemoCardViewDao;
import com.tinc.web.entity.MemoCardView;

@Repository
public class MyBatisMemoCardViewDao implements MemoCardViewDao 
{
	private SqlSession sqlSession;
	private MemoCardViewDao mapperDao;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
		mapperDao = sqlSession.getMapper(MemoCardViewDao.class);
	}

	@Override
	public List<MemoCardView> getList(int mcId)
	{
		// TODO Auto-generated method stub
		return mapperDao.getList(mcId);
	}

}
