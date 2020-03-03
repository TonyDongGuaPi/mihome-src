package com.amap.opensdk.co;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.amap.location.BasicLocateConfig;
import com.amap.location.BasicLocateManager;
import com.amap.location.collection.CollectionManagerProxy;
import com.amap.location.common.network.IHttpClient;
import com.loc.aa;
import com.loc.ch;
import com.loc.ci;
import com.loc.cj;
import com.loc.cm;
import com.loc.cp;
import com.loc.cr;
import com.taobao.weex.common.Constants;
import com.xiaomi.stat.d;
import java.util.Arrays;
import org.json.JSONObject;

public class CoManager {

    /* renamed from: a  reason: collision with root package name */
    boolean f4751a = false;
    boolean b = false;
    private Context c = null;
    private volatile boolean d = false;
    private ci e = null;
    private cr f = null;
    private ch g = null;
    private cp h = null;
    private boolean i = false;

    public CoManager(Context context) {
        if (context != null) {
            try {
                this.c = context;
            } catch (Throwable th) {
                cm.a(th, "CoManager", "<init>");
            }
        }
    }

    private void g() {
        try {
            if (!this.b && this.i) {
                BasicLocateConfig basicLocateConfig = new BasicLocateConfig();
                basicLocateConfig.d(this.e.f());
                basicLocateConfig.a((IHttpClient) this.f);
                basicLocateConfig.c(this.e.c());
                basicLocateConfig.b(this.e.d());
                basicLocateConfig.a((byte) 4);
                basicLocateConfig.a(this.e.b());
                basicLocateConfig.e(this.e.g());
                basicLocateConfig.a(this.e.a());
                BasicLocateManager.a().a(this.c, basicLocateConfig);
                this.b = true;
            }
        } catch (Throwable unused) {
        }
    }

    private void h() {
        try {
            if (this.h == null) {
                this.h = new cp(this.c);
                this.h.a(this.e, (IHttpClient) this.f);
            }
        } catch (Throwable th) {
            cm.a(th, "CoManager", "initOfflineManager");
        }
    }

    public String a(String str, ScanResult[] scanResultArr, boolean z) {
        try {
            if (this.d) {
                if (!this.f4751a) {
                    g();
                    h();
                    return this.h.a(str, scanResultArr, z);
                }
            }
            return null;
        } catch (Throwable th) {
            new String[1][0] = "getOfflineLocation error!!!!";
            cm.a(th, "CoManager", "getOfflineLoc");
            return null;
        }
    }

    public void a() {
        try {
            if (!this.d) {
                System.loadLibrary("apssdk");
                this.d = true;
            }
        } catch (Throwable unused) {
        }
    }

    public void a(int i2) {
        if (this.f != null) {
            this.f.a(i2);
        }
    }

    public void a(String str) {
        if (!this.i) {
            try {
                if (this.e == null) {
                    this.e = new ci();
                }
                if (str != null) {
                    JSONObject jSONObject = new JSONObject(str);
                    this.e.b(jSONObject.optString("als", ""));
                    this.e.a(jSONObject.optString("sv", ""));
                    this.e.d(jSONObject.optString("pn", ""));
                    this.e.c(jSONObject.optString("ak", ""));
                    this.e.e(jSONObject.optString("au", ""));
                    this.e.f(jSONObject.optString(d.t, ""));
                    this.e.a(jSONObject.optBoolean("isimei", true));
                }
            } catch (Throwable th) {
                cm.a(th, "CoManager", "init");
                return;
            }
            if (this.f == null) {
                this.f = new cr(this.c);
            }
            this.f.a(this.e);
            this.i = true;
        }
    }

    public void a(String str, ScanResult[] scanResultArr) {
        try {
            if (!this.d) {
                return;
            }
            if (!this.f4751a) {
                g();
                h();
                cp.a(str, scanResultArr);
            }
        } catch (Throwable th) {
            new String[1][0] = "correctOfflineLocation error!!!!";
            cm.a(th, "CoManager", "correctOfflineLocation");
        }
    }

    public void a(String str, ScanResult[] scanResultArr, double d2, double d3) {
        try {
            if (!this.d) {
                return;
            }
            if (!this.f4751a) {
                g();
                h();
                cp.a(str, scanResultArr, d2, d3);
            }
        } catch (Throwable th) {
            new String[1][0] = "correctOfflineLocation error!!!!";
            cm.a(th, "CoManager", "correctOfflineLocation");
        }
    }

    public String b() {
        try {
            return CollectionManagerProxy.b();
        } catch (Throwable th) {
            cm.a(th, "CoManager", "getCollectVersion");
            return null;
        }
    }

    public void b(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !this.d) {
                if (Arrays.asList(cj.f6534a).contains(aa.a(str))) {
                    System.load(str);
                    this.d = true;
                }
            }
        } catch (Throwable th) {
            cm.a(th, "CoManager", "loadSo");
        }
    }

    public void c() {
        try {
            if (!this.d) {
                return;
            }
            if (!this.f4751a) {
                if (this.g == null) {
                    this.g = new ch(this.c);
                }
                g();
                this.g.a(this.e, this.f);
            }
        } catch (Throwable th) {
            cm.a(th, "CoManager", "startCollect");
        }
    }

    public void d() {
        try {
            if (this.g != null) {
                this.g.a();
            }
            this.g = null;
        } catch (Throwable th) {
            cm.a(th, "CoManager", "stopCollect");
        }
    }

    public void e() {
        try {
            if (this.h != null) {
                this.h.a();
            }
            this.h = null;
        } catch (Throwable th) {
            cm.a(th, "CoManager", "destroyOfflineLoc");
        }
    }

    public void f() {
        try {
            d();
            e();
            BasicLocateManager.a().b();
            this.b = false;
            this.f4751a = true;
            this.c = null;
            this.d = false;
            this.e = null;
            this.f = null;
            this.f4751a = false;
            this.i = false;
        } catch (Throwable th) {
            cm.a(th, "CoManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        }
    }
}
