package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import java.text.NumberFormat;

public class SmartHomeDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    int f18592a;
    int b;
    private Context c;
    private ProgressBar d;
    private TextView e;
    /* access modifiers changed from: private */
    public TextView f;
    private TextView g;
    private CharSequence h;
    private String i;
    /* access modifiers changed from: private */
    public NumberFormat j;
    private boolean k;
    private boolean l;
    private Handler m;

    public static SmartHomeDialog a(Context context, CharSequence charSequence, CharSequence charSequence2) {
        return a(context, charSequence, charSequence2, true);
    }

    public static SmartHomeDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return a(context, charSequence, charSequence2, z, false);
    }

    public static SmartHomeDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        return a(context, charSequence, charSequence2, z, z2, (DialogInterface.OnCancelListener) null);
    }

    public static SmartHomeDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2, DialogInterface.OnCancelListener onCancelListener) {
        SmartHomeDialog smartHomeDialog = new SmartHomeDialog(context);
        smartHomeDialog.setTitle(charSequence);
        smartHomeDialog.setMessage(charSequence2);
        smartHomeDialog.a(z);
        smartHomeDialog.setCancelable(z2);
        smartHomeDialog.setOnCancelListener(onCancelListener);
        smartHomeDialog.show();
        return smartHomeDialog;
    }

    public SmartHomeDialog(Context context) {
        this(context, 2131559365);
    }

    public SmartHomeDialog(Context context, int i2) {
        super(context, i2);
        this.h = null;
        d();
        this.c = context;
        a(true);
        setCancelable(true);
    }

    private void d() {
        this.i = "%1d/%2d";
        this.j = NumberFormat.getPercentInstance();
        this.j.setMaximumFractionDigits(0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.kuailian_progress_dialog, (ViewGroup) null);
        this.m = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (SmartHomeDialog.this.j != null) {
                    double d = (double) SmartHomeDialog.this.f18592a;
                    double d2 = (double) SmartHomeDialog.this.b;
                    Double.isNaN(d);
                    Double.isNaN(d2);
                    SmartHomeDialog.this.f.setText(new SpannableString(SmartHomeDialog.this.j.format(d / d2)));
                    return;
                }
                SmartHomeDialog.this.f.setText("");
            }
        };
        this.d = (ProgressBar) inflate.findViewById(R.id.indeterminate_progress);
        this.f = (TextView) inflate.findViewById(R.id.progress_percent);
        this.e = (TextView) inflate.findViewById(R.id.progress_message);
        this.g = (TextView) inflate.findViewById(R.id.cancel_btn);
        setView(inflate);
        this.d.setIndeterminate(true);
        if (this.h != null) {
            setMessage(this.h);
        }
        a(this.k);
        if (this.l) {
            this.g.setVisibility(0);
            this.g.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SmartHomeDialog.this.cancel();
                }
            });
        } else {
            this.g.setVisibility(8);
        }
        e();
        super.onCreate(bundle);
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
        this.l = z;
    }

    public void a(int i2) {
        this.b = i2;
        e();
    }

    public int a() {
        return this.b;
    }

    public void b(int i2) {
        this.f18592a = i2;
        e();
    }

    public int b() {
        return this.f18592a;
    }

    public void a(boolean z) {
        this.k = z;
        if (this.d != null) {
            this.d.setVisibility(0);
        }
        if (this.f != null && this.k) {
            this.f.setText("");
        }
    }

    public boolean c() {
        return this.k;
    }

    public void setMessage(CharSequence charSequence) {
        if (this.e != null) {
            this.e.setText(charSequence);
        } else {
            this.h = charSequence;
        }
    }

    public void a(String str) {
        this.i = str;
        e();
    }

    public void a(NumberFormat numberFormat) {
        this.j = numberFormat;
        e();
    }

    private void e() {
        if (!this.k && this.m != null && !this.m.hasMessages(0)) {
            this.m.sendEmptyMessage(0);
        }
    }

    public void c(int i2) {
        if (this.e != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.e.getText().toString());
            if (i2 != 0) {
                this.g.setBackgroundResource(R.drawable.common_green_btn);
                this.g.setText(R.string.complete);
                this.g.setTextColor(-1);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(-10572497), 4, String.valueOf(i2).length() + 4, 33);
            }
            this.e.setText(spannableStringBuilder);
        }
    }
}
