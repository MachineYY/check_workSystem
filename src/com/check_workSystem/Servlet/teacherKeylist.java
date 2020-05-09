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
    	//�÷�����resultתΪList
    	List list = new ArrayList();
    	ResultSetMetaData md = rs.getMetaData();//��ȡ����
    	int columnCount = md.getColumnCount();//��ȡ�е�����
    	while (rs.next()) {
    	Map rowData = new HashMap();//����Map
    	for (int i = 1; i <= columnCount; i++) {
    	rowData.put(md.getColumnName(i), rs.getObject(i));//��ȡ������ֵ
    	}
    	list.add(rowData);
    	}
    	return list;
    	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");//����servlet��ӡ�����ݱ��뷽ʽ����Ҫ��
		Cookie cookie = null;
		Cookie[] cookies = null;
		// ��ȡcookies������,��һ������
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
		      System.out.println("<h2>û�з��� Cookie</h2>");
		  }
		String tea_no = cookie1.getValue();
		try {
			Class.forName("com.mysql.jdbc.Driver");//��������
		    String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//���Ʊ����ʽ��
		    String user="root";
		    String password="root";
		    Connection conn=DriverManager.getConnection(url,user,password);
		    Statement stmt=conn.createStatement();
		    String sql="select * from check_key where tea_no="+"'"+tea_no+"'";
			ResultSet rs=stmt.executeQuery(sql);//��ѯѧ���γ̱�
			//System.out.print(rs.getObject("course_no"));
			List list = convertList(rs);//��result�����ת����list�� ��Ϊresult�����ر�
			//System.out.print(list);
			rs.close();
		    conn.close();
		    stmt.close();
			//��ӡҳ��
			response.setContentType("text/html;charset=utf-8");//����servlet��ӡ�����ݱ��뷽ʽ����Ҫ��
			PrintWriter out=response.getWriter();//ȡ�������
		    out.println("<html>");//���������Ҫ����HTML��
		    out.println("<head>");
		    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
		    out.println("<link rel=\"stylesheet\" href=\"css/public.css\" />");
		    out.println("<title>�ѷ�����Կ</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<div id=\"right\">");
		    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
		    out.println("<tbody><tr><h4>�ѷ�����Կ�б�</h4></tr></tbody>");
		    out.println("</table>");
		    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
		    out.println("<thead><tr><th width=\"20%\"> <p><span>ǩ����Կ</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>�γ̱��</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>�γ�����</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>�γ̰༶</span></p> </th>");
		    out.println("<th width=\"20%\"> <p><span>��Ա�б�</span></p> </th> </tr> </thead>");
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
		    	out.println("<a href=\"studentKeylist?stu_class=" +s.get("stu_class")+ "\">Ӧ����Ա</a>");
		    	out.println("<a href=\"studentFreelist?stu_class=" +s.get("stu_class")+ "\">�����Ա</a>");
		    	out.println("<a href=\"studentNolist?check_key=" +s.get("check_key")+ "\">δǩ��</a></td></tr>");
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
