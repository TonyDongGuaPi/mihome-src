package com.xiaomi.smarthome.device.api;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

public class BleMeshFirmwareUpdateInfoV2 implements Serializable {
    public String changeLog;
    public String currentVersion;
    public String did;
    public String md5;
    public String safeUrl;
    public String uploadTime;
    public String url;
    public String version;

    public String toString() {
        return "BtFirmwareUpdateInfo{version='" + this.version + Operators.SINGLE_QUOTE + ", safeUrl='" + this.safeUrl + Operators.SINGLE_QUOTE + ", url='" + this.url + Operators.SINGLE_QUOTE + ", changeLog='" + this.changeLog + Operators.SINGLE_QUOTE + ", uploadTime='" + this.uploadTime + Operators.SINGLE_QUOTE + ", currentVersion='" + this.currentVersion + Operators.SINGLE_QUOTE + ", did='" + this.did + Operators.SINGLE_QUOTE + ", md5='" + this.md5 + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
