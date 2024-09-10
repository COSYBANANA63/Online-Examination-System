package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public final class startExam_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');

    List<String> questions = (List<String>) request.getAttribute("questions");
    List<String> options = (List<String>) request.getAttribute("options");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Start Exam</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h2>Questions for Exam</h2>\n");
      out.write("    <div id=\"questionsContainer\">\n");
      out.write("        ");

            if (questions != null && !questions.isEmpty()) {
                for (String question : questions) {
        
      out.write("\n");
      out.write("                    <p>");
      out.print( question );
      out.write("</p>\n");
      out.write("        ");

                }
            } else {
        
      out.write("\n");
      out.write("            <p>No questions available for this exam.</p>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("\n");

    // Fetch examId from session
//    session = request.getSession(false);
    String examId = (String) session.getAttribute("examId");

    // Logic to fetch questions based on examId
    questions = new ArrayList<>(); 
    options = new ArrayList<>();
    examId = request.getParameter("examId");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    if (examId != null) {
        // Example database fetch (you should replace with your actual database logic)
        try {
            // Assuming a DataSource or DriverManager setup
            Connection con = null; // Your database connection
            String query = "SELECT QuestionText, Option1, Option2, Option3, Option4, correctOption1, correctOption2, correctOption3, correctOption4 FROM EXAMQUESTIONS WHERE examId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, examId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                questions.add(rs.getString("questionText"));
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

      out.write("\n");
      out.write("    <h2>Questions for Exam: ");
      out.print( examId );
      out.write("</h2>\n");
      out.write("    <div id=\"questionsContainer\">\n");
      out.write("        ");

            if (questions.size() > 0 && options.size() > 0 ) {
                for (String question : questions){
                    for(String option : options){
        
      out.write("\n");
      out.write("                    <p>");
      out.print( question );
      out.write("</p>\n");
      out.write("                    <p>");
      out.print( option );
      out.write("</p>\n");
      out.write("        ");

                }
                }
            } else {
        
      out.write("\n");
      out.write("            <p>No questions available for this exam.</p>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
