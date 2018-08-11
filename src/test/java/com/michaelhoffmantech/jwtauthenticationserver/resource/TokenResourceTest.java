package com.michaelhoffmantech.jwtauthenticationserver.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenResourceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
           .webAppContextSetup(context)
                .build();
    }

    @Test
    public void test_tokenSuccess() throws Exception {
        String base64Token = new String(Base64.getEncoder().encode(
            "1:zeta:a1b2c3d4e5:alpha"
                .getBytes("UTF-8")), "UTF-8");
        MvcResult result = mvc
            .perform(get("/api/token")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + base64Token))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(result);
        assertNotNull(result.getResponse());
        assertNotNull(result.getResponse().getContentAsString());
    }


}
