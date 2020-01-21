package com.tinc.web.service;

import java.util.List;

import com.tinc.web.entity.CheckList;

public interface CheckListService
{
	List<CheckList> getList();
	List<CheckList> getListByCardId(int cardId);
	CheckList get(int id);
	CheckList getNewCheckListByCardId(int cardId);
	
	int insert(CheckList checkList);
	int update(CheckList checkList);
	int delete(int id);
}
