package com.michaelhoffmantech.jwtauthenticationserver.service;

import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Before
    public void setUp() {

    }

    @Test
    public void test_getAndVerifyTokenSuccessfully() throws Exception {

    }

}
