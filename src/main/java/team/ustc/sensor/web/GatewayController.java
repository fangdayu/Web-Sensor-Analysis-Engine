package team.ustc.sensor.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.*;
import team.ustc.sensor.service.CompanyService;
import team.ustc.sensor.service.GatewayService;
import team.ustc.sensor.service.SolutionService;
import team.ustc.sensor.util.Encrypt;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GatewayController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Encrypt encrypt;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SolutionService solutionService;

    Date date = new Date();

    /**
     * 查询网关列表
     * 仅GatewayView
     */
    @GetMapping("GatewayView")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "网关类型", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "condition", value = "", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "pageNum", value = "", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> getGatewayView(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String type = request.getParameter("type");
        String condition = request.getParameter("condition");
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        System.out.println("网关查询" + type + condition + pageNum);
        int pageStart = (pageNum - 1) * pageSize;
        if (pageStart < 0) {
            map.put("code", StatusCode.BADREQUEST.getCode());
            return map;
        }
        int total = gatewayService.queryGatewayNum(type, condition);
        List<GatewayView> list = gatewayService.queryGatewayPage(type, condition, pageStart, pageSize);
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
     * 添加网关
     */
    @PostMapping("GatewayAdd")
    public Map<String, Object> addGateway(@RequestBody Map<String, Object> input) {
        Map<String, Object> map = new HashMap<String, Object>();
        String updateTime = new Timestamp(date.getTime()).toString();
        input.put("updateTime", updateTime);

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

        String gatewayName = input.get("gatewayName").toString();
        GatewayView gatewayView = gatewayService.queryGatewayByName(gatewayName);
        if (gatewayView != null) {
            map.put("code", StatusCode.BADREQUEST.getCode());
        } else {
            int code = gatewayService.insertGateway(input);
            if (code != -1) {
                map.put("code", StatusCode.SUCCESS.getCode());
                map.put("gatewayId", code);
            } else {
                map.put("code", StatusCode.SERVERERROR.getCode());
            }
        }
        return map;
    }


    /**
     * 查询网关详情
     * 目前仅GatewayDetail
     */
    @GetMapping("queryGatewayDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "gatewayId",
            value = "网关id",
            dataType = "Integer",
            paramType = "query",
            required = true)})
    public Map<String, Object> getGatewayDetail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //拿到网关id
        Integer gatewayId = Integer.valueOf(request.getParameter("gatewayId"));
        //根据网关id查询
        GatewayDetail gatewayDetail = gatewayService.queryGatewayDetail(gatewayId);
        if (gatewayDetail != null) {
            //查询到网关时
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("data", gatewayDetail);
        } else {
            //未查询到网关时
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    /**
     * 根据gatewayId返回传感器View和Detail
     */
    @GetMapping("GatewayQuery")
    @ApiImplicitParams({@ApiImplicitParam(name = "gatewayId", value = "网关Id", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> queryGateway(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //根据ID查询网关View和Detail
        int gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
        GatewayView gatewayView = gatewayService.queryGatewayView(gatewayId);
        GatewayDetail gatewayDetail = gatewayService.queryGatewayDetail(gatewayId);
        //判断是否成功查询
        if (gatewayView != null && gatewayDetail != null) {
            //查询成功返回
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("gatewayView", gatewayView);
            map.put("gatewayDetail", gatewayDetail);
        } else {
            //查询失败返回错误
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    /**
     * 修改网关
     */
    @PostMapping("GatewayUpdate")
    public Map<String, Object> updatGateway(@RequestBody Map<String, Object> input) {
        Map<String, Object> map = new HashMap<String, Object>();
        int gatewayId = Integer.parseInt(input.get("gatewayId").toString());
        String updateTime = new Timestamp(date.getTime()).toString();
        input.put("updateTime", updateTime);

        GatewayView gatewayView = gatewayService.queryGatewayView(gatewayId);
        //判断更新的网关是否存在
        if (gatewayView == null) {
            //更新的网关id不存在，返回错误信息
            map.put("code", StatusCode.BADREQUEST.getCode());
        } else {
            //判断是否有更新该网关的权限
            if (!input.get("username").toString().equals(gatewayView.getUsername())) {
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
                int code = gatewayService.updateGateway(input);
                if (code != -1) {
                    map.put("code", StatusCode.SUCCESS.getCode());
                    map.put("gatewayId", code);
                } else {
                    map.put("code", StatusCode.SERVERERROR.getCode());
                }
            }
        }
        return map;
    }

    /**
     * 搜索网关
     * 目前仅GatewayDetail
     */
    @GetMapping("searchGatewayDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "word",
            value = "关键词",
            dataType = "String",
            paramType = "query",
            required = true)})
    public Map<String, Object> GatewayDetailSearch(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //得到要搜索的关键字
        String word = request.getParameter("word");
        //关键字过短不予搜索
        if (word.length() < 2) {
            map.put("code", StatusCode.BADREQUEST.getCode());
            return map;
        }
        //搜索得到符合条件的网关信息
        List<GatewayDetail> list = gatewayService.searchGatewayDetail(word);
        if (list != null && list.size() > 0) {
            //搜索到结果时
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("data", list);
        } else {
            //未搜索到结果时
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    /**
     * 删除传感器
     */
    @PostMapping("GatewayRemove")
    public Map<String, Object> removeGateway(@RequestBody Map<String, Object> input) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = input.get("username").toString();
        String password = input.get("password").toString();
        int gatewayId = Integer.parseInt(input.get("gatewayId").toString());
        User user = userDao.queryUser(username);
        if (user == null) {
            map.put("code", StatusCode.NOTFOUND.getCode());
        } else if (!user.getPassword().equals(encrypt.md5(password))) {
            map.put("code", StatusCode.BADREQUEST.getCode());
        } else {
            gatewayService.deleteGateway(gatewayId);
            map.put("code", StatusCode.SUCCESS.getCode());
        }
        return map;
    }

    /**
     * 根据网关，精确匹配传感器
     * 根据某个网关ID，返回精确匹配的5个传感器
     */
    @GetMapping("gatewayBestMatch")
    @ApiImplicitParams({@ApiImplicitParam(name = "gatewayId", value = "网关id", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> matchSensor(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //根据id查询网关
        int gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
        GatewayView gatewayView = gatewayService.queryGatewayView(gatewayId);
        if (gatewayView != null) {
            //网关View存在
            int inputType = gatewayView.getInputType();
            int tempMax = gatewayView.getTempMax();
            int tempMin = gatewayView.getTempMin();
            int inputMax = gatewayView.getInputMax();
            int inputMin = gatewayView.getInputMin();
            //返回查询传感器VIEW的list
            List<SensorView> list = gatewayService.gatewayMatchSensor(inputType, tempMax, tempMin, inputMax, inputMin);
            List<Solution> solutions = solutionService.querySolutionByGatewayId(gatewayId);
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
     * 根据网关，匹配全部传感器
     * 根据某个网关ID，返回精确匹配的全部传感器
     */
    @GetMapping("gatewayAllMatch")
    @ApiImplicitParams({@ApiImplicitParam(name = "gatewayId", value = "网关id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "total", value = "", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNum", value = "", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> matchAllSensor(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //根据gatewayId查询网关
        int gatewayId = Integer.parseInt(request.getParameter("gatewayId"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageStart = (pageNum - 1) * pageSize;
        if (pageStart < 0) {
            map.put("code", StatusCode.BADREQUEST.getCode());
            return map;
        }
        String type = null, condition = null;
        int total = gatewayService.queryGatewayNum(type, condition);

        GatewayView gatewayView = gatewayService.queryGatewayView(gatewayId);
        if (gatewayView != null) {
            //gatewayView存在
            int inputType = gatewayView.getInputType();
            int tempMax = gatewayView.getTempMax();
            int tempMin = gatewayView.getTempMin();
            int inputMax = gatewayView.getInputMax();
            int inputMin = gatewayView.getInputMin();
            //返回查询传感器View的list
            List<SensorView> list = gatewayService.gatewayMatchAllSensor(inputType, tempMax, tempMin, inputMax, inputMin, pageStart, pageSize);
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("data", list);
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
            map.put("total", total);
        } else {
            //网关View不存在
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }


}
