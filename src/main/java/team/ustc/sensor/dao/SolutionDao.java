package team.ustc.sensor.dao;

import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.SensorView;
import team.ustc.sensor.entity.Solution;

import java.util.List;

@Repository
public interface SolutionDao {

    int addSolution(Solution solution);

    List<Solution> querySolutionBySensorId(int sensorId);

    List<Solution> querySolutionByGatewayId(int gatewayId);

    List<Solution> querySolutionByUsername(String username);

    List<Solution> querySolutionPage(int pageStart, int pageSize);

    Solution querySolutionBySolutionId(int solutionId);
}
