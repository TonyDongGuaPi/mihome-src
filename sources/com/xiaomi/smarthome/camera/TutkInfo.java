package com.xiaomi.smarthome.camera;

public class TutkInfo extends P2PInfo {
    public String mTutkAccount = "admin";
    public String mTutkId = "";
    public String mTutkPwd = "";

    public boolean equals(Object obj) {
        if (!(obj instanceof TutkInfo)) {
            return false;
        }
        TutkInfo tutkInfo = (TutkInfo) obj;
        if (!this.mTutkPwd.equals(tutkInfo.mTutkPwd) || !this.mRemoteKey.equals(tutkInfo.mRemoteKey)) {
            return false;
        }
        return true;
    }

    public String getUid() {
        return this.mTutkId;
    }

    public String getKey() {
        return this.mTutkPwd;
    }
}
