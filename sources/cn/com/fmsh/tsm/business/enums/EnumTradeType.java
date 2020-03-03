package cn.com.fmsh.tsm.business.enums;

import java.io.Serializable;

public enum EnumTradeType implements Serializable {
    bus(1, "公交"),
    privilege(2, "优惠交易"),
    subwayConsumption(3, "轨道交通消费"),
    subwayUpdate(4, "轨道交通更新"),
    maglev(5, "磁悬浮"),
    recharge(6, "充资"),
    ferry(7, "轮渡"),
    taxi(8, "出租"),
    expressway(9, "高速公路"),
    park(10, "停车场"),
    gasStation(11, "加油站"),
    onlineRecharge(12, "网上充资"),
    onlineConsumption(13, "网上消费"),
    elseTrade(14, "其他交易"),
    Consumption(15, "消费"),
    CompositeConsumption(16, "复合消费");
    
    private String desc;
    private int id;

    private EnumTradeType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }
}
