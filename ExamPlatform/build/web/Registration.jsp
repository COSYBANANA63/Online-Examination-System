<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Page</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <style>
      html, body {
        display: flex;
        justify-content: center;
        height: 100%;
      }
      body{
          background-image: url(72127776_9760493.jpg);          
      }
      body, div, h1, form, input, p, select { 
        padding: 0;
        margin: 0;
        outline: none;
        font-family: Roboto, Arial, sans-serif;
        font-size: 16px;
        color: #666;
      }
      h1 {
        padding: 10px 0;
        font-size: 32px;
        font-weight: 300;
        text-align: center;
      }
      p {
        font-size: 12px;
      }
      hr {
        color: #a9a9a9;
        opacity: 0.3;
      }
      .main-block {
        max-width: 340px; 
        min-height: 520px; 
        padding: 10px 0;
        margin: auto;
        border-radius: 5px; 
        border: solid 1px #ccc;
        box-shadow: 1px 2px 5px rgba(0,0,0,.31); 
        background: #ebebeb; 
      }
      form {
        margin: 0 30px;
      }
      .account-type, .gender {
        margin: 15px 0;
      }
      input[type=radio] {
        display: none;
      }
      label#icon {
        margin: 0;
        border-radius: 5px 0 0 5px;
      }
      label.radio {
        position: relative;
        display: inline-block;
        padding-top: 4px;
        margin-right: 20px;
        text-indent: 30px;
        overflow: visible;
        cursor: pointer;
      }
      label.radio:before {
        content: "";
        position: absolute;
        top: 2px;
        left: 0;
        width: 20px;
        height: 20px;
        border-radius: 50%;
        background: #2e3a4e;
      }
      label.radio:after {
        content: "";
        position: absolute;
        width: 9px;
        height: 4px;
        top: 8px;
        left: 4px;
        border: 3px solid #fff;
        border-top: none;
        border-right: none;
        transform: rotate(-45deg);
        opacity: 0;
      }
      input[type=radio]:checked + label:after {
        opacity: 1;
      }
      input[type=text], input[type=password], input[type=date], select {
        width: calc(100% - 57px);
        height: 36px;
        margin: 13px 0 0 -5px;
        padding-left: 10px; 
        border-radius: 0 5px 5px 0;
        border: solid 1px #cbc9c9; 
        box-shadow: 1px 2px 5px rgba(0,0,0,.09); 
        background: #fff; 
      }
      input[type=password] {
        margin-bottom: 15px;
      }
      #icon {
        display: inline-block;
        padding: 9.3px 15px;
        box-shadow: 1px 2px 5px rgba(0,0,0,.09); 
        background: #2e3a4e;
        color: #fff;
        text-align: center;
      }
      .btn-block {
        margin-top: 10px;
        text-align: center;
      }
      button {
        width: 100%;
        padding: 10px 0;
        margin: 10px auto;
        border-radius: 5px; 
        border: none;
        background: #2e3a4e; 
        font-size: 14px;
        font-weight: 600;
        color: #fff;
      }
      button:hover {
        background: #26a9e0;
      }
      a {
        color: #2e3a4e;
      }
      a:visited {
        color: #2e3a4e;
      }
      select {
        height: 36px;
        width: 235px;
        margin: 13px 0 0 -5px;
        padding-left: 10px;
        border-radius: 0 5px 5px 0;
        border: solid 1px #cbc9c9;
        box-shadow: 1px 2px 5px rgba(0,0,0,.09);
        background: #fff;
      }
      
       /* Style for the popup */
        .popup {
            display: none;
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background: #4CAF50;
            color: white;
            padding: 20px;
            border-radius: 5px;
        }
        .popup.error {
            background: #f44336;
        }
        .popup.show {
            display: block;
        }
    </style>
</head>
<body>
    <div class="main-block">
        <h1>Registration</h1>
        <form id="registrationForm" action="RegistrationServlet" method="post">
            <hr>
            <div class="account-type">
                <input type="radio" value="students" id="radioOne" name="role" checked/>
                <label for="radioOne" class="radio">Student</label>
                <input type="radio" value="faculties" id="radioTwo" name="role"/>
                <label for="radioTwo" class="radio">Faculty</label>
            </div>
            <hr>
            <label id="icon" for="email"><span class="fas fa-envelope"></span></label>
            <input type="text" name="email" id="email" placeholder="Email" required onblur="checkEmail()"/>
            <label id="icon" for="fullName"><span class="fas fa-user"></span></label>
            <input type="text" name="fullName" id="fullName" placeholder="Full Name" required onblur="checkFullName()"/>
            <label id="icon" for="password"><span class="fas fa-phone"></span></label>
            <input type="text" name="phone" id="phone" placeholder="Phone Number" required onblur="checkPhone()"/>
            <label id="icon" for="dob"><span class="fas fa-calendar-alt"></span></label>
            <input type="date" name="dob" id="dob" placeholder="Date of Birth" required/>
            <label id="icon" for="course"><span class="fas fa-book"></span></label>
            <select name="course" id="course" required>
                <option value="">Select Course</option>
                <option value="1">Computer Science</option>
                <option value="2">Business Administration</option>
                <option value="3">Electrical Engineering</option>
                <option value="4">Mechanical Engineering</option>
                <option value="5">Civil Engineering</option>
                <option value="6">Medicine</option>
                <option value="7">Pharmacy</option>
                <option value="8">Law</option>
                <option value="9">Accounting</option>
                <option value="10">Economics</option>
                <option value="11">Psychology</option>
                <option value="12">Sociology</option>
                <option value="13">Full Stack Software Engineering</option>
                <option value="14">Data Science</option>
                <option value="15">Cyber Security</option>
                <option value="16">Digital Marketing</option>
                <option value="17">Artificial Intelligence</option>
                <option value="18">Cloud Computing</option>
                <option value="19">Blockchain Technology</option>
                <option value="20">Internet of Things (IoT)</option>
            </select>
            <label id="icon" for="password"><span class="fas fa-lock"></span></label>
            <input type="password" name="password" id="password" value="${generatedPassword}" placeholder="Password" readonly="Password will be auto generated">
            <label id="icon" for="userid"><span class="fas fa-user"></span></label>
            <input type="text" name="userId" id="userid" value="${generatedUserId}" placeholder="UserId" readonly>
            
                
            <hr>
            <div class="gender">
                <input type="radio" value="male" id="male" name="gender" checked/>
                <label for="male" class="radio">Male</label>
                <input type="radio" value="female" id="female" name="gender" />
                <label for="female" class="radio">Female</label>
            </div>
            <hr>
            <div class="btn-block">
                <p>By clicking Register, you agree on our <a href="#">Privacy Policy for ExamMaster</a>.</p>
                <button type="submit" href="/">Register</button>
                <a href="Login.jsp">Log In</a>
            </div>
        </form>
              <!-- Success/Error Popup -->
    <div id="popup" class="popup ${requestScope.messageType}">${requestScope.message}</div>
    </div>
    <script>
       // Display the popup if a message is set
        const messageType = "${requestScope.messageType}";
        if (messageType) {
            const popup = document.querySelector('.popup');
            popup.classList.add('show');
            setTimeout(() => {
                popup.classList.remove('show');
            }, 3000); // Hide popup after 3 seconds
        }

        // Reset the form after successful registration
        function resetForm() {
            document.getElementById('registrationForm').reset();
        }
        
//        Live Validation

    </script>
</body>
</html>
