package com.tinc.web.entity;

public class ChatMessage {
	String type;
	int chatId;// 채팅방 번호
	String memberId;  // 보낸 아이디
	String nickName; // 보낸 사람 닉네임
	String profileImg; // 보낸사람 프로필사진
	String invitedId; // 초대된 아이디  
	String exitId; // 방 나간 아이디 
	String content; // 텍스트, 메모
	String contentMode; // 텍스트 길 경우
	String fileName; // 파일이름
	String sharefile; // 공유된 파일 경로
	String date; // 기록된 날자
	String time; // 시간 
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getInvitedId() {
		return invitedId;
	}
	public void setInvitedId(String invitedId) {
		this.invitedId = invitedId;
	}
	public String getExitId() {
		return exitId;
	}
	public void setExitId(String exitId) {
		this.exitId = exitId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentMode() {
		return contentMode;
	}
	public void setContentMode(String contentMode) {
		this.contentMode = contentMode;
	}
	public String getSharefile() {
		return sharefile;
	}
	public void setSharefile(String sharefile) {
		this.sharefile = sharefile;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
