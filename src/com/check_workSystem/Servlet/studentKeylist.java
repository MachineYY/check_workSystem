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
 * Servlet implementation class studentKeylist
 */
@WebServlet("/studentKeylist")
public class studentKeylist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentKeylist() {
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
		try {
			  Class.forName("com.mysql.jdbc.Driver");
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";
		      String user="root";
		      String password="root";
		      Connection conn=DriverManager.getConnection(url,user,password);
		      Statement stmt=conn.createStatement();
		      String sql="select * from student where stu_class="+"'"+stu_class+"'";
		      ResultSet rs=stmt.executeQuery(sql);//��ѯѧ����
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
			    out.println("<title>Ӧ��ѧ���б�</title>");
			    out.println("</head>");
			    out.println("<body>");
			    out.println("<div id=\"right\">");
			    out.println("<table class=\"title tac f14px\" cellpadding=\"0\" cellspacing=\"0\">");
			    out.println("<tbody><tr><h4>Ӧ��ѧ���б�</h4></tr></tbody>");
			    out.println("</table>");
			    out.println("<table cellspacing=\"1\" class=\"list_table tac\" cellpadding=\"0\">");
			    out.println("<thead><tr><th width=\"15%\"> <p><span>ѧ��</span></p> </th>");
			    out.println("<th width=\"15%\"> <p><span>����</span></p> </th>");
			    out.println("<th width=\"15%\"> <p><span>�Ա�</span></p> </th>");
			    out.println("<th width=\"15%\"> <p><span>�༶</span></p> </th>");
			    out.println("<th width=\"25%\"> <p><span>רҵ</span></p> </th>");
			    out.println("<th width=\"15%\"> <p><span>�绰</span></p> </th> </tr></thead>");
			    for(int i=0;i<list.size();i++) {
			    	Map s = (Map)(list.get(i));
			    	out.println("<tbody style=\"text-align: center;\"><tr>");
			    	out.println("<td height=\"30px;\"> ");
			    	out.println(s.get("stu_no"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_name"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_sex"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_class"));
			    	out.println("</td><td height=\"30px;\"> ");
			    	out.println(s.get("stu_marjor"));
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
