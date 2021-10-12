package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

/** 处理收货地址数据的持久层接口 */
@Mapper
public interface AddressMapper {
    /**
     * 插入收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);
    /**
     * 统计某用户的收货地址数据的数量
     * @param uid 用户的id
     * @return 该用户的收货地址数据的数量
     */
    Integer CountByUid(Integer uid);
}
