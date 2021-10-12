package com.cy.store.service;

import com.cy.store.entity.Address;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

public interface IAddressService {
    /**
     * 添加新的收货地址
     * @param uid 当前登录的用户id
     * @param username  当前登录的用户名
     * @param address   用户提交的收货地址数据
     */
    void addNewAddress(Integer uid, String username, Address address);
}
