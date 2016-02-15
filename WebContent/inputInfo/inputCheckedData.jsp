<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckList"%>
<%@page import="java.util.ArrayList"%>

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
			<a href="reportChecked.jsp">¼����������</a>��
			<a href="inputCheckedData.jsp">�ϱ���������</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�ϱ���������</p>
			</div>
			<div class="tablecontent">
			<%
			String sId=request.getParameter("id");
			int id=Integer.parseInt(sId);
			int sessionId=0;
			if(session.getAttribute("checkListId")==null)
				session.setAttribute("checkListId", sId);
			else
				sessionId=Integer.parseInt((String)session.getAttribute("checkListId"));
			if(id==sessionId||sessionId==0)
			{
				CheckList checkList=CheckList.getCheckListById(id);
				ArrayList<LB_XB> lbxbs=CutList.getListLBXB(checkList.getList());
			%>
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>
						<th>��ϸ��Ϣ</th>
						<th>����</th>
					</tr>
						<% 
							CheckData checkData;
							for(int i=0;i<lbxbs.size();i++)
							{
								checkData=CheckData.getCheckDataByLBAndXB(lbxbs.get(i).getLb(),lbxbs.get(i).getXb());
						%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><%=checkData.getId()%></td>
							<td><%=checkData.getLb()%> </td>
							<td><%=checkData.getXb()%> </td>
							<td> <a href="../public/detailedInfo.jsp?lb=<%=checkData.getLb()%>&xb=<%=checkData.getXb()%>">�鿴</a></td>
							<td><a href="inputCheckDataByXB.jsp?lb=<%=checkData.getLb()%>&xb=<%=checkData.getXb()%>">����</a> </td>
						</tr>
						<%
							}
						%>
						<tr>
							<td colspan="5">
							<a href="inputCheckList.jsp"> �鿴����������</a>
								<%
									}
									else
									out.print("�����ύ���ռƻ��б���id��Ϊ��"+sessionId+"��������Ϣ��");
								%>
							</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>