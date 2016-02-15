package edu.nefu.DeforeMgr.bean;

import java.util.ArrayList;

public class SearchCheckDataItem 
{
	private int lb;
	private int xb;
	private int id;
	private String dcdw;
	
	public int getLb() {
		return lb;
	}
	
	public void setLb(int lb) {
		this.lb = lb;
	}
	
	public int getXb() {
		return xb;
	}
	
	public void setXb(int xb) {
		this.xb = xb;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDcdw() {
		return dcdw;
	}
	
	public void setDcdw(String dcdw) {
		this.dcdw = dcdw;
	}

	public SearchCheckDataItem() 
	{
		super();
		this.lb=0;
		this.xb=0;
		this.id=0;
		this.dcdw="";
	}

	public ArrayList<CheckData> getSearchCheckData()
	{
		ArrayList<CheckData> checkDatas=null;
		if(lb==0&&xb==0&&id==0&&dcdw=="")
		{
			try
			{
				checkDatas=CheckData.getCheckDatas("where cut=0 and checked=0");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			String where="";
			if(lb!=0&&xb!=0)
				where+=" where lb="+lb+" and xb="+xb+" and checked=0 and cut=0";
			else
			{
				where+=" where";
				if(id!=0)
					where+=" id like '%"+id+"%' and";
				if(!dcdw.equals(""))
					where+=" dcdw like '%"+dcdw+"%' and";
				if(lb!=0)
					where+=" lb="+lb+" and";
				if(xb!=0)
					where+=" xb="+xb+" and";		
				where=where.substring(0, where.length()-4);
				where+=" and checked=0 and cut=0";
			}
			try
			{
				checkDatas=CheckData.getCheckDatas(where);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		}
		return checkDatas;
	}
}
