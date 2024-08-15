package com;

import java.io.IOException;
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
//        CREATING EXAM ID

         String generatedExamID = generateExamID();
        System.out.println("Generated Exam ID: " + generatedExamID);        
        
//        .....................................
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Retrieve the data from the user input fields
        String emailOrUserId = request.getParameter("email"); // renamed for clarity
        String password = request.getParameter("password");
        String userType = request.getParameter("select");

        // SQL query to be used
        String query = null;

        // Determine the correct SQL query based on the selected user type
        if ("students".equals(userType)) {
            query = "SELECT EMAIL, USERID, FULLNAME, [PASSWORD] FROM STUDENTS WHERE (EMAIL = ? OR USERID = ?) AND [PASSWORD] = ?";
        } else if ("faculties".equals(userType)) {
            query = "SELECT EMAIL, USERID, FULLNAME, [PASSWORD] FROM FACULTIES WHERE (EMAIL = ? OR USERID = ?) AND [PASSWORD] = ?";
        }

        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
            conn = DriverManager.getConnection(DB_URL);

            if (query != null) {
                // Prepare the SQL query to check email/userId and password
                stmt = conn.prepareStatement(query);
                stmt.setString(1, emailOrUserId); // For email or userId
                stmt.setString(2, emailOrUserId); // For email or userId
                stmt.setString(3, password); // For password

                // Execute the query
                rs = stmt.executeQuery();

                // Debugging: Print the query and parameters to check values
                System.out.println("Executing query: " + query);
                System.out.println("Parameters: " + emailOrUserId + ", " + password);

                // Check if a matching row is found
                if (rs.next()) {
                    // Retrieve the user's full name from the result set
                    String fullName = rs.getString("FULLNAME");

                    // Store the full name in the session
                    HttpSession session = request.getSession();
                    session.setAttribute("fullName", fullName);
                    session.setAttribute("examId", generatedExamID); // Store the generated exam ID in the session

                    // Redirect to appropriate page based on user type
                    if ("students".equals(userType)) {
                        System.out.println("Redirecting to StudentDashboard.jsp");
                        response.sendRedirect("StudentDashboard.jsp");
                    } else if ("faculties".equals(userType)) {
                        System.out.println("Redirecting to FacultyPanel.jsp");
                        response.sendRedirect("FacultyPanel.jsp");
                    }
                } else {
                    // Redirect back to login page with an error message
                    System.out.println("Invalid email/userId or password");
                    response.sendRedirect("Login.jsp?error=Invalid email/userId or password");
                }
            } else {
                // Redirect back to login page with an error message if query is null
                System.out.println("Invalid user type");
                response.sendRedirect("Login.jsp?error=Invalid user type");
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
        }
    }
}
