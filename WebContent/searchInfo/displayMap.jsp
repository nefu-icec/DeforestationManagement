<%@page import="edu.nefu.DeforeMgr.bean.Position"%>
<%@page import="edu.nefu.DeforeMgr.map.Scaling"%>
<%@page import="edu.nefu.DeforeMgr.util.MyMath"%>
<%@page import="edu.nefu.DeforeMgr.bean.Constant"%>
<%@page import="edu.nefu.DeforeMgr.map.DisplayConfiguration"%>
<%@page import="edu.nefu.DeforeMgr.map.MapInfo"%>
<%@page import="edu.nefu.DeforeMgr.map.Map"%>
<%@page import="edu.nefu.DeforeMgr.map.Point"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nefu.DeforeMgr.map.Display"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
Map map=new Map();
DisplayConfiguration configuration=null;
if(request.getSession().getAttribute("DisplayConfiguration")!=null)
	configuration=(DisplayConfiguration)request.getSession().getAttribute("DisplayConfiguration");
Display display=map.getDisplay(configuration);
int width=display.getWidth();
int height=display.getHeight();
ArrayList<Point> points=display.getPoints();
double proportion=1.0;
if(configuration!=null)
	proportion=configuration.getProportion();
//设置缩放游标
Scaling scaling=new Scaling();
int cursorPosition=scaling.getCursorPosition(proportion);
//获取控制面板位置
Position panelPosition=new Position(Position.PANEL);
if(session.getAttribute("PanelPosition")!=null)
	panelPosition=(Position)session.getAttribute("PanelPosition");
//获取map div位置
Position mapPosition=new Position(Position.MAP);
if(session.getAttribute("MapPosition")!=null)
	mapPosition=(Position)session.getAttribute("MapPosition");
//获取scail div位置
Position scailPostion=new Position(Position.SCAIL);
if(session.getAttribute("ScailPostion")!=null)
	scailPostion=(Position)session.getAttribute("ScailPostion");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>林班小班示意图</title>
    <link rel="stylesheet" href="../css/displayMap.css">
    <script src="../js/wichung-1.0.js"></script>
	<script src="../js/jquery-1.4.4.min.js"></script>
</head>
<body>
	<jsp:include page="../public/head.jsp"></jsp:include>
	<div id="mapContent">
		<div id="map">
		    <div id="containerTitle" onmousedown="dragMap(this.parentNode, event);">
		    		<div class="proportion">显示比例：<%=proportion%></div>
		    		<div class="content">林班小班示意图</div>
		    		<div id="closeTitle" onclick="closeMap()"></div>
		    </div>
		    <div id="container"></div>
	    </div>
	    <div id="scailByProportion">
	    	<div id="scailTitle" onmousedown="dragScail(this.parentNode, event);">
	    		<div class="word">按比例缩放</div>
	    		<div id="closeScail" onclick="closeScail()"></div>
	    		<div class="enlarge">
		    		<form action="../MapServlet?task=multipliedEnlarge" id="enlargeForm" method="post">
				    	放大<input type="text" name="enlarge" size="2" onmousedown="this.focus()">倍（1~10）
				    	<input type="button" onclick="enlargeSubmitVerify()" value="确定">
				    </form>
	    		</div>
	    		<div class="reduce">
				    <form action="../MapServlet?task=multipliedReduce" id="reduceForm" method="post">
				    	缩小<input type="text" name="reduce" size="2" onmousedown="this.focus()">倍（1~10）
				    	<input type="button" onclick="reduceSubmitVerify()"value="确定">
				    </form>
	    		</div>
	    	</div>
	    </div>
	    <div id="panel">
	    	<div id="title" onmousedown="dragPanel(this.parentNode, event);">
	    		<div class="word">控制面板</div>
	    		<div id="close" onclick="closePanel()"></div>
	    	</div>
		   	<div id="moving"> 
				<div class="up" onclick="location.href='../MapServlet?task=up';"></div>
				<div class="down" onclick="location.href='../MapServlet?task=down';"></div>
				<div class="left" onclick="location.href='../MapServlet?task=left';"></div>
				<div class="right" onclick="location.href='../MapServlet?task=right';"></div>
			</div>
			<div	id="scaling">
				<div class="enlargePanel">
					<div class="symbol" onclick="location.href='../MapServlet?task=enlarge';"></div>
				</div>
				<div id="scrollBar" class="scrollBar">
				<%
				for(int i=0;i<Constant.Map.CalibrationNumber;i++)
				{
					if(i<cursorPosition)
					{
						%>
						<div class="light" style="top:<%=10*i%>px;" onclick="location.href='../MapServlet?task=enlargeByProportion&i=<%=i%>';"></div>
						<%
					}
					else if(i>cursorPosition)
					{
						%>
						<div class="dark" style="top:<%=10*i%>px;" onclick="location.href='../MapServlet?task=reduceByProportion&i=<%=i%>';"></div>
						<%
					}
					else
					{
						%>
						<div id="cursor" class="cursor" style="top:<%=10*i%>px;" onmousedown="scail(this.parentNode);"></div>
						<div id="cursorMoved" style="top:<%=10*i%>px;"></div>
						<%
					}
				}
				%>
				</div>
				<div class="reducePanel">
					<div class="symbol" onclick="location.href='../MapServlet?task=reduce';"></div>
				</div>
			</div>
	    </div>
	</div>
    <jsp:include page="../public/foot.jsp"></jsp:include>

<script>	
	document.getElementById("mapContent").style.left=(window.screen.availWidth-1000)/2+"px";
	//设置地图宽、高
	var oContainer = document.getElementById('container');
	oContainer.style.width =<%= width%>;
	oContainer.style.height =<%= height%>;
	//设置控制面板位置
	var oPanel=document.getElementById("panel");
	oPanel.style.top=<%=panelPosition.getTop()%>;
	oPanel.style.left=<%=panelPosition.getLeft()%>;
	//设置map div位置
	var oMap=document.getElementById("map");
	oMap.style.top=<%=mapPosition.getTop()%>;
	oMap.style.left=<%=mapPosition.getLeft()%>;
	//设置scail div位置
	var oScail=document.getElementById("scailByProportion");
	oScail.style.top=<%=scailPostion.getTop()%>;
	oScail.style.left=<%=scailPostion.getLeft()%>;
	var arrX = [];
	var arrY = [];
	<%
	for(int i=0;i<points.size();i++)
	{
		if(i!=points.size()-1)
		{
			%>
			arrX.push(<%=points.get(i).x%>);
			arrY.push(<%=points.get(i).y%>);
			<%
		}
	}
	%>
</script>
<script src="../js/displayMap.js"></script>