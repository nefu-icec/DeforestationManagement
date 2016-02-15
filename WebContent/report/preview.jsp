<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="edu.nefu.DeforeMgr.servlet.ExcelServlet"%>
<%@page import="edu.nefu.DeforeMgr.report.Information"%>
<%@page import="edu.nefu.DeforeMgr.bean.ReportInfo"%>
<%@ page import="java.util.*" %>
<%@ page import="edu.nefu.DeforeMgr.util.*" %>

<style>
	#menu li
	{
		float: left;
		width: 120px;
		height: 30px;
		border: 1px solid gray;
		background: #ccc;
		margin-right: 5px;
	}
</style>

<jsp:include page="../public/head.jsp"></jsp:include>			
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="reportList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				》》 您的位置：
				<a href="../public/login.jsp">主页</a> 》
				<a href="../report/report.jsp">生成报表</a> 》
				<a href="preview.jsp">预览报表</a> 
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>录入验收数据列表</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号</th>
						<th>林班</th>
						<th>小班</th>
						<th>地区和类别</th>
						<th>树种</th>
						<th>上报采伐蓄积</th>
						<th>验收采伐蓄积</th>
						<th>绝对误差</th>
						<th>相对误差</th>
						<th>其它信息</th>
					</tr>
					<%
						ArrayList<LB_XB> excelList=ExcelServlet.getExcelSession(request);
						if(excelList.isEmpty())
						{
							%>
							<tr class='alt'>
								<td colspan="10">
									列表空！
									<a href="createReport.jsp">返回</a>
								</td>
							</tr>
							<%
						}
						else
						{
							ReportInfo reportInfo;
							for(int i=0;i<excelList.size();i++)
							{
								reportInfo=Information.getReportInfo(excelList.get(i).getLb(),excelList.get(i).getXb());
					%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><%=reportInfo.getId()%></td>
							<td><%=reportInfo.getLb()%></td>
							<td><%=reportInfo.getXb()%></td>
							<td><%=reportInfo.getRegion()%> </td>
							<td><%=reportInfo.getTree()%> </td>
							<td><%=reportInfo.getReportVolume()%></td>
							<td><%=reportInfo.getVolume()%> </td>
							<td><%=reportInfo.getAbsoluteError()%></td>
							<td><%=reportInfo.getRelativeError()%></td>
							<td style="position:relative">
								<input type="button" value="其它信息"  class="menu1"/>
								<ul style="display:none">
									<li>调查单位:<%=reportInfo.getDcdw()%> </li>
									<li>调查员:<%=reportInfo.getDcy()%> </li>
									<li>林种区:<%=reportInfo.getLzq()%> </li>
									<li>土地所有权:<%=reportInfo.getZ_dq()%> </li>
									<li>林木使用权:<%=reportInfo.getZ_lq()%></li>
									<li>分类类型:<%=reportInfo.getZ_fllx()%> </li>
									<li>林种:<%=reportInfo.getZ_lz()%> </li>
									<li>地类:<%=reportInfo.getZ_dl()%></li>
									<li>起源:<%=reportInfo.getZ_qy()%></li>
								</ul>
							</td>						
						</tr>	
							<%
								}
							%>
							<tr>
								<td colspan="110">
									<a href="createReport.jsp">退出预览</a>
									<a href="../ExcelServlet?task=create">生成并下载报表</a>
									<a href="report.jsp">返回</a>								
								</td>
							</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>

<script>
	var aInfo = document.getElementsByClassName("menu1");
	var oBox = null;
	var oTd = null;
	for(var i = 0; i < aInfo.length ; i++)
	{
		aInfo[i].onmouseover = function()
		{
			var oUl = this.nextElementSibling;
			oTd = this.parentNode;
			oBox = document.createElement("div");
			oBox.innerHTML = "<ul>"+oUl.innerHTML+"</ul>";
			oBox.style.position = "absolute";
			oBox.style.backgroundColor="#ccc";
			oBox.style.width = "150px";
			oBox.style.height="200px";
			oBox.style.zIndex = 10;
			oTd.appendChild(oBox);
			
			 aInfo[i].onmouseout = function(){
				oTd.removeChild(oBox); 
			};  
			oBox.onmouseover=function(){
				oBox.style.display = 'block';
			};
			oBox.onmouseout=function(){
				oBox.style.display = 'none';
			}; 
		};
		 
	}	
</script>

