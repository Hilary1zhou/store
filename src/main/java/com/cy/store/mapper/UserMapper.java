package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Hilary
 * @serial 每天一百行，致敬未来的自己
 */
@Mapper
public interface UserMapper {
    /**
     * 插入用户的数据
     *
     * @param user 用户的数据
     * @return 受影响的行数，根据返回值判断是否执行成功
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户的数据
     *
     * @param username 用户名
     * @return 找到对应的用户，返回数据，否则返回null
     */
    User findByUserName(String username);

    /**
     * 根据用户id来修改密码
     *
     * @param uid          用户id
     * @param password     用户输入的新密码
     * @param modifiedUser 修改操作的执行者
     * @param modifiedTime 修改操作的时间
     * @return 受影响的行数，根据返回值判断是否执行成功
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户id查询用户数据
     *
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据
     *
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据用户id修改用户的头像
     *
     * @param uid          用户id
     * @param avatar       用户的头像
     * @param modifiedUser 最后修改执行人
     * @param modifiedTime 最后修改时间
     * @return
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
