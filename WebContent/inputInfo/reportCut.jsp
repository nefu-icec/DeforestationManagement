<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.nefu.DeforeMgr.util.*"%>

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
			<a href="reportCut.jsp">�ϱ��ɷ���</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�ɷ��б�</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�-С��</th>
						<th>�Ƿ�ɷ�</th>
						<th>�Ƿ��ϱ�������</th>
					</tr>
						<%
							ArrayList<CutList> cutLists=CutList.getCutList(CutList.GET_UNCUT);
							for(int i=0;i<cutLists.size();i++)
							{
						%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=cutLists.get(i).getId()%></td>
						<td><%=cutLists.get(i).getList()%></td>
						<td>
						<%
						    if(cutLists.get(i).getCut()==1)
						    	out.print("��");
						    else
						    	out.print("��");
					    %>
					    </td>
						<td> <a href="inputCutData.jsp?id=<%=cutLists.get(i).getId()%>">�ϱ��ɷ���</a></td>
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