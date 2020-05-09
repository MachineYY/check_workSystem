<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/public.css" />
<title>学生提交密钥</title>
</head>
<body>
	<div>
			<form action="studentKey" method="post">
				<h4>学生提交签到密钥</h4>
				<table>
					<tr><td>签到密钥：<input type="text" name="check_key" placeholder="每个密钥只可签到一次"/></td></tr>
				</table>
				<p id="btn">
					<input type="submit" value="提交" />
					<input type="button" value="返回" onclick = "history.back()">
				</p>
			</form>
		</div>
</body>
</html>