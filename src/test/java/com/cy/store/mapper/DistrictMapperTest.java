package com.cy.store.mapper;

import org.junit.jupiter.api.Test;
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
public class DistrictMapperTest {
    @Autowired
   private DistrictMapper districtMapper;

    @Test
    public void findNameByCode() {
        String code = "110101";
       String name= districtMapper.findNameByCode(code);
        System.out.println(name);
    }
}
