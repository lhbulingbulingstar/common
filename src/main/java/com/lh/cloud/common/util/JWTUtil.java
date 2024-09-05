package com.lh.cloud.common.util;

import com.lh.cloud.common.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: lh
 * @Date: 2021/12/29 14:51
 */
public class JWTUtil{

    /**
     * 生成Token
     * @param userKey   用户标识(唯一)
     * @param secretKey  签名算法以及密匙
     * @return
     */
        public static String generateToken(String userKey, String secretKey) {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        Map<String, Object> claims = new HashMap<>(1);
        claims.put(Constants.LOGIN_USER_KEY, userKey + "+" + uuid);
        String token =createToken(claims,secretKey);
        return token;
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String createToken(Map<String, Object> claims, String secret) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @param secret 密钥
     * @return 数据声明
     */
    private static Claims parseToken(String token,String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 通过令牌 密钥获取 userkey
     * @param token
     * @param secret
     * @return
     */
    public static String getUserKey(String token,String secret){
        try {
            Claims claims = parseToken(token, secret);
            if (claims != null && !claims.isEmpty()) {
                return (String) claims.get(Constants.LOGIN_USER_KEY);
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
