package cn.edu.neusoft.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class LogGatewayFilterFactory extends
        AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {
    //构造方法
    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }
    //读取配置文件中的参数值，赋值到配置类的属性上
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }
    //过滤器逻辑
    @Override
    public GatewayFilter apply(LogGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.isCacheLog()) {
                    System.out.println("cacheLog 已经开启了....");
                }
                if (config.isConsoleLog()) {
                    System.out.println("consoleLog 已经开启了....");
                }
                return chain.filter(exchange);
            }
        };
    }
    //配置类（内部类），用于接收配置文件中的对应参数
    @Data
    @NoArgsConstructor
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;//对应yml23 24 行
    }
}