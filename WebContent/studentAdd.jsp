<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/public.css" />
<title>添加学生信息</title>
</head>
<body>
		<div>
			<h4>学生信息添加</h4>
			<form action="studentAdd" method="post">
					<table>
						<tr><td><b>学生编号：</b><input type="text" name="stu_no"/></td></tr>
						<tr><td><b>学生姓名：</b><input type="text" name="stu_name"/></td></tr>
						<tr><td><b>学生密码：</b><input type="text" name="stu_pwd"/></td></tr>
						<tr><td><b>学生性别：</b><input type="text" name="stu_sex"/></td></tr>
						<tr><td><b>学生班级：</b><input type="text" name="stu_class"/></td></tr>
						<tr><td><b>学生专业：</b><input type="text" name="stu_marjor"/></td></tr>
						<tr><td><b>学生电话：</b><input type="text" name="stu_tel"/></td></tr>
					</table>
					<p id="btn">
						<input type="submit" value="增加">
						<input type="button" value="返回" onclick = "window.location.href = 'mange_student'">
					</p>
			</form>
		</div>
</body>
</html>