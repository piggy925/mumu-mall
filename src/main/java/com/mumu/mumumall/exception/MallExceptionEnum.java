package com.mumu.mumumall.exception;

/**
 * 异常枚举类：用于存放返回异常的代码与信息.
 */
public enum MallExceptionEnum {
    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于八位"),
    NAME_EXISTED(10004, "不允许重名"),
    INSERT_FAIL(10005, "插入失败，请重试！"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "请先登录"),
    UPDATE_FAIL(10008, "更新失败"),
    CREATE_FAIL(10011, "新增失败"),
    DELETE_FAIL(10013, "删除失败"),
    SELECT_FAIL(10018, "选择失败"),
    NEED_ADMIN(10009, "需要管理员权限"),
    PARA_NOT_COMPLETE(10010, "参数不完整"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    MKDIR_FAIL(10014, "创建文件夹失败"),
    UPLOAD_FILE_FAIL(10015, "上传文件失败"),
    NOT_SALE(10016, "商品不可售"),
    NOT_ENOUGH(10017, "商品库存不足"),
    CART_EMPTY(10019, "未勾选商品"),
    NO_ENUM(10020, "未找到对应的枚举"),
    NO_ORDER(10021, "订单不存在"),
    NO_YOUR_ORDER(10022, "订单不属于你"),
    WRONG_ORDER_STATUS(10023, "订单状态错误"),

    SYSTEM_ERROR(20000, "系统异常！");

    Integer code;
    String msg;

    MallExceptionEnum(Integer code, String msg) {
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