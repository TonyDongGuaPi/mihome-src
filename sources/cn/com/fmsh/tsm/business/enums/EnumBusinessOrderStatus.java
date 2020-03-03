package cn.com.fmsh.tsm.business.enums;

public enum EnumBusinessOrderStatus {
    noOrder(0, "未订购"),
    orderIng(1, "订购中,未知订购结果"),
    orderSucess(2, "订购成功"),
    orderFail(3, "订购失败"),
    unsubscribeing(4, "退订中"),
    unsubscribeSucess(5, "退订成功"),
    unsubscribeFail(6, "退订失败");
    
    private String desc;
    private int id;

    private EnumBusinessOrderStatus(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumBusinessOrderStatus getBusinessOrderStatus4ID(int i) {
        for (EnumBusinessOrderStatus enumBusinessOrderStatus : values()) {
            if (enumBusinessOrderStatus.getId() == i) {
                return enumBusinessOrderStatus;
            }
        }
        return null;
    }
}
