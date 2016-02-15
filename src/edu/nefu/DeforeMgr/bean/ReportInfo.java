package edu.nefu.DeforeMgr.bean;

import java.text.DecimalFormat;

/**
 * @author lm2343635
 *
 */
public class ReportInfo 
{
	private String id;
	private String dcdw;
	private String dcy;
	private int lb;
	private int xb;
	private String lzq;
	private String z_dq;
	private String z_lq;
	private String z_fllx;
	private String z_dl;
	private String z_lz;
	private String z_qy;
	private String region;
	private String tree;
	private double volume;
	private double reportVolume;
	private double relativeError;
	private double absoluteError;
	
	public double calRelativeError(double volume,double reportVolume)
	{
		double calRelativeError=100*Math.abs(calAbsoluteError(volume, reportVolume))/volume;
		DecimalFormat df = new DecimalFormat("#.00");
		calRelativeError=Double.parseDouble(df.format(calRelativeError));
		return calRelativeError;
	}
	
	public double calAbsoluteError(double volume,double reportVolume)
	{

		double calAbsoluteError=reportVolume-volume;
		DecimalFormat df = new DecimalFormat("#.00");
		calAbsoluteError=Double.parseDouble(df.format(calAbsoluteError));
		return calAbsoluteError;
	}
	
	public String getRelativeError(int length) 
	{
		String sRelativeError=String.valueOf(relativeError);
		sRelativeError=sRelativeError.substring(0, length);
		sRelativeError+="%";
		return sRelativeError;
	}
	
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
	public double getRelativeError() {
		return relativeError;
	}
	public void setRelativeError(double relativeError) {
		this.relativeError = relativeError;
	}
	public double getAbsoluteError() 
	{
		return absoluteError;
	}
	public void setAbsoluteError(double absoluteError) {
		this.absoluteError = absoluteError;
	}
	public String getLzq() {
		return lzq;
	}
	public void setLzq(String lzq) {
		this.lzq = lzq;
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
	
}
