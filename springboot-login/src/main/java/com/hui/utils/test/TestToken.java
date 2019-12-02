package com.hui.utils.test;

import com.hui.utils.JwtUtils;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/12/1
 */
public class TestToken {
    public static void main(String[] args) {
        JwtUtils jwtUtils = new JwtUtils();
        String zs = jwtUtils.createToken("1", "zs");
        System.out.println(zs);
    }
}
