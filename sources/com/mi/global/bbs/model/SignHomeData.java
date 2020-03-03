package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class SignHomeData extends BaseResult {
    public SignBean data;

    public static class SignBean implements Parcelable {
        public static final Parcelable.Creator<SignBean> CREATOR = new Parcelable.Creator<SignBean>() {
            public SignBean createFromParcel(Parcel parcel) {
                return new SignBean(parcel);
            }

            public SignBean[] newArray(int i) {
                return new SignBean[i];
            }
        };
        public int consecutivedays;
        public String dynamic_id;
        public int emotion;
        public List<EmotionBean> emotionlist;
        public int havesigned;
        public String message;
        public int signalram;
        public int totalsigned;
        public String usericon;

        public int describeContents() {
            return 0;
        }

        public static class EmotionBean implements Parcelable {
            public static final Parcelable.Creator<EmotionBean> CREATOR = new Parcelable.Creator<EmotionBean>() {
                public EmotionBean createFromParcel(Parcel parcel) {
                    return new EmotionBean(parcel);
                }

                public EmotionBean[] newArray(int i) {
                    return new EmotionBean[i];
                }
            };
            public String disableurl;
            public boolean enable;
            public String enableurl;
            public int id;
            public String name;
            public int needsign;
            public String numprefix;
            public String sign_word;

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.name);
                parcel.writeString(this.enableurl);
                parcel.writeString(this.disableurl);
                parcel.writeString(this.numprefix);
                parcel.writeInt(this.id);
                parcel.writeByte(this.enable ? (byte) 1 : 0);
                parcel.writeInt(this.needsign);
                parcel.writeString(this.sign_word);
            }

            public EmotionBean() {
            }

            protected EmotionBean(Parcel parcel) {
                this.name = parcel.readString();
                this.enableurl = parcel.readString();
                this.disableurl = parcel.readString();
                this.numprefix = parcel.readString();
                this.id = parcel.readInt();
                this.enable = parcel.readByte() != 0;
                this.needsign = parcel.readInt();
                this.sign_word = parcel.readString();
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.havesigned);
            parcel.writeInt(this.emotion);
            parcel.writeInt(this.consecutivedays);
            parcel.writeInt(this.totalsigned);
            parcel.writeString(this.usericon);
            parcel.writeInt(this.signalram);
            parcel.writeString(this.message);
            parcel.writeTypedList(this.emotionlist);
            parcel.writeString(this.dynamic_id);
        }

        protected SignBean(Parcel parcel) {
            this.havesigned = parcel.readInt();
            this.emotion = parcel.readInt();
            this.consecutivedays = parcel.readInt();
            this.totalsigned = parcel.readInt();
            this.usericon = parcel.readString();
            this.signalram = parcel.readInt();
            this.message = parcel.readString();
            this.emotionlist = parcel.createTypedArrayList(EmotionBean.CREATOR);
            this.dynamic_id = parcel.readString();
        }
    }
}
