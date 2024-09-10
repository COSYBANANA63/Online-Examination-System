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
  function loadViewQuestions() {
        $.ajax({
            url: 'viewQuestions.jsp',
            type: 'GET',
            success: function(response) {
                $('#dynamic-content').html(response);
            },
            error: function(xhr, status, error) {
                alert('Error loading page: ' + error);
            }
        });
    }
    
    function loadHomePage() {
    $.ajax({
        url: 'FacultyPanel.jsp', // Assuming you want to reload the main form section
        type: 'GET',
        success: function(response) {
            var content = $(response).find('#dynamic-content').html(); 
            $('#dynamic-content').html(content);
            initializeCKEditors(); // Re-initialize CKEditor instances if needed
        },
        error: function(xhr, status, error) {
            alert('Error loading home page: ' + error);
        }
    });
}

    function loadExamResults() {
            $.ajax({
                url: 'result.jsp',
                type: 'GET',
                success: function(response) {
                    $('#dynamic-content').html(response);
                },
                error: function(xhr, status, error) {
                    alert('Error loading exam results: ' + error);
                }
            });
        }

 
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
            resetFormAndEditors();
        },
        error: function() {
            console.log('An error occurred while saving the data.');
        }
        
    });
    //Reset CKEDITOR FIELDS
    resetFormAndEditors();
}

function publishExam() {
    $.ajax({
        type: 'POST',
        url: 'PublishServlet', // The servlet that handles publishing
        data: {
            action: 'publish'
        },
        success: function(response) {
            alert('Exam published successfully!');
            // Optionally, redirect to the home page or refresh the page to start a new session
            window.location.href = 'FacultyPanel.jsp'; // or any other appropriate URL
        },
        error: function() {
            console.log('An error occurred while publishing the exam.');
        }
    });
}

function resetFormAndEditors() {
    // Reset the form fields
    document.getElementById("exam-form").reset();

    // Reset CKEditor instances
    for (var instanceName in CKEDITOR.instances) {
        CKEDITOR.instances[instanceName].setData(''); // Clear the content of each editor
    }
}

// Show the modal
        function showModal() {
            document.getElementById('editModal').style.display = 'block';
        }

        // Close the modal
        function closeModal() {
            document.getElementById('editModal').style.display = 'none';
        }

        // Function to handle Exam ID update
        function updateExamId() {
            var newExamId = document.getElementById('newExamId').value;
            $.ajax({
                type: 'POST',
                url: 'UpdateExamIdServlet',
                data: { examId: newExamId },
                success: function(response) {
                    if (response === 'success') {
                        alert('Exam ID updated successfully!');
                        closeModal();
                        location.reload();
                    } else {
                        document.getElementById('error-message').style.display = 'block';
                    }
                },
                error: function() {
                    alert('An error occurred while updating the Exam ID.');
                }
            });
        }

    document.addEventListener('DOMContentLoaded', function() {
        initializeCKEditors();

        // Bind the AJAX submission to the publish button
        document.querySelector('.add-question-btn').addEventListener('click', function(event) {
            event.preventDefault();
            sendDataToServlet();
        });
    
      // Bind modal show and close functions
            document.getElementById('edit_btn').onclick = showModal;
            document.getElementsByClassName('close')[0].onclick = closeModal;
            window.onclick = function(event) {
                if (event.target == document.getElementById('editModal')) {
                    closeModal();
                }
            };
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

        .cancel-btn, .edit-btn, .publish-btn, .add-question-btn {
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
        .add-question-btn{
            
        }
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            background-color: #fff;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 400px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        #newExamId{
             border: 2px solid #2C3E50; border-radius: 5px; padding: 20px; font-size: 20px; outline: none;
        }
        
        #newExamIdBtn { padding: 10px; border-radius: 2px; width: 80px; color: white; background: #2C3E50; }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="logo"><i class="fas fa-user-circle"></i>
        ${sessionScope.fullName}
        </div>
        <ul>
            <li><a href="" onclick="loadHomePage();"><i class="fas fa-home"></i><span class="text">Home</span></a></li>
        <li><a href="#" onclick="loadViewQuestions();"><i class="fas fa-file-alt"></i><span class="text">Exams</span></a></li>
        <li><a href="#" onclick="loadExamResults();"><i class="fa-solid fa-square-poll-horizontal"></i><span class="text">Results</span></a>
            <li><a href="#"><i class="fas fa-cog"></i><span class="text">Settings</span></a></li>
            <li><a href="Login.jsp"><i class="fas fa-sign-out-alt"></i><span class="text">Logout</span></a></li>
        </ul>
    </div>

    <div class="main-content">
        <header>
            <div class="header-left">
                <h1>Exam Panel</h1>
            </div>
            <div class="header-right">
                <button class="cancel-btn">Cancel</button>
                <button class="edit-btn" id="edit_btn">Edit</button>
                <button class="publish-btn" name="action" value="publish" onclick="publishExam()">Publish</button>
            </div>
        </header>
        <input type="hidden" name="examId" value="${sessionScope.examId}">
        <div id="dynamic-content">
        <form class="exam-form" id="exam-form">
            <div class="form-group">
                <h4>This Exam ID is: ${sessionScope.examId}</h4>
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
                <!-- Add a timer section to the form -->
            <div class="form-group">
                <label for="timerValue">Timer Value (in minutes):</label>
                <input type="number" id="timerValue" name="timerValue" required>
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
        <div style="padding-top: 20px;">
        <button class="add-question-btn" name="action" value="add" style="padding: 20px;">Add Question</button>
        </div>
    </div>
    </div>
    <!-- Modal for editing Exam ID -->
    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Want To Continue Editing An Exam?</h2>
            <center><input type="text" id="newExamId" placeholder="Enter Exam ID"><br><br>
            <button type="button" id="newExamIdBtn" onclick="updateExamId()">Submit</button></center>
            <p id="error-message" style="color: red; display: none;">ID not found.</p>
        </div>
    </div>
</body>
</html>