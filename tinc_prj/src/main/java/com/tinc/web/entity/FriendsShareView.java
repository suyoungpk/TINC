package com.tinc.web.entity;

public class FriendsShareView
{
	private String id;
	private String friendsId;
	private String nickName;
	private String statusMsg;
	private String profileImg;
	
	public FriendsShareView()
	{
		// TODO Auto-generated constructor stub
	}

	public FriendsShareView(String id, String friendsId, String nickName, String statusMsg, String profileImg)
	{
		this.id = id;
		this.friendsId = friendsId;
		this.nickName = nickName;
		this.statusMsg = statusMsg;
		this.profileImg = profileImg;
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

	@Override
	public String toString()
	{
		return "FriendsShareView [id=" + id + ", friendsId=" + friendsId + ", nickName=" + nickName + ", statusMsg="
				+ statusMsg + ", profileImg=" + profileImg + "]";
	}
	
}
