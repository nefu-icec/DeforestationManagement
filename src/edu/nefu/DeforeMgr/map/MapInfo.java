package edu.nefu.DeforeMgr.map;

public class MapInfo 
{
	private int lb;
	private int xb;
	private double longitude;
	private double latitude;
	
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
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public MapInfo(int lb, int xb, double longitude, double latitude) 
	{
		super();
		this.lb = lb;
		this.xb = xb;
		this.longitude = longitude;
		this.latitude = latitude;
	}
}
