package com.ximalaya.ting.android.opensdk.player.statistic;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.httputil.BaseBuilder;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.model.PostResponse;
import com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord;
import com.ximalaya.ting.android.opensdk.util.Logger;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenSdkPlayStatisticUpload implements IXmPlayStatisticUpload {
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    public static final String f2243a = "trackId";
    public static final String b = "track_id";
    @Deprecated
    public static final String c = "radioId";
    public static final String d = "radio_id";
    public static final String e = "programScheduleId";
    public static final String f = "programId";
    public static final String g = "duration";
    public static final String h = "played_secs";
    public static final String i = "started_at";
    public static final String j = "rec_src";
    public static final String k = "rec_track";
    public static final String l = "play_type";
    public static final String m = "play_source";
    public static final String n = "appid";
    public static final String o = "bundleId";
    public static final String p = "deviceId";
    public static final String q = "version";
    public static final String r = "client";
    private static OpenSdkPlayStatisticUpload s = null;
    private static final int v = 3;
    private HandlerThread t = new HandlerThread("statistics-manager");
    /* access modifiers changed from: private */
    public Handler u;

    public static OpenSdkPlayStatisticUpload b() {
        if (s == null) {
            synchronized (OpenSdkPlayStatisticUpload.class) {
                if (s == null) {
                    s = new OpenSdkPlayStatisticUpload();
                }
            }
        }
        return s;
    }

    private OpenSdkPlayStatisticUpload() {
        this.t.start();
        this.u = new Handler(this.t.getLooper());
    }

    public void a(XmPlayRecord xmPlayRecord) {
        HashMap hashMap = new HashMap();
        if (xmPlayRecord.g()) {
            hashMap.put("track_id", "" + xmPlayRecord.r());
            Logger.c("StatisticsManager", "uploadtrack");
        } else {
            hashMap.put("radio_id", "" + xmPlayRecord.r());
            Logger.c("StatisticsManager", "uploadradio");
        }
        hashMap.put("played_secs", "" + xmPlayRecord.q());
        hashMap.put("duration", "" + xmPlayRecord.m());
        hashMap.put("started_at", "" + xmPlayRecord.B());
        hashMap.put("play_type", "0");
        if (xmPlayRecord.g()) {
            CommonRequest.ag(hashMap, new IDataCallBack<PostResponse>() {
                public void a(PostResponse postResponse) {
                    if (postResponse != null && postResponse.b() == 0) {
                        Logger.a((Object) "postTrackSingleRecord onSuccess");
                    }
                }

                public void a(int i, String str) {
                    Logger.a((Object) "postTrackSingleRecord onError  " + str);
                }
            });
        } else {
            CommonRequest.af(hashMap, new IDataCallBack<PostResponse>() {
                public void a(PostResponse postResponse) {
                    if (postResponse != null && postResponse.b() == 0) {
                        Logger.a((Object) "postLiveSingleRecord onSuccess");
                    }
                }

                public void a(int i, String str) {
                    Logger.a((Object) "postLiveSingleRecord onError  " + str);
                }
            });
        }
    }

    public void a() {
        synchronized (OpenSdkPlayStatisticUpload.class) {
            this.u.removeCallbacksAndMessages((Object) null);
            this.t.quit();
            s = null;
        }
    }

    @Deprecated
    private class Task implements Runnable {
        private String b = "openapi/tracks/record";
        private String c = "openapi/live/record";
        private XmPlayRecord d;
        private int e = 0;

        public Task(XmPlayRecord xmPlayRecord) {
            this.d = xmPlayRecord;
        }

        public void run() {
            Map map;
            String str;
            if (this.d != null) {
                this.e++;
                String str2 = null;
                try {
                    map = CommonRequest.a((Map<String, String>) new HashMap());
                } catch (XimalayaException e2) {
                    e2.printStackTrace();
                    map = null;
                }
                if (map == null) {
                    map = new HashMap();
                }
                if (this.d.g()) {
                    str = DTransferConstants.bj + this.b;
                    map.put("trackId", "" + this.d.r());
                    Logger.c("StatisticsManager", "uploadtrack");
                } else {
                    str = DTransferConstants.bj + this.c;
                    map.put(OpenSdkPlayStatisticUpload.c, "" + this.d.r());
                    Logger.c("StatisticsManager", "uploadradio");
                }
                map.put("played_secs", "" + this.d.q());
                map.put("duration", "" + this.d.m());
                map.put("started_at", "" + this.d.B());
                try {
                    str2 = BaseCall.a().a(BaseBuilder.b(str, map).build()).body().string();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                boolean z = false;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.has("ret") && jSONObject.getInt("ret") == 0) {
                            z = true;
                        }
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                }
                if (!z && this.e < 3) {
                    OpenSdkPlayStatisticUpload.this.u.post(this);
                }
            }
        }
    }
}
