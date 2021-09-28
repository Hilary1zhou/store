package com.cy.store.util;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Json格式进行响应
 */
@Data
@NoArgsConstructor
public class JsonResult<E> implements Serializable {
    /** 状态码*/
   private Integer state;
    /** 描述信息*/
   private String message;
    /** 数据*/
   private E data;

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
    public JsonResult(Integer state, E data) {
        this.data = data;
        this.state = state;
    }


}
