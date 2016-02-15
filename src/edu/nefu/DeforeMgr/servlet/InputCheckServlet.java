package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.CheckData;
import edu.nefu.DeforeMgr.bean.CheckList;
import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.CutList;
import edu.nefu.DeforeMgr.bean.InputCheck;
import edu.nefu.DeforeMgr.calculate.Calculate;
import edu.nefu.DeforeMgr.util.FileName;
import edu.nefu.DeforeMgr.util.LB_XB;
import edu.nefu.DeforeMgr.util.MyDate;
import edu.nefu.DeforeMgr.util.NameTransform;
import edu.nefu.DeforeMgr.util.SQL;

@WebServlet("/InputCheckServlet")
public class InputCheckServlet extends HttpServlet 
{
	String task;
	private static final long serialVersionUID = 1L;
       
    public InputCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "delete":
			delete(request,response);
			break;
		case "submit":				
			submit(request,response);
			break;
		case "clear":
			clear(request,response);
			break;
		case "importCheckFile":
			importCheckFile(request,response);
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "add":
			add(request,response);
			break;
		default:
			break;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession(); 
		request.setCharacterEncoding("gb2312");
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		String region=request.getParameter("region");
		String tree=request.getParameter("tree");
		tree=NameTransform.getTreeName(region, tree);
		region=NameTransform.getRegionName(region);
		double dgBest=Double.parseDouble(request.getParameter("dgBest"))  ;
		double dgMiddle=Double.parseDouble(request.getParameter("dgMiddle"));
		double dgWorst=Double.parseDouble(request.getParameter("dgWorst"));
		double density=Double.parseDouble(request.getParameter("density"));
		double area=Double.parseDouble(request.getParameter("area"));
		double volume=Calculate.CalV(NameTransform.getRegionValue(region), NameTransform.getTreeValue(region, tree), dgBest, dgMiddle, dgWorst, density, area);
		DecimalFormat df = new DecimalFormat("#.00");
		volume=Double.parseDouble(df.format(volume));
		InputCheck inputCheck=new InputCheck(lb, xb, region, tree, dgBest, dgMiddle, dgWorst, density, area, volume);
		//从session中获取inputCheckList
		ArrayList<InputCheck> inputCheckList=new ArrayList<InputCheck>();
		if(session.getAttribute("inputCheckList")==null)
			session.setAttribute("inputCheckList", inputCheckList);
		else 
			inputCheckList=(ArrayList<InputCheck>)session.getAttribute("inputCheckList");
		//inputCheck放入session
		inputCheckList.add(inputCheck);
		response.sendRedirect("inputInfo/inputCheckList.jsp");
	}
	
	@SuppressWarnings("unchecked")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String slb=request.getParameter("lb");
		String sxb=request.getParameter("xb");
		int lb=Integer.parseInt(slb);
		int xb=Integer.parseInt(sxb);
		System.out.print(lb+" "+xb);
		HttpSession session=request.getSession(); 
		ArrayList<InputCheck> inputCheckList = new ArrayList<InputCheck>();
		if(session.getAttribute("inputCheckList")==null)
			System.out.println("列表空");
		else 
			inputCheckList=(ArrayList<InputCheck>)session.getAttribute("inputCheckList");
		if(!inputCheckList.isEmpty())
		{
			for(int i=0;i<inputCheckList.size();i++)
				if(inputCheckList.get(i).getLb()==lb&&inputCheckList.get(i).getXb()==xb)
					inputCheckList.remove(i);
			session.setAttribute("inputCheckList", inputCheckList);
		}
		response.sendRedirect("inputInfo/inputCheckList.jsp");
	}
	
	@SuppressWarnings("unchecked")
	private void submit(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		HttpSession session=request.getSession(); 
		ArrayList<InputCheck> inputCheckList=new ArrayList<InputCheck>();
		if(session.getAttribute("inputCheckList")==null)
			System.out.println("列表空");
		else 
			inputCheckList=(ArrayList<InputCheck>)session.getAttribute("inputCheckList");
		//开始动态生成更新语句,操作数据库
		ConnectDatabase connectDatabase;
		String update;
		if(!inputCheckList.isEmpty())
		{
			for(int i=0;i<inputCheckList.size();i++)
			{
				update="update checkdata set ";
				update+="region="+"'"+inputCheckList.get(i).getRegion()+"',";
				update+="tree="+"'"+inputCheckList.get(i).getTree()+"',";
				update+="dgBest="+"'"+inputCheckList.get(i).getDgBest()+"',";
				update+="dgMiddle="+"'"+inputCheckList.get(i).getDgMiddle()+"',";
				update+="dgWorst="+"'"+inputCheckList.get(i).getDgWorst()+"',";
				update+="density="+"'"+inputCheckList.get(i).getDensity()+"',";
				update+="area="+"'"+inputCheckList.get(i).getArea()+"',";
				if(inputCheckList.get(i).getVolume()!=0)
					update+="volume="+"'"+inputCheckList.get(i).getVolume()+"',";
				update+="checked='1',";
				update+="checkDate='"+MyDate.getNowDate()+"',";
				update=update.substring(0,update.length()-1);	
				update+=" where lb="+inputCheckList.get(i).getLb()+" and xb="+inputCheckList.get(i).getXb();
				//System.out.println(update);
				try 
				{
					connectDatabase = new ConnectDatabase();
					connectDatabase.exeUpdate(update);
					connectDatabase.close();
				} 
				catch (Exception e) 
				{
					System.out.println("录入失败");
					e.printStackTrace();
				} 
			}
			int sessionId=0;
			if(session.getAttribute("checkListId")!=null)
				sessionId=Integer.parseInt((String)session.getAttribute("checkListId"));
			try 
			{
				connectDatabase = new ConnectDatabase();
				//checklist中checked置1
				update="update checklist set checked=1 where id="+sessionId;
				connectDatabase.exeUpdate(update);
				//cutlist中checked置1
				setCutListChecked1(sessionId);
				//checkdata中checked置1
				ArrayList<Integer> checkedCutListIds=CheckList.getCheckedCutListId(sessionId);
				CutList cutList;
				ArrayList<LB_XB> lbxbs;
				for(int i=0;i<checkedCutListIds.size();i++)
				{
					cutList=CutList.getCutListById(checkedCutListIds.get(i));
					lbxbs=CutList.getListLBXB(cutList.getList());
					for(int j=0;j<lbxbs.size();j++)
					{
						update="update checkdata set checked=1 where lb="+lbxbs.get(j).getLb()+" and xb="+lbxbs.get(j).getXb();
						System.out.println(update);
						connectDatabase.exeUpdate(update);
					}
				}
				connectDatabase.close();
			} 
			catch (Exception e) 
			{
				System.out.println("录入失败");
				e.printStackTrace();
			} 
			session.removeAttribute("checkListId");	
		}
		response.sendRedirect("inputInfo/reportChecked.jsp");
	}

	@SuppressWarnings("unchecked")
	private void clear(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		HttpSession session=request.getSession(); 
		ArrayList<InputCheck> inputCheckList=new ArrayList<InputCheck>();
		if(session.getAttribute("inputCheckList")==null)
			System.out.println("列表空");
		else 
			inputCheckList=(ArrayList<InputCheck>)session.getAttribute("inputCheckList");
		inputCheckList.clear();
		response.sendRedirect("inputInfo/inputCheckList.jsp");
	}
	

	private void importCheckFile(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		String id=request.getParameter("id");
		int checkListId=Integer.parseInt(id);
		ServletContext application=this.getServletContext();   
		
		String fileName=request.getParameter("fileName");
		String path=application.getRealPath("/")+"sql\\";
		//用当前时间命名文件
		String newFileName=MyDate.getNowTime()+".txt";
		FileName.modifyFileName(path, fileName,newFileName);
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			//数据写入CheckData
			ArrayList<String> updates=SQL.createUpdate(path, newFileName, checkListId);
			for(int i=0;i<updates.size();i++)
				connectDatabase.exeUpdate(updates.get(i));
			//checkdata中checked置1
			CheckList checkList=CheckList.getCheckListById(checkListId);
			ArrayList<LB_XB> checkLbxbs=CutList.getListLBXB(checkList.getList());
			String update;
			for(int i=0;i<checkLbxbs.size();i++)
			{
				update="update checkdata set checked=1 where lb="+checkLbxbs.get(i).getLb()+" and xb="+checkLbxbs.get(i).getXb();
				connectDatabase.exeUpdate(update);
			}
			for(int i=0;i<checkLbxbs.size();i++)
			{
				CheckData data=CheckData.getCheckDataByLBAndXB(checkLbxbs.get(i).getLb(), checkLbxbs.get(i).getXb());
				double volume=Calculate.CalV
						(NameTransform.getRegionValue(data.getRegion()), NameTransform.getTreeValue(data.getRegion(), data.getTree()), 
								data.getDgBest(),data.getDgMiddle(), data.getDgWorst(),data.getDensity(),data.getArea());
				DecimalFormat df = new DecimalFormat("#.00");
				volume=Double.parseDouble(df.format(volume));
				update="update checkdata set volume="+volume+" where lb="+checkLbxbs.get(i).getLb()+" and xb="+checkLbxbs.get(i).getXb();
				connectDatabase.exeUpdate(update);
			}
			//cutlist中checked置1
			setCutListChecked1(checkListId);
			//checklist中checked置1
			update="update checklist set checked=1 where id="+id;
			connectDatabase.exeUpdate(update);
			//sqllist表中插入文件名
			String insert="insert into sqllist(sName,date) values('"+newFileName+"','"+MyDate.getNowDate()+"')";
			connectDatabase.exeUpdate(insert);
			connectDatabase.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("inputInfo/importCheckedFile.jsp");
	}
	
	/**
	 * cutlist中checked置1
	 * @param id cutlist id
	 */
	private void setCutListChecked1(int id)
	{
		String update;
		try
		{
			ConnectDatabase connectDatabase=new ConnectDatabase();
			ArrayList<Integer> checkedCutListIds=CheckList.getCheckedCutListId(id);
			for(int i=0;i<checkedCutListIds.size();i++)
			{
				update="update cutlist set checked=1 where id="+checkedCutListIds.get(i);
				connectDatabase.exeUpdate(update);
				connectDatabase.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
