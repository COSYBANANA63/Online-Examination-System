import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet("/StartExamServlet")
public class StartExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examId = request.getParameter("examId");
        List<Question> questions = new ArrayList<>();

        String DB_URL = "jdbc:sqlserver://THE_NOOB_BANANA;Database=ExamPortal;encrypt=false;integratedSecurity=true";
        
        if (examId != null) {
            try (Connection con = DriverManager.getConnection(DB_URL)) {
                String query = "SELECT QuestionText, Option1, Option2, Option3, Option4, correctOption1, correctOption2, correctOption3, correctOption4 FROM EXAMQUESTIONS WHERE examId = ?";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, examId);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        Question question = new Question();
                        question.setQuestionText(rs.getString("QuestionText"));
                        question.setOption1(rs.getString("Option1"));
                        question.setOption2(rs.getString("Option2"));
                        question.setOption3(rs.getString("Option3"));
                        question.setOption4(rs.getString("Option4"));
                        question.setCorrectOption1(rs.getBoolean("correctOption1"));
                        question.setCorrectOption2(rs.getBoolean("correctOption2"));
                        question.setCorrectOption3(rs.getBoolean("correctOption3"));
                        question.setCorrectOption4(rs.getBoolean("correctOption4"));
                        questions.add(question);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Set the questions to be displayed on the JSP
        request.setAttribute("questions", questions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("startExam.jsp");
        dispatcher.forward(request, response);
    }

    public class Question {
        private String questionText;
        private String option1;
        private String option2;
        private String option3;
        private String option4;
        private boolean correctOption1;
        private boolean correctOption2;
        private boolean correctOption3;
        private boolean correctOption4;

        // Getters and setters for all attributes
        public String getQuestionText() { return questionText; }
        public void setQuestionText(String questionText) { this.questionText = questionText; }
        public String getOption1() { return option1; }
        public void setOption1(String option1) { this.option1 = option1; }
        public String getOption2() { return option2; }
        public void setOption2(String option2) { this.option2 = option2; }
        public String getOption3() { return option3; }
        public void setOption3(String option3) { this.option3 = option3; }
        public String getOption4() { return option4; }
        public void setOption4(String option4) { this.option4 = option4; }
        public boolean isCorrectOption1() { return correctOption1; }
        public void setCorrectOption1(boolean correctOption1) { this.correctOption1 = correctOption1; }
        public boolean isCorrectOption2() { return correctOption2; }
        public void setCorrectOption2(boolean correctOption2) { this.correctOption2 = correctOption2; }
        public boolean isCorrectOption3() { return correctOption3; }
        public void setCorrectOption3(boolean correctOption3) { this.correctOption3 = correctOption3; }
        public boolean isCorrectOption4() { return correctOption4; }
        public void setCorrectOption4(boolean correctOption4) { this.correctOption4 = correctOption4; }
    }
}