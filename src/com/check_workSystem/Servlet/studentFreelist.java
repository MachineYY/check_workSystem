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
 * Servlet implementation class studentFreelist
 */
@WebServlet("/studentFreelist")
public class studentFreelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentFreelist() {
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
		String stu_class = request.getParameter("stu_class");
		//��ȡ��ǰʱ�䣬�����ж���ٵ��Ƿ���Ч
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      Statement stmt=conn.createStatement();
		      //select * from free where stu_class='0216406' and free_flag='1' and starttime<'2020-2-28' and endtime>'2020-2-28';
		      String sql="select * from free where stu_class="+"'"+stu_class+"'" +"and free_flag='1'"  + "and starttime<"+"'"+dateStr+"'" + "and endtime>"+"'"+dateStr +"'";
		      ResultSet rs=stmt.executeQuery(sql);//��ѯ��ٱ�
		      List list = convertList(rs);
			  //System.out.print(list);
		      rs.close();
		      conn.close();
		      stmt.close();
			  //��ӡҳ��
		      PrintWriter out=response.getWriter();//ȡ�������
			    out.println("<html>");//���������Ҫ����HTML��
			    out.println("<head>");
			    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
			    out.println("<link rel=\"stylesheet\" href=\"css/public.css\" />");
			    out.println("<title>���ѧ���б�</title>");
			    out.println("</head>");
			    out.println("<body>");
			    out.println("<div id=\"right\">");
			    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
			    out.println("<tbody><tr><h4>���ѧ���б�</h4></tr></tbody>");
			    out.println("</table>");
			    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
			    out.println("<thead><tr><th width=\"15%\"> <p><span>ѧ��</span></p> </th>");
			    out.println("<th width=\"15%\"> <p><span>����</span></p> </th>");
			    out.println("<th width=\"15%\"> <p><span>�༶</span></p> </th>");
			    out.println("<th width=\"30%\"> <p><span>ԭ��</span></p> </th>");
			    out.println("<th width=\"25%\"> <p><span>�绰</span></p> </th> </tr></thead>");
			    for(int i=0;i<list.size();i++) {
			    	Map s = (Map)(list.get(i));
			    	out.println("<tbody style=\"text-align: center;\"><tr>");
			    	out.println("<td height=\"30px;\"> ");
			    	out.println(s.get("stu_no"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_name"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_class"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("free_reason"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_tel"));
			    	out.println("</td></tr> ");
			    }
			    out.println("</table>");
			    out.println("<p id=\"btn\"><input type=\"button\" value=\"����\" onclick = \"history.back()\"></p>");
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
			PrintWriter out=response.getWriter();//ȡ�������
	    	out.println("<html>");//���������Ҫ����HTML��
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('�鿴ʧ�ܣ������ԣ�');</script>");
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
