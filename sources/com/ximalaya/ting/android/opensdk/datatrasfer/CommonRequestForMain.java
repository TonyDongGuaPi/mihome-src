package com.ximalaya.ting.android.opensdk.datatrasfer;

import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.MethodUtil;
import com.ximalaya.ting.android.xmpayordersdk.IXmPayOrderListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommonRequestForMain {
    public static void a() {
        Logger.a((Object) "encryptStr 1");
        if (BaseUtil.d()) {
            try {
                MethodUtil.a(BaseCall.e(), "getLocalUserInfo", (Object[]) null, (Class<?>[]) null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static void a(Map<String, String> map, IDataCallBack<String> iDataCallBack, Track track) {
        Logger.a((Object) "encryptStr 0");
        if (!a(map, iDataCallBack, track, "updateTrackForPlay")) {
            iDataCallBack.a(IXmPayOrderListener.l, "call " + "updateTrackForPlay" + " is not in mainapp or class.forName CommonRequestM error");
        }
    }

    public static <T> boolean a(Map<String, String> map, IDataCallBack<T> iDataCallBack, String str) {
        Class e = BaseCall.e();
        if (e == null) {
            return false;
        }
        try {
            MethodUtil.a(e, str, new Object[]{map, iDataCallBack}, (Class<?>[]) new Class[]{Map.class, IDataCallBack.class});
            Logger.a((Object) "encryptStr 1");
        } catch (Exception e2) {
            iDataCallBack.a(IXmPayOrderListener.l, "func " + str + " invoke fail");
            StringBuilder sb = new StringBuilder();
            sb.append("encryptStr 2");
            sb.append(e2);
            Logger.a((Object) sb.toString());
        }
        return true;
    }

    public static <T> boolean a(Map<String, String> map, IDataCallBack<T> iDataCallBack, Track track, String str) {
        Class e = BaseCall.e();
        if (e == null) {
            return false;
        }
        try {
            MethodUtil.a(e, str, new Object[]{map, iDataCallBack, track}, (Class<?>[]) new Class[]{Map.class, IDataCallBack.class, Track.class});
            Logger.a((Object) "encryptStr 1");
        } catch (Exception e2) {
            iDataCallBack.a(IXmPayOrderListener.l, "func " + str + " invoke fail");
            StringBuilder sb = new StringBuilder();
            sb.append("encryptStr 2");
            sb.append(e2);
            Logger.a((Object) sb.toString());
        }
        return true;
    }

    public static String a(Track track) {
        Object obj;
        Class e = BaseCall.e();
        if (e == null) {
            return null;
        }
        try {
            obj = MethodUtil.a(e, "getChargeDownloadUrl", new Object[]{new HashMap(), track}, (Class<?>[]) new Class[]{Map.class, Track.class});
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            obj = null;
            return (String) obj;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            obj = null;
            return (String) obj;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            obj = null;
            return (String) obj;
        }
        return (String) obj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b() {
        /*
            java.lang.Class r0 = com.ximalaya.ting.android.opensdk.httputil.BaseCall.e()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r2 = "getInstanse"
            java.lang.Object r0 = com.ximalaya.ting.android.opensdk.util.MethodUtil.a((java.lang.Class) r0, (java.lang.String) r2, (java.lang.Object[]) r1, (java.lang.Class<?>[]) r1)     // Catch:{ NoSuchMethodException -> 0x0019, IllegalAccessException -> 0x0014, InvocationTargetException -> 0x000f }
            goto L_0x001e
        L_0x000f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001d
        L_0x0014:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001d
        L_0x0019:
            r0 = move-exception
            r0.printStackTrace()
        L_0x001d:
            r0 = r1
        L_0x001e:
            if (r0 != 0) goto L_0x0021
            return r1
        L_0x0021:
            java.lang.String r2 = "getUserAgent"
            java.lang.Object r0 = com.ximalaya.ting.android.opensdk.util.MethodUtil.a((java.lang.Object) r0, (java.lang.String) r2, (java.lang.Object[]) r1, (java.lang.Class<?>[]) r1)     // Catch:{ NoSuchMethodException -> 0x0032, IllegalAccessException -> 0x002d, InvocationTargetException -> 0x0028 }
            goto L_0x0037
        L_0x0028:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        L_0x0032:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0036:
            r0 = r1
        L_0x0037:
            java.lang.String r0 = (java.lang.String) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequestForMain.b():java.lang.String");
    }

    public static void a(String str, String str2, String str3) {
        try {
            Class.forName("com.sina.util.dnscache.DNSCache").getDeclaredMethod("setBadIp", new Class[]{String.class, String.class, String.class}).invoke((Object) null, new Object[]{str, str2, str3});
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[][] a(java.lang.String r8) {
        /*
            r0 = 0
            java.lang.String r1 = "com.sina.util.dnscache.DNSCache"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x0085 }
            r2 = 0
            r3 = 1
            java.lang.String r4 = "getStaticDomainServerIp"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ NoSuchMethodException -> 0x0024, IllegalAccessException -> 0x001f, InvocationTargetException -> 0x001a }
            r5[r2] = r8     // Catch:{ NoSuchMethodException -> 0x0024, IllegalAccessException -> 0x001f, InvocationTargetException -> 0x001a }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ NoSuchMethodException -> 0x0024, IllegalAccessException -> 0x001f, InvocationTargetException -> 0x001a }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r2] = r7     // Catch:{ NoSuchMethodException -> 0x0024, IllegalAccessException -> 0x001f, InvocationTargetException -> 0x001a }
            java.lang.Object r1 = com.ximalaya.ting.android.opensdk.util.MethodUtil.a((java.lang.Class) r1, (java.lang.String) r4, (java.lang.Object[]) r5, (java.lang.Class<?>[]) r6)     // Catch:{ NoSuchMethodException -> 0x0024, IllegalAccessException -> 0x001f, InvocationTargetException -> 0x001a }
            goto L_0x0029
        L_0x001a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0028
        L_0x001f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0028
        L_0x0024:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0028:
            r1 = r0
        L_0x0029:
            if (r1 == 0) goto L_0x0082
            java.lang.String r1 = (java.lang.String) r1
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 == 0) goto L_0x0036
            java.lang.String[][] r0 = (java.lang.String[][]) r0
            return r0
        L_0x0036:
            java.lang.String r0 = "__&__"
            java.lang.String[] r0 = r1.split(r0)
            int r1 = r0.length
            int r1 = r1 + r3
            r3 = 2
            int[] r1 = new int[]{r1, r3}
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r3, r1)
            java.lang.String[][] r1 = (java.lang.String[][]) r1
            r3 = 0
        L_0x004c:
            int r4 = r0.length
            if (r3 >= r4) goto L_0x007c
            r4 = r0[r3]
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0079
            r4 = r0[r3]
            java.lang.String r5 = "__#__"
            java.lang.String[] r4 = r4.split(r5)
            r1[r3] = r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "getStaticDomainServerIp ipsAndHostStrArr:"
            r5.append(r6)
            java.lang.String r4 = java.util.Arrays.toString(r4)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.Object) r4)
        L_0x0079:
            int r3 = r3 + 1
            goto L_0x004c
        L_0x007c:
            int r0 = r0.length
            r0 = r1[r0]
            r0[r2] = r8
            return r1
        L_0x0082:
            java.lang.String[][] r0 = (java.lang.String[][]) r0
            return r0
        L_0x0085:
            r8 = move-exception
            r8.printStackTrace()
            java.lang.String[][] r0 = (java.lang.String[][]) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequestForMain.a(java.lang.String):java.lang.String[][]");
    }

    public static Track a(final long j) {
        Object obj;
        Class e = BaseCall.e();
        if (e == null) {
            return null;
        }
        try {
            obj = MethodUtil.a(e, "getTrackInfoDetailSync", new Object[]{new HashMap<String, String>() {
                {
                    put("device", "android");
                    put("trackId", j + "");
                }
            }}, (Class<?>[]) new Class[]{Map.class});
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            obj = null;
            return (Track) obj;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            obj = null;
            return (Track) obj;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            obj = null;
            return (Track) obj;
        }
        return (Track) obj;
    }
}
