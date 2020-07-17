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
    ERROR_SYSTEM(1001, "服务器异常"),
    ERROR_REQUEST_BODY(1002, "接口请求JSON数据格式错误:%s"),
    ERROR_METHOD_REQUEST(1003, "调用方法不被支持"),
    ERROR_PARAMETER(1004, "检查输入参数:%s"),
    ERROR_AUTHORITY_NOT_EXIST(1005, "HTTP请求头需要包含认证信息：%s"),

    ERROR_USERNAME(1006, "用户名或密码错误"),
    ERROR_TASK(1007, "任务编号已经存在"),
    ERROR_TASK_NULL(1008, "没有可以查询的任务"),
    ERROR_TASK_DEVICE_NULL(1009, "该任务还未添加设备"),
    ERROR_USER_EXIST(1010, "该登录名已存在"),
    ERROR_AUTHORITY(1011, "认证信息不对: %s"),
    ERROR_NO_DATA(1012, "该设备还未上传过数据"),
    ERROR_EXCEL_RULE(1013, "excel字段格式不符合规则"),
    ERROR_DEVICE(1014, "设备编号已存在"),
    ERROR_DEVICE_DISCONNECT(1015, "该设备没有通电"),
    ERROR_DEVICE__EXIST(1016, "该设备不存在");

    private int code;
    private String message;

    public String bindMessage(Object... objs){
        if (objs == null) {
            return this.message;
        }
        return String.format(this.message, objs);
    }
}
