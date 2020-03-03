package com.mics.core.data.ws;

import java.util.List;

public class NotifyEvent extends NotifyMessage {
    private Body body;

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body2) {
        this.body = body2;
    }

    public static class Body {
        private String content;
        private String notifyType;
        private String roomId;
        private String toUserConnectionId;
        private String toUserId;

        public String getNotifyType() {
            return this.notifyType;
        }

        public void setNotifyType(String str) {
            this.notifyType = str;
        }

        public String getRoomId() {
            return this.roomId;
        }

        public void setRoomId(String str) {
            this.roomId = str;
        }

        public String getToUserId() {
            return this.toUserId;
        }

        public void setToUserId(String str) {
            this.toUserId = str;
        }

        public String getToUserConnectionId() {
            return this.toUserConnectionId;
        }

        public void setToUserConnectionId(String str) {
            this.toUserConnectionId = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }
    }

    public static class Content {
        private List<String> additionMsg;
        private String content;
        private String event;
        private String sessionId;
        private String subSessionId;
        private String type;

        public String getEvent() {
            return this.event;
        }

        public void setEvent(String str) {
            this.event = str;
        }

        public String getSessionId() {
            return this.sessionId;
        }

        public void setSessionId(String str) {
            this.sessionId = str;
        }

        public String getSubSessionId() {
            return this.subSessionId;
        }

        public void setSubSessionId(String str) {
            this.subSessionId = str;
        }

        public List<String> getAdditionMsg() {
            return this.additionMsg;
        }

        public void setAdditionMsg(List<String> list) {
            this.additionMsg = list;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }
    }

    public static class AdditionMsg {
        private String content;
        private String event;
        private String type;

        public String getEvent() {
            return this.event;
        }

        public void setEvent(String str) {
            this.event = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }
    }

    public static class ReadOffset {
        private long readOffset;
        private String role;
        private String userId;

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public long getReadOffset() {
            return this.readOffset;
        }

        public void setReadOffset(long j) {
            this.readOffset = j;
        }

        public String getRole() {
            return this.role;
        }

        public void setRole(String str) {
            this.role = str;
        }
    }
}
