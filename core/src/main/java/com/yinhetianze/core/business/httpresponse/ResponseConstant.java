package com.yinhetianze.core.business.httpresponse;

/**
 * 结果响应常量类
 * @author Administrator
 *
 */
public class ResponseConstant
{
    
    /**
     * 系统默认结果消息
     */
    public static final String RESULT_MESSAGE_SUCCESS = "success"; // 业务成功
    public static final String RESULT_MESSAGE_FAILED = "failed"; // 业务失败
    public static final String RESULT_MESSAGE_EXCEPTION = "exception"; // 业务异常
    
    /**
     * 常用结果编码，可以实现针对错误编码对应错误信息
     */
    public static final String RESULT_CODE_SYS_EXCEPTION = "9999"; // 系统异常
    public static final String RESULT_CODE_BUSI_FAILED = "9998"; // 业务异常
    public static final String RESULT_CODE_SUCCESS = "0000"; // 业务成功

    public static final String RESULT_DESCRIPTION_SUCCESS = "操作成功"; // 业务成功
    public static final String RESULT_DESCRIPTION_FAILED = "操作失败"; // 业务未成功
    public static final String RESULT_DESCRIPTION_EXCEPTION = "系统异常"; // 系统异常
    
}
