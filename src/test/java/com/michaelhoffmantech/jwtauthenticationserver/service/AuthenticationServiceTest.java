package com.michaelhoffmantech.jwtauthenticationserver.service;

import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Before
    public void setUp() {

    }

    @Test
    public void test_verifyUser() throws Exception {
        assertTrue(authenticationService.verifyUser("1"));
        assertFalse(authenticationService.verifyUser("2"));
    }

}
