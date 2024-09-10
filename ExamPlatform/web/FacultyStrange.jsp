<%-- 
    Document   : FacultyStrange
    Created on : Aug 28, 2024, 11:16:52 PM
    Author     : Toby Femi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Continue Editing?</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 10px;
            background-color: #f4f4f4;
        }
        .strangeMenu{
            padding: 60px;
            width: 40%;
            background-color: ghostwhite;
            font-family: monospace;
            font-size: 30px;
        }
        input{
            padding: 20px;
            border: 2px #2C3E50 solid;
        }
        button{
            margin: auto;
            /*height: 30px;*/
            color: white;
            background: #2C3E50;
            padding: 10px;
        }
    </style>
    <body>
    <center>
        <div class="strangeMenu">
            Want To Continue Editing An Exam?<br><br>
            <input id="strangeInput" class="strangeInput" name="strangeInput" placeholder="Enter ExamID">
            <div style="display:flex;">
                <br><br>
                <button id="btn">Editing</button>
            </div>
        </div>
    </center>
    </body>
</html>
