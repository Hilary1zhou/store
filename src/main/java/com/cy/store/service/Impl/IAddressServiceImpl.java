package com.cy.store.service.Impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */
@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Value("${user.address.max_count}")
    private int max_count;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用addressMapper的countByUid方法，判断收货地址数量是否超上限
        Integer count = addressMapper.CountByUid(uid);
        if (count > max_count) {
            throw new AddressCountLimitException("收货地址数量已经达到上限(" + max_count + ")!");
        }
        //将uid封装到Address中
        address.setUid(uid);
        // 补全数据：根据以上统计的数量，得到正确的isDefault值(是否默认：0-不默认，1-默认)，并封装
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        //补全日志
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入收货地址数据时出现未知错误，请联系系统管理员！");
        }

    }
}
