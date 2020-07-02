package team.ustc.sensor.dao;

import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.Sensor;
import team.ustc.sensor.entity.SensorDetail;
import team.ustc.sensor.entity.SensorView;

import java.util.List;

/**
 * 传感器详情数据库操作接口
 *
 * @auther MrJoker
 */

@Repository
public interface SensorDetailDao {

    /**
     * 根据ID查找传感器
     * @param sensorId 传感器ID
     * @return sensor
     */
    SensorDetail querySensorDetail(int sensorId);

    /**
     * 根据关键字查询传感器
     * @param word 关键字
     * @return sensorList
     */
    List<Integer> searchSensor(String word);

    /**
     * 插入传感器详情
     * @param sensorDetail 传感器
     * @return ID
     */
    int insertSensorDetail(SensorDetail sensorDetail);

    /**
     * 更新传感器详情
     * @param sensorDetail
     * @return ID
     */
    int updateSensorDetail(SensorDetail sensorDetail);

    /**
     * 删除传感器详情
     * @param sensorId 传感器ID
     * @return ID
     */
    int deleteSensorDetail(int sensorId);

}
