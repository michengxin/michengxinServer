package org.springboot.config.jwt.properties;


import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JwtProperties
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/12 15:30
 * @Version 1.0
 */
@Configuration
public class JwtProperties {
    public static final String JWT_PREFIX = "jwt";
    public static final String AUTH_HEADER_PREFIX = "Bearer ";
    private String header = "Authorization";
    private String secret = "defaultSecret";
    private Long expiration = 86400L;//一天过期时间
    private String authPath = "/auth";

    public JwtProperties() {
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getAuthPath() {
        return this.authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }
}