package cn.edu.neusoft.Controller;

import cn.edu.neusoft.model.User;
import cn.edu.neusoft.service.UserService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController


public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user/{uid}")
    public User queryUserById(@PathVariable("uid") Integer uid) throws
            InterruptedException{
        log.info("查询{}用户信息",uid);
        //模拟业务耗时 100ms
        if(uid == 1){
            Thread.sleep(100);
        }
        User user = userService.queryUserById(uid);
        log.info("用户信息查询成功，内容为：{}", JSON.toJSONString(user));
        return user;
    }
}