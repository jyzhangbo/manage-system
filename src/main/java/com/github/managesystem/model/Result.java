package com.github.managesystem.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Result<T> implements Serializable {

    public int code;

    public String message;

    protected T data;

    public static int SUCCESS = 200;
    public static int FAIL = 1;
    public static int BAD_REQUEST = 400;
    public static int UNAUTHENTICATED = 401;
    public static int UNAUTHORIZED = 403;
    public static int UNAUTHORIZED1 = 403;

    /* 提供快捷方法 */
    public static <T> Result<T> ok() {
        return Result.<T>builder().code(SUCCESS).message("success").build();
    }

    /*public static <T> Result<T> ok(String message) {
        return Result.<T>builder().code(ResultCode.OK).message(message).build();
    }*/

    public static <T> Result<T> ok(T data) {
        return Result.<T>builder().code(SUCCESS).message("success").data(data).build();
    }
    /* 提供快捷方法 */
    public static <T> Result<T> no() {
        return Result.<T>builder().code(FAIL).message("fail").build();
    }

    public static <T> Result<T> no(Integer code,String message) {
        return Result.<T>builder().code(code).message(message).build();
    }

}


