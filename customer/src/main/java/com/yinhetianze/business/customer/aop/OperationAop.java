package com.yinhetianze.business.customer.aop;

import com.yinhetianze.business.customer.annotation.OperationAnno;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

//@Component
//@Aspect
public class OperationAop {

    @Autowired
    private  HttpServletRequest request;

    @After(value="target(com.yinhetianze.core.business.AbstractRestBusiExecutor)")
    public void afterMethod3(JoinPoint jp){
        /*System.out.println("jp.getSignature():"+jp.getSignature().getName());
        System.out.println("jp.getTarget():"+jp.getTarget());
        System.out.println("jp.getKind():"+jp.getKind());

        System.out.println(jp.getArgs());
        for(Object o : jp.getArgs()){
            System.out.println(o);
        }*/
        if(jp.getTarget().getClass().isAnnotationPresent(OperationAnno.class)){
            OperationAnno operationAnno = jp.getTarget().getClass().getAnnotation(OperationAnno.class);
            System.out.println(operationAnno.methodDescription());
            System.out.println(request.getSession().getId());
        }
    }

}
