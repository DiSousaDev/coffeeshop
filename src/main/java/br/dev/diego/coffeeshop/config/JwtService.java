package br.dev.diego.coffeeshop.config;


import br.dev.diego.coffeeshop.config.exceptions.TokenGenerationException;
import br.dev.diego.coffeeshop.config.exceptions.TokenVerifyException;
import br.dev.diego.coffeeshop.models.entities.User;
import br.dev.diego.coffeeshop.models.responses.JwtResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class JwtService {

    @Value("${coffee-shop.security.jwt.secret}")
    private String secret;
    @Value("${coffee-shop.security.jwt.issuer}")
    private String issuer;

    public String gerarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new TokenGenerationException("Erro ao gerar token JWT, " + e.getMessage());
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new TokenVerifyException("Token JTW inválido ou expirado, " + e.getMessage());
        }
    }

    public JwtResponse gerarTokenJWT(User user) {
        return new JwtResponse(gerarToken(user));
    }

    private Instant getDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
