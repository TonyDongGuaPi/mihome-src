package com.mi.global.bbs.ui.qa;

import android.os.Parcel;
import android.os.Parcelable;
import com.mi.global.bbs.model.BaseResult;
import java.util.List;

public class QAInviteItem extends BaseResult {
    public List<DataBean> data;

    public static class DataBean implements Parcelable {
        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            public DataBean createFromParcel(Parcel parcel) {
                return new DataBean(parcel);
            }

            public DataBean[] newArray(int i) {
                return new DataBean[i];
            }
        };
        public boolean checked;
        public String groupname;
        public String icon;
        public String uid;
        public String username;

        public int describeContents() {
            return 0;
        }

        public DataBean() {
        }

        protected DataBean(Parcel parcel) {
            this.uid = parcel.readString();
            this.icon = parcel.readString();
            this.username = parcel.readString();
            this.groupname = parcel.readString();
            this.checked = parcel.readByte() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.uid);
            parcel.writeString(this.icon);
            parcel.writeString(this.username);
            parcel.writeString(this.groupname);
            parcel.writeByte(this.checked ? (byte) 1 : 0);
        }
    }
}
