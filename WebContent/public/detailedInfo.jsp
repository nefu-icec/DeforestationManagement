<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>

<%
	request.setCharacterEncoding("gb2312");
	int lb=Integer.parseInt(request.getParameter("lb"));
	int xb=Integer.parseInt(request.getParameter("xb"));
	CheckData checkData=CheckData.getCheckDataByLBAndXB(lb, xb);
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
	 <div id="site_content">
	   <div id="sidebar_containernew">
	     <div class="sidebar">
	       <h3>详细信息</h3>
	     </div>
	   </div>
	 </div>		
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				》》 您的位置：
				详细信息
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>详细信息</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id号：<%=checkData.getId()%></th>
						<th>林班：<%=checkData.getLb()%></th>
						<th>小班：<%=checkData.getXb()%></th>
					</tr>
					<tr class='alt'>
						<td>调查单位：<%=checkData.getDcdw()%></td>
						<td>调查员：<%=checkData.getDcy()%></td>
						<td></td>
					</tr>
					<tr>
						<td>林种区：<%=checkData.getLzq()%></td>
						<td>土地所有权：<%=checkData.getZ_dq()%></td>
						<td>林木使用权：<%=checkData.getZ_lq()%></td>
					</tr>
					<tr class='alt'>
						<td>分类类型：<%=checkData.getZ_fllx()%></td>
						<td>林种：<%=checkData.getZ_lz()%></td>
						<td>地类：<%=checkData.getZ_dl()%></td>
					</tr>
					<tr>
						<td>起源：<%=checkData.getZ_qy()%></td>
						<td>地区和类别：<%if(checkData.getRegion()==null) out.print("未录入"); else out.print(checkData.getRegion());%></td>
						<td>树种：<%if(checkData.getTree()==null) out.print("未录入"); else out.print(checkData.getTree());%></td>
					</tr>
					<tr class='alt'>
						<td>优势树根径：<%=checkData.getDgBest()%></td>
						<td>一般树根径：<%=checkData.getDgMiddle()%></td>
						<td>劣势树根径：<%=checkData.getDgWorst()%></td>
					</tr>
					<tr>
						<td>密度：<%=checkData.getDensity()%></td>
						<td>面积：<%=checkData.getArea()%></td>
						<td></td>
					</tr>
					<tr class='alt'>
						<td>上报采伐蓄积：<%=checkData.getReportVolume()%></td>
						<td>验收采伐蓄积：<%=checkData.getVolume()%></td>
						<td></td>
					</tr>
					<tr>
						<td>是否采伐：<%if(checkData.getCut()==1) out.print("是"); else out.print("否");%></td>
						<td>是否验收：<%if(checkData.getChecked()==1) out.print("是"); else out.print("否");%></td>
						<td></td>
					</tr>
				</table>
			</div>
<%-- 			<jsp:include page='../public/pageInfo.jsp'></jsp:include> --%>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
		