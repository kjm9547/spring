package com.example.demo.web.frontcontroller.v5;

import com.example.demo.web.frontcontroller.ModelView;
import com.example.demo.web.frontcontroller.MyView;
import com.example.demo.web.frontcontroller.v3.ControllerV3;
import com.example.demo.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.demo.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.demo.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.demo.web.frontcontroller.v4.ControllerV4;
import com.example.demo.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.demo.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.demo.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.example.demo.web.frontcontroller.v5.adapter.ControllerV3HandlerAdaper;
import com.example.demo.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name= "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();
    }



    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form",new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save",new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members",new MemberListControllerV3());

        //V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form",new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save",new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members",new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdaper());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = gethandler(request);

        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 업습니다.");
    }
    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
    private Object gethandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);

    }
}