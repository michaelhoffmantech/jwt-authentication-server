package com.michaelhoffmantech.jwtauthenticationserver.resource;

import com.michaelhoffmantech.jwtauthenticationserver.service.SignatureService;
import com.michaelhoffmantech.jwtauthenticationserver.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TokenResource {

    private static final Logger log = LoggerFactory.getLogger(TokenResource.class);

    private final SignatureService signatureService;
    private final TokenService tokenService;

    @GetMapping(path = "/token", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authReqHeader
    ) throws Exception {
        String userId = null;
        String audience = null;
        String userFingerprint = null;
        String source = null;

        try {
            String base64Token =
                new String(Base64.getDecoder().decode(
                    authReqHeader.substring(6).
                        getBytes("UTF-8")), "UTF-8");
            StringTokenizer tokenizer = new StringTokenizer(base64Token, ":");
            if (tokenizer.countTokens() != 4) {
                throw new Exception("Incorrect number of request header tokens");
            }
            userId = tokenizer.nextToken();
            audience = tokenizer.nextToken();
            userFingerprint = tokenizer.nextToken();
            source = tokenizer.nextToken();
            return new ResponseEntity<String>(
                tokenService.getToken(userId, audience, userFingerprint, source), HttpStatus.OK);
        } catch (Exception e) {
            log.error("An error occurred reading the token", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
