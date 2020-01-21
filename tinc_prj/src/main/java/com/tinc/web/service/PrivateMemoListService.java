package com.tinc.web.service;

import java.util.List;

import com.tinc.web.entity.PrivateMemoList;

public interface PrivateMemoListService
{
	
	List<PrivateMemoList> getPrivateMemoList(String mId);
	PrivateMemoList get(int id, String mId);
	
	int insert(PrivateMemoList privateMemoList);
	int update(PrivateMemoList privateMemoList);	
	int delete(int id, String mId);
}
