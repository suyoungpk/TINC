package com.tinc.web.entity;

public class BlackList {
	
	private String memberId;
	private String blackId;
	
	public BlackList() {
		// TODO Auto-generated constructor stub
	}

	public BlackList(String memberId, String blackId) {
		this.memberId = memberId;
		this.blackId = blackId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBlackId() {
		return blackId;
	}

	public void setBlackId(String blackId) {
		this.blackId = blackId;
	}

	@Override
	public String toString() {
		return "BlackList [memberId=" + memberId + ", blackId=" + blackId + "]";
	}
	
}
