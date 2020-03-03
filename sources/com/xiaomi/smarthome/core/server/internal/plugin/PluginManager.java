package com.xiaomi.smarthome.core.server.internal.plugin;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.coloros.mcssdk.PushManager;
import com.google.android.exoplayer2.C;
import com.google.android.gms.measurement.AppMeasurement;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.util.PushRouteUtil;
import com.miui.tsmclient.model.ErrorCode;
import com.taobao.weex.el.parse.Operators;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.accountsdk.utils.Coder;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.ExternalLoadManager;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.plugin.PluginUpdateInfo;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback;
import com.xiaomi.smarthome.core.server.internal.CoreError;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.core.server.internal.plugin.api.PluginSmartHomeApi;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.DeveloperResult;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginConfigInfoNewResult;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginUpdateInfoResult;
import com.xiaomi.smarthome.core.server.internal.plugin.util.ByteUtils;
import com.xiaomi.smarthome.core.server.internal.plugin.util.Callback;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.core.server.internal.plugin.util.PluginUtils;
import com.xiaomi.smarthome.core.server.internal.plugin.util.PreferenceUtils;
import com.xiaomi.smarthome.core.server.internal.plugin.util.ZipFileUtils;
import com.xiaomi.smarthome.core.server.internal.statistic.StatManager;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.FileAsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.setting.PluginSetting;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.CookieManager;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.security.cert.X509Certificate;
import okhttp3.Dispatcher;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class PluginManager {
    private static Object A = new Object();
    private static final String D;
    private static final String T = "model";
    private static final String U = "minPluginSdkApiVersion";
    private static final String V = "MiHomeDeveloperId";
    private static final String W = "MiHomePlatform";
    private static final String X = "MiHomeSupportWidget";
    private static final String Y = "MiHomeSupportAppAV";
    private static final String Z = ".apk";

    /* renamed from: a  reason: collision with root package name */
    public static final String f14587a = "PluginManager";
    private static final int aT = 2;
    private static final int aU = 3;
    private static final int aV = 4;
    private static final int aW = 5;
    private static final int aX = 6;
    private static final int aY = 9;
    private static final int aZ = 10;
    private static final String aa = ".mpk";
    private static final String ab = ".h5";
    private static final String ac = ".apk";
    private static final String ad = ".rn";
    private static final String ae = ".rnsdk";
    @Deprecated
    private static final String af = "plugin_config_pref";
    private static final String ag = "plugin_common_parcel2";
    private static final String ah = "plugin_device_list.parcel2";
    private static final String ai = "plugin_developer_list.parcel2";
    private static final String aj = "plugin_record_list.parcel2";
    private static final String ak = "device_list_local";
    private static final String al = "device_list_last_modify";
    private static final String am = "developer_list_last_modify";
    private static final String an = "device_list_md5";
    private static final String ao = "developer_list_md5";
    private static final String ap = "plugin_record_md5";
    private static final String aq = "last_check_update";
    private static final String ar = "last_check_version";
    private static final String as = "auto_update_plugin";
    private static final long at = 21600000;
    private static final long au = 120000;
    public static final String b = "downrnsdk_action";
    private static final String bA = "SHA-256";
    private static final String bB = "-----BEGIN ";
    private static final int bC = 1024;
    private static final JoinPoint.StaticPart bE = null;
    private static final int ba = 11;
    private static final int bb = 12;
    private static final int bc = 13;
    private static final int bd = 14;
    private static final int be = 15;
    private static final int bf = 16;
    private static final int bg = 17;
    private static final int bh = 18;
    private static final int bi = 19;
    private static final int bj = 20;
    private static final int bk = 21;
    private static final int bl = 22;
    private static final int bm = 23;
    private static final int bn = 24;
    private static final int bo = 25;
    private static final int bp = 2;
    private static final String br = "conf.json";
    private static final String bs = "_conf.json";
    private static final String bt = "android";
    private static final String bu = "_android";
    private static final String bv = "#";
    private static final String bw = "!";
    private static final String bx = "max_rn_sdk";
    private static final String by = "rn_sdk_is_forbidden";
    private static final String bz = "SHA256withECDSA";
    public static final String c = "sdk_api_level";
    public static final String d = "timestamp";
    public static final String e = "path";
    public static final String f = (File.separator + "plugin" + File.separator + k);
    public static final String g = "apk";
    public static final String h = "h5";
    public static final String i = "mpk";
    public static final String j = "rn";
    public static final String k = "rnsdk";
    public static final int p = 0;
    public static final int q = 1;
    public static final int r = 2;
    public static final int s = 3;
    public static final int t = 4;
    public static final int u = 5;
    public static final int v = 6;
    public static final int w = 7;
    public static final int x = 10;
    public static final int y = 10000;
    private static PluginManager z;
    private boolean B = false;
    /* access modifiers changed from: private */
    public Context C = CommonApplication.getAppContext();
    private String E;
    private String F;
    @Deprecated
    private String G;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private String M;
    private String N;
    private String O;
    private String P;
    private String Q;
    @Deprecated
    private String R;
    private String S;
    /* access modifiers changed from: private */
    public SharedPreferences aA;
    private PackageListener aB;
    private OkHttpClient aC;
    /* access modifiers changed from: private */
    public boolean aD = true;
    /* access modifiers changed from: private */
    public Map<String, PluginDeviceInfo> aE = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<Long, PluginDeveloperInfo> aF = new ConcurrentHashMap();
    private List<PluginPackageInfo> aG = new CopyOnWriteArrayList();
    private Map<Long, PluginPackageInfo> aH = new ConcurrentHashMap();
    private List<PluginPackageInfo> aI = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public Map<Long, PluginPackageInfo> aJ = new ConcurrentHashMap();
    private Map<Long, RnSdkTask> aK = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<Long, List<PluginDownloadTaskInternal>> aL = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<Integer, PluginDownloadTaskInternal> aM = new ConcurrentHashMap();
    private List<PluginRecord> aN = new CopyOnWriteArrayList();
    private byte[] aO;
    @Deprecated
    private List<PluginPackageInfo> aP = new CopyOnWriteArrayList();
    @Deprecated
    private Map<Long, PluginPackageInfo> aQ = new ConcurrentHashMap();
    private List<WaitingInstallApkPluginRecord> aR = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, Pattern> aS = new HashMap();
    private PackageManager av;
    /* access modifiers changed from: private */
    public SharedPreferences aw;
    private File ax;
    private File ay;
    private File az;
    private ExecutorService bD;
    private String bq = null;
    Handler l;
    MessageHandlerThread m;
    WorkerHandler n;
    public Map<String, PluginRecord> o = new ConcurrentHashMap();

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return PluginManager.a((PluginManager) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    private interface ClearAllPluginConfigCallbackInternal {
        void a();

        void b();
    }

    public interface ClearConfigCallback {
        void a();

        void b();
    }

    public static abstract class DebugPackageCallback {
        public abstract void a();

        public abstract void a(String str);

        public abstract void b();

        public abstract void b(String str);
    }

    public interface DumpPluginCallback {
        void a();

        void b();
    }

    private interface DumpPluginCallbackInternal {
        void a();

        void b();
    }

    public interface GetAutoUpdateCallback {
        void a();

        void a(boolean z);
    }

    private interface GetAutoUpdateCallbackInternal {
        void a();

        void a(boolean z);
    }

    private interface LoadLocalPluginInfoCallback {
        void a();

        void b();
    }

    public static class PluginCancelDownloadCallback {
        public void a(PluginRecord pluginRecord) {
        }

        public void b(PluginRecord pluginRecord) {
        }
    }

    public interface PluginDeleteCallback {
        void a(PluginRecord pluginRecord);

        void b(PluginRecord pluginRecord);

        void c(PluginRecord pluginRecord);
    }

    public interface PluginDownloadCallback {
        void a(PluginRecord pluginRecord);

        void a(PluginRecord pluginRecord, float f);

        void a(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask);

        void b(PluginRecord pluginRecord);

        void b(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask);

        void c(PluginRecord pluginRecord);

        void c(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask);
    }

    public interface PluginInstallCallback {
        void a(PluginRecord pluginRecord);

        void b(PluginRecord pluginRecord);

        void c(PluginRecord pluginRecord);

        void d(PluginRecord pluginRecord);
    }

    public interface PluginLoadCallback {
        void a(PluginRecord pluginRecord);

        void b(PluginRecord pluginRecord);
    }

    public interface PluginUpdateAllCallback {
        void a();

        void b();
    }

    public interface PluginUpdateCallback {
        void a(PluginRecord pluginRecord);

        void a(PluginRecord pluginRecord, float f);

        void a(PluginRecord pluginRecord, PluginUpdateInfo pluginUpdateInfo);

        void a(PluginRecord pluginRecord, HttpAsyncHandle httpAsyncHandle);

        void b(PluginRecord pluginRecord);

        void b(PluginRecord pluginRecord, HttpAsyncHandle httpAsyncHandle);

        void c(PluginRecord pluginRecord);

        void d(PluginRecord pluginRecord);

        void e(PluginRecord pluginRecord);

        void f(PluginRecord pluginRecord);
    }

    public interface SetAutoUpdateCallback {
        void a();

        void a(boolean z);
    }

    private interface SetAutoUpdateCallbackInternal {
        void a();

        void a(boolean z);
    }

    private interface UpdateAllCallbackInternal {
        void a();

        void b();
    }

    public interface UpdateConfigCallback {
        void a(CoreError coreError);

        void a(boolean z, boolean z2);
    }

    private interface UpdateConfigCallbackInternal {
        void a(CoreError coreError);

        void a(boolean z, boolean z2);
    }

    private static void X() {
        Factory factory = new Factory("PluginManager.java", PluginManager.class);
        bE = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), (int) TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE);
    }

    @Deprecated
    private PackageRawInfo a(PluginRecord pluginRecord, String str) {
        return null;
    }

    public static final boolean a(long j2) {
        return j2 >= 1000000;
    }

    @Deprecated
    private boolean a(PluginRecord pluginRecord, PackageInfo packageInfo) {
        return false;
    }

    @Deprecated
    private boolean b(int i2) {
        return i2 > 0 && i2 <= 100;
    }

    public static final boolean b(long j2) {
        return j2 >= 1000000;
    }

    private void c(PluginRecord pluginRecord) {
    }

    @Deprecated
    private boolean e(PluginRecord pluginRecord) {
        return true;
    }

    @Deprecated
    private boolean f(PluginRecord pluginRecord) {
        return false;
    }

    @Deprecated
    private boolean g(PluginRecord pluginRecord) {
        return false;
    }

    private void i(String str) {
    }

    private boolean i(long j2) {
        return j2 > 0;
    }

    @Deprecated
    private boolean i(PluginRecord pluginRecord) {
        return false;
    }

    private boolean j(long j2) {
        return j2 > 0;
    }

    @Deprecated
    private boolean j(PluginRecord pluginRecord) {
        return false;
    }

    private String k(PluginRecord pluginRecord) {
        return "";
    }

    @Deprecated
    private String l(PluginRecord pluginRecord) {
        return "";
    }

    public int h() {
        return 100;
    }

    static {
        X();
        StringBuilder sb = new StringBuilder();
        sb.append(f);
        sb.append("_config");
        D = sb.toString();
    }

    private PluginManager() {
    }

    public static PluginManager a() {
        if (z == null) {
            synchronized (A) {
                if (z == null) {
                    z = new PluginManager();
                }
            }
        }
        return z;
    }

    public void b() {
        boolean z2;
        synchronized (A) {
            z2 = this.B;
            if (!this.B) {
                this.B = true;
            }
        }
        if (!z2) {
            l("start init");
            this.E = "plugin";
            this.F = m() + File.separator + "plugin" + File.separator + "downloading";
            this.G = m() + File.separator + "plugin" + File.separator + "package";
            this.H = m() + File.separator + "plugin" + File.separator + Constants.TitleMenu.MENU_DOWNLOAD;
            this.I = m() + File.separator + "plugin" + File.separator + "tmp";
            this.J = m() + File.separator + "plugin" + File.separator + "install" + File.separator + h;
            this.K = m() + File.separator + "plugin" + File.separator + "install" + File.separator + i;
            this.L = m() + File.separator + "plugin" + File.separator + "install" + File.separator + "rn";
            StringBuilder sb = new StringBuilder();
            sb.append(this.L);
            sb.append("_temp");
            this.M = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(m());
            sb2.append(f);
            this.N = sb2.toString();
            this.O = m() + D;
            PluginSetting.a(this.C, m());
            this.P = Environment.getExternalStorageDirectory().getPath() + File.separator + "SmartHome" + File.separator + "plugin";
            StringBuilder sb3 = new StringBuilder();
            sb3.append("plugin");
            sb3.append(File.separator);
            sb3.append("package");
            this.Q = sb3.toString();
            this.R = m() + File.separator + "plugin" + File.separator + "install" + File.separator + "libs";
            this.S = m() + File.separator + "plugin" + File.separator + "install" + File.separator + ShareConstants.o;
            this.l = new Handler(Looper.getMainLooper());
            this.av = this.C.getPackageManager();
            this.aw = this.C.getSharedPreferences(ag, 0);
            this.ax = new File(m(), ah);
            this.ay = new File(m(), ai);
            this.az = new File(m(), aj);
            this.aA = this.C.getSharedPreferences(aj, 0);
            this.aB = new PackageListener();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            this.C.registerReceiver(this.aB, intentFilter);
            this.m = new MessageHandlerThread("PluginWorker");
            this.m.start();
            this.n = new WorkerHandler(this.m.getLooper());
            o();
            p();
            q();
            H();
            I();
            boolean r2 = r();
            J();
            N();
            O();
            if (AccountManager.a().l()) {
                R();
            }
            S();
            a(r2);
            UpdateAllOperation updateAllOperation = new UpdateAllOperation();
            updateAllOperation.f14674a = false;
            updateAllOperation.b = new UpdateAllCallbackInternal() {
                public void a() {
                }

                public void b() {
                }
            };
            this.n.sendMessageDelayed(this.n.obtainMessage(5, updateAllOperation), 120000);
            if (this.aE.size() == 0 || this.o.size() == 0) {
                l("end init mDeviceInfoMap:" + this.aE.size() + " mDeveloperInfoMap:" + this.aF.size() + " mPluginRecordMap:" + this.o.size() + " deviceListLastModify:" + PreferenceUtils.b(this.aw, al, 0) + " developerListLastModify:" + PreferenceUtils.b(this.aw, am, 0));
            }
        }
    }

    class WorkerHandler extends Handler {
        WorkerHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r1v2, types: [com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$ClearAllPluginConfigCallbackInternal] */
        /* JADX WARNING: type inference failed for: r1v12, types: [com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$CancelPluginDownloadCallbackInternal] */
        /* JADX WARNING: type inference failed for: r1v17 */
        /* JADX WARNING: type inference failed for: r1v18 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r5) {
            /*
                r4 = this;
                int r0 = r5.what
                r1 = 0
                switch(r0) {
                    case 2: goto L_0x0196;
                    case 3: goto L_0x0185;
                    case 4: goto L_0x016d;
                    case 5: goto L_0x0159;
                    case 6: goto L_0x0149;
                    case 7: goto L_0x0006;
                    case 8: goto L_0x0006;
                    case 9: goto L_0x0133;
                    case 10: goto L_0x011a;
                    case 11: goto L_0x0103;
                    case 12: goto L_0x00ec;
                    case 13: goto L_0x00c2;
                    case 14: goto L_0x00ad;
                    case 15: goto L_0x009a;
                    case 16: goto L_0x0087;
                    case 17: goto L_0x0070;
                    case 18: goto L_0x005b;
                    case 19: goto L_0x004f;
                    case 20: goto L_0x0043;
                    case 21: goto L_0x0037;
                    case 22: goto L_0x0031;
                    case 23: goto L_0x002a;
                    case 24: goto L_0x0019;
                    case 25: goto L_0x0008;
                    default: goto L_0x0006;
                }
            L_0x0006:
                goto L_0x01b0
            L_0x0008:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof java.util.Map
                if (r0 == 0) goto L_0x01b0
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                java.lang.Object r5 = r5.obj
                java.util.Map r5 = (java.util.Map) r5
                r0.a((java.util.Map<java.lang.String, java.lang.Object>) r5)
                goto L_0x01b0
            L_0x0019:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask
                if (r0 == 0) goto L_0x01b0
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$RnSdkTask r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r5
                r0.c((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r5)
                goto L_0x01b0
            L_0x002a:
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r5 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                boolean unused = r5.r()
                goto L_0x01b0
            L_0x0031:
                java.lang.Object r5 = r5.obj
                boolean r5 = r5 instanceof java.lang.String
                goto L_0x01b0
            L_0x0037:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof java.lang.String
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                java.lang.String r5 = (java.lang.String) r5
                goto L_0x01b0
            L_0x0043:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.WaitingInstallApkPluginRecord
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$WaitingInstallApkPluginRecord r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.WaitingInstallApkPluginRecord) r5
                goto L_0x01b0
            L_0x004f:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof java.lang.String
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                java.lang.String r5 = (java.lang.String) r5
                goto L_0x01b0
            L_0x005b:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$Operation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BaseCallbackInternal r5 = r5.c
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$DebugPackageCallbackInternal r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DebugPackageCallbackInternal) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r0.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DebugPackageCallbackInternal) r5)
                goto L_0x01b0
            L_0x0070:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$Operation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation) r5
                com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r0 = r5.b
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BaseCallbackInternal r5 = r5.c
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$DebugPackageCallbackInternal r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DebugPackageCallbackInternal) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r1 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r1.a((com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r0, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DebugPackageCallbackInternal) r5)
                goto L_0x01b0
            L_0x0087:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DumpPluginOperation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$DumpPluginOperation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DumpPluginOperation) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$DumpPluginCallbackInternal r5 = r5.f14666a
                r0.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DumpPluginCallbackInternal) r5)
                goto L_0x01b0
            L_0x009a:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.GetAutoUpdateOperation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$GetAutoUpdateOperation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.GetAutoUpdateOperation) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$GetAutoUpdateCallbackInternal r5 = r5.f14667a
                r0.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.GetAutoUpdateCallbackInternal) r5)
                goto L_0x01b0
            L_0x00ad:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.SetAutoUpdateOperation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$SetAutoUpdateOperation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.SetAutoUpdateOperation) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                boolean r1 = r5.f14673a
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$SetAutoUpdateCallbackInternal r5 = r5.b
                r0.a((boolean) r1, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.SetAutoUpdateCallbackInternal) r5)
                goto L_0x01b0
            L_0x00c2:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$Operation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation) r5
                com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r5.f14668a
                java.lang.Object r2 = r5.d
                boolean r2 = r2 instanceof com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask
                if (r2 == 0) goto L_0x00d9
                java.lang.Object r2 = r5.d
                com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask r2 = (com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask) r2
                goto L_0x00da
            L_0x00d9:
                r2 = r1
            L_0x00da:
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BaseCallbackInternal r3 = r5.c
                boolean r3 = r3 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.CancelPluginDownloadCallbackInternal
                if (r3 == 0) goto L_0x00e5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BaseCallbackInternal r5 = r5.c
                r1 = r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$CancelPluginDownloadCallbackInternal r1 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.CancelPluginDownloadCallbackInternal) r1
            L_0x00e5:
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r5 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r5.a((com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r0, (com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask) r2, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.CancelPluginDownloadCallbackInternal) r1)
                goto L_0x01b0
            L_0x00ec:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateOperation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$UpdateOperation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateOperation) r5
                com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r5.f14676a
                boolean r1 = r5.b
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$UpdateCallbackInternal r5 = r5.c
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r2 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r2.a((com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r0, (boolean) r1, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateCallbackInternal) r5)
                goto L_0x01b0
            L_0x0103:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$Operation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation) r5
                com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r5.f14668a
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BaseCallbackInternal r5 = r5.c
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$LoadPluginCallbackInternal r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.LoadPluginCallbackInternal) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r1 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r1.a((com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r0, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.LoadPluginCallbackInternal) r5)
                goto L_0x01b0
            L_0x011a:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$Operation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation) r5
                com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r5.f14668a
                com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r1 = r5.b
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BaseCallbackInternal r5 = r5.c
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$InstallCallbackInternel r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.InstallCallbackInternel) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r2 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r2.a((com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r0, (com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r1, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.InstallCallbackInternel) r5)
                goto L_0x01b0
            L_0x0133:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$Operation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.Operation) r5
                com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r5.f14668a
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BaseCallbackInternal r5 = r5.c
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PluginDownloadCallbackInternal r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadCallbackInternal) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r1 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r1.a((com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r0, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadCallbackInternal) r5)
                goto L_0x01b0
            L_0x0149:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof java.util.List
                if (r0 == 0) goto L_0x01b0
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this     // Catch:{ Exception -> 0x01b0 }
                java.lang.Object r5 = r5.obj     // Catch:{ Exception -> 0x01b0 }
                java.util.List r5 = (java.util.List) r5     // Catch:{ Exception -> 0x01b0 }
                r0.a((java.util.List<java.lang.String>) r5)     // Catch:{ Exception -> 0x01b0 }
                goto L_0x01b0
            L_0x0159:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateAllOperation
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$UpdateAllOperation r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateAllOperation) r5
                boolean r0 = r5.f14674a
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$UpdateAllCallbackInternal r5 = r5.b
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r1 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r1.a((boolean) r0, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateAllCallbackInternal) r5)
                goto L_0x01b0
            L_0x016d:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof android.util.Pair
                if (r0 == 0) goto L_0x01b0
                java.lang.Object r5 = r5.obj
                android.util.Pair r5 = (android.util.Pair) r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r0 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                java.lang.Object r1 = r5.first
                com.xiaomi.smarthome.frame.server_compact.ServerBean r1 = (com.xiaomi.smarthome.frame.server_compact.ServerBean) r1
                java.lang.Object r5 = r5.second
                com.xiaomi.smarthome.frame.server_compact.ServerBean r5 = (com.xiaomi.smarthome.frame.server_compact.ServerBean) r5
                r0.b((com.xiaomi.smarthome.frame.server_compact.ServerBean) r1, (com.xiaomi.smarthome.frame.server_compact.ServerBean) r5)
                goto L_0x01b0
            L_0x0185:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.ClearAllPluginConfigCallbackInternal
                if (r0 == 0) goto L_0x0190
                java.lang.Object r5 = r5.obj
                r1 = r5
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$ClearAllPluginConfigCallbackInternal r1 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.ClearAllPluginConfigCallbackInternal) r1
            L_0x0190:
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r5 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r5.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.ClearAllPluginConfigCallbackInternal) r1)
                goto L_0x01b0
            L_0x0196:
                java.lang.Object r0 = r5.obj
                boolean r0 = r0 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateConfigParam
                if (r0 == 0) goto L_0x01aa
                java.lang.Object r5 = r5.obj
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$UpdateConfigParam r5 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateConfigParam) r5
                boolean r0 = r5.f14675a
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$UpdateConfigCallbackInternal r5 = r5.b
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r1 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r1.a((boolean) r0, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateConfigCallbackInternal) r5)
                goto L_0x01b0
            L_0x01aa:
                com.xiaomi.smarthome.core.server.internal.plugin.PluginManager r5 = com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.this
                r0 = 0
                r5.a((boolean) r0, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.UpdateConfigCallbackInternal) r1)
            L_0x01b0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.WorkerHandler.handleMessage(android.os.Message):void");
        }
    }

    private static class PackageRawInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f14670a;
        public long b;
        public int c;
        public String d;
        public String e;
        public List<String> f;
        public boolean g;
        public boolean h;

        private PackageRawInfo() {
            this.f = new ArrayList();
            this.h = true;
        }

        public String a() {
            return "PackageRawInfo[" + "version:" + this.f14670a + " " + "developerId:" + this.b + " " + "minApiLevel:" + this.c + " " + "platform:" + this.d + " " + "packageName:" + this.e + " " + "modelList:" + this.f + " " + "mIsSupportWidget:" + this.g + " " + "mIsSupportAppAV:" + this.h + " " + Operators.ARRAY_END_STR;
        }
    }

    private class BaseCallbackInternal {
        private BaseCallbackInternal() {
        }
    }

    private abstract class DebugPackageCallbackInternal extends BaseCallbackInternal {
        /* access modifiers changed from: package-private */
        public abstract void a();

        /* access modifiers changed from: package-private */
        public abstract void a(String str);

        /* access modifiers changed from: package-private */
        public abstract void b();

        /* access modifiers changed from: package-private */
        public abstract void b(String str);

        private DebugPackageCallbackInternal() {
            super();
        }
    }

    private class InstallCallbackInternel extends BaseCallbackInternal {
        /* access modifiers changed from: package-private */
        public void a(PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
        }

        /* access modifiers changed from: package-private */
        public void b(PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
        }

        /* access modifiers changed from: package-private */
        public void c(PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
        }

        /* access modifiers changed from: package-private */
        public void d(PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
        }

        private InstallCallbackInternel() {
            super();
        }
    }

    private class CancelPluginDownloadCallbackInternal extends BaseCallbackInternal {
        public void a(PluginRecord pluginRecord) {
        }

        public void b(PluginRecord pluginRecord) {
        }

        private CancelPluginDownloadCallbackInternal() {
            super();
        }
    }

    private static class Operation {

        /* renamed from: a  reason: collision with root package name */
        PluginRecord f14668a;
        PluginPackageInfo b;
        BaseCallbackInternal c;
        Object d;

        private Operation() {
        }
    }

    private static class WaitingInstallApkPluginRecord {

        /* renamed from: a  reason: collision with root package name */
        PluginRecord f14677a;
        InstallCallbackInternel b;

        private WaitingInstallApkPluginRecord() {
        }
    }

    public static class PluginDownloadTaskInternal {

        /* renamed from: a  reason: collision with root package name */
        private static Random f14671a = new Random();
        private int b = f14671a.nextInt(Integer.MAX_VALUE);
        /* access modifiers changed from: private */
        public long c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public String e;
        private String f;
        private String g;
        private long h;
        private boolean i;
        private PluginRecord j;
        private HttpAsyncHandle k;
        private DownloadTaskCallbackInternal l;
        private Map<String, Object> m = new HashMap();

        public static abstract class DownloadTaskCallbackInternal {
            /* access modifiers changed from: package-private */
            public abstract void a(PluginDownloadTaskInternal pluginDownloadTaskInternal);

            /* access modifiers changed from: package-private */
            public abstract void a(PluginDownloadTaskInternal pluginDownloadTaskInternal, float f);

            /* access modifiers changed from: package-private */
            public abstract void b(PluginDownloadTaskInternal pluginDownloadTaskInternal);

            /* access modifiers changed from: package-private */
            public abstract void c(PluginDownloadTaskInternal pluginDownloadTaskInternal);

            /* access modifiers changed from: package-private */
            public abstract void d(PluginDownloadTaskInternal pluginDownloadTaskInternal);

            /* access modifiers changed from: package-private */
            public abstract void e(PluginDownloadTaskInternal pluginDownloadTaskInternal);
        }

        PluginDownloadTaskInternal() {
        }

        /* access modifiers changed from: package-private */
        public synchronized <T> T a(String str) {
            return this.m.get(str);
        }

        /* access modifiers changed from: package-private */
        public synchronized <T> T a(String str, T t) {
            T t2 = this.m.get(str);
            if (t2 == null) {
                return t;
            }
            return t2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void b(String str, Object obj) {
            this.m.put(str, obj);
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(long j2) {
            this.c = j2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void b(long j2) {
            this.d = j2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void b(String str) {
            this.e = str;
        }

        /* access modifiers changed from: package-private */
        public synchronized void c(String str) {
            this.f = str;
        }

        /* access modifiers changed from: package-private */
        public synchronized void d(String str) {
            this.g = str;
        }

        /* access modifiers changed from: package-private */
        public synchronized void c(long j2) {
            this.h = j2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(boolean z) {
            this.i = z;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(PluginRecord pluginRecord) {
            this.j = pluginRecord;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(HttpAsyncHandle httpAsyncHandle) {
            this.k = httpAsyncHandle;
        }

        /* access modifiers changed from: package-private */
        public synchronized void a(DownloadTaskCallbackInternal downloadTaskCallbackInternal) {
            this.l = downloadTaskCallbackInternal;
        }

        /* access modifiers changed from: package-private */
        public synchronized int a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public synchronized long b() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public synchronized long c() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public synchronized String d() {
            return this.e;
        }

        /* access modifiers changed from: package-private */
        public synchronized String e() {
            return this.f;
        }

        /* access modifiers changed from: package-private */
        public synchronized String f() {
            return this.g;
        }

        /* access modifiers changed from: package-private */
        public synchronized long g() {
            return this.h;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean h() {
            return this.i;
        }

        /* access modifiers changed from: package-private */
        public synchronized PluginRecord i() {
            return this.j;
        }

        /* access modifiers changed from: package-private */
        public synchronized HttpAsyncHandle j() {
            return this.k;
        }

        /* access modifiers changed from: package-private */
        public synchronized DownloadTaskCallbackInternal k() {
            return this.l;
        }

        public void d(long j2) {
            b("rnSdkApiLevel", Long.valueOf(j2));
        }

        /* access modifiers changed from: package-private */
        public synchronized PluginDownloadTask l() {
            PluginDownloadTask pluginDownloadTask;
            pluginDownloadTask = new PluginDownloadTask();
            pluginDownloadTask.f13989a = this.d;
            pluginDownloadTask.b = this.b;
            pluginDownloadTask.c = this.e;
            return pluginDownloadTask;
        }
    }

    private class FileDownloadCallbackInternal extends BaseCallbackInternal {
        /* access modifiers changed from: package-private */
        public void a() {
        }

        /* access modifiers changed from: package-private */
        public void a(float f) {
        }

        /* access modifiers changed from: package-private */
        public void a(HttpAsyncHandle httpAsyncHandle) {
        }

        /* access modifiers changed from: package-private */
        public void b() {
        }

        /* access modifiers changed from: package-private */
        public void b(HttpAsyncHandle httpAsyncHandle) {
        }

        /* access modifiers changed from: package-private */
        public void c() {
        }

        private FileDownloadCallbackInternal() {
            super();
        }
    }

    private class PackageListener extends BroadcastReceiver {
        private PackageListener() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && PluginManager.this.n != null) {
                if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                    PluginManager.this.n.obtainMessage(19, intent.getDataString().substring("package:".length())).sendToTarget();
                } else if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
                    PluginManager.this.n.obtainMessage(21, intent.getDataString().substring("package:".length())).sendToTarget();
                } else if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                    PluginManager.this.n.obtainMessage(22, intent.getDataString().substring("package:".length())).sendToTarget();
                }
            }
        }
    }

    private String m() {
        if (TextUtils.isEmpty(this.bq)) {
            this.bq = FileUtils.a(this.C);
        }
        return this.bq;
    }

    public String c() {
        return this.R;
    }

    public String d() {
        return this.S;
    }

    public String e() {
        File dir = this.C.getDir(ShareConstants.q, 0);
        return dir.getAbsolutePath() + File.separator + "plugin";
    }

    public String a(long j2, long j3) {
        File dir = this.C.getDir(ShareConstants.q, 0);
        return dir.getAbsolutePath() + File.separator + "plugin" + File.separator + j2;
    }

    public String a(long j2, long j3, String str) {
        return str + File.separator + j2 + File.separator + j3;
    }

    public void b(long j2, long j3) {
        if (PluginSetting.b(j3)) {
            File dir = this.C.getDir(ShareConstants.q, 0);
            new File(dir.getAbsolutePath() + File.separator + "plugin" + File.separator + j2 + File.separator + j3 + ShareConstants.w).delete();
        }
    }

    public void a(String str, long j2, long j3) {
        String str2 = str + File.separator + j2 + File.separator + j3;
        File file = new File(str2);
        if (file.exists() && file.isDirectory()) {
            FileUtils.e(str2);
        }
    }

    /* access modifiers changed from: private */
    public OkHttpClient n() {
        if (this.aC == null) {
            OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().dispatcher(new Dispatcher(CommonApplication.getNetworkThreadPool())).connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(new CookieManager()));
            JoinPoint a2 = Factory.a(bE, (Object) this, (Object) cookieJar);
            this.aC = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, cookieJar, a2}).linkClosureAndJoinPoint(4112));
        }
        return this.aC;
    }

    static final OkHttpClient a(PluginManager pluginManager, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    private void o() {
        File filesDir = this.C.getFilesDir();
        if (filesDir != null) {
            String str = filesDir.getParent() + File.separator + "shared_prefs" + File.separator + af + ".xml";
            if (FileUtils.f(str)) {
                PreferenceUtils.a(this.C.getSharedPreferences(af, 0));
                FileUtils.d(str);
            }
        }
    }

    private void p() {
        FileUtils.e(c());
        String[] list = new File(this.K).list();
        if (list != null && list.length > 0) {
            for (String str : list) {
                if (!TextUtils.isEmpty(str) && str.endsWith(".apk")) {
                    FileUtils.d(this.K + File.separator + str);
                }
            }
        }
        File dir = this.C.getDir(ShareConstants.q, 0);
        String absolutePath = dir.getAbsolutePath();
        String[] list2 = dir.list();
        if (list2 != null && list2.length > 0) {
            for (String str2 : list2) {
                if (!TextUtils.isEmpty(str2) && str2.endsWith(ShareConstants.w)) {
                    FileUtils.d(absolutePath + File.separator + str2);
                }
            }
        }
    }

    private void q() {
        this.aD = PreferenceUtils.a(this.aw, as, true);
    }

    public void f() {
        this.n.obtainMessage(23, (Object) null).sendToTarget();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0308  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean r() {
        /*
            r11 = this;
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo> r0 = r11.aE
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x001e
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r0 = r11.aF
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0012
            goto L_0x001e
        L_0x0012:
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = "login"
            java.lang.String r2 = "PluginManager initPluginConfigFromLocalInWorkThread 1122"
            android.util.Log.d(r0, r2)
        L_0x001d:
            return r1
        L_0x001e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "PluginManager.initPluginConfigFromLocal mDeviceInfoMap:"
            r0.append(r2)
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo> r2 = r11.aE
            int r2 = r2.size()
            r0.append(r2)
            java.lang.String r2 = " mDeveloperInfoMap:"
            r0.append(r2)
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r2 = r11.aF
            int r2 = r2.size()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r11.l((java.lang.String) r0)
            android.content.SharedPreferences r0 = r11.aw
            java.lang.String r2 = "last_check_version"
            com.xiaomi.smarthome.library.commonapi.SystemApi r3 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()
            android.content.Context r4 = r11.C
            int r3 = r3.e(r4)
            com.xiaomi.smarthome.core.server.internal.plugin.util.PreferenceUtils.a((android.content.SharedPreferences) r0, (java.lang.String) r2, (int) r3)
            android.content.SharedPreferences r0 = r11.aw
            java.lang.String r2 = "last_check_update"
            r3 = 0
            com.xiaomi.smarthome.core.server.internal.plugin.util.PreferenceUtils.a((android.content.SharedPreferences) r0, (java.lang.String) r2, (long) r3)
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u
            if (r0 == 0) goto L_0x006b
            java.lang.String r0 = "login"
            java.lang.String r2 = "PluginManager initPluginConfigFromLocalInWorkThread 996"
            android.util.Log.d(r0, r2)
        L_0x006b:
            java.lang.String r0 = "PluginManager.initPluginConfigFromLocal simplified_chinese"
            r11.l((java.lang.String) r0)
            r0 = 0
            r2 = 1
            com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager r3 = com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager.a()     // Catch:{ Exception -> 0x00c4 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r3 = r3.d()     // Catch:{ Exception -> 0x00c4 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c4 }
            r4.<init>()     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r5 = "plugin_config/plugin_config_"
            r4.append(r5)     // Catch:{ Exception -> 0x00c4 }
            if (r3 != 0) goto L_0x0089
            java.lang.String r3 = ""
            goto L_0x008b
        L_0x0089:
            java.lang.String r3 = r3.f1530a     // Catch:{ Exception -> 0x00c4 }
        L_0x008b:
            r4.append(r3)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r3 = ".json"
            r4.append(r3)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r3 = r4.toString()     // Catch:{ Exception -> 0x00c4 }
            android.content.Context r4 = r11.C     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r3 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a((android.content.Context) r4, (java.lang.String) r3)     // Catch:{ Exception -> 0x00c4 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x00c4 }
            if (r4 == 0) goto L_0x00a4
            return r2
        L_0x00a4:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x00c4 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r3 = "result"
            org.json.JSONObject r3 = r4.optJSONObject(r3)     // Catch:{ Exception -> 0x00c4 }
            com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginConfigInfoNewResult r3 = com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginConfigInfoNewResult.a(r3)     // Catch:{ Exception -> 0x00c4 }
            boolean r4 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ Exception -> 0x00bf }
            if (r4 == 0) goto L_0x00fd
            java.lang.String r4 = "login"
            java.lang.String r5 = "PluginManager initPluginConfigFromLocalInWorkThread 1009"
            android.util.Log.d(r4, r5)     // Catch:{ Exception -> 0x00bf }
            goto L_0x00fd
        L_0x00bf:
            r4 = move-exception
            r10 = r4
            r4 = r3
            r3 = r10
            goto L_0x00c6
        L_0x00c4:
            r3 = move-exception
            r4 = r0
        L_0x00c6:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "PluginManager.initPluginConfigFromLocal plugin exception:"
            r5.append(r6)
            java.lang.String r6 = android.util.Log.getStackTraceString(r3)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r11.l((java.lang.String) r5)
            boolean r5 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u
            if (r5 == 0) goto L_0x00fc
            java.lang.String r5 = "login"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "PluginManager initPluginConfigFromLocalInWorkThread "
            r6.append(r7)
            java.lang.String r3 = r3.getMessage()
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            android.util.Log.d(r5, r3)
        L_0x00fc:
            r3 = r4
        L_0x00fd:
            if (r3 == 0) goto L_0x02a8
            com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginConfigDeviceInfo r4 = r3.f14695a
            com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager r5 = com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager.a()
            java.util.Locale r5 = r5.g()
            if (r5 != 0) goto L_0x010f
            java.util.Locale r5 = java.util.Locale.getDefault()
        L_0x010f:
            java.util.Locale r6 = java.util.Locale.SIMPLIFIED_CHINESE
            java.lang.String r6 = r6.getCountry()
            java.lang.String r7 = r5.getCountry()
            boolean r6 = android.text.TextUtils.equals(r6, r7)
            if (r6 == 0) goto L_0x0224
            java.util.Locale r6 = java.util.Locale.SIMPLIFIED_CHINESE
            java.lang.String r6 = r6.getLanguage()
            java.lang.String r7 = r5.getLanguage()
            boolean r6 = android.text.TextUtils.equals(r6, r7)
            if (r6 == 0) goto L_0x0224
            if (r4 == 0) goto L_0x021e
            java.util.List<com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult> r5 = r4.c
            java.util.Iterator r5 = r5.iterator()
        L_0x0137:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x01dc
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult r6 = (com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult) r6
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo> r7 = r11.aE
            java.lang.String r8 = r6.c
            boolean r7 = r7.containsKey(r8)
            if (r7 == 0) goto L_0x015b
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo> r7 = r11.aE
            java.lang.String r8 = r6.c
            java.lang.Object r7 = r7.get(r8)
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r7 = (com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r7
            com.xiaomi.smarthome.core.server.internal.plugin.util.PluginUtils.a((com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult) r6, (com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r7)
            goto L_0x0166
        L_0x015b:
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r7 = new com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo
            r7.<init>()
            com.xiaomi.smarthome.core.server.internal.plugin.util.PluginUtils.a((com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult) r6, (com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r7)
            r11.a((com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r7)
        L_0x0166:
            int r6 = r7.j()
            if (r6 <= 0) goto L_0x01c0
            int r6 = r7.j()
            com.xiaomi.smarthome.library.commonapi.SystemApi r8 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()
            android.content.Context r9 = r11.C
            int r8 = r8.e(r9)
            if (r6 <= r8) goto L_0x017d
            goto L_0x01c0
        L_0x017d:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r6 = r11.o
            java.lang.String r8 = r7.c()
            java.lang.Object r6 = r6.get(r8)
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r6 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r6
            if (r6 != 0) goto L_0x0197
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r6 = new com.xiaomi.smarthome.core.entity.plugin.PluginRecord
            r6.<init>()
            r6.a((com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r7)
            r11.b((com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r6)
            goto L_0x019a
        L_0x0197:
            r6.a((com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r7)
        L_0x019a:
            java.util.Map<java.lang.String, java.util.regex.Pattern> r6 = r11.aS
            java.lang.String r8 = r7.c()
            r6.remove(r8)
            java.lang.String r6 = r7.r()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x0137
            java.util.Map<java.lang.String, java.util.regex.Pattern> r6 = r11.aS
            java.lang.String r8 = r7.c()
            java.lang.String r7 = r7.r()
            java.util.regex.Pattern r7 = java.util.regex.Pattern.compile(r7)
            r6.put(r8, r7)
            goto L_0x0137
        L_0x01c0:
            java.lang.String r6 = r7.c()
            r11.f((java.lang.String) r6)
            java.lang.String r6 = r7.r()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x0137
            java.util.Map<java.lang.String, java.util.regex.Pattern> r6 = r11.aS
            java.lang.String r7 = r7.c()
            r6.remove(r7)
            goto L_0x0137
        L_0x01dc:
            r11.F()
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo> r5 = r11.aE
            boolean r5 = r11.b((java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo>) r5)
            if (r5 == 0) goto L_0x01f0
            android.content.SharedPreferences r5 = r11.aw
            java.lang.String r6 = "device_list_last_modify"
            long r7 = r4.b
            com.xiaomi.smarthome.core.server.internal.plugin.util.PreferenceUtils.a((android.content.SharedPreferences) r5, (java.lang.String) r6, (long) r7)
        L_0x01f0:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r6 = r11.o
            int r6 = r6.size()
            r5.append(r6)
            java.lang.String r6 = " plugin device info from asset    DeviceListLastModify:"
            r5.append(r6)
            long r6 = r4.b
            r5.append(r6)
            java.lang.String r4 = "  DeviceInfoMap:"
            r5.append(r4)
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo> r4 = r11.aE
            int r4 = r4.size()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r11.l((java.lang.String) r4)
            goto L_0x023c
        L_0x021e:
            java.lang.String r4 = "plugin device info from asset  result.deviceInfo is null"
            r11.l((java.lang.String) r4)
            goto L_0x023c
        L_0x0224:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "PluginManager.initPluginConfigFromLocal locale:"
            r4.append(r6)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r11.l((java.lang.String) r4)
        L_0x023c:
            com.xiaomi.smarthome.core.server.internal.plugin.entity.PluginConfigDeveloperInfo r3 = r3.b
            if (r3 == 0) goto L_0x028d
            java.util.List<com.xiaomi.smarthome.core.server.internal.plugin.entity.DeveloperResult> r4 = r3.c
            java.util.Iterator r4 = r4.iterator()
        L_0x0246:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x027c
            java.lang.Object r5 = r4.next()
            com.xiaomi.smarthome.core.server.internal.plugin.entity.DeveloperResult r5 = (com.xiaomi.smarthome.core.server.internal.plugin.entity.DeveloperResult) r5
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r6 = r11.aF
            long r7 = r5.f14690a
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            java.lang.Object r6 = r6.get(r7)
            com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo r6 = (com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo) r6
            if (r6 != 0) goto L_0x0278
            com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo r6 = new com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo
            r6.<init>()
            com.xiaomi.smarthome.core.server.internal.plugin.util.PluginUtils.a((com.xiaomi.smarthome.core.server.internal.plugin.entity.DeveloperResult) r5, (com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo) r6)
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r5 = r11.aF
            long r7 = r6.b()
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            r5.put(r7, r6)
            goto L_0x0246
        L_0x0278:
            com.xiaomi.smarthome.core.server.internal.plugin.util.PluginUtils.a((com.xiaomi.smarthome.core.server.internal.plugin.entity.DeveloperResult) r5, (com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo) r6)
            goto L_0x0246
        L_0x027c:
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r4 = r11.aF
            boolean r4 = r11.c((java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo>) r4)
            if (r4 == 0) goto L_0x028d
            android.content.SharedPreferences r4 = r11.aw
            java.lang.String r5 = "developer_list_last_modify"
            long r6 = r3.b
            com.xiaomi.smarthome.core.server.internal.plugin.util.PreferenceUtils.a((android.content.SharedPreferences) r4, (java.lang.String) r5, (long) r6)
        L_0x028d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r4 = r11.aF
            int r4 = r4.size()
            r3.append(r4)
            java.lang.String r4 = "PluginManager.initPluginConfigFromLocal developer device info from asset"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r11.l((java.lang.String) r3)
            goto L_0x02d0
        L_0x02a8:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "%d plugin device info from asset"
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r6 = r11.o
            int r6 = r6.size()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5[r1] = r6
            java.lang.String r4 = java.lang.String.format(r4, r5)
            r3.append(r4)
            java.lang.String r4 = "    result is null"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r11.l((java.lang.String) r3)
        L_0x02d0:
            java.lang.String r3 = "%d PluginManager.initPluginConfigFromLocal plugin device info from asset"
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r5 = r11.o
            int r5 = r5.size()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4[r1] = r5
            java.lang.String r1 = java.lang.String.format(r3, r4)
            r11.l((java.lang.String) r1)
            boolean r1 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u
            if (r1 == 0) goto L_0x02f2
            java.lang.String r1 = "login"
            java.lang.String r3 = "PluginManager initPluginConfigFromLocalInWorkThread 1113"
            android.util.Log.d(r1, r3)
        L_0x02f2:
            r11.a((boolean) r2, (boolean) r2, (java.lang.String) r0)
            android.content.SharedPreferences r0 = r11.aw
            android.content.SharedPreferences$Editor r0 = r0.edit()
            java.lang.String r1 = "device_list_local"
            android.content.SharedPreferences$Editor r0 = r0.putBoolean(r1, r2)
            r0.apply()
            boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u
            if (r0 == 0) goto L_0x030f
            java.lang.String r0 = "login"
            java.lang.String r1 = "PluginManager initPluginConfigFromLocalInWorkThread 1069"
            android.util.Log.d(r0, r1)
        L_0x030f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.r():boolean");
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, UpdateConfigCallbackInternal updateConfigCallbackInternal) {
        boolean z3;
        UpdateConfigCallbackInternal updateConfigCallbackInternal2 = updateConfigCallbackInternal;
        if (!AccountManager.a().l()) {
            Log.i("PluginManager", "updatePluginConfigInternal no login");
            if (updateConfigCallbackInternal2 != null) {
                updateConfigCallbackInternal2.a(new CoreError(-1, "not login"));
                return;
            }
            return;
        }
        if (z2) {
            boolean a2 = PreferenceUtils.a(this.aw, ak, false);
            if (a2 && updateConfigCallbackInternal2 != null) {
                updateConfigCallbackInternal2.a(true, false);
            }
            z3 = a2;
        } else {
            z3 = false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long b2 = PreferenceUtils.b(this.aw, aq, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date(currentTimeMillis));
        String format2 = simpleDateFormat.format(new Date(b2));
        int c2 = PreferenceUtils.c(this.aw, ar, 0);
        int e2 = SystemApi.a().e(this.C);
        if (e2 != c2 || this.aE.size() == 0 || this.aF.size() == 0) {
            PreferenceUtils.a(this.aw, al, 0);
            PreferenceUtils.a(this.aw, am, 0);
        }
        if (z2 || !format.equals(format2) || e2 != c2) {
            PreferenceUtils.a(this.aw, aq, currentTimeMillis);
            PreferenceUtils.a(this.aw, ar, e2);
            long b3 = PreferenceUtils.b(this.aw, al, 0);
            long b4 = PreferenceUtils.b(this.aw, am, 0);
            int e3 = SystemApi.a().e(this.C);
            String l2 = SystemApi.a().l();
            Log.i("PluginManager", "Start Update All Plugin Record");
            l("Start Update All Plugin Record - " + this.o.size() + " deviceListLastModify:" + b3 + "  lastCheckVersion:" + c2);
            PluginSmartHomeApi a3 = PluginSmartHomeApi.a();
            final UpdateConfigCallbackInternal updateConfigCallbackInternal3 = updateConfigCallbackInternal;
            final long j2 = b3;
            long j3 = b3;
            final long j4 = b4;
            final long j5 = b2;
            final int i2 = c2;
            PluginSmartHomeApi pluginSmartHomeApi = a3;
            pluginSmartHomeApi.a(this.C, z3, j3, b4, e3, l2, new CoreAsyncCallback<PluginConfigInfoNewResult, CoreError>() {
                public void a(PluginConfigInfoNewResult pluginConfigInfoNewResult) {
                    PluginDeveloperInfo pluginDeveloperInfo;
                    PluginDeviceInfo pluginDeviceInfo;
                    List<DeveloperResult> list;
                    if (pluginConfigInfoNewResult != null) {
                        if (pluginConfigInfoNewResult.f14695a != null && pluginConfigInfoNewResult.a()) {
                            PluginManager.this.u();
                            PluginManager.this.v();
                            List<String> a2 = PluginUtils.a(PluginManager.this.o, pluginConfigInfoNewResult.f14695a.c);
                            if (a2.size() != 0) {
                                for (String a3 : a2) {
                                    PluginManager.this.f(a3);
                                }
                            }
                            PluginManager.this.aS.clear();
                        }
                        if (pluginConfigInfoNewResult.b()) {
                            PluginManager.this.w();
                            PluginManager.this.x();
                            if (pluginConfigInfoNewResult.b != null) {
                                list = pluginConfigInfoNewResult.b.c;
                            } else {
                                list = new ArrayList<>();
                            }
                            HashSet hashSet = new HashSet();
                            for (DeveloperResult developerResult : list) {
                                if (PluginSetting.d(developerResult.f14690a)) {
                                    hashSet.add(Long.valueOf(developerResult.f14690a));
                                }
                            }
                            for (Map.Entry<String, PluginRecord> value : PluginManager.this.o.entrySet()) {
                                PluginRecord pluginRecord = (PluginRecord) value.getValue();
                                if (pluginRecord.l() && !hashSet.contains(Long.valueOf(pluginRecord.h().k()))) {
                                    PluginManager.this.g(pluginRecord.o());
                                }
                                if (pluginRecord.k() && !hashSet.contains(Long.valueOf(pluginRecord.i().k()))) {
                                    PluginManager.this.h(pluginRecord.o());
                                }
                            }
                        }
                        if (pluginConfigInfoNewResult.f14695a != null) {
                            try {
                                if (ServerCompact.o(PluginManager.this.C)) {
                                    pluginConfigInfoNewResult.f14695a.c.add(DeviceResult.a(new JSONObject("{ \"pd_id\": 66496, \"icon_on\": \"http:\\/\\/static.home.mi.com\\/app\\/image\\/get\\/file\\/developer_1523623045a2pi7x21.png\", \"icon_off\": \"http:\\/\\/static.home.mi.com\\/app\\/image\\/get\\/file\\/developer_1523623051kmyef9vg.png\", \"icon_offline\": \"http:\\/\\/static.home.mi.com\\/app\\/image\\/get\\/file\\/developer_15236230579yghzrn1.png\", \"icon_smartconfig\": \"\", \"icon_lock_screen_on\": \"http:\\/\\/static.home.mi.com\\/app\\/image\\/get\\/file\\/developer_1523623076laeljyts.png\", \"icon_lock_screen_off\": \"http:\\/\\/static.home.mi.com\\/app\\/image\\/get\\/file\\/developer_1523623084if101t4k.png\", \"icon_bluetooth_pairing\": \"\", \"icon_real\": \"http:\\/\\/static.home.mi.com\\/app\\/image\\/get\\/file\\/developer_1523623090oiz4jikf.png\", \"icon_guidepic\": \"\", \"icon_smartconfig_off\": \"\", \"icon_336\": \"\", \"pid\": 13, \"model\": \"lumi.sensor_smoke.acn01\", \"name\": \"烟雾报警器（NB-IOT 版本）\", \"name_smartconfig\": \"\", \"brand_name\": \"\", \"desc\": \"调光控制器（双色温）是基于Zigbee技术的双色温灯光控制设备，可控一个双色温LED调光灯或灯带，可实现256级亮度调整。\", \"subcategory_id\": 155, \"status\": 1, \"ios_status\": 1, \"model_regex\": \"\", \"min_app_version\": 1, \"cate_name\": \"家居安防\", \"inherit_id\": 0, \"change_time\": 1546595475, \"rank\": 0, \"sc_type\": 15, \"sc_status\": 1, \"sc_type_more\": [ ], \"sc_type_more_v2\": [ ], \"sc_failed\": 0, \"ios_sc_visible\": true, \"bind_confirm\": 0, \"fiveG_wifi\": 0, \"bt_rssi\": \"\", \"bt_bind_style\": 0, \"bt_match\": 0, \"bt_is_secure\": 0, \"bt_gateway\": 0, \"mesh_gateway\": 0, \"permission_control\": 0, \"wexin_share\": 0, \"ct_offline_enter\": 0, \"op_history\": 0, \"voice_control\": 0, \"voice_ctrl_ed\": 0, \"infrared_gateway\": 0, \"member_cnt\": 0, \"ios_asset\": 0, \"relations\": [ ], \"resolve_type\": 0 }")));
                                }
                            } catch (Throwable th) {
                                Log.e("PluginManager", AppMeasurement.Param.FATAL, th);
                            }
                            for (DeviceResult next : pluginConfigInfoNewResult.f14695a.c) {
                                if (PluginManager.this.aE.containsKey(next.c)) {
                                    pluginDeviceInfo = (PluginDeviceInfo) PluginManager.this.aE.get(next.c);
                                    PluginUtils.a(next, pluginDeviceInfo);
                                } else {
                                    pluginDeviceInfo = new PluginDeviceInfo();
                                    PluginUtils.a(next, pluginDeviceInfo);
                                    PluginManager.this.a(pluginDeviceInfo);
                                }
                                if (pluginDeviceInfo.j() <= 0 || pluginDeviceInfo.j() > SystemApi.a().e(PluginManager.this.C)) {
                                    PluginManager.this.f(pluginDeviceInfo.c());
                                    if (!TextUtils.isEmpty(pluginDeviceInfo.r())) {
                                        PluginManager.this.aS.remove(pluginDeviceInfo.c());
                                    }
                                } else {
                                    PluginRecord pluginRecord2 = PluginManager.this.o.get(pluginDeviceInfo.c());
                                    if (pluginRecord2 == null) {
                                        PluginRecord pluginRecord3 = new PluginRecord();
                                        pluginRecord3.a(pluginDeviceInfo);
                                        PluginManager.this.b(pluginRecord3);
                                    } else {
                                        pluginRecord2.a(pluginDeviceInfo);
                                    }
                                    PluginManager.this.aS.remove(pluginDeviceInfo.c());
                                    if (!TextUtils.isEmpty(pluginDeviceInfo.r())) {
                                        PluginManager.this.aS.put(pluginDeviceInfo.c(), Pattern.compile(pluginDeviceInfo.r()));
                                    }
                                }
                            }
                            PluginManager.this.B();
                            if (PluginManager.this.b((Map<String, PluginDeviceInfo>) PluginManager.this.aE)) {
                                PreferenceUtils.a(PluginManager.this.aw, PluginManager.al, pluginConfigInfoNewResult.f14695a.b);
                            }
                        }
                        if (pluginConfigInfoNewResult.b != null) {
                            for (DeveloperResult next2 : pluginConfigInfoNewResult.b.c) {
                                if (PluginManager.this.aF.containsKey(Long.valueOf(next2.f14690a))) {
                                    pluginDeveloperInfo = (PluginDeveloperInfo) PluginManager.this.aF.get(Long.valueOf(next2.f14690a));
                                    PluginUtils.a(next2, pluginDeveloperInfo);
                                } else {
                                    pluginDeveloperInfo = new PluginDeveloperInfo();
                                    PluginUtils.a(next2, pluginDeveloperInfo);
                                    PluginManager.this.a(pluginDeveloperInfo);
                                }
                                for (Map.Entry<String, PluginRecord> value2 : PluginManager.this.o.entrySet()) {
                                    PluginRecord pluginRecord4 = (PluginRecord) value2.getValue();
                                    if (pluginRecord4.l()) {
                                        PluginPackageInfo h = pluginRecord4.h();
                                        if (h.k() == pluginDeveloperInfo.b()) {
                                            pluginRecord4.a(pluginRecord4.d(), pluginRecord4.e(), h, pluginDeveloperInfo);
                                        }
                                    }
                                    if (pluginRecord4.k()) {
                                        PluginPackageInfo i = pluginRecord4.i();
                                        if (i.k() == pluginDeveloperInfo.b()) {
                                            pluginRecord4.b(pluginRecord4.f(), pluginRecord4.g(), i, pluginDeveloperInfo);
                                        }
                                    }
                                }
                            }
                            if (PluginManager.this.c((Map<Long, PluginDeveloperInfo>) PluginManager.this.aF)) {
                                PreferenceUtils.a(PluginManager.this.aw, PluginManager.am, pluginConfigInfoNewResult.b.b);
                            }
                        }
                        PluginManager.this.F();
                        PluginManager.this.S();
                        boolean z = true;
                        boolean z2 = pluginConfigInfoNewResult.f14695a == null || j2 == 0 || pluginConfigInfoNewResult.f14695a.b == 0 || j2 != pluginConfigInfoNewResult.f14695a.b;
                        if (!(j4 == 0 || pluginConfigInfoNewResult.b.b == 0 || j4 != pluginConfigInfoNewResult.b.b)) {
                            z = false;
                        }
                        PluginManager pluginManager = PluginManager.this;
                        pluginManager.l("finish Update All Plugin Record - " + PluginManager.this.o.size());
                        PluginManager.this.a(z2, z, (String) null);
                        if (updateConfigCallbackInternal3 != null) {
                            updateConfigCallbackInternal3.a(z2, z);
                        }
                        PreferenceUtils.b(PluginManager.this.aw, PluginManager.ak, false);
                    } else if (updateConfigCallbackInternal3 != null) {
                        updateConfigCallbackInternal3.a(new CoreError(-1, "result is null"));
                    }
                }

                public void a(CoreError coreError) {
                    PreferenceUtils.a(PluginManager.this.aw, PluginManager.aq, j5);
                    PreferenceUtils.a(PluginManager.this.aw, PluginManager.ar, i2);
                    if (updateConfigCallbackInternal3 != null) {
                        updateConfigCallbackInternal3.a(coreError);
                    }
                }
            });
            return;
        }
        Log.i("PluginManager", format + " no need update");
        if (updateConfigCallbackInternal2 != null) {
            updateConfigCallbackInternal2.a(new CoreError(-1, "no need update"));
        }
    }

    /* access modifiers changed from: private */
    public void a(ClearAllPluginConfigCallbackInternal clearAllPluginConfigCallbackInternal) {
        t();
        u();
        v();
        w();
        x();
        E();
        G();
        PreferenceUtils.a(this.aw, al, 0);
        PreferenceUtils.a(this.aw, am, 0);
        a(true, true, (String) null);
        if (clearAllPluginConfigCallbackInternal != null) {
            clearAllPluginConfigCallbackInternal.a();
        }
    }

    private void s() {
        FileUtils.e(e());
        FileUtils.e(d());
        FileUtils.e(this.K);
        FileUtils.e(this.L);
        y();
        FileUtils.e(this.H);
        z();
        l("clear all package");
    }

    private void t() {
        if (this.aw != null) {
            PreferenceUtils.a(this.aw);
        }
    }

    /* access modifiers changed from: private */
    public void b(ServerBean serverBean, ServerBean serverBean2) {
        a((ClearAllPluginConfigCallbackInternal) null);
        s();
        r();
    }

    /* access modifiers changed from: private */
    public void a(PluginDeviceInfo pluginDeviceInfo) {
        this.aE.put(pluginDeviceInfo.c(), pluginDeviceInfo);
    }

    private void b(PluginDeviceInfo pluginDeviceInfo) {
        this.aE.remove(pluginDeviceInfo.c());
    }

    /* access modifiers changed from: private */
    public void u() {
        this.aE.clear();
    }

    /* access modifiers changed from: private */
    public boolean b(Map<String, PluginDeviceInfo> map) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInt(SystemApi.a().e(this.C));
            int size = map.size();
            obtain.writeInt(size);
            for (Map.Entry<String, PluginDeviceInfo> value : map.entrySet()) {
                obtain.writeParcelable((Parcelable) value.getValue(), 0);
            }
            byte[] marshall = obtain.marshall();
            String dataMd5Digest = Coder.getDataMd5Digest(marshall);
            boolean a2 = FileUtils.a(this.ax, marshall);
            if (a2) {
                SharedPreferences sharedPreferences = this.aw;
                PreferenceUtils.a(sharedPreferences, an, dataMd5Digest + String.valueOf(size));
            }
            return a2;
        } finally {
            obtain.recycle();
        }
    }

    /* access modifiers changed from: private */
    public void v() {
        try {
            this.ax.delete();
        } catch (Throwable th) {
            Log.e("PluginManager", "clearDeviceListPrefInternal fail", th);
        }
    }

    /* access modifiers changed from: private */
    public void a(PluginDeveloperInfo pluginDeveloperInfo) {
        this.aF.put(Long.valueOf(pluginDeveloperInfo.b()), pluginDeveloperInfo);
    }

    private void b(PluginDeveloperInfo pluginDeveloperInfo) {
        this.aF.remove(Long.valueOf(pluginDeveloperInfo.b()));
    }

    /* access modifiers changed from: private */
    public void w() {
        this.aF.clear();
    }

    /* access modifiers changed from: private */
    public boolean c(Map<Long, PluginDeveloperInfo> map) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInt(SystemApi.a().e(this.C));
            int size = map.size();
            obtain.writeInt(size);
            for (Map.Entry<Long, PluginDeveloperInfo> value : map.entrySet()) {
                obtain.writeParcelable((Parcelable) value.getValue(), 0);
            }
            byte[] marshall = obtain.marshall();
            String dataMd5Digest = Coder.getDataMd5Digest(marshall);
            boolean a2 = FileUtils.a(this.ay, marshall);
            if (a2) {
                SharedPreferences sharedPreferences = this.aw;
                PreferenceUtils.a(sharedPreferences, ao, dataMd5Digest + String.valueOf(size));
            }
            return a2;
        } finally {
            obtain.recycle();
        }
    }

    /* access modifiers changed from: private */
    public void x() {
        try {
            this.ay.delete();
        } catch (Throwable th) {
            Log.e("PluginManager", "clearDeveloperListPrefInternal fail", th);
        }
    }

    private void a(PluginPackageInfo pluginPackageInfo) {
        int i2 = 0;
        while (i2 < this.aG.size()) {
            if (this.aG.get(i2).b() == pluginPackageInfo.b()) {
                this.aG.remove(i2);
                i2--;
            }
            i2++;
        }
        this.aG.add(pluginPackageInfo);
        this.aH.put(Long.valueOf(pluginPackageInfo.b()), pluginPackageInfo);
    }

    private void b(PluginPackageInfo pluginPackageInfo) {
        int i2 = 0;
        while (i2 < this.aG.size()) {
            if (this.aG.get(i2).b() == pluginPackageInfo.b()) {
                this.aG.remove(i2);
                i2--;
            }
            i2++;
        }
        this.aH.remove(Long.valueOf(pluginPackageInfo.b()));
    }

    private void y() {
        this.aG.clear();
        this.aH.clear();
    }

    private void c(PluginPackageInfo pluginPackageInfo) {
        int i2 = 0;
        while (i2 < this.aI.size()) {
            if (this.aI.get(i2).b() == pluginPackageInfo.b()) {
                this.aI.remove(i2);
                i2--;
            }
            i2++;
        }
        this.aI.add(pluginPackageInfo);
        this.aJ.put(Long.valueOf(pluginPackageInfo.b()), pluginPackageInfo);
    }

    private void d(PluginPackageInfo pluginPackageInfo) {
        int i2 = 0;
        while (i2 < this.aI.size()) {
            if (this.aI.get(i2).b() == pluginPackageInfo.b()) {
                this.aI.remove(i2);
                i2--;
            }
            i2++;
        }
        this.aJ.remove(Long.valueOf(pluginPackageInfo.b()));
    }

    private void z() {
        this.aI.clear();
        this.aJ.clear();
    }

    @Deprecated
    private void e(PluginPackageInfo pluginPackageInfo) {
        int i2 = 0;
        while (i2 < this.aP.size()) {
            if (this.aP.get(i2).b() == pluginPackageInfo.b()) {
                this.aP.remove(i2);
                i2--;
            }
            i2++;
        }
        this.aP.add(pluginPackageInfo);
        this.aQ.put(Long.valueOf(pluginPackageInfo.b()), pluginPackageInfo);
    }

    @Deprecated
    private void f(PluginPackageInfo pluginPackageInfo) {
        int i2 = 0;
        while (i2 < this.aP.size()) {
            if (this.aP.get(i2).b() == pluginPackageInfo.b()) {
                this.aP.remove(i2);
                i2--;
            }
            i2++;
        }
        this.aQ.remove(Long.valueOf(pluginPackageInfo.b()));
    }

    @Deprecated
    private void A() {
        this.aP.clear();
        this.aQ.clear();
    }

    /* access modifiers changed from: private */
    public void b(PluginRecord pluginRecord) {
        this.o.put(pluginRecord.o(), pluginRecord);
    }

    private void a(Map<String, PluginRecord> map, PluginRecord pluginRecord) {
        map.put(pluginRecord.o(), pluginRecord);
    }

    /* access modifiers changed from: private */
    public void B() {
        this.aN = new ArrayList(this.o.values());
    }

    /* access modifiers changed from: private */
    public void f(String str) {
        this.o.remove(str);
    }

    /* access modifiers changed from: private */
    public void g(String str) {
        PluginRecord pluginRecord = this.o.get(str);
        if (pluginRecord != null) {
            pluginRecord.a(0, 0, (PluginPackageInfo) null, (PluginDeveloperInfo) null);
        }
    }

    private void C() {
        for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
            ((PluginRecord) value.getValue()).a(0, 0, (PluginPackageInfo) null, (PluginDeveloperInfo) null);
        }
    }

    /* access modifiers changed from: private */
    public void h(String str) {
        PluginRecord pluginRecord = this.o.get(str);
        if (pluginRecord != null) {
            pluginRecord.b(0, 0, (PluginPackageInfo) null, (PluginDeveloperInfo) null);
        }
    }

    private void D() {
        for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
            ((PluginRecord) value.getValue()).b(0, 0, (PluginPackageInfo) null, (PluginDeveloperInfo) null);
        }
    }

    private void E() {
        this.o.clear();
        this.aN.clear();
    }

    /* access modifiers changed from: private */
    public void F() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInt(SystemApi.a().e(this.C));
            int size = this.o.size();
            obtain.writeInt(size);
            for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
                obtain.writeParcelable((Parcelable) value.getValue(), 0);
            }
            this.aO = obtain.marshall();
            String dataMd5Digest = Coder.getDataMd5Digest(this.aO);
            if (FileUtils.a(this.az, this.aO)) {
                SharedPreferences sharedPreferences = this.aw;
                PreferenceUtils.a(sharedPreferences, ap, dataMd5Digest + String.valueOf(size));
            }
        } finally {
            obtain.recycle();
        }
    }

    private void G() {
        try {
            this.az.delete();
            this.aO = null;
        } catch (Throwable th) {
            Log.e("PluginManager", "clearDeveloperListPrefInternal fail", th);
        }
    }

    private void H() {
        u();
        byte[] a2 = FileUtils.a(this.ax);
        boolean z2 = false;
        if (a2 != null) {
            Parcel a3 = FileUtils.a(a2);
            if (a3 != null) {
                try {
                    if (a3.readInt() == SystemApi.a().e(this.C)) {
                        int readInt = a3.readInt();
                        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(readInt);
                        for (int i2 = 0; i2 < readInt; i2++) {
                            PluginDeviceInfo pluginDeviceInfo = (PluginDeviceInfo) a3.readParcelable(PluginDeviceInfo.class.getClassLoader());
                            if (pluginDeviceInfo != null) {
                                concurrentHashMap.put(pluginDeviceInfo.c(), pluginDeviceInfo);
                            }
                        }
                        this.aE = concurrentHashMap;
                        z2 = true;
                    } else {
                        l("readDeviceInfoPrefInternal getAppVersionCode notmatch");
                    }
                } catch (Throwable th) {
                    l("readDeviceInfoPrefInternal parcel " + Log.getStackTraceString(th));
                }
                a3.recycle();
            } else {
                l("readDeviceInfoPrefInternal parcel.unmarshall error");
            }
        } else {
            l("readDeviceInfoPrefInternal md5 notmatch");
        }
        if (z2) {
            if (TextUtils.equals(Coder.getDataMd5Digest(a2) + String.valueOf(this.aE.size()), this.aw.getString(an, (String) null))) {
                return;
            }
        }
        u();
        v();
        PreferenceUtils.a(this.aw, al, 0);
    }

    private void I() {
        w();
        byte[] a2 = FileUtils.a(this.ay);
        boolean z2 = false;
        if (a2 != null) {
            Parcel a3 = FileUtils.a(a2);
            if (a3 != null) {
                try {
                    if (a3.readInt() == SystemApi.a().e(this.C)) {
                        int readInt = a3.readInt();
                        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(readInt);
                        for (int i2 = 0; i2 < readInt; i2++) {
                            PluginDeveloperInfo pluginDeveloperInfo = (PluginDeveloperInfo) a3.readParcelable(PluginDeveloperInfo.class.getClassLoader());
                            if (pluginDeveloperInfo != null) {
                                concurrentHashMap.put(Long.valueOf(pluginDeveloperInfo.b()), pluginDeveloperInfo);
                            }
                        }
                        this.aF = concurrentHashMap;
                        z2 = true;
                    } else {
                        l("readDeveloperInfoPrefInternal getAppVersionCode notmatch");
                    }
                } catch (Throwable th) {
                    l("readDeveloperInfoPrefInternal parcel " + Log.getStackTraceString(th));
                }
                a3.recycle();
            } else {
                l("readDeveloperInfoPrefInternal parcel.unmarshall error");
            }
        } else {
            l("readDeveloperInfoPrefInternal md5 notmatch");
        }
        if (z2) {
            if (TextUtils.equals(Coder.getDataMd5Digest(a2) + String.valueOf(this.aF.size()), this.aw.getString(ao, (String) null))) {
                return;
            }
        }
        w();
        x();
        PreferenceUtils.a(this.aw, am, 0);
    }

    private void J() {
        y();
        z();
        K();
        L();
        M();
        W();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void K() {
        /*
            r26 = this;
            r0 = r26
            java.io.File r1 = new java.io.File
            java.lang.String r2 = r0.K
            r1.<init>(r2)
            boolean r2 = r1.isDirectory()
            r3 = 0
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x01a7
            java.lang.String[] r2 = r1.list()
            if (r2 == 0) goto L_0x01a7
            int r7 = r2.length
            r8 = 0
        L_0x001b:
            if (r8 >= r7) goto L_0x01a7
            r9 = r2[r8]
            long r10 = java.lang.Long.parseLong(r9)     // Catch:{ Exception -> 0x0024 }
            goto L_0x0025
        L_0x0024:
            r10 = r5
        L_0x0025:
            int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r12 > 0) goto L_0x002d
        L_0x0029:
            r20 = r1
            goto L_0x019d
        L_0x002d:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r1)
            java.lang.String r13 = java.io.File.separator
            r12.append(r13)
            r12.append(r9)
            java.lang.String r9 = r12.toString()
            java.io.File r12 = new java.io.File
            r12.<init>(r9)
            java.lang.String[] r12 = r12.list()
            if (r12 == 0) goto L_0x0029
            int r13 = r12.length
            if (r13 >= r4) goto L_0x0050
            goto L_0x0029
        L_0x0050:
            int r13 = r12.length
            r14 = 0
        L_0x0052:
            if (r14 >= r13) goto L_0x0029
            r15 = r12[r14]
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            java.lang.String r5 = java.io.File.separator
            r4.append(r5)
            r4.append(r15)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = ".apk"
            boolean r5 = r15.endsWith(r5)
            if (r5 == 0) goto L_0x0086
            int r5 = r15.length()     // Catch:{ Exception -> 0x0086 }
            java.lang.String r6 = ".apk"
            int r6 = r6.length()     // Catch:{ Exception -> 0x0086 }
            int r5 = r5 - r6
            java.lang.String r5 = r15.substring(r3, r5)     // Catch:{ Exception -> 0x0086 }
            long r5 = java.lang.Long.parseLong(r5)     // Catch:{ Exception -> 0x0086 }
            goto L_0x0088
        L_0x0086:
            r5 = 0
        L_0x0088:
            r17 = 0
            int r15 = (r5 > r17 ? 1 : (r5 == r17 ? 0 : -1))
            if (r15 > 0) goto L_0x0099
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r4)
            r20 = r1
        L_0x0093:
            r21 = r12
            r22 = r13
            goto L_0x018f
        L_0x0099:
            android.content.Context r15 = r0.C
            java.lang.String r15 = com.xiaomi.smarthome.setting.PluginSetting.b(r15, r10, r5)
            java.io.File r3 = new java.io.File
            r3.<init>(r15)
            java.lang.String[] r15 = r3.list()
            boolean r19 = r3.exists()
            if (r19 == 0) goto L_0x00e5
            boolean r3 = r3.isDirectory()
            if (r3 == 0) goto L_0x00e5
            if (r15 == 0) goto L_0x00e5
            int r3 = r15.length
            if (r3 <= 0) goto L_0x00e5
            android.content.Context r3 = r0.C
            java.lang.String r3 = com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager.b(r3, r10, r5)
            if (r3 != 0) goto L_0x00e5
            java.lang.String r3 = "PluginManager"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r20 = r1
            java.lang.String r1 = "do not find abi path pluginId:"
            r15.append(r1)
            r15.append(r10)
            java.lang.String r1 = " packageId:"
            r15.append(r1)
            r15.append(r5)
            java.lang.String r1 = r15.toString()
            android.util.Log.e(r3, r1)
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r4)
            goto L_0x0093
        L_0x00e5:
            r20 = r1
            boolean r1 = r0.k((long) r5)
            if (r1 == 0) goto L_0x00ee
            goto L_0x0093
        L_0x00ee:
            java.lang.String r1 = "mpk"
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo r1 = r0.b((java.lang.String) r4, (java.lang.String) r1)
            if (r1 != 0) goto L_0x00fa
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r4)
            goto L_0x0093
        L_0x00fa:
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r3 = r0.aF
            r21 = r12
            r22 = r13
            long r12 = r1.b
            java.lang.Long r12 = java.lang.Long.valueOf(r12)
            java.lang.Object r3 = r3.get(r12)
            com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo r3 = (com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo) r3
            java.lang.String r3 = "mpk"
            int r12 = r1.c
            boolean r3 = r0.a((java.lang.String) r3, (int) r12)
            if (r3 != 0) goto L_0x011a
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r4)
            goto L_0x018f
        L_0x011a:
            java.lang.String r3 = r1.d
            java.lang.String r12 = "mpk"
            boolean r3 = r0.c((java.lang.String) r3, (java.lang.String) r12)
            if (r3 != 0) goto L_0x0128
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r4)
            goto L_0x018f
        L_0x0128:
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r3 = new com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo
            r3.<init>()
            r3.a((long) r10)
            r3.b((long) r5)
            long r12 = r1.b
            r3.d((long) r12)
            int r12 = r1.c
            r3.b((int) r12)
            java.lang.String r12 = r1.d
            r3.d((java.lang.String) r12)
            int r12 = r1.f14670a
            r3.a((int) r12)
            java.lang.String r12 = "mpk"
            r3.c((java.lang.String) r12)
            r3.a((java.lang.String) r4)
            java.lang.String r12 = r1.e
            r3.b((java.lang.String) r12)
            java.util.List<java.lang.String> r12 = r1.f
            r3.a((java.util.List<java.lang.String>) r12)
            boolean r12 = r1.g
            r3.a((boolean) r12)
            boolean r12 = r1.h
            r3.b((boolean) r12)
            long r12 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.g(r4)
            r3.c((long) r12)
            r0.a((com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "scan local package, modellist - "
            r3.append(r4)
            java.util.List<java.lang.String> r1 = r1.f
            java.lang.String r1 = r1.toString()
            r3.append(r1)
            java.lang.String r1 = ", package id - "
            r3.append(r1)
            r3.append(r5)
            java.lang.String r1 = r3.toString()
            r0.l((java.lang.String) r1)
        L_0x018f:
            int r14 = r14 + 1
            r1 = r20
            r12 = r21
            r13 = r22
            r3 = 0
            r4 = 1
            r5 = 0
            goto L_0x0052
        L_0x019d:
            int r8 = r8 + 1
            r1 = r20
            r3 = 0
            r4 = 1
            r5 = 0
            goto L_0x001b
        L_0x01a7:
            java.io.File r1 = new java.io.File
            java.lang.String r2 = r0.L
            r1.<init>(r2)
            boolean r2 = r1.isDirectory()
            if (r2 == 0) goto L_0x02db
            java.lang.String[] r2 = r1.list()
            if (r2 == 0) goto L_0x02db
            int r3 = r2.length
            r4 = 0
        L_0x01bc:
            if (r4 >= r3) goto L_0x02db
            r5 = r2[r4]
            long r6 = java.lang.Long.parseLong(r5)     // Catch:{ Exception -> 0x01c7 }
        L_0x01c4:
            r8 = 0
            goto L_0x01ca
        L_0x01c7:
            r6 = 0
            goto L_0x01c4
        L_0x01ca:
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 > 0) goto L_0x01d8
        L_0x01ce:
            r23 = r1
            r24 = r2
            r25 = r3
            r16 = 0
            goto L_0x02d1
        L_0x01d8:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            java.lang.String r9 = java.io.File.separator
            r8.append(r9)
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            java.io.File r8 = new java.io.File
            r8.<init>(r5)
            java.lang.String[] r8 = r8.list()
            if (r8 == 0) goto L_0x01ce
            int r9 = r8.length
            r10 = 1
            if (r9 >= r10) goto L_0x01fc
            goto L_0x01ce
        L_0x01fc:
            int r9 = r8.length
            r11 = 0
        L_0x01fe:
            if (r11 >= r9) goto L_0x01ce
            r12 = r8[r11]
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r5)
            java.lang.String r14 = java.io.File.separator
            r13.append(r14)
            r13.append(r12)
            java.lang.String r13 = r13.toString()
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ NumberFormatException -> 0x02be }
            long r14 = r12.longValue()     // Catch:{ NumberFormatException -> 0x02be }
            r16 = 0
            int r12 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r12 > 0) goto L_0x022f
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.e(r13)
        L_0x0227:
            r23 = r1
        L_0x0229:
            r24 = r2
            r25 = r3
            goto L_0x02c6
        L_0x022f:
            boolean r12 = r0.k((long) r14)
            if (r12 == 0) goto L_0x0236
            goto L_0x0227
        L_0x0236:
            java.lang.String r12 = "rn"
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo r12 = r0.b((java.lang.String) r13, (java.lang.String) r12)
            if (r12 != 0) goto L_0x0242
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.e(r13)
            goto L_0x0227
        L_0x0242:
            java.lang.String r10 = "rn"
            r23 = r1
            int r1 = r12.c
            boolean r1 = r0.a((java.lang.String) r10, (int) r1)
            if (r1 != 0) goto L_0x0252
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.e(r13)
            goto L_0x0229
        L_0x0252:
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r1 = new com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo
            r1.<init>()
            r1.a((long) r6)
            r1.b((long) r14)
            r24 = r2
            r25 = r3
            long r2 = r12.b
            r1.d((long) r2)
            int r2 = r12.c
            r1.b((int) r2)
            java.lang.String r2 = r12.d
            r1.d((java.lang.String) r2)
            int r2 = r12.f14670a
            r1.a((int) r2)
            java.lang.String r2 = "rn"
            r1.c((java.lang.String) r2)
            r1.a((java.lang.String) r13)
            java.lang.String r2 = r12.e
            r1.b((java.lang.String) r2)
            java.util.List<java.lang.String> r2 = r12.f
            r1.a((java.util.List<java.lang.String>) r2)
            boolean r2 = r12.g
            r1.a((boolean) r2)
            boolean r2 = r12.h
            r1.b((boolean) r2)
            long r2 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.g(r13)
            r1.c((long) r2)
            r0.a((com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "scan local package, modellist - "
            r1.append(r2)
            java.util.List<java.lang.String> r2 = r12.f
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r2 = ", package id - "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r1 = r1.toString()
            r0.l((java.lang.String) r1)
            goto L_0x02c6
        L_0x02be:
            r23 = r1
            r24 = r2
            r25 = r3
            r16 = 0
        L_0x02c6:
            int r11 = r11 + 1
            r1 = r23
            r2 = r24
            r3 = r25
            r10 = 1
            goto L_0x01fe
        L_0x02d1:
            int r4 = r4 + 1
            r1 = r23
            r2 = r24
            r3 = r25
            goto L_0x01bc
        L_0x02db:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.K():void");
    }

    private void L() {
        String[] list;
        long j2;
        long j3;
        int i2;
        String[] strArr;
        File file = new File(this.H);
        if (file.isDirectory() && (list = file.list()) != null) {
            int length = list.length;
            int i3 = 0;
            int i4 = 0;
            while (i4 < length) {
                String str = list[i4];
                try {
                    j2 = Long.parseLong(str);
                } catch (Exception unused) {
                    j2 = 0;
                }
                if (j2 > 0) {
                    String str2 = this.H + File.separator + str;
                    String[] list2 = new File(str2).list();
                    if (list2 != null && list2.length >= 1) {
                        int length2 = list2.length;
                        int i5 = 0;
                        while (i5 < length2) {
                            String str3 = list2[i5];
                            String str4 = str2 + File.separator + str3;
                            String str5 = "";
                            String str6 = "";
                            if (str3.endsWith(aa)) {
                                str6 = str3.substring(i3, str3.length() - aa.length());
                                str5 = i;
                            } else if (str3.endsWith(ab)) {
                                str6 = str3.substring(i3, str3.length() - ab.length());
                                str5 = h;
                            } else if (str3.endsWith(".apk")) {
                                str6 = str3.substring(i3, str3.length() - ".apk".length());
                                str5 = "apk";
                            } else if (str3.endsWith(ad)) {
                                str6 = str3.substring(i3, str3.length() - ad.length());
                                str5 = "rn";
                            }
                            try {
                                j3 = Long.parseLong(str6);
                            } catch (Exception unused2) {
                                j3 = 0;
                            }
                            if (j3 <= 0 || TextUtils.isEmpty(str5)) {
                                strArr = list;
                                i2 = length;
                                FileUtils.d(str4);
                            } else {
                                if (!l(j3) && !n(j3)) {
                                    PackageRawInfo b2 = b(str4, str5);
                                    if (b2 == null) {
                                        FileUtils.d(str4);
                                    } else {
                                        strArr = list;
                                        i2 = length;
                                        PluginDeveloperInfo pluginDeveloperInfo = this.aF.get(Long.valueOf(b2.b));
                                        if (!a(str5, b2.c)) {
                                            FileUtils.d(str4);
                                        } else if (!c(b2.d, str5)) {
                                            FileUtils.d(str4);
                                        } else {
                                            PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
                                            pluginPackageInfo.a(j2);
                                            pluginPackageInfo.b(j3);
                                            pluginPackageInfo.d(b2.b);
                                            pluginPackageInfo.b(b2.c);
                                            pluginPackageInfo.d(b2.d);
                                            pluginPackageInfo.a(b2.f14670a);
                                            pluginPackageInfo.c(str5);
                                            pluginPackageInfo.a(str4);
                                            pluginPackageInfo.b(b2.e);
                                            pluginPackageInfo.a(b2.f);
                                            pluginPackageInfo.a(b2.g);
                                            pluginPackageInfo.b(b2.h);
                                            pluginPackageInfo.c(FileUtils.g(str4));
                                            c(pluginPackageInfo);
                                            l("scan downloaded package, modellist - " + b2.f.toString() + ", package id - " + j3);
                                        }
                                    }
                                }
                                strArr = list;
                                i2 = length;
                            }
                            i5++;
                            list = strArr;
                            length = i2;
                            i3 = 0;
                        }
                    }
                }
                i4++;
                list = list;
                length = length;
                i3 = 0;
            }
        }
    }

    private void M() {
        String[] list;
        long j2;
        File file = new File(this.G);
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                String str2 = this.G + File.separator + str;
                String str3 = "";
                String str4 = "";
                if (str.endsWith(aa)) {
                    str4 = str.substring(0, str.length() - aa.length());
                    str3 = i;
                } else if (str.endsWith(ab)) {
                    str4 = str.substring(0, str.length() - ab.length());
                    str3 = h;
                } else if (str.endsWith(".apk")) {
                    str4 = str.substring(0, str.length() - ".apk".length());
                    str3 = "apk";
                }
                try {
                    j2 = Long.parseLong(str4);
                } catch (Exception unused) {
                    j2 = 0;
                }
                if (j2 <= 0 || TextUtils.isEmpty(str3)) {
                    FileUtils.d(str2);
                } else if (!m(j2)) {
                    PackageRawInfo b2 = b(str2, str3);
                    if (b2 == null) {
                        FileUtils.d(str2);
                    } else {
                        PluginDeveloperInfo pluginDeveloperInfo = this.aF.get(Long.valueOf(b2.b));
                        if (!a(str3, b2.c)) {
                            FileUtils.d(str2);
                        } else if (!c(b2.d, str3)) {
                            FileUtils.d(str2);
                        } else {
                            PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
                            pluginPackageInfo.b(j2);
                            pluginPackageInfo.d(b2.b);
                            pluginPackageInfo.b(b2.c);
                            pluginPackageInfo.d(b2.d);
                            pluginPackageInfo.a(b2.f14670a);
                            pluginPackageInfo.c(i);
                            pluginPackageInfo.a(str2);
                            pluginPackageInfo.b(b2.e);
                            pluginPackageInfo.a(b2.f);
                            pluginPackageInfo.c(FileUtils.g(str2));
                            e(pluginPackageInfo);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01e1, code lost:
        if (android.text.TextUtils.equals(com.xiaomi.accountsdk.utils.Coder.getDataMd5Digest(r2) + r1.o.size(), r1.aw.getString(ap, (java.lang.String) null)) == false) goto L_0x01e3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9 A[Catch:{ Throwable -> 0x0188 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x016e A[Catch:{ Throwable -> 0x0188 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void N() {
        /*
            r21 = this;
            r1 = r21
            r21.E()
            java.io.File r0 = r1.az
            byte[] r2 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a((java.io.File) r0)
            android.os.Parcel r3 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a((byte[]) r2)
            r5 = 1
            if (r3 != 0) goto L_0x003a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "readPluginRecordPrefInternal byte=null"
            r0.append(r6)
            if (r2 != 0) goto L_0x0020
            r6 = 1
            goto L_0x0021
        L_0x0020:
            r6 = 0
        L_0x0021:
            r0.append(r6)
            java.lang.String r6 = " mPluginRecordByte=null"
            r0.append(r6)
            byte[] r6 = r1.aO
            if (r6 != 0) goto L_0x002f
            r6 = 1
            goto L_0x0030
        L_0x002f:
            r6 = 0
        L_0x0030:
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r1.l((java.lang.String) r0)
        L_0x003a:
            if (r3 == 0) goto L_0x01b7
            int r0 = r3.readInt()     // Catch:{ Throwable -> 0x0198 }
            com.xiaomi.smarthome.library.commonapi.SystemApi r6 = com.xiaomi.smarthome.library.commonapi.SystemApi.a()     // Catch:{ Throwable -> 0x0198 }
            android.content.Context r7 = r1.C     // Catch:{ Throwable -> 0x0198 }
            int r6 = r6.e(r7)     // Catch:{ Throwable -> 0x0198 }
            if (r0 != r6) goto L_0x0194
            r1.aO = r2     // Catch:{ Throwable -> 0x0198 }
            int r0 = r3.readInt()     // Catch:{ Throwable -> 0x0198 }
            java.util.concurrent.ConcurrentHashMap r6 = new java.util.concurrent.ConcurrentHashMap     // Catch:{ Throwable -> 0x0198 }
            r6.<init>(r0)     // Catch:{ Throwable -> 0x0198 }
            r7 = 0
            r8 = 0
        L_0x0059:
            if (r7 >= r0) goto L_0x018d
            java.lang.Class<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r9 = com.xiaomi.smarthome.core.entity.plugin.PluginRecord.class
            java.lang.ClassLoader r9 = r9.getClassLoader()     // Catch:{ Throwable -> 0x018a }
            android.os.Parcelable r9 = r3.readParcelable(r9)     // Catch:{ Throwable -> 0x018a }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r9 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r9     // Catch:{ Throwable -> 0x018a }
            if (r9 != 0) goto L_0x006b
            goto L_0x0184
        L_0x006b:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo> r10 = r1.aE     // Catch:{ Throwable -> 0x018a }
            java.lang.String r11 = r9.o()     // Catch:{ Throwable -> 0x018a }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ Throwable -> 0x018a }
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r10 = (com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r10     // Catch:{ Throwable -> 0x018a }
            if (r10 != 0) goto L_0x007b
            goto L_0x0184
        L_0x007b:
            r9.a((com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo) r10)     // Catch:{ Throwable -> 0x018a }
            long r11 = r9.d()     // Catch:{ Throwable -> 0x018a }
            long r13 = r9.e()     // Catch:{ Throwable -> 0x018a }
            r17 = 0
            int r10 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r10 <= 0) goto L_0x00ec
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo> r10 = r1.aH     // Catch:{ Throwable -> 0x018a }
            java.lang.Long r15 = java.lang.Long.valueOf(r13)     // Catch:{ Throwable -> 0x018a }
            java.lang.Object r10 = r10.get(r15)     // Catch:{ Throwable -> 0x018a }
            r15 = r10
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r15 = (com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r15     // Catch:{ Throwable -> 0x018a }
            if (r15 == 0) goto L_0x00db
            java.lang.String r10 = "rn"
            java.lang.String r4 = r15.i()     // Catch:{ Throwable -> 0x018a }
            boolean r4 = r10.equals(r4)     // Catch:{ Throwable -> 0x018a }
            if (r4 == 0) goto L_0x00ae
            r16 = 0
            r10 = r9
            r10.a(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x018a }
            goto L_0x00ec
        L_0x00ae:
            long r19 = r15.k()     // Catch:{ Throwable -> 0x018a }
            int r4 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r4 <= 0) goto L_0x00cf
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r4 = r1.aF     // Catch:{ Throwable -> 0x018a }
            long r19 = r15.k()     // Catch:{ Throwable -> 0x018a }
            java.lang.Long r10 = java.lang.Long.valueOf(r19)     // Catch:{ Throwable -> 0x018a }
            java.lang.Object r4 = r4.get(r10)     // Catch:{ Throwable -> 0x018a }
            r16 = r4
            com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo r16 = (com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo) r16     // Catch:{ Throwable -> 0x018a }
            if (r16 == 0) goto L_0x00cf
            r10 = r9
            r10.a(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x018a }
            goto L_0x00ec
        L_0x00cf:
            r11 = 0
            r13 = 0
            r15 = 0
            r16 = 0
            r10 = r9
            r10.a(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x00e8 }
            goto L_0x00e6
        L_0x00db:
            r11 = 0
            r13 = 0
            r15 = 0
            r16 = 0
            r10 = r9
            r10.a(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x00e8 }
        L_0x00e6:
            r4 = 1
            goto L_0x00ed
        L_0x00e8:
            r0 = move-exception
            r4 = 1
            goto L_0x019a
        L_0x00ec:
            r4 = r8
        L_0x00ed:
            long r11 = r9.f()     // Catch:{ Throwable -> 0x0188 }
            long r13 = r9.g()     // Catch:{ Throwable -> 0x0188 }
            int r8 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r8 <= 0) goto L_0x0154
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo> r8 = r1.aJ     // Catch:{ Throwable -> 0x0188 }
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ Throwable -> 0x0188 }
            java.lang.Object r8 = r8.get(r10)     // Catch:{ Throwable -> 0x0188 }
            r15 = r8
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r15 = (com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r15     // Catch:{ Throwable -> 0x0188 }
            if (r15 == 0) goto L_0x0148
            java.lang.String r8 = "rn"
            java.lang.String r10 = r15.i()     // Catch:{ Throwable -> 0x0188 }
            boolean r8 = r8.equals(r10)     // Catch:{ Throwable -> 0x0188 }
            if (r8 == 0) goto L_0x011b
            r16 = 0
            r10 = r9
            r10.b(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x0188 }
            goto L_0x0154
        L_0x011b:
            long r19 = r15.k()     // Catch:{ Throwable -> 0x0188 }
            int r8 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r8 <= 0) goto L_0x013c
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r8 = r1.aF     // Catch:{ Throwable -> 0x0188 }
            long r16 = r15.k()     // Catch:{ Throwable -> 0x0188 }
            java.lang.Long r10 = java.lang.Long.valueOf(r16)     // Catch:{ Throwable -> 0x0188 }
            java.lang.Object r8 = r8.get(r10)     // Catch:{ Throwable -> 0x0188 }
            r16 = r8
            com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo r16 = (com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo) r16     // Catch:{ Throwable -> 0x0188 }
            if (r16 == 0) goto L_0x013c
            r10 = r9
            r10.b(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x0188 }
            goto L_0x0154
        L_0x013c:
            r11 = 0
            r13 = 0
            r15 = 0
            r16 = 0
            r10 = r9
            r10.b(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x00e8 }
            goto L_0x0153
        L_0x0148:
            r11 = 0
            r13 = 0
            r15 = 0
            r16 = 0
            r10 = r9
            r10.b(r11, r13, r15, r16)     // Catch:{ Throwable -> 0x00e8 }
        L_0x0153:
            r4 = 1
        L_0x0154:
            r1.a((java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord>) r6, (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r9)     // Catch:{ Throwable -> 0x0188 }
            java.util.Map<java.lang.String, java.util.regex.Pattern> r8 = r1.aS     // Catch:{ Throwable -> 0x0188 }
            java.lang.String r10 = r9.o()     // Catch:{ Throwable -> 0x0188 }
            r8.remove(r10)     // Catch:{ Throwable -> 0x0188 }
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r8 = r9.c()     // Catch:{ Throwable -> 0x0188 }
            java.lang.String r8 = r8.r()     // Catch:{ Throwable -> 0x0188 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Throwable -> 0x0188 }
            if (r8 != 0) goto L_0x0183
            java.util.Map<java.lang.String, java.util.regex.Pattern> r8 = r1.aS     // Catch:{ Throwable -> 0x0188 }
            java.lang.String r10 = r9.o()     // Catch:{ Throwable -> 0x0188 }
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r9 = r9.c()     // Catch:{ Throwable -> 0x0188 }
            java.lang.String r9 = r9.r()     // Catch:{ Throwable -> 0x0188 }
            java.util.regex.Pattern r9 = java.util.regex.Pattern.compile(r9)     // Catch:{ Throwable -> 0x0188 }
            r8.put(r10, r9)     // Catch:{ Throwable -> 0x0188 }
        L_0x0183:
            r8 = r4
        L_0x0184:
            int r7 = r7 + 1
            goto L_0x0059
        L_0x0188:
            r0 = move-exception
            goto L_0x019a
        L_0x018a:
            r0 = move-exception
            r4 = r8
            goto L_0x019a
        L_0x018d:
            r1.o = r6     // Catch:{ Throwable -> 0x018a }
            r21.B()     // Catch:{ Throwable -> 0x018a }
            r0 = 1
            goto L_0x0196
        L_0x0194:
            r0 = 0
            r8 = 0
        L_0x0196:
            r4 = r8
            goto L_0x01b3
        L_0x0198:
            r0 = move-exception
            r4 = 0
        L_0x019a:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "readPluginRecordPrefInternal parcel "
            r6.append(r7)
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r1.l((java.lang.String) r0)
            r0 = 0
        L_0x01b3:
            r3.recycle()
            goto L_0x01b9
        L_0x01b7:
            r0 = 0
            r4 = 0
        L_0x01b9:
            if (r0 == 0) goto L_0x01e3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = com.xiaomi.accountsdk.utils.Coder.getDataMd5Digest(r2)
            r0.append(r2)
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r2 = r1.o
            int r2 = r2.size()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.content.SharedPreferences r2 = r1.aw
            java.lang.String r3 = "plugin_record_md5"
            r6 = 0
            java.lang.String r2 = r2.getString(r3, r6)
            boolean r0 = android.text.TextUtils.equals(r0, r2)
            if (r0 != 0) goto L_0x01e9
        L_0x01e3:
            r21.E()
            r21.G()
        L_0x01e9:
            if (r4 == 0) goto L_0x01ee
            r21.F()
        L_0x01ee:
            java.lang.String r0 = "%d plugin device info from pref"
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r3 = r1.o
            int r3 = r3.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4 = 0
            r2[r4] = r3
            java.lang.String r0 = java.lang.String.format(r0, r2)
            r1.l((java.lang.String) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.N():void");
    }

    private void O() {
        boolean z2 = false;
        for (Map.Entry<String, PluginDeviceInfo> value : this.aE.entrySet()) {
            PluginDeviceInfo pluginDeviceInfo = (PluginDeviceInfo) value.getValue();
            String c2 = pluginDeviceInfo.c();
            int j2 = pluginDeviceInfo.j();
            int e2 = SystemApi.a().e(this.C);
            if (!TextUtils.isEmpty(c2) && !this.o.containsKey(c2) && j2 > 0 && j2 <= e2) {
                PluginRecord pluginRecord = new PluginRecord();
                pluginRecord.a(pluginDeviceInfo);
                b(pluginRecord);
                z2 = true;
            }
        }
        if (z2) {
            B();
            F();
        }
    }

    private List<Long> P() {
        HashMap hashMap = new HashMap();
        for (PluginPackageInfo next : this.aG) {
            if (hashMap.containsKey(Long.valueOf(next.a()))) {
                ((ArrayList) hashMap.get(Long.valueOf(next.a()))).add(next);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                hashMap.put(Long.valueOf(next.a()), arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry value : hashMap.entrySet()) {
            ArrayList arrayList3 = (ArrayList) value.getValue();
            if (arrayList3.size() > 2) {
                Collections.sort(arrayList3, new Comparator<PluginPackageInfo>() {
                    /* renamed from: a */
                    public int compare(PluginPackageInfo pluginPackageInfo, PluginPackageInfo pluginPackageInfo2) {
                        if (pluginPackageInfo.e() > pluginPackageInfo2.e()) {
                            return -1;
                        }
                        return pluginPackageInfo.e() < pluginPackageInfo2.e() ? 1 : 0;
                    }
                });
                for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                    l("final pkg order " + ((PluginPackageInfo) arrayList3.get(i2)).b() + " lastmodified " + ((PluginPackageInfo) arrayList3.get(i2)).e());
                }
                for (Map.Entry<String, PluginRecord> value2 : this.o.entrySet()) {
                    PluginRecord pluginRecord = (PluginRecord) value2.getValue();
                    if (pluginRecord.l()) {
                        int i3 = 0;
                        while (i3 < arrayList3.size()) {
                            if (((PluginPackageInfo) arrayList3.get(i3)).b() == pluginRecord.e()) {
                                arrayList3.remove(i3);
                                l("one Record has package id " + pluginRecord.e());
                                i3--;
                            }
                            i3++;
                        }
                        if (arrayList3.size() == 0) {
                            break;
                        }
                    }
                }
                if (arrayList3.size() > 2) {
                    for (int i4 = 2; i4 < arrayList3.size(); i4++) {
                        arrayList2.add(Long.valueOf(((PluginPackageInfo) arrayList3.get(i4)).b()));
                        l("delete package id " + ((PluginPackageInfo) arrayList3.get(i4)).b());
                    }
                }
            }
        }
        return arrayList2;
    }

    private List<Long> Q() {
        HashMap hashMap = new HashMap();
        for (PluginPackageInfo next : this.aI) {
            if (hashMap.containsKey(Long.valueOf(next.a()))) {
                ((ArrayList) hashMap.get(Long.valueOf(next.a()))).add(next);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                hashMap.put(Long.valueOf(next.a()), arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry value : hashMap.entrySet()) {
            ArrayList arrayList3 = (ArrayList) value.getValue();
            if (arrayList3.size() > 2) {
                Collections.sort(arrayList3, new Comparator<PluginPackageInfo>() {
                    /* renamed from: a */
                    public int compare(PluginPackageInfo pluginPackageInfo, PluginPackageInfo pluginPackageInfo2) {
                        if (pluginPackageInfo.e() > pluginPackageInfo2.e()) {
                            return -1;
                        }
                        return pluginPackageInfo.e() < pluginPackageInfo2.e() ? 1 : 0;
                    }
                });
                for (Map.Entry<String, PluginRecord> value2 : this.o.entrySet()) {
                    PluginRecord pluginRecord = (PluginRecord) value2.getValue();
                    if (pluginRecord.k()) {
                        int i2 = 0;
                        while (i2 < arrayList3.size()) {
                            if (((PluginPackageInfo) arrayList3.get(i2)).b() == pluginRecord.g()) {
                                arrayList3.remove(i2);
                                i2--;
                            }
                            i2++;
                        }
                        if (arrayList3.size() == 0) {
                            break;
                        }
                    }
                }
                if (arrayList3.size() > 2) {
                    for (int i3 = 2; i3 < arrayList3.size(); i3++) {
                        arrayList2.add(Long.valueOf(((PluginPackageInfo) arrayList3.get(i3)).b()));
                    }
                }
            }
        }
        return arrayList2;
    }

    private void R() {
        List<Long> P2 = P();
        for (int i2 = 0; i2 < P2.size(); i2++) {
            long longValue = P2.get(i2).longValue();
            l("clear useless package id - " + longValue);
            e(longValue);
        }
        List<Long> Q2 = Q();
        for (int i3 = 0; i3 < Q2.size(); i3++) {
            f(Q2.get(i3).longValue());
        }
    }

    private void e(long j2) {
        if (k(j2)) {
            PluginPackageInfo pluginPackageInfo = this.aH.get(Long.valueOf(j2));
            long a2 = pluginPackageInfo.a();
            String i2 = pluginPackageInfo.i();
            if (i2.equalsIgnoreCase(i)) {
                String a3 = a(a2, j2);
                FileUtils.d(a3 + File.separator + j2 + ShareConstants.w);
                long j3 = a2;
                long j4 = j2;
                FileUtils.e(a(j3, j4, d()));
                FileUtils.d(c(j3, j4, i2));
                b(pluginPackageInfo);
            }
        }
    }

    private void f(long j2) {
        if (l(j2)) {
            PluginPackageInfo pluginPackageInfo = this.aJ.get(Long.valueOf(j2));
            FileUtils.d(b(pluginPackageInfo.a(), j2, pluginPackageInfo.i()));
            d(pluginPackageInfo);
        }
    }

    class BuiltinPkgResult {

        /* renamed from: a  reason: collision with root package name */
        String f14664a;
        String b;

        BuiltinPkgResult() {
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0158 A[SYNTHETIC, Splitter:B:40:0x0158] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0181  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void S() {
        /*
            r23 = this;
            r7 = r23
            android.content.Context r0 = r7.C
            android.content.res.AssetManager r8 = r0.getAssets()
            java.lang.String r0 = r7.Q     // Catch:{ IOException -> 0x0199 }
            java.lang.String[] r9 = r8.list(r0)     // Catch:{ IOException -> 0x0199 }
            int r10 = r9.length     // Catch:{ IOException -> 0x0199 }
            r12 = 0
        L_0x0010:
            if (r12 >= r10) goto L_0x0199
            r0 = r9[r12]     // Catch:{ IOException -> 0x0199 }
            r13 = 0
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x001c }
            r4 = r0
            goto L_0x001d
        L_0x001c:
            r4 = r13
        L_0x001d:
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 > 0) goto L_0x0024
        L_0x0021:
            r11 = 0
            goto L_0x0195
        L_0x0024:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0199 }
            r0.<init>()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r1 = r7.Q     // Catch:{ IOException -> 0x0199 }
            r0.append(r1)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ IOException -> 0x0199 }
            r0.append(r1)     // Catch:{ IOException -> 0x0199 }
            r0.append(r4)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0199 }
            java.lang.String[] r15 = r8.list(r0)     // Catch:{ IOException -> 0x0199 }
            if (r15 == 0) goto L_0x0021
            int r0 = r15.length     // Catch:{ IOException -> 0x0199 }
            r6 = 1
            if (r0 >= r6) goto L_0x0045
            goto L_0x0021
        L_0x0045:
            int r2 = r15.length     // Catch:{ IOException -> 0x0199 }
            r3 = 0
        L_0x0047:
            if (r3 >= r2) goto L_0x0021
            r0 = r15[r3]     // Catch:{ IOException -> 0x0199 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0199 }
            r1.<init>()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r13 = r7.Q     // Catch:{ IOException -> 0x0199 }
            r1.append(r13)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r13 = java.io.File.separator     // Catch:{ IOException -> 0x0199 }
            r1.append(r13)     // Catch:{ IOException -> 0x0199 }
            r1.append(r4)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r13 = java.io.File.separator     // Catch:{ IOException -> 0x0199 }
            r1.append(r13)     // Catch:{ IOException -> 0x0199 }
            r1.append(r0)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r13 = ""
            java.lang.String r14 = ""
            java.lang.String r11 = r0.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0199 }
            r6.<init>()     // Catch:{ IOException -> 0x0199 }
            r20 = r2
            java.lang.String r2 = "."
            r6.append(r2)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r2 = "mpk"
            java.lang.String r2 = r2.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            r6.append(r2)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r2 = r6.toString()     // Catch:{ IOException -> 0x0199 }
            boolean r2 = r11.endsWith(r2)     // Catch:{ IOException -> 0x0199 }
            if (r2 == 0) goto L_0x00a9
            int r2 = r0.length()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r6 = "mpk"
            int r6 = r6.length()     // Catch:{ IOException -> 0x0199 }
            int r2 = r2 - r6
            r6 = 1
            int r2 = r2 - r6
            r6 = 0
            java.lang.String r13 = r0.substring(r6, r2)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r0 = "mpk"
        L_0x00a4:
            r14 = r0
        L_0x00a5:
            r6 = 1
            r11 = 0
            goto L_0x014b
        L_0x00a9:
            java.lang.String r2 = r0.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0199 }
            r6.<init>()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r11 = "."
            r6.append(r11)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r11 = "h5"
            java.lang.String r11 = r11.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            r6.append(r11)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0199 }
            boolean r2 = r2.endsWith(r6)     // Catch:{ IOException -> 0x0199 }
            if (r2 == 0) goto L_0x00df
            int r2 = r0.length()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r6 = "h5"
            int r6 = r6.length()     // Catch:{ IOException -> 0x0199 }
            int r2 = r2 - r6
            r6 = 1
            int r2 = r2 - r6
            r6 = 0
            java.lang.String r13 = r0.substring(r6, r2)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r0 = "h5"
            goto L_0x00a4
        L_0x00df:
            java.lang.String r2 = r0.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0199 }
            r6.<init>()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r11 = "."
            r6.append(r11)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r11 = "apk"
            java.lang.String r11 = r11.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            r6.append(r11)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0199 }
            boolean r2 = r2.endsWith(r6)     // Catch:{ IOException -> 0x0199 }
            if (r2 == 0) goto L_0x0115
            int r2 = r0.length()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r6 = "apk"
            int r6 = r6.length()     // Catch:{ IOException -> 0x0199 }
            int r2 = r2 - r6
            r6 = 1
            int r2 = r2 - r6
            r6 = 0
            java.lang.String r13 = r0.substring(r6, r2)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r0 = "apk"
            goto L_0x00a4
        L_0x0115:
            java.lang.String r2 = r0.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0199 }
            r6.<init>()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r11 = "."
            r6.append(r11)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r11 = "rn"
            java.lang.String r11 = r11.toLowerCase()     // Catch:{ IOException -> 0x0199 }
            r6.append(r11)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0199 }
            boolean r2 = r2.endsWith(r6)     // Catch:{ IOException -> 0x0199 }
            if (r2 == 0) goto L_0x00a5
            int r2 = r0.length()     // Catch:{ IOException -> 0x0199 }
            java.lang.String r6 = "rn"
            int r6 = r6.length()     // Catch:{ IOException -> 0x0199 }
            int r2 = r2 - r6
            r6 = 1
            int r2 = r2 - r6
            r11 = 0
            java.lang.String r13 = r0.substring(r11, r2)     // Catch:{ IOException -> 0x0199 }
            java.lang.String r0 = "rn"
            r14 = r0
        L_0x014b:
            boolean r0 = android.text.TextUtils.isEmpty(r13)     // Catch:{ IOException -> 0x0199 }
            if (r0 != 0) goto L_0x0181
            boolean r0 = android.text.TextUtils.isEmpty(r14)     // Catch:{ IOException -> 0x0199 }
            if (r0 == 0) goto L_0x0158
            goto L_0x0181
        L_0x0158:
            long r18 = java.lang.Long.parseLong(r13)     // Catch:{ Exception -> 0x0161 }
            r16 = r18
        L_0x015e:
            r18 = 0
            goto L_0x0164
        L_0x0161:
            r16 = 0
            goto L_0x015e
        L_0x0164:
            int r0 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r0 > 0) goto L_0x016f
            r21 = r4
            r13 = r20
            r16 = 1
            goto L_0x0189
        L_0x016f:
            r0 = r23
            r13 = r20
            r20 = r3
            r2 = r4
            r21 = r4
            r4 = r16
            r16 = 1
            r6 = r14
            r0.a((java.lang.String) r1, (long) r2, (long) r4, (java.lang.String) r6)     // Catch:{ IOException -> 0x0199 }
            goto L_0x018b
        L_0x0181:
            r21 = r4
            r13 = r20
            r16 = 1
            r18 = 0
        L_0x0189:
            r20 = r3
        L_0x018b:
            int r3 = r20 + 1
            r2 = r13
            r13 = r18
            r4 = r21
            r6 = 1
            goto L_0x0047
        L_0x0195:
            int r12 = r12 + 1
            goto L_0x0010
        L_0x0199:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.S():void");
    }

    /* access modifiers changed from: private */
    public void a(String str, long j2, long j3, String str2) {
        AssetManager assets = this.C.getAssets();
        if (!l(j3) && !k(j3) && !n(j3)) {
            String a2 = a(j3, str2);
            boolean z2 = true;
            try {
                InputStream open = assets.open(str);
                FileUtils.i(a2);
                FileOutputStream fileOutputStream = new FileOutputStream(a2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
            } catch (Exception unused) {
                z2 = false;
            }
            if (!z2) {
                FileUtils.d(a2);
                return;
            }
            PackageRawInfo b2 = b(a2, str2);
            if (b2 == null) {
                FileUtils.d(a2);
            } else if (!a(b2, this.aF.get(Long.valueOf(b2.b)), a2, str2)) {
                FileUtils.d(a2);
            } else if (!a(str2, b2.c)) {
                FileUtils.d(a2);
            } else if (!c(b2.d, str2)) {
                FileUtils.d(a2);
            } else {
                String b3 = b(j2, j3, str2);
                boolean b4 = FileUtils.b(a2, b3);
                FileUtils.d(a2);
                if (b4) {
                    PackageRawInfo b5 = b(b3, str2);
                    if (b5 == null) {
                        FileUtils.d(b3);
                    } else if ("rn".equalsIgnoreCase(str2) || this.aF.containsKey(Long.valueOf(b5.b))) {
                        PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
                        pluginPackageInfo.a(j2);
                        pluginPackageInfo.b(j3);
                        pluginPackageInfo.a(b3);
                        pluginPackageInfo.d(b5.b);
                        pluginPackageInfo.b(b5.c);
                        pluginPackageInfo.d(b5.d);
                        pluginPackageInfo.a(b5.f14670a);
                        pluginPackageInfo.c(str2);
                        pluginPackageInfo.b(b5.e);
                        pluginPackageInfo.a(b5.f);
                        pluginPackageInfo.a(b5.g);
                        pluginPackageInfo.b(b5.h);
                        pluginPackageInfo.c(FileUtils.g(b3));
                        c(pluginPackageInfo);
                    } else {
                        FileUtils.d(b3);
                    }
                } else {
                    FileUtils.d(b3);
                }
            }
        }
    }

    private void b(String str, long j2, long j3, String str2) {
        AssetManager assets = this.C.getAssets();
        if (!l(j3) && !k(j3) && !n(j3)) {
            String a2 = a(j3, str2);
            boolean z2 = true;
            try {
                InputStream open = assets.open(str);
                FileUtils.i(a2);
                FileOutputStream fileOutputStream = new FileOutputStream(a2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = open.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
            } catch (Exception unused) {
                z2 = false;
            }
            if (!z2) {
                FileUtils.d(a2);
                return;
            }
            PackageRawInfo b2 = b(a2, str2);
            if (b2 == null) {
                FileUtils.d(a2);
            } else if (!a(b2, this.aF.get(Long.valueOf(b2.b)), a2, str2)) {
                FileUtils.d(a2);
            } else if (!a(str2, b2.c)) {
                FileUtils.d(a2);
            } else if (!c(b2.d, str2)) {
                FileUtils.d(a2);
            } else {
                String b3 = b(j2, j3, str2);
                boolean b4 = FileUtils.b(a2, b3);
                FileUtils.d(a2);
                if (b4) {
                    PackageRawInfo b5 = b(b3, str2);
                    if (b5 == null || !this.aF.containsKey(Long.valueOf(b5.b))) {
                        FileUtils.d(b3);
                        return;
                    }
                    PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
                    pluginPackageInfo.a(j2);
                    pluginPackageInfo.b(j3);
                    pluginPackageInfo.a(b3);
                    pluginPackageInfo.d(b5.b);
                    pluginPackageInfo.b(b5.c);
                    pluginPackageInfo.d(b5.d);
                    pluginPackageInfo.a(b5.f14670a);
                    pluginPackageInfo.c(str2);
                    pluginPackageInfo.b(b5.e);
                    pluginPackageInfo.a(b5.f);
                    pluginPackageInfo.a(b5.g);
                    pluginPackageInfo.b(b5.h);
                    pluginPackageInfo.c(FileUtils.g(b3));
                    c(pluginPackageInfo);
                    return;
                }
                FileUtils.d(b3);
            }
        }
    }

    private void a(boolean z2) {
        a(z2, (UpdateConfigCallbackInternal) new UpdateConfigCallbackInternal() {
            public void a(CoreError coreError) {
            }

            public void a(boolean z, boolean z2) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, boolean z3, String str) {
        for (IClientApi onPluginChanged : CoreManager.a().d()) {
            try {
                onPluginChanged.onPluginChanged(z2, z3, str);
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo, InstallCallbackInternel installCallbackInternel) {
        String str;
        PluginRecord pluginRecord2 = pluginRecord;
        PluginPackageInfo pluginPackageInfo2 = pluginPackageInfo;
        InstallCallbackInternel installCallbackInternel2 = installCallbackInternel;
        if (installCallbackInternel2 != null) {
            installCallbackInternel2.a(pluginRecord2, pluginPackageInfo2);
        }
        if (pluginPackageInfo2 == null || pluginPackageInfo.b() <= 0) {
            l("installPluginInternal installingPackageInfo is null pluginRecord:" + pluginRecord2 + "  installingPackageInfo:" + pluginPackageInfo2);
            if (installCallbackInternel2 != null) {
                installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                return;
            }
        }
        if (!pluginPackageInfo.n()) {
            if (pluginPackageInfo.q()) {
                long a2 = pluginPackageInfo.a();
                long b2 = pluginPackageInfo.b();
                String c2 = c(a2, b2);
                if (!new File(c2, "android").exists()) {
                    c2 = b(a2, b2, "rn");
                }
                String str2 = c2;
                String c3 = c(a2, b2, "rn");
                PackageRawInfo b3 = b(str2, "rn");
                if (b3 == null) {
                    b3 = b(c3, "rn");
                    if (b3 == null) {
                        l("installPluginInternal rawInfo is null pluginRecord:" + pluginRecord2 + "  installedPackagePath:" + c3);
                        if (installCallbackInternel2 != null) {
                            installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                            return;
                        }
                        return;
                    }
                    str = c3;
                } else {
                    String str3 = c3;
                    if (!a(a2, b2, str2, str3)) {
                        l("installPluginInternal installRNPackage false pluginRecord:" + pluginRecord2 + "  installedPackagePath:" + str3);
                        if (installCallbackInternel2 != null) {
                            installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                            return;
                        }
                        return;
                    }
                    str = str3;
                }
                PackageRawInfo packageRawInfo = b3;
                PluginPackageInfo pluginPackageInfo3 = new PluginPackageInfo();
                pluginPackageInfo3.a(a2);
                pluginPackageInfo3.b(b2);
                pluginPackageInfo3.d(packageRawInfo.b);
                pluginPackageInfo3.b(packageRawInfo.c);
                pluginPackageInfo3.d(packageRawInfo.d);
                pluginPackageInfo3.a(packageRawInfo.f14670a);
                pluginPackageInfo3.c("rn");
                pluginPackageInfo3.a(str);
                pluginPackageInfo3.b(packageRawInfo.e);
                pluginPackageInfo3.a(packageRawInfo.f);
                pluginPackageInfo3.a(packageRawInfo.g);
                pluginPackageInfo3.b(packageRawInfo.h);
                pluginPackageInfo3.c(FileUtils.g(str));
                a(pluginPackageInfo3);
                pluginRecord.a(a2, b2, pluginPackageInfo3, (PluginDeveloperInfo) null);
                F();
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("plugin_id", a2);
                    jSONObject.put(PushRouteUtil.k, b2);
                    jSONObject.put("package_type", "rn");
                    StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_install_success", jSONObject.toString(), (String) null, false);
                } catch (Exception unused) {
                }
                a(false, false, pluginRecord.o());
                l("install package success - " + pluginPackageInfo.b());
                if (installCallbackInternel2 != null) {
                    installCallbackInternel2.b(pluginRecord2, pluginPackageInfo2);
                }
            } else if (!pluginPackageInfo.o()) {
                if (pluginPackageInfo.p()) {
                    l("start install package, model - " + pluginRecord.o() + ", package id - " + pluginPackageInfo.b());
                    long a3 = pluginPackageInfo.a();
                    long b4 = pluginPackageInfo.b();
                    String i2 = pluginPackageInfo.i();
                    String f2 = pluginPackageInfo.f();
                    if (k(b4)) {
                        PluginPackageInfo pluginPackageInfo4 = this.aH.get(Long.valueOf(b4));
                        if (pluginPackageInfo4 == null) {
                            l("installPluginInternal existedPackageInfo is null pluginRecord:" + pluginRecord2 + "  installingPackageInfo:" + pluginPackageInfo2);
                            if (installCallbackInternel2 != null) {
                                installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                                return;
                            }
                            return;
                        }
                        PluginDeveloperInfo pluginDeveloperInfo = this.aF.get(Long.valueOf(pluginPackageInfo4.k()));
                        if (pluginDeveloperInfo == null) {
                            l("installPluginInternal existedDeveloperInfo is null pluginRecord:" + pluginRecord2 + "  installingPackageInfo:" + pluginPackageInfo2);
                            if (installCallbackInternel2 != null) {
                                installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                                return;
                            }
                            return;
                        }
                        pluginRecord.a(pluginPackageInfo4.a(), pluginPackageInfo4.b(), pluginPackageInfo4, pluginDeveloperInfo);
                        F();
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("plugin_id", pluginPackageInfo4.a());
                            jSONObject2.put(PushRouteUtil.k, pluginPackageInfo4.b());
                            StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_install_success", jSONObject2.toString(), (String) null, false);
                        } catch (Exception unused2) {
                        }
                        a(false, false, pluginRecord.o());
                        if (installCallbackInternel2 != null) {
                            l("installPluginInternal onInstallSuccess pluginRecord:" + pluginRecord2);
                            installCallbackInternel2.b(pluginRecord2, pluginPackageInfo2);
                            return;
                        }
                        return;
                    }
                    String c4 = c(a3, b4, i2);
                    g(pluginRecord.o());
                    F();
                    FileUtils.b(f2, c4);
                    PackageRawInfo b5 = b(c4, i2);
                    if (b5 != null) {
                        PluginDeveloperInfo pluginDeveloperInfo2 = this.aF.get(Long.valueOf(b5.b));
                        if (!a(i2, b5.c)) {
                            FileUtils.d(c4);
                            l("installPluginInternal isMinApiLevelValid is false pluginRecord:" + pluginRecord2 + "  rawInfo:" + b5);
                            if (installCallbackInternel2 != null) {
                                installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                            }
                        } else if (!c(b5.d, i2)) {
                            FileUtils.d(c4);
                            l("installPluginInternal isMinApiLevelValid RN isPlatformValid is false pluginRecord:" + pluginRecord2 + "  rawInfo:" + b5);
                            if (installCallbackInternel2 != null) {
                                installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                            }
                        } else if (!a(i2, b5.f)) {
                            l("installPluginInternal isMinApiLevelValid RN isModelValid is false pluginRecord:" + pluginRecord2 + "  rawInfo:" + b5);
                            FileUtils.d(c4);
                            if (installCallbackInternel2 != null) {
                                installCallbackInternel2.c(pluginRecord2, pluginPackageInfo2);
                            }
                        } else {
                            long j2 = b4;
                            long j3 = a3;
                            PluginSoManager.a().a(this.C, a3, j2, c4);
                            PluginPackageInfo pluginPackageInfo5 = new PluginPackageInfo();
                            pluginPackageInfo5.a(j3);
                            pluginPackageInfo5.b(j2);
                            pluginPackageInfo5.d(b5.b);
                            pluginPackageInfo5.b(b5.c);
                            pluginPackageInfo5.d(b5.d);
                            pluginPackageInfo5.a(b5.f14670a);
                            pluginPackageInfo5.c(i2);
                            pluginPackageInfo5.a(c4);
                            pluginPackageInfo5.b(b5.e);
                            pluginPackageInfo5.a(b5.f);
                            pluginPackageInfo5.a(b5.g);
                            pluginPackageInfo5.b(b5.h);
                            pluginPackageInfo5.c(FileUtils.g(c4));
                            a(pluginPackageInfo5);
                            long j4 = j3;
                            long j5 = j2;
                            long j6 = j5;
                            pluginRecord.a(j4, j5, pluginPackageInfo5, pluginDeveloperInfo2);
                            F();
                            try {
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("plugin_id", j4);
                                jSONObject3.put(PushRouteUtil.k, j6);
                                StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_install_success", jSONObject3.toString(), (String) null, false);
                            } catch (Exception unused3) {
                            }
                            PluginRuntimeManager.getInstance().loadApk(d(j6));
                            a(false, false, pluginRecord.o());
                            l("install package success - " + pluginPackageInfo.b());
                            if (installCallbackInternel2 != null) {
                                installCallbackInternel2.b(pluginRecord, pluginPackageInfo);
                            }
                        }
                    } else {
                        PluginRecord pluginRecord3 = pluginRecord2;
                        PluginPackageInfo pluginPackageInfo6 = pluginPackageInfo2;
                        FileUtils.d(c4);
                        l("installPluginInternal isMinApiLevelValid RN rawInfo is null pluginRecord:" + pluginRecord3 + "  installingPackageInfo:" + pluginPackageInfo6);
                        if (installCallbackInternel2 != null) {
                            installCallbackInternel2.c(pluginRecord3, pluginPackageInfo6);
                        }
                    }
                } else {
                    PluginRecord pluginRecord4 = pluginRecord2;
                    PluginPackageInfo pluginPackageInfo7 = pluginPackageInfo2;
                    l("installPluginInternal not rn nor mpk pluginRecord:" + pluginRecord4);
                    if (installCallbackInternel2 != null) {
                        installCallbackInternel2.c(pluginRecord4, pluginPackageInfo7);
                    }
                }
            }
        }
    }

    private class PluginDownloadCallbackInternal extends BaseCallbackInternal {
        /* access modifiers changed from: package-private */
        public void a(PluginRecord pluginRecord) {
        }

        /* access modifiers changed from: package-private */
        public void a(PluginRecord pluginRecord, float f) {
        }

        /* access modifiers changed from: package-private */
        public void a(PluginRecord pluginRecord, PluginDownloadTaskInternal pluginDownloadTaskInternal) {
        }

        /* access modifiers changed from: package-private */
        public void b(PluginRecord pluginRecord) {
        }

        /* access modifiers changed from: package-private */
        public void b(PluginRecord pluginRecord, PluginDownloadTaskInternal pluginDownloadTaskInternal) {
        }

        /* access modifiers changed from: package-private */
        public void c(PluginRecord pluginRecord) {
        }

        /* access modifiers changed from: package-private */
        public void c(PluginRecord pluginRecord, PluginDownloadTaskInternal pluginDownloadTaskInternal) {
        }

        /* access modifiers changed from: package-private */
        public void d(PluginRecord pluginRecord) {
        }

        private PluginDownloadCallbackInternal() {
            super();
        }
    }

    /* access modifiers changed from: private */
    public void a(final PluginRecord pluginRecord, final PluginDownloadCallbackInternal pluginDownloadCallbackInternal) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", pluginRecord.o());
        } catch (JSONException e2) {
            l(Log.getStackTraceString(e2));
        }
        jSONArray.put(jSONObject);
        int e3 = SystemApi.a().e(this.C);
        String l2 = SystemApi.a().l();
        final PluginDownloadTaskInternal pluginDownloadTaskInternal = new PluginDownloadTaskInternal();
        pluginDownloadTaskInternal.a(pluginRecord);
        this.aM.put(Integer.valueOf(pluginDownloadTaskInternal.a()), pluginDownloadTaskInternal);
        if (pluginDownloadCallbackInternal != null) {
            pluginDownloadCallbackInternal.a(pluginDownloadTaskInternal.i(), pluginDownloadTaskInternal);
        }
        l("Start Download Update Plugin - " + pluginRecord.o());
        PluginSmartHomeApi.a().a(this.C, jSONArray, 100, e3, l2, new CoreAsyncCallback<List<PluginUpdateInfoResult>, CoreError>() {
            public void a(List<PluginUpdateInfoResult> list) {
                if (list == null || list.size() <= 0) {
                    if (pluginDownloadCallbackInternal != null) {
                        PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                        pluginUpdateInfo.c("");
                        pluginRecord.a(pluginUpdateInfo);
                        pluginDownloadCallbackInternal.c(pluginRecord);
                    }
                    Log.e("PluginManager", "updatePlugin result is null/size<=0");
                } else if (!PluginManager.this.aM.containsKey(Integer.valueOf(pluginDownloadTaskInternal.a()))) {
                    if (pluginDownloadCallbackInternal != null) {
                        pluginDownloadCallbackInternal.c(pluginRecord);
                    }
                    Log.e("PluginManager", "download task has benn canceled");
                } else {
                    final PluginUpdateInfoResult pluginUpdateInfoResult = list.get(0);
                    if (pluginUpdateInfoResult.a()) {
                        long j = pluginUpdateInfoResult.c;
                        long j2 = pluginUpdateInfoResult.d;
                        String str = pluginUpdateInfoResult.k;
                        "rn".equalsIgnoreCase(str);
                        PluginManager pluginManager = PluginManager.this;
                        pluginManager.l(pluginRecord.o() + " has Newest Package id  - " + j2);
                        BuiltinPkgResult builtinPkgResult = new BuiltinPkgResult();
                        if (PluginManager.this.a(j, j2, builtinPkgResult)) {
                            PluginManager.this.a(builtinPkgResult.f14664a, j, j2, builtinPkgResult.b);
                        }
                        if (PluginManager.this.l(j2)) {
                            PluginManager pluginManager2 = PluginManager.this;
                            pluginManager2.l(pluginRecord.o() + " has downloaded package  - " + j2);
                            PluginManager.this.a(j, j2, pluginRecord);
                            PluginManager.this.a(false, false, pluginRecord.o());
                            if (pluginDownloadCallbackInternal != null) {
                                pluginDownloadCallbackInternal.a(pluginRecord);
                            }
                            PluginManager.this.aM.remove(Integer.valueOf(pluginDownloadTaskInternal.a()));
                        } else if (PluginManager.this.m(j2)) {
                            boolean unused = PluginManager.this.b(j, j2, pluginRecord);
                            PluginManager.this.a(false, false, pluginRecord.o());
                            if (pluginDownloadCallbackInternal != null) {
                                pluginDownloadCallbackInternal.a(pluginRecord);
                            }
                            PluginManager.this.aM.remove(Integer.valueOf(pluginDownloadTaskInternal.a()));
                        } else if (PluginManager.this.k(j2)) {
                            PluginManager pluginManager3 = PluginManager.this;
                            pluginManager3.l(pluginRecord.o() + " has installed package  - " + j2);
                            boolean unused2 = PluginManager.this.c(j, j2, pluginRecord);
                            PluginManager.this.a(false, false, pluginRecord.o());
                            if (pluginDownloadCallbackInternal != null) {
                                pluginDownloadCallbackInternal.a(pluginRecord);
                            }
                            PluginManager.this.aM.remove(Integer.valueOf(pluginDownloadTaskInternal.a()));
                        } else {
                            pluginDownloadTaskInternal.d(pluginUpdateInfoResult.l);
                            pluginDownloadTaskInternal.a(j);
                            pluginDownloadTaskInternal.b(j2);
                            pluginDownloadTaskInternal.b(str);
                            pluginDownloadTaskInternal.c(pluginUpdateInfoResult.h);
                            pluginDownloadTaskInternal.d(pluginUpdateInfoResult.i);
                            pluginDownloadTaskInternal.c(pluginUpdateInfoResult.j);
                            pluginDownloadTaskInternal.a(pluginUpdateInfoResult.b());
                            if (pluginDownloadCallbackInternal != null) {
                                pluginDownloadCallbackInternal.b(pluginDownloadTaskInternal.i(), pluginDownloadTaskInternal);
                            }
                            pluginDownloadTaskInternal.a((PluginDownloadTaskInternal.DownloadTaskCallbackInternal) new PluginDownloadTaskInternal.DownloadTaskCallbackInternal() {
                                /* access modifiers changed from: package-private */
                                public void a(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                    if (pluginDownloadTaskInternal.a() != pluginDownloadTaskInternal.a() && pluginDownloadCallbackInternal != null) {
                                        pluginDownloadCallbackInternal.a(pluginDownloadTaskInternal.i(), pluginDownloadTaskInternal);
                                    }
                                }

                                /* access modifiers changed from: package-private */
                                public void b(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                    if (pluginDownloadCallbackInternal != null) {
                                        pluginDownloadCallbackInternal.c(pluginDownloadTaskInternal.i(), pluginDownloadTaskInternal);
                                    }
                                }

                                /* access modifiers changed from: package-private */
                                public void a(PluginDownloadTaskInternal pluginDownloadTaskInternal, float f) {
                                    if (pluginDownloadCallbackInternal != null) {
                                        pluginDownloadCallbackInternal.a(pluginDownloadTaskInternal.i(), f);
                                    }
                                }

                                /* access modifiers changed from: package-private */
                                public void c(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                    if ("rn".equalsIgnoreCase(pluginDownloadTaskInternal.e) && pluginDownloadTaskInternal.c == pluginUpdateInfoResult.c && pluginDownloadTaskInternal.i() != null && pluginDownloadTaskInternal.i().i() != null) {
                                        pluginDownloadTaskInternal.i().i().a(pluginUpdateInfoResult.f);
                                        PluginManager.this.a(PluginManager.this.b(pluginDownloadTaskInternal.c, pluginDownloadTaskInternal.d, pluginDownloadTaskInternal.e), pluginDownloadTaskInternal);
                                    }
                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                    if (pluginDownloadCallbackInternal != null) {
                                        pluginDownloadCallbackInternal.a(pluginDownloadTaskInternal.i());
                                    }
                                    PluginManager pluginManager = PluginManager.this;
                                    pluginManager.l("download package success - " + pluginDownloadTaskInternal.c());
                                }

                                /* access modifiers changed from: package-private */
                                public void d(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                    if (pluginDownloadCallbackInternal != null) {
                                        pluginDownloadCallbackInternal.c(pluginDownloadTaskInternal.i());
                                    }
                                    PluginManager pluginManager = PluginManager.this;
                                    pluginManager.l("download package failed - " + pluginDownloadTaskInternal.c());
                                }

                                /* access modifiers changed from: package-private */
                                public void e(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                    if (pluginDownloadCallbackInternal != null) {
                                        pluginDownloadCallbackInternal.d(pluginDownloadTaskInternal.i());
                                    }
                                    PluginManager pluginManager = PluginManager.this;
                                    pluginManager.l("download package cancel - " + pluginDownloadTaskInternal.c());
                                }
                            });
                            PluginManager.this.a(pluginDownloadTaskInternal);
                            PluginManager.this.aM.remove(Integer.valueOf(pluginDownloadTaskInternal.a()));
                            PluginManager pluginManager4 = PluginManager.this;
                            pluginManager4.l("start download package  - " + j2);
                        }
                    } else {
                        if (pluginDownloadCallbackInternal != null) {
                            pluginDownloadCallbackInternal.b(pluginRecord);
                        }
                        PluginManager.this.aM.remove(Integer.valueOf(pluginDownloadTaskInternal.a()));
                    }
                }
            }

            public void a(CoreError coreError) {
                if (pluginDownloadCallbackInternal != null) {
                    pluginDownloadCallbackInternal.c(pluginRecord);
                }
                PluginManager.this.aM.remove(Integer.valueOf(pluginDownloadTaskInternal.a()));
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, PluginDownloadTaskInternal pluginDownloadTaskInternal) {
        if (TextUtils.isEmpty(str) || pluginDownloadTaskInternal == null) {
            LogUtil.b("miot-rn-plugin", "PluginManager project.json downloadFilePath is empty or task is null...");
        } else if (pluginDownloadTaskInternal.i() == null || pluginDownloadTaskInternal.i().i() == null) {
            LogUtil.b("miot-rn-plugin", "PluginManager project.json Record or DownloadedPackageInfo is null...");
        } else {
            String str2 = a(str) + File.separator + "project.json";
            if (!new File(str2).exists()) {
                LogUtil.b("miot-rn-plugin", "PluginManager project.json " + str2 + " is not exists...");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(FileUtils.a(str2));
                jSONObject.put(Constants.Update.e, pluginDownloadTaskInternal.i().i().g());
                FileUtils.a(str2, jSONObject.toString());
            } catch (JSONException e2) {
                LogUtil.b("miot-rn-plugin", "PluginManager project.json " + e2.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0218  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r19, com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.DebugPackageCallbackInternal r20) {
        /*
            r18 = this;
            r7 = r18
            r8 = r20
            boolean r0 = com.xiaomi.smarthome.frame.HostSetting.k
            if (r0 != 0) goto L_0x001c
            if (r19 == 0) goto L_0x0014
            long r0 = r19.b()
            boolean r0 = com.xiaomi.smarthome.setting.PluginSetting.c((long) r0)
            if (r0 != 0) goto L_0x001c
        L_0x0014:
            if (r8 == 0) goto L_0x001b
            java.lang.String r0 = ""
            r8.b(r0)
        L_0x001b:
            return
        L_0x001c:
            long r0 = r19.b()
            boolean r0 = r7.k((long) r0)
            if (r0 == 0) goto L_0x0087
            long r2 = r19.a()
            long r4 = r19.b()
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r0 = r7.o
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0038:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r1 = r1.getValue()
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r1 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r1
            long r10 = r1.e()
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0038
            java.lang.String r1 = r1.o()
            r7.g((java.lang.String) r1)
            goto L_0x0038
        L_0x005a:
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo> r0 = r7.aH
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            java.lang.Object r0 = r0.get(r1)
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r0 = (com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r0
            java.util.List r0 = r0.m()
            java.util.Iterator r0 = r0.iterator()
        L_0x006e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x007e
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            r7.b((long) r2, (long) r4)
            goto L_0x006e
        L_0x007e:
            java.lang.String r1 = r18.d()
            r0 = r18
            r0.a((java.lang.String) r1, (long) r2, (long) r4)
        L_0x0087:
            java.util.List r10 = r19.m()
            java.util.Iterator r0 = r10.iterator()
        L_0x008f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00cb
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r2 = r7.o
            java.lang.Object r1 = r2.get(r1)
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r1 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r1
            if (r1 != 0) goto L_0x00a6
            goto L_0x008f
        L_0x00a6:
            boolean r2 = r1.l()
            r3 = 0
            if (r2 == 0) goto L_0x00bf
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r2 = r1.h()
            long r3 = r2.a()
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r2 = r1.h()
            long r5 = r2.b()
            goto L_0x00c0
        L_0x00bf:
            r5 = r3
        L_0x00c0:
            r7.b((long) r3, (long) r5)
            java.lang.String r1 = r1.o()
            r7.g((java.lang.String) r1)
            goto L_0x008f
        L_0x00cb:
            r18.F()
            java.lang.String r6 = r19.f()
            long r13 = r19.a()
            long r11 = r19.b()
            java.lang.String r15 = r19.i()
            long r1 = r19.a()
            long r3 = r19.b()
            r0 = r18
            r5 = r15
            java.lang.String r5 = r0.c((long) r1, (long) r3, (java.lang.String) r5)
            java.lang.String r0 = "mpk"
            boolean r0 = r15.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x0108
            boolean r0 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.b((java.lang.String) r6, (java.lang.String) r5)
            if (r0 != 0) goto L_0x0106
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r5)
            if (r8 == 0) goto L_0x0105
            java.lang.String r0 = ""
            r8.b(r0)
        L_0x0105:
            return
        L_0x0106:
            r9 = r5
            goto L_0x0123
        L_0x0108:
            java.lang.String r0 = "rn"
            boolean r0 = r0.equalsIgnoreCase(r15)
            if (r0 == 0) goto L_0x0106
            java.lang.String r16 = "rn"
            r0 = r18
            r1 = r13
            r3 = r11
            r9 = r5
            r5 = r16
            java.lang.String r16 = r0.c((long) r1, (long) r3, (java.lang.String) r5)
            r5 = r6
            r6 = r16
            r0.a((long) r1, (long) r3, (java.lang.String) r5, (java.lang.String) r6)
        L_0x0123:
            java.lang.String r0 = r19.i()
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo r0 = r7.b((java.lang.String) r9, (java.lang.String) r0)
            if (r0 == 0) goto L_0x0218
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r1 = r7.aF
            long r2 = r0.b
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x0218
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo> r1 = r7.aF
            long r2 = r0.b
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.Object r1 = r1.get(r2)
            com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo r1 = (com.xiaomi.smarthome.core.entity.plugin.PluginDeveloperInfo) r1
            int r2 = r0.c
            boolean r2 = r7.a((java.lang.String) r15, (int) r2)
            if (r2 != 0) goto L_0x015c
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r9)
            if (r8 == 0) goto L_0x015b
            java.lang.String r0 = ""
            r8.b(r0)
        L_0x015b:
            return
        L_0x015c:
            java.lang.String r2 = r0.d
            boolean r2 = r7.c((java.lang.String) r2, (java.lang.String) r15)
            if (r2 != 0) goto L_0x016f
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r9)
            if (r8 == 0) goto L_0x016e
            java.lang.String r0 = ""
            r8.b(r0)
        L_0x016e:
            return
        L_0x016f:
            java.util.List<java.lang.String> r2 = r0.f
            boolean r2 = r7.a((java.lang.String) r15, (java.util.List<java.lang.String>) r2)
            if (r2 != 0) goto L_0x0182
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r9)
            if (r8 == 0) goto L_0x0181
            java.lang.String r0 = ""
            r8.b(r0)
        L_0x0181:
            return
        L_0x0182:
            java.lang.String r2 = "mpk"
            boolean r2 = r15.equals(r2)
            if (r2 == 0) goto L_0x019c
            com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager r2 = com.xiaomi.smarthome.core.server.internal.plugin.PluginSoManager.a()
            android.content.Context r3 = r7.C
            r4 = r11
            r11 = r2
            r12 = r3
            r2 = r13
            r6 = r15
            r15 = r4
            r17 = r9
            r11.a(r12, r13, r15, r17)
            goto L_0x019f
        L_0x019c:
            r4 = r11
            r2 = r13
            r6 = r15
        L_0x019f:
            com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r14 = new com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo
            r14.<init>()
            r14.a((long) r2)
            r14.b((long) r4)
            r14.a((java.lang.String) r9)
            long r11 = r0.b
            r14.d((long) r11)
            int r11 = r0.c
            r14.b((int) r11)
            java.lang.String r11 = r0.d
            r14.d((java.lang.String) r11)
            int r11 = r0.f14670a
            r14.a((int) r11)
            r14.c((java.lang.String) r6)
            java.lang.String r6 = r0.e
            r14.b((java.lang.String) r6)
            java.util.List<java.lang.String> r6 = r0.f
            r14.a((java.util.List<java.lang.String>) r6)
            boolean r6 = r0.g
            r14.a((boolean) r6)
            boolean r0 = r0.h
            r14.b((boolean) r0)
            long r11 = com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.g(r9)
            r14.c((long) r11)
            r7.a((com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r14)
            java.util.Iterator r0 = r10.iterator()
        L_0x01e6:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x020a
            java.lang.Object r6 = r0.next()
            java.lang.String r6 = (java.lang.String) r6
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r9 = r7.o
            java.lang.Object r6 = r9.get(r6)
            r11 = r6
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r11 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r11
            if (r11 != 0) goto L_0x01fe
            goto L_0x01e6
        L_0x01fe:
            r12 = r2
            r6 = r14
            r14 = r4
            r16 = r6
            r17 = r1
            r11.a(r12, r14, r16, r17)
            r14 = r6
            goto L_0x01e6
        L_0x020a:
            r18.F()
            r0 = 0
            r1 = 1
            r7.a((boolean) r1, (boolean) r1, (java.lang.String) r0)
            if (r8 == 0) goto L_0x0217
            r20.b()
        L_0x0217:
            return
        L_0x0218:
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r9)
            if (r8 == 0) goto L_0x0222
            java.lang.String r0 = ""
            r8.b(r0)
        L_0x0222:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.a(com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo, com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$DebugPackageCallbackInternal):void");
    }

    class DebugPkgResult {

        /* renamed from: a  reason: collision with root package name */
        String f14665a;
        long b;
        long c;
        String d;
        boolean e;
        String f;

        DebugPkgResult() {
        }
    }

    /* access modifiers changed from: private */
    public void a(DebugPackageCallbackInternal debugPackageCallbackInternal) {
        long j2;
        long j3;
        DebugPackageCallbackInternal debugPackageCallbackInternal2 = debugPackageCallbackInternal;
        DebugPkgResult T2 = T();
        if (T2.e) {
            V();
            if (debugPackageCallbackInternal2 != null) {
                debugPackageCallbackInternal2.a(T2.f);
                return;
            }
            return;
        }
        String str = T2.f14665a;
        long j4 = T2.b;
        long j5 = T2.c;
        String str2 = T2.d;
        boolean equalsIgnoreCase = "rn".equalsIgnoreCase(str2);
        PackageRawInfo b2 = b(str, str2);
        if (b2 == null) {
            V();
            if (debugPackageCallbackInternal2 != null) {
                debugPackageCallbackInternal2.a("未获取到插件包配置信息");
            }
        } else if (equalsIgnoreCase || this.aF.containsKey(Long.valueOf(b2.b))) {
            PluginDeveloperInfo pluginDeveloperInfo = this.aF.get(Long.valueOf(b2.b));
            if (!a(b2, pluginDeveloperInfo, str, str2)) {
                V();
                if (debugPackageCallbackInternal2 != null) {
                    debugPackageCallbackInternal2.a("插件包签名错误");
                }
            } else if (!a(str2, b2.c)) {
                V();
                if (debugPackageCallbackInternal2 != null) {
                    debugPackageCallbackInternal2.a("插件包MinApiLevel错误");
                }
            } else if (!c(b2.d, str2)) {
                V();
                if (debugPackageCallbackInternal2 != null) {
                    debugPackageCallbackInternal2.a("插件包Platform错误");
                }
            } else if (!a(str2, b2.f)) {
                V();
                if (debugPackageCallbackInternal2 != null) {
                    debugPackageCallbackInternal2.a("插件包Model错误");
                }
            } else {
                PluginDeveloperInfo pluginDeveloperInfo2 = pluginDeveloperInfo;
                String b3 = b(j4, j5, str2);
                boolean b4 = FileUtils.b(T2.f14665a, b3);
                V();
                if (b4) {
                    PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
                    pluginPackageInfo.a(j4);
                    pluginPackageInfo.b(j5);
                    pluginPackageInfo.a(b3);
                    pluginPackageInfo.d(b2.b);
                    pluginPackageInfo.b(b2.c);
                    pluginPackageInfo.d(b2.d);
                    pluginPackageInfo.a(b2.f14670a);
                    pluginPackageInfo.c(str2);
                    pluginPackageInfo.b(b2.e);
                    pluginPackageInfo.a(b2.f);
                    pluginPackageInfo.a(b2.g);
                    pluginPackageInfo.b(b2.h);
                    pluginPackageInfo.c(FileUtils.g(str));
                    c(pluginPackageInfo);
                    for (String str3 : b2.f) {
                        PluginRecord pluginRecord = this.o.get(str3);
                        if (pluginRecord != null) {
                            j3 = j5;
                            j2 = j4;
                            pluginRecord.b(j4, j3, pluginPackageInfo, pluginDeveloperInfo2);
                        } else {
                            j3 = j5;
                            j2 = j4;
                        }
                        j5 = j3;
                        j4 = j2;
                    }
                    F();
                    a(true, true, (String) null);
                    if (debugPackageCallbackInternal2 != null) {
                        debugPackageCallbackInternal.a();
                    }
                    a(pluginPackageInfo, debugPackageCallbackInternal2);
                } else if (debugPackageCallbackInternal2 != null) {
                    debugPackageCallbackInternal2.a("插件包拷贝失败");
                }
            }
        } else {
            V();
            if (debugPackageCallbackInternal2 != null) {
                debugPackageCallbackInternal2.a("开发者不存在(id:" + b2.b + Operators.BRACKET_END_STR);
            }
        }
    }

    private String g(long j2) {
        return this.F + File.separator + j2 + ".raw";
    }

    /* access modifiers changed from: private */
    public String b(long j2, long j3, String str) {
        return this.H + File.separator + j2 + File.separator + j3 + "." + str.toLowerCase();
    }

    private final String c(long j2, long j3) {
        return this.M + File.separator + j2 + File.separator + j3;
    }

    private String c(long j2, long j3, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.equalsIgnoreCase(i)) {
            return this.K + File.separator + j2 + File.separator + j3 + ".apk";
        } else if (!str.equalsIgnoreCase("rn")) {
            return "";
        } else {
            return this.L + File.separator + j2 + File.separator + j3;
        }
    }

    private String a(long j2, String str) {
        if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase(i)) {
            return "";
        }
        return this.I + File.separator + j2 + ".apk";
    }

    /* access modifiers changed from: private */
    public void a(long j2, long j3, PluginRecord pluginRecord) {
        PluginPackageInfo pluginPackageInfo = this.aJ.get(Long.valueOf(j3));
        if (pluginPackageInfo != null) {
            pluginRecord.b(j2, j3, pluginPackageInfo, this.aF.get(Long.valueOf(pluginPackageInfo.k())));
            F();
            d(j2, j3);
        }
    }

    /* access modifiers changed from: private */
    public void d(long j2, long j3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("plugin_id", j2);
            jSONObject.put(PushRouteUtil.k, j3);
            jSONObject.put("download_type", "local_downloaded");
            StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_download_success", jSONObject.toString(), (String) null, false);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public boolean b(long j2, long j3, PluginRecord pluginRecord) {
        boolean z2;
        long j4 = j2;
        long j5 = j3;
        PluginPackageInfo pluginPackageInfo = this.aQ.get(Long.valueOf(j3));
        pluginPackageInfo.a(j4);
        String f2 = pluginPackageInfo.f();
        String i2 = pluginPackageInfo.i();
        boolean equalsIgnoreCase = "rn".equalsIgnoreCase(i2);
        String b2 = b(j2, j3, i2);
        if (equalsIgnoreCase) {
            z2 = true;
        } else {
            z2 = FileUtils.b(f2, b2);
        }
        PackageRawInfo b3 = b(f2, i2);
        if (equalsIgnoreCase) {
            FileUtils.e(f2);
        } else {
            FileUtils.d(f2);
        }
        f(pluginPackageInfo);
        if (!z2) {
            return false;
        }
        if (b3 == null || (!equalsIgnoreCase && !this.aF.containsKey(Long.valueOf(b3.b)))) {
            FileUtils.d(b2);
            return false;
        }
        PluginPackageInfo pluginPackageInfo2 = new PluginPackageInfo();
        pluginPackageInfo2.a(j4);
        pluginPackageInfo2.b(j5);
        pluginPackageInfo2.a(b2);
        pluginPackageInfo2.d(b3.b);
        pluginPackageInfo2.b(b3.c);
        pluginPackageInfo2.d(b3.d);
        pluginPackageInfo2.a(b3.f14670a);
        pluginPackageInfo2.c(i2);
        pluginPackageInfo2.b(b3.e);
        pluginPackageInfo2.a(b3.f);
        pluginPackageInfo2.a(b3.g);
        pluginPackageInfo2.b(b3.h);
        pluginPackageInfo2.c(FileUtils.g(b2));
        c(pluginPackageInfo2);
        long j6 = j2;
        long j7 = j3;
        PluginPackageInfo pluginPackageInfo3 = pluginPackageInfo2;
        PluginDeveloperInfo pluginDeveloperInfo = this.aF.get(Long.valueOf(b3.b));
        a(j6, j7, pluginPackageInfo3, pluginDeveloperInfo);
        pluginRecord.b(j6, j7, pluginPackageInfo3, pluginDeveloperInfo);
        F();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("plugin_id", j4);
            jSONObject.put(PushRouteUtil.k, j5);
            jSONObject.put("download_type", "local_downloaded_old");
            StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_download_success", jSONObject.toString(), (String) null, false);
        } catch (Exception unused) {
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean c(long j2, long j3, PluginRecord pluginRecord) {
        long j4 = j2;
        long j5 = j3;
        PluginPackageInfo pluginPackageInfo = this.aH.get(Long.valueOf(j3));
        String f2 = pluginPackageInfo.f();
        String i2 = pluginPackageInfo.i();
        String b2 = b(j2, j3, i2);
        boolean equalsIgnoreCase = "rn".equalsIgnoreCase(i2);
        boolean b3 = !equalsIgnoreCase ? FileUtils.b(f2, b2) : false;
        if (!equalsIgnoreCase && !b3) {
            return false;
        }
        PackageRawInfo b4 = equalsIgnoreCase ? b(f2, i2) : b(b2, i2);
        boolean z2 = b4 != null && this.aF.containsKey(Long.valueOf(b4.b));
        if (b4 == null || (!equalsIgnoreCase && !z2)) {
            FileUtils.d(b2);
            return false;
        }
        PluginPackageInfo pluginPackageInfo2 = new PluginPackageInfo();
        pluginPackageInfo2.a(j4);
        pluginPackageInfo2.b(j5);
        pluginPackageInfo2.a(b2);
        pluginPackageInfo2.d(b4.b);
        pluginPackageInfo2.b(b4.c);
        pluginPackageInfo2.d(b4.d);
        pluginPackageInfo2.a(b4.f14670a);
        pluginPackageInfo2.c(i2);
        pluginPackageInfo2.b(b4.e);
        pluginPackageInfo2.a(b4.f);
        pluginPackageInfo2.a(b4.g);
        pluginPackageInfo2.b(b4.h);
        pluginPackageInfo2.c(FileUtils.g(b2));
        c(pluginPackageInfo2);
        long j6 = j2;
        long j7 = j3;
        PluginPackageInfo pluginPackageInfo3 = pluginPackageInfo2;
        PluginDeveloperInfo pluginDeveloperInfo = this.aF.get(Long.valueOf(b4.b));
        a(j6, j7, pluginPackageInfo3, pluginDeveloperInfo);
        pluginRecord.b(j6, j7, pluginPackageInfo3, pluginDeveloperInfo);
        F();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("plugin_id", j4);
            jSONObject.put(PushRouteUtil.k, j5);
            jSONObject.put("download_type", "local_installed");
            StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_download_success", jSONObject.toString(), (String) null, false);
        } catch (Exception unused) {
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean a(String str, long j2, long j3, String str2, List<PluginDownloadTaskInternal> list) {
        PackageRawInfo packageRawInfo;
        boolean z2;
        String str3 = str;
        long j4 = j2;
        long j5 = j3;
        String str4 = str2;
        String b2 = b(j2, j3, str2);
        boolean equalsIgnoreCase = "rn".equalsIgnoreCase(str4);
        if (equalsIgnoreCase) {
            FileUtils.d(b2);
            FileUtils.e(b2);
            if (!ZipFileUtils.a(str3, b2)) {
                FileUtils.d(str);
                return false;
            }
            FileUtils.i(new File(b2, br).getAbsolutePath());
            packageRawInfo = b(b2, str4);
        } else {
            packageRawInfo = b(str3, str4);
        }
        if (packageRawInfo == null || (!"rn".equalsIgnoreCase(str4) && !this.aF.containsKey(Long.valueOf(packageRawInfo.b)))) {
            FileUtils.d(str);
            if (equalsIgnoreCase) {
                FileUtils.e(b2);
            }
            return false;
        }
        PluginDeveloperInfo pluginDeveloperInfo = this.aF.get(Long.valueOf(packageRawInfo.b));
        if (!a(packageRawInfo, pluginDeveloperInfo, equalsIgnoreCase ? b2 : str3, str4)) {
            FileUtils.d(str);
            if (equalsIgnoreCase) {
                FileUtils.e(b2);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#hPDSI# validateSignature fail:");
            sb.append(packageRawInfo != null ? packageRawInfo.a() : "PluginRawInfo[null]");
            sb.append(pluginDeveloperInfo != null ? pluginDeveloperInfo.d() : "DeveloperInfo[null]");
            Log.e("PluginManager", sb.toString());
            return false;
        } else if (!a(str4, packageRawInfo.c)) {
            FileUtils.d(str);
            if (equalsIgnoreCase) {
                FileUtils.e(b2);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("#hPDSI# validateMinApiLevel fail:");
            sb2.append(packageRawInfo != null ? packageRawInfo.a() : "PluginRawInfo[null]");
            sb2.append("CurrentApiLevel[");
            sb2.append(100);
            sb2.append(Operators.ARRAY_END_STR);
            Log.e("PluginManager", sb2.toString());
            return false;
        } else if (!c(packageRawInfo.d, str4)) {
            FileUtils.d(str);
            if (equalsIgnoreCase) {
                FileUtils.e(b2);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("#hPDSI# validatePlatform fail:");
            sb3.append(packageRawInfo != null ? packageRawInfo.a() : "PluginRawInfo[null]");
            sb3.append("CurrentPlatform[");
            sb3.append(SystemApi.a().l());
            sb3.append(Operators.ARRAY_END_STR);
            Log.e("PluginManager", sb3.toString());
            return false;
        } else if (!a(str4, packageRawInfo.f)) {
            FileUtils.d(str);
            if (equalsIgnoreCase) {
                FileUtils.e(b2);
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("#hPDSI# validateModel fail:");
            sb4.append(packageRawInfo != null ? packageRawInfo.a() : "PluginRawInfo[null]");
            Log.e("PluginManager", sb4.toString());
            return false;
        } else {
            if (equalsIgnoreCase) {
                File file = new File(b2);
                z2 = file.exists() && file.isDirectory() && new File(file, br).exists();
            } else {
                z2 = FileUtils.b(str3, b2);
            }
            FileUtils.d(str);
            if (!z2) {
                Log.e("PluginManager", "#hPDSI# copy to Downloaded fail");
                return false;
            }
            PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
            pluginPackageInfo.a(j4);
            pluginPackageInfo.b(j5);
            pluginPackageInfo.a(b2);
            pluginPackageInfo.d(packageRawInfo.b);
            pluginPackageInfo.b(packageRawInfo.c);
            pluginPackageInfo.d(packageRawInfo.d);
            pluginPackageInfo.a(packageRawInfo.f14670a);
            pluginPackageInfo.c(str4);
            pluginPackageInfo.b(packageRawInfo.e);
            pluginPackageInfo.a(packageRawInfo.f);
            pluginPackageInfo.a(packageRawInfo.g);
            pluginPackageInfo.b(packageRawInfo.h);
            pluginPackageInfo.c(FileUtils.g(b2));
            c(pluginPackageInfo);
            a(j2, j3, pluginPackageInfo, pluginDeveloperInfo);
            if (list != null && list.size() > 0) {
                for (PluginDownloadTaskInternal i2 : list) {
                    PluginRecord i3 = i2.i();
                    if (i3 != null) {
                        i3.b(j2, j3, pluginPackageInfo, pluginDeveloperInfo);
                    }
                }
                F();
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("plugin_id", j4);
                jSONObject.put(PushRouteUtil.k, j5);
                jSONObject.put("download_type", "remote");
                StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_download_success", jSONObject.toString(), (String) null, false);
            } catch (Exception unused) {
            }
            return true;
        }
    }

    private void a(long j2, long j3, PluginPackageInfo pluginPackageInfo, PluginDeveloperInfo pluginDeveloperInfo) {
        for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
            PluginRecord pluginRecord = (PluginRecord) value.getValue();
            if ((pluginRecord.k() && pluginRecord.f() == j2) || (pluginRecord.l() && pluginRecord.d() == j2)) {
                pluginRecord.b(j2, j3, pluginPackageInfo, pluginDeveloperInfo);
            }
        }
        F();
    }

    /* access modifiers changed from: private */
    public void a(List<String> list) {
        PluginRecord c2;
        if (list != null && list.size() > 0) {
            for (String next : list) {
                if (!TextUtils.isEmpty(next) && b(next) && (c2 = c(next)) != null && !c2.l() && !c2.k()) {
                    a(c2, (PluginDownloadCallbackInternal) null);
                }
            }
        }
    }

    private static class UpdateAllOperation {

        /* renamed from: a  reason: collision with root package name */
        boolean f14674a;
        UpdateAllCallbackInternal b;

        private UpdateAllOperation() {
        }
    }

    /* access modifiers changed from: private */
    public void a(final boolean z2, final UpdateAllCallbackInternal updateAllCallbackInternal) {
        String str;
        long j2;
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
            PluginRecord pluginRecord = (PluginRecord) value.getValue();
            if (pluginRecord.l()) {
                j2 = pluginRecord.e();
                if (pluginRecord.h() == null) {
                    str = "";
                } else {
                    str = pluginRecord.h().i();
                }
            } else if (pluginRecord.k()) {
                j2 = pluginRecord.g();
                if (pluginRecord.i() == null) {
                    str = "";
                } else {
                    str = pluginRecord.i().i();
                }
            } else {
                str = "";
                j2 = 0;
            }
            if (j2 > 0) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("model", pluginRecord.o());
                    jSONObject.put(PushRouteUtil.k, j2);
                    if (pluginRecord.o().startsWith("lumi.")) {
                        jSONObject.put(PushRouteUtil.k, ErrorCode.CARD_STATUS_ERROR);
                    }
                    jSONObject.put("type", str);
                } catch (JSONException e2) {
                    l(Log.getStackTraceString(e2));
                }
                jSONArray.put(jSONObject);
                l("Start Update All Plugin - " + pluginRecord.o() + ", current package id - " + j2);
            }
        }
        if (jSONArray.length() != 0) {
            PluginSmartHomeApi.a().a(this.C, jSONArray, 100, SystemApi.a().e(this.C), SystemApi.a().l(), new CoreAsyncCallback<List<PluginUpdateInfoResult>, CoreError>() {
                public void a(List<PluginUpdateInfoResult> list) {
                    PluginRecord pluginRecord;
                    if (list != null && list.size() > 0) {
                        for (PluginUpdateInfoResult next : list) {
                            if (next.a()) {
                                long j = next.c;
                                long j2 = next.d;
                                String str = next.k;
                                "rn".equalsIgnoreCase(str);
                                PluginRecord pluginRecord2 = PluginManager.this.o.get(next.f14696a);
                                if (pluginRecord2 != null) {
                                    PluginManager pluginManager = PluginManager.this;
                                    pluginManager.l(pluginRecord2.o() + " has Newest Package id  - " + j2);
                                    BuiltinPkgResult builtinPkgResult = new BuiltinPkgResult();
                                    BuiltinPkgResult builtinPkgResult2 = builtinPkgResult;
                                    if (PluginManager.this.a(j, j2, builtinPkgResult)) {
                                        BuiltinPkgResult builtinPkgResult3 = builtinPkgResult2;
                                        pluginRecord = pluginRecord2;
                                        PluginManager.this.a(builtinPkgResult3.f14664a, j, j2, builtinPkgResult3.b);
                                    } else {
                                        pluginRecord = pluginRecord2;
                                    }
                                    PluginPackageInfo pluginPackageInfo = (PluginPackageInfo) PluginManager.this.aJ.get(Long.valueOf(j2));
                                    if (j2 <= 0 || pluginPackageInfo == null) {
                                        PluginRecord pluginRecord3 = pluginRecord;
                                        if (PluginManager.this.m(j2)) {
                                            boolean unused = PluginManager.this.b(j, j2, pluginRecord3);
                                            pluginRecord3.a((PluginUpdateInfo) null);
                                        } else if (PluginManager.this.k(j2)) {
                                            PluginManager pluginManager2 = PluginManager.this;
                                            pluginManager2.l(pluginRecord3.o() + " have installed package - " + j2);
                                            boolean unused2 = PluginManager.this.c(j, j2, pluginRecord3);
                                            pluginRecord3.a((PluginUpdateInfo) null);
                                        } else if (next.b() || ((WifiUtil.h(PluginManager.this.C) && PluginManager.this.aD) || z2)) {
                                            PluginDownloadTaskInternal pluginDownloadTaskInternal = new PluginDownloadTaskInternal();
                                            pluginDownloadTaskInternal.a(j);
                                            pluginDownloadTaskInternal.b(j2);
                                            pluginDownloadTaskInternal.b(str);
                                            pluginDownloadTaskInternal.d(next.l);
                                            pluginDownloadTaskInternal.c(next.h);
                                            pluginDownloadTaskInternal.d(next.i);
                                            pluginDownloadTaskInternal.c(next.j);
                                            pluginDownloadTaskInternal.a(next.b());
                                            pluginDownloadTaskInternal.a(pluginRecord3);
                                            pluginDownloadTaskInternal.a((PluginDownloadTaskInternal.DownloadTaskCallbackInternal) new PluginDownloadTaskInternal.DownloadTaskCallbackInternal() {
                                                /* access modifiers changed from: package-private */
                                                public void a(PluginDownloadTaskInternal pluginDownloadTaskInternal, float f) {
                                                }

                                                /* access modifiers changed from: package-private */
                                                public void d(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                                }

                                                /* access modifiers changed from: package-private */
                                                public void b(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                                }

                                                /* access modifiers changed from: package-private */
                                                public void a(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                                }

                                                /* access modifiers changed from: package-private */
                                                public void c(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                                }

                                                /* access modifiers changed from: package-private */
                                                public void e(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                                    PluginManager.this.a(false, false, pluginDownloadTaskInternal.i().o());
                                                }
                                            });
                                            PluginManager.this.a(pluginDownloadTaskInternal);
                                            PluginManager pluginManager3 = PluginManager.this;
                                            pluginManager3.l("start update package - " + j2);
                                            pluginRecord3.a((PluginUpdateInfo) null);
                                        } else {
                                            PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                                            pluginUpdateInfo.a(next.f14696a);
                                            pluginUpdateInfo.a(next.c);
                                            pluginUpdateInfo.b(next.d);
                                            pluginUpdateInfo.d(next.k);
                                            pluginUpdateInfo.a(next.f);
                                            pluginUpdateInfo.a(next.b());
                                            pluginUpdateInfo.b(next.g);
                                            pluginUpdateInfo.c(next.h);
                                            pluginRecord3.a(pluginUpdateInfo);
                                        }
                                    } else {
                                        PluginManager pluginManager4 = PluginManager.this;
                                        pluginManager4.l(pluginRecord.o() + " have downloaded package - " + j2);
                                        pluginRecord.b(j, j2, pluginPackageInfo, (PluginDeveloperInfo) PluginManager.this.aF.get(Long.valueOf(pluginPackageInfo.k())));
                                        PluginManager.this.d(j, j2);
                                        pluginRecord.a((PluginUpdateInfo) null);
                                    }
                                }
                            } else {
                                PluginRecord pluginRecord4 = PluginManager.this.o.get(next.f14696a);
                                if (pluginRecord4 != null) {
                                    pluginRecord4.a((PluginUpdateInfo) null);
                                }
                            }
                        }
                        PluginManager.this.F();
                        PluginManager.this.a(true, true, (String) null);
                        if (updateAllCallbackInternal != null) {
                            updateAllCallbackInternal.a();
                        }
                    } else if (updateAllCallbackInternal != null) {
                        updateAllCallbackInternal.b();
                    }
                }

                public void a(CoreError coreError) {
                    if (updateAllCallbackInternal != null) {
                        updateAllCallbackInternal.b();
                    }
                }
            });
        } else if (updateAllCallbackInternal != null) {
            updateAllCallbackInternal.b();
        }
    }

    private abstract class UpdateCallbackInternal extends BaseCallbackInternal {
        /* access modifiers changed from: package-private */
        public abstract void a(PluginRecord pluginRecord);

        /* access modifiers changed from: package-private */
        public abstract void a(PluginRecord pluginRecord, float f);

        /* access modifiers changed from: package-private */
        public abstract void a(PluginRecord pluginRecord, PluginUpdateInfo pluginUpdateInfo);

        /* access modifiers changed from: package-private */
        public abstract void a(PluginRecord pluginRecord, HttpAsyncHandle httpAsyncHandle);

        /* access modifiers changed from: package-private */
        public abstract void b(PluginRecord pluginRecord);

        /* access modifiers changed from: package-private */
        public abstract void b(PluginRecord pluginRecord, HttpAsyncHandle httpAsyncHandle);

        /* access modifiers changed from: package-private */
        public abstract void c(PluginRecord pluginRecord);

        /* access modifiers changed from: package-private */
        public abstract void d(PluginRecord pluginRecord);

        /* access modifiers changed from: package-private */
        public abstract void e(PluginRecord pluginRecord);

        /* access modifiers changed from: package-private */
        public abstract void f(PluginRecord pluginRecord);

        private UpdateCallbackInternal() {
            super();
        }
    }

    private static class UpdateOperation {

        /* renamed from: a  reason: collision with root package name */
        PluginRecord f14676a;
        boolean b;
        UpdateCallbackInternal c;

        private UpdateOperation() {
        }
    }

    private static class RnSdkTask {

        /* renamed from: a  reason: collision with root package name */
        String f14672a;
        long b;
        long c;
        long d;
        long e;
        int f;
        List<Object> g;

        private RnSdkTask() {
            this.c = 0;
            this.d = 0;
            this.f = 0;
            this.g = new CopyOnWriteArrayList();
        }
    }

    /* access modifiers changed from: private */
    public void a(PluginRecord pluginRecord, boolean z2, UpdateCallbackInternal updateCallbackInternal) {
        long j2;
        final PluginRecord pluginRecord2 = pluginRecord;
        final UpdateCallbackInternal updateCallbackInternal2 = updateCallbackInternal;
        String str = "";
        if (pluginRecord.l()) {
            j2 = pluginRecord.e();
            if (pluginRecord.h() == null) {
                str = "";
            } else {
                str = pluginRecord.h().i();
            }
        } else if (pluginRecord.k()) {
            j2 = pluginRecord.g();
            if (pluginRecord.i() == null) {
                str = "";
            } else {
                str = pluginRecord.i().i();
            }
        } else {
            j2 = 0;
        }
        if (j2 > 0) {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("model", pluginRecord.o());
                jSONObject.put(PushRouteUtil.k, j2);
                jSONObject.put("type", str);
            } catch (JSONException e2) {
                l(Log.getStackTraceString(e2));
            }
            jSONArray.put(jSONObject);
            int e3 = SystemApi.a().e(this.C);
            String l2 = SystemApi.a().l();
            l("Start Update Plugin - " + pluginRecord.o() + ", current package id - " + j2);
            final boolean z3 = z2;
            PluginSmartHomeApi.a().a(this.C, jSONArray, 100, e3, l2, new CoreAsyncCallback<List<PluginUpdateInfoResult>, CoreError>() {
                public void a(List<PluginUpdateInfoResult> list) {
                    if (list != null && list.size() > 0) {
                        PluginUpdateInfoResult pluginUpdateInfoResult = list.get(0);
                        if (pluginUpdateInfoResult.a()) {
                            long j = pluginUpdateInfoResult.c;
                            long j2 = pluginUpdateInfoResult.d;
                            String str = pluginUpdateInfoResult.k;
                            "rn".equalsIgnoreCase(str);
                            PluginManager pluginManager = PluginManager.this;
                            pluginManager.l(pluginRecord2.o() + " has Newest Package id  - " + j2);
                            BuiltinPkgResult builtinPkgResult = new BuiltinPkgResult();
                            if (PluginManager.this.a(j, j2, builtinPkgResult)) {
                                PluginManager.this.a(builtinPkgResult.f14664a, j, j2, builtinPkgResult.b);
                            }
                            if (PluginManager.this.l(j2)) {
                                PluginManager pluginManager2 = PluginManager.this;
                                pluginManager2.l(pluginRecord2.o() + " have downloaded package - " + j2);
                                PluginManager.this.a(j, j2, pluginRecord2);
                                pluginRecord2.a((PluginUpdateInfo) null);
                                PluginManager.this.a(false, false, pluginRecord2.o());
                                if (updateCallbackInternal2 != null) {
                                    updateCallbackInternal2.b(pluginRecord2);
                                }
                                PluginManager.this.c(pluginUpdateInfoResult.l);
                            } else if (PluginManager.this.m(j2)) {
                                boolean unused = PluginManager.this.b(j, j2, pluginRecord2);
                                pluginRecord2.a((PluginUpdateInfo) null);
                                PluginManager.this.a(false, false, pluginRecord2.o());
                                if (updateCallbackInternal2 != null) {
                                    updateCallbackInternal2.b(pluginRecord2);
                                }
                            } else if (PluginManager.this.k(j2)) {
                                PluginManager pluginManager3 = PluginManager.this;
                                pluginManager3.l(pluginRecord2.o() + " have installed package - " + j2);
                                boolean unused2 = PluginManager.this.c(j, j2, pluginRecord2);
                                pluginRecord2.a((PluginUpdateInfo) null);
                                PluginManager.this.a(false, false, pluginRecord2.o());
                                if (updateCallbackInternal2 != null) {
                                    updateCallbackInternal2.b(pluginRecord2);
                                }
                                PluginManager.this.c(pluginUpdateInfoResult.l);
                            } else {
                                boolean z = z3;
                                if ("rn".equalsIgnoreCase(str)) {
                                    z = true;
                                }
                                if (pluginUpdateInfoResult.b() || ((WifiUtil.h(PluginManager.this.C) && PluginManager.this.aD) || z)) {
                                    PluginDownloadTaskInternal pluginDownloadTaskInternal = new PluginDownloadTaskInternal();
                                    pluginDownloadTaskInternal.a(j);
                                    pluginDownloadTaskInternal.b(j2);
                                    pluginDownloadTaskInternal.b(str);
                                    pluginDownloadTaskInternal.d(pluginUpdateInfoResult.l);
                                    pluginDownloadTaskInternal.c(pluginUpdateInfoResult.h);
                                    pluginDownloadTaskInternal.d(pluginUpdateInfoResult.i);
                                    pluginDownloadTaskInternal.c(pluginUpdateInfoResult.j);
                                    pluginDownloadTaskInternal.a(pluginUpdateInfoResult.b());
                                    pluginDownloadTaskInternal.a(pluginRecord2);
                                    pluginDownloadTaskInternal.a((PluginDownloadTaskInternal.DownloadTaskCallbackInternal) new PluginDownloadTaskInternal.DownloadTaskCallbackInternal() {
                                        /* access modifiers changed from: package-private */
                                        public void a(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                            PluginManager.this.a(false, false, pluginRecord2.o());
                                            if (updateCallbackInternal2 != null) {
                                                updateCallbackInternal2.b(pluginDownloadTaskInternal.i(), pluginDownloadTaskInternal.j());
                                            }
                                        }

                                        /* access modifiers changed from: package-private */
                                        public void b(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                            PluginManager.this.a(false, false, pluginRecord2.o());
                                            if (updateCallbackInternal2 != null) {
                                                updateCallbackInternal2.a(pluginDownloadTaskInternal.i(), pluginDownloadTaskInternal.j());
                                            }
                                        }

                                        /* access modifiers changed from: package-private */
                                        public void a(PluginDownloadTaskInternal pluginDownloadTaskInternal, float f) {
                                            if (updateCallbackInternal2 != null) {
                                                updateCallbackInternal2.a(pluginDownloadTaskInternal.i(), f);
                                            }
                                        }

                                        /* access modifiers changed from: package-private */
                                        public void c(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                            PluginManager.this.a(false, false, pluginRecord2.o());
                                            if (updateCallbackInternal2 != null) {
                                                updateCallbackInternal2.c(pluginDownloadTaskInternal.i());
                                            }
                                        }

                                        /* access modifiers changed from: package-private */
                                        public void d(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                            if (updateCallbackInternal2 != null) {
                                                updateCallbackInternal2.d(pluginDownloadTaskInternal.i());
                                            }
                                        }

                                        /* access modifiers changed from: package-private */
                                        public void e(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                                            PluginManager.this.a(false, false, pluginRecord2.o());
                                            if (updateCallbackInternal2 != null) {
                                                updateCallbackInternal2.e(pluginDownloadTaskInternal.i());
                                            }
                                        }
                                    });
                                    PluginManager pluginManager4 = PluginManager.this;
                                    pluginManager4.l("force update package - " + j2);
                                    PluginManager.this.a(pluginDownloadTaskInternal);
                                    pluginRecord2.a((PluginUpdateInfo) null);
                                    return;
                                }
                                PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                                pluginUpdateInfo.a(pluginUpdateInfoResult.f14696a);
                                pluginUpdateInfo.a(pluginUpdateInfoResult.c);
                                pluginUpdateInfo.b(pluginUpdateInfoResult.d);
                                pluginUpdateInfo.d(pluginUpdateInfoResult.k);
                                pluginUpdateInfo.a(pluginUpdateInfoResult.f);
                                pluginUpdateInfo.a(pluginUpdateInfoResult.b());
                                pluginUpdateInfo.b(pluginUpdateInfoResult.g);
                                pluginUpdateInfo.c(pluginUpdateInfoResult.h);
                                pluginRecord2.a(pluginUpdateInfo);
                                PluginManager.this.a(false, false, pluginRecord2.o());
                                if (updateCallbackInternal2 != null) {
                                    updateCallbackInternal2.a(pluginRecord2, pluginUpdateInfo);
                                }
                            }
                        } else {
                            pluginRecord2.a((PluginUpdateInfo) null);
                            PluginManager.this.a(false, false, pluginRecord2.o());
                            if (updateCallbackInternal2 != null) {
                                updateCallbackInternal2.a(pluginRecord2);
                            }
                        }
                    } else if (updateCallbackInternal2 != null) {
                        updateCallbackInternal2.f(pluginRecord2);
                    }
                }

                public void a(CoreError coreError) {
                    if (updateCallbackInternal2 != null) {
                        updateCallbackInternal2.f(pluginRecord2);
                    }
                }
            });
        } else if (updateCallbackInternal2 != null) {
            updateCallbackInternal2.f(pluginRecord2);
        }
    }

    public static final String a(String str) {
        if (new File(str, br).exists()) {
            return new File(str, "android").getAbsolutePath();
        }
        return new File(str, bu).getAbsolutePath();
    }

    public String a(long j2, int i2) {
        String str = this.N + File.separator + j2;
        if (i2 <= 5) {
            return str + ".zip";
        } else if (i2 < 7) {
            return str + "_install";
        } else {
            return str + File.separator;
        }
    }

    private String a(RnSdkTask rnSdkTask) {
        return a(rnSdkTask.b, rnSdkTask.f);
    }

    public PluginSetting.RnSdkInfo a(Context context) {
        String a2 = FileUtils.a(context);
        PluginSetting.RnSdkInfo a3 = PluginSetting.a(context, a2);
        String str = a2 + D;
        String a4 = FileUtils.a(str);
        if (TextUtils.isEmpty(a4)) {
            return a3;
        }
        try {
            JSONObject jSONObject = new JSONObject(a4);
            PluginSetting.RnSdkInfo rnSdkInfo = new PluginSetting.RnSdkInfo(jSONObject.getLong("max_api_level"), jSONObject.getLong("build_time"), jSONObject.getString("real_installed_path"), false);
            if (a3.f22090a > rnSdkInfo.f22090a) {
                FileUtils.d(str);
                return a3;
            } else if (a3.f22090a != rnSdkInfo.f22090a || a3.b <= rnSdkInfo.b) {
                PluginSetting.a("load rn sdk info from file", Long.valueOf(rnSdkInfo.f22090a), rnSdkInfo.c);
                return rnSdkInfo;
            } else {
                FileUtils.d(str);
                return a3;
            }
        } catch (Exception e2) {
            l(Log.getStackTraceString(e2));
            return a3;
        }
    }

    private static void c(String str, long j2, long j3, String str2) {
        String str3 = "{\"max_api_level\":" + j2 + ",\"build_time\":" + j3 + ",\"real_installed_path\":\"" + str2 + "\"}";
        FileUtils.a(new File(str), str3.getBytes());
        PluginSetting.a("write rn sdk info", str3, str);
    }

    /* access modifiers changed from: private */
    public static final String b(long j2, boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append(z2 ? "rn_sdk_timestamp_" : "rn_sdk_timestamp_new");
        sb.append(j2);
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public boolean h(long j2) {
        String a2 = a(j2, 7);
        if (new File(a2, br).exists()) {
            return true;
        }
        return new File(a2, bs).exists();
    }

    public void c(long j2) {
        if (j2 < 10034) {
            PluginSetting.a("checkToUpdateRnSdk error 内置sdk level=10034  rnSdkApiLevel=" + j2);
            return;
        }
        a(j2, (PluginDownloadTaskInternal) null);
    }

    private void a(long j2, PluginDownloadTaskInternal pluginDownloadTaskInternal) {
        RnSdkTask rnSdkTask = this.aK.get(Long.valueOf(j2));
        if (rnSdkTask != null) {
            if (rnSdkTask.f == 1) {
                rnSdkTask.f = 0;
            }
            rnSdkTask.g.add(pluginDownloadTaskInternal);
            b(rnSdkTask);
            return;
        }
        String string = this.aA.getString(b(j2, true), "");
        if (!TextUtils.isEmpty(string)) {
            if (string.startsWith("!")) {
                if (10034 <= a(string.substring("!".length()), 0)) {
                    if (pluginDownloadTaskInternal != null && pluginDownloadTaskInternal.k() != null) {
                        pluginDownloadTaskInternal.b(by, true);
                        pluginDownloadTaskInternal.k().d(pluginDownloadTaskInternal);
                        return;
                    }
                    return;
                }
            } else if (string.startsWith("#")) {
                long a2 = a(string.substring("#".length()), 0);
                if (a2 > 0 && h(j2)) {
                    if (pluginDownloadTaskInternal != null) {
                        b(pluginDownloadTaskInternal);
                    }
                    RnSdkTask rnSdkTask2 = new RnSdkTask();
                    rnSdkTask2.b = j2;
                    rnSdkTask2.f = 0;
                    rnSdkTask2.c = a2;
                    this.aK.put(Long.valueOf(rnSdkTask2.b), rnSdkTask2);
                    b(rnSdkTask2);
                    return;
                }
            }
        }
        RnSdkTask rnSdkTask3 = new RnSdkTask();
        rnSdkTask3.b = j2;
        rnSdkTask3.f = 0;
        rnSdkTask3.c = 0;
        if (pluginDownloadTaskInternal != null) {
            rnSdkTask3.g.add(pluginDownloadTaskInternal);
        }
        this.aK.put(Long.valueOf(rnSdkTask3.b), rnSdkTask3);
        b(rnSdkTask3);
    }

    private boolean j(String str) {
        return a(str, (RnSdkTask) null);
    }

    private boolean a(String str, RnSdkTask rnSdkTask) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        X509Certificate x509Certificate;
        X509Certificate x509Certificate2;
        long j2;
        RnSdkTask rnSdkTask2 = rnSdkTask;
        long currentTimeMillis = System.currentTimeMillis();
        String a2 = a(str);
        File file = new File(a2, "project.json");
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(a2, "bundle.sign");
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(a2, "bundle.cert");
        if (!file3.exists()) {
            return false;
        }
        File file4 = new File(a2, PluginSetting.e);
        if (!file4.exists()) {
            file4 = new File(a2, PluginSetting.d);
            if (!file4.exists()) {
                return false;
            }
        }
        FileInputStream fileInputStream4 = null;
        try {
            String a3 = FileUtils.a(file3.getAbsolutePath());
            if (TextUtils.isEmpty(a3)) {
                IOUtils.a((InputStream) null);
                IOUtils.a((InputStream) null);
                IOUtils.a((InputStream) null);
                return false;
            }
            if (a3.charAt(0) != '-') {
                a3 = a3.trim();
            }
            int lastIndexOf = a3.lastIndexOf(bB);
            if (lastIndexOf < 0) {
                Log.v("PluginManager", " certs of rn plugin is invalid");
                IOUtils.a((InputStream) null);
                IOUtils.a((InputStream) null);
                IOUtils.a((InputStream) null);
                return false;
            }
            X509Certificate c2 = SecurityChipUtil.c();
            if (lastIndexOf > 0) {
                String trim = a3.substring(0, lastIndexOf).trim();
                String trim2 = a3.substring(lastIndexOf).trim();
                x509Certificate = X509Certificate.getInstance(trim.getBytes());
                if (SecurityChipUtil.a(c2, x509Certificate)) {
                    x509Certificate2 = X509Certificate.getInstance(trim2.getBytes());
                } else {
                    Log.v("PluginManager", " cert1 of rn plugin is invalid");
                    IOUtils.a((InputStream) null);
                    IOUtils.a((InputStream) null);
                    IOUtils.a((InputStream) null);
                    return false;
                }
            } else {
                x509Certificate2 = X509Certificate.getInstance(a3.getBytes());
                x509Certificate = c2;
            }
            if (!SecurityChipUtil.a(x509Certificate, x509Certificate2)) {
                Log.v("PluginManager", " cert of rn plugin is invalid");
                IOUtils.a((InputStream) null);
                IOUtils.a((InputStream) null);
                IOUtils.a((InputStream) null);
                return false;
            }
            byte[] bArr = new byte[((int) file.length())];
            fileInputStream2 = new FileInputStream(file);
            try {
                fileInputStream2.read(bArr);
                fileInputStream2.close();
                JSONObject jSONObject = new JSONObject(new String(bArr));
                int optInt = jSONObject.optInt("signature", -1);
                MessageDigest instance = MessageDigest.getInstance(bA);
                instance.update(bArr);
                byte[] digest = instance.digest();
                if (rnSdkTask2 != null) {
                    rnSdkTask2.d = jSONObject.optLong("build_time", 0);
                }
                long length = file4.length();
                if (optInt > 1024) {
                    j2 = (long) optInt;
                    if (length > j2) {
                        byte[] bArr2 = new byte[((int) j2)];
                        fileInputStream3 = new FileInputStream(file4);
                        fileInputStream3.read(bArr2);
                        MessageDigest instance2 = MessageDigest.getInstance(bA);
                        instance2.update(bArr2);
                        byte[] digest2 = instance2.digest();
                        byte[] bArr3 = new byte[((int) file2.length())];
                        fileInputStream = new FileInputStream(file2);
                        fileInputStream.read(bArr3);
                        fileInputStream.close();
                        java.security.Signature instance3 = java.security.Signature.getInstance(bz);
                        instance3.initVerify(x509Certificate2.getPublicKey());
                        byte[] bArr4 = new byte[(digest.length + digest2.length)];
                        System.arraycopy(digest, 0, bArr4, 0, digest.length);
                        System.arraycopy(digest2, 0, bArr4, digest.length, digest2.length);
                        instance3.update(bArr4);
                        boolean verify = instance3.verify(bArr3);
                        Log.i("PluginManager", "signature of rn bundle is " + verify + ">" + (System.currentTimeMillis() - currentTimeMillis));
                        IOUtils.a((InputStream) fileInputStream2);
                        IOUtils.a((InputStream) fileInputStream3);
                        IOUtils.a((InputStream) fileInputStream);
                        return verify;
                    }
                }
                j2 = length;
                byte[] bArr22 = new byte[((int) j2)];
                fileInputStream3 = new FileInputStream(file4);
                try {
                    fileInputStream3.read(bArr22);
                    MessageDigest instance22 = MessageDigest.getInstance(bA);
                    instance22.update(bArr22);
                    byte[] digest22 = instance22.digest();
                    byte[] bArr32 = new byte[((int) file2.length())];
                    fileInputStream = new FileInputStream(file2);
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = null;
                    fileInputStream4 = fileInputStream2;
                    try {
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream4;
                        fileInputStream4 = fileInputStream3;
                        IOUtils.a((InputStream) fileInputStream2);
                        IOUtils.a((InputStream) fileInputStream4);
                        IOUtils.a((InputStream) fileInputStream);
                        throw th;
                    }
                    try {
                        l(Log.getStackTraceString(e));
                        IOUtils.a((InputStream) fileInputStream4);
                        IOUtils.a((InputStream) fileInputStream3);
                        IOUtils.a((InputStream) fileInputStream);
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream4;
                        fileInputStream4 = fileInputStream3;
                        IOUtils.a((InputStream) fileInputStream2);
                        IOUtils.a((InputStream) fileInputStream4);
                        IOUtils.a((InputStream) fileInputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                    fileInputStream4 = fileInputStream3;
                    IOUtils.a((InputStream) fileInputStream2);
                    IOUtils.a((InputStream) fileInputStream4);
                    IOUtils.a((InputStream) fileInputStream);
                    throw th;
                }
                try {
                    fileInputStream.read(bArr32);
                    fileInputStream.close();
                    java.security.Signature instance32 = java.security.Signature.getInstance(bz);
                    instance32.initVerify(x509Certificate2.getPublicKey());
                    byte[] bArr42 = new byte[(digest.length + digest22.length)];
                    System.arraycopy(digest, 0, bArr42, 0, digest.length);
                    System.arraycopy(digest22, 0, bArr42, digest.length, digest22.length);
                    instance32.update(bArr42);
                    boolean verify2 = instance32.verify(bArr32);
                    Log.i("PluginManager", "signature of rn bundle is " + verify2 + ">" + (System.currentTimeMillis() - currentTimeMillis));
                    IOUtils.a((InputStream) fileInputStream2);
                    IOUtils.a((InputStream) fileInputStream3);
                    IOUtils.a((InputStream) fileInputStream);
                    return verify2;
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream4 = fileInputStream2;
                    l(Log.getStackTraceString(e));
                    IOUtils.a((InputStream) fileInputStream4);
                    IOUtils.a((InputStream) fileInputStream3);
                    IOUtils.a((InputStream) fileInputStream);
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    fileInputStream4 = fileInputStream3;
                    IOUtils.a((InputStream) fileInputStream2);
                    IOUtils.a((InputStream) fileInputStream4);
                    IOUtils.a((InputStream) fileInputStream);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                fileInputStream3 = null;
                fileInputStream = null;
                fileInputStream4 = fileInputStream2;
                l(Log.getStackTraceString(e));
                IOUtils.a((InputStream) fileInputStream4);
                IOUtils.a((InputStream) fileInputStream3);
                IOUtils.a((InputStream) fileInputStream);
                return false;
            } catch (Throwable th5) {
                th = th5;
                fileInputStream = null;
                IOUtils.a((InputStream) fileInputStream2);
                IOUtils.a((InputStream) fileInputStream4);
                IOUtils.a((InputStream) fileInputStream);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            fileInputStream3 = null;
            fileInputStream = null;
            l(Log.getStackTraceString(e));
            IOUtils.a((InputStream) fileInputStream4);
            IOUtils.a((InputStream) fileInputStream3);
            IOUtils.a((InputStream) fileInputStream);
            return false;
        } catch (Throwable th6) {
            th = th6;
            fileInputStream2 = null;
            fileInputStream = null;
            IOUtils.a((InputStream) fileInputStream2);
            IOUtils.a((InputStream) fileInputStream4);
            IOUtils.a((InputStream) fileInputStream);
            throw th;
        }
    }

    public void a(Map<String, Object> map) {
        File file = new File((String) map.get("installed_path"));
        for (File file2 : file.getParentFile().listFiles()) {
            if (!file2.equals(file)) {
                if (file2.isFile()) {
                    FileUtils.d(file2.getAbsolutePath());
                } else if (file2.getName().matches("^\\d+$")) {
                    FileUtils.e(file2.getAbsolutePath());
                }
            }
        }
    }

    public boolean a(long j2, long j3, String str, String str2) {
        if (new File(str).isFile()) {
            String c2 = c(j2, j3);
            FileUtils.e(c2);
            if (!ZipFileUtils.a(str, c2)) {
                FileUtils.e(c2);
                return false;
            }
            FileUtils.i(new File(c2, br).getAbsolutePath());
            FileUtils.d(str);
            if (!j(c2)) {
                FileUtils.e(c2);
                return false;
            }
            str = c2;
        }
        FileUtils.e(str2);
        FileUtils.c(str, str2);
        FileUtils.e(str);
        HashMap hashMap = new HashMap();
        hashMap.put("plugin_id", Long.valueOf(j2));
        hashMap.put(PushRouteUtil.k, Long.valueOf(j3));
        hashMap.put("installed_path", str2);
        this.n.obtainMessage(25, hashMap).sendToTarget();
        PluginPackageInfo remove = this.aJ.remove(Long.valueOf(j3));
        if (remove == null) {
            return true;
        }
        this.aI.remove(remove);
        return true;
    }

    /* access modifiers changed from: private */
    public void b(RnSdkTask rnSdkTask) {
        this.n.obtainMessage(24, rnSdkTask).sendToTarget();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x016c, code lost:
        r1 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal) r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(final com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask r10) {
        /*
            r9 = this;
            if (r10 != 0) goto L_0x0003
            return
        L_0x0003:
            monitor-enter(r10)
            int r0 = r10.f     // Catch:{ all -> 0x0229 }
            r1 = 10
            r2 = 1
            if (r0 == r1) goto L_0x01d0
            switch(r0) {
                case 0: goto L_0x01b4;
                case 1: goto L_0x01d0;
                case 2: goto L_0x0227;
                case 3: goto L_0x0158;
                case 4: goto L_0x0227;
                case 5: goto L_0x0137;
                case 6: goto L_0x00b8;
                case 7: goto L_0x0010;
                default: goto L_0x000e;
            }     // Catch:{ all -> 0x0229 }
        L_0x000e:
            goto L_0x0227
        L_0x0010:
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$RnSdkTask> r0 = r9.aK     // Catch:{ all -> 0x0229 }
            long r3 = r10.b     // Catch:{ all -> 0x0229 }
            java.lang.Long r1 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0229 }
            r0.remove(r1)     // Catch:{ all -> 0x0229 }
            java.lang.String r0 = r9.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r10)     // Catch:{ all -> 0x0229 }
            java.lang.String r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0229 }
            android.content.SharedPreferences r1 = r9.aA     // Catch:{ all -> 0x0229 }
            android.content.SharedPreferences$Editor r1 = r1.edit()     // Catch:{ all -> 0x0229 }
            long r3 = r10.b     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = b((long) r3, (boolean) r2)     // Catch:{ all -> 0x0229 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0229 }
            r3.<init>()     // Catch:{ all -> 0x0229 }
            java.lang.String r4 = "#"
            r3.append(r4)     // Catch:{ all -> 0x0229 }
            long r4 = r10.c     // Catch:{ all -> 0x0229 }
            r3.append(r4)     // Catch:{ all -> 0x0229 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0229 }
            r1.putString(r2, r3)     // Catch:{ all -> 0x0229 }
            r1.apply()     // Catch:{ all -> 0x0229 }
            r1 = 100000(0x186a0, float:1.4013E-40)
            android.content.Context r2 = r9.C     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.setting.PluginSetting$RnSdkInfo r2 = r9.a((android.content.Context) r2)     // Catch:{ all -> 0x0229 }
            if (r2 == 0) goto L_0x005b
            long r2 = r2.f22090a     // Catch:{ all -> 0x0229 }
            long r4 = r10.b     // Catch:{ all -> 0x0229 }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x006c
        L_0x005b:
            long r2 = r10.d     // Catch:{ all -> 0x0229 }
            long r4 = (long) r1     // Catch:{ all -> 0x0229 }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x006c
            java.lang.String r3 = r9.O     // Catch:{ all -> 0x0229 }
            long r4 = r10.b     // Catch:{ all -> 0x0229 }
            long r6 = r10.d     // Catch:{ all -> 0x0229 }
            r8 = r0
            c((java.lang.String) r3, (long) r4, (long) r6, (java.lang.String) r8)     // Catch:{ all -> 0x0229 }
        L_0x006c:
            java.util.List<java.lang.Object> r2 = r10.g     // Catch:{ all -> 0x0229 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0229 }
        L_0x0072:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0229 }
            if (r3 == 0) goto L_0x0090
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0229 }
            boolean r4 = r3 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal     // Catch:{ all -> 0x0229 }
            if (r4 == 0) goto L_0x0086
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PluginDownloadTaskInternal r3 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal) r3     // Catch:{ all -> 0x0229 }
            r9.b((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal) r3)     // Catch:{ all -> 0x0229 }
            goto L_0x0072
        L_0x0086:
            boolean r4 = r3 instanceof com.xiaomi.smarthome.device.api.Callback     // Catch:{ all -> 0x0229 }
            if (r4 == 0) goto L_0x0072
            com.xiaomi.smarthome.device.api.Callback r3 = (com.xiaomi.smarthome.device.api.Callback) r3     // Catch:{ all -> 0x0229 }
            r3.onSuccess(r10)     // Catch:{ all -> 0x0229 }
            goto L_0x0072
        L_0x0090:
            long r2 = r10.d     // Catch:{ all -> 0x0229 }
            long r4 = (long) r1     // Catch:{ all -> 0x0229 }
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x0227
            android.content.Intent r1 = new android.content.Intent     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = "downrnsdk_action"
            r1.<init>(r2)     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = "sdk_api_level"
            long r3 = r10.b     // Catch:{ all -> 0x0229 }
            r1.putExtra(r2, r3)     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = "timestamp"
            long r3 = r10.c     // Catch:{ all -> 0x0229 }
            r1.putExtra(r2, r3)     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = "path"
            r1.putExtra(r2, r0)     // Catch:{ all -> 0x0229 }
            android.content.Context r0 = r9.C     // Catch:{ all -> 0x0229 }
            r0.sendBroadcast(r1)     // Catch:{ all -> 0x0229 }
            goto L_0x0227
        L_0x00b8:
            java.lang.String r0 = r9.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r10)     // Catch:{ all -> 0x0229 }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0229 }
            java.lang.String r3 = "android"
            r1.<init>(r0, r3)     // Catch:{ all -> 0x0229 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0229 }
            java.lang.String r4 = "conf.json"
            r3.<init>(r0, r4)     // Catch:{ all -> 0x0229 }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.i(r3)     // Catch:{ all -> 0x0229 }
            boolean r3 = r9.a((java.lang.String) r0, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r10)     // Catch:{ all -> 0x0229 }
            if (r3 == 0) goto L_0x012d
            long r2 = r10.b     // Catch:{ all -> 0x0229 }
            r0 = 7
            java.lang.String r2 = r9.a((long) r2, (int) r0)     // Catch:{ all -> 0x0229 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0229 }
            java.lang.String r4 = "conf.json"
            r3.<init>(r2, r4)     // Catch:{ all -> 0x0229 }
            boolean r4 = r3.exists()     // Catch:{ all -> 0x0229 }
            if (r4 == 0) goto L_0x0112
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x0229 }
            java.lang.String r5 = "_android"
            r4.<init>(r2, r5)     // Catch:{ all -> 0x0229 }
            java.lang.String r5 = r4.getAbsolutePath()     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.e(r5)     // Catch:{ all -> 0x0229 }
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x0229 }
            java.lang.String r6 = "_conf.json"
            r5.<init>(r2, r6)     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = r5.getAbsolutePath()     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.i(r2)     // Catch:{ all -> 0x0229 }
            r1.renameTo(r4)     // Catch:{ all -> 0x0229 }
            java.lang.String r1 = r3.getAbsolutePath()     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r1)     // Catch:{ all -> 0x0229 }
            goto L_0x012a
        L_0x0112:
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x0229 }
            java.lang.String r5 = "android"
            r4.<init>(r2, r5)     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = r4.getAbsolutePath()     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.e(r2)     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = r3.getAbsolutePath()     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.i(r2)     // Catch:{ all -> 0x0229 }
            r1.renameTo(r4)     // Catch:{ all -> 0x0229 }
        L_0x012a:
            r10.f = r0     // Catch:{ all -> 0x0229 }
            goto L_0x0132
        L_0x012d:
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.e(r0)     // Catch:{ all -> 0x0229 }
            r10.f = r2     // Catch:{ all -> 0x0229 }
        L_0x0132:
            r9.b((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r10)     // Catch:{ all -> 0x0229 }
            goto L_0x0227
        L_0x0137:
            java.lang.String r0 = r9.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r10)     // Catch:{ all -> 0x0229 }
            long r3 = r10.b     // Catch:{ all -> 0x0229 }
            r1 = 6
            java.lang.String r3 = r9.a((long) r3, (int) r1)     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.e(r3)     // Catch:{ all -> 0x0229 }
            boolean r3 = com.xiaomi.smarthome.core.server.internal.plugin.util.ZipFileUtils.a((java.lang.String) r0, (java.lang.String) r3)     // Catch:{ all -> 0x0229 }
            if (r3 != 0) goto L_0x014e
            r10.f = r2     // Catch:{ all -> 0x0229 }
            goto L_0x0150
        L_0x014e:
            r10.f = r1     // Catch:{ all -> 0x0229 }
        L_0x0150:
            r9.b((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r10)     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.d(r0)     // Catch:{ all -> 0x0229 }
            goto L_0x0227
        L_0x0158:
            java.util.List<java.lang.Object> r0 = r10.g     // Catch:{ all -> 0x0229 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0229 }
        L_0x015e:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0229 }
            if (r1 == 0) goto L_0x019d
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0229 }
            boolean r3 = r1 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal     // Catch:{ all -> 0x0229 }
            if (r3 == 0) goto L_0x015e
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PluginDownloadTaskInternal r1 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal) r1     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PluginDownloadTaskInternal$DownloadTaskCallbackInternal r3 = r1.k()     // Catch:{ all -> 0x0229 }
            if (r3 == 0) goto L_0x015e
            java.lang.String r4 = "started"
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x0229 }
            java.lang.Object r4 = r1.a(r4, r5)     // Catch:{ all -> 0x0229 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0229 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0229 }
            if (r4 != 0) goto L_0x015e
            java.lang.String r4 = "started"
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0229 }
            r1.b(r4, r5)     // Catch:{ all -> 0x0229 }
            java.lang.String r4 = "download_sdk"
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0229 }
            r1.b(r4, r5)     // Catch:{ all -> 0x0229 }
            r3.a(r1)     // Catch:{ all -> 0x0229 }
            goto L_0x015e
        L_0x019d:
            r0 = 4
            r10.f = r0     // Catch:{ all -> 0x0229 }
            java.lang.String r2 = r10.f14672a     // Catch:{ all -> 0x0229 }
            r3 = 0
            long r4 = r10.e     // Catch:{ all -> 0x0229 }
            java.lang.String r6 = r9.a((com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.RnSdkTask) r10)     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$10 r7 = new com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$10     // Catch:{ all -> 0x0229 }
            r7.<init>(r10)     // Catch:{ all -> 0x0229 }
            r1 = r9
            r1.a((java.lang.String) r2, (java.lang.String) r3, (long) r4, (java.lang.String) r6, (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.FileDownloadCallbackInternal) r7)     // Catch:{ all -> 0x0229 }
            goto L_0x0227
        L_0x01b4:
            r0 = 2
            r10.f = r0     // Catch:{ all -> 0x0229 }
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ all -> 0x0229 }
            r0.<init>()     // Catch:{ all -> 0x0229 }
            long r1 = r10.b     // Catch:{ all -> 0x0229 }
            r0.put(r1)     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.api.PluginSmartHomeApi r1 = com.xiaomi.smarthome.core.server.internal.plugin.api.PluginSmartHomeApi.a()     // Catch:{ all -> 0x0229 }
            android.content.Context r2 = r9.C     // Catch:{ all -> 0x0229 }
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$9 r3 = new com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$9     // Catch:{ all -> 0x0229 }
            r3.<init>(r10)     // Catch:{ all -> 0x0229 }
            r1.a(r2, r0, r3)     // Catch:{ all -> 0x0229 }
            goto L_0x0227
        L_0x01d0:
            java.util.Map<java.lang.Long, com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$RnSdkTask> r0 = r9.aK     // Catch:{ all -> 0x0229 }
            long r3 = r10.b     // Catch:{ all -> 0x0229 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0229 }
            r0.remove(r3)     // Catch:{ all -> 0x0229 }
            java.util.List<java.lang.Object> r0 = r10.g     // Catch:{ all -> 0x0229 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0229 }
        L_0x01e1:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x0229 }
            if (r3 == 0) goto L_0x0227
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x0229 }
            boolean r4 = r3 instanceof com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal     // Catch:{ all -> 0x0229 }
            if (r4 == 0) goto L_0x0208
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PluginDownloadTaskInternal r3 = (com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PluginDownloadTaskInternal) r3     // Catch:{ all -> 0x0229 }
            int r4 = r10.f     // Catch:{ all -> 0x0229 }
            if (r4 != r1) goto L_0x01fe
            java.lang.String r4 = "rn_sdk_is_forbidden"
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0229 }
            r3.b(r4, r5)     // Catch:{ all -> 0x0229 }
        L_0x01fe:
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PluginDownloadTaskInternal$DownloadTaskCallbackInternal r4 = r3.k()     // Catch:{ all -> 0x0229 }
            if (r4 == 0) goto L_0x01e1
            r4.d(r3)     // Catch:{ all -> 0x0229 }
            goto L_0x01e1
        L_0x0208:
            boolean r4 = r3 instanceof com.xiaomi.smarthome.device.api.Callback     // Catch:{ all -> 0x0229 }
            if (r4 == 0) goto L_0x01e1
            com.xiaomi.smarthome.device.api.Callback r3 = (com.xiaomi.smarthome.device.api.Callback) r3     // Catch:{ all -> 0x0229 }
            r4 = 100
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0229 }
            r5.<init>()     // Catch:{ all -> 0x0229 }
            java.lang.String r6 = ""
            r5.append(r6)     // Catch:{ all -> 0x0229 }
            long r6 = r10.b     // Catch:{ all -> 0x0229 }
            r5.append(r6)     // Catch:{ all -> 0x0229 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0229 }
            r3.onFailure(r4, r5)     // Catch:{ all -> 0x0229 }
            goto L_0x01e1
        L_0x0227:
            monitor-exit(r10)     // Catch:{ all -> 0x0229 }
            return
        L_0x0229:
            r0 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0229 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.c(com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$RnSdkTask):void");
    }

    /* access modifiers changed from: private */
    public void a(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
        if (pluginDownloadTaskInternal != null) {
            if ((!i(pluginDownloadTaskInternal.b()) || !j(pluginDownloadTaskInternal.c()) || TextUtils.isEmpty(pluginDownloadTaskInternal.d()) || ((TextUtils.isEmpty(pluginDownloadTaskInternal.e()) && TextUtils.isEmpty(pluginDownloadTaskInternal.f())) || pluginDownloadTaskInternal.i() == null)) && pluginDownloadTaskInternal.k() != null) {
                pluginDownloadTaskInternal.k().d(pluginDownloadTaskInternal);
            } else if ("rn".equalsIgnoreCase(pluginDownloadTaskInternal.d())) {
                PluginSetting.RnSdkInfo a2 = a(this.C);
                long longValue = ((Long) pluginDownloadTaskInternal.a("rnSdkApiLevel", 0L)).longValue();
                if (a2 == null || longValue > a2.f22090a) {
                    a(longValue, pluginDownloadTaskInternal);
                    PluginSetting.a("start to install new sdk ", Long.valueOf(longValue));
                    return;
                }
                pluginDownloadTaskInternal.b("rnSdkApiLevel", Long.valueOf(a2.f22090a));
                PluginSetting.a("plugin manager start to download plugin");
                b(pluginDownloadTaskInternal);
                a(a2.f22090a, (PluginDownloadTaskInternal) null);
                PluginSetting.a("found sdk", Long.valueOf(longValue), " matched ", Long.valueOf(a2.f22090a), a2.c);
            } else {
                b(pluginDownloadTaskInternal);
            }
        }
    }

    private void b(PluginDownloadTaskInternal pluginDownloadTaskInternal) {
        PluginDownloadTaskInternal pluginDownloadTaskInternal2 = pluginDownloadTaskInternal;
        if (n(pluginDownloadTaskInternal.c())) {
            List list = this.aL.get(Long.valueOf(pluginDownloadTaskInternal.c()));
            if (list != null) {
                pluginDownloadTaskInternal2.a(((PluginDownloadTaskInternal) list.get(0)).j());
                list.add(pluginDownloadTaskInternal2);
                if (pluginDownloadTaskInternal.k() != null) {
                    pluginDownloadTaskInternal.k().b(pluginDownloadTaskInternal2);
                }
            } else if (pluginDownloadTaskInternal.k() != null) {
                pluginDownloadTaskInternal.k().d(pluginDownloadTaskInternal2);
            }
        } else {
            List list2 = this.aL.get(Long.valueOf(pluginDownloadTaskInternal.c()));
            if (list2 == null) {
                list2 = new CopyOnWriteArrayList();
                this.aL.put(Long.valueOf(pluginDownloadTaskInternal.c()), list2);
            }
            list2.add(pluginDownloadTaskInternal2);
            String g2 = g(pluginDownloadTaskInternal.c());
            final long b2 = pluginDownloadTaskInternal.b();
            final long c2 = pluginDownloadTaskInternal.c();
            final String d2 = pluginDownloadTaskInternal.d();
            final String str = g2;
            final PluginDownloadTaskInternal pluginDownloadTaskInternal3 = pluginDownloadTaskInternal;
            a(pluginDownloadTaskInternal.e(), pluginDownloadTaskInternal.f(), pluginDownloadTaskInternal.g(), g2, (FileDownloadCallbackInternal) new FileDownloadCallbackInternal() {
                /* access modifiers changed from: package-private */
                public void a(HttpAsyncHandle httpAsyncHandle) {
                    List<PluginDownloadTaskInternal> list = (List) PluginManager.this.aL.get(Long.valueOf(c2));
                    if (list != null) {
                        for (PluginDownloadTaskInternal pluginDownloadTaskInternal : list) {
                            pluginDownloadTaskInternal.a(httpAsyncHandle);
                            if (pluginDownloadTaskInternal.k() != null && !((Boolean) pluginDownloadTaskInternal.a("started", false)).booleanValue()) {
                                pluginDownloadTaskInternal.k().a(pluginDownloadTaskInternal);
                            }
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                public void a(float f2) {
                    List<PluginDownloadTaskInternal> list = (List) PluginManager.this.aL.get(Long.valueOf(c2));
                    if (list != null) {
                        for (PluginDownloadTaskInternal pluginDownloadTaskInternal : list) {
                            if (pluginDownloadTaskInternal.k() != null) {
                                pluginDownloadTaskInternal.k().a(pluginDownloadTaskInternal, ((Boolean) pluginDownloadTaskInternal.a("download_sdk", false)).booleanValue() ? (0.8f * f2) + 0.2f : f2);
                            }
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                public void a() {
                    List<PluginDownloadTaskInternal> list = (List) PluginManager.this.aL.get(Long.valueOf(c2));
                    if (list != null) {
                        if (PluginManager.this.a(str, b2, c2, d2, list)) {
                            PluginSetting.a("plugin manager download plugin success");
                            for (PluginDownloadTaskInternal pluginDownloadTaskInternal : list) {
                                if (pluginDownloadTaskInternal.k() != null) {
                                    pluginDownloadTaskInternal.k().c(pluginDownloadTaskInternal);
                                }
                            }
                        } else {
                            for (PluginDownloadTaskInternal pluginDownloadTaskInternal2 : list) {
                                if (pluginDownloadTaskInternal2.k() != null) {
                                    pluginDownloadTaskInternal2.k().d(pluginDownloadTaskInternal2);
                                }
                            }
                        }
                        PluginManager.this.aL.remove(Long.valueOf(c2));
                    }
                }

                /* access modifiers changed from: package-private */
                public void b() {
                    List<PluginDownloadTaskInternal> list = (List) PluginManager.this.aL.get(Long.valueOf(c2));
                    if (list != null) {
                        for (PluginDownloadTaskInternal pluginDownloadTaskInternal : list) {
                            if (pluginDownloadTaskInternal.k() != null) {
                                pluginDownloadTaskInternal.k().d(pluginDownloadTaskInternal);
                            }
                        }
                        PluginManager.this.aL.remove(Long.valueOf(c2));
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("plugin_id", pluginDownloadTaskInternal3.b());
                            jSONObject.put(PushRouteUtil.k, pluginDownloadTaskInternal3.c());
                            jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, WifiUtil.e(PluginManager.this.C));
                            jSONObject.put("url", pluginDownloadTaskInternal3.e());
                            jSONObject.put("safe_url", pluginDownloadTaskInternal3.f());
                            STAT.i.c(3, pluginDownloadTaskInternal3.i() == null ? "null" : pluginDownloadTaskInternal3.i().o());
                            StatManager.c().a(StatType.EVENT.getValue(), "mihome", "plugin_download_failed", jSONObject.toString(), (String) null, false);
                        } catch (Exception unused) {
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                public void c() {
                    List<PluginDownloadTaskInternal> list = (List) PluginManager.this.aL.get(Long.valueOf(c2));
                    if (list != null) {
                        for (PluginDownloadTaskInternal pluginDownloadTaskInternal : list) {
                            if (pluginDownloadTaskInternal.k() != null) {
                                pluginDownloadTaskInternal.k().e(pluginDownloadTaskInternal);
                            }
                        }
                        PluginManager.this.aL.remove(Long.valueOf(c2));
                    }
                }
            });
        }
    }

    private void a(String str, String str2, long j2, String str3, FileDownloadCallbackInternal fileDownloadCallbackInternal) {
        final FileDownloadCallbackInternal fileDownloadCallbackInternal2 = fileDownloadCallbackInternal;
        File i2 = FileUtils.i(str3);
        if (i2 == null) {
            FileUtils.d(str3);
            if (fileDownloadCallbackInternal2 != null) {
                fileDownloadCallbackInternal.b();
            }
        } else if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
            Request a2 = new Request.Builder().a("GET").b(TextUtils.isEmpty(str2) ? str : str2).a();
            if (FileAsyncHandler.a(i2, false)) {
                l("start down plugin and external");
                final AnonymousClass12 r6 = new Callback<Integer, Integer>() {

                    /* renamed from: a  reason: collision with root package name */
                    int f14591a = 0;

                    /* renamed from: a */
                    public Integer call(Integer num) throws Exception {
                        this.f14591a |= num.intValue();
                        PluginManager.this.l("result down plugin result:" + this.f14591a);
                        if (this.f14591a == 0) {
                            if (fileDownloadCallbackInternal2 != null) {
                                fileDownloadCallbackInternal2.b();
                            }
                        } else if (this.f14591a == 3 && fileDownloadCallbackInternal2 != null) {
                            fileDownloadCallbackInternal2.a();
                        }
                        return num;
                    }
                };
                ExternalLoadManager.instance.downExternal(r6);
                final FileDownloadCallbackInternal fileDownloadCallbackInternal3 = fileDownloadCallbackInternal;
                final long j3 = j2;
                final String str4 = str3;
                final String str5 = str2;
                final String str6 = str;
                HttpAsyncHandle a3 = HttpApi.a(n(), a2, (AsyncHandler) new FileAsyncHandler(i2) {
                    private float m = 0.0f;

                    public void onProgress(long j, long j2) {
                        if (fileDownloadCallbackInternal3 != null) {
                            float f2 = j3 > 0 ? ((float) j) / ((float) j3) : j2 > 0 ? ((float) j) / ((float) j2) : 0.0f;
                            if (f2 < 0.0f) {
                                f2 = 0.0f;
                            } else if (1.0f < f2) {
                                f2 = 1.0f;
                            }
                            if (((double) (f2 - this.m)) >= 0.01d) {
                                fileDownloadCallbackInternal3.a(f2);
                                this.m = f2;
                            }
                        }
                    }

                    /* renamed from: a */
                    public void onSuccess(File file, Response response) {
                        try {
                            r6.call(2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    public void onFailure(Error error, Exception exc, Response response) {
                        FileUtils.d(str4);
                        if (!TextUtils.isEmpty(str5)) {
                            File i = FileUtils.i(str4);
                            if (i == null) {
                                FileUtils.d(str4);
                                if (fileDownloadCallbackInternal3 != null) {
                                    fileDownloadCallbackInternal3.b();
                                    return;
                                }
                                return;
                            }
                            HttpApi.a(PluginManager.this.n(), new Request.Builder().a("GET").b(str6).a(), (AsyncHandler) new FileAsyncHandler(i) {
                                public void onProgress(long j, long j2) {
                                    if (fileDownloadCallbackInternal3 != null) {
                                        float f = j3 > 0 ? ((float) j) / ((float) j3) : j2 > 0 ? ((float) j) / ((float) j2) : 0.0f;
                                        if (f < 0.0f) {
                                            f = 0.0f;
                                        } else if (1.0f < f) {
                                            f = 1.0f;
                                        }
                                        fileDownloadCallbackInternal3.a(f);
                                    }
                                }

                                /* renamed from: a */
                                public void onSuccess(File file, Response response) {
                                    try {
                                        r6.call(2);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                public void onFailure(Error error, Exception exc, Response response) {
                                    FileUtils.d(str4);
                                    if (fileDownloadCallbackInternal3 != null) {
                                        fileDownloadCallbackInternal3.b();
                                    }
                                }
                            });
                        } else if (fileDownloadCallbackInternal3 != null) {
                            fileDownloadCallbackInternal3.b();
                        }
                    }
                });
                if (fileDownloadCallbackInternal2 != null) {
                    fileDownloadCallbackInternal2.a(a3);
                }
            } else if (fileDownloadCallbackInternal2 != null) {
                fileDownloadCallbackInternal.b();
            }
        } else {
            FileUtils.d(str3);
            if (fileDownloadCallbackInternal2 != null) {
                fileDownloadCallbackInternal.b();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask, CancelPluginDownloadCallbackInternal cancelPluginDownloadCallbackInternal) {
        if (pluginDownloadTask == null) {
            if (cancelPluginDownloadCallbackInternal != null) {
                cancelPluginDownloadCallbackInternal.b(pluginRecord);
            }
        } else if (this.aL.containsKey(Long.valueOf(pluginDownloadTask.a()))) {
            List list = this.aL.get(Long.valueOf(pluginDownloadTask.a()));
            PluginDownloadTaskInternal pluginDownloadTaskInternal = null;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PluginDownloadTaskInternal pluginDownloadTaskInternal2 = (PluginDownloadTaskInternal) it.next();
                if (pluginDownloadTaskInternal2.a() == pluginDownloadTask.b) {
                    pluginDownloadTaskInternal = pluginDownloadTaskInternal2;
                    break;
                }
            }
            if (pluginDownloadTaskInternal == null) {
                if (cancelPluginDownloadCallbackInternal != null) {
                    cancelPluginDownloadCallbackInternal.b(pluginRecord);
                }
            } else if (list.size() > 1) {
                if (pluginDownloadTaskInternal.k() != null) {
                    pluginDownloadTaskInternal.k().e(pluginDownloadTaskInternal);
                }
                list.remove(pluginDownloadTaskInternal);
                a(false, false, pluginRecord.o());
                if (cancelPluginDownloadCallbackInternal != null) {
                    cancelPluginDownloadCallbackInternal.a(pluginRecord);
                }
            } else {
                HttpAsyncHandle j2 = pluginDownloadTaskInternal.j();
                if (j2 != null) {
                    j2.a();
                }
                a(false, false, pluginRecord.o());
                if (cancelPluginDownloadCallbackInternal != null) {
                    cancelPluginDownloadCallbackInternal.a(pluginRecord);
                }
            }
        } else if (this.aM.containsKey(Integer.valueOf(pluginDownloadTask.b()))) {
            this.aM.remove(Integer.valueOf(pluginDownloadTask.b()));
            if (cancelPluginDownloadCallbackInternal != null) {
                cancelPluginDownloadCallbackInternal.a(pluginRecord);
            }
        } else if (cancelPluginDownloadCallbackInternal != null) {
            cancelPluginDownloadCallbackInternal.b(pluginRecord);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x014a A[SYNTHETIC, Splitter:B:50:0x014a] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0154 A[SYNTHETIC, Splitter:B:55:0x0154] */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.PackageRawInfo b(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 != 0) goto L_0x0159
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 == 0) goto L_0x000f
            goto L_0x0159
        L_0x000f:
            java.lang.String r0 = "mpk"
            boolean r0 = r10.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x0099
            android.content.Context r10 = r8.C     // Catch:{ Exception -> 0x0024 }
            android.content.pm.PackageManager r10 = r10.getPackageManager()     // Catch:{ Exception -> 0x0024 }
            r0 = 128(0x80, float:1.794E-43)
            android.content.pm.PackageInfo r10 = r10.getPackageArchiveInfo(r9, r0)     // Catch:{ Exception -> 0x0024 }
            goto L_0x0041
        L_0x0024:
            r10 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r9)
            java.lang.String r9 = " readMetaDataFail "
            r0.append(r9)
            java.lang.String r9 = android.util.Log.getStackTraceString(r10)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.l((java.lang.String) r9)
            r10 = r1
        L_0x0041:
            if (r10 == 0) goto L_0x0158
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo r9 = new com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo
            r9.<init>()
            int r0 = r10.versionCode
            r9.f14670a = r0
            java.lang.String r0 = r10.packageName
            r9.e = r0
            android.content.pm.ApplicationInfo r10 = r10.applicationInfo
            if (r10 == 0) goto L_0x0096
            android.os.Bundle r10 = r10.metaData
            if (r10 == 0) goto L_0x0096
            java.lang.String r0 = "model"
            java.lang.String r1 = ""
            java.lang.String r0 = r10.getString(r0, r1)
            java.lang.String r1 = "\\|"
            java.lang.String[] r0 = r0.split(r1)
            java.util.List r0 = java.util.Arrays.asList(r0)
            r9.f = r0
            java.lang.String r0 = "minPluginSdkApiVersion"
            r1 = 0
            int r0 = r10.getInt(r0, r1)
            r9.c = r0
            long r2 = r8.a((android.os.Bundle) r10)
            r9.b = r2
            java.lang.String r0 = "MiHomePlatform"
            java.lang.String r2 = ""
            java.lang.String r0 = r10.getString(r0, r2)
            r9.d = r0
            java.lang.String r0 = "MiHomeSupportWidget"
            boolean r0 = r10.getBoolean(r0, r1)
            r9.g = r0
            java.lang.String r0 = "MiHomeSupportAppAV"
            r1 = 1
            boolean r10 = r10.getBoolean(r0, r1)
            r9.h = r10
        L_0x0096:
            r1 = r9
            goto L_0x0158
        L_0x0099:
            java.lang.String r0 = "rn"
            boolean r10 = r10.equals(r0)
            if (r10 == 0) goto L_0x0158
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            r10.<init>(r9)     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            boolean r10 = r10.isFile()     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            if (r10 == 0) goto L_0x00c6
            java.util.zip.ZipFile r10 = new java.util.zip.ZipFile     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            r10.<init>(r9)     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            java.lang.String r9 = "android/project.json"
            java.util.zip.ZipEntry r9 = r10.getEntry(r9)     // Catch:{ Exception -> 0x00c1, all -> 0x00bc }
            java.io.InputStream r9 = r10.getInputStream(r9)     // Catch:{ Exception -> 0x00c1, all -> 0x00bc }
            goto L_0x00d7
        L_0x00bc:
            r9 = move-exception
            r0 = r9
            r9 = r1
            goto L_0x014f
        L_0x00c1:
            r9 = move-exception
            r0 = r9
            r9 = r1
            goto L_0x013e
        L_0x00c6:
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            java.lang.String r9 = a((java.lang.String) r9)     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            java.lang.String r0 = "project.json"
            r10.<init>(r9, r0)     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x013a, all -> 0x0135 }
            r10 = r1
        L_0x00d7:
            int r0 = r9.available()     // Catch:{ Exception -> 0x0133 }
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0133 }
            r9.read(r0)     // Catch:{ Exception -> 0x0133 }
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0133 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0133 }
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0133 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0133 }
            com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo r2 = new com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo     // Catch:{ Exception -> 0x0133 }
            r2.<init>()     // Catch:{ Exception -> 0x0133 }
            java.lang.String r3 = "developer_id"
            java.lang.String r3 = r0.optString(r3)     // Catch:{ Exception -> 0x0133 }
            r4 = 0
            long r6 = a((java.lang.String) r3, (long) r4)     // Catch:{ Exception -> 0x0133 }
            r2.b = r6     // Catch:{ Exception -> 0x0133 }
            java.lang.String r3 = "package_path"
            java.lang.String r3 = r0.optString(r3)     // Catch:{ Exception -> 0x0133 }
            r2.e = r3     // Catch:{ Exception -> 0x0133 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0133 }
            r3.<init>()     // Catch:{ Exception -> 0x0133 }
            r2.f = r3     // Catch:{ Exception -> 0x0133 }
            java.lang.String r3 = "phone"
            r2.d = r3     // Catch:{ Exception -> 0x0133 }
            java.lang.String r3 = "sdk_api_level"
            java.lang.String r3 = r0.optString(r3)     // Catch:{ Exception -> 0x0133 }
            long r6 = a((java.lang.String) r3, (long) r4)     // Catch:{ Exception -> 0x0133 }
            int r3 = (int) r6     // Catch:{ Exception -> 0x0133 }
            r2.c = r3     // Catch:{ Exception -> 0x0133 }
            java.lang.String r3 = "version_code"
            java.lang.String r0 = r0.optString(r3)     // Catch:{ Exception -> 0x0133 }
            long r3 = a((java.lang.String) r0, (long) r4)     // Catch:{ Exception -> 0x0133 }
            int r0 = (int) r3     // Catch:{ Exception -> 0x0133 }
            r2.f14670a = r0     // Catch:{ Exception -> 0x0133 }
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.InputStream) r9)
            if (r10 == 0) goto L_0x0132
            r10.close()     // Catch:{ Exception -> 0x0132 }
        L_0x0132:
            return r2
        L_0x0133:
            r0 = move-exception
            goto L_0x013e
        L_0x0135:
            r9 = move-exception
            r0 = r9
            r9 = r1
            r10 = r9
            goto L_0x014f
        L_0x013a:
            r9 = move-exception
            r0 = r9
            r9 = r1
            r10 = r9
        L_0x013e:
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x014e }
            r8.l((java.lang.String) r0)     // Catch:{ all -> 0x014e }
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.InputStream) r9)
            if (r10 == 0) goto L_0x0158
            r10.close()     // Catch:{ Exception -> 0x0158 }
            goto L_0x0158
        L_0x014e:
            r0 = move-exception
        L_0x014f:
            com.xiaomi.smarthome.library.common.util.IOUtils.a((java.io.InputStream) r9)
            if (r10 == 0) goto L_0x0157
            r10.close()     // Catch:{ Exception -> 0x0157 }
        L_0x0157:
            throw r0
        L_0x0158:
            return r1
        L_0x0159:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.b(java.lang.String, java.lang.String):com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$PackageRawInfo");
    }

    private static long a(String str, long j2) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j2;
        }
    }

    private long a(Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        try {
            String string = bundle.getString(V, "");
            return Long.parseLong(string.substring("id_".length(), string.length()));
        } catch (Exception unused) {
            return 0;
        }
    }

    private boolean a(PackageRawInfo packageRawInfo, PluginDeveloperInfo pluginDeveloperInfo, String str, String str2) {
        if (!"rn".equalsIgnoreCase(str2) && (pluginDeveloperInfo == null || TextUtils.isEmpty(pluginDeveloperInfo.c()) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            return false;
        }
        if (str2.equalsIgnoreCase(i)) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = this.C.getPackageManager().getPackageArchiveInfo(str, 64);
            } catch (Exception e2) {
                l(str + " readSignatureFail " + Log.getStackTraceString(e2));
            }
            if (packageInfo == null || !pluginDeveloperInfo.c().equalsIgnoreCase(a(packageInfo.signatures))) {
                return false;
            }
            return true;
        } else if (str2.equalsIgnoreCase("rn")) {
            return j(str);
        } else {
            return false;
        }
    }

    private boolean a(PluginDeveloperInfo pluginDeveloperInfo, String str) {
        PackageInfo packageInfo;
        if (pluginDeveloperInfo == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            packageInfo = this.av.getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null || !pluginDeveloperInfo.c().equalsIgnoreCase(a(packageInfo.signatures))) {
            return false;
        }
        return true;
    }

    private boolean a(String str, int i2) {
        if ("rn".equalsIgnoreCase(str)) {
            return true;
        }
        return i2 > 0 && i2 <= 100;
    }

    private boolean c(String str, String str2) {
        if ("rn".equalsIgnoreCase(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase(SystemApi.a().l())) {
            return false;
        }
        return true;
    }

    private boolean a(String str, List<String> list) {
        if ("rn".equalsIgnoreCase(str)) {
            return true;
        }
        boolean z2 = false;
        if (list == null || list.size() <= 0) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (TextUtils.isEmpty(it.next())) {
                    z2 = true;
                    break;
                }
            } else {
                break;
            }
        }
        return !z2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x012a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(long r22, long r24, com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.BuiltinPkgResult r26) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r3 = r24
            r5 = r26
            boolean r6 = r21.i((long) r22)
            if (r6 == 0) goto L_0x013c
            boolean r6 = r0.j((long) r3)
            if (r6 != 0) goto L_0x0016
            goto L_0x013c
        L_0x0016:
            android.content.Context r6 = r0.C
            android.content.res.AssetManager r6 = r6.getAssets()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013a }
            r8.<init>()     // Catch:{ Exception -> 0x013a }
            java.lang.String r9 = r0.Q     // Catch:{ Exception -> 0x013a }
            r8.append(r9)     // Catch:{ Exception -> 0x013a }
            java.lang.String r9 = java.io.File.separator     // Catch:{ Exception -> 0x013a }
            r8.append(r9)     // Catch:{ Exception -> 0x013a }
            r8.append(r1)     // Catch:{ Exception -> 0x013a }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x013a }
            java.lang.String[] r6 = r6.list(r8)     // Catch:{ Exception -> 0x013a }
            if (r6 == 0) goto L_0x0138
            int r8 = r6.length
            r9 = 1
            if (r8 >= r9) goto L_0x003e
            goto L_0x0138
        L_0x003e:
            int r8 = r6.length
            r10 = 0
        L_0x0040:
            if (r10 >= r8) goto L_0x0135
            r11 = r6[r10]
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = r0.Q
            r12.append(r13)
            java.lang.String r13 = java.io.File.separator
            r12.append(r13)
            r12.append(r1)
            java.lang.String r13 = java.io.File.separator
            r12.append(r13)
            r12.append(r11)
            java.lang.String r12 = r12.toString()
            java.lang.String r13 = ""
            java.lang.String r14 = ""
            java.lang.String r15 = r11.toLowerCase()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "."
            r7.append(r9)
            java.lang.String r9 = "mpk"
            java.lang.String r9 = r9.toLowerCase()
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            boolean r7 = r15.endsWith(r7)
            if (r7 == 0) goto L_0x009d
            int r7 = r11.length()
            java.lang.String r9 = "mpk"
            int r9 = r9.length()
            int r7 = r7 - r9
            r9 = 1
            int r7 = r7 - r9
            r9 = 0
            java.lang.String r13 = r11.substring(r9, r7)
            java.lang.String r14 = "mpk"
        L_0x009b:
            r9 = 1
            goto L_0x0109
        L_0x009d:
            java.lang.String r7 = r11.toLowerCase()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r15 = "."
            r9.append(r15)
            java.lang.String r15 = "h5"
            java.lang.String r15 = r15.toLowerCase()
            r9.append(r15)
            java.lang.String r9 = r9.toString()
            boolean r7 = r7.endsWith(r9)
            if (r7 == 0) goto L_0x00d3
            int r7 = r11.length()
            java.lang.String r9 = "h5"
            int r9 = r9.length()
            int r7 = r7 - r9
            r9 = 1
            int r7 = r7 - r9
            r9 = 0
            java.lang.String r13 = r11.substring(r9, r7)
            java.lang.String r14 = "h5"
            goto L_0x009b
        L_0x00d3:
            java.lang.String r7 = r11.toLowerCase()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r15 = "."
            r9.append(r15)
            java.lang.String r15 = "apk"
            java.lang.String r15 = r15.toLowerCase()
            r9.append(r15)
            java.lang.String r9 = r9.toString()
            boolean r7 = r7.endsWith(r9)
            if (r7 == 0) goto L_0x009b
            int r7 = r11.length()
            java.lang.String r9 = "apk"
            int r9 = r9.length()
            int r7 = r7 - r9
            r9 = 1
            int r7 = r7 - r9
            r13 = 0
            java.lang.String r7 = r11.substring(r13, r7)
            java.lang.String r14 = "apk"
            r13 = r7
        L_0x0109:
            boolean r7 = android.text.TextUtils.isEmpty(r13)
            if (r7 != 0) goto L_0x0131
            boolean r7 = android.text.TextUtils.isEmpty(r14)
            if (r7 == 0) goto L_0x0116
            goto L_0x0131
        L_0x0116:
            r17 = 0
            long r19 = java.lang.Long.parseLong(r13)     // Catch:{ Exception -> 0x011d }
            goto L_0x011f
        L_0x011d:
            r19 = r17
        L_0x011f:
            int r7 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r7 > 0) goto L_0x0124
            goto L_0x0131
        L_0x0124:
            int r7 = (r19 > r3 ? 1 : (r19 == r3 ? 0 : -1))
            if (r7 != 0) goto L_0x0131
            if (r5 == 0) goto L_0x012e
            r5.f14664a = r12
            r5.b = r14
        L_0x012e:
            r16 = 1
            goto L_0x0137
        L_0x0131:
            int r10 = r10 + 1
            goto L_0x0040
        L_0x0135:
            r16 = 0
        L_0x0137:
            return r16
        L_0x0138:
            r1 = 0
            return r1
        L_0x013a:
            r1 = 0
            return r1
        L_0x013c:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.a(long, long, com.xiaomi.smarthome.core.server.internal.plugin.PluginManager$BuiltinPkgResult):boolean");
    }

    /* access modifiers changed from: private */
    public boolean k(long j2) {
        return j2 > 0 && this.aH.containsKey(Long.valueOf(j2));
    }

    /* access modifiers changed from: private */
    public boolean l(long j2) {
        return j2 > 0 && this.aJ.containsKey(Long.valueOf(j2));
    }

    /* access modifiers changed from: private */
    public boolean m(long j2) {
        return j2 > 0 && this.aQ.containsKey(Long.valueOf(j2));
    }

    private boolean n(long j2) {
        return this.aL.containsKey(Long.valueOf(j2));
    }

    private boolean d(PluginRecord pluginRecord) {
        boolean z2 = false;
        for (Map.Entry<Long, List<PluginDownloadTaskInternal>> value : this.aL.entrySet()) {
            Iterator it = ((List) value.getValue()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PluginDownloadTaskInternal pluginDownloadTaskInternal = (PluginDownloadTaskInternal) it.next();
                if (pluginDownloadTaskInternal.i() != null && pluginDownloadTaskInternal.h() && pluginDownloadTaskInternal.i().o().equalsIgnoreCase(pluginRecord.o())) {
                    z2 = true;
                    continue;
                    break;
                }
            }
            if (z2) {
                break;
            }
        }
        return z2;
    }

    private boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = this.I + File.separator + "debug";
        String str3 = str2 + File.separator + "armeabi" + "_zip";
        String str4 = str2 + File.separator + "armeabi-v7a" + "_zip";
        String str5 = ShareConstants.o + File.separator + "armeabi";
        String str6 = ShareConstants.o + File.separator + "armeabi-v7a";
        ZipFileUtils.a(str, str3, str5);
        String[] list = new File(str3 + File.separator + str5).list();
        if (list == null || list.length <= 0) {
            ZipFileUtils.a(str, str4, str6);
            String[] list2 = new File(str4 + File.separator + str6).list();
            if (list2 == null || list2.length <= 0) {
                return false;
            }
            FileUtils.e(str2);
            return true;
        }
        FileUtils.e(str2);
        return true;
    }

    private DebugPkgResult T() {
        long j2;
        DebugPkgResult debugPkgResult = new DebugPkgResult();
        String[] list = new File(this.P + File.separator + "debug").list();
        if (list == null || list.length <= 0) {
            debugPkgResult.e = true;
            debugPkgResult.f = "SD卡无插件包";
            return debugPkgResult;
        }
        String str = list[0];
        String str2 = "";
        String str3 = "";
        if (str.toLowerCase().endsWith(i.toLowerCase())) {
            str3 = str.substring(0, (str.length() - i.length()) - 1);
            str2 = i;
        } else if (str.toLowerCase().endsWith(h.toLowerCase())) {
            str3 = str.substring(0, (str.length() - h.length()) - 1);
            str2 = h;
        } else if (str.toLowerCase().endsWith("apk".toLowerCase())) {
            str3 = str.substring(0, (str.length() - "apk".length()) - 1);
            str2 = "apk";
        } else if (str.toLowerCase().endsWith("rn".toLowerCase())) {
            str3 = str.substring(0, (str.length() - "rn".length()) - 1);
            str2 = "rn";
        }
        try {
            j2 = Long.parseLong(str3);
        } catch (Exception unused) {
            j2 = 0;
        }
        if (j2 <= 0) {
            debugPkgResult.e = true;
            debugPkgResult.f = "文件名格式非法(" + str + Operators.BRACKET_END_STR;
            return debugPkgResult;
        } else if (TextUtils.isEmpty(str2)) {
            debugPkgResult.e = true;
            debugPkgResult.f = "文件名格式非法(" + str + Operators.BRACKET_END_STR;
            return debugPkgResult;
        } else {
            String str4 = this.P + File.separator + "debug" + File.separator + str;
            if (k(str4)) {
                ArrayList arrayList = new ArrayList();
                for (long j3 = 1; j3 <= 100; j3++) {
                    arrayList.add(Long.valueOf(j3));
                }
                for (PluginPackageInfo b2 : this.aG) {
                    arrayList.remove(Long.valueOf(b2.b()));
                }
                Collections.shuffle(arrayList);
                if (arrayList.size() > 0) {
                    j2 = ((Long) arrayList.get(0)).longValue();
                    String str5 = this.P + File.separator + "debug" + File.separator + j2 + "." + str2;
                    new File(str4).renameTo(new File(str5));
                    str4 = str5;
                } else {
                    FileUtils.e(this.K);
                    FileUtils.e(d());
                    Notification.Builder smallIcon = new Notification.Builder(this.C).setContentTitle("米家正在重启以卸载已加载的so").setContentText("请稍候调试").setSmallIcon(17301504);
                    NotificationManager notificationManager = (NotificationManager) this.C.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
                    if (notificationManager != null) {
                        if (Build.VERSION.SDK_INT >= 26) {
                            smallIcon.setChannelId(NotificationChannelCreator.a(notificationManager));
                        }
                        notificationManager.notify(Constants.TradeCode.QUERY_ORDER_VER2, smallIcon.build());
                    }
                    U();
                }
            }
            debugPkgResult.f14665a = str4;
            debugPkgResult.b = 1;
            debugPkgResult.c = j2;
            debugPkgResult.d = str2;
            debugPkgResult.e = false;
            return debugPkgResult;
        }
    }

    private void U() {
        Intent launchIntentForPackage;
        if (HostSetting.g || HostSetting.i) {
            try {
                PackageManager packageManager = this.C.getPackageManager();
                if (packageManager != null && (launchIntentForPackage = packageManager.getLaunchIntentForPackage(this.C.getPackageName())) != null) {
                    launchIntentForPackage.addFlags(com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE);
                    ((AlarmManager) this.C.getSystemService("alarm")).set(1, System.currentTimeMillis() + 2000, PendingIntent.getActivity(this.C, 223344, launchIntentForPackage, C.ENCODING_PCM_MU_LAW));
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                }
            } catch (Exception unused) {
            }
        }
    }

    private void V() {
        FileUtils.e(this.P + File.separator + "debug");
    }

    @Deprecated
    private boolean h(PluginRecord pluginRecord) {
        W();
        W();
        return false;
    }

    @Deprecated
    private boolean m(PluginRecord pluginRecord) {
        return FileUtils.d(k(pluginRecord));
    }

    private static String a(android.content.pm.Signature[] signatureArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (signatureArr != null) {
                for (android.content.pm.Signature byteArray : signatureArr) {
                    instance.update(byteArray.toByteArray());
                }
            }
            return ByteUtils.c(instance.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    private abstract class LoadPluginCallbackInternal extends BaseCallbackInternal {
        /* access modifiers changed from: package-private */
        public abstract void a(PluginRecord pluginRecord);

        /* access modifiers changed from: package-private */
        public abstract void b(PluginRecord pluginRecord);

        private LoadPluginCallbackInternal() {
            super();
        }
    }

    /* access modifiers changed from: private */
    public void a(PluginRecord pluginRecord, LoadPluginCallbackInternal loadPluginCallbackInternal) {
        if (pluginRecord == null || !pluginRecord.l()) {
            if (loadPluginCallbackInternal != null) {
                loadPluginCallbackInternal.b(pluginRecord);
            }
        } else if (pluginRecord.m()) {
            if (loadPluginCallbackInternal != null) {
                loadPluginCallbackInternal.b(pluginRecord);
            }
        } else if (d(pluginRecord)) {
            if (loadPluginCallbackInternal != null) {
                loadPluginCallbackInternal.b(pluginRecord);
            }
        } else if (pluginRecord.A()) {
            if (pluginRecord.h() != null) {
                if (loadPluginCallbackInternal != null) {
                    loadPluginCallbackInternal.a(pluginRecord);
                }
            } else if (loadPluginCallbackInternal != null) {
                loadPluginCallbackInternal.b(pluginRecord);
            }
        } else if (loadPluginCallbackInternal != null) {
            loadPluginCallbackInternal.b(pluginRecord);
        }
    }

    private void W() {
        FileUtils.e(this.I);
    }

    private static class SetAutoUpdateOperation {

        /* renamed from: a  reason: collision with root package name */
        boolean f14673a;
        SetAutoUpdateCallbackInternal b;

        private SetAutoUpdateOperation() {
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, SetAutoUpdateCallbackInternal setAutoUpdateCallbackInternal) {
        PreferenceUtils.b(this.aw, as, z2);
        this.aD = z2;
        if (setAutoUpdateCallbackInternal != null) {
            setAutoUpdateCallbackInternal.a(z2);
        }
    }

    private static class GetAutoUpdateOperation {

        /* renamed from: a  reason: collision with root package name */
        GetAutoUpdateCallbackInternal f14667a;

        private GetAutoUpdateOperation() {
        }
    }

    /* access modifiers changed from: private */
    public void a(GetAutoUpdateCallbackInternal getAutoUpdateCallbackInternal) {
        if (getAutoUpdateCallbackInternal != null) {
            getAutoUpdateCallbackInternal.a(this.aD);
        }
    }

    private static class DumpPluginOperation {

        /* renamed from: a  reason: collision with root package name */
        DumpPluginCallbackInternal f14666a;

        private DumpPluginOperation() {
        }
    }

    /* access modifiers changed from: private */
    public void a(DumpPluginCallbackInternal dumpPluginCallbackInternal) {
        Log.d("PluginManager", "dumpPlugin");
        for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
            Log.d("PluginManager", ((PluginRecord) value.getValue()).F());
        }
        if (dumpPluginCallbackInternal != null) {
            dumpPluginCallbackInternal.a();
        }
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.o.containsKey(str);
    }

    public String g() {
        return this.J;
    }

    public PluginRecord c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.o.get(str);
    }

    public String a(int i2) {
        if (i2 <= 0) {
            return "";
        }
        try {
            for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
                PluginRecord pluginRecord = (PluginRecord) value.getValue();
                if (pluginRecord.c().d() == i2) {
                    return pluginRecord.o();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public int d(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            for (Map.Entry<String, PluginRecord> value : this.o.entrySet()) {
                PluginRecord pluginRecord = (PluginRecord) value.getValue();
                if (pluginRecord.o().equalsIgnoreCase(str)) {
                    return pluginRecord.c().d();
                }
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public PluginPackageInfo d(long j2) {
        return this.aH.get(Long.valueOf(j2));
    }

    private class UpdateConfigParam {

        /* renamed from: a  reason: collision with root package name */
        boolean f14675a;
        UpdateConfigCallbackInternal b;

        private UpdateConfigParam() {
        }
    }

    public void a(boolean z2, final UpdateConfigCallback updateConfigCallback) {
        UpdateConfigParam updateConfigParam = new UpdateConfigParam();
        updateConfigParam.f14675a = z2;
        updateConfigParam.b = new UpdateConfigCallbackInternal() {
            public void a(final boolean z, final boolean z2) {
                if (updateConfigCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            updateConfigCallback.a(z, z2);
                        }
                    });
                }
            }

            public void a(final CoreError coreError) {
                if (updateConfigCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            updateConfigCallback.a(coreError);
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(2, updateConfigParam).sendToTarget();
    }

    public void a(final ClearConfigCallback clearConfigCallback) {
        this.n.obtainMessage(3, new ClearAllPluginConfigCallbackInternal() {
            public void a() {
                if (clearConfigCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            clearConfigCallback.a();
                        }
                    });
                }
            }

            public void b() {
                if (clearConfigCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            clearConfigCallback.b();
                        }
                    });
                }
            }
        }).sendToTarget();
    }

    public void a(@Nullable ServerBean serverBean, @Nullable ServerBean serverBean2) {
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = new Pair(serverBean, serverBean2);
        this.n.sendMessage(obtain);
    }

    public void a(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask, final PluginCancelDownloadCallback pluginCancelDownloadCallback) {
        Operation operation = new Operation();
        operation.f14668a = pluginRecord;
        operation.d = pluginDownloadTask;
        operation.c = new CancelPluginDownloadCallbackInternal() {
            public void a(final PluginRecord pluginRecord) {
                if (pluginCancelDownloadCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginCancelDownloadCallback.a(pluginRecord);
                        }
                    });
                }
            }

            public void b(final PluginRecord pluginRecord) {
                if (pluginCancelDownloadCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginCancelDownloadCallback.b(pluginRecord);
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(13, operation).sendToTarget();
    }

    public void a(final DebugPackageCallback debugPackageCallback) {
        Operation operation = new Operation();
        operation.c = new DebugPackageCallbackInternal() {
            /* access modifiers changed from: package-private */
            public void a() {
                if (debugPackageCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            debugPackageCallback.a();
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void a(final String str) {
                if (debugPackageCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            debugPackageCallback.a(str);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void b() {
                if (debugPackageCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            debugPackageCallback.b();
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void b(final String str) {
                if (debugPackageCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            debugPackageCallback.b(str);
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(18, operation).sendToTarget();
    }

    public byte[] i() {
        StringBuilder sb = new StringBuilder();
        sb.append("getPluginRecordByte ");
        sb.append(this.aO == null ? null : Integer.valueOf(this.aO.length));
        LogUtil.a("PluginManager", sb.toString());
        if (this.aO != null) {
            return this.aO;
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public List<PluginRecord> j() {
        return this.aN;
    }

    public List<PluginPackageInfo> k() {
        return this.aG;
    }

    public List<PluginPackageInfo> l() {
        return this.aI;
    }

    public void a(final PluginRecord pluginRecord, final PluginDeleteCallback pluginDeleteCallback) {
        if (pluginRecord.k()) {
            try {
                f(pluginRecord.g());
                if (pluginDeleteCallback != null) {
                    this.l.post(new Runnable() {
                        public void run() {
                            pluginDeleteCallback.a(pluginRecord);
                        }
                    });
                }
            } catch (Exception unused) {
                if (pluginDeleteCallback != null) {
                    this.l.post(new Runnable() {
                        public void run() {
                            pluginDeleteCallback.b(pluginRecord);
                        }
                    });
                }
            }
        } else if (pluginDeleteCallback != null) {
            this.l.post(new Runnable() {
                public void run() {
                    pluginDeleteCallback.c(pluginRecord);
                }
            });
        }
    }

    public void a(String str, String str2) {
        PluginRecord c2 = c(str);
        if (c2 != null) {
            PluginPackageInfo pluginPackageInfo = new PluginPackageInfo();
            pluginPackageInfo.a(1);
            pluginPackageInfo.b(1);
            pluginPackageInfo.b(100);
            pluginPackageInfo.c("rn");
            pluginPackageInfo.b(str2);
            pluginPackageInfo.a((List<String>) new ArrayList());
            pluginPackageInfo.a(false);
            pluginPackageInfo.c(0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("developer_id", "77219341");
                jSONObject.put("package_path", str2);
                jSONObject.put("models", str);
                jSONObject.put("platform", SystemApi.a().l());
                jSONObject.put(c, 1);
                jSONObject.put(Constants.Update.e, 1);
                File file = new File(a(new File(c(1, 1, "rn")).getAbsolutePath()));
                file.mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "project.json"));
                fileOutputStream.write(jSONObject.toString().getBytes());
                fileOutputStream.close();
                File file2 = new File(a(new File(b(1, 1, "rn")).getAbsolutePath()));
                file2.mkdirs();
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file2, "project.json"));
                fileOutputStream2.write(jSONObject.toString().getBytes());
                fileOutputStream2.close();
            } catch (Exception e2) {
                Log.e("PluginManager", "installDebugRNPluginWithoutPackage", e2);
            }
            PluginRecord pluginRecord = c2;
            PluginPackageInfo pluginPackageInfo2 = pluginPackageInfo;
            pluginRecord.b(1, 1, pluginPackageInfo2, (PluginDeveloperInfo) null);
            pluginRecord.a(1, 1, pluginPackageInfo2, (PluginDeveloperInfo) null);
            F();
            a(true, false, (String) null);
        }
    }

    public void a(final PluginRecord pluginRecord, final PluginDownloadCallback pluginDownloadCallback) {
        if (!pluginRecord.k() && !pluginRecord.l()) {
            Operation operation = new Operation();
            operation.f14668a = pluginRecord;
            operation.c = new PluginDownloadCallbackInternal() {
                /* access modifiers changed from: package-private */
                public void a(final PluginRecord pluginRecord, final PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.a(pluginRecord, pluginDownloadTaskInternal.l());
                            }
                        });
                    }
                }

                /* access modifiers changed from: package-private */
                public void b(final PluginRecord pluginRecord, final PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.b(pluginRecord, pluginDownloadTaskInternal.l());
                            }
                        });
                    }
                }

                /* access modifiers changed from: package-private */
                public void c(final PluginRecord pluginRecord, final PluginDownloadTaskInternal pluginDownloadTaskInternal) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.c(pluginRecord, pluginDownloadTaskInternal.l());
                            }
                        });
                    }
                }

                /* access modifiers changed from: package-private */
                public void a(final PluginRecord pluginRecord, final float f) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.a(pluginRecord, f);
                            }
                        });
                    }
                }

                /* access modifiers changed from: package-private */
                public void a(final PluginRecord pluginRecord) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.a(pluginRecord);
                            }
                        });
                    }
                }

                /* access modifiers changed from: package-private */
                public void b(final PluginRecord pluginRecord) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.b(pluginRecord);
                            }
                        });
                    }
                }

                /* access modifiers changed from: package-private */
                public void c(final PluginRecord pluginRecord) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.b(pluginRecord);
                            }
                        });
                    }
                }

                /* access modifiers changed from: package-private */
                public void d(final PluginRecord pluginRecord) {
                    if (pluginDownloadCallback != null) {
                        PluginManager.this.l.post(new Runnable() {
                            public void run() {
                                pluginDownloadCallback.c(pluginRecord);
                            }
                        });
                    }
                }
            };
            this.n.obtainMessage(9, operation).sendToTarget();
        } else if (pluginDownloadCallback != null) {
            this.l.post(new Runnable() {
                public void run() {
                    pluginDownloadCallback.b(pluginRecord);
                }
            });
        }
    }

    public void a(final PluginRecord pluginRecord, boolean z2, final PluginInstallCallback pluginInstallCallback) {
        if (pluginRecord == null || !pluginRecord.k()) {
            l("installPlugin onFailure record:" + pluginRecord);
            if (pluginInstallCallback != null) {
                this.l.post(new Runnable() {
                    public void run() {
                        pluginInstallCallback.c(pluginRecord);
                    }
                });
                return;
            }
            return;
        }
        Operation operation = new Operation();
        operation.f14668a = pluginRecord;
        operation.b = pluginRecord.i();
        operation.c = new InstallCallbackInternel() {
            /* access modifiers changed from: package-private */
            public void a(final PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
                if (pluginInstallCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginInstallCallback.a(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void b(final PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
                if (pluginInstallCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginInstallCallback.b(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void c(final PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
                if (pluginInstallCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginInstallCallback.c(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void d(final PluginRecord pluginRecord, PluginPackageInfo pluginPackageInfo) {
                if (pluginInstallCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginInstallCallback.d(pluginRecord);
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(10, operation).sendToTarget();
    }

    public void a(PluginRecord pluginRecord, final PluginLoadCallback pluginLoadCallback) {
        Operation operation = new Operation();
        operation.f14668a = pluginRecord;
        operation.c = new LoadPluginCallbackInternal() {
            /* access modifiers changed from: package-private */
            public void a(final PluginRecord pluginRecord) {
                if (pluginLoadCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginLoadCallback.a(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void b(final PluginRecord pluginRecord) {
                if (pluginLoadCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginLoadCallback.b(pluginRecord);
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(11, operation).sendToTarget();
    }

    public void a(boolean z2, final PluginUpdateAllCallback pluginUpdateAllCallback) {
        UpdateAllOperation updateAllOperation = new UpdateAllOperation();
        updateAllOperation.f14674a = z2;
        updateAllOperation.b = new UpdateAllCallbackInternal() {
            public void a() {
                if (pluginUpdateAllCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateAllCallback.a();
                        }
                    });
                }
            }

            public void b() {
                if (pluginUpdateAllCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateAllCallback.b();
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(5, updateAllOperation).sendToTarget();
    }

    public void a(PluginRecord pluginRecord, boolean z2, final PluginUpdateCallback pluginUpdateCallback) {
        UpdateOperation updateOperation = new UpdateOperation();
        updateOperation.f14676a = pluginRecord;
        updateOperation.b = z2;
        updateOperation.c = new UpdateCallbackInternal() {
            /* access modifiers changed from: package-private */
            public void a(final PluginRecord pluginRecord) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.a(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void b(final PluginRecord pluginRecord) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.b(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void a(final PluginRecord pluginRecord, final PluginUpdateInfo pluginUpdateInfo) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.a(pluginRecord, pluginUpdateInfo);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void c(final PluginRecord pluginRecord) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.c(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void a(final PluginRecord pluginRecord, final HttpAsyncHandle httpAsyncHandle) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.b(pluginRecord, httpAsyncHandle);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void b(final PluginRecord pluginRecord, final HttpAsyncHandle httpAsyncHandle) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.a(pluginRecord, httpAsyncHandle);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void a(final PluginRecord pluginRecord, final float f) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.a(pluginRecord, f);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void d(final PluginRecord pluginRecord) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.d(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void e(final PluginRecord pluginRecord) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.e(pluginRecord);
                        }
                    });
                }
            }

            /* access modifiers changed from: package-private */
            public void f(final PluginRecord pluginRecord) {
                if (pluginUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            pluginUpdateCallback.f(pluginRecord);
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(12, updateOperation).sendToTarget();
    }

    public void a(boolean z2, final SetAutoUpdateCallback setAutoUpdateCallback) {
        SetAutoUpdateOperation setAutoUpdateOperation = new SetAutoUpdateOperation();
        setAutoUpdateOperation.f14673a = z2;
        setAutoUpdateOperation.b = new SetAutoUpdateCallbackInternal() {
            public void a(final boolean z) {
                if (setAutoUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            setAutoUpdateCallback.a(z);
                        }
                    });
                }
            }

            public void a() {
                if (setAutoUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            setAutoUpdateCallback.a();
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(14, setAutoUpdateOperation).sendToTarget();
    }

    public void a(final GetAutoUpdateCallback getAutoUpdateCallback) {
        GetAutoUpdateOperation getAutoUpdateOperation = new GetAutoUpdateOperation();
        getAutoUpdateOperation.f14667a = new GetAutoUpdateCallbackInternal() {
            public void a(final boolean z) {
                if (getAutoUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            getAutoUpdateCallback.a(z);
                        }
                    });
                }
            }

            public void a() {
                if (getAutoUpdateCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            getAutoUpdateCallback.a();
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(15, getAutoUpdateOperation).sendToTarget();
    }

    public String e(String str) {
        for (Map.Entry next : this.aS.entrySet()) {
            if (((Pattern) next.getValue()).matcher(str).matches()) {
                return (String) next.getKey();
            }
        }
        return null;
    }

    public void a(final DumpPluginCallback dumpPluginCallback) {
        DumpPluginOperation dumpPluginOperation = new DumpPluginOperation();
        dumpPluginOperation.f14666a = new DumpPluginCallbackInternal() {
            public void a() {
                if (dumpPluginCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            dumpPluginCallback.a();
                        }
                    });
                }
            }

            public void b() {
                if (dumpPluginCallback != null) {
                    PluginManager.this.l.post(new Runnable() {
                        public void run() {
                            dumpPluginCallback.b();
                        }
                    });
                }
            }
        };
        this.n.obtainMessage(16, dumpPluginOperation).sendToTarget();
    }

    public boolean a(PluginRecord pluginRecord) {
        return pluginRecord != null && d(pluginRecord);
    }

    /* access modifiers changed from: private */
    public void l(String str) {
        LogUtil.b("PluginManager", str);
        if (GlobalSetting.u) {
            $$Lambda$PluginManager$ntbUuy0V_0Nj3d2vCJqsuYNhE r0 = new Runnable(str) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    PluginManager.this.m(this.f$1);
                }
            };
            if (this.bD == null) {
                synchronized (this) {
                    if (this.bD == null) {
                        this.bD = Executors.newSingleThreadExecutor();
                    }
                }
            }
            this.bD.execute(r0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void m(String str) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String str2 = this.C.getExternalFilesDir(Tags.RepairOrder.SERVICE_LOG).getAbsolutePath() + "/plugin.log";
            FileUtils.i(str2);
            RandomAccessFile randomAccessFile = new RandomAccessFile(str2, "rw");
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(("time - " + simpleDateFormat.format(date) + ", ").getBytes());
            randomAccessFile.write(str.getBytes());
            randomAccessFile.write(10);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }
}
