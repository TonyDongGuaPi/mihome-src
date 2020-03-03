package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class HomeForumBean implements Parcelable {
    public static final Parcelable.Creator<HomeForumBean> CREATOR = new Parcelable.Creator<HomeForumBean>() {
        public HomeForumBean createFromParcel(Parcel parcel) {
            return new HomeForumBean(parcel);
        }

        public HomeForumBean[] newArray(int i) {
            return new HomeForumBean[i];
        }
    };
    public int affirmreplies;
    public String authimg;
    public String author;
    public String authorid;
    public String dateline;
    public int displayorder;
    public String fid;
    public String fname;
    public int negareplies;
    public int pollcount;
    public String replies;
    public int showlogo;
    public List<String> showpiclist;
    public int special;
    public String subject;
    public int thumbupcount;
    public int thumbupstatus;
    public String thumbupurl;
    public String tid;
    public int views;

    public int describeContents() {
        return 0;
    }

    protected HomeForumBean(Parcel parcel) {
        this.displayorder = parcel.readInt();
        this.tid = parcel.readString();
        this.fid = parcel.readString();
        this.author = parcel.readString();
        this.authorid = parcel.readString();
        this.subject = parcel.readString();
        this.dateline = parcel.readString();
        this.fname = parcel.readString();
        this.views = parcel.readInt();
        this.replies = parcel.readString();
        this.special = parcel.readInt();
        this.authimg = parcel.readString();
        this.pollcount = parcel.readInt();
        this.affirmreplies = parcel.readInt();
        this.negareplies = parcel.readInt();
        this.showpiclist = parcel.createStringArrayList();
        this.thumbupcount = parcel.readInt();
        this.thumbupstatus = parcel.readInt();
        this.thumbupurl = parcel.readString();
        this.showlogo = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.displayorder);
        parcel.writeString(this.tid);
        parcel.writeString(this.fid);
        parcel.writeString(this.author);
        parcel.writeString(this.authorid);
        parcel.writeString(this.subject);
        parcel.writeString(this.dateline);
        parcel.writeString(this.fname);
        parcel.writeInt(this.views);
        parcel.writeString(this.replies);
        parcel.writeInt(this.special);
        parcel.writeString(this.authimg);
        parcel.writeInt(this.pollcount);
        parcel.writeInt(this.affirmreplies);
        parcel.writeInt(this.negareplies);
        parcel.writeStringList(this.showpiclist);
        parcel.writeInt(this.thumbupcount);
        parcel.writeInt(this.thumbupstatus);
        parcel.writeString(this.thumbupurl);
        parcel.writeInt(this.showlogo);
    }
}
