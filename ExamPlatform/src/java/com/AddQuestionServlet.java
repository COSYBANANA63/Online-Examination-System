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

@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subject = request.getParameter("subject");
        String difficulty = request.getParameter("difficulty");
        String dueDate = request.getParameter("duedate");
        String cost = request.getParameter("cost");
        String currency = request.getParameter("currency");

        String[] questions = request.getParameterValues("question");
        String[] answers = request.getParameterValues("answer");

        String DB_URL = "jdbc:derby://localhost:1527/ExamPortal;user=nbuser;password=nbuser";
        String DB_USERNAME = "nbuser";
        String DB_PASSWORD = "nbuser";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            for (int i = 0; i < questions.length; i++) {
                String question = questions[i];

                String sqlQuestion = "INSERT INTO questions (subject, difficulty, due_date, cost, currency, question_text) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement psQuestion = conn.prepareStatement(sqlQuestion, PreparedStatement.RETURN_GENERATED_KEYS);
                psQuestion.setString(1, subject);
                psQuestion.setString(2, difficulty);
                psQuestion.setString(3, dueDate);
                psQuestion.setString(4, cost);
                psQuestion.setString(5, currency);
                psQuestion.setString(6, question);
                psQuestion.executeUpdate();

                // Get the generated question ID
                ResultSet rs = psQuestion.getGeneratedKeys();
                rs.next();
                int questionId = rs.getInt(1);

                // Insert the answers
                for (String answer : answers) {
                    String sqlAnswer = "INSERT INTO answers (question_id, answer_text) VALUES (?, ?)";
                    PreparedStatement psAnswer = conn.prepareStatement(sqlAnswer);
                    psAnswer.setInt(1, questionId);
                    psAnswer.setString(2, answer);
                    psAnswer.executeUpdate();
                }
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("success.jsp");
    }
}
