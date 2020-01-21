package com.tinc.web.entity;

public class DueDate {
	private int id;
	private String date;
	private String time;
	private boolean isComplete;
	private int cardId;
	
	public DueDate()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	// for selecting
	public DueDate(int id, String date, String time, boolean isComplete, int cardId)
	{
		this.id = id;
		this.date = date;
		this.time = time;
		this.isComplete = isComplete;
		this.cardId = cardId;
	}


	// for inserting
	public DueDate(String date, String time, boolean isComplete, int cardId)
	{
		this.date = date;
		this.time = time;
		this.isComplete = isComplete;
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

	public String getDate()
	{
		return date;
	}

	public void setDate(String data)
	{
		this.date = data;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public int getCardId()
	{
		return cardId;
	}

	public void setCardId(int cardId)
	{
		this.cardId = cardId;
	}
	
	public boolean getIsComplete()
	{
		return isComplete;
	}


	public void setIsComplete(boolean isComplete)
	{
		this.isComplete = isComplete;
	}

	@Override
	public String toString()
	{
		return "DueDate [id=" + id + ", date=" + date + ", time=" + time + ", cardId=" + cardId + "]";
	}
	
	
}
