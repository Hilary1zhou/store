package com.cy.store.service.Impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.UserNameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void regist(User user) {
        //调用findByUserName，判断用户是否注册过
        User result = userMapper.findByUserName(user.getUsername());
        //结果集为空抛出异常
        if (null != result) {
            throw new UserNameDuplicateException("用户名已存在");
        } else {
            Date date = new Date();
            //设置用户加密后的密码
            String salt = UUID.randomUUID().toString().toUpperCase();
            String md5Password = getMd5Password(user.getPassword(), salt);
            user.setPassword(md5Password);
            user.setSalt(salt);
            //设置is_Delete
            user.setIsDelete(0);
            //补全日志信息
            user.setCreatedUser(user.getUsername());
            user.setModifiedUser(user.getUsername());

            user.setCreatedTime(date);
            user.setModifiedTime(date);
            //注册功能实现(rows=1)
            Integer rows = userMapper.insert(user);
            //结果集不为1抛出异常
            if (rows != 1) {
                throw new UserNameDuplicateException("在用户注册过程中产生未知的异常");
            }
        }

    }

    /**
     * 密码加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 返回加密后的密文
     */
    private String getMd5Password(String password, String salt) {
        /**
         * 加密规则
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toLowerCase();
        } return password;
    }

}

