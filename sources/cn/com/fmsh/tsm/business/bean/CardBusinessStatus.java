package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.tsm.business.enums.EnumAppActivationStatus;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderStatus;

public class CardBusinessStatus {
    private EnumAppActivationStatus activationStatus;
    private EnumBusinessOrderStatus businessOrderStatus;

    public EnumAppActivationStatus getActivationStatus() {
        return this.activationStatus;
    }

    public void setActivationStatus(EnumAppActivationStatus enumAppActivationStatus) {
        this.activationStatus = enumAppActivationStatus;
    }

    public EnumBusinessOrderStatus getBusinessOrderStatus() {
        return this.businessOrderStatus;
    }

    public void setBusinessOrderStatus(EnumBusinessOrderStatus enumBusinessOrderStatus) {
        this.businessOrderStatus = enumBusinessOrderStatus;
    }
}
