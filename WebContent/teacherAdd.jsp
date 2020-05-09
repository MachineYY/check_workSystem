<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/public.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师添加页面</title>
</head>
<body>
		<div>
			<h4>教师信息添加</h4>
			<form action="teacherAdd" method="post">
					<table>
						<tr><td><b>教师编号：</b><input type="text" name="tea_no"/></td></tr>
						<tr><td><b>教师姓名：</b><input type="text" name="tea_name"/></td></tr>
						<tr><td><b>教师密码：</b><input type="text" name="tea_pwd"/></td></tr>
						<tr><td><b>教师性别：</b><input type="text" name="tea_sex"/></td></tr>
						<tr><td><b>教师电话：</b><input type="text" name="tea_tel"/></td></tr>
					</table>
					<p id="btn">
						<input type="submit" value="添加">
						<input type="button" value="返回" onclick = "window.location.href = 'mange_teacher'">
					</p>
			</form>
		</div>
</body>
</html>