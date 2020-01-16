package com.tinc.web.entity;

public class Member{
   private String id;
   private String nickName;
   private String password;
   private String phoneNum; 
   private String email;
   private String statusMsg;
   private String profileImg;
   private int emailOpen;
   private int phoneNumOpen;
   private int idOpen;
   private int chattingAlarm;
   private int memoAlarm;
   
   public Member() {
      // TODO Auto-generated constructor stub
   }

   public Member(String id, String nickName, String password, String phoneNum, String email, String statusMsg,
         String profileImg, int emailOpen, int phoneNumOpen, int idOpen, int chattingAlarm, int memoAlarm) {
      this.id = id;
      this.nickName = nickName;
      this.password = password;
      this.phoneNum = phoneNum;
      this.email = email;
      this.statusMsg = statusMsg;
      this.profileImg = profileImg;
      this.emailOpen = emailOpen;
      this.phoneNumOpen = phoneNumOpen;
      this.idOpen = idOpen;
      this.chattingAlarm = chattingAlarm;
      this.memoAlarm = memoAlarm;
   }

   // join 
   public Member(String id, String nickName, String password, String phoneNum, String email) {
      this.id = id;
      this.nickName = nickName;
      this.password = password;
      this.phoneNum = phoneNum;
      this.email = email;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getNickName() {
      return nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhoneNum() {
      return phoneNum;
   }

   public void setPhoneNum(String phoneNum) {
      this.phoneNum = phoneNum;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getStatusMsg() {
      return statusMsg;
   }

   public void setStatusMsg(String statusMsg) {
      this.statusMsg = statusMsg;
   }

   public String getProfileImg() {
      return profileImg;
   }

   public void setProfileImg(String profileImg) {
      this.profileImg = profileImg;
   }

   public int getEmailOpen() {
      return emailOpen;
   }

   public void setEmailOpen(int emailOpen) {
      this.emailOpen = emailOpen;
   }

   public int getPhoneNumOpen() {
      return phoneNumOpen;
   }

   public void setPhoneNumOpen(int phoneNumOpen) {
      this.phoneNumOpen = phoneNumOpen;
   }

   public int getIdOpen() {
      return idOpen;
   }

   public void setIdOpen(int idOpen) {
      this.idOpen = idOpen;
   }

   public int getChattingAlarm() {
      return chattingAlarm;
   }

   public void setChattingAlarm(int chattingAlarm) {
      this.chattingAlarm = chattingAlarm;
   }

   public int getMemoAlarm() {
      return memoAlarm;
   }

   public void setMemoAlarm(int memoAlarm) {
      this.memoAlarm = memoAlarm;
   }

   @Override
   public String toString() {
      return "Member [id=" + id + ", nickName=" + nickName + ", password=" + password + ", phoneNum=" + phoneNum
            + ", email=" + email + ", statusMsg=" + statusMsg + ", profileImg=" + profileImg + ", emailOpen="
            + emailOpen + ", phoneNumOpen=" + phoneNumOpen + ", idOpen=" + idOpen + ", chattingAlarm="
            + chattingAlarm + ", memoAlarm=" + memoAlarm + "]";
   }
   
}