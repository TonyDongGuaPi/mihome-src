package com.xiaomi.mistatistic.sdk.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.data.AbstractEvent;
import com.xiaomi.mistatistic.sdk.data.i;
import com.xiaomi.mistatistic.sdk.data.j;
import com.xiaomi.mistatistic.sdk.data.k;
import java.util.ArrayList;
import java.util.List;

public class m {

    /* renamed from: a  reason: collision with root package name */
    private static m f12045a = null;
    private static long c = 30000;
    private static final List<i> d = new ArrayList();
    private Handler b = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 31415927) {
                long a2 = k.a(c.a(), "session_begin", 0);
                long a3 = k.a(c.a(), "last_deactivate", 0);
                String a4 = k.a(c.a(), "pv_path", "");
                if (a2 > 0 && a3 > a2) {
                    m.this.a(c.a(), a2, a3);
                }
                if (!TextUtils.isEmpty(a4)) {
                    m.this.b(c.a(), a4);
                }
            }
        }
    };

    public static m a() {
        if (f12045a == null) {
            f12045a = new m();
        }
        return f12045a;
    }

    private m() {
    }

    public void a(final Context context, final String str, final String str2) {
        if (context == null) {
            h.a("record pageStart without context.", (Throwable) null);
            return;
        }
        this.b.removeMessages(31415927);
        d.a().a((d.a) new d.a() {
            public void a() {
                m.this.b(context, str, str2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(Context context, String str, String str2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long a2 = k.a(context.getApplicationContext(), "session_begin", 0);
            long a3 = k.a(context.getApplicationContext(), "last_deactivate", 0);
            String a4 = k.a(context.getApplicationContext(), "pv_path", "");
            if (a2 <= 0) {
                k.b(context.getApplicationContext(), "session_begin", currentTimeMillis);
            } else if (a3 <= 0) {
                k.b(context.getApplicationContext(), "session_begin", currentTimeMillis);
                if (!TextUtils.isEmpty(a4)) {
                    b(context, a4);
                    a4 = "";
                }
            }
            if (a3 > 0 && currentTimeMillis - a3 > c) {
                a(context, a2, a3);
                if (!TextUtils.isEmpty(a4)) {
                    b(context, a4);
                    a4 = "";
                }
                k.b(context.getApplicationContext(), "session_begin", currentTimeMillis);
            }
            String c2 = c(context, str);
            if (!a4.endsWith(c2) || !TextUtils.isEmpty(str2)) {
                k.b(context.getApplicationContext(), "pv_path", a(a4, c2));
                k.b(c.a(), "source_path", a(k.a(c.a(), "source_path", ""), str2));
            }
            d.add(new i(c2, Long.valueOf(currentTimeMillis)));
        } catch (Exception e) {
            h.a("processActActivated exception: ", (Throwable) e);
        }
    }

    public void a(final Context context, final String str) {
        d.a().a((d.a) new d.a() {
            public void a() {
                m.this.a(str, context);
            }
        });
        this.b.sendEmptyMessageDelayed(31415927, c);
    }

    /* access modifiers changed from: private */
    public void a(String str, Context context) {
        int i;
        try {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            k.b(c.a(), "last_deactivate", valueOf.longValue());
            if (!d.isEmpty()) {
                if (!TextUtils.isEmpty(str)) {
                    String c2 = c(context, str);
                    int size = d.size() - 1;
                    while (true) {
                        if (size < 0) {
                            i = -1;
                            break;
                        } else if (TextUtils.equals(d.get(size).e(), c2)) {
                            i = size;
                            break;
                        } else {
                            size--;
                        }
                    }
                } else {
                    i = d.size() - 1;
                }
                if (i >= 0) {
                    i iVar = d.get(i);
                    String e = iVar.e();
                    long d2 = iVar.d();
                    long longValue = valueOf.longValue() - d2;
                    if (!TextUtils.isEmpty(e) && d2 > 0 && longValue > 0) {
                        iVar.a(Long.valueOf(longValue));
                        LocalEventRecorder.a((AbstractEvent) iVar);
                        d.remove(i);
                    }
                }
            }
        } catch (Exception e2) {
            h.a("processActDeativated exception: ", (Throwable) e2);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, long j, long j2) {
        String b2 = j.b(context.getApplicationContext());
        if (TextUtils.isEmpty(b2)) {
            b2 = "NULL";
        }
        LocalEventRecorder.a((AbstractEvent) new k(j, j2, b2));
        k.b(context.getApplicationContext(), "session_begin", 0);
        k.b(c.a(), "last_deactivate", 0);
    }

    /* access modifiers changed from: private */
    public void b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            LocalEventRecorder.a((AbstractEvent) new j(str, k.a(context, "source_path", "")));
            k.b(context, "source_path", "");
            k.b(context, "pv_path", "");
        }
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        return str + "," + str2;
    }

    private String c(Context context, String str) {
        if (context == null) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            str = context.getClass().getName();
        }
        String packageName = context.getPackageName();
        return str.startsWith(packageName) ? str.replace(packageName, "") : str;
    }
}
