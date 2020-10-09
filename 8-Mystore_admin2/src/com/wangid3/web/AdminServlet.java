package com.wangid3.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wangid3.domain.Admin;
import com.wangid3.service.AdminService;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收用户名密码
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		System.out.println(name + pwd);
		//调用 登录业务
		AdminService adminService = new AdminService();
		try {
			Admin admin = adminService.login(name,pwd);
			//1.把用户保存到session
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			//2.跳转到后台首页
			//重定向，让浏览器去跳转到指定的位置
			response.sendRedirect(request.getContextPath()+"/admin/admin_index.jsp");
		} catch (Exception e) {
			
			if(e.getMessage().equals("用户名或密码错误")) {
				//跳转回登录页，回显错误信息
				request.setAttribute("err", e.getMessage());
				//转发,服务器内部的转发
				request.getRequestDispatcher("admin/admin_login.jsp").forward(request, response);
			}else {
				e.printStackTrace();
			}
		}
	}

}
