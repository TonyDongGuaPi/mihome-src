package com.lidroid.xutils.view;

public class ViewInjectInfo {

    /* renamed from: a  reason: collision with root package name */
    public Object f6376a;
    public int b;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewInjectInfo)) {
            return false;
        }
        ViewInjectInfo viewInjectInfo = (ViewInjectInfo) obj;
        if (this.b != viewInjectInfo.b) {
            return false;
        }
        if (this.f6376a == null) {
            return viewInjectInfo.f6376a == null;
        }
        return this.f6376a.equals(viewInjectInfo.f6376a);
    }

    public int hashCode() {
        return (this.f6376a.hashCode() * 31) + this.b;
    }
}
