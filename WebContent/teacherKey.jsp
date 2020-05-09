<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/public.css" />
<title>教师发布密钥</title>
</head>
<body>
		<div>
			<form action="teacherKey" method="post">
				<h4>发布签到密钥</h4>
				<table>
					<tr><td>课程编号：<input type="text" name="course_no"/></td></tr>
					<tr><td>课程名称：<input type="text" name="course_name"/></td></tr>
					<!-- <tr><td>课程时间：<input type="text" name="time"/></td></tr> -->
					<tr><td>签到密钥：<input type="text" name="key" placeholder="每个密钥只可发布一次"/></td></tr>
					<tr><td>课程班级：<input type="text" name="stu_class"/></td></tr>
				</table>
				<p id="btn">
					<input type="submit" value="提交" />
					<input type="button" value="返回" onclick = "history.back()">
				</p>
			</form>
		</div>
</body>
</html>