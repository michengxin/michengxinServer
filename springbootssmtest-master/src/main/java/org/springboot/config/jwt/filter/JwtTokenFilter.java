package org.springboot.config.jwt.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.config.Auth.interfac.VerificationAuth;
import org.springboot.config.ResponseData.clas.BaseRestResponse;
import org.springboot.config.ResponseData.clas.RestResponseData;
import org.springboot.config.ResponseData.clas.ServiceException;
import org.springboot.config.ResponseData.constants.CoreExceptionEnum;
import org.springboot.config.jwt.properties.JwtProperties;
import org.springboot.config.jwt.util.JwtTokenUtil;
import org.springboot.config.properties.BhomeProperties;
import org.springboot.config.wrapper.ModifyHttpServletRequestWrapper;
import org.springboot.utils.DistributedLockHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenFilter
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 9:10
 * @Version 1.0
 */

public class JwtTokenFilter  extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    BhomeProperties bhomeProperties;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired(
            required = false
    )
    VerificationAuth verificationAuth;


    public JwtTokenFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (StrUtil.isNotBlank(requestURI)) {
            requestURI = requestURI.replaceFirst(request.getContextPath(), "");
        }

        String authHeader = request.getHeader(this.jwtProperties.getHeader());
        boolean flagExpired;
        String userId;
        BaseRestResponse baseRestResponse = null;
        if (this.bhomeProperties.getExcludedPattern() != null && this.bhomeProperties.getExcludedPattern().matcher(requestURI).matches()) {
            ModifyHttpServletRequestWrapper requestWrapper = new ModifyHttpServletRequestWrapper(request);

            try {
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String authToken = authHeader.substring("Bearer ".length());
                    this.jwtTokenUtil.parseToken(authToken);
                    flagExpired = this.jwtTokenUtil.isTokenExpired(authToken);
                    if (!flagExpired) {//到期了(设置USER-ID)
                        userId = this.jwtTokenUtil.getUserIdFromToken(authToken);
                        requestWrapper.putHeader("USER-ID", userId);
                    }
                }
            } catch (JwtException var13) {
                log.info("未限制登录 JWT token 解析失败", var13);
            }
            System.out.println("第一次拦截通过");
            String message = request.getParameter("message");
            //第二次拦截
            if(null != message && !"".equals(message)) {
                if (message.equals("cnm")) {
                    baseRestResponse = new BaseRestResponse(CoreExceptionEnum.MANERGER_USERCODE);
                    if (baseRestResponse != null) {
                        httpServletResponse.setCharacterEncoding("UTF-8");
                        httpServletResponse.setContentType("application/json; charset=utf-8");
                        httpServletResponse.setStatus(200);
                        httpServletResponse.getWriter().write(JSONObject.toJSONString(baseRestResponse));
                    }
                } else {
                    System.out.println("第二次拦截通过");
//                String message = request.getParameter("message");
//                baseRestResponse = new BaseRestResponse(CoreExceptionEnum.MANERGER_USERCODE);
                    filterChain.doFilter(requestWrapper, httpServletResponse);
                }
            }else{
                filterChain.doFilter(requestWrapper, httpServletResponse);
            }

        } else {
            String authToken = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authToken = authHeader.substring("Bearer ".length());
                try {
                    this.jwtTokenUtil.parseToken(authToken);
                    flagExpired = this.jwtTokenUtil.isTokenExpired(authToken);
                    if (flagExpired) {//失效,刷新token
                        baseRestResponse = new BaseRestResponse(CoreExceptionEnum.TOKEN_ERROR);
                        //刷新token
//                        String userId1 = jwtTokenUtil.getUserIdFromToken(authToken);
//                        this.refreshToken(null,userId1);
//                        ModifyHttpServletRequestWrapper requestWrapper = new ModifyHttpServletRequestWrapper(request);
//                        filterChain.doFilter(requestWrapper, httpServletResponse);
                    } else {
                        userId = this.jwtTokenUtil.getUserIdFromToken(authToken);
                        if (this.verificationAuth != null) {
                            this.verificationAuth.tokenVerificationExtend(authToken);
                            this.verificationAuth.isAuth(requestURI, userId);
                        }
                        ModifyHttpServletRequestWrapper requestWrapper = new ModifyHttpServletRequestWrapper(request);
                        requestWrapper.putHeader("USER-ID", userId);
                        filterChain.doFilter(requestWrapper, httpServletResponse);
                    }
                } catch (JwtException var11) {
                    baseRestResponse = new BaseRestResponse(CoreExceptionEnum.TOKEN_ERROR);
                } catch (ServiceException var12) {
                    baseRestResponse = new BaseRestResponse(var12.getCode(), var12.getErrorMessage());
                }
            } else {
                baseRestResponse = new BaseRestResponse(CoreExceptionEnum.NO_CURRENT_USER);
            }
            if (baseRestResponse != null) {
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                httpServletResponse.setStatus(200);
                httpServletResponse.getWriter().write(JSONObject.toJSONString(baseRestResponse));
            }
        }

    }

    /**
     * 刷新token
     * @param respResult
     * @param userId
     */
    private  void refreshToken(RestResponseData respResult, String userId) {
        boolean isLock =  DistributedLockHandler.tryLock2(userId);
        if(isLock){
            respResult = new RestResponseData(CoreExceptionEnum.TOKEN_REFRESH);
            String token = jwtTokenUtil.generateToken(userId);
            Map resultMap = new HashMap(16);
            resultMap.put("token", token);
            respResult.setData(resultMap);
        }
    }

}