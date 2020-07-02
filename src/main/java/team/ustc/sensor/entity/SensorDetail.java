package team.ustc.sensor.entity;

import java.util.Map;
import java.util.Objects;

public class SensorDetail {
    private int sensorId;
    private String sensorName;
    private String companyName;
    private String sensorType;
    private String sensorTypeDetail;
    private String sensorInput;
    private String sensorOutput;
    private String sensorEnvironment;
    private String sensorRange;
    private String sensorLevel;
    private String sensorApplication;
    private String sensorDescription;
    private String sensorStrength;
    private String sensorOther;
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


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorTypeDetail() {
        return sensorTypeDetail;
    }

    public void setSensorTypeDetail(String sensorTypeDetail) {
        this.sensorTypeDetail = sensorTypeDetail;
    }

    public String getSensorInput() {
        return sensorInput;
    }

    public void setSensorInput(String sensorInput) {
        this.sensorInput = sensorInput;
    }

    public String getSensorOutput() {
        return sensorOutput;
    }

    public void setSensorOutput(String sensorOutput) {
        this.sensorOutput = sensorOutput;
    }

    public String getSensorEnvironment() {
        return sensorEnvironment;
    }

    public void setSensorEnvironment(String sensorEnvironment) {
        this.sensorEnvironment = sensorEnvironment;
    }

    public String getSensorRange() {
        return sensorRange;
    }

    public void setSensorRange(String sensorRange) {
        this.sensorRange = sensorRange;
    }

    public String getSensorLevel() {
        return sensorLevel;
    }

    public void setSensorLevel(String sensorLevel) {
        this.sensorLevel = sensorLevel;
    }

    public String getSensorApplication() {
        return sensorApplication;
    }

    public void setSensorApplication(String sensorApplication) {
        this.sensorApplication = sensorApplication;
    }

    public String getSensorDescription() {
        return sensorDescription;
    }

    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription;
    }

    public String getSensorStrength() {
        return sensorStrength;
    }

    public void setSensorStrength(String sensorStrength) {
        this.sensorStrength = sensorStrength;
    }

    public String getSensorOther() {
        return sensorOther;
    }

    public void setSensorOther(String sensorOther) {
        this.sensorOther = sensorOther;
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

    public SensorDetail() {

    }

    public SensorDetail(Map<String, Object> map) {
        this.sensorId = Integer.valueOf(map.get("sensorId").toString());
        this.sensorName = map.get("sensorName").toString();
        this.companyName = map.get("companyName").toString();
        this.sensorType = map.get("sensorType").toString();
        this.sensorTypeDetail = map.get("sensorTypeDetail").toString();
        this.sensorInput = map.get("sensorInput").toString();
        this.sensorOutput = map.get("sensorOutput").toString();
        this.sensorEnvironment = map.get("sensorEnvironment").toString();
        this.sensorRange = map.get("sensorRange").toString();
        this.sensorLevel = map.get("sensorLevel").toString();
        this.sensorApplication = map.get("sensorApplication").toString();
        this.sensorDescription = map.get("sensorDescription").toString();
        this.sensorStrength = map.get("sensorStrength").toString();
        this.sensorOther = map.get("sensorOther").toString();
        this.username = map.get("username").toString();
        this.updateTime = map.get("updateTime").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDetail that = (SensorDetail) o;
        return sensorId == that.sensorId &&
                Objects.equals(username, that.username) &&
                Objects.equals(sensorName, that.sensorName) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(sensorType, that.sensorType) &&
                Objects.equals(sensorTypeDetail, that.sensorTypeDetail) &&
                Objects.equals(sensorInput, that.sensorInput) &&
                Objects.equals(sensorOutput, that.sensorOutput) &&
                Objects.equals(sensorEnvironment, that.sensorEnvironment) &&
                Objects.equals(sensorRange, that.sensorRange) &&
                Objects.equals(sensorLevel, that.sensorLevel) &&
                Objects.equals(sensorApplication, that.sensorApplication) &&
                Objects.equals(sensorDescription, that.sensorDescription) &&
                Objects.equals(sensorStrength, that.sensorStrength) &&
                Objects.equals(sensorOther, that.sensorOther);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, sensorName, companyName, sensorType, sensorTypeDetail, sensorInput, sensorOutput, sensorEnvironment, sensorRange, sensorLevel, sensorApplication, sensorDescription, sensorStrength, sensorOther, username);
    }
}
