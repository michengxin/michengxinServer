package org.springboot.utils;
import org.springboot.config.ResponseData.clas.ServiceException;
import org.springboot.config.ResponseData.constants.CoreExceptionEnum;
import org.springboot.config.jwt.enu.BoxPlatformEnum;
import org.springboot.config.properties.Constant;
/**
 * @ClassName SecurityFilterUtil
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/12 14:03
 * @Version 1.0
 */
public class SecurityFilterUtil {
    private SecurityFilterUtil(){}

    public static String getUserId(){
        String userId = HttpContext.getRequest().getHeader(Constant.IDENTITY_HEADER);
        if(userId == null){
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }
        return userId;
    }
    public static BoxPlatformEnum getPlatform(){
        String platform = HttpContext.getRequest().getHeader(Constant.PLATFORM);
        if(platform == null){
            throw new ServiceException(CoreExceptionEnum.PLATFORM_ERROR);
        }
        try {
            return BoxPlatformEnum.valueOf(platform);
        }catch (Exception e){
            throw new ServiceException(CoreExceptionEnum.PLATFORM_ERROR);
        }
    }

}