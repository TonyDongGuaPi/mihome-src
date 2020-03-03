package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class IsSignModel extends BaseResult {
    public static final Parcelable.Creator<IsSignModel> CREATOR = new Parcelable.Creator<IsSignModel>() {
        public IsSignModel createFromParcel(Parcel parcel) {
            return new IsSignModel(parcel);
        }

        public IsSignModel[] newArray(int i) {
            return new IsSignModel[i];
        }
    };
    private DataBean data;
    private int emotion;

    public int describeContents() {
        return 0;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public int getEmotion() {
        return this.emotion;
    }

    public void setEmotion(int i) {
        this.emotion = i;
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
        parcel.writeInt(this.emotion);
    }

    public IsSignModel() {
    }

    protected IsSignModel(Parcel parcel) {
        super(parcel);
        this.data = (DataBean) parcel.readParcelable(DataBean.class.getClassLoader());
        this.emotion = parcel.readInt();
    }
}
