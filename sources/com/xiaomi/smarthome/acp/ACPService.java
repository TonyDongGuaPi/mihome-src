package com.xiaomi.smarthome.acp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.library.file.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class ACPService extends IntentService {
    public static final String ACTION_ACP = "acp_action";
    public static final String CRASH_TIME_FILE_NAME_BACKUP = "crash_time_backup";
    public static String FILE_DIR = String.format("/data/data/%s/", new Object[]{"com.xiaomi.smarthome"});
    public static final String INTENT_KEY_DATA = "acp_data";
    public static final String INTENT_KEY_REPEATED_CRASH = "acp_repeated_crash";
    public static final int INTENT_VALUE_CHECK_REPEATED_CRASH = 1;
    public static final int INTENT_VALUE_CLEAR_CACHE = 2;
    public static final int INTENT_VALUE_LOG_APP_START_TIME = 5;
    public static final int INTENT_VALUE_REPORT_REPEATED_CRASH = 4;
    public static final int INTENT_VALUE_RESET_REPEATED_CRASH = 3;
    public static final String PACKAGE_NAME = "com.xiaomi.smarthome";
    public static final long REPEATED_CRASH_INTERVAL = 10000;

    /* renamed from: a  reason: collision with root package name */
    private static final String f13625a = "ACPService";
    private static final String b = "crash_time";
    private static final String c = "crash_times";
    private static final String d = "crash_content";
    private static final String e = "app_start_time";
    private static final String f = "crash_time";
    private static ArrayList<String> g = new ArrayList<>();
    private static final int h = 3;

    public ACPService() {
        super(f13625a);
        a();
    }

    public ACPService(String str) {
        super(str);
        a();
    }

    private void a() {
        if (FILE_DIR == null) {
            FILE_DIR = String.format("/data/data/%s/", new Object[]{"com.xiaomi.smarthome"});
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            LogUtil.a(f13625a, "onHandleIntent " + action);
            if (TextUtils.equals(action, ACTION_ACP)) {
                a(intent);
            }
        }
    }

    private void a(Intent intent) {
        int intExtra = intent.getIntExtra(INTENT_KEY_REPEATED_CRASH, 0);
        LogUtil.a(f13625a, "processACPAction " + intExtra);
        if (intExtra != 0) {
            if (intExtra == 1) {
                a(intent.getStringExtra(INTENT_KEY_DATA));
            } else if (intExtra == 2) {
                d();
            } else if (intExtra == 3) {
                b();
            } else if (intExtra == 4) {
                c();
            } else if (intExtra == 5) {
                logAppStartTime(intent.getLongExtra(INTENT_KEY_DATA, 0));
            }
        }
    }

    private void b() {
        LogUtil.a(f13625a, "resetRepeatedCrash");
        try {
            a();
            CrashInfo g2 = g();
            if (g2 == null) {
                g2 = new CrashInfo();
            }
            g2.f13626a = 0;
            g2.b = 0;
            g2.d = "";
            Context applicationContext = getApplicationContext();
            FileUtil.a(applicationContext, Uri.fromFile(new File(FILE_DIR + "crash_time")), g2.a().toString().getBytes("UTF-8"));
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void c() {
        File file = new File(FILE_DIR + CRASH_TIME_FILE_NAME_BACKUP);
        if (file.exists()) {
            try {
                byte[] d2 = FileUtil.d(getApplicationContext(), Uri.fromFile(file));
                if (d2 == null) {
                    return;
                }
                if (d2.length >= 2) {
                    CrashInfo a2 = CrashInfo.a(new String(d2, "UTF-8"));
                    if (a2 == null) {
                        return;
                    }
                    if (!TextUtils.isEmpty(a2.d)) {
                        file.delete();
                        StatHelper.a(a2.d);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void d() {
        LogUtil.a(f13625a, "clearCacheData");
        Context applicationContext = getApplicationContext();
        deleteFile(applicationContext.getCacheDir(), true);
        deleteFile(applicationContext.getFilesDir(), true);
        File databasePath = applicationContext.getDatabasePath("miio.db");
        if (databasePath != null) {
            deleteFile(databasePath.getParentFile(), true);
        }
        deleteFile(applicationContext.getExternalCacheDir(), true);
        File filesDir = applicationContext.getFilesDir();
        if (filesDir != null) {
            File parentFile = filesDir.getParentFile();
            try {
                deleteFile(new File(parentFile.getCanonicalPath() + File.separator + "app_dex"), true);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                deleteFile(new File(parentFile.getCanonicalPath() + File.separator + "shared_prefs"), true);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            try {
                deleteFile(new File(parentFile.getCanonicalPath() + File.separator + "app_webview"), true);
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        b();
    }

    public static boolean deleteFile(File file, boolean z) {
        boolean z2;
        if (file == null) {
            return true;
        }
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                z2 = true;
                for (int i = 0; i < list.length; i++) {
                    z2 = deleteFile(new File(file, list[i]), true) && z2;
                }
            } else {
                z2 = true;
            }
            if (z) {
                try {
                    if (g.contains(file.getCanonicalPath())) {
                        return true;
                    }
                    file.delete();
                } catch (Exception unused) {
                    return false;
                }
            }
            return z2;
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    private void a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        CrashInfo g2 = g();
        StringBuilder sb = new StringBuilder();
        sb.append("processRepeatedCrash currentCrashTimes=");
        int i = 0;
        sb.append(g2 == null ? 0 : g2.f13626a);
        LogUtil.a(f13625a, sb.toString());
        long j = currentTimeMillis - ((g2 == null || g2.c == 0) ? currentTimeMillis : g2.c);
        if (g2 == null || (g2.f13626a < 2 && j <= 10000)) {
            if (g2 != null) {
                i = g2.f13626a;
            }
            a(i);
            a(g2, str);
        } else if (j > 10000) {
            b();
        } else {
            a(g2.f13626a);
            e();
        }
    }

    private void a(int i) {
        if (i == 0) {
            MobclickAgent.a((Context) this, "repeated_crash", "repeated_crash_count_1");
        } else if (i == 1) {
            MobclickAgent.a((Context) this, "repeated_crash", "repeated_crash_count_2");
        } else if (i == 2) {
            MobclickAgent.a((Context) this, "repeated_crash", "repeated_crash_count_3");
        }
    }

    private void e() {
        LogUtil.a(f13625a, "showRepeatedCrashDialog");
        f();
        d();
    }

    private void f() {
        FileUtil.a(FILE_DIR + "crash_time", FILE_DIR + CRASH_TIME_FILE_NAME_BACKUP);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004b A[Catch:{ Exception -> 0x0084 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logAppStartTime(long r5) {
        /*
            r4 = this;
            r4.a()     // Catch:{ Exception -> 0x0084 }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0084 }
            r1.<init>()     // Catch:{ Exception -> 0x0084 }
            java.lang.String r2 = FILE_DIR     // Catch:{ Exception -> 0x0084 }
            r1.append(r2)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r2 = "crash_time"
            r1.append(r2)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0084 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0084 }
            boolean r1 = r0.exists()     // Catch:{ Exception -> 0x0084 }
            if (r1 != 0) goto L_0x0024
            r0.createNewFile()     // Catch:{ Exception -> 0x0084 }
        L_0x0024:
            android.content.Context r1 = r4.getApplicationContext()     // Catch:{ Exception -> 0x0084 }
            android.net.Uri r0 = android.net.Uri.fromFile(r0)     // Catch:{ Exception -> 0x0084 }
            byte[] r0 = com.xiaomi.smarthome.library.file.FileUtil.d(r1, r0)     // Catch:{ Exception -> 0x0084 }
            r1 = 0
            if (r0 == 0) goto L_0x0044
            int r2 = r0.length     // Catch:{ Exception -> 0x0084 }
            r3 = 2
            if (r2 >= r3) goto L_0x0038
            goto L_0x0044
        L_0x0038:
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x0084 }
            java.lang.String r3 = "UTF-8"
            r2.<init>(r0, r3)     // Catch:{ Exception -> 0x0084 }
            com.xiaomi.smarthome.acp.ACPService$CrashInfo r0 = com.xiaomi.smarthome.acp.ACPService.CrashInfo.a(r2)     // Catch:{ Exception -> 0x0084 }
            goto L_0x0049
        L_0x0044:
            com.xiaomi.smarthome.acp.ACPService$CrashInfo r0 = new com.xiaomi.smarthome.acp.ACPService$CrashInfo     // Catch:{ Exception -> 0x0084 }
            r0.<init>()     // Catch:{ Exception -> 0x0084 }
        L_0x0049:
            if (r0 != 0) goto L_0x0050
            com.xiaomi.smarthome.acp.ACPService$CrashInfo r0 = new com.xiaomi.smarthome.acp.ACPService$CrashInfo     // Catch:{ Exception -> 0x0084 }
            r0.<init>()     // Catch:{ Exception -> 0x0084 }
        L_0x0050:
            r0.c = r5     // Catch:{ Exception -> 0x0084 }
            android.content.Context r5 = r4.getApplicationContext()     // Catch:{ Exception -> 0x0084 }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0084 }
            r1.<init>()     // Catch:{ Exception -> 0x0084 }
            java.lang.String r2 = FILE_DIR     // Catch:{ Exception -> 0x0084 }
            r1.append(r2)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r2 = "crash_time"
            r1.append(r2)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0084 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0084 }
            android.net.Uri r6 = android.net.Uri.fromFile(r6)     // Catch:{ Exception -> 0x0084 }
            org.json.JSONObject r0 = r0.a()     // Catch:{ Exception -> 0x0084 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0084 }
            java.lang.String r1 = "UTF-8"
            byte[] r0 = r0.getBytes(r1)     // Catch:{ Exception -> 0x0084 }
            com.xiaomi.smarthome.library.file.FileUtil.a((android.content.Context) r5, (android.net.Uri) r6, (byte[]) r0)     // Catch:{ Exception -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.acp.ACPService.logAppStartTime(long):void");
    }

    private void a(CrashInfo crashInfo, String str) {
        LogUtil.a(f13625a, "increaseCrashTimes");
        try {
            a();
            File file = new File(FILE_DIR + "crash_time");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            JSONObject jSONObject = crashInfo == null ? new JSONObject() : crashInfo.a();
            jSONObject.put(c, jSONObject.optInt(c, 0) + 1);
            jSONObject.put(d, str);
            jSONObject.put("crash_time", System.currentTimeMillis());
            Context applicationContext = getApplicationContext();
            FileUtil.a(applicationContext, Uri.fromFile(new File(FILE_DIR + "crash_time")), jSONObject.toString().getBytes("UTF-8"));
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean isReachMaxCrashTimes(Context context) {
        try {
            byte[] d2 = FileUtil.d(context, Uri.fromFile(new File(FILE_DIR + "crash_time")));
            if (d2 == null) {
                return false;
            }
            JSONObject jSONObject = new JSONObject(new String(d2, "UTF-8"));
            if (!jSONObject.isNull(c) && jSONObject.optInt(c, 0) >= 2) {
                return true;
            }
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private CrashInfo g() {
        LogUtil.a(f13625a, "readCrashTimes");
        try {
            a();
            Context applicationContext = getApplicationContext();
            byte[] d2 = FileUtil.d(applicationContext, Uri.fromFile(new File(FILE_DIR + "crash_time")));
            if (d2 != null) {
                if (d2.length >= 2) {
                    return CrashInfo.a(new String(d2, "UTF-8"));
                }
            }
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private static class CrashInfo {

        /* renamed from: a  reason: collision with root package name */
        int f13626a;
        long b;
        long c;
        String d;

        private CrashInfo() {
        }

        public static CrashInfo a(String str) {
            CrashInfo crashInfo = new CrashInfo();
            if (TextUtils.isEmpty(str)) {
                return crashInfo;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(ACPService.c)) {
                    crashInfo.f13626a = jSONObject.optInt(ACPService.c, 0);
                }
                if (!jSONObject.isNull("crash_time")) {
                    crashInfo.b = (long) jSONObject.optInt("crash_time", 0);
                }
                if (!jSONObject.isNull(ACPService.d)) {
                    crashInfo.d = jSONObject.optString(ACPService.d, "");
                }
                if (!jSONObject.isNull(ACPService.e)) {
                    crashInfo.c = jSONObject.optLong(ACPService.e, 0);
                }
                return crashInfo;
            } catch (Throwable th) {
                th.printStackTrace();
                return crashInfo;
            }
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ACPService.c, this.f13626a);
                jSONObject.put("crash_time", this.b);
                jSONObject.put(ACPService.d, this.d);
                jSONObject.put(ACPService.e, this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return jSONObject;
        }
    }
}
