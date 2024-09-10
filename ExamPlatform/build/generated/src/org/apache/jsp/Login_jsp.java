package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>SEMS Login</title>\n");
      out.write("    </head>\n");
      out.write("       <link href=\"https://fonts.googleapis.com/css?family=Roboto:300,400,500,700\" rel=\"stylesheet\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.4.1/css/all.css\" integrity=\"sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz\" crossorigin=\"anonymous\">\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n");
      out.write("    <script>\n");
      out.write("        $.ajax({\n");
      out.write("    type: \"POST\",\n");
      out.write("    url: \"LoginServlet\",\n");
      out.write("    data: $(this).serialize(),\n");
      out.write("    dataType: \"json\",\n");
      out.write("    success: function(response) {\n");
      out.write("        if (response.status === \"success\") {\n");
      out.write("            window.location.href = response.redirect;\n");
      out.write("        } else {\n");
      out.write("            $(\"#error-message\").text(response.message);\n");
      out.write("        }\n");
      out.write("    },\n");
      out.write("    error: function() {\n");
      out.write("        $(\"#error-message\").text(\"An error occurred while processing your request.\");\n");
      out.write("    }\n");
      out.write("});\n");
      out.write("    </script>\n");
      out.write("    <style>\n");
      out.write("      html, body {\n");
      out.write("      display: flex;\n");
      out.write("      justify-content: center;\n");
      out.write("      height: 100%;\n");
      out.write("      }\n");
      out.write("      body{\n");
      out.write("          background-image: url(72127776_9760493.jpg);\n");
      out.write("      }\n");
      out.write("      body, div, h1, form, input, p { \n");
      out.write("      padding: 0;\n");
      out.write("      margin: 0;\n");
      out.write("      outline: none;\n");
      out.write("      font-family: Roboto, Arial, sans-serif;\n");
      out.write("      font-size: 16px;\n");
      out.write("      color: #666;\n");
      out.write("      }\n");
      out.write("      h1 {\n");
      out.write("      padding: 10px 0;\n");
      out.write("      font-size: 32px;\n");
      out.write("      font-weight: 300;\n");
      out.write("      text-align: center;\n");
      out.write("      /*border: 1px solid;*/\n");
      out.write("      }\n");
      out.write("      p {\n");
      out.write("      font-size: 12px;\n");
      out.write("      }\n");
      out.write("      hr {\n");
      out.write("      color: #a9a9a9;\n");
      out.write("      opacity: 0.3;\n");
      out.write("      }\n");
      out.write("      .main-block {\n");
      out.write("      max-width: 340px; \n");
      out.write("      min-height: 460px; \n");
      out.write("      padding: 10px 0;\n");
      out.write("      margin: auto;\n");
      out.write("      border-radius: 5px; \n");
      out.write("      border: solid 1px #ccc;\n");
      out.write("      box-shadow: 1px 2px 5px rgba(0,0,0,.31); \n");
      out.write("      background: #ebebeb; \n");
      out.write("      }\n");
      out.write("      form {\n");
      out.write("      margin: 0 30px;\n");
      out.write("      }\n");
      out.write("      .account-type, .gender {\n");
      out.write("      margin: 15px 0;\n");
      out.write("      }\n");
      out.write("      input[type=radio] {\n");
      out.write("      display: none;\n");
      out.write("      }\n");
      out.write("      label#icon {\n");
      out.write("      margin: 0;\n");
      out.write("      border-radius: 5px 0 0 5px;\n");
      out.write("      }\n");
      out.write("      label.radio {\n");
      out.write("      position: relative;\n");
      out.write("      display: inline-block;\n");
      out.write("      padding-top: 4px;\n");
      out.write("      margin-right: 20px;\n");
      out.write("      text-indent: 30px;\n");
      out.write("      overflow: visible;\n");
      out.write("      cursor: pointer;\n");
      out.write("      }\n");
      out.write("      label.radio:before {\n");
      out.write("      content: \"\";\n");
      out.write("      position: absolute;\n");
      out.write("      top: 2px;\n");
      out.write("      left: 0;\n");
      out.write("      width: 20px;\n");
      out.write("      height: 20px;\n");
      out.write("      border-radius: 50%;\n");
      out.write("      background: #2e3a4e;\n");
      out.write("      }\n");
      out.write("      label.radio:after {\n");
      out.write("      content: \"\";\n");
      out.write("      position: absolute;\n");
      out.write("      width: 9px;\n");
      out.write("      height: 4px;\n");
      out.write("      top: 8px;\n");
      out.write("      left: 4px;\n");
      out.write("      border: 3px solid #fff;\n");
      out.write("      border-top: none;\n");
      out.write("      border-right: none;\n");
      out.write("      transform: rotate(-45deg);\n");
      out.write("      opacity: 0;\n");
      out.write("      }\n");
      out.write("      input[type=radio]:checked + label:after {\n");
      out.write("      opacity: 1;\n");
      out.write("      }\n");
      out.write("      input[type=text], input[type=password] {\n");
      out.write("      width: calc(100% - 57px);\n");
      out.write("      height: 36px;\n");
      out.write("      margin: 13px 0 0 -5px;\n");
      out.write("      padding-left: 10px; \n");
      out.write("      border-radius: 0 5px 5px 0;\n");
      out.write("      border: solid 1px #cbc9c9; \n");
      out.write("      box-shadow: 1px 2px 5px rgba(0,0,0,.09); \n");
      out.write("      background: #fff; \n");
      out.write("      }\n");
      out.write("      input[type=password] {\n");
      out.write("      margin-bottom: 15px;\n");
      out.write("      }\n");
      out.write("      #icon {\n");
      out.write("      display: inline-block;\n");
      out.write("      padding: 9.3px 15px;\n");
      out.write("      box-shadow: 1px 2px 5px rgba(0,0,0,.09); \n");
      out.write("      background: #2e3a4e;\n");
      out.write("      color: #fff;\n");
      out.write("      text-align: center;\n");
      out.write("      }\n");
      out.write("      .btn-block {\n");
      out.write("      margin-top: 10px;\n");
      out.write("      text-align: center;\n");
      out.write("      }\n");
      out.write("      button {\n");
      out.write("      width: 100%;\n");
      out.write("      padding: 10px 0;\n");
      out.write("      margin: 10px auto;\n");
      out.write("      border-radius: 5px; \n");
      out.write("      border: none;\n");
      out.write("      background: #2e3a4e; \n");
      out.write("      font-size: 14px;\n");
      out.write("      font-weight: 600;\n");
      out.write("      color: #fff;\n");
      out.write("      }\n");
      out.write("      button:hover {\n");
      out.write("      background: #26a9e0;\n");
      out.write("      }\n");
      out.write("      a{\n");
      out.write("          color:#2e3a4e;\n");
      out.write("      }\n");
      out.write("      a:visited{\n");
      out.write("          color:#2e3a4e;\n");
      out.write("      }\n");
      out.write("      .errorMessage{\n");
      out.write("          transition: 0.5s;\n");
      out.write("          /*display: none;*/\n");
      out.write("      }\n");
      out.write("    </style>\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <div class=\"main-block\">\n");
      out.write("      <h1>ExamMaster</h1>\n");
      out.write("      <center>\n");
      out.write("      ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("      </center>\n");
      out.write("      <form action=\"LoginServlet\" method=\"post\">\n");
      out.write("        <hr>\n");
      out.write("        <div class=\"account-type\">\n");
      out.write("          <input type=\"radio\" value=\"students\" id=\"radioOne\" name=\"role\" checked/>\n");
      out.write("          <label for=\"radioOne\" class=\"radio\">Student</label>\n");
      out.write("          <input type=\"radio\" value=\"faculties\" id=\"radioTwo\" name=\"role\"/>\n");
      out.write("          <label for=\"radioTwo\" class=\"radio\">Faculty</label>\n");
      out.write("        </div>\n");
      out.write("        <hr>\n");
      out.write("        \n");
      out.write("        <label id=\"icon\" for=\"name\"><span class=\"fas fa-envelope\"></span></label>\n");
      out.write("        <input type=\"text\" name=\"email\" id=\"email_user\" placeholder=\"Email Or UserId\" required/>\n");
      out.write("        \n");
      out.write("        <label id=\"icon\" for=\"name\"><span class=\"fas fa-unlock-alt\"></span></label>\n");
      out.write("        <input type=\"password\" name=\"password\" id=\"password\" placeholder=\"Password\" required/>\n");
      out.write("        \n");
      out.write("        <div class=\"btn-block\">\n");
      out.write("          <p>By clicking Register, you agree on our <a href=\"#\">Privacy Policy for ExamMaster</a>.</p>\n");
      out.write("          <button type=\"submit\" href=\"/\">Submit</button>\n");
      out.write("          <a href=\"Registration.jsp\">Register</a>\n");
      out.write("        </div>\n");
      out.write("      </form>\n");
      out.write("    </div>\n");
      out.write("  </body>\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty errorMessage}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("          <p class=\"errorMessage\" style=\"color: red;\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${errorMessage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</p>\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }
}
