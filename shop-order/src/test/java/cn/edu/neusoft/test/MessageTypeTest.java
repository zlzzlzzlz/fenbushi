package cn.edu.neusoft.test;

import cn.edu.neusoft.OrderApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**************************普通消息
     start***************************************/
    //普通消息-同步消息
    @Test
    public void testSyncSend() {
        //参数 1：topic:tag
        //参数 2：消息体
        //参数 3：超时时间
        SendResult result = rocketMQTemplate.syncSend("test-topic-1:tag1", "这是一条同步消息 ", 10000);
        System.out.println(result);
    }

    //普通消息-异步消息
    @Test
    public void testAsyncSend() throws InterruptedException {
        //参数 1：topic:tag
        //参数 2：消息体
        //参数 3：回调对象
        rocketMQTemplate.asyncSend("test-topic-1:tag2", "这是一条异步消息", new
                SendCallback() {
                    //成功响应的回调方法
                    @Override
                    public void onSuccess(SendResult result) {
                        System.out.println(result);
                    }

                    //异常响应的回调方法
                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println(throwable);
                    }
                });
        System.out.println("==============================");
        Thread.sleep(300000000);
    }

    //普通消息-单向消息
    @Test
    public void testOneWay() {
        rocketMQTemplate.sendOneWay("test-topic-1:tag3", "这是一条单向消息");
    }
    /**************************普通消息 end***************************************/
    /**************************顺序消息
     start***************************************/
    //顺序消息-同步消息
    @Test
    public void testSyncSendOrderly() {
        //参数 1：topic:tag
        //参数 2：消息体
        //参数 3：用于队列的选择
        //参数 4：超时时间
        rocketMQTemplate.syncSendOrderly("test-topic-2:tag1", "这是一条同步顺序消息",
                "001", 100000);
    }

    //顺序消息-同步消息
    @Test
    public void testAsyncSendOrderly() throws InterruptedException {
        //参数 1：topic:tag
        //参数 2：消息体
        //参数 3：用于队列的选择
        //参数 4：回调对象
        rocketMQTemplate.asyncSendOrderly("test-topic-2:tag2", "这是一条异步消息", "001", new
                SendCallback() {
                    //成功响应的回调方法
                    @Override
                    public void onSuccess(SendResult result) {
                        System.out.println(result);
                    }

                    //异常响应的回调方法
                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println(throwable);
                    }
                });
        System.out.println("==============================");
        Thread.sleep(300000000);
    }

    //顺序消息-同步消息
    @Test
    public void testOneWayOrderly() {
        //参数 1：topic:tag
        //参数 2：消息体
        //参数 3：用于队列的选择
        rocketMQTemplate.sendOneWayOrderly("test-topic-2:tag3", "这是一条同步顺序消息 ", "001");
    }
    /**************************顺序消息 end***************************************/
}