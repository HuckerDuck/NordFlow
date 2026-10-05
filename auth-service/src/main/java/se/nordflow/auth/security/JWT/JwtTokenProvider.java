package se.nordflow.auth.security.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // Secret key with enviromental, loaded from application.properties
    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_EXPIRATION_MS}")
    private long jwtExpirationMs;

    // Generate the JWT Token for the authenticated user
    public String generateToken(UserDetails userDetails) {
         Date now = new Date();
         Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

         //? Build the signing key based on the secret
        SecretKey key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );

        return Jwts.builder()
                //Subject is the username
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())

                )
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    // Extract the username from a valid token
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    // Validate the token signature and expiration
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        }
        catch (JwtException | IllegalArgumentException e) {
            // Token is invalid or expired
            return false;
        }
    }

    // Private method for parsing and verifing the token
    private Claims parseToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
