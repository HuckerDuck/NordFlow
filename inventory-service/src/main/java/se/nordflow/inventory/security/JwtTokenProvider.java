package se.nordflow.inventory.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.List;

// Only reads tokens, auth-service is the one that creates them
// Uses the same JWT_SECRET as auth so the signature can be checked here
@Component
public class JwtTokenProvider {
    private final SecretKey key;

    public JwtTokenProvider(@Value("${JWT_SECRET}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Checks the signature and that the token has not expired
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        }
        catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return parseToken(token).getSubject();
    }

    //? auth puts the roles in the token like ["ROLE_USER"] or ["ROLE_ADMIN"]
    public List<String> getRolesFromToken(String token) {
        List<?> roles = parseToken(token).get("roles", List.class);

        if (roles == null) {
            return List.of();
        }

        return roles.stream()
                .map(Object::toString)
                .toList();
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
