package com.mics.core.data.request;

public class SessionChooseService extends Session {
    private String queueId;
    private boolean rejectIfNoOnlineKefu = true;

    public String getQueueId() {
        return this.queueId;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }

    public boolean isRejectIfNoOnlineKefu() {
        return this.rejectIfNoOnlineKefu;
    }

    public void setRejectIfNoOnlineKefu(boolean z) {
        this.rejectIfNoOnlineKefu = z;
    }
}
