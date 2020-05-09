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
 * Servlet implementation class studentUpdate
 */
@WebServlet("/studentUpdate")
public class studentUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentUpdate() {
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
		String stu_no=request.getParameter("stu_no");
		String stu_name=request.getParameter("stu_name");
		String stu_pwd=request.getParameter("stu_pwd");
		String stu_sex=request.getParameter("stu_sex");
		String stu_class=request.getParameter("stu_class");
		String stu_marjor=request.getParameter("stu_marjor");
		String stu_tel=request.getParameter("stu_tel");
		//System.out.print(stu_no);
		//System.out.print(stu_name);
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      String sql="update student set stu_name=?,stu_pwd=?,stu_sex=?,stu_class=?,stu_marjor=?,stu_tel=? where stu_no=?";
		      java.sql.PreparedStatement ps=conn.prepareStatement(sql);
	          ps.setString(1,stu_name);
		      ps.setString(2,stu_pwd);
		      ps.setString(3,stu_sex);
		      ps.setString(4,stu_class);
		      ps.setString(5,stu_marjor);
		      ps.setString(6,stu_tel);
		      ps.setString(7,stu_no);
		      ps.executeUpdate();
		      ps.close();
		      conn.close();
		      PrintWriter out=response.getWriter();//取得输出流
	    	  out.println("<html>");//输出的内容要放在HTML中
	    	  out.println("<body>");
	    	  out.println("<script language='javaScript'> alert('修改成功！');</script>");
	    	  out.println("</body>");
	    	  out.println("</html>");
	    	  response.setHeader("refresh", "0;url=mange_student");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			PrintWriter out=response.getWriter();//取得输出流
	    	out.println("<html>");//输出的内容要放在HTML中
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('修改失败！请重试！');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	response.setHeader("refresh", "0;url=mange_student");
		}
		//doGet(request, response);
	}

}
