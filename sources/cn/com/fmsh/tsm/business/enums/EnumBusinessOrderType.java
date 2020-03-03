package cn.com.fmsh.tsm.business.enums;

public enum EnumBusinessOrderType {
    ORDER_TYPE_RECHARGE(1, "充值订单"),
    ORDER_TYPE_ISSUE(2, "发卡订单"),
    ORDER_TYPE_PROMOTION(3, "促销订单"),
    TRANSFER(4, "转账订单"),
    ORDER_TYPE_ADDED(5, "补充值订单"),
    ORDER_TYPE_WELFARE(6, "福利订单"),
    UNKNOW(0, "未知");
    
    private String desc;
    private int id;

    private EnumBusinessOrderType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumBusinessOrderType instance(int i) {
        for (EnumBusinessOrderType enumBusinessOrderType : values()) {
            if (enumBusinessOrderType.getId() == i) {
                return enumBusinessOrderType;
            }
        }
        return null;
    }
}
