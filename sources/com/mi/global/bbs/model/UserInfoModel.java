package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class UserInfoModel extends BaseResult {
    public DataBean data;

    public static class DataBean implements Parcelable {
        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            public DataBean createFromParcel(Parcel parcel) {
                return new DataBean(parcel);
            }

            public DataBean[] newArray(int i) {
                return new DataBean[i];
            }
        };
        public int alarm;
        public String authortitle;
        public String birthday;
        public String birthmonth;
        public String bkgimg;
        public int columnId;
        public String education;
        public String email;
        public int followFlag;
        public String followerCount;
        public String followingCount;
        public String gender;
        public String groupid;
        public String icon;
        public String interest;
        public int isAdmin;
        public int isColunmWriter;
        public String lastvisit;
        public String livingcity;
        public int medalCount;
        public List<MedalsBean> medals;
        public String occupation;
        public int online;
        public int points;
        public String realname;
        public String regdate;
        public String replies;
        public int shareFlag;
        public int specialGroup;
        public int threads;
        public String uid;
        public String username;

        public int describeContents() {
            return 0;
        }

        public static class MedalsBean implements Parcelable {
            public static final Parcelable.Creator<MedalsBean> CREATOR = new Parcelable.Creator<MedalsBean>() {
                public MedalsBean createFromParcel(Parcel parcel) {
                    return new MedalsBean(parcel);
                }

                public MedalsBean[] newArray(int i) {
                    return new MedalsBean[i];
                }
            };
            public String image;
            public String medalid;

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.medalid);
                parcel.writeString(this.image);
            }

            public MedalsBean() {
            }

            protected MedalsBean(Parcel parcel) {
                this.medalid = parcel.readString();
                this.image = parcel.readString();
            }
        }

        public DataBean() {
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.uid);
            parcel.writeString(this.email);
            parcel.writeString(this.username);
            parcel.writeString(this.regdate);
            parcel.writeString(this.icon);
            parcel.writeInt(this.threads);
            parcel.writeString(this.realname);
            parcel.writeString(this.gender);
            parcel.writeString(this.birthmonth);
            parcel.writeString(this.birthday);
            parcel.writeString(this.education);
            parcel.writeString(this.occupation);
            parcel.writeString(this.interest);
            parcel.writeString(this.lastvisit);
            parcel.writeString(this.replies);
            parcel.writeString(this.livingcity);
            parcel.writeString(this.authortitle);
            parcel.writeString(this.followingCount);
            parcel.writeString(this.followerCount);
            parcel.writeString(this.bkgimg);
            parcel.writeInt(this.medalCount);
            parcel.writeInt(this.points);
            parcel.writeInt(this.shareFlag);
            parcel.writeInt(this.followFlag);
            parcel.writeInt(this.online);
            parcel.writeTypedList(this.medals);
            parcel.writeInt(this.isColunmWriter);
            parcel.writeInt(this.columnId);
            parcel.writeInt(this.specialGroup);
            parcel.writeInt(this.isAdmin);
            parcel.writeString(this.groupid);
            parcel.writeInt(this.alarm);
        }

        protected DataBean(Parcel parcel) {
            this.uid = parcel.readString();
            this.email = parcel.readString();
            this.username = parcel.readString();
            this.regdate = parcel.readString();
            this.icon = parcel.readString();
            this.threads = parcel.readInt();
            this.realname = parcel.readString();
            this.gender = parcel.readString();
            this.birthmonth = parcel.readString();
            this.birthday = parcel.readString();
            this.education = parcel.readString();
            this.occupation = parcel.readString();
            this.interest = parcel.readString();
            this.lastvisit = parcel.readString();
            this.replies = parcel.readString();
            this.livingcity = parcel.readString();
            this.authortitle = parcel.readString();
            this.followingCount = parcel.readString();
            this.followerCount = parcel.readString();
            this.bkgimg = parcel.readString();
            this.medalCount = parcel.readInt();
            this.points = parcel.readInt();
            this.shareFlag = parcel.readInt();
            this.followFlag = parcel.readInt();
            this.online = parcel.readInt();
            this.medals = parcel.createTypedArrayList(MedalsBean.CREATOR);
            this.isColunmWriter = parcel.readInt();
            this.columnId = parcel.readInt();
            this.specialGroup = parcel.readInt();
            this.isAdmin = parcel.readInt();
            this.groupid = parcel.readString();
            this.alarm = parcel.readInt();
        }
    }
}
