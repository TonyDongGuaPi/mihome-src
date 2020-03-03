package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FeedbackUploadResult extends BaseResult {
    public static final Parcelable.Creator<FeedbackUploadResult> CREATOR = new Parcelable.Creator<FeedbackUploadResult>() {
        public FeedbackUploadResult createFromParcel(Parcel parcel) {
            return new FeedbackUploadResult(parcel);
        }

        public FeedbackUploadResult[] newArray(int i) {
            return new FeedbackUploadResult[i];
        }
    };
    private DataBean data;

    public int describeContents() {
        return 0;
    }

    public FeedbackUploadResult() {
    }

    protected FeedbackUploadResult(Parcel parcel) {
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
        private int id;
        private String url;

        public int describeContents() {
            return 0;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.id);
            parcel.writeString(this.url);
        }

        public DataBean() {
        }

        protected DataBean(Parcel parcel) {
            this.id = parcel.readInt();
            this.url = parcel.readString();
        }
    }
}
