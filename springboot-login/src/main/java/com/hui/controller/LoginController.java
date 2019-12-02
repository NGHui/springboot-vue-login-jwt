package com.hui.controller;

import com.hui.bean.User;
import com.hui.commoms.ResultData;
import com.hui.service.UserService;
import com.hui.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/12/1
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResultData login(@RequestBody User user) {

        User login = userService.login(user);
        if (login == null) {
            return ResultData.fail().add("error", "账号或密码错误!");
        } else {
            String id = String.valueOf(login.getId());
            String token = jwtUtils.createToken(id, user.getUsername());
            return ResultData.success(token).add("success", "登陆成功!");
        }
    }

    @GetMapping("/list")
    public ResultData list(HttpServletRequest request) {
        Claims claims = (Claims)request.getAttribute("user_claims");
        if (claims!=null){
            /**
             * 验证前端传过来的 authorization是否可以被解析
             */
            /*String token = request.getHeader("authorization");
            if (token!=null){
                claims = jwtUtils.parseToken(token);
                String id = claims.getId();
                System.out.println(id);
            }*/
            return ResultData.success();
        }else {
            return ResultData.fail();
        }

    }

    @GetMapping("/token")
    public String token(){
        /*String zs = jwtUtils.createToken("1", "zs");*/
        Claims claims = jwtUtils.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTc1MjAyMDA3LCJleUpoYkdjaU9pSklVekkxTmlKOSI6Ilg3WFcwckhxRWhEZWx0VUVOWTVvLWtCX2NJR0lMWFFvZzY4V29NYjk3VjRYN1hXMHJIcXNkc3ZoRGVsdFVFTlk1by1rQl9jSUdJTFhRb2c2OFdvTWI5N1Y0IiwiZXhwIjoxNTc1MjM4MDA3fQ.vZ-Xd_ed6yvPeKKiamJuLverwjF0x_8QBi35_bVpSks");
        String subject = claims.getSubject();
        return subject;
    }
    
}
