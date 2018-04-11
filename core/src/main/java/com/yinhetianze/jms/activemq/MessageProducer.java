package com.yinhetianze.jms.activemq;

/**
 * 消息队列：消息生产者
 * @author Administrator
 *
 */
public interface MessageProducer
{
    
    void produceMessage(final AmqMessage message);
    
}
