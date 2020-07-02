package team.ustc.sensor.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 工具类：md5密码加密并存储
 *
 * @auther MrJoker
 */
@Component
public class Encrypt {
    public String md5(String plainText) {
        //设置md5密钥长度
        int length = 15;
        try {
            //获取MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            //转换为byte类型值
            md.update(plainText.getBytes());
            //此处得到的是md5加密后的byte类型值
            byte[] digest = md.digest();
            //二次加密
            int i;
            StringBuilder sb = new StringBuilder();
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append(0);
                sb.append(Integer.toHexString(i));//通过Integer.toHexString方法把值变为16进制
            }
            return sb.toString().substring(0, length);//从下标0开始，length目的是截取多少长度的值
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}