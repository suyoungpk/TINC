package com.tinc.web.entity;


public class CheckList {
	private int id;
	private String title;
	private boolean hideStatus;
	private int cardId;
	
	public CheckList()
	{
		// TODO Auto-generated constructor stub
	}
	
	// for selecting
	public CheckList(int id, String title, boolean hideStatus, int cardId)
	{
		this.id = id;
		this.title = title;
		this.hideStatus = hideStatus;
		this.cardId = cardId;
	}

	// for inserting, updating
	public CheckList(String title, boolean hideStatus, int cardId)
	{
		this.title = title;
		this.hideStatus = hideStatus;
		this.cardId = cardId;
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

	public boolean getHideStatus()
	{
		return hideStatus;
	}

	public void setHideStatus(boolean hideStatus)
	{
		this.hideStatus = hideStatus;
	}

		public int getCardId()
	{
		return cardId;
	}

	public void setCardId(int cardId)
	{
		this.cardId = cardId;
	}

	@Override
	public String toString()
	{
		return "CheckList [id=" + id + ", title=" + title + ", hideStatus=" + hideStatus
				+ ", cardId=" + cardId + "]";
	}
	
		
	
}
