package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.calculate.Calculate;
import edu.nefu.DeforeMgr.util.MyDate;
import edu.nefu.DeforeMgr.util.NameTransform;
import edu.nefu.DeforeMgr.util.XmlTool;

/**
 * Android传送CheckDate的缓冲区
 * @author lm2343635
 *
 */
public class AndroidCheckdataBuffer 
{
	private int checkListId;
	private int lb;
	private int xb;
	private String region;
	private String tree;
	private double dgBest;
	private double dgMiddle;
	private double dgWorst;
	private double density;
	private double area;
	
	public int getCheckListId() {
		return checkListId;
	}

	public void setCheckListId(int checkListId) {
		this.checkListId = checkListId;
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

	public AndroidCheckdataBuffer(int checkListId,int lb, int xb, String region, String tree,
			double dgBest, double dgMiddle, double dgWorst, double density,double area) 
	{
		super();
		this.checkListId=checkListId;
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
	
	public static ArrayList<AndroidCheckdataBuffer> getBuffersByCheckListId(int checkListId)
	{
		ArrayList<AndroidCheckdataBuffer> buffers=new ArrayList<AndroidCheckdataBuffer>();
		String select="select * from androidcheckdatabuffer where checkListId="+checkListId;
		ConnectDatabase connectDatabase;
		try 
		{
			connectDatabase = new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			while(rs.next())
			{
				int id=Integer.parseInt(rs.getString("checkListId"));
				int lb=Integer.parseInt(rs.getString("lb"));
				int xb=Integer.parseInt(rs.getString("xb"));
				String region=rs.getString("region");
				String tree=rs.getString("tree");
				double dgBest=Double.parseDouble(rs.getString("dgBest"));
				double dgMiddle=Double.parseDouble(rs.getString("dgMiddle"));
				double dgWorst=Double.parseDouble(rs.getString("dgWorst"));
				double density=Double.parseDouble(rs.getString("density"));
				double area=Double.parseDouble(rs.getString("area"));
				buffers.add(new AndroidCheckdataBuffer(id, lb, xb, region, tree, dgBest, dgMiddle, dgWorst, density, area));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		} 
		connectDatabase.close();
		return buffers;
	}
	
	private boolean isItemExist()
	{
		String select="select * from androidcheckdatabuffer where lb="+lb+" and xb="+xb;
		ConnectDatabase connectDatabase;
		try 
		{
			connectDatabase = new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			if(rs.next())
				return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		} 
		connectDatabase.close();
		return false;
	}
	
	public boolean insertOrUpdate()
	{
		String insert="insert into androidcheckdatabuffer values("+checkListId+","+lb+","+xb+",'"+region+"','"+tree+"','"
				+dgBest+"','"+dgMiddle+"','"+dgWorst+"','"+density+"','"+area+"')";
		String update="update androidcheckdatabuffer set checkListId='"+checkListId+"',region='"+region+"',tree='"+tree+"',dgBest='"+dgBest
				+"',dgMiddle='"+dgMiddle+"',dgWorst='"+dgWorst+"',density='"+density+"',area='"+area+"' where lb="+lb+" and xb="+xb;
		ConnectDatabase connectDatabase;
		int result=0;
		try 
		{
			connectDatabase = new ConnectDatabase();
			if(!isItemExist())
				result=connectDatabase.exeUpdate(insert);
			else
				result=connectDatabase.exeUpdate(update);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		} 
		connectDatabase.close();
		if(result>0)
			return true;
		else 
			return false;
	}
	
	private static int getItemNumByCheckListId(int checkListId)
	{
		String select="select * from androidcheckdatabuffer where checkListId="+checkListId;
		ConnectDatabase connectDatabase;
		int result=0;
		try 
		{
			connectDatabase = new ConnectDatabase();
			ResultSet rs=connectDatabase.exeQuery(select);
			while(rs.next())
				result++;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		} 
		connectDatabase.close();
		return result;
	}
	
	public static void scanBuffer()
	{
		try 
		{
			ArrayList<Integer> uncheckedIds=CheckList.getUnCheckListIdList();
			for(int id:uncheckedIds)
				if(CheckList.getListSize(id)==getItemNumByCheckListId(id))
					updateCheckData(id);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void updateCheckData(int checkListId) throws ClassNotFoundException, SQLException 
	{
		ArrayList<AndroidCheckdataBuffer> buffers=AndroidCheckdataBuffer.getBuffersByCheckListId(checkListId);
		ConnectDatabase connectDatabase=new ConnectDatabase();
		for(AndroidCheckdataBuffer buffer:buffers)
		{
			String region=NameTransform.getRegionName(buffer.getRegion());
			String tree=NameTransform.getTreeName(buffer.getRegion(),buffer.getTree());
			double dgBest=buffer.getDgBest();
			double dgMiddle=buffer.getDgMiddle();
			double dgWorst=buffer.getDgWorst();
			double density=buffer.getDensity();
			double area=buffer.getArea();
			double volume=Calculate.CalV(buffer.getRegion(), buffer.getTree(), dgBest, dgMiddle, dgWorst, density, area);
			DecimalFormat df = new DecimalFormat("#.00");
			volume=Double.parseDouble(df.format(volume));
			String date=MyDate.getNowDate();
			String updateCheckData="update checkdata set region='"+region+"',tree='"+tree+"',dgBest='"+dgBest
					+"',dgMiddle='"+dgMiddle+"',dgWorst='"+dgWorst+"',density='"+density+"',area='"+area
					+"',volume='"+volume+"',checkDate='"+date+"',checked=1 where lb="+buffer.getLb()+" and xb="+buffer.getXb();
			connectDatabase.exeUpdate(updateCheckData);//更新Checkdata
		}
		String updateCheckList="update checklist set checked=1 where id="+checkListId;
		connectDatabase.exeUpdate(updateCheckList);//更新CheckList
		String deleteBuffer="delete from androidcheckdatabuffer where checkListId="+checkListId;
		connectDatabase.exeUpdate(deleteBuffer);//删除缓存
		connectDatabase.close();
	}

	public static void main(String[] args) 
	{
		XmlTool tool=new XmlTool("D:/20.xml");
		ArrayList<AndroidCheckdataBuffer> lists=tool.readCheckData();
		UnCheckList unCheckList=new UnCheckList();
		for(AndroidCheckdataBuffer data:lists)
			if(unCheckList.inUnCheckedList(data.getLb(), data.getXb(),data.getCheckListId()))
				data.insertOrUpdate();
		scanBuffer();
	}
}
