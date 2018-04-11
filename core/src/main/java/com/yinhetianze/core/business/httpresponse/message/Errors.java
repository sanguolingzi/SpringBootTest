package com.yinhetianze.core.business.httpresponse.message;

public class Errors
{
    private String errorCode;
    
    private String defaultMessage;
    
    private String errorField;
    
    public Errors()
    {
    }
    
    public Errors(String field, String errorCode)
    {
        this.errorField = field;
        this.errorCode = errorCode;
    }
    
    public Errors(String field, String errorCode, String defaultMessage)
    {
        this.errorField = field;
        this.errorCode = errorCode;
        this.defaultMessage = defaultMessage;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorField()
    {
        return errorField;
    }

    public void setErrorField(String errorField)
    {
        this.errorField = errorField;
    }

    public String getDefaultMessage()
    {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage)
    {
        this.defaultMessage = defaultMessage;
    }
}
