package com.tmall.wireless.vaf.virtualview.loader;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.libra.expr.common.ExprCode;

public class ExprCodeLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9400a = "CodeManager_TMTEST";
    private ArrayMap<Integer, ExprCode> b = new ArrayMap<>();

    public void a() {
    }

    public ExprCode a(int i) {
        return this.b.get(Integer.valueOf(i));
    }

    public boolean a(CodeReader codeReader, int i) {
        int e = codeReader.e();
        int i2 = codeReader.i();
        int i3 = 0;
        while (i3 < i2) {
            int i4 = codeReader.i();
            short h = codeReader.h();
            int d = codeReader.d();
            if (d + h <= e) {
                this.b.put(Integer.valueOf(i4), new ExprCode(codeReader.c(), d, h));
                codeReader.b(h);
                i3++;
            } else {
                Log.e(f9400a, "read string over");
                return false;
            }
        }
        return true;
    }
}
