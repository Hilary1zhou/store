package com.cy.store.service.ex;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

/**
 * 非法访问的异常
 */
public class AccessDeniedException extends ServiceException {
    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    protected AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}