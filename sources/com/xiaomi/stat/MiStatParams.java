package com.xiaomi.stat;

import com.xiaomi.stat.d.j;
import com.xiaomi.stat.d.k;
import com.xiaomi.stat.d.n;
import java.io.Reader;
import java.io.StringReader;
import org.json.JSONException;
import org.json.JSONObject;

public class MiStatParams {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22994a = "MiStatParams";
    private JSONObject b;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    public MiStatParams() {
        this.b = new JSONObject();
    }

    MiStatParams(MiStatParams miStatParams) {
        if (miStatParams == null || miStatParams.b == null) {
            this.b = new JSONObject();
        } else {
            this.b = a(miStatParams.b);
        }
    }

    private JSONObject a(JSONObject jSONObject) {
        StringReader stringReader;
        Exception e;
        try {
            stringReader = new StringReader(jSONObject.toString());
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int read = stringReader.read();
                    if (read != -1) {
                        sb.append((char) read);
                    } else {
                        JSONObject jSONObject2 = new JSONObject(sb.toString());
                        j.a((Reader) stringReader);
                        return jSONObject2;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    k.e(" deepCopy " + e);
                    j.a((Reader) stringReader);
                    return jSONObject;
                } catch (Throwable th) {
                    th = th;
                    j.a((Reader) stringReader);
                    throw th;
                }
            }
        } catch (Exception e3) {
            Exception exc = e3;
            stringReader = null;
            e = exc;
            k.e(" deepCopy " + e);
            j.a((Reader) stringReader);
            return jSONObject;
        } catch (Throwable th2) {
            th = th2;
            stringReader = null;
            j.a((Reader) stringReader);
            throw th;
        }
    }

    public void putInt(String str, int i) {
        if (!a(str)) {
            n.e(str);
        } else if (c(str)) {
            n.a();
        } else {
            try {
                this.b.put(str, i);
            } catch (JSONException e) {
                k.c(f22994a, "put value error " + e);
            }
        }
    }

    public void putLong(String str, long j) {
        if (!a(str)) {
            n.e(str);
        } else if (c(str)) {
            n.a();
        } else {
            try {
                this.b.put(str, j);
            } catch (JSONException e) {
                k.c(f22994a, "put value error " + e);
            }
        }
    }

    public void putDouble(String str, double d) {
        if (!a(str)) {
            n.e(str);
        } else if (c(str)) {
            n.a();
        } else {
            try {
                this.b.put(str, d);
            } catch (JSONException e) {
                k.c(f22994a, "put value error " + e);
            }
        }
    }

    public void putString(String str, String str2) {
        if (!a(str)) {
            n.e(str);
        } else if (!b(str2)) {
            n.f(str2);
        } else if (c(str)) {
            n.a();
        } else {
            try {
                this.b.put(str, n.c(str2));
            } catch (JSONException e) {
                k.c(f22994a, "put value error " + e);
            }
        }
    }

    public void putBoolean(String str, boolean z) {
        if (!a(str)) {
            n.e(str);
        } else if (c(str)) {
            n.a();
        } else {
            try {
                this.b.put(str, z);
            } catch (JSONException e) {
                k.c(f22994a, "put value error " + e);
            }
        }
    }

    public String toJsonString() {
        return this.b.toString();
    }

    public boolean isEmpty() {
        return this.b.length() == 0;
    }

    public int getParamsNumber() {
        return this.b.length();
    }

    private boolean c(String str) {
        return a() && !this.b.has(str) && this.b.length() == 30;
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) {
        return n.a(str);
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str) {
        return n.b(str);
    }
}
