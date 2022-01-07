package pl.teo.crm.app.security;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Getter
@Setter
@ConfigurationProperties(prefix = "application.jwt")
@Component
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private int expirationAfterDays;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
