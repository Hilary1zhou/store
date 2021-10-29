package com.cy.store.service.ex;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

/**
 * 用户名被占用的异常，继承ServiceException
 */
public class UserNameDuplicateException extends ServiceException {
    public UserNameDuplicateException() {
        super();
    }

    public UserNameDuplicateException(String message) {
        super(message);
    }

    public UserNameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UserNameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
