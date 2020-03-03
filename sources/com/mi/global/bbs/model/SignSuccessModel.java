package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SignSuccessModel extends BaseResult {
    public static final Parcelable.Creator<SignSuccessModel> CREATOR = new Parcelable.Creator<SignSuccessModel>() {
        public SignSuccessModel createFromParcel(Parcel parcel) {
            return new SignSuccessModel(parcel);
        }

        public SignSuccessModel[] newArray(int i) {
            return new SignSuccessModel[i];
        }
    };
    private DataBean data;

    public int describeContents() {
        return 0;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean {
        private String percent;

        public String getPercent() {
            return this.percent;
        }

        public void setPercent(String str) {
            this.percent = str;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public SignSuccessModel() {
    }

    protected SignSuccessModel(Parcel parcel) {
        super(parcel);
        this.data = (DataBean) parcel.readParcelable(DataBean.class.getClassLoader());
    }
}
