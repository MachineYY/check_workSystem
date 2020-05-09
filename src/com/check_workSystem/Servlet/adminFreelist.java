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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class adminFreelist
 */
@WebServlet("/adminFreelist")
public class adminFreelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminFreelist() {
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
		response.setContentType("text/html;charset=utf-8");//控制servlet打印的内容编码方式（重要）
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      Statement stmt=conn.createStatement();
		      String sql="select * from free where free_flag='0'";
		      ResultSet rs=stmt.executeQuery(sql);//查询请假表
		      List list = convertList(rs);
			  //System.out.print(list);
		      rs.close();
		      conn.close();
		      stmt.close();
			  //打印页面
		      PrintWriter out=response.getWriter();//取得输出流
			    out.println("<html>");//输出的内容要放在HTML中
			    out.println("<head>");
			    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
			    out.println("<link rel=\"stylesheet\" href=\"css/public.css\" />");
			    out.println("<title>请假申请列表</title>");
			    out.println("</head>");
			    out.println("<body>");
			    out.println("<div id=\"right\">");
			    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
			    out.println("<tbody><tr><h4>请假申请列表</h4></tr></tbody>");
			    out.println("</table>");
			    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
			    out.println("<thead><tr><th width=\"10%\"> <p><span>申请编号</span></p> </th>");
			    out.println("<th width=\"10%\"> <p><span>学生编号</span></p> </th>");
			    out.println("<th width=\"10%\"> <p><span>学生姓名</span></p> </th>");
			    out.println("<th width=\"10%\"> <p><span>班级</span></p> </th>");
			    out.println("<th width=\"20%\"> <p><span>请假原因</span></p> </th>");
			    out.println("<th width=\"10%\"> <p><span>开始时间</span></p> </th>");
			    out.println("<th width=\"10%\"> <p><span>结束时间</span></p> </th>");
			    out.println("<th width=\"10%\"> <p><span>电话</span></p> </th>");
			    out.println("<th width=\"10%\"> <p><span></span></p> </th> </tr></thead>");
			    for(int i=0;i<list.size();i++) {
			    	Map s = (Map)(list.get(i));
			    	out.println("<tbody style=\"text-align: center;\"><tr>");
			    	out.println("<td height=\"30px;\"> ");
			    	out.println(s.get("free_no"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_no"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_name"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_class"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("free_reason"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("starttime"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("endtime"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_tel"));
			    	out.println("</td><td><a href=\"adminAgreefree?free_no=" +s.get("free_no")+ "\">同意申请</a></br>");
			    	out.println("<a href=\"adminRefusefree?free_no=" +s.get("free_no")+ "\">拒绝申请</a>");
			    	out.println("</td></tr> ");
			    }
			    out.println("</table>");
			    //out.println("<input type=\"button\" value=\"返回\" onclick = \"history.back()\">");
			    out.println("</div>");
			    out.println("</body>");
			    out.println("</html>");
	    	  //response.setHeader("refresh", "0;url=teacher.jsp");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			PrintWriter out=response.getWriter();//取得输出流
	    	out.println("<html>");//输出的内容要放在HTML中
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('查询失败！请重试！');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	out.println("<script language=javascript>history.back()</script>") ;
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
