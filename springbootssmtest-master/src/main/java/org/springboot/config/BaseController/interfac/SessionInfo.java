package org.springboot.config.BaseController.interfac;
/**
 * @interface SessionInfo
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/9 13:48
 * @Version 1.0
 */
public interface SessionInfo {
    default String getUserId() {
        return null;
    }

    default void setUserId(String userId) {
    }
}
