package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.Impl.IUserServiceImpl;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private IUserServiceImpl userService;

    @RequestMapping("/regist")
    public JsonResult<Void> regist(User user) {
        //调用业务对象注册
        userService.regist(user);
        //返回数据
        return new JsonResult<Void>(OK);

    }
}
