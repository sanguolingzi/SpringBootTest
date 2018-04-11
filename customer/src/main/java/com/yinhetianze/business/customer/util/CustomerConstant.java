package com.yinhetianze.business.customer.util;

public class CustomerConstant {

    public static String regeisterSufixKey="_regeister";
    public static String updPasswordSufixKey="_password";
    public static String tokenSufixKey="_token";

    /**
     * string.matche 为false  代表手机号格式不对
     */
    public static String phoneRegex = "^[1][3,4,5,7,8][0-9]{9}$";
    /**
     * 数字、英文字母、汉字
     */
    public static String nameRegex= "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
}
