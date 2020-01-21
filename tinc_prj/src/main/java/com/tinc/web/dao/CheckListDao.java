package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.CheckList;

public interface CheckListDao
{
	List<CheckList> getList();
	List<CheckList> getListByCardId(int cardId);
	CheckList get(int id);
	CheckList getNewCheckListByCardId(int cardId);
	
	int getNewClIdByCardId(int cardId);
	
	int insert(CheckList checkList);
	int update(CheckList checkList);
	int delete(int id);
}
