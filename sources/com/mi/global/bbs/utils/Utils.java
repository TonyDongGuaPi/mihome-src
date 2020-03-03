package com.mi.global.bbs.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.alipay.sdk.sys.a;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.model.PackageInfo;
import com.mi.global.bbs.model.SyncModel;
import com.mi.global.bbs.request.ServiceConnection;
import com.mi.global.bbs.utils.Constants;
import com.mi.log.LogUtil;
import com.mi.util.Utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.JSONObject;

public class Utils {
    /* access modifiers changed from: private */
    public static final String TAG = "Utils";

    public static final class Network extends Utils.Network {
    }

    public static final class Preference extends Utils.Preference {
        public static void setDefaultBooleanValue(Context context, String str, boolean z) {
            SharedPreferences defaultSharedPreferences;
            if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && !defaultSharedPreferences.contains(str)) {
                setBooleanPref(context, str, z);
            }
        }
    }

    public static final class IntentHelper {
        public static Intent newEmailIntent(Context context, String str, String str2, String str3, String str4) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
            intent.putExtra("android.intent.extra.TEXT", str3);
            intent.putExtra("android.intent.extra.SUBJECT", str2);
            intent.putExtra("android.intent.extra.CC", str4);
            intent.setType("message/rfc822");
            return intent;
        }
    }

    public static final class Files {
        public static long getFolderSize(File file) {
            long j;
            File[] listFiles = file.listFiles();
            long j2 = 0;
            if (listFiles == null || listFiles.length == 0) {
                return 0;
            }
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    j = getFolderSize(listFiles[i]);
                } else {
                    j = listFiles[i].length();
                }
                j2 += j;
            }
            return j2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0097, code lost:
            if (com.mi.global.bbs.BBSApplication.isUserDebug() == false) goto L_0x00c8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0099, code lost:
            r7.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x00c5, code lost:
            if (com.mi.global.bbs.BBSApplication.isUserDebug() == false) goto L_0x00c8;
         */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0079 A[Catch:{ all -> 0x00c9 }] */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x007e A[SYNTHETIC, Splitter:B:47:0x007e] */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x008e A[SYNTHETIC, Splitter:B:54:0x008e] */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x00a7 A[Catch:{ all -> 0x00c9 }] */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x00ac A[SYNTHETIC, Splitter:B:67:0x00ac] */
        /* JADX WARNING: Removed duplicated region for block: B:74:0x00bc A[SYNTHETIC, Splitter:B:74:0x00bc] */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x00cc A[SYNTHETIC, Splitter:B:82:0x00cc] */
        /* JADX WARNING: Removed duplicated region for block: B:89:0x00dc A[SYNTHETIC, Splitter:B:89:0x00dc] */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:62:0x00a1=Splitter:B:62:0x00a1, B:42:0x0073=Splitter:B:42:0x0073} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static long getFileString(java.lang.String r7) {
            /*
                boolean r0 = android.text.TextUtils.isEmpty(r7)
                r1 = -1
                if (r0 == 0) goto L_0x0009
                return r1
            L_0x0009:
                java.io.File r0 = new java.io.File
                r0.<init>(r7)
                boolean r7 = r0.exists()
                if (r7 != 0) goto L_0x0015
                return r1
            L_0x0015:
                r7 = 0
                java.io.FileReader r3 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x009d, IOException -> 0x006f, all -> 0x0069 }
                r3.<init>(r0)     // Catch:{ FileNotFoundException -> 0x009d, IOException -> 0x006f, all -> 0x0069 }
                java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0064, IOException -> 0x005f, all -> 0x0059 }
                r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0064, IOException -> 0x005f, all -> 0x0059 }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054 }
                r7.<init>()     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054 }
            L_0x0025:
                java.lang.String r4 = r0.readLine()     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054 }
                if (r4 == 0) goto L_0x002f
                r7.append(r4)     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054 }
                goto L_0x0025
            L_0x002f:
                java.lang.String r7 = r7.toString()     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054 }
                long r4 = java.lang.Long.parseLong(r7)     // Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x0054 }
                r3.close()     // Catch:{ IOException -> 0x003b }
                goto L_0x0045
            L_0x003b:
                r7 = move-exception
                boolean r1 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r1 == 0) goto L_0x0045
                r7.printStackTrace()
            L_0x0045:
                r0.close()     // Catch:{ IOException -> 0x0049 }
                goto L_0x0053
            L_0x0049:
                r7 = move-exception
                boolean r0 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r0 == 0) goto L_0x0053
                r7.printStackTrace()
            L_0x0053:
                return r4
            L_0x0054:
                r7 = move-exception
                goto L_0x0073
            L_0x0056:
                r7 = move-exception
                goto L_0x00a1
            L_0x0059:
                r0 = move-exception
                r6 = r0
                r0 = r7
                r7 = r6
                goto L_0x00ca
            L_0x005f:
                r0 = move-exception
                r6 = r0
                r0 = r7
                r7 = r6
                goto L_0x0073
            L_0x0064:
                r0 = move-exception
                r6 = r0
                r0 = r7
                r7 = r6
                goto L_0x00a1
            L_0x0069:
                r0 = move-exception
                r3 = r7
                r7 = r0
                r0 = r3
                goto L_0x00ca
            L_0x006f:
                r0 = move-exception
                r3 = r7
                r7 = r0
                r0 = r3
            L_0x0073:
                boolean r4 = com.mi.global.bbs.BBSApplication.isUserDebug()     // Catch:{ all -> 0x00c9 }
                if (r4 == 0) goto L_0x007c
                r7.printStackTrace()     // Catch:{ all -> 0x00c9 }
            L_0x007c:
                if (r3 == 0) goto L_0x008c
                r3.close()     // Catch:{ IOException -> 0x0082 }
                goto L_0x008c
            L_0x0082:
                r7 = move-exception
                boolean r3 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r3 == 0) goto L_0x008c
                r7.printStackTrace()
            L_0x008c:
                if (r0 == 0) goto L_0x00c8
                r0.close()     // Catch:{ IOException -> 0x0092 }
                goto L_0x00c8
            L_0x0092:
                r7 = move-exception
                boolean r0 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r0 == 0) goto L_0x00c8
            L_0x0099:
                r7.printStackTrace()
                goto L_0x00c8
            L_0x009d:
                r0 = move-exception
                r3 = r7
                r7 = r0
                r0 = r3
            L_0x00a1:
                boolean r4 = com.mi.global.bbs.BBSApplication.isUserDebug()     // Catch:{ all -> 0x00c9 }
                if (r4 == 0) goto L_0x00aa
                r7.printStackTrace()     // Catch:{ all -> 0x00c9 }
            L_0x00aa:
                if (r3 == 0) goto L_0x00ba
                r3.close()     // Catch:{ IOException -> 0x00b0 }
                goto L_0x00ba
            L_0x00b0:
                r7 = move-exception
                boolean r3 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r3 == 0) goto L_0x00ba
                r7.printStackTrace()
            L_0x00ba:
                if (r0 == 0) goto L_0x00c8
                r0.close()     // Catch:{ IOException -> 0x00c0 }
                goto L_0x00c8
            L_0x00c0:
                r7 = move-exception
                boolean r0 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r0 == 0) goto L_0x00c8
                goto L_0x0099
            L_0x00c8:
                return r1
            L_0x00c9:
                r7 = move-exception
            L_0x00ca:
                if (r3 == 0) goto L_0x00da
                r3.close()     // Catch:{ IOException -> 0x00d0 }
                goto L_0x00da
            L_0x00d0:
                r1 = move-exception
                boolean r2 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r2 == 0) goto L_0x00da
                r1.printStackTrace()
            L_0x00da:
                if (r0 == 0) goto L_0x00ea
                r0.close()     // Catch:{ IOException -> 0x00e0 }
                goto L_0x00ea
            L_0x00e0:
                r0 = move-exception
                boolean r1 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r1 == 0) goto L_0x00ea
                r0.printStackTrace()
            L_0x00ea:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.global.bbs.utils.Utils.Files.getFileString(java.lang.String):long");
        }

        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r1v1, types: [java.io.OutputStream] */
        /* JADX WARNING: type inference failed for: r1v2 */
        /* JADX WARNING: type inference failed for: r1v3, types: [java.io.InputStream] */
        /* JADX WARNING: type inference failed for: r1v4 */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b8, code lost:
            r4.printStackTrace();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
            return;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00d1 A[Catch:{ all -> 0x00f3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00d6 A[SYNTHETIC, Splitter:B:59:0x00d6] */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x00e6 A[SYNTHETIC, Splitter:B:66:0x00e6] */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x00f8 A[SYNTHETIC, Splitter:B:75:0x00f8] */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x0108 A[SYNTHETIC, Splitter:B:82:0x0108] */
        /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static void copyAssetFilePremeditatedly(android.content.res.AssetManager r4, java.lang.String r5, java.lang.String r6) {
            /*
                r0 = 1024(0x400, float:1.435E-42)
                byte[] r0 = new byte[r0]
                r1 = 0
                java.io.InputStream r4 = r4.open(r5)     // Catch:{ IOException -> 0x00c9, all -> 0x00c6 }
                java.io.File r5 = new java.io.File     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                r5.<init>(r6)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                boolean r6 = r5.exists()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                if (r6 != 0) goto L_0x008d
                java.io.File r6 = r5.getParentFile()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                boolean r6 = r6.exists()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                if (r6 != 0) goto L_0x0051
                java.io.File r6 = r5.getParentFile()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                boolean r6 = r6.mkdirs()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                if (r6 != 0) goto L_0x0051
                boolean r6 = com.mi.global.bbs.BBSApplication.DEBUG     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                if (r6 == 0) goto L_0x0051
                java.lang.String r6 = com.mi.global.bbs.utils.Utils.TAG     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                r2.<init>()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r3 = "Create directory "
                r2.append(r3)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.io.File r3 = r5.getParentFile()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r3 = r3.getName()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                r2.append(r3)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r3 = " failed"
                r2.append(r3)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                android.util.Log.e(r6, r2)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
            L_0x0051:
                boolean r6 = r5.createNewFile()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                if (r6 != 0) goto L_0x008d
                boolean r6 = com.mi.global.bbs.BBSApplication.DEBUG     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                if (r6 == 0) goto L_0x007c
                java.lang.String r6 = com.mi.global.bbs.utils.Utils.TAG     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                r0.<init>()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r2 = "Create file "
                r0.append(r2)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r5 = r5.getName()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                r0.append(r5)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r5 = " failed"
                r0.append(r5)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                java.lang.String r5 = r0.toString()     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                android.util.Log.e(r6, r5)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
            L_0x007c:
                if (r4 == 0) goto L_0x008c
                r4.close()     // Catch:{ IOException -> 0x0082 }
                goto L_0x008c
            L_0x0082:
                r4 = move-exception
                boolean r5 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r5 == 0) goto L_0x008c
                r4.printStackTrace()
            L_0x008c:
                return
            L_0x008d:
                java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
                r6.<init>(r5)     // Catch:{ IOException -> 0x00c2, all -> 0x00c0 }
            L_0x0092:
                int r5 = r4.read(r0)     // Catch:{ IOException -> 0x00be, all -> 0x00bc }
                if (r5 <= 0) goto L_0x009d
                r1 = 0
                r6.write(r0, r1, r5)     // Catch:{ IOException -> 0x00be, all -> 0x00bc }
                goto L_0x0092
            L_0x009d:
                if (r4 == 0) goto L_0x00ad
                r4.close()     // Catch:{ IOException -> 0x00a3 }
                goto L_0x00ad
            L_0x00a3:
                r4 = move-exception
                boolean r5 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r5 == 0) goto L_0x00ad
                r4.printStackTrace()
            L_0x00ad:
                r6.close()     // Catch:{ IOException -> 0x00b1 }
                goto L_0x00f2
            L_0x00b1:
                r4 = move-exception
                boolean r5 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r5 == 0) goto L_0x00f2
            L_0x00b8:
                r4.printStackTrace()
                goto L_0x00f2
            L_0x00bc:
                r5 = move-exception
                goto L_0x00f5
            L_0x00be:
                r5 = move-exception
                goto L_0x00c4
            L_0x00c0:
                r5 = move-exception
                goto L_0x00f6
            L_0x00c2:
                r5 = move-exception
                r6 = r1
            L_0x00c4:
                r1 = r4
                goto L_0x00cb
            L_0x00c6:
                r5 = move-exception
                r4 = r1
                goto L_0x00f6
            L_0x00c9:
                r5 = move-exception
                r6 = r1
            L_0x00cb:
                boolean r4 = com.mi.global.bbs.BBSApplication.isUserDebug()     // Catch:{ all -> 0x00f3 }
                if (r4 == 0) goto L_0x00d4
                r5.printStackTrace()     // Catch:{ all -> 0x00f3 }
            L_0x00d4:
                if (r1 == 0) goto L_0x00e4
                r1.close()     // Catch:{ IOException -> 0x00da }
                goto L_0x00e4
            L_0x00da:
                r4 = move-exception
                boolean r5 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r5 == 0) goto L_0x00e4
                r4.printStackTrace()
            L_0x00e4:
                if (r6 == 0) goto L_0x00f2
                r6.close()     // Catch:{ IOException -> 0x00ea }
                goto L_0x00f2
            L_0x00ea:
                r4 = move-exception
                boolean r5 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r5 == 0) goto L_0x00f2
                goto L_0x00b8
            L_0x00f2:
                return
            L_0x00f3:
                r5 = move-exception
                r4 = r1
            L_0x00f5:
                r1 = r6
            L_0x00f6:
                if (r4 == 0) goto L_0x0106
                r4.close()     // Catch:{ IOException -> 0x00fc }
                goto L_0x0106
            L_0x00fc:
                r4 = move-exception
                boolean r6 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r6 == 0) goto L_0x0106
                r4.printStackTrace()
            L_0x0106:
                if (r1 == 0) goto L_0x0116
                r1.close()     // Catch:{ IOException -> 0x010c }
                goto L_0x0116
            L_0x010c:
                r4 = move-exception
                boolean r6 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r6 == 0) goto L_0x0116
                r4.printStackTrace()
            L_0x0116:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.global.bbs.utils.Utils.Files.copyAssetFilePremeditatedly(android.content.res.AssetManager, java.lang.String, java.lang.String):void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:46:0x0073 A[SYNTHETIC, Splitter:B:46:0x0073] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x0083 A[SYNTHETIC, Splitter:B:53:0x0083] */
        /* JADX WARNING: Removed duplicated region for block: B:62:0x0095 A[SYNTHETIC, Splitter:B:62:0x0095] */
        /* JADX WARNING: Removed duplicated region for block: B:69:0x00a5 A[SYNTHETIC, Splitter:B:69:0x00a5] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static byte[] getFileBytes(java.lang.String r6) {
            /*
                boolean r0 = android.text.TextUtils.isEmpty(r6)
                r1 = 0
                if (r0 == 0) goto L_0x0008
                return r1
            L_0x0008:
                java.io.File r0 = new java.io.File
                r0.<init>(r6)
                boolean r6 = r0.exists()
                if (r6 == 0) goto L_0x00b4
                long r2 = r0.length()
                r4 = 0
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 > 0) goto L_0x001f
                goto L_0x00b4
            L_0x001f:
                java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006b, all -> 0x0067 }
                r6.<init>(r0)     // Catch:{ Exception -> 0x006b, all -> 0x0067 }
                r0 = 1024(0x400, float:1.435E-42)
                byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
                r2 = r1
            L_0x0029:
                int r3 = r6.read(r0)     // Catch:{ Exception -> 0x005f }
                if (r3 <= 0) goto L_0x003c
                if (r2 != 0) goto L_0x0037
                java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x005f }
                r4.<init>()     // Catch:{ Exception -> 0x005f }
                r2 = r4
            L_0x0037:
                r4 = 0
                r2.write(r0, r4, r3)     // Catch:{ Exception -> 0x005f }
                goto L_0x0029
            L_0x003c:
                byte[] r0 = r2.toByteArray()     // Catch:{ Exception -> 0x005f }
                if (r2 == 0) goto L_0x0050
                r2.close()     // Catch:{ IOException -> 0x0046 }
                goto L_0x0050
            L_0x0046:
                r1 = move-exception
                boolean r2 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r2 == 0) goto L_0x0050
                r1.printStackTrace()
            L_0x0050:
                r6.close()     // Catch:{ IOException -> 0x0054 }
                goto L_0x005e
            L_0x0054:
                r6 = move-exception
                boolean r1 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r1 == 0) goto L_0x005e
                r6.printStackTrace()
            L_0x005e:
                return r0
            L_0x005f:
                r0 = move-exception
                goto L_0x006e
            L_0x0061:
                r0 = move-exception
                r2 = r1
                goto L_0x0093
            L_0x0064:
                r0 = move-exception
                r2 = r1
                goto L_0x006e
            L_0x0067:
                r0 = move-exception
                r6 = r1
                r2 = r6
                goto L_0x0093
            L_0x006b:
                r0 = move-exception
                r6 = r1
                r2 = r6
            L_0x006e:
                r0.printStackTrace()     // Catch:{ all -> 0x0092 }
                if (r2 == 0) goto L_0x0081
                r2.close()     // Catch:{ IOException -> 0x0077 }
                goto L_0x0081
            L_0x0077:
                r0 = move-exception
                boolean r2 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r2 == 0) goto L_0x0081
                r0.printStackTrace()
            L_0x0081:
                if (r6 == 0) goto L_0x0091
                r6.close()     // Catch:{ IOException -> 0x0087 }
                goto L_0x0091
            L_0x0087:
                r6 = move-exception
                boolean r0 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r0 == 0) goto L_0x0091
                r6.printStackTrace()
            L_0x0091:
                return r1
            L_0x0092:
                r0 = move-exception
            L_0x0093:
                if (r2 == 0) goto L_0x00a3
                r2.close()     // Catch:{ IOException -> 0x0099 }
                goto L_0x00a3
            L_0x0099:
                r1 = move-exception
                boolean r2 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r2 == 0) goto L_0x00a3
                r1.printStackTrace()
            L_0x00a3:
                if (r6 == 0) goto L_0x00b3
                r6.close()     // Catch:{ IOException -> 0x00a9 }
                goto L_0x00b3
            L_0x00a9:
                r6 = move-exception
                boolean r1 = com.mi.global.bbs.BBSApplication.isUserDebug()
                if (r1 == 0) goto L_0x00b3
                r6.printStackTrace()
            L_0x00b3:
                throw r0
            L_0x00b4:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.global.bbs.utils.Utils.Files.getFileBytes(java.lang.String):byte[]");
        }

        public static boolean downloadPackageFile(Context context, String str, String str2, boolean z) {
            boolean z2;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                Log.d(Utils.TAG, "The url or dest path should be null");
                return false;
            }
            if (BBSApplication.DEBUG) {
                Log.d(Utils.TAG, "url is --------->" + str);
                Log.d(Utils.TAG, "destPath is --------->" + str2);
            }
            try {
                if (!createFile(str2)) {
                    Log.e(Utils.TAG, "Create file" + str2 + " fails");
                    return false;
                }
                long fileString = getFileString(Constants.WebViewRes.getVersionFilePath(context));
                if (fileString < 0) {
                    try {
                        fileString = Preference.getLongPref(context, "pref_web_res_version", 0);
                    } catch (Exception unused) {
                    }
                }
                if (fileString < 0) {
                    fileString = 0;
                }
                LogUtil.b(Utils.TAG, String.format("local version:%s", new Object[]{Long.valueOf(fileString)}));
                new ServiceConnection(str).setUseGet(true);
                JSONObject jSONObject = SyncModel.response;
                LogUtil.b("Download web resouce", "SyncModel.response=" + jSONObject);
                if (jSONObject != null) {
                    PackageInfo packageInfo = PackageInfo.getPackageInfo(jSONObject);
                    LogUtil.b(Utils.TAG, "packageInfo:" + packageInfo);
                    if (packageInfo == null) {
                        return false;
                    }
                    String url = packageInfo.getUrl();
                    if (!url.endsWith("?deep=1")) {
                        url = url + "?deep=1";
                    }
                    if (BBSApplication.DEBUG) {
                        Log.d(Utils.TAG, jSONObject.toString());
                        Log.d(Utils.TAG, "fileUrl is -------->" + url);
                        Log.d(Utils.TAG, "(packageInfo.getVersion()-version):" + (packageInfo.getVersion() - fileString));
                    }
                    if (packageInfo.getVersion() > fileString) {
                        z2 = downLoadFile(context, url, str2, fileString);
                        if (!z2) {
                            return z2;
                        }
                        try {
                            Preference.setLongPref(context, "pref_web_res_version", Long.valueOf(packageInfo.getVersion()));
                            return z2;
                        } catch (Exception e) {
                            e = e;
                        }
                    }
                }
                return false;
            } catch (Exception e2) {
                e = e2;
                z2 = false;
                if (!BBSApplication.isUserDebug()) {
                    return z2;
                }
                e.printStackTrace();
                return z2;
            }
        }

        public static boolean downLoadFile(Context context, String str, String str2, long j) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                if (BBSApplication.DEBUG) {
                    Log.d(Utils.TAG, "The url or dest path should be null");
                }
                return false;
            }
            try {
                if (!createFile(str2)) {
                    if (BBSApplication.DEBUG) {
                        String access$000 = Utils.TAG;
                        Log.e(access$000, "Create file" + str2 + " fails");
                    }
                    return false;
                }
                File file = new File(str2);
                ServiceConnection serviceConnection = new ServiceConnection(str);
                serviceConnection.setUseGet(true);
                if (j > 0) {
                    serviceConnection.getClass();
                    new ServiceConnection.Parameter(serviceConnection).add("version", (Object) Long.valueOf(j));
                }
                if (serviceConnection.requestFile(file) == ServiceConnection.NetworkError.OK) {
                    return true;
                }
                return false;
            } catch (IOException e) {
                if (!BBSApplication.isUserDebug()) {
                    return false;
                }
                e.printStackTrace();
                return false;
            }
        }

        public static boolean createFile(String str) {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            File parentFile = file.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    if (BBSApplication.isUserDebug()) {
                        e.printStackTrace();
                    }
                    return false;
                }
            } else {
                if (BBSApplication.DEBUG) {
                    String access$000 = Utils.TAG;
                    Log.e(access$000, "Create file " + parentFile.getAbsolutePath() + " fails");
                }
                return false;
            }
        }

        public static boolean copyFile(String str, String str2) {
            File file = new File(str);
            File file2 = new File(str2);
            if (createFile(str2)) {
                return copyFile(file, file2);
            }
            return false;
        }

        public static boolean copyFile(File file, File file2) {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                boolean copyToFile = copyToFile(fileInputStream, file2);
                fileInputStream.close();
                return copyToFile;
            } catch (IOException unused) {
                return false;
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(14:1|2|(1:4)|5|6|7|8|(2:9|(1:11)(1:12))|13|14|15|16|17|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(7:19|20|21|22|23|24|25) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0027 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0037 */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:16:0x0027=Splitter:B:16:0x0027, B:23:0x0037=Splitter:B:23:0x0037} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static boolean copyToFile(java.io.InputStream r3, java.io.File r4) {
            /*
                r0 = 0
                boolean r1 = r4.exists()     // Catch:{ IOException -> 0x003b }
                if (r1 == 0) goto L_0x000a
                r4.delete()     // Catch:{ IOException -> 0x003b }
            L_0x000a:
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003b }
                r1.<init>(r4)     // Catch:{ IOException -> 0x003b }
                r4 = 4096(0x1000, float:5.74E-42)
                byte[] r4 = new byte[r4]     // Catch:{ all -> 0x002c }
            L_0x0013:
                int r2 = r3.read(r4)     // Catch:{ all -> 0x002c }
                if (r2 < 0) goto L_0x001d
                r1.write(r4, r0, r2)     // Catch:{ all -> 0x002c }
                goto L_0x0013
            L_0x001d:
                r1.flush()     // Catch:{ IOException -> 0x003b }
                java.io.FileDescriptor r3 = r1.getFD()     // Catch:{ IOException -> 0x0027 }
                r3.sync()     // Catch:{ IOException -> 0x0027 }
            L_0x0027:
                r1.close()     // Catch:{ IOException -> 0x003b }
                r3 = 1
                return r3
            L_0x002c:
                r3 = move-exception
                r1.flush()     // Catch:{ IOException -> 0x003b }
                java.io.FileDescriptor r4 = r1.getFD()     // Catch:{ IOException -> 0x0037 }
                r4.sync()     // Catch:{ IOException -> 0x0037 }
            L_0x0037:
                r1.close()     // Catch:{ IOException -> 0x003b }
                throw r3     // Catch:{ IOException -> 0x003b }
            L_0x003b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mi.global.bbs.utils.Utils.Files.copyToFile(java.io.InputStream, java.io.File):boolean");
        }

        public static boolean unZip(String str, String str2) {
            LogUtil.b(Utils.TAG, String.format("zipFile:%s, targetDir:%s", new Object[]{str, str2}));
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                return false;
            }
            if (!str2.endsWith("/")) {
                str2 = str2 + "/";
            }
            try {
                ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
                byte[] bArr = new byte[4096];
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        String name = nextEntry.getName();
                        File file = new File(str2 + name);
                        if (!name.endsWith("/")) {
                            File file2 = new File(file.getParent());
                            if (!file2.exists() || !file2.isDirectory()) {
                                file2.mkdirs();
                            }
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 4096);
                            while (true) {
                                int read = zipInputStream.read(bArr, 0, 4096);
                                if (read == -1) {
                                    break;
                                }
                                bufferedOutputStream.write(bArr, 0, read);
                            }
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        }
                    } else {
                        zipInputStream.close();
                        return true;
                    }
                }
            } catch (IOException unused) {
                return false;
            }
        }

        public static boolean deleteFile(File file) {
            if (file == null || !file.exists()) {
                return false;
            }
            if (!file.isDirectory()) {
                return file.delete();
            }
            for (File deleteFile : file.listFiles()) {
                if (!deleteFile(deleteFile)) {
                    return false;
                }
            }
            file.delete();
            return true;
        }

        public static boolean copyAssetFolder(AssetManager assetManager, String str, String str2) {
            try {
                String[] list = assetManager.list(str);
                new File(str2).mkdirs();
                boolean z = true;
                for (String str3 : list) {
                    if (str3.contains(".")) {
                        z &= copyAsset(assetManager, str + "/" + str3, str2 + "/" + str3);
                    } else {
                        z &= copyAssetFolder(assetManager, str + "/" + str3, str2 + "/" + str3);
                    }
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private static boolean copyAsset(AssetManager assetManager, String str, String str2) {
            try {
                InputStream open = assetManager.open(str);
                new File(str2).createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                copyFile(open, (OutputStream) fileOutputStream);
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        }
    }

    public static final class SoftInput {
        public static void hide(Context context, IBinder iBinder) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
            }
        }

        public static void show(Context context, View view) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(view, 0);
            }
        }
    }

    public static String getValueByName(String str, String str2) {
        for (String str3 : str.substring(str.indexOf("?") + 1).split(a.b)) {
            if (str3.contains(str2)) {
                return str3.replace(str2 + "=", "");
            }
        }
        return "";
    }

    public static String getTopActivity() {
        return ((ActivityManager) BBSApplication.getInstance().getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName();
    }
}
