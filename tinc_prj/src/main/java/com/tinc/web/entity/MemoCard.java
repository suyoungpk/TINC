package com.tinc.web.entity;

public class MemoCard {
	private int id;
	private Integer privateListId;
	private Integer groupListId;
	private String title;
	private String content;
	
	public MemoCard()
	{
		// TODO Auto-generated constructor stub
	}
	
	// for selecting
	public MemoCard(int id, Integer privateListId, Integer groupListId, String title, String content, String type)
	{
		this.id = id;
		this.privateListId = privateListId;
		this.groupListId = groupListId;
		this.title = title;
		this.content = content;
	}
	
	// for inserting, updating
	public MemoCard(Integer privateListId, Integer groupListId, String title, String content, String type)
	{
		this.privateListId = privateListId;
		this.groupListId = groupListId;
		this.title = title;
		this.content = content;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Integer getPrivateListId()
	{
		return privateListId;
	}

	public void setPrivateListId(Integer privateListId)
	{
		this.privateListId = privateListId;
	}

	public Integer getGroupListId()
	{
		return groupListId;
	}

	public void setGroupListId(Integer groupListId)
	{
		this.groupListId = groupListId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	
	@Override
	public String toString()
	{
		return "MemoCard [id=" + id + ", privateListId=" + privateListId + ", groupListId=" + groupListId + ", title="
				+ title + ", content=" + content +  "]";
	}
	
	

}
