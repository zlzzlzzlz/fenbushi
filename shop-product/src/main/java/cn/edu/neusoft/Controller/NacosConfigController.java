package cn.edu.neusoft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
public class NacosConfigController{
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("/nacos-config-test1")
    public String nacosConfigTest1(){
        return applicationContext.getEnvironment().getProperty("config.appName");
    }

    @Value("${config.appName}")
    private String appName;

    //2 注解方式
    @GetMapping("/nacos-config-test2")
    public String nacosConfingTest2() {
        return appName;
    }

    @Value("${config.env}")
    private String env;

    @GetMapping("/nacos-config-env")
    public String nacosConfingTest3() {
        return env;
    }
}