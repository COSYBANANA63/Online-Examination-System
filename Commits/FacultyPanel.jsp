<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Faculty Panel</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://cdn.ckeditor.com/4.16.0/standard-all/ckeditor.js"></script>
    <script src="https://ckeditor.com/docs/ckeditor4/4.16.0/examples/assets/plugins/mathjax/plugin.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
    function initializeCKEditors() {
        var editorConfig = {
            extraPlugins: 'mathjax',
            mathJaxLib: 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?config=TeX-MML-AM_CHTML'
        };

        function suppressNotification(editor) {
            editor.on('instanceReady', function () {
                editor.showNotification = function (message, type) {
                    if (message.id !== 'editor-is-not-secure') {
                        return this.showNotification.original.call(this, message, type);
                    }
                };
                editor.showNotification.original = editor.showNotification;
            });
        }

        // Initialize CKEditors
        var questionEditor = CKEDITOR.replace('question', editorConfig);
        suppressNotification(questionEditor);

        var option1Editor = CKEDITOR.replace('option1', editorConfig);
        suppressNotification(option1Editor);

        var option2Editor = CKEDITOR.replace('option2', editorConfig);
        suppressNotification(option2Editor);

        var option3Editor = CKEDITOR.replace('option3', editorConfig);
        suppressNotification(option3Editor);

        var option4Editor = CKEDITOR.replace('option4', editorConfig);
        suppressNotification(option4Editor);
    }

    function sendDataToServlet() {
    // Collect the exam title
    var examTitle = $('#title').val();

    $.ajax({
        type: 'POST',
        url: 'SubmitExamServlet',
        data: {
            title: examTitle,
            subject: $('#subject').val(),
            question: CKEDITOR.instances.question.getData(),
            option1: CKEDITOR.instances.option1.getData(),
            option2: CKEDITOR.instances.option2.getData(),
            option3: CKEDITOR.instances.option3.getData(),
            option4: CKEDITOR.instances.option4.getData(),
            correctOption1: $("input[name='correctOption1']").is(':checked') ? "1" : "0",
            correctOption2: $("input[name='correctOption2']").is(':checked') ? "1" : "0",
            correctOption3: $("input[name='correctOption3']").is(':checked') ? "1" : "0",
            correctOption4: $("input[name='correctOption4']").is(':checked') ? "1" : "0"
        },
        success: function(response) {
            console.log('Data saved successfully!');
        },
        error: function() {
            console.log('An error occurred while saving the data.');
        }
    });
}


    document.addEventListener('DOMContentLoaded', function() {
        initializeCKEditors();

        // Bind the AJAX submission to the publish button
        document.querySelector('.publish-btn').addEventListener('click', function(event) {
            event.preventDefault();
            sendDataToServlet();
        });
    });
    </script>
    <style>
         body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 10px;
            background-color: #f4f4f4;
        }

        .sidebar {
            width: 40px;
            height: 100vh;
            background-color: #2C3E50;
            color: #fff;
            position: fixed;
            top: 0;
            left: 0;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
            transition: width 0.3s;
            overflow: hidden;
        }

        .sidebar:hover {
            width: 200px;
        }

        .sidebar .logo {
            font-size: 24px;
            text-align: center;
            margin-bottom: 30px;
            font-weight: bold;
            display: none;
        }

        .sidebar:hover .logo {
            display: block;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar ul li {
            margin: 20px 0;
        }

        .sidebar ul li a {
            color: #fff;
            text-decoration: none;
            font-size: 18px;
            display: flex;
            align-items: center;
            padding: 10px;
            border-radius: 4px;
            position: relative;
        }

        .sidebar ul li a i {
            margin-right: 10px;
        }

        .sidebar ul li a .text {
            display: inline-block;
            opacity: 0;
            transition: opacity 0.3s;
        }

        .sidebar:hover ul li a .text {
            opacity: 1;
        }

        .sidebar ul li a:hover {
            background-color: #34495E;
        }

        .main-content {
            margin-left: 60px;
            padding: 20px;
            transition: margin-left 0.3s;
        }

        .sidebar:hover ~ .main-content {
            margin-left: 220px;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #34495E;
            color: #fff;
            padding: 10px 20px;
        }

        .header-left h1 {
            margin: 0;
        }

        .header-right {
            display: flex;
            gap: 10px;
        }

        .cancel-btn, .save-btn, .publish-btn {
            background-color: #2980B9;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
        }

        .stats-bar {
            display: flex;
            justify-content: space-around;
            background-color: #ECF0F1;
            padding: 20px 0;
            margin: 20px 0;
        }

        .stat {
            text-align: center;
        }

        .stat span {
            display: block;
            font-size: 24px;
            font-weight: bold;
        }

        .exam-form {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }

        .form-group label {
            margin-bottom: 5px;
        }

        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            height: 100px;
        }

        .form-group select, .form-group input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="logo"><i class="fas fa-user-circle"></i></div>
        <ul>
            <li><a href="#"><i class="fas fa-home"></i><span class="text">Home</span></a></li>
            <li><a href="#"><i class="fas fa-user"></i><span class="text">Profile</span></a></li>
            <li><a href="#"><i class="fas fa-cog"></i><span class="text">Settings</span></a></li>
            <li><a href="#"><i class="fas fa-sign-out-alt"></i><span class="text">Logout</span></a></li>
        </ul>
    </div>

    <div class="main-content">
        <header>
            <div class="header-left">
                <h1>Create Exam</h1>
            </div>
            <div class="header-right">
                <button class="cancel-btn">Cancel</button>
                <button class="save-btn">Save Draft</button>
                <button class="publish-btn">Publish</button>
            </div>
        </header>

        <form class="exam-form">
            <div class="form-group">
                <label for="title">Exam Title:</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="subject">Subject:</label>
                <select id="subject" name="subject" required>
                    <option value="math">Mathematics</option>
                    <option value="science">Science</option>
                    <option value="english">English</option>
                </select>
            </div>

            <div class="form-group">
                <label for="question">Question:</label>
                <textarea id="question" name="question"></textarea>
            </div>
            <div class="form-group">
                <label for="option1">Option 1:</label>
                <textarea id="option1" name="option1"></textarea>
                <label><input type="checkbox" name="correctOption1" value="1"> Correct Answer</label>
            </div>
            <div class="form-group">
                <label for="option2">Option 2:</label>
                <textarea id="option2" name="option2"></textarea>
                <label><input type="checkbox" name="correctOption2" value="2"> Correct Answer</label>
            </div>
            <div class="form-group">
                <label for="option3">Option 3:</label>
                <textarea id="option3" name="option3"></textarea>
                <label><input type="checkbox" name="correctOption3" value="3"> Correct Answer</label>
            </div>
            <div class="form-group">
                <label for="option4">Option 4:</label>
                <textarea id="option4" name="option4"></textarea>
                <label><input type="checkbox" name="correctOption4" value="4"> Correct Answer</label>
            </div>
        </form>
    </div>
</body>
</html>