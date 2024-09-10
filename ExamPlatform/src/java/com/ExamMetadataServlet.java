package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExamMetadataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve data from request parameters
        String examId = request.getParameter("examId");
        String examTitle = request.getParameter("examTitle");
        int timerValue = Integer.parseInt(request.getParameter("timerValue"));

        // Database connection settings
        String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";

        try {
            // Load JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Establish connection
            Connection conn = DriverManager.getConnection(DB_URL);

            // SQL query to insert data
            String query = "INSERT INTO ExamMetadata (ExamId, ExamTitle, TimerValue) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, examId);
            pstmt.setString(2, examTitle);
            pstmt.setInt(3, timerValue);

            // Execute update
            pstmt.executeUpdate();

            // Close the connection
            conn.close();

            // Send a success response
            response.setContentType("text/plain");
            response.getWriter().write("Exam metadata saved successfully!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        }
    }
}
