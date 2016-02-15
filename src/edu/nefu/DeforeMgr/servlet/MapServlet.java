package edu.nefu.DeforeMgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.nefu.DeforeMgr.bean.Constant;
import edu.nefu.DeforeMgr.bean.Position;
import edu.nefu.DeforeMgr.map.DisplayConfiguration;
import edu.nefu.DeforeMgr.map.Map;
import edu.nefu.DeforeMgr.map.MapInfo;
import edu.nefu.DeforeMgr.map.Scaling;

@WebServlet("/MapServlet")
public class MapServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String task;
	private DisplayConfiguration configuration;//显示配置信息
	private static final int REDUCE=0;
	private static final int ENLARGE=1;
       
    public MapServlet() 
    {
        super();
        configuration=new DisplayConfiguration();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "enlarge":
			enlarge(request,response,Constant.Map.EnlargeProportion);
			break;
		case "reduce":
			reduce(request,response,Constant.Map.ReduceProportion);
			break;
		case "up":
			up(request,response);
			break;
		case "down":
			down(request,response);
			break;
		case "right":
			right(request,response);
			break;
		case "left":
			left(request,response);
			break;
		case "enlargeByProportion":
			enlargeByProportion(request,response);
			break;
		case "reduceByProportion":
			reduceByProportion(request,response);
			break;
		case "scailByMove":
			scailByCursor(request,response);
			break;
		case "receivePanelPosition":
			receivePanelPosition(request);
			break;
		case "receiveMapPosition":
			receiveMapPosition(request);
			break;
		case "receiveScailPosition":
			receiveScailPosition(request);
			break;
		case "getXbInfo":
			getXbInfo(request,response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "multipliedEnlarge":
			double enlarge=Double.parseDouble(request.getParameter("enlarge"));
			enlarge(request, response, enlarge);
			break;
		case "multipliedReduce":
			double reduce=Double.parseDouble(request.getParameter("reduce"));
			reduce(request, response, 1/reduce);
			break;
		default:
			break;
		}
	}
	
	private void setConfigurationBySession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		if(session.getAttribute("DisplayConfiguration")==null)
			session.setAttribute("DisplayConfiguration", this.configuration);
		else
			this.configuration=(DisplayConfiguration)session.getAttribute("DisplayConfiguration");
	}
	
	private void writeConfigurationToSession(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.setAttribute("DisplayConfiguration", this.configuration);
	}
	
	/**
	 * 将配置文件推送至前端
	 */
	private void pushConfigurationToPage(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().setAttribute("DisplayConfiguration", configuration);
		try 
		{
			response.sendRedirect("searchInfo/displayMap.jsp");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * 得到纬度跨度范围
	 * @return getLatitudeWidth()
	 */
	private double getLatitudeWidth()
	{
		Map map=new Map();
		double startLatitude=configuration.getStartLatitude();
		double proportion=configuration.getProportion();
		double endLatitude=startLatitude+proportion*(map.getMaxLatitude()-map.getMinLatitude());
		return endLatitude-startLatitude;
	}
	
	/**
	 * 得到经度跨度范围
	 * @return 经度跨度范围
	 */
	private double getLongitudeWidth()
	{
		Map map=new Map();
		double startLongitude=configuration.getStartLongitude();
		double proportion=configuration.getProportion();
		double endLongitude=startLongitude+proportion*(map.getMaxLongitude()-map.getMinLongitude());
		return endLongitude-startLongitude;
	}

	/**
	 * 设置放大缩小后的新的经纬度起始点
	 * @param method 设置的方式：放大或缩小
	 * @param multiply 放大或缩小的倍数
	 */
	private void setNewStart(int method,double multiply)
	{
		double startLatitude=configuration.getStartLatitude();
		double startLongitude=configuration.getStartLongitude();
		double newStartLatitude=0;
		double newStartLongitude=0;
		switch (method) 
		{
		case REDUCE:
			newStartLatitude=startLatitude-getLatitudeWidth()*(1/multiply-1)/2;
			newStartLongitude=startLongitude-getLongitudeWidth()*(1/multiply-1)/2;
			break;
		case ENLARGE:
			newStartLatitude=startLatitude+getLatitudeWidth()*(1-1/multiply)/2;
			newStartLongitude=startLongitude+getLongitudeWidth()*(1-1/multiply)/2;
			break;
		default:
			break;
		}
		configuration.setStartLatitude(newStartLatitude);
		configuration.setStartLongitude(newStartLongitude);
	}
	
	/**
	 * 设置新的显示点的大小
	 * @param method 设置的方式：放大或缩小
	 * @param multiply 放大或缩小的倍数
	 */
	private void setNewPointSize(int method,double multiply)
	{
		double newPointSize=configuration.getPointSize();
		switch (method)
		{
		case REDUCE:
			newPointSize*=multiply;
			break;
		case ENLARGE:
			newPointSize*=multiply;
			break;
		default:
			break;
		}
		configuration.setPointSize(newPointSize);
	}

	/**
	 * 按指定倍数缩小
	 * @param request
	 * @param response
	 * @param multiply 缩小的倍数
	 * @throws IOException
	 */
	private void reduce(HttpServletRequest request, HttpServletResponse response,double multiply)
	{
		setConfigurationBySession(request);
		double proportion=configuration.getProportion();
		if(proportion<1)
		{
			if(proportion/multiply<1)
			{
				setNewStart(REDUCE,multiply);
				setNewPointSize(REDUCE,multiply);
				configuration.setProportion(proportion/multiply);
			}
			else
				configuration=new DisplayConfiguration();
		}
		writeConfigurationToSession(request);
		pushConfigurationToPage(request, response);
	}

	/**
	 * 按指定倍数放大
	 * @param request
	 * @param response
	 * @param multiply	放大倍数
	 * @throws IOException
	 */
	private void enlarge(HttpServletRequest request,HttpServletResponse response,double multiply)
	{
		setConfigurationBySession(request);
		double proportion=configuration.getProportion();
		if(proportion>Constant.Map.MinMapProportion&&proportion/Constant.Map.EnlargeProportion>Constant.Map.MinMapProportion)
		{
			setNewStart(ENLARGE,multiply);
			setNewPointSize(ENLARGE,multiply);
			configuration.setProportion(proportion/multiply);
		}
		writeConfigurationToSession(request);
		pushConfigurationToPage(request, response);
	}
	
	private void left(HttpServletRequest request, HttpServletResponse response) 
	{
		setConfigurationBySession(request);
		double startLongitude=configuration.getStartLongitude();
		double newStartLongitude=startLongitude-getLongitudeWidth()*Constant.Map.MapMoveProportion;
		configuration.setStartLongitude(newStartLongitude);
		request.getSession().setAttribute("DisplayConfiguration", configuration);
		writeConfigurationToSession(request);
		pushConfigurationToPage(request, response);
	}

	private void right(HttpServletRequest request, HttpServletResponse response) 
	{
		setConfigurationBySession(request);
		double startLongitude=configuration.getStartLongitude();
		double newStartLongitude=startLongitude+getLongitudeWidth()*Constant.Map.MapMoveProportion;
		configuration.setStartLongitude(newStartLongitude);
		request.getSession().setAttribute("DisplayConfiguration", configuration);
		writeConfigurationToSession(request);
		pushConfigurationToPage(request, response);
	}

	private void down(HttpServletRequest request, HttpServletResponse response)
	{
		setConfigurationBySession(request);
		double startLatitude=configuration.getStartLatitude();
		double newStartLatitude=startLatitude-getLatitudeWidth()*Constant.Map.MapMoveProportion;
		configuration.setStartLatitude(newStartLatitude);
		request.getSession().setAttribute("DisplayConfiguration", configuration);
		writeConfigurationToSession(request);
		pushConfigurationToPage(request, response);
	}

	private void up(HttpServletRequest request, HttpServletResponse response)
	{
		setConfigurationBySession(request);
		double startLatitude=configuration.getStartLatitude();
		double newStartLatitude=startLatitude+getLatitudeWidth()*Constant.Map.MapMoveProportion;
		configuration.setStartLatitude(newStartLatitude);
		request.getSession().setAttribute("DisplayConfiguration", configuration);
		writeConfigurationToSession(request);
		pushConfigurationToPage(request, response);
	}

	private double getProportionByI(HttpServletRequest request)
	{
		int i=Integer.parseInt(request.getParameter("i"))+1;
		Scaling scaling=new Scaling();
		double proportion;
		if(i<15) 
			proportion=scaling.getProportions()[i];
		else
			proportion=scaling.getProportions()[14];
		return proportion;
	}
	
	private void reduceByProportion(HttpServletRequest request,HttpServletResponse response) 
	{
		setConfigurationBySession(request);
		double newProportion=getProportionByI(request);
		double multiply=configuration.getProportion()/newProportion;
		reduce(request, response, multiply);
	}

	private void enlargeByProportion(HttpServletRequest request,HttpServletResponse response)
	{
		setConfigurationBySession(request);
		double newProportion=getProportionByI(request);
		double multiply=configuration.getProportion()/newProportion;
		enlarge(request, response, multiply);
	}
	
	/**
	 * 拖拽游标缩放
	 * @param request
	 * @param response
	 */
	private void scailByCursor(HttpServletRequest request,HttpServletResponse response)
	{
		setConfigurationBySession(request);
		int top=Integer.parseInt(request.getParameter("top"));
		if((top%10)<5)
			top=top/10+1;
		else 
			top=top/10+2;
		Scaling scaling=new Scaling();
		double newProportion;
		if(top<15)
			newProportion=scaling.getProportions()[top];
		else
			newProportion=scaling.getProportions()[14];
		double multiply=configuration.getProportion()/newProportion;
		if(newProportion>configuration.getProportion())
			reduce(request, response, multiply);
		else if(newProportion<configuration.getProportion())
			enlarge(request, response, multiply);
	}
	
	/**
	 * 异步接收控制面板位置信息
	 * @param request
	 */
	private void receivePanelPosition(HttpServletRequest request) 
	{
		int panelTop=Integer.parseInt(request.getParameter("panelTop"));
		int panelLeft=Integer.parseInt(request.getParameter("panelLeft"));
		HttpSession session=request.getSession();
		Position panelPosition=new Position(panelTop,panelLeft,Position.PANEL);
		session.setAttribute("PanelPosition", panelPosition);
	}
	
	/**
	 * 异步接收地图div位置信息
	 * @param request
	 */
	private void receiveMapPosition(HttpServletRequest request) 
	{
		int mapTop=Integer.parseInt(request.getParameter("mapTop"));
		int mapLeft=Integer.parseInt(request.getParameter("mapLeft"));
		HttpSession session=request.getSession();
		Position mapPosition=new Position(mapTop, mapLeft, Position.MAP);
		session.setAttribute("MapPosition", mapPosition);
	}
	
	/**
	 * 异步接收scail div位置信息
	 * @param request
	 */
	private void receiveScailPosition(HttpServletRequest request) 
	{
		int scailTop=Integer.parseInt(request.getParameter("scailTop"));
		int scailLeft=Integer.parseInt(request.getParameter("scailLeft"));
		HttpSession session=request.getSession();
		Position scailPosition=new Position(scailTop, scailLeft, Position.SCAIL);
		session.setAttribute("ScailPostion", scailPosition);
	}
	
	
	/**
	 * 异步获取小班信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getXbInfo(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		int id=Integer.parseInt(request.getParameter("id"));
		setConfigurationBySession(request);
		Map map=new Map();
		MapInfo info=map.getDisplay(configuration).getInfos().get(id);
		String data=info.getLb()+"&"+info.getXb()+"&"+info.getLongitude()+"&"+info.getLatitude()+"&"+id;
		PrintWriter out=response.getWriter();
		out.print(data);
	}
}
