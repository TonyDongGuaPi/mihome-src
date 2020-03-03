package com.xiaomi.smarthome.framework.page.verify.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class PinSoftKeyboard_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private PinSoftKeyboard f17089a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;

    @UiThread
    public PinSoftKeyboard_ViewBinding(PinSoftKeyboard pinSoftKeyboard) {
        this(pinSoftKeyboard, pinSoftKeyboard);
    }

    @UiThread
    public PinSoftKeyboard_ViewBinding(final PinSoftKeyboard pinSoftKeyboard, View view) {
        this.f17089a = pinSoftKeyboard;
        View findRequiredView = Utils.findRequiredView(view, R.id.xiao_sm_pin_1, "method 'onClickNumber'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.xiao_sm_pin_2, "method 'onClickNumber'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.xiao_sm_pin_3, "method 'onClickNumber'");
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.xiao_sm_pin_4, "method 'onClickNumber'");
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.xiao_sm_pin_5, "method 'onClickNumber'");
        this.f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.xiao_sm_pin_6, "method 'onClickNumber'");
        this.g = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.xiao_sm_pin_7, "method 'onClickNumber'");
        this.h = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.xiao_sm_pin_8, "method 'onClickNumber'");
        this.i = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.xiao_sm_pin_9, "method 'onClickNumber'");
        this.j = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.xiao_sm_pin_0, "method 'onClickNumber'");
        this.k = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickNumber(view);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.xiao_sm_pin_back, "method 'onClickPinBack'");
        this.l = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickPinBack(view);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.xiao_sm_pin_delete, "method 'onClickPinDelete'");
        this.m = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                pinSoftKeyboard.onClickPinDelete(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        if (this.f17089a != null) {
            this.f17089a = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            this.e.setOnClickListener((View.OnClickListener) null);
            this.e = null;
            this.f.setOnClickListener((View.OnClickListener) null);
            this.f = null;
            this.g.setOnClickListener((View.OnClickListener) null);
            this.g = null;
            this.h.setOnClickListener((View.OnClickListener) null);
            this.h = null;
            this.i.setOnClickListener((View.OnClickListener) null);
            this.i = null;
            this.j.setOnClickListener((View.OnClickListener) null);
            this.j = null;
            this.k.setOnClickListener((View.OnClickListener) null);
            this.k = null;
            this.l.setOnClickListener((View.OnClickListener) null);
            this.l = null;
            this.m.setOnClickListener((View.OnClickListener) null);
            this.m = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
