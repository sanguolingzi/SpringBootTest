package com.lx.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

	private final HandlerMethodReturnValueHandler delegate;  
    public  ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate){  
      this.delegate=delegate;  
    }  
  
    public boolean supportsReturnType(MethodParameter returnType) {  
        return delegate.supportsReturnType(returnType);  
    }  
  
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {  
        delegate.handleReturnValue(returnValue,returnType,mavContainer,webRequest);  
    }  

}
