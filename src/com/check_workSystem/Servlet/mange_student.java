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
 * Servlet implementation class mange_student
 */
@WebServlet("/mange_student")
public class mange_student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mange_student() {
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
		try {
			Class.forName("com.mysql.jdbc.Driver");//��������
		    String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//���Ʊ����ʽ��
		    String user="root";
		    String password="root";
		    Connection conn=DriverManager.getConnection(url,user,password);
		    Statement stmt=conn.createStatement();
		    String sql="select * from student";
			ResultSet rs=stmt.executeQuery(sql);//��ѯѧ����
			List list = convertList(rs);
			rs.close();
			conn.close();
			stmt.close();
			//System.out.print(list);
			//��ӡҳ��
			response.setContentType("text/html;charset=utf-8");//����servlet��ӡ�����ݱ��뷽ʽ����Ҫ��
			PrintWriter out=response.getWriter();//ȡ�������
    		out.println("<html>");//���������Ҫ����HTML��
    		out.println("<head>");
    		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
    		out.println("<link rel=\"stylesheet\" href=\"css/public.css\" />");
    		out.println("<title>ѧ������ҳ</title>");
    		out.println("</head>");
    		out.println("<body>");
    		out.println("<div id=\"right\">");
    		out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
    		out.println("<tbody><tr><h4>ѧ�������</h4></tr></tbody>");
    		out.println("</table>");
    		out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
    		out.println("<thead><tr><th width=\"12.5%\"> <p><span>ѧ�����</span></p> </th>");
    		out.println("<th width=\"12.5%\"> <p><span>ѧ������<span></span></p> </th>");
    		out.println("<th width=\"12.5%\"> <p><span>ѧ������</span></p> </th>");
    		out.println("<th width=\"12.5%\"> <p><span>ѧ���Ա�</span></p> </th>");
    		out.println("<th width=\"12.5%\"> <p><span>ѧ���༶</span></p> </th>");
    		out.println("<th width=\"12.5%\"> <p><span>ѧ��רҵ</span></p> </th>");
    		out.println("<th width=\"12.5%\"> <p><span>ѧ���绰</span></p> </th>");
    		out.println("<th width=\"12.5%\"> <p><span>����</span></p> </th></tr></thead>");
    		for(int i=0;i<list.size();i++) {
    			Map s = (Map)(list.get(i));
    			String temp = s.get("stu_no").toString();
    			out.println("<tr><td height=\"30px;\"> " +s.get("stu_no")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_name")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_pwd")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_sex")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_class")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_marjor")+ "</td>");
    			out.println("<td height=\"30px;\"> " +s.get("stu_tel")+ "</td>");
    			out.println("<td height=\"30px;\"> <a href=\"studentUpdate.jsp?stu_no=" +temp+ "\">�޸�</a>");
    			out.println("<a href=\"studentDelete?stu_no=" +temp+ "\">ɾ��</a></td></tr>");
    		}
    		out.println("</table></div>");
    		out.println("<div><p id=\"add3\"><a href=\"studentAdd.jsp\">����ѧ����Ϣ</a></p></div>");
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
