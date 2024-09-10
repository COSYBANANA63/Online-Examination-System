package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/FetchStudentQuestionsServlet")
public class FetchStudentQuestionsServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String examId = request.getParameter("examId");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        JSONArray questionsList = new JSONArray();

        try {
            // Database connection
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
            conn = DriverManager.getConnection(DB_URL);
            
            // Query to fetch questions and options for the given exam ID
            String sql = "SELECT QuestionText, Option1, Option2, Option3, Option4, correctOption1, correctOption2, correctOption3, correctOption4 "
                       + "FROM EXAMQUESTIONS WHERE examId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, examId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                JSONObject question = new JSONObject();
                question.put("question", rs.getString("QuestionText"));
                
                // Add question options
                JSONArray options = new JSONArray();
                options.put(rs.getString("Option1"));
                options.put(rs.getString("Option2"));
                options.put(rs.getString("Option3"));
                options.put(rs.getString("Option4"));
                
                question.put("options", options);
                
                // Store correct options as boolean values in an array
                JSONArray correctOptions = new JSONArray();
                correctOptions.put(rs.getBoolean("correctOption1"));
                correctOptions.put(rs.getBoolean("correctOption2"));
                correctOptions.put(rs.getBoolean("correctOption3"));
                correctOptions.put(rs.getBoolean("correctOption4"));
                
                question.put("correctOptions", correctOptions);

                questionsList.put(question);
            }

            // Send the questions list as the response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(questionsList.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching questions");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
