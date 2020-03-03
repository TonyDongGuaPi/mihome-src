package com.xiaomi.smarthome.frame.mistat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import java.util.Map;

public class MiStatApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16352a = "MiStatApi.onMiStatReadyInternal";
    private static final Object d = new Object();
    private static MiStatApi e;
    HandlerThread b = new MessageHandlerThread("MiStatApiWorker", 10);
    Handler c;
    private boolean f;

    public interface IsMiStatReadyCallback {
        void a();
    }

    private MiStatApi() {
        this.b.start();
        this.c = new Handler(this.b.getLooper());
    }

    public static MiStatApi a() {
        if (e == null) {
            synchronized (d) {
                if (e == null) {
                    e = new MiStatApi();
                }
            }
        }
        return e;
    }

    public void a(final int i, final long j) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(i, j);
            }
        });
    }

    public void a(final Context context, final String str, final String str2) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(context, str, str2);
            }
        });
    }

    public void a(final Activity activity, final String str) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(activity, str);
            }
        });
    }

    public void a(final Context context, final String str) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(context, str);
            }
        });
    }

    public void b() {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.c();
            }
        });
    }

    public void b(final Context context, final String str) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.b(context, str);
            }
        });
    }

    public void a(final String str, final String str2) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(str, str2);
            }
        });
    }

    public void a(final String str, final String str2, final Map map) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(str, str2, (Map<String, String>) map);
            }
        });
    }

    public void a(String str, String str2, long j) {
        final String str3 = str;
        final String str4 = str2;
        final long j2 = j;
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(str3, str4, j2);
            }
        });
    }

    public void a(String str, String str2, long j, Map map) {
        final String str3 = str;
        final String str4 = str2;
        final long j2 = j;
        final Map map2 = map;
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(str3, str4, j2, (Map<String, String>) map2);
            }
        });
    }

    public void b(String str, String str2, long j) {
        final String str3 = str;
        final String str4 = str2;
        final long j2 = j;
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.b(str3, str4, j2);
            }
        });
    }

    public void a(final String str, final String str2, final String str3) {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.a(str, str2, str3);
            }
        });
    }

    public void c() {
        a(new IsMiStatReadyCallback() {
            public void a() {
                MiStatInterface.d();
            }
        });
    }

    public void d() {
        synchronized (d) {
            this.f = true;
        }
    }

    private boolean e() {
        boolean z;
        synchronized (d) {
            z = this.f;
        }
        return z;
    }

    private void a(final IsMiStatReadyCallback isMiStatReadyCallback) {
        if (!e()) {
            IntentFilter intentFilter = new IntentFilter(f16352a);
            LocalBroadcastManager.getInstance(FrameManager.b().c()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    LocalBroadcastManager.getInstance(FrameManager.b().c()).unregisterReceiver(this);
                    if (isMiStatReadyCallback != null) {
                        MiStatApi.this.c.post(new Runnable() {
                            public void run() {
                                isMiStatReadyCallback.a();
                            }
                        });
                    }
                }
            }, intentFilter);
        } else if (isMiStatReadyCallback != null) {
            this.c.post(new Runnable() {
                public void run() {
                    isMiStatReadyCallback.a();
                }
            });
        }
    }
}
