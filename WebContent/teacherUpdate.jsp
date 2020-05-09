<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师修改页面</title>
</head>
<body>
<%-- <%
String tea_no=request.getParameter("tea_no");
out.print(tea_no);
%> --%>
		<div>
			<h4>教师信息修改</h4>
			<form action="teacherUpdate" method="post">
					<tr>
					<td><b>教师编号：</b><input type="text" name="tea_no" value="<%=request.getParameter("tea_no")%>"/></td></br>
					<td><b>教师姓名：</b><input type="text" name="tea_name"/></td></br>
					<td><b>教师密码：</b><input type="text" name="tea_pwd"/></td></br>
					<td><b>教师性别：</b><input type="text" name="tea_sex"/></td></br>
					<td><b>教师电话：</b><input type="text" name="tea_tel"/></td></br>
					</tr>
					<input type="submit" value="修改">
					<input type="button" value="返回" onclick = "window.location.href = 'mange_teacher'">
			</form>
		</div>
</body>
</html>