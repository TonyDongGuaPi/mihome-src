package com.xiaomi.miot.support.monitor.core.net;

import android.content.ContentValues;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class NetInfo extends BaseInfo {
    public static final String KEY_BACK_TYPE = "bt";
    public static final String KEY_ERROR_CODE = "ec";
    public static final String KEY_IS_WIFI = "w";
    public static final String KEY_RECEIVE_BYTES = "rb";
    public static final String KEY_SEND_BYTES = "sb";
    public static final String KEY_STATUS_CODE = "sc";
    public static final String KEY_TIME_COST = "tc";
    public static final String KEY_TIME_START = "t";
    public static final String KEY_URL = "u";

    /* renamed from: a  reason: collision with root package name */
    private static final String f1485a = "NetInfo";
    public int back_type;
    public long costTime;
    public int errorCode;
    public boolean isWifi;
    public long receivedBytes;
    public long sentBytes;
    public long startTime;
    public int statusCode;
    public String url;

    public NetInfo(int i) {
        this.url = "";
        this.sentBytes = 0;
        this.receivedBytes = 0;
        this.startTime = 0;
        this.costTime = 0;
        this.isWifi = false;
        this.statusCode = 0;
        this.errorCode = 0;
        this.back_type = -1;
        this.mId = i;
        this.startTime = System.currentTimeMillis();
    }

    public NetInfo() {
        this(-1);
    }

    public JSONObject toJson() throws JSONException {
        return super.toJson().put("u", this.url).put(KEY_STATUS_CODE, this.statusCode).put(KEY_ERROR_CODE, this.errorCode).put(KEY_SEND_BYTES, this.sentBytes).put(KEY_RECEIVE_BYTES, this.receivedBytes).put("w", this.isWifi).put("t", this.startTime).put("tc", this.costTime);
    }

    public void parserJsonStr(String str) throws JSONException {
        parserJson(new JSONObject(str));
    }

    public void parserJson(JSONObject jSONObject) throws JSONException {
        this.url = jSONObject.getString("u");
        this.statusCode = jSONObject.getInt(KEY_STATUS_CODE);
        this.errorCode = jSONObject.getInt(KEY_ERROR_CODE);
        this.sentBytes = jSONObject.getLong(KEY_SEND_BYTES);
        this.receivedBytes = jSONObject.getLong(KEY_RECEIVE_BYTES);
        this.isWifi = jSONObject.getBoolean("w");
        this.startTime = jSONObject.getLong("t");
        this.costTime = jSONObject.getLong("tc");
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("u", this.url);
        contentValues.put(KEY_STATUS_CODE, Integer.valueOf(this.statusCode));
        contentValues.put(KEY_ERROR_CODE, Integer.valueOf(this.errorCode));
        contentValues.put(KEY_SEND_BYTES, Long.valueOf(this.sentBytes));
        contentValues.put(KEY_RECEIVE_BYTES, Long.valueOf(this.receivedBytes));
        contentValues.put("w", Boolean.valueOf(this.isWifi));
        contentValues.put("t", Long.valueOf(this.startTime));
        contentValues.put("tc", Long.valueOf(this.costTime));
        return contentValues;
    }

    public void end() {
        if (this.startTime != 0) {
            if (this.costTime == 0) {
                this.costTime = System.currentTimeMillis() - this.startTime;
            }
            if (this.costTime <= 15000) {
                int i = 1;
                if (!(this.back_type == 1 || this.back_type == 2)) {
                    if (!AndroidUtils.a(MiotMonitorManager.a().h())) {
                        i = 2;
                    }
                    this.back_type = i;
                }
                Uri parse = Uri.parse(this.url);
                MiotDataStorage.a().a("http", parse.getHost() + parse.getPath(), this);
            }
        }
    }

    public void setURL(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.sentBytes = (long) str.getBytes().length;
            this.url = a(str);
        }
    }

    private String a(String str) {
        String str2;
        try {
            URL url2 = new URL(str);
            if (url2.getPort() == -1) {
                str2 = "";
            } else {
                str2 = ":" + url2.getPort();
            }
            return TextUtils.concat(new CharSequence[]{url2.getProtocol(), "://", url2.getHost(), str2, url2.getPath()}).toString();
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setSendBytes(long j) {
        this.sentBytes = j;
    }

    public void setReceivedBytes(long j) {
        this.receivedBytes = j;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setCostTime(long j) {
        this.costTime = j;
    }

    public void setBackType(int i) {
        this.back_type = i;
    }
}
