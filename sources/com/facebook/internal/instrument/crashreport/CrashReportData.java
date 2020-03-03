package com.facebook.internal.instrument.crashreport;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class CrashReportData {
    private static final String PARAM_APP_VERSION = "app_version";
    private static final String PARAM_CALLSTACK = "callstack";
    private static final String PARAM_DEVICE_MODEL = "device_model";
    private static final String PARAM_DEVICE_OS = "device_os_version";
    private static final String PARAM_REASON = "reason";
    private static final String PARAM_TIMESTAMP = "timestamp";
    @Nullable
    private String appVersion;
    @Nullable
    private String cause;
    private String filename;
    @Nullable
    private String stackTrace;
    @Nullable
    private Long timestamp;

    public CrashReportData(Throwable th) {
        this.appVersion = Utility.getAppVersion();
        this.cause = InstrumentUtility.getCause(th);
        this.stackTrace = InstrumentUtility.getStackTrace(th);
        this.timestamp = Long.valueOf(System.currentTimeMillis() / 1000);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(InstrumentUtility.CRASH_REPORT_PREFIX);
        stringBuffer.append(this.timestamp.toString());
        stringBuffer.append(".json");
        this.filename = stringBuffer.toString();
    }

    public CrashReportData(File file) {
        this.filename = file.getName();
        JSONObject readFile = InstrumentUtility.readFile(this.filename, true);
        if (readFile != null) {
            this.appVersion = readFile.optString("app_version", (String) null);
            this.cause = readFile.optString("reason", (String) null);
            this.stackTrace = readFile.optString(PARAM_CALLSTACK, (String) null);
            this.timestamp = Long.valueOf(readFile.optLong("timestamp", 0));
        }
    }

    public int compareTo(CrashReportData crashReportData) {
        if (this.timestamp == null) {
            return -1;
        }
        if (crashReportData.timestamp == null) {
            return 1;
        }
        return crashReportData.timestamp.compareTo(this.timestamp);
    }

    public boolean isValid() {
        return (this.stackTrace == null || this.timestamp == null) ? false : true;
    }

    public void save() {
        if (isValid()) {
            InstrumentUtility.writeFile(this.filename, toString());
        }
    }

    public void clear() {
        InstrumentUtility.deleteFile(this.filename);
    }

    @Nullable
    public String toString() {
        JSONObject parameters = getParameters();
        if (parameters == null) {
            return null;
        }
        return parameters.toString();
    }

    @Nullable
    public JSONObject getParameters() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_os_version", Build.VERSION.RELEASE);
            jSONObject.put("device_model", Build.MODEL);
            if (this.appVersion != null) {
                jSONObject.put("app_version", this.appVersion);
            }
            if (this.timestamp != null) {
                jSONObject.put("timestamp", this.timestamp);
            }
            if (this.cause != null) {
                jSONObject.put("reason", this.cause);
            }
            if (this.stackTrace != null) {
                jSONObject.put(PARAM_CALLSTACK, this.stackTrace);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
