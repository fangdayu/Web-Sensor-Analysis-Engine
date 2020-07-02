package team.ustc.sensor.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ustc.sensor.dao.SensorDao;
import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.Sensor;
import team.ustc.sensor.entity.User;
import team.ustc.sensor.util.Encrypt;
import team.ustc.sensor.util.Format;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户请求
 */
@RestController
public class LogController {

    @Autowired(required = false)
    UserDao userDao;
    @Autowired
    Encrypt encrypt = new Encrypt();

    Date date = new Date();

    /**
     * 登录请求
     */
    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User input, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        //得到输入的用户名和密码
        String username = input.getUsername();
        String password = input.getPassword();
        User user = userDao.queryUser(username);
        if (user == null) {
            //未查询到用户名信息
            map.put("message", "用户名不存在");
            map.put("code", 404);
        } else if (!user.getPassword().equals(encrypt.md5(password))) {
            //用户密码不正确
            map.put("message", "密码不正确");
            map.put("code", 500);
        } else {
            //用户登录成功
            map.put("message", "登录成功");
            map.put("username", username);
//            map.put("usertype", user.isCompany());
            map.put("code", 200);
            session.setAttribute("user", username);
        }
        System.out.println(username + "请求登录：" + map.get("message"));
        return map;
    }

    /**
     * 注册请求
     */
    @PostMapping("logup")
    public Map<String, Object> logup(@RequestBody User input, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        //判断用户是否存在
        String username = input.getUsername();
        User user = userDao.queryUser(username);
        if (user != null) {
            //用户已存在
            map.put("message", "用户已存在");
            map.put("code", 111);
        } else {
            //用户尚未注册
            //判断用户输入是否符合格式
            if (Format.isEmailFormat(input) && Format.isPasswordFormat(input)
                    && Format.isUsernameFormat(input) && Format.isMobilePhoneFormat(input)) {
                //输入符合格式
                //密码md5加密
                input.setPassword(encrypt.md5(input.getPassword()));
                //添加时间
                input.setUpdateTime(new Timestamp(date.getTime()).toString());
                //添加用户
                userDao.addUser(input);
                //判断用户是否注册成功
                user = userDao.queryUser(username);
                if (user != null) {
                    //用户注册成功
                    map.put("message", "注册成功");
                    map.put("username", username);
//                    map.put("userType", user.isCompany());
                    map.put("code", 200);
                    session.setAttribute("user", username);
                } else {
                    //用户注册失败
                    map.put("message", "注册失败，请重试");
                    map.put("code", 500);
                }
            } else if (!Format.isUsernameFormat(input)) {
                //用户名无效
                map.put("message", "无效的用户名");
                map.put("code", 400);
            } else if (!Format.isPasswordFormat(input)) {
                //用户密码无效
                map.put("message", "无效的用户密码");
                map.put("code", 400);
            } else if (!Format.isEmailFormat(input)) {
                //电子邮件无效
                map.put("message", "无效的邮件地址");
                map.put("code", 400);
            } else if (!Format.isMobilePhoneFormat(input)) {
                //手机号码无效
                map.put("message", "无效的手机号码");
                map.put("code", 400);
            }
        }
        System.out.println(username + "注册请求：" + map.get("message"));
        return map;
    }

    /**
     * 忘记密码时修改密码
     */
    @PostMapping("passwordChange")
    public Map<String, Object> passwordChange(@RequestBody User input, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = input.getUsername();
        String newPassword = input.getPassword();
        String mobilePhone = input.getMobilePhone();
        String email = input.getEmail();
        String updateTime = new Timestamp(date.getTime()).toString();
        User user = userDao.queryUser(username);
        //判断是否可以修改
        if (user != null && user.getEmail().equals(email) && user.getMobilePhone().equals(mobilePhone)) {
            //具有修改权限
            userDao.updateUserPassword(username, encrypt.md5(newPassword), updateTime);
            map.put("code", 200);
            map.put("message", "修改成功");
            map.put("username", username);
            session.setAttribute("user", username);

        } else {
            //不具有修改权限
            map.put("code", 400);
            map.put("message", "不具备修改权限");
        }
        return map;
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getUser/{username}")
    public Map<String, Object> getDevice(@PathVariable String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("接收到查询用户请求" + username);
        User user = userDao.queryUser(username);
        if (user != null) {
            user.setPassword(null);
            map.put("data", user);
            map.put("message", "用户信息已返回");
            map.put("code", 200);
        } else {
            map.put("message", "用户信息未找到");
            map.put("code", 404);
        }
        return map;
    }

    /**
     * 删除用户
     */
    @PostMapping("/deleteUser")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> deleteDevice(@RequestBody User input, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String username = input.getUsername();
        String password = input.getPassword();
        //根据username查询用户信息
        User user = userDao.queryUser(username);
        if (user != null) {
            //找到用户信息时
            //判断用户密码是否正确
            if (user.getPassword().equals(encrypt.md5(password))) {
                //用户密码正确可以删除用户
                userDao.deleteUser(username);
                //判断用户是否删除
                if (userDao.queryUser(username) == null) {
                    //用户删除成功
                    map.put("message", "已删除用户：" + username);
                    map.put("code", 200);
                } else {
                    //用户删除失败
                    map.put("message", "删除用户失败");
                    map.put("code", 500);
                }
            } else {
                //用户密码不正确
                map.put("message", "用户密码不正确");
                map.put("code", 400);
            }
        } else {
            //未找到用户信息时
            map.put("message", "未找到用户信息");
            map.put("code", 404);
        }
        return map;
    }


    /**
     * 修改用户信息
     * email,mobilePhone,companyName,companyDescription
     */
    @PostMapping("/updateUser")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "修改后的email", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "mobilePhone", value = "修改后的mobilePhone", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "companyName", value = "修改后的companyName", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "companyDescription", value = "修改后的companyDescription", dataType = "String", paramType = "query", required = false)})
    public Map<String, Object> updateUser(@RequestBody User input, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String username = input.getUsername();
        String email = input.getEmail();
        String mobilePhone = input.getMobilePhone();
        String description = input.getDescription();
        //查询用户是否存在
        User user = userDao.queryUser(username);
        if (user != null) {
            //            //修改用户存在
            if (mobilePhone != null && !Format.isMobilePhoneFormat(mobilePhone)) {
                //修改的手机号码无效
                map.put("message", "无效的手机号码");
                map.put("code", 400);
            } else if (email != null && !Format.isEmailFormat(email)) {
                //修改的邮箱号码无效
                map.put("message", "无效的邮箱号码");
                map.put("code", 400);
            } else {
                //可以修改
                userDao.updateUser(username, email, mobilePhone, description);
                map.put("message", "已完成修改");
                map.put("code", 200);
                map.put("chagedUser", userDao.queryUser(username));
            }
        } else {
            //用户名无效
            map.put("message", "无效的用户名");
            map.put("code", 404);
        }
        return map;
    }

    /**
     * 知道密码时修改用户密码
     */
    @PostMapping("/updatePassword")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "原password", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "newPassword", value = "修改后的password", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> updateUserPassword(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String updateTime = new Timestamp(date.getTime()).toString();
        //查询用户是否存在
        User user = userDao.queryUser(username);
        if (user != null) {
            //用户存在
            //判断原密码输入
            if (user.getPassword().equals(encrypt.md5(oldPassword))) {
                //原密码输入正确
                //判断新密码是否符合格式
                if (Format.isPasswordFormat(newPassword)) {
                    //新密码符合格式
                    //修改密码
                    userDao.updateUserPassword(username, encrypt.md5(newPassword), updateTime);
                    map.put("message", "成功修改密码");
                    map.put("code", 200);
                } else {
                    //新密码不符合格式
                    map.put("message", "新密码不符合格式");
                    map.put("code", 400);
                }
            } else {
                //原密码输入不正确
                map.put("message", "原密码输入不正确");
                map.put("code", 400);
            }
        } else {
            //用户不存在
            map.put("message", "用户不存在");
            map.put("code", 404);
        }
        return map;
    }

}