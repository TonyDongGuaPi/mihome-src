package com.mics.core.data.response;

import com.taobao.weex.el.parse.Operators;

public class BaseResponse {
    private int code;
    private Object data;
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

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public String toString() {
        return "BaseResponse{code=" + this.code + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }
}
