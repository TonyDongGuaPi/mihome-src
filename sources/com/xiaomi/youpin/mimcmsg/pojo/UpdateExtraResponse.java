package com.xiaomi.youpin.mimcmsg.pojo;

import java.io.Serializable;
import java.util.List;

public class UpdateExtraResponse extends BaseBean<List<UpdateMsgItem>> {

    public static class UpdateMsgItem implements Serializable {
        private String extra;
        private String fromAccount;
        private String sequence;
        private String toAccount;
        private String updateCode;
        private String updateMsg;

        public String getUpdateCode() {
            return this.updateCode;
        }

        public void setUpdateCode(String str) {
            this.updateCode = str;
        }

        public String getUpdateMsg() {
            return this.updateMsg;
        }

        public void setUpdateMsg(String str) {
            this.updateMsg = str;
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

        public String getSequence() {
            return this.sequence;
        }

        public void setSequence(String str) {
            this.sequence = str;
        }

        public String getExtra() {
            return this.extra;
        }

        public void setExtra(String str) {
            this.extra = str;
        }
    }
}
