package edu.nefu.DeforeMgr.bean;

public class InputCheck 
{
	String id;
	int lb;
	int xb;
	private String region;
	private String tree;
	private double dgBest;
	private double dgMiddle;
	private double dgWorst;
	private double density;
	private double area;
	private double volume;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTree() {
		return tree;
	}
	public void setTree(String tree) {
		this.tree = tree;
	}
	public double getDgBest() {
		return dgBest;
	}
	public void setDgBest(double dgBest) {
		this.dgBest = dgBest;
	}
	public double getDgMiddle() {
		return dgMiddle;
	}
	public void setDgMiddle(double dgMiddle) {
		this.dgMiddle = dgMiddle;
	}
	public double getDgWorst() {
		return dgWorst;
	}
	public void setDgWorst(double dgWorst) {
		this.dgWorst = dgWorst;
	}
	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public InputCheck() 
	{}
	
	public InputCheck(int lb, int xb, String region, String tree,double dgBest, double dgMiddle, double dgWorst, double density,double area, double volume) 
	{
		this.lb = lb;
		this.xb = xb;
		this.region = region;
		this.tree = tree;
		this.dgBest = dgBest;
		this.dgMiddle = dgMiddle;
		this.dgWorst = dgWorst;
		this.density = density;
		this.area = area;
		this.volume = volume;
		CheckData checkData=null;
		try 
		{
			checkData = CheckData.getCheckDataByLBAndXB(lb, xb);
			this.id=checkData.getId();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 	
	}
	
}
