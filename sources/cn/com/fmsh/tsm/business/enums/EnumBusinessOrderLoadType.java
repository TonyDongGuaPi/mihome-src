package cn.com.fmsh.tsm.business.enums;

import java.io.Serializable;

public enum EnumBusinessOrderLoadType implements Serializable {
    unknown(0, "未知"),
    load(1, "普通充值"),
    supplementLoad(2, "补充值"),
    welfareLoad(3, "福利充值"),
    promotionLoad(4, "营销充值 ");
    
    private String desc;
    private int id;

    private EnumBusinessOrderLoadType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumBusinessOrderLoadType getBusinessOrderLoadType4ID(int i) {
        for (EnumBusinessOrderLoadType enumBusinessOrderLoadType : values()) {
            if (enumBusinessOrderLoadType.getId() == i) {
                return enumBusinessOrderLoadType;
            }
        }
        return null;
    }
}
