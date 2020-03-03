package com.xiaomi.smarthome.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import cn.jiajixin.nuwa.Nuwa;
import cn.jiajixin.nuwa.NuwaException;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class HotFixManager {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f13734a = false;
    private static final String d = "HotFixManager";
    private static final String e = "hot_fix_pre";
    private static final String f = "hot_fix_pre_md5";
    private static final String g = "hot_fix_pre_patch_file";
    private static final String h = "hot_fix_pre_last_patch_file";
    private static final String i = "hot_fix_pre_app_version";
    private static final String j = "hot_fix_pre_url";
    private static final String k = "hot_fix_pre_update_time";
    private static HotFixManager l;
    volatile boolean b = false;
    WeakReference<AsyncCallback<Void, Error>> c;
    private boolean m = false;
    /* access modifiers changed from: private */
    public Context n;
    private SharedPreferences o;
    private int p;
    private String q;
    /* access modifiers changed from: private */
    public String r;
    /* access modifiers changed from: private */
    public String s;
    private String t;
    private long u;
    private File v;
    private Handler w = new Handler();

    public static void a() {
    }

    public static void b(Context context) {
    }

    public static synchronized HotFixManager a(Context context) {
        HotFixManager hotFixManager;
        synchronized (HotFixManager.class) {
            if (l == null) {
                l = new HotFixManager(context);
            }
            hotFixManager = l;
        }
        return hotFixManager;
    }

    private HotFixManager(Context context) {
        this.n = context.getApplicationContext();
        this.v = context.getDir("patch", 0);
        this.v.mkdirs();
        this.o = context.getSharedPreferences(e, 0);
        this.p = this.o.getInt(i, 0);
        this.q = this.o.getString(f, "");
        this.r = this.o.getString(g, "");
        this.s = this.o.getString(j, "");
        this.t = this.o.getString(h, "");
        this.u = this.o.getLong(k, 0);
        if (!TextUtils.isEmpty(this.t) && !this.t.equals(this.r)) {
            Log.e(d, "delete old patch:" + this.t);
            new File(this.t).deleteOnExit();
            this.o.edit().remove(h).apply();
            this.t = "";
        }
        if (this.p != SystemApi.a().e(this.n)) {
            Log.d(d, "version not same clear");
            c();
        }
        try {
            Nuwa.a(context);
            this.m = true;
        } catch (NuwaException unused) {
            this.m = false;
        }
        if (this.m && !TextUtils.isEmpty(this.r)) {
            try {
                Nuwa.a(context, this.r);
                Log.d(d, "loadpatch:" + this.r);
            } catch (NuwaException unused2) {
            }
        }
        if (ProcessUtil.b(context)) {
            this.w.postDelayed(new Runnable() {
                public void run() {
                    HotFixManager.a();
                }
            }, 30000);
        }
    }

    static class Pacth {

        /* renamed from: a  reason: collision with root package name */
        public boolean f13740a;
        public String b;
        public String c;

        Pacth() {
        }

        public static Pacth a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            Log.d(HotFixManager.d, "parseFromJson:" + jSONObject.toString());
            Pacth pacth = new Pacth();
            pacth.f13740a = jSONObject.getBoolean("hasPatch");
            if (pacth.f13740a) {
                pacth.b = jSONObject.getString("url");
                pacth.c = jSONObject.getString("md5");
            }
            return pacth;
        }
    }

    public static void a(Context context, AsyncCallback<Void, Error> asyncCallback) {
        if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, Constants.Name.DISABLED));
        }
    }

    public void a(AsyncCallback<Void, Error> asyncCallback) {
        if (this.b) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "is updating"));
            }
        } else if (!this.m) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "not supprot"));
            }
        } else if (System.currentTimeMillis() - this.u >= 60000) {
            this.b = true;
            if (asyncCallback != null) {
                this.c = new WeakReference<>(asyncCallback);
            }
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("version", SystemApi.a().e(this.n));
                jSONObject.put("channel", GlobalSetting.v);
                jSONObject.put("apiLevel", Build.VERSION.SDK_INT);
                jSONObject.put("model", Build.MODEL);
            } catch (JSONException unused) {
            }
            Log.d(d, "updatePatch:" + jSONObject.toString());
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            CoreApi.a().a(this.n, new NetRequest.Builder().a("POST").b("/patch/check").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Pacth>() {
                /* renamed from: a */
                public Pacth parse(JSONObject jSONObject) throws JSONException {
                    return Pacth.a(jSONObject);
                }
            }, Crypto.RC4, new AsyncCallback<Pacth, Error>() {
                /* renamed from: a */
                public void onSuccess(Pacth pacth) {
                    HotFixManager.this.a(pacth);
                }

                public void onFailure(Error error) {
                    Log.d(HotFixManager.d, "updatePatch onfailure");
                    HotFixManager.this.a(error.a(), error.b());
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "updates too more"));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str) {
        if (this.c != null && this.c.get() != null) {
            ((AsyncCallback) this.c.get()).onFailure(new Error(i2, str));
            this.c = null;
            this.b = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.c != null && this.c.get() != null) {
            ((AsyncCallback) this.c.get()).onSuccess(null);
            this.c = null;
            this.b = false;
        }
    }

    /* access modifiers changed from: private */
    public void a(Pacth pacth) {
        if (pacth == null) {
            a(-1, "patch is null");
        } else if (!pacth.f13740a) {
            c();
            a(-1, "patch.hasPatch");
        } else if (!pacth.c.equals(this.q) || !new File(this.r).exists()) {
            this.t = this.r;
            this.r = this.v.getAbsolutePath() + "/" + pacth.c + ".apk";
            this.q = pacth.c;
            this.s = pacth.b;
            this.u = System.currentTimeMillis();
            SharedPreferences.Editor edit = this.o.edit();
            edit.putString(f, this.q);
            edit.putString(g, this.r);
            edit.putString(j, this.s);
            edit.putInt(i, this.p);
            edit.putString(h, this.t);
            edit.putLong(k, this.u);
            edit.apply();
            d();
        } else {
            Log.d(d, "patch not need update");
            a(-1, "patch not need update");
        }
    }

    private void c() {
        Log.d(d, "clearPreference");
        this.t = this.r;
        if (this.t == null) {
            this.t = "";
        }
        this.q = "";
        this.r = "";
        this.p = SystemApi.a().e(this.n);
        this.s = "";
        this.u = 0;
        SharedPreferences.Editor edit = this.o.edit();
        edit.remove(f);
        edit.remove(g);
        edit.remove(j);
        edit.putInt(i, this.p);
        edit.putString(h, this.t);
        edit.remove(k);
        edit.apply();
    }

    private void d() {
        if (TextUtils.isEmpty(this.r) || TextUtils.isEmpty(this.s)) {
            a(-1, "mPatchFile not exist");
        } else {
            new Thread() {
                public void run() {
                    Log.d(HotFixManager.d, "downloadPatch:" + HotFixManager.this.s);
                    final File file = new File(HotFixManager.this.r + DefaultDiskStorage.FileType.TEMP);
                    NetworkUtils.a(HotFixManager.this.n, HotFixManager.this.s, file, new NetworkUtils.OnDownloadProgress() {
                        public void a(long j, long j2) {
                        }

                        public void a(String str) {
                            Log.d(HotFixManager.d, "patch download onCompleted:" + HotFixManager.this.r);
                            file.renameTo(new File(HotFixManager.this.r));
                            HotFixManager.this.b();
                        }

                        public void a() {
                            HotFixManager.this.a(-1, "failed download path");
                        }

                        public void b() {
                            HotFixManager.this.a(-1, "failed download path");
                        }
                    }, false, false);
                }
            }.start();
        }
    }
}
