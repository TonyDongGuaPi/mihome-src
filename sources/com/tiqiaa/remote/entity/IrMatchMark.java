package com.tiqiaa.remote.entity;

import com.imi.fastjson.annotation.JSONField;
import com.tiqiaa.common.IJsonable;

public class IrMatchMark implements IJsonable {
    @JSONField(name = "key_type")
    int key_type;
    @JSONField(name = "mark")
    String mark;
    @JSONField(name = "remote_id")
    String remote_id;

    public String getRemote_id() {
        return this.remote_id;
    }

    public void setRemote_id(String str) {
        this.remote_id = str;
    }

    public int getKey_type() {
        return this.key_type;
    }

    public void setKey_type(int i) {
        this.key_type = i;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String str) {
        this.mark = str;
    }
}
