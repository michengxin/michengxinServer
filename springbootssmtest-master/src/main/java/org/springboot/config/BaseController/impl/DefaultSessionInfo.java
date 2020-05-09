package org.springboot.config.BaseController.impl;

import org.springboot.config.BaseController.interfac.SessionInfo;

/**
 * @ClassName DefaultSessionInfo
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/9 13:53
 * @Version 1.0
 */
public class DefaultSessionInfo implements SessionInfo {
    String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DefaultSessionInfo(String userId) {
        this.userId = userId;
    }
}