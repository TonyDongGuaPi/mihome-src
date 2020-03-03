package com.xiaomi.smarthome.notishortcut;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.smarthome.frame.log.MyLogger;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.notishortcut.ISmartNoti;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SmartNotiApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21002a = "SmartNotiApi";
    private static SmartNotiApi b;
    /* access modifiers changed from: private */
    public static NetUtil e;
    /* access modifiers changed from: private */
    public static List<ConfigReadyCallback> f = new ArrayList();
    /* access modifiers changed from: private */
    public static ISmartNoti g;
    /* access modifiers changed from: private */
    public static ServiceConnection h = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName componentName) {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ISmartNoti unused = SmartNotiApi.g = ISmartNoti.Stub.asInterface(iBinder);
            synchronized (SmartNotiApi.f) {
                if (SmartNotiApi.f != null) {
                    for (int i = 0; i < SmartNotiApi.f.size(); i++) {
                        if (SmartNotiApi.f.get(i) != null) {
                            ((ConfigReadyCallback) SmartNotiApi.f.get(i)).a(SmartNotiApi.g);
                        }
                    }
                    SmartNotiApi.f.clear();
                }
            }
        }
    };
    private MessageHandlerThread c = new MessageHandlerThread("shourcutWorker");
    private Handler d;

    public interface Callback {
        void a(int i, String str);

        void a(String str);
    }

    public static abstract class CallbackV2 implements Callback {
        public void a() {
        }

        public void b() {
        }
    }

    private SmartNotiApi(final Context context) {
        this.c.start();
        this.d = new Handler(this.c.getLooper());
        this.d.post(new Runnable() {
            public void run() {
                try {
                    context.bindService(new Intent(context, NotiSmartService.class), SmartNotiApi.h, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    MyLogger.a().a(SmartNotiApi.f21002a, e.getMessage());
                }
            }
        });
    }

    public static SmartNotiApi a(Context context) {
        if (b == null) {
            b = new SmartNotiApi(context);
        }
        return b;
    }

    public void a(final String str) {
        this.d.post(new Runnable() {
            public void run() {
                SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                    /* access modifiers changed from: protected */
                    public void a(ISmartNoti iSmartNoti) {
                        try {
                            SmartNotiApi.g.notifyLogin(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void a() {
        this.d.post(new Runnable() {
            public void run() {
                SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                    /* access modifiers changed from: protected */
                    public void a(ISmartNoti iSmartNoti) {
                        try {
                            SmartNotiApi.g.notifyLogout();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void b(final String str) {
        this.d.post(new Runnable() {
            public void run() {
                SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                    /* access modifiers changed from: protected */
                    public void a(ISmartNoti iSmartNoti) {
                        try {
                            SmartNotiApi.g.notifyPushMsg(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(ConfigReadyCallback configReadyCallback) {
        if (g == null) {
            synchronized (f) {
                if (!f.contains(configReadyCallback)) {
                    f.add(configReadyCallback);
                }
            }
            return;
        }
        configReadyCallback.a(g);
    }

    public void a(Context context, final String str, final String str2, CallbackV2 callbackV2) {
        if (callbackV2 != null) {
            callbackV2.a();
        }
        if (callbackV2 != null && (callbackV2 instanceof CallbackV2)) {
            callbackV2.b();
        }
        e = NetUtil.a(context);
        a((ConfigReadyCallback) new ConfigReadyCallback(callbackV2) {
            /* JADX WARNING: Can't wrap try/catch for region: R(23:18|(1:20)(1:21)|22|(1:24)(1:25)|26|(1:28)(1:29)|30|(1:32)(1:33)|34|(1:36)(1:37)|38|(1:40)(1:41)|42|(1:44)|45|(5:47|(1:49)(1:50)|51|(1:53)(1:54)|55)|56|57|58|59|60|61|(3:62|63|76)) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:60:0x0132 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(com.xiaomi.smarthome.notishortcut.ISmartNoti r11) {
                /*
                    r10 = this;
                    r0 = -9999(0xffffffffffffd8f1, float:NaN)
                    org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0176 }
                    java.lang.String r11 = r11.getNetConfig()     // Catch:{ Exception -> 0x0176 }
                    r1.<init>(r11)     // Catch:{ Exception -> 0x0176 }
                    java.lang.String r11 = "userId"
                    boolean r11 = r1.has(r11)     // Catch:{ Exception -> 0x0176 }
                    if (r11 == 0) goto L_0x001a
                    java.lang.String r11 = "userId"
                    java.lang.String r11 = r1.optString(r11)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x001c
                L_0x001a:
                    java.lang.String r11 = ""
                L_0x001c:
                    boolean r2 = android.text.TextUtils.isEmpty(r11)     // Catch:{ Exception -> 0x0176 }
                    if (r2 == 0) goto L_0x002f
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r2 = r10.d     // Catch:{ Exception -> 0x0176 }
                    if (r2 == 0) goto L_0x002f
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r2 = r10.d     // Catch:{ Exception -> 0x0176 }
                    r3 = -400(0xfffffffffffffe70, float:NaN)
                    java.lang.String r4 = "user logout"
                    r2.a(r3, r4)     // Catch:{ Exception -> 0x0176 }
                L_0x002f:
                    java.lang.String r2 = r3     // Catch:{ Exception -> 0x0176 }
                    boolean r2 = android.text.TextUtils.equals(r11, r2)     // Catch:{ Exception -> 0x0176 }
                    r3 = 0
                    if (r2 != 0) goto L_0x0048
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r11 = r10.d     // Catch:{ Exception -> 0x0176 }
                    if (r11 == 0) goto L_0x0045
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r11 = r10.d     // Catch:{ Exception -> 0x0176 }
                    r1 = 401(0x191, float:5.62E-43)
                    java.lang.String r2 = "invalid userid"
                    r11.a(r1, r2)     // Catch:{ Exception -> 0x0176 }
                L_0x0045:
                    r10.d = r3     // Catch:{ Exception -> 0x0176 }
                    return
                L_0x0048:
                    java.lang.String r2 = "server"
                    boolean r2 = r1.has(r2)     // Catch:{ Exception -> 0x0176 }
                    if (r2 == 0) goto L_0x0057
                    java.lang.String r2 = "server"
                    java.lang.String r2 = r1.optString(r2)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x0059
                L_0x0057:
                    java.lang.String r2 = ""
                L_0x0059:
                    java.lang.String r4 = "serverEnv"
                    boolean r4 = r1.has(r4)     // Catch:{ Exception -> 0x0176 }
                    if (r4 == 0) goto L_0x0068
                    java.lang.String r4 = "serverEnv"
                    java.lang.String r4 = r1.optString(r4)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x006a
                L_0x0068:
                    java.lang.String r4 = "release"
                L_0x006a:
                    java.lang.String r5 = "domain"
                    boolean r5 = r1.has(r5)     // Catch:{ Exception -> 0x0176 }
                    if (r5 == 0) goto L_0x0079
                    java.lang.String r5 = "domain"
                    java.lang.String r5 = r1.optString(r5)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x007b
                L_0x0079:
                    java.lang.String r5 = ""
                L_0x007b:
                    java.lang.String r6 = "serviceToken"
                    boolean r6 = r1.has(r6)     // Catch:{ Exception -> 0x0176 }
                    if (r6 == 0) goto L_0x008a
                    java.lang.String r6 = "serviceToken"
                    java.lang.String r6 = r1.optString(r6)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x008c
                L_0x008a:
                    java.lang.String r6 = ""
                L_0x008c:
                    java.lang.String r7 = "ssecurity"
                    boolean r7 = r1.has(r7)     // Catch:{ Exception -> 0x0176 }
                    if (r7 == 0) goto L_0x009b
                    java.lang.String r7 = "ssecurity"
                    java.lang.String r7 = r1.optString(r7)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x009d
                L_0x009b:
                    java.lang.String r7 = ""
                L_0x009d:
                    java.lang.String r8 = "timeDiff"
                    boolean r8 = r1.has(r8)     // Catch:{ Exception -> 0x0176 }
                    if (r8 == 0) goto L_0x00ac
                    java.lang.String r8 = "timeDiff"
                    java.lang.String r8 = r1.optString(r8)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x00ae
                L_0x00ac:
                    java.lang.String r8 = "0"
                L_0x00ae:
                    java.lang.String r9 = "locale"
                    boolean r9 = r1.has(r9)     // Catch:{ Exception -> 0x0176 }
                    if (r9 == 0) goto L_0x00bc
                    java.lang.String r3 = "locale"
                    org.json.JSONObject r3 = r1.optJSONObject(r3)     // Catch:{ Exception -> 0x0176 }
                L_0x00bc:
                    java.lang.String r1 = ""
                    java.lang.String r9 = ""
                    if (r3 == 0) goto L_0x00e5
                    java.lang.String r1 = "country"
                    boolean r1 = r3.has(r1)     // Catch:{ Exception -> 0x0176 }
                    if (r1 == 0) goto L_0x00d1
                    java.lang.String r1 = "country"
                    java.lang.String r1 = r3.optString(r1)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x00d3
                L_0x00d1:
                    java.lang.String r1 = ""
                L_0x00d3:
                    java.lang.String r9 = "language"
                    boolean r9 = r3.has(r9)     // Catch:{ Exception -> 0x0176 }
                    if (r9 == 0) goto L_0x00e2
                    java.lang.String r9 = "language"
                    java.lang.String r3 = r3.optString(r9)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x00e4
                L_0x00e2:
                    java.lang.String r3 = ""
                L_0x00e4:
                    r9 = r3
                L_0x00e5:
                    java.util.Locale r3 = new java.util.Locale     // Catch:{ Exception -> 0x0176 }
                    r3.<init>(r9, r1)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r1 = new com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper     // Catch:{ Exception -> 0x0176 }
                    r1.<init>()     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r1.a((java.lang.String) r11)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r11.a((java.util.Locale) r3)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r11.b(r2)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r11.c(r4)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r11.f(r5)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r11.d(r6)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r11.e(r7)     // Catch:{ Exception -> 0x0176 }
                    long r1 = java.lang.Long.parseLong(r8)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$FlushHelper r11 = r11.a((long) r1)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil r1 = com.xiaomi.smarthome.notishortcut.SmartNotiApi.e     // Catch:{ Exception -> 0x0176 }
                    r11.a((com.xiaomi.smarthome.notishortcut.NetUtil) r1)     // Catch:{ Exception -> 0x0176 }
                    java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Exception -> 0x0176 }
                    r11.<init>()     // Catch:{ Exception -> 0x0176 }
                    org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0176 }
                    r1.<init>()     // Catch:{ Exception -> 0x0176 }
                    java.lang.String r2 = "key"
                    java.lang.String r3 = "click"
                    r1.put(r2, r3)     // Catch:{ JSONException -> 0x0132 }
                    java.lang.String r2 = "us_id"
                    java.lang.String r3 = r4     // Catch:{ JSONException -> 0x0132 }
                    r1.put(r2, r3)     // Catch:{ JSONException -> 0x0132 }
                L_0x0132:
                    com.xiaomi.smarthome.library.http.KeyValuePair r2 = new com.xiaomi.smarthome.library.http.KeyValuePair     // Catch:{ Exception -> 0x0176 }
                    java.lang.String r3 = "data"
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0176 }
                    r2.<init>(r3, r1)     // Catch:{ Exception -> 0x0176 }
                    r11.add(r2)     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$MyRequest$Builder r1 = new com.xiaomi.smarthome.notishortcut.NetUtil$MyRequest$Builder     // Catch:{ Exception -> 0x0166 }
                    r1.<init>()     // Catch:{ Exception -> 0x0166 }
                    java.lang.String r2 = "/scene/start"
                    com.xiaomi.smarthome.notishortcut.NetUtil$MyRequest$Builder r1 = r1.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0166 }
                    java.lang.String r2 = "POST"
                    com.xiaomi.smarthome.notishortcut.NetUtil$MyRequest$Builder r1 = r1.b(r2)     // Catch:{ Exception -> 0x0166 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$MyRequest$Builder r11 = r1.a((java.util.List<com.xiaomi.smarthome.library.http.KeyValuePair>) r11)     // Catch:{ Exception -> 0x0166 }
                    com.xiaomi.smarthome.notishortcut.NetUtil$MyRequest r11 = r11.a()     // Catch:{ Exception -> 0x0166 }
                    com.xiaomi.smarthome.notishortcut.NetUtil r1 = com.xiaomi.smarthome.notishortcut.SmartNotiApi.e     // Catch:{ Exception -> 0x0166 }
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$5$1 r2 = new com.xiaomi.smarthome.notishortcut.SmartNotiApi$5$1     // Catch:{ Exception -> 0x0166 }
                    r2.<init>()     // Catch:{ Exception -> 0x0166 }
                    r1.a((com.xiaomi.smarthome.notishortcut.NetUtil.MyRequest) r11, (com.xiaomi.smarthome.notishortcut.NetUtil.MyCallback) r2)     // Catch:{ Exception -> 0x0166 }
                    goto L_0x0181
                L_0x0166:
                    r11 = move-exception
                    r11.printStackTrace()     // Catch:{ Exception -> 0x0176 }
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r11 = r10.d     // Catch:{ Exception -> 0x0176 }
                    if (r11 == 0) goto L_0x0181
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r11 = r10.d     // Catch:{ Exception -> 0x0176 }
                    java.lang.String r1 = ""
                    r11.a(r0, r1)     // Catch:{ Exception -> 0x0176 }
                    goto L_0x0181
                L_0x0176:
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r11 = r10.d
                    if (r11 == 0) goto L_0x0181
                    com.xiaomi.smarthome.notishortcut.SmartNotiApi$Callback r11 = r10.d
                    java.lang.String r1 = ""
                    r11.a(r0, r1)
                L_0x0181:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.notishortcut.SmartNotiApi.AnonymousClass5.a(com.xiaomi.smarthome.notishortcut.ISmartNoti):void");
            }
        });
    }

    public void a(final Locale locale) {
        if (locale != null) {
            this.d.post(new Runnable() {
                public void run() {
                    final Bundle bundle = new Bundle();
                    if (locale != null) {
                        bundle.putSerializable("data", locale);
                    }
                    SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                        /* access modifiers changed from: protected */
                        public void a(ISmartNoti iSmartNoti) {
                            try {
                                SmartNotiApi.g.setLocale(bundle);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }

    public void a(final ServerBean serverBean) {
        if (serverBean != null) {
            this.d.post(new Runnable() {
                public void run() {
                    final String str = serverBean.f1530a;
                    SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                        /* access modifiers changed from: protected */
                        public void a(ISmartNoti iSmartNoti) {
                            try {
                                SmartNotiApi.g.setServer(str);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }

    public static boolean b(Context context) {
        String a2 = InnerCookieManager.a(context);
        return !TextUtils.isEmpty(a2) && !a2.equalsIgnoreCase("cn");
    }

    public void c(final String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.post(new Runnable() {
                public void run() {
                    SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                        /* access modifiers changed from: protected */
                        public void a(ISmartNoti iSmartNoti) {
                            try {
                                SmartNotiApi.g.setServerEnv(str);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }

    public void a(final String str, final String str2) {
        this.d.post(new Runnable() {
            public void run() {
                SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                    /* access modifiers changed from: protected */
                    public void a(ISmartNoti iSmartNoti) {
                        try {
                            SmartNotiApi.g.notifyOpenNoti(str, str2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void b() {
        this.d.post(new Runnable() {
            public void run() {
                SmartNotiApi.this.b((ConfigReadyCallback) new ConfigReadyCallback() {
                    /* access modifiers changed from: protected */
                    public void a(ISmartNoti iSmartNoti) {
                        try {
                            SmartNotiApi.g.notifyCloseNoti();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void a(final ConfigReadyCallback configReadyCallback) {
        this.d.post(new Runnable() {
            public void run() {
                SmartNotiApi.this.b(configReadyCallback);
            }
        });
    }

    public static abstract class ConfigReadyCallback {
        Callback d;

        /* access modifiers changed from: protected */
        public abstract void a(ISmartNoti iSmartNoti);

        public ConfigReadyCallback() {
        }

        public ConfigReadyCallback(Callback callback) {
            this.d = callback;
        }
    }
}
