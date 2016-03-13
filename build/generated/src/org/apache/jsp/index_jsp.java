package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import models.Queries;
import models.Conection;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");

Conection conection = new Conection(); // class conection instance
Queries q = new Queries(); // class queries instance
boolean stateConection = conection.conect(); // get state conection

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Marathon Skills 2016</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"stylesheets/main.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            if(!stateConection){ 
      out.write("\n");
      out.write("                <script>document.write('No conected to database <a onclick=\"location.reload()\">Reload</a>');</script>\n");
      out.write("            ");
 }else{ 
      out.write("\n");
      out.write("                <h1 class=\"title\">Marathon Skills 2016</h1>\n");
      out.write("                <h2 class=\"subtitle\">Runner Results</h2>\n");
      out.write("                <form action=\"\" method=\"POST\" class=\"formFindRunner\">\n");
      out.write("                    <label for=\"nameRunner\">Name</label>\n");
      out.write("                    <input id=\"nameRunner\" type=\"text\" name=\"nameRunner\" required>\n");
      out.write("                    <button type=\"submit\" name=\"btnFind\">Find</button>\n");
      out.write("                </form>\n");
      out.write("                <div class=\"center\">\n");
      out.write("                    This list show the event results of the marathon in the you are participed. <br>\n");
      out.write("                    The general place is in relation to all runners from event. <br>\n");
      out.write("                    The place of category  is in relation to all runners from the same gender <br>\n");
      out.write("                    and age category.\n");
      out.write("                </div>\n");
      out.write("                <footer>\n");
      out.write("                    <button type=\"button\" name=\"btnUnderstandMarathon\" onclick=\"location.href='./understandMarathon.jsp'\">Undertand Marathon</button>\n");
      out.write("                </footer>\n");
      out.write("            ");
 }
            
            /* validate if the user is no empty */
            if(request.getParameter("nameRunner") != null && request.getParameter("nameRunner") != ""){
                String nameRunner = request.getParameter("nameRunner");
                
                String[] dataRunner = q.getDataRunner(nameRunner);
                ResultSet dataRunners = q.getDataRunners(nameRunner); 
            
                if(dataRunner.length > 0){ 
      out.write("\n");
      out.write("                <div class=\"resultMarathon\">\n");
      out.write("                    <div class=\"center mid-padding\">\n");
      out.write("                        <b>Gender: </b> <span>");
      out.print( dataRunner[0] );
      out.write("</span>&nbsp;&nbsp;<b>Category: </b> <span>");
      out.print( q.calculateCategory(dataRunner[1]) );
      out.write("</span>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("                <div class=\"resultMarathon\">\n");
      out.write("                    <div class=\"center mid-padding\">\n");
      out.write("                        <b>No found data</b>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            ");
 }
        
      out.write("\n");
      out.write("    </body>\n");
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
