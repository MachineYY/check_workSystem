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
 * Servlet implementation class studentFreelistadd
 */
@WebServlet("/studentFreelistadd")
public class studentFreelistadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentFreelistadd() {
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
		String u_id = null;
		Cookie[] cookies = request.getCookies();
		// ��ȡcookies������,��һ������
		cookies = request.getCookies();
		Cookie cookie = null;
		if( cookies != null ){
		    for (int i = 0; i < cookies.length; i++){
		       cookie = cookies[i];
		       if(cookies[i].getName().equals("u_id")){
		    	   u_id = cookie.getValue();
		    	   //System.out.print(cookie.getValue());
		           cookie=cookies[i];
		         }
		      }
		 }else{
		      System.out.println("<h2>û�з��� Cookie</h2>");
		  }
		try {
			Class.forName("com.mysql.jdbc.Driver");//��������
		    String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//���Ʊ����ʽ��
		    String user="root";
		    String password="root";
		    Connection conn=DriverManager.getConnection(url,user,password);
		    Statement stmt=conn.createStatement();
		    String sql="select * from free where stu_no="+"'"+u_id+"'";
			ResultSet rs=stmt.executeQuery(sql);//��ѯ��ٱ�
			List list = convertList(rs);//��result�����ת����list�� ��Ϊresult�����ر�
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
		    out.println("<title>��������б�</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<div id=\"right\">");
		    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
		    out.println("<tbody><tr><h4>��������б�</h4></tr></tbody>");
		    out.println("</table>");
		    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
		    out.println("<thead><tr>");
		    out.println("<th width=\"12.5%\"> <p><span>������</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>ѧ�����</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>ѧ������</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>���ԭ��</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>��ʼʱ��</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>����ʱ��</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>״̬</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>����</span></p> </th> </tr> </thead>");
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
		    	out.println(s.get("free_reason"));
		    	out.println("</td><td height=\"30px;\"> ");
		    	out.println(s.get("starttime"));
		    	out.println("</td><td height=\"30px;\"> ");
		    	out.println(s.get("endtime"));
		    	out.println("</td><td height=\"30px;\"> ");
		    	if(s.get("free_flag").equals(1)) {
		    		out.println("ͨ��");
		    	}else {
		    		if(s.get("free_flag").equals(0)) {
		    			out.println("δ����");
		    		}else {
		    			out.println("δͨ��");}
		    	}
		    	
		    	out.println("</td><td><a href=\"studentFreedelete?free_no=" +s.get("free_no")+ "\">��������</a>");
		    	out.println("</td></tr> ");
		    }
		   
		    out.println("</table>");
		    out.println("<div><p id=\"add5\"><a href=\"studentFree.jsp\">�ύ�������</a></p></div>");
		    //out.println("<p id=\"btn\"><input type=\"button\" value=\"�ύ�������\" onclick = \"window.location.href = 'studentFree.jsp'\"></p>");
		    out.println("</div>");
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
