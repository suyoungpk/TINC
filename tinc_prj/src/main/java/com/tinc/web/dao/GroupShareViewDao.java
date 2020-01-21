package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.GroupShareView;

public interface GroupShareViewDao
{
	List<GroupShareView> getList(String id);

}
