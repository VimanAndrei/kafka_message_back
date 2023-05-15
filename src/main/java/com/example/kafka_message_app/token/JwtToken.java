package com.example.kafka_message_app.token;

import com.example.kafka_message_app.models.Person;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class JwtToken {
    private static final long expireDuration = 24 * 60 * 60 * 100;
    @Value("${app.jwt.secret}")
    private String secretKey;

    public String accesTokenGenerator(Person person){
        return Jwts.builder()
                .setSubject(person.getName() + ";" + person.getId())
                .setIssuer("Viamn")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireDuration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String errorAccesTokenGenerator(String err){
        return Jwts.builder()
                .setSubject(err)
                .setIssuer("Viamn")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireDuration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
