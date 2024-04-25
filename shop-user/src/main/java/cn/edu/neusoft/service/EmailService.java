package cn.edu.neusoft.service;

import cn.edu.neusoft.model.Order;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//consumerGroup 消费者组名，topic 消息主题，与发送发保持一致
@RocketMQMessageListener(topic = "order-topic",consumerGroup = "shop-user")
public class EmailService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{},接下来发送邮件", JSON.toJSONString(order));
    }
}