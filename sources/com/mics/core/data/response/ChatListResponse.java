package com.mics.core.data.response;

import java.util.List;

public class ChatListResponse {
    private int code;
    private List<Data> data;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> list) {
        this.data = list;
    }

    public static class Data {
        private long kfMaxReadMsgId;
        private String logoUrl;
        private long maxMsgId;
        private long maxReadMsgId;
        private String merchantId;
        private String merchantName;
        private String roomId;
        private long updateTime;

        public String getMerchantId() {
            return this.merchantId;
        }

        public void setMerchantId(String str) {
            this.merchantId = str;
        }

        public String getMerchantName() {
            return this.merchantName;
        }

        public void setMerchantName(String str) {
            this.merchantName = str;
        }

        public long getMaxMsgId() {
            return this.maxMsgId;
        }

        public void setMaxMsgId(long j) {
            this.maxMsgId = j;
        }

        public long getMaxReadMsgId() {
            return this.maxReadMsgId;
        }

        public void setMaxReadMsgId(long j) {
            this.maxReadMsgId = j;
        }

        public long getKfMaxReadMsgId() {
            return this.kfMaxReadMsgId;
        }

        public void setKfMaxReadMsgId(long j) {
            this.kfMaxReadMsgId = j;
        }

        public String getLogoUrl() {
            return this.logoUrl;
        }

        public void setLogoUrl(String str) {
            this.logoUrl = str;
        }

        public long getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(long j) {
            this.updateTime = j;
        }

        public String getRoomId() {
            return this.roomId;
        }

        public void setRoomId(String str) {
            this.roomId = str;
        }
    }
}
