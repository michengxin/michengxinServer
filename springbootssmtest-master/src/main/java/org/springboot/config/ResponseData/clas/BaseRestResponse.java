package org.springboot.config.ResponseData.clas;


import org.springboot.config.ResponseData.interfac.IRestResponse;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class BaseRestResponse implements IRestResponse, Serializable {
    protected Integer code;
    protected String message;

    public BaseRestResponse() {
        this.code = HttpStatus.OK.value();
        this.message = "OK.";
    }

    public BaseRestResponse(Integer code, String message) {
        this.code = HttpStatus.OK.value();
        this.message = "OK.";
        this.code = code;
        this.message = message;
    }

    public BaseRestResponse(IRestResponse response) {
        this.code = HttpStatus.OK.value();
        this.message = "OK.";
        this.code = response.getCode();
        this.message = response.getMessage();
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