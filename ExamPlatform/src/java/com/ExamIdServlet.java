package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ExamIdServlet")
public class ExamIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve the exam ID from the request
        String examId = request.getParameter("examId");

        // Store the exam ID in the session
        HttpSession session = request.getSession();
        session.setAttribute("examId", examId);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL);

            // Check if the exam ID exists
            String examQuery = "SELECT COUNT(*) As total FROM EXAMQUESTIONS WHERE EXAMID = ?";
            stmt = conn.prepareStatement(examQuery);
            stmt.setString(1, examId);
            rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt("total");

            if (count == 0) {
                // Exam ID does not exist
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"message\":\"Exam ID not found.\"}");
                return;
            }

            // Check if the student has already submitted the exam
            String userId = (String) session.getAttribute("userId");
            String submissionQuery = "SELECT COUNT(*) As total FROM ExamResults WHERE userId = ? AND examId = ?";
            stmt = conn.prepareStatement(submissionQuery);
            stmt.setString(1, userId);
            stmt.setString(2, examId);
            rs = stmt.executeQuery();
            rs.next();
            int submissionCount = rs.getInt("total");

            if (submissionCount > 0) {
                // Student has already submitted the exam
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"message\":\"You have already submitted this exam.\"}");
                return;
            }

            // If everything is fine, proceed with the current operation
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"success\"}");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamIdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExamIdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamIdServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
