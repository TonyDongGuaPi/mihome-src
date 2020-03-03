package com.mi.global.bbs.utils;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.style.ClickableSpan;
import android.view.View;
import com.mi.global.bbs.ui.WebActivity;

public class MyURLSpan extends ClickableSpan implements Parcelable {
    public static final Parcelable.Creator<MyURLSpan> CREATOR = new Parcelable.Creator<MyURLSpan>() {
        public MyURLSpan createFromParcel(Parcel parcel) {
            return new MyURLSpan(parcel);
        }

        public MyURLSpan[] newArray(int i) {
            return new MyURLSpan[i];
        }
    };
    private Context mContext;
    private final String mURL;

    public int describeContents() {
        return 0;
    }

    public MyURLSpan(Context context, String str) {
        this.mContext = context;
        this.mURL = str;
    }

    public void writeToParcelInternal(Parcel parcel, int i) {
        parcel.writeString(this.mURL);
    }

    public String getURL() {
        return this.mURL;
    }

    public void onClick(View view) {
        WebActivity.jump(this.mContext, getURL());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mURL);
    }

    protected MyURLSpan(Parcel parcel) {
        this.mURL = parcel.readString();
    }
}
