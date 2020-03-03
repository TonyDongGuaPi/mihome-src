package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.tmall.wireless.vaf.expr.engine.data.Data;
import com.tmall.wireless.vaf.expr.engine.data.Value;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class FunExecutor extends ArithExecutor {
    private static final String j = "FunExecutor_TMTEST";

    public int a(Object obj) {
        int a2 = super.a(obj);
        Set<Object> b = b();
        if (b != null) {
            int f = this.r.f();
            Value[] c = c();
            if (c == null || !a(f, this.r.d(), c, b)) {
                return a2;
            }
            return 1;
        }
        Log.e(j, "execute findObject failed");
        return a2;
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, int i2, Value[] valueArr, Set<Object> set) {
        int length = valueArr.length;
        Class[] clsArr = new Class[length];
        Object[] objArr = new Object[length];
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            clsArr[i3] = valueArr[i3].b();
            objArr[i3] = valueArr[i3].c();
        }
        String a2 = this.p.a(i);
        for (Object next : set) {
            try {
                Method method = next.getClass().getMethod(a2, clsArr);
                if (method != null) {
                    Object invoke = method.invoke(next, objArr);
                    Data a3 = this.s.a(i2);
                    if (invoke == null) {
                        a3.a();
                    } else if (!a3.a(invoke)) {
                        Log.e(j, "call set return value failed:" + invoke);
                    }
                    z = true;
                } else {
                    Log.e(j, "get method failed:" + next.getClass());
                }
            } catch (NoSuchMethodException e) {
                Log.e(j, "call get method failed:" + e + next);
            } catch (InvocationTargetException unused) {
            } catch (IllegalAccessException e2) {
                Log.e(j, "call get method failed:" + e2 + next);
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public Value[] c() {
        int d = this.r.d();
        Value[] valueArr = new Value[d];
        for (int i = 0; i < d; i++) {
            byte d2 = this.r.d();
            Data a2 = a((int) d2);
            if (a2 != null) {
                valueArr[i] = a2.f;
            } else {
                Log.e(j, "read param failed:" + d2);
                valueArr = null;
            }
        }
        return valueArr;
    }
}
