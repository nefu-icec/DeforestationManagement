package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;


import java.util.ArrayList;

public class News 
{
	private int id;
	private String ndate;
	private String content;
	private String title;
	private String author;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public News()
	{
		super();
	}
	public News(int id, String ndate, String content, String title,String author) {
		super();
		this.id = id;
		this.ndate = ndate;
		this.content = content;
		this.title = title;
		this.author = author;
	}
	public News(String ndate, String content, String title, String author) {
		super();
		this.ndate = ndate;
		this.content = content;
		this.title = title;
		this.author = author;
	}
	public void insertNews()
	{
		String insert="insert into news(ndate,title,content,author) values('"+ndate+"','"+title+"','"+content+"','"+author+"')";
		try 
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			connectDatabase.exeUpdate(insert);
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static ArrayList<News> getNews(String where)
	{
		ArrayList<News> newsList=new ArrayList<News>();
		if(where==null)
			where="";
		String select="select * from news "+where;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			while(rs.next())
				newsList.add(new News(Integer.parseInt(rs.getString("id")),
									 rs.getString("ndate"),
									 rs.getString("content"),
									 rs.getString("title"),
									 rs.getString("author")));
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newsList;
	}
	
	public static News getNewsById(int id)
	{
		String where=" where id="+id;
		ArrayList<News> newsList=getNews(where);
		News news=new News();
		if(!newsList.isEmpty())
			news=newsList.get(0);
		return news;
	}
	
	
	public static ArrayList<News> getShowNews(int ShowNumber)
	{
		ArrayList<News> newsList=new ArrayList<News>();
		String select="select * from news order by ndate desc";
		try 
		{
			ConnectDatabase connectDatabase = new ConnectDatabase();
			ResultSet rs = connectDatabase.exeQuery(select);
			int n=0;
			while (rs.next()&&n<ShowNumber) 
			{
				newsList.add(new News(Integer.parseInt(rs.getString("id")),
						 rs.getString("ndate"),
						 rs.getString("content"),
						 rs.getString("title"),
						 rs.getString("author")));
				n++;
			}
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return newsList;
	}
}
