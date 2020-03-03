package com.mics.core.data.business;

import java.util.List;

public class ChatList {
    private List<Data> data;

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> list) {
        this.data = list;
    }

    public static class Data {
        private String msgContent;
        private String msgMerchantId;
        private String msgMerchantLogo;
        private String msgRoomId;
        private String msgTitle;
        private long msgUnReadCount;
        private long msgUpdateTime;

        public String getMsgRoomId() {
            return this.msgRoomId;
        }

        public void setMsgRoomId(String str) {
            this.msgRoomId = str;
        }

        public String getMsgMerchantId() {
            return this.msgMerchantId;
        }

        public void setMsgMerchantId(String str) {
            this.msgMerchantId = str;
        }

        public String getMsgMerchantLogo() {
            return this.msgMerchantLogo;
        }

        public void setMsgMerchantLogo(String str) {
            this.msgMerchantLogo = str;
        }

        public String getMsgTitle() {
            return this.msgTitle;
        }

        public void setMsgTitle(String str) {
            this.msgTitle = str;
        }

        public String getMsgContent() {
            return this.msgContent;
        }

        public void setMsgContent(String str) {
            this.msgContent = str;
        }

        public long getMsgUnReadCount() {
            return this.msgUnReadCount;
        }

        public void setMsgUnReadCount(long j) {
            this.msgUnReadCount = j;
        }

        public long getMsgUpdateTime() {
            return this.msgUpdateTime;
        }

        public void setMsgUpdateTime(long j) {
            this.msgUpdateTime = j;
        }
    }
}
