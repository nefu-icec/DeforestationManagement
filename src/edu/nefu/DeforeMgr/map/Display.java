package edu.nefu.DeforeMgr.map;

import java.util.ArrayList;

public class Display
{
	private int width;
	private int height;
	private ArrayList<Point> points;
	private ArrayList<MapInfo> infos;
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public ArrayList<MapInfo> getInfos() {
		return infos;
	}

	public void setInfos(ArrayList<MapInfo> infos) {
		this.infos = infos;
	}

	public Display(int width, int height, ArrayList<Point> points,ArrayList<MapInfo> infos)
	{
		super();
		this.width = width;
		this.height = height;
		this.points = points;
		this.infos = infos;
	}
}
