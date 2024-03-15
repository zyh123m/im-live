package com.example.oss.response;


public enum ExceptionResponse {

    INVALID_CAPTCHA("验证码错误");
    ExceptionResponse(String msg){
        this.msg = msg;
    }

    public static final Integer ERROR_CODE=500;
    private String msg;




    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result<?> ERROR() {
        return Result.error(ERROR_CODE,this.msg);
    }
    public Result<?> OK() {
        Result<?> ok = Result.OK();
        ok.setMessage(this.msg);
        return ok;
    }
}
