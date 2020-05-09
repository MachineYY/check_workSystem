<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码更改</title>
</head>
<body>
	<div>
		<form action="Changepwd" method="post" target="sypost">
			<table>
				<tr><td>原密码：<input type="password" name="pwd"></td></tr>
				<tr><td>新密码：<input type="password" name="newpwd"></td></tr>
				<tr><td>重复新密码：<input type="password" name="newpwd1"></td></tr>
			</table>
			<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>