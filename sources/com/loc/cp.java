package com.loc;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.amap.location.common.model.AmapLoc;
import com.amap.location.common.model.CellState;
import com.amap.location.common.model.CellStatus;
import com.amap.location.common.model.FPS;
import com.amap.location.common.model.WifiStatus;
import com.amap.location.common.network.IHttpClient;
import com.amap.location.offline.IOfflineCloudConfig;
import com.amap.location.offline.OfflineConfig;
import com.amap.location.offline.OfflineManager;
import com.amap.location.offline.upload.UploadConfig;
import com.taobao.weex.common.Constants;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.ArrayList;
import org.json.JSONObject;

public final class cp {

    /* renamed from: a  reason: collision with root package name */
    Context f6537a = null;
    private co b = null;

    public cp(Context context) {
        this.f6537a = context;
        try {
            this.b = new co(this.f6537a);
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, ScanResult[] scanResultArr) {
        try {
            OfflineManager.a().a(b(str, scanResultArr));
        } catch (Throwable th) {
            cm.a(th, "OfflineLocManager", "trainingFps");
        }
    }

    public static void a(String str, ScanResult[] scanResultArr, double d, double d2) {
        try {
            FPS b2 = b(str, scanResultArr);
            AmapLoc amapLoc = new AmapLoc();
            amapLoc.b(d);
            amapLoc.a(d2);
            OfflineManager.a().a(b2, amapLoc);
        } catch (Throwable th) {
            cm.a(th, "OfflineLocManager", "correctLocation");
        }
    }

    private static FPS b(String str, ScanResult[] scanResultArr) {
        JSONObject jSONObject;
        CellStatus cellStatus = new CellStatus();
        if (!TextUtils.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
                JSONObject optJSONObject = jSONObject.optJSONObject("mainCgi");
                if (optJSONObject != null) {
                    CellState cellState = new CellState(optJSONObject.optInt("type", 0), optJSONObject.optBoolean("registered", false));
                    cellState.g = optJSONObject.optInt("mcc");
                    cellState.h = optJSONObject.optInt("mnc");
                    cellState.i = optJSONObject.optInt("lac");
                    cellState.j = optJSONObject.optInt("cid");
                    cellState.k = optJSONObject.optInt("sid");
                    cellState.l = optJSONObject.optInt("nid");
                    cellState.m = optJSONObject.optInt("bid");
                    cellState.n = optJSONObject.optInt(DTransferConstants.n);
                    cellStatus.h = cellState;
                }
            } catch (Throwable th) {
                cm.a(th, "OfflineLocManager", "buildFPS_1");
            }
            try {
                JSONObject optJSONObject2 = jSONObject.optJSONObject("newCgi");
                if (optJSONObject2 != null) {
                    CellState cellState2 = new CellState(optJSONObject2.optInt("type", 0), optJSONObject2.optBoolean("registered", false));
                    cellState2.g = optJSONObject2.optInt("mcc");
                    cellState2.h = optJSONObject2.optInt("mnc");
                    cellState2.i = optJSONObject2.optInt("lac");
                    cellState2.j = optJSONObject2.optInt("cid");
                    cellState2.k = optJSONObject2.optInt("sid");
                    cellState2.l = optJSONObject2.optInt("nid");
                    cellState2.m = optJSONObject2.optInt("bid");
                    cellState2.n = optJSONObject2.optInt(DTransferConstants.n);
                    cellStatus.j = cellState2;
                }
            } catch (Throwable th2) {
                cm.a(th2, "OfflineLocManager", "buildFPS_1_2");
            }
        }
        WifiStatus wifiStatus = new WifiStatus();
        if (scanResultArr != null) {
            try {
                ArrayList arrayList = new ArrayList();
                for (ScanResult add : scanResultArr) {
                    arrayList.add(add);
                }
                wifiStatus.a(wifiStatus.b(arrayList));
            } catch (Throwable th3) {
                cm.a(th3, "OfflineLocManager", "buildFPS_2");
            }
        }
        FPS fps = new FPS();
        fps.f4587a = cellStatus;
        fps.b = wifiStatus;
        return fps;
    }

    private OfflineConfig b(ci ciVar, IHttpClient iHttpClient) {
        String g;
        OfflineConfig offlineConfig = new OfflineConfig();
        offlineConfig.h = 4;
        if (ciVar != null) {
            try {
                offlineConfig.i = ciVar.e();
                offlineConfig.p = ciVar.d();
                offlineConfig.j = ciVar.b();
                offlineConfig.o = ciVar.c();
                offlineConfig.k = ciVar.f();
                g = ciVar.g();
            } catch (Throwable th) {
                cm.a(th, "OfflineLocManager", "generateOfflineConfig");
            }
        } else {
            offlineConfig.p = u.f(this.f6537a);
            offlineConfig.i = u.c(this.f6537a);
            offlineConfig.j = "";
            offlineConfig.o = "S128DF1572465B890OE3F7A13167KLEI";
            offlineConfig.k = x.a(this.f6537a);
            g = ciVar.g();
        }
        offlineConfig.n = g;
        UploadConfig uploadConfig = new UploadConfig();
        uploadConfig.f4601a = 100;
        uploadConfig.b = 100000;
        uploadConfig.c = 864000000;
        uploadConfig.d = 60000;
        uploadConfig.e = 60000;
        uploadConfig.f = 1000;
        uploadConfig.g = 500000;
        uploadConfig.h = false;
        offlineConfig.u = uploadConfig;
        if (this.b == null) {
            try {
                this.b = new co(this.f6537a);
            } catch (Throwable unused) {
            }
        }
        offlineConfig.v = this.b;
        offlineConfig.t = iHttpClient;
        return offlineConfig;
    }

    public final String a(String str, ScanResult[] scanResultArr, boolean z) {
        try {
            AmapLoc a2 = OfflineManager.a().a(b(str, scanResultArr), z);
            if (a2 == null) {
                return null;
            }
            if (this.b != null) {
                double[] a3 = this.b.a(new double[]{a2.d(), a2.c()});
                a2.b(a3[0]);
                a2.a(a3[1]);
            }
            return a2.d(1);
        } catch (Throwable th) {
            cm.a(th, "OfflineLocManager", "getOfflineLocation");
            return null;
        }
    }

    public final void a() {
        try {
            OfflineManager.a().b();
            this.b = null;
        } catch (Throwable th) {
            cm.a(th, "OfflineLocManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
        }
    }

    public final void a(ci ciVar, IHttpClient iHttpClient) {
        try {
            cn cnVar = new cn();
            OfflineManager.a().a(this.f6537a, b(ciVar, iHttpClient), (IOfflineCloudConfig) cnVar);
        } catch (Throwable th) {
            cm.a(th, "OfflineLocManager", "init");
        }
    }
}
