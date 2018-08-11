package com.michaelhoffmantech.jwtauthenticationserver.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    private final SignatureService signatureService;
    private final AuthenticationService authenticationService;

    public String getToken(String userId, String audience,
        String userFingerprint, String source) {

        String token = null;

        try {
            // 1. User ID must be valid with existing, unexpired session (userId == 1)
            if (!authenticationService.verifyUser(userId)) {
                throw new Exception("User ID was not valid");
            }

            // 2. Get the algorithm for signature.
            Algorithm algo = signatureService.getAlgorithm();

            // 3. Expiration time should be two minutes after generation
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 2);
            Date expirationDate = cal.getTime();

            // 4. Create the token
            token = JWT.
                create().
                withIssuer("com.michaelhoffmantech").
                withExpiresAt(expirationDate).
                withJWTId(UUID.randomUUID().toString()).
                withSubject(userId).
                withIssuedAt(new Date(System.currentTimeMillis())).
                withAudience(audience).
                withClaim("userFingerprint", userFingerprint).
                withClaim("source", source).
                sign(algo);
        } catch (JWTCreationException e) {
            log.error("An error occurred creating the JWT.", e);
        } catch (Exception e) {
            log.error("An error occurred creating the JWT.", e);
        }

        return token;
    }

    public boolean verifyToken(String token) {
        boolean isValid = false;
        try {
            Algorithm algo = signatureService.getAlgorithm();

            JWTVerifier verifier = JWT.
                require(algo).
                withIssuer("com.michaelhoffmantech").
                acceptLeeway(1L).
                build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("An error occurred during validation", e);
        } catch (Exception e) {
            log.error("An error occurred during validation", e);
        }

        return isValid;
    }

}
