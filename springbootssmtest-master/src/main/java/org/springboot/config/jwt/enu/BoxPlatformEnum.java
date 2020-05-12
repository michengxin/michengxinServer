package org.springboot.config.jwt.enu;

/**
 * @ClassName BoxPlatformEnum
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/12 14:16
 * @Version 1.0
 */
public enum  BoxPlatformEnum {
    MICHENGXIN("MICHENGXIN");   //商城APP

    String userType;
    BoxPlatformEnum(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }
}