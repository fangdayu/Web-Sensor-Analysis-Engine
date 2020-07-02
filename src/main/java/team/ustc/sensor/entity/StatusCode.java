package team.ustc.sensor.entity;


/**
 * 状态码
 *
 * @auther MrJoker
 */
public enum StatusCode {

    SUCCESS(200,"成功"), NOTFOUND(404,"未找到"),
    BADREQUEST(400,"输入参数错误"), SERVERERROR(500,"服务器出错");

    private int code;
    private String message;

    private StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "PinType{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
