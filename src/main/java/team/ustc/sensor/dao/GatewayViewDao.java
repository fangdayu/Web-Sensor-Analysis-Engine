package team.ustc.sensor.dao;

import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.GatewayView;
import team.ustc.sensor.entity.SensorView;

import java.util.List;

@Repository
public interface GatewayViewDao {

    List<GatewayView> searchGateway(String word);

    /**
     * 按 id 查询网关
     *
     * @param gatewayId ID
     * @return gatewayNum
     */
    GatewayView queryGatewayView(Integer gatewayId);

    /**
     * 按 name 查询网关
     *
     * @param gatewayName 名称
     * @return gatewayNum
     */
    GatewayView queryGatewayViewByName(String gatewayName);

    /**
     * 查询网关个数
     *
     * @param type      查询类型
     * @param condition 查询条件
     * @return gatewayNum
     */
    int queryGatewayNum(String type, String condition);

    /**
     * 查询网关列表
     *
     * @param type      查询类型
     * @param condition 查询条件
     * @param pageStart 返回位置
     * @param pageSize  返回个数
     * @return gatewayList
     */
    List<GatewayView> queryGatewayPage(String type, String condition, int pageStart, int pageSize);


    /**
     * 插入网关概览
     *
     * @param gatewayView 网关
     * @return ID
     */
    int insertGatewayView(GatewayView gatewayView);

    /**
     * 更新传感器概览
     * @param gatewayView 更新的网关信息
     * @return ID
     */
    int updateGatewayView(GatewayView gatewayView);

    /**
     * 根据参数返回匹配的传感器
     * @param inputType
     * @param tempMax
     * @param tempMin
     * @param inputMax
     * @param inputMin
     * @return
     */
    List<SensorView> gatewayMatchSensor(int inputType, int tempMax, int tempMin, int inputMax, int inputMin);

    /**
     * 根据参数返回匹配的全部传感器
     * @param inputType
     * @param tempMax
     * @param tempMin
     * @param inputMax
     * @param inputMin
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<SensorView> gatewayMatchAllSensor(int inputType, int tempMax, int tempMin, int inputMax, int inputMin, int pageStart, int pageSize);

    /**
     * 删除网关概览
     *
     * @param gatewayId 网关ID
     * @return ID
     */
    int deleteGatewayView(int gatewayId);

    /**
     * 根据公司名称查询不超过5个的网关View信息
     *
     * @param companyName 公司名称
     * @return 网关View的List
     */
    List<GatewayView> queryGatewayViewByCompanyName(String companyName);
}
