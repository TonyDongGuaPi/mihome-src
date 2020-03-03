package com.amap.openapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.amap.location.common.log.ALLog;
import com.amap.location.common.network.HttpRequest;
import com.amap.location.common.network.HttpResponse;
import com.amap.location.common.util.d;
import com.amap.location.offline.IOfflineCloudConfig;
import com.amap.location.offline.OfflineConfig;
import com.amap.location.security.Core;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mishopsdk.util.Constants;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;

public class cd {

    /* renamed from: a  reason: collision with root package name */
    private Context f4659a;
    private OfflineConfig b;
    private IOfflineCloudConfig c;
    private cg d = new cg();
    private by e;
    private boolean f;
    private a g;

    interface a {
        void a();
    }

    public cd(Context context, OfflineConfig offlineConfig, IOfflineCloudConfig iOfflineCloudConfig, a aVar) {
        this.f4659a = context;
        this.b = offlineConfig;
        this.c = iOfflineCloudConfig;
        this.g = aVar;
    }

    private void a(ce ceVar) {
        this.f = false;
        if (ceVar != null) {
            if (ceVar.f4660a == 1) {
                cp.c(this.f4659a);
            } else if (this.g != null) {
                this.g.a();
            }
        }
    }

    private void a(ce ceVar, HttpResponse httpResponse) {
        if (httpResponse == null) {
            ALLog.d("@_18_6_@", "@_18_6_12_@");
            a(ceVar);
            return;
        }
        String str = null;
        List list = httpResponse.b.get("code");
        if (list != null) {
            str = (String) list.get(list.size() - 1);
        }
        ALLog.d("@_18_6_@", "@_18_6_13_@" + str);
        if (ceVar == null) {
            this.f = false;
            ALLog.d("@_18_6_@", "@_18_6_11_@");
        } else if (!"260".equals(str)) {
            a(ceVar);
        } else {
            if (ceVar.f4660a == 1) {
                cp.c(this.f4659a);
                if (ceVar.b == 0) {
                    cp.d(this.f4659a);
                }
            }
            if (ceVar.f4660a == 0) {
                cp.b(this.f4659a);
            }
            boolean b2 = b(ceVar, httpResponse);
            this.f = false;
            if ((b2 || ceVar.f4660a == 0) && this.g != null) {
                this.g.a();
            }
        }
    }

    private ce b(byte b2, int i) throws Exception {
        List<Long> list;
        List<String> list2;
        byte[] xxt;
        byte[] a2;
        byte b3 = b2;
        if (this.e == null) {
            this.e = by.a(this.f4659a);
        }
        if (b3 == 1) {
            int d2 = this.c.d();
            int i2 = this.c.i();
            List<Long> b4 = this.e.b(d2, i2);
            int size = b4.size();
            List<String> a3 = this.e.a(d2, size < i2 ? i2 - size : (i2 * 2) / 10);
            int size2 = a3.size();
            if (size2 > 0 && size == i2) {
                b4 = b4.subList(0, i2 - size2);
            }
            if (b4.size() + a3.size() < 5) {
                ALLog.d("@_18_6_@", "@_18_6_6_@");
                return null;
            }
            ALLog.d("@_18_6_@", "@_18_6_7_@(" + a3.size() + "," + b4.size() + Operators.BRACKET_END_STR);
            list2 = a3;
            list = b4;
        } else {
            ALLog.d("@_18_6_@", "@_18_6_8_@");
            list2 = null;
            list = null;
        }
        ce ceVar = new ce(b3, list, list2);
        ceVar.b = i;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/octet-stream");
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("gzipped", "1");
        hashMap.put("v", "1.4.0");
        hashMap.put("et", Constants.Plugin.PLUGINID_BARCODE);
        ceVar.e = hashMap;
        ce ceVar2 = ceVar;
        byte b5 = b2;
        byte[] a4 = this.d.a(b5, "1.4.0", this.b.h, this.b.i, this.b.j, (byte) com.amap.location.common.a.d(), this.b.l, this.b.m, this.b.n, com.amap.location.common.a.c(this.f4659a), com.amap.location.common.a.e(this.f4659a), com.amap.location.common.a.c(), com.amap.location.common.a.b(), this.b.o, this.b.p, list, list2);
        if (a4 == null || (xxt = Core.xxt(a4, 1)) == null || xxt.length == 0 || (a2 = d.a(xxt)) == null || a2.length == 0) {
            return null;
        }
        ce ceVar3 = ceVar2;
        ceVar3.f = a2;
        return ceVar3;
    }

    private boolean b(ce ceVar, HttpResponse httpResponse) {
        ck a2 = a(httpResponse);
        if (a2 == null) {
            ALLog.d("@_18_6_@", "@_18_6_10_@");
            return false;
        }
        if (this.e == null) {
            this.e = by.a(this.f4659a);
        }
        if (ceVar.f4660a == 0) {
            this.e.a(a2);
            return true;
        }
        this.e.a(a2, ceVar.c, ceVar.d, this.f4659a);
        return true;
    }

    public ck a(HttpResponse httpResponse) {
        try {
            List list = httpResponse.b.get("Content-Encoding");
            String str = (list == null || list.size() <= 0) ? null : (String) list.get(0);
            byte[] bArr = httpResponse.c;
            if (bArr != null && bArr.length > 0) {
                if ("gzip".equals(str)) {
                    bArr = d.b(bArr);
                }
                return ck.a(ByteBuffer.wrap(bArr));
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public void a(byte b2, int i) {
        this.f = true;
        try {
            ce b3 = b(b2, i);
            if (b3 == null || this.b.t == null) {
                this.f = false;
                return;
            }
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.f4591a = OfflineConfig.f4596a ? "http://aps.testing.amap.com/LoadOfflineData/repeatData" : "http://offline.aps.amap.com/LoadOfflineData/repeatData";
            httpRequest.b = b3.e;
            httpRequest.c = b3.f;
            a(b3, this.b.t.a(httpRequest));
        } catch (Throwable th) {
            this.f = false;
            ALLog.d("@_18_6_@", "@_18_6_2_@" + Log.getStackTraceString(th));
        }
    }

    public void a(@NonNull OfflineConfig offlineConfig) {
        this.b = offlineConfig;
    }

    public boolean a() {
        ALLog.d("@_18_6_@", "@_18_6_5_@" + this.f);
        return this.f;
    }
}
