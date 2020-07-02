package team.ustc.sensor.entity;

public class CompanyName {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CompanyName(String name) {
        this.value = name;
    }
}
