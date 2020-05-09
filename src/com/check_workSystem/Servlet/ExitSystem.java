package com.check_workSystem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExitSystem
 */
@WebServlet("/ExitSystem")
public class ExitSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExitSystem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");//����servlet��ӡ�����ݱ��뷽ʽ����Ҫ��
		Cookie[] cookies = request.getCookies();

		/* ����cookie�Ƿ���
		if(cookies != null){
			for(int i=0;i <cookies.length;i++){
		      Cookie c = cookies[i];
		     if("username".equals(c.getName())){
		    	 System.out.println("cookieΪ:");
		         System.out.println(c.getValue());
		     }}} */
		if (cookies != null && cookies.length > 0) {
		for (Cookie cookie : cookies) {
		// �ҵ���Ҫɾ����Cookie
		if("u_id".equals(cookie.getName())){
		// ����������Ϊ0
		                    cookie.setMaxAge(0);
		// ���Response����Ч
		                    response.addCookie(cookie);
		                }

		            }
		        }
		PrintWriter out=response.getWriter();//ȡ�������
		out.println("<html>");//���������Ҫ����HTML��
		out.println("<body>");
		out.println("<script language='javaScript'> alert('�ɹ��˳�ϵͳ��');</script>");
		out.println("</body>");
		out.println("</html>");
		response.setHeader("refresh", "0;url=Login.jsp");

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
