package com.lx.aop;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect 
@Component
public class TestAop {
	@PostConstruct 
	public void TestAops(){
		
		System.out.println("-----------init TestAops --------------");
	}
	
	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void testBefore(JoinPoint point){
		System.out.println("---------------------------");
		System.out.println("-----------"+point.getKind()+"----------------");
		System.out.println("-----------"+java.util.Arrays.toString(point.getArgs())+"----------------");
		System.out.println("-----------"+point.getTarget().getClass()+"----------------");
		System.out.println("-----------"+point.getSignature().getName()+"----------------");
	
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        
        System.out.println("-----------"+request.getRequestURI()+"----------------");
        System.out.println("-----------"+request.getRequestURL()+"----------------");
	}
	
	@AfterThrowing("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void testException(JoinPoint joinPoint){
		System.out.println("----testException--");
	}
}
