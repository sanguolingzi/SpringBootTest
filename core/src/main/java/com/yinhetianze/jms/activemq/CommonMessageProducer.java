package com.yinhetianze.jms.activemq;

import com.yinhetianze.core.utils.ApplicationContextFactory;
import com.yinhetianze.core.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 通用消息发送类
 * 可以发送topic，queue
 * @author Administrator
 *
 */
@Service
public class CommonMessageProducer implements MessageProducer
{

    private static Logger logger = LoggerFactory.getLogger(CommonMessageProducer.class);

    public void produceMessage(final AmqMessage message)
    {
        // 判空
        Assert.notNull(message, "消息对象不能为空");
        Assert.hasLength(message.getDestinationName(), "发送目的地名称不能为空！");
        
        // 普通jms模板
        final JmsTemplate template = (JmsTemplate) ApplicationContextFactory.getInstance().getBean("jmsTemplate");
        
        if (CommonUtil.isNotEmpty(template))
        {
            logger.info("获取JMS发送模板：{}", template.getClass());
            
            // 设置发送模式，p2p或pub/sub
            template.setPubSubDomain(message.getIsPubMode()); // true为订阅模式，false为队列模式
            
            // 设置是否持久化
            template.setDeliveryMode(message.getIsPersistence() ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
            logger.info("JMS是否是订阅模式：{}; 是否开始持久化 : {}", template.isPubSubDomain(), message.getIsPersistence());
            
            try
            {
                // 开始消息发送
                template.send(message.getDestinationName(), new MessageCreator()
                {
                    public Message createMessage(Session session) throws JMSException
                    {
                        return template.getMessageConverter().toMessage(message, session);
                    }
                });
                
                logger.info("发送信息成功！队列名称：{}", message.getDestinationName());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                logger.error("发送消息发生异常！异常信息为：{}", e.toString());
            }
        }
        else
        {
            logger.info("消息发送失败！没有适配的消息发送模板。");
        }
        
    }

}
