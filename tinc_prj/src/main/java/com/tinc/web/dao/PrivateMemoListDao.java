package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.PrivateMemoList;


public interface PrivateMemoListDao
{
	List<PrivateMemoList> getList(String mId);
	PrivateMemoList get(int id, String mId);
	PrivateMemoList getByMemberId(String mId);

	int insert(PrivateMemoList privateMemoList);
	int update(PrivateMemoList privateMemoList);	
	int delete(int id, String mId);

}
