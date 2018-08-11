package com.michaelhoffmantech.jwtauthenticationserver.service;

import org.springframework.stereotype.Service;

/**
 * Acts as a dummy authentication service to return certain details based on input.
 */
@Service
public class AuthenticationService {

    public boolean verifyUser(String userId) {
        // In a real world scenario, this service call would do any number of
        // checks, including validating the user ID is a real ID in the system,
        // assuring there is a valid session open for the user ID and/or assuring
        // that the time left in the session is not smaller than the JWT
        // expiration time. For this, keeping it simple.

        if ("1".equals(userId)) {
            return true;
        } else {
            return false;
        }
    }

}
