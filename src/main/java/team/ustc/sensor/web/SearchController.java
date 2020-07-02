package team.ustc.sensor.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.*;
import team.ustc.sensor.service.CompanyService;
import team.ustc.sensor.service.GatewayService;
import team.ustc.sensor.service.SensorService;
import team.ustc.sensor.service.UserServiceImpl;
import team.ustc.sensor.util.Encrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应传感器请求
 *
 * @auther MrJoker
 */
@RestController
public class SearchController {

    @Autowired
    private SensorService sensorService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    CompanyService companyService;


    /**
     * 搜索
     */
    @GetMapping("/SearchResult")
    @ApiImplicitParams({@ApiImplicitParam(name = "word",
            value = "关键词",
            dataType = "String",
            paramType = "query",
            required = true)})
    public Map<String, Object> getSensorSearch(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String word = request.getParameter("word");
        if (word.length() < 2) {
            map.put("code", StatusCode.BADREQUEST.getCode());
            return map;
        }
        List<SensorView> sensorList = sensorService.searchSensor(word);
        List<GatewayView> gatewayList = gatewayService.searchGateway(word);
        List<Company> companyList = companyService.searchCompany(word);
        List<User> userList = userService.searchUser(word);
        map.put("code", StatusCode.SUCCESS.getCode());
        map.put("sensorList", sensorList);
        map.put("gatewayList", gatewayList);
        map.put("companyList", companyList);
        map.put("userList", userList);
        return map;
    }

}
