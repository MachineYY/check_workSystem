<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/studentleft.css" />
<title>学生左部页面</title>
</head>
<body>
		<div id="left">
			<div id="left_top">
				<p>功能菜单</p>
			</div>
			<div id="left_middle">
				<ul>
			        <!-- 学生-->
			        <li id="left_menu"><a href="student_coursetable" target="rightframe">学期课表</a></li>
			        <li id="left_menu"><a href="studentKey.jsp" target="rightframe">学生签到</a></li>
			        <li id="left_menu"><a href="studentNocheck" target="rightframe">未签到课程</a></li>
			        <li id="left_menu"><a href="studentFreelistadd" target="rightframe">请假申请</a></li>
			   </ul>
			   
			</div>
			<div id="left_bottom">
				<p id="bottom">版权所有</p>
				</div>
		</div>
</body>
</html>