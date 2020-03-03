package com.xiaomi.youpin.mimcmsg.pojo;

import java.io.Serializable;
import java.util.List;

public class RecentSessionResponse extends BaseBean<ContactsBean> {

    public static class ContactsBean implements Serializable {
        private String appAccount;
        private String appId;
        private List<RecentItemBean> contacts;
        private int row;
        private long timestamp;
        private long uuid;

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String str) {
            this.appId = str;
        }

        public String getAppAccount() {
            return this.appAccount;
        }

        public void setAppAccount(String str) {
            this.appAccount = str;
        }

        public long getUuid() {
            return this.uuid;
        }

        public void setUuid(long j) {
            this.uuid = j;
        }

        public int getRow() {
            return this.row;
        }

        public void setRow(int i) {
            this.row = i;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public void setTimestamp(long j) {
            this.timestamp = j;
        }

        public List<RecentItemBean> getContacts() {
            return this.contacts;
        }

        public void setContacts(List<RecentItemBean> list) {
            this.contacts = list;
        }
    }

    public static class RecentItemBean implements Serializable {
        private String extra;
        private String id;
        private MIMCCustomMsg lastMessage;
        private String name;
        private String timestamp;
        private String userType;

        public String getUserType() {
            return this.userType;
        }

        public void setUserType(String str) {
            this.userType = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getTimestamp() {
            return this.timestamp;
        }

        public void setTimestamp(String str) {
            this.timestamp = str;
        }

        public String getExtra() {
            return this.extra;
        }

        public void setExtra(String str) {
            this.extra = str;
        }

        public MIMCCustomMsg getLastMessage() {
            return this.lastMessage;
        }

        public void setLastMessage(MIMCCustomMsg mIMCCustomMsg) {
            this.lastMessage = mIMCCustomMsg;
        }
    }
}
