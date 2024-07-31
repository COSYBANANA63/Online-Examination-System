<%-- 
    Document   : fDash
    Created on : Jul 29, 2024, 1:16:04 AM
    Author     : noob_banana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <style>
        .main-content {
    flex: 1;
    padding: 20px;
}

.header {
    margin-bottom: 20px;
}

.header h2 {
    margin: 0 0 10px;
}

.header p {
    color: #6c757d;
}

.cards {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    gap: 20px;
}

.card {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    text-align: center;
}

.card h3 {
    margin: 0 0 10px;
    font-size: 18px;
}

.card p {
    font-size: 24px;
    margin: 0;
}

.sidebar ul li a.active {
    background-color: #4a5a73;
    font-weight: bold;
}
    </style>
     <div class="main-content">
            <div class="header">
                <h2>Analytics Dashboard</h2>
                <p>This is an example dashboard created using built-in elements and components.</p>
            </div>
            <div class="cards">
                <div class="card">
                    <h3>Total Course</h3>
                    <p>3</p>
                </div>
                <div class="card">
                    <h3>Total Exam</h3>
                    <p>6</p>
                </div>
                <div class="card">
                    <h3>Total Examinee</h3>
                    <p>46%</p>
                </div>
                <div class="card">
                    <h3>Total Orders</h3>
                    <p>1896</p>
                </div>
                <div class="card">
                    <h3>Products Sold</h3>
                    <p>3M</p>
                </div>
                <div class="card">
                    <h3>Followers</h3>
                    <p>45.9%</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
