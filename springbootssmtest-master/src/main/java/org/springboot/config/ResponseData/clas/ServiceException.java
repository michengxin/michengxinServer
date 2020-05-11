package org.springboot.config.ResponseData.clas;


import org.springboot.config.ResponseData.constants.CoreExceptionEnum;
import org.springboot.config.ResponseData.interfac.IRestResponse;

public class ServiceException extends RuntimeException {
    private Integer code;
    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(IRestResponse exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    public ServiceException(CoreExceptionEnum noCurrentUser) {
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
