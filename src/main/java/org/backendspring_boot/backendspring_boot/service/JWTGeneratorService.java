package org.backendspring_boot.backendspring_boot.service;

import io.jsonwebtoken.SignatureAlgorithm;
import org.backendspring_boot.backendspring_boot.entity.User;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.UUID;

@Service
public class JWTGeneratorService {
    private final String hashingKey;

    public JWTGeneratorService(@Value("${jwt.key}") String hashingKey)
    {
        this.hashingKey = hashingKey;
    }

    public String generateJWT(User user)
    {
        Long id = user.getId();
        Key key = new SecretKeySpec(hashingKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(new java.util.Date())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
