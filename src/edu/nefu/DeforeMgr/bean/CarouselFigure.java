package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;

import java.util.ArrayList;

public class CarouselFigure
{
	private int id;
	private String name;
	private String date;
	private String author;
	private int nid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public CarouselFigure() {
		super();
	}
	
	public CarouselFigure(String name, String date, String author) {
		super();
		this.name = name;
		this.date = date;
		this.author = author;
	}
	public CarouselFigure(int id, String name, String date, String author,
			int nid) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.author = author;
		this.nid = nid;
	}
	public CarouselFigure(String name, String date, String author, int nid) {
		super();
		this.name = name;
		this.date = date;
		this.author = author;
		this.nid = nid;
	}
	public void insertFigure()
	{
		String insert="insert into carouselFigure(name,date,author,nid) values('"+name+"','"+date+"','"+author+"',"+nid+")";
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
	
	public static ArrayList<CarouselFigure> getFigure(String where)
	{
		ArrayList<CarouselFigure> figures=new ArrayList<CarouselFigure>();
		if(where==null)
			where="";
		String select="select * from carouselFigure "+where;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			while(rs.next())
				figures.add(new CarouselFigure(Integer.parseInt(rs.getString("id")),
						 			 rs.getString("name"),
									 rs.getString("date"),
									 rs.getString("author"),
									 Integer.parseInt(rs.getString("nid"))));
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return figures;
	}
	
	public static ArrayList<CarouselFigure> getShowFigure(int ShowNumber)
	{
		ArrayList<CarouselFigure> figures=new ArrayList<CarouselFigure>();
		String select="select * from carouselfigure order by date desc";
		try 
		{
			ConnectDatabase connectDatabase = new ConnectDatabase();
			ResultSet rs = connectDatabase.exeQuery(select);
			int n = 0;
			while (rs.next()&&n<ShowNumber) 
			{
				figures.add(new CarouselFigure(Integer.parseInt(rs.getString("id")),
									rs.getString("name"), 
									rs.getString("date"), 
									rs.getString("author"), 
									Integer.parseInt(rs.getString("nid"))));
				n++;
			}
			connectDatabase.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return figures;
	}
	
	public static CarouselFigure getFigureById(int id) 
	{
		String where=" where id="+id;
		ArrayList<CarouselFigure> figures=getFigure(where);
		CarouselFigure figure=new CarouselFigure();
		if(!figures.isEmpty())
			figure=figures.get(0);
		return figure;
	}
	
	public static void main(String[] args) 
	{
		ArrayList<CarouselFigure> figures=getShowFigure(5);
		for(int i=0;i<figures.size();i++)
			System.out.println(figures.get(i).getName());
	}
}
