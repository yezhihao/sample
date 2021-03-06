package org.sample.seckill.model.enums;

import org.sample.model.enums.ResultCode;

public enum ResultCodes implements ResultCode {

    S000(0, "成功"), //

    S200(200, "DatabaseException"), //
    S300(300, "参数不能为空"), //
    S305(305, "参数转换异常"), //

    S400(400, "请先注册"), //
    S401(401, "数据校验未通过"), //
    S402(402, "秒杀结束"), //
    S403(403, "重复秒杀"), //
    NotFoundUser(405, "找不到该用户"), //
    S500(500, "系统异常,请联系管理员.");

    private final int code;

    private final String message;

    ResultCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}