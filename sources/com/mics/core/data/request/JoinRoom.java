package com.mics.core.data.request;

import com.mics.core.MiCS;

public class JoinRoom {
    private Body body;
    private String cmdCode = String.valueOf(100);
    private long requestId = System.currentTimeMillis();
    private int version = 1;

    public long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(long j) {
        this.requestId = j;
    }

    public String getCmdCode() {
        return this.cmdCode;
    }

    public void setCmdCode(String str) {
        this.cmdCode = str;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        private String channelType = ("android@" + MiCS.a().k() + ":" + MiCS.a().l());
        private String connectionId;
        private String gatewayInfo;
        private long localMaxMsgId;
        private long requestTime = System.currentTimeMillis();
        private String roomId;
        private String userId;
        private String userName;

        public String getRoomId() {
            return this.roomId;
        }

        public void setRoomId(String str) {
            this.roomId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getConnectionId() {
            return this.connectionId;
        }

        public void setConnectionId(String str) {
            this.connectionId = str;
        }

        public String getChannelType() {
            return this.channelType;
        }

        public void setChannelType(String str) {
            this.channelType = str;
        }

        public String getGatewayInfo() {
            return this.gatewayInfo;
        }

        public void setGatewayInfo(String str) {
            this.gatewayInfo = str;
        }

        public long getRequestTime() {
            return this.requestTime;
        }

        public void setRequestTime(long j) {
            this.requestTime = j;
        }

        public long getLocalMaxMsgId() {
            return this.localMaxMsgId;
        }

        public void setLocalMaxMsgId(long j) {
            this.localMaxMsgId = j;
        }
    }
}
