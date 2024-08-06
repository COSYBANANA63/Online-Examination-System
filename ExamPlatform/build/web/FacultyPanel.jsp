<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Faculty Panel</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <script src="https://cdn.ckeditor.com/4.16.0/standard-all/ckeditor.js"></script>
    <script src="https://ckeditor.com/docs/ckeditor4/4.16.0/examples/assets/plugins/mathjax/plugin.js"></script>
    <script>
        function addOption(questionBlock) {
            const options = questionBlock.querySelectorAll('.option-block');
            if (options.length >= 4) {
                alert("Max Options Reached");
                return;
            }
    
            const optionContainer = document.createElement('div');
            optionContainer.classList.add('option-block');
    
            const optionInput = document.createElement('textarea');
            optionInput.name = 'answer';
            optionInput.placeholder = 'Type answer here';
    
            CKEDITOR.replace(optionInput, {
                extraPlugins: 'mathjax',
                mathJaxLib: 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?config=TeX-MML-AM_CHTML',
                toolbar: [
                    { name: 'clipboard', items: ['Cut', 'Copy', 'Paste', '-', 'Undo', 'Redo'] },
                    { name: 'editing', items: ['Find', 'Replace', '-', 'SelectAll'] },
                    { name: 'insert', items: ['Image', 'Table', 'HorizontalRule', 'SpecialChar', 'Mathjax'] },
                    { name: 'tools', items: ['Maximize'] },
                    '/',
                    { name: 'basicstyles', items: ['Bold', 'Italic', 'Strike', '-', 'RemoveFormat'] },
                    { name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] },
                    { name: 'styles', items: ['Styles', 'Format'] },
                    { name: 'about', items: ['About'] }
                ]
            }).on('instanceReady', function (event) {
                applyNotificationHandler(event.editor);
            });
    
            const optionCheckbox = document.createElement('input');
            optionCheckbox.type = 'checkbox';
            optionCheckbox.name = 'correctAnswer';
            optionCheckbox.title = 'Mark as correct answer';
    
            const deleteOptionButton = document.createElement('button');
            deleteOptionButton.type = 'button';
            deleteOptionButton.classList.add('delete-option-btn');
            deleteOptionButton.textContent = 'Delete answer';
            deleteOptionButton.onclick = function() {
                questionBlock.removeChild(optionContainer);
            };
    
            optionContainer.appendChild(optionInput);
            optionContainer.appendChild(optionCheckbox);
            optionContainer.appendChild(deleteOptionButton);
            questionBlock.appendChild(optionContainer);
        }
    
        function addQuestion() {
            const questionsContainer = document.querySelector('.questions-container');
            const questionBlock = document.createElement('div');
            questionBlock.classList.add('question-block');
            const questionInput = document.createElement('textarea');
            questionInput.name = 'question';
            questionInput.placeholder = 'Type question here';
            questionBlock.appendChild(questionInput);
    
            CKEDITOR.replace(questionInput, {
                extraPlugins: 'mathjax',
                mathJaxLib: 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.5/MathJax.js?config=TeX-MML-AM_CHTML',
                toolbar: [
                    { name: 'clipboard', items: ['Cut', 'Copy', 'Paste', '-', 'Undo', 'Redo'] },
                    { name: 'editing', items: ['Find', 'Replace', '-', 'SelectAll'] },
                    { name: 'insert', items: ['Image', 'Table', 'HorizontalRule', 'SpecialChar', 'Mathjax'] },
                    { name: 'tools', items: ['Maximize'] },
                    '/',
                    { name: 'basicstyles', items: ['Bold', 'Italic', 'Strike', '-', 'RemoveFormat'] },
                    { name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] },
                    { name: 'styles', items: ['Styles', 'Format'] },
                    { name: 'about', items: ['About'] }
                ]
            }).on('instanceReady', function (event) {
                applyNotificationHandler(event.editor);
            });
    
            const addOptionButton = document.createElement('button');
            addOptionButton.type = 'button';
            addOptionButton.classList.add('add-option-btn');
            addOptionButton.textContent = '+ Add answer';
            addOptionButton.onclick = function() {
                addOption(questionBlock);
            };
    
            const deleteQuestionButton = document.createElement('button');
            deleteQuestionButton.type = 'button';
            deleteQuestionButton.classList.add('delete-question-btn');
            deleteQuestionButton.textContent = 'Delete question';
            deleteQuestionButton.onclick = function() {
                questionsContainer.removeChild(questionBlock);
            };
    
            questionBlock.appendChild(addOptionButton);
            questionBlock.appendChild(deleteQuestionButton);
            questionsContainer.appendChild(questionBlock);
        }
    
        function applyNotificationHandler(editor) {
            // Override the default showNotification method
            editor.on('notificationShow', function (evt) {
                // List of message IDs to suppress
                var suppressMessages = [
                    'notification-id-to-suppress-1',
                    'notification-id-to-suppress-2'
                    // Add more notification IDs here
                ];
    
                // Check if the notification should be suppressed
                if (suppressMessages.indexOf(evt.data.message.id) !== -1) {
                    evt.cancel(); // Cancel the notification display
                }
            });
        }
    
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelector('.add-question-btn').onclick = addQuestion;
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
            width: 40px; /* Shrunk width */
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
            width: 200px; /* Expanded width */
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

        .form-group input, .form-group select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .questions-container {
            margin-top: 20px;
        }

        .question-block {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }

        .question-block textarea {
            width: 100%;
            height: 100px;
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .option-block {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .option-block textarea {
            width: calc(100% - 70px);
            margin-right: 10px;
        }

        .option-block input[type="checkbox"] {
            margin-right: 10px;
        }

        .add-option-btn, .delete-option-btn, .add-question-btn, .delete-question-btn {
            background-color: #2980B9;
            color: #fff;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 5px;
            margin-right: 5px;
        }

        .add-option-btn:hover, .delete-option-btn:hover, .add-question-btn:hover, .delete-question-btn:hover {
            background-color: #1F4F75;
        }

        .delete-option-btn, .delete-question-btn {
            background-color: #E74C3C;
        }

        .delete-option-btn:hover, .delete-question-btn:hover {
            background-color: #C0392B;
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
                <h1>Exam Management</h1>
            </div>
            <div class="header-right">
                <button onclick="document.getElementById('exam-form').reset();" class="cancel-btn">Cancel</button>
                <button class="save-btn">Save</button>
                <button onclick="document.getElementById('exam-form').submit();" class="publish-btn">Publish</button>
            </div>
        </header>
        <div class="stats-bar">
            <div class="stat">
                <span>10</span>
                Questions
            </div>
            <div class="stat">
                <span>200</span>
                Students
            </div>
            <div class="stat">
                <span>50</span>
                Marks
            </div>
        </div>
        <div class="exam-form">
            <h2>Create Exam</h2>
            <div class="form-group">
                <label for="examTitle">Exam Title</label>
                <input type="text" id="examTitle" name="examTitle">
            </div>
            <div class="form-group">
                <label for="examDescription">Description</label>
                <textarea style="height: 50px; font-size: large;" id="examDescription" name="examDescription" placeholder="Exam Description"></textarea>
            </div>
            <div class="form-group">
                <label for="examDuration">Duration</label>
                <select id="examDuration" name="examDuration">
                    <option value="">-- Select --</option>
                    <option value="10Mins">10 Minutes</option><option value="20Mins">20 Minutes</option><option value="40Mins">40 Minutes</option><option value="50Mins">50 Minutes</option><option value="60Mins">60 Minutes</option><option value="75Mins">75 Minutes</option>
                </select>
            </div>
            <div class="questions-container">
                <!-- spawn questions -->
            </div>
            <button class="add-question-btn">+ Add Question</button>
        </div>
    </div>
</body>
</html>