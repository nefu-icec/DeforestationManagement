package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AndroidUpdate
{
	private int version;
	private String apkName;
	private String updateTime;
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getApkName() {
		return apkName;
	}
	
	public void setApkName(String apkName) {
		this.apkName = apkName;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public AndroidUpdate(String apkName, String updateTime) 
	{
		super();
		this.apkName = apkName;
		this.updateTime = updateTime;
	}

	public AndroidUpdate(int version, String apkName, String updateTime) 
	{
		super();
		this.version = version;
		this.apkName = apkName;
		this.updateTime = updateTime;
	}
	
	public static ArrayList<AndroidUpdate> getAndroidUpdate(String where) throws SQLException, ClassNotFoundException
	{
		ArrayList<AndroidUpdate> updateList = new ArrayList<AndroidUpdate>();
		String select = "select * from androidupdate ";
		if(where==null)
			where = "";
		select += where;
		ConnectDatabase connectDatabase = new ConnectDatabase();
		ResultSet rs= connectDatabase.exeQuery(select);
		while(rs.next())
		{
			int version = Integer.parseInt(rs.getString("version"));
			String apkName = rs.getString("apkName");
			String updateTime = rs.getString("updateTime");
			updateList.add(new AndroidUpdate(version, apkName, updateTime));
		}
		connectDatabase.close();
		return updateList;
	}
	
	public static ArrayList<AndroidUpdate> getAndroidUpdateByDate(String startDate,String endDate) throws ClassNotFoundException, SQLException
	{
		String where=" where updateTime between '"+startDate+"' and '"+endDate+"'";
		ArrayList<AndroidUpdate> updateList=getAndroidUpdate(where);
		return updateList;
	}
	
	public static String getNameByVersion(int version) throws ClassNotFoundException, SQLException
	{
		String where=" where version="+version;
		ArrayList<AndroidUpdate> updateList=getAndroidUpdate(where);
		return updateList.get(0).getApkName();
	}
	
	public boolean insert() throws ClassNotFoundException, SQLException
	{
		String insert="insert into androidupdate(apkname,updatetime) values('"+apkName+"','"+updateTime+"')";
		ConnectDatabase connectDatabase=new ConnectDatabase();
		int result=connectDatabase.exeUpdate(insert);
		connectDatabase.close();
		if(result>0)
			return true;
		else
			return false;
	}
	
	public static AndroidUpdate getNewestUpdate()
	{
		String select="select * from androidupdate order by updateTime desc";
		try
		{		
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			if(rs.next())
			{
				int version=Integer.parseInt(rs.getString("version"));
				String apkName=rs.getString("apkName");
				String updateTime=rs.getString("updateTime");
				connectDatabase.close();
				return new AndroidUpdate(version, apkName, updateTime);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
