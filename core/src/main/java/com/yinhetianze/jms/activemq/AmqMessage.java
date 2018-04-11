package com.yinhetianze.jms.activemq;

import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;

import java.io.Serializable;
import java.util.Date;

public abstract class AmqMessage implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 6392548061285050772L;

    /**
     * 消息创建时间
     */
    private Date createTime;
    
    /**
     * 消息更新时间，即被消费/订阅时间
     */
    private Date updateTime;
    
    /**
     * 是否持久化
     */
    private Boolean isPersistence = false;
    
    /**
     * 是否是发布/订阅模式，默认false为队列，true为消息订阅
     */
    private Boolean isPubMode;
    
    /**
     * 消息发送目标
     */
    private String destinationName;
    
    /**
     * 数据主体
     */
//    @Deprecated
//    private Object mainData;

    /**
     * 基于activemq5.12-5.13版本之后
     * 需要对序列化的数据进行安全认证，使用传递的数据类型统一转变为json，作为字符串传输
     * 在通过dataClass字段标记数据类型，在消息消费端进行发序列化处理
     */
    private String jsonData;

    /**
     * 序列化数据的完整全类名
     */
    private String dataEntireClass;

    /**
     * 序列化数据的类型
     */
    private Class dataClazz;
    
    /**
     * 消息处理指令
     * 为消息消费者提供处理指令，消费者可以根据该指令进行特殊操作
     */
    private String msgHandleCommand;
    
    public String getMsgHandleCommand()
    {
        return msgHandleCommand;
    }

    public void setMsgHandleCommand(String msgHandleCommand)
    {
        this.msgHandleCommand = msgHandleCommand;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    /*public Object getMainData()
    {
        return mainData;
    }*/

    public void setMainData(Object mainData)
    {
//        this.mainData = mainData;
        if (CommonUtil.isNotEmpty(mainData))
        {
            this.dataClazz = mainData.getClass();
            this.dataEntireClass = dataClazz.getName();
            try
            {
                this.jsonData = CommonUtil.objectToJsonString(mainData);
            }
            catch (Exception e)
            {
                LoggerUtil.error(AmqMessage.class, e);
            }
        }
    }

    public String getDestinationName()
    {
        return destinationName;
    }

    public void setDestinationName(String destinationName)
    {
        this.destinationName = destinationName;
    }

    public Boolean getIsPubMode()
    {
        return isPubMode;
    }

    public void setIsPubMode(Boolean isPubMode)
    {
        this.isPubMode = isPubMode;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Boolean getIsPersistence()
    {
        return isPersistence;
    }

    public void setIsPersistence(Boolean isPersistence)
    {
        this.isPersistence = isPersistence;
    }

    public String getJsonData()
    {
        return jsonData;
    }

    public void setJsonData(String jsonData)
    {
        this.jsonData = jsonData;
    }

    public String getDataEntireClass()
    {
        return dataEntireClass;
    }

    public void setDataEntireClass(String dataEntireClass)
    {
        this.dataEntireClass = dataEntireClass;
    }

    public Class getDataClazz()
    {
        return dataClazz;
    }

    public void setDataClazz(Class dataClazz)
    {
        this.dataClazz = dataClazz;
    }
}
