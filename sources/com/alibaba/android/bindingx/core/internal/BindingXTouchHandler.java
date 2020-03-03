package com.alibaba.android.bindingx.core.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.alibaba.android.bindingx.core.BindingXCore;
import com.alibaba.android.bindingx.core.LogProxy;
import com.alibaba.android.bindingx.core.PlatformManager;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BindingXTouchHandler extends AbstractEventHandler implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private float k;
    private float l;
    private double m;
    private double n;
    private GestureDetector o;
    private boolean p;
    private boolean q;

    public void b() {
    }

    public void c() {
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public BindingXTouchHandler(Context context, PlatformManager platformManager, Object... objArr) {
        super(context, platformManager, objArr);
        this.o = new GestureDetector(context, this);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.p = z;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        this.q = z;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.q;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.k = motionEvent.getRawX();
                    this.l = motionEvent.getRawY();
                    a("start", 0.0d, 0.0d, new Object[0]);
                    break;
                case 1:
                    this.k = 0.0f;
                    this.l = 0.0f;
                    d();
                    a("end", this.m, this.n, new Object[0]);
                    this.m = 0.0d;
                    this.n = 0.0d;
                    break;
                case 2:
                    if (this.k != 0.0f || this.l != 0.0f) {
                        this.m = (double) (motionEvent.getRawX() - this.k);
                        this.n = (double) (motionEvent.getRawY() - this.l);
                        break;
                    } else {
                        this.k = motionEvent.getRawX();
                        this.l = motionEvent.getRawY();
                        a("start", 0.0d, 0.0d, new Object[0]);
                        break;
                    }
                    break;
                case 3:
                    this.k = 0.0f;
                    this.l = 0.0f;
                    d();
                    a("cancel", this.m, this.n, new Object[0]);
                    break;
            }
        } catch (Exception e) {
            LogProxy.e("runtime error ", e);
        }
        return this.o.onTouchEvent(motionEvent);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float f3;
        float f4;
        if (!this.p) {
            LogProxy.c("pan gesture is not enabled");
            return false;
        }
        if (motionEvent == null) {
            f4 = this.k;
            f3 = this.l;
        } else {
            float rawX = motionEvent.getRawX();
            f3 = motionEvent.getRawY();
            f4 = rawX;
        }
        if (motionEvent2 == null) {
            return false;
        }
        float rawX2 = motionEvent2.getRawX() - f4;
        float rawY = motionEvent2.getRawY() - f3;
        try {
            if (LogProxy.f745a) {
                LogProxy.c(String.format(Locale.getDefault(), "[TouchHandler] pan moved. (x:%f,y:%f)", new Object[]{Float.valueOf(rawX2), Float.valueOf(rawY)}));
            }
            JSMath.a(this.d, (double) rawX2, (double) rawY, this.i.a());
            if (!a(this.j, (Map<String, Object>) this.d)) {
                a((Map<String, List<ExpressionHolder>>) this.f749a, (Map<String, Object>) this.d, "pan");
            }
        } catch (Exception e) {
            LogProxy.e("runtime error", e);
        }
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return !this.q ? false : false;
    }

    public boolean a(@NonNull String str, @NonNull String str2) {
        View a2 = this.i.b().a(str, TextUtils.isEmpty(this.f) ? this.e : this.f);
        if (a2 == null) {
            LogProxy.e("[ExpressionTouchHandler] onCreate failed. sourceView not found:" + str);
            return false;
        }
        a2.setOnTouchListener(this);
        LogProxy.c("[ExpressionTouchHandler] onCreate success. {source:" + str + ",type:" + str2 + "}");
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(@android.support.annotation.NonNull java.lang.String r3, @android.support.annotation.NonNull java.lang.String r4) {
        /*
            r2 = this;
            int r3 = r4.hashCode()
            r0 = 110749(0x1b09d, float:1.55192E-40)
            r1 = 1
            if (r3 == r0) goto L_0x001a
            r0 = 97520651(0x5d00c0b, float:1.956465E-35)
            if (r3 == r0) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r3 = "flick"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0024
            r3 = 1
            goto L_0x0025
        L_0x001a:
            java.lang.String r3 = "pan"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0024
            r3 = 0
            goto L_0x0025
        L_0x0024:
            r3 = -1
        L_0x0025:
            switch(r3) {
                case 0: goto L_0x002d;
                case 1: goto L_0x0029;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x0030
        L_0x0029:
            r2.b((boolean) r1)
            goto L_0x0030
        L_0x002d:
            r2.a(r1)
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.core.internal.BindingXTouchHandler.b(java.lang.String, java.lang.String):void");
    }

    public void a(@NonNull String str, @Nullable Map<String, Object> map, @Nullable ExpressionPair expressionPair, @NonNull List<Map<String, Object>> list, @Nullable BindingXCore.JavaScriptCallback javaScriptCallback) {
        super.a(str, map, expressionPair, list, javaScriptCallback);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0080 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(@android.support.annotation.NonNull java.lang.String r6, @android.support.annotation.NonNull java.lang.String r7) {
        /*
            r5 = this;
            int r0 = r7.hashCode()
            r1 = 110749(0x1b09d, float:1.55192E-40)
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x001b
            r1 = 97520651(0x5d00c0b, float:1.956465E-35)
            if (r0 == r1) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            java.lang.String r0 = "flick"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0025
            r0 = 1
            goto L_0x0026
        L_0x001b:
            java.lang.String r0 = "pan"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0025
            r0 = 0
            goto L_0x0026
        L_0x0025:
            r0 = -1
        L_0x0026:
            switch(r0) {
                case 0: goto L_0x002e;
                case 1: goto L_0x002a;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x0031
        L_0x002a:
            r5.b((boolean) r3)
            goto L_0x0031
        L_0x002e:
            r5.a(r3)
        L_0x0031:
            boolean r0 = r5.e()
            if (r0 != 0) goto L_0x0080
            boolean r0 = r5.f()
            if (r0 != 0) goto L_0x0080
            java.lang.String r0 = r5.f
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0048
            java.lang.String r0 = r5.e
            goto L_0x004a
        L_0x0048:
            java.lang.String r0 = r5.f
        L_0x004a:
            com.alibaba.android.bindingx.core.PlatformManager r1 = r5.i
            com.alibaba.android.bindingx.core.PlatformManager$IViewFinder r1 = r1.b()
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r4[r3] = r0
            android.view.View r0 = r1.a(r6, r4)
            if (r0 == 0) goto L_0x005e
            r1 = 0
            r0.setOnTouchListener(r1)
        L_0x005e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "remove touch listener success.["
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = ","
            r0.append(r6)
            r0.append(r7)
            java.lang.String r6 = "]"
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            com.alibaba.android.bindingx.core.LogProxy.c(r6)
            return r2
        L_0x0080:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.bindingx.core.internal.BindingXTouchHandler.c(java.lang.String, java.lang.String):boolean");
    }

    public void a() {
        super.a();
        if (this.f749a != null) {
            this.f749a.clear();
            this.f749a = null;
        }
        this.j = null;
        this.c = null;
        this.q = false;
        this.p = false;
    }

    /* access modifiers changed from: protected */
    public void b(@NonNull Map<String, Object> map) {
        a("exit", ((Double) map.get("internal_x")).doubleValue(), ((Double) map.get("internal_y")).doubleValue(), new Object[0]);
    }

    /* access modifiers changed from: protected */
    public void a(String str, @NonNull Map<String, Object> map) {
        a(BindingXConstants.h, ((Double) map.get("internal_x")).doubleValue(), ((Double) map.get("internal_y")).doubleValue(), Collections.singletonMap(BindingXConstants.h, str));
    }

    private void a(String str, double d, double d2, Object... objArr) {
        if (this.c != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("state", str);
            double b = this.i.a().b(d, new Object[0]);
            double b2 = this.i.a().b(d2, new Object[0]);
            hashMap.put("deltaX", Double.valueOf(b));
            hashMap.put("deltaY", Double.valueOf(b2));
            hashMap.put("token", this.g);
            if (objArr != null && objArr.length > 0 && (objArr[0] instanceof Map)) {
                hashMap.putAll(objArr[0]);
            }
            this.c.a(hashMap);
            LogProxy.c(">>>>>>>>>>>fire event:(" + str + "," + b + "," + b2 + Operators.BRACKET_END_STR);
        }
    }
}
