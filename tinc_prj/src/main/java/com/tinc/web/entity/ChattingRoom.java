package com.tinc.web.entity;

public class ChattingRoom {
	private int id;
	private String memberId;
	private String title;
	private boolean owner;
	private String authority;
	private String notice;
	private String chatfile;
	
	public ChattingRoom(String memberId, String title) {// for create room by owner
		this.memberId = memberId;
		this.title = title;
	}
	public ChattingRoom(int id, String memberId, String title, boolean owner, String chatfile) {
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.owner = owner;
		this.chatfile = chatfile;
	}
	public ChattingRoom(int id, String memberId, String title, boolean owner, String authority, String notice,
			String chatfile) {
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.owner = owner;
		this.authority = authority;
		this.notice = notice;
		this.chatfile = chatfile;
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
	public String getChatfile() {
		return chatfile;
	}
	public void setChatfile(String chatfile) {
		this.chatfile = chatfile;
	}
}
