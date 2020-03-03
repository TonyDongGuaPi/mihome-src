package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class IdentifyingCode {
    private String code;
    private byte[] serial;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public byte[] getSerial() {
        return this.serial;
    }

    public void setSerial(byte[] bArr) {
        this.serial = bArr;
    }

    public static IdentifyingCode fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到验证码对象时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到验证码对象时，Tag对象为空");
            }
            return null;
        }
        IdentifyingCode identifyingCode = new IdentifyingCode();
        for (ITag iTag2 : itemTags) {
            byte id = iTag2.getId();
            if (id == 12) {
                identifyingCode.setCode(iTag2.getStringVal());
            } else if (id == 64) {
                identifyingCode.setSerial(iTag2.getTagValue());
            }
        }
        return identifyingCode;
    }
}
