一.用户注册功能注意事项
1.注意用户的密码加密，使用md5进行加密（原始密码前后进行拼接循环三次）
private String getMd5Password(String password, String salt) {
    for (int i = 0; i < 3; i++) {
    password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toLowerCase();
} return password;
}
2.可能出现用户被占用，插入数据异常，通过自定义异常（ServiceException）抛出
3.控制层设计请求
依据当前的业务功能模块进行请求的设计
请求路径:/users/regist
请求参数:User user
请求类型:Post
响应结果:JsonResult<void>
4.@ExceptionHandler注解用于统一处理方法抛出的异常
UserController继承BaseController，否则无法响应message
5.前端页面:
ajax:
url:标识请求的地址（url地址），不能包含参数列表部分的内容
type:请求类型（"Post","Get"）
data:像指定的url地址提交的数据，data:"username=tom&pwd=123"
dataType:提交的数据的类型。一般指json类型  dataType:"json"
success:当服务器正常响应客户端时，会自动调用success参数的方法，并将服务器返回的数据以参数的形式传递给success这个方法的参数上。
error:当服务器未正常响应客户端时，会自动调用error参数的方法，并将服务器返回的数据以参数的形式传递给error这个方法的参数上。

登录功能
Session用户会话： 用户的id，头像，名称，保存服务器的临时数据，把session看做共享的数据，首次登录获取，转移到session对象中。
protected 无法被其他package调用 
Integer.valueof返回保存指定的String的值的Integer对象:uid

拦截器
1.定义拦截器，获取session数据，没有数据就进行拦截，反之放行
2.注册拦截器，配置放行的白名单，拦截除了白名单的所有请求 
registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);

