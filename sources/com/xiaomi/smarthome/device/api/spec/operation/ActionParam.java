package com.xiaomi.smarthome.device.api.spec.operation;

import java.util.ArrayList;
import java.util.List;

public class ActionParam {
    private int aiid;
    private String did;

    /* renamed from: in  reason: collision with root package name */
    private List<Object> f15012in = new ArrayList();
    private List<Object> out = new ArrayList();
    private int resultCode = -1;
    private int siid;

    public ActionParam() {
    }

    public ActionParam(String str, int i, int i2, List<Object> list) {
        this.did = str;
        this.siid = i;
        this.aiid = i2;
        this.f15012in = list;
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

    public int getAiid() {
        return this.aiid;
    }

    public void setAiid(int i) {
        this.aiid = i;
    }

    public List<Object> getIn() {
        return this.f15012in;
    }

    public void setIn(List<Object> list) {
        this.f15012in = list;
    }

    public List<Object> getOut() {
        return this.out;
    }

    public void setOut(List<Object> list) {
        this.out = list;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }
}
