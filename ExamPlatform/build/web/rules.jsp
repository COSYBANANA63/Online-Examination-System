<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Rules Page</title>

    <!-- MathJax Script for Rendering LaTeX -->
    <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
    <script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>

    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: sans-serif; }
        body { font-family: Arial, sans-serif; margin: 0; padding: 10px; background-color: #f4f4f4; }
        header { border: 2px solid darkslategrey; background-color: #2C3E50; }
        #logo { height: 80px; }
        input { border: 2px solid #2C3E50; border-radius: 5px; padding: 20px; font-size: 20px; outline: none; }
        label { font-size: 20px; }
        button { padding: 10px; border-radius: 2px; width: 100px; color: white; background: #2C3E50; }
        .option-container {
            display: flex;
            align-items: center;
            margin-bottom: 8px;
        }
        .option-label {
            margin-left: 5px;
        }
        .rules-container { padding: 20px; background-color: white; border-radius: 5px; margin-top: 20px; }
        .rules-list { list-style-type: decimal; padding-left: 20px; }
        .question-container { padding: 20px; background-color: #ffffff; border-radius: 5px; margin-top: 20px; }
    </style>
</head>
<body>
    <header>
        <img id="logo" src="SEMS.png" alt="Logo">
    </header>

    <div class="rules-container">
        <h2>Exam Rules</h2>
        <input type="text" id="examId" name="examId" value="${sessionScope.examId}" required>
        <input type="text" id="fullName" name="userName" value="${sessionScope.fullName}" required>
        <input type="text" id="userId" name="userId" value="${sessionScope.userId}" required>

        <ul class="rules-list">
            <li>Rule 1: Read all questions carefully.</li>
            <li>Rule 2: Do not use any unauthorized materials.</li>
            <li>Rule 3: Manage your time effectively.</li>
            <li>Rule 4: Submit your answers before the deadline.</li>
            <li>Rule 5: Any form of cheating will result in disqualification.</li>
        </ul>
        <br>
        <button id="startExamBtn" type="button">Start Exam</button>
    </div>

    <div class="question-container" id="questionContainer" style="display: none;">
        <!-- Questions will be loaded here -->
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
    var questionsList = [];
    var currentIndex = 0;
    var userAnswers = [];
    var correctAnswers = 0;

    function displayQuestion() {
        if (currentIndex >= 0 && currentIndex < questionsList.length) {
            var question = questionsList[currentIndex];
            var questionHtml = "<div class='question'>";
            questionHtml += "<p><strong>Question:</strong> " + question.question + "</p>";

            for (var i = 0; i < question.options.length; i++) {
                var isChecked = userAnswers[currentIndex] === question.options[i];
                questionHtml += "<div class='option-container'>";
                questionHtml += "<input type='radio' id='option-" + i + "' name='options' value='" + question.options[i] + "'" + (isChecked ? " checked" : "") + ">";
                questionHtml += "<label class='option-label' for='option-" + i + "'>" + question.options[i] + "</label>";
                questionHtml += "</div>";
            }
            
            questionHtml += "</div><hr>";

            // Navigation buttons
            if (currentIndex > 0) {
                questionHtml += "<button onclick='navigate(-1)'>Previous</button>";
            }
            if (currentIndex < questionsList.length - 1) {
                questionHtml += "<button onclick='navigate(1)'>Next</button>";
            } else {
                questionHtml += "<button onclick='finishExam()'>Finish Exam</button>";
            }

            $('#questionContainer').html(questionHtml).show();
            $('.rules-container').hide();

            // Reprocess MathJax after content is added
            if (typeof MathJax !== 'undefined') {
                MathJax.typesetPromise().then(() => {
                    console.log("MathJax rendering completed.");
                });
            }
        }
    }

    function navigate(direction) {
        var selectedOption = $('input[name="options"]:checked').val();
        userAnswers[currentIndex] = selectedOption;
        
        if (selectedOption) {
            checkAnswer(selectedOption);
        }
        
        currentIndex += direction;
        displayQuestion();
    }

    function checkAnswer(selectedOption) {
    var correctOptions = questionsList[currentIndex].correctOptions;
    var options = questionsList[currentIndex].options;

    // Check if the selected option is correct
    for (var i = 0; i < correctOptions.length; i++) {
        if (correctOptions[i] === true && options[i] === selectedOption) {
            correctAnswers++;
            break; // Stop checking once the correct answer is found
        }
    }
}

    function finishExam() {
    // Capture the current question's selected answer before finishing
    var selectedOption = $('input[name="options"]:checked').val();
    userAnswers[currentIndex] = selectedOption;

    if (selectedOption) {
        checkAnswer(selectedOption);
    }

    // Prompt the user to confirm submission
    var confirmSubmit = confirm("Are you sure you want to submit the exam?");
    if (confirmSubmit) {
        // Proceed with submission
        var totalQuestions = questionsList.length;
        var score = (correctAnswers / totalQuestions) * 100;
        console.log("Total Questions: " + totalQuestions);
        console.log("Correct Answers: " + correctAnswers);
        console.log("Score: " + score);

        var userName = $('#fullName').val();
        var userId = $('#userId').val();
        var examId = $('#examId').val();

        $.ajax({
            url: 'SubmitStudentExamServlet',
            type: 'POST',
            data: {
                userName: userName,
                userId: userId,
                examId: examId,
                score: score
            },
            success: function(response) {
                // Alert success message
                alert("Exam submitted successfully! You scored: " + score + "%");

                // Redirect to another page or exit the exam page
                 location.replace("thankYou.jsp"); // Replace the current page in the browser's history
            },
            error: function(xhr, status, error) {
                alert('Error submitting exam: ' + error);
            }
        });
    } else {
        // If the user cancels, return to the exam
        console.log("Exam submission cancelled.");
    }
}


    function fetchQuestions() {
        var examId = $('#examId').val();

        $.ajax({
            url: 'FetchStudentQuestionsServlet',
            type: 'GET',
            data: { examId: examId },
            success: function(response) {
                questionsList = response;
                currentIndex = 0;
                displayQuestion();
            },
            error: function(xhr, status, error) {
                alert('Error fetching questions: ' + error);
            }
        });
    }

    $(document).ready(function() {
        $('#startExamBtn').on('click', function() {
            correctAnswers = 0; // Reset score
            currentIndex = 0;   // Reset question index
            fetchQuestions();
        });
    });
    </script>
</body>
</html>
