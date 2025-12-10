package ru.daria.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

// access token - короткоживущий токен, 1 час - 1 день
// refresh token - долгоживущий токен, от 2х недель до месяца

// JWT (JSON Web Token) выглядит так: xxxxx.yyyyy.zzzzz, три части, разделенные точками
// 1. Header (Заголовок): "Это JWT, подписан алгоритмом HMAC-SHA".
// 2. Payload (Полезная нагрузка): "Этот пропуск выдан userName: "daria", действует до expiration: "10.11.2025 18:00"".
// 3. Signature (Подпись): Самая важная часть. Это печать и подпись директора завода, созданная с помощью секретного ключа.kjkj lkjljlkj
//

@Component // доп логика, у service бизнесс- логика
public class JwtUtil {
    //@Value("${spring.security.token}")
    private final String jwtSecret;

    public JwtUtil(@Value("${spring.security.token}") String jwtSecret) { //вопрос по конструктору @Value и вопрос, где лучше должен лежать JwtUtil?
        this.jwtSecret = jwtSecret;
    }

    public SecretKey getSecretKey() { //объект, которым будем подписывать токены
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes); //Превращаем байты в крипто-ключ для алгоритма HMAC-SHA
    }

    public String generateJwtToken(String userName) { //
        // Срок годности токена
        Date date = Date.from(LocalDateTime.now()
                .plusMinutes(30)
                .atZone(ZoneId.systemDefault()).
                toInstant());

        // "Сборка" токена
        return Jwts.builder()
                .subject(userName)     // Кому выдан (Payload)
                .expiration(date)      // До какого годен (Payload)
                .signWith(getSecretKey()) // Ставим "печать" (Signature) + формируются header: typ и alg
                .compact();            // "Ламинируем" в строку
    }

    public Jws<Claims> validateToken (final String token) { // проверка токена
        return Jwts.parser()
                .verifyWith(getSecretKey()) // Ключ подписи
                .build()
                .parseSignedClaims(token); // Проверка

    }

    public String generateRefreshJwtToken(String userName) {
        Date date = Date.from(LocalDateTime.now().plusDays(14).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(userName)
                .expiration(date)
                .signWith(getSecretKey())
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parse(token)
                .getPayload()
                .toString();

    }
}
