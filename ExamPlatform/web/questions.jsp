<%-- 
    Document   : questions
    Created on : Aug 24, 2024, 9:44:38 AM
    Author     : Toby Femi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="questionContainer"></div>
        <button id="nextButton">Next</button>
    </body>
    <script>
        $(document).ready(function(){
            loadNextQuestion();
        });
        
        function loadNextQuestion(){
            $.ajax({
                type: 'POST',
                url: 'GetNextQuestionServlet',
                data: {examId: '<%=session.getAttribute("examId")%>'
            },
                    success: function(response){
                        $('#nextButton').click(function(){
                            loadNextQuestion();
                  ))
        }
    </script>
</html>
