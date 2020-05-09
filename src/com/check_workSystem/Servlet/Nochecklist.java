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
 * Servlet implementation class Nochecklist
 */
@WebServlet("/Nochecklist")
public class Nochecklist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Nochecklist() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
		String stu_no = request.getParameter("stu_no");
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载驱动
		    String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//控制编码格式
		    String user="root";
		    String password="root";
		    Connection conn=DriverManager.getConnection(url,user,password);
		    Statement stmt=conn.createStatement();
		    String sql="select * from stu_check where check_flag='0' and stu_no='"+stu_no+"'";
			ResultSet rs=stmt.executeQuery(sql);//查询表
			List list = convertList(rs);
			rs.close();
			conn.close();
			stmt.close();
			//打印页面
			response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
			PrintWriter out=response.getWriter();//取得输出流
    		out.println("<html>");//输出的内容要放在HTML中
    		out.println("<head>");
    		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
    		out.println("<link rel=\"stylesheet\" href=\"css/public.css\" />");
    		out.println("<title>学生缺勤详情</title>");
    		out.println("</head>");
    		out.println("<body>");
    		out.println("<div id=\"right\">");
    		out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
    		out.println("<tbody><tr><h4>学生缺勤详情表</h4></tr></tbody>");
    		out.println("</table>");
    		out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
    		out.println("<thead><tr><th width=\"25%\"> <p><span>学生编号</span></p> </th>");
    		out.println("<th width=\"25%\"> <p>学生姓名<span></span></p> </th>");
    		out.println("<th width=\"25%\"> <p><span>课程编号</span></p> </th>");
    		out.println("<th width=\"25%\"> <p><span>课程名称</span></p> </th></tr></thead>");
    		for(int i=0;i<list.size();i++) {
    			Map s = (Map)(list.get(i));
    			out.println("<tr><td height=\"30px;\"> " +s.get("stu_no")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_name")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("course_no")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("course_name")+ "</td></tr>");
    		}
    		out.println("</table></div>");
    		out.print("<p id=\"btn\"><input type=\"button\" value=\"返回\" onclick = \"history.back()\"></p>");
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
