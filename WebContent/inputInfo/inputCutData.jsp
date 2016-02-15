<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="edu.nefu.DeforeMgr.bean.*"%>
<%@ page import="edu.nefu.DeforeMgr.util.*"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
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
			<a href="plan.jsp">�´�ƻ�</a>��
			����ɷ���
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>����ɷ���</p>
			</div>
			<div class="tablecontent">
			<form action="../InputCutServlet?task=submit" method="post" name="data" id="data">
				<table id="table">
					<tr>
						<th>id��</th>
						<th>�ְ�</th>
						<th>С��</th>
						<th>��ϸ��Ϣ</th>
						<th>�ɷ����</th>
					</tr>
						<%
							String sId=request.getParameter("id");
							int id=Integer.parseInt(sId);
							CutList cutList=CutList.getCutListById(id);
							ArrayList<LB_XB> lbxbs=CutList.getListLBXB(cutList.getList());
							CheckData checkData;
							for(int i=0;i<lbxbs.size();i++)
							{
								checkData=CheckData.getCheckDataByLBAndXB(lbxbs.get(i).getLb(),lbxbs.get(i).getXb());
						%>
					    <tr <%if(i%2==0) out.print("class='alt'");%>>
						<td><%=checkData.getId()%></td>
						<td><%=checkData.getLb()%></td>
						<td><%=checkData.getXb()%> </td>
						<td> <a href="../public/detailedInfo.jsp?lb=<%=checkData.getLb()%>&xb=<%=checkData.getXb()%>"  target="_blank">�鿴</a></td>
						<td><input type="text" name="reportVolume<%=i%>" id="vol<%=i%>"></td>
					</tr>
						<%
					}
					%>
					<tr>
						<td><input type="hidden" name="count" value="<%=lbxbs.size()%>"></td>
						<td><input type="hidden" name="id" value="<%=id%>"></td>
						<td><input type="button" value="�ύ"  onclick="submitVerify()"></td>
						<td><input type="reset"></td>
						<td></td>
					</tr>
				</table>
				</form>
			</div>
	</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>

<script>
function submitVerify()
{
	var count=<%=lbxbs.size()%>;
	var notEmpty=1;
	var isNumber=1;
	for(var i=0;i<count;i++)
	{
		if(document.getElementById("vol"+i).value=="")
			notEmpty=0;
		if(!/^\d+(\.\d+)?$/.test(document.getElementById("vol"+i).value))
			isNumber=0;
	}
	if(isNumber==1&&notEmpty==1)
		document.getElementById("data").submit();
	else if(notEmpty==0)
		alert("�뽫�ɷ������д����");
	else
		alert("����д�����ַ�");
}
</script>