package team.ustc.sensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ustc.sensor.dao.SolutionDao;
import team.ustc.sensor.entity.SensorView;
import team.ustc.sensor.entity.Solution;

import java.util.List;
import java.util.Map;

@Service
public class SolutionService {
    @Autowired
    SolutionDao solutionDao;

    public int addSolution(Map<String, Object> input) {
        Solution solution = new Solution(input);
        int solutionId = solutionDao.addSolution(solution);
        return solutionId;
    }

    public List<Solution> querySolutionBySensorId(int sensorId){
        return solutionDao.querySolutionBySensorId(sensorId);
    }

    public List<Solution> querySolutionByGatewayId(int gatewayId){
        return solutionDao.querySolutionByGatewayId(gatewayId);
    }

    public List<Solution> querySolutionByUsername(String username){
        return solutionDao.querySolutionByUsername(username);
    }

    public List<Solution> querySolutionPage(int pageStart, int pageSize) {
        return solutionDao.querySolutionPage(pageStart,pageSize);
    }

    public Solution querySolutionBySolutionId(int solutionId){
        return solutionDao.querySolutionBySolutionId(solutionId);
    }
}
