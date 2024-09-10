package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubmitStudentExamServlet")
public class SubmitStudentExamServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String userId = request.getParameter("userId");
        String examId = request.getParameter("examId");
        String scoreStr = request.getParameter("score");

        // Logging to check values
        System.out.println("Full Name: " + userName);
        System.out.println("User ID: " + userId);
        System.out.println("Exam ID: " + examId);
        System.out.println("Score: " + scoreStr);

        // Parse score and handle potential exceptions
        double score;
        try {
            score = Double.parseDouble(scoreStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid score value.");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Database connection
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
            conn = DriverManager.getConnection(DB_URL);

            // Insert the result into the ExamResults table
            String sql = "INSERT INTO ExamResults (UserID, UserName, ExamID, Score) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, userName);
            stmt.setString(3, examId);
            stmt.setDouble(4, score);

            // Execute the SQL statement
            stmt.executeUpdate();
            System.out.println("Exam results saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
