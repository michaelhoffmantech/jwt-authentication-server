package com.michaelhoffmantech.jwtauthenticationserver.service;

import static org.junit.Assert.*;

import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignatureServiceTest {

    @Autowired
    private SignatureService signatureService;

    @Before
    public void setUp() {

    }

    @Test
    public void test_getAlgorithm() throws Exception {
        Algorithm algo = signatureService.getAlgorithm();
        assertNotNull(algo);
        assertEquals("HS256", algo.getName());
    }

}
