package org.example.common.response;

import lombok.Data;
import org.example.common.constant.CommonConstant;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public Result() {

    }

    public Result<T> SUCCESS(String message) {
        this.message = message;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    public static<T> Result<T> OK() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage("成功");
        return r;
    }

    public static<T> Result<T> OK(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setResult(data);
        return r;
    }

    public static<T> Result<T> OK(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

    public static Result<Object> ERROR(String msg) {
        return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }
    public static Result<Object> error( String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(500);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }


    /**
     * 无权限访问返回结果
     */
    public static Result<Object> noAuth(String msg) {
        return error(CommonConstant.SC_NO_AUTH, msg);
    }

}