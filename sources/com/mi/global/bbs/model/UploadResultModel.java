package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UploadResultModel extends BaseResult {
    public static final Parcelable.Creator<UploadResultModel> CREATOR = new Parcelable.Creator<UploadResultModel>() {
        public UploadResultModel createFromParcel(Parcel parcel) {
            return new UploadResultModel(parcel);
        }

        public UploadResultModel[] newArray(int i) {
            return new UploadResultModel[i];
        }
    };
    private DataBean data;

    public int describeContents() {
        return 0;
    }

    public UploadResultModel() {
    }

    protected UploadResultModel(Parcel parcel) {
        super(parcel);
        this.data = (DataBean) parcel.readParcelable(DataBean.class.getClassLoader());
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.data, i);
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
        private int aid;
        private String url;

        public int describeContents() {
            return 0;
        }

        public DataBean() {
        }

        protected DataBean(Parcel parcel) {
            this.url = parcel.readString();
            this.aid = parcel.readInt();
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public int getAid() {
            return this.aid;
        }

        public void setAid(int i) {
            this.aid = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.url);
            parcel.writeInt(this.aid);
        }
    }
}
