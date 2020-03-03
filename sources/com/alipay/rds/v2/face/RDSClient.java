package com.alipay.rds.v2.face;

import android.content.Context;
import android.util.Log;
import com.alipay.deviceid.DeviceTokenClient;
import com.alipay.deviceid.module.senative.DeviceIdUtil;
import com.alipay.deviceid.module.x.ce;
import com.alipay.deviceid.module.x.cf;
import com.alipay.deviceid.module.x.cp;
import com.alipay.deviceid.module.x.cv;
import com.alipay.deviceid.module.x.cw;
import com.taobao.weex.el.parse.Operators;
import java.util.Map;

public class RDSClient {
    private static final String RDS_VERSION = "1";
    private static final String RDS_ZIP_VERSION = "2";
    private static Context context = null;
    private static boolean debug = false;
    private ce manager;

    public static void init(final Context context2) {
        if (context2 != null) {
            isDebug();
            new Thread(new Runnable() {
                public final void run() {
                    DeviceIdUtil.getInstance(context2).initialize();
                    RDSClient.isDebug();
                }
            }).start();
        }
    }

    public synchronized boolean onPage(Context context2, Map<String, String> map, boolean z) {
        Map<String, String> map2 = map;
        synchronized (this) {
            if (context2 == null) {
                return false;
            }
            setContext(context2);
            String str = "";
            String str2 = "";
            try {
                DeviceTokenClient.TokenResult tokenResult = DeviceTokenClient.getInstance(context2).getTokenResult();
                if (tokenResult != null) {
                    String str3 = tokenResult.apdid;
                    try {
                        str2 = tokenResult.apdidToken;
                    } catch (Throwable unused) {
                    }
                    str = str3;
                }
            } catch (Throwable unused2) {
            }
            String str4 = str2;
            String str5 = str;
            String str6 = map2.get("refPageName");
            ce ceVar = r2;
            ce ceVar2 = new ce(context2, str5, map2.get("umidToken"), map2.get("utdid"), map2.get("tid"), str4, map2.get("appver"), map2.get("user"), map2.get("appname"), map2.get("appkey"), map2.get("appPackageName"), z);
            this.manager = ceVar;
            cp cpVar = this.manager.f;
            cpVar.c.incrementAndGet();
            cv cvVar = new cv(map2.get("pageName"), "1", cpVar.d.incrementAndGet());
            cvVar.a(str6);
            cpVar.e.a(cvVar);
            cpVar.f = cvVar;
            return true;
        }
    }

    public synchronized String onPageEndAndZip(Context context2, String str) {
        String str2;
        this.manager.a(str);
        this.manager.a();
        if (getContext() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String str3 = "1";
        try {
            byte[] zipAndEncryptData = DeviceIdUtil.getInstance(context2).zipAndEncryptData(getContext(), cf.a(this.manager).getBytes("UTF-8"));
            if (zipAndEncryptData != null) {
                try {
                    str2 = new String(zipAndEncryptData, "UTF-8");
                    str3 = "2";
                } catch (Exception unused) {
                    str3 = "2";
                    str2 = "";
                    sb.append(Operators.BLOCK_START_STR);
                    sb.append("\"version\":\"" + str3 + "\", ");
                    sb.append("\"data\":\"");
                    sb.append(str2 + "\"}");
                    return sb.toString();
                }
            } else {
                str2 = "";
            }
        } catch (Exception unused2) {
            str2 = "";
            sb.append(Operators.BLOCK_START_STR);
            sb.append("\"version\":\"" + str3 + "\", ");
            sb.append("\"data\":\"");
            sb.append(str2 + "\"}");
            return sb.toString();
        }
        sb.append(Operators.BLOCK_START_STR);
        sb.append("\"version\":\"" + str3 + "\", ");
        sb.append("\"data\":\"");
        sb.append(str2 + "\"}");
        return sb.toString();
    }

    public synchronized String onPageEnd(Context context2, String str) {
        String str2;
        this.manager.a(str);
        this.manager.a();
        if (getContext() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            str2 = new String(DeviceIdUtil.getInstance(context2).zipAndEncryptData(getContext(), cf.a(this.manager).getBytes("UTF-8")), "UTF-8");
        } catch (Exception unused) {
            str2 = "";
        }
        sb.append(Operators.BLOCK_START_STR);
        sb.append("\"version\":\"" + "1" + "\", ");
        sb.append("\"data\":\"");
        sb.append(str2 + "\"}");
        return sb.toString();
    }

    public synchronized void onControlClick(String str, String str2) {
        this.manager.f.a(str, str2);
    }

    public synchronized void onKeyDown(String str, String str2, String str3) {
        this.manager.f.a(str, str2, str3);
    }

    public synchronized void onGetFocus(String str, String str2) {
        this.manager.a(str, str2, true);
    }

    public synchronized void onLostFocus(String str, String str2) {
        this.manager.a(str, str2, false);
    }

    public synchronized void onLostFocus(String str, String str2, boolean z) {
        this.manager.a(str, str2, z);
    }

    public synchronized void onTouchScreen(String str, String str2, double d, double d2) {
        cp cpVar = this.manager.f;
        String b = cp.b(str, str2);
        cpVar.c.incrementAndGet();
        if (cpVar.f == null) {
            Log.e("RDSInfo", "[-] pageEnter first");
        } else if (cpVar.f == null || !(cpVar.f instanceof cw)) {
            cw cwVar = new cw(str, str2, b, cpVar.d.incrementAndGet());
            cwVar.b(d);
            cpVar.e.a(cwVar);
            cpVar.f = cwVar;
        } else {
            cpVar.e.a("", false, d);
        }
    }

    public static synchronized void enableLog() {
        synchronized (RDSClient.class) {
            debug = true;
        }
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    public static boolean isDebug() {
        return debug;
    }
}
