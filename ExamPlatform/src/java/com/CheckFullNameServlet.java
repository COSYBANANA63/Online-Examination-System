package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/CheckFullNameServlet")
public class CheckFullNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        String fullName = request.getParameter("value");

        boolean exists = false;
        String message = "";

        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ExamPortal", "user", "password");
            String query = "SELECT * FROM Users WHERE fullName = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, fullName);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                exists = true;
                message = "This full name is already registered.";
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.print("{\"exists\":" + exists + ",\"message\":\"" + message + "\"}");
        out.flush();
    }
}
