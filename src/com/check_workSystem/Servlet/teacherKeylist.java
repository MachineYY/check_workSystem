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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class teacherKeylist
 */
@WebServlet("/teacherKeylist")
public class teacherKeylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherKeylist() {
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
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载驱动
		    String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//控制编码格式
		    String user="root";
		    String password="root";
		    Connection conn=DriverManager.getConnection(url,user,password);
		    Statement stmt=conn.createStatement();
		    String sql="select * from check_key where tea_no="+"'"+tea_no+"'";
			ResultSet rs=stmt.executeQuery(sql);//查询学生课程表
			//System.out.print(rs.getObject("course_no"));
			List list = convertList(rs);//将result结果集转存在list中 因为result对象会关闭
			//System.out.print(list);
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
		    out.println("<title>已发布密钥</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<div id=\"right\">");
		    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
		    out.println("<tbody><tr><h4>已发布密钥列表</h4></tr></tbody>");
		    out.println("</table>");
		    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
		    out.println("<thead><tr><th width=\"20%\"> <p><span>签到密钥</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>课程编号</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>课程名称</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>课程班级</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>人员列表</span></p> </th> </tr> </thead>");
		    for(int i=0;i<list.size();i++) {
		    	Map s = (Map)(list.get(i));
		    	out.println("<tbody style=\"text-align: center;\"><tr>");
		    	out.println("<td height=\"30px;\"> ");
		    	out.println(s.get("check_key"));
		    	out.println("</td><td height=\"30px;\"> ");
		    	out.println(s.get("course_no"));
		    	out.println("</td><td height=\"30px;\"> ");
		    	out.println(s.get("course_name"));
		    	out.println("</td><td height=\"30px;\"> ");
		    	out.println(s.get("stu_class"));
		    	out.println("</td><td height=\"30px;\"> ");
		    	out.println("<a href=\"studentKeylist?stu_class=" +s.get("stu_class")+ "\">应到人员</a>");
		    	out.println("<a href=\"studentFreelist?stu_class=" +s.get("stu_class")+ "\">请假人员</a>");
		    	out.println("<a href=\"studentNolist?check_key=" +s.get("check_key")+ "\">未签到</a></td></tr>");
		    }
		    out.println("</table>");
		    out.println("</div>");
		    out.println("</body>");
		    out.println("</html>");
		    //response.setHeader("refresh", "0;url=student_coursetable.jsp");
			
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
