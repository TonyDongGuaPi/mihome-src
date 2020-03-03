package com.xiaomi.smarthome.framework.push;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.alipay.mobile.common.logging.api.ProcessInfo;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tencent.bugly.crashreport.BuglyLog;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushConfiguration;
import com.xiaomi.push.service.module.PushChannelRegion;
import com.xiaomi.smarthome.application.ApplicationLifeCycle;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.devicesubscribe.DeviceBatchPushListener;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.push.api.PushApi;
import com.xiaomi.smarthome.framework.push.listener.AdsPushListener;
import com.xiaomi.smarthome.framework.push.listener.CommonPushListener;
import com.xiaomi.smarthome.framework.push.listener.CommonUriPushListener;
import com.xiaomi.smarthome.framework.push.listener.DeviceBindPushListener;
import com.xiaomi.smarthome.framework.push.listener.DeviceNewPushListener;
import com.xiaomi.smarthome.framework.push.listener.DevicePushListener;
import com.xiaomi.smarthome.framework.push.listener.HomeMemberPushListener;
import com.xiaomi.smarthome.framework.push.listener.SceneStatusPushListener;
import com.xiaomi.smarthome.framework.push.listener.UserBannerPropPushListener;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.scene.push.ScenePushListener;
import com.xiaomi.smarthome.shop.utils.DeviceShopPushListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PushManager extends ApplicationLifeCycle {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1546a = "SmartHome-Push";
    static final int b = 1;
    static final int c = 2;
    private static final String e = "sh_pref_key_reged_pushid_deviceid";
    private static final int p = 5;
    private static PushManager u;
    Handler d = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    PushManager.this.a((MiPushCommandMessage) message.obj);
                    return;
                case 2:
                    PushManager.this.a((MiPushMessage) message.obj);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public String f;
    private long g = -1;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private HashMap<String, PushListener> o = new HashMap<>();
    private HashMap<String, Long> q = new HashMap<>();
    /* access modifiers changed from: private */
    public Context r = SHApplication.getAppContext();
    private Object s = new Object();
    private boolean t = false;

    public static PushManager a() {
        if (u == null) {
            u = new PushManager();
        }
        return u;
    }

    private PushManager() {
    }

    public void b() {
        super.b();
        this.d.postDelayed(new Runnable() {
            public void run() {
                PushManager.this.c();
            }
        }, 30000);
    }

    public void c() {
        boolean z;
        if (j()) {
            synchronized (this.s) {
                z = this.t;
                if (!this.t) {
                    this.t = true;
                }
            }
            if (!z) {
                Logger.a(this.r, (LoggerInterface) new LoggerInterface() {
                    public void a(String str) {
                    }

                    public void a(String str, Throwable th) {
                        Log.d(PushManager.f1546a, str, th);
                    }

                    public void b(String str) {
                        Log.d(PushManager.f1546a, str);
                    }
                });
                a(PushType.ADS, (PushListener) new AdsPushListener());
                a(PushType.DEVICE, (PushListener) DevicePushListener.a());
                a(PushType.DEVICE_BATCH, (PushListener) DeviceBatchPushListener.a());
                a(PushType.SCENE, (PushListener) new ScenePushListener());
                a(PushType.COMMON, (PushListener) new CommonPushListener());
                a(PushType.THIRDPARTYAPI, (PushListener) RemoteAsyncApiHelper.a().f16381a);
                a(PushType.USER_BANNER_PROP, (PushListener) new UserBannerPropPushListener());
                a(PushType.SCENE_STATUS, (PushListener) new SceneStatusPushListener());
                a(PushType.DEVICE_NEW, (PushListener) new DeviceNewPushListener());
                a(PushType.AIOT_BIND, (PushListener) new DeviceBindPushListener());
                a(PushType.INNER_JUMP, (PushListener) new CommonUriPushListener());
                if (!CoreApi.a().D()) {
                    a(PushType.SHOP, (PushListener) new DeviceShopPushListener());
                }
                a(PushType.HOME_MEMBER, (PushListener) new HomeMemberPushListener());
                CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        new Thread(new Runnable() {
                            public void run() {
                                PushChannelRegion pushChannelRegion;
                                if (ServerCompact.d(CoreApi.a().F())) {
                                    pushChannelRegion = PushChannelRegion.Europe;
                                } else if (CoreApi.a().D()) {
                                    pushChannelRegion = PushChannelRegion.Global;
                                } else {
                                    pushChannelRegion = PushChannelRegion.China;
                                }
                                Log.d("PushManager", "start register to push - " + pushChannelRegion.name());
                                PushConfiguration.PushConfigurationBuilder pushConfigurationBuilder = new PushConfiguration.PushConfigurationBuilder();
                                pushConfigurationBuilder.a(true);
                                pushConfigurationBuilder.c(true);
                                if (pushChannelRegion != PushChannelRegion.China) {
                                    FirebaseMessaging.getInstance().setAutoInitEnabled(true);
                                    pushConfigurationBuilder.a(pushChannelRegion).b(true);
                                }
                                MiPushClient.a(PushManager.this.r, GlobalSetting.f1547a, GlobalSetting.b, pushConfigurationBuilder.a());
                            }
                        }).start();
                    }
                });
            }
        }
    }

    public void d() {
        this.f = null;
        MiPushClient.g(this.r);
        synchronized (this.s) {
            this.t = false;
        }
    }

    public void e() {
        Pair<String, String> i2 = i();
        final String a2 = SystemApi.a().a(this.r, ServerCompact.g(this.r));
        if ((i2 == null || TextUtils.isEmpty((CharSequence) i2.first) || TextUtils.isEmpty((CharSequence) i2.second) || !((String) i2.first).equalsIgnoreCase(this.f) || !((String) i2.second).equalsIgnoreCase(a2)) && !TextUtils.isEmpty(this.f) && CoreApi.a().q()) {
            Log.d("PushManager", "start register push to miot - " + this.f + ", " + CoreApi.a().q());
            PushApi.a().a(this.r, this.f, a2, MiPushClient.p(SHApplication.getAppContext()), new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    PushManager.this.a(PushManager.this.f, a2);
                }

                public void onFailure(Error error) {
                    String str;
                    try {
                        ServerBean F = CoreApi.a().F();
                        StringBuilder sb = new StringBuilder();
                        sb.append("fail registering push to miot:");
                        if (error == null) {
                            str = null;
                        } else {
                            str = error.a() + "-" + error.b();
                        }
                        sb.append(str);
                        sb.append(",mPushId=");
                        sb.append(PushManager.this.f);
                        sb.append(",islogin=");
                        sb.append(CoreApi.a().q());
                        sb.append(",server=");
                        sb.append(F == null ? "" : F.f1530a);
                        sb.append(",userid=");
                        sb.append(CoreApi.a().u());
                        String sb2 = sb.toString();
                        MyLog.d(sb2);
                        if (!ServerCompact.e(PushManager.this.r)) {
                            BuglyLog.i(ProcessInfo.ALIAS_PUSH, sb2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public AsyncHandle a(AsyncCallback<Void, Error> asyncCallback) {
        if (CoreApi.a().q()) {
            return PushApi.a().a(this.r, this.f, SystemApi.a().a(this.r, ServerCompact.g(this.r)), asyncCallback);
        }
        if (asyncCallback != null) {
            asyncCallback.sendFailureMessage(new Error(-9999, ""));
        }
        return new AsyncHandle(null);
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        PreferenceUtils.b(e, this.f + "@$##$@" + str2);
    }

    private Pair<String, String> i() {
        String a2 = PreferenceUtils.a(e, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        String[] split = a2.split("@\\$##\\$@");
        if (split.length < 2) {
            return null;
        }
        return new Pair<>(split[0], split[1]);
    }

    public void f() {
        PreferenceUtils.c(this.r, e);
    }

    private boolean j() {
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.r.getSystemService("activity")).getRunningAppProcesses();
            String packageName = this.r.getPackageName();
            int myPid = Process.myPid();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.pid == myPid && packageName.equals(next.processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void a(MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        List<String> commandArguments = miPushCommandMessage.getCommandArguments();
        String str = null;
        String str2 = (commandArguments == null || commandArguments.size() <= 0) ? null : commandArguments.get(0);
        if (commandArguments != null && commandArguments.size() > 1) {
            str = commandArguments.get(1);
        }
        if (MiPushClient.f11511a.equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                this.f = str2;
                Log.d("PushManager", "receive push id - " + this.f);
                e();
            }
        } else if (MiPushClient.c.equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                this.l = str2;
            }
        } else if (MiPushClient.d.equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                this.l = str2;
            }
        } else if (MiPushClient.g.equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                this.k = str2;
            }
        } else if (MiPushClient.h.equals(command)) {
            if (miPushCommandMessage.getResultCode() == 0) {
                this.k = str2;
            }
        } else if (MiPushClient.i.equals(command) && miPushCommandMessage.getResultCode() == 0) {
            this.m = str2;
            this.n = str;
        }
    }

    /* access modifiers changed from: private */
    public void a(MiPushMessage miPushMessage) {
        if (b(miPushMessage)) {
            c(miPushMessage);
        }
    }

    private boolean b(MiPushMessage miPushMessage) {
        try {
            String optString = new JSONObject(miPushMessage.getContent()).optString("msgid");
            if (!TextUtils.isEmpty(optString)) {
                if (this.q.get(optString) != null) {
                    this.q.remove(optString);
                    return false;
                }
                if (this.q.size() >= 5) {
                    String str = "";
                    Long l2 = Long.MAX_VALUE;
                    for (Map.Entry next : this.q.entrySet()) {
                        Long l3 = (Long) next.getValue();
                        if (l3.longValue() < l2.longValue()) {
                            str = (String) next.getKey();
                            l2 = l3;
                        }
                    }
                    if (!TextUtils.isEmpty(str)) {
                        this.q.remove(str);
                    }
                }
                this.q.put(optString, Long.valueOf(System.currentTimeMillis()));
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(com.xiaomi.mipush.sdk.MiPushMessage r10) {
        /*
            r9 = this;
            java.lang.String r0 = r10.getContent()     // Catch:{  }
            java.lang.String r1 = "PushManager"
            com.xiaomi.smarthome.framework.log.LogUtil.c(r1, r0)     // Catch:{  }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{  }
            r1.<init>(r0)     // Catch:{  }
            java.lang.String r0 = "msgid"
            java.lang.String r4 = r1.optString(r0)     // Catch:{  }
            java.lang.String r0 = "type"
            java.lang.String r5 = r1.optString(r0)     // Catch:{  }
            java.lang.String r0 = "body"
            java.lang.String r6 = r1.optString(r0)     // Catch:{  }
            java.lang.String r0 = "up_info"
            java.lang.String r8 = r1.optString(r0)     // Catch:{  }
            java.util.HashMap<java.lang.String, com.xiaomi.smarthome.framework.push.PushListener> r0 = r9.o     // Catch:{  }
            java.lang.Object r0 = r0.get(r5)     // Catch:{  }
            r7 = r0
            com.xiaomi.smarthome.framework.push.PushListener r7 = (com.xiaomi.smarthome.framework.push.PushListener) r7     // Catch:{  }
            if (r7 != 0) goto L_0x0034
            return
        L_0x0034:
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{  }
            if (r0 == 0) goto L_0x004e
            java.lang.String r0 = "PushManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{  }
            r1.<init>()     // Catch:{  }
            java.lang.String r2 = "process - "
            r1.append(r2)     // Catch:{  }
            r1.append(r6)     // Catch:{  }
            java.lang.String r1 = r1.toString()     // Catch:{  }
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)     // Catch:{  }
        L_0x004e:
            boolean r10 = r10.isNotified()     // Catch:{ JSONException -> 0x006a }
            if (r10 == 0) goto L_0x0067
            com.xiaomi.smarthome.frame.core.CoreApi r10 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x006a }
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ JSONException -> 0x006a }
            com.xiaomi.smarthome.framework.push.PushManager$6 r1 = new com.xiaomi.smarthome.framework.push.PushManager$6     // Catch:{ JSONException -> 0x006a }
            r2 = r1
            r3 = r9
            r2.<init>(r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x006a }
            r10.a((android.content.Context) r0, (com.xiaomi.smarthome.frame.core.CoreApi.IsCoreReadyCallback) r1)     // Catch:{ JSONException -> 0x006a }
            goto L_0x006a
        L_0x0067:
            r7.a(r4, r6)     // Catch:{ JSONException -> 0x006a }
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.push.PushManager.c(com.xiaomi.mipush.sdk.MiPushMessage):void");
    }

    public String g() {
        return this.f;
    }

    public void a(PushType pushType, PushListener pushListener) {
        if (pushListener != null) {
            this.o.put(pushType.getValue(), pushListener);
        }
    }

    public void b(PushType pushType, PushListener pushListener) {
        this.o.remove(pushType.getValue());
    }

    public DeviceShopPushListener h() {
        return (DeviceShopPushListener) this.o.get(PushType.SHOP.getValue());
    }
}
