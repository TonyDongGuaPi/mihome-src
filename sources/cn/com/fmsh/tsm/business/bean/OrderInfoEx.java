package cn.com.fmsh.tsm.business.bean;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoEx {
    private List<OrderChiefInfo> orderChiefInfos = new ArrayList();
    private String tn;

    public String getTn() {
        return this.tn;
    }

    public void setTn(String str) {
        this.tn = str;
    }

    public OrderChiefInfo[] getOrderChiefInfos() {
        return (OrderChiefInfo[]) this.orderChiefInfos.toArray(new OrderChiefInfo[0]);
    }

    public void AddOrderChiefInfo(OrderChiefInfo orderChiefInfo) {
        this.orderChiefInfos.add(orderChiefInfo);
    }
}
