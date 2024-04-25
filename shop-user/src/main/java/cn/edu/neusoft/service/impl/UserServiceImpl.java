package cn.edu.neusoft.service.impl;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.service.UserService;
import cn.edu.neusoft.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User queryUserById(Integer uid) {
        User user = userDao.selectById(uid);
        return user;
    }
}