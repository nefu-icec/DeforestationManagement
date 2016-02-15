<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.bean.CheckData"%>

<%
	request.setCharacterEncoding("gb2312");
	int lb=Integer.parseInt(request.getParameter("lb"));
	int xb=Integer.parseInt(request.getParameter("xb"));
	CheckData checkData=CheckData.getCheckDataByLBAndXB(lb, xb);
%>

<jsp:include page="../public/head.jsp"></jsp:include>
<div id="infomain" class="clearfix">
	<div id="leftBlock">
	 <div id="site_content">
	   <div id="sidebar_containernew">
	     <div class="sidebar">
	       <h3>��ϸ��Ϣ</h3>
	     </div>
	   </div>
	 </div>		
	</div>
	<div id="rightBlock">
		<div class="navigation">
			<p>
				���� ����λ�ã�
				��ϸ��Ϣ
			</p>
		</div>
		<div class="content">
			<div class="name">
				<p>��ϸ��Ϣ</p>
			</div>
			<div class="tablecontent">
				<table id="table">
					<tr>
						<th>id�ţ�<%=checkData.getId()%></th>
						<th>�ְࣺ<%=checkData.getLb()%></th>
						<th>С�ࣺ<%=checkData.getXb()%></th>
					</tr>
					<tr class='alt'>
						<td>���鵥λ��<%=checkData.getDcdw()%></td>
						<td>����Ա��<%=checkData.getDcy()%></td>
						<td></td>
					</tr>
					<tr>
						<td>��������<%=checkData.getLzq()%></td>
						<td>��������Ȩ��<%=checkData.getZ_dq()%></td>
						<td>��ľʹ��Ȩ��<%=checkData.getZ_lq()%></td>
					</tr>
					<tr class='alt'>
						<td>�������ͣ�<%=checkData.getZ_fllx()%></td>
						<td>���֣�<%=checkData.getZ_lz()%></td>
						<td>���ࣺ<%=checkData.getZ_dl()%></td>
					</tr>
					<tr>
						<td>��Դ��<%=checkData.getZ_qy()%></td>
						<td>���������<%if(checkData.getRegion()==null) out.print("δ¼��"); else out.print(checkData.getRegion());%></td>
						<td>���֣�<%if(checkData.getTree()==null) out.print("δ¼��"); else out.print(checkData.getTree());%></td>
					</tr>
					<tr class='alt'>
						<td>������������<%=checkData.getDgBest()%></td>
						<td>һ����������<%=checkData.getDgMiddle()%></td>
						<td>������������<%=checkData.getDgWorst()%></td>
					</tr>
					<tr>
						<td>�ܶȣ�<%=checkData.getDensity()%></td>
						<td>�����<%=checkData.getArea()%></td>
						<td></td>
					</tr>
					<tr class='alt'>
						<td>�ϱ��ɷ������<%=checkData.getReportVolume()%></td>
						<td>���ղɷ������<%=checkData.getVolume()%></td>
						<td></td>
					</tr>
					<tr>
						<td>�Ƿ�ɷ���<%if(checkData.getCut()==1) out.print("��"); else out.print("��");%></td>
						<td>�Ƿ����գ�<%if(checkData.getChecked()==1) out.print("��"); else out.print("��");%></td>
						<td></td>
					</tr>
				</table>
			</div>
<%-- 			<jsp:include page='../public/pageInfo.jsp'></jsp:include> --%>
		</div>
	</div>
</div>
<jsp:include page="../public/foot.jsp"></jsp:include>
		