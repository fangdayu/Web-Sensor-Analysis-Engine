package team.ustc.sensor.entity;

import java.util.Map;

public class GatewayDetail {
    private int gatewayId;
    private String gatewayName;
    private String companyName;
    private String gatewayLevel;
    private String gatewayCharge;
    private String gatewayInput;
    private String gatewayEnvironment;
    private String gatewayDescription;
    private String gatewayOther;
    private String betweenProxy;
    private String uploadProxy;
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

    public String getGatewayLevel() {
        return gatewayLevel;
    }

    public void setGatewayLevel(String gatewayLevel) {
        this.gatewayLevel = gatewayLevel;
    }

    public String getGatewayCharge() {
        return gatewayCharge;
    }

    public void setGatewayCharge(String gatewayCharge) {
        this.gatewayCharge = gatewayCharge;
    }

    public String getGatewayInput() {
        return gatewayInput;
    }

    public void setGatewayInput(String gatewayInput) {
        this.gatewayInput = gatewayInput;
    }

    public String getGatewayEnvironment() {
        return gatewayEnvironment;
    }

    public void setGatewayEnvironment(String gatewayEnvironment) {
        this.gatewayEnvironment = gatewayEnvironment;
    }

    public String getGatewayDescription() {
        return gatewayDescription;
    }

    public void setGatewayDescription(String gatewayDescription) {
        this.gatewayDescription = gatewayDescription;
    }

    public String getGatewayOther() {
        return gatewayOther;
    }

    public void setGatewayOther(String gatewayOther) {
        this.gatewayOther = gatewayOther;
    }

    public String getBetweenProxy() {
        return betweenProxy;
    }

    public void setBetweenProxy(String betweenProxy) {
        this.betweenProxy = betweenProxy;
    }

    public String getUploadProxy() {
        return uploadProxy;
    }

    public void setUploadProxy(String uploadProxy) {
        this.uploadProxy = uploadProxy;
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

    public GatewayDetail(Map<String, Object> map) {
        this.gatewayId = Integer.parseInt(map.get("gatewayId").toString());
        this.gatewayName = map.get("gatewayName").toString();
        this.companyName = map.get("companyName").toString();
        this.gatewayLevel = map.get("gatewayLevel").toString();
        this.gatewayCharge = map.get("gatewayCharge").toString();
        this.gatewayInput = map.get("gatewayInput").toString();
        this.gatewayEnvironment = map.get("gatewayEnvironment").toString();
        this.gatewayDescription = map.get("gatewayDescription").toString();
        this.gatewayOther = map.get("gatewayOther").toString();
        this.betweenProxy = map.get("betweenProxy").toString();
        this.uploadProxy = map.get("uploadProxy").toString();
        this.username = map.get("username").toString();
        this.updateTime = map.get("updateTime").toString();
    }

    public GatewayDetail() {

    }
}
