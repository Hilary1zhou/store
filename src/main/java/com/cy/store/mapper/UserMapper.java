package com.cy.store.mapper;

import com.cy.store.entity.User;

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
     * @return 找到对于的用户，返回数据，否则返回null
     */
    User findByUserName(String username);
}
