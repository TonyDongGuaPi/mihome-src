package miuipub.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.NumberFormat;
import miuipub.v6.R;

public class ProgressDialog extends AlertDialog {
    public static final int g = 0;
    public static final int h = 1;
    /* access modifiers changed from: private */
    public ProgressBar i;
    /* access modifiers changed from: private */
    public TextView j;
    private int k = 0;
    private String l;
    /* access modifiers changed from: private */
    public NumberFormat m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private Drawable s;
    private Drawable t;
    /* access modifiers changed from: private */
    public CharSequence u;
    private boolean v;
    private boolean w;
    private Handler x;

    public ProgressDialog(Context context) {
        super(context);
        j();
    }

    public ProgressDialog(Context context, int i2) {
        super(context, i2);
        j();
    }

    private void j() {
        this.l = "%1d/%2d";
        this.m = NumberFormat.getPercentInstance();
        this.m.setMaximumFractionDigits(0);
    }

    public static ProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2) {
        return a(context, charSequence, charSequence2, false);
    }

    public static ProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return a(context, charSequence, charSequence2, z, false, (DialogInterface.OnCancelListener) null);
    }

    public static ProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        return a(context, charSequence, charSequence2, z, z2, (DialogInterface.OnCancelListener) null);
    }

    public static ProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2, DialogInterface.OnCancelListener onCancelListener) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(charSequence);
        progressDialog.a(charSequence2);
        progressDialog.a(z);
        progressDialog.setCancelable(z2);
        progressDialog.setOnCancelListener(onCancelListener);
        progressDialog.show();
        return progressDialog;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View view;
        LayoutInflater from = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, R.styleable.V6_AlertDialog, 16842845, 0);
        if (this.k == 1) {
            this.x = new Handler() {
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    int progress = ProgressDialog.this.i.getProgress();
                    int max = ProgressDialog.this.i.getMax();
                    if (ProgressDialog.this.m != null) {
                        double d = (double) progress;
                        double d2 = (double) max;
                        Double.isNaN(d);
                        Double.isNaN(d2);
                        double d3 = d / d2;
                        int i = 0;
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                        if (!TextUtils.isEmpty(ProgressDialog.this.u)) {
                            i = ProgressDialog.this.u.length();
                            spannableStringBuilder.append(ProgressDialog.this.u);
                        }
                        String format = ProgressDialog.this.m.format(d3);
                        spannableStringBuilder.append(format);
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(ProgressDialog.this.getContext().getResources().getColor(R.color.v6_progress_percent_color)), i, format.length() + i, 34);
                        ProgressDialog.this.j.setText(spannableStringBuilder);
                    }
                }
            };
            view = from.inflate(obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_horizontalProgressLayout, R.layout.v6_alert_dialog_progress), (ViewGroup) null);
        } else {
            view = from.inflate(obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_progressLayout, R.layout.v6_progress_dialog), (ViewGroup) null);
        }
        this.i = (ProgressBar) view.findViewById(16908301);
        this.j = (TextView) view.findViewById(R.id.message);
        b(view);
        obtainStyledAttributes.recycle();
        if (this.n > 0) {
            d(this.n);
        }
        if (this.o > 0) {
            b(this.o);
        }
        if (this.p > 0) {
            c(this.p);
        }
        if (this.q > 0) {
            e(this.q);
        }
        if (this.r > 0) {
            f(this.r);
        }
        if (this.s != null) {
            a(this.s);
        }
        if (this.t != null) {
            b(this.t);
        }
        if (this.u != null) {
            a(this.u);
        }
        a(this.v);
        k();
        super.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
        this.w = true;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.w = false;
    }

    public void b(int i2) {
        if (this.w) {
            this.i.setProgress(i2);
            k();
            return;
        }
        this.o = i2;
    }

    public void c(int i2) {
        if (this.i != null) {
            this.i.setSecondaryProgress(i2);
            k();
            return;
        }
        this.p = i2;
    }

    public int f() {
        if (this.i != null) {
            return this.i.getProgress();
        }
        return this.o;
    }

    public int g() {
        if (this.i != null) {
            return this.i.getSecondaryProgress();
        }
        return this.p;
    }

    public int h() {
        if (this.i != null) {
            return this.i.getMax();
        }
        return this.n;
    }

    public void d(int i2) {
        if (this.i != null) {
            this.i.setMax(i2);
            k();
            return;
        }
        this.n = i2;
    }

    public void e(int i2) {
        if (this.i != null) {
            this.i.incrementProgressBy(i2);
            k();
            return;
        }
        this.q += i2;
    }

    public void f(int i2) {
        if (this.i != null) {
            this.i.incrementSecondaryProgressBy(i2);
            k();
            return;
        }
        this.r += i2;
    }

    public void a(Drawable drawable) {
        if (this.i != null) {
            this.i.setProgressDrawable(drawable);
        } else {
            this.s = drawable;
        }
    }

    public void b(Drawable drawable) {
        if (this.i != null) {
            this.i.setIndeterminateDrawable(drawable);
        } else {
            this.t = drawable;
        }
    }

    public void a(boolean z) {
        if (this.i != null) {
            this.i.setIndeterminate(z);
        } else {
            this.v = z;
        }
    }

    public boolean i() {
        if (this.i != null) {
            return this.i.isIndeterminate();
        }
        return this.v;
    }

    public void a(CharSequence charSequence) {
        if (this.i != null) {
            if (this.k == 1) {
                this.u = charSequence;
            }
            this.j.setText(charSequence);
            return;
        }
        this.u = charSequence;
    }

    public void g(int i2) {
        this.k = i2;
    }

    public void a(String str) {
        this.l = str;
        k();
    }

    public void a(NumberFormat numberFormat) {
        this.m = numberFormat;
        k();
    }

    private void k() {
        if (this.k == 1 && this.x != null && !this.x.hasMessages(0)) {
            this.x.sendEmptyMessage(0);
        }
    }
}
