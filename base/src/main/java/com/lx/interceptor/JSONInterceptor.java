package com.lx.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.lx.anno.JSON;

public class JSONInterceptor implements HandlerMethodReturnValueHandler {
	
	
	
	public boolean supportsReturnType(MethodParameter returnType) {
		//dosomething here
		System.out.println("123123123123123");
		returnType.getMethodAnnotation(JSON.class);
        boolean hasJSONAnno = returnType.getMethodAnnotation(JSON.class) != null || returnType.getMethodAnnotation(JSON.class) != null;
		return hasJSONAnno;
	}
	
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		System.out.println("handleReturnValue");
		
		mavContainer.setRequestHandled(true);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		 response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
	        //String json = jsonSerializer.toJson(returnValue);
	        response.getWriter().write("abcd1234");
	}
	/*@PostConstruct 
	public void JSONInterceptor(){
		System.out.println("-----------init JSONInterceptor --------------");
	}*/

}
