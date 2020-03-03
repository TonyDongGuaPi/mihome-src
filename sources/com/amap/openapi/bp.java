package com.amap.openapi;

import android.content.Context;
import android.support.annotation.NonNull;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.model.AmapLoc;
import com.amap.location.common.model.CellStatus;
import com.amap.location.common.model.FPS;
import com.amap.location.common.model.WiFi;
import com.amap.location.common.model.WifiStatus;
import com.amap.location.offline.IOfflineCloudConfig;
import com.amap.location.offline.OfflineConfig;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class bp {

    /* renamed from: a  reason: collision with root package name */
    private Context f4640a;
    private OfflineConfig b;
    private cc c;
    private a d = new a();
    private int e = 0;

    private static class a implements Comparator<WiFi> {
        private a() {
        }

        /* renamed from: a */
        public int compare(WiFi wiFi, WiFi wiFi2) {
            return wiFi2.c - wiFi.c;
        }
    }

    public bp(@NonNull Context context, @NonNull OfflineConfig offlineConfig, @NonNull IOfflineCloudConfig iOfflineCloudConfig) {
        this.f4640a = context;
        this.b = offlineConfig;
        this.c = new cc(context, offlineConfig, iOfflineCloudConfig);
    }

    private AmapLoc a(boolean z, AmapLoc amapLoc) {
        if (amapLoc == null) {
            return null;
        }
        if (z && "file".equals(amapLoc.k())) {
            return null;
        }
        com.amap.location.offline.upload.a.a(z ? 100035 : 100036);
        return amapLoc;
    }

    private bs a(CellStatus cellStatus) {
        String a2 = cn.a(cellStatus);
        return by.a(this.f4640a).a(a2, cn.a(a2));
    }

    private bu a(WifiStatus wifiStatus) {
        bu buVar = new bu();
        by.a(this.f4640a).a(a(wifiStatus, buVar), buVar);
        return buVar;
    }

    private String a(WifiStatus wifiStatus, bu buVar) {
        StringBuilder sb = new StringBuilder();
        if (wifiStatus != null && wifiStatus.a() > 0) {
            List<WiFi> b2 = wifiStatus.b();
            Collections.sort(b2, this.d);
            int min = Math.min(b2.size(), 30);
            buVar.f4643a = min;
            boolean z = true;
            for (int i = 0; i < min; i++) {
                WiFi wiFi = b2.get(i);
                long a2 = cn.a(wiFi.f4589a);
                if (a2 != -1) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(',');
                    }
                    sb.append(a2);
                    bt btVar = new bt();
                    btVar.f4642a = a2;
                    btVar.b = wiFi.f4589a;
                    btVar.c = wiFi.c;
                    buVar.b.put(Long.valueOf(a2), btVar);
                }
            }
        }
        return sb.toString();
    }

    private void a(AmapLoc amapLoc, bu buVar, bs bsVar, int i) {
        try {
            if (this.b.s != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(amapLoc.k());
                sb.append("|");
                sb.append(amapLoc.c());
                sb.append(",");
                sb.append(amapLoc.d());
                sb.append(",");
                sb.append(amapLoc.g());
                sb.append("|");
                if (amapLoc.k().equals(AmapLoc.h)) {
                    sb.append(buVar.d);
                    sb.append("@");
                    sb.append(buVar.e);
                    sb.append("@");
                    sb.append(i);
                    sb.append("@");
                    sb.append(buVar.f4643a);
                    if (bsVar != null) {
                        sb.append("@");
                        sb.append(bsVar.c + "," + bsVar.b + "," + bsVar.d);
                    }
                } else {
                    sb.append(bsVar.f);
                    sb.append("@");
                    sb.append(bsVar.c);
                    sb.append(",");
                    sb.append(bsVar.b);
                    sb.append(",");
                    sb.append(bsVar.d);
                }
                this.b.s.a(sb.toString().getBytes());
            }
        } catch (Exception unused) {
        }
    }

    public AmapLoc a(FPS fps, int i, boolean z) {
        AmapLoc amapLoc;
        String str;
        StringBuilder sb;
        try {
            bs a2 = a(fps.f4587a);
            bu a3 = a(fps.b);
            if (!z) {
                amapLoc = bq.a(a2, a3, i);
                if (amapLoc != null) {
                    str = "@_18_1_@";
                    sb = new StringBuilder("@_18_1_1_@");
                    sb.append(ALLog.a(a2.toString() + "," + a3.toString() + "," + i));
                } else {
                    str = "@_18_1_@";
                    sb = new StringBuilder("@_18_1_3_@");
                    sb.append(a2.f4641a);
                    sb.append(",");
                    sb.append(a3.f4643a);
                    sb.append(",");
                    sb.append(a3.c);
                }
                ALLog.d(str, sb.toString());
            } else {
                amapLoc = null;
            }
            br.a(this.f4640a, a2);
            br.a(this.f4640a, a3);
            cl.a().a(this.f4640a, a2);
            boolean z2 = true;
            this.e++;
            if (this.e > 20) {
                by.a(this.f4640a).b();
                this.e = 0;
            }
            if (i <= 0) {
                z2 = false;
            }
            AmapLoc a4 = a(z2, amapLoc);
            if (a4 != null) {
                a(a4, a3, a2, i);
            }
            return a4;
        } catch (Throwable unused) {
            return null;
        }
    }

    public void a() {
        this.c.a();
    }

    public void a(FPS fps, AmapLoc amapLoc) {
        String str;
        StringBuilder sb;
        bs a2 = a(fps.f4587a);
        bu a3 = a(fps.b);
        if (bq.a(a2, a3, 0) != null) {
            str = "@_18_1_@";
            sb = new StringBuilder("@_18_1_2_@");
            sb.append(ALLog.a(a2.toString() + "," + a3.toString() + ",(" + amapLoc.d() + "," + amapLoc.c() + Operators.BRACKET_END_STR));
        } else {
            str = "@_18_1_@";
            sb = new StringBuilder("@_18_1_4_@");
            sb.append(a2.f4641a);
            sb.append(",");
            sb.append(a3.c);
            sb.append(",");
            sb.append(a3.c);
        }
        ALLog.d(str, sb.toString());
        if (amapLoc != null) {
            br.a(this.f4640a, this.b, a2, a3, amapLoc);
        }
    }

    public void a(@NonNull OfflineConfig offlineConfig) {
        this.c.a(offlineConfig);
    }

    public void b() {
        this.c.b();
    }
}
