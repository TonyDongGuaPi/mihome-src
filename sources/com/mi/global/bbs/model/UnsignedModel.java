package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UnsignedModel extends BaseResult {
    public static final Parcelable.Creator<UnsignedModel> CREATOR = new Parcelable.Creator<UnsignedModel>() {
        public UnsignedModel createFromParcel(Parcel parcel) {
            return new UnsignedModel(parcel);
        }

        public UnsignedModel[] newArray(int i) {
            return new UnsignedModel[i];
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

    public static class DataBean implements Parcelable {
        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            public DataBean createFromParcel(Parcel parcel) {
                return new DataBean(parcel);
            }

            public DataBean[] newArray(int i) {
                return new DataBean[i];
            }
        };
        private String percent;
        private String total;

        public int describeContents() {
            return 0;
        }

        public String getPercent() {
            return this.percent;
        }

        public void setPercent(String str) {
            this.percent = str;
        }

        public String getTotal() {
            return this.total;
        }

        public void setTotal(String str) {
            this.total = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.percent);
            parcel.writeString(this.total);
        }

        public DataBean() {
        }

        protected DataBean(Parcel parcel) {
            this.percent = parcel.readString();
            this.total = parcel.readString();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.data, i);
    }

    public UnsignedModel() {
    }

    protected UnsignedModel(Parcel parcel) {
        super(parcel);
        this.data = (DataBean) parcel.readParcelable(DataBean.class.getClassLoader());
    }
}
