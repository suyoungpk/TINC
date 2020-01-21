package com.tinc.web.dao;


import com.tinc.web.entity.DueDate;

public interface DueDateDao
{
	DueDate getByCardId(int cardId);
	DueDate getById(int id);
	
	int insert(DueDate dueDate);
	int update(DueDate dueDate);
	int delete(int id);
	int deleteByCardId(int cardId);
}
