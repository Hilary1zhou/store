package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.Impl.IUserServiceImpl;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author huan
 * @serial 每天一百行, 致敬未来的自己
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private IUserServiceImpl userService;

    @RequestMapping("/regist")
    public JsonResult<Void> regist(User user) {
        //调用业务对象注册
        userService.regist(user);
        //返回数据
        return new JsonResult<Void>(OK);

    }

    @RequestMapping("/login")
    public JsonResult<User> login(HttpSession session, String username, String password) {
        //调用业务对象注册
        User data = userService.login(username, password);
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        //返回数据
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("/update")
    public JsonResult<User> update(HttpSession session, String oldPassword, String newPassword) {
        //调用session.getAttribbute("")获取uid，username
        Integer uid = getUidFromSession(session);
        String userName = getUserNameFromSession(session);
        //调用业务对象修改密码
        userService.updatePassword(uid, userName, oldPassword, newPassword);
        return new JsonResult<>(200);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        //调用业务对象获取数据
        User data = userService.getByUid(getUidFromSession(session));
        //响应成功和数据
        return new JsonResult<User>(200, data);
    }

    @RequestMapping("update_info")
    public JsonResult<Void> updateInfo(HttpSession session, User user) {
        //调用业务对象执行用户修改资料
        userService.updateInfoByUid(getUidFromSession(session), getUserNameFromSession(session), user);
        //响应成功
        return new JsonResult<Void>(OK);
    }

    /**
     * 头像文件大小的上限值(10MB)
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    /**
     * 允许上传的头像的文件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }


    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) {

        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("上传的头像文件不存在");
        }
        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "kb的头像文件");
        }
        // 判断上传的文件类型是否超出限制(contains测试列表是否包含元素)
        if (!AVATAR_TYPES.contains(file.getContentType())) {
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        File dir = new File(session.getServletContext().getRealPath("upload"));

        //保存头像文件的文件夹
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        if (index > 0) {
            //文件的后缀名
            suffix = originalFilename.substring(index);

        }
        String fileName = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, fileName);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/upload/" + fileName;
        // 从Session中获取uid和username
        String username = getUserNameFromSession(session);
        Integer uid = getUidFromSession(session);
        // 将头像写入到数据库中
        userService.changeAvatar(uid, username, avatar);
        // 返回成功和头像路径
        return new JsonResult<String> (OK,avatar);

    }
}
