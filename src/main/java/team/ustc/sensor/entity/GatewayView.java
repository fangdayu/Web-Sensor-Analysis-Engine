package team.ustc.sensor.entity;

import java.util.Map;

public class GatewayView {
    private int gatewayId;
    private String gatewayName;
    private String companyName;
    private String gatewayFeature;
    private int inputType;
    private int inputMax;
    private int inputMin;
    private int tempMax;
    private int tempMin;
    private String username;
    private String updateTime;


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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGatewayFeature() {
        return gatewayFeature;
    }

    public void setGatewayFeature(String gatewayFeature) {
        this.gatewayFeature = gatewayFeature;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public int getInputMax() {
        return inputMax;
    }

    public void setInputMax(int inputMax) {
        this.inputMax = inputMax;
    }

    public int getInputMin() {
        return inputMin;
    }

    public void setInputMin(int inputMin) {
        this.inputMin = inputMin;
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

    public GatewayView(Map<String, Object> map) {
        if (map.get("gatewayId") != null) {
            this.gatewayId = Integer.parseInt(map.get("gatewayId").toString());
        }
        this.gatewayName = map.get("gatewayName").toString();
        this.gatewayFeature = map.get("gatewayFeature").toString();
        this.companyName = map.get("companyName").toString();
        this.inputType = Integer.parseInt(map.get("inputType").toString());
        this.inputMax = Integer.parseInt(map.get("inputMax").toString());
        this.inputMin = Integer.parseInt(map.get("inputMin").toString());
        this.tempMax = Integer.parseInt(map.get("tempMax").toString());
        this.tempMin = Integer.parseInt(map.get("tempMin").toString());
        this.username = map.get("username").toString();
        this.updateTime = map.get("updateTime").toString();
    }

    public GatewayView() {

    }
}
