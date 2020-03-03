package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.a.s;

public class DistrictSearchQuery implements Parcelable, Cloneable {
    public static final Parcelable.Creator<DistrictSearchQuery> CREATOR = new Parcelable.Creator<DistrictSearchQuery>() {
        /* renamed from: a */
        public DistrictSearchQuery createFromParcel(Parcel parcel) {
            DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
            districtSearchQuery.setKeywords(parcel.readString());
            districtSearchQuery.setKeywordsLevel(parcel.readString());
            districtSearchQuery.setPageNum(parcel.readInt());
            districtSearchQuery.setPageSize(parcel.readInt());
            boolean z = false;
            districtSearchQuery.setShowChild(parcel.readByte() == 1);
            districtSearchQuery.setShowBoundary(parcel.readByte() == 1);
            if (parcel.readByte() == 1) {
                z = true;
            }
            districtSearchQuery.setShowBusinessArea(z);
            return districtSearchQuery;
        }

        /* renamed from: a */
        public DistrictSearchQuery[] newArray(int i) {
            return new DistrictSearchQuery[i];
        }
    };
    public static final String KEYWORDS_BUSINESS = "biz_area";
    public static final String KEYWORDS_CITY = "city";
    public static final String KEYWORDS_COUNTRY = "country";
    public static final String KEYWORDS_DISTRICT = "district";
    public static final String KEYWORDS_PROVINCE = "province";

    /* renamed from: a  reason: collision with root package name */
    private int f4455a;
    private int b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;

    public int describeContents() {
        return 0;
    }

    public void setShowBoundary(boolean z) {
        this.g = z;
    }

    public boolean isShowBoundary() {
        return this.g;
    }

    public DistrictSearchQuery() {
        this.f4455a = 1;
        this.b = 20;
        this.e = true;
        this.f = false;
        this.g = false;
    }

    public DistrictSearchQuery(String str, String str2, int i) {
        this.f4455a = 1;
        this.b = 20;
        this.e = true;
        this.f = false;
        this.g = false;
        this.c = str;
        this.d = str2;
        this.f4455a = i;
    }

    public DistrictSearchQuery(String str, String str2, int i, boolean z, int i2) {
        this(str, str2, i);
        this.e = z;
        this.b = i2;
    }

    public int getPageNum() {
        if (this.f4455a < 1) {
            return 1;
        }
        return this.f4455a;
    }

    public void setPageNum(int i) {
        this.f4455a = i;
    }

    public int getPageSize() {
        return this.b;
    }

    public void setPageSize(int i) {
        this.b = i;
    }

    public String getKeywords() {
        return this.c;
    }

    public void setKeywords(String str) {
        this.c = str;
    }

    public String getKeywordsLevel() {
        return this.d;
    }

    public void setKeywordsLevel(String str) {
        this.d = str;
    }

    public boolean isShowChild() {
        return this.e;
    }

    public void setShowChild(boolean z) {
        this.e = z;
    }

    public boolean isShowBusinessArea() {
        return this.f;
    }

    public void setShowBusinessArea(boolean z) {
        this.f = z;
    }

    public boolean checkLevels() {
        if (this.d == null) {
            return false;
        }
        if (this.d.trim().equals("country") || this.d.trim().equals("province") || this.d.trim().equals("city") || this.d.trim().equals("district") || this.d.trim().equals(KEYWORDS_BUSINESS)) {
            return true;
        }
        return false;
    }

    public boolean checkKeyWords() {
        if (this.c != null && !this.c.trim().equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1237;
        int i3 = ((this.g ? 1231 : 1237) + 31) * 31;
        int i4 = 0;
        if (this.c == null) {
            i = 0;
        } else {
            i = this.c.hashCode();
        }
        int i5 = (i3 + i) * 31;
        if (this.d != null) {
            i4 = this.d.hashCode();
        }
        int i6 = (((((i5 + i4) * 31) + this.f4455a) * 31) + this.b) * 31;
        if (this.e) {
            i2 = 1231;
        }
        return i6 + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DistrictSearchQuery districtSearchQuery = (DistrictSearchQuery) obj;
        if (this.g != districtSearchQuery.g) {
            return false;
        }
        if (this.c == null) {
            if (districtSearchQuery.c != null) {
                return false;
            }
        } else if (!this.c.equals(districtSearchQuery.c)) {
            return false;
        }
        return this.f4455a == districtSearchQuery.f4455a && this.b == districtSearchQuery.b && this.e == districtSearchQuery.e;
    }

    public boolean weakEquals(DistrictSearchQuery districtSearchQuery) {
        if (this == districtSearchQuery) {
            return true;
        }
        if (districtSearchQuery == null) {
            return false;
        }
        if (this.c == null) {
            if (districtSearchQuery.c != null) {
                return false;
            }
        } else if (!this.c.equals(districtSearchQuery.c)) {
            return false;
        }
        return this.b == districtSearchQuery.b && this.e == districtSearchQuery.e && this.g == districtSearchQuery.g;
    }

    public DistrictSearchQuery clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e2) {
            s.a(e2, "DistrictSearchQuery", "clone");
        }
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
        districtSearchQuery.setKeywords(this.c);
        districtSearchQuery.setKeywordsLevel(this.d);
        districtSearchQuery.setPageNum(this.f4455a);
        districtSearchQuery.setPageSize(this.b);
        districtSearchQuery.setShowChild(this.e);
        districtSearchQuery.setShowBoundary(this.g);
        districtSearchQuery.setShowBusinessArea(this.f);
        return districtSearchQuery;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.f4455a);
        parcel.writeInt(this.b);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeByte(this.g ? (byte) 1 : 0);
        parcel.writeByte(this.f ? (byte) 1 : 0);
    }
}
