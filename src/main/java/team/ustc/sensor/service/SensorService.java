package team.ustc.sensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ustc.sensor.dao.SensorDetailDao;
import team.ustc.sensor.dao.SensorViewDao;
import team.ustc.sensor.dao.UserDao;
import team.ustc.sensor.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 传感器数据操作事务
 *
 * @auther MrJoker
 */
@Service
public class SensorService {
    @Autowired
    private SensorViewDao sensorViewDao;
    @Autowired
    private SensorDetailDao sensorDetailDao;

    /* */
    public int querySensorNum(String type, String condition){
        return sensorViewDao.querySensorNum(type, condition);
    }

    public List<SensorView> querySensorPage(String type, String condition, int pageStart, int pageSize) {
        return sensorViewDao.querySensorPage(type, condition, pageStart, pageSize);
    }

    public SensorDetail querySensor(int sensorId) {
        return sensorDetailDao.querySensorDetail(sensorId);
    }

    public SensorView querySensorByName(String sensorName) {
        return sensorViewDao.querySensorViewByName(sensorName);
    }

    public SensorView querySensorView(int sensorId) {
        return sensorViewDao.querySensorView(sensorId);
    }

    public List<GatewayView> sensorMatchGateway(int outputType,int tempMax,int tempMin,int outputMax,int outputMin){
        return sensorViewDao.sensorMatchGateway(outputType,tempMax,tempMin,outputMax,outputMin);
    }

    public List<GatewayView> sensorMatchAllGateway(int outputType,int tempMax,int tempMin,int outputMax,int outputMin,int pageStart,int pageSize){
        return sensorViewDao.sensorMatchAllGateway(outputType,tempMax,tempMin,outputMax,outputMin, pageStart, pageSize);
    }

    /* 关键字查询 */
    public List<SensorView> searchSensor(String word) {
        return sensorViewDao.searchSensor(word);
    }

    /* 插入数据，插入成功返回 id */
    public int insertSensor(Map<String, Object> input) {
        SensorView sensorView = new SensorView(input);
        sensorViewDao.insertSensorView(sensorView);
        SensorView sensorView_out = sensorViewDao.querySensorViewByName(sensorView.getSensorName());
        // if(sensorView_out == null) return -1;
        input.put("sensorId", sensorView_out.getSensorId());
        SensorDetail sensorDetail = new SensorDetail(input);
        sensorDetailDao.insertSensorDetail(sensorDetail);
        return sensorView_out.getSensorId();
    }

    public int updateSensor(Map<String,Object> input){
        SensorView sensorView = new SensorView(input);
        sensorViewDao.updateSensorView(sensorView);
        SensorView sensorView_out = sensorViewDao.querySensorView(sensorView.getSensorId());
        SensorDetail sensorDetail = new SensorDetail(input);
        sensorDetailDao.updateSensorDetail(sensorDetail);
        return sensorView_out.getSensorId();
    }

    public int deleteSensor(int sensorId) {
        sensorViewDao.deleteSensorView(sensorId);
        sensorDetailDao.deleteSensorDetail(sensorId);
        return 1;
    }

    public List<SensorView> querySensorViewByCompanyName(String companyName){
        return sensorViewDao.querySensorViewByCompanyName(companyName);
    }
}
