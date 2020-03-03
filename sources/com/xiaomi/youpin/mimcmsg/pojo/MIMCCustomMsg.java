package com.xiaomi.youpin.mimcmsg.pojo;

import android.text.TextUtils;
import com.google.gson.Gson;
import java.io.Serializable;

public class MIMCCustomMsg implements Serializable {
    private String bizType;
    private String extra;
    private String fromAccount;
    private String fromUuid;
    private ExtraBean mExtraBean;
    private String msgExtra;
    private String payload;
    private String sequence;
    private long timestamp;
    private String toAccount;
    private long ts;

    public String getSequence() {
        return this.sequence;
    }

    public void setSequence(String str) {
        this.sequence = str;
    }

    public String getPayload() {
        return this.payload;
    }

    public void setPayload(String str) {
        this.payload = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public void setFromAccount(String str) {
        this.fromAccount = str;
    }

    public String getToAccount() {
        return this.toAccount;
    }

    public void setToAccount(String str) {
        this.toAccount = str;
    }

    public String getBizType() {
        return this.bizType;
    }

    public void setBizType(String str) {
        this.bizType = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public String getFromUuid() {
        return this.fromUuid;
    }

    public void setFromUuid(String str) {
        this.fromUuid = str;
    }

    public String getMsgExtra() {
        return this.msgExtra;
    }

    public void setMsgExtra(String str) {
        this.msgExtra = str;
    }

    public long getTs() {
        return this.ts;
    }

    public void setTs(long j) {
        this.ts = j;
    }

    public ExtraBean getExtraBean() {
        if (!TextUtils.isEmpty(this.msgExtra)) {
            this.mExtraBean = (ExtraBean) new Gson().fromJson(this.msgExtra, ExtraBean.class);
        } else if (!TextUtils.isEmpty(this.extra)) {
            this.mExtraBean = (ExtraBean) new Gson().fromJson(this.extra, ExtraBean.class);
        }
        return this.mExtraBean;
    }

    public static class ExtraBean implements Serializable {
        private boolean isRead;
        private boolean isShow;

        public ExtraBean(boolean z, boolean z2) {
            this.isShow = z;
            this.isRead = z2;
        }

        public boolean isShow() {
            return this.isShow;
        }

        public void setShow(boolean z) {
            this.isShow = z;
        }

        public boolean isRead() {
            return this.isRead;
        }

        public void setRead(boolean z) {
            this.isRead = z;
        }
    }
}
