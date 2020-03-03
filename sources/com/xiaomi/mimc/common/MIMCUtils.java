package com.xiaomi.mimc.common;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.cipher.Base64;
import com.xiaomi.mimc.client.Connection;
import com.xiaomi.mimc.json.JSONArray;
import com.xiaomi.mimc.packet.V6Packet;
import com.xiaomi.mimc.proto.ImsPushService;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.zip.Adler32;
import kotlin.text.Typography;

public class MIMCUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11176a = "MIMCUtils";

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0098 A[Catch:{ Exception -> 0x01cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ee A[Catch:{ Exception -> 0x01cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x010c A[Catch:{ Exception -> 0x01cd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(com.xiaomi.mimc.MIMCUser r14) {
        /*
            com.xiaomi.mimc.MIMCTokenFetcher r0 = r14.s()
            r1 = 0
            if (r0 != 0) goto L_0x000f
            java.lang.String r14 = "MIMCUtils"
            java.lang.String r0 = "fetchToken failed, tokenFetcher is not register."
            com.xiaomi.msg.logger.MIMCLog.d(r14, r0)
            return r1
        L_0x000f:
            java.lang.String r0 = "%s_%s_%s"
            r2 = 3
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = r14.q()
            r3[r1] = r4
            java.lang.String r4 = r14.n()
            r5 = 1
            r3[r5] = r4
            java.lang.String r4 = "mimcToken"
            r6 = 2
            r3[r6] = r4
            java.lang.String r0 = java.lang.String.format(r0, r3)
            r3 = 0
            java.lang.String r4 = r14.ac()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r7 = r14.T()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r4 = a((java.lang.String) r4, (java.lang.String) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x01cd }
            boolean r7 = c((java.lang.String) r4)     // Catch:{ Exception -> 0x01cd }
            if (r7 != 0) goto L_0x0095
            java.lang.String r3 = "MIMCUtils"
            java.lang.String r7 = "get token from local file, tokenKey:%s, token:%s"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x01cd }
            r8[r1] = r0     // Catch:{ Exception -> 0x01cd }
            r8[r5] = r4     // Catch:{ Exception -> 0x01cd }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.msg.logger.MIMCLog.b(r3, r7)     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.mimc.json.JSONObject r3 = a((java.lang.String) r4)     // Catch:{ Exception -> 0x01cd }
            java.lang.String r7 = "appId"
            java.lang.String r7 = r3.l(r7)     // Catch:{ Exception -> 0x01cd }
            long r7 = java.lang.Long.parseLong(r7)     // Catch:{ Exception -> 0x01cd }
            long r9 = r14.k()     // Catch:{ Exception -> 0x01cd }
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L_0x0093
            java.lang.String r9 = "MIMCUtils"
            java.lang.String r10 = "fetchToken appId is not equal, local appId:%d != file appId:%d delete file:%s"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x01cd }
            long r11 = r14.k()     // Catch:{ Exception -> 0x01cd }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ Exception -> 0x01cd }
            r2[r1] = r11     // Catch:{ Exception -> 0x01cd }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x01cd }
            r2[r5] = r7     // Catch:{ Exception -> 0x01cd }
            java.lang.String r7 = r14.T()     // Catch:{ Exception -> 0x01cd }
            r2[r6] = r7     // Catch:{ Exception -> 0x01cd }
            java.lang.String r2 = java.lang.String.format(r10, r2)     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.msg.logger.MIMCLog.d(r9, r2)     // Catch:{ Exception -> 0x01cd }
            java.lang.String r2 = r14.ac()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r7 = r14.T()     // Catch:{ Exception -> 0x01cd }
            a((java.lang.String) r2, (java.lang.String) r7)     // Catch:{ Exception -> 0x01cd }
            goto L_0x0095
        L_0x0093:
            r2 = 1
            goto L_0x0096
        L_0x0095:
            r2 = 0
        L_0x0096:
            if (r2 != 0) goto L_0x00dc
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.mimc.MIMCTokenFetcher r7 = r14.s()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r7 = r7.a()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r8 = "MIMCUtils"
            java.lang.String r9 = "fetchToken by http request, tokenStr:%s, timeCost:%d ms"
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x01cd }
            r10[r1] = r7     // Catch:{ Exception -> 0x01cd }
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x01cd }
            r13 = 0
            long r11 = r11 - r3
            java.lang.Long r3 = java.lang.Long.valueOf(r11)     // Catch:{ Exception -> 0x01cd }
            r10[r5] = r3     // Catch:{ Exception -> 0x01cd }
            java.lang.String r3 = java.lang.String.format(r9, r10)     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.msg.logger.MIMCLog.b(r8, r3)     // Catch:{ Exception -> 0x01cd }
            boolean r3 = c((java.lang.String) r7)     // Catch:{ Exception -> 0x01cd }
            if (r3 == 0) goto L_0x00cd
            java.lang.String r14 = "MIMCUtils"
            java.lang.String r0 = "fetchToken tokenStr is null."
            com.xiaomi.msg.logger.MIMCLog.b(r14, r0)     // Catch:{ Exception -> 0x01cd }
            return r1
        L_0x00cd:
            com.xiaomi.mimc.json.JSONObject r3 = a((java.lang.String) r7)     // Catch:{ Exception -> 0x01cd }
            if (r3 != 0) goto L_0x00db
            java.lang.String r14 = "MIMCUtils"
            java.lang.String r0 = "FETCH TOKEN FAILED."
            com.xiaomi.msg.logger.MIMCLog.c(r14, r0)     // Catch:{ Exception -> 0x01cd }
            return r1
        L_0x00db:
            r4 = r7
        L_0x00dc:
            java.lang.String r7 = "appId"
            java.lang.String r7 = r3.l(r7)     // Catch:{ Exception -> 0x01cd }
            long r7 = java.lang.Long.parseLong(r7)     // Catch:{ Exception -> 0x01cd }
            long r9 = r14.k()     // Catch:{ Exception -> 0x01cd }
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L_0x010c
            java.lang.String r0 = "MIMCUtils"
            java.lang.String r2 = "fetchToken appId is not equal, local appId:%d != server appId:%d"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x01cd }
            long r9 = r14.k()     // Catch:{ Exception -> 0x01cd }
            java.lang.Long r14 = java.lang.Long.valueOf(r9)     // Catch:{ Exception -> 0x01cd }
            r3[r1] = r14     // Catch:{ Exception -> 0x01cd }
            java.lang.Long r14 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x01cd }
            r3[r5] = r14     // Catch:{ Exception -> 0x01cd }
            java.lang.String r14 = java.lang.String.format(r2, r3)     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.msg.logger.MIMCLog.d(r0, r14)     // Catch:{ Exception -> 0x01cd }
            return r1
        L_0x010c:
            java.lang.String r7 = r14.q()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r8 = "appAccount"
            java.lang.String r8 = r3.l(r8)     // Catch:{ Exception -> 0x01cd }
            boolean r7 = r7.equals(r8)     // Catch:{ Exception -> 0x01cd }
            if (r7 != 0) goto L_0x0138
            java.lang.String r0 = "MIMCUtils"
            java.lang.String r2 = "APP_ACCOUNT DO NOT MATCH, appAccount:%s, tokenAppAccount:%s"
            java.lang.Object[] r4 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x01cd }
            java.lang.String r14 = r14.q()     // Catch:{ Exception -> 0x01cd }
            r4[r1] = r14     // Catch:{ Exception -> 0x01cd }
            java.lang.String r14 = "appAccount"
            java.lang.String r14 = r3.l(r14)     // Catch:{ Exception -> 0x01cd }
            r4[r5] = r14     // Catch:{ Exception -> 0x01cd }
            java.lang.String r14 = java.lang.String.format(r2, r4)     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.msg.logger.MIMCLog.c(r0, r14)     // Catch:{ Exception -> 0x01cd }
            return r1
        L_0x0138:
            if (r2 != 0) goto L_0x0157
            java.lang.String r2 = r14.ac()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r6 = r14.T()     // Catch:{ Exception -> 0x01cd }
            boolean r2 = a((java.lang.String) r2, (java.lang.String) r6, (java.lang.String) r0, (java.lang.String) r4)     // Catch:{ Exception -> 0x01cd }
            if (r2 == 0) goto L_0x0157
            java.lang.String r2 = "MIMCUtils"
            java.lang.String r4 = "fetchToken write token into file success, tokenKey:%s"
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x01cd }
            r6[r1] = r0     // Catch:{ Exception -> 0x01cd }
            java.lang.String r0 = java.lang.String.format(r4, r6)     // Catch:{ Exception -> 0x01cd }
            com.xiaomi.msg.logger.MIMCLog.b(r2, r0)     // Catch:{ Exception -> 0x01cd }
        L_0x0157:
            java.lang.String r0 = "appPackage"
            java.lang.String r0 = r3.l(r0)     // Catch:{ Exception -> 0x01cd }
            r14.d((java.lang.String) r0)     // Catch:{ Exception -> 0x01cd }
            java.lang.String r0 = "miChid"
            int r0 = r3.h(r0)     // Catch:{ Exception -> 0x01cd }
            r14.b((int) r0)     // Catch:{ Exception -> 0x01cd }
            java.lang.String r0 = "miUserId"
            java.lang.String r0 = r3.l(r0)     // Catch:{ Exception -> 0x01cd }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ Exception -> 0x01cd }
            long r6 = r0.longValue()     // Catch:{ Exception -> 0x01cd }
            r14.g((long) r6)     // Catch:{ Exception -> 0x01cd }
            java.lang.String r0 = "miUserSecurityKey"
            java.lang.String r0 = r3.l(r0)     // Catch:{ Exception -> 0x01cd }
            r14.b((java.lang.String) r0)     // Catch:{ Exception -> 0x01cd }
            java.lang.String r0 = "token"
            java.lang.String r0 = r3.l(r0)     // Catch:{ Exception -> 0x01cd }
            r14.c((java.lang.String) r0)     // Catch:{ Exception -> 0x01cd }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r2 = "regionBucket"
            boolean r0 = r0.contains(r2)     // Catch:{ Exception -> 0x01cd }
            if (r0 == 0) goto L_0x01a2
            java.lang.String r0 = "regionBucket"
            int r0 = r3.h(r0)     // Catch:{ Exception -> 0x01cd }
            long r6 = (long) r0     // Catch:{ Exception -> 0x01cd }
            r14.o(r6)     // Catch:{ Exception -> 0x01cd }
        L_0x01a2:
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r2 = "feDomainName"
            boolean r0 = r0.contains(r2)     // Catch:{ Exception -> 0x01cd }
            if (r0 == 0) goto L_0x01b7
            java.lang.String r0 = "feDomainName"
            java.lang.String r0 = r3.l(r0)     // Catch:{ Exception -> 0x01cd }
            r14.f((java.lang.String) r0)     // Catch:{ Exception -> 0x01cd }
        L_0x01b7:
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x01cd }
            java.lang.String r2 = "relayDomainName"
            boolean r0 = r0.contains(r2)     // Catch:{ Exception -> 0x01cd }
            if (r0 == 0) goto L_0x01cc
            java.lang.String r0 = "relayDomainName"
            java.lang.String r0 = r3.l(r0)     // Catch:{ Exception -> 0x01cd }
            r14.g((java.lang.String) r0)     // Catch:{ Exception -> 0x01cd }
        L_0x01cc:
            return r5
        L_0x01cd:
            r14 = move-exception
            java.lang.String r0 = "MIMCUtils"
            java.lang.String r2 = "fetchToken Exception e"
            com.xiaomi.msg.logger.MIMCLog.c(r0, r2, r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.MIMCUtils.a(com.xiaomi.mimc.MIMCUser):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.xiaomi.mimc.json.JSONObject a(java.lang.String r3) {
        /*
            java.lang.Class<com.xiaomi.mimc.common.MIMCUtils> r0 = com.xiaomi.mimc.common.MIMCUtils.class
            monitor-enter(r0)
            java.lang.String r1 = "code"
            boolean r1 = r3.contains(r1)     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0011
            com.xiaomi.mimc.json.JSONObject r1 = new com.xiaomi.mimc.json.JSONObject     // Catch:{ all -> 0x002b }
            r1.<init>((java.lang.String) r3)     // Catch:{ all -> 0x002b }
            goto L_0x0029
        L_0x0011:
            com.xiaomi.mimc.json.JSONObject r1 = new com.xiaomi.mimc.json.JSONObject     // Catch:{ all -> 0x002b }
            r1.<init>((java.lang.String) r3)     // Catch:{ all -> 0x002b }
            java.lang.String r3 = "code"
            int r3 = r1.h(r3)     // Catch:{ all -> 0x002b }
            r2 = 200(0xc8, float:2.8E-43)
            if (r3 == r2) goto L_0x0023
            r3 = 0
            monitor-exit(r0)
            return r3
        L_0x0023:
            java.lang.String r3 = "data"
            com.xiaomi.mimc.json.JSONObject r1 = r1.j(r3)     // Catch:{ all -> 0x002b }
        L_0x0029:
            monitor-exit(r0)
            return r1
        L_0x002b:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.MIMCUtils.a(java.lang.String):com.xiaomi.mimc.json.JSONObject");
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x006e A[SYNTHETIC, Splitter:B:47:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x007c A[SYNTHETIC, Splitter:B:54:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x008d A[SYNTHETIC, Splitter:B:64:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x009d A[SYNTHETIC, Splitter:B:72:0x009d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean a(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.Class<com.xiaomi.mimc.common.MIMCUtils> r0 = com.xiaomi.mimc.common.MIMCUtils.class
            monitor-enter(r0)
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            boolean r3 = r2.exists()     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            if (r3 != 0) goto L_0x0012
            r2.mkdirs()     // Catch:{ Exception -> 0x0062, all -> 0x005f }
        L_0x0012:
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r2.<init>(r4, r5)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            boolean r4 = r2.exists()     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            if (r4 != 0) goto L_0x0020
            r2.createNewFile()     // Catch:{ Exception -> 0x0062, all -> 0x005f }
        L_0x0020:
            java.util.Properties r4 = new java.util.Properties     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r4.<init>()     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r5.<init>(r2)     // Catch:{ Exception -> 0x0062, all -> 0x005f }
            r4.load(r5)     // Catch:{ Exception -> 0x005b, all -> 0x0057 }
            r4.setProperty(r6, r7)     // Catch:{ Exception -> 0x005b, all -> 0x0057 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x005b, all -> 0x0057 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x005b, all -> 0x0057 }
            r4.store(r6, r1)     // Catch:{ Exception -> 0x0055, all -> 0x0053 }
            r4 = 1
            r5.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0045
        L_0x003d:
            r5 = move-exception
            java.lang.String r7 = "MIMCUtils"
            java.lang.String r1 = "Close file input stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r7, r1, r5)     // Catch:{ all -> 0x0091 }
        L_0x0045:
            r6.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x0051
        L_0x0049:
            r5 = move-exception
            java.lang.String r6 = "MIMCUtils"
            java.lang.String r7 = "Close file output stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r6, r7, r5)     // Catch:{ all -> 0x0091 }
        L_0x0051:
            monitor-exit(r0)
            return r4
        L_0x0053:
            r4 = move-exception
            goto L_0x0059
        L_0x0055:
            r4 = move-exception
            goto L_0x005d
        L_0x0057:
            r4 = move-exception
            r6 = r1
        L_0x0059:
            r1 = r5
            goto L_0x008b
        L_0x005b:
            r4 = move-exception
            r6 = r1
        L_0x005d:
            r1 = r5
            goto L_0x0064
        L_0x005f:
            r4 = move-exception
            r6 = r1
            goto L_0x008b
        L_0x0062:
            r4 = move-exception
            r6 = r1
        L_0x0064:
            java.lang.String r5 = "MIMCUtils"
            java.lang.String r7 = "writePairs exception, e:"
            com.xiaomi.msg.logger.MIMCLog.d(r5, r7, r4)     // Catch:{ all -> 0x008a }
            r4 = 0
            if (r1 == 0) goto L_0x007a
            r1.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x007a
        L_0x0072:
            r5 = move-exception
            java.lang.String r7 = "MIMCUtils"
            java.lang.String r1 = "Close file input stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r7, r1, r5)     // Catch:{ all -> 0x0091 }
        L_0x007a:
            if (r6 == 0) goto L_0x0088
            r6.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0088
        L_0x0080:
            r5 = move-exception
            java.lang.String r6 = "MIMCUtils"
            java.lang.String r7 = "Close file output stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r6, r7, r5)     // Catch:{ all -> 0x0091 }
        L_0x0088:
            monitor-exit(r0)
            return r4
        L_0x008a:
            r4 = move-exception
        L_0x008b:
            if (r1 == 0) goto L_0x009b
            r1.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x009b
        L_0x0091:
            r4 = move-exception
            goto L_0x00aa
        L_0x0093:
            r5 = move-exception
            java.lang.String r7 = "MIMCUtils"
            java.lang.String r1 = "Close file input stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r7, r1, r5)     // Catch:{ all -> 0x0091 }
        L_0x009b:
            if (r6 == 0) goto L_0x00a9
            r6.close()     // Catch:{ IOException -> 0x00a1 }
            goto L_0x00a9
        L_0x00a1:
            r5 = move-exception
            java.lang.String r6 = "MIMCUtils"
            java.lang.String r7 = "Close file output stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r6, r7, r5)     // Catch:{ all -> 0x0091 }
        L_0x00a9:
            throw r4     // Catch:{ all -> 0x0091 }
        L_0x00aa:
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.MIMCUtils.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A[SYNTHETIC, Splitter:B:34:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007e A[SYNTHETIC, Splitter:B:45:0x007e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String a(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.Class<com.xiaomi.mimc.common.MIMCUtils> r0 = com.xiaomi.mimc.common.MIMCUtils.class
            monitor-enter(r0)
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x008b }
            r1.<init>(r5)     // Catch:{ all -> 0x008b }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x008b }
            r2 = 0
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L_0x0022
            java.lang.String r6 = "MIMCUtils"
            java.lang.String r7 = "getValueByKey the folder is not existed, filePath:%s"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ all -> 0x008b }
            r1[r2] = r5     // Catch:{ all -> 0x008b }
            java.lang.String r5 = java.lang.String.format(r7, r1)     // Catch:{ all -> 0x008b }
            com.xiaomi.msg.logger.MIMCLog.c(r6, r5)     // Catch:{ all -> 0x008b }
            monitor-exit(r0)
            return r4
        L_0x0022:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            r1.<init>(r5, r6)     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            boolean r5 = r1.exists()     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            if (r5 != 0) goto L_0x003e
            java.lang.String r5 = "MIMCUtils"
            java.lang.String r7 = "file is not exist in local, fileName:%s"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            r1[r2] = r6     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            java.lang.String r6 = java.lang.String.format(r7, r1)     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            com.xiaomi.msg.logger.MIMCLog.c(r5, r6)     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            monitor-exit(r0)
            return r4
        L_0x003e:
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            r5.<init>()     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            r5.load(r6)     // Catch:{ Exception -> 0x005d }
            java.lang.String r5 = r5.getProperty(r7)     // Catch:{ Exception -> 0x005d }
            r6.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x005b
        L_0x0053:
            r6 = move-exception
            java.lang.String r7 = "MIMCUtils"
            java.lang.String r1 = "Close file input stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r7, r1, r6)     // Catch:{ all -> 0x008b }
        L_0x005b:
            monitor-exit(r0)
            return r5
        L_0x005d:
            r5 = move-exception
            goto L_0x0063
        L_0x005f:
            r5 = move-exception
            goto L_0x007c
        L_0x0061:
            r5 = move-exception
            r6 = r4
        L_0x0063:
            java.lang.String r7 = "MIMCUtils"
            java.lang.String r1 = "getValueByKey exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r7, r1, r5)     // Catch:{ all -> 0x007a }
            if (r6 == 0) goto L_0x0078
            r6.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0078
        L_0x0070:
            r5 = move-exception
            java.lang.String r6 = "MIMCUtils"
            java.lang.String r7 = "Close file input stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r6, r7, r5)     // Catch:{ all -> 0x008b }
        L_0x0078:
            monitor-exit(r0)
            return r4
        L_0x007a:
            r5 = move-exception
            r4 = r6
        L_0x007c:
            if (r4 == 0) goto L_0x008a
            r4.close()     // Catch:{ IOException -> 0x0082 }
            goto L_0x008a
        L_0x0082:
            r6 = move-exception
            java.lang.String r7 = "MIMCUtils"
            java.lang.String r1 = "Close file input stream exception, e:"
            com.xiaomi.msg.logger.MIMCLog.c(r7, r1, r6)     // Catch:{ all -> 0x008b }
        L_0x008a:
            throw r5     // Catch:{ all -> 0x008b }
        L_0x008b:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.MIMCUtils.a(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static synchronized boolean a(String str, String str2) {
        synchronized (MIMCUtils.class) {
            try {
                if (!new File(str).exists()) {
                    return true;
                }
                File file = new File(str, str2);
                if (!file.exists()) {
                    return true;
                }
                boolean delete = file.delete();
                return delete;
            } catch (Exception e) {
                MIMCLog.d(f11176a, "Delete file exception, e:", e);
                return false;
            }
        }
    }

    public static V6Packet a(Connection connection) {
        MIMCLog.b(f11176a, String.format("Create bind packet for uuid:%d", new Object[]{Long.valueOf(connection.r().j())}));
        V6Packet v6Packet = new V6Packet();
        ImsPushService.ClientHeader a2 = a(connection.r(), MIMCConstant.m);
        v6Packet.a(a2);
        v6Packet.b(a(a2, connection).K());
        return v6Packet;
    }

    public static V6Packet b(Connection connection) {
        ImsPushService.ClientHeader.Builder D = ImsPushService.ClientHeader.D();
        D.a("xiaomi.com");
        D.b(0);
        D.c(MIMCConstant.l);
        D.a(IdGenerator.a().b());
        ImsPushService.XMMsgConn.Builder F = ImsPushService.XMMsgConn.F();
        F.a(106);
        F.a(b(connection.i()));
        F.b(b(connection.j()));
        F.c(b(connection.k()));
        F.b(33);
        F.d(b(connection.h()));
        F.e(String.format("%s:%s", new Object[]{connection.o(), Integer.valueOf(connection.p())}));
        F.f(b(connection.m()));
        F.c(connection.n());
        V6Packet v6Packet = new V6Packet();
        v6Packet.a((ImsPushService.ClientHeader) D.Z());
        v6Packet.b(((ImsPushService.XMMsgConn) F.Z()).K());
        return v6Packet;
    }

    public static V6Packet c(Connection connection) {
        MIMCLog.b(f11176a, String.format("Create ping packet for uuid:%d", new Object[]{Long.valueOf(connection.r().j())}));
        V6Packet v6Packet = new V6Packet();
        v6Packet.a(a(connection.r(), MIMCConstant.n));
        return v6Packet;
    }

    public static String b(String str) {
        return str == null ? "" : str.trim();
    }

    public static ImsPushService.XMMsgBind a(ImsPushService.ClientHeader clientHeader, Connection connection) {
        MIMCUser r = connection.r();
        ImsPushService.XMMsgBind.Builder t = ImsPushService.XMMsgBind.t();
        t.a(r.m());
        t.c(MIMCConstant.A);
        t.d(r.h());
        t.e(r.i());
        t.b("0");
        t.f(a(clientHeader, (ImsPushService.XMMsgBind) t.Z(), connection.g(), r.W()));
        return (ImsPushService.XMMsgBind) t.Z();
    }

    public static ImsPushService.ClientHeader a(MIMCUser mIMCUser, String str) {
        return a(mIMCUser, str, 0, Long.toString(IdGenerator.a().b()));
    }

    public static ImsPushService.ClientHeader a(MIMCUser mIMCUser, String str, int i, String str2) {
        ImsPushService.ClientHeader.Builder D = ImsPushService.ClientHeader.D();
        D.c(str);
        D.a("xiaomi.com");
        D.a(mIMCUser.j());
        D.a(mIMCUser.l());
        D.b(i);
        D.b(mIMCUser.n());
        D.e(str2);
        D.a(ImsPushService.ClientHeader.MSG_DIR_FLAG.CS_REQ);
        return (ImsPushService.ClientHeader) D.Z();
    }

    public static String a(ImsPushService.ClientHeader clientHeader, ImsPushService.XMMsgBind xMMsgBind, String str, String str2) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("challenge", str);
        treeMap.put("token", xMMsgBind.b());
        treeMap.put("chid", Integer.valueOf(clientHeader.b()));
        boolean z = true;
        treeMap.put("from", String.format("%s@xiaomi.com/%s", new Object[]{Long.valueOf(clientHeader.d()), clientHeader.i()}));
        treeMap.put("id", clientHeader.s());
        treeMap.put("to", "xiaomi.com");
        treeMap.put("kick", xMMsgBind.e());
        treeMap.put("client_attrs", xMMsgBind.l() == null ? "" : xMMsgBind.l());
        treeMap.put("cloud_attrs", xMMsgBind.o() == null ? "" : xMMsgBind.o());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(xMMsgBind.h().toUpperCase());
        for (Map.Entry entry : treeMap.entrySet()) {
            arrayList.add(String.format("%s=%s", new Object[]{entry.getKey(), entry.getValue()}));
        }
        arrayList.add(str2);
        StringBuilder sb = new StringBuilder();
        for (String str3 : arrayList) {
            if (!z) {
                sb.append(Typography.c);
            }
            sb.append(str3);
            z = false;
        }
        return f(sb.toString());
    }

    private static String f(String str) {
        try {
            return Base64.a(MessageDigest.getInstance("SHA1").digest(str.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("failed to SHA1");
        } catch (UnsupportedEncodingException e2) {
            System.out.println(e2.getMessage());
            throw new IllegalStateException("failed to SHA1");
        }
    }

    public static int a(byte[] bArr, int i, int i2) {
        Adler32 adler32 = new Adler32();
        adler32.reset();
        adler32.update(bArr, i, i2);
        return (int) adler32.getValue();
    }

    public static String a(int i) {
        return a(i, true, false);
    }

    public static String a(int i, boolean z, boolean z2) {
        return a(i, 0, 0, z, z2);
    }

    public static String a(int i, int i2, int i3, boolean z, boolean z2) {
        return a(i, i2, i3, z, z2, (char[]) null, new Random());
    }

    public static String a(int i, int i2, int i3, boolean z, boolean z2, char[] cArr, Random random) {
        char c;
        if (i == 0) {
            return "";
        }
        if (i < 0) {
            throw new IllegalArgumentException("Requested random string length " + i + " is less than 0.");
        } else if (cArr == null || cArr.length != 0) {
            if (i2 == 0 && i3 == 0) {
                if (cArr != null) {
                    i3 = cArr.length;
                } else if (z || z2) {
                    i3 = 123;
                    i2 = 32;
                } else {
                    i3 = Integer.MAX_VALUE;
                }
            } else if (i3 <= i2) {
                throw new IllegalArgumentException("Parameter end (" + i3 + ") must be greater than start (" + i2 + Operators.BRACKET_END_STR);
            }
            char[] cArr2 = new char[i];
            int i4 = i3 - i2;
            while (true) {
                int i5 = i - 1;
                if (i == 0) {
                    return new String(cArr2);
                }
                if (cArr == null) {
                    c = (char) (random.nextInt(i4) + i2);
                } else {
                    c = cArr[random.nextInt(i4) + i2];
                }
                if ((!z || !Character.isLetter(c)) && ((!z2 || !Character.isDigit(c)) && (z || z2))) {
                    i5++;
                } else if (c < 56320 || c > 57343) {
                    if (c < 55296 || c > 56191) {
                        if (c < 56192 || c > 56319) {
                            cArr2[i5] = c;
                        } else {
                            i5++;
                        }
                    } else if (i5 == 0) {
                        i5++;
                    } else {
                        cArr2[i5] = (char) (random.nextInt(128) + 56320);
                        i5--;
                        cArr2[i5] = c;
                    }
                } else if (i5 == 0) {
                    i5++;
                } else {
                    cArr2[i5] = c;
                    i5--;
                    cArr2[i5] = (char) (random.nextInt(128) + 55296);
                }
                i = i5;
            }
        } else {
            throw new IllegalArgumentException("The chars array must not be empty");
        }
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean d(String str) {
        if (c(str)) {
            return true;
        }
        try {
            if (new JSONArray(str).c()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            MIMCLog.d(f11176a, "There is a syntax error, exception:", e);
            return false;
        }
    }

    public static byte[] e(String str) {
        if (c(str)) {
            MIMCLog.c("ipv4", "addr is empty");
            return null;
        }
        String[] split = str.split("\\.");
        if (split.length != 4) {
            MIMCLog.c("ipv4", String.format("addr invalid format:%s, %d parts", new Object[]{str, Integer.valueOf(split.length)}));
            return null;
        }
        try {
            return new byte[]{Byte.parseByte(split[0]), Byte.parseByte(split[1]), Byte.parseByte(split[2]), Byte.parseByte(split[3])};
        } catch (Exception e) {
            MIMCLog.c("ipv4", String.format("addr %s convert exception:%s", new Object[]{str, MIMCLog.a((Throwable) e)}));
            return null;
        }
    }

    public static void b(MIMCUser mIMCUser) {
        mIMCUser.h((String) null);
        a(mIMCUser.ac(), mIMCUser.T(), MIMCConstant.ah, "");
    }

    public static void c(MIMCUser mIMCUser) {
        mIMCUser.c((String) null);
        a(mIMCUser.ac(), mIMCUser.T(), String.format("%s_%s_%s", new Object[]{mIMCUser.q(), mIMCUser.n(), MIMCConstant.aj}), "");
        mIMCUser.k(0);
    }
}
