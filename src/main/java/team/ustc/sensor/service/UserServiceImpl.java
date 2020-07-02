package team.ustc.sensor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.Company;
import team.ustc.sensor.entity.User;

import java.math.BigDecimal;
import java.util.List;


/**
 * 账号数据操作事务
 *
 * @auther MrJoker
 */
@Service
public class UserServiceImpl {
    @Autowired(required = false)
    private UserDao userDao;

    /* 关键字查询 */
    public List<User> searchUser(String word) {
        List<User> list = userDao.searchUser(word);
        for (User item : list) {
            item.setPassword(null);
        }
        return list;
    }


    public User queryUser(String username) {
        return userDao.queryUser(username);
    }

    // 账户间转账
    public int balanceService(String outUsername, String inUsername, BigDecimal price) {
        User outUser = userDao.queryUser(outUsername);
        User inUser = userDao.queryUser(inUsername);
        if(outUser == null || inUser == null) return -1;
//        if(outUser.getBalance().compareTo(price) < 0) return -2;
        BigDecimal zero = new BigDecimal("0");
        BigDecimal price2 = zero.subtract(price);
        userDao.editBalance(price2 , outUsername);
        userDao.editBalance(price , inUsername);
        return 1;
    }

}
