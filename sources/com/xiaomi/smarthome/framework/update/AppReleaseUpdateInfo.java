package com.xiaomi.smarthome.framework.update;

import com.taobao.weex.el.parse.Operators;

public class AppReleaseUpdateInfo {

    /* renamed from: a  reason: collision with root package name */
    public boolean f17690a;
    public boolean b;
    public int c;
    public String d;
    public String e;
    public String f;

    public String toString() {
        return "AppReleaseUpdateInfo{mHasNewVersion=" + this.f17690a + ", mIsForce=" + this.b + ", mVersionCode=" + this.c + ", mVersionName='" + this.d + Operators.SINGLE_QUOTE + ", mDownloadUrl='" + this.e + Operators.SINGLE_QUOTE + ", mChangeLog='" + this.f + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
