package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class studentKey
 */
@WebServlet("/studentKey")
public class studentKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentKey() {
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
		        //System.out.print(cookie1.getValue());
		        }
		     }
		  }else{
		      System.out.println("<h2>没有发现 Cookie</h2>");
		  }
		String stu_no = cookie1.getValue();
		String check_key = request.getParameter("check_key");
		if(check_key!="") {
			try {
				  Class.forName("com.mysql.jdbc.Driver");
			      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
			      String user="root";
			      String password="root";
			      Connection conn=DriverManager.getConnection(url,user,password);
			      String sql="update stu_check set check_flag=? where stu_no=? and check_key=?";
			      java.sql.PreparedStatement ps=conn.prepareStatement(sql);
			      ps.setLong(1,1);
			      ps.setString(2,stu_no);
			      ps.setString(3,check_key);
			      ps.executeUpdate();
			      ps.close();
			      conn.close();
			      PrintWriter out=response.getWriter();//取得输出流
		    	  out.println("<html>");//输出的内容要放在HTML中
		    	  out.println("<body>");
		    	  out.println("<script language='javaScript'> alert('该密钥签到成功！');</script>");
		    	  out.println("</body>");
		    	  out.println("</html>");
		    	  out.println("<script language=javascript>history.back()</script>") ;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
				PrintWriter out=response.getWriter();//取得输出流
		    	out.println("<html>");//输出的内容要放在HTML中
		    	out.println("<body>");
		    	out.println("<script language='javaScript'> alert('该密钥签到失败！请重试！');</script>");
		    	out.println("</body>");
		    	out.println("</html>");
		    	out.println("<script language=javascript>history.back()</script>") ;
			}
		}else {
			PrintWriter out=response.getWriter();//取得输出流
	    	out.println("<html>");//输出的内容要放在HTML中
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('该密钥签到失败！请重试！');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	out.println("<script language=javascript>history.back()</script>") ;
		}
		//doGet(request, response);
	}

}
