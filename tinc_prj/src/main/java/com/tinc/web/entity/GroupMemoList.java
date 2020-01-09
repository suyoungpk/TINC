package com.tinc.web.entity;

public class GroupMemoList {
	private int id;
	private String title;
	private int chattingRoomId;
	private String memberId;
	
	public GroupMemoList()
	{
		// TODO Auto-generated constructor stub
	}

	// for selecting
	public GroupMemoList(int id, String title, int chattingRoomId, String memberId)
	{
		this.id = id;
		this.title = title;
		this.chattingRoomId = chattingRoomId;
		this.memberId = memberId;
	}	
	
	// for inserting, updating
	public GroupMemoList(String title, int chattingRoomId, String memberId)
	{
		this.title = title;
		this.chattingRoomId = chattingRoomId;
		this.memberId = memberId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getChattingRoomId()
	{
		return chattingRoomId;
	}

	public void setChattingRoomId(int chattingRoomId)
	{
		this.chattingRoomId = chattingRoomId;
	}

	public String getMemberId()
	{
		return memberId;
	}

	public void setMemberId(String memberId)
	{
		this.memberId = memberId;
	}

	@Override
	public String toString()
	{
		return "GroupMemoList [id=" + id + ", title=" + title + ", chattingRoomId=" + chattingRoomId + ", memberId="
				+ memberId + "]";
	}
	
	
}
