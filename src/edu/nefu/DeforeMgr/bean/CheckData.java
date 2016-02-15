package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckData 
{
	private String  id;
	private String  dcdw;
	private String dcy;
	private int lb;
	private String lzq;
	private int xb;
	private String z_dq;
	private String z_lq;
	private String z_fllx;
	private String z_dl;
	private String z_lz;
	private String z_qy;
	private String region;
	private String tree;
	private double dgBest;
	private double dgMiddle;
	private double dgWorst;
	private double density;
	private double area;
	private double volume;
	private double reportVolume;
	private int cut;
	private int checked;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDcdw() {
		return dcdw;
	}
	public void setDcdw(String dcdw) {
		this.dcdw = dcdw;
	}
	public String getDcy() {
		return dcy;
	}
	public void setDcy(String dcy) {
		this.dcy = dcy;
	}
	public int getLb() {
		return lb;
	}
	public void setLb(int lb) {
		this.lb = lb;
	}
	public String getLzq() {
		return lzq;
	}
	public void setLzq(String lzq) {
		this.lzq = lzq;
	}
	public int getXb() {
		return xb;
	}
	public void setXb(int xb) {
		this.xb = xb;
	}
	public String getZ_dq() {
		return z_dq;
	}
	public void setZ_dq(String z_dq) {
		this.z_dq = z_dq;
	}
	public String getZ_lq() {
		return z_lq;
	}
	public void setZ_lq(String z_lq) {
		this.z_lq = z_lq;
	}
	public String getZ_fllx() {
		return z_fllx;
	}
	public void setZ_fllx(String z_fllx) {
		this.z_fllx = z_fllx;
	}
	public String getZ_dl() {
		return z_dl;
	}
	public void setZ_dl(String z_dl) {
		this.z_dl = z_dl;
	}
	public String getZ_lz() {
		return z_lz;
	}
	public void setZ_lz(String z_lz) {
		this.z_lz = z_lz;
	}
	public String getZ_qy() {
		return z_qy;
	}
	public void setZ_qy(String z_qy) {
		this.z_qy = z_qy;
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
	public double getReportVolume() {
		return reportVolume;
	}
	public void setReportVolume(double reportVolume) {
		this.reportVolume = reportVolume;
	}
	public int getCut() {
		return cut;
	}
	public void setCut(int cut) {
		this.cut = cut;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	
	public CheckData()
	{}
	
	public CheckData(String id,int lb,int xb)
	{
		this.id=id;
		this.xb=xb;
		this.lb=lb;
	}
	
	public CheckData(int lb, int xb, String region, String tree, double dgBest,
			double dgMiddle, double dgWorst, double density, double area) {
		super();
		this.lb = lb;
		this.xb = xb;
		this.region = region;
		this.tree = tree;
		this.dgBest = dgBest;
		this.dgMiddle = dgMiddle;
		this.dgWorst = dgWorst;
		this.density = density;
		this.area = area;
	}
	public static CheckData getCheckDataByLBAndXB(int lb,int xb) throws ClassNotFoundException, SQLException
	{
		CheckData checkData=new CheckData();
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select * from checkdata ";
		select+="where lb="+lb+" and xb="+xb;
		ResultSet rs=connectDatabase.exeQuery(select);
		if(rs.next())
		{
			checkData.setId(rs.getString("id"));
			checkData.setDcdw(rs.getString("dcdw"));
			checkData.setDcy(rs.getString("dcy"));
			checkData.setLb(rs.getInt("lb"));
			checkData.setLzq(rs.getString("lzq"));
			checkData.setXb(rs.getInt("xb"));
			checkData.setZ_dq(rs.getString("z_dq"));
			checkData.setZ_lq(rs.getString("z_lq"));
			checkData.setZ_fllx(rs.getString("z_fllx"));
			checkData.setZ_dl(rs.getString("z_dl"));
			checkData.setZ_lz(rs.getString("z_lz"));
			checkData.setZ_qy(rs.getString("z_qy"));
			checkData.setRegion(rs.getString("region"));
			checkData.setTree(rs.getString("tree"));
			checkData.setDgBest(rs.getDouble("dgBest"));
			checkData.setDgMiddle(rs.getDouble("dgmiddle"));
			checkData.setDgWorst(rs.getDouble("dgworst"));
			checkData.setDensity(rs.getDouble("density"));
			checkData.setArea(rs.getDouble("area"));
			checkData.setVolume(rs.getDouble("volume"));
			checkData.setReportVolume(rs.getDouble("reportVolume"));
			checkData.setCut(rs.getInt("cut"));
			checkData.setChecked(rs.getInt("checked"));
		}
		connectDatabase.close();
		return checkData;
	}
	
	public static ArrayList<CheckData> getCheckDatas(String where) throws ClassNotFoundException, SQLException
	{
		ArrayList<CheckData> checkDatas=new ArrayList<CheckData>();
		CheckData checkData=null;
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select * from checkdata ";
		System.out.println(select);
		if(where==null)
			where="";
		select+=where;
		ResultSet rs=connectDatabase.exeQuery(select);
		while(rs.next())
		{
			checkData=new CheckData();
			checkData.setId(rs.getString("id"));
			checkData.setDcdw(rs.getString("dcdw"));
			checkData.setDcy(rs.getString("dcy"));
			checkData.setLb(rs.getInt("lb"));
			checkData.setLzq(rs.getString("lzq"));
			checkData.setXb(rs.getInt("xb"));
			checkData.setZ_dq(rs.getString("z_dq"));
			checkData.setZ_lq(rs.getString("z_lq"));
			checkData.setZ_fllx(rs.getString("z_fllx"));
			checkData.setZ_dl(rs.getString("z_dl"));
			checkData.setZ_lz(rs.getString("z_lz"));
			checkData.setZ_qy(rs.getString("z_qy"));
			checkData.setRegion(rs.getString("region"));
			checkData.setTree(rs.getString("tree"));
			checkData.setDgBest(rs.getDouble("dgBest"));
			checkData.setDgMiddle(rs.getDouble("dgmiddle"));
			checkData.setDgWorst(rs.getDouble("dgworst"));
			checkData.setDensity(rs.getDouble("density"));
			checkData.setArea(rs.getDouble("area"));
			checkData.setVolume(rs.getDouble("volume"));
			checkData.setReportVolume(rs.getDouble("reportVolume"));
			checkData.setCut(rs.getInt("cut"));
			checkData.setChecked(rs.getInt("checked"));
			checkDatas.add(checkData);
		}
		connectDatabase.close();
		return checkDatas;
	}
	
	public static CheckData getCheckDataById(String id) throws ClassNotFoundException, SQLException
	{
		String where=" where id="+id;
		ArrayList<CheckData> checkDatas=getCheckDatas(where);
		return checkDatas.get(0);
	}
}
