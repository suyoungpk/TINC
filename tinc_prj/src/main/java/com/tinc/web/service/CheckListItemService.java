package com.tinc.web.service;

import java.util.List;

import com.tinc.web.entity.CheckListItem;

public interface CheckListItemService
{
	List<CheckListItem> getList();
	CheckListItem getById(int id);
	
	int getNewItemId(int checkListId);
	
	int insert(CheckListItem checkListItem);
	int update(CheckListItem checkListItem);
	int delete(int id);
}
