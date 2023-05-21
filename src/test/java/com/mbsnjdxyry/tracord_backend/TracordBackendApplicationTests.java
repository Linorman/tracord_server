package com.mbsnjdxyry.tracord_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class TracordBackendApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void TestBCryptPasswordEncoder(){

//        $2a$10$npv5JSeFR6/wLz8BBMmSBOMb8byg2eyfK4/vvoBk3RKtTLBhIhcpy

        System.out.println(passwordEncoder.
                matches("1234",
                        "$2a$10$F48Sii8ktK9GTlAxbqjtKOzPMhKjxdvTYPuOdHA34IxdIY84NTOQG"));
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);

//        String encode2 = passwordEncoder.encode("1234");
//        System.out.println(encode2);

    }

}
