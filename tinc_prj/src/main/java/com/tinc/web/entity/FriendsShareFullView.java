package com.tinc.web.entity;

public class FriendsShareFullView
{
	private String id;
	private String friendsId;
	private String nickName;
	private String statusMsg;
	private String profileImg;
	private String tmpId;
	private int chattingRoomId;
	private String chattingRoomTitle;
	private int countId;
	private String tmpId2;
	
	public FriendsShareFullView()
	{
		// TODO Auto-generated constructor stub
	}

	public FriendsShareFullView(String id, String friendsId, String nickName, String statusMsg, String profileImg,
			String tmpId, int chattingRoomId, String chattingRoomTitle, int countId, String tmpId2)
	{
		this.id = id;
		this.friendsId = friendsId;
		this.nickName = nickName;
		this.statusMsg = statusMsg;
		this.profileImg = profileImg;
		this.tmpId = tmpId;
		this.chattingRoomId = chattingRoomId;
		this.chattingRoomTitle = chattingRoomTitle;
		this.countId = countId;
		this.tmpId2 = tmpId2;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFriendsId()
	{
		return friendsId;
	}

	public void setFriendsId(String friendsId)
	{
		this.friendsId = friendsId;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getStatusMsg()
	{
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg)
	{
		this.statusMsg = statusMsg;
	}

	public String getProfileImg()
	{
		return profileImg;
	}

	public void setProfileImg(String profileImg)
	{
		this.profileImg = profileImg;
	}

	public String getTmpId()
	{
		return tmpId;
	}

	public void setTmpId(String tmpId)
	{
		this.tmpId = tmpId;
	}

	public int getChattingRoomId()
	{
		return chattingRoomId;
	}

	public void setChattingRoomId(int chattingRoomId)
	{
		this.chattingRoomId = chattingRoomId;
	}

	public String getChattingRoomTitle()
	{
		return chattingRoomTitle;
	}

	public void setChattingRoomTitle(String chattingRoomTitle)
	{
		this.chattingRoomTitle = chattingRoomTitle;
	}

	public int getCountId()
	{
		return countId;
	}

	public void setCountId(int countId)
	{
		this.countId = countId;
	}

	public String getTmpId2()
	{
		return tmpId2;
	}

	public void setTmpId2(String tmpId2)
	{
		this.tmpId2 = tmpId2;
	}

	@Override
	public String toString()
	{
		return "FriendsShareFullView [id=" + id + ", friendsId=" + friendsId + ", nickName=" + nickName + ", statusMsg="
				+ statusMsg + ", profileImg=" + profileImg + ", tmpId=" + tmpId + ", chattingRoomId=" + chattingRoomId
				+ ", chattingRoomTitle=" + chattingRoomTitle + ", countId=" + countId + ", tmpId2=" + tmpId2 + "]";
	}
	
	
}
