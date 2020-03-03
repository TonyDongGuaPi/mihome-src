package com.tencent.tinker.loader.hotplug;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.mi.mistatistic.sdk.data.EventData;
import com.miui.tsmclient.entity.CardUIInfo;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class IncrementComponentManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1356a = "Tinker.IncrementCompMgr";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static Context f = null;
    /* access modifiers changed from: private */
    public static String g = null;
    private static volatile boolean h = false;
    private static final Map<String, ActivityInfo> i = new HashMap();
    private static final Map<String, IntentFilter> j = new HashMap();
    private static final AttrTranslator<ActivityInfo> k = new AttrTranslator<ActivityInfo>() {
        /* access modifiers changed from: package-private */
        public void a(Context context, int i, XmlPullParser xmlPullParser) {
            if (i == 0) {
                try {
                    if (xmlPullParser.getEventType() != 2 || !"activity".equals(xmlPullParser.getName())) {
                        throw new IllegalStateException("unexpected xml parser state when parsing incremental component manifest.");
                    }
                } catch (XmlPullParserException e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(Context context, int i, String str, String str2, ActivityInfo activityInfo) {
            int i2;
            if ("name".equals(str)) {
                if (str2.charAt(0) == '.') {
                    activityInfo.name = context.getPackageName() + str2;
                    return;
                }
                activityInfo.name = str2;
            } else if ("parentActivityName".equals(str)) {
                if (Build.VERSION.SDK_INT < 16) {
                    return;
                }
                if (str2.charAt(0) == '.') {
                    activityInfo.parentActivityName = context.getPackageName() + str2;
                    return;
                }
                activityInfo.parentActivityName = str2;
            } else if ("exported".equals(str)) {
                activityInfo.exported = "true".equalsIgnoreCase(str2);
            } else if ("launchMode".equals(str)) {
                activityInfo.launchMode = a(str2);
            } else if ("theme".equals(str)) {
                activityInfo.theme = context.getResources().getIdentifier(str2, "style", context.getPackageName());
            } else if ("uiOptions".equals(str)) {
                if (Build.VERSION.SDK_INT >= 14) {
                    activityInfo.uiOptions = Integer.decode(str2).intValue();
                }
            } else if ("permission".equals(str)) {
                activityInfo.permission = str2;
            } else if ("taskAffinity".equals(str)) {
                activityInfo.taskAffinity = str2;
            } else if ("multiprocess".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 1;
                } else {
                    activityInfo.flags &= -2;
                }
            } else if ("finishOnTaskLaunch".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 2;
                } else {
                    activityInfo.flags &= -3;
                }
            } else if ("clearTaskOnLaunch".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 4;
                } else {
                    activityInfo.flags &= -5;
                }
            } else if ("noHistory".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 128;
                } else {
                    activityInfo.flags &= -129;
                }
            } else if ("alwaysRetainTaskState".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 8;
                } else {
                    activityInfo.flags &= -9;
                }
            } else if ("stateNotNeeded".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 16;
                } else {
                    activityInfo.flags &= -17;
                }
            } else if ("excludeFromRecents".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 32;
                } else {
                    activityInfo.flags &= -33;
                }
            } else if ("allowTaskReparenting".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 64;
                } else {
                    activityInfo.flags &= -65;
                }
            } else if ("finishOnCloseSystemDialogs".equals(str)) {
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 256;
                } else {
                    activityInfo.flags &= -257;
                }
            } else if ("showOnLockScreen".equals(str) || "showForAllUsers".equals(str)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int a2 = ShareReflectUtil.a((Class<?>) ActivityInfo.class, "FLAG_SHOW_FOR_ALL_USERS", 0);
                    if ("true".equalsIgnoreCase(str2)) {
                        activityInfo.flags = a2 | activityInfo.flags;
                        return;
                    }
                    activityInfo.flags = (a2 ^ -1) & activityInfo.flags;
                }
            } else if ("immersive".equals(str)) {
                if (Build.VERSION.SDK_INT < 18) {
                    return;
                }
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 2048;
                } else {
                    activityInfo.flags &= -2049;
                }
            } else if ("hardwareAccelerated".equals(str)) {
                if (Build.VERSION.SDK_INT < 11) {
                    return;
                }
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 512;
                } else {
                    activityInfo.flags &= -513;
                }
            } else if ("documentLaunchMode".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    activityInfo.documentLaunchMode = Integer.decode(str2).intValue();
                }
            } else if ("maxRecents".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    activityInfo.maxRecents = Integer.decode(str2).intValue();
                }
            } else if ("configChanges".equals(str)) {
                activityInfo.configChanges = Integer.decode(str2).intValue();
            } else if ("windowSoftInputMode".equals(str)) {
                activityInfo.softInputMode = Integer.decode(str2).intValue();
            } else if ("persistableMode".equals(str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    activityInfo.persistableMode = Integer.decode(str2).intValue();
                }
            } else if ("allowEmbedded".equals(str)) {
                int a3 = ShareReflectUtil.a((Class<?>) ActivityInfo.class, "FLAG_ALLOW_EMBEDDED", 0);
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags = a3 | activityInfo.flags;
                    return;
                }
                activityInfo.flags = (a3 ^ -1) & activityInfo.flags;
            } else if ("autoRemoveFromRecents".equals(str)) {
                if (Build.VERSION.SDK_INT < 21) {
                    return;
                }
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 8192;
                } else {
                    activityInfo.flags &= -8193;
                }
            } else if ("relinquishTaskIdentity".equals(str)) {
                if (Build.VERSION.SDK_INT < 21) {
                    return;
                }
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 4096;
                } else {
                    activityInfo.flags &= -4097;
                }
            } else if ("resumeWhilePausing".equals(str)) {
                if (Build.VERSION.SDK_INT < 21) {
                    return;
                }
                if ("true".equalsIgnoreCase(str2)) {
                    activityInfo.flags |= 16384;
                } else {
                    activityInfo.flags &= -16385;
                }
            } else if ("screenOrientation".equals(str)) {
                activityInfo.screenOrientation = b(str2);
            } else if ("label".equals(str)) {
                try {
                    i2 = context.getResources().getIdentifier(str2, EventData.b, IncrementComponentManager.g);
                } catch (Throwable unused) {
                    i2 = 0;
                }
                if (i2 != 0) {
                    activityInfo.labelRes = i2;
                } else {
                    activityInfo.nonLocalizedLabel = str2;
                }
            } else if ("icon".equals(str)) {
                try {
                    activityInfo.icon = context.getResources().getIdentifier(str2, (String) null, IncrementComponentManager.g);
                } catch (Throwable unused2) {
                }
            } else if ("banner".equals(str)) {
                if (Build.VERSION.SDK_INT >= 20) {
                    activityInfo.banner = context.getResources().getIdentifier(str2, (String) null, IncrementComponentManager.g);
                }
            } else if (CardUIInfo.KEY_LOGO.equals(str)) {
                activityInfo.logo = context.getResources().getIdentifier(str2, (String) null, IncrementComponentManager.g);
            }
        }

        private int a(String str) {
            if ("standard".equalsIgnoreCase(str)) {
                return 0;
            }
            if ("singleTop".equalsIgnoreCase(str)) {
                return 1;
            }
            if ("singleTask".equalsIgnoreCase(str)) {
                return 2;
            }
            if ("singleInstance".equalsIgnoreCase(str)) {
                return 3;
            }
            Log.w(IncrementComponentManager.f1356a, "Unknown launchMode: " + str);
            return 0;
        }

        private int b(String str) {
            if ("unspecified".equalsIgnoreCase(str)) {
                return -1;
            }
            if ("behind".equalsIgnoreCase(str)) {
                return 3;
            }
            if ("landscape".equalsIgnoreCase(str)) {
                return 0;
            }
            if ("portrait".equalsIgnoreCase(str)) {
                return 1;
            }
            if ("reverseLandscape".equalsIgnoreCase(str)) {
                return 8;
            }
            if ("reversePortrait".equalsIgnoreCase(str)) {
                return 9;
            }
            if ("sensorLandscape".equalsIgnoreCase(str)) {
                return 6;
            }
            if ("sensorPortrait".equalsIgnoreCase(str)) {
                return 7;
            }
            if ("sensor".equalsIgnoreCase(str)) {
                return 4;
            }
            if ("fullSensor".equalsIgnoreCase(str)) {
                return 10;
            }
            if ("nosensor".equalsIgnoreCase(str)) {
                return 5;
            }
            if ("user".equalsIgnoreCase(str)) {
                return 2;
            }
            if (Build.VERSION.SDK_INT >= 18 && "fullUser".equalsIgnoreCase(str)) {
                return 13;
            }
            if (Build.VERSION.SDK_INT >= 18 && "locked".equalsIgnoreCase(str)) {
                return 14;
            }
            if (Build.VERSION.SDK_INT >= 18 && "userLandscape".equalsIgnoreCase(str)) {
                return 11;
            }
            if (Build.VERSION.SDK_INT < 18 || !"userPortrait".equalsIgnoreCase(str)) {
                return -1;
            }
            return 12;
        }
    };

    private static abstract class AttrTranslator<T_RESULT> {
        /* access modifiers changed from: package-private */
        public abstract void a(Context context, int i, String str, String str2, T_RESULT t_result);

        /* access modifiers changed from: package-private */
        public void a(Context context, int i, XmlPullParser xmlPullParser) {
        }

        private AttrTranslator() {
        }

        /* access modifiers changed from: package-private */
        public final void a(Context context, int i, XmlPullParser xmlPullParser, T_RESULT t_result) {
            a(context, i, xmlPullParser);
            int attributeCount = xmlPullParser.getAttributeCount();
            for (int i2 = 0; i2 < attributeCount; i2++) {
                if ("android".equals(xmlPullParser.getAttributePrefix(i2))) {
                    a(context, i, xmlPullParser.getAttributeName(i2), xmlPullParser.getAttributeValue(i2), t_result);
                }
            }
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x0091 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:58:0x00aa */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a7 A[SYNTHETIC, Splitter:B:56:0x00a7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean a(android.content.Context r6, com.tencent.tinker.loader.shareutil.ShareSecurityCheck r7) throws java.io.IOException {
        /*
            java.lang.Class<com.tencent.tinker.loader.hotplug.IncrementComponentManager> r0 = com.tencent.tinker.loader.hotplug.IncrementComponentManager.class
            monitor-enter(r0)
            java.util.HashMap r1 = r7.a()     // Catch:{ all -> 0x00ae }
            java.lang.String r2 = "assets/inc_component_meta.txt"
            boolean r1 = r1.containsKey(r2)     // Catch:{ all -> 0x00ae }
            if (r1 != 0) goto L_0x0019
            java.lang.String r6 = "Tinker.IncrementCompMgr"
            java.lang.String r7 = "package has no incremental component meta, skip init."
            android.util.Log.i(r6, r7)     // Catch:{ all -> 0x00ae }
            r6 = 0
            monitor-exit(r0)
            return r6
        L_0x0019:
            boolean r1 = r6 instanceof android.content.ContextWrapper     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x0029
            r1 = r6
            android.content.ContextWrapper r1 = (android.content.ContextWrapper) r1     // Catch:{ all -> 0x00ae }
            android.content.Context r1 = r1.getBaseContext()     // Catch:{ all -> 0x00ae }
            if (r1 != 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r6 = r1
            goto L_0x0019
        L_0x0029:
            f = r6     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = r6.getPackageName()     // Catch:{ all -> 0x00ae }
            g = r1     // Catch:{ all -> 0x00ae }
            java.util.HashMap r7 = r7.a()     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = "assets/inc_component_meta.txt"
            java.lang.Object r7 = r7.get(r1)     // Catch:{ all -> 0x00ae }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x00ae }
            java.io.StringReader r1 = new java.io.StringReader     // Catch:{ all -> 0x00ae }
            r1.<init>(r7)     // Catch:{ all -> 0x00ae }
            r7 = 0
            org.xmlpull.v1.XmlPullParser r2 = android.util.Xml.newPullParser()     // Catch:{ XmlPullParserException -> 0x009d, all -> 0x009a }
            r2.setInput(r1)     // Catch:{ XmlPullParserException -> 0x0098 }
            int r3 = r2.getEventType()     // Catch:{ XmlPullParserException -> 0x0098 }
        L_0x004e:
            r4 = 1
            if (r3 == r4) goto L_0x008a
            r4 = 2
            if (r3 == r4) goto L_0x0055
            goto L_0x0085
        L_0x0055:
            java.lang.String r3 = r2.getName()     // Catch:{ XmlPullParserException -> 0x0098 }
            java.lang.String r4 = "activity"
            boolean r4 = r4.equalsIgnoreCase(r3)     // Catch:{ XmlPullParserException -> 0x0098 }
            if (r4 == 0) goto L_0x006d
            android.content.pm.ActivityInfo r3 = a((android.content.Context) r6, (org.xmlpull.v1.XmlPullParser) r2)     // Catch:{ XmlPullParserException -> 0x0098 }
            java.util.Map<java.lang.String, android.content.pm.ActivityInfo> r4 = i     // Catch:{ XmlPullParserException -> 0x0098 }
            java.lang.String r5 = r3.name     // Catch:{ XmlPullParserException -> 0x0098 }
            r4.put(r5, r3)     // Catch:{ XmlPullParserException -> 0x0098 }
            goto L_0x0085
        L_0x006d:
            java.lang.String r4 = "service"
            boolean r4 = r4.equalsIgnoreCase(r3)     // Catch:{ XmlPullParserException -> 0x0098 }
            if (r4 == 0) goto L_0x0076
            goto L_0x0085
        L_0x0076:
            java.lang.String r4 = "receiver"
            boolean r4 = r4.equalsIgnoreCase(r3)     // Catch:{ XmlPullParserException -> 0x0098 }
            if (r4 == 0) goto L_0x007f
            goto L_0x0085
        L_0x007f:
            java.lang.String r4 = "provider"
            boolean r3 = r4.equalsIgnoreCase(r3)     // Catch:{ XmlPullParserException -> 0x0098 }
        L_0x0085:
            int r3 = r2.next()     // Catch:{ XmlPullParserException -> 0x0098 }
            goto L_0x004e
        L_0x008a:
            h = r4     // Catch:{ XmlPullParserException -> 0x0098 }
            if (r2 == 0) goto L_0x0091
            r2.setInput(r7)     // Catch:{ Throwable -> 0x0091 }
        L_0x0091:
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r1)     // Catch:{ all -> 0x00ae }
            monitor-exit(r0)
            return r4
        L_0x0096:
            r6 = move-exception
            goto L_0x00a5
        L_0x0098:
            r6 = move-exception
            goto L_0x009f
        L_0x009a:
            r6 = move-exception
            r2 = r7
            goto L_0x00a5
        L_0x009d:
            r6 = move-exception
            r2 = r7
        L_0x009f:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x0096 }
            r3.<init>(r6)     // Catch:{ all -> 0x0096 }
            throw r3     // Catch:{ all -> 0x0096 }
        L_0x00a5:
            if (r2 == 0) goto L_0x00aa
            r2.setInput(r7)     // Catch:{ Throwable -> 0x00aa }
        L_0x00aa:
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r1)     // Catch:{ all -> 0x00ae }
            throw r6     // Catch:{ all -> 0x00ae }
        L_0x00ae:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.hotplug.IncrementComponentManager.a(android.content.Context, com.tencent.tinker.loader.shareutil.ShareSecurityCheck):boolean");
    }

    private static synchronized ActivityInfo a(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ActivityInfo activityInfo;
        synchronized (IncrementComponentManager.class) {
            activityInfo = new ActivityInfo();
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            activityInfo.applicationInfo = applicationInfo;
            activityInfo.packageName = g;
            activityInfo.processName = applicationInfo.processName;
            activityInfo.launchMode = 0;
            activityInfo.permission = applicationInfo.permission;
            activityInfo.screenOrientation = -1;
            activityInfo.taskAffinity = applicationInfo.taskAffinity;
            if (Build.VERSION.SDK_INT >= 11 && (applicationInfo.flags & 536870912) != 0) {
                activityInfo.flags |= 512;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                activityInfo.documentLaunchMode = 0;
            }
            if (Build.VERSION.SDK_INT >= 14) {
                activityInfo.uiOptions = applicationInfo.uiOptions;
            }
            k.a(context, 0, xmlPullParser, activityInfo);
            int depth = xmlPullParser.getDepth();
            while (true) {
                int next = xmlPullParser.next();
                if (next != 1) {
                    if (next == 3 && xmlPullParser.getDepth() <= depth) {
                        break;
                    } else if (next != 3) {
                        if (next != 4) {
                            String name = xmlPullParser.getName();
                            if ("intent-filter".equalsIgnoreCase(name)) {
                                a(context, activityInfo.name, xmlPullParser);
                            } else if ("meta-data".equalsIgnoreCase(name)) {
                                a(context, activityInfo, xmlPullParser);
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return activityInfo;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:2|3|(1:5)|6|7|(3:9|10|11)|12|13|(2:14|(1:79)(2:16|(2:(1:81)(2:22|(2:24|82)(4:25|(2:27|(1:29))(2:30|(2:32|(1:34))(2:35|(14:37|(2:39|40)|45|(1:47)|48|(6:50|(1:52)|53|(1:55)|56|(1:58))|59|(1:61)|62|(1:64)|65|(1:67)|68|(1:70))))|71|83))|78)(2:80|20)))|72|73) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x004e */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058 A[Catch:{ MalformedMimeTypeException -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0121 A[EDGE_INSN: B:79:0x0121->B:72:0x0121 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void a(android.content.Context r9, java.lang.String r10, org.xmlpull.v1.XmlPullParser r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            java.lang.Class<com.tencent.tinker.loader.hotplug.IncrementComponentManager> r9 = com.tencent.tinker.loader.hotplug.IncrementComponentManager.class
            monitor-enter(r9)
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch:{ all -> 0x0128 }
            r0.<init>()     // Catch:{ all -> 0x0128 }
            java.lang.String r1 = "priority"
            r2 = 0
            java.lang.String r1 = r11.getAttributeValue(r2, r1)     // Catch:{ all -> 0x0128 }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0128 }
            if (r3 != 0) goto L_0x0020
            java.lang.Integer r1 = java.lang.Integer.decode(r1)     // Catch:{ all -> 0x0128 }
            int r1 = r1.intValue()     // Catch:{ all -> 0x0128 }
            r0.setPriority(r1)     // Catch:{ all -> 0x0128 }
        L_0x0020:
            java.lang.String r1 = "autoVerify"
            java.lang.String r1 = r11.getAttributeValue(r2, r1)     // Catch:{ all -> 0x0128 }
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0128 }
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L_0x004e
            java.lang.Class<android.content.IntentFilter> r3 = android.content.IntentFilter.class
            java.lang.String r6 = "setAutoVerify"
            java.lang.Class[] r7 = new java.lang.Class[r5]     // Catch:{ Throwable -> 0x004e }
            java.lang.Class r8 = java.lang.Boolean.TYPE     // Catch:{ Throwable -> 0x004e }
            r7[r4] = r8     // Catch:{ Throwable -> 0x004e }
            java.lang.reflect.Method r3 = com.tencent.tinker.loader.shareutil.ShareReflectUtil.a((java.lang.Class<?>) r3, (java.lang.String) r6, (java.lang.Class<?>[]) r7)     // Catch:{ Throwable -> 0x004e }
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x004e }
            java.lang.String r7 = "true"
            boolean r1 = r7.equalsIgnoreCase(r1)     // Catch:{ Throwable -> 0x004e }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x004e }
            r6[r4] = r1     // Catch:{ Throwable -> 0x004e }
            r3.invoke(r0, r6)     // Catch:{ Throwable -> 0x004e }
        L_0x004e:
            int r1 = r11.getDepth()     // Catch:{ all -> 0x0128 }
        L_0x0052:
            int r3 = r11.next()     // Catch:{ all -> 0x0128 }
            if (r3 == r5) goto L_0x0121
            r6 = 3
            if (r3 != r6) goto L_0x0063
            int r7 = r11.getDepth()     // Catch:{ all -> 0x0128 }
            if (r7 > r1) goto L_0x0063
            goto L_0x0121
        L_0x0063:
            if (r3 == r6) goto L_0x0052
            r6 = 4
            if (r3 != r6) goto L_0x0069
            goto L_0x0052
        L_0x0069:
            java.lang.String r3 = r11.getName()     // Catch:{ all -> 0x0128 }
            java.lang.String r6 = "action"
            boolean r6 = r6.equals(r3)     // Catch:{ all -> 0x0128 }
            if (r6 == 0) goto L_0x0082
            java.lang.String r3 = "name"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x011c
            r0.addAction(r3)     // Catch:{ all -> 0x0128 }
            goto L_0x011c
        L_0x0082:
            java.lang.String r6 = "category"
            boolean r6 = r6.equals(r3)     // Catch:{ all -> 0x0128 }
            if (r6 == 0) goto L_0x0097
            java.lang.String r3 = "name"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x011c
            r0.addCategory(r3)     // Catch:{ all -> 0x0128 }
            goto L_0x011c
        L_0x0097:
            java.lang.String r6 = "data"
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x011c
            java.lang.String r3 = "mimeType"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x00b4
            r0.addDataType(r3)     // Catch:{ MalformedMimeTypeException -> 0x00ab }
            goto L_0x00b4
        L_0x00ab:
            r10 = move-exception
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ all -> 0x0128 }
            java.lang.String r1 = "bad mimeType"
            r0.<init>(r1, r11, r10)     // Catch:{ all -> 0x0128 }
            throw r0     // Catch:{ all -> 0x0128 }
        L_0x00b4:
            java.lang.String r3 = "scheme"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x00bf
            r0.addDataScheme(r3)     // Catch:{ all -> 0x0128 }
        L_0x00bf:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0128 }
            r6 = 19
            r7 = 2
            if (r3 < r6) goto L_0x00ea
            java.lang.String r3 = "ssp"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x00d2
            r0.addDataSchemeSpecificPart(r3, r4)     // Catch:{ all -> 0x0128 }
        L_0x00d2:
            java.lang.String r3 = "sspPrefix"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x00de
            r0.addDataSchemeSpecificPart(r3, r5)     // Catch:{ all -> 0x0128 }
        L_0x00de:
            java.lang.String r3 = "sspPattern"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x00ea
            r0.addDataSchemeSpecificPart(r3, r7)     // Catch:{ all -> 0x0128 }
        L_0x00ea:
            java.lang.String r3 = "host"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            java.lang.String r6 = "port"
            java.lang.String r6 = r11.getAttributeValue(r2, r6)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x00fb
            r0.addDataAuthority(r3, r6)     // Catch:{ all -> 0x0128 }
        L_0x00fb:
            java.lang.String r3 = "path"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x0106
            r0.addDataPath(r3, r4)     // Catch:{ all -> 0x0128 }
        L_0x0106:
            java.lang.String r3 = "pathPrefix"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x0111
            r0.addDataPath(r3, r5)     // Catch:{ all -> 0x0128 }
        L_0x0111:
            java.lang.String r3 = "pathPattern"
            java.lang.String r3 = r11.getAttributeValue(r2, r3)     // Catch:{ all -> 0x0128 }
            if (r3 == 0) goto L_0x011c
            r0.addDataPath(r3, r7)     // Catch:{ all -> 0x0128 }
        L_0x011c:
            a((org.xmlpull.v1.XmlPullParser) r11)     // Catch:{ all -> 0x0128 }
            goto L_0x0052
        L_0x0121:
            java.util.Map<java.lang.String, android.content.IntentFilter> r11 = j     // Catch:{ all -> 0x0128 }
            r11.put(r10, r0)     // Catch:{ all -> 0x0128 }
            monitor-exit(r9)
            return
        L_0x0128:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.hotplug.IncrementComponentManager.a(android.content.Context, java.lang.String, org.xmlpull.v1.XmlPullParser):void");
    }

    private static synchronized void a(Context context, ActivityInfo activityInfo, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        synchronized (IncrementComponentManager.class) {
            ClassLoader classLoader = IncrementComponentManager.class.getClassLoader();
            String attributeValue = xmlPullParser.getAttributeValue((String) null, "name");
            String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "value");
            if (!TextUtils.isEmpty(attributeValue)) {
                if (activityInfo.metaData == null) {
                    activityInfo.metaData = new Bundle(classLoader);
                }
                activityInfo.metaData.putString(attributeValue, attributeValue2);
            }
        }
    }

    private static void a(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }

    private static synchronized void b() {
        synchronized (IncrementComponentManager.class) {
            if (!h) {
                throw new IllegalStateException("Not initialized!!");
            }
        }
    }

    public static boolean a(String str) {
        b();
        return str != null && i.containsKey(str);
    }

    public static ActivityInfo b(String str) {
        b();
        if (str != null) {
            return i.get(str);
        }
        return null;
    }

    public static ResolveInfo a(Intent intent) {
        IntentFilter intentFilter;
        String str;
        b();
        ComponentName component = intent.getComponent();
        int i2 = -1;
        int i3 = 0;
        if (component != null) {
            str = component.getClassName();
            if (i.containsKey(str)) {
                i2 = 0;
            } else {
                str = null;
            }
            intentFilter = null;
        } else {
            String str2 = null;
            intentFilter = null;
            int i4 = 0;
            int i5 = -1;
            for (Map.Entry next : j.entrySet()) {
                String str3 = (String) next.getKey();
                IntentFilter intentFilter2 = (IntentFilter) next.getValue();
                int match = intentFilter2.match(intent.getAction(), intent.getType(), intent.getScheme(), intent.getData(), intent.getCategories(), f1356a);
                boolean z = (match == -3 || match == -4 || match == -2 || match == -1) ? false : true;
                int priority = intentFilter2.getPriority();
                if (z && priority > i5) {
                    intentFilter = intentFilter2;
                    str2 = str3;
                    i4 = match;
                    i5 = priority;
                }
            }
            str = str2;
            i3 = i4;
            i2 = i5;
        }
        if (str == null) {
            return null;
        }
        ResolveInfo resolveInfo = new ResolveInfo();
        resolveInfo.activityInfo = i.get(str);
        resolveInfo.filter = intentFilter;
        resolveInfo.match = i3;
        resolveInfo.priority = i2;
        resolveInfo.resolvePackageName = g;
        resolveInfo.icon = resolveInfo.activityInfo.icon;
        resolveInfo.labelRes = resolveInfo.activityInfo.labelRes;
        return resolveInfo;
    }

    private IncrementComponentManager() {
        throw new UnsupportedOperationException();
    }
}
