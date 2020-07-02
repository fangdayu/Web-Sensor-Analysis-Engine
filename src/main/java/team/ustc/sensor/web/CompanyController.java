package team.ustc.sensor.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.ustc.sensor.entity.*;
import team.ustc.sensor.service.CompanyService;
import team.ustc.sensor.service.GatewayService;
import team.ustc.sensor.service.SensorService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;
    @Autowired
    GatewayService gatewayService;
    @Autowired
    SensorService sensorService;

    @GetMapping("/AllCompanies")
    public Map<String, Object> getAllCompanies(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询全部公司
        List<Company> list = companyService.getAllCompanies();
        if (list != null) {
            //查询成功
            //取出公司名称
            map.put("code", StatusCode.SUCCESS.getCode());
            List<CompanyName> nameList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                nameList.add(new CompanyName(list.get(i).getCompanyName()));
            }
            map.put("companyNameList", nameList);
        } else {
            //查询失败
            map.put("code", StatusCode.NOTFOUND.getCode());
        }
        return map;
    }

    @GetMapping("/CompanyDetail")
    @ApiImplicitParams({@ApiImplicitParam(name = "companyName", value = "公司名称", dataType = "String", paramType = "query", required = true)})
    public Map<String, Object> getCompanyView(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //根据公司名称查询公司信息
        String companyName = request.getParameter("companyName");
        Company company = companyService.queryCompany(companyName);
        if (company != null) {
            //查询到公司信息
            //根据公司信息查询相应传感器
            List<SensorView> sensorViewlist = sensorService.querySensorViewByCompanyName(companyName);
            //判断是否为空
//            if (sensorViewlist == null) {
//                //为空说明不是传感器公司
//                List<GatewayView> gatewayViewList = gatewayService.q
//            }
            map.put("code", StatusCode.SUCCESS.getCode());
            map.put("companyInfo", company.getCompanyInfo());
            map.put("sensorList", sensorViewlist);
        } else {
            //公司名称有误
            map.put("code", StatusCode.BADREQUEST.getCode());
        }
        return map;
    }


}
