package com.tinc.web.entity;

public class GroupShareMemberView
{
	private String id;
	private int chattingRoomId;
	private String chattingRoomTitle;
	private String nickName;
	private String statusMsg;
	private String profileImg;
	
	public GroupShareMemberView()
	{
		// TODO Auto-generated constructor stub
	}

	public GroupShareMemberView(String id, int chattingRoomId, String chattingRoomTitle, String nickName,
			String statusMsg, String profileImg)
	{
		this.id = id;
		this.chattingRoomId = chattingRoomId;
		this.chattingRoomTitle = chattingRoomTitle;
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
		return "GroupShareMemberView [id=" + id + ", chattingRoomId=" + chattingRoomId + ", chattingRoomTitle="
				+ chattingRoomTitle + ", nickName=" + nickName + ", statusMsg=" + statusMsg + ", profileImg="
				+ profileImg + "]";
	}
	
}
