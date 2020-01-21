package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.MemoCard;


public interface MemoCardDao
{
	List<MemoCard> getList();
	List<MemoCard> getPrivateMemoCardList(int privateListId);
	List<MemoCard> getGroupMemoCardList(int groupListId);
	MemoCard getById(int id);
	
	int getNewMcId();
	
	int insert(MemoCard memoCard);
	int update(MemoCard memoCard);
	int delete(int id);
}
