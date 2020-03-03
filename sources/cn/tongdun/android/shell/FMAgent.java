package cn.tongdun.android.shell;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import cn.tongdun.android.core.gqg9qq9gqq9q9q;
import cn.tongdun.android.shell.common.CollectorError;
import cn.tongdun.android.shell.common.HelperJNI;
import cn.tongdun.android.shell.inter.FMCallback;
import cn.tongdun.android.shell.settings.Constants;
import cn.tongdun.android.shell.utils.BoxUtil;
import cn.tongdun.android.shell.utils.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class FMAgent {
    public static String CURRENT_ENV = null;
    public static String CURRENT_PARTNERCODE = null;
    public static final String ENV_PRODUCTION = linkxxxxx("682e6d257c336b2e6d2f", 109);
    public static final String ENV_SANDBOX = linkxxxxx("6b737c797a746d", 32);
    public static final String OPTION_ALWAYS_DEMOTION = linkxxxxx("79397a2f62254e1e4f164d0d500b51", 117);
    public static final String OPTION_APP_NAME = linkxxxxx("597e4171507e5c76", 46);
    public static final String OPTION_BLACKBOX_MAXSIZE = linkxxxxx("7a766f74677d6a6a4d5841414a5b5944", 57);
    public static final String OPTION_CHECK_LICENSE = linkxxxxx("7b5f6e59666d55685f6e546352", 21);
    public static final String OPTION_CUSTOM_URL = linkxxxxx("7b0265057e074c2d4b33", 85);
    public static final String OPTION_CUST_PROCESS = linkxxxxx("7b5e65594e764c6b406d566d", 9);
    public static final String OPTION_DOMAIN = linkxxxxx("7c1c66106e17", 86);
    public static final String OPTION_DOUBLE_URL = linkxxxxx("7c007e17701e4a344d2a", 74);
    public static final String OPTION_GOOGLE_AID = linkxxxxx("5f2147294c20761e7e13", 72);
    public static final String OPTION_INIT_TIMESPAN = linkxxxxx("71016e1c45375833502553345c", 71);
    public static final String OPTION_KILL_DEBUGGER = linkxxxxx("73296e295d125c024e024c15", 106);
    public static final String OPTION_OVERRIDE_CERTI = linkxxxxx("777a7c6d7c7671774b4b4d5c4b41", 34);
    public static final String OPTION_PARTNER_CODE = linkxxxxx("681c631a720d5f31533a52", 76);
    public static final String OPTION_PROXY_URL = linkxxxxx("683e6d296c0f460858", 125);
    public static final String OPTION_SKIP_GPS = linkxxxxx("6b65717c5e444947", 60);
    public static final String OPTION_WAIT_TIME = linkxxxxx("6f307f2d5406490241", 103);
    public static final String STATUS_COLLECTING = linkxxxxx("7b7f607f69797e64796d", 50);
    public static final String STATUS_FAILED = linkxxxxx("7e556e506751", 19);
    public static final String STATUS_LOADING = linkxxxxx("745a625f6f5866", 24);
    public static final String STATUS_PROFILING = linkxxxxx("68656d6c6269676e6e", 38);
    public static final String STATUS_SUCCESSFUL = linkxxxxx("6b0d650d631b630e7017", 74);
    public static final String STATUS_UNINIT = linkxxxxx("6d2e72297534", 116);
    public static final int Tag = 63109263;
    private static final int class_version = 312;
    /* access modifiers changed from: private */
    public static int mBlackboxMaxSize = Integer.MAX_VALUE;
    /* access modifiers changed from: private */
    public static CountDownLatch mDownLatch = null;
    /* access modifiers changed from: private */
    public static gqg9qq9gqq9q9q mFmInter = null;
    private static boolean mInited = false;
    /* access modifiers changed from: private */
    public static long mLastInitTime = 0;
    public static Map mOptions = null;
    public static long mStartInitTime = 0;
    public static String mStatus = STATUS_UNINIT;
    private static int mWaitTime = 3000;
    public static FMCallback mfmCallBack = null;

    public static void openLog() {
        LogUtil.openLog();
        LogUtil.info(linkxxxxx("57605a6b143f0f3e063d172659754e7a0536063e", 30));
    }

    public static String getInitStatus() {
        return mStatus;
    }

    public static void initWithCallback(Context context, String str, Map map, FMCallback fMCallback) {
        mfmCallBack = fMCallback;
        init(context, str, map);
        mOptions = map;
    }

    public static void initWithCallback(Context context, String str, FMCallback fMCallback) {
        initWithCallback(context, str, (Map) null, fMCallback);
    }

    private static void init1(Context context, String str) {
        if (mfmCallBack != null) {
            mfmCallBack = null;
        }
        if (TextUtils.isEmpty(str) || context == null) {
            LogUtil.err(linkxxxxx("f003f052b571d308e9eec5efdffec2f2509c4ee226e7f6e0e84a967ddc08ea48a146e500ea", 5));
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(OPTION_CUSTOM_URL, str);
        init(context, ENV_PRODUCTION, hashMap);
    }

    public static void initWithDomain(Context context, String str) {
        if (mfmCallBack != null) {
            mfmCallBack = null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(OPTION_DOMAIN, str);
        mOptions = hashMap;
        init(context, ENV_PRODUCTION, hashMap);
    }

    public static void init(Context context, String str) {
        if (mfmCallBack != null) {
            mfmCallBack = null;
        }
        init(context, str, (Map) null);
    }

    public static void initWithOptions(Context context, String str, Map map) {
        if (mfmCallBack != null) {
            mfmCallBack = null;
        }
        init(context, str, map);
        mOptions = map;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v38, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v42, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v44, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v49, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void init(android.content.Context r20, java.lang.String r21, java.util.Map r22) {
        /*
            r0 = r21
            r1 = r22
            long r2 = java.lang.System.currentTimeMillis()
            mStartInitTime = r2
            r2 = 1
            mInited = r2     // Catch:{ Throwable -> 0x021a }
            cn.tongdun.android.shell.common.CollectorError$TYPE r3 = cn.tongdun.android.shell.common.CollectorError.TYPE.ERROR_INIT     // Catch:{ Throwable -> 0x021a }
            cn.tongdun.android.shell.common.CollectorError.remove(r3)     // Catch:{ Throwable -> 0x021a }
            android.content.Context r3 = r20.getApplicationContext()     // Catch:{ Throwable -> 0x021a }
            r4 = 3000(0xbb8, float:4.204E-42)
            r5 = 600000(0x927c0, float:8.40779E-40)
            java.lang.String r6 = cn.tongdun.android.shell.settings.Constants.DEFAULT_PARTNER_CODE     // Catch:{ Throwable -> 0x021a }
            java.lang.String r7 = cn.tongdun.android.shell.settings.Constants.DEFAULT_DOMAIN     // Catch:{ Throwable -> 0x021a }
            java.lang.String r8 = cn.tongdun.android.shell.settings.Constants.DEFAULT_CUST_PROCESS     // Catch:{ Throwable -> 0x021a }
            java.lang.String r9 = cn.tongdun.android.shell.settings.Constants.DEFAULT_CUSTOM_URL     // Catch:{ Throwable -> 0x021a }
            java.lang.String r10 = cn.tongdun.android.shell.settings.Constants.DEFAULT_DOUBLE_URL     // Catch:{ Throwable -> 0x021a }
            java.lang.String r11 = cn.tongdun.android.shell.settings.Constants.DEFAULT_PROXY_URL     // Catch:{ Throwable -> 0x021a }
            java.lang.String r12 = cn.tongdun.android.shell.settings.Constants.DEFAULT_GOOGLE_AID     // Catch:{ Throwable -> 0x021a }
            java.lang.String r13 = cn.tongdun.android.shell.settings.Constants.DEFAULT_APPNAME     // Catch:{ Throwable -> 0x021a }
            if (r1 == 0) goto L_0x015f
            mOptions = r1     // Catch:{ Throwable -> 0x021a }
            java.lang.String r15 = OPTION_WAIT_TIME     // Catch:{ Throwable -> 0x021a }
            boolean r15 = r1.containsKey(r15)     // Catch:{ Throwable -> 0x021a }
            if (r15 == 0) goto L_0x0043
            java.lang.String r4 = OPTION_WAIT_TIME     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r4 = r1.get(r4)     // Catch:{ Throwable -> 0x021a }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Throwable -> 0x021a }
            int r4 = r4.intValue()     // Catch:{ Throwable -> 0x021a }
        L_0x0043:
            java.lang.String r15 = OPTION_INIT_TIMESPAN     // Catch:{ Throwable -> 0x021a }
            boolean r15 = r1.containsKey(r15)     // Catch:{ Throwable -> 0x021a }
            if (r15 == 0) goto L_0x0057
            java.lang.String r5 = OPTION_INIT_TIMESPAN     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r5 = r1.get(r5)     // Catch:{ Throwable -> 0x021a }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ Throwable -> 0x021a }
            int r5 = r5.intValue()     // Catch:{ Throwable -> 0x021a }
        L_0x0057:
            java.lang.String r15 = OPTION_BLACKBOX_MAXSIZE     // Catch:{ Throwable -> 0x021a }
            boolean r15 = r1.containsKey(r15)     // Catch:{ Throwable -> 0x021a }
            if (r15 == 0) goto L_0x006c
            java.lang.String r15 = OPTION_BLACKBOX_MAXSIZE     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r15 = r1.get(r15)     // Catch:{ Throwable -> 0x021a }
            java.lang.Integer r15 = (java.lang.Integer) r15     // Catch:{ Throwable -> 0x021a }
            int r15 = r15.intValue()     // Catch:{ Throwable -> 0x021a }
            goto L_0x006d
        L_0x006c:
            r15 = 0
        L_0x006d:
            java.lang.String r14 = OPTION_SKIP_GPS     // Catch:{ Throwable -> 0x021a }
            boolean r14 = r1.containsKey(r14)     // Catch:{ Throwable -> 0x021a }
            if (r14 == 0) goto L_0x0082
            java.lang.String r14 = OPTION_SKIP_GPS     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r14 = r1.get(r14)     // Catch:{ Throwable -> 0x021a }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ Throwable -> 0x021a }
            boolean r14 = r14.booleanValue()     // Catch:{ Throwable -> 0x021a }
            goto L_0x0083
        L_0x0082:
            r14 = 0
        L_0x0083:
            java.lang.String r2 = OPTION_KILL_DEBUGGER     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x009a
            java.lang.String r2 = OPTION_KILL_DEBUGGER     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r2.booleanValue()     // Catch:{ Throwable -> 0x021a }
            r17 = r2
            goto L_0x009c
        L_0x009a:
            r17 = 0
        L_0x009c:
            java.lang.String r2 = OPTION_ALWAYS_DEMOTION     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x00b3
            java.lang.String r2 = OPTION_ALWAYS_DEMOTION     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r2.booleanValue()     // Catch:{ Throwable -> 0x021a }
            r18 = r2
            goto L_0x00b5
        L_0x00b3:
            r18 = 0
        L_0x00b5:
            java.lang.String r2 = OPTION_PARTNER_CODE     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x00c6
            java.lang.String r2 = OPTION_PARTNER_CODE     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x021a }
            r6 = r2
        L_0x00c6:
            java.lang.String r2 = OPTION_DOMAIN     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x00d7
            java.lang.String r2 = OPTION_DOMAIN     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Throwable -> 0x021a }
        L_0x00d7:
            java.lang.String r2 = OPTION_CUST_PROCESS     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x00e8
            java.lang.String r2 = OPTION_CUST_PROCESS     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x021a }
            r8 = r2
        L_0x00e8:
            java.lang.String r2 = OPTION_CUSTOM_URL     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x00f9
            java.lang.String r2 = OPTION_CUSTOM_URL     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Throwable -> 0x021a }
        L_0x00f9:
            java.lang.String r2 = OPTION_DOUBLE_URL     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x010a
            java.lang.String r2 = OPTION_DOUBLE_URL     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Throwable -> 0x021a }
        L_0x010a:
            java.lang.String r2 = OPTION_PROXY_URL     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x011b
            java.lang.String r2 = OPTION_PROXY_URL     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Throwable -> 0x021a }
        L_0x011b:
            java.lang.String r2 = OPTION_GOOGLE_AID     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x012c
            java.lang.String r2 = OPTION_GOOGLE_AID     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            r12 = r2
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ Throwable -> 0x021a }
        L_0x012c:
            java.lang.String r2 = OPTION_APP_NAME     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x013d
            java.lang.String r2 = OPTION_APP_NAME     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r2 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x021a }
            r13 = r2
        L_0x013d:
            java.lang.String r2 = OPTION_OVERRIDE_CERTI     // Catch:{ Throwable -> 0x021a }
            boolean r2 = r1.containsKey(r2)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x0156
            java.lang.String r2 = OPTION_OVERRIDE_CERTI     // Catch:{ Throwable -> 0x021a }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ Throwable -> 0x021a }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ Throwable -> 0x021a }
            boolean r1 = r1.booleanValue()     // Catch:{ Throwable -> 0x021a }
            r16 = r1
            r1 = r8
            r8 = r11
            goto L_0x015a
        L_0x0156:
            r1 = r8
            r8 = r11
            r16 = 0
        L_0x015a:
            r11 = r7
            r7 = r10
            r10 = r18
            goto L_0x016a
        L_0x015f:
            r1 = r8
            r8 = r11
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r11 = r7
            r7 = r10
            r10 = 0
        L_0x016a:
            r2 = 5120(0x1400, float:7.175E-42)
            if (r15 >= r2) goto L_0x0170
            r15 = 5120(0x1400, float:7.175E-42)
        L_0x0170:
            mBlackboxMaxSize = r15     // Catch:{ Throwable -> 0x021a }
            if (r6 != 0) goto L_0x0179
            java.lang.String r2 = cn.tongdun.android.shell.common.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(r3)     // Catch:{ Throwable -> 0x021a }
            r6 = r2
        L_0x0179:
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x021a }
            if (r2 == 0) goto L_0x0191
            android.os.Handler r0 = new android.os.Handler     // Catch:{ Throwable -> 0x021a }
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ Throwable -> 0x021a }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x021a }
            cn.tongdun.android.shell.gqg9qq9gqq9q9q r1 = new cn.tongdun.android.shell.gqg9qq9gqq9q9q     // Catch:{ Throwable -> 0x021a }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x021a }
            r0.post(r1)     // Catch:{ Throwable -> 0x021a }
            return
        L_0x0191:
            if (r13 != 0) goto L_0x0198
            java.lang.String r2 = cn.tongdun.android.shell.common.gqg9qq9gqq9q9q.qgg9qgg9999g9g(r3)     // Catch:{ Throwable -> 0x021a }
            r13 = r2
        L_0x0198:
            java.util.concurrent.CountDownLatch r2 = new java.util.concurrent.CountDownLatch     // Catch:{ Throwable -> 0x021a }
            r15 = 1
            r2.<init>(r15)     // Catch:{ Throwable -> 0x021a }
            mDownLatch = r2     // Catch:{ Throwable -> 0x021a }
            cn.tongdun.android.shell.common.q9qq99qg9qqgqg9gqgg9 r2 = new cn.tongdun.android.shell.common.q9qq99qg9qqgqg9gqgg9     // Catch:{ Throwable -> 0x021a }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x021a }
            r2.gqg9qq9gqq9q9q(r0, r8, r6, r1)     // Catch:{ Throwable -> 0x021a }
            if (r0 == 0) goto L_0x01c6
            java.lang.String r15 = ENV_SANDBOX     // Catch:{ Throwable -> 0x021a }
            boolean r15 = r0.equals(r15)     // Catch:{ Throwable -> 0x021a }
            if (r15 == 0) goto L_0x01c6
            android.os.Handler r15 = new android.os.Handler     // Catch:{ Throwable -> 0x021a }
            r19 = r13
            android.os.Looper r13 = android.os.Looper.getMainLooper()     // Catch:{ Throwable -> 0x021a }
            r15.<init>(r13)     // Catch:{ Throwable -> 0x021a }
            cn.tongdun.android.shell.qgg9qgg9999g9g r13 = new cn.tongdun.android.shell.qgg9qgg9999g9g     // Catch:{ Throwable -> 0x021a }
            r13.<init>(r3)     // Catch:{ Throwable -> 0x021a }
            r15.post(r13)     // Catch:{ Throwable -> 0x021a }
            goto L_0x01c8
        L_0x01c6:
            r19 = r13
        L_0x01c8:
            CURRENT_ENV = r0     // Catch:{ Throwable -> 0x021a }
            CURRENT_PARTNERCODE = r6     // Catch:{ Throwable -> 0x021a }
            boolean r1 = r2.gqg9qq9gqq9q9q(r1)     // Catch:{ Throwable -> 0x021a }
            if (r1 != 0) goto L_0x01d8
            java.util.concurrent.CountDownLatch r0 = mDownLatch     // Catch:{ Throwable -> 0x021a }
            r0.countDown()     // Catch:{ Throwable -> 0x021a }
            return
        L_0x01d8:
            long r0 = mLastInitTime     // Catch:{ Throwable -> 0x021a }
            boolean r0 = r2.gqg9qq9gqq9q9q((long) r0, (int) r5)     // Catch:{ Throwable -> 0x021a }
            if (r0 != 0) goto L_0x01f9
            java.lang.String r0 = mStatus     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = STATUS_SUCCESSFUL     // Catch:{ Throwable -> 0x021a }
            if (r0 != r1) goto L_0x01f9
            java.util.concurrent.CountDownLatch r0 = mDownLatch     // Catch:{ Throwable -> 0x021a }
            r0.countDown()     // Catch:{ Throwable -> 0x021a }
            cn.tongdun.android.shell.inter.FMCallback r0 = mfmCallBack     // Catch:{ Throwable -> 0x021a }
            if (r0 == 0) goto L_0x01f8
            cn.tongdun.android.shell.inter.FMCallback r0 = mfmCallBack     // Catch:{ Throwable -> 0x021a }
            java.lang.String r1 = onEvent(r3)     // Catch:{ Throwable -> 0x021a }
            r0.onEvent(r1)     // Catch:{ Throwable -> 0x021a }
        L_0x01f8:
            return
        L_0x01f9:
            java.lang.String r0 = STATUS_LOADING     // Catch:{ Throwable -> 0x021a }
            mStatus = r0     // Catch:{ Throwable -> 0x021a }
            mWaitTime = r4     // Catch:{ Throwable -> 0x021a }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Throwable -> 0x021a }
            cn.tongdun.android.shell.q9qq99qg9qqgqg9gqgg9 r15 = new cn.tongdun.android.shell.q9qq99qg9qqgqg9gqgg9     // Catch:{ Throwable -> 0x021a }
            r1 = r15
            r2 = r3
            r3 = r21
            r4 = r6
            r5 = r14
            r6 = r9
            r9 = r17
            r13 = r19
            r14 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ Throwable -> 0x021a }
            r0.<init>(r15)     // Catch:{ Throwable -> 0x021a }
            r0.start()     // Catch:{ Throwable -> 0x021a }
            goto L_0x0239
        L_0x021a:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "5b6a4e6a02230524186a02"
            r3 = 9
            java.lang.String r2 = linkxxxxx(r2, r3)
            r1.append(r2)
            org.json.JSONObject r0 = cn.tongdun.android.shell.common.CollectorError.catchErr(r0)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            cn.tongdun.android.shell.utils.LogUtil.err(r0)
        L_0x0239:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.shell.FMAgent.init(android.content.Context, java.lang.String, java.util.Map):void");
    }

    public static String onEvent(Context context) {
        JSONObject jSONObject;
        String errorCode;
        String str;
        if (!mInited) {
            CollectorError.addError(CollectorError.TYPE.ERROR_INIT, linkxxxxx("5c66492207231c7755704d6949670c2e0b2916", 10));
            LogUtil.err(linkxxxxx("55514b561f1f180701030f464f60446c626e69743333343429206966667d677a327a32", 40));
        }
        if (mDownLatch != null) {
            try {
                mDownLatch.await((long) mWaitTime, TimeUnit.MILLISECONDS);
            } catch (Exception unused) {
            }
        }
        if (mFmInter != null) {
            return mFmInter.gqg9qq9gqq9q9q(context);
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject = new JSONObject();
            errorCode = CollectorError.getErrorCode();
            str = HelperJNI.err0r(errorCode, CollectorError.getErrorMsg());
        } catch (Throwable th) {
            JSONObject catchErr = CollectorError.catchErr(th);
            LogUtil.err(linkxxxxx("7751446257694d2757", 17) + catchErr);
            return catchErr.toString();
        }
        jSONObject.put(linkxxxxx("7d2c6531781c44104f11", 122), errorCode);
        jSONObject.put(linkxxxxx("7d5e6543786e4a705e", 8), str);
        jSONObject.put(linkxxxxx("7c3977267d20", 121), cn.tongdun.android.shell.common.gqg9qq9gqq9q9q.g9q9q9g9(context));
        jSONObject2.put(linkxxxxx("7771", 44), Constants.OS);
        jSONObject2.put(linkxxxxx("6e1d611c7b1a7a", 79), Constants.VERSION);
        jSONObject2.put(linkxxxxx("6806720e78087a1e", 86), cn.tongdun.android.shell.common.gqg9qq9gqq9q9q.q9qq99qg9qqgqg9gqgg9(context));
        jSONObject2.put(linkxxxxx("7d23653e78134e144909", 117), jSONObject);
        return Base64.encodeToString(BoxUtil.limitBox(jSONObject2, mBlackboxMaxSize).getBytes(linkxxxxx("6d72673972", 50)), 2);
    }

    public static String linkxxxxx(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 65);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 24);
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
