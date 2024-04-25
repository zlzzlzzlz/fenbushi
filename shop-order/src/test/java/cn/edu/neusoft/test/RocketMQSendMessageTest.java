package cn.edu.neusoft.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class RocketMQSendMessageTest {
    //发送消息
    public static void main(String[] args) throws Exception {

        //1、常见消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");

        //2、为生产者指定Nameserver地址
        producer.setNamesrvAddr("192.168.56.101:9876");

        //3、启动生产者producer
        producer.start();

        //4、创建消息对象，设置消息主题Topic、标签Tag和消息内容Content
        Message message = new Message("myTopic ","myTag",("Test RocketMQ Message").getBytes());

        //5、发送消息,第二个参数，代表超时时间
        SendResult result = producer.send(message,10000);
        System.out.println(result);

        //6、关闭生产者producer
        producer.shutdown();
    }
}
