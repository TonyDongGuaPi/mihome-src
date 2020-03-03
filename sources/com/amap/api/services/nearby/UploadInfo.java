package com.amap.api.services.nearby;

import com.amap.api.services.core.LatLonPoint;

public class UploadInfo {

    /* renamed from: a  reason: collision with root package name */
    private int f4476a = 1;
    private String b;
    private LatLonPoint c;

    public void setPoint(LatLonPoint latLonPoint) {
        this.c = latLonPoint;
    }

    public LatLonPoint getPoint() {
        return this.c;
    }

    public void setUserID(String str) {
        this.b = str;
    }

    public String getUserID() {
        return this.b;
    }

    public int getCoordType() {
        return this.f4476a;
    }

    public void setCoordType(int i) {
        if (i == 0 || i == 1) {
            this.f4476a = i;
        } else {
            this.f4476a = 1;
        }
    }
}
