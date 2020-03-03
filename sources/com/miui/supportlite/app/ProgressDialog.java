package com.miui.supportlite.app;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.miui.supportlite.R;
import java.text.NumberFormat;

public class ProgressDialog extends DialogFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8200a = 0;
    public static final int b = 1;
    /* access modifiers changed from: private */
    public ProgressBar c;
    private TextView d;
    private int e = 0;
    private String f;
    /* access modifiers changed from: private */
    public NumberFormat g;
    private DialogInterface.OnCancelListener h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private Drawable n;
    private Drawable o;
    private boolean p;
    private boolean q;
    private Handler r;
    /* access modifiers changed from: private */
    public String s;

    public ProgressDialog() {
        f();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        if (this.e == 1) {
            this.r = new Handler() {
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    int progress = ProgressDialog.this.c.getProgress();
                    int max = ProgressDialog.this.c.getMax();
                    if (ProgressDialog.this.g != null) {
                        double d = (double) progress;
                        double d2 = (double) max;
                        Double.isNaN(d);
                        Double.isNaN(d2);
                        double d3 = d / d2;
                        int i = 0;
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                        if (!TextUtils.isEmpty(ProgressDialog.this.s)) {
                            i = ProgressDialog.this.s.length();
                            spannableStringBuilder.append(ProgressDialog.this.s);
                        }
                        String format = ProgressDialog.this.g.format(d3);
                        spannableStringBuilder.append(format);
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(ProgressDialog.this.getActivity().getResources().getColor(R.color.miuisupport_progress_percent_color)), i, format.length() + i, 34);
                        ProgressDialog.this.a((CharSequence) spannableStringBuilder);
                    }
                }
            };
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        Window window = getDialog().getWindow();
        if (this.e == 1) {
            view = layoutInflater.inflate(R.layout.miuisupport_progress_dialog_horizontal, (ViewGroup) window.findViewById(16908290), false);
            this.c = (ProgressBar) view.findViewById(16908301);
            this.d = (TextView) view.findViewById(R.id.message);
            e();
        } else {
            view = layoutInflater.inflate(R.layout.miuisupport_progress_dialog, (ViewGroup) window.findViewById(16908290), false);
            ((ProgressBar) view.findViewById(R.id.progress_bar)).setInterpolator(new Interpolator() {
                public float getInterpolation(float f) {
                    return 0.0f;
                }
            });
            ((TextView) view.findViewById(R.id.message)).setText(this.s);
        }
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(80);
        window.setLayout(-1, -2);
        window.setDimAmount(0.5f);
        return view;
    }

    private void e() {
        if (this.i > 0) {
            c(this.i);
        }
        if (this.j > 0) {
            a(this.j);
        }
        if (this.k > 0) {
            b(this.k);
        }
        if (this.l > 0) {
            d(this.l);
        }
        if (this.m > 0) {
            e(this.m);
        }
        this.n = getActivity().getResources().getDrawable(R.drawable.miuisupport_progressbar_horizontal);
        if (this.n != null) {
            a(this.n);
        }
        if (this.o != null) {
            b(this.o);
        }
        if (this.s != null) {
            a((CharSequence) this.s);
        }
        a(this.p);
        g();
    }

    public void onStart() {
        super.onStart();
        this.q = true;
    }

    public void onStop() {
        super.onStop();
        this.q = false;
    }

    private void f() {
        this.f = "%1d/%2d";
        this.g = NumberFormat.getPercentInstance();
        this.g.setMaximumFractionDigits(0);
    }

    public ProgressDialog a(CharSequence charSequence) {
        if (this.e != 1 || this.d == null) {
            this.s = charSequence.toString();
        } else {
            this.s = charSequence.toString();
            this.d.setText(charSequence);
        }
        return this;
    }

    public void a(int i2) {
        if (this.q) {
            this.c.setProgress(i2);
            g();
            return;
        }
        this.j = i2;
    }

    public void b(int i2) {
        if (this.c != null) {
            this.c.setSecondaryProgress(i2);
            g();
            return;
        }
        this.k = i2;
    }

    public int a() {
        if (this.c != null) {
            return this.c.getProgress();
        }
        return this.j;
    }

    public int b() {
        if (this.c != null) {
            return this.c.getSecondaryProgress();
        }
        return this.k;
    }

    public int c() {
        if (this.c != null) {
            return this.c.getMax();
        }
        return this.i;
    }

    public void c(int i2) {
        if (this.c != null) {
            this.c.setMax(i2);
            g();
            return;
        }
        this.i = i2;
    }

    public void d(int i2) {
        if (this.c != null) {
            this.c.incrementProgressBy(i2);
            g();
            return;
        }
        this.l += i2;
    }

    public void e(int i2) {
        if (this.c != null) {
            this.c.incrementSecondaryProgressBy(i2);
            g();
            return;
        }
        this.m += i2;
    }

    public void a(Drawable drawable) {
        if (this.c != null) {
            this.c.setProgressDrawable(drawable);
        } else {
            this.n = drawable;
        }
    }

    public void b(Drawable drawable) {
        if (this.c != null) {
            this.c.setIndeterminateDrawable(drawable);
        } else {
            this.o = drawable;
        }
    }

    public void a(boolean z) {
        if (this.c != null) {
            this.c.setIndeterminate(z);
        } else {
            this.p = z;
        }
    }

    public boolean d() {
        if (this.c != null) {
            return this.c.isIndeterminate();
        }
        return this.p;
    }

    public void f(int i2) {
        this.e = i2;
    }

    public void a(String str) {
        this.f = str;
        g();
    }

    public void a(NumberFormat numberFormat) {
        this.g = numberFormat;
        g();
    }

    private void g() {
        if (this.e == 1 && this.r != null && !this.r.hasMessages(0)) {
            this.r.sendEmptyMessage(0);
        }
    }

    public void a(DialogInterface.OnCancelListener onCancelListener) {
        this.h = onCancelListener;
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (this.h != null) {
            this.h.onCancel(dialogInterface);
        }
    }
}
