package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class FacultyDashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Admin Dashboard</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/dashboard.css\">\n");
      out.write("</head>\n");
      out.write("<style>\n");
      out.write("    body {\n");
      out.write("    font-family: Arial, sans-serif;\n");
      out.write("    background-color: #f4f7f9;\n");
      out.write("    margin: 0;\n");
      out.write("    padding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".container {\n");
      out.write("    display: flex;\n");
      out.write("    height: 100vh;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".sidebar {\n");
      out.write("    width: 150px;\n");
      out.write("    background-color: ;\n");
      out.write("    color: #fff;\n");
      out.write("    padding: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".logo h1 {\n");
      out.write("    font-size: 24px;\n");
      out.write("    margin: 0 0 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".sidebar ul {\n");
      out.write("    list-style: none;\n");
      out.write("    padding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".sidebar ul li {\n");
      out.write("    margin-bottom: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".sidebar ul li a {\n");
      out.write("    color: #fff;\n");
      out.write("    text-decoration: none;\n");
      out.write("    display: block;\n");
      out.write("    padding: 10px 15px;\n");
      out.write("    border-radius: 4px;\n");
      out.write("    transition: background 0.3s;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".sidebar ul li a:hover {\n");
      out.write("    background-color: #4a5a73;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".sidebar ul li ul {\n");
      out.write("    padding-left: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".main-content {\n");
      out.write("    flex: 1;\n");
      out.write("    padding: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".header {\n");
      out.write("    margin-bottom: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".header h2 {\n");
      out.write("    margin: 0 0 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".header p {\n");
      out.write("    color: #6c757d;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".cards {\n");
      out.write("    display: grid;\n");
      out.write("    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));\n");
      out.write("    gap: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".card {\n");
      out.write("    background-color: #fff;\n");
      out.write("    padding: 20px;\n");
      out.write("    border-radius: 8px;\n");
      out.write("    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\n");
      out.write("    text-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".card h3 {\n");
      out.write("    margin: 0 0 10px;\n");
      out.write("    font-size: 18px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".card p {\n");
      out.write("    font-size: 24px;\n");
      out.write("    margin: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("</style>\n");
      out.write("<body>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"sidebar\">\n");
      out.write("            <div class=\"logo\">\n");
      out.write("                <h1>CEE Admin Panel</h1>\n");
      out.write("            </div>\n");
      out.write("            <ul>\n");
      out.write("                <li><a href=\"#\">Dashboard</a></li>\n");
      out.write("                <li><a href=\"#\">Manage Course</a>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"#\">Course</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li><a href=\"#\">Manage Exam</a>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"#\">Exam</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li><a href=\"#\">Manage Examinee</a>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"#\">Add Examinee</a></li>\n");
      out.write("                        <li><a href=\"#\">Manage Examinee</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li><a href=\"#\">Ranking</a>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"#\">Ranking By Exam</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li><a href=\"#\">Reports</a>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"#\">Examinee Result</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li><a href=\"#\">Feedbacks</a>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><a href=\"#\">All Feedbacks</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"main-content\">\n");
      out.write("            <div class=\"header\">\n");
      out.write("                <h2>Analytics Dashboard</h2>\n");
      out.write("                <p>This is an example dashboard created using built-in elements and components.</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"cards\">\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h3>Total Course</h3>\n");
      out.write("                    <p>3</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h3>Total Exam</h3>\n");
      out.write("                    <p>6</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h3>Total Examinee</h3>\n");
      out.write("                    <p>46%</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h3>Total things</h3>\n");
      out.write("                    <p>1896</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h3>Finito</h3>\n");
      out.write("                    <p>3M</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card\">\n");
      out.write("                    <h3>Student</h3>\n");
      out.write("                    <p>45.9%</p>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
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
