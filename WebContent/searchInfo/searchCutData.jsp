<%@page import="edu.nefu.DeforeMgr.util.Page"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />

<%
String lb="";
String xb="";
String dcdw="";
String id="";	
//������session������session����request��ֵ
if(session.getAttribute("lb")==null&&session.getAttribute("xb")==null
	&&session.getAttribute("dcdw")==null&&session.getAttribute("id")==null)
{
	if(request.getParameter("lb")!=null)
		lb=request.getParameter("lb");
	if(request.getParameter("xb")!=null)
		xb=request.getParameter("xb");
	if(request.getParameter("dcdw")!=null)
		dcdw=request.getParameter("dcdw");
	if(request.getParameter("id")!=null)
		id=request.getParameter("id");
	session.setAttribute("lb",lb);
	session.setAttribute("xb",xb);
	session.setAttribute("dcdw",dcdw);
	session.setAttribute("id",id);
}
//����session
else
{
	//requestȫΪ��˵���ύ����ҳ�����
	if(request.getParameter("lb")==null&&request.getParameter("xb")==null
		&&request.getParameter("dcdw")==null&&request.getParameter("id")==null)
	{
		lb=session.getAttribute("lb").toString();
		xb=session.getAttribute("xb").toString();
		dcdw=session.getAttribute("dcdw").toString();
		id=session.getAttribute("id").toString();
	}
	//���Բ�ѯ
	else
	{
		if(request.getParameter("lb")!=null)
			lb=request.getParameter("lb");
		if(request.getParameter("xb")!=null)
			xb=request.getParameter("xb");
		if(request.getParameter("dcdw")!=null)
			dcdw=request.getParameter("dcdw");
		if(request.getParameter("id")!=null)
			id=request.getParameter("id");
		session.setAttribute("lb",lb);
		session.setAttribute("xb",xb);
		session.setAttribute("dcdw",dcdw);
		session.setAttribute("id",id);
	}
}
//���ɲ�ѯ�������
String where="";
if(!lb.equals("null")&&!xb.equals("null"))
	where+=" where lb="+lb+" and xb="+xb+" and checked=0 and cut=1";
else
{
	where+=" where";
	if(!id.equals(""))
		where+=" id like '%"+id+"%' and";
	if(!dcdw.equals(""))
		where+=" dcdw like '%"+dcdw+"%' and";
	if(!lb.equals("null"))
		where+=" lb="+lb+" and";
	if(!xb.equals("null"))
		where+=" xb="+xb+" and";		
	where=where.substring(0, where.length()-4);
	where+=" and checked=0 and cut=1";
}
if(lb.equals("null")&&xb.equals("null")&&id.equals("")&&dcdw.equals(""))
	where=" where checked=0 and cut=1";
//out.print(where+"<br>");
//������
ArrayList<CheckData> checkDatas=CheckData.getCheckDatas(where);
Page p=new Page("searchCutData.jsp",1,10,Page.CHECKDATA);
if(request.getParameter("pageNumber")!=null)
 	p.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
if(request.getParameter("number")!=null)
 	p.setNumber(Integer.parseInt(request.getParameter("number")));
p.setCheckDatas(checkDatas);
p.calculate();
session.setAttribute("page", p);
%>

<jsp:include page="../public/head.jsp"></jsp:include>			
<div id="infomain" class="clearfix">
	<div id="leftBlock">
		<jsp:include page="searchList.jsp"></jsp:include>
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				���� ����λ�ã�
				<a href="../public/login.jsp">��ҳ</a> ��
				<a href="../searchInfo/search.jsp">���ݼ���</a> ��
				<a href="../searchInfo/searchCut.jsp">��ѯ���ϱ�������������</a>��
				��ϸ��Ϣ
			</p>
		</div>
			<div class="content">
			<div class="name">
				<p>��ѯ���ϱ���������������ϸ��Ϣ</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>					
						<th>�������</th>
						<th>��ϸ��Ϣ</th>
					</tr>
					<%
					for(int i=p.getStart();i<p.getEnd();i++)
						{
					%>
				    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkDatas.get(i).getId()%></td>
						<td><%=checkDatas.get(i).getLb()%></td>
						<td><%=checkDatas.get(i).getXb()%></td>
						<td><%=checkDatas.get(i).getReportVolume()%> </td>
						<td><a  target="_Blank" href="../public/detailedInfo.jsp?lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">��ϸ��Ϣ</a></td>					
					</tr>
					<%
						}
					%>					
				</table>
				<div>
					<jsp:include page='../public/pageInfo.jsp'></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>