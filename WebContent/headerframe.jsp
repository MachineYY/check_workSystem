<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/headerame.css" />
<title>顶部页</title>
</head>
<body>
	<div class="root">
		<div class="logo">
			<h1 id="title">湖北民族大学学生考勤系统                </h1>
		</div>
<%
   Cookie cookie = null;
   Cookie[] cookies = null;
   // 获取cookies的数据,是一个数组
   cookies = request.getCookies();
   Cookie cookie1 = null;
   if( cookies != null ){
      for (int i = 0; i < cookies.length; i++){
         cookie = cookies[i];
         if(cookies[i].getName().equals("u_id")){
        	 cookie1=cookies[i];
         }
      }
  }else{
      out.println("<h2>没有发现 Cookie</h2>");
  }
%>
		<div class="login_info">
			<ul class="login_menu">
				<li class="huadong"><a href="ExitSystem" target="_top" class="Get_out">退出</a></li>
				<li class="huadong"><a href="Changepwd.jsp" target="rightframe">修改密码</a></li>
				<li style="width:120px;"><span class="login-name"><a href="javascript:;" id="username"><%=cookie1.getValue() %></a></span></li>
			</ul>
		</div>
	</div>
</body>
</html>