package com.cy.store;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void regist() {
        try {
            User user = new User();
            user.setUsername("sout");
            user.setPassword("654321");
            user.setGender(1);
            user.setPhone("17858802974");
            user.setEmail("lower@tedu.cn");
            user.setAvatar("xxxx");
            userService.regist(user);
            System.out.println("注册成功！");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}



