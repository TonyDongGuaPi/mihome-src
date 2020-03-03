package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class InvoiceToken {
    private byte[] orderId;
    private String token;

    public byte[] getOrderId() {
        return this.orderId;
    }

    public void setOrderId(byte[] bArr) {
        this.orderId = bArr;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public static InvoiceToken fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "Tag生成发票领取凭证信息时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), "Tag生成发票领取凭证信息时，Tag子项为空");
            }
            return null;
        }
        InvoiceToken invoiceToken = new InvoiceToken();
        for (ITag iTag2 : itemTags) {
            byte id = iTag2.getId();
            if (id == 17) {
                invoiceToken.setOrderId(iTag2.getBytesVal());
            } else if (id == 66) {
                invoiceToken.setToken(FM_Bytes.bytesToHexString(iTag2.getBytesVal()));
            }
        }
        return invoiceToken;
    }
}
