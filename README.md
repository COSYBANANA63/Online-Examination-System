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
    git clone https://github.com/yourusername/online-examination-system.git
    cd online-examination-system
    ```

2. **Setup Database**:

    Ensure you have Apache Derby installed and running. Create a database named `ExamPortal` and configure the connection settings in the `LoginServlet`.

3. **Build and Deploy**:

    - Import the project into your IDE (e.g., IntelliJ IDEA).
    - Configure the web server (e.g., Apache Tomcat) and deploy the application.

4. **Configuration**:

    Update the database connection settings in `LoginServlet` and other relevant classes if necessary.

## Usage

1. **Running the Application**:

    Start your web server and navigate to `http://localhost:8080/online-examination-system/Login.jsp` to access the login page.

2. **Login**:

    - **Students**: Use email or userId and password to log in.
    - **Faculties**: Use email and password to log in.

3. **Access Dashboards**:

    - **Student Dashboard**: After successful login, students are redirected to `StudentDashboard.jsp`.
    - **Faculty Dashboard**: After successful login, faculties are redirected to `FacultyDashboard.jsp`.

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

