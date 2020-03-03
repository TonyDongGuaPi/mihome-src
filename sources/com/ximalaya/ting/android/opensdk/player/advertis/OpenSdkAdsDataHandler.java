package com.ximalaya.ting.android.opensdk.player.advertis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.common.Constants;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager;
import com.ximalaya.ting.android.opensdk.player.advertis.shopAdsEvent.XmShopEvent;
import com.ximalaya.ting.android.opensdk.player.advertis.shopAdsEvent.XmShopEvents;
import com.ximalaya.ting.android.opensdk.player.advertis.shopAdsEvent.XmShopsRecord;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.MyAsyncTask;
import com.ximalaya.ting.android.opensdk.util.NetworkType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OpenSdkAdsDataHandler implements IXmAdsDataHandle {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f2152a = false;
    private static OpenSdkAdsDataHandler b = null;
    private static String l = null;
    /* access modifiers changed from: private */
    public static int n = 600000;
    private static final int o = 1;
    private static final int p = 2;
    private static final int q = 3;
    /* access modifiers changed from: private */
    public AppConfig c;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public boolean e = true;
    /* access modifiers changed from: private */
    public long f;
    /* access modifiers changed from: private */
    public TempAdvertis g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public boolean i;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public List<String> k;
    private long m = 0;

    public void a(String str) {
    }

    private OpenSdkAdsDataHandler() {
    }

    public static OpenSdkAdsDataHandler b() {
        if (b == null) {
            synchronized (OpenSdkAdsDataHandler.class) {
                if (b == null) {
                    b = new OpenSdkAdsDataHandler();
                }
            }
        }
        return b;
    }

    private class TempAdvertis {

        /* renamed from: a  reason: collision with root package name */
        long f2162a;
        IDataCallBack<AdvertisList> b;
        boolean c;

        private TempAdvertis() {
            this.c = true;
        }
    }

    public String a(Track track, Map<String, String> map, IDataCallBack<AdvertisList> iDataCallBack) {
        int i2;
        this.i = true;
        Logger.a("OpenSdkAdsDataHandler  == 1");
        if (map != null) {
            try {
                this.f = Long.parseLong(map.get("trackId"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (XmPlayerService.getPlayerSrvice() != null) {
                float n2 = XmPlayerService.getPlayerSrvice().getPlayControl().n();
                if (Float.compare(n2, 0.8f) <= 0) {
                    i2 = 0;
                } else {
                    i2 = Float.compare(n2, 1.4f) >= 0 ? 2 : 1;
                }
                Logger.a("OpenSdkAdsDataHandler  == 2");
                map.put("playSpeed", i2 + "");
            }
        }
        this.g = new TempAdvertis();
        this.g.f2162a = this.f;
        this.g.b = iDataCallBack;
        final String str = map.get("playMethod");
        final long j2 = this.f;
        if (XmPlayerService.getPlayerSrvice() != null) {
            this.h = XmPlayerService.getPlayerSrvice().getTrackUrl(track);
            if (this.h == null || !this.h.startsWith("http")) {
                if (this.f > 0) {
                    this.i = true;
                } else {
                    this.i = false;
                }
            }
        }
        if (!this.i) {
            iDataCallBack.a(null);
            return null;
        }
        if (!this.d) {
            Logger.a("OpenSdkAdsDataHandler  == 3");
            final String str2 = str;
            final IDataCallBack<AdvertisList> iDataCallBack2 = iDataCallBack;
            final Map<String, String> map2 = map;
            CommonRequest.a("http://api.ximalaya.com/openapi-gateway-app/app/config", (Map<String, String>) null, new IDataCallBack<AppConfig>() {
                public void a(AppConfig appConfig) {
                    AppConfig unused = OpenSdkAdsDataHandler.this.c = appConfig;
                    if (OpenSdkAdsDataHandler.this.f == j2) {
                        if (appConfig != null) {
                            if (appConfig.c != null) {
                                boolean unused2 = OpenSdkAdsDataHandler.this.j = appConfig.c.b;
                                OpenSdkAdsDataHandler.f2152a = !appConfig.c.f2160a && !appConfig.c.b && !appConfig.c.c;
                            }
                            boolean unused3 = OpenSdkAdsDataHandler.this.e = appConfig.b;
                            if (appConfig.f != null) {
                                List unused4 = OpenSdkAdsDataHandler.this.k = Arrays.asList(appConfig.f.split(","));
                            }
                            int unused5 = OpenSdkAdsDataHandler.n = appConfig.e * 1000;
                            if (!TextUtils.isEmpty(OpenSdkAdsDataHandler.this.h)) {
                                if (OpenSdkAdsDataHandler.this.k.contains(Uri.parse(OpenSdkAdsDataHandler.this.h).getHost())) {
                                    boolean unused6 = OpenSdkAdsDataHandler.this.i = true;
                                } else {
                                    boolean unused7 = OpenSdkAdsDataHandler.this.i = false;
                                }
                            }
                            try {
                                if (!OpenSdkAdsDataHandler.this.i) {
                                    iDataCallBack2.a(null);
                                } else if (!appConfig.b) {
                                    Logger.a("OpenSdkAdsDataHandler  == 6   ");
                                    iDataCallBack2.a(OpenSdkAdsDataHandler.this.a((AdvertisList) null, (String) map2.get(Advertis.T), str2));
                                } else if ("4".equals(str2)) {
                                    iDataCallBack2.a(OpenSdkAdsDataHandler.this.a((AdvertisList) null, (String) map2.get(Advertis.T), str2));
                                    return;
                                } else {
                                    CommonRequest.a((Map<String, String>) map2, (IDataCallBack<AdvertisList>) new IDataCallBack<AdvertisList>() {
                                        public void a(AdvertisList advertisList) {
                                            if (OpenSdkAdsDataHandler.this.f == j2) {
                                                if (!(advertisList == null || advertisList.a() == null)) {
                                                    for (int i = 0; i < advertisList.a().size(); i++) {
                                                        advertisList.a().get(i).c(OpenSdkAdsDataHandler.this.f);
                                                    }
                                                }
                                                if (!(advertisList == null || advertisList.a() == null)) {
                                                    OpenSdkAdsDataHandler.this.g.c = false;
                                                }
                                                iDataCallBack2.a(OpenSdkAdsDataHandler.this.a(advertisList, (String) map2.get(Advertis.T), str2));
                                            }
                                        }

                                        public void a(int i, String str) {
                                            if (OpenSdkAdsDataHandler.this.f == j2) {
                                                iDataCallBack2.a(i, str);
                                            }
                                        }
                                    }, 1500);
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                Logger.a("OpenSdkAdsDataHandler  == 7   ");
                                iDataCallBack2.a(OpenSdkAdsDataHandler.this.a((AdvertisList) null, (String) map2.get(Advertis.T), str2));
                            }
                        } else {
                            Logger.a("OpenSdkAdsDataHandler  == 8   ");
                            iDataCallBack2.a(OpenSdkAdsDataHandler.this.a((AdvertisList) null, (String) map2.get(Advertis.T), str2));
                        }
                        boolean unused8 = OpenSdkAdsDataHandler.this.d = true;
                    }
                }

                public void a(int i, String str) {
                    if (OpenSdkAdsDataHandler.this.f == j2) {
                        boolean unused = OpenSdkAdsDataHandler.this.d = true;
                        Logger.a("OpenSdkAdsDataHandler  == 9   ");
                        iDataCallBack2.a(OpenSdkAdsDataHandler.this.a((AdvertisList) null, (String) map2.get(Advertis.T), str2));
                    }
                }
            }, new CommonRequest.IRequestCallBack<AppConfig>() {
                /* renamed from: a */
                public AppConfig b(String str) throws Exception {
                    return (AppConfig) new Gson().fromJson(str, AppConfig.class);
                }
            });
        } else {
            if (!TextUtils.isEmpty(this.h) && this.k != null) {
                if (this.k.contains(Uri.parse(this.h).getHost())) {
                    this.i = true;
                } else {
                    this.i = false;
                }
            }
            if (!this.i) {
                iDataCallBack.a(null);
            } else if (!this.e) {
                iDataCallBack.a(a((AdvertisList) null, map.get(Advertis.T), str));
            } else if ("4".equals(str)) {
                iDataCallBack.a(a((AdvertisList) null, map.get(Advertis.T), str));
                return null;
            } else {
                final IDataCallBack<AdvertisList> iDataCallBack3 = iDataCallBack;
                final Map<String, String> map3 = map;
                CommonRequest.a(map, (IDataCallBack<AdvertisList>) new IDataCallBack<AdvertisList>() {
                    public void a(AdvertisList advertisList) {
                        if (OpenSdkAdsDataHandler.this.f == j2) {
                            Logger.a("OpenSdkAdsDataHandler  == 10   ");
                            if (!(advertisList == null || advertisList.a() == null)) {
                                for (int i = 0; i < advertisList.a().size(); i++) {
                                    advertisList.a().get(i).c(OpenSdkAdsDataHandler.this.f);
                                }
                            }
                            if (!(advertisList == null || advertisList.a() == null)) {
                                OpenSdkAdsDataHandler.this.g.c = false;
                            }
                            iDataCallBack3.a(OpenSdkAdsDataHandler.this.a(advertisList, (String) map3.get(Advertis.T), str));
                        }
                    }

                    public void a(int i, String str) {
                        if (OpenSdkAdsDataHandler.this.f == j2) {
                            Logger.a("OpenSdkAdsDataHandler  == 11   ");
                            iDataCallBack3.a(i, str);
                        }
                    }
                }, 1500);
            }
        }
        return null;
    }

    @SuppressLint({"StaticFieldLeak"})
    public void a(final XmAdsManager.TaskWrapper taskWrapper) {
        if (taskWrapper != null && taskWrapper.c != null && taskWrapper.c.a() != null && taskWrapper.c.a().size() > 0 && taskWrapper.c.a().get(0) != null) {
            if (taskWrapper.c.a().get(0).f() > 0 && !TextUtils.isEmpty(taskWrapper.c.a().get(0).l()) && !"null".equals(taskWrapper.c.a().get(0).l())) {
                new MyAsyncTask<Void, Void, Void>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Void doInBackground(Void... voidArr) {
                        Advertis advertis = taskWrapper.c.a().get(0);
                        if (XmPlayerService.getPlayerSrvice() != null) {
                            if (advertis.x() != null) {
                                for (String a2 : advertis.x()) {
                                    AdUrlUtil.a((Context) XmPlayerService.getPlayerSrvice()).a(a2);
                                }
                            }
                            if (advertis.z() != null) {
                                for (String a3 : advertis.z()) {
                                    AdUrlUtil.a((Context) XmPlayerService.getPlayerSrvice()).a(a3);
                                }
                            }
                            AdUrlUtil.a((Context) XmPlayerService.getPlayerSrvice()).a(advertis.m());
                        }
                        final XmAdsRecord xmAdsRecord = new XmAdsRecord();
                        xmAdsRecord.a(System.currentTimeMillis());
                        xmAdsRecord.b(taskWrapper.f2177a.a());
                        xmAdsRecord.c((long) advertis.f());
                        xmAdsRecord.d(taskWrapper.c.e());
                        xmAdsRecord.g(CommonRequest.a().r());
                        xmAdsRecord.e(OpenSdkAdsDataHandler.a((Context) XmPlayerService.getPlayerSrvice()));
                        xmAdsRecord.g(advertis.S());
                        xmAdsRecord.h(-2);
                        xmAdsRecord.b(advertis.a());
                        xmAdsRecord.c(advertis.b());
                        try {
                            xmAdsRecord.h(CommonRequest.a().f());
                        } catch (XimalayaException e) {
                            e.printStackTrace();
                        }
                        if (!TextUtils.isEmpty(advertis.X())) {
                            try {
                                xmAdsRecord.i(new String(OpenSdkUtils.a(advertis.X())));
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        final XmAdsEvent xmAdsEvent = new XmAdsEvent();
                        xmAdsEvent.a(xmAdsRecord);
                        xmAdsEvent.a(System.currentTimeMillis());
                        new XmAdsEvents().a(new ArrayList<XmAdsEvent>() {
                            {
                                add(xmAdsEvent);
                            }
                        });
                        CommonRequest.a((List<XmAdsRecord>) new ArrayList<XmAdsRecord>() {
                            {
                                add(xmAdsRecord);
                            }
                        }, (IDataCallBack) null);
                        return null;
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Void voidR) {
                        super.onPostExecute(voidR);
                    }
                }.a((Params[]) new Void[0]);
            } else if (taskWrapper.c.a().get(0).f() == -1) {
                new MyAsyncTask<Void, Void, Void>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Void doInBackground(Void... voidArr) {
                        Advertis advertis = taskWrapper.c.a().get(0);
                        if (advertis == null) {
                            return null;
                        }
                        XmShopsRecord xmShopsRecord = new XmShopsRecord();
                        try {
                            xmShopsRecord.a(CommonRequest.a().f());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String str = "front";
                        if (advertis.aq() == 1) {
                            str = "front";
                        } else if (advertis.aq() == 2) {
                            str = "middle";
                        } else if (advertis.aq() == 3) {
                            str = "end";
                        }
                        xmShopsRecord.b(str);
                        xmShopsRecord.n(advertis.l());
                        xmShopsRecord.c(CommonRequest.a().r());
                        xmShopsRecord.a(System.currentTimeMillis());
                        int e2 = NetworkType.e(CommonRequest.a().c());
                        try {
                            xmShopsRecord.e(URLEncoder.encode(e2 == 0 ? "移动" : e2 == 1 ? "联通" : e2 == 2 ? "电信" : "其他", "UTF-8"));
                        } catch (UnsupportedEncodingException e3) {
                            e3.printStackTrace();
                        }
                        try {
                            xmShopsRecord.f(CommonRequest.a().k());
                        } catch (XimalayaException e4) {
                            e4.printStackTrace();
                        }
                        xmShopsRecord.g("android");
                        try {
                            xmShopsRecord.h(URLEncoder.encode(Build.MODEL, "UTF-8"));
                        } catch (UnsupportedEncodingException e5) {
                            e5.printStackTrace();
                        }
                        try {
                            xmShopsRecord.i(URLEncoder.encode(CommonRequest.a().h(), "UTF-8"));
                        } catch (UnsupportedEncodingException e6) {
                            e6.printStackTrace();
                        }
                        xmShopsRecord.j(NetworkType.a(CommonRequest.a().c()).getName());
                        xmShopsRecord.k("android");
                        final XmShopEvent xmShopEvent = new XmShopEvent();
                        xmShopEvent.a(xmShopsRecord);
                        xmShopEvent.a(System.currentTimeMillis());
                        XmShopEvents xmShopEvents = new XmShopEvents();
                        xmShopEvents.a(new ArrayList<XmShopEvent>() {
                            {
                                add(xmShopEvent);
                            }
                        });
                        CommonRequest.a(xmShopEvents, (IDataCallBack) null);
                        return null;
                    }
                }.a((Params[]) new Void[0]);
            }
        }
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(l)) {
            return l;
        }
        if (context != null) {
            l = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        }
        if (l == null || l.equalsIgnoreCase(DeviceInfoResult.BUNDLE_KEY_ANDROID_ID) || l.equalsIgnoreCase("9774d56d682e549c")) {
            l = Constants.Name.UNDEFINED;
        }
        return l;
    }

    static class AppConfig extends XimalayaResponse {
        @SerializedName("app_name")

        /* renamed from: a  reason: collision with root package name */
        public String f2159a;
        @SerializedName("ea")
        public boolean b;
        @SerializedName("ad_location")
        public AdLocaltion c;
        @SerializedName("brand_ad_url")
        public AdUrl d;
        @SerializedName("brand_ad_interval")
        public int e = 300;
        @SerializedName("audio_url_prefix")
        public String f;

        AppConfig() {
        }

        class AdLocaltion {

            /* renamed from: a  reason: collision with root package name */
            public boolean f2160a;
            public boolean b;
            public boolean c;

            AdLocaltion() {
            }
        }

        class AdUrl {

            /* renamed from: a  reason: collision with root package name */
            public String f2161a;
            public String b;
            public String c;

            AdUrl() {
            }
        }
    }

    public void a() {
        b.d = false;
    }

    public void a(int i2, int i3) {
        if ((i3 / 2) / 1000 != i2 / 1000) {
            return;
        }
        if (((this.g != null && !this.g.c) || this.j) && System.currentTimeMillis() - this.m > ((long) n) && !f2152a && this.i) {
            if (this.g != null) {
                this.g.c = true;
            }
            XmPlayerService.getPlayerSrvice().pausePlay();
            if (this.g != null) {
                this.g.b.a(a((AdvertisList) null, "false", 2));
            }
        }
    }

    public AdvertisList a(AdvertisList advertisList, String str, String str2) {
        return a(advertisList, str, "4".equals(str2) ? 3 : 1);
    }

    public AdvertisList a(AdvertisList advertisList, String str, int i2) {
        Advertis advertis;
        boolean equals = "true".equals(str);
        if ((System.currentTimeMillis() - this.m > ((long) n) && !f2152a && this.f >= 0 && ((i2 == 1 || i2 == 3) && (advertisList == null || advertisList.a() == null || advertisList.a().size() == 0))) || i2 == 2) {
            this.m = System.currentTimeMillis();
            if (advertisList == null) {
                advertisList = new AdvertisList();
                advertisList.a(0);
            }
            Advertis advertis2 = new Advertis();
            advertis2.b(-1);
            advertis2.q(i2);
            String str2 = new String(Base64.decode("aHR0cDovL2ZkZnMueG1jZG4uY29tL2dyb3VwMjYvTTA5L0RDLzMxL3dLZ0pXRmpjcVhIU0xUTF9BQUFfOVR3aEFVRTIxMy5tcDM=", 0));
            if (this.c != null) {
                if (i2 == 1) {
                    str2 = (this.c.d == null || TextUtils.isEmpty(this.c.d.f2161a)) ? new String(Base64.decode("aHR0cDovL2ZkZnMueG1jZG4uY29tL2dyb3VwNDEvTTA3LzVGLzBCL3dLZ0o4VnM1d3ptVHU1QUtBQVBkZk9jYjdMSTczOC5tcDM=", 0)) : this.c.d.f2161a;
                } else if (i2 == 2) {
                    str2 = (this.c.d == null || TextUtils.isEmpty(this.c.d.b)) ? new String(Base64.decode("aHR0cDovL2ZkZnMueG1jZG4uY29tL2dyb3VwMjYvTTA5L0RDLzMxL3dLZ0pXRmpjcVhIU0xUTF9BQUFfOVR3aEFVRTIxMy5tcDM=", 0)) : this.c.d.b;
                } else if (i2 == 3) {
                    str2 = (this.c.d == null || TextUtils.isEmpty(this.c.d.c)) ? new String(Base64.decode("aHR0cDovL2ZkZnMueG1jZG4uY29tL2dyb3VwMjYvTTA5L0RDLzMxL3dLZ0pXRmpjcVhIU0xUTF9BQUFfOVR3aEFVRTIxMy5tcDM", 0)) : this.c.d.c;
                }
            }
            advertis2.g(str2);
            if (advertisList.a() == null) {
                advertisList.a((List<Advertis>) new ArrayList());
            }
            advertisList.a().add(advertis2);
        }
        if (advertisList == null) {
            advertisList = new AdvertisList();
        }
        advertisList.a(equals);
        if (!(advertisList.a() == null || advertisList.a().size() <= 0 || (advertis = advertisList.a().get(0)) == null)) {
            List<String> V = advertis.V();
            if (V != null && V.size() > 0) {
                advertis.s(V.remove(0));
            }
            advertis.f(equals);
        }
        return advertisList;
    }
}
