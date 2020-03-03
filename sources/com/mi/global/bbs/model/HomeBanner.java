package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class HomeBanner implements Parcelable {
    public static final Parcelable.Creator<HomeBanner> CREATOR = new Parcelable.Creator<HomeBanner>() {
        public HomeBanner createFromParcel(Parcel parcel) {
            return new HomeBanner(parcel);
        }

        public HomeBanner[] newArray(int i) {
            return new HomeBanner[i];
        }
    };
    @SerializedName("blank")
    private String blank;
    @SerializedName("ctime")
    private int ctime;
    @SerializedName("desc")
    private String desc;
    @SerializedName("id")
    private String id;
    @SerializedName("link")
    private String link;
    @SerializedName("mtime")
    private int mtime;
    @SerializedName("order")
    private String order;
    @SerializedName("pic_url")
    private String pic_url;
    @SerializedName("status")
    private String status;
    @SerializedName("vtime")
    private String vtime;

    public int describeContents() {
        return 0;
    }

    public HomeBanner() {
    }

    public HomeBanner(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2) {
        this.id = str;
        this.order = str2;
        this.link = str3;
        this.desc = str4;
        this.pic_url = str5;
        this.blank = str6;
        this.status = str7;
        this.vtime = str8;
        this.ctime = i;
        this.mtime = i2;
    }

    protected HomeBanner(Parcel parcel) {
        this.id = parcel.readString();
        this.order = parcel.readString();
        this.link = parcel.readString();
        this.desc = parcel.readString();
        this.pic_url = parcel.readString();
        this.blank = parcel.readString();
        this.status = parcel.readString();
        this.vtime = parcel.readString();
        this.ctime = parcel.readInt();
        this.mtime = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.order);
        parcel.writeString(this.link);
        parcel.writeString(this.desc);
        parcel.writeString(this.pic_url);
        parcel.writeString(this.blank);
        parcel.writeString(this.status);
        parcel.writeString(this.vtime);
        parcel.writeInt(this.ctime);
        parcel.writeInt(this.mtime);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String str) {
        this.order = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public void setPic_url(String str) {
        this.pic_url = str;
    }

    public String getBlank() {
        return this.blank;
    }

    public void setBlank(String str) {
        this.blank = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getVtime() {
        return this.vtime;
    }

    public void setVtime(String str) {
        this.vtime = str;
    }

    public int getCtime() {
        return this.ctime;
    }

    public void setCtime(int i) {
        this.ctime = i;
    }

    public int getMtime() {
        return this.mtime;
    }

    public void setMtime(int i) {
        this.mtime = i;
    }

    public String toString() {
        return "HomeBanner{id='" + this.id + Operators.SINGLE_QUOTE + ", order='" + this.order + Operators.SINGLE_QUOTE + ", link='" + this.link + Operators.SINGLE_QUOTE + ", desc='" + this.desc + Operators.SINGLE_QUOTE + ", pic_url='" + this.pic_url + Operators.SINGLE_QUOTE + ", blank='" + this.blank + Operators.SINGLE_QUOTE + ", status='" + this.status + Operators.SINGLE_QUOTE + ", vtime='" + this.vtime + Operators.SINGLE_QUOTE + ", ctime=" + this.ctime + ", mtime=" + this.mtime + Operators.BLOCK_END;
    }
}
