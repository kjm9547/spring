package com.example.demo.web.frontcontroller.v5.adapter;

import com.example.demo.web.frontcontroller.ModelView;
import com.example.demo.web.frontcontroller.v4.ControllerV4;
import com.example.demo.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws SerialException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String,String> paraMap = createParamMap(request);
        HashMap<String,Object> model = new HashMap<>();

        String viewName = controller.process(paraMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }
    private Map<String,String> createParamMap(HttpServletRequest request){
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName,request.getParameter(paramName)));

        return paramMap;
    }
}
