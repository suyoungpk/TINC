package com.tinc.web.entity;

public class UploadFiles {
	private int id;
	private int chattingRoomId;
	private String memberId;
	private String files;

	public UploadFiles(int id, int chattingRoomId, String memberId, String files) {
		this.id = id;
		this.chattingRoomId = chattingRoomId;
		this.memberId = memberId;
		this.files = files;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChattingRoomId() {
		return chattingRoomId;
	}

	public void setChattingRoomId(int chattingRoomId) {
		this.chattingRoomId = chattingRoomId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "UploadFiles [id=" + id + ", chattingRoomId=" + chattingRoomId + ", memberId=" + memberId + ", files="
				+ files + "]";
	}
}
