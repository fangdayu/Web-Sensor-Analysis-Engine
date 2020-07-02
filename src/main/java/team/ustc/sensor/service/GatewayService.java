package team.ustc.sensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ustc.sensor.dao.GatewayDetailDao;
import team.ustc.sensor.dao.GatewayViewDao;
import team.ustc.sensor.entity.GatewayDetail;
import team.ustc.sensor.entity.GatewayView;
import team.ustc.sensor.entity.SensorDetail;
import team.ustc.sensor.entity.SensorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class GatewayService {

    @Autowired
    GatewayDetailDao gatewayDetailDao;

    @Autowired
    GatewayViewDao gatewayViewDao;

    /* 关键字查询 */
    public List<GatewayView> searchGateway(String word) {
        return gatewayViewDao.searchGateway(word);
    }

    public int queryGatewayNum(String type, String condition) {
        return gatewayViewDao.queryGatewayNum(type, condition);
    }

    public List<GatewayView> queryGatewayPage(String type, String condition, int pageStart, int pageSize) {
        return gatewayViewDao.queryGatewayPage(type, condition, pageStart, pageSize);
    }

    public GatewayView queryGatewayByName(String gatewayName) {
        return gatewayViewDao.queryGatewayViewByName(gatewayName);
    }

    /* 插入数据，插入成功返回id */
    public int insertGateway(Map<String, Object> input) {
        GatewayView gatewayView = new GatewayView(input);
        gatewayViewDao.insertGatewayView(gatewayView);
        GatewayView gatewayView_out = gatewayViewDao.queryGatewayViewByName(gatewayView.getGatewayName());
        input.put("gatewayId", gatewayView_out.getGatewayId());
        GatewayDetail gatewayDetail = new GatewayDetail(input);
        gatewayDetailDao.insertGatewayDetail(gatewayDetail);
        return gatewayView_out.getGatewayId();
    }

    public int updateGateway(Map<String, Object> input) {
        GatewayView gatewayView = new GatewayView(input);
        gatewayViewDao.updateGatewayView(gatewayView);
        GatewayView gatewayView_out = gatewayViewDao.queryGatewayView(gatewayView.getGatewayId());
        GatewayDetail gatewayDetail = new GatewayDetail(input);
        gatewayDetailDao.updateGatewayDetail(gatewayDetail);
        return gatewayView_out.getGatewayId();
    }

    public List<SensorView> gatewayMatchSensor(int inputType, int tempMax, int tempMin, int inputMax, int inputMin) {
        return gatewayViewDao.gatewayMatchSensor(inputType, tempMax, tempMin, inputMax, inputMin);
    }

    public List<SensorView> gatewayMatchAllSensor(int inputType, int tempMax, int tempMin, int inputMax, int inputMin, int pageStart, int pageSize) {
        return gatewayViewDao.gatewayMatchAllSensor(inputType, tempMax, tempMin, inputMax, inputMin, pageStart, pageSize);
    }


    //网关查询(GatewayDetail)
    public GatewayDetail queryGatewayDetail(int gatewayId) {
        return gatewayDetailDao.queryGatewayDetail(gatewayId);
    }

    //网关查询(GatewayView)
    public GatewayView queryGatewayView(int gatewayId) {
        return gatewayViewDao.queryGatewayView(gatewayId);
    }

    /* 插入数据(目前仅GatewayDetail) */
    public int insertGateway(GatewayDetail gatewayDetail) {
//        SensorView sensorView = new SensorView(input);
//        GatewayDetail gatewayDetail = new GatewayDetail(input);
//        sensorViewDao.insertSensorView(sensorView);
        return gatewayDetailDao.insertGatewayDetail(gatewayDetail);
    }

    //网关搜索(目前仅GatewayDetail)
    public List<GatewayDetail> searchGatewayDetail(String word) {
        List<GatewayDetail> ids = gatewayDetailDao.searchGatewayDetail(word);
        List<GatewayDetail> res = new ArrayList<GatewayDetail>();
        for (int i = 0; i < ids.size(); i++) {
            GatewayDetail gatewayDetail = gatewayDetailDao.queryGatewayDetail(ids.get(i).getGatewayId());
            if (gatewayDetail != null) {
                res.add(gatewayDetail);
            }
        }
        return res;
    }

    public int deleteGateway(int gatewayId) {
        gatewayViewDao.deleteGatewayView(gatewayId);
        gatewayDetailDao.deleteGatewayDetail(gatewayId);
        return 1;
    }

    //网管删除(目前仅GatewayDetail)
    public void deleteGatewayDetail(int gatewayId) {
        gatewayDetailDao.deleteGatewayDetail(gatewayId);
    }
}
