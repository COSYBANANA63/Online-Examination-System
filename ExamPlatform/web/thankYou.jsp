<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thank You!</title>

    <!-- No Cache Headers to prevent revisiting pages -->
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">

    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: sans-serif; }
        body { font-family: Arial, sans-serif; margin: 0; padding: 10px; background-color: #f4f4f4; }
        header { border: 2px solid darkslategrey; background-color: #2C3E50; }
        #logo { height: 80px; }
        h2 { text-align: center; color: green; margin-top: 20px; }
    </style>
</head>
<body>
    <header>
        <img id="logo" src="SEMS.png" alt="Logo">
    </header>
    <h2>Exam Submitted Successfully, Thank You!</h2>
</body>

<!-- Prevent the user from going back -->
<script>
    // Disable the back button functionality
    window.history.forward();
    
    // Redirect to the login page after 4.5 seconds
    setTimeout(function() {
        window.location.href = "Login.jsp"; // Change this to your actual login page
    }, 4500);
</script>
</html>
