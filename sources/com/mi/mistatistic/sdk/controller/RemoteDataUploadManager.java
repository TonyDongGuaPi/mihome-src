package com.mi.mistatistic.sdk.controller;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.mi.mistatistic.sdk.BuildSetting;
import com.mi.mistatistic.sdk.Constants;
import com.mi.mistatistic.sdk.CustomSettings;
import com.mi.mistatistic.sdk.controller.AsyncJobDispatcher;
import com.mi.mistatistic.sdk.controller.NetworkUtils;
import com.mi.mistatistic.sdk.data.CustomDataEvent;
import com.mi.mistatistic.sdk.data.EventData;
import com.mi.mistatistic.sdk.model.EventExtraData;
import com.mi.mistatistic.sdk.model.Stat;
import com.mi.mistatistic.sdk.model.Value;
import com.mi.mistatistic.sdk.model.Value_Event;
import com.mi.mistatistic.sdk.model.Value_Page;
import com.mi.mistatistic.sdk.model.Value_RN_Activity;
import com.mi.mistatistic.sdk.model.Value_RN_Download;
import com.mi.mistatistic.sdk.model.Value_RN_LoadBundle;
import com.mi.mistatistic.sdk.model.Value_Session;
import com.mi.mistatistic.sdk.model.Value_Startup;
import com.mi.mistatistic.sdk.model.Value_View_Click;
import com.mi.mistatistic.sdk.model.Value_View_Show;
import com.mi.mistatistic.sdk.util.AesEncryptionUtil;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.stat.d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteDataUploadManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7339a = 20;
    public static String b = (Constants.f7315a + "micra/crash");
    public static final String c = "http://agent.com/app/stat";
    public static String d = (Constants.f7315a + "app/stat");
    public static final String e = "is_new_user";
    public static final String f = "mistat_session";
    public static final String g = "mistat_page_tv";
    public static final String h = "mistat_view_click";
    public static final String i = "mistat_view_show";
    public static final String j = "mistat_stat_event";
    public static final String k = "mistat_start_up";
    public static final String l = "mistat_rn_download";
    public static final String m = "mistat_rn_load_bundle";
    public static final String n = "mistat_rn_activity";
    public static final String o = "upload_delay";
    /* access modifiers changed from: private */
    public static AtomicBoolean p = new AtomicBoolean(false);
    private static final long q = 10000;
    private static final String r = "next_upload_ts";
    private static final String s = "ok";
    private static final String t = "status";
    private static final String u = "data";
    private static final String v = "delay";
    private static final long w = 30000;
    private final List<String> x = Arrays.asList(new String[]{d.u, "fr", "de", "gb", "it"});
    private final boolean y = this.x.contains(Locale.getDefault().getCountry().toLowerCase());

    public void a() {
        a(true);
    }

    public void a(boolean z) {
        if (CustomSettings.d()) {
            if (p.compareAndSet(false, true)) {
                if (e()) {
                    c();
                } else {
                    p.set(false);
                }
            } else if (z) {
                Logger.a(String.format("trigger uploading job with delay %d", new Object[]{10000L}));
                AsyncJobDispatcher.a().a((AsyncJobDispatcher.AsyncJob) new TriggerUploadingJob(), 10000);
            }
        }
    }

    public class PackingEvent {
        /* access modifiers changed from: private */
        public JSONArray b;
        /* access modifiers changed from: private */
        public long c;

        public PackingEvent(JSONArray jSONArray, long j) {
            this.b = jSONArray;
            this.c = j;
        }

        public JSONArray a() {
            return this.b;
        }
    }

    public class TriggerUploadingJob implements AsyncJobDispatcher.AsyncJob {
        public TriggerUploadingJob() {
        }

        public void a() {
            RemoteDataUploadManager.this.a(false);
        }
    }

    public static boolean b() {
        return p.get();
    }

    private void a(String str, long j2) throws IOException {
        Context a2 = ApplicationContextHolder.a();
        String b2 = ApplicationContextHolder.b();
        String f2 = ApplicationContextHolder.f();
        String g2 = ApplicationContextHolder.g();
        String e2 = BuildSetting.e();
        String str2 = Build.VERSION.RELEASE;
        String valueOf = String.valueOf(20);
        String d2 = ApplicationContextHolder.d();
        String c2 = ApplicationContextHolder.c();
        String c3 = this.y ? DeviceIDHolder.c(a2) : DeviceIDHolder.a(a2);
        String str3 = Build.MODEL;
        String valueOf2 = String.valueOf(UploadPolicyEngine.a().e());
        String valueOf3 = String.valueOf(UploadPolicyEngine.a().f());
        String locale = Locale.getDefault().toString();
        String e3 = ApplicationContextHolder.e();
        Context context = a2;
        String k2 = ApplicationContextHolder.k();
        long b3 = TimeUtil.a().b();
        Stat.Builder builder = new Stat.Builder();
        builder.a(b2);
        builder.b(e2);
        builder.c(str2);
        builder.d(valueOf);
        builder.e(d2);
        builder.f(c2);
        builder.g(c3);
        builder.h(str3);
        builder.k(valueOf2);
        builder.l(valueOf3);
        builder.n(locale);
        builder.a(Long.valueOf(b3));
        builder.q(e3);
        builder.r(k2);
        builder.m(g2);
        List<Value> a3 = a(str);
        builder.p(AesEncryptionUtil.b(f2));
        builder.a(a3);
        Stat.Builder builder2 = builder;
        if (!this.y) {
            builder2.i(NetworkUtils.e(context));
            builder2.j(NetworkUtils.b(DeviceIDHolder.b(context)));
        }
        int size = a3.size();
        builder2.a(Integer.valueOf(size));
        if (size > 0) {
            Stat a4 = builder2.build();
            Logger.a("mUploadContent MiStat stat : " + a4.toString(), (Throwable) null);
            final long j3 = j2;
            NetworkUtils.a(BuildSetting.a() ? c : d, a4, (NetworkUtils.IUploadCallback) new NetworkUtils.IUploadCallback() {
                public void a(String str) {
                    try {
                        Logger.a("mUploadContent MiStat result : " + str, (Throwable) null);
                        if (RemoteDataUploadManager.this.l(str)) {
                            RemoteDataUploadManager.this.b(j3);
                        }
                    } catch (Exception e) {
                        RemoteDataUploadManager.p.set(false);
                        e.printStackTrace();
                    }
                }
            });
            return;
        }
        p.set(false);
    }

    private List<Value> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    boolean z = false;
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        Value value = null;
                        JSONObject jSONObject = new JSONObject((String) jSONArray.get(i2));
                        String optString = jSONObject.optString("category");
                        String optString2 = jSONObject.optString("dataJson");
                        if (f.equals(optString)) {
                            value = b(optString2);
                        } else if (g.equals(optString)) {
                            value = c(optString2);
                        } else if (h.equals(optString)) {
                            value = d(optString2);
                        } else if (i.equals(optString)) {
                            value = e(optString2);
                        } else if (j.equals(optString)) {
                            value = f(optString2);
                        } else if (k.equals(optString)) {
                            value = g(optString2);
                        } else if (l.equals(optString)) {
                            value = h(optString2);
                        } else if (m.equals(optString)) {
                            value = i(optString2);
                        } else if (n.equals(optString)) {
                            value = j(optString2);
                        } else if (e.equals(optString)) {
                            value = k(optString2);
                            z = true;
                        }
                        if (value != null) {
                            arrayList.add(value);
                        }
                    }
                    if (!z) {
                        arrayList.add(g());
                    }
                }
            } catch (Exception e2) {
                p.set(false);
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private Value b(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_Session.Builder builder = new Value_Session.Builder();
        builder.a(Long.valueOf(jSONObject.optLong("startTime")));
        builder.b(Long.valueOf(jSONObject.optLong("endTime")));
        builder.a(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.b(jSONObject.optString("netWork"));
        Value_Session a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value c(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_Page.Builder builder = new Value_Page.Builder();
        builder.a(Long.valueOf(jSONObject.optLong("startTime")));
        builder.b(Long.valueOf(jSONObject.optLong("endTime")));
        builder.a(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.b(jSONObject.optString("pageId"));
        builder.c(jSONObject.optString("pageRef"));
        Value_Page a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value d(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_View_Click.Builder builder = new Value_View_Click.Builder();
        builder.a(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.a(Long.valueOf(jSONObject.optLong("timestamp")));
        builder.b(jSONObject.optString("viewId"));
        builder.c(jSONObject.optString("label"));
        builder.d(jSONObject.optString("pageId"));
        Value_View_Click a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value e(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_View_Show.Builder builder = new Value_View_Show.Builder();
        builder.b(Long.valueOf(jSONObject.optLong("viewShowTime")));
        builder.a(Long.valueOf(jSONObject.optLong("viewLeaveTime")));
        builder.c(Long.valueOf(jSONObject.optLong("pageShowTime")));
        builder.a(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.d(jSONObject.optString("pageId"));
        builder.b(jSONObject.optString("viewId"));
        Value_View_Show a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value f(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_Event.Builder builder = new Value_Event.Builder();
        builder.a(Long.valueOf(jSONObject.optLong("timestamp")));
        builder.a(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.b(jSONObject.optString("eventId"));
        builder.d(jSONObject.optString("pageId"));
        builder.c(jSONObject.optString("label"));
        String optString = jSONObject.optString("data");
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(optString)) {
            JSONArray jSONArray = new JSONArray(optString);
            if (jSONArray.length() > 0) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    EventExtraData.Builder builder2 = new EventExtraData.Builder();
                    builder2.a(optJSONObject.optString("key"));
                    builder2.c(optJSONObject.optString("type"));
                    builder2.b(optJSONObject.optString("value"));
                    arrayList.add(builder2.build());
                }
            }
        }
        builder.a((List<EventExtraData>) arrayList);
        Value_Event a2 = builder.build();
        Value.Builder builder3 = new Value.Builder();
        builder3.a(a2);
        return builder3.build();
    }

    private Value g(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_Startup.Builder builder = new Value_Startup.Builder();
        builder.a(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.c(jSONObject.optString(com.xiaomi.market.sdk.Constants.x));
        builder.a(Long.valueOf(jSONObject.optLong("startTime")));
        builder.b(jSONObject.optString(LogCategory.CATEGORY_NETWORK));
        Value_Startup a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value h(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_RN_Download.Builder builder = new Value_RN_Download.Builder();
        builder.e(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.e(Long.valueOf(jSONObject.optLong("timestamp")));
        String optString = jSONObject.optString("data");
        if (!TextUtils.isEmpty(optString)) {
            JSONObject jSONObject2 = new JSONObject(optString);
            String optString2 = jSONObject2.optString("bundle_name");
            String optString3 = jSONObject2.optString("bundle_download_version");
            String optString4 = jSONObject2.optString("bundle_using_version");
            String optString5 = jSONObject2.optString("rn_version");
            long optLong = jSONObject2.optLong("download_time");
            long optLong2 = jSONObject2.optLong("unzip_time");
            long optLong3 = jSONObject2.optLong("patch_time");
            long optLong4 = jSONObject2.optLong("md5_time");
            int optInt = jSONObject2.optInt("code");
            String optString6 = jSONObject2.optString("error_message");
            builder.a(optString2);
            builder.b(optString3);
            builder.c(optString4);
            builder.d(optString5);
            builder.a(Long.valueOf(optLong));
            builder.b(Long.valueOf(optLong2));
            builder.c(Long.valueOf(optLong3));
            builder.d(Long.valueOf(optLong4));
            builder.a(Integer.valueOf(optInt));
            builder.f(optString6);
        }
        Value_RN_Download a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value i(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_RN_LoadBundle.Builder builder = new Value_RN_LoadBundle.Builder();
        builder.d(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.c(Long.valueOf(jSONObject.optLong("timestamp")));
        String optString = jSONObject.optString("data");
        if (!TextUtils.isEmpty(optString)) {
            JSONObject jSONObject2 = new JSONObject(optString);
            String optString2 = jSONObject2.optString("bundle_name");
            String optString3 = jSONObject2.optString("bundle_using_version");
            String optString4 = jSONObject2.optString("rn_version");
            long optLong = jSONObject2.optLong("instance_time");
            long optLong2 = jSONObject2.optLong("view_time");
            builder.a(optString2);
            builder.b(optString3);
            builder.c(optString4);
            builder.a(Long.valueOf(optLong));
            builder.b(Long.valueOf(optLong2));
        }
        Value_RN_LoadBundle a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value j(String str) throws JSONException {
        long j2;
        long j3;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Value_RN_Activity.Builder builder = new Value_RN_Activity.Builder();
        builder.d(jSONObject.optString(TSMAuthContants.PARAM_SESSION_ID));
        builder.g(Long.valueOf(jSONObject.optLong("timestamp")));
        String optString = jSONObject.optString("data");
        if (!TextUtils.isEmpty(optString)) {
            JSONObject jSONObject2 = new JSONObject(optString);
            String optString2 = jSONObject2.optString("bundle_name");
            String optString3 = jSONObject2.optString("bundle_using_version");
            String optString4 = jSONObject2.optString("rn_version");
            String optString5 = jSONObject2.optString("rn_info");
            long optLong = jSONObject2.optLong("onCreate_time");
            long optLong2 = jSONObject2.optLong("onStart_time");
            long optLong3 = jSONObject2.optLong("onResume_time");
            long optLong4 = jSONObject2.optLong("constructor_time");
            long optLong5 = jSONObject2.optLong("componentWillMount_time");
            JSONArray optJSONArray = jSONObject2.optJSONArray("render_time");
            long j4 = optLong5;
            long optLong6 = jSONObject2.optLong("componentDidMount_time");
            long optLong7 = jSONObject2.optLong("init_time");
            long optLong8 = jSONObject2.optLong("total_load_time");
            ArrayList arrayList = new ArrayList();
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                j2 = optLong4;
                j3 = optLong8;
            } else {
                j2 = optLong4;
                j3 = optLong8;
                int i2 = 0;
                while (i2 < optJSONArray.length()) {
                    Long valueOf = Long.valueOf(optJSONArray.optLong(i2));
                    arrayList.add(valueOf);
                    Logger.a("Upload getRNActivityByContent  rederTimes   : " + valueOf, (Throwable) null);
                    i2++;
                    optJSONArray = optJSONArray;
                }
            }
            builder.a(optString2);
            builder.b(optString3);
            builder.c(optString4);
            builder.e(optString5);
            builder.a(Long.valueOf(optLong));
            builder.b(Long.valueOf(optLong2));
            builder.c(Long.valueOf(optLong3));
            builder.d(Long.valueOf(j2));
            builder.e(Long.valueOf(j4));
            builder.a((List<Long>) arrayList);
            builder.f(Long.valueOf(optLong6));
            builder.h(Long.valueOf(optLong7));
            builder.i(Long.valueOf(j3));
        }
        Value_RN_Activity a2 = builder.build();
        Value.Builder builder2 = new Value.Builder();
        builder2.a(a2);
        return builder2.build();
    }

    private Value g() {
        EventExtraData a2 = new EventExtraData.Builder().a("").b("").c(EventData.b).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(a2);
        return new Value.Builder().a(new Value_Event.Builder().a(Long.valueOf(System.currentTimeMillis() / 1000)).b("extraContextEvent").a((List<EventExtraData>) arrayList).build()).build();
    }

    private Value k(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("eventId");
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("data"));
            EventExtraData a2 = new EventExtraData.Builder().a(jSONObject2.optString("key")).b(jSONObject2.optString("value")).c(jSONObject2.optString("type")).build();
            ArrayList arrayList = new ArrayList();
            arrayList.add(a2);
            return new Value.Builder().a(new Value_Event.Builder().a(Long.valueOf(System.currentTimeMillis() / 1000)).b(string).a((List<EventExtraData>) arrayList).build()).build();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void b(long j2) {
        Logger.a("Upload MiStat  doDeleting   :  endTS " + j2, (Throwable) null);
        new EventDAO().a(j2);
        p.set(false);
    }

    public void c() {
        JSONArray jSONArray = new JSONArray();
        ArrayList<CustomDataEvent> b2 = new EventDAO().b();
        long j2 = 0;
        int i2 = 0;
        while (i2 < b2.size() && i2 < 100) {
            CustomDataEvent customDataEvent = b2.get(i2);
            long d2 = customDataEvent.d();
            if (d2 > j2) {
                j2 = d2;
            }
            jSONArray.put(customDataEvent.b());
            if (jSONArray.toString().getBytes().length / 1024 > 50) {
                break;
            }
            i2++;
        }
        PackingEvent packingEvent = new PackingEvent(jSONArray, j2);
        try {
            a(packingEvent.b.toString(), packingEvent.c);
        } catch (Exception unused) {
            p.set(false);
        }
    }

    /* access modifiers changed from: private */
    public boolean l(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("status");
        a(jSONObject);
        return "ok".equals(string);
    }

    private void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("data")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (jSONObject2.has(v)) {
                long j2 = jSONObject2.getLong(v);
                a(j2);
                Logger.c("update upload delay to " + j2);
            }
        }
    }

    public static void a(long j2) {
        PrefPersistUtils.b(ApplicationContextHolder.a(), r, TimeUtil.a().b() + j2);
        PrefPersistUtils.b(ApplicationContextHolder.a(), o, j2);
    }

    public static long d() {
        return PrefPersistUtils.a(ApplicationContextHolder.a(), o, 30000);
    }

    public static boolean e() {
        return TimeUtil.a().b() > PrefPersistUtils.a(ApplicationContextHolder.a(), r, 0);
    }
}
