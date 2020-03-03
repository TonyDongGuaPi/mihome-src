package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.Cif;
import com.xiaomi.push.ai;
import com.xiaomi.push.bf;
import com.xiaomi.push.br;
import com.xiaomi.push.fd;
import com.xiaomi.push.ho;
import com.xiaomi.push.hy;
import com.xiaomi.push.ia;
import com.xiaomi.push.ib;
import com.xiaomi.push.ic;
import com.xiaomi.push.ie;
import com.xiaomi.push.ik;
import com.xiaomi.push.in;
import com.xiaomi.push.ir;
import com.xiaomi.push.iy;
import com.xiaomi.push.iz;
import com.xiaomi.push.je;
import com.xiaomi.push.r;
import com.xiaomi.push.service.aa;
import com.xiaomi.smarthome.library.common.util.DateTimeHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;

public class at {

    /* renamed from: a  reason: collision with root package name */
    private static at f11536a;
    private static Queue<String> c;
    private static Object d = new Object();
    private Context b;

    private at(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0148  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Intent a(android.content.Context r6, java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x018d
            java.lang.String r1 = "notify_effect"
            boolean r1 = r8.containsKey(r1)
            if (r1 != 0) goto L_0x000d
            goto L_0x018d
        L_0x000d:
            java.lang.String r1 = "notify_effect"
            java.lang.Object r1 = r8.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            r2 = -1
            java.lang.String r3 = "intent_flag"
            java.lang.Object r3 = r8.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ NumberFormatException -> 0x002a }
            if (r4 != 0) goto L_0x0043
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x002a }
            r2 = r3
            goto L_0x0043
        L_0x002a:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Cause by intent_flag: "
            r4.append(r5)
            java.lang.String r3 = r3.getMessage()
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r3)
        L_0x0043:
            java.lang.String r3 = com.xiaomi.push.service.aq.f12888a
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            android.content.pm.PackageManager r8 = r6.getPackageManager()     // Catch:{ Exception -> 0x0056 }
            android.content.Intent r7 = r8.getLaunchIntentForPackage(r7)     // Catch:{ Exception -> 0x0056 }
            r8 = r7
            goto L_0x0146
        L_0x0056:
            r7 = move-exception
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "Cause: "
            r8.append(r1)
            java.lang.String r7 = r7.getMessage()
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r7)
            goto L_0x0145
        L_0x0071:
            java.lang.String r3 = com.xiaomi.push.service.aq.b
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x00c9
            java.lang.String r1 = "intent_uri"
            boolean r1 = r8.containsKey(r1)
            if (r1 == 0) goto L_0x00a9
            java.lang.String r1 = "intent_uri"
            java.lang.Object r8 = r8.get(r1)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x0145
            r1 = 1
            android.content.Intent r8 = android.content.Intent.parseUri(r8, r1)     // Catch:{ URISyntaxException -> 0x0097 }
            r8.setPackage(r7)     // Catch:{ URISyntaxException -> 0x0095 }
            goto L_0x0146
        L_0x0095:
            r7 = move-exception
            goto L_0x0099
        L_0x0097:
            r7 = move-exception
            r8 = r0
        L_0x0099:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Cause: "
            r1.append(r3)
            java.lang.String r7 = r7.getMessage()
            goto L_0x013a
        L_0x00a9:
            java.lang.String r1 = "class_name"
            boolean r1 = r8.containsKey(r1)
            if (r1 == 0) goto L_0x0145
            java.lang.String r1 = "class_name"
            java.lang.Object r8 = r8.get(r1)
            java.lang.String r8 = (java.lang.String) r8
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            android.content.ComponentName r3 = new android.content.ComponentName
            r3.<init>(r7, r8)
            r1.setComponent(r3)
            r8 = r1
            goto L_0x0146
        L_0x00c9:
            java.lang.String r7 = com.xiaomi.push.service.aq.c
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0145
            java.lang.String r7 = "web_uri"
            java.lang.Object r7 = r8.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto L_0x0145
            java.lang.String r7 = r7.trim()
            java.lang.String r8 = "http://"
            boolean r8 = r7.startsWith(r8)
            if (r8 != 0) goto L_0x0100
            java.lang.String r8 = "https://"
            boolean r8 = r7.startsWith(r8)
            if (r8 != 0) goto L_0x0100
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "http://"
            r8.append(r1)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
        L_0x0100:
            java.net.URL r8 = new java.net.URL     // Catch:{ MalformedURLException -> 0x012a }
            r8.<init>(r7)     // Catch:{ MalformedURLException -> 0x012a }
            java.lang.String r8 = r8.getProtocol()     // Catch:{ MalformedURLException -> 0x012a }
            java.lang.String r1 = "http"
            boolean r1 = r1.equals(r8)     // Catch:{ MalformedURLException -> 0x012a }
            if (r1 != 0) goto L_0x0119
            java.lang.String r1 = "https"
            boolean r8 = r1.equals(r8)     // Catch:{ MalformedURLException -> 0x012a }
            if (r8 == 0) goto L_0x0145
        L_0x0119:
            android.content.Intent r8 = new android.content.Intent     // Catch:{ MalformedURLException -> 0x012a }
            java.lang.String r1 = "android.intent.action.VIEW"
            r8.<init>(r1)     // Catch:{ MalformedURLException -> 0x012a }
            android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ MalformedURLException -> 0x0128 }
            r8.setData(r7)     // Catch:{ MalformedURLException -> 0x0128 }
            goto L_0x0146
        L_0x0128:
            r7 = move-exception
            goto L_0x012c
        L_0x012a:
            r7 = move-exception
            r8 = r0
        L_0x012c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Cause: "
            r1.append(r3)
            java.lang.String r7 = r7.getMessage()
        L_0x013a:
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r7)
            goto L_0x0146
        L_0x0145:
            r8 = r0
        L_0x0146:
            if (r8 == 0) goto L_0x018d
            if (r2 < 0) goto L_0x014d
            r8.setFlags(r2)
        L_0x014d:
            r7 = 268435456(0x10000000, float:2.5243549E-29)
            r8.addFlags(r7)
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch:{ Exception -> 0x0174 }
            r7 = 65536(0x10000, float:9.18355E-41)
            android.content.pm.ResolveInfo r6 = r6.resolveActivity(r8, r7)     // Catch:{ Exception -> 0x0174 }
            if (r6 == 0) goto L_0x015f
            return r8
        L_0x015f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0174 }
            r6.<init>()     // Catch:{ Exception -> 0x0174 }
            java.lang.String r7 = "not resolve activity:"
            r6.append(r7)     // Catch:{ Exception -> 0x0174 }
            r6.append(r8)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0174 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r6)     // Catch:{ Exception -> 0x0174 }
            goto L_0x018d
        L_0x0174:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Cause: "
            r7.append(r8)
            java.lang.String r6 = r6.getMessage()
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.xiaomi.channel.commonutils.logger.b.d(r6)
        L_0x018d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.at.a(android.content.Context, java.lang.String, java.util.Map):android.content.Intent");
    }

    /* JADX WARNING: type inference failed for: r4v16, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r10v5, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARNING: type inference failed for: r12v0, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r10v7, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARNING: type inference failed for: r12v1, types: [java.util.List] */
    /* JADX WARNING: type inference failed for: r10v9, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.mipush.sdk.PushMessageHandler.a a(com.xiaomi.push.ik r20, boolean r21, byte[] r22, java.lang.String r23, int r24) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r0 = r21
            r3 = r22
            r8 = r23
            r9 = r24
            r10 = 0
            android.content.Context r4 = r1.b     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            com.xiaomi.push.iz r4 = com.xiaomi.mipush.sdk.ap.a((android.content.Context) r4, (com.xiaomi.push.ik) r2)     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            if (r4 != 0) goto L_0x0041
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            r0.<init>()     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            java.lang.String r3 = "receiving an un-recognized message. "
            r0.append(r3)     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            com.xiaomi.push.ho r3 = r2.f12803a     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            r0.append(r3)     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            java.lang.String r0 = r0.toString()     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            com.xiaomi.channel.commonutils.logger.b.d(r0)     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            android.content.Context r0 = r1.b     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            com.xiaomi.push.fd r0 = com.xiaomi.push.fd.a((android.content.Context) r0)     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            android.content.Context r3 = r1.b     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            java.lang.String r4 = com.xiaomi.push.fc.a((int) r24)     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            java.lang.String r5 = "receiving an un-recognized message."
            r0.b(r3, r4, r8, r5)     // Catch:{ t -> 0x09c3, je -> 0x09a6 }
            return r10
        L_0x0041:
            com.xiaomi.push.ho r5 = r20.a()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "processing a message, action="
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r6)
            int[] r6 = com.xiaomi.mipush.sdk.av.f11538a
            int r5 = r5.ordinal()
            r5 = r6[r5]
            r6 = 1
            r11 = 0
            r7 = 0
            switch(r5) {
                case 1: goto L_0x070f;
                case 2: goto L_0x0661;
                case 3: goto L_0x0646;
                case 4: goto L_0x060d;
                case 5: goto L_0x05d4;
                case 6: goto L_0x04b0;
                case 7: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x09a5
        L_0x006a:
            android.content.Context r0 = r1.b
            java.lang.String r0 = r0.getPackageName()
            android.content.Context r5 = r1.b
            com.xiaomi.push.ho r8 = com.xiaomi.push.ho.Notification
            int r3 = r3.length
            com.xiaomi.push.dh.a(r0, r5, r4, r8, r3)
            boolean r0 = r4 instanceof com.xiaomi.push.Cif
            if (r0 == 0) goto L_0x020b
            com.xiaomi.push.if r4 = (com.xiaomi.push.Cif) r4
            java.lang.String r0 = r4.a()
            com.xiaomi.push.hy r2 = com.xiaomi.push.hy.DisablePushMessage
            java.lang.String r2 = r2.f114a
            java.lang.String r3 = r4.d
            boolean r2 = r2.equalsIgnoreCase(r3)
            r3 = 10
            if (r2 == 0) goto L_0x0147
            long r4 = r4.f12798a
            int r2 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x00eb
            java.lang.Class<com.xiaomi.mipush.sdk.am> r2 = com.xiaomi.mipush.sdk.am.class
            monitor-enter(r2)
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x00e8 }
            boolean r3 = r3.e(r0)     // Catch:{ all -> 0x00e8 }
            if (r3 == 0) goto L_0x00e5
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x00e8 }
            r3.d(r0)     // Catch:{ all -> 0x00e8 }
            java.lang.String r0 = "syncing"
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.bb r4 = com.xiaomi.mipush.sdk.bb.DISABLE_PUSH     // Catch:{ all -> 0x00e8 }
            java.lang.String r3 = r3.a((com.xiaomi.mipush.sdk.bb) r4)     // Catch:{ all -> 0x00e8 }
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x00e8 }
            if (r0 == 0) goto L_0x00e5
            android.content.Context r0 = r1.b     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.am r0 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r0)     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.bb r3 = com.xiaomi.mipush.sdk.bb.DISABLE_PUSH     // Catch:{ all -> 0x00e8 }
            java.lang.String r4 = "synced"
            r0.a(r3, r4)     // Catch:{ all -> 0x00e8 }
            android.content.Context r0 = r1.b     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.MiPushClient.n(r0)     // Catch:{ all -> 0x00e8 }
            android.content.Context r0 = r1.b     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.MiPushClient.f(r0)     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.PushMessageHandler.a()     // Catch:{ all -> 0x00e8 }
            android.content.Context r0 = r1.b     // Catch:{ all -> 0x00e8 }
            com.xiaomi.mipush.sdk.aw r0 = com.xiaomi.mipush.sdk.aw.a((android.content.Context) r0)     // Catch:{ all -> 0x00e8 }
            r0.b()     // Catch:{ all -> 0x00e8 }
        L_0x00e5:
            monitor-exit(r2)     // Catch:{ all -> 0x00e8 }
            goto L_0x09a5
        L_0x00e8:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00e8 }
            throw r0
        L_0x00eb:
            java.lang.String r2 = "syncing"
            android.content.Context r4 = r1.b
            com.xiaomi.mipush.sdk.am r4 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r4)
            com.xiaomi.mipush.sdk.bb r5 = com.xiaomi.mipush.sdk.bb.DISABLE_PUSH
            java.lang.String r4 = r4.a((com.xiaomi.mipush.sdk.bb) r5)
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x013c
            java.lang.Class<com.xiaomi.mipush.sdk.am> r2 = com.xiaomi.mipush.sdk.am.class
            monitor-enter(r2)
            android.content.Context r4 = r1.b     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.am r4 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r4)     // Catch:{ all -> 0x0139 }
            boolean r4 = r4.e(r0)     // Catch:{ all -> 0x0139 }
            if (r4 == 0) goto L_0x0136
            android.content.Context r4 = r1.b     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.am r4 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r4)     // Catch:{ all -> 0x0139 }
            int r4 = r4.c(r0)     // Catch:{ all -> 0x0139 }
            if (r4 >= r3) goto L_0x012d
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x0139 }
            r3.b(r0)     // Catch:{ all -> 0x0139 }
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.aw r3 = com.xiaomi.mipush.sdk.aw.a((android.content.Context) r3)     // Catch:{ all -> 0x0139 }
            r3.a((boolean) r6, (java.lang.String) r0)     // Catch:{ all -> 0x0139 }
            goto L_0x0136
        L_0x012d:
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x0139 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x0139 }
            r3.d(r0)     // Catch:{ all -> 0x0139 }
        L_0x0136:
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            goto L_0x09a5
        L_0x0139:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            throw r0
        L_0x013c:
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.am r2 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r2)
            r2.d(r0)
            goto L_0x09a5
        L_0x0147:
            com.xiaomi.push.hy r2 = com.xiaomi.push.hy.EnablePushMessage
            java.lang.String r2 = r2.f114a
            java.lang.String r5 = r4.d
            boolean r2 = r2.equalsIgnoreCase(r5)
            if (r2 == 0) goto L_0x01e9
            long r4 = r4.f12798a
            int r2 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x0198
            java.lang.Class<com.xiaomi.mipush.sdk.am> r2 = com.xiaomi.mipush.sdk.am.class
            monitor-enter(r2)
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x0195 }
            boolean r3 = r3.e(r0)     // Catch:{ all -> 0x0195 }
            if (r3 == 0) goto L_0x0192
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x0195 }
            r3.d(r0)     // Catch:{ all -> 0x0195 }
            java.lang.String r0 = "syncing"
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.bb r4 = com.xiaomi.mipush.sdk.bb.ENABLE_PUSH     // Catch:{ all -> 0x0195 }
            java.lang.String r3 = r3.a((com.xiaomi.mipush.sdk.bb) r4)     // Catch:{ all -> 0x0195 }
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x0195 }
            if (r0 == 0) goto L_0x0192
            android.content.Context r0 = r1.b     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.am r0 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r0)     // Catch:{ all -> 0x0195 }
            com.xiaomi.mipush.sdk.bb r3 = com.xiaomi.mipush.sdk.bb.ENABLE_PUSH     // Catch:{ all -> 0x0195 }
            java.lang.String r4 = "synced"
            r0.a(r3, r4)     // Catch:{ all -> 0x0195 }
        L_0x0192:
            monitor-exit(r2)     // Catch:{ all -> 0x0195 }
            goto L_0x09a5
        L_0x0195:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0195 }
            throw r0
        L_0x0198:
            java.lang.String r2 = "syncing"
            android.content.Context r4 = r1.b
            com.xiaomi.mipush.sdk.am r4 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r4)
            com.xiaomi.mipush.sdk.bb r5 = com.xiaomi.mipush.sdk.bb.ENABLE_PUSH
            java.lang.String r4 = r4.a((com.xiaomi.mipush.sdk.bb) r5)
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x013c
            java.lang.Class<com.xiaomi.mipush.sdk.am> r2 = com.xiaomi.mipush.sdk.am.class
            monitor-enter(r2)
            android.content.Context r4 = r1.b     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.am r4 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r4)     // Catch:{ all -> 0x01e6 }
            boolean r4 = r4.e(r0)     // Catch:{ all -> 0x01e6 }
            if (r4 == 0) goto L_0x01e3
            android.content.Context r4 = r1.b     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.am r4 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r4)     // Catch:{ all -> 0x01e6 }
            int r4 = r4.c(r0)     // Catch:{ all -> 0x01e6 }
            if (r4 >= r3) goto L_0x01da
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x01e6 }
            r3.b(r0)     // Catch:{ all -> 0x01e6 }
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.aw r3 = com.xiaomi.mipush.sdk.aw.a((android.content.Context) r3)     // Catch:{ all -> 0x01e6 }
            r3.a((boolean) r7, (java.lang.String) r0)     // Catch:{ all -> 0x01e6 }
            goto L_0x01e3
        L_0x01da:
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x01e6 }
            com.xiaomi.mipush.sdk.am r3 = com.xiaomi.mipush.sdk.am.a((android.content.Context) r3)     // Catch:{ all -> 0x01e6 }
            r3.d(r0)     // Catch:{ all -> 0x01e6 }
        L_0x01e3:
            monitor-exit(r2)     // Catch:{ all -> 0x01e6 }
            goto L_0x09a5
        L_0x01e6:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01e6 }
            throw r0
        L_0x01e9:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.ThirdPartyRegUpdate
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x01fa
            r1.b((com.xiaomi.push.Cif) r4)
            goto L_0x09a5
        L_0x01fa:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.UploadTinyData
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x09a5
            r1.a((com.xiaomi.push.Cif) r4)
            goto L_0x09a5
        L_0x020b:
            boolean r0 = r4 instanceof com.xiaomi.push.in
            if (r0 == 0) goto L_0x09a5
            com.xiaomi.push.in r4 = (com.xiaomi.push.in) r4
            java.lang.String r0 = "registration id expired"
            java.lang.String r3 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x02a5
            android.content.Context r0 = r1.b
            java.util.List r0 = com.xiaomi.mipush.sdk.MiPushClient.b(r0)
            android.content.Context r2 = r1.b
            java.util.List r2 = com.xiaomi.mipush.sdk.MiPushClient.c(r2)
            android.content.Context r3 = r1.b
            java.util.List r3 = com.xiaomi.mipush.sdk.MiPushClient.d(r3)
            android.content.Context r4 = r1.b
            java.lang.String r4 = com.xiaomi.mipush.sdk.MiPushClient.y(r4)
            android.content.Context r5 = r1.b
            com.xiaomi.push.ic r8 = com.xiaomi.push.ic.RegIdExpired
            com.xiaomi.mipush.sdk.MiPushClient.a((android.content.Context) r5, (com.xiaomi.push.ic) r8)
            java.util.Iterator r0 = r0.iterator()
        L_0x023e:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0255
            java.lang.Object r5 = r0.next()
            java.lang.String r5 = (java.lang.String) r5
            android.content.Context r8 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.e(r8, r5)
            android.content.Context r8 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.b(r8, r5, r10)
            goto L_0x023e
        L_0x0255:
            java.util.Iterator r0 = r2.iterator()
        L_0x0259:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0270
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            android.content.Context r5 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.i(r5, r2)
            android.content.Context r5 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.f(r5, r2, r10)
            goto L_0x0259
        L_0x0270:
            java.util.Iterator r0 = r3.iterator()
        L_0x0274:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x028b
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            android.content.Context r3 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.g(r3, r2)
            android.content.Context r3 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.d(r3, r2, r10)
            goto L_0x0274
        L_0x028b:
            java.lang.String r0 = ","
            java.lang.String[] r0 = r4.split(r0)
            int r2 = r0.length
            r3 = 2
            if (r2 != r3) goto L_0x09a5
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.x(r2)
            android.content.Context r2 = r1.b
            r3 = r0[r7]
            r0 = r0[r6]
            com.xiaomi.mipush.sdk.MiPushClient.i(r2, r3, r0)
            goto L_0x09a5
        L_0x02a5:
            java.lang.String r0 = "client_info_update_ok"
            java.lang.String r3 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x02d8
            java.util.Map r0 = r4.a()
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            java.lang.String r2 = "app_version"
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            java.lang.String r2 = "app_version"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.b r2 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r2)
            r2.a((java.lang.String) r0)
            goto L_0x09a5
        L_0x02d8:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.AwakeApp
            java.lang.String r0 = r0.f114a
            java.lang.String r3 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x0329
            boolean r0 = r20.b()
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            java.lang.String r2 = "awake_info"
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            java.lang.String r2 = "awake_info"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            android.content.Context r2 = r1.b
            android.content.Context r3 = r1.b
            com.xiaomi.mipush.sdk.b r3 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r3)
            java.lang.String r3 = r3.c()
            android.content.Context r4 = r1.b
            com.xiaomi.push.service.ah r4 = com.xiaomi.push.service.ah.a((android.content.Context) r4)
            com.xiaomi.push.ht r5 = com.xiaomi.push.ht.AwakeInfoUploadWaySwitch
            int r5 = r5.a()
            int r4 = r4.a((int) r5, (int) r7)
            com.xiaomi.mipush.sdk.n.a((android.content.Context) r2, (java.lang.String) r3, (int) r4, (java.lang.String) r0)
            goto L_0x09a5
        L_0x0329:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.NormalClientConfigUpdate
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x034c
            com.xiaomi.push.im r0 = new com.xiaomi.push.im
            r0.<init>()
            byte[] r2 = r4.a()     // Catch:{ je -> 0x043a }
            com.xiaomi.push.iy.a(r0, (byte[]) r2)     // Catch:{ je -> 0x043a }
            android.content.Context r2 = r1.b     // Catch:{ je -> 0x043a }
            com.xiaomi.push.service.ah r2 = com.xiaomi.push.service.ah.a((android.content.Context) r2)     // Catch:{ je -> 0x043a }
            com.xiaomi.push.service.ai.a((com.xiaomi.push.service.ah) r2, (com.xiaomi.push.im) r0)     // Catch:{ je -> 0x043a }
            goto L_0x09a5
        L_0x034c:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.CustomClientConfigUpdate
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x036f
            com.xiaomi.push.il r0 = new com.xiaomi.push.il
            r0.<init>()
            byte[] r2 = r4.a()     // Catch:{ je -> 0x043a }
            com.xiaomi.push.iy.a(r0, (byte[]) r2)     // Catch:{ je -> 0x043a }
            android.content.Context r2 = r1.b     // Catch:{ je -> 0x043a }
            com.xiaomi.push.service.ah r2 = com.xiaomi.push.service.ah.a((android.content.Context) r2)     // Catch:{ je -> 0x043a }
            com.xiaomi.push.service.ai.a((com.xiaomi.push.service.ah) r2, (com.xiaomi.push.il) r0)     // Catch:{ je -> 0x043a }
            goto L_0x09a5
        L_0x036f:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.SyncInfoResult
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0382
            android.content.Context r0 = r1.b
            com.xiaomi.mipush.sdk.bc.a((android.content.Context) r0, (com.xiaomi.push.in) r4)
            goto L_0x09a5
        L_0x0382:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.ForceSync
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x039a
            java.lang.String r0 = "receive force sync notification"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
            android.content.Context r0 = r1.b
            com.xiaomi.mipush.sdk.bc.a((android.content.Context) r0, (boolean) r7)
            goto L_0x09a5
        L_0x039a:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.CancelPushMessage
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x041b
            java.util.Map r0 = r4.a()
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            java.lang.String r2 = com.xiaomi.push.service.aq.J
            boolean r0 = r0.containsKey(r2)
            r2 = -2
            if (r0 == 0) goto L_0x03d6
            java.util.Map r0 = r4.a()
            java.lang.String r3 = com.xiaomi.push.service.aq.J
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x03d6
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x03d1 }
            r2 = r0
            goto L_0x03d6
        L_0x03d1:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x03d6:
            r0 = -1
            if (r2 < r0) goto L_0x03e0
            android.content.Context r0 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.b((android.content.Context) r0, (int) r2)
            goto L_0x09a5
        L_0x03e0:
            java.lang.String r0 = ""
            java.lang.String r2 = ""
            java.util.Map r3 = r4.a()
            java.lang.String r5 = com.xiaomi.push.service.aq.H
            boolean r3 = r3.containsKey(r5)
            if (r3 == 0) goto L_0x03fc
            java.util.Map r0 = r4.a()
            java.lang.String r3 = com.xiaomi.push.service.aq.H
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
        L_0x03fc:
            java.util.Map r3 = r4.a()
            java.lang.String r5 = com.xiaomi.push.service.aq.I
            boolean r3 = r3.containsKey(r5)
            if (r3 == 0) goto L_0x0414
            java.util.Map r2 = r4.a()
            java.lang.String r3 = com.xiaomi.push.service.aq.I
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
        L_0x0414:
            android.content.Context r3 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.h(r3, r0, r2)
            goto L_0x09a5
        L_0x041b:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.HybridRegisterResult
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0440
            com.xiaomi.push.ip r0 = new com.xiaomi.push.ip     // Catch:{ je -> 0x043a }
            r0.<init>()     // Catch:{ je -> 0x043a }
            byte[] r2 = r4.a()     // Catch:{ je -> 0x043a }
            com.xiaomi.push.iy.a(r0, (byte[]) r2)     // Catch:{ je -> 0x043a }
            android.content.Context r2 = r1.b     // Catch:{ je -> 0x043a }
            com.xiaomi.mipush.sdk.MiPushClient4Hybrid.a((android.content.Context) r2, (com.xiaomi.push.ip) r0)     // Catch:{ je -> 0x043a }
            goto L_0x09a5
        L_0x043a:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r0)
            goto L_0x09a5
        L_0x0440:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.HybridUnregisterResult
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x045f
            com.xiaomi.push.iv r0 = new com.xiaomi.push.iv     // Catch:{ je -> 0x043a }
            r0.<init>()     // Catch:{ je -> 0x043a }
            byte[] r2 = r4.a()     // Catch:{ je -> 0x043a }
            com.xiaomi.push.iy.a(r0, (byte[]) r2)     // Catch:{ je -> 0x043a }
            android.content.Context r2 = r1.b     // Catch:{ je -> 0x043a }
            com.xiaomi.mipush.sdk.MiPushClient4Hybrid.a((android.content.Context) r2, (com.xiaomi.push.iv) r0)     // Catch:{ je -> 0x043a }
            goto L_0x09a5
        L_0x045f:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.PushLogUpload
            java.lang.String r0 = r0.f114a
            java.lang.String r2 = r4.d
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            java.lang.String r2 = "packages"
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x09a5
            java.util.Map r0 = r4.a()
            java.lang.String r2 = "packages"
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = ","
            java.lang.String[] r0 = r0.split(r2)
            android.content.Context r2 = r1.b
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = "com.xiaomi.xmsf"
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L_0x04a9
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.Logger.a((android.content.Context) r2, (boolean) r6)
            android.content.Context r2 = r1.b
            r1.a((android.content.Context) r2, (java.lang.String[]) r0)
            goto L_0x09a5
        L_0x04a9:
            android.content.Context r0 = r1.b
            com.xiaomi.mipush.sdk.Logger.a((android.content.Context) r0, (boolean) r7)
            goto L_0x09a5
        L_0x04b0:
            android.content.Context r0 = r1.b
            java.lang.String r0 = r0.getPackageName()
            android.content.Context r2 = r1.b
            com.xiaomi.push.ho r5 = com.xiaomi.push.ho.Command
            int r3 = r3.length
            com.xiaomi.push.dh.a(r0, r2, r4, r5, r3)
            com.xiaomi.push.ij r4 = (com.xiaomi.push.ij) r4
            java.lang.String r13 = r4.a()
            java.util.List r0 = r4.a()
            long r2 = r4.f12802a
            int r5 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r5 != 0) goto L_0x05c3
            com.xiaomi.push.fi r2 = com.xiaomi.push.fi.COMMAND_SET_ACCEPT_TIME
            java.lang.String r2 = r2.f70a
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x052c
            if (r0 == 0) goto L_0x052c
            int r2 = r0.size()
            if (r2 <= r6) goto L_0x052c
            android.content.Context r2 = r1.b
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r5 = r0.get(r6)
            java.lang.String r5 = (java.lang.String) r5
            com.xiaomi.mipush.sdk.MiPushClient.i(r2, r3, r5)
            java.lang.String r2 = "00:00"
            java.lang.Object r3 = r0.get(r7)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0513
            java.lang.String r2 = "00:00"
            java.lang.Object r3 = r0.get(r6)
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0513
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.b r2 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r2)
            r2.a((boolean) r6)
            goto L_0x051c
        L_0x0513:
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.b r2 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r2)
            r2.a((boolean) r7)
        L_0x051c:
            java.lang.String r2 = "GMT+08"
            java.util.TimeZone r2 = java.util.TimeZone.getTimeZone(r2)
            java.util.TimeZone r3 = java.util.TimeZone.getDefault()
            java.util.List r0 = r1.a((java.util.TimeZone) r2, (java.util.TimeZone) r3, (java.util.List<java.lang.String>) r0)
            goto L_0x05c3
        L_0x052c:
            com.xiaomi.push.fi r2 = com.xiaomi.push.fi.COMMAND_SET_ALIAS
            java.lang.String r2 = r2.f70a
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x054b
            if (r0 == 0) goto L_0x054b
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x054b
            android.content.Context r2 = r1.b
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.d(r2, r3)
            goto L_0x05c3
        L_0x054b:
            com.xiaomi.push.fi r2 = com.xiaomi.push.fi.COMMAND_UNSET_ALIAS
            java.lang.String r2 = r2.f70a
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x0569
            if (r0 == 0) goto L_0x0569
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x0569
            android.content.Context r2 = r1.b
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.e(r2, r3)
            goto L_0x05c3
        L_0x0569:
            com.xiaomi.push.fi r2 = com.xiaomi.push.fi.COMMAND_SET_ACCOUNT
            java.lang.String r2 = r2.f70a
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x0587
            if (r0 == 0) goto L_0x0587
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x0587
            android.content.Context r2 = r1.b
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.f(r2, r3)
            goto L_0x05c3
        L_0x0587:
            com.xiaomi.push.fi r2 = com.xiaomi.push.fi.COMMAND_UNSET_ACCOUNT
            java.lang.String r2 = r2.f70a
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x05a5
            if (r0 == 0) goto L_0x05a5
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x05a5
            android.content.Context r2 = r1.b
            java.lang.Object r3 = r0.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.mipush.sdk.MiPushClient.g(r2, r3)
            goto L_0x05c3
        L_0x05a5:
            com.xiaomi.push.fi r2 = com.xiaomi.push.fi.COMMAND_CHK_VDEVID
            java.lang.String r2 = r2.f70a
            boolean r2 = android.text.TextUtils.equals(r13, r2)
            if (r2 == 0) goto L_0x05c3
            if (r0 == 0) goto L_0x05c2
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x05c2
            android.content.Context r2 = r1.b
            java.lang.Object r0 = r0.get(r7)
            java.lang.String r0 = (java.lang.String) r0
            com.xiaomi.push.i.a((android.content.Context) r2, (java.lang.String) r0)
        L_0x05c2:
            return r10
        L_0x05c3:
            r14 = r0
            long r2 = r4.f12802a
            java.lang.String r0 = r4.d
            java.lang.String r18 = r4.b()
            r15 = r2
            r17 = r0
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.a(r13, r14, r15, r17, r18)
            return r0
        L_0x05d4:
            com.xiaomi.push.ix r4 = (com.xiaomi.push.ix) r4
            long r2 = r4.f12816a
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x05e5
            android.content.Context r0 = r1.b
            java.lang.String r2 = r4.a()
            com.xiaomi.mipush.sdk.MiPushClient.i(r0, r2)
        L_0x05e5:
            java.lang.String r0 = r4.a()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x05fb
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r0 = r4.a()
            r10.add(r0)
        L_0x05fb:
            r12 = r10
            com.xiaomi.push.fi r0 = com.xiaomi.push.fi.COMMAND_UNSUBSCRIBE_TOPIC
            java.lang.String r11 = r0.f70a
            long r13 = r4.f12816a
            java.lang.String r15 = r4.d
            java.lang.String r16 = r4.b()
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.a(r11, r12, r13, r15, r16)
            return r0
        L_0x060d:
            com.xiaomi.push.it r4 = (com.xiaomi.push.it) r4
            long r2 = r4.f12812a
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x061e
            android.content.Context r0 = r1.b
            java.lang.String r2 = r4.a()
            com.xiaomi.mipush.sdk.MiPushClient.h(r0, r2)
        L_0x061e:
            java.lang.String r0 = r4.a()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0634
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r0 = r4.a()
            r10.add(r0)
        L_0x0634:
            r12 = r10
            com.xiaomi.push.fi r0 = com.xiaomi.push.fi.COMMAND_SUBSCRIBE_TOPIC
            java.lang.String r11 = r0.f70a
            long r13 = r4.f12812a
            java.lang.String r15 = r4.d
            java.lang.String r16 = r4.b()
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.a(r11, r12, r13, r15, r16)
            return r0
        L_0x0646:
            com.xiaomi.push.iv r4 = (com.xiaomi.push.iv) r4
            long r2 = r4.f12814a
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x065c
            android.content.Context r0 = r1.b
            com.xiaomi.mipush.sdk.b r0 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r0)
            r0.i()
            android.content.Context r0 = r1.b
            com.xiaomi.mipush.sdk.MiPushClient.e(r0)
        L_0x065c:
            com.xiaomi.mipush.sdk.PushMessageHandler.a()
            goto L_0x09a5
        L_0x0661:
            r0 = r4
            com.xiaomi.push.ip r0 = (com.xiaomi.push.ip) r0
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.b r2 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r2)
            java.lang.String r2 = r2.f11544a
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x06f4
            java.lang.String r3 = r0.a()
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 != 0) goto L_0x067e
            goto L_0x06f4
        L_0x067e:
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.b r2 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r2)
            r2.f11544a = r10
            long r2 = r0.f191a
            int r4 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r4 != 0) goto L_0x06b5
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.b r2 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r2)
            java.lang.String r3 = r0.e
            java.lang.String r4 = r0.f
            java.lang.String r5 = r0.l
            r2.b(r3, r4, r5)
            android.content.Context r2 = r1.b
            com.xiaomi.push.fd r2 = com.xiaomi.push.fd.a((android.content.Context) r2)
            android.content.Context r3 = r1.b
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.fc.a((int) r24)
            r6 = 6006(0x1776, float:8.416E-42)
            java.lang.String r7 = "receive register result success"
        L_0x06af:
            r5 = r23
            r2.a(r3, r4, r5, r6, r7)
            goto L_0x06ca
        L_0x06b5:
            android.content.Context r2 = r1.b
            com.xiaomi.push.fd r2 = com.xiaomi.push.fd.a((android.content.Context) r2)
            android.content.Context r3 = r1.b
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.fc.a((int) r24)
            r6 = 6006(0x1776, float:8.416E-42)
            java.lang.String r7 = "receive register result fail"
            goto L_0x06af
        L_0x06ca:
            java.lang.String r2 = r0.e
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x06dc
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r2 = r0.e
            r10.add(r2)
        L_0x06dc:
            r4 = r10
            com.xiaomi.push.fi r2 = com.xiaomi.push.fi.COMMAND_REGISTER
            java.lang.String r3 = r2.f70a
            long r5 = r0.f191a
            java.lang.String r7 = r0.d
            r8 = 0
            com.xiaomi.mipush.sdk.MiPushCommandMessage r0 = com.xiaomi.mipush.sdk.PushMessageHelper.a(r3, r4, r5, r7, r8)
            android.content.Context r2 = r1.b
            com.xiaomi.mipush.sdk.aw r2 = com.xiaomi.mipush.sdk.aw.a((android.content.Context) r2)
            r2.e()
            return r0
        L_0x06f4:
            java.lang.String r0 = "bad Registration result:"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
            android.content.Context r0 = r1.b
            com.xiaomi.push.fd r0 = com.xiaomi.push.fd.a((android.content.Context) r0)
            android.content.Context r2 = r1.b
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.fc.a((int) r24)
            java.lang.String r4 = "bad Registration result:"
            r0.b(r2, r3, r8, r4)
            return r10
        L_0x070f:
            boolean r5 = r20.b()
            if (r5 != 0) goto L_0x071b
            java.lang.String r0 = "receiving an un-encrypt message(SendMessage)."
            com.xiaomi.channel.commonutils.logger.b.d(r0)
            return r10
        L_0x071b:
            android.content.Context r5 = r1.b
            com.xiaomi.mipush.sdk.b r5 = com.xiaomi.mipush.sdk.b.a((android.content.Context) r5)
            boolean r5 = r5.l()
            if (r5 == 0) goto L_0x0744
            if (r0 != 0) goto L_0x0744
            java.lang.String r0 = "receive a message in pause state. drop it"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
            android.content.Context r0 = r1.b
            com.xiaomi.push.fd r0 = com.xiaomi.push.fd.a((android.content.Context) r0)
            android.content.Context r2 = r1.b
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.fc.a((int) r24)
            java.lang.String r4 = "receive a message in pause state. drop it"
            r0.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r8, (java.lang.String) r4)
            return r10
        L_0x0744:
            com.xiaomi.push.ir r4 = (com.xiaomi.push.ir) r4
            com.xiaomi.push.ia r5 = r4.a()
            if (r5 != 0) goto L_0x0767
            java.lang.String r0 = "receive an empty message without push content, drop it"
            com.xiaomi.channel.commonutils.logger.b.d(r0)
            android.content.Context r0 = r1.b
            com.xiaomi.push.fd r0 = com.xiaomi.push.fd.a((android.content.Context) r0)
            android.content.Context r2 = r1.b
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.fc.a((int) r24)
            java.lang.String r4 = "receive an empty message without push content, drop it"
            r0.b(r2, r3, r8, r4)
            return r10
        L_0x0767:
            if (r0 == 0) goto L_0x0794
            boolean r6 = com.xiaomi.push.service.aa.a((com.xiaomi.push.ik) r20)
            if (r6 == 0) goto L_0x0783
            android.content.Context r6 = r1.b
            java.lang.String r7 = r5.a()
            com.xiaomi.push.ib r13 = r20.a()
            java.lang.String r14 = r2.b
            java.lang.String r15 = r5.b()
            com.xiaomi.mipush.sdk.MiPushClient.a((android.content.Context) r6, (java.lang.String) r7, (com.xiaomi.push.ib) r13, (java.lang.String) r14, (java.lang.String) r15)
            goto L_0x0794
        L_0x0783:
            android.content.Context r6 = r1.b
            java.lang.String r7 = r5.a()
            com.xiaomi.push.ib r13 = r20.a()
            java.lang.String r14 = r5.b()
            com.xiaomi.mipush.sdk.MiPushClient.a((android.content.Context) r6, (java.lang.String) r7, (com.xiaomi.push.ib) r13, (java.lang.String) r14)
        L_0x0794:
            if (r0 != 0) goto L_0x07d9
            java.lang.String r6 = r4.d()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x07b8
            android.content.Context r6 = r1.b
            java.lang.String r7 = r4.d()
            long r6 = com.xiaomi.mipush.sdk.MiPushClient.l(r6, r7)
            int r13 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r13 >= 0) goto L_0x07b8
            android.content.Context r6 = r1.b
            java.lang.String r7 = r4.d()
            com.xiaomi.mipush.sdk.MiPushClient.d(r6, r7)
            goto L_0x07d9
        L_0x07b8:
            java.lang.String r6 = r4.c()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x07d9
            android.content.Context r6 = r1.b
            java.lang.String r7 = r4.c()
            long r6 = com.xiaomi.mipush.sdk.MiPushClient.j(r6, r7)
            int r13 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r13 >= 0) goto L_0x07d9
            android.content.Context r6 = r1.b
            java.lang.String r7 = r4.c()
            com.xiaomi.mipush.sdk.MiPushClient.h(r6, r7)
        L_0x07d9:
            com.xiaomi.push.ib r6 = r2.f166a
            if (r6 == 0) goto L_0x07f2
            com.xiaomi.push.ib r6 = r2.f166a
            java.util.Map r6 = r6.a()
            if (r6 == 0) goto L_0x07f2
            com.xiaomi.push.ib r6 = r2.f166a
            java.util.Map<java.lang.String, java.lang.String> r6 = r6.f130a
            java.lang.String r7 = "jobkey"
            java.lang.Object r6 = r6.get(r7)
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x07f3
        L_0x07f2:
            r6 = r10
        L_0x07f3:
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L_0x07fd
            java.lang.String r6 = r5.a()
        L_0x07fd:
            if (r0 != 0) goto L_0x0841
            android.content.Context r7 = r1.b
            boolean r7 = b(r7, r6)
            if (r7 == 0) goto L_0x0841
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "drop a duplicate message, key="
            r3.append(r5)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r3)
            android.content.Context r3 = r1.b
            com.xiaomi.push.fd r3 = com.xiaomi.push.fd.a((android.content.Context) r3)
            android.content.Context r5 = r1.b
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r7 = com.xiaomi.push.fc.a((int) r24)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "drop a duplicate message, key="
            r9.append(r11)
            r9.append(r6)
            java.lang.String r6 = r9.toString()
            r3.c(r5, r7, r8, r6)
            goto L_0x099a
        L_0x0841:
            com.xiaomi.push.ib r7 = r20.a()
            com.xiaomi.mipush.sdk.MiPushMessage r7 = com.xiaomi.mipush.sdk.PushMessageHelper.a(r4, r7, r0)
            int r11 = r7.getPassThrough()
            if (r11 != 0) goto L_0x0861
            if (r0 != 0) goto L_0x0861
            java.util.Map r11 = r7.getExtra()
            boolean r11 = com.xiaomi.push.service.aa.a((java.util.Map<java.lang.String, java.lang.String>) r11)
            if (r11 == 0) goto L_0x0861
            android.content.Context r0 = r1.b
            com.xiaomi.push.service.aa.a((android.content.Context) r0, (com.xiaomi.push.ik) r2, (byte[]) r3)
            return r10
        L_0x0861:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r11 = "receive a message, msgid="
            r3.append(r11)
            java.lang.String r11 = r5.a()
            r3.append(r11)
            java.lang.String r11 = ", jobkey="
            r3.append(r11)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r3)
            if (r0 == 0) goto L_0x0999
            java.util.Map r3 = r7.getExtra()
            if (r3 == 0) goto L_0x0999
            java.util.Map r3 = r7.getExtra()
            java.lang.String r6 = "notify_effect"
            boolean r3 = r3.containsKey(r6)
            if (r3 == 0) goto L_0x0999
            java.util.Map r0 = r7.getExtra()
            java.lang.String r3 = "notify_effect"
            java.lang.Object r3 = r0.get(r3)
            r11 = r3
            java.lang.String r11 = (java.lang.String) r11
            boolean r3 = com.xiaomi.push.service.aa.a((com.xiaomi.push.ik) r20)
            if (r3 == 0) goto L_0x0915
            android.content.Context r3 = r1.b
            java.lang.String r2 = r2.b
            android.content.Intent r0 = a((android.content.Context) r3, (java.lang.String) r2, (java.util.Map<java.lang.String, java.lang.String>) r0)
            java.lang.String r2 = "eventMessageType"
            r0.putExtra(r2, r9)
            java.lang.String r2 = "messageId"
            r0.putExtra(r2, r8)
            if (r0 != 0) goto L_0x08d7
            java.lang.String r0 = "Getting Intent fail from ignore reg message. "
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
            android.content.Context r0 = r1.b
            com.xiaomi.push.fd r0 = com.xiaomi.push.fd.a((android.content.Context) r0)
            android.content.Context r2 = r1.b
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.fc.a((int) r24)
            java.lang.String r4 = "Getting Intent fail from ignore reg message."
            r0.b(r2, r3, r8, r4)
            return r10
        L_0x08d7:
            java.lang.String r2 = r5.c()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x08e6
            java.lang.String r3 = "payload"
            r0.putExtra(r3, r2)
        L_0x08e6:
            android.content.Context r2 = r1.b
            r2.startActivity(r0)
            android.content.Context r0 = r1.b
            com.xiaomi.push.fd r2 = com.xiaomi.push.fd.a((android.content.Context) r0)
            android.content.Context r0 = r1.b
            java.lang.String r3 = r0.getPackageName()
            java.lang.String r4 = com.xiaomi.push.fc.a((int) r24)
            r6 = 3006(0xbbe, float:4.212E-42)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "business message is clicked typeId "
            r0.append(r5)
            r0.append(r11)
            java.lang.String r7 = r0.toString()
            r5 = r23
            r2.a(r3, r4, r5, r6, r7)
            goto L_0x0998
        L_0x0915:
            android.content.Context r2 = r1.b
            android.content.Context r3 = r1.b
            java.lang.String r3 = r3.getPackageName()
            android.content.Intent r0 = a((android.content.Context) r2, (java.lang.String) r3, (java.util.Map<java.lang.String, java.lang.String>) r0)
            if (r0 == 0) goto L_0x0998
            java.lang.String r2 = com.xiaomi.push.service.aq.c
            boolean r2 = r11.equals(r2)
            if (r2 != 0) goto L_0x093a
            java.lang.String r2 = "key_message"
            r0.putExtra(r2, r7)
            java.lang.String r2 = "eventMessageType"
            r0.putExtra(r2, r9)
            java.lang.String r2 = "messageId"
            r0.putExtra(r2, r8)
        L_0x093a:
            android.content.Context r2 = r1.b
            r2.startActivity(r0)
            java.lang.String r0 = "start activity succ"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)
            android.content.Context r0 = r1.b
            com.xiaomi.push.fd r2 = com.xiaomi.push.fd.a((android.content.Context) r0)
            android.content.Context r0 = r1.b
            java.lang.String r3 = r0.getPackageName()
            java.lang.String r4 = com.xiaomi.push.fc.a((int) r24)
            r6 = 1006(0x3ee, float:1.41E-42)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "notification message is clicked typeId "
            r0.append(r5)
            r0.append(r11)
            java.lang.String r7 = r0.toString()
            r5 = r23
            r2.a(r3, r4, r5, r6, r7)
            java.lang.String r0 = com.xiaomi.push.service.aq.c
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto L_0x0998
            android.content.Context r0 = r1.b
            com.xiaomi.push.fd r0 = com.xiaomi.push.fd.a((android.content.Context) r0)
            android.content.Context r2 = r1.b
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r3 = com.xiaomi.push.fc.a((int) r24)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "try open web page typeId "
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            r0.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r8, (java.lang.String) r4)
        L_0x0998:
            return r10
        L_0x0999:
            r10 = r7
        L_0x099a:
            com.xiaomi.push.ib r3 = r20.a()
            if (r3 != 0) goto L_0x09a5
            if (r0 != 0) goto L_0x09a5
            r1.a((com.xiaomi.push.ir) r4, (com.xiaomi.push.ik) r2)
        L_0x09a5:
            return r10
        L_0x09a6:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r0)
            java.lang.String r2 = "receive a message which action string is not valid. is the reg expired?"
            com.xiaomi.channel.commonutils.logger.b.d(r2)
            android.content.Context r2 = r1.b
            com.xiaomi.push.fd r2 = com.xiaomi.push.fd.a((android.content.Context) r2)
            android.content.Context r3 = r1.b
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.fc.a((int) r24)
            r2.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r8, (java.lang.Throwable) r0)
            return r10
        L_0x09c3:
            r0 = move-exception
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r0)
            r19.a((com.xiaomi.push.ik) r20)
            android.content.Context r2 = r1.b
            com.xiaomi.push.fd r2 = com.xiaomi.push.fd.a((android.content.Context) r2)
            android.content.Context r3 = r1.b
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = com.xiaomi.push.fc.a((int) r24)
            r2.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r8, (java.lang.Throwable) r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.at.a(com.xiaomi.push.ik, boolean, byte[], java.lang.String, int):com.xiaomi.mipush.sdk.PushMessageHandler$a");
    }

    private PushMessageHandler.a a(ik ikVar, byte[] bArr) {
        String str;
        String str2 = null;
        try {
            iz a2 = ap.a(this.b, ikVar);
            if (a2 == null) {
                b.d("message arrived: receiving an un-recognized message. " + ikVar.f12803a);
                return null;
            }
            ho a3 = ikVar.a();
            b.a("message arrived: processing an arrived message, action=" + a3);
            if (av.f11538a[a3.ordinal()] != 1) {
                return null;
            }
            if (!ikVar.b()) {
                str = "message arrived: receiving an un-encrypt message(SendMessage).";
            } else {
                ir irVar = (ir) a2;
                ia a4 = irVar.a();
                if (a4 == null) {
                    str = "message arrived: receive an empty message without push content, drop it";
                } else {
                    if (!(ikVar.f166a == null || ikVar.f166a.a() == null)) {
                        str2 = ikVar.f166a.f130a.get("jobkey");
                    }
                    MiPushMessage a5 = PushMessageHelper.a(irVar, ikVar.a(), false);
                    a5.setArrivedMessage(true);
                    b.a("message arrived: receive a message, msgid=" + a4.a() + ", jobkey=" + str2);
                    return a5;
                }
            }
            b.d(str);
            return null;
        } catch (t e) {
            b.a((Throwable) e);
            str = "message arrived: receive a message but decrypt failed. report when click.";
        } catch (je e2) {
            b.a((Throwable) e2);
            str = "message arrived: receive a message which action string is not valid. is the reg expired?";
        }
    }

    public static at a(Context context) {
        if (f11536a == null) {
            f11536a = new at(context);
        }
        return f11536a;
    }

    private void a() {
        SharedPreferences sharedPreferences = this.b.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong(Constants.f11504a, 0)) > 1800000) {
            MiPushClient.a(this.b, ic.PackageUnregistered);
            sharedPreferences.edit().putLong(Constants.f11504a, currentTimeMillis).commit();
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            int length = serviceInfoArr.length;
            int i = 0;
            while (i < length) {
                ServiceInfo serviceInfo = serviceInfoArr[i];
                if (!serviceInfo.exported || !serviceInfo.enabled || !"com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) || context.getPackageName().equals(serviceInfo.packageName)) {
                    i++;
                } else {
                    try {
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.SYNC_LOG");
                        PushMessageHandler.a(context, intent);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
            }
        }
    }

    public static void a(Context context, String str) {
        synchronized (d) {
            c.remove(str);
            b.a(context);
            SharedPreferences b2 = b.b(context);
            String a2 = bf.a((Collection<?>) c, ",");
            SharedPreferences.Editor edit = b2.edit();
            edit.putString("pref_msg_ids", a2);
            r.a(edit);
        }
    }

    private void a(Context context, String[] strArr) {
        ai.a(context).a((Runnable) new au(this, strArr, context));
    }

    private void a(Cif ifVar) {
        String a2 = ifVar.a();
        b.b("receive ack " + a2);
        Map a3 = ifVar.a();
        if (a3 != null) {
            String str = (String) a3.get("real_source");
            if (!TextUtils.isEmpty(str)) {
                b.b("receive ack : messageId = " + a2 + "  realSource = " + str);
                br.a(this.b).a(a2, str, Boolean.valueOf(ifVar.f12798a == 0));
            }
        }
    }

    private void a(ik ikVar) {
        b.a("receive a message but decrypt failed. report now.");
        in inVar = new in(ikVar.a().f128a, false);
        inVar.c(hy.DecryptMessageFail.f114a);
        inVar.b(ikVar.a());
        inVar.d(ikVar.b);
        inVar.f177a = new HashMap();
        inVar.f177a.put("regid", MiPushClient.o(this.b));
        aw.a(this.b).a(inVar, ho.Notification, false, (ib) null);
    }

    private void a(ir irVar, ik ikVar) {
        ib a2 = ikVar.a();
        ie ieVar = new ie();
        ieVar.b(irVar.b());
        ieVar.a(irVar.a());
        ieVar.a(irVar.a().a());
        if (!TextUtils.isEmpty(irVar.c())) {
            ieVar.c(irVar.c());
        }
        if (!TextUtils.isEmpty(irVar.d())) {
            ieVar.d(irVar.d());
        }
        ieVar.a(iy.a(this.b, ikVar));
        aw.a(this.b).a(ieVar, ho.AckMessage, a2);
    }

    private void a(String str, long j, d dVar) {
        bb c2 = k.c(dVar);
        if (c2 != null) {
            if (j == 0) {
                synchronized (am.class) {
                    if (am.a(this.b).e(str)) {
                        am.a(this.b).d(str);
                        if ("syncing".equals(am.a(this.b).a(c2))) {
                            am.a(this.b).a(c2, "synced");
                        }
                    }
                }
            } else if ("syncing".equals(am.a(this.b).a(c2))) {
                synchronized (am.class) {
                    if (am.a(this.b).e(str)) {
                        if (am.a(this.b).c(str) < 10) {
                            am.a(this.b).b(str);
                            aw.a(this.b).a(str, c2, dVar);
                        } else {
                            am.a(this.b).d(str);
                        }
                    }
                }
            } else {
                am.a(this.b).d(str);
            }
        }
    }

    private void b(Cif ifVar) {
        long j;
        d dVar;
        b.c("ASSEMBLE_PUSH : " + ifVar.toString());
        String a2 = ifVar.a();
        Map a3 = ifVar.a();
        if (a3 != null) {
            String str = (String) a3.get(Constants.D);
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("brand:" + an.c.name())) {
                    b.a("ASSEMBLE_PUSH : receive fcm token sync ack");
                    h.b(this.b, d.ASSEMBLE_PUSH_FCM, str);
                    j = ifVar.f12798a;
                    dVar = d.ASSEMBLE_PUSH_FCM;
                } else {
                    if (str.contains("brand:" + an.HUAWEI.name())) {
                        b.a("ASSEMBLE_PUSH : receive hw token sync ack");
                        h.b(this.b, d.ASSEMBLE_PUSH_HUAWEI, str);
                        j = ifVar.f12798a;
                        dVar = d.ASSEMBLE_PUSH_HUAWEI;
                    } else {
                        if (str.contains("brand:" + an.OPPO.name())) {
                            b.a("ASSEMBLE_PUSH : receive COS token sync ack");
                            h.b(this.b, d.ASSEMBLE_PUSH_COS, str);
                            j = ifVar.f12798a;
                            dVar = d.ASSEMBLE_PUSH_COS;
                        } else {
                            if (str.contains("brand:" + an.VIVO.name())) {
                                b.a("ASSEMBLE_PUSH : receive FTOS token sync ack");
                                h.b(this.b, d.ASSEMBLE_PUSH_FTOS, str);
                                j = ifVar.f12798a;
                                dVar = d.ASSEMBLE_PUSH_FTOS;
                            } else {
                                return;
                            }
                        }
                    }
                }
                a(a2, j, dVar);
            }
        }
    }

    private void b(ik ikVar) {
        ib a2 = ikVar.a();
        ie ieVar = new ie();
        ieVar.b(ikVar.a());
        ieVar.a(a2.a());
        ieVar.a(a2.a());
        if (!TextUtils.isEmpty(a2.b())) {
            ieVar.c(a2.b());
        }
        ieVar.a(iy.a(this.b, ikVar));
        aw.a(this.b).a(ieVar, ho.AckMessage, false, ikVar.a());
    }

    private static boolean b(Context context, String str) {
        synchronized (d) {
            b.a(context);
            SharedPreferences b2 = b.b(context);
            if (c == null) {
                String[] split = b2.getString("pref_msg_ids", "").split(",");
                c = new LinkedList();
                for (String add : split) {
                    c.add(add);
                }
            }
            if (c.contains(str)) {
                return true;
            }
            c.add(str);
            if (c.size() > 25) {
                c.poll();
            }
            String a2 = bf.a((Collection<?>) c, ",");
            SharedPreferences.Editor edit = b2.edit();
            edit.putString("pref_msg_ids", a2);
            r.a(edit);
            return false;
        }
    }

    private boolean c(ik ikVar) {
        if (!TextUtils.equals(Constants.t, ikVar.b()) && !TextUtils.equals(Constants.u, ikVar.b())) {
            return false;
        }
        Map a2 = ikVar.a() == null ? null : ikVar.a().a();
        if (a2 == null) {
            return false;
        }
        String str = (String) a2.get(Constants.w);
        return TextUtils.equals(str, Constants.x) || TextUtils.equals(str, Constants.y);
    }

    public PushMessageHandler.a a(Intent intent) {
        String str;
        fd a2;
        String packageName;
        String format;
        String action = intent.getAction();
        b.a("receive an intent from server, action=" + action);
        String stringExtra = intent.getStringExtra("mrt");
        if (stringExtra == null) {
            stringExtra = Long.toString(System.currentTimeMillis());
        }
        String stringExtra2 = intent.getStringExtra("messageId");
        int intExtra = intent.getIntExtra("eventMessageType", -1);
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                b.d("receiving an empty message, drop");
                fd.a(this.b).a(this.b.getPackageName(), intent, "receiving an empty message, drop");
                return null;
            }
            ik ikVar = new ik();
            try {
                iy.a(ikVar, byteArrayExtra);
                b a3 = b.a(this.b);
                ib a4 = ikVar.a();
                if (ikVar.a() == ho.SendMessage && a4 != null && !a3.l() && !booleanExtra) {
                    a4.a("mrt", stringExtra);
                    a4.a("mat", Long.toString(System.currentTimeMillis()));
                    if (!c(ikVar)) {
                        b(ikVar);
                    } else {
                        b.b("this is a mina's message, ack later");
                        a4.a(Constants.A, String.valueOf(a4.a()));
                        a4.a(Constants.B, String.valueOf(iy.a(this.b, ikVar)));
                    }
                }
                if (ikVar.a() != ho.SendMessage || ikVar.b()) {
                    if (ikVar.a() == ho.SendMessage && ikVar.b() && aa.a(ikVar)) {
                        if (!booleanExtra || a4 == null || a4.a() == null || !a4.a().containsKey("notify_effect")) {
                            Object[] objArr = new Object[2];
                            objArr[0] = ikVar.b();
                            objArr[1] = a4 != null ? a4.a() : "";
                            b.a(String.format("drop a wake-up messages which not has 'notify_effect' attr. %1$s, %2$s", objArr));
                            fd a5 = fd.a(this.b);
                            String packageName2 = this.b.getPackageName();
                            Object[] objArr2 = new Object[2];
                            objArr2[0] = ikVar.b();
                            objArr2[1] = a4 != null ? a4.a() : "";
                            a5.a(packageName2, intent, String.format("drop a wake-up messages which not has 'notify_effect' attr. %1$s, %2$s", objArr2));
                            return null;
                        }
                    }
                    if (a3.j() || ikVar.f12803a == ho.Registration) {
                        if (!a3.j() || !a3.n()) {
                            return a(ikVar, booleanExtra, byteArrayExtra, stringExtra2, intExtra);
                        }
                        if (ikVar.f12803a == ho.UnRegistration) {
                            a3.i();
                            MiPushClient.e(this.b);
                            PushMessageHandler.a();
                        } else {
                            MiPushClient.g(this.b);
                        }
                    } else if (aa.a(ikVar)) {
                        return a(ikVar, booleanExtra, byteArrayExtra, stringExtra2, intExtra);
                    } else {
                        b.d("receive message without registration. need re-register!");
                        fd.a(this.b).a(this.b.getPackageName(), intent, "receive message without registration. need re-register!");
                        a();
                    }
                } else {
                    if (aa.a(ikVar)) {
                        Object[] objArr3 = new Object[2];
                        objArr3[0] = ikVar.b();
                        objArr3[1] = a4 != null ? a4.a() : "";
                        b.a(String.format("drop an un-encrypted wake-up messages. %1$s, %2$s", objArr3));
                        a2 = fd.a(this.b);
                        packageName = this.b.getPackageName();
                        Object[] objArr4 = new Object[2];
                        objArr4[0] = ikVar.b();
                        objArr4[1] = a4 != null ? a4.a() : "";
                        format = String.format("drop an un-encrypted wake-up  messages. %1$s, %2$s", objArr4);
                    } else {
                        Object[] objArr5 = new Object[2];
                        objArr5[0] = ikVar.b();
                        objArr5[1] = a4 != null ? a4.a() : "";
                        b.a(String.format("drop an un-encrypted messages. %1$s, %2$s", objArr5));
                        a2 = fd.a(this.b);
                        packageName = this.b.getPackageName();
                        Object[] objArr6 = new Object[2];
                        objArr6[0] = ikVar.b();
                        objArr6[1] = a4 != null ? a4.a() : "";
                        format = String.format("drop an un-encrypted messages. %1$s, %2$s", objArr6);
                    }
                    a2.a(packageName, intent, format);
                    return null;
                }
            } catch (je | Exception e) {
                fd.a(this.b).a(this.b.getPackageName(), intent, e);
                b.a(e);
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
            ik ikVar2 = new ik();
            try {
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra2 != null) {
                    iy.a(ikVar2, byteArrayExtra2);
                }
            } catch (je unused) {
            }
            miPushCommandMessage.setCommand(String.valueOf(ikVar2.a()));
            miPushCommandMessage.setResultCode((long) intent.getIntExtra("mipush_error_code", 0));
            miPushCommandMessage.setReason(intent.getStringExtra("mipush_error_msg"));
            b.d("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
            return miPushCommandMessage;
        } else if ("com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
            byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra3 == null) {
                b.d("message arrived: receiving an empty message, drop");
                return null;
            }
            ik ikVar3 = new ik();
            try {
                iy.a(ikVar3, byteArrayExtra3);
                b a6 = b.a(this.b);
                if (aa.a(ikVar3)) {
                    str = "message arrived: receive ignore reg message, ignore!";
                } else if (!a6.j()) {
                    str = "message arrived: receive message without registration. need unregister or re-register!";
                } else if (!a6.j() || !a6.n()) {
                    return a(ikVar3, byteArrayExtra3);
                } else {
                    str = "message arrived: app info is invalidated";
                }
                b.d(str);
            } catch (je | Exception e2) {
                b.a(e2);
            }
        }
        return null;
    }

    public List<String> a(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        List<String> list2 = list;
        if (timeZone.equals(timeZone2)) {
            return list2;
        }
        long rawOffset = (long) (((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60);
        long parseLong = Long.parseLong(list2.get(0).split(":")[0]);
        long parseLong2 = Long.parseLong(list2.get(0).split(":")[1]);
        long parseLong3 = Long.parseLong(list2.get(1).split(":")[0]);
        long parseLong4 = Long.parseLong(list2.get(1).split(":")[1]);
        long j = ((((parseLong * 60) + parseLong2) - rawOffset) + DateTimeHelper.e) % DateTimeHelper.e;
        long j2 = ((((parseLong3 * 60) + parseLong4) - rawOffset) + DateTimeHelper.e) % DateTimeHelper.e;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j / 60), Long.valueOf(j % 60)}));
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j2 / 60), Long.valueOf(j2 % 60)}));
        return arrayList;
    }
}
