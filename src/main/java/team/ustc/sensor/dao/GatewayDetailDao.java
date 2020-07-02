package team.ustc.sensor.dao;

import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.GatewayDetail;
import team.ustc.sensor.entity.SensorDetail;

import java.util.List;

@Repository
public interface GatewayDetailDao {
    /**
     * 根据ID查找网关
     *
     * @param gatewayId 网关ID
     * @return sensor
     */
    GatewayDetail queryGatewayDetail(int gatewayId);

    /**
     * 插入网关详情
     *
     * @param gatewayDetail 网关
     * @return ID
     */
    int insertGatewayDetail(GatewayDetail gatewayDetail);

    /**
     * 更新网关详情
     * @param gatewayDetail
     * @return ID
     */
    int updateGatewayDetail(GatewayDetail gatewayDetail);

    /**
     *
     * @param  word 查询的内容
     * @return  符合条件的网关id
     */
    List<GatewayDetail> searchGatewayDetail(String word);

    void deleteGatewayDetail(int gatewayId);
}
