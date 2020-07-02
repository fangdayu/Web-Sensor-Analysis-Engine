package team.ustc.sensor.entity;

import java.util.Map;

public class Solution {
    private int solutionId;
    private String username;
    private String updateTime;
    private int sensorId;
    private String sensorName;
    private int gatewayId;
    private String gatewayName;
    private int collectId;
    private String collectName;
    private String summary;
    private String note;

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
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

    public int getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(int gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Solution() {

    }

    public Solution (Map<String, Object> map) {
        if (map.get("solutionId")!=null){
            this.solutionId = Integer.parseInt(map.get("solutionId").toString());
        }
        this.username = map.get("username").toString();
        this.updateTime = map.get("updateTime").toString();
        this.sensorId = Integer.parseInt(map.get("sensorId").toString());
        this.sensorName = map.get("sensorName").toString();
        this.gatewayId = Integer.parseInt(map.get("gatewayId").toString());
        this.gatewayName = map.get("gatewayName").toString();
        if (map.get("collectId")!=null){
            this.collectId = Integer.parseInt(map.get("collectId").toString());
        }
        if (map.get("collectName")!=null){
            this.collectName = map.get("collectName").toString();
        }
        this.summary = map.get("summary").toString();
        this.note = map.get("note").toString();
    }
}
