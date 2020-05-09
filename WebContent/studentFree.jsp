<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/public.css" />
<title>学生请假申请</title>
</head>
<body>
		<div>
			<h4>学生请假申请</h4>
			<form action="studentFree" method="post">
					<table>
						<tr><td><b>学生编号：</b><input type="text" name="stu_no"/></td></tr>
						<tr><td><b>学生姓名：</b><input type="text" name="stu_name"/></td></tr>
						<tr><td><b>学生班级：</b><input type="text" name="stu_class"/></td></tr>
						<tr><td><b>请假原因：</b><input type="text" name="free_reason"/></td></tr>
						<tr><td><b>开始时间：</b><input type="text" name="starttime" placeholder="格式2020-2-27 8-30-0"/></td></tr>
						<tr><td><b>结束时间：</b><input type="text" name="endtime" placeholder="格式2020-2-27 17-30-0"/></td></tr>
						<tr><td><b>学生电话：</b><input type="text" name="stu_tel"/></td></tr>
					</table>
					<p id="btn">
						<input type="submit" value="提交">
						<input type="button" value="返回" onclick = "history.back()">
					</p>
			</form>
		</div>
</body>
</html>