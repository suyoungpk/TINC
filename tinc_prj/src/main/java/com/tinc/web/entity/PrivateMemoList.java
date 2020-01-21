package com.tinc.web.entity;

public class PrivateMemoList {
	private int id;
	private String title;
	private String memberId;
	
	public PrivateMemoList()
	{
		// TODO Auto-generated constructor stub
	}

	// for selecting
	public PrivateMemoList(int id, String title, String memberId)
	{
		this.id = id;
		this.title = title;
		this.memberId = memberId;
	}
	
	// for inserting, updating
	public PrivateMemoList(String title, String memberId)
	{
		super();
		this.title = title;
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
		return "PrivateMemoList [id=" + id + ", title=" + title + ", memberId=" + memberId + "]";
	}
	
	
}
