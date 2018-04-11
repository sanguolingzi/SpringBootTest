package com.yinhetianze.core.business;

import com.yinhetianze.core.utils.CommonUtil;

/**
 * 本地自定义业务异常类
 * @author Administrator
 *
 */
public class BusinessException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = -2598940140799205683L;
    
    /**
     * 系统异常，未处理的本地系统运行时异常信息
     */
    private static final String DEFAULT_ERROR_MESSAGE = "System error.";
    
    /**
     * 默认异常编码
     */
    private static final String DEFAULT_ERROR_CODE = "9998";
    
    /**
     * 系统异常编码
     */
    private static final String SYSTEM_ERROR_CODE = "9999";
    
    /**
     * 错误信息
     */
    private String errorMessage;
    
    /**
     * 错误编码
     */
    private String errorCode;
    
    /**
     * Throwable对象
     */
    private Throwable throwable;
    
    /**
     * 默认系统构造函数
     * 对应运行时异常
     */
    public BusinessException()
    {
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorMessage = DEFAULT_ERROR_MESSAGE;
    }
    
    /**
     * 带参构造
     * @param e
     *      throwable对象
     */
    public BusinessException(Throwable e)
    {
        if (e instanceof BusinessException)
        {
            BusinessException be = (BusinessException) e;
            this.errorCode = be.getErrorCode();
            this.errorMessage = be.getErrorMessage();
        }
        else
        {
            if (CommonUtil.isNotEmpty(e.getMessage()))
            {
                this.errorMessage = e.getMessage();
            }
            else if (CommonUtil.isNotEmpty(e.getCause()))
            {
//                this.errorMessage = e.getCause().getMessage();
                this.errorMessage = getExceptionMessage(e.getCause());
            }
            else
            {
                this.errorMessage = e.toString();
            }
            this.throwable = e;
            this.errorCode = SYSTEM_ERROR_CODE;
        }
    }

    private String getExceptionMessage(Throwable e)
    {
        // 获取最底层的异常。1,如果cause不为空，则递归cause；2,如果cause为空，则取e的信息；3,为空则返回空串
        if (CommonUtil.isNotEmpty(e) && CommonUtil.isNotEmpty(e.getCause()))
        {
            // 递归cause
            return getExceptionMessage(e.getCause());
        }
        else if (CommonUtil.isNotEmpty(e))
        {
            // 取e的异常信息
//            return e.toString();
            return e.getMessage();
        }
        else
        {
            return "";
        }
    }
    
    /**
     * 错误信息构造函数
     * @param message 错误信息
     */
    public BusinessException(String message)
    {
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorMessage = message;
    }
    
    /**
     * 指定错误编码构造函数，最精确的错误异常
     * 
     * @param errorCode 
     *      指定错误编码
     * @param errorMessage
     *      指定错误信息
     */
    public BusinessException(String errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public Throwable getThrowable()
    {
        return throwable;
    }

    public void setThrowable(Throwable throwable)
    {
        this.throwable = throwable;
    }
    
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        
        sb.append(this.getClass().getName()).append("：").append(this.getErrorCode()).append("=").append(this.getErrorMessage());
        
        return sb.toString();
    }
    
}
