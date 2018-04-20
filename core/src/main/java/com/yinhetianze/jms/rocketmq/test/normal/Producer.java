
package com.yinhetianze.jms.rocketmq.test.normal;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 * OrderProducer，发送消息
 */
public class Producer {

    private static Random rand = new Random();
    private static int count = 5;

    /**
     * 这是一个模拟堆积消息的程序，生成的消息模型和我们比赛的消息模型是一样的，
     * 所以选手可以利用这个程序生成数据，做线下的测试。
     * @param args
     * @throws MQClientException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("producer");

        //在本地搭建好broker后,记得指定nameServer的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        final Semaphore semaphore = new Semaphore(0);

        for (int i = 0; i < count; i++) {
            try {

                Message msgToBroker = new Message("test", new String("asd_"+i).getBytes());
                //异步发送消息
                producer.send(msgToBroker, new SendCallback() {
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult.toString());
                        //semaphore.release();
                    }
                    public void onException(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        /*
        semaphore.acquire(count);

        //用一个short标识生产者停止生产数据
        byte [] zero = new  byte[]{0,0};
        Message endMsgTB = new Message(RaceConfig.MqTaobaoTradeTopic, zero);
        Message endMsgTM = new Message(RaceConfig.MqTmallTradeTopic, zero);
        Message endMsgPay = new Message(RaceConfig.MqPayTopic, zero);

        try {
            producer.send(endMsgTB);
            producer.send(endMsgTM);
            producer.send(endMsgPay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        producer.shutdown();
    }
}
