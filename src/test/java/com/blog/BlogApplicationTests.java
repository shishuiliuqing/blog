package com.blog;

import com.blog.utils.ImageUtil;
import com.blog.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

//@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() throws IOException {
        ImageUtil.delete("http://localhost:8080/images/default.png");
    }

    @Test
    void test() throws IOException {
//        ImageUtil.rollBack("http://localhost:8080/images/default.png");
        ImageUtil.clearBin();
    }
    @Test
    void a() {
        String s = JWTUtil.generateJWT(1,"hjc");
        System.out.println(s);
    }

    @Test
    void b() {
        Jws<Claims> jws = JWTUtil.parse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJoamMiLCJleHAiOjE3Mjc1MjE3NDksInN1YiI6IkxvZ2luIiwiaXNzIjoiSldUX0pDIn0.WvE8Y3eAK363zk4bfTrEM-YQnt--HM_FEbmHDSBwYMw");
        System.out.println(jws.getPayload().get("username"));
    }
}
