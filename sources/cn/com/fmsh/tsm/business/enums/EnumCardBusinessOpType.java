package cn.com.fmsh.tsm.business.enums;

public enum EnumCardBusinessOpType {
    ORDER(1, "订购激活"),
    UNSUBSCRIBE(2, "退订");
    
    private String desc;
    private int id;

    private EnumCardBusinessOpType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumCardBusinessOpType getCardIoType(int i) {
        for (EnumCardBusinessOpType enumCardBusinessOpType : values()) {
            if (enumCardBusinessOpType.getId() == i) {
                return enumCardBusinessOpType;
            }
        }
        return null;
    }
}
