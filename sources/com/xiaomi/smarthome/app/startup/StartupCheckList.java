package com.xiaomi.smarthome.app.startup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.app.startup.CTAHelper;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.corereceiver.CoreHostApiImpl;
import com.xiaomi.smarthome.international.SelectServerUtils;
import com.xiaomi.smarthome.international.ServerHelper;
import com.xiaomi.smarthome.library.common.util.MijiaWrapper;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;

public class StartupCheckList {

    public interface CheckListCallback {
        void a();

        void b();

        void c();

        void d();

        void e();
    }

    /* access modifiers changed from: private */
    public static void b(String str) {
    }

    public static boolean a() {
        return !CTAHelper.a(SHApplication.getAppContext()) && CTAHelper.b(SHApplication.getAppContext()) && ServerCompact.a(SHApplication.getAppContext()) != null;
    }

    public static boolean b() {
        if (SHApplication.isApplicationStart()) {
            return true;
        }
        if (CTAHelper.a(SHApplication.getAppContext()) || !CTAHelper.b(SHApplication.getAppContext()) || ServerCompact.a(SHApplication.getAppContext()) == null) {
            return false;
        }
        return true;
    }

    public static void a(CheckListCallback checkListCallback) {
        if (SHApplication.isApplicationStart()) {
            SHApplication.getApplication().onApplicationLifeCycleStart();
            if (checkListCallback != null) {
                checkListCallback.e();
                return;
            }
            return;
        }
        d(checkListCallback);
    }

    public static void b(final CheckListCallback checkListCallback) {
        CoreApi.a().a(false, true, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                CoreApi.a().a(ServerCompact.b(), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        CoreApi.a().S();
                        checkListCallback.e();
                    }
                });
            }

            public void onFailure(Error error) {
                checkListCallback.b();
            }
        });
    }

    private static void d(CheckListCallback checkListCallback) {
        if (CTAHelper.a(SHApplication.getAppContext())) {
            f(checkListCallback);
        } else if (CTAHelper.b(SHApplication.getAppContext())) {
            e(checkListCallback);
        } else {
            f(checkListCallback);
        }
    }

    /* access modifiers changed from: private */
    public static void e(CheckListCallback checkListCallback) {
        boolean z;
        if (ServerCompact.a(SHApplication.getAppContext()) != null) {
            b("already selected server,skip");
            SHApplication.getApplication().onApplicationLifeCycleStart();
            if (checkListCallback != null) {
                checkListCallback.e();
                return;
            }
            return;
        }
        if (!CoreApi.a().l() ? !ServerHelper.a(SHApplication.getAppContext()) : !ServerHelper.a(SHApplication.getAppContext())) {
            z = false;
        } else {
            z = true;
        }
        if (z || SelectServerUtils.c()) {
            b(String.format("compatOldVersion: %s ,SelectServerUtils.isCN(): %s", new Object[]{Boolean.toString(z), Boolean.toString(SelectServerUtils.c())}));
            SmartNotiApi.a(SHApplication.getAppContext()).a(ServerCompact.b());
            if (CoreApi.a().l()) {
                CoreApi.a().a(ServerCompact.b(), (AsyncCallback<Void, Error>) null);
                if (checkListCallback != null) {
                    checkListCallback.c();
                }
                SHApplication.getApplication().onApplicationLifeCycleStart();
                if (checkListCallback != null) {
                    checkListCallback.e();
                    return;
                }
                return;
            }
            final MijiaWrapper mijiaWrapper = new MijiaWrapper(checkListCallback);
            IntentFilter intentFilter = new IntentFilter(CoreHostApiImpl.e);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    StartupCheckList.b("onReceive: set server on core ready ");
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                    CheckListCallback checkListCallback = (CheckListCallback) mijiaWrapper.f18690a;
                    mijiaWrapper.f18690a = null;
                    CoreApi.a().a(ServerCompact.b(), (AsyncCallback<Void, Error>) null);
                    if (checkListCallback != null) {
                        checkListCallback.c();
                    }
                    SHApplication.getApplication().onApplicationLifeCycleStart();
                    if (checkListCallback != null) {
                        checkListCallback.e();
                    }
                }
            }, intentFilter);
            return;
        }
        b("show server");
        g(checkListCallback);
    }

    static void a(CTAHelper.DisclaimCallback disclaimCallback) {
        if (SHApplication.getAppContext() != null) {
            final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(SHApplication.getAppContext());
            final MijiaWrapper mijiaWrapper = new MijiaWrapper(disclaimCallback);
            instance.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    instance.unregisterReceiver(this);
                    CTAHelper.DisclaimCallback disclaimCallback = (CTAHelper.DisclaimCallback) mijiaWrapper.f18690a;
                    mijiaWrapper.f18690a = null;
                    int intExtra = intent.getIntExtra("param_key", 0);
                    if (intExtra == 1) {
                        if (disclaimCallback != null) {
                            disclaimCallback.a();
                        }
                    } else if (intExtra == 2 && disclaimCallback != null) {
                        disclaimCallback.b();
                    }
                }
            }, new IntentFilter(CTAHelper.f13727a));
            Intent intent = new Intent(SHApplication.getAppContext(), CTAActivity.class);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            SHApplication.getAppContext().startActivity(intent);
        }
    }

    private static void f(final CheckListCallback checkListCallback) {
        a((CTAHelper.DisclaimCallback) new CTAHelper.DisclaimCallback() {
            public void a() {
                if (checkListCallback != null) {
                    checkListCallback.a();
                }
                StartupCheckList.e(checkListCallback);
            }

            public void b() {
                if (checkListCallback != null) {
                    checkListCallback.b();
                }
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        });
    }

    private static void g(final CheckListCallback checkListCallback) {
        ServerHelper.a(SHApplication.getAppContext(), 1, new ServerHelper.InternationalCallback() {
            public void a() {
                if (checkListCallback != null) {
                    checkListCallback.c();
                }
                SHApplication.getApplication().onApplicationLifeCycleStart();
                if (checkListCallback != null) {
                    checkListCallback.e();
                }
            }

            public void b() {
                if (checkListCallback != null) {
                    checkListCallback.d();
                }
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        }, (String) null);
    }
}
