package com.hui.mapper;

import com.hui.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/11/20
 */
@Repository
@Mapper
public interface LoginMapper {

    @Select("select * from user")
    List<User> list();

    @Select("select * from user where username=#{username} and password=#{password}")
    User login(User user);

}
