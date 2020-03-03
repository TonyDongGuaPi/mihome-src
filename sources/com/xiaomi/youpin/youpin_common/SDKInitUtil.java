package com.xiaomi.youpin.youpin_common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.youpin.youpin_common.api.StoreBaseCallback;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import com.xiaomi.youpin.youpin_common.login.YouPinCookieUtils;
import com.xiaomi.youpin.youpin_network.NetWorkDependency;
import com.xiaomi.youpin.youpin_network.NetworkConfig;
import com.xiaomi.youpin.youpin_network.NetworkConfigManager;

public class SDKInitUtil {
    public static void a(Context context, final StoreApiProvider storeApiProvider) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public final void run() {
                StoreApiProvider.this.a("ypsupport2", (StoreBaseCallback<MiServiceTokenInfo>) new StoreBaseCallback<MiServiceTokenInfo>() {
                    public void onFail(int i, String str) {
                    }

                    /* renamed from: a */
                    public void onSuccess(MiServiceTokenInfo miServiceTokenInfo) {
                        YouPinCookieUtils.a(miServiceTokenInfo);
                    }
                });
            }
        });
        NetworkConfigManager.a().a(new NetworkConfig.Builder(context).a((NetWorkDependency) new NetWorkDependency() {
            public void a() {
                storeApiProvider.j();
            }

            public boolean b() {
                return storeApiProvider.f();
            }

            public String c() {
                return UserAgent.d();
            }

            public boolean d() {
                return storeApiProvider.k();
            }

            public String e() {
                return storeApiProvider.i();
            }

            public void a(final NetWorkDependency.ServiceTokenCallback serviceTokenCallback) {
                storeApiProvider.a("miotstore", (StoreBaseCallback<MiServiceTokenInfo>) new StoreBaseCallback<MiServiceTokenInfo>() {
                    /* renamed from: a */
                    public void onSuccess(MiServiceTokenInfo miServiceTokenInfo) {
                        YouPinCookieUtils.a(miServiceTokenInfo);
                        if (serviceTokenCallback != null) {
                            serviceTokenCallback.a();
                        }
                    }

                    public void onFail(int i, String str) {
                        boolean z = i == -1007 || i == -2007 || i == -2011;
                        if (serviceTokenCallback != null) {
                            serviceTokenCallback.a(i, str, z);
                        }
                    }
                });
            }
        }).c());
    }
}
