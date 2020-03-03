package com.alibaba.android.bindingx.core.internal;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class AbstractScrollEventHandler extends AbstractEventHandler {
    protected int k;
    protected int l;
    private boolean m = false;

    public AbstractScrollEventHandler(Context context, PlatformManager platformManager, Object... objArr) {
        super(context, platformManager, objArr);
    }

    @CallSuper
    public boolean c(@NonNull String str, @NonNull String str2) {
        d();
        this.m = false;
        a("end", (double) this.k, (double) this.l, 0.0d, 0.0d, 0.0d, 0.0d, new Object[0]);
        return true;
    }

    /* access modifiers changed from: protected */
    public void b(@NonNull Map<String, Object> map) {
        Map<String, Object> map2 = map;
        a("exit", ((Double) map2.get("internal_x")).doubleValue(), ((Double) map2.get("internal_y")).doubleValue(), 0.0d, 0.0d, 0.0d, 0.0d, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void a(String str, @NonNull Map<String, Object> map) {
        Map<String, Object> map2 = map;
        a(BindingXConstants.h, ((Double) map2.get("internal_x")).doubleValue(), ((Double) map2.get("internal_y")).doubleValue(), ((Double) map2.get("dx")).doubleValue(), ((Double) map2.get(Constants.Name.DISTANCE_Y)).doubleValue(), ((Double) map2.get("tdx")).doubleValue(), ((Double) map2.get("tdy")).doubleValue(), Collections.singletonMap(BindingXConstants.h, str));
    }

    @CallSuper
    public void a() {
        super.a();
        this.m = false;
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, int i3, int i4, int i5, int i6) {
        AbstractScrollEventHandler abstractScrollEventHandler;
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        int i11 = i5;
        int i12 = i6;
        if (LogProxy.f745a) {
            LogProxy.c(String.format(Locale.getDefault(), "[ScrollHandler] scroll changed. (contentOffsetX:%d,contentOffsetY:%d,dx:%d,dy:%d,tdx:%d,tdy:%d)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)}));
        }
        this.k = i7;
        this.l = i8;
        if (!this.m) {
            this.m = true;
            double d = (double) i8;
            double d2 = (double) i10;
            double d3 = (double) i12;
            abstractScrollEventHandler = this;
            double d4 = d2;
            int i13 = i4;
            int i14 = i5;
            abstractScrollEventHandler.a("start", (double) i7, d, (double) i9, d4, (double) i11, d3, new Object[0]);
        } else {
            abstractScrollEventHandler = this;
        }
        try {
            JSMath.a(abstractScrollEventHandler.d, (double) i7, (double) i2, (double) i3, (double) i4, (double) i5, (double) i6, abstractScrollEventHandler.i.a());
            if (!abstractScrollEventHandler.a(abstractScrollEventHandler.j, (Map<String, Object>) abstractScrollEventHandler.d)) {
                abstractScrollEventHandler.a((Map<String, List<ExpressionHolder>>) abstractScrollEventHandler.f749a, (Map<String, Object>) abstractScrollEventHandler.d, "scroll");
            }
        } catch (Exception e) {
            LogProxy.e("runtime error", e);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, double d, double d2, double d3, double d4, double d5, double d6, Object... objArr) {
        String str2 = str;
        Object[] objArr2 = objArr;
        if (this.c != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("state", str2);
            double b = this.i.a().b(d, new Object[0]);
            double b2 = this.i.a().b(d2, new Object[0]);
            hashMap.put("x", Double.valueOf(b));
            hashMap.put(Constants.Name.Y, Double.valueOf(b2));
            double b3 = this.i.a().b(d3, new Object[0]);
            double b4 = this.i.a().b(d4, new Object[0]);
            hashMap.put("dx", Double.valueOf(b3));
            hashMap.put(Constants.Name.DISTANCE_Y, Double.valueOf(b4));
            double d7 = b;
            double b5 = this.i.a().b(d5, new Object[0]);
            double d8 = b4;
            double b6 = this.i.a().b(d6, new Object[0]);
            hashMap.put("tdx", Double.valueOf(b5));
            hashMap.put("tdy", Double.valueOf(b6));
            hashMap.put("token", this.g);
            if (objArr2 != null && objArr2.length > 0 && (objArr2[0] instanceof Map)) {
                hashMap.putAll((Map) objArr2[0]);
            }
            this.c.a(hashMap);
            LogProxy.c(">>>>>>>>>>>fire event:(" + str2 + "," + d7 + "," + b2 + "," + b3 + "," + d8 + "," + b5 + "," + b6 + Operators.BRACKET_END_STR);
        }
    }
}
