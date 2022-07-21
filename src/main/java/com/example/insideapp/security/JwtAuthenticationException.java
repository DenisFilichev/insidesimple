package com.example.insideapp.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Authetication exception for insideapp application.
 *
 * @author Denis Filichev
 * @version 1.0
 */

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
