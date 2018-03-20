package com.lx.wrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.lx.interceptor.ResponseBodyWrapHandler;


//InitializingBean 提供了初始化bean的方法  在初始化bean的时候 会调用 afterPropertiesSet 方法
public class ResponseBodyWrapFactoryBean implements InitializingBean {

	@Autowired  
    private RequestMappingHandlerAdapter adapter;  
  
    public void afterPropertiesSet() throws Exception {  
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();  
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<HandlerMethodReturnValueHandler>(returnValueHandlers);  
        decorateHandlers(handlers);  
        adapter.setReturnValueHandlers(handlers);  
    }  
  
    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {  
        for (HandlerMethodReturnValueHandler handler : handlers) {  
            if (handler instanceof RequestResponseBodyMethodProcessor) {  
                //用自己的ResponseBody包装类替换掉框架的，达到返回Result的效果  
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);  
                int index = handlers.indexOf(handler);  
                handlers.set(index, decorator);  
                break;  
            }  
        }  
    }  

}
