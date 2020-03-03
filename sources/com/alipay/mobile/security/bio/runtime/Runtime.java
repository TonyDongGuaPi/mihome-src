package com.alipay.mobile.security.bio.runtime;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import com.alipay.mobile.security.bio.exception.BioIllegalArgumentException;
import com.alipay.mobile.security.bio.runtime.FrameworkDesc;
import com.alipay.mobile.security.bio.service.BioAppDescription;
import com.alipay.mobile.security.bio.service.BioMetaInfo;
import com.alipay.mobile.security.bio.service.BioServiceDescription;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.local.LocalService;
import com.alipay.mobile.security.bio.service.local.dynamicrelease.DynamicReleaseService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.StringUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Runtime {
    public static final String TAG = "Runtime";

    /* renamed from: a  reason: collision with root package name */
    private static Method f996a;
    private static Method b;
    private static Object c;
    private static FrameworkDesc d;
    private static HashMap<String, ModuleDesc> e;
    private static Boolean f = null;

    static {
        try {
            Class<?> cls = Class.forName("com.alipay.mobile.framework.LauncherApplicationAgent");
            Method declaredMethod = cls.getDeclaredMethod("getmBundleContext", new Class[0]);
            declaredMethod.setAccessible(true);
            c = declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls2 = c.getClass();
            f996a = cls2.getMethod("getResourcesByBundle", new Class[]{String.class});
            f996a.setAccessible(true);
            b = cls2.getMethod("findClassLoaderByBundleName", new Class[]{String.class});
            b.setAccessible(true);
        } catch (Throwable th) {
            BioLog.e("Failed to reflect Quinox's Bundle APIs : " + th.getMessage());
        }
    }

    public static boolean isRunningOnQuinox(Context context) {
        if (f == null) {
            synchronized ("com.alipay.mobile.quinox.LauncherApplication") {
                if (f == null) {
                    f = Boolean.valueOf("com.alipay.mobile.quinox.LauncherApplication".equals(context.getApplicationContext().getClass().getName()));
                    BioLog.d("Runtime : isOnQuinox=" + f);
                }
            }
        }
        return f.booleanValue();
    }

    public static ClassLoader getClassLoaderByBundleName(String str) {
        try {
            return (ClassLoader) b.invoke(c, new Object[]{str});
        } catch (Throwable th) {
            BioLog.w("Failed to reflect invoke findClassLoaderByBundleName(" + str + ") : " + th.toString());
            return null;
        }
    }

    public static Resources getResourcesByBundleName(String str) {
        try {
            return (Resources) f996a.invoke(c, new Object[]{str});
        } catch (Throwable th) {
            BioLog.w("Failed to reflect invoke getResourcesByBundle(" + str + ") : " + th.toString());
            return null;
        }
    }

    public static List<BioMetaInfo> getBioMetaInfoList(Context context, BioServiceManager bioServiceManager) {
        ArrayList arrayList = new ArrayList();
        for (ModuleDesc next : e.values()) {
            if (next.mBioMetaInfoList != null && !next.mBioMetaInfoList.isEmpty()) {
                arrayList.addAll(next.mBioMetaInfoList);
            }
        }
        return arrayList;
    }

    public static BioServiceDescription getBioServiceDescriptionByInterface(Context context, String str) {
        a(context, isRunningOnQuinox(context));
        return a(str);
    }

    private static BioServiceDescription a(String str) {
        BioServiceDescription bioServiceDescription = null;
        for (ModuleDesc next : e.values()) {
            if (next.mBioServiceDescription != null && !next.mBioServiceDescription.isEmpty()) {
                Iterator<BioServiceDescription> it = next.mBioServiceDescription.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        continue;
                        break;
                    }
                    BioServiceDescription next2 = it.next();
                    if (TextUtils.equals(str, next2.getInterfaceName())) {
                        bioServiceDescription = next2;
                        continue;
                        break;
                    }
                }
            }
            if (bioServiceDescription != null) {
                break;
            }
        }
        return bioServiceDescription;
    }

    public static void getLocalService(Context context, HashMap<String, LocalService> hashMap, HashMap<String, BioServiceDescription> hashMap2) {
        a(context, isRunningOnQuinox(context));
        ArrayList<BioServiceDescription> arrayList = new ArrayList<>();
        for (ModuleDesc next : e.values()) {
            if (next.mBioServiceDescription != null && !next.mBioServiceDescription.isEmpty()) {
                arrayList.addAll(next.mBioServiceDescription);
            }
        }
        for (BioServiceDescription bioServiceDescription : arrayList) {
            if (bioServiceDescription.isLazy()) {
                if (hashMap2 != null) {
                    hashMap2.put(bioServiceDescription.getInterfaceName(), bioServiceDescription);
                }
            } else if (hashMap != null) {
                try {
                    hashMap.put(bioServiceDescription.getInterfaceName(), (LocalService) bioServiceDescription.getClazz().newInstance());
                } catch (Throwable th) {
                    BioLog.e(th);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class<?> loadClass(android.content.Context r3, boolean r4, java.lang.String r5, java.lang.String r6) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            if (r4 == 0) goto L_0x0031
            java.lang.ClassLoader r4 = getClassLoaderByBundleName(r6)
            if (r4 == 0) goto L_0x0031
            java.lang.Class r6 = r4.loadClass(r5)     // Catch:{ ClassNotFoundException -> 0x0012 }
            goto L_0x0032
        L_0x0012:
            r0.add(r4)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "Failed to loadClass("
            r6.append(r1)
            r6.append(r5)
            java.lang.String r1 = ") by "
            r6.append(r1)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            com.alipay.mobile.security.bio.utils.BioLog.w((java.lang.String) r4)
        L_0x0031:
            r6 = 0
        L_0x0032:
            if (r6 != 0) goto L_0x0065
            java.lang.Class<com.alipay.mobile.security.bio.runtime.Runtime> r4 = com.alipay.mobile.security.bio.runtime.Runtime.class
            java.lang.ClassLoader r4 = r4.getClassLoader()
            boolean r1 = r0.contains(r4)
            if (r1 != 0) goto L_0x0065
            java.lang.Class r1 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x0046 }
            r6 = r1
            goto L_0x0065
        L_0x0046:
            r0.add(r4)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to loadClass("
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = ") by "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            com.alipay.mobile.security.bio.utils.BioLog.w((java.lang.String) r4)
        L_0x0065:
            if (r6 != 0) goto L_0x0096
            if (r3 == 0) goto L_0x0096
            java.lang.ClassLoader r3 = r3.getClassLoader()
            if (r3 == 0) goto L_0x0096
            boolean r4 = r0.contains(r3)
            if (r4 != 0) goto L_0x0096
            java.lang.Class r4 = r3.loadClass(r5)     // Catch:{ ClassNotFoundException -> 0x007a }
            goto L_0x0097
        L_0x007a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Failed to loadClass("
            r4.append(r0)
            r4.append(r5)
            java.lang.String r5 = ") by "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.alipay.mobile.security.bio.utils.BioLog.w((java.lang.String) r3)
        L_0x0096:
            r4 = r6
        L_0x0097:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.bio.runtime.Runtime.loadClass(android.content.Context, boolean, java.lang.String, java.lang.String):java.lang.Class");
    }

    public static String getMetaInfos(Context context) {
        if (context != null) {
            boolean isRunningOnQuinox = isRunningOnQuinox(context);
            a(context, isRunningOnQuinox);
            String str = d.frameworkVersion + ":" + a(context) + "," + b(context, isRunningOnQuinox);
            BioLog.i("MetaInfo:" + str);
            return str;
        }
        throw new BioIllegalArgumentException("Context is null");
    }

    public static String getFrameworkVersion(Context context) {
        return d.frameworkVersion;
    }

    private static synchronized void a(Context context, boolean z) {
        synchronized (Runtime.class) {
            if (d == null) {
                d = FrameworkDesc.create(context);
            }
            HashSet<String> hashSet = new HashSet<>();
            if (e == null) {
                e = new HashMap<>(d.configs.size());
                for (FrameworkDesc.ConfigDesc next : d.configs) {
                    ModuleDesc create = ModuleDesc.create(context, z, next);
                    BioLog.i("load : configDesc=" + next + ", moduleDesc=" + create);
                    if (z) {
                        if (next.dev) {
                            if (next.dynamic) {
                                if (create == null) {
                                    BioLog.w("No need to trigger dynamicrelease a dev bundle: " + next);
                                } else {
                                    e.put(next.configFileName, create);
                                }
                            } else if (create == null) {
                                BioLog.w("There is a static dev bundle can't be found: " + next);
                            } else {
                                e.put(next.configFileName, create);
                            }
                        } else if (next.dynamic) {
                            if (create == null) {
                                hashSet.add(next.bundleName);
                            } else {
                                e.put(next.configFileName, create);
                            }
                        } else if (create != null) {
                            e.put(next.configFileName, create);
                        } else {
                            throw new RuntimeException("There is a static bundle can't be found: " + next);
                        }
                    } else if (next.dev) {
                        throw new RuntimeException("On no-quinox, there is a dev bundle: " + next);
                    } else if (next.dynamic) {
                        throw new RuntimeException("On no-quinox, there is a dynamic bundle: " + next);
                    } else if (create != null) {
                        e.put(next.configFileName, create);
                    } else {
                        throw new RuntimeException("There is a static bundle can't be found: " + next);
                    }
                }
            } else if (z) {
                for (FrameworkDesc.ConfigDesc next2 : d.configs) {
                    if (next2.dynamic) {
                        ModuleDesc create2 = ModuleDesc.create(context, true, next2);
                        BioLog.i("reload : configDesc=" + next2 + ", moduleDesc=" + create2);
                        if (create2 == null) {
                            hashSet.add(next2.bundleName);
                        } else {
                            e.put(next2.configFileName, create2);
                        }
                    }
                }
            }
            if (z) {
                DynamicReleaseService dynamicReleaseService = (DynamicReleaseService) BioServiceManager.getLocalService(context, a(DynamicReleaseService.class.getName()));
                if (dynamicReleaseService != null) {
                    for (FrameworkDesc.ConfigDesc next3 : d.configs) {
                        if (next3.dynamic) {
                            dynamicReleaseService.monitorCoverage(next3.bundleName, (String) null);
                        }
                    }
                }
                if (!hashSet.isEmpty()) {
                    BioLog.w("Not exist bundle names: " + StringUtil.collection2String(hashSet));
                    if (dynamicReleaseService == null) {
                        BioLog.e("Failed to get DynamicReleaseService, doesn't find it.");
                    } else {
                        for (String trigDynamicRelease : hashSet) {
                            dynamicReleaseService.trigDynamicRelease(context, trigDynamicRelease);
                        }
                    }
                }
            }
        }
    }

    private static String a(Context context) {
        int i = 0;
        for (BioMetaInfo next : getBioMetaInfoList(context, (BioServiceManager) null)) {
            for (Long longValue : next.getProductIDs()) {
                double d2 = (double) i;
                double pow = Math.pow(2.0d, (double) longValue.longValue());
                Double.isNaN(d2);
                i = (int) (d2 + pow);
            }
            for (BioAppDescription productID : next.getApplications()) {
                long productID2 = productID.getProductID();
                if (-1 != productID2) {
                    double d3 = (double) i;
                    double pow2 = Math.pow(2.0d, (double) productID2);
                    Double.isNaN(d3);
                    i = (int) (d3 + pow2);
                }
            }
        }
        return "" + i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String b(android.content.Context r10, boolean r11) {
        /*
            int r11 = com.alipay.mobile.security.bio.workspace.Env.getProtocolFormat(r10)
            r0 = 4611686018427387904(0x4000000000000000, double:2.0)
            r2 = 1
            r3 = 0
            if (r2 == r11) goto L_0x001c
            double r4 = (double) r3
            com.alipay.mobile.security.bio.config.BisRuntimeInfoEnum r11 = com.alipay.mobile.security.bio.config.BisRuntimeInfoEnum.PROTOCOL_FORMAT
            int r11 = r11.getProductID()
            double r6 = (double) r11
            double r6 = java.lang.Math.pow(r0, r6)
            java.lang.Double.isNaN(r4)
            double r4 = r4 + r6
            int r11 = (int) r4
            goto L_0x001d
        L_0x001c:
            r11 = 0
        L_0x001d:
            java.lang.String r4 = "android-phone-securitycommon-eyemetric"
            java.lang.ClassLoader r4 = getClassLoaderByBundleName(r4)
            if (r4 == 0) goto L_0x0094
            java.lang.Class<com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService> r5 = com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService.class
            com.alipay.mobile.security.bio.service.local.LocalService r5 = com.alipay.mobile.security.bio.service.BioServiceManager.getLocalService((android.content.Context) r10, r5)
            com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService r5 = (com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService) r5
            r6 = 0
            if (r5 == 0) goto L_0x0039
            java.lang.String r7 = "DynamicRelease"
            java.lang.String r8 = "BUNDLE"
            java.lang.String r9 = "EYE_METRIC"
            r5.keyBizTrace(r7, r8, r9, r6)
        L_0x0039:
            java.lang.String r7 = "DynamicRelease"
            java.lang.String r8 = "BUNDLE->DynamicBundle.EYE_METRIC"
            android.util.Log.e(r7, r8)
            java.lang.String r7 = "com.alipay.mobile.security.bio.eye.Config"
            java.lang.Class r4 = r4.loadClass(r7)     // Catch:{ Throwable -> 0x005c }
            if (r4 == 0) goto L_0x0060
            java.lang.String r7 = "getDownLoadStateKey"
            java.lang.Class[] r8 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x005c }
            java.lang.reflect.Method r7 = r4.getMethod(r7, r8)     // Catch:{ Throwable -> 0x005c }
            r7.setAccessible(r2)     // Catch:{ Throwable -> 0x005c }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x005c }
            java.lang.Object r2 = r7.invoke(r4, r2)     // Catch:{ Throwable -> 0x005c }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x005c }
            goto L_0x0061
        L_0x005c:
            r2 = move-exception
            com.alipay.mobile.security.bio.utils.BioLog.w((java.lang.Throwable) r2)
        L_0x0060:
            r2 = r6
        L_0x0061:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0094
            java.lang.String r10 = com.alipay.mobile.security.bio.utils.PreferenceHelper.getValue(r10, r2)
            boolean r10 = java.lang.Boolean.parseBoolean(r10)
            if (r10 == 0) goto L_0x0094
            if (r5 == 0) goto L_0x007c
            java.lang.String r10 = "DynamicRelease"
            java.lang.String r2 = "BUNDLE"
            java.lang.String r3 = "EYE_METRIC_ASSETS_READY"
            r5.keyBizTrace(r10, r2, r3, r6)
        L_0x007c:
            java.lang.String r10 = "DynamicRelease"
            java.lang.String r2 = "BUNDLE->DynamicBundle.EYE_METRIC_ASSETS_READY"
            android.util.Log.e(r10, r2)
            double r10 = (double) r11
            com.alipay.mobile.security.bio.config.BisRuntimeInfoEnum r2 = com.alipay.mobile.security.bio.config.BisRuntimeInfoEnum.ASSETS_READY
            int r2 = r2.getProductID()
            double r2 = (double) r2
            double r0 = java.lang.Math.pow(r0, r2)
            java.lang.Double.isNaN(r10)
            double r10 = r10 + r0
            int r11 = (int) r10
        L_0x0094:
            com.alipay.mobile.security.bio.service.local.language.LanguageService r10 = new com.alipay.mobile.security.bio.service.local.language.LanguageService
            r10.<init>()
            int r10 = r10.getCurrentLanguage()
            int r10 = r10 * 4
            int r11 = r11 + r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = ""
            r10.append(r0)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.bio.runtime.Runtime.b(android.content.Context, boolean):java.lang.String");
    }

    public static boolean startActivity(Intent intent) {
        try {
            Class<?> cls = Class.forName("com.alipay.mobile.framework.LauncherApplicationAgent");
            Method method = cls.getMethod("getInstance", new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(cls, new Object[0]);
            Method method2 = cls.getMethod("getMicroApplicationContext", new Class[0]);
            method2.setAccessible(true);
            Object invoke2 = method2.invoke(invoke, new Object[0]);
            Method method3 = invoke2.getClass().getMethod("getTopApplication", new Class[0]);
            method3.setAccessible(true);
            Object invoke3 = method3.invoke(invoke2, new Object[0]);
            Method method4 = invoke2.getClass().getMethod("startExtActivity", new Class[]{Class.forName("com.alipay.mobile.framework.app.MicroApplication"), Intent.class});
            method4.setAccessible(true);
            method4.invoke(invoke2, new Object[]{invoke3, intent});
            return true;
        } catch (Throwable th) {
            BioLog.w(th);
            return false;
        }
    }
}
