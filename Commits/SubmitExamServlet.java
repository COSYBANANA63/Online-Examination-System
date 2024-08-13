package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/SubmitExamServlet")
public class SubmitExamServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SubmitExamServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examTitle = request.getParameter("title");
        String subject = request.getParameter("subject");
        String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");

        boolean correctOption1 = "1".equals(request.getParameter("correctOption1"));
        boolean correctOption2 = "1".equals(request.getParameter("correctOption2"));
        boolean correctOption3 = "1".equals(request.getParameter("correctOption3"));
        boolean correctOption4 = "1".equals(request.getParameter("correctOption4"));

        String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL);
            String sql = "INSERT INTO ExamQuestions (ExamTitle, Subject, QuestionText, Option1, Option2, Option3, Option4, CorrectOption1, CorrectOption2, CorrectOption3, CorrectOption4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            LOGGER.log(Level.INFO, "CorrectOption1: {0}, CorrectOption2: {1}, CorrectOption3: {2}, CorrectOption4: {3}", 
    new Object[]{correctOption1, correctOption2, correctOption3, correctOption4});
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, examTitle);
            stmt.setString(2, subject);
            stmt.setString(3, question);
            stmt.setString(4, option1);
            stmt.setString(5, option2);
            stmt.setString(6, option3);
            stmt.setString(7, option4);
            stmt.setBoolean(8, correctOption1);
            stmt.setBoolean(9, correctOption2);
            stmt.setBoolean(10, correctOption3);
            stmt.setBoolean(11, correctOption4);

            stmt.executeUpdate();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL error: {0}", e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while saving the exam data.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubmitExamServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing resources: {0}", e.getMessage());
            }
        }
    }
}
