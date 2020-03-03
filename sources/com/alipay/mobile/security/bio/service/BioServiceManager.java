package com.alipay.mobile.security.bio.service;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.alipay.mobile.security.bio.exception.BioIllegalArgumentException;
import com.alipay.mobile.security.bio.exception.InvalidCallException;
import com.alipay.mobile.security.bio.module.MicroModule;
import com.alipay.mobile.security.bio.runtime.Runtime;
import com.alipay.mobile.security.bio.service.impl.BioServiceManagerImpl;
import com.alipay.mobile.security.bio.service.local.LocalService;
import com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.workspace.Env;

public abstract class BioServiceManager {
    public static final String TAG = "BioServiceManager";
    protected static BioServiceManager b;
    private static Env d = Env.ONLINE;

    /* renamed from: a  reason: collision with root package name */
    protected final Context f1004a;
    private final String c;

    /* access modifiers changed from: protected */
    public abstract void destroy();

    public abstract <T> T getBioService(Class<T> cls);

    public abstract <T> T getBioService(String str);

    public abstract int preLoad();

    public abstract <T extends BioService> T putBioService(String str, Class<T> cls);

    public abstract String startBioActivity(BioAppDescription bioAppDescription, MicroModule microModule);

    public static Env getEnv() {
        return d;
    }

    public static void setEnv(Env env) {
        if (d != env) {
            d = env;
            BioLog.i("setEnv: " + env.name);
        }
    }

    protected BioServiceManager(Context context, String str) {
        if (context != null) {
            this.f1004a = context.getApplicationContext();
            this.c = str;
            return;
        }
        throw new BioIllegalArgumentException();
    }

    @Deprecated
    public static synchronized void createInstance(Context context) {
        synchronized (BioServiceManager.class) {
            if (b == null) {
                BioLog.w(TAG, "BioServiceManager.createInstance()");
                b = new BioServiceManagerImpl(context, (String) null);
            } else {
                BioLog.w(TAG, (Throwable) new InvalidCallException("BioServiceManager.createInstance(Context) : null != sInstance"));
            }
        }
    }

    public static synchronized void createInstance(Context context, String str) {
        synchronized (BioServiceManager.class) {
            if (b == null) {
                BioLog.w(TAG, "BioServiceManager.createInstance() zimId=" + str);
                b = new BioServiceManagerImpl(context, str);
            } else if (TextUtils.isEmpty(b.c)) {
                BioLog.w(TAG, (Throwable) new InvalidCallException("BioServiceManager already exist with zimId=null"));
                b.destroy();
                b = new BioServiceManagerImpl(context, str);
            } else if (TextUtils.equals(b.c, str)) {
                BioLog.w(TAG, "Reuse the BioServiceManager.sInstance for zimId=" + str);
            } else {
                MonitorLogService monitorLogService = (MonitorLogService) b.getBioService(MonitorLogService.class);
                if (monitorLogService != null) {
                    monitorLogService.trigUpload();
                }
                BioLog.w(TAG, (Throwable) new InvalidCallException("BioServiceManager already exist with zimId=" + b.c));
                Process.killProcess(Process.myPid());
            }
        }
    }

    public static BioServiceManager getCurrentInstance() {
        return b;
    }

    public static void destroyInstance() {
        if (b != null) {
            b.destroy();
            BioLog.w(TAG, "BioServiceManager.destroyInstance() zimId=" + b.c);
            b = null;
        }
    }

    public Context getBioApplicationContext() {
        return this.f1004a;
    }

    public static <T extends LocalService> T getLocalService(Context context, Class<T> cls) {
        return getLocalService(context, Runtime.getBioServiceDescriptionByInterface(context, cls.getName()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends com.alipay.mobile.security.bio.service.local.LocalService> T getLocalService(android.content.Context r1, com.alipay.mobile.security.bio.service.BioServiceDescription r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0014
            java.lang.Class r2 = r2.getClazz()
            if (r2 == 0) goto L_0x0014
            java.lang.Object r2 = r2.newInstance()     // Catch:{ Throwable -> 0x0010 }
            com.alipay.mobile.security.bio.service.local.LocalService r2 = (com.alipay.mobile.security.bio.service.local.LocalService) r2     // Catch:{ Throwable -> 0x0010 }
            goto L_0x0015
        L_0x0010:
            r2 = move-exception
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.Throwable) r2)
        L_0x0014:
            r2 = r0
        L_0x0015:
            if (r2 == 0) goto L_0x0021
            android.content.Context r1 = r1.getApplicationContext()
            r2.setContext(r1)
            r2.create(r0)
        L_0x0021:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.bio.service.BioServiceManager.getLocalService(android.content.Context, com.alipay.mobile.security.bio.service.BioServiceDescription):com.alipay.mobile.security.bio.service.local.LocalService");
    }
}
