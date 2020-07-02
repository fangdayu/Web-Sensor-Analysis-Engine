package team.ustc.sensor.entity;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * 传感器实体类
 *
 * @auther MrJoker
 */
public class Sensor {
    // 型号
    private String id;
    // 类型名称
    private String typeName;
    // 功能类型（尚未定义）
    private int functionType;
    // 出品公司
    private String companyName;
    // 出品公司账户
    private String companyUsername;
    // 价格
    BigDecimal price;
    // 产品描述
    private String description;
    // 更新时间
    private Date updateTime;

    // 输入电气特性
    private String inputElectricalCharacteristic;
    // 输出电气特性
    private String outputElectricalCharacteristic;
    // 环境温度-最低温
    private int lowTemperature;
    // 环境温度-最高温
    private int highTemperature;
    // 测量范围-低
    private int lowScaleRange;
    // 测量范围-高
    private int highScaleRange;
    // 精度（%）
    private int precision;
    // 响应时间 (ms)
    private int responseTime;
    // 防水等级（IP-65/67等可选）
    private int ipLevel;
    // 包装特性
    private String packagingFeatures;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getFunctionType() {
        return functionType;
    }

    public void setFunctionType(int functionType) {
        this.functionType = functionType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUsername() {
        return companyUsername;
    }

    public void setCompanyUsername(String companyUsername) {
        this.companyUsername = companyUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getInputElectricalCharacteristic() {
        return inputElectricalCharacteristic;
    }

    public void setInputElectricalCharacteristic(String inputElectricalCharacteristic) {
        this.inputElectricalCharacteristic = inputElectricalCharacteristic;
    }

    public String getOutputElectricalCharacteristic() {
        return outputElectricalCharacteristic;
    }

    public void setOutputElectricalCharacteristic(String outputElectricalCharacteristic) {
        this.outputElectricalCharacteristic = outputElectricalCharacteristic;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    public int getLowScaleRange() {
        return lowScaleRange;
    }

    public void setLowScaleRange(int lowScaleRange) {
        this.lowScaleRange = lowScaleRange;
    }

    public int getHighScaleRange() {
        return highScaleRange;
    }

    public void setHighScaleRange(int highScaleRange) {
        this.highScaleRange = highScaleRange;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getIpLevel() {
        return ipLevel;
    }

    public void setIpLevel(int ipLevel) {
        this.ipLevel = ipLevel;
    }

    public String getPackagingFeatures() {
        return packagingFeatures;
    }

    public void setPackagingFeatures(String packagingFeatures) {
        this.packagingFeatures = packagingFeatures;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", functionType=" + functionType +
                ", companyName='" + companyName + '\'' +
                ", companyUsername='" + companyUsername + '\'' +
                ", description='" + description + '\'' +
                ", updateTime=" + updateTime +
                ", inputElectricalCharacteristic='" + inputElectricalCharacteristic + '\'' +
                ", outputElectricalCharacteristic='" + outputElectricalCharacteristic + '\'' +
                ", lowTemperature=" + lowTemperature +
                ", highTemperature=" + highTemperature +
                ", lowScaleRange=" + lowScaleRange +
                ", highScaleRange=" + highScaleRange +
                ", precision='" + precision + '\'' +
                ", responseTime=" + responseTime +
                ", ipLevel=" + ipLevel +
                ", packagingFeatures='" + packagingFeatures + '\'' +
                '}';
    }
}
