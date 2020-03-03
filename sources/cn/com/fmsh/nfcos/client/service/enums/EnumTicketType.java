package cn.com.fmsh.nfcos.client.service.enums;

import java.io.Serializable;

public enum EnumTicketType implements Serializable {
    NONE(0, "无效票"),
    TICKET_TYPE_ONE_DAY(1, "申通一日票"),
    TICKET_TYPE_THRESS_DAYS(3, "申通三日票");
    
    private String desc;
    private int id;

    private EnumTicketType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumTicketType instance(int i) {
        for (EnumTicketType enumTicketType : values()) {
            if (enumTicketType.getId() == i) {
                return enumTicketType;
            }
        }
        return null;
    }
}
