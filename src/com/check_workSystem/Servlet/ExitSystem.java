package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExitSystem
 */
@WebServlet("/ExitSystem")
public class ExitSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExitSystem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
		Cookie[] cookies = request.getCookies();

		/* 测试cookie是否处理
		if(cookies != null){
			for(int i=0;i <cookies.length;i++){
		      Cookie c = cookies[i];
		     if("username".equals(c.getName())){
		    	 System.out.println("cookie为:");
		         System.out.println(c.getValue());
		     }}} */
		if (cookies != null && cookies.length > 0) {
		for (Cookie cookie : cookies) {
		// 找到需要删除的Cookie
		if("u_id".equals(cookie.getName())){
		// 设置生存期为0
		                    cookie.setMaxAge(0);
		// 设回Response中生效
		                    response.addCookie(cookie);
		                }

		            }
		        }
		PrintWriter out=response.getWriter();//取得输出流
		out.println("<html>");//输出的内容要放在HTML中
		out.println("<body>");
		out.println("<script language='javaScript'> alert('成功退出系统！');</script>");
		out.println("</body>");
		out.println("</html>");
		response.setHeader("refresh", "0;url=Login.jsp");

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
