package com.mumu.mumumall.exception;

/**
 * 异常类.
 */
public class MallException extends RuntimeException {
    private Integer code;
    private String msg;

    public MallException(MallExceptionEnum ex) {
        this(ex.getCode(), ex.getMsg());
    }

    public MallException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}