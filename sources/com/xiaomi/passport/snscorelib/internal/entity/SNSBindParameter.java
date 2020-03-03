package com.xiaomi.passport.snscorelib.internal.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SNSBindParameter implements Parcelable {
    public static final Parcelable.Creator<SNSBindParameter> CREATOR = new Parcelable.Creator<SNSBindParameter>() {
        public SNSBindParameter createFromParcel(Parcel parcel) {
            return new SNSBindParameter(parcel);
        }

        public SNSBindParameter[] newArray(int i) {
            return new SNSBindParameter[i];
        }
    };
    public final String snsBindUrl;
    public final String snsSid;
    public final String sns_token_ph;
    public final String sns_weixin_openId;

    public int describeContents() {
        return 0;
    }

    private SNSBindParameter(Builder builder) {
        this.sns_token_ph = builder.sns_token_ph;
        this.sns_weixin_openId = builder.sns_weixin_openId;
        this.snsBindUrl = builder.snsBindUrl;
        this.snsSid = builder.snsSid;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.sns_token_ph);
        parcel.writeString(this.sns_weixin_openId);
        parcel.writeString(this.snsBindUrl);
        parcel.writeString(this.snsSid);
    }

    private SNSBindParameter(Parcel parcel) {
        this.sns_token_ph = parcel.readString();
        this.sns_weixin_openId = parcel.readString();
        this.snsBindUrl = parcel.readString();
        this.snsSid = parcel.readString();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String snsBindUrl;
        /* access modifiers changed from: private */
        public String snsSid;
        /* access modifiers changed from: private */
        public String sns_token_ph;
        /* access modifiers changed from: private */
        public String sns_weixin_openId;

        public Builder sns_token_ph(String str) {
            this.sns_token_ph = str;
            return this;
        }

        public Builder sns_weixin_openId(String str) {
            this.sns_weixin_openId = str;
            return this;
        }

        public Builder snsBindUrl(String str) {
            this.snsBindUrl = str;
            return this;
        }

        public Builder snsSid(String str) {
            this.snsSid = str;
            return this;
        }

        public SNSBindParameter build() {
            return new SNSBindParameter(this);
        }
    }
}
