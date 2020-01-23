package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.MemoCardDao;
import com.tinc.web.dao.MemoCardViewDao;
import com.tinc.web.entity.MemoCard;
import com.tinc.web.entity.MemoCardView;
import com.tinc.web.service.MemoCardService;

@Service
public class TincMemoCardService implements MemoCardService{
	
	@Autowired
	MemoCardDao memoCardDao;
	@Autowired
	MemoCardViewDao memoCardViewDao;
	
	@Override
	public List<MemoCardView> getMemoCardViewList(int mcId)
	{
		// TODO Auto-generated method stub
		return memoCardViewDao.getList(mcId);
	}
	
	@Override
	public List<MemoCard> getList()
	{
		// TODO Auto-generated method stub
		return memoCardDao.getList();
	}
	
	@Override
	public List<MemoCard> getPrivateMemoCardList(int privateListId)
	{
		// TODO Auto-generated method stub
		return memoCardDao.getPrivateMemoCardList(privateListId);
	}

	@Override
	public List<MemoCard> getGroupMemoCardList(int groupListId)
	{
		// TODO Auto-generated method stub
		return memoCardDao.getGroupMemoCardList(groupListId);
	}	

	
	@Override
	public int insert(MemoCard memoCard)
	{
		// TODO Auto-generated method stub
		return memoCardDao.insert(memoCard);
	}
	

	@Override
	public MemoCard getById(int id)
	{
		// TODO Auto-generated method stub
		return memoCardDao.getById(id);
	}

	@Override
	public int update(MemoCard memoCard)
	{
		// TODO Auto-generated method stub
		return memoCardDao.update(memoCard);
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return memoCardDao.delete(id);
	}

	@Override
	public int getNewMcId()
	{
		// TODO Auto-generated method stub
		return memoCardDao.getNewMcId();
	}

	
	
}
