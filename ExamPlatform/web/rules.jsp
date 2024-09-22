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
        <p style="float:right; font-family: monospace; font-weight: bold; font-size: 2.5em; border: 1px solid #ffffff; border-radius: 3px; background-color: #ffffff" id="timer">Time: 60:00</p>

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
        <div style="position: fixed; bottom: 0; right: 0;">
             <button onclick="finishExam()">Finish Exam</button>
             <button onclick="showSummary()">Summary</button>
        </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
//        Disable navigation using JavaScript
history.pushState(null, null, location.href);
window.onpopstate = function () {
  history.go(1);
};



    var questionsList = [];
    var currentIndex = 0;
    var userAnswers = [];
    var correctAnswers = 0;
    var timerInterval;

    function displayQuestion() {
        if (currentIndex >= 0 && currentIndex < questionsList.length) {
            var question = questionsList[currentIndex];
            var questionHtml = "<div class='question'>";
            questionHtml += "<p><strong>Question " + (currentIndex + 1) + ":</strong> " +question.question + "</p>";

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
                questionHtml += "<button onclick='finishExam(true)'>Finish Exam</button>";
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

    function showSummary() {
        var summaryHtml = "<div class='summary-container' style='position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5);'>";
        summaryHtml += "<div style='background-color: white; padding: 20px; border-radius: 5px;'>";
        summaryHtml += "<h2>Question Summary</h2>";
        for (var i = 0; i < questionsList.length; i++) {
          summaryHtml += "<button onclick='navigateToQuestion(" + i + ")'>Question " + (i + 1) + "</button><br>";
        }
        summaryHtml += "</div>";
        $('body').append(summaryHtml);
    }

    function navigateToQuestion(index) {
        currentIndex = index;
        displayQuestion();
        // Remove the summary container
        $('.summary-container').remove();
    }

    function startTimer() {
    var timeRemaining = 60; // 60 minutes in seconds
    timerInterval = setInterval(function() {
        timeRemaining--;
        var minutes = Math.floor(timeRemaining / 60);
        var seconds = timeRemaining % 60;
        $('#timer').text('Time: ' + minutes + ':' + (seconds < 10 ? '0' : '') + seconds);
        if (timeRemaining <= 0) {
            clearInterval(timerInterval);
            finishExam(false); // Submit the exam automatically without confirmation
        }
    }, 1000);
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

    function finishExam(manualSubmit = true) {
    // Capture the current question's selected answer before finishing
    var selectedOption = $('input[name="options"]:checked').val();
    userAnswers[currentIndex] = selectedOption;

    if (selectedOption) {
        checkAnswer(selectedOption);
    }

    // Only prompt the user to confirm submission if this is a manual submission
    if (manualSubmit) {
        var confirmSubmit = confirm("Are you sure you want to submit the exam?");
        if (!confirmSubmit) {
            // If the user cancels, return to the exam
            console.log("Exam submission cancelled.");
            return;
        }
    }

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
            // Redirect to thank you page after successful submission
            location.replace("thankYou.jsp"); // Replace the current page in the browser's history
        },
        error: function(xhr, status, error) {
            alert('Error submitting exam: ' + error);
        }
    });
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
            startTimer();
            fetchQuestions();
        });
    });
    </script>
</body>
</html>
