package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.Date;

/**
 * @author Hilary
 * @serial 每天一百行，致敬未来的自己
 */

public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数，根据返回值判断是否执行成功
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户的数据
     * @param username 用户名
     * @return 找到对应的用户，返回数据，否则返回null
     */
    User findByUserName(String username);

    /**
     * 根据uid更新用户的密码
     * @param uid 用户id
     * @param password 新密码
     * @param modifiedUser  最后修改执行人
     * @param modifiedTime  最后修改时间
     * @return  受影响的行数
     */
    Integer changePassword(String uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id查询用户数据
     * @param uid 用户id
     * @return 匹配用户的数据，没有返回null
     */
    User findUserByUid(String uid);
}
