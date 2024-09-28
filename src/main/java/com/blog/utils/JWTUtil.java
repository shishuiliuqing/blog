package com.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    //令牌有效期1小时
    private static final int DURATION = 3600 * 1000;

    //加密算法
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

    //私钥
    private static final String SECRET = "f5h8ds45-aaf48er6-bs45ad2a-af55acv4";

    //签发者
    private static final String JTW_ISS = "JWT_JC";

    //JWT主题
    private static final String SUBJECT = "Login";

    //私钥实例
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * 生成JWT令牌
     *
     * @param id
     * @param username
     * @return
     */
    public static String generateJWT(Integer id, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);

        String jwt = Jwts.builder()
                //设计头部信息
                .header()
                .add("alg", "HS256")
                .add("typ", "JWT")
                .and()
                //设计自定义负载信息（用户ID和用户名）
                .claims(claims)
                //过期时间
                .expiration(new Date(System.currentTimeMillis() + DURATION))
                //主题
                .subject(SUBJECT)
                //签发人
                .issuer(JTW_ISS)
                //签名
                .signWith(KEY, ALGORITHM)
                .compact();
//        System.out.println(jwt);
        return jwt;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }
}
