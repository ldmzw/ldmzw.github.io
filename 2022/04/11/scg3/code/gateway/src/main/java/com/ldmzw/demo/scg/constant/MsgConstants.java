package com.ldmzw.demo.scg.constant;

public enum MsgConstants {

    SUCCESS("00000", "成功"),
    ERROR("99999", "失败"),
    SERVER_ERROR("330099", "网关服务异常"),

    /**
     * 远程调用错误
     * 3309XX
     */
    REMOTE_SERVER_ERROR("330999", "远程服务不可用，熔断降级"),

    ;

    private String status;
    private String message;

    MsgConstants(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
