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
 * Servlet implementation class teacher_coursetable
 */
@WebServlet("/teacher_coursetable")
public class teacher_coursetable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacher_coursetable() {
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
    private static String KBG(){
    	//�÷�������jspҳ���ӡ�հ׸�
    	return "<td height='90px;'></td>";
    }
    private static String course(List list,String section,String day){
    	//�÷����ж��ܼ��ͽڴ�������Ȼ���ӡ�α�
    	String temp = null;
    	for (int i = 0; i < list.size(); i++) {
            Map s = (Map)(list.get(i));
            if(s.get("course_section").toString().equals(section) && s.get("course_day").toString().equals(day)) {
            	temp = "<td height=\"90px;\"> " +s.get("course_name")+ "</br>" +s.get("course_week") +"��</br>" +s.get("course_add")+ "</td>";
            	return temp;
            }else {
            	temp = "<td height=\"90px;\"></td>";
            }
            }
    	//System.out.print(temp);
		return temp;
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
		    String sql="select * from s_course where tea_no="+"'"+u_id+"'";
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
		    out.println("<link rel=\"stylesheet\" href=\"css/student_coursetable.css\" />");
		    out.println("<title>ѧ���γ̱�</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<div id=\"right\">");
		    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
		    out.println("<tbody><tr><h4>2020  ����ѧ�ڿγ̰���</h4></tr></tbody>");
		    out.println("</table>");
		    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
		    out.println("<thead><tr><th width=\"12.5%\"> <p><span>&nbsp;</span><span lang=\"EN-US\"> </span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>��һ</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>�ܶ�</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>����</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>����</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>����</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>����</span></p> </th>");
		    out.println("<th width=\"12.5%\"> <p><span>����</span></p> </th> </tr> </thead>");
		    out.println("<tbody style=\"text-align: center;\"><tr><th class=\"vam\">����1-2��</th>");
		    out.println(course(list,"1","1"));
		    out.println(course(list,"1","2"));
		    out.println(course(list,"1","3"));
		    out.println(course(list,"1","4"));
		    out.println(course(list,"1","5"));
		    out.println(course(list,"1","6"));
		    out.println(course(list,"1","7"));
		    out.println("</tr><tr><th class=\"vam\">����3-4��</th>");
		    out.println(course(list,"2","1"));
		    out.println(course(list,"2","2"));
		    out.println(course(list,"2","3"));
		    out.println(course(list,"2","4"));
		    out.println(course(list,"2","5"));
		    out.println(course(list,"2","6"));
		    out.println(course(list,"2","7"));
		    out.println("</tr><tr><th class=\"vam\">����5-6��</th>");
		    out.println(course(list,"3","1"));
		    out.println(course(list,"3","2"));
		    out.println(course(list,"3","3"));
		    out.println(course(list,"3","4"));
		    out.println(course(list,"3","5"));
		    out.println(course(list,"3","6"));
		    out.println(course(list,"3","7"));
		    out.println("</tr><tr><th class=\"vam\">����7-8��</th>");
		    out.println(course(list,"4","1"));
		    out.println(course(list,"4","2"));
		    out.println(course(list,"4","3"));
		    out.println(course(list,"4","4"));
		    out.println(course(list,"4","5"));
		    out.println(course(list,"4","6"));
		    out.println(course(list,"4","7"));
		    out.println("</tr><tr><th class=\"vam\">����9-10��</th>");
		    out.println(course(list,"5","1"));
		    out.println(course(list,"5","2"));
		    out.println(course(list,"5","3"));
		    out.println(course(list,"5","4"));
		    out.println(course(list,"5","5"));
		    out.println(course(list,"5","6"));
		    out.println(course(list,"5","7"));
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
		  //request.getRequestDispatcher("student_coursetable.jsp").forward(request, response);
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
