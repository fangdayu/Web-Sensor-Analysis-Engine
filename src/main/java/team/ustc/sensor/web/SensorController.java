package team.ustc.sensor.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ustc.sensor.dao.SensorDao;
import team.ustc.sensor.dao.SensorViewDao;
import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.*;
import team.ustc.sensor.service.CompanyService;
import team.ustc.sensor.service.SensorService;
import team.ustc.sensor.service.SolutionService;
import team.ustc.sensor.util.Encrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

/**
 * 响应传感器请求
 *
 * @auther MrJoker
 */
@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private Encrypt encrypt;
    @Autowired
    CompanyService companyService;
    @Autowired
    SolutionService solutionService;

    Date date = new Date();

    /**
     * 查询传感器列表
     */
    @GetMapping("/SensorView")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "传感器类型", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "condition", value = "", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "pageNum", value = "", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> getSensorView(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String type = request.getParameter("type");
        String condition = request.getParameter("condition");
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int pageStart = (pageNum - 1) * pageSize;
        if (pageStart < 0) {
            map.put("code", StatusCode.BADREQUEST.getCode());
            return map;
        }
        int total = sensorService.querySensorNum(type, condition);
        List<SensorView> list = sensorService.querySensorPage(type, condition, pageStart, pageSize);
        if (list != null && list.size() > 0) {
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("data", list);
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            map.put("total", total);
        } else {
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }


    /**
     * 查询传感器详情
     */
    @GetMapping("/SensorDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "sensorId",
            value = "传感器id",
            dataType = "Integer",
            paramType = "query",
            required = true)})
    public Map<String, Object> getSensorDetail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer sensorId = Integer.valueOf(request.getParameter("sensorId"));
        System.out.println(sensorId);
        SensorDetail sensorDetail = sensorService.querySensor(sensorId);
        if (sensorDetail != null) {
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("data", sensorDetail);
        } else {
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    /**
     * 添加传感器
     */
    @PostMapping("SensorAdd")
    public Map<String, Object> addSensor(@RequestBody Map<String, Object> input) {
        Map<String, Object> map = new HashMap<String, Object>();
        String updateTime = new Timestamp(date.getTime()).toString();

        //判断companyName字段是否存在
        String companyName = input.get("companyName").toString();
        if (companyName != null) {
            //companyName字段存在时，
            Company company = companyService.queryCompany(companyName);
            if (company == null) {
                //公司不存在时，创建新的公司
                int judge = companyService.addCompany(companyName, updateTime);
                //公司创建不成功时返回错误信息,创建成功不作操作
                if (judge == -1) {
                    map.put("code", StatusCode.SERVERERROR.getCode());
                    return map;
                }
            }
        }

        String sensorName = input.get("sensorName").toString();
        input.put("updateTime", updateTime);
        SensorView sensorView = sensorService.querySensorByName(sensorName);
        if (sensorView != null) {
            map.put("code", StatusCode.BADREQUEST.getCode());
        } else {
            int code = sensorService.insertSensor(input);
            if (code != -1) {
                map.put("code", StatusCode.SUCCESS.getCode());
                map.put("sensorId", code);
            } else {
                map.put("code", StatusCode.SERVERERROR.getCode());
            }
        }
        return map;
    }

    /**
     * 根据sensorId返回传感器View和Detail
     */
    @GetMapping("SensorQuery")
    @ApiImplicitParams({@ApiImplicitParam(name = "sensorId", value = "传感器id", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> querySensor(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //根据ID查询传感器View和Detail
        int sensorId = Integer.parseInt(request.getParameter("sensorId"));
        SensorView sensorView = sensorService.querySensorView(sensorId);
        SensorDetail sensorDetail = sensorService.querySensor(sensorId);
        //判断是否成功查询
        if (sensorView != null && sensorDetail != null) {
            //查询成功返回
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("sensorView", sensorView);
            map.put("sensorDetail", sensorDetail);
        } else {
            //查询失败返回错误
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    /**
     * 修改传感器
     */
    @PostMapping("SensorUpdate")
    public Map<String, Object> updateSensor(@RequestBody Map<String, Object> input) {
        Map<String, Object> map = new HashMap<String, Object>();
        String updateTime = new Timestamp(date.getTime()).toString();
        input.put("updateTime", updateTime);

        int sensorId = Integer.parseInt(input.get("sensorId").toString());
        SensorView sensorView = sensorService.querySensorView(sensorId);
        //判断更新的传感器是否存在
        if (sensorView == null) {
            //更新的传感器id不存在，返回错误信息
            map.put("code", StatusCode.BADREQUEST.getCode());
        } else {
            //判断是否有更新该传感器的权限
            if (!input.get("username").toString().equals(sensorView.getUsername())) {
                //用户名对应不上时，无权限
                map.put("code", StatusCode.BADREQUEST.getCode());
            } else {
                //有修改权限，可进行修改
                //判断companyName字段是否存在
                String companyName = input.get("companyName").toString();
                if (companyName != null) {
                    //companyName字段存在时，
                    Company company = companyService.queryCompany(companyName);
                    if (company == null) {
                        //公司不存在时，创建新的公司
                        int judge = companyService.addCompany(companyName, updateTime);
                        //公司创建不成功时返回错误信息,创建成功不作操作
                        if (judge == -1) {
                            map.put("code", StatusCode.SERVERERROR.getCode());
                            return map;
                        }
                    }
                }
                int code = sensorService.updateSensor(input);
                if (code != -1) {
                    map.put("code", StatusCode.SUCCESS.getCode());
                    map.put("sensorId", code);
                } else {
                    map.put("code", StatusCode.SERVERERROR.getCode());
                }
            }
        }
        return map;
    }


    /**
     * 删除传感器
     */
    @PostMapping("SensorRemove")
    public Map<String, Object> removeSensor(@RequestBody Map<String, Object> input) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = input.get("username").toString();
        String password = input.get("password").toString();
        int sensorId = Integer.valueOf(input.get("sensorId").toString());
        User user = userDao.queryUser(username);
        if (user == null) {
            map.put("code", StatusCode.NOTFOUND.getCode());
        } else if (!user.getPassword().equals(encrypt.md5(password))) {
            map.put("code", StatusCode.BADREQUEST.getCode());
        } else {
            sensorService.deleteSensor(sensorId);
            map.put("code", StatusCode.SUCCESS.getCode());
        }
        return map;
    }


    /**
     * 根据传感器，精确匹配网关
     * 根据某个传感器ID，返回精确匹配的5个网关
     */
    @GetMapping("sensorBestMatch")
    @ApiImplicitParams({@ApiImplicitParam(name = "sensorId", value = "传感器id", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> matchGateway(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //根据sensorId查询传感器
        int sensorId = Integer.parseInt(request.getParameter("sensorId"));
        SensorView sensorView = sensorService.querySensorView(sensorId);
        if (sensorView != null) {
            //传感器View存在
            int outputType = sensorView.getOutputType();
            int tempMax = sensorView.getTempMax();
            int tempMin = sensorView.getTempMin();
            int outputMax = sensorView.getOutputMax();
            int outputMin = sensorView.getOutputMin();
            //返回查询网关VIEW的list
            List<GatewayView> list = sensorService.sensorMatchGateway(outputType, tempMax, tempMin, outputMax, outputMin);
            //返回Solution的solutions
            List<Solution> solutions = solutionService.querySolutionBySensorId(sensorId);
            map.put("solutions",solutions);
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("list", list);
        } else {
            //传感器View不存在
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    /**
     * 根据传感器，匹配全部网关
     * 根据某个传感器ID，返回精确匹配的全部网关
     */
    @GetMapping("sensorAllMatch")
    @ApiImplicitParams({@ApiImplicitParam(name = "sensorId", value = "传感器id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "total", value = "", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNum", value = "", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> matchAllGateway(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //根据sensorId查询传感器
        int sensorId = Integer.parseInt(request.getParameter("sensorId"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageStart = (pageNum - 1) * pageSize;
        if (pageStart < 0) {
            map.put("code", StatusCode.BADREQUEST.getCode());
            return map;
        }
        String type = null, condition = null;
        int total = sensorService.querySensorNum(type, condition);

        SensorView sensorView = sensorService.querySensorView(sensorId);
        if (sensorView != null) {
            //传感器View存在
            int outputType = sensorView.getOutputType();
            int tempMax = sensorView.getTempMax();
            int tempMin = sensorView.getTempMin();
            int outputMax = sensorView.getOutputMax();
            int outputMin = sensorView.getOutputMin();
            //返回查询网关VIEW的list
            List<GatewayView> list = sensorService.sensorMatchAllGateway(outputType, tempMax, tempMin, outputMax, outputMin, pageStart, pageSize);
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("data", list);
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            map.put("total", total);
        } else {
            //传感器View不存在
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

}
