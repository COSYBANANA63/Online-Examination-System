package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FetchQuestionsServlet", urlPatterns = {"/FetchQuestionsServlet"})
public class FetchQuestionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String examId = request.getParameter("examId");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
            conn = DriverManager.getConnection(DB_URL);

            String query = "SELECT QuestionText, Option1, Option2, Option3, Option4, correctOption1, correctOption2, correctOption3, correctOption4 FROM EXAMQUESTIONS WHERE examId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, examId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                out.println("<div class='question'>");
                out.println("<p><strong>Question:</strong> " + rs.getString("QuestionText") + "</p>");
                for (int i = 1; i <= 4; i++) {
                    String option = rs.getString("Option" + i);
                    boolean isCorrect = rs.getBoolean("correctOption" + i);
                    out.println("<p>Option " + i + ": " + option + (isCorrect ? " <strong>(Correct)</strong>" : "") + "</p>");
                }
                out.println("</div><hr>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error retrieving questions", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
