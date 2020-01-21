package com.tinc.web.entity;

public class GroupShareView
{
	private String id;
	private int chattingRoomId;
	private String chattingRoomTitle;
	
	public GroupShareView()
	{
		// TODO Auto-generated constructor stub
	}

	public GroupShareView(String id, int chattingRoomId, String chattingRoomTitle)
	{
		this.id = id;
		this.chattingRoomId = chattingRoomId;
		this.chattingRoomTitle = chattingRoomTitle;
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

	@Override
	public String toString()
	{
		return "GroupShareView [id=" + id + ", chattingRoomId=" + chattingRoomId + ", chattingRoomTitle="
				+ chattingRoomTitle + "]";
	}
	
	
}
