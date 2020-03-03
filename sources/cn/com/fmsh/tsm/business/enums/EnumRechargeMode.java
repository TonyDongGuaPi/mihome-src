package cn.com.fmsh.tsm.business.enums;

public enum EnumRechargeMode {
    COMPANY_GIVE(0, "商户优惠"),
    ALIPAY_ONE_KEY(1, "支付宝一键支付"),
    ALIPAY_SECURE(2, "支付宝安全支付"),
    UNIONPAY(3, "银联无卡-卡公司光大 "),
    UNIONPAY_CARD(49, "银联有卡-卡公司光大"),
    UNIONPAY_FM(4, "银联-复旦微"),
    UNIONPAY_CARD_FM(65, "银联有卡-复旦微账号 "),
    CARD_SHIFT_CAPITAL(58, "交通卡补充值(不可领发票)"),
    MIPAY_MI(81, "小米支付"),
    SAMSUNG_PAY(86, "三星支付"),
    UNIONPAY_CARD_PT(87, "银联有卡-鹏泰三星"),
    MOBILE_PROMOTION(60, "移动促销(不可领发票"),
    HW_PAY(69, "华为支付"),
    LKL_PAY(80, "拉卡拉支付");
    
    private String desc;
    private int id;

    private EnumRechargeMode(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumRechargeMode instance(int i) {
        for (EnumRechargeMode enumRechargeMode : values()) {
            if (enumRechargeMode.getId() == i) {
                return enumRechargeMode;
            }
        }
        return null;
    }
}
