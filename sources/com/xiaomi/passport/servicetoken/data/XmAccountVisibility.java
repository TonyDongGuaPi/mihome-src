package com.xiaomi.passport.servicetoken.data;

import android.accounts.Account;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

public class XmAccountVisibility implements Parcelable {
    public static final Parcelable.Creator<XmAccountVisibility> CREATOR = new Parcelable.Creator<XmAccountVisibility>() {
        public XmAccountVisibility createFromParcel(Parcel parcel) {
            return new XmAccountVisibility(parcel);
        }

        public XmAccountVisibility[] newArray(int i) {
            return new XmAccountVisibility[0];
        }
    };
    private static final String KEY_ACCOUNT = "account";
    private static final String KEY_ACCOUNT_VISIBLE = "visible";
    private static final String KEY_BUILD_SDK_VERSION = "build_sdk_version";
    private static final String KEY_ERROR_CODE = "error_code";
    private static final String KEY_ERROR_MSG = "error_msg";
    private static final String KEY_NEW_CHOOSE_ACCOUNT_INTENT = "new_choose_account_intent";
    public final Account account;
    public final int buildSdkVersion;
    public final ErrorCode errorCode;
    public final String errorMsg;
    public final Intent newChooseAccountIntent;
    public final boolean visible;

    public int describeContents() {
        return 0;
    }

    public enum ErrorCode {
        ERROR_NONE("successful"),
        ERROR_NOT_SUPPORT("no support account service"),
        ERROR_PRE_ANDROID_O("no support account service, and pre o version"),
        ERROR_NO_ACCOUNT("no account"),
        ERROR_NO_PERMISSION("no access account service permission"),
        ERROR_CANCELLED("task cancelled"),
        ERROR_EXECUTION("execution error"),
        ERROR_UNKNOWN("unknown");
        
        String errorMsg;

        private ErrorCode(String str) {
            this.errorMsg = str;
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Account account;
        /* access modifiers changed from: private */
        public int buildSdkVersion = Build.VERSION.SDK_INT;
        /* access modifiers changed from: private */
        public final ErrorCode errorCode;
        /* access modifiers changed from: private */
        public final String errorMsg;
        /* access modifiers changed from: private */
        public Intent newChooseAccountIntent;
        /* access modifiers changed from: private */
        public boolean visible;

        public Builder(ErrorCode errorCode2, String str) {
            this.errorCode = errorCode2;
            this.errorMsg = TextUtils.isEmpty(str) ? errorCode2.errorMsg : str;
        }

        public Builder accountVisible(boolean z, Account account2) {
            this.visible = z;
            this.account = account2;
            return this;
        }

        public Builder newChooseAccountIntent(Intent intent) {
            this.newChooseAccountIntent = intent;
            return this;
        }

        public XmAccountVisibility build() {
            return new XmAccountVisibility(this);
        }
    }

    public XmAccountVisibility(Builder builder) {
        this.errorCode = builder.errorCode;
        this.errorMsg = builder.errorMsg;
        this.visible = builder.visible;
        this.account = builder.account;
        this.buildSdkVersion = builder.buildSdkVersion;
        this.newChooseAccountIntent = builder.newChooseAccountIntent;
    }

    public XmAccountVisibility(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        this.errorCode = ErrorCode.values()[readBundle.getInt("error_code")];
        this.errorMsg = readBundle.getString(KEY_ERROR_MSG);
        this.visible = readBundle.getBoolean("visible");
        this.account = (Account) readBundle.getParcelable("account");
        this.buildSdkVersion = readBundle.getInt(KEY_BUILD_SDK_VERSION);
        this.newChooseAccountIntent = (Intent) readBundle.getParcelable(KEY_NEW_CHOOSE_ACCOUNT_INTENT);
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("error_code", this.errorCode.ordinal());
        bundle.putString(KEY_ERROR_MSG, this.errorMsg);
        bundle.putBoolean("visible", this.visible);
        bundle.putParcelable("account", this.account);
        bundle.putInt(KEY_BUILD_SDK_VERSION, this.buildSdkVersion);
        bundle.putParcelable(KEY_NEW_CHOOSE_ACCOUNT_INTENT, this.newChooseAccountIntent);
        parcel.writeBundle(bundle);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AccountVisibility{");
        stringBuffer.append(", errorCode=");
        stringBuffer.append(this.errorCode);
        stringBuffer.append(", errorMessage='");
        stringBuffer.append(this.errorMsg);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", accountVisible='");
        stringBuffer.append(this.visible);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(Operators.BLOCK_END);
        return stringBuffer.toString();
    }
}
