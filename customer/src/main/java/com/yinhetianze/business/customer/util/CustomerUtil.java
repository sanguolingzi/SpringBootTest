package com.yinhetianze.business.customer.util;

import com.yinhetianze.core.utils.MD5CoderUtil;

public class CustomerUtil {


    /**
     * 校验密码是否一致
     * @param passwordFromDatabase 数据库存储密码
     * @param passwordFromInput 页面输入密码
     * @return
     */
    public static boolean checkPassword(String passwordFromDatabase,String passwordFromInput){
        if(passwordFromDatabase == null || passwordFromInput == null)
            return false;
        String ma5Password = MD5CoderUtil.encode(MD5CoderUtil.encode(passwordFromInput));
        return (ma5Password.equalsIgnoreCase(passwordFromDatabase));
    }


    public static String createPassword(String passwordFromInput){
        return MD5CoderUtil.encode(MD5CoderUtil.encode(passwordFromInput));
    }
}
