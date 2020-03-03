package com.xiaomi.smarthome.shopglobal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.domain.DomainModel;
import com.mi.global.shop.xmsf.account.ShopSdkCleanListener;
import com.mi.global.shop.xmsf.account.ShopSdkInitParamGroup;
import com.mi.global.shop.xmsf.account.ShopSdkTokenExpiredListener;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopGlobalHelper {

    /* renamed from: a  reason: collision with root package name */
    public static Map<String, String> f1572a = new HashMap();
    private static ShopSdkTokenExpiredListener b;
    private static boolean c = false;

    static {
        f1572a.put(ServerCompact.c, LocaleHelper.e);
        f1572a.put(ServerCompact.f1531a, LocaleHelper.f);
        f1572a.put(ServerCompact.b, LocaleHelper.g);
        f1572a.put(ServerCompact.d, LocaleHelper.l);
        f1572a.put(ServerCompact.e, LocaleHelper.j);
        f1572a.put(ServerCompact.f, LocaleHelper.i);
        f1572a.put("ID", LocaleHelper.h);
        f1572a.put(ServerCompact.i, LocaleHelper.m);
        f1572a.put(ServerCompact.h, LocaleHelper.k);
    }

    public static boolean a(ServerBean serverBean) {
        return !TextUtils.isEmpty(a(serverBean.b));
    }

    public static boolean a(Context context) {
        return !TextUtils.isEmpty(b(context));
    }

    public static String b(Context context) {
        ServerBean a2 = ServerCompact.a(context);
        if (a2 == null) {
            return "";
        }
        return a(a2.b);
    }

    public static String a(String str) {
        if (f1572a.containsKey(str)) {
            return f1572a.get(str);
        }
        return null;
    }

    public static void a() {
        try {
            if (ProcessUtil.b(SHApplication.getApplication())) {
                LogUtilGrey.a("ShopGlobalHelper", "onLogout ");
                if (a((Context) SHApplication.getApplication())) {
                    LogUtilGrey.a("MijiaShopApp", "ShopApp.logout");
                    ShopApp.logout();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b() {
        try {
            if (ProcessUtil.b(SHApplication.getApplication())) {
                LogUtilGrey.a("ShopGlobalHelper", "onLoginSuccess");
                if (a((Context) SHApplication.getApplication())) {
                    a(false);
                    if (!c()) {
                        a((String) null, (ExtendedAuthToken) null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(final ServerBean serverBean) {
        try {
            if (ProcessUtil.b(SHApplication.getApplication()) && a((Context) SHApplication.getApplication())) {
                LogUtilGrey.a("ShopGlobalHelper", "afterSwitchRegionClean " + b((Context) SHApplication.getApplication()));
                boolean z = false;
                c = false;
                if (GlobalSetting.q || GlobalSetting.u) {
                    z = true;
                }
                LogUtil.a("ShopGlobalHelper", "ShopApp.printLog=" + z);
                ShopApp.a(z);
                ShopApp.a(b((Context) SHApplication.getApplication()), f1572a.get(serverBean) != null ? f1572a.get(serverBean) : LocaleHelper.d, (ShopSdkCleanListener) new ShopSdkCleanListener() {
                    public void a() {
                        if (ShopGlobalHelper.f1572a.containsKey(serverBean) && !TextUtils.equals(ShopGlobalHelper.f1572a.get(serverBean), ShopGlobalHelper.b((Context) SHApplication.getApplication()))) {
                            LogUtilGrey.a("ShopGlobalHelper", "cleanOver done, will init " + ShopGlobalHelper.f1572a.get(serverBean));
                            ShopGlobalHelper.a(false);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b A[Catch:{ Exception -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b8 A[Catch:{ Exception -> 0x0014 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(boolean r5) {
        /*
            if (r5 != 0) goto L_0x0007
            boolean r0 = c
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            if (r5 != 0) goto L_0x0017
            com.xiaomi.smarthome.application.SHApplication r0 = com.xiaomi.smarthome.application.SHApplication.getApplication()     // Catch:{ Exception -> 0x0014 }
            boolean r0 = com.xiaomi.smarthome.frame.process.ProcessUtil.b(r0)     // Catch:{ Exception -> 0x0014 }
            if (r0 != 0) goto L_0x0017
            return
        L_0x0014:
            r5 = move-exception
            goto L_0x00c2
        L_0x0017:
            r0 = 1
            c = r0     // Catch:{ Exception -> 0x0014 }
            r1 = 0
            if (r5 != 0) goto L_0x002a
            com.xiaomi.smarthome.application.SHApplication r5 = com.xiaomi.smarthome.application.SHApplication.getApplication()     // Catch:{ Exception -> 0x0014 }
            boolean r5 = a((android.content.Context) r5)     // Catch:{ Exception -> 0x0014 }
            if (r5 == 0) goto L_0x0028
            goto L_0x002a
        L_0x0028:
            r5 = 0
            goto L_0x0052
        L_0x002a:
            com.xiaomi.smarthome.application.SHApplication r5 = com.xiaomi.smarthome.application.SHApplication.getApplication()     // Catch:{ Exception -> 0x0014 }
            java.lang.String r5 = b((android.content.Context) r5)     // Catch:{ Exception -> 0x0014 }
            java.lang.String r2 = "MijiaShopApp"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0014 }
            r3.<init>()     // Catch:{ Exception -> 0x0014 }
            java.lang.String r4 = "ShopApp.init "
            r3.append(r4)     // Catch:{ Exception -> 0x0014 }
            r3.append(r5)     // Catch:{ Exception -> 0x0014 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0014 }
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)     // Catch:{ Exception -> 0x0014 }
            com.xiaomi.smarthome.application.SHApplication r2 = com.xiaomi.smarthome.application.SHApplication.getApplication()     // Catch:{ Exception -> 0x0014 }
            java.lang.String r3 = "mihome_sdk"
            com.mi.global.shop.ShopApp.a((android.app.Application) r2, (java.lang.String) r5, (java.lang.String) r3)     // Catch:{ Exception -> 0x0014 }
            r5 = 1
        L_0x0052:
            java.lang.String r2 = "ShopGlobalHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0014 }
            r3.<init>()     // Catch:{ Exception -> 0x0014 }
            java.lang.String r4 = "initMiShopGlobalSDK "
            r3.append(r4)     // Catch:{ Exception -> 0x0014 }
            r3.append(r5)     // Catch:{ Exception -> 0x0014 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0014 }
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)     // Catch:{ Exception -> 0x0014 }
            r2 = 0
            if (r5 == 0) goto L_0x00b8
            a(r2, r2)     // Catch:{ Exception -> 0x0014 }
            boolean r5 = com.xiaomi.smarthome.globalsetting.GlobalSetting.q     // Catch:{ Exception -> 0x0014 }
            if (r5 != 0) goto L_0x0078
            boolean r5 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ Exception -> 0x0014 }
            if (r5 == 0) goto L_0x0077
            goto L_0x0078
        L_0x0077:
            r0 = 0
        L_0x0078:
            java.lang.String r5 = "ShopGlobalHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0014 }
            r2.<init>()     // Catch:{ Exception -> 0x0014 }
            java.lang.String r3 = "ShopApp.printLog="
            r2.append(r3)     // Catch:{ Exception -> 0x0014 }
            r2.append(r0)     // Catch:{ Exception -> 0x0014 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0014 }
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r5, (java.lang.String) r2)     // Catch:{ Exception -> 0x0014 }
            com.mi.global.shop.ShopApp.a((boolean) r0)     // Catch:{ Exception -> 0x0014 }
            com.mi.global.shop.ShopApp.c(r1)     // Catch:{ Exception -> 0x0014 }
            com.xiaomi.smarthome.shopglobal.ShopGlobalHelper$2 r5 = new com.xiaomi.smarthome.shopglobal.ShopGlobalHelper$2     // Catch:{ Exception -> 0x0014 }
            r5.<init>()     // Catch:{ Exception -> 0x0014 }
            b = r5     // Catch:{ Exception -> 0x0014 }
            java.lang.String r5 = "MijiaShopApp"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0014 }
            r0.<init>()     // Catch:{ Exception -> 0x0014 }
            java.lang.String r1 = "ShopApp.tokenExpiredListener "
            r0.append(r1)     // Catch:{ Exception -> 0x0014 }
            com.mi.global.shop.xmsf.account.ShopSdkTokenExpiredListener r1 = b     // Catch:{ Exception -> 0x0014 }
            r0.append(r1)     // Catch:{ Exception -> 0x0014 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0014 }
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r5, r0)     // Catch:{ Exception -> 0x0014 }
            com.mi.global.shop.xmsf.account.ShopSdkTokenExpiredListener r5 = b     // Catch:{ Exception -> 0x0014 }
            com.mi.global.shop.ShopApp.f = r5     // Catch:{ Exception -> 0x0014 }
            goto L_0x00df
        L_0x00b8:
            java.lang.String r5 = "MijiaShopApp"
            java.lang.String r0 = "ShopApp.tokenExpiredListener set null"
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r5, r0)     // Catch:{ Exception -> 0x0014 }
            com.mi.global.shop.ShopApp.f = r2     // Catch:{ Exception -> 0x0014 }
            goto L_0x00df
        L_0x00c2:
            r5.printStackTrace()
            java.lang.String r0 = "ShopGlobalHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "initMiShopGlobalSDK exception "
            r1.append(r2)
            java.lang.String r5 = r5.getMessage()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r5)
        L_0x00df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shopglobal.ShopGlobalHelper.a(boolean):void");
    }

    public static boolean c() {
        try {
            LogUtilGrey.a("ShopGlobalHelper", "preFetchSidToken");
            if (!a((Context) SHApplication.getApplication()) || SHApplication.getStateNotifier().a() != 4) {
                return false;
            }
            ArrayList<DomainModel> arrayList = null;
            try {
                arrayList = ShopApp.b();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (arrayList != null) {
                if (!arrayList.isEmpty()) {
                    String str = "";
                    if (a((Context) SHApplication.getApplication())) {
                        str = LocaleHelper.b(b((Context) SHApplication.getApplication()));
                    }
                    LogUtilGrey.a("ShopGlobalHelper", "preFetchSidToken found sid=" + str);
                    if (TextUtils.isEmpty(str)) {
                        return false;
                    }
                    return c(str);
                }
            }
            return false;
        } catch (Exception e2) {
            e2.getMessage();
            LogUtilGrey.a("ShopGlobalHelper", "preFetchSidToken exception " + e2.getMessage());
            return false;
        }
    }

    public static void a(String str, final ExtendedAuthToken extendedAuthToken) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("setLoginData sid=");
            sb.append(str);
            sb.append(",");
            sb.append(extendedAuthToken == null ? null : extendedAuthToken.toPlain());
            LogUtilGrey.a("ShopGlobalHelper", sb.toString());
            ShopSdkInitParamGroup.Builder a2 = new ShopSdkInitParamGroup.Builder("mihome_sdk", a((Context) SHApplication.getApplication()) ? b((Context) SHApplication.getApplication()) : "").a(str).c(a((Context) SHApplication.getApplication()) ? b((Context) SHApplication.getApplication()) : "").a(extendedAuthToken);
            if (SHApplication.getStateNotifier().a() == 4) {
                a2.b(CoreApi.a().s());
            } else {
                a2.b("");
            }
            final ShopSdkInitParamGroup a3 = a2.a();
            LogUtilGrey.a("MijiaShopApp", "ShopApp.paramGroup write " + a3);
            ShopApp.h = a3;
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    LogUtilGrey.a("MijiaShopApp", "ShopApp.setLoginData paramGroup");
                    ShopApp.a(CoreApi.a().v(), a3.b, extendedAuthToken);
                }
            });
        } catch (Exception e) {
            e.getMessage();
            LogUtilGrey.a("ShopGlobalHelper", "setLoginData exception " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public static boolean c(final String str) {
        try {
            LogUtilGrey.a("ShopGlobalHelper", "refreshToken " + str);
            if (SHApplication.getStateNotifier().a() != 4) {
                return false;
            }
            LoginManager.a().a(str, new AsyncCallback<MiServiceTokenInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(MiServiceTokenInfo miServiceTokenInfo) {
                    if (GlobalSetting.q) {
                        ToastUtil.a((CharSequence) "global shop token onSuccess");
                    }
                    if (GlobalSetting.u) {
                        Log.d("ShopGlobalFragment", "global shop token onSuccess");
                    }
                    ShopGlobalHelper.a(str, ExtendedAuthToken.build(miServiceTokenInfo.c, miServiceTokenInfo.d));
                }

                public void onFailure(Error error) {
                    if (GlobalSetting.q) {
                        ToastUtil.a((CharSequence) "global shop token fail:" + error);
                    }
                    if (GlobalSetting.u) {
                        Log.d("ShopGlobalFragment", "global shop token fail:" + error);
                    }
                    ShopGlobalHelper.a(str, (ExtendedAuthToken) null);
                }
            });
            return true;
        } catch (Exception e) {
            e.getMessage();
            LogUtilGrey.a("ShopGlobalHelper", "refreshToken exception " + e.getMessage());
            return false;
        }
    }
}
