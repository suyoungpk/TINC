package com.tinc.web.service;

import java.util.List;

import com.tinc.web.entity.FriendsShareFullView;
import com.tinc.web.entity.FriendsShareView;
import com.tinc.web.entity.GroupShareFullView;
import com.tinc.web.entity.GroupShareMemberView;
import com.tinc.web.entity.GroupShareView;

public interface MemoShareService
{
	List<FriendsShareView> getFriendsShareViewList(String id);
	List<FriendsShareFullView> getFriendsShareFullViewList(String id);
	List<GroupShareView> getGroupShareViewList(String id);
	List<GroupShareFullView> getGroupShareFullViewList(String id);
	List<GroupShareMemberView> getGroupShareMemberViewList();

	int hasPrivateChatRoom(String id1, String id2);
	
	int shareMemoToFriends(String mId, int mcId);
	int shareMemoToChattingRoom(String mId, int gsId, int mcId);
}
