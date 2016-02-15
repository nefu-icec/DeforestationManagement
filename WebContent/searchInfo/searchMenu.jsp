<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="edu.nefu.DeforeMgr.util.LB_XB"%>

<tr class="alt">
	<td>
		<select name="lb"  id="lb" onchange="getXb()">
			<option value="null" selected>请选择林班</option>
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
			<option value="null" selected>请先选择林班</option>
		</select>
	</td>
	<td><input type="text" name="id"/></td>
	<td><input type="text" name="dcdw"/></td>
</tr>