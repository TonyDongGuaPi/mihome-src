package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.cookie.SetCookie2;

@Deprecated
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BasicClientCookie2(java.lang.String r1, java.lang.String r2) {
        /*
            r0 = this;
            r1 = 0
            java.lang.String r1 = (java.lang.String) r1
            r0.<init>(r1, r1)
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Stub!"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.cookie.BasicClientCookie2.<init>(java.lang.String, java.lang.String):void");
    }

    public int[] getPorts() {
        throw new RuntimeException("Stub!");
    }

    public void setPorts(int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public String getCommentURL() {
        throw new RuntimeException("Stub!");
    }

    public void setCommentURL(String str) {
        throw new RuntimeException("Stub!");
    }

    public void setDiscard(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public boolean isPersistent() {
        throw new RuntimeException("Stub!");
    }

    public boolean isExpired(Date date) {
        throw new RuntimeException("Stub!");
    }

    public Object clone() throws CloneNotSupportedException {
        throw new RuntimeException("Stub!");
    }
}
