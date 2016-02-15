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
				���� ����λ�ã�
				<a href="../public/login.jsp">��ҳ</a> ��
				<a href="../report/report.jsp">���ɱ���</a> ��
				<a href="preview.jsp">Ԥ������</a> 
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>¼�����������б�</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>
						<th>���������</th>
						<th>����</th>
						<th>�ϱ��ɷ����</th>
						<th>���ղɷ����</th>
						<th>�������</th>
						<th>������</th>
						<th>������Ϣ</th>
					</tr>
					<%
						ArrayList<LB_XB> excelList=ExcelServlet.getExcelSession(request);
						if(excelList.isEmpty())
						{
							%>
							<tr class='alt'>
								<td colspan="10">
									�б�գ�
									<a href="createReport.jsp">����</a>
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
								<input type="button" value="������Ϣ"  class="menu1"/>
								<ul style="display:none">
									<li>���鵥λ:<%=reportInfo.getDcdw()%> </li>
									<li>����Ա:<%=reportInfo.getDcy()%> </li>
									<li>������:<%=reportInfo.getLzq()%> </li>
									<li>��������Ȩ:<%=reportInfo.getZ_dq()%> </li>
									<li>��ľʹ��Ȩ:<%=reportInfo.getZ_lq()%></li>
									<li>��������:<%=reportInfo.getZ_fllx()%> </li>
									<li>����:<%=reportInfo.getZ_lz()%> </li>
									<li>����:<%=reportInfo.getZ_dl()%></li>
									<li>��Դ:<%=reportInfo.getZ_qy()%></li>
								</ul>
							</td>						
						</tr>	
							<%
								}
							%>
							<tr>
								<td colspan="110">
									<a href="createReport.jsp">�˳�Ԥ��</a>
									<a href="../ExcelServlet?task=create">���ɲ����ر���</a>
									<a href="report.jsp">����</a>								
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

