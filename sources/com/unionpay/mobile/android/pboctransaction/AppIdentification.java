package com.unionpay.mobile.android.pboctransaction;

import android.os.Parcel;
import android.os.Parcelable;

public class AppIdentification implements Parcelable, Comparable<AppIdentification> {
    public static final Parcelable.Creator<AppIdentification> CREATOR = new a();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f9641a;
    /* access modifiers changed from: private */
    public String b;

    private AppIdentification() {
        this.f9641a = "";
        this.b = "";
    }

    /* synthetic */ AppIdentification(byte b2) {
        this();
    }

    public AppIdentification(String str, String str2) {
        this.f9641a = str;
        this.b = str2;
    }

    public final String a() {
        return this.f9641a;
    }

    public final String b() {
        try {
            return this.f9641a.substring(14, 16);
        } catch (Exception unused) {
            return "";
        }
    }

    public final boolean c() {
        if (this.f9641a != null) {
            return this.f9641a.startsWith("A000000333");
        }
        return false;
    }

    public /* synthetic */ int compareTo(Object obj) {
        String str;
        String str2;
        AppIdentification appIdentification = (AppIdentification) obj;
        if (!this.f9641a.equalsIgnoreCase(appIdentification.f9641a)) {
            str = this.f9641a;
            str2 = appIdentification.f9641a;
        } else if (this.b.equalsIgnoreCase(appIdentification.b)) {
            return 0;
        } else {
            str = this.b;
            str2 = appIdentification.b;
        }
        return str.compareTo(str2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof AppIdentification)) {
            AppIdentification appIdentification = (AppIdentification) obj;
            return this.f9641a.equalsIgnoreCase(appIdentification.f9641a) && this.b.equalsIgnoreCase(appIdentification.b);
        }
    }

    public int hashCode() {
        return ((this.f9641a.hashCode() + 31) * 31) + this.b.hashCode();
    }

    public String toString() {
        return "{appId:" + this.f9641a + ", appVersion:" + this.b + "}";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f9641a);
        parcel.writeString(this.b);
    }
}
