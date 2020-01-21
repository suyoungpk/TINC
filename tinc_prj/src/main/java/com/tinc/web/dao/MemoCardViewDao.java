package com.tinc.web.dao;

import java.util.List;

import com.tinc.web.entity.MemoCardView;

public interface MemoCardViewDao
{
	List<MemoCardView> getList(int mcId);
}	
