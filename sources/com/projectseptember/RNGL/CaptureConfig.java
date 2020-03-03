package com.projectseptember.RNGL;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.taobao.weex.common.Constants;
import com.tencent.smtt.sdk.TbsReaderView;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class CaptureConfig {

    /* renamed from: a  reason: collision with root package name */
    String f8559a;
    String b;
    String c;
    Double d;

    public CaptureConfig(String str, String str2, String str3, Double d2) {
        this.f8559a = str;
        this.b = str2;
        this.c = str3;
        this.d = d2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CaptureConfig captureConfig = (CaptureConfig) obj;
        if (this.f8559a == null ? captureConfig.f8559a != null : !this.f8559a.equals(captureConfig.f8559a)) {
            return false;
        }
        if (this.b == null ? captureConfig.b != null : !this.b.equals(captureConfig.b)) {
            return false;
        }
        if (this.c == null ? captureConfig.c != null : !this.c.equals(captureConfig.c)) {
            return false;
        }
        if (this.d != null) {
            if (!this.d.equals(captureConfig.d)) {
                return false;
            }
            return true;
        } else if (captureConfig.d == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.f8559a != null ? this.f8559a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public static CaptureConfig a(ReadableMap readableMap) {
        return new CaptureConfig(readableMap.getString(IjkMediaMeta.IJKM_KEY_FORMAT), readableMap.getString("type"), readableMap.getString(TbsReaderView.KEY_FILE_PATH), Double.valueOf(readableMap.getDouble(Constants.Name.QUALITY)));
    }

    public WritableMap a() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(IjkMediaMeta.IJKM_KEY_FORMAT, this.f8559a);
        createMap.putString("type", this.b);
        createMap.putString(TbsReaderView.KEY_FILE_PATH, this.c);
        createMap.putDouble(Constants.Name.QUALITY, this.d.doubleValue());
        return createMap;
    }
}
