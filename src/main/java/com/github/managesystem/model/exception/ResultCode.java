package com.github.managesystem.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Date: 2016/11/1
 * Time: 15:25
 *
 * @author qiuy19@ziroom.com
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    //成功
    SUCCESS(200, "成功"),

    //系统异常 151
    ERROR_SYSTEM(1001, "服务器异常：%s"),
    ERROR_REQUEST_BODY(1002, "接口请求JSON数据格式错误:%s"),
    ERROR_METHOD_REQUEST(1003, "调用方法不被支持"),
    ERROR_PARAMETER(1004, "检查输入参数:%s"),
    ERROR_AUTHORITY(1005, "HTTP请求头需要包含认证信息：%s");

    private int code;
    private String message;

    public String bindMessage(Object... objs){
        if (objs == null) {
            return this.message;
        }
        return String.format(this.message, objs);
    }
}
