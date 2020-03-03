package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderLoadType;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.Serializable;

public class BusinessOrder implements Serializable {
    private static final long serialVersionUID = 1;
    private int amount;
    private EnumBusinessOrderLoadType businessOrderLoadType;
    private EnumBusinessOrderType businessOrderType;
    private EnumCardAppType cardAppType;
    private EnumCardIoType cardIoType;
    private byte[] cardNo;
    private String deviceModel;
    private int invoiceStatus;
    private byte[] mainOrder;
    private byte[] order;
    private int payChannel;
    private String product;
    private byte[] seid;
    private int serialNo;
    private String tac;
    private byte[] terminalNo;
    private String tradeDate;
    private EnumOrderStatus tradeState;
    private String tradeTime;

    public String getTradeDate() {
        return this.tradeDate;
    }

    public void setTradeDate(String str) {
        this.tradeDate = str;
    }

    public String getTradeTime() {
        return this.tradeTime;
    }

    public void setTradeTime(String str) {
        this.tradeTime = str;
    }

    public byte[] getOrder() {
        return this.order;
    }

    public void setOrder(byte[] bArr) {
        this.order = bArr;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i) {
        this.amount = i;
    }

    public EnumOrderStatus getTradeState() {
        return this.tradeState;
    }

    public void setTradeState(EnumOrderStatus enumOrderStatus) {
        this.tradeState = enumOrderStatus;
    }

    public int getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(int i) {
        this.serialNo = i;
    }

    public byte[] getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(byte[] bArr) {
        this.cardNo = bArr;
    }

    public byte[] getTerminalNo() {
        return this.terminalNo;
    }

    public void setTerminalNo(byte[] bArr) {
        this.terminalNo = bArr;
    }

    public String getTac() {
        return this.tac;
    }

    public void setTac(String str) {
        this.tac = str;
    }

    public int getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public void setInvoiceStatus(int i) {
        this.invoiceStatus = i;
    }

    public int getPayChannel() {
        return this.payChannel;
    }

    public void setPayChannel(int i) {
        this.payChannel = i;
    }

    public EnumCardIoType getCardIoType() {
        return this.cardIoType;
    }

    public void setCardIoType(EnumCardIoType enumCardIoType) {
        this.cardIoType = enumCardIoType;
    }

    public EnumCardAppType getCardAppType() {
        return this.cardAppType;
    }

    public void setCardAppType(EnumCardAppType enumCardAppType) {
        this.cardAppType = enumCardAppType;
    }

    public EnumBusinessOrderType getBusinessOrderType() {
        return this.businessOrderType;
    }

    public void setBusinessOrderType(EnumBusinessOrderType enumBusinessOrderType) {
        this.businessOrderType = enumBusinessOrderType;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String str) {
        this.product = str;
    }

    public byte[] getSeid() {
        return this.seid;
    }

    public void setSeid(byte[] bArr) {
        this.seid = bArr;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public byte[] getMainOrder() {
        return this.mainOrder;
    }

    public void setMainOrder(byte[] bArr) {
        this.mainOrder = bArr;
    }

    public EnumBusinessOrderLoadType getBusinessOrderLoadType() {
        return this.businessOrderLoadType;
    }

    public void setBusinessOrderLoadType(EnumBusinessOrderLoadType enumBusinessOrderLoadType) {
        this.businessOrderLoadType = enumBusinessOrderLoadType;
    }

    public static BusinessOrder fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "Tag生成业务订单时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "Tag生成业务订单时，Tag子项为空");
            }
            return null;
        }
        BusinessOrder businessOrder = new BusinessOrder();
        for (ITag iTag2 : itemTags) {
            byte id = iTag2.getId();
            if (id == -79) {
                businessOrder.setSeid(iTag2.getBytesVal());
            } else if (id == -55) {
                businessOrder.setBusinessOrderLoadType(EnumBusinessOrderLoadType.getBusinessOrderLoadType4ID(iTag2.getIntVal()));
            } else if (id == 47) {
                businessOrder.setCardIoType(EnumCardIoType.getCardIoType(iTag2.getIntVal()));
            } else if (id != 72) {
                switch (id) {
                    case 13:
                        businessOrder.setPayChannel(iTag2.getIntVal());
                        break;
                    case 14:
                        businessOrder.setCardAppType(EnumCardAppType.instance(iTag2.getIntVal()));
                        break;
                    case 15:
                        businessOrder.setCardNo(FM_Bytes.hexStringToBytes(iTag2.getStringVal()));
                        break;
                    case 16:
                        businessOrder.setAmount(FM_Bytes.bytesToInt(iTag2.getBytesVal()));
                        break;
                    case 17:
                        businessOrder.setOrder(iTag2.getBytesVal());
                        break;
                    default:
                        switch (id) {
                            case 19:
                                businessOrder.setTradeDate(iTag2.getStringVal());
                                break;
                            case 20:
                                businessOrder.setTradeTime(iTag2.getStringVal());
                                break;
                            case 21:
                                businessOrder.setTradeState(EnumOrderStatus.getOrderStatus4ID(iTag2.getIntVal()));
                                break;
                            case 22:
                                businessOrder.setSerialNo(FM_Bytes.bytesToInt(iTag2.getBytesVal()));
                                break;
                            case 23:
                                businessOrder.setTerminalNo(FM_Bytes.hexStringToBytes(iTag2.getStringVal()));
                                break;
                            case 24:
                                businessOrder.setInvoiceStatus(iTag2.getIntVal());
                                break;
                            default:
                                switch (id) {
                                    case 103:
                                        businessOrder.setProduct(iTag2.getStringVal());
                                        break;
                                    case 104:
                                        businessOrder.setDeviceModel(iTag2.getStringVal());
                                        break;
                                    case 105:
                                        businessOrder.setMainOrder(iTag2.getBytesVal());
                                        break;
                                }
                        }
                }
            } else {
                businessOrder.setBusinessOrderType(EnumBusinessOrderType.instance(iTag2.getIntVal()));
            }
        }
        return businessOrder;
    }
}
