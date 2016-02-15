package edu.nefu.DeforeMgr.map;

import edu.nefu.DeforeMgr.bean.Constant;

public class DisplayConfiguration 
{
	private double startLongitude;
	private double startLatitude;
	private double proportion;
	private double pointSize;
	
	public double getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(double startLongitude) {
		this.startLongitude = startLongitude;
	}

	public double getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(double startLatitude) {
		this.startLatitude = startLatitude;
	}

	public double getProportion() {
		return proportion;
	}

	public void setProportion(double proportion) {
		this.proportion = proportion;
	}

	public double getPointSize() {
		return pointSize;
	}

	public void setPointSize(double pointSize) {
		this.pointSize = pointSize;
	}

	public DisplayConfiguration() 
	{
		super();
		Map map=new Map();
		this.startLongitude=map.getMinLongitude();
		this.startLatitude=map.getMinLatitude();
		this.proportion = 1;
		this.pointSize=Constant.Map.PointSize;
	}

	public DisplayConfiguration(double startLongitude, double startLatitude,double proportion) 
	{
		super();
		this.startLongitude = startLongitude;
		this.startLatitude = startLatitude;
		this.proportion = proportion;
	}

	public DisplayConfiguration(double startLongitude, double startLatitude,
			double proportion, double pointSize) 
	{
		super();
		this.startLongitude = startLongitude;
		this.startLatitude = startLatitude;
		this.proportion = proportion;
		this.pointSize = pointSize;
	}

}
