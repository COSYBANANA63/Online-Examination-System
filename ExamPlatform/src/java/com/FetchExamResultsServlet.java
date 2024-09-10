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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/FetchExamResults")
public class FetchExamResultsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Database connection
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
            conn = DriverManager.getConnection(DB_URL);
            
            // Get the examId from the request
            String examId = request.getParameter("examId");
            
            // Fetch results related to the examId
            String query = "SELECT USERID, USERNAME, score FROM ExamResults WHERE ExamId = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, examId);
            rs = stmt.executeQuery();

            // Prepare the HTML table with proper styling
            StringBuilder htmlTable = new StringBuilder();
            htmlTable.append("<style>")
                     .append("table {width: 100%; border-collapse: collapse; margin: 25px 0; font-size: 18px; text-align: left;}")
                     .append("th, td {padding: 12px; border-bottom: 1px solid #ddd;}")
                     .append("th {background-color: #007BFF; color: white;}")
                     .append("tr:nth-child(even) {background-color: #f2f2f2;}")
                     .append("tr:hover {background-color: #f5f5f5;}")
                     .append("</style>");
            htmlTable.append("<table>");
            htmlTable.append("<tr><th>User ID</th><th>Student Name</th><th>Score</th></tr>");

            while (rs.next()) {
                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(rs.getString("USERID")).append("</td>");
                htmlTable.append("<td>").append(rs.getString("USERNAME")).append("</td>");
                htmlTable.append("<td>").append(rs.getInt("score")).append("</td>");
                htmlTable.append("</tr>");
            }

            htmlTable.append("</table>");
            
            // Write the HTML to the response
            response.getWriter().write(htmlTable.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FetchExamResultsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
