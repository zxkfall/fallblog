package com.flywinter.fallblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class FallblogApplicationTests {

    @Test
    void contextLoads() {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456"); //{bcrypt}$2a$10$QeOl0J8ZiiGWv7W4znoleOQMceQEkz9aC4pkHXFAEzek1R1Ctnn/m
        System.out.printf(encode);
    }

}

