package cn.edu.neusoft.service;

import cn.edu.neusoft.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User queryUserById(Integer uid);
}