package com.cy.store.service;
/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

import com.cy.store.entity.User;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    /**
     * 用户注册方法
     *
     * @param user 用户的数据对象
     */
    void regist(User user);

    /**
     * 用户登录方法
     *
     * @param username 用户名
     * @param password 原始密码
     * @return
     */
    User login(String username, String password);


    /**
     * 修改密码方法
     *
     * @param uid         用户的id
     * @param username    用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 更新个人资料
     *
     * @param uid      用户id
     * @param username 用户名
     * @param user     用户的数据
     */
    public void updateInfoByUid(Integer uid, String username, User user);

    /**
     * 获取当前用户登录的信息
     *
     * @param uid 用户id
     * @return 当前登录的用户的信息
     */
    public User getByUid(Integer uid);

    /**
     * 修改用户头像
     *
     * @param uid      当前登录的用户的id
     * @param username 当前登录的用户名
     * @param avatar   用户的新头像的路径
     */
    void changeAvatar(Integer uid, String username, String avatar);
}
