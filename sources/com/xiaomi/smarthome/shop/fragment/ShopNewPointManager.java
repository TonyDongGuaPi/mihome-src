package com.xiaomi.smarthome.shop.fragment;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.youpin.core.api.YouPinHttpsAuthApi;
import com.xiaomi.youpin.core.net.NetCallback;
import com.xiaomi.youpin.core.net.NetError;
import com.xiaomi.youpin.core.net.NetRequest;
import com.xiaomi.youpin.core.net.NetResult;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

public class ShopNewPointManager {
    private static ShopNewPointManager f;

    /* renamed from: a  reason: collision with root package name */
    String f22172a;
    String b;
    SharedPreferences c;
    WeakReference<IShowNewPointListener> d;
    long e;

    public interface IShowNewPointListener {
        void onNewPoint(boolean z);
    }

    public static ShopNewPointManager a() {
        if (f == null) {
            f = new ShopNewPointManager();
        }
        return f;
    }

    public void a(IShowNewPointListener iShowNewPointListener) {
        this.d = new WeakReference<>(iShowNewPointListener);
    }

    private void g() {
        if (this.c == null) {
            this.c = SHApplication.getAppContext().getSharedPreferences("new_point_pre", 0);
        }
    }

    public String b() {
        g();
        this.b = this.c.getString(c(), "");
        return this.b;
    }

    public String c() {
        return "new_point_" + CoreApi.a().s();
    }

    public boolean d() {
        return !TextUtils.isEmpty(this.f22172a) && !this.f22172a.equals(this.b);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        IShowNewPointListener iShowNewPointListener;
        if (this.d != null && (iShowNewPointListener = (IShowNewPointListener) this.d.get()) != null) {
            iShowNewPointListener.onNewPoint(z);
        }
    }

    public void e() {
        if (d() && !TextUtils.isEmpty(this.f22172a)) {
            g();
            this.c.edit().putString(c(), this.f22172a).apply();
            this.b = this.f22172a;
            a(false);
        }
    }

    public void f() {
        if (!CoreApi.a().D()) {
            if (!CoreApi.a().q()) {
                a(false);
            } else if (System.currentTimeMillis() - this.e >= 10000) {
                this.e = System.currentTimeMillis();
                NetRequest.Builder builder = new NetRequest.Builder();
                builder.a("GET");
                builder.b("/shopv3/new");
                YouPinHttpsAuthApi.a().a(builder.a(), false, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                    public void a(long j, long j2, boolean z) {
                    }

                    /* renamed from: a */
                    public void b(NetResult netResult) {
                    }

                    /* renamed from: b */
                    public void a(NetResult netResult) {
                        try {
                            String optString = new JSONObject(netResult.d).optString("data");
                            if (!TextUtils.isEmpty(optString)) {
                                ShopNewPointManager.this.f22172a = optString;
                                ShopNewPointManager.this.b = ShopNewPointManager.this.b();
                                if (ShopNewPointManager.this.d()) {
                                    ShopNewPointManager.this.a(true);
                                    return;
                                }
                                return;
                            }
                            ShopNewPointManager.this.a(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void a(NetError netError) {
                        Log.d("MIIO", "onFailure:" + netError.toString());
                    }
                });
            }
        }
    }
}
