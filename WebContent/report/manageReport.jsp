<%@page import="edu.nefu.DeforeMgr.util.MyDate"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nefu.DeforeMgr.bean.ReportExcel"%>

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
				<a href="../report/report.jsp">���ɱ���</a>��
				<a href="manageReport.jsp">������</a>
			</p>
		</div>
		<div class="content">
			<div onload="YYYYMMDDstart()">
				<div class="name">
					<p>������</p>
				</div>
				<div class="search">
					<form action="manageReport.jsp">
						�������������ڼ�����
						<jsp:include page="../public/dateSelect.jsp"/>	
						<input type="hidden" name="flag" value="true">
						<input type="submit" value="����">
					</form>
					<%
						String startYear="";
						String startMonth="";
						String startDay="";
						String endYear="";
						String endMonth="";
						String endDay="";
						if(request.getParameter("startYear")!=null)
							startYear=request.getParameter("startYear");
						if(request.getParameter("startMonth")!=null)
							startMonth=request.getParameter("startMonth");
						if(request.getParameter("startDay")!=null)
							startDay=request.getParameter("startDay");
						if(request.getParameter("endYear")!=null)
							endYear=request.getParameter("endYear");
						if(request.getParameter("startMonth")!=null)
							endMonth=request.getParameter("endMonth");
						if(request.getParameter("startDay")!=null)
							endDay=request.getParameter("endDay");
						String flag="false";
						if(request.getParameter("flag")!=null)
							flag=request.getParameter("flag");
						// out.print(startYear+"-"+startMonth+"-"+startDay+"<br>");
						// out.print(endYear+"-"+endMonth+"-"+endDay+"<br>");
						// out.print(flag+"<br>");
					%>
				</div>
				<div class="tablecontent">
					<table id="table">
						<tr>
							<th>����id��</th>
							<th>��������</th>
							<th>����ʱ��</th>
							<th>����</th>
							<th>ɾ��</th>
						</tr>
						<%
							ArrayList<ReportExcel> reportExcels=new ArrayList<ReportExcel>();
							if(flag.equals("false"))
								reportExcels=ReportExcel.getReportExcel(null);
							else if(flag.equals("true"))
							{
								MyDate startDate=new MyDate(Integer.parseInt(startYear),Integer.parseInt(startMonth),Integer.parseInt(startDay));
								MyDate endDate=new MyDate(Integer.parseInt(endYear),Integer.parseInt(endMonth),Integer.parseInt(endDay));
								reportExcels=ReportExcel.getReportExcelByTime(startDate, endDate);
							}
							for(int i=0;i<reportExcels.size();i++)
							{
						%>
						<tr <%if(i%2==0) out.print("class='alt'");%>>
							<td><%=reportExcels.get(i).getId()%></td>
							<td><%=reportExcels.get(i).getrName()%> </td>
							<td><%=reportExcels.get(i).getDate().getStringDate()%></td>
							<td><a href="../ManageExcelServlet?task=download&id=<%=reportExcels.get(i).getId()%>">����</a></td>
							<td><a href="../ManageExcelServlet?task=delete&id=<%=reportExcels.get(i).getId()%>">ɾ��</a></td>
						</tr>
						<%
							}
						%>
						<tr>
							<td><a href="report.jsp">����</a></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>