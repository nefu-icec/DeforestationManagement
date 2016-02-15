package edu.nefu.DeforeMgr.bean;

import java.util.ArrayList;

import edu.nefu.DeforeMgr.util.LB_XB;
import edu.nefu.DeforeMgr.util.MyDate;

/**
 * 未验收列表
 * @author lm2343635
 *
 */
public class UnCheckList 
{
	private ArrayList<UnCheckListItem> items;
	
	public ArrayList<UnCheckListItem> getItems()
	{
		return items;
	}

	public void setItems(ArrayList<UnCheckListItem> items) 
	{
		this.items = items;
	}
	
	public UnCheckList() 
	{
		super();
		getUnCheckedList();
	}

	private void getUnCheckedList()
	{
		items=new ArrayList<UnCheckListItem>();
		try 
		{
			ArrayList<CheckList> uncheckedList=CheckList.getCheckList(CheckList.GET_UNCHECK);
			for(CheckList list:uncheckedList)
			{
				ArrayList<LB_XB> lbxbs=CheckList.getLbXbByCheckListID(list.getId());
				for(LB_XB lbxb:lbxbs)
				{
					int checkListId=CheckList.getIdByLbAndXb(lbxb.getLb(), lbxb.getXb());
					String lb=String.valueOf(lbxb.getLb());
					String xb=String.valueOf(lbxb.getXb());
					String date=MyDate.getNowDate();
					items.add(new UnCheckListItem(checkListId, lb, xb, date));
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public boolean inUnCheckedList(int lb,int xb,int checkListId)
	{
		for(UnCheckListItem item:items)
			if(Integer.parseInt(item.getLb())==lb&&Integer.parseInt(item.getXb())==xb&&item.getCheckListId()==checkListId)
				return true;
		return false;
	}
}
