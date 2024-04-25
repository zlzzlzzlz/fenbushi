package cn.edu.neusoft.config;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }
    //读取配置文件中的参数值，赋值到配置类的属性上
    @Override
    public List<String> shortcutFieldOrder() {
        //返回值的顺序必须和配置文件中的参数顺序一致
        return Arrays.asList("minAge","maxAge");
    }
    //断言逻辑
    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //1. 获取请求参数中的 age
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                //2、先判断是否为空
                if(StringUtils.isNotEmpty(ageStr)){
                    int age = Integer.parseInt(ageStr);
                    //3、如果不为空，则进行路由逻辑判断
                    if(age < config.getMaxAge() && age > config.getMinAge()){
                        return true;
                    }else {
                        return false;
                    }
                }
                return false;
            }
        };
    }

    //配置类（内部类），用于接收配置文件中的对应参数
    @Data
    @NoArgsConstructor
    public static class Config{
        private int minAge;
        private int maxAge;
    }
}