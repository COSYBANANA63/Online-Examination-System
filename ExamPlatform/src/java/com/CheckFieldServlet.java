package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckFieldServlet")
public class CheckFieldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:derby://localhost:1527/ExamPortal";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        String value = request.getParameter("value");
        String field = request.getParameter("field"); // "email", "phone", "fullname"
        String role = request.getParameter("role"); // "students" or "faculties"

        if (value == null || field == null || role == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
            return;
        }

        boolean exists = false;
        String message = "";
        String table = role.equals("students") ? "STUDENTS" : "FACULTIES";
        String column = "";

        switch (field) {
            case "email":
                column = "email";
                break;
            case "phone":
                column = "phoneNo";
                break;
            case "fullname":
                column = "fullName";
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid field");
                return;
        }

        String query = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, value);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    exists = count > 0;
                    message = exists ? "This " + field + " is already registered." : field + " is available.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            return;
        }

        out.print("{\"exists\":" + exists + ",\"message\":\"" + message + "\"}");
        out.flush();
    }
}
