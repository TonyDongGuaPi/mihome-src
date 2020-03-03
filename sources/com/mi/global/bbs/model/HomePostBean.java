package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HomePostBean implements Parcelable {
    public static final Parcelable.Creator<HomePostBean> CREATOR = new Parcelable.Creator<HomePostBean>() {
        public HomePostBean createFromParcel(Parcel parcel) {
            return new HomePostBean(parcel);
        }

        public HomePostBean[] newArray(int i) {
            return new HomePostBean[i];
        }
    };
    @SerializedName("authimg")
    private String authimg;
    @SerializedName("authname")
    private String authorName;
    @SerializedName("authorid")
    private String authorid;
    @SerializedName("content")
    private String content;
    @SerializedName("dateline")
    private String dateline;
    @SerializedName("fid")
    private String fid;
    @SerializedName("fname")
    private String fname;
    @SerializedName("forumurl")
    private String forumurl;
    @SerializedName("picurls")
    private List<String> picurls;
    @SerializedName("replies")
    private String replies;
    @SerializedName("showlogo")
    private int showlogo;
    @SerializedName("spaceurl")
    private String spaceurl;
    @SerializedName("threadurl")
    private String threadurl;
    @SerializedName("tid")
    private String tid;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("vediourl")
    private String vediourl;
    @SerializedName("views")
    private String views;

    public int describeContents() {
        return 0;
    }

    public HomePostBean() {
    }

    protected HomePostBean(Parcel parcel) {
        this.authorid = parcel.readString();
        this.authorName = parcel.readString();
        this.authimg = parcel.readString();
        this.fid = parcel.readString();
        this.tid = parcel.readString();
        this.type = parcel.readString();
        this.vediourl = parcel.readString();
        this.title = parcel.readString();
        this.content = parcel.readString();
        this.picurls = parcel.createStringArrayList();
        this.fname = parcel.readString();
        this.dateline = parcel.readString();
        this.views = parcel.readString();
        this.replies = parcel.readString();
        this.spaceurl = parcel.readString();
        this.forumurl = parcel.readString();
        this.threadurl = parcel.readString();
        this.showlogo = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.authorid);
        parcel.writeString(this.authorName);
        parcel.writeString(this.authimg);
        parcel.writeString(this.fid);
        parcel.writeString(this.tid);
        parcel.writeString(this.type);
        parcel.writeString(this.vediourl);
        parcel.writeString(this.title);
        parcel.writeString(this.content);
        parcel.writeStringList(this.picurls);
        parcel.writeString(this.fname);
        parcel.writeString(this.dateline);
        parcel.writeString(this.views);
        parcel.writeString(this.replies);
        parcel.writeString(this.spaceurl);
        parcel.writeString(this.forumurl);
        parcel.writeString(this.threadurl);
        parcel.writeInt(this.showlogo);
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String str) {
        this.tid = str;
    }

    public String getAuthorid() {
        return this.authorid;
    }

    public void setAuthorid(String str) {
        this.authorid = str;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String str) {
        this.authorName = str;
    }

    public String getAuthimg() {
        return this.authimg;
    }

    public void setAuthimg(String str) {
        this.authimg = str;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getVediourl() {
        return this.vediourl;
    }

    public void setVediourl(String str) {
        this.vediourl = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public List<String> getPicurls() {
        return this.picurls;
    }

    public void setPicurls(List<String> list) {
        this.picurls = list;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String str) {
        this.fname = str;
    }

    public String getDateline() {
        return this.dateline;
    }

    public void setDateline(String str) {
        this.dateline = str;
    }

    public String getViews() {
        return this.views;
    }

    public void setViews(String str) {
        this.views = str;
    }

    public String getReplies() {
        return this.replies;
    }

    public void setReplies(String str) {
        this.replies = str;
    }

    public String getSpaceurl() {
        return this.spaceurl;
    }

    public void setSpaceurl(String str) {
        this.spaceurl = str;
    }

    public String getForumurl() {
        return this.forumurl;
    }

    public void setForumurl(String str) {
        this.forumurl = str;
    }

    public String getThreadurl() {
        return this.threadurl;
    }

    public void setThreadurl(String str) {
        this.threadurl = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getShowlogo() {
        return this.showlogo;
    }

    public void setShowlogo(int i) {
        this.showlogo = i;
    }
}
