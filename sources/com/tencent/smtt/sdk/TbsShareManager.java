package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import java.io.File;
import java.io.IOException;

public class TbsShareManager {

    /* renamed from: a  reason: collision with root package name */
    private static Context f9102a = null;
    private static boolean b = false;
    private static String c = null;
    private static String d = null;
    private static int e = 0;
    private static String f = null;
    private static boolean g = false;
    private static boolean h = false;
    private static String i = null;
    private static boolean j = false;
    private static boolean k = false;
    public static boolean mHasQueryed = false;

    static int a(Context context, boolean z) {
        b(context, z);
        return e;
    }

    static Context a(Context context, String str) {
        try {
            return context.createPackageContext(str, 2);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static String a() {
        return d;
    }

    static void a(Context context) {
        try {
            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(context);
            a(context, tbsLinuxToolsJni, am.a().q(context));
            tbsLinuxToolsJni.a(am.a().r(context).getAbsolutePath(), "755");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void a(Context context, TbsLinuxToolsJni tbsLinuxToolsJni, File file) {
        String absolutePath;
        String str;
        if (file != null && file.exists() && file.isDirectory()) {
            tbsLinuxToolsJni.a(file.getAbsolutePath(), "755");
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    if (file2.getAbsolutePath().indexOf(".so") > 0) {
                        absolutePath = file2.getAbsolutePath();
                        str = "755";
                    } else {
                        absolutePath = file2.getAbsolutePath();
                        str = "644";
                    }
                    tbsLinuxToolsJni.a(absolutePath, str);
                } else if (file2.isDirectory()) {
                    a(context, tbsLinuxToolsJni, file2);
                } else {
                    TbsLog.e("TbsShareManager", "unknown file type.", true);
                }
            }
        }
    }

    private static File b(Context context, String str) {
        File r = am.a().r(context);
        if (r == null) {
            return null;
        }
        File file = new File(r, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static void b(Context context) {
        try {
            a(context, new TbsLinuxToolsJni(context), am.a().p(context));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static boolean b(Context context, boolean z) {
        if (i(context)) {
            return true;
        }
        if (!z) {
            return false;
        }
        QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailable forceSysWebViewInner!");
        return false;
    }

    static String c(Context context) {
        j(context);
        return d;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:5|6|7|(1:9)|10|11|12|13|14|15|16|49) */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0063 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b A[SYNTHETIC, Splitter:B:30:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0087 A[SYNTHETIC, Splitter:B:39:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x008c A[SYNTHETIC, Splitter:B:43:0x008c] */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.Context r6, boolean r7) {
        /*
            r0 = 0
            java.lang.String r1 = "core_info"
            java.io.File r1 = b((android.content.Context) r6, (java.lang.String) r1)     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            if (r1 != 0) goto L_0x000a
            return
        L_0x000a:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0074, all -> 0x0071 }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            r2.<init>()     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            r2.load(r3)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.lang.String r4 = "core_disabled"
            r5 = 0
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            r2.setProperty(r4, r5)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            if (r7 == 0) goto L_0x0053
            com.tencent.smtt.sdk.am r7 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.io.File r7 = r7.q(r6)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            android.content.Context r4 = r6.getApplicationContext()     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            int r6 = com.tencent.smtt.utils.b.b(r6)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.lang.String r5 = "core_packagename"
            r2.setProperty(r5, r4)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.lang.String r4 = "core_path"
            r2.setProperty(r4, r7)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.lang.String r7 = "app_version"
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            r2.setProperty(r7, r6)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
        L_0x0053:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            r6.<init>(r1)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            r7.<init>(r6)     // Catch:{ Throwable -> 0x006d, all -> 0x006b }
            r2.store(r7, r0)     // Catch:{ Throwable -> 0x0069, all -> 0x0067 }
            r3.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            r7.close()     // Catch:{ Exception -> 0x0081 }
            goto L_0x0081
        L_0x0067:
            r6 = move-exception
            goto L_0x0084
        L_0x0069:
            r6 = move-exception
            goto L_0x006f
        L_0x006b:
            r6 = move-exception
            goto L_0x0085
        L_0x006d:
            r6 = move-exception
            r7 = r0
        L_0x006f:
            r0 = r3
            goto L_0x0076
        L_0x0071:
            r6 = move-exception
            r3 = r0
            goto L_0x0085
        L_0x0074:
            r6 = move-exception
            r7 = r0
        L_0x0076:
            r6.printStackTrace()     // Catch:{ all -> 0x0082 }
            if (r0 == 0) goto L_0x007e
            r0.close()     // Catch:{ Exception -> 0x007e }
        L_0x007e:
            if (r7 == 0) goto L_0x0081
            goto L_0x0063
        L_0x0081:
            return
        L_0x0082:
            r6 = move-exception
            r3 = r0
        L_0x0084:
            r0 = r7
        L_0x0085:
            if (r3 == 0) goto L_0x008a
            r3.close()     // Catch:{ Exception -> 0x008a }
        L_0x008a:
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch:{ Exception -> 0x008f }
        L_0x008f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.c(android.content.Context, boolean):void");
    }

    static int d(Context context) {
        return a(context, true);
    }

    static Context e(Context context) {
        j(context);
        if (f == null) {
            return null;
        }
        Context a2 = a(context, f);
        if (!am.a().f(a2)) {
            return null;
        }
        return a2;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0056 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0048 A[SYNTHETIC, Splitter:B:31:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0050 A[SYNTHETIC, Splitter:B:38:0x0050] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized java.lang.String f(android.content.Context r6) {
        /*
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r0 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r0)
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r6 = b((android.content.Context) r6, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0041, all -> 0x003c }
            if (r6 != 0) goto L_0x000e
            monitor-exit(r0)
            return r1
        L_0x000e:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0041, all -> 0x003c }
            r2.<init>(r6)     // Catch:{ Throwable -> 0x0041, all -> 0x003c }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0041, all -> 0x003c }
            r6.<init>(r2)     // Catch:{ Throwable -> 0x0041, all -> 0x003c }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Throwable -> 0x003a }
            r2.<init>()     // Catch:{ Throwable -> 0x003a }
            r2.load(r6)     // Catch:{ Throwable -> 0x003a }
            java.lang.String r3 = "core_packagename"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.getProperty(r3, r4)     // Catch:{ Throwable -> 0x003a }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x003a }
            if (r3 != 0) goto L_0x0035
            r6.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            monitor-exit(r0)
            return r2
        L_0x0035:
            r6.close()     // Catch:{ Exception -> 0x0038 }
        L_0x0038:
            monitor-exit(r0)
            return r1
        L_0x003a:
            r2 = move-exception
            goto L_0x0043
        L_0x003c:
            r6 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x004e
        L_0x0041:
            r2 = move-exception
            r6 = r1
        L_0x0043:
            r2.printStackTrace()     // Catch:{ all -> 0x004d }
            if (r6 == 0) goto L_0x004b
            r6.close()     // Catch:{ Exception -> 0x004b }
        L_0x004b:
            monitor-exit(r0)
            return r1
        L_0x004d:
            r1 = move-exception
        L_0x004e:
            if (r6 == 0) goto L_0x0056
            r6.close()     // Catch:{ Exception -> 0x0056 }
            goto L_0x0056
        L_0x0054:
            r6 = move-exception
            goto L_0x0057
        L_0x0056:
            throw r1     // Catch:{ all -> 0x0054 }
        L_0x0057:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.f(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int findCoreForThirdPartyApp(android.content.Context r6) {
        /*
            n(r6)
            java.lang.String r0 = "TbsShareManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "core_info mAvailableCoreVersion is "
            r1.append(r2)
            int r2 = e
            r1.append(r2)
            java.lang.String r2 = " mAvailableCorePath is "
            r1.append(r2)
            java.lang.String r2 = d
            r1.append(r2)
            java.lang.String r2 = " mSrcPackageName is "
            r1.append(r2)
            java.lang.String r2 = f
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            java.lang.String r0 = f
            if (r0 != 0) goto L_0x003a
            java.lang.String r0 = "TbsShareManager"
            java.lang.String r1 = "mSrcPackageName is null !!!"
            com.tencent.smtt.utils.TbsLog.e(r0, r1)
        L_0x003a:
            java.lang.String r0 = f
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x0089
            java.lang.String r0 = f
            java.lang.String r3 = "AppDefined"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0089
            int r0 = e
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            java.lang.String r4 = c
            int r3 = r3.a((java.lang.String) r4)
            if (r0 == r3) goto L_0x00a0
            e = r1
            d = r2
            f = r2
            java.lang.String r0 = "TbsShareManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "check AppDefined core is error src is "
            r3.append(r4)
            int r4 = e
            r3.append(r4)
            java.lang.String r4 = " dest is "
            r3.append(r4)
            com.tencent.smtt.sdk.am r4 = com.tencent.smtt.sdk.am.a()
            java.lang.String r5 = c
            int r4 = r4.a((java.lang.String) r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
        L_0x0085:
            com.tencent.smtt.utils.TbsLog.i(r0, r3)
            goto L_0x00a0
        L_0x0089:
            boolean r0 = k(r6)
            if (r0 != 0) goto L_0x00a0
            boolean r0 = l(r6)
            if (r0 != 0) goto L_0x00a0
            e = r1
            d = r2
            f = r2
            java.lang.String r0 = "TbsShareManager"
            java.lang.String r3 = "core_info error checkCoreInfo is false and checkCoreInOthers is false "
            goto L_0x0085
        L_0x00a0:
            int r0 = e
            if (r0 <= 0) goto L_0x00dd
            java.lang.String r0 = "com.tencent.android.qqdownloader"
            java.lang.String r3 = "com.jd.jrapp"
            android.content.pm.ApplicationInfo r4 = r6.getApplicationInfo()
            java.lang.String r5 = r4.packageName
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x00bf
            java.lang.String r0 = r4.packageName
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x00bd
            goto L_0x00bf
        L_0x00bd:
            r0 = 0
            goto L_0x00c0
        L_0x00bf:
            r0 = 1
        L_0x00c0:
            if (r0 != 0) goto L_0x00c9
            int r0 = e
            boolean r6 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r6, (int) r0)
            goto L_0x00ca
        L_0x00c9:
            r6 = 0
        L_0x00ca:
            if (r6 != 0) goto L_0x00d0
            boolean r6 = g
            if (r6 == 0) goto L_0x00dd
        L_0x00d0:
            e = r1
            d = r2
            f = r2
            java.lang.String r6 = "TbsShareManager"
            java.lang.String r0 = "core_info error QbSdk.isX5Disabled "
            com.tencent.smtt.utils.TbsLog.i(r6, r0)
        L_0x00dd:
            int r6 = e
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.findCoreForThirdPartyApp(android.content.Context):int");
    }

    public static boolean forceLoadX5FromTBSDemo(Context context) {
        int sharedTbsCoreVersion;
        if (context == null || am.a().a(context, (File[]) null) || (sharedTbsCoreVersion = getSharedTbsCoreVersion(context, TbsConfig.APP_DEMO)) <= 0) {
            return false;
        }
        writeProperties(context, Integer.toString(sharedTbsCoreVersion), TbsConfig.APP_DEMO, am.a().q(a(context, TbsConfig.APP_DEMO)).getAbsolutePath(), "1");
        return true;
    }

    public static void forceToLoadX5ForThirdApp(Context context, boolean z) {
        File r;
        int a2;
        try {
            if (!isThirdPartyApp(context) || QbSdk.getOnlyDownload() || (r = am.a().r(context)) == null) {
                return;
            }
            if (z && new File(r, "core_info").exists()) {
                return;
            }
            if (c == null || (a2 = am.a().a(c)) <= 0) {
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #1");
                int h2 = h(context);
                int i2 = am.a().i(context);
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromConfig is " + h2);
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromCoreShare is " + i2);
                String[] coreProviderAppList = getCoreProviderAppList();
                for (String str : coreProviderAppList) {
                    int coreShareDecoupleCoreVersion = getCoreShareDecoupleCoreVersion(context, str);
                    if (coreShareDecoupleCoreVersion >= h2) {
                        if (coreShareDecoupleCoreVersion >= i2) {
                            if (coreShareDecoupleCoreVersion > 0) {
                                d = am.a().c(context, a(context, str)).getAbsolutePath();
                                f = str;
                                e = coreShareDecoupleCoreVersion;
                                if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                                    int b2 = b.b(context);
                                    TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #2");
                                    writeProperties(context, Integer.toString(e), f, d, Integer.toString(b2));
                                    return;
                                }
                                e = 0;
                                d = null;
                                f = null;
                            } else {
                                continue;
                            }
                        }
                    }
                }
                for (String str2 : coreProviderAppList) {
                    int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str2);
                    if (sharedTbsCoreVersion >= h2) {
                        if (sharedTbsCoreVersion >= i2) {
                            if (sharedTbsCoreVersion > 0) {
                                d = am.a().b(context, a(context, str2)).getAbsolutePath();
                                f = str2;
                                e = sharedTbsCoreVersion;
                                if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                                    writeProperties(context, Integer.toString(e), f, d, Integer.toString(b.b(context)));
                                    return;
                                }
                                e = 0;
                                d = null;
                                f = null;
                            } else {
                                continue;
                            }
                        }
                    }
                }
                return;
            }
            d = c;
            f = "AppDefined";
            e = a2;
            writeProperties(context, Integer.toString(e), f, d, Integer.toString(1));
        } catch (Exception unused) {
        }
    }

    static String g(Context context) {
        try {
            n(context);
            if (d != null) {
                if (!TextUtils.isEmpty(d)) {
                    return d + File.separator + "res.apk";
                }
            }
            return null;
        } catch (Throwable th) {
            Log.e("", "getTbsResourcesPath exception: " + Log.getStackTraceString(th));
            return null;
        }
    }

    public static boolean getCoreDisabled() {
        return g;
    }

    public static boolean getCoreFormOwn() {
        return j;
    }

    public static String[] getCoreProviderAppList() {
        return new String[]{TbsConfig.APP_DEMO, "com.tencent.mm", TbsConfig.APP_QQ, TbsConfig.APP_QZONE};
    }

    public static int getCoreShareDecoupleCoreVersion(Context context, String str) {
        Context a2 = a(context, str);
        if (a2 != null) {
            return am.a().h(a2);
        }
        return 0;
    }

    public static String getHostCorePathAppDefined() {
        return c;
    }

    public static long getHostCoreVersions(Context context) {
        long sharedTbsCoreVersion;
        long sharedTbsCoreVersion2;
        long j2;
        long j3 = 0;
        for (String str : getCoreProviderAppList()) {
            if (str.equalsIgnoreCase("com.tencent.mm")) {
                sharedTbsCoreVersion2 = (long) getSharedTbsCoreVersion(context, str);
                j2 = 10000000000L;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QQ)) {
                sharedTbsCoreVersion2 = (long) getSharedTbsCoreVersion(context, str);
                j2 = 100000;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QZONE)) {
                sharedTbsCoreVersion = (long) getSharedTbsCoreVersion(context, str);
                j3 += sharedTbsCoreVersion;
            }
            sharedTbsCoreVersion = sharedTbsCoreVersion2 * j2;
            j3 += sharedTbsCoreVersion;
        }
        return j3;
    }

    public static int getSharedTbsCoreVersion(Context context, String str) {
        Context a2 = a(context, str);
        if (a2 != null) {
            return am.a().i(a2);
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0079 A[SYNTHETIC, Splitter:B:43:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x008e A[SYNTHETIC, Splitter:B:54:0x008e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized int h(android.content.Context r6) {
        /*
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r0 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r0)
            java.lang.String r1 = "TbsShareManager"
            java.lang.String r2 = "readCoreVersionFromConfig #1"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)     // Catch:{ all -> 0x0097 }
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r6 = b((android.content.Context) r6, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0070, all -> 0x006b }
            r2 = 0
            if (r6 != 0) goto L_0x001d
            java.lang.String r6 = "TbsShareManager"
            java.lang.String r3 = "readCoreVersionFromConfig #2"
            com.tencent.smtt.utils.TbsLog.i(r6, r3)     // Catch:{ Throwable -> 0x0070, all -> 0x006b }
            monitor-exit(r0)
            return r2
        L_0x001d:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0070, all -> 0x006b }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x0070, all -> 0x006b }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0070, all -> 0x006b }
            r6.<init>(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x006b }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x0069 }
            r1.<init>()     // Catch:{ Throwable -> 0x0069 }
            r1.load(r6)     // Catch:{ Throwable -> 0x0069 }
            java.lang.String r3 = "core_version"
            java.lang.String r4 = ""
            java.lang.String r1 = r1.getProperty(r3, r4)     // Catch:{ Throwable -> 0x0069 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ Throwable -> 0x0069 }
            if (r3 != 0) goto L_0x0058
            java.lang.String r3 = "TbsShareManager"
            java.lang.String r4 = "readCoreVersionFromConfig #3"
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ Throwable -> 0x0069 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x0069 }
            int r1 = java.lang.Math.max(r1, r2)     // Catch:{ Throwable -> 0x0069 }
            r6.close()     // Catch:{ Exception -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0097 }
        L_0x0056:
            monitor-exit(r0)
            return r1
        L_0x0058:
            java.lang.String r1 = "TbsShareManager"
            java.lang.String r3 = "readCoreVersionFromConfig #4"
            com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch:{ Throwable -> 0x0069 }
            r6.close()     // Catch:{ Exception -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0097 }
        L_0x0067:
            monitor-exit(r0)
            return r2
        L_0x0069:
            r1 = move-exception
            goto L_0x0074
        L_0x006b:
            r6 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x008c
        L_0x0070:
            r6 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
        L_0x0074:
            r1.printStackTrace()     // Catch:{ all -> 0x008b }
            if (r6 == 0) goto L_0x0081
            r6.close()     // Catch:{ Exception -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0097 }
        L_0x0081:
            java.lang.String r6 = "TbsShareManager"
            java.lang.String r1 = "readCoreVersionFromConfig #5"
            com.tencent.smtt.utils.TbsLog.i(r6, r1)     // Catch:{ all -> 0x0097 }
            r6 = -2
            monitor-exit(r0)
            return r6
        L_0x008b:
            r1 = move-exception
        L_0x008c:
            if (r6 == 0) goto L_0x0096
            r6.close()     // Catch:{ Exception -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0097 }
        L_0x0096:
            throw r1     // Catch:{ all -> 0x0097 }
        L_0x0097:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.h(android.content.Context):int");
    }

    static boolean i(Context context) {
        try {
            if (e == 0) {
                findCoreForThirdPartyApp(context);
            }
            if (e == 0) {
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE, (String) null, new Object[0]);
                return false;
            }
            if (c == null) {
                if (e != 0 && getSharedTbsCoreVersion(context, f) == e) {
                    return true;
                }
            } else if (e != 0 && am.a().a(c) == e) {
                return true;
            }
            if (l(context)) {
                return true;
            }
            TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
            instance.a(context, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, new Throwable("mAvailableCoreVersion=" + e + "; mSrcPackageName=" + f + "; getSharedTbsCoreVersion(ctx, mSrcPackageName) is " + getSharedTbsCoreVersion(context, f) + "; getHostCoreVersions is " + getHostCoreVersions(context)));
            d = null;
            e = 0;
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, (String) null, new Object[0]);
            QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailableInner forceSysWebViewInner!");
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE, (String) null, new Object[0]);
            return false;
        }
    }

    public static boolean isThirdPartyApp(Context context) {
        try {
            if (f9102a != null && f9102a.equals(context.getApplicationContext())) {
                return b;
            }
            f9102a = context.getApplicationContext();
            String packageName = f9102a.getPackageName();
            for (String equals : getCoreProviderAppList()) {
                if (packageName.equals(equals)) {
                    b = false;
                    return false;
                }
            }
            b = true;
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static boolean j(Context context) {
        return b(context, true);
    }

    private static boolean k(Context context) {
        if (f == null) {
            return false;
        }
        return e == getSharedTbsCoreVersion(context, f) || e == getCoreShareDecoupleCoreVersion(context, f);
    }

    private static boolean l(Context context) {
        if (QbSdk.getOnlyDownload()) {
            return false;
        }
        String[] coreProviderAppList = getCoreProviderAppList();
        for (String str : coreProviderAppList) {
            if (e > 0 && e == getSharedTbsCoreVersion(context, str)) {
                Context a2 = a(context, str);
                if (am.a().f(context)) {
                    d = am.a().b(context, a2).getAbsolutePath();
                    f = str;
                    return true;
                }
            }
        }
        for (String str2 : coreProviderAppList) {
            if (e > 0 && e == getCoreShareDecoupleCoreVersion(context, str2)) {
                Context a3 = a(context, str2);
                if (am.a().f(context)) {
                    d = am.a().c(context, a3).getAbsolutePath();
                    f = str2;
                    writeProperties(context, Integer.toString(e), f, d, Integer.toString(0));
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean m(Context context) {
        if (context == null) {
            return false;
        }
        writeProperties(context, Integer.toString(0), "", "", Integer.toString(0));
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00bc A[SYNTHETIC, Splitter:B:50:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c7 A[SYNTHETIC, Splitter:B:57:0x00c7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void n(android.content.Context r7) {
        /*
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r0 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r0)
            boolean r1 = k     // Catch:{ all -> 0x00d0 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)
            return
        L_0x0009:
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r7 = b((android.content.Context) r7, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ae }
            if (r7 != 0) goto L_0x0014
            monitor-exit(r0)
            return
        L_0x0014:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00b3, all -> 0x00ae }
            r2.<init>(r7)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ae }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00b3, all -> 0x00ae }
            r7.<init>(r2)     // Catch:{ Throwable -> 0x00b3, all -> 0x00ae }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x00ac }
            r1.<init>()     // Catch:{ Throwable -> 0x00ac }
            r1.load(r7)     // Catch:{ Throwable -> 0x00ac }
            java.lang.String r2 = "core_version"
            java.lang.String r3 = ""
            java.lang.String r2 = r1.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00ac }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00ac }
            r4 = 0
            if (r3 != 0) goto L_0x0041
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Throwable -> 0x00ac }
            int r2 = java.lang.Math.max(r2, r4)     // Catch:{ Throwable -> 0x00ac }
            e = r2     // Catch:{ Throwable -> 0x00ac }
        L_0x0041:
            java.lang.String r2 = "core_packagename"
            java.lang.String r3 = ""
            java.lang.String r2 = r1.getProperty(r2, r3)     // Catch:{ Throwable -> 0x00ac }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ Throwable -> 0x00ac }
            if (r3 != 0) goto L_0x0053
            f = r2     // Catch:{ Throwable -> 0x00ac }
        L_0x0053:
            java.lang.String r2 = f     // Catch:{ Throwable -> 0x00ac }
            r3 = 1
            if (r2 == 0) goto L_0x006f
            android.content.Context r2 = f9102a     // Catch:{ Throwable -> 0x00ac }
            if (r2 == 0) goto L_0x006f
            java.lang.String r2 = f     // Catch:{ Throwable -> 0x00ac }
            android.content.Context r5 = f9102a     // Catch:{ Throwable -> 0x00ac }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ Throwable -> 0x00ac }
            boolean r2 = r2.equals(r5)     // Catch:{ Throwable -> 0x00ac }
            if (r2 == 0) goto L_0x006d
            j = r3     // Catch:{ Throwable -> 0x00ac }
            goto L_0x006f
        L_0x006d:
            j = r4     // Catch:{ Throwable -> 0x00ac }
        L_0x006f:
            java.lang.String r2 = "core_path"
            java.lang.String r4 = ""
            java.lang.String r2 = r1.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ac }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ Throwable -> 0x00ac }
            if (r4 != 0) goto L_0x0081
            d = r2     // Catch:{ Throwable -> 0x00ac }
        L_0x0081:
            java.lang.String r2 = "app_version"
            java.lang.String r4 = ""
            java.lang.String r2 = r1.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ac }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ Throwable -> 0x00ac }
            if (r4 != 0) goto L_0x0093
            i = r2     // Catch:{ Throwable -> 0x00ac }
        L_0x0093:
            java.lang.String r2 = "core_disabled"
            java.lang.String r4 = "false"
            java.lang.String r1 = r1.getProperty(r2, r4)     // Catch:{ Throwable -> 0x00ac }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ Throwable -> 0x00ac }
            g = r1     // Catch:{ Throwable -> 0x00ac }
            k = r3     // Catch:{ Throwable -> 0x00ac }
            r7.close()     // Catch:{ Exception -> 0x00a7 }
            goto L_0x00c2
        L_0x00a7:
            r7 = move-exception
        L_0x00a8:
            r7.printStackTrace()     // Catch:{ all -> 0x00d0 }
            goto L_0x00c2
        L_0x00ac:
            r1 = move-exception
            goto L_0x00b7
        L_0x00ae:
            r7 = move-exception
            r6 = r1
            r1 = r7
            r7 = r6
            goto L_0x00c5
        L_0x00b3:
            r7 = move-exception
            r6 = r1
            r1 = r7
            r7 = r6
        L_0x00b7:
            r1.printStackTrace()     // Catch:{ all -> 0x00c4 }
            if (r7 == 0) goto L_0x00c2
            r7.close()     // Catch:{ Exception -> 0x00c0 }
            goto L_0x00c2
        L_0x00c0:
            r7 = move-exception
            goto L_0x00a8
        L_0x00c2:
            monitor-exit(r0)
            return
        L_0x00c4:
            r1 = move-exception
        L_0x00c5:
            if (r7 == 0) goto L_0x00cf
            r7.close()     // Catch:{ Exception -> 0x00cb }
            goto L_0x00cf
        L_0x00cb:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00d0 }
        L_0x00cf:
            throw r1     // Catch:{ all -> 0x00d0 }
        L_0x00d0:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.n(android.content.Context):void");
    }

    public static void setHostCorePathAppDefined(String str) {
        c = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0291, code lost:
        if (r4.equals(r9.getApplicationContext().getPackageName()) != false) goto L_0x02c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0293, code lost:
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:?, code lost:
        com.tencent.smtt.utils.j.b(com.tencent.smtt.sdk.am.a().q(r9));
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02bc, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:?, code lost:
        r11.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01ee, code lost:
        if (r4.equals(r9.getApplicationContext().getPackageName()) != false) goto L_0x021d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01f0, code lost:
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        com.tencent.smtt.utils.j.b(com.tencent.smtt.sdk.am.a().q(r9));
        com.tencent.smtt.utils.TbsLog.i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0219, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        r11.printStackTrace();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void writeCoreInfoForThirdPartyApp(android.content.Context r9, int r10, boolean r11) {
        /*
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r0 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r0)
            java.lang.String r1 = "TbsShareManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0304 }
            r2.<init>()     // Catch:{ all -> 0x0304 }
            java.lang.String r3 = "writeCoreInfoForThirdPartyApp coreVersion is "
            r2.append(r3)     // Catch:{ all -> 0x0304 }
            r2.append(r10)     // Catch:{ all -> 0x0304 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.TbsLog.i(r1, r2)     // Catch:{ all -> 0x0304 }
            if (r10 != 0) goto L_0x002b
            m(r9)     // Catch:{ all -> 0x0304 }
            android.content.Context r9 = f9102a     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ all -> 0x0304 }
            r10 = -401(0xfffffffffffffe6f, float:NaN)
            r9.setDownloadInterruptCode(r10)     // Catch:{ all -> 0x0304 }
            monitor-exit(r0)
            return
        L_0x002b:
            int r1 = h(r9)     // Catch:{ all -> 0x0304 }
            java.lang.String r2 = "TbsShareManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0304 }
            r3.<init>()     // Catch:{ all -> 0x0304 }
            java.lang.String r4 = "writeCoreInfoForThirdPartyApp coreVersionFromConfig is "
            r3.append(r4)     // Catch:{ all -> 0x0304 }
            r3.append(r1)     // Catch:{ all -> 0x0304 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x0304 }
            if (r1 >= 0) goto L_0x0054
            android.content.Context r9 = f9102a     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ all -> 0x0304 }
            r10 = -402(0xfffffffffffffe6e, float:NaN)
            r9.setDownloadInterruptCode(r10)     // Catch:{ all -> 0x0304 }
            monitor-exit(r0)
            return
        L_0x0054:
            if (r10 != r1) goto L_0x0066
            c(r9, r11)     // Catch:{ all -> 0x0304 }
            android.content.Context r9 = f9102a     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ all -> 0x0304 }
            r10 = -403(0xfffffffffffffe6d, float:NaN)
            r9.setDownloadInterruptCode(r10)     // Catch:{ all -> 0x0304 }
            monitor-exit(r0)
            return
        L_0x0066:
            r2 = -404(0xfffffffffffffe6c, float:NaN)
            if (r10 >= r1) goto L_0x0078
            m(r9)     // Catch:{ all -> 0x0304 }
            android.content.Context r9 = f9102a     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ all -> 0x0304 }
            r9.setDownloadInterruptCode(r2)     // Catch:{ all -> 0x0304 }
            monitor-exit(r0)
            return
        L_0x0078:
            com.tencent.smtt.sdk.am r1 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            int r1 = r1.i(r9)     // Catch:{ all -> 0x0304 }
            java.lang.String r3 = "TbsShareManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0304 }
            r4.<init>()     // Catch:{ all -> 0x0304 }
            java.lang.String r5 = "writeCoreInfoForThirdPartyApp coreVersionFromCoreShare is "
            r4.append(r5)     // Catch:{ all -> 0x0304 }
            r4.append(r1)     // Catch:{ all -> 0x0304 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ all -> 0x0304 }
            if (r10 >= r1) goto L_0x00a6
            m(r9)     // Catch:{ all -> 0x0304 }
            android.content.Context r9 = f9102a     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)     // Catch:{ all -> 0x0304 }
            r9.setDownloadInterruptCode(r2)     // Catch:{ all -> 0x0304 }
            monitor-exit(r0)
            return
        L_0x00a6:
            boolean r1 = com.tencent.smtt.sdk.QbSdk.getOnlyDownload()     // Catch:{ all -> 0x0304 }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x00bb
            java.lang.String[] r11 = new java.lang.String[r3]     // Catch:{ all -> 0x0304 }
            android.content.Context r1 = r9.getApplicationContext()     // Catch:{ all -> 0x0304 }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ all -> 0x0304 }
            r11[r2] = r1     // Catch:{ all -> 0x0304 }
            goto L_0x00cf
        L_0x00bb:
            java.lang.String[] r1 = getCoreProviderAppList()     // Catch:{ all -> 0x0304 }
            if (r11 == 0) goto L_0x00ce
            java.lang.String[] r11 = new java.lang.String[r3]     // Catch:{ all -> 0x0304 }
            android.content.Context r1 = r9.getApplicationContext()     // Catch:{ all -> 0x0304 }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ all -> 0x0304 }
            r11[r2] = r1     // Catch:{ all -> 0x0304 }
            goto L_0x00cf
        L_0x00ce:
            r11 = r1
        L_0x00cf:
            java.lang.String r1 = c     // Catch:{ all -> 0x0304 }
            if (r1 == 0) goto L_0x01b7
            com.tencent.smtt.sdk.am r1 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            java.lang.String r4 = c     // Catch:{ all -> 0x0304 }
            int r1 = r1.a((java.lang.String) r4)     // Catch:{ all -> 0x0304 }
            if (r10 != r1) goto L_0x0124
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ all -> 0x0304 }
            java.lang.String r11 = "AppDefined"
            java.lang.String r1 = c     // Catch:{ all -> 0x0304 }
            java.lang.String r2 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x0304 }
            writeProperties(r9, r10, r11, r1, r2)     // Catch:{ all -> 0x0304 }
            java.lang.String r10 = "core_info"
            java.io.File r10 = b((android.content.Context) r9, (java.lang.String) r10)     // Catch:{ Throwable -> 0x011e }
            boolean r11 = h     // Catch:{ Throwable -> 0x011e }
            if (r11 != 0) goto L_0x0122
            if (r10 == 0) goto L_0x0122
            com.tencent.smtt.sdk.TbsLinuxToolsJni r11 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x011e }
            android.content.Context r1 = f9102a     // Catch:{ Throwable -> 0x011e }
            r11.<init>(r1)     // Catch:{ Throwable -> 0x011e }
            java.lang.String r10 = r10.getAbsolutePath()     // Catch:{ Throwable -> 0x011e }
            java.lang.String r1 = "644"
            r11.a(r10, r1)     // Catch:{ Throwable -> 0x011e }
            com.tencent.smtt.sdk.am r10 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x011e }
            java.io.File r9 = r10.r(r9)     // Catch:{ Throwable -> 0x011e }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ Throwable -> 0x011e }
            java.lang.String r10 = "755"
            r11.a(r9, r10)     // Catch:{ Throwable -> 0x011e }
            h = r3     // Catch:{ Throwable -> 0x011e }
            goto L_0x0122
        L_0x011e:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ all -> 0x0304 }
        L_0x0122:
            monitor-exit(r0)
            return
        L_0x0124:
            com.tencent.smtt.sdk.am r1 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            java.lang.String r4 = c     // Catch:{ all -> 0x0304 }
            int r1 = r1.a((java.lang.String) r4)     // Catch:{ all -> 0x0304 }
            if (r10 <= r1) goto L_0x01b7
            int r1 = r11.length     // Catch:{ all -> 0x0304 }
            r4 = 0
        L_0x0132:
            if (r4 >= r1) goto L_0x01b7
            r5 = r11[r4]     // Catch:{ all -> 0x0304 }
            int r6 = getSharedTbsCoreVersion(r9, r5)     // Catch:{ all -> 0x0304 }
            if (r10 != r6) goto L_0x01b3
            android.content.Context r5 = a((android.content.Context) r9, (java.lang.String) r5)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r6 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            java.io.File r6 = r6.q(r5)     // Catch:{ all -> 0x0304 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.b.b(r9)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r7 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            boolean r5 = r7.f(r5)     // Catch:{ all -> 0x0304 }
            if (r5 != 0) goto L_0x015a
            goto L_0x01b3
        L_0x015a:
            java.io.File r11 = new java.io.File     // Catch:{ all -> 0x0304 }
            java.lang.String r1 = c     // Catch:{ all -> 0x0304 }
            r11.<init>(r1)     // Catch:{ all -> 0x0304 }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0304 }
            r1.<init>(r6)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.bb r2 = new com.tencent.smtt.sdk.bb     // Catch:{ all -> 0x0304 }
            r2.<init>()     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.j.a((java.io.File) r1, (java.io.File) r11, (java.io.FileFilter) r2)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r11 = "AppDefined"
            java.lang.String r1 = c     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r2 = java.lang.Integer.toString(r3)     // Catch:{ Throwable -> 0x01ad }
            writeProperties(r9, r10, r11, r1, r2)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r10 = "core_info"
            java.io.File r10 = b((android.content.Context) r9, (java.lang.String) r10)     // Catch:{ Throwable -> 0x01ad }
            boolean r11 = h     // Catch:{ Throwable -> 0x01ad }
            if (r11 != 0) goto L_0x01b1
            if (r10 == 0) goto L_0x01b1
            com.tencent.smtt.sdk.TbsLinuxToolsJni r11 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x01ad }
            android.content.Context r1 = f9102a     // Catch:{ Throwable -> 0x01ad }
            r11.<init>(r1)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r10 = r10.getAbsolutePath()     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r1 = "644"
            r11.a(r10, r1)     // Catch:{ Throwable -> 0x01ad }
            com.tencent.smtt.sdk.am r10 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x01ad }
            java.io.File r9 = r10.r(r9)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r10 = "755"
            r11.a(r9, r10)     // Catch:{ Throwable -> 0x01ad }
            h = r3     // Catch:{ Throwable -> 0x01ad }
            goto L_0x01b1
        L_0x01ad:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ all -> 0x0304 }
        L_0x01b1:
            monitor-exit(r0)
            return
        L_0x01b3:
            int r4 = r4 + 1
            goto L_0x0132
        L_0x01b7:
            int r1 = r11.length     // Catch:{ all -> 0x0304 }
        L_0x01b8:
            if (r2 >= r1) goto L_0x0302
            r4 = r11[r2]     // Catch:{ all -> 0x0304 }
            int r5 = getSharedTbsCoreVersion(r9, r4)     // Catch:{ all -> 0x0304 }
            if (r10 != r5) goto L_0x025f
            android.content.Context r5 = a((android.content.Context) r9, (java.lang.String) r4)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r6 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            java.io.File r6 = r6.q(r5)     // Catch:{ all -> 0x0304 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ all -> 0x0304 }
            int r7 = com.tencent.smtt.utils.b.b(r9)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r8 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            boolean r5 = r8.f(r5)     // Catch:{ all -> 0x0304 }
            if (r5 != 0) goto L_0x01e2
            goto L_0x02fe
        L_0x01e2:
            android.content.Context r11 = r9.getApplicationContext()     // Catch:{ all -> 0x0304 }
            java.lang.String r11 = r11.getPackageName()     // Catch:{ all -> 0x0304 }
            boolean r11 = r4.equals(r11)     // Catch:{ all -> 0x0304 }
            if (r11 != 0) goto L_0x021d
            java.lang.String r11 = "TbsShareManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0304 }
            r1.<init>()     // Catch:{ all -> 0x0304 }
            java.lang.String r2 = "thirdAPP pre--> delete old core_share Directory:"
            r1.append(r2)     // Catch:{ all -> 0x0304 }
            r1.append(r10)     // Catch:{ all -> 0x0304 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.TbsLog.i(r11, r1)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r11 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            java.io.File r11 = r11.q(r9)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.j.b((java.io.File) r11)     // Catch:{ Throwable -> 0x0219 }
            java.lang.String r11 = "TbsShareManager"
            java.lang.String r1 = "thirdAPP success--> delete old core_share Directory"
            com.tencent.smtt.utils.TbsLog.i(r11, r1)     // Catch:{ Throwable -> 0x0219 }
            goto L_0x021d
        L_0x0219:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ all -> 0x0304 }
        L_0x021d:
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ all -> 0x0304 }
            java.lang.String r11 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x0304 }
            writeProperties(r9, r10, r4, r6, r11)     // Catch:{ all -> 0x0304 }
            java.lang.String r10 = "core_info"
            java.io.File r10 = b((android.content.Context) r9, (java.lang.String) r10)     // Catch:{ Throwable -> 0x0259 }
            boolean r11 = h     // Catch:{ Throwable -> 0x0259 }
            if (r11 != 0) goto L_0x0302
            if (r10 == 0) goto L_0x0302
            com.tencent.smtt.sdk.TbsLinuxToolsJni r11 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x0259 }
            android.content.Context r1 = f9102a     // Catch:{ Throwable -> 0x0259 }
            r11.<init>(r1)     // Catch:{ Throwable -> 0x0259 }
            java.lang.String r10 = r10.getAbsolutePath()     // Catch:{ Throwable -> 0x0259 }
            java.lang.String r1 = "644"
            r11.a(r10, r1)     // Catch:{ Throwable -> 0x0259 }
            com.tencent.smtt.sdk.am r10 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x0259 }
            java.io.File r9 = r10.r(r9)     // Catch:{ Throwable -> 0x0259 }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ Throwable -> 0x0259 }
            java.lang.String r10 = "755"
            r11.a(r9, r10)     // Catch:{ Throwable -> 0x0259 }
            h = r3     // Catch:{ Throwable -> 0x0259 }
            goto L_0x0302
        L_0x0259:
            r9 = move-exception
        L_0x025a:
            r9.printStackTrace()     // Catch:{ all -> 0x0304 }
            goto L_0x0302
        L_0x025f:
            int r5 = getCoreShareDecoupleCoreVersion(r9, r4)     // Catch:{ all -> 0x0304 }
            if (r10 != r5) goto L_0x02fe
            android.content.Context r5 = a((android.content.Context) r9, (java.lang.String) r4)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r6 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            java.io.File r6 = r6.p(r5)     // Catch:{ all -> 0x0304 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ all -> 0x0304 }
            int r7 = com.tencent.smtt.utils.b.b(r9)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r8 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            boolean r5 = r8.f(r5)     // Catch:{ all -> 0x0304 }
            if (r5 != 0) goto L_0x0285
            goto L_0x02fe
        L_0x0285:
            android.content.Context r11 = r9.getApplicationContext()     // Catch:{ all -> 0x0304 }
            java.lang.String r11 = r11.getPackageName()     // Catch:{ all -> 0x0304 }
            boolean r11 = r4.equals(r11)     // Catch:{ all -> 0x0304 }
            if (r11 != 0) goto L_0x02c0
            java.lang.String r11 = "TbsShareManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0304 }
            r1.<init>()     // Catch:{ all -> 0x0304 }
            java.lang.String r2 = "thirdAPP pre--> delete old core_share Directory:"
            r1.append(r2)     // Catch:{ all -> 0x0304 }
            r1.append(r10)     // Catch:{ all -> 0x0304 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.TbsLog.i(r11, r1)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.sdk.am r11 = com.tencent.smtt.sdk.am.a()     // Catch:{ all -> 0x0304 }
            java.io.File r11 = r11.q(r9)     // Catch:{ all -> 0x0304 }
            com.tencent.smtt.utils.j.b((java.io.File) r11)     // Catch:{ Throwable -> 0x02bc }
            java.lang.String r11 = "TbsShareManager"
            java.lang.String r1 = "thirdAPP success--> delete old core_share Directory"
            com.tencent.smtt.utils.TbsLog.i(r11, r1)     // Catch:{ Throwable -> 0x02bc }
            goto L_0x02c0
        L_0x02bc:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ all -> 0x0304 }
        L_0x02c0:
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ all -> 0x0304 }
            java.lang.String r11 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x0304 }
            writeProperties(r9, r10, r4, r6, r11)     // Catch:{ all -> 0x0304 }
            java.lang.String r10 = "core_info"
            java.io.File r10 = b((android.content.Context) r9, (java.lang.String) r10)     // Catch:{ Throwable -> 0x02fb }
            boolean r11 = h     // Catch:{ Throwable -> 0x02fb }
            if (r11 != 0) goto L_0x0302
            if (r10 == 0) goto L_0x0302
            com.tencent.smtt.sdk.TbsLinuxToolsJni r11 = new com.tencent.smtt.sdk.TbsLinuxToolsJni     // Catch:{ Throwable -> 0x02fb }
            android.content.Context r1 = f9102a     // Catch:{ Throwable -> 0x02fb }
            r11.<init>(r1)     // Catch:{ Throwable -> 0x02fb }
            java.lang.String r10 = r10.getAbsolutePath()     // Catch:{ Throwable -> 0x02fb }
            java.lang.String r1 = "644"
            r11.a(r10, r1)     // Catch:{ Throwable -> 0x02fb }
            com.tencent.smtt.sdk.am r10 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x02fb }
            java.io.File r9 = r10.r(r9)     // Catch:{ Throwable -> 0x02fb }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ Throwable -> 0x02fb }
            java.lang.String r10 = "755"
            r11.a(r9, r10)     // Catch:{ Throwable -> 0x02fb }
            h = r3     // Catch:{ Throwable -> 0x02fb }
            goto L_0x0302
        L_0x02fb:
            r9 = move-exception
            goto L_0x025a
        L_0x02fe:
            int r2 = r2 + 1
            goto L_0x01b8
        L_0x0302:
            monitor-exit(r0)
            return
        L_0x0304:
            r9 = move-exception
            monitor-exit(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(android.content.Context, int, boolean):void");
    }

    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e4 A[SYNTHETIC, Splitter:B:44:0x00e4] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ee A[SYNTHETIC, Splitter:B:49:0x00ee] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00f7 A[SYNTHETIC, Splitter:B:54:0x00f7] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0101 A[SYNTHETIC, Splitter:B:59:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeProperties(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "TbsShareManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "writeProperties coreVersion is "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = " corePackageName is "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = " corePath is "
            r1.append(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            java.lang.String r0 = "TbsShareManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "writeProperties -- stack: "
            r1.append(r2)
            java.lang.Throwable r2 = new java.lang.Throwable
            java.lang.String r3 = "#"
            r2.<init>(r3)
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r0 = 0
            java.lang.String r1 = "core_info"
            java.io.File r6 = b((android.content.Context) r6, (java.lang.String) r1)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            if (r6 != 0) goto L_0x005c
            android.content.Context r6 = f9102a     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            r7 = -405(0xfffffffffffffe6b, float:NaN)
            r6.setDownloadInterruptCode(r7)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            return
        L_0x005c:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            r1.<init>(r6)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r1.<init>()     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r1.load(r2)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r3 = 0
            int r4 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x0074 }
            goto L_0x0075
        L_0x0074:
            r4 = 0
        L_0x0075:
            if (r4 == 0) goto L_0x0095
            java.lang.String r4 = "core_version"
            r1.setProperty(r4, r7)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            java.lang.String r7 = "core_disabled"
            java.lang.String r4 = java.lang.String.valueOf(r3)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r1.setProperty(r7, r4)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            java.lang.String r7 = "core_packagename"
            r1.setProperty(r7, r8)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            java.lang.String r7 = "core_path"
            r1.setProperty(r7, r9)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            java.lang.String r7 = "app_version"
            r1.setProperty(r7, r10)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            goto L_0x009f
        L_0x0095:
            java.lang.String r7 = "core_disabled"
            r8 = 1
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r1.setProperty(r7, r8)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
        L_0x009f:
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r7.<init>(r6)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r6.<init>(r7)     // Catch:{ Throwable -> 0x00d6, all -> 0x00d4 }
            r1.store(r6, r0)     // Catch:{ Throwable -> 0x00ce, all -> 0x00ca }
            k = r3     // Catch:{ Throwable -> 0x00ce, all -> 0x00ca }
            android.content.Context r7 = f9102a     // Catch:{ Throwable -> 0x00ce, all -> 0x00ca }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Throwable -> 0x00ce, all -> 0x00ca }
            r8 = -406(0xfffffffffffffe6a, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ Throwable -> 0x00ce, all -> 0x00ca }
            r2.close()     // Catch:{ Exception -> 0x00bd }
            goto L_0x00c1
        L_0x00bd:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00c1:
            r6.close()     // Catch:{ Exception -> 0x00c5 }
            goto L_0x00f1
        L_0x00c5:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x00f1
        L_0x00ca:
            r7 = move-exception
            r0 = r6
            r6 = r7
            goto L_0x00f5
        L_0x00ce:
            r7 = move-exception
            r0 = r2
            r5 = r7
            r7 = r6
            r6 = r5
            goto L_0x00df
        L_0x00d4:
            r6 = move-exception
            goto L_0x00f5
        L_0x00d6:
            r6 = move-exception
            r7 = r0
            r0 = r2
            goto L_0x00df
        L_0x00da:
            r6 = move-exception
            r2 = r0
            goto L_0x00f5
        L_0x00dd:
            r6 = move-exception
            r7 = r0
        L_0x00df:
            r6.printStackTrace()     // Catch:{ all -> 0x00f2 }
            if (r0 == 0) goto L_0x00ec
            r0.close()     // Catch:{ Exception -> 0x00e8 }
            goto L_0x00ec
        L_0x00e8:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00ec:
            if (r7 == 0) goto L_0x00f1
            r7.close()     // Catch:{ Exception -> 0x00c5 }
        L_0x00f1:
            return
        L_0x00f2:
            r6 = move-exception
            r2 = r0
            r0 = r7
        L_0x00f5:
            if (r2 == 0) goto L_0x00ff
            r2.close()     // Catch:{ Exception -> 0x00fb }
            goto L_0x00ff
        L_0x00fb:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00ff:
            if (r0 == 0) goto L_0x0109
            r0.close()     // Catch:{ Exception -> 0x0105 }
            goto L_0x0109
        L_0x0105:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0109:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeProperties(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
