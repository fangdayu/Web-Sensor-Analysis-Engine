package team.ustc.sensor.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.ustc.sensor.dao.SolutionDao;
import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.GatewayView;
import team.ustc.sensor.entity.SensorView;
import team.ustc.sensor.entity.Solution;
import team.ustc.sensor.entity.StatusCode;
import team.ustc.sensor.service.CompanyService;
import team.ustc.sensor.service.GatewayService;
import team.ustc.sensor.service.SensorService;
import team.ustc.sensor.service.SolutionService;
import team.ustc.sensor.util.Encrypt;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SolutionController {
    @Autowired
    private SolutionService solutionService;
    @Autowired
    private Encrypt encrypt;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private SensorService sensorService;

    Date date = new Date();

    /**
     * 添加Solution
     */
    @PostMapping("addSolution")
    public Map<String, Object> addGateway(@RequestBody Map<String, Object> input) {
        Map<String, Object> map = new HashMap<>();
        String updateTime = new Timestamp(date.getTime()).toString();
        input.put("updateTime", updateTime);
//        int sensorId = Integer.parseInt(input.get("sensorId").toString());
//        int gatewayId = Integer.parseInt(input.get("gatewayId").toString());
//        SensorView sensorView = sensorService.querySensorView(sensorId);
//        GatewayView gatewayView = gatewayService.queryGatewayView(gatewayId);
//        if (sensorView != null && gatewayView != null){
//            input.put("sensorName",sensorView.getSensorName());
//            input.put("gatewayName",gatewayView.getGatewayName());
//        } else {
//            map.put("code", StatusCode.NOTFOUND.getCode());
//        }
        int solutionId = solutionService.addSolution(input);
        if (solutionId > 0) {
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("solutionId", solutionId);
        } else {
            map.put("code", StatusCode.SERVERERROR.getCode());
        }
        return map;
    }

    @GetMapping("allSolution")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> allSolution(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        int pageStart = (pageNum - 1) * pageSize;
        if (pageStart < 0) {
            map.put("code", StatusCode.BADREQUEST.getCode());
            return map;
        }
        List<Solution> solutionList = solutionService.querySolutionPage(pageStart, pageSize);
        if (solutionList != null && solutionList.size() > 0) {
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("solutions", solutionList);
            map.put("pageNum", pageNum);
            map.put("pageSize", pageSize);
        } else {
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    @GetMapping("mySolution")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> mySolution(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String username = request.getParameter("username").toString();
        List<Solution> solutionList = solutionService.querySolutionByUsername(username);
        if (solutionList != null && solutionList.size() > 0) {
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("solutions", solutionList);
        } else {
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    @GetMapping("getSolution")
    @ApiImplicitParams({@ApiImplicitParam(name = "solutionId", value = "", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> getSolution(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int solutionId = Integer.parseInt(request.getParameter("solutionId").toString());
        Solution solution = solutionService.querySolutionBySolutionId(solutionId);
        if (solution != null) {
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("solutions", solution);
        } else {
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }


}
