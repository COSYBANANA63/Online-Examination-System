<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<html>
<head>
    <title>View Results</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- MathJax Script for Rendering LaTeX -->
    <!--<script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>-->
    <!--<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>-->
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
            background-color: #f4f4f4;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .exam-id-input {
            margin-bottom: 20px;
        }

        .exam-id-input label {
            font-weight: bold;
            margin-right: 10px;
        }

        .exam-id-input input {
            padding: 8px;
            margin-right: 10px;
            width: 200px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .exam-id-input button {
            padding: 8px 15px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .exam-id-input button:hover {
            background-color: #0056b3;
        }

        #questionsContainer {
            margin-top: 20px;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        .question-block {
            margin-bottom: 30px;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        .question-block:last-child {
            border-bottom: none;
        }

        .question-text {
            font-weight: bold;
            font-size: 1.1em;
            margin-bottom: 10px;
        }

        .option-text {
            margin-left: 20px;
            margin-bottom: 5px;
        }

        .correct-answer {
            color: #28a745;
            font-weight: bold;
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #34495E;
            color: #fff;
            padding: 10px 20px;
        }
    </style>
</head>
<body>
    <header>
            <div class="header-left">
                <h1>Exam Panel</h1>
            </div>
<!--            <div class="header-right">
                <button class="cancel-btn">Cancel</button>
                <button class="edit-btn" id="edit_btn">Edit</button>
                <button class="publish-btn" name="action" value="publish" onclick="publishExam()">Publish</button>
            </div>-->
        </header>
    <h2>Exam Results</h2>

    <div class="exam-id-input">
        <label for="examId">Enter Exam ID:</label>
        <input type="text" oninput="convertToUppercase(this)" id="examId" name="examId" placeholder="EXA123456" required>
        <button id="fetchResultsBtn">Fetch Questions</button>
    </div>

    <div id="questionsContainer">
        <!-- Questions will be displayed here -->
    </div>

    <script>
        $(document).ready(function() {
            $('#fetchResultsBtn').on('click', function() {
                var examId = $('#examId').val();

                $.ajax({
                    url: 'FetchExamResults',
                    type: 'GET',
                    data: { examId: examId },
                    success: function(response) {
                        $('#questionsContainer').html(response);
                    },
                    error: function(xhr, status, error) {
                        alert('Error fetching questions: ' + error);
                    }
                });
            });
        });

        function convertToUppercase(element) {
            element.value = element.value.toUpperCase();
        }
    </script>
</body>
</html>