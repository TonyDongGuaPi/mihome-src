package com.xiaomi.smarthome.device.api.spec.operation;

import com.taobao.weex.el.parse.Operators;

public class PropertyParam {
    private String did;
    private int piid;
    private int resultCode;
    private int siid;
    private long timestamp;
    private Object value;

    public PropertyParam() {
        this.resultCode = -1;
    }

    public PropertyParam(String str, int i, int i2) {
        this(str, i, i2, (Object) null);
    }

    public PropertyParam(String str, int i, int i2, Object obj) {
        this.resultCode = -1;
        this.did = str;
        this.siid = i;
        this.piid = i2;
        this.value = obj;
    }

    public String getDid() {
        return this.did;
    }

    public void setDid(String str) {
        this.did = str;
    }

    public int getSiid() {
        return this.siid;
    }

    public void setSiid(int i) {
        this.siid = i;
    }

    public int getPiid() {
        return this.piid;
    }

    public void setPiid(int i) {
        this.piid = i;
    }

    public Object getValue() {
        return this.value;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String toString() {
        return "PropertyParam{did='" + this.did + Operators.SINGLE_QUOTE + ", siid=" + this.siid + ", piid=" + this.piid + ", value=" + this.value + ", timestamp=" + this.timestamp + ", resultCode=" + this.resultCode + Operators.BLOCK_END;
    }
}
