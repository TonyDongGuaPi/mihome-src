package cn.com.fmsh.tsm.business.enums;

import java.io.Serializable;

public enum EnumCardAppType implements Serializable {
    CARD_APP_TYPE_SH(1, "上海交通卡"),
    CARD_APP_TYPE_LNT(2, "岭南通"),
    CARD_APP_TYPE_SH_TOUR(3, "上海旅游卡"),
    CARD_APP_TYPE_SH_RENT(4, "上海短租卡");
    
    private String desc;
    private int id;

    private EnumCardAppType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumCardAppType instance(int i) {
        for (EnumCardAppType enumCardAppType : values()) {
            if (enumCardAppType.getId() == i) {
                return enumCardAppType;
            }
        }
        return null;
    }
}
