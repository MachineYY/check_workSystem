package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class mange_course
 */
@WebServlet("/mange_course")
public class mange_course extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mange_course() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载驱动
		    String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//控制编码格式
		    String user="root";
		    String password="root";
		    Connection conn=DriverManager.getConnection(url,user,password);
		    Statement stmt=conn.createStatement();
		    String sql="select * from s_course";
			ResultSet rs=stmt.executeQuery(sql);//查询课程表
			List list = convertList(rs);
			rs.close();
			conn.close();
			stmt.close();
			//System.out.print(list);
			//打印页面
			response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
			PrintWriter out=response.getWriter();//取得输出流
    		out.println("<html>");//输出的内容要放在HTML中
    		out.println("<head>");
    		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
    		out.println("<link rel=\"stylesheet\" href=\"css/public.css\" />");
    		out.println("<title>课程管理页</title>");
    		out.println("</head>");
    		out.println("<body>");
    		out.println("<div id=\"right\">");
    		out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
    		out.println("<tbody><tr><h4>课程管理表</h4></tr></tbody>");
    		out.println("</table>");
    		out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
    		out.println("<thead><tr><th width=\"10%\"> <p><span>课程编号</span></p> </th>");
    		out.println("<th width=\"20%\"> <p><span>课程名称<span></span></p> </th>");
    		out.println("<th width=\"10%\"> <p><span>教师编号</span></p> </th>");
    		out.println("<th width=\"10%\"> <p><span>课程班级</span></p> </th>");
    		out.println("<th width=\"10%\"> <p><span>课程周</span></p> </th>");
    		out.println("<th width=\"10%\"> <p><span>课程日</span></p> </th>");
    		out.println("<th width=\"10%\"> <p><span>课程节次</span></p> </th>");
    		out.println("<th width=\"10%\"> <p><span>课程地点</span></p> </th>");
    		out.println("<th width=\"10%\"> <p><span>操作</span></p> </th></tr></thead>");
    		for(int i=0;i<list.size();i++) {
    			Map s = (Map)(list.get(i));
    			out.println("<tr><td height=\"30px;\"> " +s.get("course_no")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("course_name")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("tea_no")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_class")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("course_week")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("course_day")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("course_section")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("course_add")+ "</td>");
    			out.println("<td height=\"30px;\"> <a href=\"courseDelete?number=" +s.get("number")+ "\">删除</a>");
    			out.println("</td></tr>");
    		}
    		out.println("</table></div>");
    		out.println("<div><p id=\"add1\"><a href=\"courseAdd.jsp\">添加课程信息</a></p></div>");
    		out.println("</body>");
    		out.println("</html>");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		  }
		  catch(SQLException e){
			e.printStackTrace();
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
