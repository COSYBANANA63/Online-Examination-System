<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Start Exam</title>
    <style>
        .question {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h1>Start Exam</h1>
    <c:forEach items="${questions}" var="question">
        <div class="question">
            <p><strong>Question:</strong> ${question.questionText}</p>
            <p>Option 1: ${question.option1} ${question.correctOption1 ? '(Correct)' : ''}</p>
            <p>Option 2: ${question.option2} ${question.correctOption2 ? '(Correct)' : ''}</p>
            <p>Option 3: ${question.option3} ${question.correctOption3 ? '(Correct)' : ''}</p>
            <p>Option 4: ${question.option4} ${question.correctOption4 ? '(Correct)' : ''}</p>
        </div>
    </c:forEach>
</body>
</html>