package com.xiaomi.smarthome.core.server.internal.whitelist;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.google.code.microlog4android.format.PatternFormatter;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.whitelist.api.WhiteListApi;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.frame.SDKSetting;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.TimerManager;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WhiteListManager {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f14734a = new Object();
    private static final String b = "core_service_whitelist_pref";
    private static final String c = "whitelist_last_modify";
    private static final String d = "whitelist_last_check_time";
    private static final String e = "whitelist_last_check_version";
    private static final long f = 21600000;
    private static WhiteListManager g;
    private final char[] h = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};
    private boolean i = false;
    private boolean j = false;
    private Context k = CommonApplication.getAppContext();
    private SharedPreferences l;
    private Map<String, WhiteListInfo> m = new ConcurrentHashMap();
    private TimerManager n;
    /* access modifiers changed from: private */
    public boolean o = false;

    public void a(boolean z) {
    }

    private WhiteListManager() {
        f();
        b();
    }

    public static WhiteListManager a() {
        if (g == null) {
            synchronized (f14734a) {
                if (g == null) {
                    g = new WhiteListManager();
                }
            }
        }
        return g;
    }

    private String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append(this.h[(b2 & 240) >>> 4]);
            sb.append(this.h[b2 & 15]);
        }
        return sb.toString();
    }

    public void b() {
        WhiteListApi.a().a(new Callback<Boolean>() {
            public void onFailure(int i, String str) {
            }

            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                boolean unused = WhiteListManager.this.o = bool.booleanValue();
            }
        });
    }

    private void f() {
        boolean z;
        WhiteListInfo a2;
        synchronized (f14734a) {
            z = this.i;
            if (!this.i) {
                this.i = true;
            }
        }
        if (!z) {
            this.l = this.k.getSharedPreferences(b, 0);
            Map<String, ?> all = this.l.getAll();
            if (all != null && all.size() > 0) {
                for (Map.Entry next : all.entrySet()) {
                    String str = (String) next.getKey();
                    if (!a(str)) {
                        Object value = next.getValue();
                        if ((value instanceof String) && (a2 = WhiteListInfo.a(str, (String) value)) != null) {
                            a(a2);
                        }
                    }
                }
            }
            if (this.m.size() == 0) {
                this.m.put("com.xiaomi.router", new WhiteListInfo("com.xiaomi.router", "D52E033C39B6F47A0248B2505A2D6A91"));
            }
        }
    }

    public boolean c() {
        boolean z;
        synchronized (f14734a) {
            z = this.i;
        }
        return z;
    }

    private boolean g() {
        boolean z;
        synchronized (f14734a) {
            z = this.j;
        }
        return z;
    }

    private void b(boolean z) {
        synchronized (f14734a) {
            this.j = z;
        }
    }

    public boolean d() {
        return this.o;
    }

    public void e() {
        h();
        if (this.l != null) {
            SharePrefsManager.a(this.l);
        }
    }

    private boolean a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase(c) && !str.equalsIgnoreCase(d) && !str.equalsIgnoreCase(e)) {
            return false;
        }
        return true;
    }

    private void a(WhiteListInfo whiteListInfo) {
        this.m.put(whiteListInfo.b(), whiteListInfo);
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.m.remove(str);
        }
    }

    private void h() {
        this.m.clear();
    }

    private void b(WhiteListInfo whiteListInfo) {
        if (!a(whiteListInfo.b())) {
            SharePrefsManager.a(this.l, whiteListInfo.b(), whiteListInfo.a());
        }
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str) && !a(str)) {
            SharePrefsManager.a(this.l, str);
        }
    }

    private void i() {
        if (this.l != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            Map<String, ?> all = this.l.getAll();
            if (all != null && all.size() > 0) {
                for (Map.Entry<String, ?> key : all.entrySet()) {
                    String str = (String) key.getKey();
                    if (!TextUtils.isEmpty(str) && !a(str)) {
                        arrayList.add(str);
                    }
                }
            }
            for (String a2 : arrayList) {
                SharePrefsManager.a(this.l, a2);
            }
        }
    }

    public boolean a(int i2, int i3) {
        if (!SDKSetting.f1524a) {
            return true;
        }
        String[] a2 = a(i2);
        if (a2 != null && a2.length > 0) {
            for (String equalsIgnoreCase : a2) {
                if (equalsIgnoreCase.equalsIgnoreCase("com.xiaomi.smarthome")) {
                    return true;
                }
            }
        }
        String[] b2 = b(i3);
        if (b2 != null && b2.length > 0) {
            for (String equalsIgnoreCase2 : b2) {
                if (equalsIgnoreCase2.equalsIgnoreCase("com.xiaomi.smarthome")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean b(int i2, int i3) {
        if (!SDKSetting.f1524a) {
            return true;
        }
        if ((GlobalSetting.J != i3 || GlobalSetting.I != i2) && !a(a(i2)) && !a(b(i3))) {
            return false;
        }
        return true;
    }

    private String[] a(int i2) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.k.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next != null && next.pid == i2) {
                    return next.pkgList;
                }
            }
        }
        return null;
    }

    private String[] b(int i2) {
        return this.k.getPackageManager().getPackagesForUid(i2);
    }

    private boolean a(String[] strArr) {
        PackageInfo packageInfo;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                if (str.equalsIgnoreCase("com.xiaomi.smarthome")) {
                    return true;
                }
                if (this.m.containsKey(str)) {
                    WhiteListInfo whiteListInfo = this.m.get(str);
                    try {
                        packageInfo = this.k.getPackageManager().getPackageInfo(str, 64);
                    } catch (PackageManager.NameNotFoundException unused) {
                        packageInfo = null;
                    }
                    if (a(whiteListInfo, packageInfo)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean a(WhiteListInfo whiteListInfo, PackageInfo packageInfo) {
        String str;
        if (whiteListInfo == null || TextUtils.isEmpty(whiteListInfo.c()) || packageInfo == null) {
            return false;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (packageInfo.signatures != null) {
                for (Signature byteArray : packageInfo.signatures) {
                    instance.update(byteArray.toByteArray());
                }
            }
            str = a(instance.digest());
        } catch (Exception unused) {
            str = "";
        }
        if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase(whiteListInfo.c())) {
            return false;
        }
        return true;
    }
}
