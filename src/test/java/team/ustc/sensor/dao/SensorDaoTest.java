package team.ustc.sensor.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ustc.sensor.entity.Sensor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SensorDaoTest {
    @Autowired
    SensorDao sensorDao;

    @Test
    void querySensorNum() {
        int x = sensorDao.querySensorNum();
        System.out.println(x);
    }

    @Test
    void querySensor() {
        List<Sensor> x = sensorDao.querySensor(2,2);
        System.out.println(x);
    }
}