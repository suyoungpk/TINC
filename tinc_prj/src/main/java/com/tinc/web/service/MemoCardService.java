package com.tinc.web.service;

import java.util.List;

import com.tinc.web.entity.MemoCard;
import com.tinc.web.entity.MemoCardView;

public interface MemoCardService
{
	List<MemoCardView> getMemoCardViewList(int mcId);
	
	List<MemoCard> getList();
	List<MemoCard> getPrivateMemoCardList(int privateListId);
	List<MemoCard> getGroupMemoCardList(int groupListId);
	MemoCard getById(int id);
	
	int getNewMcId();
	
	int insert(MemoCard memoCard);
	int update(MemoCard memoCard);
	int delete(int id);
	
}
