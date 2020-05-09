<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/public.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息添加</title>
</head>
<body>
		<div>
			<h4>课程信息添加</h4>
			<form action="courseAdd" method="post">
					<table>
						<tr><td><b>课程编号：</b><input type="text" name="course_no"/></td></tr>
						<tr><td><b>课程名称：</b><input type="text" name="course_name"/></td></tr>
						<tr><td><b>教师编号：</b><input type="text" name="course_name"/></td></tr>
						<tr><td><b>课程班级：</b><input type="text" name="stu_class"/></td></tr>
						<tr><td><b>&nbsp;课程&nbsp;周&nbsp;：</b><input type="text" name="course_week" placeholder="请输入 x-y"/></td></tr>
						<tr><td><b>&nbsp;课程&nbsp;日&nbsp;：</b><input type="text" name="course_day"/></td></tr>
						<tr><td><b>课程节次：</b><input type="text" name="course_section"/></td></tr>
						<tr><td><b>课程地点：</b><input type="text" name="course_add"/></td></tr>
					</table>
					<p id="btn">
						<input type="submit" value="增加">
						<input type="button" value="返回" onclick = "window.location.href = 'mange_course'">
					</p>
			</form>
		</div>

</body>
</html>