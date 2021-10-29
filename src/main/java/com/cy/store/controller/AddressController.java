package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */
@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService iAddressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUserNameFromSession(session);
        iAddressService.addNewAddress(uid, username, address);
        return new JsonResult<Void>(OK);
    }

    @GetMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = iAddressService.getByUid(uid);
        return new JsonResult<List<Address>>(OK, data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        iAddressService.setDefault(aid, getUidFromSession(session), getUserNameFromSession(session));
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        iAddressService.Delete(aid, getUidFromSession(session), getUserNameFromSession(session));
        return new JsonResult<Void>(OK);
    }
}
