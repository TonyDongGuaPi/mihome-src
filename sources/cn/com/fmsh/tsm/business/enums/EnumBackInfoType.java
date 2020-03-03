package cn.com.fmsh.tsm.business.enums;

public enum EnumBackInfoType {
    TRAFFIC_CARD(0, "交通卡应用"),
    RECHARGE_REFUND(1, "充值及退款"),
    CHANGE_MOVE(2, "换卡及移资"),
    SUBWAY_PAY(3, "地铁公交刷卡"),
    IMPROVE_ADVICE(4, "功能改善建议"),
    OTHER(5, "交通卡应用");
    
    private String desc;
    private int id;

    private EnumBackInfoType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumBackInfoType instance(int i) {
        for (EnumBackInfoType enumBackInfoType : values()) {
            if (enumBackInfoType.getId() == i) {
                return enumBackInfoType;
            }
        }
        return null;
    }
}
