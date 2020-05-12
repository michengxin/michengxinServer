package org.springboot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springboot.config.ResponseData.clas.ServiceException;
import org.springboot.config.jwt.enu.BoxPlatformEnum;
import org.springboot.config.jwt.util.JwtTokenUtil;
import org.springboot.config.properties.CodeConstants;
import org.springboot.config.properties.Constant;
import org.springboot.dao.UserDao;
import org.springboot.entity.User;
import org.springboot.utils.MD5Util;
import org.springboot.utils.SecurityFilterUtil;
import org.springboot.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName authService
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/12 14:25
 * @Version 1.0
 */
@Service
public class AuthService {
    @Resource
    UserDao userDao;
    @Resource
    JwtTokenUtil jwtTokenUtil;
    /**
     * 用户登录
     *
     * @param userVo
     * @return
     */
    @Transactional
    public String login(UserVo userVo) {
        BoxPlatformEnum platform = SecurityFilterUtil.getPlatform();
        return login(userVo.getUsername(), userVo.getPassword(), platform);
    }
    /**
     * 登录方法
     */
    public String login(String username,String password,BoxPlatformEnum platform){
        if(StringUtils.isBlank(username)){
            throw new ServiceException(CodeConstants.USER_LOGIN_ERROR,"用户名不能为空");
        }
        if(StringUtils.isBlank(password)){
            throw new ServiceException(CodeConstants.USER_LOGIN_ERROR,"密码不能为空");
        }
        User user = userDao.getUserInfo(username,platform.getUserType());  //根据用户code获取用户信息

        if(user == null){
            throw new ServiceException(CodeConstants.USER_LOGIN_ERROR,"用户名不存在或已锁定");
        }
        String md5Pwd = MD5Util.encodeByMD5(MD5Util.encodeByMD5(password));
        if(md5Pwd.equals(user.getPassword())){

            //写入openid  生成token返回
            return createToken(user.getId(),platform);
        }else{
            throw new ServiceException(CodeConstants.USER_LOGIN_ERROR,"用户密码错误");
        }
    }

    private String createToken(String userId, BoxPlatformEnum platform) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.PLATFORM, platform.name());
        String token = jwtTokenUtil.generateToken(userId, claims);
//        redisService.set(CacheGroupKeyConstants.USER_TOKEN.concat(":").concat(platform.name()) + ":" + userId, token, jwtTokenUtil.getExpireTime());
        return token;
    }
}