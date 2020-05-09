<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/public.css" />
<title>班级添加</title>
</head>
<body>
		<div>
			<h4>班级信息添加</h4>
			<form action="classAdd" method="post">
					<table>
						<tr><td><b>班级编号：</b><input type="text" name="stu_class"/></td></tr>

					</table>
					<p id="btn">
						<input type="submit" value="添加">
						<input type="button" value="返回" onclick = "window.location.href = 'mange_class'">
					</p>
			</form>
		</div>

</body>
</html>