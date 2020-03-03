package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.enums.EnumBackInfoType;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class TerminalBackInfo {
    private String appVersion = null;
    private String basebandVersion;
    private byte[] children;
    private String date;
    private byte[] id;
    private EnumBackInfoType infoType = null;
    private String modelNumber;
    private String osVersion;
    private int questionFlag;
    private String time;
    private String title = null;

    public byte[] getId() {
        return this.id;
    }

    public void setId(byte[] bArr) {
        this.id = bArr;
    }

    public byte[] getChildren() {
        return this.children;
    }

    public void setChildren(byte[] bArr) {
        this.children = bArr;
    }

    public int getQuestionFlag() {
        return this.questionFlag;
    }

    public void setQuestionFlag(int i) {
        this.questionFlag = i;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public EnumBackInfoType getInfoType() {
        return this.infoType;
    }

    public void setInfoType(EnumBackInfoType enumBackInfoType) {
        this.infoType = enumBackInfoType;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getModelNumber() {
        return this.modelNumber;
    }

    public void setModelNumber(String str) {
        this.modelNumber = str;
    }

    public String getBasebandVersion() {
        return this.basebandVersion;
    }

    public void setBasebandVersion(String str) {
        this.basebandVersion = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public static TerminalBackInfo fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到反馈信息时，Tag对象为空");
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
        TerminalBackInfo terminalBackInfo = new TerminalBackInfo();
        for (ITag iTag2 : itemTags) {
            switch (iTag2.getId()) {
                case 19:
                    terminalBackInfo.setDate(iTag2.getStringVal());
                    break;
                case 20:
                    terminalBackInfo.setTime(iTag2.getStringVal());
                    break;
                case 65:
                    terminalBackInfo.setTitle(iTag2.getStringVal());
                    break;
                case 67:
                    terminalBackInfo.setInfoType(EnumBackInfoType.instance(iTag2.getIntVal()));
                    break;
                case 81:
                    terminalBackInfo.setId(iTag2.getBytesVal());
                    break;
                case 82:
                    terminalBackInfo.setQuestionFlag(iTag2.getIntVal());
                    break;
                case 85:
                    terminalBackInfo.setChildren(iTag2.getBytesVal());
                    break;
            }
        }
        return terminalBackInfo;
    }
}
