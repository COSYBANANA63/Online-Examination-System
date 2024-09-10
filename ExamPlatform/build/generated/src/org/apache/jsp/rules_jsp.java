package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rules_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Rules Page</title>\n");
      out.write("\n");
      out.write("    <!-- MathJax Script for Rendering LaTeX -->\n");
      out.write("    <script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>\n");
      out.write("    <script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"></script>\n");
      out.write("\n");
      out.write("    <style>\n");
      out.write("        * { margin: 0; padding: 0; box-sizing: border-box; font-family: sans-serif; }\n");
      out.write("        body { font-family: Arial, sans-serif; margin: 0; padding: 10px; background-color: #f4f4f4; }\n");
      out.write("        header { border: 2px solid darkslategrey; background-color: #2C3E50; }\n");
      out.write("        #logo { height: 80px; }\n");
      out.write("        input { border: 2px solid #2C3E50; border-radius: 5px; padding: 20px; font-size: 20px; outline: none; }\n");
      out.write("        label { font-size: 20px; }\n");
      out.write("        button { padding: 10px; border-radius: 2px; width: 100px; color: white; background: #2C3E50; }\n");
      out.write("        .option-container {\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            margin-bottom: 8px;\n");
      out.write("        }\n");
      out.write("        .option-label {\n");
      out.write("            margin-left: 5px;\n");
      out.write("        }\n");
      out.write("        .rules-container { padding: 20px; background-color: white; border-radius: 5px; margin-top: 20px; }\n");
      out.write("        .rules-list { list-style-type: decimal; padding-left: 20px; }\n");
      out.write("        .question-container { padding: 20px; background-color: #ffffff; border-radius: 5px; margin-top: 20px; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <img id=\"logo\" src=\"SEMS.png\" alt=\"Logo\">\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("    <div class=\"rules-container\">\n");
      out.write("        <h2>Exam Rules</h2>\n");
      out.write("        <input type=\"text\" id=\"examId\" name=\"examId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.examId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required>\n");
      out.write("        <input type=\"text\" id=\"fullName\" name=\"userName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.fullName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required>\n");
      out.write("        <input type=\"text\" id=\"userId\" name=\"userId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.userId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required>\n");
      out.write("\n");
      out.write("        <ul class=\"rules-list\">\n");
      out.write("            <li>Rule 1: Read all questions carefully.</li>\n");
      out.write("            <li>Rule 2: Do not use any unauthorized materials.</li>\n");
      out.write("            <li>Rule 3: Manage your time effectively.</li>\n");
      out.write("            <li>Rule 4: Submit your answers before the deadline.</li>\n");
      out.write("            <li>Rule 5: Any form of cheating will result in disqualification.</li>\n");
      out.write("        </ul>\n");
      out.write("        <br>\n");
      out.write("        <button id=\"startExamBtn\" type=\"button\">Start Exam</button>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"question-container\" id=\"questionContainer\" style=\"display: none;\">\n");
      out.write("        <!-- Questions will be loaded here -->\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("    var questionsList = [];\n");
      out.write("    var currentIndex = 0;\n");
      out.write("    var userAnswers = [];\n");
      out.write("    var correctAnswers = 0;\n");
      out.write("\n");
      out.write("    function displayQuestion() {\n");
      out.write("        if (currentIndex >= 0 && currentIndex < questionsList.length) {\n");
      out.write("            var question = questionsList[currentIndex];\n");
      out.write("            var questionHtml = \"<div class='question'>\";\n");
      out.write("            questionHtml += \"<p><strong>Question:</strong> \" + question.question + \"</p>\";\n");
      out.write("\n");
      out.write("            for (var i = 0; i < question.options.length; i++) {\n");
      out.write("                var isChecked = userAnswers[currentIndex] === question.options[i];\n");
      out.write("                questionHtml += \"<div class='option-container'>\";\n");
      out.write("                questionHtml += \"<input type='radio' id='option-\" + i + \"' name='options' value='\" + question.options[i] + \"'\" + (isChecked ? \" checked\" : \"\") + \">\";\n");
      out.write("                questionHtml += \"<label class='option-label' for='option-\" + i + \"'>\" + question.options[i] + \"</label>\";\n");
      out.write("                questionHtml += \"</div>\";\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            questionHtml += \"</div><hr>\";\n");
      out.write("\n");
      out.write("            // Navigation buttons\n");
      out.write("            if (currentIndex > 0) {\n");
      out.write("                questionHtml += \"<button onclick='navigate(-1)'>Previous</button>\";\n");
      out.write("            }\n");
      out.write("            if (currentIndex < questionsList.length - 1) {\n");
      out.write("                questionHtml += \"<button onclick='navigate(1)'>Next</button>\";\n");
      out.write("            } else                questionHtml += \"<button onclick='finishExam()'>Finish Exam</button>\";\n");
      out.write("\n");
      out.write("            $(\"#questionContainer\").html(questionHtml);\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function navigate(direction) {\n");
      out.write("        currentIndex += direction;\n");
      out.write("        displayQuestion();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function checkAnswer(selectedOption) {\n");
      out.write("        var correctOptions = questionsList[currentIndex].correctOptions;\n");
      out.write("        var isCorrect = false;\n");
      out.write("\n");
      out.write("        // Check which option is correct\n");
      out.write("        for (var i = 0; i < correctOptions.length; i++) {\n");
      out.write("            if (correctOptions[i] === true && questionsList[currentIndex].options[i] === selectedOption) {\n");
      out.write("                isCorrect = true;\n");
      out.write("                break;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        if (isCorrect) {\n");
      out.write("            correctAnswers++;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        userAnswers[currentIndex] = selectedOption;\n");
      out.write("        displayQuestion();\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function finishExam() {\n");
      out.write("        var score = (correctAnswers / questionsList.length) * 100;\n");
      out.write("        alert(\"Your score is: \" + score.toFixed(2) + \"%\");\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    $(document).ready(function() {\n");
      out.write("        $.ajax({\n");
      out.write("            type: \"GET\",\n");
      out.write("            url: \"GetQuestions\",\n");
      out.write("            dataType: \"json\",\n");
      out.write("            success: function(data) {\n");
      out.write("                questionsList = data;\n");
      out.write("                $(\"#startExamBtn\").click(function() {\n");
      out.write("                    $(\"#rulesContainer\").hide();\n");
      out.write("                    $(\"#questionContainer\").show();\n");
      out.write("                    displayQuestion();\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("    });\n");
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
