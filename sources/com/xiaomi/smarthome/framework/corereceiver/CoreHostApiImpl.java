package com.xiaomi.smarthome.framework.corereceiver;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.Error;
import com.xiaomi.smarthome.core.entity.account.RefreshServiceTokenResult;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.IServerCallback;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.core.CoreHostApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.page.ActivityStack;
import com.xiaomi.smarthome.framework.redpoint.ServerTimerManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;

public class CoreHostApiImpl extends CoreHostApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1534a = "ClientApiStub.onAccountReady";
    public static final String b = "ClientApiStub.onGlobalDynamicSettingReady";
    public static final String c = "ClientApiStub.onStatisticReady";
    public static final String d = "ClientApiStub.onPluginReady";
    public static final String e = "ClientApiStub.onCoreReady";
    public static final String f = "ClientApiStub.onServerChanged";
    public static final String g = "ClientApiStub.onLocaleChanged";
    public static final String h = "ClientApiStub.onUnAuthorized";
    private static final Object i = new Object();
    private static CoreHostApiImpl j;

    private interface CheckStartCallback {
        void a();
    }

    private CoreHostApiImpl() {
    }

    public static CoreHostApiImpl g() {
        if (j == null) {
            synchronized (i) {
                if (j == null) {
                    j = new CoreHostApiImpl();
                }
            }
        }
        return j;
    }

    public void a() throws RemoteException {
        if (GlobalSetting.u) {
            LogUtilGrey.a("login", "CoreHostApiImpl onAccountReady, will send ACTION_ON_ACCOUNT_READY");
        }
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(f1534a));
    }

    public void b() throws RemoteException {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(b));
    }

    public void c() throws RemoteException {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(c));
    }

    public void d() throws RemoteException {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(d));
    }

    public void e() throws RemoteException {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(e));
    }

    public void a(String str, String str2, boolean z, String str3, IServerCallback iServerCallback) throws RemoteException {
        final boolean z2 = z;
        final String str4 = str;
        final IServerCallback iServerCallback2 = iServerCallback;
        final String str5 = str2;
        final String str6 = str3;
        a((CheckStartCallback) new CheckStartCallback() {
            public void a() {
                CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                    public void run() {
                        if (z2) {
                            MiServiceTokenInfo a2 = CoreApi.a().a(str4);
                            if (a2 == null) {
                                Log.e("CoreHostApiImpl", "miServiceTokenInfo is null, sid " + str4);
                                if (iServerCallback2 != null) {
                                    try {
                                        iServerCallback2.onFailure(new Bundle());
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                MiLoginApi.a(str4, a2, true, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                                    public void a(MiServiceTokenInfo miServiceTokenInfo) {
                                        if (iServerCallback2 != null) {
                                            Bundle bundle = new Bundle();
                                            RefreshServiceTokenResult refreshServiceTokenResult = new RefreshServiceTokenResult();
                                            refreshServiceTokenResult.f13965a = str5;
                                            refreshServiceTokenResult.c = miServiceTokenInfo.c;
                                            refreshServiceTokenResult.d = miServiceTokenInfo.d;
                                            refreshServiceTokenResult.e = miServiceTokenInfo.e;
                                            bundle.putParcelable("result", refreshServiceTokenResult);
                                            try {
                                                iServerCallback2.onSuccess(bundle);
                                            } catch (RemoteException unused) {
                                            }
                                        }
                                    }

                                    public void a(ExceptionError exceptionError) {
                                        CoreHostApiImpl.this.a(str4, str5, z2, str6, exceptionError);
                                        if (iServerCallback2 != null) {
                                            try {
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelable("error", new Error(exceptionError.a(), exceptionError.b()));
                                                iServerCallback2.onFailure(bundle);
                                            } catch (RemoteException unused) {
                                            }
                                        }
                                    }
                                });
                            }
                        } else {
                            MiLoginApi.a(str4, str5, str6, true, new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                                public void a(MiServiceTokenInfo miServiceTokenInfo) {
                                    if (iServerCallback2 != null) {
                                        Bundle bundle = new Bundle();
                                        RefreshServiceTokenResult refreshServiceTokenResult = new RefreshServiceTokenResult();
                                        refreshServiceTokenResult.f13965a = str5;
                                        refreshServiceTokenResult.b = str6;
                                        refreshServiceTokenResult.c = miServiceTokenInfo.c;
                                        refreshServiceTokenResult.d = miServiceTokenInfo.d;
                                        refreshServiceTokenResult.e = miServiceTokenInfo.e;
                                        bundle.putParcelable("result", refreshServiceTokenResult);
                                        try {
                                            iServerCallback2.onSuccess(bundle);
                                        } catch (RemoteException unused) {
                                        }
                                    }
                                }

                                public void a(ExceptionError exceptionError) {
                                    CoreHostApiImpl.this.a(str4, str5, z2, str6, exceptionError);
                                    if (iServerCallback2 != null) {
                                        try {
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable("error", new Error(exceptionError.a(), exceptionError.b()));
                                            iServerCallback2.onFailure(bundle);
                                        } catch (RemoteException unused) {
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, boolean z, String str3, ExceptionError exceptionError) {
        String str4;
        String str5;
        String str6;
        String str7;
        MiServiceTokenInfo a2 = CoreApi.a().a(str);
        String str8 = "" + CoreApi.a().q();
        CoreApi.a().s();
        String u = CoreApi.a().u();
        CoreApi.a().w();
        String str9 = "" + CoreApi.a().v();
        String a3 = UserAgentUtil.a(SHApplication.getAppContext());
        String str10 = "" + ServerTimerManager.a(SHApplication.getAppContext()).c();
        MyLog.d("-----------------mijia 401 refresh----------------- ");
        MyLog.a("sid", str);
        MyLog.a("isLogin", str8);
        MyLog.a("cUserId", u);
        MyLog.a("ua", a3);
        MyLog.a("timediff", str10);
        if (a2 == null) {
            str4 = "serviceToken is null ";
        } else {
            str4 = a2.toString();
        }
        MyLog.a("serviceToken", str4);
        MyLog.a("isSystemAccount", str9);
        MyLog.a("log_isSystemAccount", "" + z);
        if (exceptionError == null) {
            str5 = "error is null";
        } else {
            str5 = exceptionError.toString();
        }
        MyLog.a("error", str5);
        MyLog.d("-----------------mijia 401 end-----------------");
        BuglyLog.d(PageUrl.e, "-----------------mijia 401 start-----------------");
        BuglyLog.d("sid", str);
        BuglyLog.d("isLogin", str8);
        BuglyLog.d("ua", a3);
        BuglyLog.d("cUserId", u);
        BuglyLog.d("timediff", str10);
        BuglyLog.d("isSystemAccount", str9);
        BuglyLog.d("log_isSystemAccount", "" + z);
        if (exceptionError == null) {
            str6 = "error is null";
        } else {
            str6 = exceptionError.toString();
        }
        BuglyLog.d("error", str6);
        if (a2 == null) {
            str7 = "serviceToken is null ";
        } else {
            str7 = a2.toString();
        }
        BuglyLog.d("serviceToken", str7);
        LoginMiAccount y = CoreApi.a().y();
        if (y != null) {
            for (MiServiceTokenInfo next : y.d()) {
                BuglyLog.d(next.f23514a != null ? next.f23514a : "", next.c != null ? next.c : "");
                BuglyLog.d(next.f23514a != null ? next.f23514a : "", next.f != null ? next.f : "");
            }
        }
        BuglyLog.d(PageUrl.e, "-----------------mijia 401 end-----------------");
        CrashReport.postCatchedException(new Throwable("smarthome 401&refresh fail"));
    }

    public void f() throws RemoteException {
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            a((CheckStartCallback) new CheckStartCallback() {
                public void a() {
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            SHApplication.getPushManager().f();
                            CoreApi.a().N();
                            HashSet<ActivityListener> hashSet = new HashSet<>();
                            Iterator<ActivityListener> it = ActivityStack.instance.getActivityListeners().iterator();
                            while (it.hasNext()) {
                                ActivityListener next = it.next();
                                try {
                                    next.onUnauthorized();
                                } catch (Exception unused) {
                                    hashSet.add(next);
                                }
                            }
                            for (ActivityListener unregisterActivityListener : hashSet) {
                                ActivityStack.instance.unregisterActivityListener(unregisterActivityListener);
                            }
                            SHApplication.getAppContext().sendBroadcast(new Intent(CoreHostApiImpl.h));
                        }
                    });
                }
            });
        }
    }

    public void a(ServerBean serverBean, ServerBean serverBean2) throws RemoteException {
        CoreApi.a().G();
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            a((CheckStartCallback) new CheckStartCallback() {
                public void a() {
                    CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                        public void run() {
                            SmartHomeDeviceHelper.a().b().i();
                            AreaInfoManager.a().c();
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(CoreHostApiImpl.f));
                        }
                    });
                }
            });
        }
    }

    public void a(final Locale locale, final Locale locale2) throws RemoteException {
        CoreApi.a().J();
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            a((CheckStartCallback) new CheckStartCallback() {
                public void a() {
                    CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                        public void run() {
                            LocalBroadcastManager instance = LocalBroadcastManager.getInstance(SHApplication.getAppContext());
                            Intent intent = new Intent(CoreHostApiImpl.g);
                            intent.putExtra("old_locale", locale);
                            intent.putExtra("new_locale", locale2);
                            instance.sendBroadcast(intent);
                        }
                    });
                }
            });
        }
    }

    public void a(final int i2, final int i3, final String str) throws RemoteException {
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            a((CheckStartCallback) new CheckStartCallback() {
                public void a() {
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            HashSet<ActivityListener> hashSet = new HashSet<>();
                            Iterator<ActivityListener> it = ActivityStack.instance.getActivityListeners().iterator();
                            while (it.hasNext()) {
                                ActivityListener next = it.next();
                                try {
                                    next.onActivityResume(i2, i3, str);
                                } catch (Exception unused) {
                                    hashSet.add(next);
                                }
                            }
                            for (ActivityListener unregisterActivityListener : hashSet) {
                                ActivityStack.instance.unregisterActivityListener(unregisterActivityListener);
                            }
                        }
                    });
                }
            });
        }
    }

    public void a(Bundle bundle) {
        Device n;
        Bundle bundle2 = bundle;
        if (ProcessUtil.b(SHApplication.getAppContext())) {
            String string = bundle2.getString("key_device_address");
            UUID uuid = (UUID) bundle2.getSerializable("key_service_uuid");
            UUID uuid2 = (UUID) bundle2.getSerializable("key_character_uuid");
            byte[] byteArray = bundle2.getByteArray("key_character_value");
            String e2 = BleCacheUtils.e(string);
            if (!TextUtils.isEmpty(e2) && (n = SmartHomeDeviceManager.a().n(e2)) != null && CoreApi.a().c(n.model)) {
                PluginRecord d2 = CoreApi.a().d(n.model);
                Intent intent = new Intent();
                intent.putExtra("key_device_address", string);
                intent.putExtra("key_service_uuid", uuid);
                intent.putExtra("key_character_uuid", uuid2);
                intent.putExtra("key_character_value", byteArray);
                PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d2, 22, intent, n.newDeviceStat(), (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
            }
        }
    }

    private void a(final CheckStartCallback checkStartCallback) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                SHApplication.getApplication().onApplicationLifeCycleStart();
                if (checkStartCallback != null) {
                    checkStartCallback.a();
                }
            }
        });
    }
}
