package com.xiaomi.smarthome.service.tasks;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.login.util.LoginUtil;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.youpin.login.api.manager.callback.MiuiSystemLoginCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.Map;

public class LoginTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22078a = "current_uid";
    public static final String b = "error_code";
    /* access modifiers changed from: private */
    public ICallback c;

    LoginTask(ICallback iCallback) {
        this.c = iCallback;
    }

    public void run() {
        Log.e("GetLoginTask", "start login task");
        if (StartupCheckList.b()) {
            CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    LoginTask.this.b();
                }
            });
        } else if (this.c != null) {
            Bundle bundle = new Bundle();
            try {
                Log.e("GetDeviceTask", "CTA not passed yet");
                bundle.putString("GetDeviceTask", "CTA not passed yet");
                this.c.onFailure(bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsAccountReadyCallback) new CoreApi.IsAccountReadyCallback() {
            public void a(boolean z, String str) {
                if (z) {
                    Bundle bundle = new Bundle();
                    bundle.putString(LoginTask.f22078a, str);
                    try {
                        LoginTask.this.c.onSuccess(bundle);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    LoginTask.this.c();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!StartupCheckList.b()) {
            Handler globalHandler = SHApplication.getGlobalHandler();
            if (globalHandler != null) {
                globalHandler.post(new Runnable() {
                    public void run() {
                        Log.e("GetDeviceTask", "realPassCTA");
                        FrameManager.b().m();
                        StartupCheckList.b((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                            public void a() {
                            }

                            public void c() {
                            }

                            public void d() {
                            }

                            public void b() {
                                Bundle bundle = new Bundle();
                                bundle.putInt("error_code", 3);
                                try {
                                    LoginTask.this.c.onFailure(bundle);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }

                            public void e() {
                                Log.e("GetDeviceTask", "pass cta");
                                SHApplication.getApplication().onApplicationLifeCycleStart();
                                LoginTask.this.a();
                            }
                        });
                    }
                });
                return;
            }
            return;
        }
        SHApplication.getApplication().onApplicationLifeCycleStart();
        a();
    }

    /* access modifiers changed from: private */
    public void c() {
        try {
            if (!TextUtils.isEmpty(LoginUtil.b())) {
                SHApplication.getStateNotifier().d();
                MultiHomeDeviceManager.a().b();
                SmartHomeDeviceManager.a().v();
                Log.e("GetDeviceTask", "start login");
                LoginApi.a().a(SHApplication.getAppContext(), (Activity) null, (MiuiSystemLoginCallback) new MiuiSystemLoginCallback() {
                    public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                        Log.e("GetDeviceTask", "login success");
                        Bundle bundle = new Bundle();
                        bundle.putString(LoginTask.f22078a, loginMiAccount.a());
                        try {
                            LoginTask.this.c.onSuccess(bundle);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    public void onLoginFail(int i, String str, Map<String, String> map) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("error_code", 2);
                        try {
                            Log.e("GetDeviceTask", "login failed");
                            LoginTask.this.c.onFailure(bundle);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            SHApplication.getStateNotifier().f();
            Bundle bundle = new Bundle();
            bundle.putInt("error_code", 1);
            Log.e("GetDeviceTask", "login failed");
            this.c.onFailure(bundle);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("GetDeviceTask", "execption happen");
        }
    }
}
