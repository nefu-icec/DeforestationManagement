<%@page import="java.util.ArrayList"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckList"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>
<%@page import="edu.nefu.DeforeMgr.bean.CutList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>


<%
	int pageNumber=1;
	int number=10;
	if(request.getParameter("pageNumber")!=null)
		pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
	if(request.getParameter("number")!=null)
		number=Integer.parseInt(request.getParameter("number"));
	String sId=request.getParameter("id");
	int id=Integer.parseInt(sId);
	//ȷ������lcheckdata
	ArrayList<CheckData> checkDatas=CheckList.getCheckDataForChoose(id);
	//��ȡҳ������
	int pageCount=0;
	pageCount=checkDatas.size()/number+1;
	if(checkDatas.size()%number==0)
		pageCount--;
	//ȷ����һҳ����һҳ��ҳ��
	int previous=pageNumber-1;
	int next=pageNumber+1;
	if(previous<1)
		previous=pageCount;
	if(next>pageCount)
		next=1;
	//ȷ�����Ŀ�ͷ��β
	int start=number*(pageNumber-1);
	int end=number*(pageNumber-1)+number;
	if(end+1>checkDatas.size())//����β�����������ǽ�β��������
		end=checkDatas.size();
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
				<a href="chooseCheckXB.jsp">����ѡ�����շ�Χ</a>
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>�ֶ�������շ�Χ</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>
						<th>�鿴��ϸ��Ϣ</th>
						<th>��ӵ������б�</th>
					</tr>
						<%
							for(int i=start;i<end;i++)
							{
						%>
				    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkDatas.get(i).getId()%></td>
						<td><%=checkDatas.get(i).getLb()%></td>
						<td><%=checkDatas.get(i).getXb()%></td>
						<td><a href="../public/detailedInfo.jsp?lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>"  target="_blank">��ϸ��Ϣ</a> </td>
						<td><a href="../CheckListServlet?task=add&lb=<%=checkDatas.get(i).getLb()%>&xb=<%=checkDatas.get(i).getXb()%>">���</a></td>
					</tr>
						<%
							}
						%>
				</table>
				<div>
					��<%=checkDatas.size()%>����¼����ǰ��<%=pageNumber%>/<%=pageCount%>ҳ��ÿҳ<%=number%>����¼	
					<a href="chooseCheckXB.jsp?id=<%=id%>&number=<%=number%>&pageNumber=<%=previous%>">��һҳ</a>
					<a href="chooseCheckXB.jsp?id=<%=id%>&number=<%=number%>&pageNumber=<%=next%>">��һҳ</a>
					��ת����
					<select name="goto" id="goto" onchange="goTo()">
						<%
							for(int i=1;i<=pageCount;i++)
							{
						%>
							<option value="<%=i%>" <%if(i==pageNumber) out.print("selected");%>><%=i%></option>
						<%
							}
						%>
					</select>
					ҳ��
					ÿҳ��ʾ
					<select name="number" id="number" onchange="number()">
						<%
							for(int i=5;i<=15;i++)
							{
						%>
							<option value="<%=i%>" <%if(i==number) out.print("selected");%>><%=i%></option>
						<%
							}
						%>
					</select>��
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>


<script>
	function goTo()
	{
		var goPageNumber=document.getElementById("goto");
		window.location.href="chooseCheckXB.jsp?id=<%=id%>&number=<%=number%>&pageNumber="+goPageNumber.value;
	}
	function number()
	{
		var number=document.getElementById("number");
		window.location.href="chooseCheckXB.jsp?id=<%=id%>&number="+number.value;
	}
</script>