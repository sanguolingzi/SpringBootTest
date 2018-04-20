
package com.yinhetianze.jms.rocketmq.test.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.*;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 * OrderProducer，发送消息
 */
public class TransactionProducer {

    private static Random rand = new Random();
    private static int count = 5;

    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("transactionProducer");
        //在本地搭建好broker后,记得指定nameServer的地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);

        //设置发送到mq失败 重试次数
        producer.setRetryTimesWhenSendFailed(10);


        producer.setTransactionCheckListener(new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
                System.out.println("-------here----------");
                int seed = rand.nextInt(10);
                if(seed%2==0){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;


            }
        });

        producer.start();
        final Semaphore semaphore = new Semaphore(0);

        for (int i = 0; i < count; i++) {
            try {

                Message msgToBroker = new Message("transaction", new String("事务消息"+i).getBytes());
                TransactionSendResult transactionSendResult =  producer.sendMessageInTransaction(msgToBroker, new LocalTransactionExecuter() {
                    @Override
                    public LocalTransactionState executeLocalTransactionBranch(Message message, Object o) {
                        //执行本地事务 执行成功 则 确认消息 否则回滚
                        System.out.println("----executeLocalTransactionBranch--------"+message+",,,,,,o:"+o);
                        int seed = rand.nextInt(10);
                        if(seed%2==0){
                            return LocalTransactionState.COMMIT_MESSAGE;
                        }
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                },"abc");

                System.out.println(transactionSendResult);
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
