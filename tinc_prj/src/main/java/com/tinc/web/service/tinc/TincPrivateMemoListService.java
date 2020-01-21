package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.PrivateMemoListDao;
import com.tinc.web.entity.PrivateMemoList;
import com.tinc.web.service.PrivateMemoListService;

@Service
public class TincPrivateMemoListService implements PrivateMemoListService{
	
	@Autowired
	PrivateMemoListDao privateMemoListDao;	

	@Override
	public List<PrivateMemoList> getPrivateMemoList(String mId)
	{
		// TODO Auto-generated method stub
		return privateMemoListDao.getList(mId);
	}

	@Override
	public PrivateMemoList get(int id, String mId)
	{
		// TODO Auto-generated method stub
		return privateMemoListDao.get(id, mId);
	}

	@Override
	public int insert(PrivateMemoList privateMemoList)
	{
		// TODO Auto-generated method stub
		return privateMemoListDao.insert(privateMemoList);
	}

	@Override
	public int update(PrivateMemoList privateMemoList)
	{
		// TODO Auto-generated method stub
		return privateMemoListDao.update(privateMemoList);
	}

	@Override
	public int delete(int id, String mId)
	{
		// TODO Auto-generated method stub
		return privateMemoListDao.delete(id, mId);
	}

}
