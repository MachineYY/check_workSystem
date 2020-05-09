package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login_check
 */
@WebServlet("/login_check")
public class login_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login_check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
		String u_id = request.getParameter("u_id");
		String u_pwd = request.getParameter("u_pwd");
//		System.out.println(u_id);
//		System.out.println(u_pwd);
		try{
			  Class.forName("com.mysql.jdbc.Driver");//加载驱动
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//控制编码格式
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      Statement stmt=conn.createStatement();
		      String sql="select stu_pwd from student where stu_no="+"'"+u_id+"'";
		      String sql1="select tea_pwd from teacher where tea_no="+"'"+u_id+"'";
		      String sql2="select admin_pwd from admin where admin_no="+"'"+u_id+"'";
			  ResultSet rs=stmt.executeQuery(sql);//查询学生表
			    if(rs.next())
			    {
			        if(u_pwd.equals(rs.getObject("stu_pwd"))){
			        	//将用户名存入cookie 并且设置cookie存在时长
	            	    Cookie cookie_uid = new Cookie("u_id",u_id);
	            	    cookie_uid.setMaxAge(60*60*60);
	            	    response.addCookie(cookie_uid);
			            response.sendRedirect("student.jsp");
			        }
			        else{
			        	PrintWriter out=response.getWriter();//取得输出流
			    		out.println("<html>");//输出的内容要放在HTML中
			    		out.println("<body>");
			    		out.println("<script language='javaScript'> alert('密码错误！请重新登录！');</script>");
			    		out.println("</body>");
			    		out.println("</html>");
			    		response.setHeader("refresh", "0;url=Login.jsp");
			        }  
			    }else{
			    	rs.close();//学生表无符合条件，关闭rs
			        ResultSet rs1=stmt.executeQuery(sql1);//查询教师表
			        if(rs1.next()) {
				        if(u_pwd.equals(rs1.getObject("tea_pwd"))){
				        	//将用户名存入cookie 并且设置cookie存在时长
		            	    Cookie cookie_uid = new Cookie("u_id",u_id);
		            	    cookie_uid.setMaxAge(60*60*60);
		            	    response.addCookie(cookie_uid);
				            response.sendRedirect("teacher.jsp");
				        }
				        else{
				        	PrintWriter out=response.getWriter();//取得输出流
				    		out.println("<html>");//输出的内容要放在HTML中
				    		out.println("<body>");
				    		out.println("<script language='javaScript'> alert('密码错误！请重新登录！');</script>");
				    		out.println("</body>");
				    		out.println("</html>");
				    		response.setHeader("refresh", "0;url=Login.jsp");
				        }
			        }else{
			        	rs1.close();//教师表无符合条件，关闭rs1
				        ResultSet rs2=stmt.executeQuery(sql2);//查询辅导员表
				        if(rs2.next()) {
					        if(u_pwd.equals(rs2.getObject("admin_pwd"))){
					        	//将用户名存入cookie 并且设置cookie存在时长
			            	    Cookie cookie_uid = new Cookie("u_id",u_id);
			            	    cookie_uid.setMaxAge(60*60*60);
			            	    response.addCookie(cookie_uid);
					            response.sendRedirect("admin.jsp");
					        }
					        else{
					        	PrintWriter out=response.getWriter();//取得输出流
					    		out.println("<html>");//输出的内容要放在HTML中
					    		out.println("<body>");
					    		out.println("<script language='javaScript'> alert('密码错误！请重新登录！');</script>");
					    		out.println("</body>");
					    		out.println("</html>");
					    		response.setHeader("refresh", "0;url=Login.jsp");
					        }
				        }else {
						    	PrintWriter out=response.getWriter();//取得输出流
					    		out.println("<html>");//输出的内容要放在HTML中
					    		out.println("<body>");
					    		out.println("<script language='javaScript'> alert('用户名错误！请重新登录！');</script>");
					    		out.println("</body>");
					    		out.println("</html>");
					    		response.setHeader("refresh", "0;url=Login.jsp");
					    		rs2.close();
					    		conn.close();
					    		stmt.close();
				        		}
			        	}
			    }
		      if(conn!=null){
		    	  System.out.print("数据库连接成功！");
		      }//else
//		      {
//		    	  System.out.print("数据库连接失败！");
//		       }
			  }
		      catch(ClassNotFoundException e){
				e.printStackTrace();
			  }
			  catch(SQLException e){
				e.printStackTrace();
			  }	
	}

}
