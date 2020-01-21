package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.CheckListItemDao;
import com.tinc.web.entity.CheckListItem;
import com.tinc.web.service.CheckListItemService;


@Service
public class TincCheckListItemService implements CheckListItemService
{
	@Autowired
	private CheckListItemDao checkListItemDao;
	
	
	@Override
	public List<CheckListItem> getList()
	{
		// TODO Auto-generated method stub
		return checkListItemDao.getList();
	}

	@Override
	public CheckListItem getById(int id)
	{
		// TODO Auto-generated method stub
		return checkListItemDao.getById(id);
	}

	@Override
	public int insert(CheckListItem checkListItem)
	{
		// TODO Auto-generated method stub
		return checkListItemDao.insert(checkListItem);
	}

	@Override
	public int update(CheckListItem checkListItem)
	{
		// TODO Auto-generated method stub
		return checkListItemDao.update(checkListItem);
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return checkListItemDao.delete(id);
	}

	@Override
	public int getNewItemId(int checkListId)
	{
		// TODO Auto-generated method stub
		return checkListItemDao.getNewItemId(checkListId);
	}

}
