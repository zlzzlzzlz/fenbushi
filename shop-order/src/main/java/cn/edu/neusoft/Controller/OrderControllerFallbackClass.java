package cn.edu.neusoft.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderControllerFallbackClass {
    public static String messageProductFallback(String str, Throwable t) {
        log.error("异常，参数信息：{}，异常信息：{}", str, t);
        return "messageProductFallback 异常，参数："+str+"，异常信息：" + t.getMessage();
    }
}