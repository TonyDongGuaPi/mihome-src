package com.alibaba.android.bindingx.core.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.view.animation.AnimationUtils;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.BindingXEventType;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.alibaba.android.bindingx.core.internal.AnimationFrame;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BindingXTimingHandler extends AbstractEventHandler implements AnimationFrame.Callback {
    private long k = 0;
    private AnimationFrame l;
    private boolean m = false;

    public boolean a(@NonNull String str, @NonNull String str2) {
        return true;
    }

    public void b() {
    }

    public void b(@NonNull String str, @NonNull String str2) {
    }

    public void c() {
    }

    public BindingXTimingHandler(Context context, PlatformManager platformManager, Object... objArr) {
        super(context, platformManager, objArr);
        if (this.l == null) {
            this.l = AnimationFrame.a();
        } else {
            this.l.b();
        }
    }

    @VisibleForTesting
    BindingXTimingHandler(Context context, PlatformManager platformManager, AnimationFrame animationFrame, Object... objArr) {
        super(context, platformManager, objArr);
        this.l = animationFrame;
    }

    public void a(@NonNull String str, @Nullable Map<String, Object> map, @Nullable ExpressionPair expressionPair, @NonNull List<Map<String, Object>> list, @Nullable BindingXCore.JavaScriptCallback javaScriptCallback) {
        super.a(str, map, expressionPair, list, javaScriptCallback);
        if (this.l == null) {
            this.l = AnimationFrame.a();
        }
        a("start", 0, new Object[0]);
        this.l.b();
        this.l.a(this);
    }

    @WorkerThread
    private void e() {
        long j;
        if (this.k == 0) {
            this.k = AnimationUtils.currentAnimationTimeMillis();
            this.m = false;
            j = 0;
        } else {
            j = AnimationUtils.currentAnimationTimeMillis() - this.k;
        }
        try {
            if (LogProxy.f745a) {
                LogProxy.c(String.format(Locale.getDefault(), "[TimingHandler] timing elapsed. (t:%d)", new Object[]{Long.valueOf(j)}));
            }
            JSMath.a(this.d, (double) j);
            if (!this.m) {
                a((Map<String, List<ExpressionHolder>>) this.f749a, (Map<String, Object>) this.d, BindingXEventType.d);
            }
            this.m = a(this.j, (Map<String, Object>) this.d);
        } catch (Exception e) {
            LogProxy.e("runtime error", e);
        }
    }

    public boolean c(@NonNull String str, @NonNull String str2) {
        a("end", System.currentTimeMillis() - this.k, new Object[0]);
        d();
        if (this.l != null) {
            this.l.b();
        }
        this.k = 0;
        return true;
    }

    public void a() {
        super.a();
        d();
        if (this.l != null) {
            this.l.c();
            this.l = null;
        }
        this.k = 0;
    }

    /* access modifiers changed from: protected */
    public void b(@NonNull Map<String, Object> map) {
        a("exit", (long) ((Double) map.get("t")).doubleValue(), new Object[0]);
        if (this.l != null) {
            this.l.b();
        }
        this.k = 0;
    }

    /* access modifiers changed from: protected */
    public void a(String str, @NonNull Map<String, Object> map) {
        a(BindingXConstants.h, (long) ((Double) map.get("t")).doubleValue(), Collections.singletonMap(BindingXConstants.h, str));
    }

    private void a(String str, long j, Object... objArr) {
        if (this.c != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("state", str);
            hashMap.put("t", Long.valueOf(j));
            hashMap.put("token", this.g);
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Map)) {
                hashMap.putAll(objArr[0]);
            }
            this.c.a(hashMap);
            LogProxy.c(">>>>>>>>>>>fire event:(" + str + "," + j + Operators.BRACKET_END_STR);
        }
    }

    public void c_() {
        e();
    }
}
