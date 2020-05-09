package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class teacherKey
 */
@WebServlet("/teacherKey")
public class teacherKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherKey() {
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
	  private static List convertList(ResultSet rs) throws SQLException{
	    	//该方法将result转为List
	    	List list = new ArrayList();
	    	ResultSetMetaData md = rs.getMetaData();//获取键名
	    	int columnCount = md.getColumnCount();//获取行的数量
	    	while (rs.next()) {
	    	Map rowData = new HashMap();//声明Map
	    	for (int i = 1; i <= columnCount; i++) {
	    	rowData.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
	    	}
	    	list.add(rowData);
	    	}
	    	return list;
	    	}
	private static void updatefree(String stu_class,String check_key) throws SQLException{
		//获取当前时间，用于判断请假单是否有效
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      String sql="select stu_no from free where free_flag='1'"+ "and stu_class="+"'"+stu_class+"'"  + "and starttime<"+"'"+dateStr+"'" + "and endtime>"+"'"+dateStr +"'";
		      Statement stmt=conn.createStatement();
		      ResultSet rs=stmt.executeQuery(sql);
		      List list = convertList(rs);//将result结果集转存在list中 因为result对象会关闭
		      //System.out.print(list);
		      rs.close();
		      //conn.close();
		      stmt.close();
		      for(int i=0; i<list.size();i++) {
				  Map s = (Map)(list.get(i));
			      String sqllist="update stu_check set check_flag=? where stu_no=? and check_key=?";
			      java.sql.PreparedStatement ps1=conn.prepareStatement(sqllist);
			      ps1.setLong(1,-1);
		          ps1.setString(2,(String) s.get("stu_no"));
			      ps1.setString(3,check_key);
			      ps1.executeUpdate();
			      ps1.close();  
		      }
		      conn.close();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static void createcheck(String key,String course_no,String course_name,String stu_class) throws SQLException{
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      String sql = "select * from student where stu_class="+"'"+stu_class+"'";
		      Statement stmt=conn.createStatement();
		      ResultSet rs=stmt.executeQuery(sql);//查询学生表
		      List list = convertList(rs);//将result结果集转存在list中 因为result对象会关闭
		      //System.out.print(list);
		      rs.close();
		      //conn.close();
		      stmt.close();
		      for(int i=0; i<list.size();i++) {
				  Map s = (Map)(list.get(i));
			      String sqllist="insert into stu_check(stu_no,stu_name,course_no,course_name,check_flag,check_key) values(?,?,?,?,?,?)";
			      java.sql.PreparedStatement ps1=conn.prepareStatement(sqllist);
			      ps1.setString(1,(String) s.get("stu_no"));
		          ps1.setString(2,(String) s.get("stu_name"));
			      ps1.setString(3,course_no);
			      ps1.setString(4,course_name);
			      //ps1.setString(5,stu_class);
			      ps1.setLong(5,0);
			      ps1.setString(6,key);
			      ps1.executeUpdate();
			      ps1.close();  
		      }
		      conn.close();
		      updatefree(stu_class,key);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
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
		String tea_no = cookie1.getValue();
		String course_no = request.getParameter("course_no");
		String course_name = request.getParameter("course_name");
		String key = request.getParameter("key");
		String stu_class = request.getParameter("stu_class");
		if(key!="") {
			try {
				  Class.forName("com.mysql.jdbc.Driver");
			      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
			      String user="root";
			      String password="root";
			      Connection conn=DriverManager.getConnection(url,user,password);
			      String sql="insert into check_key(check_key,stu_class,course_no,course_name,tea_no) values(?,?,?,?,?)";
			      java.sql.PreparedStatement ps=conn.prepareStatement(sql);
			      ps.setString(1,key);
			      ps.setString(2,stu_class);
			      ps.setString(3,course_no);
		          ps.setString(4,course_name);
		          ps.setString(5,tea_no);
			      ps.executeUpdate();
			      ps.close();
			      conn.close();
			      createcheck(key,course_no,course_name,stu_class);
			      PrintWriter out=response.getWriter();//取得输出流
		    	  out.println("<html>");//输出的内容要放在HTML中
		    	  out.println("<body>");
		    	  out.println("<script language='javaScript'> alert('发布密钥成功！');</script>");
		    	  out.println("</body>");
		    	  out.println("</html>");
		    	  response.setHeader("refresh", "0;url=teacherKeylist");
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
				PrintWriter out=response.getWriter();//取得输出流
		    	out.println("<html>");//输出的内容要放在HTML中
		    	out.println("<body>");
		    	out.println("<script language='javaScript'> alert('发布密钥失败！请重试！');</script>");
		    	out.println("</body>");
		    	out.println("</html>");
		    	out.println("<script language=javascript>history.back()</script>") ;
			}
		}else {
			PrintWriter out=response.getWriter();//取得输出流
	    	out.println("<html>");//输出的内容要放在HTML中
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('发布密钥失败！请重试！');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	out.println("<script language=javascript>history.back()</script>") ;
		}
		//doGet(request, response);
	}

}
