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

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!$&*#@;:.,?";
    private static final String USERIDCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int PASSWORD_LENGTH = 8;
    private static final int USER_ID_LENGTH = 10;

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String generateRandomUserString(String role) {
        StringBuilder sb = new StringBuilder();
        
        if ("students".equalsIgnoreCase(role)) {
            sb.append("STU");
        } else if ("faculties".equalsIgnoreCase(role)) {
            sb.append("FAC");
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
        
        for (int i = 0; i < USER_ID_LENGTH - 3; i++) {
            sb.append(USERIDCHAR.charAt(RANDOM.nextInt(USERIDCHAR.length())));
        }
        
        return sb.toString();
    }

    public static String generateRandomPassword() {
        return generateRandomString(PASSWORD_LENGTH);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String phoneNo = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String role = request.getParameter("role");
        String course = request.getParameter("course");
        String gender = request.getParameter("gender");

        String generatedPassword = generateRandomPassword();
        String generatedUserId = generateRandomUserString(role);
        
        String DB_URL = "jdbc:derby://localhost:1527/ExamPortal;user=nbuser;password=nbuser";
        String DB_USERNAME = "nbuser";
        String DB_PASSWORD = "nbuser";
        
        if (detailsExist(email, fullName, phoneNo, generatedUserId, role)) {
            request.setAttribute("messageType", "error");
            request.setAttribute("message", "One or more details already exist. Please use different details.");
            request.getRequestDispatcher("/Registration.jsp").forward(request, response);
            return;
        }
        
        String query = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            if ("students".equalsIgnoreCase(role)) {
                query = "INSERT INTO STUDENTS (email, fullName, phoneNo, dateofbirth, role, course, gender, password, userid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else if ("faculties".equalsIgnoreCase(role)) {
                query = "INSERT INTO FACULTIES (email, fullName, phoneNo, dateofbirth, role, course, gender, password, userid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, fullName);
                stmt.setString(3, phoneNo);
                stmt.setString(4, dob);
                stmt.setString(5, role);
                stmt.setString(6, course);
                stmt.setString(7, gender);
                stmt.setString(8, generatedPassword);
                stmt.setString(9, generatedUserId);
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    request.setAttribute("messageType", "success");
                    request.setAttribute("message", "User has been registered successfully!");
                } else {
                    request.setAttribute("messageType", "error");
                    request.setAttribute("message", "Failed to register user. Please try again.");
                }
            }
        } catch (SQLException e) {
            request.setAttribute("messageType", "error");
            request.setAttribute("message", "An error occurred while registering the user: " + e.getMessage());
            e.printStackTrace();
        }
        
        request.setAttribute("generatedPassword", generatedPassword);
        request.setAttribute("generatedUserId", generatedUserId);
        
        request.getRequestDispatcher("/Registration.jsp").forward(request, response);
    }

    private boolean detailsExist(String email, String fullName, String phoneNo, String userId, String role) {
        String query = "SELECT COUNT(*) FROM " + (role.equalsIgnoreCase("students") ? "STUDENTS" : "FACULTIES") + " WHERE email = ? OR fullName = ? OR phoneNo = ? OR userid = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ExamPortal;user=nbuser;password=nbuser");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, fullName);
            stmt.setString(3, phoneNo);
            stmt.setString(4, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getServletInfo() {
        return "Registration Servlet";
    }
}
