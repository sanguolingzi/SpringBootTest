package com.yinhetianze.jms.rocketmq.test.order;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.*;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.yinhetianze.jms.rocketmq.RaceConfig;
import com.yinhetianze.jms.rocketmq.RaceUtils;
import com.yinhetianze.jms.rocketmq.model.PaymentMessage;

import java.util.List;


/**
 * OrderConsumer，订阅消息
 */

/**
 * RocketMq消费组信息我们都会再正式提交代码前告知选手
 */
public class OrderConsumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("orderMessageGroup");

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //在本地搭建好broker后,记得指定nameServer的地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("orderMessage", "*");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                //System.out.println("here start "+Thread.currentThread().getName());
                for (MessageExt msg : list) {
                    System.out.println(msg.toString());
                    System.out.println(new String(msg.getBody()));
                    System.out.println("BuyerId:"+msg.getBuyerId());
                    System.out.println("MsgId:"+msg.getMsgId());
                    System.out.println("QueueId:"+msg.getQueueId());
                    System.out.println("QueueOffset:"+msg.getQueueOffset());
                }
                //System.out.println("here end "+Thread.currentThread().getName());
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

        System.out.println("OrderConsumer Started.");
    }
}
