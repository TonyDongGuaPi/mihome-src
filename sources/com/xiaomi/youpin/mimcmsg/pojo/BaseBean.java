package com.xiaomi.youpin.mimcmsg.pojo;

import com.google.gson.Gson;

public class BaseBean<T> implements IResponse {
    private int code;
    private T data;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }

    public String toJson() {
        return new Gson().toJson((Object) this);
    }
}
