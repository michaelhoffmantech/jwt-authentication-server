package com.michaelhoffmantech.jwtauthenticationserver.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SignatureService {

    @Value("${app.security.secret}")
    private String secret;

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}
