/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Toby Femi
 */
@WebServlet("/PublishServlet")
public class PublishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // End the current session
        HttpSession session = request.getSession(false); // Retrieve the session without creating a new one
        if (session != null) {
            session.invalidate(); // End the session
        }

        // Generate a new examID
        String newExamId = generateExamId();

        // Start a new session and set the new examID
        session = request.getSession(true); // Create a new session
        session.setAttribute("examId", newExamId);

        // Redirect to the JSP page with the new examId
        response.sendRedirect("FacultyPanel.jsp"); // Or any other JSP page you want to redirect to
    }

    private String generateExamId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder examId = new StringBuilder("EXA");

        for (int i = 0; i < 6; i++) {
            int index = (int) (characters.length() * Math.random());
            examId.append(characters.charAt(index));
        }
        
        return examId.toString();
    }
}
