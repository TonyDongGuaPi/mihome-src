package cn.com.fmsh.tsm.business.enums;

public enum EnumOrderType {
    MAIN(1, "主订单"),
    BUSINESS(2, "业务订单"),
    PAY(3, "支付订单");
    
    private String desc;
    private int id;

    private EnumOrderType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumOrderType instance(int i) {
        for (EnumOrderType enumOrderType : values()) {
            if (enumOrderType.getId() == i) {
                return enumOrderType;
            }
        }
        return null;
    }
}
