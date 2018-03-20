package com.lx.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.lx.anno.AddOrUpdate;
import com.lx.exception.AddOrUpdateUniqueException;

/**
 * 拦截 指定注解 用于特定的业务需要 
 * 此处 是模拟service中方法 对新增 或者 修改  某些唯一性校验的处理
 * @author luoxiang
 *
 */
@Aspect
@Component
public class TestServiceAop {
	
	@PostConstruct 
	public void TestServiceAop(){
		
		System.out.println("-----------init TestServiceAop --------------");
	}
	
	@Before("@annotation(com.lx.anno.AddOrUpdate)")
	public void testException(JoinPoint point) throws Exception{
		System.out.println("---------------------------");
		System.out.println("-----------"+point.getKind()+"----------------");
		System.out.println("-----------"+java.util.Arrays.toString(point.getArgs())+"----------------");
		System.out.println("-----------"+point.getTarget().getClass()+"----------------");
		System.out.println("-----------"+point.getSignature().getName()+"----------------");
		System.out.println(Thread.currentThread().getName());
		Signature signature = point.getSignature();    
		MethodSignature methodSignature = (MethodSignature)signature;    
		Method targetMethod = methodSignature.getMethod();   
		if(targetMethod.isAnnotationPresent(AddOrUpdate.class)){
			AddOrUpdate add = targetMethod.getAnnotation(AddOrUpdate.class);
			System.out.println(add.methodName());
			Object result = point.getTarget().getClass().getMethod(add.methodName(), Map.class)
			.invoke(point.getTarget(), point.getArgs());
			System.out.println("-----result-------"+result);
			boolean bool = true;
			if(bool)
				throw new AddOrUpdateUniqueException("新增或修改存在参数唯一性异常");
		}
	}
}
