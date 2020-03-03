package com.xiaomi.jr.agreement;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.agreement.api.MifiAgreementApi;
import com.xiaomi.jr.base.IAppDelegate;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.NetworkStatusReceiver;
import com.xiaomi.jr.http.model.MiFiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgreementUpdateManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1382a = "agreement_update_info";
    public static final String b = "confirm_ids";
    private static final int c = 5;
    private static final long d = 2000;
    private static final int e = 5;
    private static final long f = 2000;
    private static volatile AgreementUpdateManager g;
    private MifiAgreementApi h;
    private AgreementCallback i;
    private String j;
    private String k;
    /* access modifiers changed from: private */
    public Context l;
    /* access modifiers changed from: private */
    public IAppDelegate m;
    private long n;
    private int o;
    /* access modifiers changed from: private */
    public boolean p;
    private long q;
    private int r;
    /* access modifiers changed from: private */
    public boolean s;
    /* access modifiers changed from: private */
    public Handler t = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Runnable u = new Runnable() {
        public void run() {
            AgreementUpdateManager.this.a(AgreementUpdateManager.this.l, true);
        }
    };
    /* access modifiers changed from: private */
    public Runnable v = new Runnable() {
        public void run() {
            AgreementUpdateManager.this.b(AgreementUpdateManager.this.l, true);
        }
    };
    private NetworkStatusReceiver.NetworkStatusListener w = new NetworkStatusReceiver.NetworkStatusListener() {
        public void a(Context context, NetworkInfo networkInfo) {
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                if (AgreementUpdateManager.this.p) {
                    AgreementUpdateManager.this.t.post(AgreementUpdateManager.this.u);
                    boolean unused = AgreementUpdateManager.this.p = false;
                }
                if (AgreementUpdateManager.this.s) {
                    AgreementUpdateManager.this.t.post(AgreementUpdateManager.this.v);
                    boolean unused2 = AgreementUpdateManager.this.s = false;
                }
            }
        }
    };

    public static AgreementUpdateManager a() {
        if (g == null) {
            synchronized (AgreementUpdateManager.class) {
                if (g == null) {
                    g = new AgreementUpdateManager();
                }
            }
        }
        return g;
    }

    private AgreementUpdateManager() {
    }

    public void a(Context context, IAppDelegate iAppDelegate, AgreementCallback agreementCallback) {
        this.l = context;
        this.i = agreementCallback;
        this.m = iAppDelegate;
        NetworkStatusReceiver.addNetworkStatusListener(context, this.w, false);
    }

    public AgreementCallback b() {
        return this.i;
    }

    public IAppDelegate c() {
        return this.m;
    }

    public MifiAgreementApi d() {
        if (this.h == null) {
            this.h = (MifiAgreementApi) MifiHttpManager.a().a(MifiAgreementApi.class);
        }
        return this.h;
    }

    public void e() {
        this.p = false;
        this.s = false;
        this.t.removeCallbacksAndMessages((Object) null);
    }

    public void a(String str, String str2) {
        this.j = str;
        this.k = str2;
    }

    public void f() {
        a(this.l, false);
    }

    /* access modifiers changed from: private */
    public void a(final Context context, boolean z) {
        if (!XiaomiAccountManager.a().d()) {
            return;
        }
        if (z && this.o >= 5) {
            return;
        }
        if (!NetworkUtils.b(context)) {
            this.p = true;
            return;
        }
        if (z) {
            this.o++;
            this.n *= 2;
        } else {
            this.o = 0;
            this.n = 2000;
        }
        d().a(this.j).enqueue(new Callback<MiFiResponse<AgreementUpdateInfo>>() {
            public void onResponse(Call<MiFiResponse<AgreementUpdateInfo>> call, Response<MiFiResponse<AgreementUpdateInfo>> response) {
                MiFiResponse body = response.body();
                if (body == null || !body.c()) {
                    AgreementUpdateManager.this.h();
                } else {
                    AgreementUpdateManager.this.a(context, (AgreementUpdateInfo) body.d());
                }
            }

            public void onFailure(Call<MiFiResponse<AgreementUpdateInfo>> call, Throwable th) {
                AgreementUpdateManager.this.h();
            }
        });
    }

    /* access modifiers changed from: private */
    public void h() {
        this.t.postDelayed(this.u, this.n);
    }

    /* access modifiers changed from: private */
    public void a(Context context, AgreementUpdateInfo agreementUpdateInfo) {
        if (agreementUpdateInfo == null) {
            return;
        }
        if ((agreementUpdateInfo.a() || agreementUpdateInfo.b()) && agreementUpdateInfo.c() != null && !agreementUpdateInfo.c().isEmpty()) {
            AgreementUpdateUtils.a(context, agreementUpdateInfo);
        }
    }

    public void g() {
        b(this.l, false);
    }

    /* access modifiers changed from: private */
    public void b(Context context, boolean z) {
        a(context, this.m.f().a(b), z);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.r = 0;
            this.m.f().a(b, str);
            a(context, str, false);
        }
    }

    private void a(Context context, String str, boolean z) {
        if (!TextUtils.isEmpty(str) && XiaomiAccountManager.a().d()) {
            if (z && this.r >= 5) {
                return;
            }
            if (!NetworkUtils.b(context)) {
                this.s = true;
                return;
            }
            if (z) {
                this.r++;
                this.q *= 2;
            } else {
                this.r = 0;
                this.q = 2000;
            }
            d().a(this.k, str).enqueue(new Callback<MiFiResponse<Void>>() {
                public void onResponse(Call<MiFiResponse<Void>> call, Response<MiFiResponse<Void>> response) {
                    MiFiResponse body = response.body();
                    if (body == null || !body.c()) {
                        AgreementUpdateManager.this.i();
                    } else {
                        AgreementUpdateManager.this.m.f().b(AgreementUpdateManager.b);
                    }
                }

                public void onFailure(Call<MiFiResponse<Void>> call, Throwable th) {
                    AgreementUpdateManager.this.h();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        this.t.postDelayed(this.v, this.q);
    }
}
