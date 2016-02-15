package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.util.MyDate;

public class ReportExcel 
{
	private int id;
	private String rName;
	private MyDate date;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public ReportExcel() {
		super();
	}

	public ReportExcel(int id,String rName, MyDate date) {
		super();
		this.id=id;
		this.rName = rName;
		this.date = date;
	}

	public static ArrayList<ReportExcel> getReportExcel(String where)
	{
		ArrayList<ReportExcel> reportExcels=new ArrayList<ReportExcel>();
		if(where==null)
			where="";
		String select="select * from reportexcel "+where;
		ConnectDatabase connectDatabase;
		try 
		{
			connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			int id;
			String rName,date;
			String [] buffer;
			int year,month,day;
			while(rs.next())
			{
				id=Integer.parseInt(rs.getString("id"));
				rName=rs.getString("rName");
				date=rs.getString("date");
				buffer=date.split("-");
				year=Integer.parseInt(buffer[0]);
				month=Integer.parseInt(buffer[1]);
				day=Integer.parseInt(buffer[2]);
				reportExcels.add(new ReportExcel(id,rName, new MyDate(year, month, day)));
			}
			connectDatabase.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		return reportExcels;
	}
	
	public static ArrayList<ReportExcel> getReportExcelByTime(MyDate startDate,MyDate endDate)
	{
		String where=" where date between '"+startDate.getStringDate()+"' and '"+endDate.getStringDate()+"'";
		ArrayList<ReportExcel> reportExcels=getReportExcel(where);
		return reportExcels;
	}
	
	public static ReportExcel getReportExcelById(int id)
	{
		String where=" where id="+id;
		ArrayList<ReportExcel> reportExcels=getReportExcel(where);
		ReportExcel reportExcel=null;
		if(!reportExcels.isEmpty())
			reportExcel=reportExcels.get(0);
		return reportExcel;
	}
}
