package com.ximalaya.ting.android.opensdk.auth.model;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.ximalaya.ting.android.opensdk.auth.utils.Logger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Set;

public class XmlyParameters {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1837a = "UTF-8";
    private LinkedHashMap<String, Object> b = new LinkedHashMap<>();
    private String c;

    public XmlyParameters(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }

    public LinkedHashMap<String, Object> b() {
        return this.b;
    }

    public void a(LinkedHashMap<String, Object> linkedHashMap) {
        this.b = linkedHashMap;
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public void a(String str, int i) {
        this.b.put(str, String.valueOf(i));
    }

    public void a(String str, long j) {
        this.b.put(str, String.valueOf(j));
    }

    public void a(String str, Bitmap bitmap) {
        this.b.put(str, bitmap);
    }

    public void a(String str, Object obj) {
        this.b.put(str, obj.toString());
    }

    public Object a(String str) {
        return this.b.get(str);
    }

    public void b(String str) {
        if (this.b.containsKey(str)) {
            this.b.remove(str);
            this.b.remove(this.b.get(str));
        }
    }

    public Set<String> c() {
        return this.b.keySet();
    }

    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    public boolean d(String str) {
        return this.b.containsValue(str);
    }

    public int d() {
        return this.b.size();
    }

    public String e() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String next : this.b.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(a.b);
            }
            Object obj = this.b.get(next);
            if (obj instanceof String) {
                String str = (String) obj;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        sb.append(URLEncoder.encode(next, "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Logger.b("encodeUrl", sb.toString());
            }
        }
        return sb.toString();
    }

    public String f() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String next : this.b.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(a.b);
            }
            Object obj = this.b.get(next);
            if (obj instanceof String) {
                String str = (String) obj;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        sb.append(URLEncoder.encode(next, "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Logger.b("encodeUrl", sb.toString());
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0012  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean g() {
        /*
            r3 = this;
            java.util.LinkedHashMap<java.lang.String, java.lang.Object> r0 = r3.b
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x0012
            r0 = 0
            return r0
        L_0x0012:
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.LinkedHashMap<java.lang.String, java.lang.Object> r2 = r3.b
            java.lang.Object r1 = r2.get(r1)
            boolean r2 = r1 instanceof java.io.ByteArrayOutputStream
            if (r2 != 0) goto L_0x0026
            boolean r1 = r1 instanceof android.graphics.Bitmap
            if (r1 == 0) goto L_0x000a
        L_0x0026:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.model.XmlyParameters.g():boolean");
    }
}
