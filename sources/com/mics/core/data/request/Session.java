package com.mics.core.data.request;

import com.mics.core.MiCS;

public class Session {
    private Header header;

    public Header getHeader() {
        return this.header;
    }

    public void setHeader(Header header2) {
        this.header = header2;
    }

    public static class Header {
        private String sessionId = "";
        private String userId = MiCS.a().n();

        public String getSessionId() {
            return this.sessionId;
        }

        public void setSessionId(String str) {
            this.sessionId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }
}
