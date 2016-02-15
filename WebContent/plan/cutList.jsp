<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.*"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@page import="edu.nefu.DeforeMgr.util.Page"%>

<%
Page p=new Page("cutList.jsp",1, 10,Page.CHECKDATA);
if(request.getParameter("pageNumber")!=null)
	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
if(request.getParameter("number")!=null)
	p.setNumber(Integer.parseInt(request.getParameter("number")));
ArrayList<LB_XB> cutList=new ArrayList<LB_XB>();
ArrayList<CheckData> checkDatas=new ArrayList<CheckData>();
//��session���ȡcutList
if(session.getAttribute("cutList")!=null)
	cutList=(ArrayList<LB_XB>)session.getAttribute("cutList");
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="planList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				���� ����λ�ã�
				<a href="../public/login.jsp">��ҳ</a> ��
				<a href="plan.jsp">�´�ƻ�</a>��
				<a href="cutList.jsp">�������ӵĲɷ��ƻ�</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�������ӵĲɷ��ƻ�</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>
						<th>ɾ��</th>
					</tr>
					<%
						if(!cutList.isEmpty())
						{
							//��ѯ��������checkDatas
							for(int i=0;i<cutList.size();i++)
								checkDatas.add(CheckData.getCheckDataByLBAndXB(cutList.get(i).getLb(), cutList.get(i).getXb()));
							p.setCheckDatas(checkDatas);
							p.calculate();
							session.setAttribute("page", p);
							//��ʼ���
							for(int i=p.getStart();i<p.getEnd();i++)
							{
								%>
								<tr <%if(i%2==0) out.print("class='alt'");%>>
									<td><%=checkDatas.get(i).getId()%></td>
									<td><%=checkDatas.get(i).getLb()%></td>
									<td><%=checkDatas.get(i).getXb()%></td>
									<td><a href="../CutListServlet?task=delete&&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">ɾ��</a></td>
								</tr>					
								<%
							}
						}
						else
						{
							%>
							<tr class="alt">
								<td colspan="4">�����ڲɷ��ƻ��б���</td>
							</tr>	
							<%
						}
					%>
					<tr>
						<td><a href="issueCutPlan.jsp">��������</a></td>
						<td><a href="../CutListServlet?task=submit">�ύ</a></td>
						<td><a href="../CutListServlet?task=clear">���</a></td>
						<td><a href="plan.jsp">����</a></td>
					</tr>
				</table>
			</div>
			<%
			if(!checkDatas.isEmpty())
			{
				%>
				<jsp:include page='../public/pageInfo.jsp'></jsp:include>
				<% 
			}
			%>	
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>



