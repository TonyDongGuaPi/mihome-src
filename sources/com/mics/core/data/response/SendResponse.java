package com.mics.core.data.response;

import com.taobao.weex.el.parse.Operators;

public class SendResponse {
    private int code;
    private long data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public long getData() {
        return this.data;
    }

    public void setData(long j) {
        this.data = j;
    }

    public String toString() {
        return "BaseResponse{code=" + this.code + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }
}
