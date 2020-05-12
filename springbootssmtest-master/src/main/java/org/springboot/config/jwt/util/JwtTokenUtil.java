package org.springboot.config.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springboot.config.jwt.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenUtil
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/12 15:25
 * @Version 1.0
 */
@Component
public class JwtTokenUtil {
    @Autowired
    private JwtProperties jwtProperties;
    private String secret;
    private long expireTime;

    public JwtTokenUtil() {
    }

    public JwtTokenUtil(String secret, long expireTime) {
        this.secret = secret;
        this.expireTime = expireTime;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    @PostConstruct
    public void postMethod() {
        this.secret = this.jwtProperties.getSecret();
        this.expireTime = this.jwtProperties.getExpiration();
    }

    public String getUserIdFromToken(String token) {
        return this.getClaimFromToken(token).getSubject();
    }

    public Date getIssuedAtDateFromToken(String token) {
        return this.getClaimFromToken(token).getIssuedAt();
    }

    public Date getExpirationDateFromToken(String token) {
        return this.getClaimFromToken(token).getExpiration();
    }

    public String getAudienceFromToken(String token) {
        return this.getClaimFromToken(token).getAudience();
    }

    public String getPrivateClaimFromToken(String token, String key) {
        return this.getClaimFromToken(token).get(key).toString();
    }

    public Claims getClaimFromToken(String token) {
        return (Claims) Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap();
        return this.doGenerateToken(claims, userId);
    }

    public String generateToken(String userId, Map<String, Object> claims) {
        return this.doGenerateToken(claims, userId);
    }

    private String doGenerateToken(Map<String, Object> claims, String userId) {
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + this.expireTime * 1000L);
        return Jwts.builder().setClaims(claims).setSubject(userId).setIssuedAt(createdDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }
}