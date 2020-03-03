package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.j;
import com.xiaomi.smarthome.auth.AuthCode;
import com.xiaomi.smarthome.library.bluetooth.OTAErrorCode;
import java.io.File;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";

    /* renamed from: a  reason: collision with root package name */
    static boolean f9092a;
    private static String b;
    /* access modifiers changed from: private */
    public static Context c;
    private static Handler d;
    private static String e;
    private static Object f = new byte[0];
    /* access modifiers changed from: private */
    public static ag g;
    private static HandlerThread h;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;
    private static long l = -1;

    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i);
    }

    protected static File a(int i2) {
        String str;
        String str2;
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            String str3 = coreProviderAppList[i3];
            if (!str3.equals(c.getApplicationInfo().packageName)) {
                file = new File(j.a(c, str3, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
                if (!file.exists()) {
                    str2 = LOGTAG;
                    str = "can not find local backup core file";
                } else if (a.a(c, file) == i2) {
                    TbsLog.i(LOGTAG, "local tbs version fond,path = " + file.getAbsolutePath());
                    break;
                } else {
                    str2 = LOGTAG;
                    str = "version is not match";
                }
                TbsLog.i(str2, str);
            }
            i3++;
        }
        return file;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String a(android.content.Context r5) {
        /*
            java.lang.String r5 = b
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x000b
            java.lang.String r5 = b
            return r5
        L_0x000b:
            java.util.Locale r5 = java.util.Locale.getDefault()
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = android.os.Build.VERSION.RELEASE
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0024 }
            java.lang.String r3 = "UTF-8"
            byte[] r3 = r1.getBytes(r3)     // Catch:{ Exception -> 0x0024 }
            java.lang.String r4 = "ISO8859-1"
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0024 }
            r1 = r2
        L_0x0024:
            if (r1 != 0) goto L_0x002c
        L_0x0026:
            java.lang.String r1 = "1.0"
        L_0x0028:
            r0.append(r1)
            goto L_0x0033
        L_0x002c:
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x0026
            goto L_0x0028
        L_0x0033:
            java.lang.String r1 = "; "
            r0.append(r1)
            java.lang.String r1 = r5.getLanguage()
            if (r1 == 0) goto L_0x0055
            java.lang.String r1 = r1.toLowerCase()
            r0.append(r1)
            java.lang.String r5 = r5.getCountry()
            if (r5 == 0) goto L_0x005a
            java.lang.String r1 = "-"
            r0.append(r1)
            java.lang.String r5 = r5.toLowerCase()
            goto L_0x0057
        L_0x0055:
            java.lang.String r5 = "en"
        L_0x0057:
            r0.append(r5)
        L_0x005a:
            java.lang.String r5 = "REL"
            java.lang.String r1 = android.os.Build.VERSION.CODENAME
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0088
            java.lang.String r5 = android.os.Build.MODEL
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x0074 }
            java.lang.String r2 = "UTF-8"
            byte[] r2 = r5.getBytes(r2)     // Catch:{ Exception -> 0x0074 }
            java.lang.String r3 = "ISO8859-1"
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0074 }
            r5 = r1
        L_0x0074:
            if (r5 != 0) goto L_0x007c
            java.lang.String r5 = "; "
        L_0x0078:
            r0.append(r5)
            goto L_0x0088
        L_0x007c:
            int r1 = r5.length()
            if (r1 <= 0) goto L_0x0088
            java.lang.String r1 = "; "
            r0.append(r1)
            goto L_0x0078
        L_0x0088:
            java.lang.String r5 = android.os.Build.ID
            if (r5 != 0) goto L_0x008f
            java.lang.String r5 = ""
            goto L_0x0091
        L_0x008f:
            java.lang.String r5 = android.os.Build.ID
        L_0x0091:
            java.lang.String r1 = "[一-龥]"
            java.lang.String r2 = ""
            java.lang.String r5 = r5.replaceAll(r1, r2)
            if (r5 != 0) goto L_0x00a6
            java.lang.String r5 = " Build/"
            r0.append(r5)
            java.lang.String r5 = "00"
        L_0x00a2:
            r0.append(r5)
            goto L_0x00b2
        L_0x00a6:
            int r1 = r5.length()
            if (r1 <= 0) goto L_0x00b2
            java.lang.String r1 = " Build/"
            r0.append(r1)
            goto L_0x00a2
        L_0x00b2:
            java.lang.String r5 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            r1[r2] = r0
            java.lang.String r5 = java.lang.String.format(r5, r1)
            b = r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(android.content.Context):java.lang.String");
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static void a(boolean z, TbsDownloaderCallback tbsDownloaderCallback) {
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        d.removeMessages(100);
        Message obtain = Message.obtain(d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        obtain.arg1 = z ? 1 : 0;
        obtain.sendToTarget();
    }

    private static boolean a(Context context, boolean z, TbsDownloaderCallback tbsDownloaderCallback) {
        Matcher matcher;
        int i2;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
        if (Build.VERSION.SDK_INT < 8) {
            i2 = -102;
        } else if (QbSdk.c || !TbsShareManager.isThirdPartyApp(c) || c()) {
            if (!instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                if (z && !"com.tencent.mm".equals(context.getApplicationInfo().packageName)) {
                    TbsLog.i(LOGTAG, "needDownload-oversea is true, but not WX");
                    z = false;
                }
                instance.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, Boolean.valueOf(z));
                instance.commit();
                j = z;
                TbsLog.i(LOGTAG, "needDownload-first-called--isoversea = " + z);
            }
            if (!getOverSea(context) || Build.VERSION.SDK_INT == 16 || Build.VERSION.SDK_INT == 17 || Build.VERSION.SDK_INT == 18) {
                e = instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, (String) null);
                if (TextUtils.isEmpty(e)) {
                    return true;
                }
                try {
                    matcher = Pattern.compile("i686|mips|x86_64").matcher(e);
                } catch (Exception unused) {
                    matcher = null;
                }
                if (matcher == null || !matcher.find()) {
                    return true;
                }
                if (tbsDownloaderCallback != null) {
                    tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                }
                i2 = -104;
            } else {
                TbsLog.i(LOGTAG, "needDownload- return false,  because of  version is " + Build.VERSION.SDK_INT + ", and overea");
                if (tbsDownloaderCallback != null) {
                    tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                }
                i2 = -103;
            }
        } else {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        instance.setDownloadInterruptCode(i2);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0161, code lost:
        if (r9.equals(r17) != false) goto L_0x01cf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.content.Context r20, boolean r21, boolean r22) {
        /*
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r20)
            r1 = 0
            if (r21 != 0) goto L_0x01d4
            android.content.SharedPreferences r3 = r0.mPreferences
            java.lang.String r4 = "app_versionname"
            java.lang.String r3 = r3.getString(r4, r1)
            android.content.SharedPreferences r4 = r0.mPreferences
            java.lang.String r5 = "app_versioncode"
            r6 = 0
            int r4 = r4.getInt(r5, r6)
            android.content.SharedPreferences r5 = r0.mPreferences
            java.lang.String r7 = "app_metadata"
            java.lang.String r5 = r5.getString(r7, r1)
            android.content.Context r7 = c
            java.lang.String r7 = com.tencent.smtt.utils.b.a((android.content.Context) r7)
            android.content.Context r8 = c
            int r8 = com.tencent.smtt.utils.b.b(r8)
            android.content.Context r9 = c
            java.lang.String r10 = "com.tencent.mm.BuildInfo.CLIENT_VERSION"
            java.lang.String r9 = com.tencent.smtt.utils.b.a((android.content.Context) r9, (java.lang.String) r10)
            java.lang.String r10 = "TbsDownload"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "[TbsDownloader.needSendQueryRequest] appVersionName="
            r11.append(r12)
            r11.append(r7)
            java.lang.String r12 = " oldAppVersionName="
            r11.append(r12)
            r11.append(r3)
            java.lang.String r12 = " appVersionCode="
            r11.append(r12)
            r11.append(r8)
            java.lang.String r12 = " oldAppVersionCode="
            r11.append(r12)
            r11.append(r4)
            java.lang.String r12 = " appMetadata="
            r11.append(r12)
            r11.append(r9)
            java.lang.String r12 = " oldAppVersionMetadata="
            r11.append(r12)
            r11.append(r5)
            java.lang.String r11 = r11.toString()
            com.tencent.smtt.utils.TbsLog.i(r10, r11)
            long r10 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences r12 = r0.mPreferences
            java.lang.String r13 = "last_check"
            r14 = 0
            long r12 = r12.getLong(r13, r14)
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r6 = "[TbsDownloader.needSendQueryRequest] timeLastCheck="
            r2.append(r6)
            r2.append(r12)
            java.lang.String r6 = " timeNow="
            r2.append(r6)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            if (r22 == 0) goto L_0x00cb
            android.content.SharedPreferences r1 = r0.mPreferences
            java.lang.String r2 = "last_check"
            boolean r1 = r1.contains(r2)
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r14 = "[TbsDownloader.needSendQueryRequest] hasLaskCheckKey="
            r6.append(r14)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r6)
            if (r1 == 0) goto L_0x00c8
            r1 = 0
            int r6 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r6 != 0) goto L_0x00cc
            r12 = r10
            goto L_0x00cc
        L_0x00c8:
            r1 = 0
            goto L_0x00cc
        L_0x00cb:
            r1 = r14
        L_0x00cc:
            android.content.SharedPreferences r6 = r0.mPreferences
            java.lang.String r14 = "last_request_success"
            long r14 = r6.getLong(r14, r1)
            android.content.SharedPreferences r6 = r0.mPreferences
            r17 = r5
            java.lang.String r5 = "count_request_fail_in_24hours"
            long r5 = r6.getLong(r5, r1)
            long r0 = r0.getRetryInterval()
            java.lang.String r2 = "TbsDownload"
            r18 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r19 = r3
            java.lang.String r3 = "retryInterval = "
            r4.append(r3)
            r4.append(r0)
            java.lang.String r3 = " s"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            long r2 = r10 - r12
            r12 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r12
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 <= 0) goto L_0x010f
        L_0x010b:
            r1 = 0
            r2 = 1
            goto L_0x01d1
        L_0x010f:
            android.content.Context r4 = c
            boolean r4 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r4)
            if (r4 == 0) goto L_0x0129
            r12 = 0
            int r4 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r4 <= 0) goto L_0x0129
            long r10 = r10 - r14
            int r4 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r4 <= 0) goto L_0x0129
            r0 = 11
            int r4 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x0129
            goto L_0x010b
        L_0x0129:
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x0149
            android.content.Context r0 = c
            int r0 = com.tencent.smtt.sdk.TbsShareManager.findCoreForThirdPartyApp(r0)
            if (r0 != 0) goto L_0x0149
            boolean r0 = e()
            if (r0 != 0) goto L_0x0149
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()
            android.content.Context r1 = c
            r0.d(r1)
            goto L_0x010b
        L_0x0149:
            if (r7 == 0) goto L_0x0164
            if (r8 == 0) goto L_0x0164
            if (r9 == 0) goto L_0x0164
            r0 = r19
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x010b
            r1 = r18
            if (r8 != r1) goto L_0x010b
            r4 = r17
            boolean r0 = r9.equals(r4)
            if (r0 != 0) goto L_0x01cf
            goto L_0x010b
        L_0x0164:
            r4 = r17
            r1 = r18
            r0 = r19
            android.content.Context r5 = c
            boolean r5 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r5)
            if (r5 == 0) goto L_0x01cf
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "timeNow - timeLastCheck is "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = " TbsShareManager.findCoreForThirdPartyApp(sAppContext) is "
            r5.append(r2)
            android.content.Context r2 = c
            int r2 = com.tencent.smtt.sdk.TbsShareManager.findCoreForThirdPartyApp(r2)
            r5.append(r2)
            java.lang.String r2 = " sendRequestWithSameHostCoreVersion() is "
            r5.append(r2)
            boolean r2 = e()
            r5.append(r2)
            java.lang.String r2 = " appVersionName is "
            r5.append(r2)
            r5.append(r7)
            java.lang.String r2 = " appVersionCode is "
            r5.append(r2)
            r5.append(r8)
            java.lang.String r2 = " appMetadata is "
            r5.append(r2)
            r5.append(r9)
            java.lang.String r2 = " oldAppVersionName is "
            r5.append(r2)
            r5.append(r0)
            java.lang.String r0 = " oldAppVersionCode is "
            r5.append(r0)
            r5.append(r1)
            java.lang.String r0 = " oldAppVersionMetadata is "
            r5.append(r0)
            r5.append(r4)
            java.lang.String r0 = r5.toString()
            r1 = r0
            goto L_0x01d0
        L_0x01cf:
            r1 = 0
        L_0x01d0:
            r2 = 0
        L_0x01d1:
            r16 = r2
            goto L_0x01d7
        L_0x01d4:
            r1 = 0
            r16 = 1
        L_0x01d7:
            if (r16 != 0) goto L_0x01fe
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x01fe
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r0)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r0.a()
            r2 = -119(0xffffffffffffff89, float:NaN)
            r0.setErrorCode(r2)
            r0.setFailDetail((java.lang.String) r1)
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r1)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            r1.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r2, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r0)
        L_0x01fe:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(android.content.Context, boolean, boolean):boolean");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x075f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01d1 A[Catch:{ Exception -> 0x01f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01ef  */
    @android.annotation.TargetApi(11)
    private static boolean a(java.lang.String r32, int r33, boolean r34, boolean r35) {
        /*
            r0 = r32
            r1 = r33
            r2 = r34
            r3 = r35
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[TbsDownloader.readResponse] response="
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            android.content.Context r4 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r32)
            r6 = 0
            if (r5 == 0) goto L_0x003e
            if (r2 == 0) goto L_0x0033
            r0 = -108(0xffffffffffffff94, float:NaN)
        L_0x002f:
            r4.setDownloadInterruptCode(r0)
            goto L_0x0036
        L_0x0033:
            r0 = -208(0xffffffffffffff30, float:NaN)
            goto L_0x002f
        L_0x0036:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] return #1,response is empty..."
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            return r6
        L_0x003e:
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>(r0)
            java.lang.String r0 = "RET"
            int r0 = r5.getInt(r0)
            if (r0 == 0) goto L_0x006d
            if (r2 == 0) goto L_0x0053
            r1 = -109(0xffffffffffffff93, float:NaN)
        L_0x004f:
            r4.setDownloadInterruptCode(r1)
            goto L_0x0056
        L_0x0053:
            r1 = -209(0xffffffffffffff2f, float:NaN)
            goto L_0x004f
        L_0x0056:
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[TbsDownloader.readResponse] return #2,returnCode="
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            return r6
        L_0x006d:
            java.lang.String r0 = "RESPONSECODE"
            int r7 = r5.getInt(r0)
            java.lang.String r0 = "DOWNLOADURL"
            java.lang.String r8 = r5.getString(r0)
            java.lang.String r0 = "URLLIST"
            java.lang.String r9 = ""
            java.lang.String r9 = r5.optString(r0, r9)
            java.lang.String r0 = "TBSAPKSERVERVERSION"
            int r10 = r5.getInt(r0)
            java.lang.String r0 = "DOWNLOADMAXFLOW"
            int r11 = r5.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_MIN_FREE_SPACE"
            int r12 = r5.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_SUCCESS_MAX_RETRYTIMES"
            int r13 = r5.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_FAILED_MAX_RETRYTIMES"
            int r14 = r5.getInt(r0)
            java.lang.String r0 = "DOWNLOAD_SINGLE_TIMEOUT"
            long r15 = r5.getLong(r0)
            java.lang.String r0 = "TBSAPKFILESIZE"
            long r17 = r5.getLong(r0)
            java.lang.String r0 = "RETRY_INTERVAL"
            r19 = r7
            r6 = 0
            long r20 = r5.optLong(r0, r6)
            java.lang.String r0 = "FLOWCTR"
            r6 = -1
            int r6 = r5.optInt(r0, r6)
            java.lang.String r0 = "USEBBACKUPVER"
            int r0 = r5.getInt(r0)     // Catch:{ Exception -> 0x00c3 }
            goto L_0x00c4
        L_0x00c3:
            r0 = 0
        L_0x00c4:
            java.util.Map<java.lang.String, java.lang.Object> r7 = r4.f9091a
            java.lang.String r3 = "use_backup_version"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.put(r3, r0)
            if (r2 == 0) goto L_0x0120
            boolean r0 = com.tencent.smtt.sdk.QbSdk.i
            if (r0 == 0) goto L_0x0120
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 == 0) goto L_0x0120
            java.lang.String r0 = "BUGLY"
            r7 = 0
            int r0 = r5.optInt(r0, r7)     // Catch:{ Throwable -> 0x0100 }
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r7 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x0100 }
            android.content.Context r3 = c     // Catch:{ Throwable -> 0x0100 }
            r22 = r15
            java.lang.String r15 = "bugly_switch.txt"
            r24 = r14
            r14 = 1
            if (r0 != r14) goto L_0x00f5
            r0 = 1
            goto L_0x00f6
        L_0x00f5:
            r0 = 0
        L_0x00f6:
            r7.setFunctionEnable(r3, r15, r0)     // Catch:{ Throwable -> 0x00fa }
            goto L_0x0124
        L_0x00fa:
            r0 = move-exception
            goto L_0x0105
        L_0x00fc:
            r0 = move-exception
            r24 = r14
            goto L_0x0105
        L_0x0100:
            r0 = move-exception
            r24 = r14
            r22 = r15
        L_0x0105:
            java.lang.String r3 = "qbsdk"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r14 = "throwable:"
            r7.append(r14)
            java.lang.String r0 = r0.toString()
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r0)
            goto L_0x0124
        L_0x0120:
            r24 = r14
            r22 = r15
        L_0x0124:
            if (r2 == 0) goto L_0x0199
            java.lang.String r0 = "TEMPLATESWITCH"
            r7 = 0
            int r0 = r5.optInt(r0, r7)     // Catch:{ Throwable -> 0x017e }
            r7 = r0 & 1
            if (r7 == 0) goto L_0x0133
            r7 = 1
            goto L_0x0134
        L_0x0133:
            r7 = 0
        L_0x0134:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r14 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x017e }
            android.content.Context r15 = c     // Catch:{ Throwable -> 0x017e }
            java.lang.String r3 = "cookie_switch.txt"
            r14.setFunctionEnable(r15, r3, r7)     // Catch:{ Throwable -> 0x017e }
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x017e }
            r14.<init>()     // Catch:{ Throwable -> 0x017e }
            java.lang.String r15 = "useCookieCompatiable:"
            r14.append(r15)     // Catch:{ Throwable -> 0x017e }
            r14.append(r7)     // Catch:{ Throwable -> 0x017e }
            java.lang.String r7 = r14.toString()     // Catch:{ Throwable -> 0x017e }
            com.tencent.smtt.utils.TbsLog.w(r3, r7)     // Catch:{ Throwable -> 0x017e }
            r3 = 2
            r0 = r0 & r3
            if (r0 == 0) goto L_0x015b
            r0 = 1
            goto L_0x015c
        L_0x015b:
            r0 = 0
        L_0x015c:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r3 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ Throwable -> 0x017e }
            android.content.Context r7 = c     // Catch:{ Throwable -> 0x017e }
            java.lang.String r14 = "disable_get_apk_version_switch.txt"
            r3.setFunctionEnable(r7, r14, r0)     // Catch:{ Throwable -> 0x017e }
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x017e }
            r7.<init>()     // Catch:{ Throwable -> 0x017e }
            java.lang.String r14 = "disableGetApkVersionByReadFile:"
            r7.append(r14)     // Catch:{ Throwable -> 0x017e }
            r7.append(r0)     // Catch:{ Throwable -> 0x017e }
            java.lang.String r0 = r7.toString()     // Catch:{ Throwable -> 0x017e }
            com.tencent.smtt.utils.TbsLog.w(r3, r0)     // Catch:{ Throwable -> 0x017e }
            goto L_0x0199
        L_0x017e:
            r0 = move-exception
            java.lang.String r3 = "qbsdk"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r14 = "throwable:"
            r7.append(r14)
            java.lang.String r0 = r0.toString()
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r0)
        L_0x0199:
            java.lang.String r0 = ""
            java.lang.String r7 = "PKGMD5"
            java.lang.String r7 = r5.getString(r7)     // Catch:{ Exception -> 0x020d }
            java.lang.String r14 = "RESETX5"
            int r14 = r5.getInt(r14)     // Catch:{ Exception -> 0x0207 }
            java.lang.String r15 = "UPLOADLOG"
            int r15 = r5.getInt(r15)     // Catch:{ Exception -> 0x0201 }
            java.lang.String r3 = "RESETTOKEN"
            boolean r3 = r5.has(r3)     // Catch:{ Exception -> 0x01fb }
            if (r3 == 0) goto L_0x01c6
            java.lang.String r3 = "RESETTOKEN"
            int r3 = r5.getInt(r3)     // Catch:{ Exception -> 0x01c3 }
            if (r3 == 0) goto L_0x01bf
            r3 = 1
            goto L_0x01c0
        L_0x01bf:
            r3 = 0
        L_0x01c0:
            r25 = r0
            goto L_0x01c9
        L_0x01c3:
            r26 = r0
            goto L_0x01ff
        L_0x01c6:
            r25 = r0
            r3 = 0
        L_0x01c9:
            java.lang.String r0 = "SETTOKEN"
            boolean r0 = r5.has(r0)     // Catch:{ Exception -> 0x01f8 }
            if (r0 == 0) goto L_0x01da
            java.lang.String r0 = "SETTOKEN"
            java.lang.String r0 = r5.getString(r0)     // Catch:{ Exception -> 0x01f8 }
            r26 = r0
            goto L_0x01dc
        L_0x01da:
            r26 = r25
        L_0x01dc:
            java.lang.String r0 = "ENABLE_LOAD_RENAME_FILE_LOCK"
            boolean r0 = r5.has(r0)     // Catch:{ Exception -> 0x0215 }
            if (r0 == 0) goto L_0x01ef
            java.lang.String r0 = "ENABLE_LOAD_RENAME_FILE_LOCK"
            int r0 = r5.getInt(r0)     // Catch:{ Exception -> 0x0215 }
            if (r0 == 0) goto L_0x01ed
            goto L_0x01ef
        L_0x01ed:
            r0 = 0
            goto L_0x01f0
        L_0x01ef:
            r0 = 1
        L_0x01f0:
            r27 = r7
            r28 = r13
            r7 = r0
            r0 = r26
            goto L_0x021c
        L_0x01f8:
            r26 = r25
            goto L_0x0215
        L_0x01fb:
            r25 = r0
            r26 = r25
        L_0x01ff:
            r3 = 0
            goto L_0x0215
        L_0x0201:
            r25 = r0
            r26 = r25
            r3 = 0
            goto L_0x0214
        L_0x0207:
            r25 = r0
            r26 = r25
            r3 = 0
            goto L_0x0213
        L_0x020d:
            r25 = r0
            r26 = r25
            r3 = 0
            r7 = 0
        L_0x0213:
            r14 = 0
        L_0x0214:
            r15 = 0
        L_0x0215:
            r27 = r7
            r28 = r13
            r0 = r26
            r7 = 1
        L_0x021c:
            java.lang.String r13 = "RESETDECOUPLECORE"
            int r13 = r5.getInt(r13)     // Catch:{ Exception -> 0x0223 }
            goto L_0x0224
        L_0x0223:
            r13 = 0
        L_0x0224:
            r29 = r12
            java.lang.String r12 = "RESETTODECOUPLECORE"
            int r12 = r5.getInt(r12)     // Catch:{ Exception -> 0x022d }
            goto L_0x022e
        L_0x022d:
            r12 = 0
        L_0x022e:
            java.lang.Object r16 = f
            monitor-enter(r16)
            if (r3 == 0) goto L_0x0244
            java.util.Map<java.lang.String, java.lang.Object> r3 = r4.f9091a     // Catch:{ all -> 0x0241 }
            r30 = r11
            java.lang.String r11 = "tbs_deskey_token"
            r31 = r9
            java.lang.String r9 = ""
            r3.put(r11, r9)     // Catch:{ all -> 0x0241 }
            goto L_0x0248
        L_0x0241:
            r0 = move-exception
            goto L_0x076e
        L_0x0244:
            r31 = r9
            r30 = r11
        L_0x0248:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0241 }
            if (r3 != 0) goto L_0x0275
            int r3 = r0.length()     // Catch:{ all -> 0x0241 }
            r9 = 96
            if (r3 != r9) goto L_0x0275
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0241 }
            r3.<init>()     // Catch:{ all -> 0x0241 }
            r3.append(r0)     // Catch:{ all -> 0x0241 }
            java.lang.String r0 = "&"
            r3.append(r0)     // Catch:{ all -> 0x0241 }
            java.lang.String r0 = com.tencent.smtt.utils.n.c()     // Catch:{ all -> 0x0241 }
            r3.append(r0)     // Catch:{ all -> 0x0241 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0241 }
            java.util.Map<java.lang.String, java.lang.Object> r3 = r4.f9091a     // Catch:{ all -> 0x0241 }
            java.lang.String r9 = "tbs_deskey_token"
            r3.put(r9, r0)     // Catch:{ all -> 0x0241 }
        L_0x0275:
            monitor-exit(r16)     // Catch:{ all -> 0x0241 }
            r3 = 1
            if (r14 != r3) goto L_0x02a6
            if (r2 == 0) goto L_0x0281
            r0 = -110(0xffffffffffffff92, float:NaN)
        L_0x027d:
            r4.setDownloadInterruptCode(r0)
            goto L_0x0284
        L_0x0281:
            r0 = -210(0xffffffffffffff2e, float:NaN)
            goto L_0x027d
        L_0x0284:
            android.content.Context r0 = c
            if (r12 != r3) goto L_0x028a
            r1 = 1
            goto L_0x028b
        L_0x028a:
            r1 = 0
        L_0x028b:
            com.tencent.smtt.sdk.QbSdk.reset(r0, r1)
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "[TbsDownloader.readResponse] return #3,needResetTbs=1,isQuery="
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        L_0x02a1:
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r1 = 0
            return r1
        L_0x02a6:
            if (r7 != 0) goto L_0x02ab
            r4.setTbsCoreLoadRenameFileLockEnable(r7)
        L_0x02ab:
            r3 = 1
            if (r13 != r3) goto L_0x02b3
            android.content.Context r0 = c
            com.tencent.smtt.sdk.QbSdk.resetDecoupleCore(r0)
        L_0x02b3:
            if (r15 != r3) goto L_0x02c5
            android.os.Handler r0 = d
            r7 = 104(0x68, float:1.46E-43)
            r0.removeMessages(r7)
            android.os.Handler r0 = d
            android.os.Message r0 = android.os.Message.obtain(r0, r7)
            r0.sendToTarget()
        L_0x02c5:
            r11 = 86400(0x15180, double:4.26873E-319)
            if (r6 != r3) goto L_0x02da
            r6 = 604800(0x93a80, double:2.98811E-318)
            int r0 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x02d3
            r20 = r6
        L_0x02d3:
            r6 = 0
            int r0 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x02dc
            goto L_0x02de
        L_0x02da:
            r6 = 0
        L_0x02dc:
            r20 = r11
        L_0x02de:
            long r11 = getRetryIntervalInSeconds()
            int r0 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x02ea
            long r20 = getRetryIntervalInSeconds()
        L_0x02ea:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r3 = "retry_interval"
            java.lang.Long r6 = java.lang.Long.valueOf(r20)
            r0.put(r3, r6)
            if (r2 == 0) goto L_0x02ff
            java.lang.String r0 = "DECOUPLECOREVERSION"
            int r0 = r5.getInt(r0)     // Catch:{ Exception -> 0x030f }
        L_0x02fd:
            r6 = r0
            goto L_0x0310
        L_0x02ff:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x030f }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Exception -> 0x030f }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x030f }
            java.lang.String r3 = "tbs_decouplecoreversion"
            r6 = 0
            int r0 = r0.getInt(r3, r6)     // Catch:{ Exception -> 0x030f }
            goto L_0x02fd
        L_0x030f:
            r6 = 0
        L_0x0310:
            android.content.Context r0 = c     // Catch:{ Exception -> 0x0320 }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)     // Catch:{ Exception -> 0x0320 }
            android.content.SharedPreferences r0 = r0.mPreferences     // Catch:{ Exception -> 0x0320 }
            java.lang.String r3 = "tbs_downloaddecouplecore"
            r7 = 0
            int r0 = r0.getInt(r3, r7)     // Catch:{ Exception -> 0x0320 }
            goto L_0x0321
        L_0x0320:
            r0 = 0
        L_0x0321:
            if (r2 == 0) goto L_0x0337
            android.content.Context r3 = c
            boolean r3 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r3)
            if (r3 != 0) goto L_0x0337
            if (r6 != 0) goto L_0x0337
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r6 = c
            int r6 = r3.h(r6)
        L_0x0337:
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "in response decoupleCoreVersion is "
            r7.append(r9)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r7)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r4.f9091a
            java.lang.String r7 = "tbs_decouplecoreversion"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)
            r3.put(r7, r9)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r4.f9091a
            java.lang.String r7 = "tbs_downloaddecouplecore"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)
            r3.put(r7, r9)
            android.content.Context r3 = c
            boolean r3 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r3)
            if (r3 != 0) goto L_0x039e
            if (r6 <= 0) goto L_0x038f
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r7 = c
            int r3 = r3.h(r7)
            if (r6 == r3) goto L_0x038f
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r7 = c
            int r3 = r3.i(r7)
            if (r6 != r3) goto L_0x038f
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r6 = c
            r3.n(r6)
            goto L_0x039e
        L_0x038f:
            if (r6 != 0) goto L_0x039e
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()     // Catch:{ Throwable -> 0x039e }
            android.content.Context r6 = c     // Catch:{ Throwable -> 0x039e }
            java.io.File r3 = r3.p(r6)     // Catch:{ Throwable -> 0x039e }
            com.tencent.smtt.utils.j.b((java.io.File) r3)     // Catch:{ Throwable -> 0x039e }
        L_0x039e:
            boolean r3 = android.text.TextUtils.isEmpty(r8)
            if (r3 == 0) goto L_0x03ca
            android.content.Context r3 = c
            boolean r3 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r3)
            if (r3 == 0) goto L_0x03ca
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_needdownload"
            r3 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            r0.put(r1, r5)
            r4.commit()
            if (r2 == 0) goto L_0x03c2
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsShareManager.writeCoreInfoForThirdPartyApp(r0, r10, r3)
        L_0x03c2:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] return #4,current app is third app..."
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            return r3
        L_0x03ca:
            java.lang.String r3 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "in response responseCode is "
            r6.append(r7)
            r7 = r19
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r3, r6)
            if (r7 != 0) goto L_0x0432
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_responsecode"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_needdownload"
            r3 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            r0.put(r1, r5)
            if (r2 == 0) goto L_0x040b
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_interrupt_code_reason"
            r2 = -111(0xffffffffffffff91, float:NaN)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L_0x041b
        L_0x040b:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_interrupt_code_reason"
            r2 = -211(0xffffffffffffff2d, float:NaN)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r3)
            r4.setDownloadInterruptCode(r2)
        L_0x041b:
            r4.commit()
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            if (r0 != 0) goto L_0x0429
            startDecoupleCoreIfNeeded()
        L_0x0429:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] return #5,responseCode=0"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r3 = 0
            return r3
        L_0x0432:
            r3 = 0
            android.content.Context r6 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)
            android.content.SharedPreferences r6 = r6.mPreferences
            java.lang.String r9 = "tbs_download_version"
            int r6 = r6.getInt(r9, r3)
            if (r6 <= r10) goto L_0x0451
            com.tencent.smtt.sdk.ag r3 = g
            r3.d()
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r9 = c
            r3.o(r9)
        L_0x0451:
            android.content.Context r3 = c
            boolean r3 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r3)
            if (r3 != 0) goto L_0x0487
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r9 = c
            int r3 = r3.g(r9)
            if (r3 < r10) goto L_0x0467
            r9 = 1
            goto L_0x0468
        L_0x0467:
            r9 = 0
        L_0x0468:
            java.lang.String r11 = "TbsDownload"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "tmpCoreVersion is "
            r12.append(r13)
            r12.append(r3)
            java.lang.String r3 = " tbsDownloadVersion is"
            r12.append(r3)
            r12.append(r10)
            java.lang.String r3 = r12.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r3)
            goto L_0x0488
        L_0x0487:
            r9 = 0
        L_0x0488:
            if (r1 >= r10) goto L_0x0492
            boolean r3 = android.text.TextUtils.isEmpty(r8)
            if (r3 != 0) goto L_0x0492
            if (r9 == 0) goto L_0x0522
        L_0x0492:
            r3 = 1
            if (r0 == r3) goto L_0x0522
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r3 = "tbs_needdownload"
            r5 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            r0.put(r3, r7)
            if (r2 == 0) goto L_0x04d0
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 == 0) goto L_0x04b7
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r2 = "tbs_download_interrupt_code_reason"
            r3 = -124(0xffffffffffffff84, float:NaN)
        L_0x04af:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.put(r2, r3)
            goto L_0x04f2
        L_0x04b7:
            if (r10 > 0) goto L_0x04c0
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r2 = "tbs_download_interrupt_code_reason"
            r3 = -125(0xffffffffffffff83, float:NaN)
            goto L_0x04af
        L_0x04c0:
            if (r1 < r10) goto L_0x04c9
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r2 = "tbs_download_interrupt_code_reason"
            r3 = -127(0xffffffffffffff81, float:NaN)
            goto L_0x04af
        L_0x04c9:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r2 = "tbs_download_interrupt_code_reason"
            r3 = -112(0xffffffffffffff90, float:NaN)
            goto L_0x04af
        L_0x04d0:
            r0 = -212(0xffffffffffffff2c, float:NaN)
            boolean r2 = android.text.TextUtils.isEmpty(r8)
            if (r2 == 0) goto L_0x04db
            r0 = -217(0xffffffffffffff27, float:NaN)
            goto L_0x04e4
        L_0x04db:
            if (r10 > 0) goto L_0x04e0
            r0 = -218(0xffffffffffffff26, float:NaN)
            goto L_0x04e4
        L_0x04e0:
            if (r1 < r10) goto L_0x04e4
            r0 = -219(0xffffffffffffff25, float:NaN)
        L_0x04e4:
            java.util.Map<java.lang.String, java.lang.Object> r2 = r4.f9091a
            java.lang.String r3 = "tbs_download_interrupt_code_reason"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r2.put(r3, r5)
            r4.setDownloadInterruptCode(r0)
        L_0x04f2:
            r4.commit()
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "version error or downloadUrl empty ,return ahead tbsLocalVersion="
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = " tbsDownloadVersion="
            r2.append(r1)
            r2.append(r10)
            java.lang.String r1 = " tbsLastDownloadVersion="
            r2.append(r1)
            r2.append(r6)
            java.lang.String r1 = " downloadUrl="
            r2.append(r1)
            r2.append(r8)
            java.lang.String r1 = r2.toString()
            goto L_0x02a1
        L_0x0522:
            r1 = 0
            android.content.SharedPreferences r3 = r4.mPreferences
            java.lang.String r6 = "tbs_downloadurl"
            r9 = 0
            java.lang.String r3 = r3.getString(r6, r9)
            boolean r3 = r8.equals(r3)
            if (r3 != 0) goto L_0x054d
            com.tencent.smtt.sdk.ag r3 = g
            r3.d()
            java.util.Map<java.lang.String, java.lang.Object> r3 = r4.f9091a
            java.lang.String r6 = "tbs_download_failed_retrytimes"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)
            r3.put(r6, r9)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r4.f9091a
            java.lang.String r6 = "tbs_download_success_retrytimes"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)
            r3.put(r6, r9)
        L_0x054d:
            java.util.Map<java.lang.String, java.lang.Object> r1 = r4.f9091a
            java.lang.String r3 = "tbs_download_version"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            r1.put(r3, r6)
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "put KEY_TBS_DOWNLOAD_V is "
            r3.append(r6)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r3)
            if (r10 <= 0) goto L_0x05a1
            r1 = 1
            if (r0 != r1) goto L_0x057f
            java.util.Map<java.lang.String, java.lang.Object> r3 = r4.f9091a
            java.lang.String r6 = "tbs_download_version_type"
            java.lang.Integer r9 = java.lang.Integer.valueOf(r1)
            r3.put(r6, r9)
            goto L_0x058b
        L_0x057f:
            java.util.Map<java.lang.String, java.lang.Object> r1 = r4.f9091a
            java.lang.String r3 = "tbs_download_version_type"
            r6 = 0
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)
            r1.put(r3, r9)
        L_0x058b:
            java.lang.String r1 = "TbsDownload"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "put KEY_TBS_DOWNLOAD_V_TYPE is "
            r3.append(r6)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x05a1:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_downloadurl"
            r0.put(r1, r8)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_downloadurl_list"
            r3 = r31
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_responsecode"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_maxflow"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r30)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_min_free_space"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r29)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_success_max_retrytimes"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r28)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_failed_max_retrytimes"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r24)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_single_timeout"
            java.lang.Long r3 = java.lang.Long.valueOf(r22)
            r0.put(r1, r3)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_apkfilesize"
            java.lang.Long r3 = java.lang.Long.valueOf(r17)
            r0.put(r1, r3)
            r4.commit()
            r0 = r27
            if (r0 == 0) goto L_0x060c
            java.util.Map<java.lang.String, java.lang.Object> r1 = r4.f9091a
            java.lang.String r3 = "tbs_apk_md5"
            r1.put(r3, r0)
        L_0x060c:
            r1 = r35
            if (r1 != 0) goto L_0x0652
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()
            android.content.Context r3 = c
            boolean r0 = r0.a((android.content.Context) r3, (int) r10)
            if (r0 == 0) goto L_0x0652
            if (r2 == 0) goto L_0x062c
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_interrupt_code_reason"
            r2 = -113(0xffffffffffffff8f, float:NaN)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L_0x063c
        L_0x062c:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_download_interrupt_code_reason"
            r2 = -213(0xffffffffffffff2b, float:NaN)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r3)
            r4.setDownloadInterruptCode(r2)
        L_0x063c:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_needdownload"
            r2 = 0
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r0.put(r1, r3)
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##6 set needDownload=false"
        L_0x064c:
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r2 = 1
            goto L_0x0756
        L_0x0652:
            r0 = 100
            if (r1 != 0) goto L_0x06c5
            com.tencent.smtt.sdk.ag r3 = g
            r6 = 1
            if (r7 == r6) goto L_0x0661
            r6 = 2
            if (r7 != r6) goto L_0x065f
            goto L_0x0661
        L_0x065f:
            r6 = 0
            goto L_0x0662
        L_0x0661:
            r6 = 1
        L_0x0662:
            boolean r1 = r3.a((boolean) r1, (boolean) r6)
            if (r1 == 0) goto L_0x06c5
            java.util.Map<java.lang.String, java.lang.Object> r1 = r4.f9091a
            java.lang.String r2 = "tbs_needdownload"
            r3 = 0
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)
            r1.put(r2, r6)
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r1)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r1 = r1.a()
            r1.setErrorCode(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "use local backup apk in needDownload"
            r0.append(r2)
            com.tencent.smtt.sdk.ag r2 = g
            java.lang.String r2 = r2.f9137a
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.setFailDetail((java.lang.String) r0)
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r2 = "tbs_downloaddecouplecore"
            r3 = 0
            int r0 = r0.getInt(r2, r3)
            r2 = 1
            if (r0 != r2) goto L_0x06b7
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
        L_0x06b3:
            r0.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r2, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r1)
            goto L_0x06c0
        L_0x06b7:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            goto L_0x06b3
        L_0x06c0:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##7 set needDownload=false"
            goto L_0x064c
        L_0x06c5:
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1)
            android.content.SharedPreferences r1 = r1.mPreferences
            java.lang.String r3 = "tbs_download_version_type"
            r6 = 0
            int r1 = r1.getInt(r3, r6)
            r3 = 1
            if (r1 != r3) goto L_0x073c
            com.tencent.smtt.sdk.ag r1 = g
            boolean r1 = r1.a()
            if (r1 == 0) goto L_0x073c
            java.util.Map<java.lang.String, java.lang.Object> r1 = r4.f9091a
            java.lang.String r2 = "tbs_needdownload"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r6)
            r1.put(r2, r3)
            android.content.Context r1 = c
            com.tencent.smtt.sdk.TbsLogReport r1 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r1)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r1 = r1.a()
            r1.setErrorCode(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "installDecoupleCoreFromBackup"
            r0.append(r2)
            com.tencent.smtt.sdk.ag r2 = g
            java.lang.String r2 = r2.f9137a
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.setFailDetail((java.lang.String) r0)
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r2 = "tbs_downloaddecouplecore"
            r3 = 0
            int r0 = r0.getInt(r2, r3)
            r2 = 1
            if (r0 != r2) goto L_0x072d
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
        L_0x0729:
            r0.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r2, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r1)
            goto L_0x0736
        L_0x072d:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r0)
            com.tencent.smtt.sdk.TbsLogReport$EventType r2 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            goto L_0x0729
        L_0x0736:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##8 set needDownload=false"
            goto L_0x064c
        L_0x073c:
            if (r2 != 0) goto L_0x0743
            r0 = -216(0xffffffffffffff28, float:NaN)
            r4.setDownloadInterruptCode(r0)
        L_0x0743:
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_needdownload"
            r2 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r0.put(r1, r3)
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.readResponse] ##9 set needDownload=true"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
        L_0x0756:
            java.lang.String r0 = "stop_pre_oat"
            r1 = 0
            int r0 = r5.optInt(r0, r1)
            if (r0 != r2) goto L_0x076a
            java.util.Map<java.lang.String, java.lang.Object> r0 = r4.f9091a
            java.lang.String r1 = "tbs_stop_preoat"
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r0.put(r1, r3)
        L_0x076a:
            r4.commit()
            return r2
        L_0x076e:
            monitor-exit(r16)     // Catch:{ all -> 0x0241 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(java.lang.String, int, boolean, boolean):boolean");
    }

    protected static File b(int i2) {
        String str;
        StringBuilder sb;
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int length = coreProviderAppList.length;
        File file = null;
        int i3 = 0;
        while (i3 < length) {
            String str2 = coreProviderAppList[i3];
            File file2 = new File(j.a(c, str2, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (!file2.exists() || a.a(c, file2) != i2) {
                file2 = new File(j.a(c, str2, 4, false), "x5.tbs.decouple");
                if (!file2.exists() || a.a(c, file2) != i2) {
                    i3++;
                    file = file2;
                } else {
                    str = LOGTAG;
                    sb = new StringBuilder();
                }
            } else {
                str = LOGTAG;
                sb = new StringBuilder();
            }
            sb.append("local tbs version fond,path = ");
            sb.append(file2.getAbsolutePath());
            TbsLog.i(str, sb.toString());
            return file2;
        }
        return file;
    }

    private static JSONObject b(boolean z, boolean z2, boolean z3) {
        int i2;
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData]isQuery: " + z + " forDecoupleCore is " + z3);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        String a2 = a(c);
        String d2 = b.d(c);
        String c2 = b.c(c);
        String f2 = b.f(c);
        String str = "";
        String str2 = "";
        String id = TimeZone.getDefault().getID();
        if (id != null) {
            str = id;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) c.getSystemService("phone");
            if (telephonyManager != null) {
                id = telephonyManager.getSimCountryIso();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (id != null) {
            str2 = id;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("TIMEZONEID", str);
            jSONObject.put("COUNTRYISO", str2);
            jSONObject.put("PROTOCOLVERSION", 1);
            boolean z4 = false;
            if (TbsShareManager.isThirdPartyApp(c)) {
                i2 = QbSdk.c ? TbsShareManager.a(c, false) : TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            } else {
                i2 = am.a().m(c);
                if (i2 == 0 && am.a().l(c)) {
                    i2 = -1;
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] tbsLocalVersion=" + i2 + " isDownloadForeground=" + z2);
                if (z2) {
                    if (!am.a().l(c)) {
                        i2 = 0;
                    }
                }
            }
            if (z) {
                jSONObject.put("FUNCTION", 2);
            } else {
                jSONObject.put("FUNCTION", i2 == 0 ? 0 : 1);
            }
            if (TbsShareManager.isThirdPartyApp(c)) {
                JSONArray f3 = f();
                jSONObject.put("TBSVLARR", f3);
                instance.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, f3.toString());
                instance.commit();
                if (QbSdk.c) {
                    jSONObject.put("THIRDREQ", 1);
                }
            } else {
                JSONArray h2 = h();
                if (Apn.getApnType(c) != 3 && h2.length() != 0 && i2 == 0 && z) {
                    jSONObject.put("TBSBACKUPARR", h2);
                }
            }
            jSONObject.put("APPN", c.getPackageName());
            jSONObject.put("APPVN", a(instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, (String) null)));
            jSONObject.put("APPVC", instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0));
            jSONObject.put("APPMETA", a(instance.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, (String) null)));
            jSONObject.put("TBSSDKV", 43610);
            jSONObject.put("TBSV", i2);
            jSONObject.put("DOWNLOADDECOUPLECORE", z3 ? 1 : 0);
            instance.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, Integer.valueOf(z3));
            instance.commit();
            if (i2 != 0) {
                jSONObject.put("TBSBACKUPV", g.b());
            }
            jSONObject.put("CPU", e);
            jSONObject.put("UA", a2);
            jSONObject.put("IMSI", a(d2));
            jSONObject.put("IMEI", a(c2));
            jSONObject.put("ANDROID_ID", a(f2));
            if (!TbsShareManager.isThirdPartyApp(c)) {
                if (i2 != 0) {
                    jSONObject.put("STATUS", QbSdk.a(c, i2) ^ true ? 1 : 0);
                } else {
                    jSONObject.put("STATUS", 0);
                }
                jSONObject.put("TBSDV", am.a().h(c));
            }
            boolean z5 = TbsDownloadConfig.getInstance(c).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
            Object a3 = QbSdk.a(c, "can_unlzma", (Bundle) null);
            if ((a3 == null || !(a3 instanceof Boolean)) ? false : ((Boolean) a3).booleanValue()) {
                z4 = !z5;
            }
            if (z4) {
                jSONObject.put("REQUEST_LZMA", 1);
            }
            if (getOverSea(c)) {
                jSONObject.put("OVERSEA", 1);
            }
            if (z2) {
                jSONObject.put("DOWNLOAD_FOREGROUND", 1);
            }
        } catch (Exception unused) {
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] jsonData=" + jSONObject.toString());
        return jSONObject;
    }

    @TargetApi(11)
    static void b(Context context) {
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.a(context).d();
        ag.c(context);
        (Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_extension_config", 4) : context.getSharedPreferences("tbs_extension_config", 0)).edit().clear().commit();
        (Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).edit().clear().commit();
    }

    /* access modifiers changed from: private */
    public static boolean b(boolean z, boolean z2) {
        return c(z, z2, false);
    }

    private static boolean c() {
        try {
            for (String sharedTbsCoreVersion : TbsShareManager.getCoreProviderAppList()) {
                if (TbsShareManager.getSharedTbsCoreVersion(c, sharedTbsCoreVersion) > 0) {
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0201 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0204 A[SYNTHETIC, Splitter:B:81:0x0204] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(boolean r20, boolean r21, boolean r22) {
        /*
            r1 = r20
            r0 = r22
            java.util.Map<java.lang.String, java.lang.Object> r2 = com.tencent.smtt.sdk.QbSdk.n
            r3 = 0
            if (r2 == 0) goto L_0x002b
            java.util.Map<java.lang.String, java.lang.Object> r2 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.String r4 = com.tencent.smtt.sdk.QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD
            boolean r2 = r2.containsKey(r4)
            if (r2 == 0) goto L_0x002b
            java.util.Map<java.lang.String, java.lang.Object> r2 = com.tencent.smtt.sdk.QbSdk.n
            java.lang.String r4 = com.tencent.smtt.sdk.QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD
            java.lang.Object r2 = r2.get(r4)
            java.lang.String r4 = "false"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x002b
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.sendRequest] -- SET_SENDREQUEST_AND_UPLOAD is false"
        L_0x0027:
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            return r3
        L_0x002b:
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "[TbsDownloader.sendRequest]isQuery: "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r5 = " forDecoupleCore is "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r4)
            com.tencent.smtt.sdk.am r2 = com.tencent.smtt.sdk.am.a()
            android.content.Context r4 = c
            boolean r2 = r2.c(r4)
            if (r2 == 0) goto L_0x005a
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.sendRequest] -- isTbsLocalInstalled!"
            goto L_0x0027
        L_0x005a:
            android.content.Context r2 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.io.File r4 = new java.io.File
            android.content.Context r5 = c
            r6 = 1
            java.lang.String r5 = com.tencent.smtt.utils.j.a((android.content.Context) r5, (int) r6)
            android.content.Context r7 = c
            boolean r7 = getOverSea(r7)
            if (r7 == 0) goto L_0x0074
            java.lang.String r7 = "x5.oversea.tbs.org"
            goto L_0x0076
        L_0x0074:
            java.lang.String r7 = "x5.tbs.org"
        L_0x0076:
            r4.<init>(r5, r7)
            java.io.File r5 = new java.io.File
            android.content.Context r7 = c
            r8 = 2
            java.lang.String r7 = com.tencent.smtt.utils.j.a((android.content.Context) r7, (int) r8)
            android.content.Context r8 = c
            boolean r8 = getOverSea(r8)
            if (r8 == 0) goto L_0x008d
            java.lang.String r8 = "x5.oversea.tbs.org"
            goto L_0x008f
        L_0x008d:
            java.lang.String r8 = "x5.tbs.org"
        L_0x008f:
            r5.<init>(r7, r8)
            java.io.File r7 = new java.io.File
            android.content.Context r8 = c
            r9 = 3
            java.lang.String r8 = com.tencent.smtt.utils.j.a((android.content.Context) r8, (int) r9)
            android.content.Context r9 = c
            boolean r9 = getOverSea(r9)
            if (r9 == 0) goto L_0x00a6
            java.lang.String r9 = "x5.oversea.tbs.org"
            goto L_0x00a8
        L_0x00a6:
            java.lang.String r9 = "x5.tbs.org"
        L_0x00a8:
            r7.<init>(r8, r9)
            java.io.File r8 = new java.io.File
            android.content.Context r9 = c
            r10 = 4
            java.lang.String r9 = com.tencent.smtt.utils.j.a((android.content.Context) r9, (int) r10)
            android.content.Context r10 = c
            boolean r10 = getOverSea(r10)
            if (r10 == 0) goto L_0x00bf
            java.lang.String r10 = "x5.oversea.tbs.org"
            goto L_0x00c1
        L_0x00bf:
            java.lang.String r10 = "x5.tbs.org"
        L_0x00c1:
            r8.<init>(r9, r10)
            boolean r9 = r8.exists()
            if (r9 != 0) goto L_0x00e7
            boolean r9 = r7.exists()
            if (r9 == 0) goto L_0x00d4
            r7.renameTo(r8)
            goto L_0x00e7
        L_0x00d4:
            boolean r7 = r5.exists()
            if (r7 == 0) goto L_0x00de
            r5.renameTo(r8)
            goto L_0x00e7
        L_0x00de:
            boolean r5 = r4.exists()
            if (r5 == 0) goto L_0x00e7
            r4.renameTo(r8)
        L_0x00e7:
            java.lang.String r4 = e
            if (r4 != 0) goto L_0x00fd
            java.lang.String r4 = com.tencent.smtt.utils.b.a()
            e = r4
            java.util.Map<java.lang.String, java.lang.Object> r4 = r2.f9091a
            java.lang.String r5 = "device_cpuabi"
            java.lang.String r7 = e
            r4.put(r5, r7)
            r2.commit()
        L_0x00fd:
            java.lang.String r4 = e
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x016d
            r4 = 0
            java.lang.String r5 = "i686|mips|x86_64"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)     // Catch:{ Exception -> 0x0113 }
            java.lang.String r7 = e     // Catch:{ Exception -> 0x0113 }
            java.util.regex.Matcher r5 = r5.matcher(r7)     // Catch:{ Exception -> 0x0113 }
            r4 = r5
        L_0x0113:
            if (r4 == 0) goto L_0x016d
            boolean r4 = r4.find()
            if (r4 == 0) goto L_0x016d
            android.content.Context r4 = c
            boolean r4 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r4)
            r5 = -205(0xffffffffffffff33, float:NaN)
            r7 = -104(0xffffffffffffff98, float:NaN)
            if (r4 == 0) goto L_0x0162
            android.content.Context r4 = c
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r4)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r4 = r4.a()
            if (r1 == 0) goto L_0x013a
            r2.setDownloadInterruptCode(r7)
            r4.setErrorCode(r7)
            goto L_0x0140
        L_0x013a:
            r2.setDownloadInterruptCode(r5)
            r4.setErrorCode(r5)
        L_0x0140:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "mycpu is "
            r5.append(r7)
            java.lang.String r7 = e
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r4.setFailDetail((java.lang.String) r5)
            android.content.Context r5 = c
            com.tencent.smtt.sdk.TbsLogReport r5 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r5)
            com.tencent.smtt.sdk.TbsLogReport$EventType r7 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
            r5.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r7, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r4)
            goto L_0x016b
        L_0x0162:
            if (r1 == 0) goto L_0x0168
            r2.setDownloadInterruptCode(r7)
            goto L_0x016b
        L_0x0168:
            r2.setDownloadInterruptCode(r5)
        L_0x016b:
            r4 = 1
            goto L_0x016e
        L_0x016d:
            r4 = 0
        L_0x016e:
            org.json.JSONObject r5 = b(r20, r21, r22)
            r7 = -1
            java.lang.String r8 = "TBSV"
            int r8 = r5.getInt(r8)     // Catch:{ Exception -> 0x017a }
            goto L_0x017b
        L_0x017a:
            r8 = -1
        L_0x017b:
            if (r4 != 0) goto L_0x017f
            if (r8 == r7) goto L_0x0202
        L_0x017f:
            long r9 = java.lang.System.currentTimeMillis()
            android.content.Context r11 = c
            boolean r11 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r11)
            if (r11 == 0) goto L_0x01b9
            android.content.SharedPreferences r11 = r2.mPreferences
            java.lang.String r12 = "request_fail"
            r13 = 0
            long r11 = r11.getLong(r12, r13)
            android.content.SharedPreferences r15 = r2.mPreferences
            java.lang.String r6 = "count_request_fail_in_24hours"
            long r13 = r15.getLong(r6, r13)
            long r11 = r9 - r11
            long r16 = r2.getRetryInterval()
            r18 = 1000(0x3e8, double:4.94E-321)
            long r16 = r16 * r18
            int r6 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1))
            r11 = 1
            if (r6 >= 0) goto L_0x01ae
            long r11 = r11 + r13
        L_0x01ae:
            java.util.Map<java.lang.String, java.lang.Object> r6 = r2.f9091a
            java.lang.String r13 = "count_request_fail_in_24hours"
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            r6.put(r13, r11)
        L_0x01b9:
            java.util.Map<java.lang.String, java.lang.Object> r6 = r2.f9091a
            java.lang.String r11 = "last_check"
            java.lang.Long r12 = java.lang.Long.valueOf(r9)
            r6.put(r11, r12)
            java.util.Map<java.lang.String, java.lang.Object> r6 = r2.f9091a
            java.lang.String r11 = "request_fail"
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            r6.put(r11, r9)
            java.util.Map<java.lang.String, java.lang.Object> r6 = r2.f9091a
            java.lang.String r9 = "app_versionname"
            android.content.Context r10 = c
            java.lang.String r10 = com.tencent.smtt.utils.b.a((android.content.Context) r10)
            r6.put(r9, r10)
            java.util.Map<java.lang.String, java.lang.Object> r6 = r2.f9091a
            java.lang.String r9 = "app_versioncode"
            android.content.Context r10 = c
            int r10 = com.tencent.smtt.utils.b.b(r10)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r6.put(r9, r10)
            java.util.Map<java.lang.String, java.lang.Object> r6 = r2.f9091a
            java.lang.String r9 = "app_metadata"
            android.content.Context r10 = c
            java.lang.String r11 = "com.tencent.mm.BuildInfo.CLIENT_VERSION"
            java.lang.String r10 = com.tencent.smtt.utils.b.a((android.content.Context) r10, (java.lang.String) r11)
            r6.put(r9, r10)
            r2.commit()
            if (r4 == 0) goto L_0x0202
            return r3
        L_0x0202:
            if (r8 == r7) goto L_0x028e
            android.content.Context r4 = c     // Catch:{ Throwable -> 0x027f }
            if (r4 == 0) goto L_0x0245
            java.lang.String r4 = "com.tencent.mm"
            android.content.Context r6 = c     // Catch:{ Throwable -> 0x027f }
            android.content.pm.ApplicationInfo r6 = r6.getApplicationInfo()     // Catch:{ Throwable -> 0x027f }
            java.lang.String r6 = r6.packageName     // Catch:{ Throwable -> 0x027f }
            boolean r4 = r4.equals(r6)     // Catch:{ Throwable -> 0x027f }
            if (r4 == 0) goto L_0x0245
            if (r0 != 0) goto L_0x0245
            java.lang.String r0 = "FUNCTION"
            int r0 = r5.getInt(r0)     // Catch:{ Throwable -> 0x027f }
            if (r0 == 0) goto L_0x022b
            java.lang.String r0 = "FUNCTION"
            int r0 = r5.getInt(r0)     // Catch:{ Throwable -> 0x027f }
            r4 = 1
            if (r0 != r4) goto L_0x0245
        L_0x022b:
            android.content.Context r0 = c     // Catch:{ Throwable -> 0x027f }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r0)     // Catch:{ Throwable -> 0x027f }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r0.a()     // Catch:{ Throwable -> 0x027f }
            r4 = 127(0x7f, float:1.78E-43)
            r0.setErrorCode(r4)     // Catch:{ Throwable -> 0x027f }
            android.content.Context r4 = c     // Catch:{ Throwable -> 0x027f }
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r4)     // Catch:{ Throwable -> 0x027f }
            com.tencent.smtt.sdk.TbsLogReport$EventType r6 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD     // Catch:{ Throwable -> 0x027f }
            r4.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r6, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r0)     // Catch:{ Throwable -> 0x027f }
        L_0x0245:
            android.content.Context r0 = c     // Catch:{ Throwable -> 0x027f }
            com.tencent.smtt.utils.s r0 = com.tencent.smtt.utils.s.a(r0)     // Catch:{ Throwable -> 0x027f }
            java.lang.String r0 = r0.d()     // Catch:{ Throwable -> 0x027f }
            java.lang.String r4 = "TbsDownload"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x027f }
            r6.<init>()     // Catch:{ Throwable -> 0x027f }
            java.lang.String r7 = "[TbsDownloader.sendRequest] postUrl="
            r6.append(r7)     // Catch:{ Throwable -> 0x027f }
            r6.append(r0)     // Catch:{ Throwable -> 0x027f }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x027f }
            com.tencent.smtt.utils.TbsLog.i(r4, r6)     // Catch:{ Throwable -> 0x027f }
            java.lang.String r4 = r5.toString()     // Catch:{ Throwable -> 0x027f }
            java.lang.String r5 = "utf-8"
            byte[] r4 = r4.getBytes(r5)     // Catch:{ Throwable -> 0x027f }
            com.tencent.smtt.sdk.ak r5 = new com.tencent.smtt.sdk.ak     // Catch:{ Throwable -> 0x027f }
            r5.<init>(r2, r1)     // Catch:{ Throwable -> 0x027f }
            java.lang.String r0 = com.tencent.smtt.utils.m.a(r0, r4, r5, r3)     // Catch:{ Throwable -> 0x027f }
            r4 = r21
            boolean r0 = a(r0, r8, r1, r4)     // Catch:{ Throwable -> 0x027f }
            goto L_0x028f
        L_0x027f:
            r0 = move-exception
            r0.printStackTrace()
            if (r1 == 0) goto L_0x028b
            r0 = -106(0xffffffffffffff96, float:NaN)
        L_0x0287:
            r2.setDownloadInterruptCode(r0)
            goto L_0x028e
        L_0x028b:
            r0 = -206(0xffffffffffffff32, float:NaN)
            goto L_0x0287
        L_0x028e:
            r0 = 0
        L_0x028f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.c(boolean, boolean, boolean):boolean");
    }

    private static synchronized void d() {
        synchronized (TbsDownloader.class) {
            if (h == null) {
                h = al.a();
                try {
                    g = new ag(c);
                    d = new aj(h.getLooper());
                } catch (Exception unused) {
                    i = true;
                    TbsLog.e(LOGTAG, "TbsApkDownloader init has Exception");
                }
            }
        }
    }

    private static boolean e() {
        try {
            return TbsDownloadConfig.getInstance(c).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, "").equals(f().toString());
        } catch (Exception unused) {
            return false;
        }
    }

    private static JSONArray f() {
        String[] strArr;
        boolean z;
        boolean z2;
        if (!TbsShareManager.isThirdPartyApp(c)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        boolean z3 = true;
        if (QbSdk.getOnlyDownload()) {
            strArr = new String[]{c.getApplicationContext().getPackageName()};
        } else {
            strArr = TbsShareManager.getCoreProviderAppList();
            String packageName = c.getApplicationContext().getPackageName();
            if (packageName.equals(TbsShareManager.f(c))) {
                int length = strArr.length;
                String[] strArr2 = new String[(length + 1)];
                System.arraycopy(strArr, 0, strArr2, 0, length);
                strArr2[length] = packageName;
                strArr = strArr2;
            }
        }
        for (String str : strArr) {
            int sharedTbsCoreVersion = TbsShareManager.getSharedTbsCoreVersion(c, str);
            if (sharedTbsCoreVersion > 0) {
                Context a2 = TbsShareManager.a(c, str);
                if (a2 == null || am.a().f(a2)) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z2 = false;
                            break;
                        } else if (jSONArray.optInt(i2) == sharedTbsCoreVersion) {
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z2) {
                        jSONArray.put(sharedTbsCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str);
                }
            }
        }
        for (String str2 : strArr) {
            int coreShareDecoupleCoreVersion = TbsShareManager.getCoreShareDecoupleCoreVersion(c, str2);
            if (coreShareDecoupleCoreVersion > 0) {
                Context a3 = TbsShareManager.a(c, str2);
                if (a3 == null || am.a().f(a3)) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (jSONArray.optInt(i3) == coreShareDecoupleCoreVersion) {
                            z = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(coreShareDecoupleCoreVersion);
                    }
                } else {
                    TbsLog.e(LOGTAG, "host check failed,packageName = " + str2);
                }
            }
        }
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            int a4 = am.a().a(TbsShareManager.getHostCorePathAppDefined());
            int i4 = 0;
            while (true) {
                if (i4 >= jSONArray.length()) {
                    z3 = false;
                    break;
                } else if (jSONArray.optInt(i4) == a4) {
                    break;
                } else {
                    i4++;
                }
            }
            if (!z3) {
                jSONArray.put(a4);
            }
        }
        return jSONArray;
    }

    private static boolean g() {
        int i2;
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= instance.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            i2 = AuthCode.r;
        } else if (instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= instance.getDownloadFailedMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            i2 = AuthCode.s;
        } else if (!j.b(c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            i2 = -117;
        } else {
            if (System.currentTimeMillis() - instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0) <= 86400000) {
                long j2 = instance.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0);
                TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j2);
                if (j2 >= instance.getDownloadMaxflow()) {
                    TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    i2 = -120;
                }
            }
            return true;
        }
        instance.setDownloadInterruptCode(i2);
        return false;
    }

    public static int getCoreShareDecoupleCoreVersion() {
        return am.a().h(c);
    }

    public static int getCoreShareDecoupleCoreVersionByContext(Context context) {
        return am.a().h(context);
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!k) {
                k = true;
                TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
                if (instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                    j = instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + j);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + j);
            }
            z = j;
        }
        return z;
    }

    public static long getRetryIntervalInSeconds() {
        return l;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return h;
    }

    private static JSONArray h() {
        boolean z;
        JSONArray jSONArray = new JSONArray();
        for (String a2 : TbsShareManager.getCoreProviderAppList()) {
            File file = new File(j.a(c, a2, 4, false), getOverSea(c) ? "x5.oversea.tbs.org" : "x5.tbs.org");
            if (file.exists()) {
                long a3 = (long) a.a(c, file);
                if (a3 > 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jSONArray.length()) {
                            z = false;
                            break;
                        } else if (((long) jSONArray.optInt(i2)) == a3) {
                            z = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (!z) {
                        jSONArray.put(a3);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static boolean isDownloadForeground() {
        return g != null && g.e();
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading] is " + f9092a);
            z = f9092a;
        }
        return z;
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, (TbsDownloaderCallback) null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        boolean z3;
        boolean z4;
        int i2;
        String str;
        StringBuilder sb;
        String str2;
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] oversea=" + z + ",isDownloadForeground=" + z2);
        TbsLog.initIfNeed(context);
        if (am.b) {
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            str = LOGTAG;
            sb = new StringBuilder();
            str2 = "[TbsDownloader.needDownload]#1,return ";
        } else {
            TbsLog.app_extra(LOGTAG, context);
            c = context.getApplicationContext();
            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
            instance.setDownloadInterruptCode(-100);
            if (!a(c, z, tbsDownloaderCallback)) {
                str = LOGTAG;
                sb = new StringBuilder();
                str2 = "[TbsDownloader.needDownload]#2,return ";
            } else {
                d();
                if (i) {
                    if (tbsDownloaderCallback != null) {
                        tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                    }
                    instance.setDownloadInterruptCode(-105);
                    str = LOGTAG;
                    sb = new StringBuilder();
                    str2 = "[TbsDownloader.needDownload]#3,return ";
                } else {
                    boolean a2 = a(c, z2, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needSendRequest=" + a2);
                    if (a2) {
                        a(z2, tbsDownloaderCallback);
                        instance.setDownloadInterruptCode(AuthCode.q);
                    }
                    d.removeMessages(102);
                    Message.obtain(d, 102).sendToTarget();
                    if (QbSdk.c || !TbsShareManager.isThirdPartyApp(context)) {
                        z3 = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
                        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + z3);
                        z4 = (z3 || TbsShareManager.isThirdPartyApp(context)) ? instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
                    } else {
                        z4 = false;
                        z3 = false;
                    }
                    TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#4,needDownload=" + z4 + ",hasNeedDownloadKey=" + z3);
                    if (!z4) {
                        int m = am.a().m(c);
                        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#7,tbsLocalVersion=" + m + ",needSendRequest=" + a2);
                        if (a2 || m <= 0) {
                            d.removeMessages(103);
                            ((m > 0 || a2) ? Message.obtain(d, 103, 1, 0, c) : Message.obtain(d, 103, 0, 0, c)).sendToTarget();
                            i2 = -121;
                        } else {
                            i2 = -119;
                        }
                        instance.setDownloadInterruptCode(i2);
                    } else if (!g()) {
                        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#5,set needDownload = false");
                        z4 = false;
                    } else {
                        instance.setDownloadInterruptCode(-118);
                        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#6");
                    }
                    if (!a2 && tbsDownloaderCallback != null) {
                        tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                    }
                    TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] needDownload=" + z4);
                    return z4;
                }
            }
        }
        sb.append(str2);
        sb.append(false);
        TbsLog.i(str, sb.toString());
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0044, code lost:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(c).mPreferences.getInt(com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean needDownloadDecoupleCore() {
        /*
            android.content.Context r0 = c
            boolean r0 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r0)
            r1 = 0
            if (r0 == 0) goto L_0x000a
            return r1
        L_0x000a:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r2 = "tbs_downloaddecouplecore"
            int r0 = r0.getInt(r2, r1)
            r2 = 1
            if (r0 != r2) goto L_0x001c
            return r1
        L_0x001c:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r3 = "last_download_decouple_core"
            r4 = 0
            long r3 = r0.getLong(r3, r4)
            long r5 = java.lang.System.currentTimeMillis()
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            long r7 = r0.getRetryInterval()
            long r5 = r5 - r3
            r3 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0044
            return r1
        L_0x0044:
            android.content.Context r0 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r3 = "tbs_decouplecoreversion"
            int r0 = r0.getInt(r3, r1)
            if (r0 <= 0) goto L_0x0071
            com.tencent.smtt.sdk.am r3 = com.tencent.smtt.sdk.am.a()
            android.content.Context r4 = c
            int r3 = r3.h(r4)
            if (r0 == r3) goto L_0x0071
            android.content.Context r3 = c
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            android.content.SharedPreferences r3 = r3.mPreferences
            java.lang.String r4 = "tbs_download_version"
            int r3 = r3.getInt(r4, r1)
            if (r3 == r0) goto L_0x0071
            return r2
        L_0x0071:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.needDownloadDecoupleCore():boolean");
    }

    public static boolean needSendRequest(Context context, boolean z) {
        c = context.getApplicationContext();
        TbsLog.initIfNeed(context);
        if (!a(c, z, (TbsDownloaderCallback) null)) {
            return false;
        }
        int m = am.a().m(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] localTbsVersion=" + m);
        if (m > 0) {
            return false;
        }
        boolean z2 = true;
        if (a(c, false, true)) {
            return true;
        }
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(c);
        boolean contains = instance.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] hasNeedDownloadKey=" + contains);
        boolean z3 = !contains ? true : instance.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] needDownload=" + z3);
        if (!z3 || !g()) {
            z2 = false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needSendRequest] ret=" + z2);
        return z2;
    }

    public static void setRetryIntervalInSeconds(Context context, long j2) {
        if (context != null) {
            if (context.getApplicationInfo().packageName.equals("com.tencent.qqlive")) {
                l = j2;
            }
            TbsLog.i(LOGTAG, "mRetryIntervalInSeconds is " + l);
        }
    }

    public static boolean startDecoupleCoreIfNeeded() {
        StringBuilder sb;
        String str;
        int i2;
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded ");
        if (TbsShareManager.isThirdPartyApp(c)) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #1");
        if (TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #2");
        long j2 = TbsDownloadConfig.getInstance(c).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0);
        if (System.currentTimeMillis() - j2 < TbsDownloadConfig.getInstance(c).getRetryInterval() * 1000) {
            return false;
        }
        TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #3");
        int i3 = TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
        if (i3 <= 0 || i3 == am.a().h(c)) {
            str = LOGTAG;
            sb = new StringBuilder();
            sb.append("startDecoupleCoreIfNeeded no need, deCoupleCoreVersion is ");
            sb.append(i3);
            sb.append(" getTbsCoreShareDecoupleCoreVersion is ");
            i2 = am.a().h(c);
        } else if (TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i3 || TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0) == 1) {
            TbsLog.i(LOGTAG, "startDecoupleCoreIfNeeded #4");
            f9092a = true;
            d.removeMessages(108);
            Message obtain = Message.obtain(d, 108, QbSdk.m);
            obtain.arg1 = 0;
            obtain.sendToTarget();
            TbsDownloadConfig.getInstance(c).f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, Long.valueOf(System.currentTimeMillis()));
            return true;
        } else {
            str = LOGTAG;
            sb = new StringBuilder();
            sb.append("startDecoupleCoreIfNeeded no need, KEY_TBS_DOWNLOAD_V is ");
            sb.append(TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
            sb.append(" deCoupleCoreVersion is ");
            sb.append(i3);
            sb.append(" KEY_TBS_DOWNLOAD_V_TYPE is ");
            i2 = TbsDownloadConfig.getInstance(c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, 0);
        }
        sb.append(i2);
        TbsLog.i(str, sb.toString());
        return false;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        synchronized (TbsDownloader.class) {
            if (context != null) {
                try {
                    if ("com.tencent.mm".equals(context.getApplicationInfo().packageName)) {
                        TbsLogReport.TbsLogInfo a2 = TbsLogReport.a(c).a();
                        a2.setErrorCode(126);
                        TbsLogReport.a(c).a(TbsLogReport.EventType.TYPE_DOWNLOAD, a2);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            TbsLog.i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + c);
            if (!am.b) {
                f9092a = true;
                c = context.getApplicationContext();
                TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(OTAErrorCode.m);
                if (Build.VERSION.SDK_INT < 8) {
                    QbSdk.m.onDownloadFinish(110);
                    TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(OTAErrorCode.n);
                    return;
                }
                d();
                if (i) {
                    QbSdk.m.onDownloadFinish(121);
                    TbsDownloadConfig.getInstance(c).setDownloadInterruptCode(-202);
                    return;
                }
                if (z) {
                    stopDownload();
                }
                d.removeMessages(101);
                d.removeMessages(100);
                Message obtain = Message.obtain(d, 101, QbSdk.m);
                obtain.arg1 = z ? 1 : 0;
                obtain.sendToTarget();
            }
        }
    }

    public static void stopDownload() {
        if (!i) {
            TbsLog.i(LOGTAG, "[TbsDownloader.stopDownload]");
            if (g != null) {
                g.c();
            }
            if (d != null) {
                d.removeMessages(100);
                d.removeMessages(101);
                d.removeMessages(108);
            }
        }
    }
}
