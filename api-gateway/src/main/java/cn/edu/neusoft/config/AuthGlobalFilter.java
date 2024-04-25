package cn.edu.neusoft.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    //过滤器逻辑
    //filter 方法中的参数 ServerWebExchange，表示当前请求和响应的上下文，GatewayFilterChain，表示当前请求的过滤器链
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(!StringUtils.equals("admin",token)) {
            log.error("认证失败....");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //调用 chain.filter 放行
        log.info("认证成功....");
        return chain.filter(exchange);
    }
    //标识当前过滤器的优先级，返回值越小，优先级越高，此处默认用 0
    @Override
    public int getOrder() {
        return 0;
    }
}