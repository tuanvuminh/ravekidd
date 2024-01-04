package com.ravekidd.security.token;

import com.ravekidd.consts.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;

import static com.ravekidd.consts.Messages.UNSUCCESSFUL_TOKEN_VALIDATION;

/**
 * JWTProvider class responsible for generating, parsing, and validating JWT tokens.
 */
@Component
public class JWTProvider {

    /**
     * Generates a JWT token based on the provided authentication information.
     *
     * @param authentication The Authentication object.
     * @return The generated JWT token.
     */
    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Instant currentInstant = Instant.now();
        Instant expireInstant = currentInstant.plus(Constants.JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expireInstant))
                .signWith(Constants.KEY)
                .compact();
    }

    /**
     * Retrieves the username from a JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the JWT token.
     */
    public String getUsernameFromJWT(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(Constants.KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Extracts the JWT token from the Authorization header in the HttpServletRequest.
     *
     * @param request The HttpServletRequest.
     * @return The extracted JWT token.
     */
    public String getJWTFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    /**
     * Validates the provided JWT token.
     *
     * @param token The JWT token to validate.
     * @return True if the token is valid, otherwise false.
     * @throws AuthenticationCredentialsNotFoundException If the token is expired or incorrect.
     */
    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                    .setSigningKey(Constants.KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException(UNSUCCESSFUL_TOKEN_VALIDATION.getMessage(), e);
        }
    }
}
