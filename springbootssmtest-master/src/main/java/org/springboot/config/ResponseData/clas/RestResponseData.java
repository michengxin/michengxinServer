package org.springboot.config.ResponseData.clas;



import org.springboot.config.ResponseData.constants.CoreExceptionEnum;
import org.springboot.config.ResponseData.interfac.IRestResponse;

public class RestResponseData<T> extends BaseRestResponse {
    T data;

    public RestResponseData() {
    }

    public RestResponseData(T data) {
        this.data = data;
    }

    public RestResponseData(Integer code, String message) {
        super(code, message);
    }

    public RestResponseData(IRestResponse response) {
        super(response);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static RestResponseData success() {
        return new RestResponseData();
    }

    public static <T> RestResponseData success(T data) {
        return new RestResponseData(data);
    }

    public static RestResponseData success(String message) {
        RestResponseData res = new RestResponseData();
        res.setMessage(message);
        return res;
    }

    public static <T> RestResponseData success(T data, String message) {
        RestResponseData res = new RestResponseData(data);
        res.setMessage(message);
        return res;
    }

    public static RestResponseData fail() {
        RestResponseData res = new RestResponseData(500, "服务器异常！");
        return res;
    }

    public static RestResponseData fail(Integer code, String message) {
        RestResponseData res = new RestResponseData();
        res.setMessage(message);
        res.setCode(code);
        return res;
    }

    public static RestResponseData fail(String message) {
        RestResponseData res = new RestResponseData();
        res.setMessage(message);
        res.setCode(500);
        return res;
    }

    public static RestResponseData fail(ServiceException ex) {
        RestResponseData res = new RestResponseData();
        res.setMessage(ex.getMessage());
        res.setCode(ex.getCode());
        return res;
    }

    public static RestResponseData fail(CoreExceptionEnum ex) {
        RestResponseData res = new RestResponseData();
        res.setMessage(ex.getMessage());
        res.setCode(ex.getCode());
        return res;
    }
}