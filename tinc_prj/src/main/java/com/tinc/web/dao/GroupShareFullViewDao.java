package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.GroupShareFullView;

public interface GroupShareFullViewDao
{
	List<GroupShareFullView> getListById(String id);

}
