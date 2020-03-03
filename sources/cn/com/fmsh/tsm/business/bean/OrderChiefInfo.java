package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;

public class OrderChiefInfo {
    private int amount;
    private byte[] order;
    private EnumOrderStatus state;
    private EnumBusinessOrderType type;

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

    public EnumBusinessOrderType getType() {
        return this.type;
    }

    public void setType(EnumBusinessOrderType enumBusinessOrderType) {
        this.type = enumBusinessOrderType;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i) {
        this.amount = i;
    }
}
