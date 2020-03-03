package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.AMapException;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;

public final class DistrictResult implements Parcelable {
    public Parcelable.Creator<DistrictResult> CREATOR = new Parcelable.Creator<DistrictResult>() {
        /* renamed from: a */
        public DistrictResult createFromParcel(Parcel parcel) {
            return new DistrictResult(parcel);
        }

        /* renamed from: a */
        public DistrictResult[] newArray(int i) {
            return new DistrictResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private DistrictSearchQuery f4452a;
    private ArrayList<DistrictItem> b = new ArrayList<>();
    private int c;
    private AMapException d;

    public int describeContents() {
        return 0;
    }

    public DistrictResult(DistrictSearchQuery districtSearchQuery, ArrayList<DistrictItem> arrayList) {
        this.f4452a = districtSearchQuery;
        this.b = arrayList;
    }

    public DistrictResult() {
    }

    public ArrayList<DistrictItem> getDistrict() {
        return this.b;
    }

    public void setDistrict(ArrayList<DistrictItem> arrayList) {
        this.b = arrayList;
    }

    public DistrictSearchQuery getQuery() {
        return this.f4452a;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.f4452a = districtSearchQuery;
    }

    public int getPageCount() {
        return this.c;
    }

    public void setPageCount(int i) {
        this.c = i;
    }

    public AMapException getAMapException() {
        return this.d;
    }

    public void setAMapException(AMapException aMapException) {
        this.d = aMapException;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4452a, i);
        parcel.writeTypedList(this.b);
    }

    protected DistrictResult(Parcel parcel) {
        this.f4452a = (DistrictSearchQuery) parcel.readParcelable(DistrictSearchQuery.class.getClassLoader());
        this.b = parcel.createTypedArrayList(DistrictItem.CREATOR);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.f4452a == null) {
            i = 0;
        } else {
            i = this.f4452a.hashCode();
        }
        int i3 = (i + 31) * 31;
        if (this.b != null) {
            i2 = this.b.hashCode();
        }
        return i3 + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DistrictResult districtResult = (DistrictResult) obj;
        if (this.f4452a == null) {
            if (districtResult.f4452a != null) {
                return false;
            }
        } else if (!this.f4452a.equals(districtResult.f4452a)) {
            return false;
        }
        if (this.b == null) {
            if (districtResult.b != null) {
                return false;
            }
        } else if (!this.b.equals(districtResult.b)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "DistrictResult [mDisQuery=" + this.f4452a + ", mDistricts=" + this.b + Operators.ARRAY_END_STR;
    }
}
