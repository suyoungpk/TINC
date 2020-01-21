package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.FriendsShareView;

public interface FriendsShareViewDao
{
	List<FriendsShareView> getList(String id);
}
