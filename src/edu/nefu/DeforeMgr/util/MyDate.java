package edu.nefu.DeforeMgr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate
{
	private int year;
	private int month;
	private int day;
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public MyDate() 
	{
		String [] nowDate=getNowDate().split("-");
		year=Integer.parseInt(nowDate[0]);
		month=Integer.parseInt(nowDate[1]);
		day=Integer.parseInt(nowDate[2]);
	}

	public MyDate(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public MyDate(String date) {
		super();
		String[] buffer=date.split("-");
		this.year = Integer.parseInt(buffer[0]);
		this.month = Integer.parseInt(buffer[1]);
		this.day = Integer.parseInt(buffer[2]);
	}

	public static String getNowDate()
	{
 		Date now=new Date();
 		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
 		String nowDate=dateFormat.format(now);
 		return nowDate;
	}
	
	public String getStringDate()
	{
		String sDate=year+"-"+month+"-"+day;
		return sDate;
	}
	
	public static String getNowTime()
	{
		Date now=new Date();
 		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
 		String nowTime=dateFormat.format(now); 
 		return nowTime;
	}
	
	public static String getSqlTime()
	{
		Date now=new Date();
 		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
 		String nowTime=dateFormat.format(now); 
 		return nowTime;
	}
	
	public static void main(String[] args) 
	{
		System.out.println(getNowDate());
		System.out.println(getNowTime());
		System.out.println(getSqlTime());
	}
}
