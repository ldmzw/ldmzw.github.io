package com.ldmzw.demo.scg.controller.info;


import com.ldmzw.demo.scg.constant.MsgConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ResponseVO", description = "公共返回对象")
public class ResponseVO<T> {

    @ApiModelProperty(value = "返回码", example = "00000")
    private String status;
    @ApiModelProperty(value = "返回信息", example = "成功")
    private String message;
    @ApiModelProperty(value = "返回实体类")
    private T result;

    public ResponseVO() {
        this(MsgConstants.SUCCESS);
    }

    public ResponseVO(String status, String message) {
        this(status, message, null);
    }

    public ResponseVO(String status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ResponseVO(MsgConstants messageCode) {
        this(messageCode, null);
    }

    public ResponseVO(MsgConstants messageCode, T result) {
        this(messageCode.getStatus(), messageCode.getMessage(), result);
    }

    public ResponseVO(T result) {
        this(MsgConstants.SUCCESS, result);
    }
}
