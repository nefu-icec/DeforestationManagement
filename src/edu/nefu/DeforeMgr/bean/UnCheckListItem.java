package edu.nefu.DeforeMgr.bean;

public class UnCheckListItem 
{
	private int checkListId;
	private String lb;
	private String xb;
	private String date;
	
	public int getCheckListId() {
		return checkListId;
	}

	public void setCheckListId(int checkListId) {
		this.checkListId = checkListId;
	}

	public String getLb() {
		return lb;
	}
	
	public void setLb(String lb) {
		this.lb = lb;
	}
	
	public String getXb() {
		return xb;
	}
	
	public void setXb(String xb) {
		this.xb = xb;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public UnCheckListItem(int checkListId, String lb, String xb, String date) 
	{
		super();
		this.checkListId = checkListId;
		this.lb = lb;
		this.xb = xb;
		this.date = date;
	}
}
