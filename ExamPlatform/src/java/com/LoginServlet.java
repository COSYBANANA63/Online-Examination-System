package com;

import java.io.IOException;
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

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            query = "SELECT EMAIL, USERID, [PASSWORD] FROM STUDENTS WHERE (EMAIL = ? OR USERID = ?) AND [PASSWORD] = ?";
        } else if ("faculties".equals(userType)) {
            query = "SELECT EMAIL, USERID, [PASSWORD] FROM FACULTIES WHERE (EMAIL = ? OR USERID = ?) AND [PASSWORD] = ?";
        }

        try {
            // Load the Derby JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
            conn = DriverManager.getConnection(DB_URL);

            if (query != null) {
                // Prepare the SQL query to check email/userId and password
                stmt = conn.prepareStatement(query);

                if ("students".equals(userType)) {
                    stmt.setString(1, emailOrUserId); // For email or userId
                    stmt.setString(2, emailOrUserId); // For email or userId
                    stmt.setString(3, password); // For password
                } else if ("faculties".equals(userType)) {
                    stmt.setString(1, emailOrUserId); // For email
                    stmt.setString(2, password); // For password
                }

                // Execute the query
                rs = stmt.executeQuery();

                // Debugging: Print the query and parameters to check values
                System.out.println("Executing query: " + query);
                System.out.println("Parameters: " + emailOrUserId + ", " + password);

                // Check if a matching row is found
                if (rs.next()) {
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
