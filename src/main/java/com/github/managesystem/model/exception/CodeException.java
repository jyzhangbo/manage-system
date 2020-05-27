package com.github.managesystem.model.exception;

import com.github.managesystem.model.resp.Result;
import lombok.Data;


/**
 * 自定义的异常.
 *
 * @Author:zhangbo
 * @Date:2018/5/14 21:18
 */
@Data
public class CodeException extends Exception{

    private Integer code;
    private String message;

    public CodeException(ResultCode resultCode, Object... args) {
        this.code = resultCode.getCode();
        this.message = String.format(resultCode.getMessage(), args);
    }

    public CodeException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public CodeException(Result result) {
        this.message = result.getMessage();
        this.code = result.getCode();
    }

}
