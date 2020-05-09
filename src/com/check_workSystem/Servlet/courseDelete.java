package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class courseDelete
 */
@WebServlet("/courseDelete")
public class courseDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public courseDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
		String number=request.getParameter("number");
		try {
			  Class.forName("com.mysql.jdbc.Driver");//加载JDBC驱动程序
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//连接数据库的url地址,后面的控制编码语句很重要
		      String user="root";//登录数据库的用户名
		      String password="root";//登录数据库的用户名的密码
		      Connection conn=DriverManager.getConnection(url,user,password);
		      //java.sql.Statement stmt=conn.createStatement();//获取statement对象
		      String sql="delete from s_course where number=?";
		      java.sql.PreparedStatement ps=conn.prepareStatement(sql);
		      ps.setString(1,number);
		      ps.executeUpdate();
		      ps.close();
		      conn.close();
		      PrintWriter out=response.getWriter();//取得输出流
	    	  out.println("<html>");//输出的内容要放在HTML中
	    	  out.println("<body>");
	    	  out.println("<script language='javaScript'> alert('删除课程成功！');</script>");
	    	  out.println("</body>");
	    	  out.println("</html>");
	    	  response.setHeader("refresh", "0;url=mange_course");
		     
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			PrintWriter out=response.getWriter();//取得输出流
	    	out.println("<html>");//输出的内容要放在HTML中
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('删除课程失败！请重试！');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	response.setHeader("refresh", "0;url=mange_course");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
