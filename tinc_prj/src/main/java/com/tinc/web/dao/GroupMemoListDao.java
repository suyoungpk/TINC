package com.tinc.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tinc.web.entity.GroupMemoList;


public interface GroupMemoListDao
{
	List<GroupMemoList> getList(String mId);
	GroupMemoList get(@Param("gsId")int gsId, @Param("mId")String mId);

	int insert(GroupMemoList groupMemoList);
	int update(GroupMemoList groupMemoList);	
	int delete(int id, String mId);
}
