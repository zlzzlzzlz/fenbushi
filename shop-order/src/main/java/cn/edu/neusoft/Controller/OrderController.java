package cn.edu.neusoft.Controller;

import cn.edu.neusoft.Service.OrderService;
import cn.edu.neusoft.Service.ProductServiceFeign;
import cn.edu.neusoft.Service.UserServiceFeign;
import cn.edu.neusoft.model.Order;
import cn.edu.neusoft.model.Product;
import cn.edu.neusoft.model.User;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private  ProductServiceFeign productServiceFeign;
    @Autowired
    private UserServiceFeign userServiceFeign;
    @RequestMapping("/order/product/{pid}")

    public Order getOrderById(@PathVariable("pid") Integer pid){


        //1.获取商品信息
//        log.info("收到{}号商品的下单请求，调用商品微服务查询此商品信息", pid);
//        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);
//        log.info("查询到{}号商品信息，商品内容为{}", pid, JSON.toJSONString(product));

        //ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);


        //loadbanlance负载均衡1

        //List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        //int index = new Random().nextInt(instances.size());
        //ServiceInstance serviceInstance = instances.get(index);
        //String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
        //log.info(">>从 nacos 中获取到的微服务地址为:" + url);
        //Product product = restTemplate.getForObject("http://"+url+"/product/" + pid, Product.class);

        //loadbanlance负载均衡2
        //Product product = restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
        //负载均衡4
        Product product = productServiceFeign.findByPid(pid);
        if(product.getPid() ==-100){
            Order order = new Order();
            order.setOid(-100L);
            order.setPname("下单失败");
            return order;
        }




        //2.创建订单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("zhangsan");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.addOrder(order);

        /****向MQ投递消息 Start****/

        //下单成功之后,将消息放到mq中
        //convertAndSend()方法中的第一个参数是topic，第二个参数是消息内容
        rocketMQTemplate.convertAndSend("order-topic", order);

        /****向MQ投递消息 end****/
        log.info("创建订单成功，订单信息为{}", JSON.toJSONString(order));
        return order;


    }
    @SentinelResource(value="hot_queryoid")
    @RequestMapping("/order/query/{oid}")
    public Order queryOrderById(@PathVariable("oid") Long oid){
        log.info("收到订单编号：{}请求，调用订单查询方法：queryOrderById()", oid);
        Order order = orderService.queryOrderById(oid);
        log.info("订单查询成功，订单信息为{}", JSON.toJSONString(order));
        return order;
    }
    @RequestMapping("/order/updatestatus/{oid}")
    public Order updateOrderStatusById(@PathVariable("oid") Long oid){
        log.info("收到订单编号：{}请求，调用订单状态修改方法：updateOrderStatus()", oid);
        orderService.updateOrderStatus(oid);
        Order order = orderService.queryOrderById(oid);
        log.info("订单状态修改成功，订单信息为{}", JSON.toJSONString(order));
        return order;
    }
    @RequestMapping("/order/queryproduct/{oid}")
    @SentinelResource(value="queryProduct")
    public Product queryProduct(@PathVariable("oid") Long oid){
        log.info("收到编号{}的订单，调用订单查询方法：queryOrderById()", oid);
        Order order = orderService.queryOrderById(oid);
        if(order !=null){
            Product product = productServiceFeign.findByPid(order.getPid());
            log.info("订单查询成功，订单信息为{}", JSON.toJSONString(product));
            return product;
        }else{
            log.info("订单查询失败，订单编号无效");
            return null;
        }
    }

    @RequestMapping("/order/queryall/{oid}")
    public Map<String,Object> queryOrderForALLById(@PathVariable("oid") Long oid){
        log.info("收到订单编号：{}请求，调用订单查询方法：queryOrderById()", oid);
        Map<String,Object> orderMap = new HashMap<>();
        Order order = orderService.queryOrderById(oid);
        orderMap.put("order", order);
        User user = userServiceFeign.findUserByUid(order.getUid());
        orderMap.put("user", user);
        log.info("查询成功，所有信息为{}", JSON.toJSONString(orderMap));
        return orderMap;
    }

    @RequestMapping("/order/msg")
    public String message(){
        return "message";
    }
    int i = 0;
    @SentinelResource(value = "mesageproduct",
            blockHandler = "messageProductBlockHandler",
            fallback = "messageProductFallback",
            blockHandlerClass = OrderControllerBlockHandlerClass.class,
            fallbackClass = OrderControllerBlockHandlerClass.class)

    @RequestMapping("/order/msgproduct/{str}")
    public String messageProduct(@PathVariable("str") String str) {
        log.info("参数信息：{}", str);
// 模拟程序出现异常
        if (++i % 3 == 0) {
            throw new RuntimeException("messageProduct()方法模拟异常");
        }
        return "OrderController：" + str;
    }
    public String messageProductBlockHandler(String str, BlockException ex) {
        log.error("服务限流或熔断异常，参数信息：{}，异常信息：{}", str, ex);
        return "messageProductBlockHandler 服务限流或熔断异常，参数：" + str + "，异常信息：" + ex.getMessage();
    }
    public String messageProductFallback(String str, Throwable t) {
        log.error("异常，参数信息：{}，异常信息：{}", str, t);
        return "messageProductFallback 异常，参数："+str+"，异常信息：" + t.getMessage();
    }
    //基于RocketMQ实现消息发送
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


}