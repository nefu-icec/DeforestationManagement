package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.util.LB_XB;

public class CutList 
{
	private int id;
	private String list;
	private int cut;
	private int checked;
	
	public static int GET_ALL=0;
	public static int GET_CUT=2;
	public static int GET_UNCUT=1;
	public static int GET_CUT_UNCHECKED=3;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
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
	public static ArrayList<CutList> getCutList(int getMethod) throws ClassNotFoundException, SQLException
	{
		ArrayList<CutList> cutLists=new ArrayList<CutList>();
		CutList cutList;
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select * from cutlist";
		switch (getMethod) 
		{
		case 0:
			break;
		case 1:
			select+=" where cut=0";
			break;
		case 2:
			select+=" where cut=1";
			break;
		case 3:
			select+=" where cut=1 and checked=0";
			break;
		default:
			break;
		}
		ResultSet rs=connectDatabase.exeQuery(select);
		while(rs.next())
		{
			cutList=new CutList();
			cutList.setId(Integer.parseInt(rs.getString("id")));
			cutList.setList(rs.getString("list"));
			cutList.setCut(Integer.parseInt(rs.getString("cut")));
			cutList.setChecked(Integer.parseInt(rs.getString("checked")));
			cutLists.add(cutList);
		}
		connectDatabase.close();
		return cutLists;
	}
	
	public static CutList getCutListById(int id) throws ClassNotFoundException, SQLException
	{
		CutList cutList=new CutList();
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select * from cutlist where id="+id;
		ResultSet rs=connectDatabase.exeQuery(select);
		if(rs.next())
		{
			cutList.setId(Integer.parseInt(rs.getString("id")));
			cutList.setList(rs.getString("list"));
			cutList.setCut(Integer.parseInt(rs.getString("cut")));
			cutList.setChecked(Integer.parseInt(rs.getString("checked")));
		}
		connectDatabase.close();
		return cutList;
	}
	
	public static ArrayList<LB_XB> getListLBXB(String list)
	{
		String [] lists=list.split(",");
		ArrayList<LB_XB> lbxbs=new ArrayList<LB_XB>();
		for(int i=0;i<lists.length;i++)
		{
			String [] buffer=lists[i].split("-");
			int lb=Integer.parseInt(buffer[0]);
			int xb=Integer.parseInt(buffer[1]);
			lbxbs.add(new LB_XB(lb, xb));
		}
		return lbxbs;
	}
	
	public static ArrayList<LB_XB> getUnCutLBXB() throws ClassNotFoundException, SQLException
	{
		ArrayList<LB_XB> lbxbs=new ArrayList<LB_XB>();
		ArrayList<CutList> unCutLists=CutList.getCutList(GET_UNCUT);
		for(int i=0;i<unCutLists.size();i++)
		{
			String [] lb_xb=unCutLists.get(i).getList().split(",");
			for(int j=0;j<lb_xb.length;j++)
			{
				String [] buffer=lb_xb[j].split("-");
				int lb=Integer.parseInt(buffer[0]);
				int xb=Integer.parseInt(buffer[1]);
				lbxbs.add(new LB_XB(lb, xb));
			}
		}
		return lbxbs;
	}
}
