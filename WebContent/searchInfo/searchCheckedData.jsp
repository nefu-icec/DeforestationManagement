<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.nefu.DeforeMgr.util.*"%>
<jsp:useBean id="connectDatabase" class="edu.nefu.DeforeMgr.bean.ConnectDatabase" />

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
				<a href="../searchInfo/searchCut.jsp">��ѯ���ϱ�������������</a>
			</p>
		</div>
			<div class="content">
			<div class="name">
				<p>��ѯ���ϱ�������������</p>
			</div>
			<div class="tablecontent">
				<form action="searchCutData.jsp" method="post" id="input">
					<table id="table">
						<tr>
							<th>���ְ���������ѡ���ְࣩ</th>
							<th>��С����������ѡ��С�ࣩ</th>
							<th>��id��������������id�ţ�</th>
							<th>�����鵥λ����</th>
						</tr>
						<tr>
							<td>
								<select name="lb"  id="lb" onchange="getXb()">
									<option value="null" selected>��ѡ���ְ�</option>
									<%
										ArrayList<String> LBs=LB_XB.getLB();
										for(int i=0;i<LBs.size();i++)
										{
											%>
											<option value="<%=LBs.get(i) %>"><%=LBs.get(i) %></option>
											<% 
										}
									%>
								</select>
							</td>
							<td>
								<select name="xb"  id="xb" >
									<option value="null" selected>����ѡ���ְ�</option>
								</select>
							</td>
							<td><input type="text" name="id"/></td>
							<td><input type="text" name="dcdw"/></td>
						</tr>
						<tr>
							<td><input type="submit" /></td>
							<td><input type="reset"/></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				<%
					String [] lbs=new String[LBs.size()];
					for(int i=0;i<LBs.size();i++)
						lbs[i]=LBs.get(i);
					ArrayList<String> XBs=new ArrayList<String>();
					String xb="";
					for(int i=0;i<lbs.length;i++)
					{
						XBs=LB_XB.getXB(lbs[i]);
						for(int j=0;j<XBs.size();j++)
							xb+=XBs.get(j)+",";
						xb=xb.substring(0, xb.length()-1);
						xb+="-";
						XBs.clear();
					}	
					xb=xb.substring(0, xb.length()-1);
					connectDatabase.close();
					//out.print(xb);
				%>
				</form>
			</div>
		</div>
	</div>
</div>
<div><jsp:include page="../public/foot.jsp"></jsp:include></div>


<script type="text/javascript">
	function getXb()
	{
		var lb=input.lb.value;
		var xb=document.getElementById("xb");
		var no=parseInt(lb)-1;
		var xbData="<%=xb%>";
		var xbs=new Array();
		xbs=xbData.split("-");
		var SlectedLb_xbs=new Array();
		SlectedLb_xbs=xbs[no].split(",");
		xb[0]=new Option("��ѡ��С��","null");
		for(var i=1;i<SlectedLb_xbs.length+1;i++)
			xb[i]=new Option(SlectedLb_xbs[i-1],SlectedLb_xbs[i-1]);
	}
</script>