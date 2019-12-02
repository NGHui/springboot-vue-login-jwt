package com.hui.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/11/22
 */
@Component
//@ConfigurationProperties(prefix = "jwt.config")
public class JwtUtils {

    @Value("${jwt.config.key}")
    private String key;

    @Value("${jwt.config.ttl}")
    private Long ttl;

    @Value("${jwt.config.claimsKey}")
    private String claimsKey;

    @Value("${jwt.config.claimsValue}")
    private Object claimsValue;

    public String getClaimsKey() {
        return claimsKey;
    }

    public void setClaimsKey(String claimsKey) {
        this.claimsKey = claimsKey;
    }

    public Object getClaimsValue() {
        return claimsValue;
    }

    public void setClaimsValue(Object claimsValue) {
        this.claimsValue = claimsValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    /*
     * 创建token
     *     id : 表示的时用户登陆id
     *     name : 表示的时用户名
     */
    public  String createToken(String id, String name
            /*使用map后取不到id,名户名的值 Map<String,Object> map*/
            /*只能使用传值,或者配置文件取值的方式String claimsKey, Object claimsValue*/) {

        //设置失效时间
        long nowTime = System.currentTimeMillis();//当前毫秒
        long exp = nowTime + ttl;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())
                //传递的需要是一个json格式
                .signWith(SignatureAlgorithm.HS256, key)
                //3.根据map设置claims
                .claim(claimsKey, claimsValue);

        jwtBuilder.setExpiration(new Date(exp));//失效时间的设置
        //4.创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /*
     * 解析token
     *
     *
     *  */
    public Claims parseToken(String token) {

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            return null;
        } catch (SignatureException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        JwtUtils jwtUtils = new JwtUtils();
        String zs = jwtUtils.createToken("1", "zs");
        System.out.println(zs);
        //Claims claims = jwtUtils.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMyIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTc1MjAwMjQwLCJleUpoYkdjaU9pSklVekkxTmlKOSI6Ilg3WFcwckhxRWhEZWx0VUVOWTVvLWtCX2NJR0lMWFFvZzY4V29NYjk3VjRYN1hXMHJIcXNkc3ZoRGVsdFVFTlk1by1rQl9jSUdJTFhRb2c2OFdvTWI5N1Y0IiwiZXhwIjoxNTc1MjM2MjQwfQ.iPsXDyiukjsZSu_u2rU9VnZDotIQLMvCLiPAnFlgE7M");
        //System.out.println(claims.getSubject());
    }
}
