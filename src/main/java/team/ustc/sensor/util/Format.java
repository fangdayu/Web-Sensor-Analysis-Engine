package team.ustc.sensor.util;

import team.ustc.sensor.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Format {
    //判断email是否符合格式
    public static boolean isEmailFormat(User input) {
        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
        Matcher m = p.matcher(input.getEmail());
        return m.matches();
    }

    public static boolean isEmailFormat(String email) {
        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //用户名只能包含字母、数字和下划线，并且首字母只能为字母，用户名最短不能少于8个字符，最长不能超过16个字符
    public static boolean isUsernameFormat(User input) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z_0-9]{7,15}$");
        Matcher m = p.matcher(input.getUsername());
        return m.matches();
    }

    public static boolean isUsernameFormat(String username) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z_0-9]{7,15}$");
        Matcher m = p.matcher(username);
        return m.matches();
    }


    //密码只能包含字母、数字和下划线，并且首字母只能为字母，用户名最短不能少于8个字符，最长不能超过16个字符
    public static boolean isPasswordFormat(User input) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z_0-9]{7,15}$");
        Matcher m = p.matcher(input.getPassword());
        return m.matches();
    }

    public static boolean isPasswordFormat(String password) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z_0-9]{7,15}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    //判断手机号码是否有效
    public static boolean isMobilePhoneFormat(User input) {
        Pattern p = Pattern.compile("^1[34578][0-9]{9}$");
        Matcher m = p.matcher(input.getMobilePhone());
        return m.matches();
    }

    public static boolean isMobilePhoneFormat(String mobilePhone) {
        Pattern p = Pattern.compile("^1[34578][0-9]{9}$");
        Matcher m = p.matcher(mobilePhone);
        return m.matches();
    }


}
