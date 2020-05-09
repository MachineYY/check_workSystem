package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Changepwd
 */
@WebServlet("/Changepwd")
public class Changepwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Changepwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String u_id = cookie1.getValue();
		String pwd = request.getParameter("pwd");
		String newpwd = request.getParameter("newpwd");
		String newpwd1 = request.getParameter("newpwd1");
		//System.out.print(newpwd.equals(newpwd1));
		if(newpwd.equals(newpwd1)) {
			try{
				  Class.forName("com.mysql.jdbc.Driver");//��������
			      String url="jdbc:mysql://localhost:3306/check_work?useUnicode=true&characterEncoding=utf8";//���Ʊ����ʽ��
			      String user="root";
			      String password="root";
			      Connection conn=DriverManager.getConnection(url,user,password);
			      Statement stmt=conn.createStatement();
			      String sql1="select stu_pwd from student where stu_no="+"'"+u_id+"'";
			      String sql2="select tea_pwd from teacher where tea_no="+"'"+u_id+"'";
			      String sql3="select admin_pwd from admin where admin_no="+"'"+u_id+"'";
				  ResultSet rs=stmt.executeQuery(sql1);//��ѯѧ����
				  PrintWriter out=response.getWriter();//ȡ�������
				  if(rs.next()) {
					  if(pwd.equals(rs.getObject("stu_pwd"))) {
						  //System.out.print("��֤ͨ��");
						  String sql11="update student set stu_pwd=? where stu_no=?";
						  java.sql.PreparedStatement ps=conn.prepareStatement(sql11);
						  ps.setString(1,newpwd);
					      ps.setString(2,u_id);
					      ps.executeUpdate();
					      ps.close();
					      conn.close();
					      //PrintWriter out=response.getWriter();//ȡ�������
				    	  out.println("<html>");//���������Ҫ����HTML��
				    	  out.println("<body>");
				    	  out.println("<script language='javaScript'> alert('�޸ĳɹ���');</script>");
				    	  out.println("</body>");
				    	  out.println("</html>");
				    	  response.setHeader("refresh", "0;url=ExitSystem");
					  }else {
						  out.println("<html>");//���������Ҫ����HTML��
				    	  out.println("<body>");
				    	  out.println("<script language='javaScript'> alert('�޸�ʧ�ܣ�ԭ�������');</script>");
				    	  out.println("</body>");
				    	  out.println("</html>");
				    	  response.setHeader("refresh", "0;url=student.jsp");
					  }
				  }else{
					  rs.close();
					  ResultSet rs1=stmt.executeQuery(sql2);//��ѯ��ʦ��
					  if(rs1.next()) {
					        if(pwd.equals(rs1.getObject("tea_pwd"))){
					        	String sql11="update teacher set tea_pwd=? where tea_no=?";
								java.sql.PreparedStatement ps=conn.prepareStatement(sql11);
								ps.setString(1,newpwd);
							    ps.setString(2,u_id);
							    ps.executeUpdate();
							    ps.close();
							    conn.close();
						    	out.println("<html>");//���������Ҫ����HTML��
						    	out.println("<body>");
						    	out.println("<script language='javaScript'> alert('�޸ĳɹ���');</script>");
						    	out.println("</body>");
						    	out.println("</html>");
						    	response.setHeader("refresh", "0;url=ExitSystem");
					        }else{
					    		out.println("<html>");//���������Ҫ����HTML��
					    		out.println("<body>");
					    		out.println("<script language='javaScript'> alert('�޸�ʧ�ܣ�ԭ�������');</script>");
					    		out.println("</body>");
					    		out.println("</html>");
					    		response.setHeader("refresh", "0;url=teacher.jsp");
					        }
					  }else{
				        	rs1.close();//��ʦ���޷����������ر�rs1
					        ResultSet rs2=stmt.executeQuery(sql3);//��ѯ����Ա��
					        if(rs2.next()) {
						        if(pwd.equals(rs2.getObject("admin_pwd"))){
						        	String sql11="update admin set admin_pwd=? where admin_no=?";
									java.sql.PreparedStatement ps=conn.prepareStatement(sql11);
									ps.setString(1,newpwd);
								    ps.setString(2,u_id);
								    ps.executeUpdate();
								    ps.close();
								    conn.close();
							    	out.println("<html>");//���������Ҫ����HTML��
							    	out.println("<body>");
							    	out.println("<script language='javaScript'> alert('�޸ĳɹ���');</script>");
							    	out.println("</body>");
							    	out.println("</html>");
							    	response.setHeader("refresh", "0;url=ExitSystem");
						        }
						        else{
						    		out.println("<html>");//���������Ҫ����HTML��
						    		out.println("<body>");
						    		out.println("<script language='javaScript'> alert('�޸�ʧ�ܣ�ԭ�������');</script>");
						    		out.println("</body>");
						    		out.println("</html>");
						    		response.setHeader("refresh", "0;url=admin.jsp");
						        }
					        }else {
					    		out.println("<html>");//���������Ҫ����HTML��
					    		out.println("<body>");
					    		out.println("<script language='javaScript'> alert('�û����쳣��');</script>");
					    		out.println("</body>");
					    		out.println("</html>");
					    		response.setHeader("refresh", "0;url=Login.jsp");
					    		rs2.close();
					    		conn.close();
					    		stmt.close();
				        		}
				  }	  
				  }
			}
		      catch(ClassNotFoundException e){
				e.printStackTrace();
			  }
			  catch(SQLException e){
				e.printStackTrace();
			  }
			
			
		}else {
			PrintWriter out=response.getWriter();//ȡ�������
			out.println("<html>");//���������Ҫ����HTML��
	    	out.println("<body>");
	    	out.println("<script language='javaScript'> alert('���������벻һ�£�');</script>");
	        out.println("</body>");
	    	out.println("</html>");
	    	out.println("<script language=javascript>history.back()</script>") ;
		}
			
		//doGet(request, response);
	}

}
