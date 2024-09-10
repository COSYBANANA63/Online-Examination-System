package com;

import java.io.IOException;
import static java.lang.System.out;
import java.security.SecureRandom;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final String EXAMIDCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static int EXAM_ID_LENGTH = 9;
    
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        sb.append("EXA"); // Prefix for the exam ID
        for (int i = 0; i < length; i++) {
            sb.append(EXAMIDCHAR.charAt(RANDOM.nextInt(EXAMIDCHAR.length())));
        }
        return sb.toString();
    }
    
    public static String generateExamID() {
        return generateRandomString(EXAM_ID_LENGTH);
    }
    
   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String generatedExamID = generateExamID();
    System.out.println("Generated Exam ID: " + generatedExamID); 
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    // Retrieve the data from the user input fields
    String emailOrUserId = request.getParameter("email"); // renamed for clarity
    String password = request.getParameter("password");

    try {
        // Load the SQL Server JDBC driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
        conn = DriverManager.getConnection(DB_URL);

        // Prepare the SQL queries to check email/userId and password in both students and faculties tables
        String studentQuery = "SELECT EMAIL, USERID, FULLNAME, [PASSWORD] FROM STUDENTS WHERE (EMAIL = ? OR USERID = ?) AND [PASSWORD] = ?";
        String facultyQuery = "SELECT EMAIL, USERID, FULLNAME, [PASSWORD] FROM FACULTIES WHERE (EMAIL = ? OR USERID = ?) AND [PASSWORD] = ?";

        String role = request.getParameter("role");
        HttpSession session = request.getSession();
        if ("students".equalsIgnoreCase(role)) {
            session.setAttribute("student", role);
            
        } else if ("faculties".equalsIgnoreCase(role)) {
            session.setAttribute("faculty", role);
        } else if ("faculties".equalsIgnoreCase(role) && emailOrUserId.equals("admin") && password.equals("admin")){
            response.sendRedirect("AdminPanel.jsp");
        } 
        else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
        // Execute the queries
        stmt = conn.prepareStatement(studentQuery);
        stmt.setString(1, emailOrUserId); // For email or userId
        stmt.setString(2, emailOrUserId); // For email or userId
        stmt.setString(3, password); // For password
        rs = stmt.executeQuery();

        // Check if a matching row is found in students table
        if (rs.next()) {
            // Retrieve the user's full name from the result set
            String fullName = rs.getString("FULLNAME");
            String userId = rs.getString("USERID");

            // Store the full name in the session
            session.setAttribute("fullName", fullName);
            session.setAttribute("examId", generatedExamID);
            session.setAttribute("userId",userId);

            // Redirect to StudentDashboard.jsp
            response.sendRedirect("StudentDashboard.jsp");
        } else {
            // If not found in students table, check in faculties table
            stmt = conn.prepareStatement(facultyQuery);
            stmt.setString(1, emailOrUserId); // For email or userId
            stmt.setString(2, emailOrUserId); // For email or userId
            stmt.setString(3, password); // For password
            rs = stmt.executeQuery();

            // Check if a matching row is found in faculties table
            if (rs.next()) {
                // Retrieve the user's full name from the result set
                String fullName = rs.getString("FULLNAME");

                // Store the full name in the session
                session.setAttribute("fullName", fullName);
                session.setAttribute("examId", generatedExamID); // Store the generated exam ID in the session

                // Redirect to FacultyPanel.jsp
                response.sendRedirect("FacultyPanel.jsp");
            } else {
                // If not found in both tables, redirect back to login page with an error message
                request.setAttribute("errorMessage", "Invalid email/userId or password");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        throw new ServletException("Login failed", e);
    } finally {
        // Close the resources
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.flush();
    }
}
}