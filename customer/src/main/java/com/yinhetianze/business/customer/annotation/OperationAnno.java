package com.yinhetianze.business.customer.annotation;



import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Documented
public @interface OperationAnno {

    /**
     * 方法描述
     * @return
     */
    String methodDescription();
}
