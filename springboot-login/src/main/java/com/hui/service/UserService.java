package com.hui.service;

import com.hui.bean.User;

import java.util.List;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/12/1
 */
public interface UserService {

    User login(User user);

    List<User> list();
}
