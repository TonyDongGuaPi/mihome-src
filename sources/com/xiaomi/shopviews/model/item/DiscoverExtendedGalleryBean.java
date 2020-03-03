package com.xiaomi.shopviews.model.item;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscoverExtendedGalleryBean implements Parcelable {
    public static final Parcelable.Creator<DiscoverExtendedGalleryBean> CREATOR = new Parcelable.Creator<DiscoverExtendedGalleryBean>() {
        /* renamed from: a */
        public DiscoverExtendedGalleryBean createFromParcel(Parcel parcel) {
            return new DiscoverExtendedGalleryBean(parcel);
        }

        /* renamed from: a */
        public DiscoverExtendedGalleryBean[] newArray(int i) {
            return new DiscoverExtendedGalleryBean[i];
        }
    };
    @SerializedName("image_url")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f13169a;
    @SerializedName("video_url")
    @Expose
    private String b;
    @SerializedName("desc")
    @Expose
    private String c;
    @SerializedName("jump_to")
    @Expose
    private String d;
    @SerializedName("jump_to_btn_text")
    @Expose
    private String e;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.f13169a;
    }

    public void a(String str) {
        this.f13169a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13169a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    public DiscoverExtendedGalleryBean() {
    }

    protected DiscoverExtendedGalleryBean(Parcel parcel) {
        this.f13169a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}
