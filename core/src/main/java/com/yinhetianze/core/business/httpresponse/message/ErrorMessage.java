package com.yinhetianze.core.business.httpresponse.message;

import com.yinhetianze.core.utils.CommonConstant;
import com.yinhetianze.core.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorMessage
{
    /**
     * 错误信息标识
     */
    public static final String ERROR_MESSAGE = "errorMessage";
    
    /**
     * 校验错误信息集
     */
    private Map<String, Object> errors;
    
    /**
     * 校验时添加的错误信息
     */
    private List<Errors> errorList;
    
    /**
     * 错误参数集合
     * 用于带占位参数的错误信息
     */
    private Map<String, Object[]> errorParams;
    
    /**
     * 错误跳转路径
     */
    private String errorPath;

    /**
     * 错误跳转页面后置构造函数
     * 如果不手动设定跳转错误页面，默认404错误页面
     */
    public ErrorMessage()
    {
        errors = new HashMap<String, Object>();
        errorList = new ArrayList<Errors>();
        errorParams = new HashMap<String, Object[]>();
    }
    
    /**
     * 获取上一个请求的地址构造函数
     * @param request
     */
    public ErrorMessage(HttpServletRequest request)
    {
        errors = new HashMap<String, Object>();
        errorList = new ArrayList<Errors>();
        errorParams = new HashMap<String, Object[]>();
        errorPath = request.getHeader("Referer");
    }
    
    public boolean isEmpty()
    {
        return errorList.isEmpty();
    }

    /**
     * 判断是否有错误信息
     * true=有，false=无
     * @return
     */
    public Boolean hasError()
    {
        return !isEmpty();
    }
    
    /**
     * 重载错误信息处理方法
     * @param field
     * @param code
     * @param objs
     */
    public void rejectError(String field, String code, Object...objs)
    {
        rejectError(field, code, code, objs);
    }
    
    /**
     * 对于提示信息有占位替换关系的错误信息处理
     * @param field
     * @param code
     * @param defaultMessage
     * @param objs
     */
    public void rejectError(String field, String code, String defaultMessage, Object...objs)
    {
        if (CommonUtil.isNotEmpty(field))
        {
            // 如果code为空，以filed替换code
            Errors error = new Errors(field, CommonUtil.isNotEmpty(code) ? code : field, defaultMessage);
            errorList.add(error);
            errorParams.put(field, objs);
        }
    }
    
    /**
     * 重载错误信息提示方法
     * @param field
     * @param code
     */
    public void rejectError(String field, String code)
    {
        rejectError(field, code, code);
    }
    
    /**
     * 错误信息提示方法
     * @param field
     * @param code
     * @param defaultMessage
     */
    public void rejectError(String field, String code, String defaultMessage)
    {
        if (CommonUtil.isNotEmpty(field))
        {
            Errors error = new Errors();
            error.setErrorField(field);
            error.setErrorCode(code);
            error.setDefaultMessage(defaultMessage);
            
            errorList.add(error);
        }
    }
    
    /**
     * 重载设置错误信息
     * @param field
     * @param message
     */
    public void rejectErrorMessage(String field, String message)
    {
        rejectErrorMessage(field, field, "");
    }
    
    /**
     * 直接设置错误提示信息
     * @param field
     * @param message
     * @param params
     */
    public void rejectErrorMessage(String field, String message, Object... params)
    {
        if (CommonUtil.isNotEmpty(field))
        {
            Errors error = new Errors();
            error.setErrorField(field);
            error.setErrorCode(field);
            error.setDefaultMessage(message);

            if (CommonUtil.isNotEmpty(params))
            {
                errorParams.put(field, params);
            }
            errorList.add(error);
        }
    }
    
    /**
     * 不能为空
     * @param field 字段名称
     * @param value 校验值
     * @param params 占位参数
     */
    public void rejectNull(String field, String value, Object...params)
    {
        if (CommonUtil.isEmpty(value))
        {
            if (CommonUtil.isNotEmpty(params))
            {
                params = new Object[]{field};
            }
            rejectError(field, "BC0006", params);
        }
    }
    
    /**
     * 长度校验
     * @param field 字段名称
     * @param value 校验值
     * @param fieldName 字段名称
     * @param min 最小值
     * @param max 最大值
     */
    public void rejectLength(String field, String value, String fieldName, Integer min, Integer max)
    {
        if (CommonUtil.isNotEmpty(value))
        {
            if (CommonUtil.isEmpty(fieldName))
            {
                fieldName = field;
            }
            if (CommonUtil.isNotEmpty(min) && CommonUtil.isNotEmpty(max))
            {
                if (value.length() < min || value.length() > max)
                {
                    rejectError(field, "BC0010", new Object[]{fieldName, min, max});
                }
            }
            else if (CommonUtil.isEmpty(min) && CommonUtil.isNotEmpty(max))
            {
                if (value.length() > max)
                {
                    rejectError(field, "BC0012", new Object[]{fieldName, max});
                }
            }
            else if (CommonUtil.isEmpty(max) && CommonUtil.isNotEmpty(min))
            {
                if (value.length() < min)
                {
                    rejectError(field, "BC0011", new Object[]{fieldName, min});
                }
            }
        }
    }
    
    /**
     * 判断长度不能小于 @param min
     * @param field
     * @param value
     * @param fieldName
     * @param min
     */
    public void rejectMinLength(String field, String value, String fieldName, Integer min)
    {
        rejectLength(field, value, fieldName, min, null);
    }
    
    /**
     * 判断长度不能大于 @param max
     * @param field
     * @param value
     * @param fieldName
     * @param max
     */
    public void rejectMaxLength(String field, String value, String fieldName, Integer max)
    {
        rejectLength(field, value, fieldName, null, max);
    }
    
    /**
     * 判断是否纯数字
     * @param field
     * @param value
     * @param fieldName
     */
    public void rejectDigital(String field, String value, Object...fieldName)
    {
        if (CommonUtil.isNotEmpty(value))
        {
            if (!value.matches(CommonConstant.DIGITAL_REGEX))
            {
                if (CommonUtil.isEmpty(fieldName))
                {
                    rejectError(field, "BC0015", "", field);
                }
                else
                {
                    rejectError(field, "BC0015", fieldName);
                }
            }
        }
    }

    /**
     * 判断是否是金额
     * @param field
     * @param value
     * @param fieldName
     */
    public void rejectMoney(String field, String value, Object...fieldName)
    {
        if (CommonUtil.isNotEmpty(value))
        {
            if (!value.matches(CommonConstant.MONEY_REGEX))
            {
                rejectError(field, "BC0015", fieldName);
            }
            BigDecimal money=new BigDecimal(value);
            if(money.doubleValue()>10000000){
                rejectError(field, "BC0088", fieldName);
            }
            if(money.doubleValue() == 0){
                rejectError(field, "BC0047",fieldName);
            }
        }
    }
    
    /**
     * 判断是否纯字符
     * @param field
     * @param value
     * @param fieldName
     */
    public void rejectChar(String field, String value, Object...fieldName)
    {
        if (CommonUtil.isNotEmpty(value))
        {
            if (!value.matches(CommonConstant.CHAR_REGEX))
            {
                rejectError(field, "BC0014", fieldName);
            }
        }
    }
    
    /**
     * 判断邮箱格式是否正确
     * @param field
     * @param value
     */
    public void rejectEmail(String field, String value)
    {
        if (CommonUtil.isNotEmpty(value))
        {
            if (!value.matches(CommonConstant.EMAIL_REGEX))
            {
                rejectError(field, "BC0013");
            }
        }
    }

    public Map<String, Object> getErrors()
    {
        return errors;
    }

    public void setErrors(Map<String, Object> errors)
    {
        this.errors = errors;
    }

    public String getErrorPath()
    {
        return errorPath;
    }

    public void setErrorPath(String errorPath)
    {
        this.errorPath = errorPath;
    }

    public List<Errors> getErrorList()
    {
        return errorList;
    }

    public void setErrorList(List<Errors> errorList)
    {
        this.errorList = errorList;
    }

    public Map<String, Object[]> getErrorParams()
    {
        return errorParams;
    }

    public void setErrorParams(Map<String, Object[]> errorParams)
    {
        this.errorParams = errorParams;
    }
}
