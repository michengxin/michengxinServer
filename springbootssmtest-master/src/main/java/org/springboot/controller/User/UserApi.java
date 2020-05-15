package org.springboot.controller.User;


import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.config.BaseController.abstrac.BaseController;
import org.springboot.config.ResponseData.clas.RestResponseData;
import org.springboot.config.jwt.properties.JwtProperties;
import org.springboot.config.jwt.util.JwtTokenUtil;
import org.springboot.controller.TestController;
import org.springboot.dto.UserDto;
import org.springboot.service.impl.UserService;
import org.springboot.utils.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @ClassName UserApi
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 17:51
 * @Version 1.0
 */
@CrossOrigin
@RestController()
@Api(tags = "用户API")
@RequestMapping("api/user")
@Slf4j
public class UserApi extends BaseController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    //根据前端返回过来的token解析获取用户详细数据，获取该用户所属角色下的所有权限
    @ApiOperation(value = "token解析",notes = "token解析")
    @ApiImplicitParams({

    })
    @PostMapping("/getUserInfo")
    public RestResponseData login(){
        LOGGER.info("日志打印测试");
        String token  = HttpContext.getRequest().getHeader(jwtProperties.getHeader());
        token = token.replace("Bearer ","");
        Claims claimFromToken = jwtTokenUtil.getClaimFromToken(token);
        String userId = claimFromToken.get("sub").toString();
        UserDto userDto =userService.selectGetUserInfo(userId);
        return new RestResponseData(userDto);
    }
}