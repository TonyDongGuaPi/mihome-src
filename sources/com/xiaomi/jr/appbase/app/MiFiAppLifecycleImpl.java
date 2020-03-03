package com.xiaomi.jr.appbase.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import cn.com.fmsh.communication.message.constants.Constants;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import com.facebook.infer.annotation.ThreadConfined;
import com.xiaomi.jr.QualityApi;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.IWebLoginProcessor;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.account.XiaomiServices;
import com.xiaomi.jr.agreement.AgreementCallback;
import com.xiaomi.jr.agreement.AgreementUpdateManager;
import com.xiaomi.jr.agreement.AgreementUpdateUtils;
import com.xiaomi.jr.antifraud.AntifraudManager;
import com.xiaomi.jr.antifraud.Tongdun;
import com.xiaomi.jr.antifraud.por.EventTracker;
import com.xiaomi.jr.appbase.ApplicationSpec;
import com.xiaomi.jr.appbase.CustomizedSnippets;
import com.xiaomi.jr.appbase.MiFiActivityChecker;
import com.xiaomi.jr.appbase.MiFiActivityManager;
import com.xiaomi.jr.appbase.MiFiPermissionDialogDelegate;
import com.xiaomi.jr.appbase.MiFiVerificationCallback;
import com.xiaomi.jr.appbase.R;
import com.xiaomi.jr.appbase.accounts.MiFiAccountManagerInitializer;
import com.xiaomi.jr.appbase.configuration.Configuration;
import com.xiaomi.jr.appbase.configuration.ConfigurationManager;
import com.xiaomi.jr.appbase.https.MiFiCertificatePinning;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.appbase.utils.WebUtils;
import com.xiaomi.jr.base.IAppDelegate;
import com.xiaomi.jr.common.AccountEnvironment;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.lifecycle.Interceptor;
import com.xiaomi.jr.common.lifecycle.InterceptorChain;
import com.xiaomi.jr.common.lifecycle.ObjectMonitor;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.DeviceInfoHelper;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiHostsUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.ReflectUtil;
import com.xiaomi.jr.common.utils.ThreadUtils;
import com.xiaomi.jr.feature.account.Account;
import com.xiaomi.jr.feature.antifraud.AntiFraud;
import com.xiaomi.jr.feature.codepay.CodePay;
import com.xiaomi.jr.feature.data.Data;
import com.xiaomi.jr.feature.feedback.Feedback;
import com.xiaomi.jr.feature.identity.Identity;
import com.xiaomi.jr.feature.navigator.Navigator;
import com.xiaomi.jr.feature.octopus.Octopus;
import com.xiaomi.jr.feature.photo.Photo;
import com.xiaomi.jr.feature.reload.Reload;
import com.xiaomi.jr.feature.security.Security;
import com.xiaomi.jr.feature.stats.Stats;
import com.xiaomi.jr.feature.system.System;
import com.xiaomi.jr.feature.ui.UI;
import com.xiaomi.jr.feature.verification.Verification;
import com.xiaomi.jr.feature.voice.Voice;
import com.xiaomi.jr.http.BasicParamsInterceptor;
import com.xiaomi.jr.http.HttpManager;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.http.MifiParamsSignInterceptor;
import com.xiaomi.jr.http.NetworkStatusReceiver;
import com.xiaomi.jr.http.PostHintInterceptor;
import com.xiaomi.jr.http.SimpleHttpRequest;
import com.xiaomi.jr.http.WebHttpManager;
import com.xiaomi.jr.http.netopt.NetworkDiagnosis;
import com.xiaomi.jr.hybrid.FeatureConfigManager;
import com.xiaomi.jr.hybrid.FeatureManager;
import com.xiaomi.jr.mipay.codepay.CodePayManager;
import com.xiaomi.jr.mipay.common.MipayManager;
import com.xiaomi.jr.mipay.common.http.MipayCertificatePinning;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.stats.HttpRequester;
import com.xiaomi.jr.stats.StatUtils;
import com.xiaomi.jr.verification.VerificationUserEnvironment;
import com.xiaomi.jr.verification.WeBankWebViewConfig;
import com.xiaomi.jr.web.WebManager;
import com.xiaomi.jr.web.staticresource.StaticResourceUtils;
import com.xiaomi.jr.web.webkit.WebViewConfig;
import com.xiaomi.jr.web.webkit.XiaomiWebLoginProcessor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Response;

public class MiFiAppLifecycleImpl {
    private static final String b = "MiFiAppLifecycle";

    /* renamed from: a  reason: collision with root package name */
    protected Application f1397a;
    private boolean c;
    private NetworkStatusReceiver d = new NetworkStatusReceiver();

    public MiFiAppLifecycleImpl(Application application) {
        this.f1397a = application;
    }

    public void a() {
        g();
    }

    public void b() {
        if (!this.c) {
            d();
            this.c = true;
        }
    }

    public void c() {
        MifiLog.a(this.f1397a);
        try {
            ReflectUtil.a(Class.forName("com.xiaomi.jr.ApplicationConfigurator"), "config", (Class<?>[]) null, (Object) null, new Object[0]);
            DeviceInfoHelper.a((String) CustomizedSnippets.a(CustomizedSnippets.f1388a, this.f1397a));
            AntifraudManager.a().a((Context) this.f1397a, ApplicationSpec.f1387a);
            a(this.f1397a);
            f();
            ObjectMonitor.a(this.f1397a);
            ActivityChecker.a((Class<? extends ActivityChecker.Checker>) MiFiActivityChecker.class);
            PermissionManager.a(new MiFiPermissionDialogDelegate());
            Configuration a2 = ConfigurationManager.a((Context) this.f1397a).a();
            if (a2 != null) {
                StaticResourceUtils.a((Map<String, String>) a2.f1401a);
            }
            MiFiAccountManagerInitializer.a(this.f1397a);
            XiaomiAccountManager.a().a((IWebLoginProcessor) new XiaomiWebLoginProcessor());
            WebUtils.a();
            MifiHttpManager.a(new HttpManager.Builder(this.f1397a).a(AppConstants.n).a((Interceptor) new BasicParamsInterceptor(true) {
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    a(WebUtils.a((Context) MiFiAppLifecycleImpl.this.f1397a));
                    return super.intercept(chain);
                }
            }).a((Interceptor) new PostHintInterceptor(this.f1397a)).a((Interceptor) new MifiParamsSignInterceptor()).a(MiFiCertificatePinning.f1404a, MiFiCertificatePinning.d).a());
            QualityMonitor.a((QualityApi) MifiHttpManager.a().a(QualityApi.class), true ^ ((Boolean) CustomizedSnippets.a(CustomizedSnippets.k, this.f1397a)).booleanValue());
            WebHttpManager.a(new WebHttpManager.Builder(this.f1397a).a(MiFiCertificatePinning.f1404a, MiFiCertificatePinning.d).a());
            MipayManager.a(this.f1397a);
            HashMap hashMap = new HashMap();
            hashMap.put(MiFiCertificatePinning.f1404a, MiFiCertificatePinning.d);
            hashMap.put(MipayCertificatePinning.f1449a, MipayCertificatePinning.b);
            SimpleHttpRequest.a((Map<String, String[]>) hashMap);
            NetworkDiagnosis.a((Context) this.f1397a, Uri.parse(AppConstants.n).getHost());
            h();
            AgreementUpdateUtils.a(((Boolean) CustomizedSnippets.a(CustomizedSnippets.l, new Object[0])).booleanValue());
        } catch (Exception e) {
            MifiLog.e(b, "Configure application failed.", e);
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        int i = CustomizedSnippets.f1388a;
        Object[] objArr = {this.f1397a};
        StatUtils.a((Context) this.f1397a, ApplicationSpec.f1387a, ApplicationSpec.b, (String) CustomizedSnippets.a(i, objArr), (HttpRequester) $$Lambda$MiFiAppLifecycleImpl$kk18lHYGpEwv0TNsvPd7fjttuK4.INSTANCE);
        VerificationUserEnvironment.a(MiFiVerificationCallback.class);
        CodePayManager.a(this.f1397a, this.f1397a.getString(R.string.codepay_shortcut_name), R.drawable.mipay_code_pay, "mifinance");
        EventTracker.a().a((Context) this.f1397a);
        AgreementUpdateManager.a().a((Context) this.f1397a, (IAppDelegate) MiFiAppDelegate.a(), (AgreementCallback) new AgreementCallback() {
            public void a() {
            }

            public void b() {
                MiFiAppController.a().c();
            }
        });
        AgreementUpdateManager.a().a(AppConstants.x, AppConstants.y);
        if (Constants.f1410a) {
            AgreementUpdateManager.a().f();
        }
        this.f1397a.registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        ThreadUtils.b(new Runnable() {
            public final void run() {
                MiFiAppLifecycleImpl.this.i();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void i() {
        Tongdun.a(this.f1397a);
        OctopusManager.b().a(this.f1397a, AccountEnvironment.f1407a ? AppConstants.c : AppConstants.f1405a, AccountEnvironment.f1407a ? AppConstants.d : AppConstants.b);
    }

    public void e() {
        MiFiActivityManager.a().b();
        EventTracker.a().c();
        FileUtils.a(this.f1397a.getFileStreamPath(Constants.k));
        if (this.c) {
            this.f1397a.unregisterReceiver(this.d);
        }
        MiFiAccountManagerInitializer.a();
        MifiLog.a();
    }

    private void f() {
        InterceptorChain.a().a(Interceptor.Stage.CREATE, $$Lambda$MiFiAppLifecycleImpl$39yH4qIDVWUOZ7maBW8PGAgHWrQ.INSTANCE).a(Interceptor.Stage.DESTROY, $$Lambda$MiFiAppLifecycleImpl$JzAlzqe6dK5tskDVieTM3I27pUI.INSTANCE);
        this.f1397a.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
                a(Interceptor.Stage.CREATE, activity, bundle);
            }

            public void onActivityStarted(Activity activity) {
                a(Interceptor.Stage.START, activity, (Bundle) null);
            }

            public void onActivityResumed(Activity activity) {
                a(Interceptor.Stage.RESUME, activity, (Bundle) null);
            }

            public void onActivityPaused(Activity activity) {
                a(Interceptor.Stage.PAUSE, activity, (Bundle) null);
            }

            public void onActivityStopped(Activity activity) {
                a(Interceptor.Stage.STOP, activity, (Bundle) null);
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                a(Interceptor.Stage.SAVE, activity, bundle);
            }

            public void onActivityDestroyed(Activity activity) {
                a(Interceptor.Stage.DESTROY, activity, (Bundle) null);
            }

            private void a(Interceptor.Stage stage, Activity activity, Bundle bundle) {
                ArrayList<WeakReference<com.xiaomi.jr.common.lifecycle.Interceptor>> a2 = InterceptorChain.a().a(stage);
                if (a2 != null) {
                    Iterator<WeakReference<com.xiaomi.jr.common.lifecycle.Interceptor>> it = a2.iterator();
                    while (it.hasNext()) {
                        WeakReference next = it.next();
                        if (next != null && next.get() != null && ((com.xiaomi.jr.common.lifecycle.Interceptor) next.get()).process(activity, bundle)) {
                            return;
                        }
                    }
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00f1 A[Catch:{ Exception -> 0x0168 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f3 A[Catch:{ Exception -> 0x0168 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x011f A[Catch:{ Exception -> 0x0168 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0144 A[Catch:{ Exception -> 0x0168 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() {
        /*
            r13 = this;
            r0 = 0
            com.xiaomi.jr.common.utils.MifiLog.f1417a = r0
            java.lang.String r1 = "RpNktUEoNBDOWZXaquWclt+m6Gs="
            android.app.Application r2 = r13.f1397a     // Catch:{ Exception -> 0x016d }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Exception -> 0x016d }
            java.lang.String r3 = "com.xiaomi.jr.tool"
            r4 = 64
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r4)     // Catch:{ Exception -> 0x016d }
            r3 = 1
            if (r2 == 0) goto L_0x0043
            android.content.pm.Signature[] r4 = r2.signatures     // Catch:{ Exception -> 0x016d }
            if (r4 == 0) goto L_0x0043
            java.lang.String r4 = "SHA"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch:{ Exception -> 0x016d }
            android.content.pm.Signature[] r2 = r2.signatures     // Catch:{ Exception -> 0x016d }
            int r5 = r2.length     // Catch:{ Exception -> 0x016d }
            r6 = 0
            r7 = 0
        L_0x0025:
            if (r6 >= r5) goto L_0x0044
            r8 = r2[r6]     // Catch:{ Exception -> 0x016d }
            byte[] r8 = r8.toByteArray()     // Catch:{ Exception -> 0x016d }
            r4.update(r8)     // Catch:{ Exception -> 0x016d }
            byte[] r8 = r4.digest()     // Catch:{ Exception -> 0x016d }
            r9 = 2
            java.lang.String r8 = android.util.Base64.encodeToString(r8, r9)     // Catch:{ Exception -> 0x016d }
            boolean r8 = android.text.TextUtils.equals(r8, r1)     // Catch:{ Exception -> 0x016d }
            if (r8 == 0) goto L_0x0040
            r7 = 1
        L_0x0040:
            int r6 = r6 + 1
            goto L_0x0025
        L_0x0043:
            r7 = 0
        L_0x0044:
            if (r7 != 0) goto L_0x0047
            return
        L_0x0047:
            android.content.IntentFilter r1 = new android.content.IntentFilter
            java.lang.String r2 = "com.xiaomi.jr.TOOL_CONFIGURATION_CHANGED"
            r1.<init>(r2)
            android.app.Application r2 = r13.f1397a
            com.xiaomi.jr.appbase.ToolConfigurationChangedReceiver r4 = new com.xiaomi.jr.appbase.ToolConfigurationChangedReceiver
            r4.<init>()
            r2.registerReceiver(r4, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "content://com.xiaomi.jr.tool/configuration?app="
            r1.append(r2)
            android.app.Application r2 = r13.f1397a
            java.lang.String r2 = r2.getPackageName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r5 = android.net.Uri.parse(r1)
            android.app.Application r1 = r13.f1397a     // Catch:{ Exception -> 0x0168 }
            android.content.ContentResolver r4 = r1.getContentResolver()     // Catch:{ Exception -> 0x0168 }
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0168 }
            if (r1 != 0) goto L_0x0084
            return
        L_0x0084:
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x0168 }
            if (r2 == 0) goto L_0x0164
            java.lang.String r2 = "account_env"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ Exception -> 0x0168 }
            int r2 = r1.getInt(r2)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r4 = "service_mapping"
            int r4 = r1.getColumnIndex(r4)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r4 = r1.getString(r4)     // Catch:{ Exception -> 0x0168 }
            boolean r5 = com.xiaomi.jr.common.utils.MifiLog.f1417a     // Catch:{ Exception -> 0x0168 }
            if (r5 != 0) goto L_0x00b8
            java.lang.String r5 = "mifi_log_enabled"
            int r5 = r1.getColumnIndex(r5)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r5 = r1.getString(r5)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r6 = "true"
            boolean r5 = android.text.TextUtils.equals(r5, r6)     // Catch:{ Exception -> 0x0168 }
            if (r5 == 0) goto L_0x00b6
            goto L_0x00b8
        L_0x00b6:
            r5 = 0
            goto L_0x00b9
        L_0x00b8:
            r5 = 1
        L_0x00b9:
            com.xiaomi.jr.common.utils.MifiLog.f1417a = r5     // Catch:{ Exception -> 0x0168 }
            java.lang.String r5 = "liveness_debug_enabled"
            int r5 = r1.getColumnIndex(r5)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r5 = r1.getString(r5)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r6 = "true"
            boolean r5 = android.text.TextUtils.equals(r5, r6)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r6 = "static_resource_disabled"
            int r6 = r1.getColumnIndex(r6)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r6 = r1.getString(r6)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r7 = "true"
            boolean r6 = android.text.TextUtils.equals(r6, r7)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r7 = "cert_pinning_disabled"
            int r7 = r1.getColumnIndex(r7)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r7 = r1.getString(r7)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r8 = "true"
            boolean r7 = android.text.TextUtils.equals(r7, r8)     // Catch:{ Exception -> 0x0168 }
            if (r2 == 0) goto L_0x00f3
            r8 = 1
            goto L_0x00f4
        L_0x00f3:
            r8 = 0
        L_0x00f4:
            android.app.Application r9 = r13.f1397a     // Catch:{ Exception -> 0x0168 }
            java.lang.String r10 = "staging_sp"
            java.lang.String r11 = "is_staging"
            boolean r9 = com.xiaomi.jr.common.utils.PreferenceUtils.e(r9, r10, r11)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r10 = "TestTool"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0168 }
            r11.<init>()     // Catch:{ Exception -> 0x0168 }
            java.lang.String r12 = "oldStaging: "
            r11.append(r12)     // Catch:{ Exception -> 0x0168 }
            r11.append(r9)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r12 = ", newStaging: "
            r11.append(r12)     // Catch:{ Exception -> 0x0168 }
            r11.append(r8)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x0168 }
            android.util.Log.i(r10, r11)     // Catch:{ Exception -> 0x0168 }
            if (r8 == r9) goto L_0x0142
            android.app.Application r9 = r13.f1397a     // Catch:{ Exception -> 0x0168 }
            java.io.File r9 = r9.getFilesDir()     // Catch:{ Exception -> 0x0168 }
            java.io.File r9 = r9.getParentFile()     // Catch:{ Exception -> 0x0168 }
            com.xiaomi.jr.common.utils.FileUtils.a((java.io.File) r9)     // Catch:{ Exception -> 0x0168 }
            android.app.Application r9 = r13.f1397a     // Catch:{ Exception -> 0x0168 }
            java.lang.String r10 = "staging_sp"
            android.content.SharedPreferences r9 = r9.getSharedPreferences(r10, r0)     // Catch:{ Exception -> 0x0168 }
            android.content.SharedPreferences$Editor r9 = r9.edit()     // Catch:{ Exception -> 0x0168 }
            java.lang.String r10 = "is_staging"
            android.content.SharedPreferences$Editor r9 = r9.putBoolean(r10, r8)     // Catch:{ Exception -> 0x0168 }
            r9.commit()     // Catch:{ Exception -> 0x0168 }
        L_0x0142:
            if (r2 != r3) goto L_0x0145
            r0 = 1
        L_0x0145:
            com.xiaomi.jr.common.AccountEnvironment.a(r8, r0)     // Catch:{ Exception -> 0x0168 }
            com.xiaomi.jr.common.utils.MifiHostsUtils.a(r4)     // Catch:{ Exception -> 0x0168 }
            com.xiaomi.jr.verification.livenessdetection.detector.Detector.f1454a = r5     // Catch:{ Exception -> 0x0168 }
            boolean r0 = com.xiaomi.jr.common.utils.MifiLog.f1417a     // Catch:{ Exception -> 0x0168 }
            com.xiaomi.jr.deeplink.DeeplinkUtils.DEBUG = r0     // Catch:{ Exception -> 0x0168 }
            boolean r0 = com.xiaomi.jr.common.utils.MifiLog.f1417a     // Catch:{ Exception -> 0x0168 }
            com.xiaomi.jr.hybrid.HybridUtils.f1442a = r0     // Catch:{ Exception -> 0x0168 }
            com.xiaomi.jr.web.staticresource.ResourceUpdateManager.f1457a = r6     // Catch:{ Exception -> 0x0168 }
            com.xiaomi.jr.http.certificate.CertificatePinning.f1434a = r7     // Catch:{ Exception -> 0x0168 }
            if (r7 == 0) goto L_0x0164
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0168 }
            r2 = 24
            if (r0 < r2) goto L_0x0164
            com.xiaomi.jr.http.certificate.CertificatePinning.a()     // Catch:{ Exception -> 0x0168 }
        L_0x0164:
            r1.close()     // Catch:{ Exception -> 0x0168 }
            goto L_0x016c
        L_0x0168:
            r0 = move-exception
            r0.printStackTrace()
        L_0x016c:
            return
        L_0x016d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.appbase.app.MiFiAppLifecycleImpl.g():void");
    }

    private void h() {
        HashMap hashMap = new HashMap();
        hashMap.put("Account", Account.class);
        hashMap.put("AntiFraud", AntiFraud.class);
        hashMap.put(Constants.XMLNode.XMLMessage.DATA, Data.class);
        hashMap.put("Navigator", Navigator.class);
        hashMap.put("Photo", Photo.class);
        hashMap.put("Reload", Reload.class);
        hashMap.put("Security", Security.class);
        hashMap.put("Voice", Voice.class);
        hashMap.put("Stats", Stats.class);
        hashMap.put("System", System.class);
        hashMap.put(ThreadConfined.UI, UI.class);
        hashMap.put("Verification", Verification.class);
        hashMap.put("Identity", Identity.class);
        hashMap.put("CodePay", CodePay.class);
        hashMap.put("Octopus", Octopus.class);
        hashMap.put("Feedback", Feedback.class);
        HashMap hashMap2 = new HashMap();
        for (String str : hashMap.keySet()) {
            hashMap2.put(str, ((Class) hashMap.get(str)).getName());
        }
        FeatureManager.a((Map<String, String>) hashMap2);
        HashMap hashMap3 = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add("*:*");
        hashMap3.put(".jr.mi.com", arrayList);
        hashMap3.put(".mifi.pt.xiaomi.com", arrayList);
        hashMap3.put(".mipay.com", arrayList);
        hashMap3.put(".pay.xiaomi.com", arrayList);
        hashMap3.put(".miinsurtech.com", arrayList);
        Configuration a2 = ConfigurationManager.a((Context) this.f1397a).a();
        if (!(a2 == null || a2.c == null)) {
            hashMap3.putAll(a2.c);
        }
        FeatureConfigManager.a((Map<String, List<String>>) hashMap3);
        WebManager.a((Collection<String>) hashMap3.keySet());
        WebManager.a((WebViewConfig) new WeBankWebViewConfig());
    }

    private void a(Context context) {
        Configuration a2 = ConfigurationManager.a(context).a();
        StringBuilder sb = new StringBuilder();
        sb.append("config: ");
        sb.append(a2 != null);
        sb.append(", service: ");
        sb.append(a2 != null ? a2.b : null);
        MifiLog.b("TestConfig", sb.toString());
        if (a2 != null && a2.b != null) {
            for (Configuration.Service next : a2.b) {
                MifiHostsUtils.ServiceConfig b2 = MifiHostsUtils.b(next.b);
                String str = next.b;
                String str2 = next.f1402a;
                if (b2 != null) {
                    str = b2.b;
                    str2 = b2.c;
                }
                XiaomiServices.a(str, str2, next.c);
            }
        }
    }
}
