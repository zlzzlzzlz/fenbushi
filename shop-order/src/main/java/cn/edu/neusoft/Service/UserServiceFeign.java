package cn.edu.neusoft.Service;

import cn.edu.neusoft.config.FeignConfiguration;
import cn.edu.neusoft.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-user",configuration = FeignConfiguration.class)
public interface UserServiceFeign {
    @RequestMapping("/user/{uid}")
    User findUserByUid(@PathVariable("uid") Integer uid);
}
