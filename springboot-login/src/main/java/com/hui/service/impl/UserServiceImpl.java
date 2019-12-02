package com.hui.service.impl;

import com.hui.bean.User;
import com.hui.mapper.LoginMapper;
import com.hui.service.UserService;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/12/1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User login(User user) {
        return loginMapper.login(user);
    }

    @Override
    public List<User> list() {
        return loginMapper.list();
    }
}
