package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.Impl.IUserServiceImpl;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/login")
    public JsonResult<User> login(HttpSession session, String username, String password) {
        //调用业务对象注册
        User data = userService.login(username, password);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        //返回数据
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("/update")
    public JsonResult<User> update(HttpSession session, String oldPassword, String newPassword) {
        //调用session.getAttribbute("")获取uid，username
        Integer uid = getUidFromSession(session);
        String userName = getUserNameFromSession(session);
        //调用业务对象修改密码
        userService.updatePassword(uid, userName, oldPassword, newPassword);
        return new JsonResult<>(200);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        //调用业务对象获取数据
        User data = userService.getByUid(getUidFromSession(session));
        //响应成功和数据
        return new JsonResult<User>(200, data);
    }
    @RequestMapping("update_info")
    public JsonResult<Void> updateInfo(HttpSession session,User user) {
        //调用业务对象执行用户修改资料
        userService.updateInfoByUid(getUidFromSession(session),getUserNameFromSession(session),user);
        //响应成功
        return new JsonResult<Void>(OK);
    }



    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) {

    }
}
