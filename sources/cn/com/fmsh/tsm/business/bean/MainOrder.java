package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderLoadType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.util.FM_Bytes;
import java.util.ArrayList;
import java.util.List;

public class MainOrder {
    private int amount;
    private EnumBusinessOrderLoadType businessOrderLoadType;
    private List<BusinessOrder> businessOrders = new ArrayList();
    private String date;
    private byte[] id;
    private List<PayOrder> payOrders = new ArrayList();
    private EnumOrderStatus state;
    private String time;

    public EnumOrderStatus getState() {
        return this.state;
    }

    public void setState(EnumOrderStatus enumOrderStatus) {
        this.state = enumOrderStatus;
    }

    public byte[] getId() {
        return this.id;
    }

    public void setId(byte[] bArr) {
        this.id = bArr;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i) {
        this.amount = i;
    }

    public BusinessOrder[] getBusinessOrders() {
        return (BusinessOrder[]) this.businessOrders.toArray(new BusinessOrder[0]);
    }

    public void addBusinessOrders(BusinessOrder businessOrder) {
        if (businessOrder != null) {
            this.businessOrders.add(businessOrder);
        }
    }

    public PayOrder[] getPayOrders() {
        return (PayOrder[]) this.payOrders.toArray(new PayOrder[0]);
    }

    public void addPayOrders(PayOrder payOrder) {
        if (payOrder != null) {
            this.payOrders.add(payOrder);
        }
    }

    public EnumBusinessOrderLoadType getBusinessOrderLoadType() {
        return this.businessOrderLoadType;
    }

    public void setBusinessOrderLoadType(EnumBusinessOrderLoadType enumBusinessOrderLoadType) {
        this.businessOrderLoadType = enumBusinessOrderLoadType;
    }

    public static MainOrder fromTag(ITag iTag) throws FMCommunicationMessageException {
        ITag[] itemTags;
        if (iTag == null || (itemTags = iTag.getItemTags()) == null || itemTags.length < 1) {
            return null;
        }
        MainOrder mainOrder = new MainOrder();
        for (ITag iTag2 : itemTags) {
            byte id2 = iTag2.getId();
            if (id2 == -55) {
                mainOrder.setBusinessOrderLoadType(EnumBusinessOrderLoadType.getBusinessOrderLoadType4ID(iTag2.getIntVal()));
            } else if (id2 == 16) {
                mainOrder.setAmount(FM_Bytes.bytesToInt(iTag2.getBytesVal()));
            } else if (id2 == 27) {
                ITag[] itemTags2 = iTag2.getItemTags();
                if (itemTags2 != null && itemTags2.length > 0) {
                    for (ITag fromTag : itemTags2) {
                        BusinessOrder fromTag2 = BusinessOrder.fromTag(fromTag);
                        if (fromTag2 != null) {
                            mainOrder.addBusinessOrders(fromTag2);
                        }
                    }
                }
            } else if (id2 == 100) {
                ITag[] itemTags3 = iTag2.getItemTags();
                if (itemTags3 != null && itemTags3.length > 0) {
                    for (ITag fromTag3 : itemTags3) {
                        PayOrder fromTag4 = PayOrder.fromTag(fromTag3);
                        if (fromTag4 != null) {
                            mainOrder.addPayOrders(fromTag4);
                        }
                    }
                }
            } else if (id2 != 105) {
                switch (id2) {
                    case 19:
                        mainOrder.setDate(iTag2.getStringVal());
                        break;
                    case 20:
                        mainOrder.setTime(iTag2.getStringVal());
                        break;
                    case 21:
                        mainOrder.setState(EnumOrderStatus.getOrderStatus4ID(iTag2.getIntVal()));
                        break;
                }
            } else {
                mainOrder.setId(iTag2.getBytesVal());
            }
        }
        return mainOrder;
    }
}
