package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;

public class OrderInfo {
    private byte[] order;
    private EnumOrderStatus state;
    private String tn;

    public EnumOrderStatus getState() {
        return this.state;
    }

    public void setState(EnumOrderStatus enumOrderStatus) {
        this.state = enumOrderStatus;
    }

    public byte[] getOrder() {
        return this.order;
    }

    public void setOrder(byte[] bArr) {
        this.order = bArr;
    }

    public String getTn() {
        return this.tn;
    }

    public void setTn(String str) {
        this.tn = str;
    }
}
