package com.tinc.web.service.tinc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.DueDateDao;
import com.tinc.web.entity.DueDate;
import com.tinc.web.service.DueDateService;

@Service
public class TincDueDateService implements DueDateService
{
	@Autowired
	private DueDateDao dueDateDao;
	
	@Override
	public DueDate getByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return dueDateDao.getByCardId(cardId);
	}

	@Override
	public DueDate getById(int id)
	{
		// TODO Auto-generated method stub
		return dueDateDao.getById(id);
	}

	@Override
	public int insert(DueDate dueDate)
	{
		// TODO Auto-generated method stub
		return dueDateDao.insert(dueDate);
	}

	@Override
	public int update(DueDate dueDate)
	{
		// TODO Auto-generated method stub
		return dueDateDao.update(dueDate);
	}

	@Override
	public int delete(int id)
	{
		// TODO Auto-generated method stub
		return dueDateDao.delete(id);
	}

	@Override
	public int deleteByCardId(int cardId)
	{
		// TODO Auto-generated method stub
		return dueDateDao.deleteByCardId(cardId);
	}

}
