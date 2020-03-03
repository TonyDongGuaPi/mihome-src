package cn.com.fmsh.tsm.business.enums;

import java.io.Serializable;

public enum EnumOrderStatus implements Serializable {
    notExist(0, "订单不存在"),
    notPay(1, "未支付"),
    hasPaid(2, "已付款"),
    success(3, "交易成功"),
    failure(4, "交易失败"),
    unsettled(5, "交易状态未知"),
    apilyForRefund(6, "申请退款中"),
    hasRefunded(7, "已退款"),
    refundFailure(8, "退款失败"),
    payFailure(9, "付款失败"),
    waitForPay(10, "待支付"),
    recharging(11, "交通卡充值中"),
    dubious(12, "可疑账待处理"),
    invalid(13, "订单关闭"),
    unknown(99, "未知");
    
    private String desc;
    private int id;

    private EnumOrderStatus(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumOrderStatus getOrderStatus4ID(int i) {
        for (EnumOrderStatus enumOrderStatus : values()) {
            if (enumOrderStatus.getId() == i) {
                return enumOrderStatus;
            }
        }
        return null;
    }
}
