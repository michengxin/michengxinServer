package org.springboot.config.ResponseData.constants;


import org.springboot.config.ResponseData.interfac.IRestResponse;

public enum CoreExceptionEnum implements IRestResponse {
    NO_CURRENT_USER(700, "当前没有登录的用户"),
    REMOTE_LOGIN(700, "您的帐号已在其他地方登陆,请重新登陆"),
    PLATFORM_ERROR(700, "非法登录"),
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),
    PERMISSION_ERROR(800, "没有访问该资源的权限"),
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),
    REQUEST_NULL(400, "请求数据为空或格式错误"),
    PAGE_NULL(404, "请求页面不存在"),
    IO_ERROR(500, "流读取异常"),
    SERVICE_ERROR(500, "服务器异常"),
    REMOTE_SERVICE_NULL(404, "远程服务不存在");

    private Integer code;
    private String message;

    private CoreExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
