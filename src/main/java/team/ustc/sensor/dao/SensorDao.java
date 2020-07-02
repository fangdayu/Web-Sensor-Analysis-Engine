package team.ustc.sensor.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.Sensor;

import java.util.List;

/**
 * 传感器数据库操作接口
 *
 * @auther MrJoker
 */

@Repository
public interface SensorDao {
    /**
     * 查询传感器个数
     * @return sensorNum
     */
    int querySensorNum();

    /**
     * 查询传感器列表
     * @param pageStart  返回位置
     * @param pageSize   返回个数
     * @return sensorList
     */
    List<Sensor> querySensor(int pageStart, int pageSize);

    /**
     * 根据ID查找传感器
     * @return sensor
     */
    Sensor querySensorById(String id);

    /**
     * 插入传感器
     * @param sensor
     * @return ID
     */
    int insertSensor(Sensor sensor);

    /**
     * 根据公司展示传感器
     *
     * @param company
     * @return sensorList
     */
    List<Sensor> querySensorByCompany(String company);





    /**
     * 更新传感器信息
     * @param sensor
     * @return
     */
    int updateSensor(Sensor sensor);

    /**
     * 删除传感器信息
     * @param ID
     * @return ID
     */
    int deleteSensor(int ID);
}
