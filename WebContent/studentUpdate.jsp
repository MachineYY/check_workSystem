<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息修改</title>
</head>
<body>
	<div>
			<h4>学生信息修改</h4>
			<form action="studentUpdate" method="post">
					<tr>
					<td><b>学生编号：</b><input type="text" name="stu_no" value="<%=request.getParameter("stu_no")%>"/></td></br>
					<td><b>学生姓名：</b><input type="text" name="stu_name"/></td></br>
					<td><b>学生密码：</b><input type="text" name="stu_pwd"/></td></br>
					<td><b>学生性别：</b><input type="text" name="stu_sex"/></td></br>
					<td><b>学生班级：</b><input type="text" name="stu_class"/></td></br>
					<td><b>学生专业：</b><input type="text" name="stu_marjor"/></td></br>
					<td><b>学生电话：</b><input type="text" name="stu_tel"/></td></br>
					</tr>
					<input type="submit" value="修改">
					<input type="button" value="返回" onclick = "window.location.href = 'mange_student'">
			</form>
		</div>

</body>
</html>