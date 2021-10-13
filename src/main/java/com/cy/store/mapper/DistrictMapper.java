package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

/**
 * 处理省/市/区数据的持久层接口
 */
public interface DistrictMapper {
    /**
     * 获取全国所有省/某省所有市/某市所有区
     *
     * @param parent 父级代号，当获取某市所有区时，使用市的代号；当获取省所有市时，使用省的代号；当获取全国所有省时，使用"86"作为父级代号
     * @return 全国所有省/某省所有市/某市所有区的列表
     */
    List<District> findByParent(String parent);
}
