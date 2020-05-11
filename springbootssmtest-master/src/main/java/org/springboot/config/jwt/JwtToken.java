package org.springboot.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName JwtToken
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/11 11:57
 * @Version 1.0
 */
@Configuration
public class JwtToken {
    private static Logger logger = LoggerFactory.getLogger(JwtToken.class);
    /** 秘钥 */
    @Value("${jwt.secret}")
    private String secret;
    /** 过期时间(秒) */
    @Value("${jwt.expiration}")
    private long expiration;
    /**
     * 生成jwt token
     * Map<String, Object> claims,
     */
    public String generateToken(Long userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expiration * 1000);
        return Jwts.builder()
//                .setClaims(claims)
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    /**
     * 解析jwt token
     */
    public Claims getClaimByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String[] header = token.split("Bearer");
        token = header[1];
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.debug("validate is token error ", e);
            return null;
        }
    }
    /**
     * token是否过期
     * @return true：过期
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
// Getter && Setter
}
