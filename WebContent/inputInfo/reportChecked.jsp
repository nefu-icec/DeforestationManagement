<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckList"%>
<%@ page import="java.util.*"%>

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
				<p>�����б�</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�-С��</th>
						<th>�Ƿ�����</th>
						<th>�Ƿ��ϱ�����</th>
					</tr>
						<%
			ArrayList<CheckList> 

checkLists=CheckList.getCheckList(CheckList.GET_UNCHECK);
			for(int i=0;i<checkLists.size();i++)
			{
				%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkLists.get(i).getId()%></td>
						<td><%=checkLists.get(i).getList()%></td>
						<td><%
						    if(checkLists.get(i).getChecked()==1)
						    	out.print("��");
						    else
						    	out.print("��");
						    %>
					    </td>
						<td> <a href="inputCheckedData.jsp?id=<%=checkLists.get(i).getId()%>">�ϱ���������</a></td>
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