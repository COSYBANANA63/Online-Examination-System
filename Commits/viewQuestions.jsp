<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<html>
<head>
    <title>View Questions</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <h2>Exam Questions</h2>

    <div id="questions-container">
        <% 
            HttpSession session = request.getSession();
            String examID = (String) session.getAttribute("examID");

            if (examID != null) {
                Connection conn = null; 
                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {
                    String sql = "SELECT * FROM EXAMQUESTIONS WHERE EXAMID = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, examID);
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        int questionID = rs.getInt("QuestionID");
                        String questionText = rs.getString("QuestionText");
                        String option1 = rs.getString("Option1");
                        String option2 = rs.getString("Option2");
                        String option3 = rs.getString("Option3");
                        String option4 = rs.getString("Option4");
                        boolean correctOption1 = rs.getBoolean("CorrectOption1");
                        boolean correctOption2 = rs.getBoolean("CorrectOption2");
                        boolean correctOption3 = rs.getBoolean("CorrectOption3");
                        boolean correctOption4 = rs.getBoolean("CorrectOption4");
        %>
                        <div class="question" id="question-<%= questionID %>">
                            <h3>Question: <%= questionText %></h3>
                            <p>Option 1: <%= option1 %> <input type="checkbox" <%= correctOption1 ? "checked" : "" %> disabled></p>
                            <p>Option 2: <%= option2 %> <input type="checkbox" <%= correctOption2 ? "checked" : "" %> disabled></p>
                            <p>Option 3: <%= option3 %> <input type="checkbox" <%= correctOption3 ? "checked" : "" %> disabled></p>
                            <p>Option 4: <%= option4 %> <input type="checkbox" <%= correctOption4 ? "checked" : "" %> disabled></p>
                            <button onclick="deleteQuestion('<%= questionID %>')">Delete</button>
                        </div>
        <%          }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
        %>
            <p>No questions available for this exam.</p>
        <% } %>
    </div>

    <script>
        function deleteQuestion(questionID) {
            $.ajax({
                type: 'POST',
                url: 'DeleteQuestionServlet',
                data: { questionID: questionID },
                success: function(response) {
                    if (response.status === "success") {
                        $('#question-' + questionID).remove();
                    } else {
                        alert('Failed to delete the question.');
                    }
                },
                error: function() {
                    alert('Failed to delete the question.');
                }
            });
        }

//        function loadQuestions() {
//            $.ajax({
//                url: 'ViewQuestions.jsp',
//                success: function(data) {
//                    $('#questions-container').html(data);
//                }
//            });
//        }
    </script>
</body>
</html>
