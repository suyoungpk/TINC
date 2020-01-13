package com.tinc.web.entity;

public class FriendsList {
	
	private String memberId;
	private String friendsId;
	
	public FriendsList() {
		// TODO Auto-generated constructor stub
	}
	
	public FriendsList(String memberId, String friendsId) {
		this.memberId = memberId;
		this.friendsId = friendsId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(String friendsId) {
		this.friendsId = friendsId;
	}

	@Override
	public String toString() {
		return "FriendsList [memberId=" + memberId + ", friendsId=" + friendsId + "]";
	}
	
}
