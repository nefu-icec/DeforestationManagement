<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="edu.nefu.DeforeMgr.bean.*" %>
<%@ page import="java.util.ArrayList"%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="inputList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
			���� ����λ�ã�
			<a href="../public/login.jsp">��ҳ</a> ��
			<a href="input.jsp">��������</a>��
			<a href="reportChecked.jsp">¼����������</a>
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
						<th>����������</th>
						<th>һ��������</th>
						<th>����������</th>
						<th>�ܶ�</th>
						<th>���</th>
						<th>�������</th>
						<th>�޸�����</th>
						<th>ɾ������</th>
					</tr>
					<%
						int sessionId=0;
						if(session.getAttribute("checkListId")!=null)
							sessionId=Integer.parseInt((String)session.getAttribute("checkListId"));
						int listSize=CheckList.getListSize(sessionId);
						ArrayList<InputCheck> inputCheckList=null;
						if(session.getAttribute("inputCheckList")==null)
							{out.print("<tr class='alt'>");
							out.print("<td>");
							out.print("��δ�������ݣ�");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<td>");
							out.print("<td/>");
							out.print("<tr/>");}
						else 
							inputCheckList=(ArrayList<InputCheck>)session.getAttribute("inputCheckList");
						if(inputCheckList!=null)
						{
							for(int i=0;i<inputCheckList.size();i++)
							{
					%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><%=inputCheckList.get(i).getId()%> </td>
							<td><%=inputCheckList.get(i).getLb()%> </td>
							<td><%=inputCheckList.get(i).getXb()%> </td>
							<td><%=inputCheckList.get(i).getRegion()%> </td>
							<td><%=inputCheckList.get(i).getTree()%> </td>
							<td><%=inputCheckList.get(i).getDgBest()%> </td>
							<td><%=inputCheckList.get(i).getDgMiddle()%> </td>
							<td><%=inputCheckList.get(i).getDgWorst()%> </td>
							<td><%=inputCheckList.get(i).getDensity()%> </td>
							<td><%=inputCheckList.get(i).getArea()%> </td>
							<td><%=inputCheckList.get(i).getVolume()%> </td>
							<td><a href="#">�޸�</a></td>
							<td><a href="../InputCheckServlet?task=delete&lb=<%=inputCheckList.get(i).getLb()%>&xb=<%=inputCheckList.get(i).getXb()%> ">ɾ��</a></td>
							<%
								}
							%>
						</tr>
						<tr>
							<td><a href="../InputCheckServlet?task=clear">���</a></td>
							<td><a href="inputCheckedData.jsp?id=<%=sessionId%>">����</a></td>
							<td></td>
							<td>
							<%
								if(inputCheckList.size()==listSize)
								{
									%>
									<a href="../InputCheckServlet?task=submit">�ύ</a>
									<%
								}
								else 
									out.print("���ݲ�ȫ��");
								%>	
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
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