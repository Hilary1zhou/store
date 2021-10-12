package com.cy.store;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
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
@RunWith(SpringRunner.class)
@SpringBootTest
public class IAddressServiceTest {
    @Autowired
    private IAddressService IAddressService;

    @Test
    public void addNewAddress() {
        try {
            Integer uid = 14;
            String username = "管理员";
            Address address = new Address();
            address.setName("张三");
            address.setPhone("17858805555");
            address.setAddress("雁塔区小寨华旗");
            IAddressService.addNewAddress(uid, username, address);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
