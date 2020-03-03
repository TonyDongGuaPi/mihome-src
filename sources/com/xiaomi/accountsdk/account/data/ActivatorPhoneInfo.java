package com.xiaomi.accountsdk.account.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class ActivatorPhoneInfo implements Parcelable {
    public static final Parcelable.Creator<ActivatorPhoneInfo> CREATOR = new Parcelable.Creator<ActivatorPhoneInfo>() {
        public ActivatorPhoneInfo createFromParcel(Parcel parcel) {
            Bundle readBundle = parcel.readBundle();
            if (readBundle == null) {
                return null;
            }
            return new Builder().phone(readBundle.getString("phone")).phoneHash(readBundle.getString(ActivatorPhoneInfo.KEY_PHONE_HASH)).activatorToken(readBundle.getString(ActivatorPhoneInfo.KEY_ACTIVATOR_TOKEN)).slotId(readBundle.getInt(ActivatorPhoneInfo.KEY_SLOT_ID)).copyWriter(readBundle.getString(ActivatorPhoneInfo.KEY_COPY_WRITER)).operatorLink(readBundle.getString(ActivatorPhoneInfo.KEY_OPERATOR_LINK)).build();
        }

        public ActivatorPhoneInfo[] newArray(int i) {
            return new ActivatorPhoneInfo[i];
        }
    };
    private static final String KEY_ACTIVATOR_TOKEN = "activator_token";
    private static final String KEY_COPY_WRITER = "copy_writer";
    private static final String KEY_OPERATOR_LINK = "operator_link";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PHONE_HASH = "phone_hash";
    private static final String KEY_SLOT_ID = "slot_id";
    public final String activatorToken;
    public final String copyWriter;
    public final String operatorLink;
    public final String phone;
    public final String phoneHash;
    public final int slotId;

    public int describeContents() {
        return 0;
    }

    public ActivatorPhoneInfo(Builder builder) {
        this.phone = builder.phone;
        this.phoneHash = builder.phoneHash;
        this.activatorToken = builder.activatorToken;
        this.slotId = builder.slotId;
        this.copyWriter = builder.copyWriter;
        this.operatorLink = builder.operatorLink;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public String activatorToken;
        /* access modifiers changed from: private */
        public String copyWriter;
        /* access modifiers changed from: private */
        public String operatorLink;
        /* access modifiers changed from: private */
        public String phone;
        /* access modifiers changed from: private */
        public String phoneHash;
        /* access modifiers changed from: private */
        public int slotId;

        public Builder phone(String str) {
            this.phone = str;
            return this;
        }

        public Builder phoneHash(String str) {
            this.phoneHash = str;
            return this;
        }

        public Builder activatorToken(String str) {
            this.activatorToken = str;
            return this;
        }

        public Builder slotId(int i) {
            this.slotId = i;
            return this;
        }

        public Builder copyWriter(String str) {
            this.copyWriter = str;
            return this;
        }

        public Builder operatorLink(String str) {
            this.operatorLink = str;
            return this;
        }

        public ActivatorPhoneInfo build() {
            return new ActivatorPhoneInfo(this);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("phone", this.phone);
        bundle.putString(KEY_PHONE_HASH, this.phoneHash);
        bundle.putString(KEY_ACTIVATOR_TOKEN, this.activatorToken);
        bundle.putInt(KEY_SLOT_ID, this.slotId);
        bundle.putString(KEY_COPY_WRITER, this.copyWriter);
        bundle.putString(KEY_OPERATOR_LINK, this.operatorLink);
        parcel.writeBundle(bundle);
    }
}
