package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class studentFreedelete
 */
@WebServlet("/studentFreedelete")
public class studentFreedelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentFreedelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");//����servlet��ӡ�����ݱ��뷽ʽ����Ҫ��
		String free_no=request.getParameter("free_no");
		System.out.print(free_no);
		try {
			  Class.forName("com.mysql.jdbc.Driver");//����JDBC��������
		      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//�������ݿ��url��ַ,����Ŀ��Ʊ���������Ҫ
		      String user="root";//��¼���ݿ���û���
		      String password="root";//��¼���ݿ���û���������
		      Connection conn=DriverManager.getConnection(url,user,password);
		      //java.sql.Statement stmt=conn.createStatement();//��ȡstatement����
		      String sql="delete from free where free_no=?";
		      java.sql.PreparedStatement ps=conn.prepareStatement(sql);
		      ps.setString(1,free_no);
		      ps.executeUpdate();
		      ps.close();
		      conn.close();
		      PrintWriter out=response.getWriter();//ȡ�������
	    	  out.println("<html>");//���������Ҫ����HTML��
	    	  out.println("<body>");
	    	  out.println("<script language='javaScript'> alert('��������ɹ���');</script>");
	    	  out.println("</body>");
	    	  out.println("</html>");
	    	  response.setHeader("refresh", "0;url=studentFreelistadd");
		     
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			PrintWriter out=response.getWriter();//ȡ�������
	    	out.println("<html>");//���������Ҫ����HTML��
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('��������ʧ�ܣ������ԣ�');</script>");
	    	out.println("</body>");
	    	out.println("</html>");
	    	response.setHeader("refresh", "0;url=studentFreelistadd");
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
