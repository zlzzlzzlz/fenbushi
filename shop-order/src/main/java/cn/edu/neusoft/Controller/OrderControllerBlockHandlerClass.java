package cn.edu.neusoft.Controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderControllerBlockHandlerClass {
    public static String messageProductBlockHandler(String str, BlockException ex) {
        log.error("服务限流或熔断异常，参数信息：{}，异常信息：{}", str, ex);
        return "messageProductBlockHandler 服务限流或熔断异常，参数：" + str + "，异常信息：" + ex.getMessage();
    }
}