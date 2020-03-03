package com.xiaomi.smarthome.device.api;

import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class SecurityKeyInfo {
    public long activeTime;
    public long expireTime;
    public int isoutofdate;
    public String keyId;
    public String shareUid;
    public int status;
    public List<Integer> weekdays;

    public String toString() {
        return "SecurityKeyInfo{keyId='" + this.keyId + Operators.SINGLE_QUOTE + ", shareUid='" + this.shareUid + Operators.SINGLE_QUOTE + ", status=" + this.status + ", activeTime=" + this.activeTime + ", expireTime=" + this.expireTime + ", weekdays=" + this.weekdays + ", isoutofdate=" + this.isoutofdate + Operators.BLOCK_END;
    }
}
