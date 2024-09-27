package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result success(T data) {
        return new Result(1, "操作成功", data);
    }

    public static <T> Result success() {
        return new Result(1,"操作成功",null);
    }

    public static Result fail(String message) {
        return new Result(0, message, null);
    }
}
