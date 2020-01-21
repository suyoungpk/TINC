package com.tinc.web.entity;

public class MemoCardView
{
	private int mcId;
	private Integer mcPrivateListId;
	private Integer mcGroupListId;
	private String mcTitle;
	private String mcContent;
	private int clId;
	private String clTitle;
	private boolean clHideStatus;
	private int cliId;
	private String cliTitle;
	private boolean cliCheckStatus;
	private int ddId;
	private String ddDate;
	private String ddTime;
	
	public MemoCardView()
	{
		// TODO Auto-generated constructor stub
	}

	public MemoCardView(int mcId, Integer mcPrivateListId, Integer mcGroupListId, String mcTitle, String mcContent,
			int clId, String clTitle, boolean clHideStatus, int cliId, String cliTitle, boolean cliCheckStatus,
			int ddId, String ddDate, String ddTime)
	{
		this.mcId = mcId;
		this.mcPrivateListId = mcPrivateListId;
		this.mcGroupListId = mcGroupListId;
		this.mcTitle = mcTitle;
		this.mcContent = mcContent;
		this.clId = clId;
		this.clTitle = clTitle;
		this.clHideStatus = clHideStatus;
		this.cliId = cliId;
		this.cliTitle = cliTitle;
		this.cliCheckStatus = cliCheckStatus;
		this.ddId = ddId;
		this.ddDate = ddDate;
		this.ddTime = ddTime;
	}

	public int getMcId()
	{
		return mcId;
	}

	public void setMcId(int mcId)
	{
		this.mcId = mcId;
	}

	public Integer getMcPrivateListId()
	{
		return mcPrivateListId;
	}

	public void setMcPrivateListId(Integer mcPrivateListId)
	{
		this.mcPrivateListId = mcPrivateListId;
	}

	public Integer getMcGroupListId()
	{
		return mcGroupListId;
	}

	public void setMcGroupListId(Integer mcGroupListId)
	{
		this.mcGroupListId = mcGroupListId;
	}

	public String getMcTitle()
	{
		return mcTitle;
	}

	public void setMcTitle(String mcTitle)
	{
		this.mcTitle = mcTitle;
	}

	public String getMcContent()
	{
		return mcContent;
	}

	public void setMcContent(String mcContent)
	{
		this.mcContent = mcContent;
	}

	public int getClId()
	{
		return clId;
	}

	public void setClId(int clId)
	{
		this.clId = clId;
	}

	public String getClTitle()
	{
		return clTitle;
	}

	public void setClTitle(String clTitle)
	{
		this.clTitle = clTitle;
	}

	public boolean isClHideStatus()
	{
		return clHideStatus;
	}

	public void setClHideStatus(boolean clHideStatus)
	{
		this.clHideStatus = clHideStatus;
	}

	public int getCliId()
	{
		return cliId;
	}

	public void setCliId(int cliId)
	{
		this.cliId = cliId;
	}

	public String getCliTitle()
	{
		return cliTitle;
	}

	public void setCliTitle(String cliTitle)
	{
		this.cliTitle = cliTitle;
	}

	public boolean isCliCheckStatus()
	{
		return cliCheckStatus;
	}

	public void setCliCheckStatus(boolean cliCheckStatus)
	{
		this.cliCheckStatus = cliCheckStatus;
	}

	public int getDdId()
	{
		return ddId;
	}

	public void setDdId(int ddId)
	{
		this.ddId = ddId;
	}

	public String getDdDate()
	{
		return ddDate;
	}

	public void setDdDate(String ddDate)
	{
		this.ddDate = ddDate;
	}

	public String getDdTime()
	{
		return ddTime;
	}

	public void setDdTime(String ddTime)
	{
		this.ddTime = ddTime;
	}

	@Override
	public String toString()
	{
		return "MemoCardView [mcId=" + mcId + ", mcPrivateListId=" + mcPrivateListId + ", mcGroupListId="
				+ mcGroupListId + ", mcTitle=" + mcTitle + ", mcContent=" + mcContent + ", clId=" + clId + ", clTitle="
				+ clTitle + ", clHideStatus=" + clHideStatus + ", cliId=" + cliId + ", cliTitle=" + cliTitle
				+ ", cliCheckStatus=" + cliCheckStatus + ", ddId=" + ddId + ", ddDate=" + ddDate + ", ddTime=" + ddTime
				+ "]";
	}
	
}
