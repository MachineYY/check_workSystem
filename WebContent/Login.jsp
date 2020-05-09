<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Login.css" /> 
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.11"></script>
<script type="text/javascript" src="js/Login.js"></script>
<title>欢迎使用学生考勤管理系统！</title>

</head>
<body>
		<div class="root">
			<!--根容器-->
			<div class="top">
				<img src="img/logo.png" />
				<span id="topspan">学生考勤管理系统</span>
			</div>
			<div class="clear"></div>
			<div class="center">
				<div id="form">
					<span id="login_title">用户登录</span>
					<form action="login_check" method="post">
						<div id="inputbox">
							<input type="text" name=u_id id="u_id" placeholder="请输入您的工号/学号！" />
						</div>
						<div id="inputbox">
							<input type="password" name=u_pwd id="u_pwd" placeholder="请输入您的密码！" />
						</div>
						<input type="submit" id="inputbtn" value="登录" />
					</form>
				</div>
			</div>
		</div>
</body>
</html>