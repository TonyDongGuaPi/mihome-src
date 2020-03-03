package com.xiaomi.accountsdk.service;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class DeviceInfoResult implements Parcelable {
    public static final String BUNDLE_KEY_ANDROID_ID = "android_id";
    public static final String BUNDLE_KEY_HASHED_DEVICE_ID = "hashed_device_id";
    public static final Parcelable.Creator<DeviceInfoResult> CREATOR = new Parcelable.Creator<DeviceInfoResult>() {
        public DeviceInfoResult createFromParcel(Parcel parcel) {
            return new DeviceInfoResult(parcel);
        }

        public DeviceInfoResult[] newArray(int i) {
            return new DeviceInfoResult[i];
        }
    };
    public static final int FLAG_ANDROID_ID = 2;
    public static final int FLAG_HASHED_DEVICE_ID = 1;
    private static final String PARCEL_BUNDLE_KEY_DEVICE_INFO = "device_info";
    private static final String PARCEL_BUNDLE_KEY_ERROR_CODE = "error_code";
    private static final String PARCEL_BUNDLE_KEY_ERROR_MESSAGE = "error_message";
    private static final String PARCEL_BUNDLE_KEY_ERROR_STACK_TRACE = "stacktrace";
    public final Bundle deviceInfo;
    public final ErrorCode errorCode;
    public final String errorMessage;
    public final String errorStackTrace;

    public enum ErrorCode {
        ERROR_UNKNOWN,
        ERROR_NONE,
        ERROR_APP_PERMISSION_FORBIDDEN,
        ERROR_TIME_OUT,
        ERROR_NOT_SUPPORTED,
        ERROR_EXECUTION_EXCEPTION,
        ERROR_QUERY_TOO_FREQUENTLY
    }

    public int describeContents() {
        return 0;
    }

    private DeviceInfoResult(Builder builder) {
        this.deviceInfo = builder.deviceInfo;
        this.errorMessage = builder.errorMessage;
        this.errorCode = builder.errorCode;
        this.errorStackTrace = builder.errorStackTrace;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfoResult)) {
            return false;
        }
        DeviceInfoResult deviceInfoResult = (DeviceInfoResult) obj;
        if (this.deviceInfo == null ? deviceInfoResult.deviceInfo != null : !this.deviceInfo.equals(deviceInfoResult.deviceInfo)) {
            return false;
        }
        if (this.errorCode != deviceInfoResult.errorCode) {
            return false;
        }
        if (this.errorMessage == null ? deviceInfoResult.errorMessage == null : this.errorMessage.equals(deviceInfoResult.errorMessage)) {
            return this.errorStackTrace == null ? deviceInfoResult.errorStackTrace == null : this.errorStackTrace.equals(deviceInfoResult.errorStackTrace);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.deviceInfo != null ? this.deviceInfo.hashCode() : 0) * 31) + (this.errorCode != null ? this.errorCode.hashCode() : 0)) * 31) + (this.errorMessage != null ? this.errorMessage.hashCode() : 0)) * 31;
        if (this.errorStackTrace != null) {
            i = this.errorStackTrace.hashCode();
        }
        return hashCode + i;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final Bundle deviceInfo;
        /* access modifiers changed from: private */
        public ErrorCode errorCode = ErrorCode.ERROR_NONE;
        /* access modifiers changed from: private */
        public String errorMessage;
        /* access modifiers changed from: private */
        public String errorStackTrace;

        public Builder(Bundle bundle) {
            this.deviceInfo = bundle;
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

        public static Builder copyFrom(DeviceInfoResult deviceInfoResult) {
            return new Builder(deviceInfoResult.deviceInfo).errorCode(deviceInfoResult.errorCode).errorMessage(deviceInfoResult.errorMessage).errorStackTrace(deviceInfoResult.errorStackTrace);
        }

        public DeviceInfoResult build() {
            return new DeviceInfoResult(this);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putBundle("device_info", this.deviceInfo);
        bundle.putInt("error_code", this.errorCode == null ? -1 : this.errorCode.ordinal());
        bundle.putString("error_message", this.errorMessage);
        bundle.putString(PARCEL_BUNDLE_KEY_ERROR_STACK_TRACE, this.errorStackTrace);
        parcel.writeBundle(bundle);
    }

    protected DeviceInfoResult(Parcel parcel) {
        ErrorCode errorCode2;
        Bundle readBundle = parcel.readBundle(DeviceInfoResult.class.getClassLoader());
        this.deviceInfo = readBundle.getBundle("device_info");
        int i = readBundle.getInt("error_code");
        if (i == -1) {
            errorCode2 = null;
        } else {
            errorCode2 = ErrorCode.values()[i];
        }
        this.errorCode = errorCode2;
        this.errorMessage = readBundle.getString("error_message");
        this.errorStackTrace = readBundle.getString(PARCEL_BUNDLE_KEY_ERROR_STACK_TRACE);
    }
}
