package com.tinc.web.entity;

public class GroupShareFullView
{
	private String id;
	private int chattingRoomId;
	private String chattingRoomTitle;
	private int countId;

	
	public GroupShareFullView()
	{
		// TODO Auto-generated constructor stub
	}


	public GroupShareFullView(String id, int chattingRoomId, String chattingRoomTitle, int countId)
	{
		this.id = id;
		this.chattingRoomId = chattingRoomId;
		this.chattingRoomTitle = chattingRoomTitle;
		this.countId = countId;
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


	public int getCountId()
	{
		return countId;
	}


	public void setCountId(int countId)
	{
		this.countId = countId;
	}


	@Override
	public String toString()
	{
		return "GroupShareFullView [id=" + id + ", chattingRoomId=" + chattingRoomId + ", chattingRoomTitle="
				+ chattingRoomTitle + ", countId=" + countId + "]";
	}

	
}
