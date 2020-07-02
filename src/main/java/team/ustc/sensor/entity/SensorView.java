package team.ustc.sensor.entity;

import java.util.Map;

/**
 * 传感器概览
 *
 * @auther MrJoker
 */
public class SensorView {
    private int sensorId;
    private String sensorName;
    private String sensorType;
    private String companyName;
    private String sensorFeature;
    private int outputType;
    private int outputMax;
    private int outputMin;
    private int tempMax;
    private int tempMin;
    private String username;
    private String updateTime;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSensorFeature() {
        return sensorFeature;
    }

    public void setSensorFeature(String sensorFeature) {
        this.sensorFeature = sensorFeature;
    }

    public int getOutputType() {
        return outputType;
    }

    public void setOutputType(int outputType) {
        this.outputType = outputType;
    }

    public int getOutputMax() {
        return outputMax;
    }

    public void setOutputMax(int outputMax) {
        this.outputMax = outputMax;
    }

    public int getOutputMin() {
        return outputMin;
    }

    public void setOutputMin(int outputMin) {
        this.outputMin = outputMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public SensorView() {
    }

    public SensorView(Map<String, Object> map) {
        if (map.get("sensorId") != null) {
            this.sensorId = Integer.valueOf(map.get("sensorId").toString());
        }
        this.sensorName = map.get("sensorName").toString();
        this.sensorType = map.get("sensorType").toString();
        this.companyName = map.get("companyName").toString();
        this.sensorFeature = map.get("sensorFeature").toString();
        this.outputType = Integer.valueOf(map.get("outputType").toString());
        this.outputMax = Integer.valueOf(map.get("outputMax").toString());
        this.outputMin = Integer.valueOf(map.get("outputMin").toString());
        this.tempMax = Integer.valueOf(map.get("tempMax").toString());
        this.tempMin = Integer.valueOf(map.get("tempMin").toString());
        this.username = map.get("username").toString();
        this.updateTime = map.get("updateTime").toString();
    }
}
