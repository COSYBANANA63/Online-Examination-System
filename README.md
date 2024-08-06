# Online Examination System

An online examination system designed to facilitate tests and quizzes for educational institutions. This system allows teachers to create and manage exams, and students to take them. The platform includes user roles for students and faculties with different access levels.

## Features

- **User Authentication**: Secure login for students and faculties.
- **Test Creation**: Faculties can create and manage tests with various question types.
- **Automated Grading**: Automatic grading of exams and quizzes.
- **Real-Time Statistics**: View real-time stats on student performance.
- **Customizable Exams**: Create public and private exams with customizable settings.
- **Question Banks**: Reuse content and randomize questions from a question bank.

## Technology Stack

- **Backend**: Java Servlets, JSP
- **Database**: Microsoft SQL Server
- **Frontend**: HTML, CSS, JavaScript

## Installation

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/COSYBANANA63/online-examination-system.git
    cd online-examination-system
    ```

2. **Setup Database**:

    Ensure you have Microsoft SQL Server installed and running. Create a database named `ExamPortal` and configure the connection settings in the `ResgistrationServlet` `LoginServlet`.

3. **Build and Deploy**:

    - Import the project into your IDE (e.g., NetBeans, IntelliJ IDEA. Eclipse).
    - Configure the web server (e.g., Apache Tomcat) and deploy the application.

4. **Configuration**:

    Update the database connection settings in the `RegistrationServlet` & `LoginServlet` and other relevant classes if necessary.

## Usage

1. **Running the Application**:

    Start your web server and navigate to `http://localhost:8080/online-examination-system/Login.jsp` to access the login page.

2.**Registration**:

-    Registration is meant to be handled by the people incharge of registering new students and or faculties and will be isolated from the login form in later updates. This will be done to prevent others from casually entering the registration page and then registering themselves into the system.
    
3. **Login**:

    - **Students**: Use email or userId and password to log in.
    - **Faculties**: Use email or userId and password to log in.

4. **Access Dashboards**:

    - **Student Dashboard**: After successful login, students are redirected to `StudentDashboard.jsp`.
    - **Faculty Dashboard**: After successful login, faculties are redirected to `FacultyDashboard.jsp`.

## MSSQL FIXES
1.    If getting error such as (this driver is not configured for "integratedSecurity") please make sure you download the latest version of the MSSQL JAR Drivers from `https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16`.Extract its contents into Program Files. Then select the JAR file suitable for your JDK version and add it to your Project Libraries. in the auth folder of the MSSQL JDBC Extract copy the authentication dll and past it in you JDK bin folder, and ensure that it is specified in your System Path.
2.    To add to System Path copy the directory where the auth.dll is located e.g "C:\Program Files\sqljdbc_12.8\enu\auth\x64\mssql-jdbc_auth-12.8.0.x64.dll" Open your search bar and search for Environment Variables, click on the "Environment Variables button at the buttom and in the new dialogue boc look for path(click on path and then click new then paste the directory in it click Ok then close); might need to restart pc.

## Contributing

Contributions are welcome! If you have suggestions or improvements, please follow these steps:

1. **Fork the Repository**: Create a personal copy of the repository by forking it.
2. **Clone Your Fork**:

    ```bash
    git clone https://github.com/COSYBANANA63/online-examination-system.git
    ```

3. **Create a New Branch**:
In Process
    ```bash
    git checkout -b feature/your-feature
    ```

4. **Make Changes**: Implement your changes or features.
5. **Commit and Push**:
In Process
    ```bash
    git add .
    git commit -m "Add new feature or fix"
    git push origin feature/your-feature
    ```

6. **Create a Pull Request**: Submit a pull request with a clear description of your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions or feedback, please contact [tobyfemi55@gmail.com](mailto:tobyfemi55@gmail.com).

