package com.xiaomi.yp_ui.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.yp_ui.R;
import com.xiaomiyoupin.toast.dialog.CustomCircleProgressBar;
import com.xiaomiyoupin.toast.dialog.MLAlertDialog;
import java.text.NumberFormat;

public class XQProgressDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    int f1605a;
    int b;
    private Context c;
    private ProgressBar d;
    /* access modifiers changed from: private */
    public CustomCircleProgressBar e;
    private TextView f;
    /* access modifiers changed from: private */
    public TextView g;
    private TextView h;
    private CharSequence i;
    private String j;
    /* access modifiers changed from: private */
    public NumberFormat k;
    private boolean l;
    private boolean m;
    private Handler n;

    public XQProgressDialog(Context context) {
        this(context, R.style.V5_AlertDialog);
    }

    public XQProgressDialog(Context context, int i2) {
        super(context, i2);
        this.i = null;
        d();
        this.c = context;
        a(true);
        setCancelable(true);
    }

    public static XQProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2) {
        return a(context, charSequence, charSequence2, true);
    }

    public static XQProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return a(context, charSequence, charSequence2, z, false);
    }

    public static XQProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        return a(context, charSequence, charSequence2, z, z2, (DialogInterface.OnCancelListener) null);
    }

    public static XQProgressDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2, DialogInterface.OnCancelListener onCancelListener) {
        XQProgressDialog xQProgressDialog = new XQProgressDialog(context);
        xQProgressDialog.setTitle(charSequence);
        xQProgressDialog.setMessage(charSequence2);
        xQProgressDialog.a(z);
        xQProgressDialog.setCancelable(z2);
        xQProgressDialog.setOnCancelListener(onCancelListener);
        xQProgressDialog.show();
        return xQProgressDialog;
    }

    private void d() {
        this.j = "%1d/%2d";
        this.k = NumberFormat.getPercentInstance();
        this.k.setMaximumFractionDigits(0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.yp_xq_progress_dialog, (ViewGroup) null);
        this.n = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int max = XQProgressDialog.this.e.getMax();
                int progress = XQProgressDialog.this.e.getProgress();
                if (XQProgressDialog.this.k != null) {
                    double d = (double) progress;
                    double d2 = (double) max;
                    Double.isNaN(d);
                    Double.isNaN(d2);
                    XQProgressDialog.this.g.setText(new SpannableString(XQProgressDialog.this.k.format(d / d2)));
                    return;
                }
                XQProgressDialog.this.g.setText("");
            }
        };
        this.d = (ProgressBar) inflate.findViewById(R.id.indeterminate_progress);
        this.e = (CustomCircleProgressBar) inflate.findViewById(R.id.determinate_progress);
        this.g = (TextView) inflate.findViewById(R.id.progress_percent);
        this.f = (TextView) inflate.findViewById(R.id.progress_message);
        this.h = (TextView) inflate.findViewById(R.id.cancel_btn);
        setView(inflate);
        this.d.setIndeterminate(true);
        if (this.i != null) {
            setMessage(this.i);
        }
        a(this.l);
        if (this.m) {
            this.h.setVisibility(0);
            this.h.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    XQProgressDialog.this.cancel();
                }
            });
        } else {
            this.h.setVisibility(8);
        }
        if (this.f1605a > 0) {
            this.e.setProgress(this.f1605a);
        }
        if (this.b > 0) {
            this.e.setMax(this.b);
        }
        e();
        super.onCreate(bundle);
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
        this.m = z;
    }

    public int a() {
        if (this.e != null) {
            return this.e.getMax();
        }
        return 0;
    }

    public void a(int i2) {
        if (!this.l) {
            this.b = i2;
            if (this.e != null) {
                this.e.setMax(i2);
                e();
            }
        }
    }

    public int b() {
        if (this.e != null) {
            return this.e.getProgress();
        }
        return 0;
    }

    public void b(int i2) {
        if (!this.l) {
            this.f1605a = i2;
            if (this.e != null) {
                this.e.setProgress(i2);
                e();
            }
        }
    }

    public boolean c() {
        return this.l;
    }

    public void a(boolean z) {
        this.l = z;
        if (!(this.d == null || this.e == null)) {
            if (z) {
                this.d.setVisibility(0);
                this.e.setVisibility(8);
            } else {
                this.d.setVisibility(8);
                this.e.setVisibility(0);
            }
        }
        if (this.g != null && this.l) {
            this.g.setText("");
        }
    }

    public void setMessage(CharSequence charSequence) {
        if (this.f != null) {
            this.f.setText(charSequence);
        } else {
            this.i = charSequence;
        }
    }

    public void a(String str) {
        this.j = str;
        e();
    }

    public void a(NumberFormat numberFormat) {
        this.k = numberFormat;
        e();
    }

    private void e() {
        if (!this.l && this.n != null && !this.n.hasMessages(0)) {
            this.n.sendEmptyMessage(0);
        }
    }
}
