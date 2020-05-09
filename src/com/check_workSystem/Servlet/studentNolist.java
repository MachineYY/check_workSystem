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
 * Servlet implementation class studentNolist
 */
@WebServlet("/studentNolist")
public class studentNolist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentNolist() {
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
		String check_key = request.getParameter("check_key");
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      Statement stmt=conn.createStatement();
		      //select * from free where stu_class='0216406' and free_flag='1' and starttime<'2020-2-28' and endtime>'2020-2-28';
		      String sql="select * from stu_check where check_flag='0' and check_key="+"'"+check_key+"'" ;
		      ResultSet rs=stmt.executeQuery(sql);//查询表
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
			    out.println("<title>未签到学生列表</title>");
			    out.println("</head>");
			    out.println("<body>");
			    out.println("<div id=\"right\">");
			    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
			    out.println("<tbody><tr><h4>未签到学生列表</h4></tr></tbody>");
			    out.println("</table>");
			    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
			    out.println("<thead><tr><th width=\"50%\"> <p><span>学号</span></p> </th>");
			    out.println("<th width=\"50%\"> <p><span>姓名</span></p> </th> </tr></thead>");
			    for(int i=0;i<list.size();i++) {
			    	Map s = (Map)(list.get(i));
			    	out.println("<tbody style=\"text-align: center;\"><tr>");
			    	out.println("<td height=\"30px;\"> ");
			    	out.println(s.get("stu_no"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_name"));
			    	out.println("</td></tr> ");
			    }
			    out.println("</table>");
			    out.println("<p id=\"btn\"><input type=\"button\" value=\"返回\" onclick = \"history.back()\"></p>");
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
	    	out.println("<script language='javaScript'> alert('查看失败！请重试！');</script>");
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
