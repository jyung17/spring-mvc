package hello.servlet.web.frontcontoller.v5;

import hello.servlet.web.frontcontoller.ModelView;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {
  boolean supports(Object handler);
  
  ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws ServletException, IOException;
}
