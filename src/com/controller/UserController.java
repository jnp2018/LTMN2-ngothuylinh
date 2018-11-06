package com.controller;

import com.dao.UserDAO;
import com.model.User;
import com.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/view")
public class UserController extends HttpServlet {

    private UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Admin user = (Admin) session.getAttribute("user");
        if(user != null){
            try {
                if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("delete")){
                	userDAO.delete(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("/QuanLy/view");
                }
                ArrayList<User> list = userDAO.search("");
//                System.out.print(list);
                request.setAttribute("piList",list);
                request.getRequestDispatcher("/view.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.sendRedirect("/QuanLy/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("search")){
            String txt_name = request.getParameter("pid");
            if(txt_name.equals("") || txt_name == null){
                response.sendRedirect(request.getContextPath()+"/view");
            }else{
                ArrayList<User> list = userDAO.search(txt_name);

                request.setAttribute("piList",list);
                request.getRequestDispatcher("/view.jsp").forward(request, response);
            }
        }
        if(action.equalsIgnoreCase("insert")){
            //String id = request.getParameter("id");
            String name = request.getParameter("name");
            String department = request.getParameter("department");
            String sdt = request.getParameter("sdt");
//            double price = Double.parseDouble(request.getParameter("price"));

            User user = new User(name, department, sdt);
            UserDAO usdao = new UserDAO();
            try {
                if (usdao.insertNew(user)) {
                    response.sendRedirect("/QuanLy/view");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(action.equalsIgnoreCase("edit")){
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String department = request.getParameter("department");
            String sdt = request.getParameter("sdt");
            User user = new User(Integer.parseInt(id), name, department, sdt);
            try {
                if(userDAO.getUserID(Integer.parseInt(id)) == null){
                    response.sendRedirect("error.jsp");
                }else{
                    if(userDAO.updateOld(user)){
                        response.sendRedirect("/QuanLy/view");
                    }else{
                        response.sendRedirect("error.jsp");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

}

