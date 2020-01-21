package com.tinc.web.entity;


public class CheckListItem
{
	private int id;
	private String title;
	private boolean checkStatus;
	private int checkListId;
	
	public CheckListItem()
	{
		// TODO Auto-generated constructor stub
	}
	
	// for selecting
	public CheckListItem(int id, String title, boolean checkStatus, int checkListId)
	{
		this.id = id;
		this.title = title;
		this.checkStatus = checkStatus;
		this.checkListId = checkListId;
	}

	// for inserting, updating
	public CheckListItem(String title, boolean checkStatus, int checkListId)
	{
		this.title = title;
		this.checkStatus = checkStatus;
		this.checkListId = checkListId;
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

	public boolean getCheckStatus()
	{
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus)
	{
		this.checkStatus = checkStatus;
	}

	public int getCheckListId()
	{
		return checkListId;
	}

	public void setCheckListId(int checkListId)
	{
		this.checkListId = checkListId;
	}

	@Override
	public String toString()
	{
		return "CheckListItem [id=" + id + ", title=" + title + ", checkStatus=" + checkStatus 
				+ ", checkListId=" + checkListId + "]";
	}
	
	
}
