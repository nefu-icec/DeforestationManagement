package edu.nefu.DeforeMgr.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.bean.ConnectDatabase;

public class LB_XB 
{
	private int lb;
	private int xb;
	
	public LB_XB()
	{}
	
	public LB_XB(int lb,int xb)
	{
		this.lb=lb;
		this.xb=xb;
	}
	
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

	public static ArrayList<String> getLB() throws ClassNotFoundException, SQLException
	{
		ArrayList<String> LBs=new ArrayList<String>();
		String select="select lb from checkdata group by lb";
		ConnectDatabase connectDatabase=new ConnectDatabase();
		ResultSet rs=connectDatabase.exeQuery(select);
		while(rs.next())
		{
			LBs.add(rs.getString("lb"));
		}
		connectDatabase.close();
		return LBs;
	}
	
	public static ArrayList<String> getXB(String lb) throws ClassNotFoundException, SQLException
	{
		ArrayList<String> XBs=new ArrayList<String>();
		String select="select xb from checkdata where lb="+lb;
		ConnectDatabase connectDatabase=new ConnectDatabase();
		ResultSet rs=connectDatabase.exeQuery(select);
		while(rs.next())
		{
			XBs.add(rs.getString("xb"));
		}
		connectDatabase.close();
		return XBs;
	}
	//得到已验收的林班小班数组
	public static LB_XB [] getReportLbAndXb() throws ClassNotFoundException, SQLException
	{
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select lb,xb from checkdata where cut=1 and checked=1";
		ResultSet rs=connectDatabase.exeQuery(select);
		rs.last(); 
		int length = rs.getRow();
		LB_XB [] lbxbs=new LB_XB[length];
		for(int i=0;i<length;i++)
			lbxbs[i]=new LB_XB();
		rs.beforeFirst();
		int i=0;
		while(rs.next())
		{
			lbxbs[i].setLb(Integer.parseInt(rs.getString("lb")));
			lbxbs[i].setXb(Integer.parseInt(rs.getString("xb")));
			i++;
		}
		connectDatabase.close();
		return lbxbs;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		LB_XB [] lbxbs=getReportLbAndXb();
		for(int i=0;i<lbxbs.length;i++)
			System.out.println(lbxbs[i].getLb()+"  "+lbxbs[i].getXb());
	}
}
