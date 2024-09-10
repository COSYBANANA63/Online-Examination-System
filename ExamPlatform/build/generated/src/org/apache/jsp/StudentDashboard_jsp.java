package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class StudentDashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Student Dashboard</title>\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n");
      out.write("\n");
      out.write("    <style>\n");
      out.write("        /* Existing CSS */\n");
      out.write("        * { margin: 0; padding: 0; box-sizing: border-box; font-family: sans-serif; }\n");
      out.write("        body { font-family: Arial, sans-serif; margin: 0; padding: 10px; background-color: #f4f4f4; }\n");
      out.write("        header { border: 2px solid darkslategrey; background-color: #2C3E50; }\n");
      out.write("        #logo { height: 80px; }\n");
      out.write("        input { border: 2px solid #2C3E50; border-radius: 5px; padding: 20px; font-size: 20px; outline: none; }\n");
      out.write("        label { font-size: 20px; }\n");
      out.write("        #fetchQuestionsBtn { padding: 10px; border-radius: 2px; width: 80px; color: white; background: #2C3E50; }\n");
      out.write("\n");
      out.write("        /* Loading GIF */\n");
      out.write("        #loading {\n");
      out.write("            display: none;\n");
      out.write("            width: 25px;\n");
      out.write("            height: 25px;\n");
      out.write("            vertical-align: middle;\n");
      out.write("            margin-left: 10px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <img id=\"logo\" src=\"SEMS.png\">\n");
      out.write("    </header>\n");
      out.write("    <div class=\"name\">\n");
      out.write("        <br><br>\n");
      out.write("        <h3>Name: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.fullName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h3><br><br>\n");
      out.write("        <h3>Student ID: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.userId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h3><br><br>\n");
      out.write("    </div>\n");
      out.write("    <br><br>\n");
      out.write("    <h4>Role: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.student}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h4>\n");
      out.write("\n");
      out.write("    <div class=\"examIdForm\">\n");
      out.write("        <center>\n");
      out.write("            <label for=\"examId\">Exam ID</label><br><br><br>\n");
      out.write("            <input type=\"text\" oninput=\"convertToUppercase(this)\" id=\"examId\" name=\"examId\" placeholder=\"EXA123456\" required>\n");
      out.write("            <img id=\"loading\" src=\"SEMScube.gif\" alt=\"Loading...\">\n");
      out.write("            <br><br>\n");
      out.write("            <button id=\"fetchQuestionsBtn\" type=\"button\">Submit</button><br><br>\n");
      out.write("            <a href=\"Login.jsp\" style=\"text-decoration: none\">Log Out</a>\n");
      out.write("        </center>\n");
      out.write("    </div>\n");
      out.write("   <div id=\"questionsContainer\">\n");
      out.write("        <!-- Questions will be displayed here -->\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        $(document).ready(function() {\n");
      out.write("            $('#fetchQuestionsBtn').on('click', function() {\n");
      out.write("                var examId = $('#examId').val();\n");
      out.write("\n");
      out.write("                $.ajax({\n");
      out.write("                    url: 'FetchStudentQuestionsServlet',\n");
      out.write("                    type: 'GET',\n");
      out.write("                    data: { examId: examId },\n");
      out.write("                    success: function(response) {\n");
      out.write("                        $('#questionsContainer').html(response);\n");
      out.write("                    },\n");
      out.write("                    error: function(xhr, status, error) {\n");
      out.write("                        alert('Error fetching questions: ' + error);\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("        \n");
      out.write("function convertToUppercase(element) {\n");
      out.write("            element.value = element.value.toUpperCase();\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    </script>\n");
      out.write("</body>\n");
      out.write("</html>");
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
