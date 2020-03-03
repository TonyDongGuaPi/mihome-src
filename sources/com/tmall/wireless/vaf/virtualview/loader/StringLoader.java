package com.tmall.wireless.vaf.virtualview.loader;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.libra.TextUtils;
import com.libra.expr.common.StringSupport;
import com.libra.virtualview.common.StringBase;
import java.util.Map;

public class StringLoader extends StringBase implements StringSupport {
    private static final String ch = "StringLoader_TMTEST";
    private Map<String, Integer> ci = new ArrayMap();
    private Map<Integer, String> cj = new ArrayMap();
    private Map<String, Integer> ck = new ArrayMap();
    private Map<Integer, String> cl = new ArrayMap();
    private int cm;

    public void b() {
    }

    public void d(int i) {
    }

    public StringLoader() {
        for (int i = 0; i < StringBase.c; i++) {
            this.ck.put(f6275a[i], Integer.valueOf(StringBase.b[i]));
            this.cl.put(Integer.valueOf(StringBase.b[i]), f6275a[i]);
        }
    }

    public void a() {
        this.ci.clear();
        this.cj.clear();
        this.ck.clear();
        this.cl.clear();
    }

    public void c(int i) {
        this.cm = i;
    }

    public boolean a(CodeReader codeReader, int i) {
        this.cm = i;
        int e = codeReader.e();
        int i2 = codeReader.i();
        int i3 = 0;
        while (i3 < i2) {
            int i4 = codeReader.i();
            short h = codeReader.h();
            if (codeReader.d() + h <= e) {
                String str = new String(codeReader.c(), codeReader.d(), h);
                this.cj.put(Integer.valueOf(i4), str);
                this.ci.put(str, Integer.valueOf(i4));
                codeReader.b(h);
                i3++;
            } else {
                Log.e(ch, "read string over");
                return false;
            }
        }
        return true;
    }

    public String a(int i) {
        if (this.cl.containsKey(Integer.valueOf(i))) {
            return this.cl.get(Integer.valueOf(i));
        }
        if (this.cj.containsKey(Integer.valueOf(i))) {
            return this.cj.get(Integer.valueOf(i));
        }
        Log.e(ch, "getString null:" + i);
        return null;
    }

    public int a(String str) {
        return a(str, true);
    }

    public int a(String str, boolean z) {
        int i = 0;
        if (TextUtils.a(str)) {
            return 0;
        }
        if (this.ck.containsKey(str)) {
            i = this.ck.get(str).intValue();
        }
        return (i != 0 || !this.ci.containsKey(str)) ? i : this.ci.get(str).intValue();
    }

    public boolean b(int i) {
        return this.cl.containsKey(Integer.valueOf(i));
    }

    public boolean b(String str) {
        return this.ck.containsKey(str);
    }
}
