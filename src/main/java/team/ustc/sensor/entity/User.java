package team.ustc.sensor.entity;

import java.math.BigDecimal;

/**
 * 账号实体类
 */
public class User {
    // 用户名
    String username;
    // 密码
    String password;
    //    // 是否为公司
//    boolean isCompany;
    //手机号码
    String mobilePhone;
    //电子邮箱
    String email;
    //    // 公司名称（公司特有）
//    String companyName = null;
    // 描述
    String description = null;
//    // 余额
//    BigDecimal balance;

    private String updateTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
