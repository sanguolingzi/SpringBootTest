package com.yinhetianze.jms.activemq;

import com.yinhetianze.core.utils.CommonUtil;

/**
 * 消息消费端数据转换处理对象
 * 消费端会使用此类处理jsonData转换后的数据
 * 作用于activemq5.1.2以后的版本，添加了包路径的安全认证
 */
public class MessageData
{
    /**
     * 消息数据的对象类型
     */
    private Object objectData;

    /**
     * 消息数据的字符串
     */
    private String stringData;

    /**
     * 是否是字符串，默认不是
     */
    private boolean isString;

    /**
     * 无参构造
     */
    public MessageData(){}

    /**
     * 数据构造
     * @param data
     */
    public MessageData(Object data)
    {
        setMainData(data);
    }

    public Object getMainData()
    {
        return isString ? stringData : objectData;
    }

    public void setMainData(Object mainData)
    {
        if (CommonUtil.isNotEmpty(mainData))
        {
            if (mainData instanceof String)
            {
                this.stringData = (String)mainData;
                this.isString = true;
            }
            else
            {
                this.objectData = mainData;
                this.isString = false;
            }
        }
    }

    public String getStringData()
    {
        return stringData;
    }

    public void setStringData(String stringData)
    {
        this.stringData = stringData;
    }

    public boolean getIsString()
    {
        return isString;
    }

    public Object getObjectData()
    {
        return objectData;
    }

    public void setObjectData(Object objectData)
    {
        this.objectData = objectData;
    }
}
