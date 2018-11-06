package com.controller;

import com.dao.AdminDAO;
import com.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class AdminController extends HttpServlet {
    AdminDAO adminDAO = new AdminDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String action = req.getParameter("action");
    	if(action != null){
    		if(action.equalsIgnoreCase("logout")){
    			HttpSession session = req.getSession();
    			session.removeAttribute("user");
    			resp.sendRedirect("/QuanLy/login");
    		}
    	}else
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(!username.equals("") && !password.equals("") && username != null && password !=null){
            if (adminDAO.checkLogin(new Admin(username, password))) {
                resp.sendRedirect("view");
                session.setAttribute("user", adminDAO.getAdmin(username));
            }else{
                resp.sendRedirect("login");
            }
        }
    }
}
