<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.servlet.ExcelServlet"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@ page import="java.util.*" %>
<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="reportList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				���� ����λ�ã�
				<a href="../report/report.jsp">���ɱ���</a> ��
				<a href="createReport.jsp">���ɱ���</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>���ɱ���</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>
						<th>�ϱ����</th>
						<th>�������</th>
						<th>��ϸ��Ϣ</th>
						<th>���������б�</th>
					</tr>
					<%
						String where=" where cut=1 and checked=1 and volume>0";
						ArrayList<CheckData> checkDatas=CheckData.getCheckDatas(where);
						for(int i=0;i<checkDatas.size();i++)
						{
					%>
				    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkDatas.get(i).getId()%></td>
						<td><%=checkDatas.get(i).getLb()%></td>
						<td><%=checkDatas.get(i).getXb()%></td>
						<td><%=checkDatas.get(i).getReportVolume()%> </td>
						<td><%=checkDatas.get(i).getVolume()%></td>
						<td>
							<a href="../public/detailedInfo.jsp?lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">�鿴</a>
						</td>
						<td>
							<%
							if(ExcelServlet.isThisXBExist(checkDatas.get(i).getLb(), checkDatas.get(i).getXb(), request))
								{
									%>
										<a href="../ExcelServlet?task=delete&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">ɾ��</a>
									<%
								}
							else 
								{
									%>
										<a href="../ExcelServlet?task=add&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">���</a>
									<%
								}
							%>
					    </td>
					</tr>
						<%
							}
						%>
					<tr>
						<td><a href="../ExcelServlet?task=selectAll">ȫѡ</a></td>
						<td><a href="../ExcelServlet?task=clear">���</a></td>
						<td><a href="preview.jsp">Ԥ������</a></td>
						<td><a href="report.jsp">����</a></td>
						<td></td>
						<td></td>	
						<td></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>