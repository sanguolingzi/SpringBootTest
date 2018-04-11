package com.yinhetianze.core.business.httpresponse;

import com.yinhetianze.core.utils.CommonUtil;

public class Result
{
    private String code;
    
    private String message;
    
    private String description = "";
    
    /**
     * 默认创建成功结果信息
     */
    public Result()
    {
        this.code = ResponseConstant.RESULT_CODE_SUCCESS;
        this.message = ResponseConstant.RESULT_MESSAGE_SUCCESS;
        this.description = ResponseConstant.RESULT_DESCRIPTION_SUCCESS;
    }
    
    /**
     * 默认创建指定消息的异常结果消息
     * @param description
     */
    public Result(String description)
    {
        this.code = ResponseConstant.RESULT_CODE_SYS_EXCEPTION;
        this.message = ResponseConstant.RESULT_MESSAGE_EXCEPTION;
        this.description = description;
    }
    
    /**
     * 指定错误码与消息信息
     * 成功消息码为0000，非此码一律为错误/异常消息结果
     * @param code
     * @param description
     */
    public Result(String code, String description)
    {
        if (ResponseConstant.RESULT_CODE_SUCCESS.equals(code))
        {
            this.code = ResponseConstant.RESULT_CODE_SUCCESS;
            this.message = ResponseConstant.RESULT_MESSAGE_SUCCESS;
            return;
        }
        else if (CommonUtil.isNotEmpty(code))
        {
            this.code = code;
            this.message = ResponseConstant.RESULT_MESSAGE_FAILED;
        }
        else
        {
            this.code = ResponseConstant.RESULT_CODE_SYS_EXCEPTION;
            this.message = ResponseConstant.RESULT_MESSAGE_EXCEPTION;
        }
        this.description = description;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
