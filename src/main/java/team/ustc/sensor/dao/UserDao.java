package team.ustc.sensor.dao;

import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户数据库操作接口
 *
 */
@Repository
public interface UserDao {

    List<User> searchUser(String word);

    /**
     * 通过用户名查询账户
     *
     * @param username
     * @return user
     */
    User queryUser(String username);

    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 添加用户
     *
     * @param price    转入金额
     * @param username 转入用户
     */
    void editBalance(BigDecimal price, String username);

    /**
     * 删除用户
     *
     * @param username 删除用户用户名
     */

    void deleteUser(String username);

    /**
     * 修改用户信息
     * @param username  用户名
     * @param email  修改后的email
     * @param mobilePhone  修改后的手机号
     * @param description 描述
     */
    void updateUser(String username, String email, String mobilePhone, String description);

    /**
     * 更改用户密码
     * @param username 用户名
     * @param password 用户新密码
     */
    void updateUserPassword(String username, String password,String updateTime);
}
