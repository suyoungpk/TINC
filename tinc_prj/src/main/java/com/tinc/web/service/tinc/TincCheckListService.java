package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.CheckListDao;
import com.tinc.web.entity.CheckList;
import com.tinc.web.service.CheckListService;

@Service
public class TincCheckListService implements CheckListService
{
	@Autowired
	private CheckListDao checkListDao;
	
	@Override
	public List<CheckList> getList()
	{
		// TODO Auto-generated method stub
		return checkListDao.getList();
	}
	
	@Override
	public List<CheckList> getListByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return checkListDao.getListByCardId(cardId);
	}

	@Override
	public CheckList get(int id)
	{
		// TODO Auto-generated method stub
		return checkListDao.get(id);
	}


	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return checkListDao.delete(id);
	}

	@Override
	public int insert(CheckList checkList)
	{
		// TODO Auto-generated method stub
		return checkListDao.insert(checkList);
	}

	@Override
	public int update(CheckList checkList)
	{
		// TODO Auto-generated method stub
		return checkListDao.update(checkList);
	}

	@Override
	public CheckList getNewCheckListByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return checkListDao.getNewCheckListByCardId(cardId);
	}

	
}
