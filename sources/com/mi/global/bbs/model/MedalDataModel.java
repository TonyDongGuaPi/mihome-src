package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class MedalDataModel extends BaseResult {
    public static final Parcelable.Creator<MedalDataModel> CREATOR = new Parcelable.Creator<MedalDataModel>() {
        public MedalDataModel createFromParcel(Parcel parcel) {
            return new MedalDataModel(parcel);
        }

        public MedalDataModel[] newArray(int i) {
            return new MedalDataModel[i];
        }
    };
    private List<MedalDataBean> data;

    public int describeContents() {
        return 0;
    }

    public List<MedalDataBean> getData() {
        return this.data;
    }

    public void setData(List<MedalDataBean> list) {
        this.data = list;
    }

    public static class MedalDataBean {
        private String image;
        private String msgbody;
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public String getMsgbody() {
            return this.msgbody;
        }

        public void setMsgbody(String str) {
            this.msgbody = str;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeList(this.data);
    }

    public MedalDataModel() {
    }

    protected MedalDataModel(Parcel parcel) {
        super(parcel);
        this.data = new ArrayList();
        parcel.readList(this.data, MedalDataBean.class.getClassLoader());
    }
}
