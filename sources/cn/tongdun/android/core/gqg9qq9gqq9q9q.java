package cn.tongdun.android.core;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.text.TextUtils;
import android.util.Base64;
import cn.tongdun.android.core.qgg9qgg9999g9g.g9g9g99;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqq9qggqgg9g99;
import cn.tongdun.android.core.qgg9qgg9999g9g.q9qgqg9;
import cn.tongdun.android.core.qqq9gg9gqq9qgg99q.qgg9qgg9999g9g;
import cn.tongdun.android.shell.FMAgent;
import cn.tongdun.android.shell.common.CollectorError;
import cn.tongdun.android.shell.common.HelperJNI;
import cn.tongdun.android.shell.utils.BoxUtil;
import cn.tongdun.android.shell.utils.LogUtil;
import org.json.JSONObject;

public class gqg9qq9gqq9q9q {
    public static long gqg9qq9gqq9q9q = 0;
    private static Context qgg99qqg9gq = null;
    public static String qgg9qgg9999g9g = "";
    private String g999gqq9ggqgqq = "";
    private gqq9qggqgg9g99 g9q9q9g9;
    private String gqgqgqq9gq9q9q9;
    private long q9gqqq99999qq = 0;
    private boolean q9q99gq99gggqg9qqqgg = false;
    private long q9qq99qg9qqgqg9gqgg9 = 0;
    private boolean qqq9gg9gqq9qgg99q = false;

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02e2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x022c, code lost:
        r5 = r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:66:0x01eb, B:74:0x0225] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:129:0x0319 */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02e2 A[ExcHandler: Throwable (th java.lang.Throwable), PHI: r12 
      PHI: (r12v16 cn.tongdun.android.core.gqg9qq9gqq9q9q) = (r12v18 cn.tongdun.android.core.gqg9qq9gqq9q9q), (r12v18 cn.tongdun.android.core.gqg9qq9gqq9q9q), (r12v18 cn.tongdun.android.core.gqg9qq9gqq9q9q), (r12v18 cn.tongdun.android.core.gqg9qq9gqq9q9q), (r12v18 cn.tongdun.android.core.gqg9qq9gqq9q9q), (r12v18 cn.tongdun.android.core.gqg9qq9gqq9q9q), (r12v25 cn.tongdun.android.core.gqg9qq9gqq9q9q) binds: [B:78:0x022f, B:88:0x024b, B:86:0x0244, B:87:?, B:74:0x0225, B:75:?, B:66:0x01eb] A[DONT_GENERATE, DONT_INLINE], Splitter:B:66:0x01eb] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0351  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0356  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x035d  */
    /* JADX WARNING: Removed duplicated region for block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:118:0x02f1=Splitter:B:118:0x02f1, B:129:0x0319=Splitter:B:129:0x0319} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void gqg9qq9gqq9q9q(android.content.Context r16, java.lang.String r17, java.lang.String r18, boolean r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, boolean r23, boolean r24, java.util.concurrent.CountDownLatch r25, java.lang.String r26, int r27, java.lang.String r28, java.lang.String r29, boolean r30) {
        /*
            r15 = this;
            r1 = r15
            r2 = r16
            long r3 = java.lang.System.currentTimeMillis()
            r0 = 0
            r1.qqq9gg9gqq9qgg99q = r0
            cn.tongdun.android.core.qgg9qgg9999g9g.gqq9qggqgg9g99 r5 = cn.tongdun.android.core.qgg9qgg9999g9g.gqq9qggqgg9g99.gqg9qq9gqq9q9q((android.content.Context) r16)
            r1.g9q9q9g9 = r5
            qgg99qqg9gq = r2
            java.lang.String r5 = cn.tongdun.android.shell.FMAgent.STATUS_COLLECTING
            cn.tongdun.android.shell.FMAgent.mStatus = r5
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg9qgg9999g9g = r17
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9q99gq99gggqg9qqqgg = r19
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.g9q9q9g9 = r20
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9qq99qg9qqgqg9gqgg9 = r21
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9gqqq99999qq = r22
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qqq9gg9gqq9qgg99q = r18
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.g999gqq9ggqgqq = r23
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.gqgqgqq9gq9q9q9 = r24
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg99qqg9gq = r27
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qq9q9ggg = r26
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgggqg999gg9qqggq = r29
            cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qq9gq9g9g99 = r30
            android.content.Context r5 = qgg99qqg9gq
            if (r5 == 0) goto L_0x0048
            android.content.Context r5 = qgg99qqg9gq     // Catch:{ Throwable -> 0x0038 }
            cn.tongdun.android.shell.common.HelperJNI.setContext(r5)     // Catch:{ Throwable -> 0x0038 }
            goto L_0x0048
        L_0x0038:
            java.lang.String r5 = "7b6c677a7c7b75786463"
            r6 = 8
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (int) r6)
            java.lang.System.loadLibrary(r5)
            android.content.Context r5 = qgg99qqg9gq
            cn.tongdun.android.shell.common.HelperJNI.setContext(r5)
        L_0x0048:
            r5 = 4
            r6 = 3
            cn.tongdun.android.shell.common.HelperJNI.exprot(r6, r5)
            cn.tongdun.android.shell.common.HelperJNI.exprot(r5, r0)
            java.lang.String r5 = ""
            cn.tongdun.android.core.qgg9qgg9999g9g.q9qgqg9.qqq9gg9gqq9qgg99q()
            java.lang.String r7 = "sensor"
            java.lang.Object r7 = r2.getSystemService(r7)
            android.hardware.SensorManager r7 = (android.hardware.SensorManager) r7
            cn.tongdun.android.core.qgg9qgg9999g9g.q9qgqg9 r8 = new cn.tongdun.android.core.qgg9qgg9999g9g.q9qgqg9
            r8.<init>()
            r15.gqg9qq9gqq9q9q((android.hardware.SensorManager) r7, (cn.tongdun.android.core.qgg9qgg9999g9g.q9qgqg9) r8)
            cn.tongdun.android.shell.common.CollectorError$TYPE r9 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_SHORT_INTERVAL
            cn.tongdun.android.shell.common.CollectorError.remove(r9)
            java.lang.String r9 = "4375557c566d4d231e34117f5d7c5379172a113c113a073a"
            r10 = 47
            java.lang.String r9 = gqg9qq9gqq9q9q((java.lang.String) r9, (int) r10)
            cn.tongdun.android.shell.utils.LogUtil.info((java.lang.String) r9)
            r9 = 1
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            gqg9qq9gqq9q9q = r10     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            cn.tongdun.android.core.qgg9qgg9999g9g.gqq9qggqgg9g99 r10 = r1.g9q9q9g9     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            int r10 = r10.gqg9qq9gqq9q9q()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r11 = 233(0xe9, float:3.27E-43)
            if (r10 == r11) goto L_0x00a7
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r1.q9qq99qg9qqgqg9gqgg9
            long r3 = r3 - r5
            r1.q9gqqq99999qq = r3
            if (r25 == 0) goto L_0x0094
            r25.countDown()
        L_0x0094:
            if (r7 == 0) goto L_0x0099
            r7.unregisterListener(r8)
        L_0x0099:
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            if (r0 == 0) goto L_0x00a6
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            java.lang.String r2 = r15.gqg9qq9gqq9q9q(r16)
            r0.onEvent(r2)
        L_0x00a6:
            return
        L_0x00a7:
            boolean r10 = android.text.TextUtils.isEmpty(r28)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r10 != 0) goto L_0x00b4
            cn.tongdun.android.core.qgg9qgg9999g9g.gqq9qggqgg9g99 r10 = r1.g9q9q9g9     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r11 = r28
            r10.gqg9qq9gqq9q9q((java.lang.String) r11)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
        L_0x00b4:
            cn.tongdun.android.core.qgg9qgg9999g9g.gqq9qggqgg9g99 r10 = r1.g9q9q9g9     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            byte[] r10 = r10.qgg9qgg9999g9g()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r10 != 0) goto L_0x00dd
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r1.q9qq99qg9qqgqg9gqgg9
            long r3 = r3 - r5
            r1.q9gqqq99999qq = r3
            if (r25 == 0) goto L_0x00ca
            r25.countDown()
        L_0x00ca:
            if (r7 == 0) goto L_0x00cf
            r7.unregisterListener(r8)
        L_0x00cf:
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            if (r0 == 0) goto L_0x00dc
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            java.lang.String r2 = r15.gqg9qq9gqq9q9q(r16)
            r0.onEvent(r2)
        L_0x00dc:
            return
        L_0x00dd:
            java.lang.String r11 = cn.tongdun.android.shell.FMAgent.STATUS_PROFILING     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            cn.tongdun.android.shell.FMAgent.mStatus = r11     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            cn.tongdun.android.shell.common.CollectorError$TYPE r11 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_PROFILE_DELAY     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r12 = "47404d49424c4b090f0806051e"
            java.lang.String r6 = gqg9qq9gqq9q9q((java.lang.String) r12, (int) r6)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            cn.tongdun.android.shell.common.CollectorError.addError((cn.tongdun.android.shell.common.CollectorError.TYPE) r11, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r6.<init>()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r11 = "677d637b79706e"
            r12 = 13
            java.lang.String r11 = gqg9qq9gqq9q9q((java.lang.String) r11, (int) r12)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r12 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qqq9gg9gqq9qgg99q     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r6.put(r11, r12)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r11 = "614161407b467a"
            r12 = 51
            java.lang.String r11 = gqg9qq9gqq9q9q((java.lang.String) r11, (int) r12)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.qgg9qgg9999g9g.gqg9qq9gqq9q9q     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r6.put(r11, r13)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            long r13 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r1.q9qq99qg9qqgqg9gqgg9 = r13     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            boolean r11 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.gqgqgqq9gq9q9q9     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r11 == 0) goto L_0x0125
            cn.tongdun.android.shell.common.CollectorError$TYPE r0 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_ALWAYS_DEMOTION     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r3 = "56155a034209114d1045125e0f580e"
            r4 = 89
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r4)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            cn.tongdun.android.shell.common.CollectorError.addError((cn.tongdun.android.shell.common.CollectorError.TYPE) r0, (java.lang.String) r3)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r12 = r1
            goto L_0x02ca
        L_0x0125:
            java.lang.String r11 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.qgg9qgg9999g9g.q9gqqq99999qq     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qq9q9ggg     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            boolean r13 = android.text.TextUtils.isEmpty(r13)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r13 == 0) goto L_0x0151
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg9qgg9999g9g     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r13 == 0) goto L_0x0140
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg9qgg9999g9g     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r14 = cn.tongdun.android.shell.FMAgent.ENV_SANDBOX     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            boolean r13 = r13.equals(r14)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r13 == 0) goto L_0x0140
            java.lang.String r11 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.qgg9qgg9999g9g.qqq9gg9gqq9qgg99q     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            goto L_0x0164
        L_0x0140:
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg9qgg9999g9g     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r13 == 0) goto L_0x0164
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg9qgg9999g9g     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r14 = cn.tongdun.android.shell.FMAgent.ENV_PRODUCTION     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            boolean r13 = r13.equals(r14)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            if (r13 == 0) goto L_0x0164
            java.lang.String r11 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.qgg9qgg9999g9g.q9gqqq99999qq     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            goto L_0x0164
        L_0x0151:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r11.<init>()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qq9q9ggg     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r11.append(r13)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r13 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.qgg9qgg9999g9g.g9q9q9g9     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r11.append(r13)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
        L_0x0164:
            java.util.Random r13 = new java.util.Random     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r13.<init>()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r14 = 999999(0xf423f, float:1.401297E-39)
            int r13 = r13.nextInt(r14)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r14 = 100000(0x186a0, float:1.4013E-40)
            if (r13 >= r14) goto L_0x0176
            int r13 = r13 + r14
        L_0x0176:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            r14.<init>()     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            java.lang.String r12 = ""
            r14.append(r12)     // Catch:{ JSONException -> 0x0318, Throwable -> 0x02ef, all -> 0x02ec }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r14.append(r0)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r14.append(r13)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r0 = r14.toString()     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            qgg9qgg9999g9g = r0     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.g9q9q9g9     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r1 = 2
            if (r0 == 0) goto L_0x0218
            boolean r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qq9gq9g9g99     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            if (r0 == 0) goto L_0x01a1
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.g9q9q9g9     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r0 = cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r0, (java.util.Map) r6, (byte[]) r10, (int) r9)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
        L_0x019f:
            r5 = r0
            goto L_0x01a9
        L_0x01a1:
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.g9q9q9g9     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r11 = 0
            java.lang.String r0 = cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r0, (java.util.Map) r6, (byte[]) r10, (int) r11)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            goto L_0x019f
        L_0x01a9:
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            android.content.Context r0 = qgg99qqg9gq     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            boolean r0 = cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.q9gqqq99999qq.gqg9qq9gqq9q9q((android.content.Context) r0)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            if (r0 == 0) goto L_0x0216
            java.lang.String r0 = "4373"
            java.lang.String r0 = gqg9qq9gqq9q9q((java.lang.String) r0, (int) r1)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r1.<init>()     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r12 = "4368590d2c0a327b"
            r13 = 21
            java.lang.String r12 = gqg9qq9gqq9q9q((java.lang.String) r12, (int) r13)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r1.append(r12)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r12 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.g9q9q9g9     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r6 = cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r12, (java.util.Map) r6)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r1.append(r6)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r6 = "379073a0df"
            r12 = 62
            java.lang.String r6 = gqg9qq9gqq9q9q((java.lang.String) r6, (int) r12)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r1.append(r6)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            java.lang.String r6 = "5f64486038133f062c00587445704d08"
            r12 = 25
            java.lang.String r6 = gqg9qq9gqq9q9q((java.lang.String) r6, (int) r12)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r1.append(r6)     // Catch:{ JSONException -> 0x02ea, Throwable -> 0x02e7, all -> 0x02e4 }
            r12 = r15
            long r13 = r12.q9qq99qg9qqgqg9gqgg9     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r1.append(r13)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r6 = "3b283028345851535b372f2a2b2253"
            r13 = 45
            java.lang.String r6 = gqg9qq9gqq9q9q((java.lang.String) r6, (int) r13)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r1.append(r6)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r1.append(r10)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r6 = "3b7230723402570e4b093f7d22792a01"
            r13 = 119(0x77, float:1.67E-43)
            java.lang.String r6 = gqg9qq9gqq9q9q((java.lang.String) r6, (int) r13)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r1.append(r6)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r6 = 0
            long r10 = r10 - r3
            r1.append(r10)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            android.util.Log.i(r0, r1)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            goto L_0x0250
        L_0x0216:
            r12 = r15
            goto L_0x0250
        L_0x0218:
            r12 = r15
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9gqqq99999qq     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            if (r0 == 0) goto L_0x022f
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9qq99qg9qqgqg9gqgg9     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            if (r0 == 0) goto L_0x022f
            java.lang.String r0 = cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r11, (java.util.Map) r6, (byte[]) r10, (int) r1)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r1 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9qq99qg9qqgqg9gqgg9     // Catch:{ JSONException -> 0x022c, Throwable -> 0x02e2 }
            r3 = 0
            cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r1, (java.util.Map) r6, (byte[]) r10, (int) r3)     // Catch:{ JSONException -> 0x022c, Throwable -> 0x02e2 }
            goto L_0x023a
        L_0x022c:
            r5 = r0
            goto L_0x0319
        L_0x022f:
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9gqqq99999qq     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            if (r0 == 0) goto L_0x023c
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9gqqq99999qq     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r1 = 0
            java.lang.String r0 = cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r0, (java.util.Map) r6, (byte[]) r10, (int) r1)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
        L_0x023a:
            r5 = r0
            goto L_0x0250
        L_0x023c:
            java.lang.String r0 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9qq99qg9qqgqg9gqgg9     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            if (r0 == 0) goto L_0x024b
            java.lang.String r0 = cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r11, (java.util.Map) r6, (byte[]) r10, (int) r1)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r1 = cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.q9qq99qg9qqgqg9gqgg9     // Catch:{ JSONException -> 0x022c, Throwable -> 0x02e2 }
            r3 = 0
            cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r1, (java.util.Map) r6, (byte[]) r10, (int) r3)     // Catch:{ JSONException -> 0x022c, Throwable -> 0x02e2 }
            goto L_0x023a
        L_0x024b:
            java.lang.String r0 = cn.tongdun.android.core.g9q9q9g9.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q((java.lang.String) r11, (java.util.Map) r6, (byte[]) r10, (int) r1)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            goto L_0x023a
        L_0x0250:
            if (r5 == 0) goto L_0x0257
            java.lang.String r0 = ""
            r0.equals(r5)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
        L_0x0257:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r0.<init>(r5)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r1 = "277f30"
            r3 = 30
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r1, (int) r3)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r3 = "74726873"
            r4 = 31
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r4)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r3 = r0.optString(r3)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            boolean r1 = r1.equals(r3)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            if (r1 == 0) goto L_0x0296
            r12.qqq9gg9gqq9qgg99q = r9     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r12.q9q99gq99gggqg9qqqgg = r9     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r1 = cn.tongdun.android.shell.FMAgent.STATUS_SUCCESSFUL     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            cn.tongdun.android.shell.FMAgent.mStatus = r1     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r1 = "73207230"
            r3 = 64
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r1, (int) r3)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r0 = r0.getString(r1)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r12.gqgqgqq9gq9q9q9 = r0     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            cn.tongdun.android.core.qgg9qgg9999g9g.g9qqggg99gqq99g9q r0 = cn.tongdun.android.core.qgg9qgg9999g9g.g9qqggg99gqq99g9q.gqg9qq9gqq9q9q()     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            android.content.Context r1 = qgg99qqg9gq     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r0.qgg9qgg9999g9g((android.content.Context) r1)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            goto L_0x02c5
        L_0x0296:
            java.lang.String r1 = "267b35"
            r3 = 27
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r1, (int) r3)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r3 = "745e685f"
            r4 = 51
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r4)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r0 = r0.optString(r3)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            boolean r0 = r1.equals(r0)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            if (r0 == 0) goto L_0x02ba
            java.lang.String r0 = cn.tongdun.android.shell.FMAgent.STATUS_FAILED     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            cn.tongdun.android.shell.FMAgent.mStatus = r0     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r0 = 0
            r12.q9q99gq99gggqg9qqqgg = r0     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            r12.g999gqq9ggqgqq = r5     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            goto L_0x02c5
        L_0x02ba:
            r12.q9q99gq99gggqg9qqqgg = r9     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            java.lang.String r0 = cn.tongdun.android.shell.FMAgent.STATUS_FAILED     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            cn.tongdun.android.shell.FMAgent.mStatus = r0     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            cn.tongdun.android.shell.common.CollectorError$TYPE r0 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_PROFILE_FAILED     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            cn.tongdun.android.shell.common.CollectorError.addError((cn.tongdun.android.shell.common.CollectorError.TYPE) r0, (java.lang.String) r5)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
        L_0x02c5:
            cn.tongdun.android.shell.common.CollectorError$TYPE r0 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_PROFILE_DELAY     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
            cn.tongdun.android.shell.common.CollectorError.remove(r0)     // Catch:{ JSONException -> 0x0319, Throwable -> 0x02e2 }
        L_0x02ca:
            long r0 = java.lang.System.currentTimeMillis()
            long r3 = r12.q9qq99qg9qqgqg9gqgg9
            long r0 = r0 - r3
            r12.q9gqqq99999qq = r0
            if (r25 == 0) goto L_0x02d8
            r25.countDown()
        L_0x02d8:
            if (r7 == 0) goto L_0x02dd
            r7.unregisterListener(r8)
        L_0x02dd:
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            if (r0 == 0) goto L_0x0344
            goto L_0x033b
        L_0x02e2:
            r0 = move-exception
            goto L_0x02f1
        L_0x02e4:
            r0 = move-exception
            r12 = r15
            goto L_0x0346
        L_0x02e7:
            r0 = move-exception
            r12 = r15
            goto L_0x02f1
        L_0x02ea:
            r12 = r15
            goto L_0x0319
        L_0x02ec:
            r0 = move-exception
            r12 = r1
            goto L_0x0346
        L_0x02ef:
            r0 = move-exception
            r12 = r1
        L_0x02f1:
            r12.q9q99gq99gggqg9qqqgg = r9     // Catch:{ all -> 0x0345 }
            java.lang.String r1 = cn.tongdun.android.shell.FMAgent.STATUS_FAILED     // Catch:{ all -> 0x0345 }
            cn.tongdun.android.shell.FMAgent.mStatus = r1     // Catch:{ all -> 0x0345 }
            org.json.JSONObject r0 = cn.tongdun.android.shell.common.CollectorError.catchErr(r0)     // Catch:{ all -> 0x0345 }
            cn.tongdun.android.shell.common.CollectorError$TYPE r1 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_PROFILE_FAILED     // Catch:{ all -> 0x0345 }
            cn.tongdun.android.shell.common.CollectorError.addError((cn.tongdun.android.shell.common.CollectorError.TYPE) r1, (org.json.JSONObject) r0)     // Catch:{ all -> 0x0345 }
            long r0 = java.lang.System.currentTimeMillis()
            long r3 = r12.q9qq99qg9qqgqg9gqgg9
            long r0 = r0 - r3
            r12.q9gqqq99999qq = r0
            if (r25 == 0) goto L_0x030e
            r25.countDown()
        L_0x030e:
            if (r7 == 0) goto L_0x0313
            r7.unregisterListener(r8)
        L_0x0313:
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            if (r0 == 0) goto L_0x0344
            goto L_0x033b
        L_0x0318:
            r12 = r1
        L_0x0319:
            r12.q9q99gq99gggqg9qqqgg = r9     // Catch:{ all -> 0x0345 }
            java.lang.String r0 = cn.tongdun.android.shell.FMAgent.STATUS_FAILED     // Catch:{ all -> 0x0345 }
            cn.tongdun.android.shell.FMAgent.mStatus = r0     // Catch:{ all -> 0x0345 }
            cn.tongdun.android.shell.common.CollectorError$TYPE r0 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_JSONERROR     // Catch:{ all -> 0x0345 }
            cn.tongdun.android.shell.common.CollectorError.addError((cn.tongdun.android.shell.common.CollectorError.TYPE) r0, (java.lang.String) r5)     // Catch:{ all -> 0x0345 }
            long r0 = java.lang.System.currentTimeMillis()
            long r3 = r12.q9qq99qg9qqgqg9gqgg9
            long r0 = r0 - r3
            r12.q9gqqq99999qq = r0
            if (r25 == 0) goto L_0x0332
            r25.countDown()
        L_0x0332:
            if (r7 == 0) goto L_0x0337
            r7.unregisterListener(r8)
        L_0x0337:
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            if (r0 == 0) goto L_0x0344
        L_0x033b:
            cn.tongdun.android.shell.inter.FMCallback r0 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            java.lang.String r1 = r15.gqg9qq9gqq9q9q(r16)
            r0.onEvent(r1)
        L_0x0344:
            return
        L_0x0345:
            r0 = move-exception
        L_0x0346:
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r12.q9qq99qg9qqgqg9gqgg9
            long r3 = r3 - r5
            r12.q9gqqq99999qq = r3
            if (r25 == 0) goto L_0x0354
            r25.countDown()
        L_0x0354:
            if (r7 == 0) goto L_0x0359
            r7.unregisterListener(r8)
        L_0x0359:
            cn.tongdun.android.shell.inter.FMCallback r1 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            if (r1 == 0) goto L_0x0366
            cn.tongdun.android.shell.inter.FMCallback r1 = cn.tongdun.android.shell.FMAgent.mfmCallBack
            java.lang.String r2 = r15.gqg9qq9gqq9q9q(r16)
            r1.onEvent(r2)
        L_0x0366:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(android.content.Context, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, java.util.concurrent.CountDownLatch, java.lang.String, int, java.lang.String, java.lang.String, boolean):void");
    }

    private void gqg9qq9gqq9q9q(SensorManager sensorManager, q9qgqg9 q9qgqg9) {
        try {
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            sensorManager.registerListener(q9qgqg9, defaultSensor, 0);
            if (defaultSensor != null) {
                defaultSensor = sensorManager.getDefaultSensor(9);
                sensorManager.registerListener(q9qgqg9, defaultSensor, 0);
            }
            if (defaultSensor != null) {
                defaultSensor = sensorManager.getDefaultSensor(5);
                sensorManager.registerListener(q9qgqg9, defaultSensor, 0);
            }
            if (defaultSensor != null) {
                defaultSensor = sensorManager.getDefaultSensor(2);
                sensorManager.registerListener(q9qgqg9, defaultSensor, 0);
            }
            if (defaultSensor != null) {
                defaultSensor = sensorManager.getDefaultSensor(4);
                sensorManager.registerListener(q9qgqg9, defaultSensor, 0);
            }
            if (defaultSensor != null) {
                sensorManager.registerListener(q9qgqg9, sensorManager.getDefaultSensor(9), 0);
            }
        } catch (Exception unused) {
        }
    }

    public String gqg9qq9gqq9q9q(Context context) {
        String str;
        String str2;
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis() - FMAgent.mStartInitTime;
            int i = (int) (currentTimeMillis / 86400000);
            if (i > 15 && context != null) {
                FMAgent.initWithOptions(context, FMAgent.ENV_PRODUCTION, FMAgent.mOptions);
                Thread.sleep(2500);
            }
            jSONObject.put(gqg9qq9gqq9q9q("7806", 123), qgg9qgg9999g9g.qgg9qgg9999g9g);
            jSONObject.put(gqg9qq9gqq9q9q("612661277b217a", 84), qgg9qgg9999g9g.gqg9qq9gqq9q9q);
            jSONObject.put(gqg9qq9gqq9q9q("67217229782f7a39", 81), g9g9g99.qqq9gg9gqq9qgg99q(context));
            jSONObject.put(gqg9qq9gqq9q9q("67656d6c62696b53404e4446", 6), this.q9gqqq99999qq);
            jSONObject.put(gqg9qq9gqq9q9q("7e787369646d7360404b5d4f55", 30), currentTimeMillis);
            if (!this.q9q99gq99gggqg9qqqgg && !TextUtils.isEmpty(this.g999gqq9ggqgqq)) {
                return this.g999gqq9ggqgqq;
            }
            if (!this.qqq9gg9gqq9qgg99q) {
                String gqg9qq9gqq9q9q2 = this.g9q9q9g9.gqg9qq9gqq9q9q(cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg99qqg9gq);
                JSONObject jSONObject2 = new JSONObject();
                String errorCode = CollectorError.getErrorCode();
                str2 = HelperJNI.err0r(errorCode, CollectorError.getErrorMsg());
                jSONObject2.put(gqg9qq9gqq9q9q("7223653e7813441f4f1e", 85), errorCode);
                jSONObject2.put(gqg9qq9gqq9q9q("7212650f78224a3c5e", 100), str2);
                jSONObject.put(gqg9qq9gqq9q9q("7256654b78664e61497c", 32), jSONObject2);
                jSONObject.put(gqg9qq9gqq9q9q("73277132", 67), gqg9qq9gqq9q9q2);
                str = BoxUtil.limitBox(jSONObject, cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q.qgg99qqg9gq);
            } else {
                jSONObject.put(gqg9qq9gqq9q9q("6360706e7b5f4d52", 26), this.gqgqgqq9gq9q9q9);
                str = jSONObject.toString();
            }
            String encodeToString = Base64.encodeToString(str.getBytes(gqg9qq9gqq9q9q("6217675c72", 119)), 2);
            if (i >= 1 && i < 15 && context != null) {
                FMAgent.initWithOptions(context, FMAgent.ENV_PRODUCTION, FMAgent.mOptions);
            }
            LogUtil.info(gqg9qq9gqq9q9q("500e565a1454195611622c6f3b3768317e31782778", 77));
            return encodeToString;
        } catch (Throwable th) {
            return CollectorError.catchErr(th).toString();
        }
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 97);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 23);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
