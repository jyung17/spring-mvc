package hello.servlet.web.frontcontoller.v5;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontoller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontoller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontoller.v5.adapter.ControllerV3HandlerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
  
  private final Map<String, Object> handlerMappingMap = new HashMap<>();
  private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
  
  public FrontControllerServletV5() {
    initHandlerMappingMap(); //핸들러 매핑 초기화
    initHandlerAdapters(); //어댑터 초기화
  }
  
  private boolean initHandlerAdapters() {
    return handlerAdapters.add(new ControllerV3HandlerAdapter());
  }
  
  private void initHandlerMappingMap() {
    handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
  }
  
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Object handler = getHandler(request);
  
    if (handler == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    
    MyHandlerAdapter adapter = getHandlerAdapter(handler);
  
    ModelView mv = adapter.handle(request, response, handler);
  
    String viewName = mv.getViewName();
    MyView view = viewResolver(viewName);
  
    view.render(mv.getModel(), request, response);
  }
  
  private MyHandlerAdapter getHandlerAdapter(Object handler) {
    for (MyHandlerAdapter adapter : handlerAdapters) {
      if (adapter.supports(handler)) {
        return adapter;
      }
    }
    throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
  }
  
  private Object getHandler(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    return handlerMappingMap.get(requestURI);
  }
  
  private static MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }
}
