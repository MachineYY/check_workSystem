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
 * Servlet implementation class studentFree
 */
@WebServlet("/studentFree")
public class studentFree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentFree() {
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
		String stu_class=request.getParameter("stu_class");
		String free_reason=request.getParameter("free_reason");
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		String stu_tel=request.getParameter("stu_tel");
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      String sql="insert into free(stu_no,stu_name,stu_class,free_reason,starttime,endtime,stu_tel,free_flag) values(?,?,?,?,?,?,?,?)";
		      java.sql.PreparedStatement ps=conn.prepareStatement(sql);
		      //ps.setString(1,"");
		      ps.setString(1,stu_no);
	          ps.setString(2,stu_name);
		      ps.setString(3,stu_class);
		      ps.setString(4,free_reason);
		      ps.setString(5,starttime);
		      ps.setString(6,endtime);
		      ps.setString(7,stu_tel);
		      ps.setLong(8,0);
		      ps.executeUpdate();
		      ps.close();
		      conn.close();
		      PrintWriter out=response.getWriter();//取得输出流
	    	  out.println("<html>");//输出的内容要放在HTML中
	    	  out.println("<body>");
	    	  out.println("<script language='javaScript'> alert('提交申请成功！');</script>");
	    	  out.println("</body>");
	    	  out.println("</html>");
	    	  response.setHeader("refresh", "0;url=studentFreelistadd");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			PrintWriter out=response.getWriter();//取得输出流
	    	out.println("<html>");//输出的内容要放在HTML中
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('提交申请失败！请重试！');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	response.setHeader("refresh", "0;url=studentFreelistadd");
		}
		//doGet(request, response);
	}

}
