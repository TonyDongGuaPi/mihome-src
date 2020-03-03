package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.xiaomi.stat.a.l;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static String f9026a;

    private static Map<String, Integer> c(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(",")) {
                String[] split = str2.split(":");
                if (split.length != 2) {
                    x.e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split[0], Integer.valueOf(Integer.parseInt(split[1])));
            }
            return hashMap;
        } catch (Exception e) {
            x.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        HashMap hashMap;
        Map<String, String> map2 = map;
        if (map2 == null) {
            return null;
        }
        if (a.a(context) == null) {
            x.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str3 = map2.get("intStateStr");
        if (str3 == null || str3.trim().length() <= 0) {
            x.e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> c = c(str3);
        if (c == null) {
            x.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            c.get("sino").intValue();
            c.get("sud").intValue();
            String str4 = map2.get("soVersion");
            if (str4 == null) {
                x.e("error format at version", new Object[0]);
                return null;
            }
            String str5 = map2.get("errorAddr");
            if (str5 == null) {
                str5 = "unknown";
            }
            String str6 = str5;
            String str7 = map2.get("codeMsg");
            if (str7 == null) {
                str7 = "unknown";
            }
            String str8 = map2.get("tombPath");
            if (str8 == null) {
                str8 = "unknown";
            }
            String str9 = str8;
            String str10 = map2.get("signalName");
            if (str10 == null) {
                str10 = "unknown";
            }
            map2.get("errnoMsg");
            String str11 = map2.get("stack");
            if (str11 == null) {
                str11 = "unknown";
            }
            String str12 = map2.get("jstack");
            if (str12 != null) {
                str11 = str11 + "java:\n" + str12;
            }
            Integer num = c.get("sico");
            if (num == null || num.intValue() <= 0) {
                str = str7;
                str2 = str10;
            } else {
                str2 = str10 + Operators.BRACKET_START_STR + str7 + Operators.BRACKET_END_STR;
                str = "KERNEL";
            }
            String str13 = map2.get("nativeLog");
            byte[] a2 = (str13 == null || str13.isEmpty()) ? null : z.a((File) null, str13, "BuglyNativeLog.txt");
            String str14 = map2.get("sendingProcess");
            if (str14 == null) {
                str14 = "unknown";
            }
            Integer num2 = c.get("spd");
            if (num2 != null) {
                str14 = str14 + Operators.BRACKET_START_STR + num2 + Operators.BRACKET_END_STR;
            }
            String str15 = str14;
            String str16 = map2.get("threadName");
            if (str16 == null) {
                str16 = "unknown";
            }
            Integer num3 = c.get("et");
            if (num3 != null) {
                str16 = str16 + Operators.BRACKET_START_STR + num3 + Operators.BRACKET_END_STR;
            }
            String str17 = str16;
            String str18 = map2.get("processName");
            if (str18 == null) {
                str18 = "unknown";
            }
            Integer num4 = c.get(DTransferConstants.aN);
            if (num4 != null) {
                str18 = str18 + Operators.BRACKET_START_STR + num4 + Operators.BRACKET_END_STR;
            }
            String str19 = map2.get("key-value");
            if (str19 != null) {
                HashMap hashMap2 = new HashMap();
                String[] split = str19.split("\n");
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String[] split2 = split[i].split("=");
                    String[] strArr = split;
                    if (split2.length == 2) {
                        hashMap2.put(split2[0], split2[1]);
                    }
                    i++;
                    split = strArr;
                }
                hashMap = hashMap2;
            } else {
                hashMap = null;
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str18, str17, (((long) c.get(l.a.C).intValue()) * 1000) + (((long) c.get("etms").intValue()) / 1000), str2, str6, a(str11), str, str15, str9, map2.get("sysLogPath"), str4, a2, hashMap, false);
            if (packageCrashDatas != null) {
                String str20 = map2.get("userId");
                if (str20 != null) {
                    x.c("[Native record info] userId: %s", str20);
                    packageCrashDatas.m = str20;
                }
                String str21 = map2.get("sysLog");
                if (str21 != null) {
                    packageCrashDatas.w = str21;
                }
                String str22 = map2.get("appVersion");
                if (str22 != null) {
                    x.c("[Native record info] appVersion: %s", str22);
                    packageCrashDatas.f = str22;
                }
                String str23 = map2.get("isAppForeground");
                if (str23 != null) {
                    x.c("[Native record info] isAppForeground: %s", str23);
                    packageCrashDatas.M = str23.equalsIgnoreCase("true");
                }
                String str24 = map2.get("launchTime");
                if (str24 != null) {
                    x.c("[Native record info] launchTime: %s", str24);
                    packageCrashDatas.L = Long.parseLong(str24);
                }
                packageCrashDatas.y = null;
                packageCrashDatas.k = true;
            }
            return packageCrashDatas;
        } catch (NumberFormatException e) {
            if (!x.a(e)) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            x.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    private static String a(BufferedInputStream bufferedInputStream) throws IOException {
        if (bufferedInputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = bufferedInputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return sb.toString();
            }
            sb.append((char) read);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x008c A[SYNTHETIC, Splitter:B:53:0x008c] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0098 A[SYNTHETIC, Splitter:B:60:0x0098] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.bugly.crashreport.crash.CrashDetailBean a(android.content.Context r6, java.lang.String r7, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler r8) {
        /*
            r0 = 0
            r1 = 0
            if (r6 == 0) goto L_0x00a2
            if (r7 == 0) goto L_0x00a2
            if (r8 != 0) goto L_0x000a
            goto L_0x00a2
        L_0x000a:
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "rqd_record.eup"
            r2.<init>(r7, r3)
            boolean r7 = r2.exists()
            if (r7 == 0) goto L_0x00a1
            boolean r7 = r2.canRead()
            if (r7 != 0) goto L_0x001f
            goto L_0x00a1
        L_0x001f:
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x0085, all -> 0x0082 }
            java.lang.String r2 = a((java.io.BufferedInputStream) r7)     // Catch:{ IOException -> 0x0080 }
            r3 = 1
            if (r2 == 0) goto L_0x006e
            java.lang.String r4 = "NATIVE_RQD_REPORT"
            boolean r4 = r2.equals(r4)     // Catch:{ IOException -> 0x0080 }
            if (r4 != 0) goto L_0x0039
            goto L_0x006e
        L_0x0039:
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ IOException -> 0x0080 }
            r2.<init>()     // Catch:{ IOException -> 0x0080 }
        L_0x003e:
            r4 = r1
        L_0x003f:
            java.lang.String r5 = a((java.io.BufferedInputStream) r7)     // Catch:{ IOException -> 0x0080 }
            if (r5 == 0) goto L_0x004d
            if (r4 != 0) goto L_0x0049
            r4 = r5
            goto L_0x003f
        L_0x0049:
            r2.put(r4, r5)     // Catch:{ IOException -> 0x0080 }
            goto L_0x003e
        L_0x004d:
            if (r4 == 0) goto L_0x0061
            java.lang.String r6 = "record not pair! drop! %s"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x0080 }
            r8[r0] = r4     // Catch:{ IOException -> 0x0080 }
            com.tencent.bugly.proguard.x.e(r6, r8)     // Catch:{ IOException -> 0x0080 }
            r7.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0060:
            return r1
        L_0x0061:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r6 = a((android.content.Context) r6, (java.util.Map<java.lang.String, java.lang.String>) r2, (com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler) r8)     // Catch:{ IOException -> 0x0080 }
            r7.close()     // Catch:{ IOException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r7 = move-exception
            r7.printStackTrace()
        L_0x006d:
            return r6
        L_0x006e:
            java.lang.String r6 = "record read fail! %s"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x0080 }
            r8[r0] = r2     // Catch:{ IOException -> 0x0080 }
            com.tencent.bugly.proguard.x.e(r6, r8)     // Catch:{ IOException -> 0x0080 }
            r7.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r6 = move-exception
            r6.printStackTrace()
        L_0x007f:
            return r1
        L_0x0080:
            r6 = move-exception
            goto L_0x0087
        L_0x0082:
            r6 = move-exception
            r7 = r1
            goto L_0x0096
        L_0x0085:
            r6 = move-exception
            r7 = r1
        L_0x0087:
            r6.printStackTrace()     // Catch:{ all -> 0x0095 }
            if (r7 == 0) goto L_0x0094
            r7.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0094:
            return r1
        L_0x0095:
            r6 = move-exception
        L_0x0096:
            if (r7 == 0) goto L_0x00a0
            r7.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00a0:
            throw r6
        L_0x00a1:
            return r1
        L_0x00a2:
            java.lang.String r6 = "get eup record file args error"
            java.lang.Object[] r7 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.e(r6, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(android.content.Context, java.lang.String, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }

    private static String b(String str, String str2) {
        BufferedReader a2 = z.a(str, "reg_record.txt");
        if (a2 == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = a2.readLine();
            if (readLine != null) {
                if (readLine.startsWith(str2)) {
                    int i = 0;
                    int i2 = 18;
                    int i3 = 0;
                    while (true) {
                        String readLine2 = a2.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        if (i % 4 == 0) {
                            if (i > 0) {
                                sb.append("\n");
                            }
                            sb.append("  ");
                        } else {
                            if (readLine2.length() > 16) {
                                i2 = 28;
                            }
                            sb.append("                ".substring(0, i2 - i3));
                        }
                        i3 = readLine2.length();
                        sb.append(readLine2);
                        i++;
                    }
                    sb.append("\n");
                    String sb2 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                    return sb2;
                }
            }
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Exception e2) {
                    x.a(e2);
                }
            }
            return null;
        } catch (Throwable th) {
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Exception e3) {
                    x.a(e3);
                }
            }
            throw th;
        }
    }

    private static String c(String str, String str2) {
        BufferedReader a2 = z.a(str, "map_record.txt");
        if (a2 == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = a2.readLine();
            if (readLine != null) {
                if (readLine.startsWith(str2)) {
                    while (true) {
                        String readLine2 = a2.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        sb.append("  ");
                        sb.append(readLine2);
                        sb.append("\n");
                    }
                    String sb2 = sb.toString();
                    if (a2 != null) {
                        try {
                            a2.close();
                        } catch (Exception e) {
                            x.a(e);
                        }
                    }
                    return sb2;
                }
            }
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Exception e2) {
                    x.a(e2);
                }
            }
            return null;
        } catch (Throwable th) {
            if (a2 != null) {
                try {
                    a2.close();
                } catch (Exception e3) {
                    x.a(e3);
                }
            }
            throw th;
        }
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String b = b(str, str2);
        if (b != null && !b.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(b);
        }
        String c = c(str, str2);
        if (c != null && !c.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("System SO infos:\n");
            sb.append(c);
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static void a(boolean z, String str) {
        File[] listFiles;
        if (str != null) {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canWrite()) {
                file.delete();
                x.c("delete record file %s", file.getAbsoluteFile());
            }
            File file2 = new File(str, "reg_record.txt");
            if (file2.exists() && file2.canWrite()) {
                file2.delete();
                x.c("delete record file %s", file2.getAbsoluteFile());
            }
            File file3 = new File(str, "map_record.txt");
            if (file3.exists() && file3.canWrite()) {
                file3.delete();
                x.c("delete record file %s", file3.getAbsoluteFile());
            }
            File file4 = new File(str, "backup_record.txt");
            if (file4.exists() && file4.canWrite()) {
                file4.delete();
                x.c("delete record file %s", file4.getAbsoluteFile());
            }
            if (f9026a != null) {
                File file5 = new File(f9026a);
                if (file5.exists() && file5.canWrite()) {
                    file5.delete();
                    x.c("delete record file %s", file5.getAbsoluteFile());
                }
            }
            if (z) {
                File file6 = new File(str);
                if (file6.canRead() && file6.isDirectory() && (listFiles = file6.listFiles()) != null) {
                    for (File file7 : listFiles) {
                        if (file7.canRead() && file7.canWrite() && file7.length() == 0) {
                            file7.delete();
                            x.c("delete invalid record file %s", file7.getAbsoluteFile());
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b0 A[SYNTHETIC, Splitter:B:35:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bc A[SYNTHETIC, Splitter:B:42:0x00bc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r7, int r8, java.lang.String r9) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x00c6
            if (r8 > 0) goto L_0x0007
            goto L_0x00c6
        L_0x0007:
            java.io.File r1 = new java.io.File
            r1.<init>(r7)
            boolean r2 = r1.exists()
            if (r2 == 0) goto L_0x00c5
            boolean r2 = r1.canRead()
            if (r2 != 0) goto L_0x001a
            goto L_0x00c5
        L_0x001a:
            f9026a = r7
            java.lang.String r2 = "Read system log from native record file(length: %s bytes): %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            long r5 = r1.length()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            r3[r4] = r5
            r4 = 1
            java.lang.String r5 = r1.getAbsolutePath()
            r3[r4] = r5
            com.tencent.bugly.proguard.x.a(r2, r3)
            if (r9 != 0) goto L_0x0042
            java.io.File r9 = new java.io.File
            r9.<init>(r7)
            java.lang.String r7 = com.tencent.bugly.proguard.z.a((java.io.File) r9)
            goto L_0x0092
        L_0x0042:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
            r7.<init>()     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
            r4.<init>(r1)     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
            java.lang.String r1 = "utf-8"
            r3.<init>(r4, r1)     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a9, all -> 0x00a6 }
        L_0x0058:
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x00a4 }
            if (r1 == 0) goto L_0x0086
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a4 }
            r3.<init>()     // Catch:{ Throwable -> 0x00a4 }
            r3.append(r9)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r4 = "[ ]*:"
            r3.append(r4)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x00a4 }
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)     // Catch:{ Throwable -> 0x00a4 }
            java.util.regex.Matcher r3 = r3.matcher(r1)     // Catch:{ Throwable -> 0x00a4 }
            boolean r3 = r3.find()     // Catch:{ Throwable -> 0x00a4 }
            if (r3 == 0) goto L_0x0058
            r7.append(r1)     // Catch:{ Throwable -> 0x00a4 }
            java.lang.String r1 = "\n"
            r7.append(r1)     // Catch:{ Throwable -> 0x00a4 }
            goto L_0x0058
        L_0x0086:
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x00a4 }
            r2.close()     // Catch:{ Exception -> 0x008e }
            goto L_0x0092
        L_0x008e:
            r9 = move-exception
            com.tencent.bugly.proguard.x.a(r9)
        L_0x0092:
            if (r7 == 0) goto L_0x00a3
            int r9 = r7.length()
            if (r9 <= r8) goto L_0x00a3
            int r9 = r7.length()
            int r9 = r9 - r8
            java.lang.String r7 = r7.substring(r9)
        L_0x00a3:
            return r7
        L_0x00a4:
            r7 = move-exception
            goto L_0x00ab
        L_0x00a6:
            r7 = move-exception
            r2 = r0
            goto L_0x00ba
        L_0x00a9:
            r7 = move-exception
            r2 = r0
        L_0x00ab:
            com.tencent.bugly.proguard.x.a(r7)     // Catch:{ all -> 0x00b9 }
            if (r2 == 0) goto L_0x00b8
            r2.close()     // Catch:{ Exception -> 0x00b4 }
            goto L_0x00b8
        L_0x00b4:
            r7 = move-exception
            com.tencent.bugly.proguard.x.a(r7)
        L_0x00b8:
            return r0
        L_0x00b9:
            r7 = move-exception
        L_0x00ba:
            if (r2 == 0) goto L_0x00c4
            r2.close()     // Catch:{ Exception -> 0x00c0 }
            goto L_0x00c4
        L_0x00c0:
            r8 = move-exception
            com.tencent.bugly.proguard.x.a(r8)
        L_0x00c4:
            throw r7
        L_0x00c5:
            return r0
        L_0x00c6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.b.a(java.lang.String, int, java.lang.String):java.lang.String");
    }
}
