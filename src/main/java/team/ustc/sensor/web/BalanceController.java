package team.ustc.sensor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ustc.sensor.dao.SensorDao;
import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.Sensor;
import team.ustc.sensor.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应传感器请求
 */
@RestController
public class BalanceController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 查询传感器列表
     */
    @PostMapping("/balance")
    public Map<String, Object> getListAll(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("接收到转账请求");
        String outUsername = request.getParameter("outUsername");
        String inUsername = request.getParameter("inUsername");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        int res = userService.balanceService(outUsername, inUsername, price);
        if (res > 0) {
            map.put("message", "转账成功");
            map.put("code", 200);

        }else if(res == -1) {
            map.put("message", "账号不存在");
            map.put("code", 404);
        }else {
            map.put("message", "余额不足");
            map.put("code", 500);
        }
        return map;
    }


}
