package org.xutils.view;

final class ViewInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f11944a;
    public int b;

    ViewInfo() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ViewInfo viewInfo = (ViewInfo) obj;
        if (this.f11944a == viewInfo.f11944a && this.b == viewInfo.b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f11944a * 31) + this.b;
    }
}
