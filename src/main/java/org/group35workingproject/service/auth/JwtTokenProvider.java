package org.group35workingproject.service.auth;

import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.group35workingproject.service.exception.InvalidJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.lifetime}")
    private long jwtLifetime;

    public String createToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtLifetime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getManagerNameFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            // Invalid JWT signature
            throw new InvalidJwtException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            // Invalid JWT token
            throw new InvalidJwtException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            // Expired JWT token
            log.error(e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            // Unsupported
            throw new InvalidJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            // JWT claims string is empty
            throw new InvalidJwtException("JWT claims string is empty");
        }
    }

}
