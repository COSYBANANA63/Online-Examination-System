<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student Dashboard</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        /* Existing CSS */
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: sans-serif; }
        body { font-family: Arial, sans-serif; margin: 0; padding: 10px; background-color: #f4f4f4; }
        header { border: 2px solid darkslategrey; background-color: #2C3E50; }
        #logo { height: 80px; }
        input { border: 2px solid #2C3E50; border-radius: 5px; padding: 20px; font-size: 20px; outline: none; }
        label { font-size: 20px; }
        #fetchQuestionsBtn { padding: 10px; border-radius: 2px; width: 80px; color: white; background: #2C3E50; }

        /* Loading GIF */
        #loading {
            display: none;
            width: 25px;
            height: 25px;
            vertical-align: middle;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <header>
        <img id="logo" src="SEMS.png">
    </header>
    <div class="name">
        <br><br>
        <h3>Name: ${sessionScope.fullName}</h3><br><br>
        <h3>Student ID: ${sessionScope.userId}</h3><br><br>
    </div>
    <br><br>
    <h4>Role: ${sessionScope.student}</h4>

    <div class="examIdForm">
        <center>
            <label for="examId">Exam ID</label><br><br><br>
            <input type="text" oninput="convertToUppercase(this)" id="examId" name="examId" placeholder="EXA123456" required>
            <img id="loading" src="SEMScube.gif" alt="Loading...">
            <br><br>
            <button id="fetchQuestionsBtn" type="button">Submit</button><br><br>
            <a href="Login.jsp" style="text-decoration: none">Log Out</a>
        </center>
    </div>
   <div id="questionsContainer">
        <!-- Questions will be displayed here -->
    </div>

    <script>
        $(document).ready(function() {
    $('#fetchQuestionsBtn').on('click', function() {
        var examId = $('#examId').val();

        $.ajax({
            url: 'ExamIdServlet',
            type: 'POST',
            data: { examId: examId },
            success: function(response) {
                var responseData = JSON.parse(response);
                if (responseData.message === "success") {
                    window.location.href = 'rules.jsp';
                }
            },
            error: function(xhr, status, error) {
                if (xhr.status === 403) {
                    alert('You have already submitted this exam.');
                } else if (xhr.status === 404) {
                    alert('Exam ID not found.');
                } else {
                    alert('Error fetching questions: ' + error);
                }
            },
            beforeSend: function() {
                $('#loading').show(); // Show loading gif before request
            },
            complete: function() {
                $('#loading').hide(); // Hide loading gif after request
            }
        });
    });
});
//        Disable navigation using JavaScript
    history.pushState(null, null, location.href);
window.onpopstate = function () {
  history.go(1);
};


        
function convertToUppercase(element) {
            element.value = element.value.toUpperCase();
        }

    </script>
</body>
</html>