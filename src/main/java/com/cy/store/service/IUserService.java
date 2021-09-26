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
}
