package com.tinc.web.service;

import java.util.List;

import com.tinc.web.entity.GroupMemoList;

public interface GroupMemoListService
{	
	List<GroupMemoList> getGroupMemoList(String mId);
	GroupMemoList get(int id, String mId);

	int insert(GroupMemoList groupMemoList);
	int update(GroupMemoList groupMemoList);	
	int delete(int id, String mId);
}
