package org.springboot.config.Auth.interfac;

import org.springboot.config.ResponseData.clas.ServiceException;

public interface VerificationAuth {
    default void isAuth(String url, String userId) throws ServiceException {
    }

    default void tokenVerificationExtend(String token) throws ServiceException {
    }
}
