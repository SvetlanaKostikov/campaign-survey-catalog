package com.crowdsourcing.campaign.survey.api.web.jwt;


import com.crowdsourcing.campaign.survey.catalog.domain.RegisteredUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserOperations {

    private static final String TOKEN_PREFIX = "Bearer ";
    @Value("${token.secret.key}")
    private String secret;

    public RegisteredUser createFrom(String token) {
        Claims claims = parseToken(token);

        String id = claims.get(UserClaims.SUB.getName(), String.class);

        return new RegisteredUser(id);
    }

    private String replaceToken(String token) {
        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new InvalidTokenException("Отсутствует префикс");
        } else {
            return token.replace(TOKEN_PREFIX, "");
        }
    }

    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(replaceToken(token)).getBody();
    }
}
