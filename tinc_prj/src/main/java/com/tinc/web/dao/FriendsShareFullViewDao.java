package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.FriendsShareFullView;

public interface FriendsShareFullViewDao
{
	List<FriendsShareFullView> getList(String id);
}
