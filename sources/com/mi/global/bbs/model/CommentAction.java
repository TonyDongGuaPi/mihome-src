package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentAction implements Parcelable {
    public static final Parcelable.Creator<CommentAction> CREATOR = new Parcelable.Creator<CommentAction>() {
        public CommentAction createFromParcel(Parcel parcel) {
            return new CommentAction(parcel);
        }

        public CommentAction[] newArray(int i) {
            return new CommentAction[i];
        }
    };
    public String name;
    public String url;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.url);
    }

    public CommentAction() {
    }

    protected CommentAction(Parcel parcel) {
        this.name = parcel.readString();
        this.url = parcel.readString();
    }
}
