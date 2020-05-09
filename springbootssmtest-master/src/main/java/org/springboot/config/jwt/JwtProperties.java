package org.springboot.config.jwt;



/**
 * @ClassName JwtProperties
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/9 17:15
 * @Version 1.0
 */

public class JwtProperties {
    public static final String JWT_PREFIX = "jwt";
    public static final String AUTH_HEADER_PREFIX = "Bearer ";
    private String header = "Authorization";
    private String secret = "defaultSecret";
    private Long expiration = 86400L;
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