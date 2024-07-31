<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/dashboard.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
            background-color: #f0f0f0;
        }

        .container {
            display: flex;
        }

        .sidebar {
            width: 200px;
            background-color: #2c3e50;
            color: white;
            height: 100vh;
            overflow-y: auto;
            position: fixed;
        }

        .sidebar .logo {
            padding: 15px;
            background-color: #1a252f;
            text-align: center;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        .sidebar ul li {
            padding: 5px 15px;
        }

        .sidebar ul li a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 10px 15px;
        }

        .sidebar ul li a:hover,
        .sidebar ul li a.active {
            background-color: #34495e;
        }

        .main-content {
            margin-left: 200px;
            padding: 20px;
            flex: 1;
        }

        .sidebar::-webkit-scrollbar {
            width: 10px;
        }

        .sidebar::-webkit-scrollbar-track {
            background: #1a252f;
        }

        .sidebar::-webkit-scrollbar-thumb {
            background-color: #34495e;
            border-radius: 5px;
            border: 2px solid #1a252f;
        }

        .sidebar {
            scrollbar-width: thin;
            scrollbar-color: #34495e #1a252f;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="sidebar">
            <div class="logo">
                <h1>CEE Admin Panel</h1>
            </div>
            <ul>
                <li><a href="fDash.jsp" id="fDash-link" class="nav-link">Dashboard</a></li>
                <li>
                    <p>Manage Course</p>
                    <ul>
                        <li><a href="course.jsp" id="course-link" class="nav-link">Course</a></li>
                    </ul>
                </li>
                <li>
                    <p>Manage Exam</p>
                    <ul>
                        <li><a href="exam.jsp" id="exam-link" class="nav-link">Exam</a></li>
                    </ul>
                </li>
                <li>
                    <p>Manage Examinee</p>
                    <ul>
                        <li><a href="add-examinee.jsp" id="add-examinee-link" class="nav-link">Add Examinee</a></li>
                        <li><a href="manage-examinee.jsp" id="manage-examinee-link" class="nav-link">Manage Examinee</a></li>
                    </ul>
                </li>
                <li>
                    <p>Ranking</p>
                    <ul>
                        <li><a href="ranking.jsp" id="ranking-link" class="nav-link">Ranking By Exam</a></li>
                    </ul>
                </li>
                <li>
                    <p>Reports</p>
                    <ul>
                        <li><a href="examinee-result.jsp" id="examinee-result-link" class="nav-link">Examinee Result</a></li>
                    </ul>
                </li>
                <li>
                    <p>Feedbacks</p>
                    <ul>
                        <li><a href="all-feedbacks.jsp" id="all-feedbacks-link" class="nav-link">All Feedbacks</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="main-content" id="main-content">
            <!-- Content will be loaded here dynamically -->
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // Function to load content into the main content area
            function loadContent(url) {
                $('#main-content').load(url, function(response, status, xhr) {
                    if (status == "error") {
                        $('#main-content').html("<p>Sorry, there was an error loading the content.</p>");
                    }
                });
            }

            // Load the default page
            loadContent('fDash.jsp');

            // Handle click events on the sidebar links
            $('.nav-link').on('click', function(event) {
                event.preventDefault();
                var url = $(this).attr('href');

                // Load the content for the clicked link
                loadContent(url);

                // Remove active class from all links
                $('.nav-link').removeClass('active');

                // Add active class to the clicked link
                $(this).addClass('active');
            });

            // Get the current URL path
            const currentPath = window.location.pathname.split('/').pop();

            // Map each path to its corresponding sidebar link ID
            const linkMap = {
                'fDash.jsp': 'fDash-link',
                'course.jsp': 'course-link',
                'exam.jsp': 'exam-link',
                'add-examinee.jsp': 'add-examinee-link',
                'manage-examinee.jsp': 'manage-examinee-link',
                'ranking.jsp': 'ranking-link',
                'examinee-result.jsp': 'examinee-result-link',
                'all-feedbacks.jsp': 'all-feedbacks-link'
            };

            // Set the active class on the corresponding link
            if (linkMap[currentPath]) {
                document.getElementById(linkMap[currentPath]).classList.add('active');
            }
        });
    </script>
</body>
</html>
