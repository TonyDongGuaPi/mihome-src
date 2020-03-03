package cn.com.fmsh.tsm.business.enums;

public enum EnumCardIoType {
    CARD_IO_UNKNOW(0, "未知"),
    CARD_IO_TYPE_OUT(1, "外部独立卡（标卡、异形卡）"),
    CARD_IO_TYPE_IN(2, "内卡（SIM卡形态、eSE形态）");
    
    private String desc;
    private int id;

    private EnumCardIoType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumCardIoType getCardIoType(int i) {
        for (EnumCardIoType enumCardIoType : values()) {
            if (enumCardIoType.getId() == i) {
                return enumCardIoType;
            }
        }
        return null;
    }
}
