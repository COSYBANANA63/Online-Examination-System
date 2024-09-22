package com;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitExamServlet")
public class SubmitExamServlet extends HttpServlet {    
    private static final Logger LOGGER = Logger.getLogger(SubmitExamServlet.class.getName());    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String examId = (String) session.getAttribute("examId");
        
        String examTitle = request.getParameter("title");
        String subject = request.getParameter("subject");
        String timerValue = request.getParameter("timerValue");
        String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
//        String examId = request.getParameter("examId");
        String action = request.getParameter("action");

        boolean correctOption1 = "1".equals(request.getParameter("correctOption1"));
        boolean correctOption2 = "1".equals(request.getParameter("correctOption2"));
        boolean correctOption3 = "1".equals(request.getParameter("correctOption3"));
        boolean correctOption4 = "1".equals(request.getParameter("correctOption4"));

        // Generate the Exam ID
//        String generatedExamID = generateExamID();
//        System.out.println("Generated Exam ID: " + generatedExamID);

        // Store the Exam ID in the session
//        HttpSession session = request.getSession();
//        session.setAttribute("examId", generatedExamID); // Store the generated exam ID in the session

        String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";

        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL);
            String sql = "INSERT INTO EXAMQUESTIONS (EXAMTITLE, SUBJECT, QUESTIONTEXT, OPTION1, OPTION2, OPTION3, OPTION4, CORRECTOPTION1, CORRECTOPTION2, CORRECTOPTION3, CORRECTOPTION4, EXAMID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            LOGGER.log(Level.INFO, "CORRECTOPTION1: {0}, CORRECTOPTION2: {1}, CORRECTOPTION3: {2}, CORRECTOPTION4: {3}", 
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
            stmt.setString(12, examId); // Store the generated Exam ID in the database "subarashi"

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
        private String generateExamID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder examID = new StringBuilder("EXA");

        Random rnd = new Random();
        while (examID.length() < 9) {  // 'EXA' + 6 random characters
            int index = rnd.nextInt(characters.length());
            examID.append(characters.charAt(index));
        }

        return examID.toString();
    }
}









































//.........Easter Eggs...............

//Ryoiki Tenkai - Muriyo no Kyujo
//  //      //     Fukumami Mizushi
//  //      //     Gihen Endonka