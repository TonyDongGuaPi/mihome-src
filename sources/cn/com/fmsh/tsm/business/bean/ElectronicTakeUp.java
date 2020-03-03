package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class ElectronicTakeUp {
    private int appType;
    private String endTime;
    private int frozenFlag;
    private byte[] id;
    private String number;
    private byte[] order;
    private String orderElseThing;
    private byte[] outSerial;
    private int outState;
    private int price;
    private int price4favourable;
    private String publishEndTime;
    private String publishStartTime;
    private String startTime;
    private int state;
    private int tradeState;
    private int transferFlag;
    private int type;
    private byte[] typeId;
    private int useCount;
    private String useTime;
    private int useType;

    public byte[] getId() {
        return this.id;
    }

    public void setId(byte[] bArr) {
        this.id = bArr;
    }

    public byte[] getTypeId() {
        return this.typeId;
    }

    public void setTypeId(byte[] bArr) {
        this.typeId = bArr;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getUseType() {
        return this.useType;
    }

    public void setUseType(int i) {
        this.useType = i;
    }

    public int getTransferFlag() {
        return this.transferFlag;
    }

    public void setTransferFlag(int i) {
        this.transferFlag = i;
    }

    public int getFrozenFlag() {
        return this.frozenFlag;
    }

    public void setFrozenFlag(int i) {
        this.frozenFlag = i;
    }

    public int getUseCount() {
        return this.useCount;
    }

    public void setUseCount(int i) {
        this.useCount = i;
    }

    public int getAppType() {
        return this.appType;
    }

    public void setAppType(int i) {
        this.appType = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getOutState() {
        return this.outState;
    }

    public void setOutState(int i) {
        this.outState = i;
    }

    public String getUseTime() {
        return this.useTime;
    }

    public void setUseTime(String str) {
        this.useTime = str;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public int getPrice4favourable() {
        return this.price4favourable;
    }

    public void setPrice4favourable(int i) {
        this.price4favourable = i;
    }

    public byte[] getOutSerial() {
        return this.outSerial;
    }

    public void setOutSerial(byte[] bArr) {
        this.outSerial = bArr;
    }

    public String getPublishStartTime() {
        return this.publishStartTime;
    }

    public void setPublishStartTime(String str) {
        this.publishStartTime = str;
    }

    public String getPublishEndTime() {
        return this.publishEndTime;
    }

    public void setPublishEndTime(String str) {
        this.publishEndTime = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public int getTradeState() {
        return this.tradeState;
    }

    public void setTradeState(int i) {
        this.tradeState = i;
    }

    public byte[] getOrder() {
        return this.order;
    }

    public void setOrder(byte[] bArr) {
        this.order = bArr;
    }

    public String getOrderElseThing() {
        return this.orderElseThing;
    }

    public void setOrderElseThing(String str) {
        this.orderElseThing = str;
    }

    public static ElectronicTakeUp fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "转换Tag对象到电子卷信息时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到反馈信息时，Tag对象为空");
            }
            return null;
        }
        ElectronicTakeUp electronicTakeUp = new ElectronicTakeUp();
        for (ITag iTag2 : itemTags) {
            byte id2 = iTag2.getId();
            if (id2 != 21) {
                switch (id2) {
                    case 17:
                        electronicTakeUp.setOrder(iTag2.getBytesVal());
                        break;
                    case 18:
                        electronicTakeUp.setOrderElseThing(iTag2.getStringVal());
                        break;
                    default:
                        switch (id2) {
                            case 109:
                                electronicTakeUp.setPublishEndTime(iTag2.getStringVal());
                                break;
                            case 110:
                                electronicTakeUp.setStartTime(iTag2.getStringVal());
                                break;
                            case 111:
                                electronicTakeUp.setEndTime(iTag2.getStringVal());
                                break;
                            case 112:
                                electronicTakeUp.setId(iTag2.getBytesVal());
                                break;
                            case 113:
                                electronicTakeUp.setTypeId(iTag2.getBytesVal());
                                break;
                            case 114:
                                electronicTakeUp.setNumber(iTag2.getStringVal());
                                break;
                            case 115:
                                electronicTakeUp.setType(iTag2.getIntVal());
                                break;
                            case 116:
                                electronicTakeUp.setUseType(iTag2.getIntVal());
                                break;
                            case 117:
                                electronicTakeUp.setTransferFlag(iTag2.getIntVal());
                                break;
                            case 118:
                                electronicTakeUp.setFrozenFlag(iTag2.getIntVal());
                                break;
                            case 119:
                                electronicTakeUp.setUseCount(iTag2.getIntVal());
                                break;
                            case 120:
                                electronicTakeUp.setAppType(iTag2.getIntVal());
                                break;
                            case 121:
                                electronicTakeUp.setState(iTag2.getIntVal());
                                break;
                            case 122:
                                electronicTakeUp.setOutState(iTag2.getIntVal());
                                break;
                            case 123:
                                electronicTakeUp.setUseTime(iTag2.getStringVal());
                                break;
                            case 124:
                                electronicTakeUp.setPrice(iTag2.getIntVal());
                                break;
                            case 125:
                                electronicTakeUp.setPrice4favourable(iTag2.getIntVal());
                                break;
                            case 126:
                                electronicTakeUp.setOutSerial(iTag2.getBytesVal());
                                break;
                            case Byte.MAX_VALUE:
                                electronicTakeUp.setPublishStartTime(iTag2.getStringVal());
                                break;
                        }
                }
            } else {
                electronicTakeUp.setTradeState((byte) iTag2.getIntVal());
            }
        }
        return electronicTakeUp;
    }
}
