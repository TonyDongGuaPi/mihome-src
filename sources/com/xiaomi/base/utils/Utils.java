package com.xiaomi.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SuppressLint({"MissingPermission"})
public class Utils {

    public static class Preference {
        private static boolean b(Context context) {
            return context == null;
        }

        public static void a(Context context, String str, TreeSet<String> treeSet) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!b(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putStringSet(str, treeSet);
                edit.commit();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.util.Set<java.lang.String> b(android.content.Context r1, java.lang.String r2, java.util.TreeSet<java.lang.String> r3) {
            /*
                boolean r0 = b(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                java.util.Set r1 = r1.getStringSet(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Preference.b(android.content.Context, java.lang.String, java.util.TreeSet):java.util.Set");
        }

        public static void a(Context context, String str, Long l) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!b(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putLong(str, l.longValue());
                edit.commit();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static long a(android.content.Context r1, java.lang.String r2, long r3) {
            /*
                boolean r0 = b(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                long r1 = r1.getLong(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Preference.a(android.content.Context, java.lang.String, long):long");
        }

        @Deprecated
        public static void a(Context context, String str, String str2) {
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor edit;
            if (context != null && (sharedPreferences = context.getSharedPreferences(str, 4)) != null && (edit = sharedPreferences.edit()) != null) {
                edit.putString(str, str2);
                edit.commit();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
            r1 = r1.getSharedPreferences(r2, 4);
         */
        @java.lang.Deprecated
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.String b(android.content.Context r1, java.lang.String r2, java.lang.String r3) {
            /*
                if (r1 != 0) goto L_0x0003
                return r3
            L_0x0003:
                r0 = 4
                android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r0)
                if (r1 == 0) goto L_0x000f
                java.lang.String r1 = r1.getString(r2, r3)
                return r1
            L_0x000f:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Preference.b(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
        }

        public static void c(Context context, String str, String str2) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!b(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putString(str, str2);
                edit.commit();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.String d(android.content.Context r1, java.lang.String r2, java.lang.String r3) {
            /*
                boolean r0 = b(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                java.lang.String r1 = r1.getString(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Preference.d(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
            r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static int a(android.content.Context r0, java.lang.String r1, int r2) {
            /*
                if (r0 != 0) goto L_0x0003
                return r2
            L_0x0003:
                android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
                if (r0 == 0) goto L_0x000e
                int r0 = r0.getInt(r1, r2)
                return r0
            L_0x000e:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Preference.a(android.content.Context, java.lang.String, int):int");
        }

        public static void b(Context context, String str, int i) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (context != null && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putInt(str, i);
                edit.commit();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
            r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static boolean a(android.content.Context r1, java.lang.String r2, boolean r3) {
            /*
                boolean r0 = b(r1)
                if (r0 == 0) goto L_0x0007
                return r3
            L_0x0007:
                android.content.SharedPreferences r1 = android.preference.PreferenceManager.getDefaultSharedPreferences(r1)
                if (r1 == 0) goto L_0x0012
                boolean r1 = r1.getBoolean(r2, r3)
                return r1
            L_0x0012:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Preference.a(android.content.Context, java.lang.String, boolean):boolean");
        }

        public static void b(Context context, String str, boolean z) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!b(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.putBoolean(str, z);
                edit.commit();
            }
        }

        @Deprecated
        public static void a(Context context, String str) {
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor edit;
            if (!b(context) && (sharedPreferences = context.getSharedPreferences(str, 4)) != null && (edit = sharedPreferences.edit()) != null) {
                edit.remove(str);
                edit.commit();
            }
        }

        public static void b(Context context, String str) {
            SharedPreferences defaultSharedPreferences;
            SharedPreferences.Editor edit;
            if (!b(context) && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) != null && (edit = defaultSharedPreferences.edit()) != null) {
                edit.remove(str);
                edit.commit();
            }
        }

        public static ArrayList<String> a(Context context) {
            SharedPreferences defaultSharedPreferences;
            if (b(context) || (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) == null) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            for (String obj : defaultSharedPreferences.getAll().keySet()) {
                arrayList.add(obj.toString());
            }
            return arrayList;
        }
    }

    public static class Network {
        public static boolean a(Context context) {
            NetworkInfo activeNetworkInfo;
            if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return false;
            }
            return true;
        }

        public static int b(Context context) {
            NetworkInfo activeNetworkInfo;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        }

        public static boolean c(Context context) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return false;
            }
            return true;
        }

        public static String d(Context context) {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
        }

        public static boolean e(Context context) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
        }

        public static String f(Context context) {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getMacAddress();
            }
            return null;
        }
    }

    public static final class Files {
        public static long a(File file) {
            long j;
            File[] listFiles = file.listFiles();
            long j2 = 0;
            if (listFiles == null || listFiles.length == 0) {
                return 0;
            }
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    j = a(listFiles[i]);
                } else {
                    j = listFiles[i].length();
                }
                j2 += j;
            }
            return j2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:47:0x005f, code lost:
            if (r0 == null) goto L_0x006f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x006c, code lost:
            if (r0 == null) goto L_0x006f;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003a */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x004f A[SYNTHETIC, Splitter:B:33:0x004f] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0054 A[SYNTHETIC, Splitter:B:37:0x0054] */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x005c A[SYNTHETIC, Splitter:B:45:0x005c] */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x0069 A[SYNTHETIC, Splitter:B:55:0x0069] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static long a(java.lang.String r7) {
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
                java.io.FileReader r3 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0065, IOException -> 0x0058, all -> 0x0049 }
                r3.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0065, IOException -> 0x0058, all -> 0x0049 }
                java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x0045, all -> 0x0040 }
                r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0047, IOException -> 0x0045, all -> 0x0040 }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x005a, all -> 0x003e }
                r7.<init>()     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x005a, all -> 0x003e }
            L_0x0025:
                java.lang.String r4 = r0.readLine()     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x005a, all -> 0x003e }
                if (r4 == 0) goto L_0x002f
                r7.append(r4)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x005a, all -> 0x003e }
                goto L_0x0025
            L_0x002f:
                java.lang.String r7 = r7.toString()     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x005a, all -> 0x003e }
                long r4 = java.lang.Long.parseLong(r7)     // Catch:{ FileNotFoundException -> 0x0067, IOException -> 0x005a, all -> 0x003e }
                r3.close()     // Catch:{ IOException -> 0x003a }
            L_0x003a:
                r0.close()     // Catch:{ IOException -> 0x003d }
            L_0x003d:
                return r4
            L_0x003e:
                r7 = move-exception
                goto L_0x004d
            L_0x0040:
                r0 = move-exception
                r6 = r0
                r0 = r7
                r7 = r6
                goto L_0x004d
            L_0x0045:
                r0 = r7
                goto L_0x005a
            L_0x0047:
                r0 = r7
                goto L_0x0067
            L_0x0049:
                r0 = move-exception
                r3 = r7
                r7 = r0
                r0 = r3
            L_0x004d:
                if (r3 == 0) goto L_0x0052
                r3.close()     // Catch:{ IOException -> 0x0052 }
            L_0x0052:
                if (r0 == 0) goto L_0x0057
                r0.close()     // Catch:{ IOException -> 0x0057 }
            L_0x0057:
                throw r7
            L_0x0058:
                r0 = r7
                r3 = r0
            L_0x005a:
                if (r3 == 0) goto L_0x005f
                r3.close()     // Catch:{ IOException -> 0x005f }
            L_0x005f:
                if (r0 == 0) goto L_0x006f
            L_0x0061:
                r0.close()     // Catch:{ IOException -> 0x006f }
                goto L_0x006f
            L_0x0065:
                r0 = r7
                r3 = r0
            L_0x0067:
                if (r3 == 0) goto L_0x006c
                r3.close()     // Catch:{ IOException -> 0x006c }
            L_0x006c:
                if (r0 == 0) goto L_0x006f
                goto L_0x0061
            L_0x006f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Files.a(java.lang.String):long");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(12:1|2|3|4|(4:6|(1:8)|9|(1:(3:12|13|54)(1:56)))|15|16|(3:17|18|(1:20)(1:53))|(2:22|23)|24|25|60) */
        /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0046 */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0055 A[SYNTHETIC, Splitter:B:34:0x0055] */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x005a A[SYNTHETIC, Splitter:B:38:0x005a] */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x0061 A[SYNTHETIC, Splitter:B:46:0x0061] */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x0066 A[SYNTHETIC, Splitter:B:50:0x0066] */
        /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static void a(android.content.res.AssetManager r2, java.lang.String r3, java.lang.String r4) {
            /*
                r0 = 1024(0x400, float:1.435E-42)
                byte[] r0 = new byte[r0]
                r1 = 0
                java.io.InputStream r2 = r2.open(r3)     // Catch:{ IOException -> 0x005e, all -> 0x0051 }
                java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                r3.<init>(r4)     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                boolean r4 = r3.exists()     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                if (r4 != 0) goto L_0x0031
                java.io.File r4 = r3.getParentFile()     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                boolean r4 = r4.exists()     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                if (r4 != 0) goto L_0x0025
                java.io.File r4 = r3.getParentFile()     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                r4.mkdirs()     // Catch:{ IOException -> 0x005f, all -> 0x004f }
            L_0x0025:
                boolean r4 = r3.createNewFile()     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                if (r4 != 0) goto L_0x0031
                if (r2 == 0) goto L_0x0030
                r2.close()     // Catch:{ IOException -> 0x0030 }
            L_0x0030:
                return
            L_0x0031:
                java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x005f, all -> 0x004f }
                r4.<init>(r3)     // Catch:{ IOException -> 0x005f, all -> 0x004f }
            L_0x0036:
                int r3 = r2.read(r0)     // Catch:{ IOException -> 0x004d, all -> 0x004a }
                if (r3 <= 0) goto L_0x0041
                r1 = 0
                r4.write(r0, r1, r3)     // Catch:{ IOException -> 0x004d, all -> 0x004a }
                goto L_0x0036
            L_0x0041:
                if (r2 == 0) goto L_0x0046
                r2.close()     // Catch:{ IOException -> 0x0046 }
            L_0x0046:
                r4.close()     // Catch:{ IOException -> 0x0069 }
                goto L_0x0069
            L_0x004a:
                r3 = move-exception
                r1 = r4
                goto L_0x0053
            L_0x004d:
                r1 = r4
                goto L_0x005f
            L_0x004f:
                r3 = move-exception
                goto L_0x0053
            L_0x0051:
                r3 = move-exception
                r2 = r1
            L_0x0053:
                if (r2 == 0) goto L_0x0058
                r2.close()     // Catch:{ IOException -> 0x0058 }
            L_0x0058:
                if (r1 == 0) goto L_0x005d
                r1.close()     // Catch:{ IOException -> 0x005d }
            L_0x005d:
                throw r3
            L_0x005e:
                r2 = r1
            L_0x005f:
                if (r2 == 0) goto L_0x0064
                r2.close()     // Catch:{ IOException -> 0x0064 }
            L_0x0064:
                if (r1 == 0) goto L_0x0069
                r1.close()     // Catch:{ IOException -> 0x0069 }
            L_0x0069:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Files.a(android.content.res.AssetManager, java.lang.String, java.lang.String):void");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(12:7|8|9|10|11|12|(3:13|14|(2:(2:17|59)(1:60)|18)(1:58))|19|(2:21|22)|23|24|25) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0044 */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x005c A[SYNTHETIC, Splitter:B:39:0x005c] */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x0061 A[SYNTHETIC, Splitter:B:43:0x0061] */
        /* JADX WARNING: Removed duplicated region for block: B:49:0x0068 A[SYNTHETIC, Splitter:B:49:0x0068] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x006d A[SYNTHETIC, Splitter:B:53:0x006d] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static byte[] b(java.lang.String r6) {
            /*
                boolean r0 = android.text.TextUtils.isEmpty(r6)
                r1 = 0
                if (r0 == 0) goto L_0x0008
                return r1
            L_0x0008:
                java.io.File r0 = new java.io.File
                r0.<init>(r6)
                boolean r6 = r0.exists()
                if (r6 == 0) goto L_0x0071
                long r2 = r0.length()
                r4 = 0
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 > 0) goto L_0x001e
                goto L_0x0071
            L_0x001e:
                java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0054, all -> 0x0050 }
                r6.<init>(r0)     // Catch:{ Exception -> 0x0054, all -> 0x0050 }
                r0 = 1024(0x400, float:1.435E-42)
                byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x004d, all -> 0x004a }
                r2 = r1
            L_0x0028:
                int r3 = r6.read(r0)     // Catch:{ Exception -> 0x0048 }
                if (r3 <= 0) goto L_0x003b
                if (r2 != 0) goto L_0x0036
                java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0048 }
                r4.<init>()     // Catch:{ Exception -> 0x0048 }
                r2 = r4
            L_0x0036:
                r4 = 0
                r2.write(r0, r4, r3)     // Catch:{ Exception -> 0x0048 }
                goto L_0x0028
            L_0x003b:
                byte[] r0 = r2.toByteArray()     // Catch:{ Exception -> 0x0048 }
                if (r2 == 0) goto L_0x0044
                r2.close()     // Catch:{ IOException -> 0x0044 }
            L_0x0044:
                r6.close()     // Catch:{ IOException -> 0x0047 }
            L_0x0047:
                return r0
            L_0x0048:
                r0 = move-exception
                goto L_0x0057
            L_0x004a:
                r0 = move-exception
                r2 = r1
                goto L_0x0066
            L_0x004d:
                r0 = move-exception
                r2 = r1
                goto L_0x0057
            L_0x0050:
                r0 = move-exception
                r6 = r1
                r2 = r6
                goto L_0x0066
            L_0x0054:
                r0 = move-exception
                r6 = r1
                r2 = r6
            L_0x0057:
                r0.printStackTrace()     // Catch:{ all -> 0x0065 }
                if (r2 == 0) goto L_0x005f
                r2.close()     // Catch:{ IOException -> 0x005f }
            L_0x005f:
                if (r6 == 0) goto L_0x0064
                r6.close()     // Catch:{ IOException -> 0x0064 }
            L_0x0064:
                return r1
            L_0x0065:
                r0 = move-exception
            L_0x0066:
                if (r2 == 0) goto L_0x006b
                r2.close()     // Catch:{ IOException -> 0x006b }
            L_0x006b:
                if (r6 == 0) goto L_0x0070
                r6.close()     // Catch:{ IOException -> 0x0070 }
            L_0x0070:
                throw r0
            L_0x0071:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Files.b(java.lang.String):byte[]");
        }

        public static boolean c(String str) {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            File parentFile = file.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                return false;
            }
            try {
                return file.createNewFile();
            } catch (IOException unused) {
                return false;
            }
        }

        public static boolean a(String str, String str2) {
            File file = new File(str);
            File file2 = new File(str2);
            if (c(str2)) {
                return a(file, file2);
            }
            return false;
        }

        public static boolean a(File file, File file2) {
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                boolean a2 = a((InputStream) fileInputStream, file2);
                fileInputStream.close();
                return a2;
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
        public static boolean a(java.io.InputStream r3, java.io.File r4) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.base.utils.Utils.Files.a(java.io.InputStream, java.io.File):boolean");
        }

        public static boolean b(String str, String str2) {
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

        public static boolean b(File file) {
            if (file == null || !file.exists()) {
                return false;
            }
            if (!file.isDirectory()) {
                return file.delete();
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File b : listFiles) {
                    if (!b(b)) {
                        return false;
                    }
                }
            }
            file.delete();
            return true;
        }

        public static boolean b(AssetManager assetManager, String str, String str2) {
            try {
                String[] list = assetManager.list(str);
                new File(str2).mkdirs();
                boolean z = true;
                for (String str3 : list) {
                    if (str3.contains(".")) {
                        z &= c(assetManager, str + "/" + str3, str2 + "/" + str3);
                    } else {
                        z &= b(assetManager, str + "/" + str3, str2 + "/" + str3);
                    }
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private static boolean c(AssetManager assetManager, String str, String str2) {
            try {
                InputStream open = assetManager.open(str);
                new File(str2).createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                a(open, (OutputStream) fileOutputStream);
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
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
}
