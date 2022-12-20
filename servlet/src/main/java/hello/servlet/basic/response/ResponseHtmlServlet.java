package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
  
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //Content-Type: text/html;charset=utf8
    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");
  
    PrintWriter writer = response.getWriter();
    writer.print("<html>");
    writer.print("<body>");
    writer.print("  <div>안녕</div");
    writer.print("</body>");
    writer.print("</html>");
  }
}
