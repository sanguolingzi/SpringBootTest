package com.yinhetianze.jms.activemq;

import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;

/**
 * 消息实体工厂类
 * 提供获取消息实体类的方法
 * @author Administrator
 *
 */
public class AmqMessageFactory
{
    
    /**
     * 获得原始话题消息实体类
     * 需要自己指定发送消息目的地destinationName
     * @return
     */
    public static AmqMessage getTopicMessage()
    {
        return new AmqTopicMessage();
    }

    /**
     * 获得原始队列消息实体类
     * 需要自己指定发送消息目的地destinationName
     * @return
     */
    public static AmqMessage getQueueMessage()
    {
        return new AmqQueueMessage();
    }

    /**
     * 获得原始话题消息实体类
     * @return
     */
    public static AmqMessage getTopicMessage(String destinationName)
    {
        return new AmqTopicMessage(destinationName);
    }

    /**
     * 获得原始队列消息实体类
     * @return
     */
    public static AmqMessage getQueueMessage(String destinationName)
    {
        return new AmqQueueMessage(destinationName);
    }
    
    /**
     * 通过指定参数获取相对应的消息实体类
     * @param destination 消息目的地(队列/话题名称)
     * @param isPubSubDomain 订阅/队列类型，true订阅类型消息实体，false队列类型消息实体
     * @return
     */
    public static AmqMessage getAmqMessage(String destination, boolean isPubSubDomain)
    {
        return isPubSubDomain ? new AmqTopicMessage(destination) : new AmqQueueMessage(destination);
    }

    /**
     * 默认预处理消息AmqMessage，将json转成对象，或直接使用string类型消息数据
     * @param amqMessage
     * @return
     */
    public static MessageData convertMessage(AmqMessage amqMessage)
    {
        // 消息数据对象，
        MessageData messageData = new MessageData();

        if (CommonUtil.isNotEmpty(amqMessage))
        {
            // 消息生产者传递的消息对象的json字符串数据
            String jsonData = amqMessage.getJsonData();

            // 字符串消息
            messageData.setStringData(jsonData);
            if (CommonUtil.isNotEmpty(jsonData))
            {
                try
                {
                    // 将消息对象的json字符串转换成指定类型
                    Object data = CommonUtil.readFromString(jsonData, amqMessage.getDataClazz());
                    messageData.setMainData(data);
                }
                catch (Exception e)
                {
                    LoggerUtil.error(AbstractMessageConsumer.class, e);
                    // json串转换异常，直接赋值为string数据类型
                    messageData.setMainData(jsonData);
                }
            }
        }

        return messageData;
    }
    
}
