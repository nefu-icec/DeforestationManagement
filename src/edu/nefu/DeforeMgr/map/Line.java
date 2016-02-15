package edu.nefu.DeforeMgr.map;

import java.util.ArrayList;

public class Line 
{
	private Point start;
	private Point end;
	private ArrayList<Point> points;

	public ArrayList<Point> getDisplayPoints() 
	{
		return points;
	}

	public Line(Point start, Point end) 
	{
		super();
		this.start = start;
		this.end = end;
		setDisplayPoints();
	}

	/**
	 * 中点画线法
	 * @param x1 起始点横坐标
	 * @param y1 起始点纵坐标
	 * @param x2 结束点横坐标
	 * @param y2 结束点纵坐标
	 */
	private void MPLine(int x1,int y1,int x2,int y2)
	{
		int x,y,a,b,d,d1,d2;
		a=y1-y2; b=x2-x1;
		x=x1; y=y1;
		d=2*a+b; d1=2*a; d2=2*(a+b);
		putpixel(x, y);
		for(x=x1;x<=x2;x++)
		{
			if(d<0){ y++; d+=d2; }
			else d+=d1;
			putpixel(x, y);
		}
	}
	
	private void putpixel(int x, int y) 
	{
		Point point=new Point(x, y);
		points.add(point);
	}
	
	private void setDisplayPoints() 
	{
		points=new ArrayList<Point>();
		MPLine(start.x, start.y, end.x, end.y);
	}
}
