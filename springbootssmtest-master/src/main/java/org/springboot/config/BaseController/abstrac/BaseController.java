package org.springboot.config.BaseController.abstrac;

import org.springboot.config.BaseController.impl.DefaultSessionInfo;
import org.springboot.config.BaseController.interfac.SessionInfo;
import org.springboot.config.ResponseData.clas.ServiceException;
import org.springboot.config.ResponseData.constants.CoreExceptionEnum;
import org.springboot.utils.HttpContext;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/9 13:41
 * @Version 1.0
 */
public abstract class BaseController {
    public BaseController() {
    }

    public SessionInfo getSessionInfo() {
        String userId = HttpContext.getRequest().getHeader("USER-ID");
        if (userId == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        } else {
            return new DefaultSessionInfo(userId);
        }
    }

    public String getUserId() {
        String userId = HttpContext.getRequest().getHeader("USER-ID");
        if (userId == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        } else {
            return userId;
        }
    }
}