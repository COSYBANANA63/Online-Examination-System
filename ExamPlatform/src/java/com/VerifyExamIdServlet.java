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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Toby Femi
 */
@WebServlet(name = "VerifyExamIdServlet", urlPatterns = {"/VerifyExamIdServlet"})
public class VerifyExamIdServlet extends HttpServlet {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String examId = request.getParameter("examId");
        List<Question> questions = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
            conn = DriverManager.getConnection(DB_URL);

            String query = "SELECT * FROM EXAMQUESTIONS WHERE EXAMID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, examId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("ID"));
                question.setQuestionText(rs.getString("QUESTIONTEXT"));
                question.setOption1(rs.getString("OPTION1"));
                question.setOption2(rs.getString("OPTION2"));
                question.setOption3(rs.getString("OPTION3"));
                question.setOption4(rs.getString("OPTION4"));
                question.setCorrectOption1(rs.getBoolean("CORRECTOPTION1"));
                question.setCorrectOption2(rs.getBoolean("CORRECTOPTION2"));
                question.setCorrectOption3(rs.getBoolean("CORRECTOPTION3"));
                question.setCorrectOption4(rs.getBoolean("CORRECTOPTION4"));
                questions.add(question);
            }

            // Process student's answers
            int score = 0;
            for (Question question : questions) {
                String studentAnswer = request.getParameter("question" + question.getId());
                if (studentAnswer != null) {
                    if (studentAnswer.equals("option1") && question.isCorrectOption1()) {
                        score++;
                    } else if (studentAnswer.equals("option2") && question.isCorrectOption2()) {
                        score++;
                    } else if (studentAnswer.equals("option3") && question.isCorrectOption3()) {
                        score++;
                    } else if (studentAnswer.equals("option4") && question.isCorrectOption4()) {
                        score++;
                    }
                }
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Your score is: " + score + " out of " + questions.size());

        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error retrieving examId", e);
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

class Question {
    private int id;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private boolean correctOption1;
    private boolean correctOption2;
    private boolean correctOption3;
    private boolean correctOption4;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public boolean isCorrectOption1() {
        return correctOption1;
    }

    public void setCorrectOption1(boolean correctOption1) {
        this.correctOption1 = correctOption1;
    }

      public boolean isCorrectOption2() {
        return correctOption2;
    }

    public void setCorrectOption2(boolean correctOption2) {
        this.correctOption2 = correctOption2;
    }

    public boolean isCorrectOption3() {
        return correctOption3;
    }

    public void setCorrectOption3(boolean correctOption3) {
        this.correctOption3 = correctOption3;
    }

    public boolean isCorrectOption4() {
        return correctOption4;
    }

    public void setCorrectOption4(boolean correctOption4) {
        this.correctOption4 = correctOption4;
    }
}