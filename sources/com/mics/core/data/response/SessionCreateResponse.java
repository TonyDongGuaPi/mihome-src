package com.mics.core.data.response;

import com.taobao.weex.el.parse.Operators;

public class SessionCreateResponse {
    private int code;
    private Data data;
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

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public static class Data {
        private boolean closed;
        private String currentKefuId;
        private String currentKefuImg;
        private String currentKefuName;
        private String currentSubSessionId;
        private String fromMerchantId;
        private String fromQueueId;
        private String merchantId;
        private String merchantLogo;
        private String merchantName;
        private String queueId;
        private String roomId;
        private boolean scoreSubmitted;
        private String sessionId;
        private String sessionState;
        private String userId;
        private String userName;
        private int version;

        public String getSessionId() {
            return this.sessionId;
        }

        public void setSessionId(String str) {
            this.sessionId = str;
        }

        public boolean isClosed() {
            return this.closed;
        }

        public void setClosed(boolean z) {
            this.closed = z;
        }

        public String getRoomId() {
            return this.roomId;
        }

        public void setRoomId(String str) {
            this.roomId = str;
        }

        public boolean isScoreSubmitted() {
            return this.scoreSubmitted;
        }

        public void setScoreSubmitted(boolean z) {
            this.scoreSubmitted = z;
        }

        public String getSessionState() {
            return this.sessionState;
        }

        public void setSessionState(String str) {
            this.sessionState = str;
        }

        public String getCurrentSubSessionId() {
            return this.currentSubSessionId;
        }

        public void setCurrentSubSessionId(String str) {
            this.currentSubSessionId = str;
        }

        public String getCurrentKefuId() {
            return this.currentKefuId;
        }

        public void setCurrentKefuId(String str) {
            this.currentKefuId = str;
        }

        public String getCurrentKefuName() {
            return this.currentKefuName;
        }

        public void setCurrentKefuName(String str) {
            this.currentKefuName = str;
        }

        public String getCurrentKefuImg() {
            return this.currentKefuImg;
        }

        public void setCurrentKefuImg(String str) {
            this.currentKefuImg = str;
        }

        public String getFromMerchantId() {
            return this.fromMerchantId;
        }

        public void setFromMerchantId(String str) {
            this.fromMerchantId = str;
        }

        public String getMerchantId() {
            return this.merchantId;
        }

        public void setMerchantId(String str) {
            this.merchantId = str;
        }

        public String getMerchantLogo() {
            return this.merchantLogo;
        }

        public void setMerchantLogo(String str) {
            this.merchantLogo = str;
        }

        public String getMerchantName() {
            return this.merchantName;
        }

        public void setMerchantName(String str) {
            this.merchantName = str;
        }

        public String getQueueId() {
            return this.queueId;
        }

        public void setQueueId(String str) {
            this.queueId = str;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getFromQueueId() {
            return this.fromQueueId;
        }

        public void setFromQueueId(String str) {
            this.fromQueueId = str;
        }

        public int getVersion() {
            return this.version;
        }

        public void setVersion(int i) {
            this.version = i;
        }

        public String toString() {
            return "Data{sessionId='" + this.sessionId + Operators.SINGLE_QUOTE + ", closed=" + this.closed + ", roomId='" + this.roomId + Operators.SINGLE_QUOTE + ", scoreSubmitted=" + this.scoreSubmitted + ", sessionState='" + this.sessionState + Operators.SINGLE_QUOTE + ", currentKefuId='" + this.currentKefuId + Operators.SINGLE_QUOTE + ", currentKefuName='" + this.currentKefuName + Operators.SINGLE_QUOTE + ", currentKefuImg='" + this.currentKefuImg + Operators.SINGLE_QUOTE + ", fromMerchantId='" + this.fromMerchantId + Operators.SINGLE_QUOTE + ", merchantId='" + this.merchantId + Operators.SINGLE_QUOTE + ", queueId='" + this.queueId + Operators.SINGLE_QUOTE + ", userId='" + this.userId + Operators.SINGLE_QUOTE + ", userName='" + this.userName + Operators.SINGLE_QUOTE + ", fromQueueId='" + this.fromQueueId + Operators.SINGLE_QUOTE + ", version=" + this.version + Operators.BLOCK_END;
        }
    }

    public String toString() {
        return "SessionCreateResponse{code=" + this.code + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }
}
