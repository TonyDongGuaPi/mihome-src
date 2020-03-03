package com.alibaba.android.bindingx.core.internal;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.BindingXJSFunctionRegister;
import com.alibaba.android.bindingx.core.BindingXPropertyInterceptor;
import com.alibaba.android.bindingx.core.IEventHandler;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractEventHandler implements IEventHandler {

    /* renamed from: a  reason: collision with root package name */
    protected volatile Map<String, List<ExpressionHolder>> f749a;
    protected volatile Map<String, ExpressionPair> b;
    protected BindingXCore.JavaScriptCallback c;
    protected final Map<String, Object> d = new HashMap();
    protected String e;
    protected String f;
    protected String g;
    protected Context h;
    protected PlatformManager i;
    protected ExpressionPair j;
    private Cache<String, Expression> k = new Cache<>(16);

    /* access modifiers changed from: protected */
    public abstract void a(String str, @NonNull Map<String, Object> map);

    /* access modifiers changed from: protected */
    public abstract void b(@NonNull Map<String, Object> map);

    public AbstractEventHandler(Context context, PlatformManager platformManager, Object... objArr) {
        this.h = context;
        this.i = platformManager;
        this.e = (objArr == null || objArr.length <= 0 || !(objArr[0] instanceof String)) ? null : objArr[0];
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(@NonNull String str, @Nullable Map<String, Object> map, @Nullable ExpressionPair expressionPair, @NonNull List<Map<String, Object>> list, @Nullable BindingXCore.JavaScriptCallback javaScriptCallback) {
        d();
        a(str, list);
        this.c = javaScriptCallback;
        this.j = expressionPair;
        if (!this.d.isEmpty()) {
            this.d.clear();
        }
        e();
    }

    @CallSuper
    public void a() {
        this.k.clear();
        BindingXPropertyInterceptor.a().c();
    }

    private void e() {
        JSMath.a(this.d);
        TimingFunctions.a(this.d);
        Map<String, JSFunctionInterface> c2 = BindingXJSFunctionRegister.a().c();
        if (c2 != null && !c2.isEmpty()) {
            this.d.putAll(c2);
        }
    }

    private void a(@NonNull String str, @NonNull List<Map<String, Object>> list) {
        Map<String, Object> map;
        if (this.f749a == null) {
            this.f749a = new HashMap();
        }
        for (Map next : list) {
            String a2 = Utils.a((Map<String, Object>) next, BindingXConstants.i);
            String a3 = Utils.a((Map<String, Object>) next, BindingXConstants.p);
            String a4 = Utils.a((Map<String, Object>) next, BindingXConstants.j);
            ExpressionPair b2 = Utils.b(next, BindingXConstants.k);
            Object obj = next.get("config");
            if (obj != null && (obj instanceof Map)) {
                try {
                    map = Utils.a(new JSONObject((Map) obj));
                } catch (Exception e2) {
                    LogProxy.e("parse config failed", e2);
                }
                if (!TextUtils.isEmpty(a2) || !TextUtils.isEmpty(a4) || b2 == null) {
                    LogProxy.e("skip illegal binding args[" + a2 + "," + a4 + "," + b2 + Operators.ARRAY_END_STR);
                } else {
                    ExpressionHolder expressionHolder = new ExpressionHolder(a2, a3, b2, a4, str, map);
                    List list2 = this.f749a.get(a2);
                    if (list2 == null) {
                        ArrayList arrayList = new ArrayList(4);
                        this.f749a.put(a2, arrayList);
                        arrayList.add(expressionHolder);
                    } else if (!list2.contains(expressionHolder)) {
                        list2.add(expressionHolder);
                    }
                }
            }
            map = null;
            if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a4)) {
            }
            LogProxy.e("skip illegal binding args[" + a2 + "," + a4 + "," + b2 + Operators.ARRAY_END_STR);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.alibaba.android.bindingx.core.internal.ExpressionPair r2, @android.support.annotation.NonNull java.util.Map<java.lang.String, java.lang.Object> r3) {
        /*
            r1 = this;
            boolean r0 = com.alibaba.android.bindingx.core.internal.ExpressionPair.a(r2)
            if (r0 == 0) goto L_0x001e
            com.alibaba.android.bindingx.core.internal.Expression r0 = new com.alibaba.android.bindingx.core.internal.Expression
            java.lang.String r2 = r2.b
            r0.<init>((java.lang.String) r2)
            java.lang.Object r2 = r0.a((java.util.Map<java.lang.String, java.lang.Object>) r3)     // Catch:{ Exception -> 0x0018 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Exception -> 0x0018 }
            boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x0018 }
            goto L_0x001f
        L_0x0018:
            r2 = move-exception
            java.lang.String r0 = "evaluateExitExpression failed. "
            com.alibaba.android.bindingx.core.LogProxy.e(r0, r2)
        L_0x001e:
            r2 = 0
        L_0x001f:
            if (r2 == 0) goto L_0x0033
            r1.d()
            r1.b((java.util.Map<java.lang.String, java.lang.Object>) r3)     // Catch:{ Exception -> 0x0028 }
            goto L_0x002e
        L_0x0028:
            r3 = move-exception
            java.lang.String r0 = "execute exit expression failed: "
            com.alibaba.android.bindingx.core.LogProxy.e(r0, r3)
        L_0x002e:
            java.lang.String r3 = "exit = true,consume finished"
            com.alibaba.android.bindingx.core.LogProxy.c(r3)
        L_0x0033:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.core.internal.AbstractEventHandler.a(com.alibaba.android.bindingx.core.internal.ExpressionPair, java.util.Map):boolean");
    }

    public void a(@Nullable Map<String, ExpressionPair> map) {
        this.b = map;
    }

    public void a(@NonNull String str, @NonNull ExpressionPair expressionPair, @NonNull Map<String, Object> map) {
        if (ExpressionPair.a(expressionPair)) {
            Expression expression = new Expression(expressionPair.b);
            boolean z = false;
            try {
                z = ((Boolean) expression.a(map)).booleanValue();
            } catch (Exception e2) {
                LogProxy.e("evaluate interceptor [" + str + "] expression failed. ", e2);
            }
            if (z) {
                a(str, map);
            }
        }
    }

    private void c(@NonNull Map<String, Object> map) {
        if (this.b != null && !this.b.isEmpty()) {
            for (Map.Entry next : this.b.entrySet()) {
                String str = (String) next.getKey();
                ExpressionPair expressionPair = (ExpressionPair) next.getValue();
                if (!TextUtils.isEmpty(str) && expressionPair != null) {
                    a(str, expressionPair, map);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(@Nullable Map<String, List<ExpressionHolder>> map, @NonNull Map<String, Object> map2, @NonNull String str) throws IllegalArgumentException, JSONException {
        Map<String, Object> map3 = map2;
        String str2 = str;
        c(map3);
        if (map == null) {
            LogProxy.e("expression args is null");
        } else if (map.isEmpty()) {
            LogProxy.e("no expression need consumed");
        } else {
            int i2 = 2;
            if (LogProxy.f745a) {
                LogProxy.c(String.format(Locale.getDefault(), "consume expression with %d tasks. event type is %s", new Object[]{Integer.valueOf(map.size()), str2}));
            }
            for (List<ExpressionHolder> it : map.values()) {
                for (ExpressionHolder expressionHolder : it) {
                    if (!str2.equals(expressionHolder.e)) {
                        LogProxy.c("skip expression with wrong event type.[expected:" + str2 + ",found:" + expressionHolder.e + Operators.ARRAY_END_STR);
                    } else {
                        String str3 = TextUtils.isEmpty(expressionHolder.b) ? this.e : expressionHolder.b;
                        ExpressionPair expressionPair = expressionHolder.c;
                        if (ExpressionPair.a(expressionPair)) {
                            Expression expression = (Expression) this.k.get(expressionPair.b);
                            if (expression == null) {
                                expression = new Expression(expressionPair.b);
                                this.k.put(expressionPair.b, expression);
                            }
                            Object a2 = expression.a(map3);
                            if (a2 == null) {
                                LogProxy.e("failed to execute expression,expression result is null");
                            } else if ((!(a2 instanceof Double) || !Double.isNaN(((Double) a2).doubleValue())) && (!(a2 instanceof Float) || !Float.isNaN(((Float) a2).floatValue()))) {
                                View a3 = this.i.b().a(expressionHolder.f756a, str3);
                                BindingXPropertyInterceptor a4 = BindingXPropertyInterceptor.a();
                                String str4 = expressionHolder.d;
                                PlatformManager.IDeviceResolutionTranslator a5 = this.i.a();
                                Map<String, Object> map4 = expressionHolder.f;
                                Object[] objArr = new Object[i2];
                                objArr[0] = expressionHolder.f756a;
                                objArr[1] = str3;
                                a4.a(a3, str4, a2, a5, map4, objArr);
                                if (a3 == null) {
                                    LogProxy.e("failed to execute expression,target view not found.[ref:" + expressionHolder.f756a + Operators.ARRAY_END_STR);
                                } else {
                                    this.i.c().a(a3, expressionHolder.d, a2, this.i.a(), expressionHolder.f, expressionHolder.f756a, str3);
                                }
                                i2 = 2;
                            } else {
                                LogProxy.e("failed to execute expression,expression result is NaN");
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        LogProxy.c("all expression are cleared");
        if (this.f749a != null) {
            this.f749a.clear();
            this.f749a = null;
        }
        this.j = null;
    }

    public void b(String str) {
        this.g = str;
    }

    static class Cache<K, V> extends LinkedHashMap<K, V> {
        private int maxSize;

        Cache(int i) {
            super(4, 0.75f, true);
            this.maxSize = Math.max(i, 4);
        }

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry entry) {
            return size() > this.maxSize;
        }
    }
}
