package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

/**
 * 控制器类的基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static int OK = 200;

    /**
     * 统一处理方法抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UserNameDuplicateException) {
            result.setState(4000);
        }
        if (e instanceof UserNotFoundException) {
            result.setState(4001);
        }
        if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录用户的uid值
     */
    protected Integer getUidFromSession(HttpSession session) {
      return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     * @param session   session对象
     * @return 当前登录用户的用户名
     */
    protected String getUserNameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
