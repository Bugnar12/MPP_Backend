package org.backendspring_boot.backendspring_boot.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.backendspring_boot.backendspring_boot.entity.User;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.UUID;

@Service
public class JWTGeneratorService {
    private final Key key;

    public JWTGeneratorService(@Value("${jwt.key}") String hashingKey)
    {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateJWT(User user)
    {
        Long id = user.getId();

        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
