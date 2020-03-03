package com.amap.location.offline.upload;

import android.content.Context;
import com.amap.location.common.HeaderConfig;
import com.amap.location.offline.IOfflineCloudConfig;
import com.amap.location.offline.OfflineConfig;
import com.amap.openapi.dk;
import com.amap.openapi.dl;
import com.amap.openapi.dp;
import com.amap.openapi.dq;

public class a {
    public static void a(int i) {
        dl.a(i);
    }

    public static void a(int i, byte[] bArr) {
        dl.a(i, bArr);
    }

    public static void a(Context context, final OfflineConfig offlineConfig, IOfflineCloudConfig iOfflineCloudConfig) {
        if (offlineConfig.h == 4 && offlineConfig.q && iOfflineCloudConfig.a()) {
            HeaderConfig.a((byte) 4);
            HeaderConfig.a(offlineConfig.j);
            HeaderConfig.d(offlineConfig.i);
            com.amap.location.common.a.b(context, offlineConfig.k);
            HeaderConfig.c(offlineConfig.p);
            HeaderConfig.b(offlineConfig.o);
            dk dkVar = new dk();
            dkVar.f = offlineConfig.t;
            dkVar.b = new dq() {
                public final int a() {
                    return 10;
                }

                public final long a(int i) {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.f;
                    }
                    return 1000;
                }

                public final long b(int i) {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.g;
                    }
                    return 5000;
                }

                public final void b() {
                }

                public final long c() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.f4601a;
                    }
                    return 100;
                }

                public final boolean c(int i) {
                    if (i == 1) {
                        return true;
                    }
                    if (i != 0 || offlineConfig.u == null) {
                        return false;
                    }
                    return offlineConfig.u.h;
                }

                public final long d() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.e;
                    }
                    return 300000;
                }

                public final long e() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.d;
                    }
                    return 60000;
                }

                public final int f() {
                    return 10000;
                }

                public final long g() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.b;
                    }
                    return 100000;
                }

                public final long h() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.c;
                    }
                    return 864000000;
                }
            };
            dkVar.f4700a = new dp() {
                public final long a() {
                    return 10;
                }

                public final long a(int i) {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.f;
                    }
                    return 1000;
                }

                public final long b(int i) {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.g;
                    }
                    return 5000;
                }

                public final void b() {
                }

                public final long c() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.f4601a;
                    }
                    return 100;
                }

                public final boolean c(int i) {
                    if (i == 1) {
                        return true;
                    }
                    if (i != 0 || offlineConfig.u == null) {
                        return false;
                    }
                    return offlineConfig.u.h;
                }

                public final long d() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.e;
                    }
                    return 300000;
                }

                public final long e() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.d;
                    }
                    return 60000;
                }

                public final int f() {
                    return 10000;
                }

                public final long g() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.b;
                    }
                    return 100000;
                }

                public final long h() {
                    if (offlineConfig.u != null) {
                        return offlineConfig.u.c;
                    }
                    return 864000000;
                }
            };
            dl.a(context, dkVar);
        }
    }

    public static void a(OfflineConfig offlineConfig) {
        if (offlineConfig != null && offlineConfig.h == 4) {
            dl.a();
        }
    }
}
