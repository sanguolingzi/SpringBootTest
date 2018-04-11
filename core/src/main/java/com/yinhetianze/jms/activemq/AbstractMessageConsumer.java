package com.yinhetianze.jms.activemq;

import com.yinhetianze.core.utils.ApplicationContextFactory;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.UUID;

/**
 * 消息消费者模板类
 * 使用配置文件 jms:listener-container 中注册
 * 例如：
 * <jms:listnener-container destination-type="queue" container-type="default" connection-factory="connectionFactorySpringId" acknowledge="auto">
 *      <jms:listener destination="queue.destination.name" ref="springBeanId"/>
 * </jms:listnener-container>
 * @author Administrator
 *
 */
public abstract class AbstractMessageConsumer implements MessageListener
{
    private static Logger logger = LoggerFactory.getLogger(AbstractMessageConsumer.class);
    
    protected String consumerId;
    
    public AbstractMessageConsumer()
    {
        consumerId = UUID.randomUUID().toString();
    }
    
    public final void onMessage(Message message)
    {
        logger.info("接收消息！消费者ID为：", consumerId);
        try
        {
            // 默认配置的消息转换类
            MessageConverter messageConverter = (MessageConverter) ApplicationContextFactory.getInstance().getBean("jmsMessageConverter");
            if (CommonUtil.isEmpty(messageConverter))
            {
                messageConverter = new SimpleMessageConverter();
            }
            logger.debug("消息转换器是：{}", messageConverter.getClass());
            
            // 转化为amq自定义消息处理对象
            AmqMessage amqMessage = (AmqMessage) messageConverter.fromMessage(message);
            logger.debug("消息来自 {}, 消息产生时间为：{}, 消息类型是否为话题订阅：{}",
                    new Object[]{amqMessage.getDestinationName(), 
                            String.valueOf(amqMessage.getCreateTime()), 
                            String.valueOf(amqMessage.getIsPubMode())});
            
            // 消息转换，预处理，转成map数据格式
            MessageData data = convertMessage(amqMessage);

            if (CommonUtil.isNotEmpty(data))
            {
                // 消息处理最终执行，由子类实现
                handleMessage(data);
            }
            else
            {
                LoggerUtil.info(AbstractMessageConsumer.class, "消息处理");
            }

            LoggerUtil.debug(AbstractMessageConsumer.class, "MQ消费的信息是：{}", new Object[]{data + ""});
            logger.info("消息处理完毕！");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("消息处理发生异常：{} ", e.toString());
        }
    }

    /**
     * 默认预处理消息AmqMessage，将json转成对象，或直接使用string类型消息数据
     * @param amqMessage
     * @return
     */
    protected MessageData convertMessage(AmqMessage amqMessage)
    {
        // 消息数据对象，
        MessageData messageData = new MessageData();

        if (CommonUtil.isNotEmpty(amqMessage))
        {
            // 字符串消息
            messageData.setStringData(amqMessage.getJsonData());

            // 消息生产者传递的消息对象的json字符串数据
            String jsonData = amqMessage.getJsonData();
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

    /**
     * 子类自行实现处理消息的方法
     * @param obj
     */
    public abstract void handleMessage(MessageData obj);

}
