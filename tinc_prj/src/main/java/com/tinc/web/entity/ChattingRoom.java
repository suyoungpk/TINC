package com.tinc.web.entity;

public class ChattingRoom {
	private int id;
	private String memberId;
	private String title;
	private boolean owner;
	private String authority;
	private String notice;
	private String meg;
	private boolean status;
	
	public ChattingRoom(int id, String memberId, String title, boolean owner, String authority, String notice,
			String meg, boolean status) {
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.owner = owner;
		this.authority = authority;
		this.notice = notice;
		this.meg = meg;
		this.status = status;
	}
	public ChattingRoom(String memberId, String title) {// for create room by owner
		this.memberId = memberId;
		this.title = title;
	}
	public ChattingRoom(int id, String memberId, String title, boolean owner, String meg) {
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.owner = owner;
		this.meg = meg;
	}
	public ChattingRoom(int id, String memberId, String title, boolean owner, String authority, String notice,
			String meg) {
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.owner = owner;
		this.authority = authority;
		this.notice = notice;
		this.meg = meg;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isOwner() {
		return owner;
	}
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getMeg() {
		return meg;
	}
	public void setMeg(String meg) {
		this.meg = meg;
	}
}
