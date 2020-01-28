package com.tinc.web.service.tinc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinc.web.dao.GroupMemoListDao;
import com.tinc.web.entity.GroupMemoList;
import com.tinc.web.service.GroupMemoListService;

@Service
public class TincGroupMemoListService implements GroupMemoListService {
	
	@Autowired
	private GroupMemoListDao groupMemoListDao;
	

	@Override
	public List<GroupMemoList> getGroupMemoList(String mId)
	{
		// TODO Auto-generated method stub
		return groupMemoListDao.getList(mId);
	}

	@Override
	public GroupMemoList get(int chatId, String mId)
	{
		// TODO Auto-generated method stub
		return groupMemoListDao.get(chatId, mId);
	}

	@Override
	public int insert(GroupMemoList groupMemoList)
	{
		// TODO Auto-generated method stub
		return groupMemoListDao.insert(groupMemoList);
	}

	@Override
	public int update(GroupMemoList groupMemoList)
	{
		// TODO Auto-generated method stub
		return groupMemoListDao.update(groupMemoList);
	}

	@Override
	public int delete(int id, String mId)
	{
		// TODO Auto-generated method stub
		return groupMemoListDao.delete(id, mId);
	}

}
