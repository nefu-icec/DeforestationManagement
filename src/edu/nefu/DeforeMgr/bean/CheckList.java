package edu.nefu.DeforeMgr.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nefu.DeforeMgr.util.LB_XB;
import edu.nefu.DeforeMgr.util.MyList;

public class CheckList 
{
	private int id;
	private String list;
	private int checked;
	
	public static int GET_ALL=0;
	public static int GET_CHECK=1;
	public static int GET_UNCHECK=2;
	
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
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	
	public static ArrayList<CheckList> getCheckList(int getMethod) throws ClassNotFoundException, SQLException
	{
		ArrayList<CheckList> checkLists=new ArrayList<CheckList>();
		CheckList checkList;
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select * from checklist";
		switch (getMethod) 
		{
		case 0:
			break;
		case 2:
			select+=" where checked=0";
			break;
		case 1:
			select+=" where checked=1";
			break;
		default:
			break;
		}
		ResultSet rs=connectDatabase.exeQuery(select);
		while(rs.next())
		{
			checkList=new CheckList();
			checkList.setId(Integer.parseInt(rs.getString("id")));
			checkList.setList(rs.getString("list"));
			checkList.setChecked(Integer.parseInt(rs.getString("checked")));
			checkLists.add(checkList);
		}
		connectDatabase.close();
		return checkLists;
	}
	
	public static CheckList getCheckListById(int id) throws ClassNotFoundException, SQLException 
	{
		CheckList checkList=new CheckList();
		ConnectDatabase connectDatabase=new ConnectDatabase();
		String select="select * from checklist where id="+id;
		ResultSet rs=connectDatabase.exeQuery(select);
		if(rs.next())
		{
			checkList.setId(Integer.parseInt(rs.getString("id")));
			checkList.setList(rs.getString("list"));
			checkList.setChecked(Integer.parseInt(rs.getString("checked")));
		}
		connectDatabase.close();
		return checkList;
	}
	
	public static int [] getListId(String list)
	{
		String [] sIds=list.split(",");
		int [] ids=new int[sIds.length];
		for(int i=0;i<sIds.length;i++)
			ids[i]=Integer.parseInt(sIds[i]);
		return ids;
	}
	
	public static int [] getUnCheckListId() throws ClassNotFoundException, SQLException
	{
		int count=0;
		int [] listId;
		int [] unCheckListId;
		ArrayList<CheckList> checkLists=getCheckList(GET_UNCHECK);
		if(checkLists.isEmpty())
			unCheckListId=null;
		else
		{
			for(int i=0;i<checkLists.size();i++)
			{
				listId=getListId(checkLists.get(i).getList());
				for(int j=0;j<listId.length;j++)
					count++;
			}
			unCheckListId=new int[count];
			int n=0;
			for(int i=0;i<checkLists.size();i++)
			{
				listId=getListId(checkLists.get(i).getList());
				for(int j=0;j<listId.length;j++)
				{
					unCheckListId[n]=listId[j];
					n++;
				}
			}
		}
		return unCheckListId;
	}
	
	public static ArrayList<Integer> getUnCheckListIdList()
	{
		ArrayList<Integer> unCheckListIds=new ArrayList<Integer>();
		try
		{
			ArrayList<CheckList> checkLists = getCheckList(GET_UNCHECK);
			for (CheckList item : checkLists)
				unCheckListIds.add(item.getId());
			return unCheckListIds;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getSUnCheckListIds() throws ClassNotFoundException, SQLException
	{
		int [] unCheckListId=getUnCheckListId();
		String sUnCheckListIds="";
		if(unCheckListId!=null)
		{
			for(int i=0;i<unCheckListId.length;i++)
				sUnCheckListIds+=unCheckListId[i]+",";
			sUnCheckListIds=sUnCheckListIds.substring(0, sUnCheckListIds.length()-1);
		}
		else 
			sUnCheckListIds="'null'";
		return sUnCheckListIds;
	}

	public static ArrayList<CheckData> getCheckDataForChoose(int id) throws ClassNotFoundException, SQLException
	{
		//确定打表的xb
		CutList cutList=CutList.getCutListById(id);
		ArrayList<LB_XB> lbxbs=CutList.getListLBXB(cutList.getList());
		ArrayList<CheckData>checkDatas=new ArrayList<CheckData>();
		ConnectDatabase connectDatabase=new ConnectDatabase();
		for(int i=0;i<lbxbs.size();i++)
		{
			String select="select id from checkdata where lb="+lbxbs.get(i).getLb()+" and xb="+lbxbs.get(i).getXb();
			ResultSet rs=connectDatabase.exeQuery(select);
			if(rs.next())
				checkDatas.add(new CheckData(rs.getString("id"), lbxbs.get(i).getLb(), lbxbs.get(i).getXb()));
		}
		connectDatabase.close();
		return checkDatas;
	}
	
	public static int getListSize(int id) throws ClassNotFoundException, SQLException
	{
		int listSize=0;
		CheckList checkList=getCheckListById(id);
		listSize=checkList.getList().split(",").length;
		return listSize;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> getCheckedCutListId(int checkedCheckListId) throws ClassNotFoundException, SQLException
	{
		ArrayList<Integer> checkedCutListIds=new ArrayList<Integer>();
		CheckList checkList=getCheckListById(checkedCheckListId);
		ArrayList<LB_XB> checkedLbxbs=CutList.getListLBXB(checkList.getList());
		String select;
		ConnectDatabase connectDatabase=new ConnectDatabase();
		ResultSet rs;
		int cutListId=0;
		for(int i=0;i<checkedLbxbs.size();i++)
		{
			select="select * from cutlist where list like '%"+checkedLbxbs.get(i).getLb()+"-"+checkedLbxbs.get(i).getXb()+"%'";
			//System.out.println(select);
			rs=connectDatabase.exeQuery(select);
			if(rs.next())
				cutListId=Integer.parseInt(rs.getString("id"));
			checkedCutListIds.add(cutListId);
		}
		connectDatabase.close();
		return MyList.removeDuplicateWithOrder(checkedCutListIds);
	}
	
	public static ArrayList<LB_XB> getLbXbByCheckListID(int id) throws ClassNotFoundException, SQLException
	{
		ArrayList<LB_XB> lbxbs=new ArrayList<LB_XB>();
		CheckList checkList=getCheckListById(id);
		String list=checkList.getList();
		String [] slbxb=list.split(",");
		for(String lbxb:slbxb)
		{
			String [] buffer=lbxb.split("-");
			int lb=Integer.parseInt(buffer[0]);
			int xb=Integer.parseInt(buffer[1]);
			lbxbs.add(new LB_XB(lb, xb));
		}
		return lbxbs;
	}
	
	public static int getIdByLbAndXb(int lb,int xb)
	{
		String select="select id from checklist where list like '%"+lb+"-"+xb+"%' and checked=0";
		try 
		{
			ConnectDatabase connectDatabase = new ConnectDatabase();
			ResultSet rs = connectDatabase.exeQuery(select);
			if (rs.next())
			{
				int id= Integer.parseInt(rs.getString("id"));
				connectDatabase.close();
				return id;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
