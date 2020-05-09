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
 * Servlet implementation class teacherAdd
 */
@WebServlet("/teacherAdd")
public class teacherAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
		String tea_no=request.getParameter("tea_no");
		String tea_name=request.getParameter("tea_name");
		String tea_pwd=request.getParameter("tea_pwd");
		String tea_sex=request.getParameter("tea_sex");
		String tea_tel=request.getParameter("tea_tel");
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      String sql="insert into teacher(tea_no,tea_name,tea_pwd,tea_sex,tea_tel) values(?,?,?,?,?)";
		      java.sql.PreparedStatement ps=conn.prepareStatement(sql);
		      ps.setString(1,tea_no);
		      ps.setString(2,tea_name);
	          ps.setString(3,tea_pwd);
		      ps.setString(4,tea_sex);
		      ps.setString(5,tea_tel);
		      ps.executeUpdate();
		      ps.close();
		      conn.close();
		      PrintWriter out=response.getWriter();//取得输出流
	    	  out.println("<html>");//输出的内容要放在HTML中
	    	  out.println("<body>");
	    	  out.println("<script language='javaScript'> alert('增加成功！');</script>");
	    	  out.println("</body>");
	    	  out.println("</html>");
	    	  response.setHeader("refresh", "0;url=mange_teacher");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			PrintWriter out=response.getWriter();//取得输出流
	    	out.println("<html>");//输出的内容要放在HTML中
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('增加失败！请重试！');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	response.setHeader("refresh", "0;url=teacherAdd.jsp");
		}
		//doGet(request, response);
	}

}
