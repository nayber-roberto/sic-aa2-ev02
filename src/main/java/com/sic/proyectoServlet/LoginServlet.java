package com.sic.proyectoServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("email");
            String password = request.getParameter("password");

            out.println("<html><head><title>Result</title></head><body>");
            out.println("<h2>Username: " + email + "</h2>");
            out.println("<h2>Password: " + password + "</h2>");
            out.println("</body></html>");
        }
        */

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }

        String url = "jdbc:mysql://localhost:3306/sicdb";

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, "root", "Nayber932911*");
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM USUARIO WHERE correo_electronico='"+email+"' AND contrasena='"+password+"'");
            if(rs.next()) {
                request.getSession().setAttribute("correo_electronico",email);
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("index.html");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
