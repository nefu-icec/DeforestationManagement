package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.nefu.DeforeMgr.bean.ConnectDatabase;
import edu.nefu.DeforeMgr.bean.CutList;
import edu.nefu.DeforeMgr.util.LB_XB;
import edu.nefu.DeforeMgr.util.MyDate;

@WebServlet("/InputCutServlet")
public class InputCutServlet extends HttpServlet {
	String task;
	private static final long serialVersionUID = 1L;
       
    public InputCutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		task=request.getParameter("task");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task) 
		{
		case "submit":
			inputCutData(request,response);
			break;
		default:
			break;
		}
	}

	private void inputCutData(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String sCount=request.getParameter("count");
		String id=request.getParameter("id");
		String nowDate=MyDate.getNowDate();
		int count=Integer.parseInt(sCount);
		double [] vols=new double[count];
		for(int i=0;i<count;i++)
			vols[i]=Double.parseDouble(request.getParameter("reportVolume"+i));
		System.out.println(id);
		try
		{
			CutList cutList=CutList.getCutListById(Integer.parseInt(id));
			ArrayList<LB_XB> lbxbs=CutList.getListLBXB(cutList.getList());
			String update;
			ConnectDatabase connectDatabase=new ConnectDatabase();
			for(int i=0;i<count;i++)
			{
				update="update checkdata set reportVolume="+vols[i]+",cut=1,reportDate='"
							+nowDate+"' where lb="+lbxbs.get(i).getLb()+" and xb="+lbxbs.get(i).getXb();
				System.out.println(update);
				connectDatabase.exeUpdate(update);
			}
			update="update cutlist set cut=1 where id="+id;
			connectDatabase.exeUpdate(update);
			connectDatabase.close();
			response.sendRedirect("inputInfo/reportCut.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
