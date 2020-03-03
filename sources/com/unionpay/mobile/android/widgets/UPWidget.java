package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.u;
import org.json.JSONObject;

public final class UPWidget extends aa implements u.b {
    /* access modifiers changed from: private */
    public static final int o = (a.t / 3);
    protected int c = 0;
    /* access modifiers changed from: private */
    public long p;
    private boolean q = true;
    private String r = null;
    private boolean s = false;
    private ViewTreeObserver.OnGlobalLayoutListener t = new aw(this);
    private bb u = null;
    private View.OnClickListener v = new ax(this);

    public UPWidget(Context context, long j, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.p = j;
        this.b.a((u.b) this);
        this.b.a((InputFilter) new InputFilter.LengthFilter(6));
        this.b.f();
        this.b.d();
        e();
    }

    /* access modifiers changed from: private */
    public native void appendOnce(long j, String str);

    static /* synthetic */ void b(UPWidget uPWidget) {
        k.a("kb", "pwdInputFinished() +++");
        k.a("kb", "size = " + uPWidget.c);
        uPWidget.k();
        k.a("kb", "pwdInputFinished() ---");
    }

    private native void clearAll(long j);

    /* access modifiers changed from: private */
    public native void deleteOnce(long j);

    private native String getMsg(long j);

    private native String getMsgExtra(long j, String str);

    private void w() {
        if (x() != null) {
            x().getViewTreeObserver().removeGlobalOnLayoutListener(this.t);
        }
        if (this.u != null && this.u.b()) {
            this.u.a();
        }
    }

    /* access modifiers changed from: private */
    public View x() {
        return ((Activity) this.d).findViewById(8888);
    }

    public final String a() {
        return this.q ? getMsgExtra(this.p, this.r) : getMsg(this.p);
    }

    public final void a(long j) {
        this.p = j;
    }

    public final void a(boolean z) {
        this.s = z;
        if (z) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.b.getWindowToken(), 0);
            boolean z2 = true;
            int height = x().getRootView().getHeight() - x().getHeight();
            Rect rect = new Rect();
            getWindowVisibleDisplayFrame(rect);
            if (height != rect.top) {
                z2 = false;
            }
            if (z2) {
                l();
            } else if (!j()) {
                k.a("uppay", "key board is closing..");
                k.a("uppay", "registerKeyboardDissmisslisner() +++");
                if (x() != null) {
                    x().getViewTreeObserver().addOnGlobalLayoutListener(this.t);
                }
                k.a("uppay", "registerKeyboardDissmisslisner() ---");
            }
        } else {
            w();
        }
    }

    public final void a_() {
        if (this.s && !j()) {
            l();
        }
    }

    public final void b(String str) {
        this.r = str;
    }

    public final void b(boolean z) {
        this.q = z;
    }

    public final boolean b() {
        return this.c == 6;
    }

    public final boolean c() {
        k.a("uppay", "emptyCheck() +++ ");
        k.a("uppay", "mPINCounts =  " + this.c);
        k.a("uppay", "emptyCheck() --- ");
        return this.c != 0;
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "_bank_pwd";
    }

    public final void e() {
        clearAll(this.p);
        this.c = 0;
    }

    public final boolean j() {
        return this.u != null && this.u.b();
    }

    public final void k() {
        k.a("uppay", "closeCustomKeyboard() +++");
        if (j()) {
            w();
        }
        k.a("uppay", "closeCustomKeyboard() ---");
    }

    public final void l() {
        if (this.s && !j()) {
            this.u = new bb(getContext(), this.v, this);
            this.u.a((View) this);
            String str = "";
            for (int i = 0; i < this.c; i++) {
                str = str + "*";
            }
            this.b.c(str);
            this.b.b(str.length());
        }
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }
}
