package cn.com.fmsh.nfcos.client.service.business;

import cn.com.fmsh.nfcos.client.service.enums.EnumTicketType;

public class STTicketInfo {
    private byte[] appNo;
    private int inOutFlag;
    private String startUsageTime;
    private EnumTicketType ticketType;
    private String validationDate;

    public EnumTicketType getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(EnumTicketType enumTicketType) {
        this.ticketType = enumTicketType;
    }

    public byte[] getAppNo() {
        return this.appNo;
    }

    public void setAppNo(byte[] bArr) {
        this.appNo = bArr;
    }

    public int getInOutFlag() {
        return this.inOutFlag;
    }

    public void setInOutFlag(int i) {
        this.inOutFlag = i;
    }

    public String getStartUsageTime() {
        return this.startUsageTime;
    }

    public void setStartUsageTime(String str) {
        this.startUsageTime = str;
    }

    public String getValidationDate() {
        return this.validationDate;
    }

    public void setValidationDate(String str) {
        this.validationDate = str;
    }
}
