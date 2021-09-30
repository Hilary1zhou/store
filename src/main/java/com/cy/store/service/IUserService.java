package com.cy.store.service;
/**
 * @author huan
 * @serial 每天一百行,致敬未来的自己
 */

import com.cy.store.entity.User;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void regist(User user);

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 原始密码
     * @return
     */
    User login(String username, String password);

    void changePassword(String uid,String username,String oldPassword,String newPassword);

}
