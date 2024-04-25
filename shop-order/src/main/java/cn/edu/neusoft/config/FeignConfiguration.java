package cn.edu.neusoft.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
