package com.xiaomi.passport.servicetoken;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

public class ServiceTokenResult implements Parcelable {
    public static final Parcelable.Creator<ServiceTokenResult> CREATOR = new Parcelable.Creator<ServiceTokenResult>() {
        public ServiceTokenResult createFromParcel(Parcel parcel) {
            return new ServiceTokenResult(parcel);
        }

        public ServiceTokenResult[] newArray(int i) {
            return new ServiceTokenResult[i];
        }
    };
    private static final String PARCEL_BUNDLE_KEY_C_USER_ID = "cUserId";
    private static final String PARCEL_BUNDLE_KEY_ERROR_CODE = "errorCode";
    private static final String PARCEL_BUNDLE_KEY_ERROR_MESSAGE = "errorMessage";
    private static final String PARCEL_BUNDLE_KEY_ERROR_STACK_TRACE = "stackTrace";
    private static final String PARCEL_BUNDLE_KEY_INTENT = "intent";
    private static final String PARCEL_BUNDLE_KEY_PEEKED = "peeked";
    private static final String PARCEL_BUNDLE_KEY_PH = "ph";
    private static final String PARCEL_BUNDLE_KEY_SECURITY = "security";
    private static final String PARCEL_BUNDLE_KEY_SERVICE_TOKEN = "serviceToken";
    private static final String PARCEL_BUNDLE_KEY_SID = "sid";
    private static final String PARCEL_BUNDLE_KEY_SLH = "slh";
    private static final String PARCEL_BUNDLE_KEY_USER_ID = "userId";
    private static final String PARCEL_V2_FLAG = "V2#";
    public static final int TO_STRING_MASK_SHOW_SECURITY = 2;
    public static final int TO_STRING_MASK_SHOW_SERVICETOKEN = 1;
    public final String cUserId;
    public final ErrorCode errorCode;
    public final String errorMessage;
    public final String errorStackTrace;
    public final Intent intent;
    public final boolean peeked;
    public final String ph;
    public final String security;
    public final String serviceToken;
    public final String sid;
    public final String slh;
    /* access modifiers changed from: private */
    public final boolean useV1Parcel;
    public final String userId;

    public enum ErrorCode {
        ERROR_UNKNOWN,
        ERROR_NONE,
        ERROR_NO_ACCOUNT,
        ERROR_APP_PERMISSION_FORBIDDEN,
        ERROR_IOERROR,
        ERROR_OLD_MIUI_ACCOUNT_MANAGER_PERMISSION_ISSUE,
        ERROR_CANCELLED,
        ERROR_AUTHENTICATOR_ERROR,
        ERROR_TIME_OUT,
        ERROR_REMOTE_EXCEPTION,
        ERROR_USER_INTERACTION_NEEDED
    }

    public int describeContents() {
        return 0;
    }

    private ServiceTokenResult(Builder builder) {
        this.sid = builder.sid;
        this.serviceToken = builder.serviceToken;
        this.security = builder.security;
        this.errorMessage = builder.errorMessage;
        this.errorCode = builder.errorCode;
        this.intent = builder.intent;
        this.errorStackTrace = builder.errorStackTrace;
        this.slh = builder.slh;
        this.ph = builder.ph;
        this.cUserId = builder.cUserId;
        this.peeked = builder.peeked;
        this.useV1Parcel = builder.useV1Parcel;
        this.userId = builder.userId;
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int i) {
        String str;
        boolean z = true;
        boolean z2 = (i & 1) == 1;
        if ((i & 2) != 2) {
            z = false;
        }
        String str2 = z2 ? this.serviceToken : "serviceTokenMasked";
        String str3 = z ? this.security : "securityMasked";
        if (TextUtils.isEmpty(this.userId) || this.userId.length() <= 3) {
            str = this.cUserId;
        } else {
            str = TextUtils.substring(this.userId, 0, 2) + "****";
        }
        StringBuffer stringBuffer = new StringBuffer("ServiceTokenResult{");
        stringBuffer.append("userId=");
        stringBuffer.append(str);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", sid='");
        stringBuffer.append(this.sid);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", serviceToken='");
        stringBuffer.append(str2);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", security='");
        stringBuffer.append(str3);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", errorCode=");
        stringBuffer.append(this.errorCode);
        stringBuffer.append(", errorMessage='");
        stringBuffer.append(this.errorMessage);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", errorStackTrace='");
        stringBuffer.append(this.errorStackTrace);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", intent=");
        stringBuffer.append(this.intent);
        stringBuffer.append(", slh='");
        stringBuffer.append(this.slh);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", ph='");
        stringBuffer.append(this.ph);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", cUserId='");
        stringBuffer.append(this.cUserId);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", peeked=");
        stringBuffer.append(this.peeked);
        stringBuffer.append(Operators.SINGLE_QUOTE);
        stringBuffer.append(", useV1Parcel=");
        stringBuffer.append(this.useV1Parcel);
        stringBuffer.append(Operators.BLOCK_END);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ServiceTokenResult)) {
            return false;
        }
        ServiceTokenResult serviceTokenResult = (ServiceTokenResult) obj;
        if (this.userId != serviceTokenResult.userId || this.peeked != serviceTokenResult.peeked || this.useV1Parcel != serviceTokenResult.useV1Parcel) {
            return false;
        }
        if (this.sid == null ? serviceTokenResult.sid != null : !this.sid.equals(serviceTokenResult.sid)) {
            return false;
        }
        if (this.serviceToken == null ? serviceTokenResult.serviceToken != null : !this.serviceToken.equals(serviceTokenResult.serviceToken)) {
            return false;
        }
        if (this.security == null ? serviceTokenResult.security != null : !this.security.equals(serviceTokenResult.security)) {
            return false;
        }
        if (this.errorCode != serviceTokenResult.errorCode) {
            return false;
        }
        if (this.errorMessage == null ? serviceTokenResult.errorMessage != null : !this.errorMessage.equals(serviceTokenResult.errorMessage)) {
            return false;
        }
        if (this.errorStackTrace == null ? serviceTokenResult.errorStackTrace != null : !this.errorStackTrace.equals(serviceTokenResult.errorStackTrace)) {
            return false;
        }
        if (this.intent == null ? serviceTokenResult.intent != null : !this.intent.equals(serviceTokenResult.intent)) {
            return false;
        }
        if (this.slh == null ? serviceTokenResult.slh != null : !this.slh.equals(serviceTokenResult.slh)) {
            return false;
        }
        if (this.ph == null ? serviceTokenResult.ph != null : !this.ph.equals(serviceTokenResult.ph)) {
            return false;
        }
        if (this.cUserId != null) {
            return this.cUserId.equals(serviceTokenResult.cUserId);
        }
        if (serviceTokenResult.cUserId == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((((((this.sid != null ? this.sid.hashCode() : 0) * 31) + (this.serviceToken != null ? this.serviceToken.hashCode() : 0)) * 31) + (this.security != null ? this.security.hashCode() : 0)) * 31) + (this.errorCode != null ? this.errorCode.hashCode() : 0)) * 31) + (this.errorMessage != null ? this.errorMessage.hashCode() : 0)) * 31) + (this.errorStackTrace != null ? this.errorStackTrace.hashCode() : 0)) * 31) + (this.intent != null ? this.intent.hashCode() : 0)) * 31) + (this.slh != null ? this.slh.hashCode() : 0)) * 31) + (this.ph != null ? this.ph.hashCode() : 0)) * 31) + (this.cUserId != null ? this.cUserId.hashCode() : 0)) * 31) + (this.peeked ? 1 : 0)) * 31) + (this.useV1Parcel ? 1 : 0)) * 31;
        if (this.userId != null) {
            i = this.userId.hashCode();
        }
        return hashCode + i;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public String cUserId;
        /* access modifiers changed from: private */
        public ErrorCode errorCode = ErrorCode.ERROR_NONE;
        /* access modifiers changed from: private */
        public String errorMessage;
        /* access modifiers changed from: private */
        public String errorStackTrace;
        /* access modifiers changed from: private */
        public Intent intent;
        /* access modifiers changed from: private */
        public boolean peeked;
        /* access modifiers changed from: private */
        public String ph;
        /* access modifiers changed from: private */
        public String security;
        /* access modifiers changed from: private */
        public String serviceToken;
        /* access modifiers changed from: private */
        public final String sid;
        /* access modifiers changed from: private */
        public String slh;
        /* access modifiers changed from: private */
        public boolean useV1Parcel;
        /* access modifiers changed from: private */
        public String userId;

        public Builder(String str) {
            this.sid = str;
        }

        public Builder serviceToken(String str) {
            this.serviceToken = str;
            return this;
        }

        public Builder security(String str) {
            this.security = str;
            return this;
        }

        public Builder errorMessage(String str) {
            this.errorMessage = str;
            return this;
        }

        public Builder errorStackTrace(String str) {
            this.errorStackTrace = str;
            return this;
        }

        public Builder errorCode(ErrorCode errorCode2) {
            this.errorCode = errorCode2;
            return this;
        }

        public Builder intent(Intent intent2) {
            this.intent = intent2;
            return this;
        }

        public Builder slh(String str) {
            this.slh = str;
            return this;
        }

        public Builder ph(String str) {
            this.ph = str;
            return this;
        }

        public Builder cUserId(String str) {
            this.cUserId = str;
            return this;
        }

        public Builder peeked(boolean z) {
            this.peeked = z;
            return this;
        }

        public Builder useV1Parcel(boolean z) {
            this.useV1Parcel = z;
            return this;
        }

        public Builder userId(String str) {
            this.userId = str;
            return this;
        }

        public static Builder copyFrom(ServiceTokenResult serviceTokenResult) {
            return new Builder(serviceTokenResult.sid).serviceToken(serviceTokenResult.serviceToken).security(serviceTokenResult.security).errorCode(serviceTokenResult.errorCode).errorMessage(serviceTokenResult.errorMessage).errorStackTrace(serviceTokenResult.errorStackTrace).intent(serviceTokenResult.intent).slh(serviceTokenResult.slh).ph(serviceTokenResult.ph).cUserId(serviceTokenResult.cUserId).peeked(serviceTokenResult.peeked).useV1Parcel(serviceTokenResult.useV1Parcel).userId(serviceTokenResult.userId);
        }

        public ServiceTokenResult build() {
            return new ServiceTokenResult(this);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.useV1Parcel) {
            writeToParcelV1(parcel, i);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("sid", this.sid);
        bundle.putString("serviceToken", this.serviceToken);
        bundle.putString("security", this.security);
        bundle.putInt("errorCode", this.errorCode == null ? -1 : this.errorCode.ordinal());
        bundle.putString("errorMessage", this.errorMessage);
        bundle.putString(PARCEL_BUNDLE_KEY_ERROR_STACK_TRACE, this.errorStackTrace);
        bundle.putParcelable("intent", this.intent);
        bundle.putString("slh", this.slh);
        bundle.putString("ph", this.ph);
        bundle.putString("cUserId", this.cUserId);
        bundle.putBoolean(PARCEL_BUNDLE_KEY_PEEKED, this.peeked);
        bundle.putString("userId", this.userId);
        parcel.writeString(PARCEL_V2_FLAG);
        parcel.writeBundle(bundle);
    }

    protected ServiceTokenResult(Parcel parcel) {
        ErrorCode errorCode2;
        String readString = parcel.readString();
        ErrorCode errorCode3 = null;
        if (!TextUtils.equals(PARCEL_V2_FLAG, readString)) {
            this.sid = readString;
            this.serviceToken = parcel.readString();
            this.security = parcel.readString();
            int readInt = parcel.readInt();
            if (readInt == -1) {
                errorCode2 = null;
            } else {
                errorCode2 = ErrorCode.values()[readInt];
            }
            this.errorCode = errorCode2;
            this.errorMessage = parcel.readString();
            this.errorStackTrace = parcel.readString();
            this.intent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
            this.slh = null;
            this.ph = null;
            this.cUserId = null;
            this.peeked = false;
            this.useV1Parcel = false;
            this.userId = null;
            return;
        }
        Bundle readBundle = parcel.readBundle(Intent.class.getClassLoader());
        this.sid = readBundle.getString("sid");
        this.serviceToken = readBundle.getString("serviceToken");
        this.security = readBundle.getString("security");
        int i = readBundle.getInt("errorCode");
        this.errorCode = i != -1 ? ErrorCode.values()[i] : errorCode3;
        this.errorMessage = readBundle.getString("errorMessage");
        this.errorStackTrace = readBundle.getString(PARCEL_BUNDLE_KEY_ERROR_STACK_TRACE);
        this.intent = (Intent) readBundle.getParcelable("intent");
        this.slh = readBundle.getString("slh");
        this.ph = readBundle.getString("ph");
        this.cUserId = readBundle.getString("cUserId");
        this.peeked = readBundle.getBoolean(PARCEL_BUNDLE_KEY_PEEKED);
        this.useV1Parcel = true;
        this.userId = readBundle.getString("userId");
    }

    private void writeToParcelV1(Parcel parcel, int i) {
        parcel.writeString(this.sid);
        parcel.writeString(this.serviceToken);
        parcel.writeString(this.security);
        parcel.writeInt(this.errorCode == null ? -1 : this.errorCode.ordinal());
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.errorStackTrace);
        parcel.writeParcelable(this.intent, i);
    }
}
