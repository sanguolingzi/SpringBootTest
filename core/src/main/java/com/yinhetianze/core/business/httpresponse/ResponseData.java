package com.yinhetianze.core.business.httpresponse;

import java.util.Map;

/**
 * 请求响应实体信息
 * 包含响应头信息，业务数据实体
 * @author Administrator
 *
 * @param <T>
 */
public class ResponseData<T>
{
    /**
     * 业务数据
     * 与errorInfo选填
     */
    private T data;
    
    /**
     * 结果信息
     * 成功或失败时选取data或者errorInfo
     * 必填项
     */
    private Result resultInfo;
    
    /**
     * 错误信息
     * 与data选填
     */
    private Map<String, Object> errorInfo;

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public Result getResultInfo()
    {
        return resultInfo;
    }

    public void setResultInfo(Result resultInfo)
    {
        this.resultInfo = resultInfo;
    }

    public Map<String, Object> getErrorInfo()
    {
        return errorInfo;
    }

    public void setErrorInfo(Map<String, Object> errorInfo)
    {
        this.errorInfo = errorInfo;
    }
}
