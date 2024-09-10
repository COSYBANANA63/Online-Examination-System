package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;

public final class viewQuestions_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>View Questions</title>\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n");
      out.write("    <!-- MathJax Script for Rendering LaTeX -->\n");
      out.write("    <script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>\n");
      out.write("    <script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"></script>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            line-height: 1.6;\n");
      out.write("            margin: 20px;\n");
      out.write("            background-color: #f4f4f4;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .exam-id-input {\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .exam-id-input label {\n");
      out.write("            font-weight: bold;\n");
      out.write("            margin-right: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .exam-id-input input {\n");
      out.write("            padding: 8px;\n");
      out.write("            margin-right: 10px;\n");
      out.write("            width: 200px;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .exam-id-input button {\n");
      out.write("            padding: 8px 15px;\n");
      out.write("            background-color: #007BFF;\n");
      out.write("            color: #fff;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .exam-id-input button:hover {\n");
      out.write("            background-color: #0056b3;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        #questionsContainer {\n");
      out.write("            margin-top: 20px;\n");
      out.write("            background: #fff;\n");
      out.write("            padding: 20px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .question-block {\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            padding: 10px;\n");
      out.write("            border-bottom: 1px solid #ddd;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .question-block:last-child {\n");
      out.write("            border-bottom: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .question-text {\n");
      out.write("            font-weight: bold;\n");
      out.write("            font-size: 1.1em;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .option-text {\n");
      out.write("            margin-left: 20px;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .correct-answer {\n");
      out.write("            color: #28a745;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h2>Exam Questions</h2>\n");
      out.write("\n");
      out.write("    <div class=\"exam-id-input\">\n");
      out.write("        <label for=\"examId\">Enter Exam ID:</label>\n");
      out.write("        <input type=\"text\" oninput=\"convertToUppercase(this)\" id=\"examId\" name=\"examId\" placeholder=\"EXA123456\" required>\n");
      out.write("        <button id=\"fetchQuestionsBtn\">Fetch Questions</button>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div id=\"questionsContainer\">\n");
      out.write("        <!-- Questions will be displayed here -->\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        $(document).ready(function() {\n");
      out.write("            $('#fetchQuestionsBtn').on('click', function() {\n");
      out.write("                var examId = $('#examId').val();\n");
      out.write("\n");
      out.write("                $.ajax({\n");
      out.write("                    url: 'FetchQuestionsServlet',\n");
      out.write("                    type: 'GET',\n");
      out.write("                    data: { examId: examId },\n");
      out.write("                    success: function(response) {\n");
      out.write("                        $('#questionsContainer').html(response);\n");
      out.write("                        \n");
      out.write("                        // After content is added, reprocess MathJax\n");
      out.write("                        if (typeof MathJax !== 'undefined') {\n");
      out.write("                            MathJax.typesetPromise().then(() => {\n");
      out.write("                                console.log(\"MathJax rendering completed.\");\n");
      out.write("                            });\n");
      out.write("                        }\n");
      out.write("                    },\n");
      out.write("                    error: function(xhr, status, error) {\n");
      out.write("                        alert('Error fetching questions: ' + error);\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("\n");
      out.write("        function convertToUppercase(element) {\n");
      out.write("            element.value = element.value.toUpperCase();\n");
      out.write("        }\n");
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
