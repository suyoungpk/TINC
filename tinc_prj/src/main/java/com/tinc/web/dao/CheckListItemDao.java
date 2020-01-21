package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.CheckListItem;

public interface CheckListItemDao
{
	List<CheckListItem> getList();
	List<CheckListItem> getListByCheckListId(int clId);
	CheckListItem getById(int id);
	
	int getNewItemId(int checkListId);
	
	int insert(CheckListItem checkListItem);
	int update(CheckListItem checkListItem);
	int delete(int id);
}
