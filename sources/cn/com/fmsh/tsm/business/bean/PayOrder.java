package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class PayOrder {
    private int amount;
    private int channel;
    private String date;
    private byte[] id;
    private byte[] mainOrder;
    private String payUser;
    private EnumOrderStatus state;
    private String thirdPayInfo;
    private String time;

    public byte[] getId() {
        return this.id;
    }

    public void setId(byte[] bArr) {
        this.id = bArr;
    }

    public String getThirdPayInfo() {
        return this.thirdPayInfo;
    }

    public void setThirdPayInfo(String str) {
        this.thirdPayInfo = str;
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

    public EnumOrderStatus getState() {
        return this.state;
    }

    public void setState(EnumOrderStatus enumOrderStatus) {
        this.state = enumOrderStatus;
    }

    public String getPayUser() {
        return this.payUser;
    }

    public void setPayUser(String str) {
        this.payUser = str;
    }

    public int getChannel() {
        return this.channel;
    }

    public void setChannel(int i) {
        this.channel = i;
    }

    public byte[] getMainOrder() {
        return this.mainOrder;
    }

    public void setMainOrder(byte[] bArr) {
        this.mainOrder = bArr;
    }

    public static PayOrder fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "Tag生成支付订单时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "Tag生成支付订单时，Tag子项为空");
            }
            return null;
        }
        PayOrder payOrder = new PayOrder();
        for (ITag iTag2 : itemTags) {
            byte id2 = iTag2.getId();
            if (id2 == 2) {
                payOrder.setPayUser(iTag2.getStringVal());
            } else if (id2 == 13) {
                payOrder.setChannel(iTag2.getIntVal());
            } else if (id2 != 16) {
                switch (id2) {
                    case 18:
                        payOrder.setThirdPayInfo(iTag2.getStringVal());
                        break;
                    case 19:
                        payOrder.setDate(iTag2.getStringVal());
                        break;
                    case 20:
                        payOrder.setTime(iTag2.getStringVal());
                        break;
                    case 21:
                        payOrder.setState(EnumOrderStatus.getOrderStatus4ID(iTag2.getIntVal()));
                        break;
                    default:
                        switch (id2) {
                            case 105:
                                payOrder.setMainOrder(iTag2.getBytesVal());
                                break;
                            case 106:
                                payOrder.setId(iTag2.getBytesVal());
                                break;
                        }
                }
            } else {
                payOrder.setAmount(FM_Bytes.bytesToInt(iTag2.getBytesVal()));
            }
        }
        return payOrder;
    }
}
