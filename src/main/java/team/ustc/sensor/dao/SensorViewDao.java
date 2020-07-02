package team.ustc.sensor.dao;

import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.GatewayView;
import team.ustc.sensor.entity.Sensor;
import team.ustc.sensor.entity.SensorView;

import java.util.List;


/**
 * 传感器概览数据库操作接口
 *
 * @auther MrJoker
 */

@Repository
public interface SensorViewDao {

    List<SensorView> searchSensor(String word);

    /**
     * 按 id 查询传感器
     *
     * @param sensorId ID
     * @return sensorNum
     */
    SensorView querySensorView(Integer sensorId);

    /**
     * 按 name 查询传感器
     *
     * @param sensorName 名称
     * @return sensorNum
     */
    SensorView querySensorViewByName(String sensorName);

    /**
     * 查询传感器个数
     *
     * @param type      查询类型
     * @param condition 查询条件
     * @return sensorNum
     */
    int querySensorNum(String type, String condition);

    /**
     * 查询传感器列表
     *
     * @param type      查询类型
     * @param condition 查询条件
     * @param pageStart 返回位置
     * @param pageSize  返回个数
     * @return sensorList
     */
    List<SensorView> querySensorPage(String type, String condition, int pageStart, int pageSize);


    /**
     * 插入传感器概览
     *
     * @param sensorView 传感器
     * @return ID
     */
    int insertSensorView(SensorView sensorView);

    /**
     * 更新传感器概览
     * @param sensorView 更新的传感器信息
     * @return ID
     */
    int updateSensorView(SensorView sensorView);

    /**
     * 删除传感器概览
     *
     * @param sensorId 传感器ID
     * @return ID
     */
    int deleteSensorView(int sensorId);

    /**
     * 根据公司名称查询不超过5个的传感器View信息
     *
     * @param companyName 公司名称
     * @return 传感器View的List
     */
    List<SensorView> querySensorViewByCompanyName(String companyName);

    /**
     * 根据传感器id查询sensorView
     *
     * @param sensorId 传感器ID
     * @return 传感器View
     */
    SensorView querySensorView(int sensorId);

    /**
     * 根据参数返回匹配的网关
     *
     * @param outputType 输出类型
     * @param tempMax    最大温度
     * @param tempMin    最小温度
     * @param outputMax  最大输出
     * @param outputMin  最小输出
     * @return 网关View的List
     */
    List<GatewayView> sensorMatchGateway(int outputType, int tempMax, int tempMin, int outputMax, int outputMin);

    /**
     * 根据参数返回匹配的全部网关
     *
     * @param outputType 输出类型
     * @param tempMax    最大温度
     * @param tempMin    最小温度
     * @param outputMax  最大输出
     * @param outputMin  最小输出
     * @return 网关View的List
     */
    List<GatewayView> sensorMatchAllGateway(int outputType, int tempMax, int tempMin, int outputMax, int outputMin,int pageStart,int pageSize);


}