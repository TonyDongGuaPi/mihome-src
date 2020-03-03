package com.xiaomi.yp_ui.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.yp_ui.R;
import com.xiaomiyoupin.toast.dialog.MLAlertDialog;
import java.text.NumberFormat;

public class XQProgressHorizontalDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f1609a;
    private ProgressBar b;
    private TextView c;
    private TextView d;
    private TextView e;
    private CharSequence f;
    private String g;
    private NumberFormat h;
    private boolean i;

    public XQProgressHorizontalDialog(Context context) {
        this(context, R.style.V5_AlertDialog);
    }

    public XQProgressHorizontalDialog(Context context, int i2) {
        super(context, i2);
        this.f = null;
        this.i = true;
        d();
        this.f1609a = context;
        setCancelable(true);
    }

    public static XQProgressHorizontalDialog a(Context context, CharSequence charSequence) {
        XQProgressHorizontalDialog xQProgressHorizontalDialog = new XQProgressHorizontalDialog(context);
        xQProgressHorizontalDialog.setMessage(charSequence);
        xQProgressHorizontalDialog.show();
        return xQProgressHorizontalDialog;
    }

    public static XQProgressHorizontalDialog b(Context context, CharSequence charSequence) {
        XQProgressHorizontalDialog xQProgressHorizontalDialog = new XQProgressHorizontalDialog(context);
        xQProgressHorizontalDialog.setMessage(charSequence);
        return xQProgressHorizontalDialog;
    }

    private void d() {
        this.g = "%1d/%2d";
        this.h = NumberFormat.getPercentInstance();
        this.h.setMaximumFractionDigits(0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.f1609a).inflate(R.layout.yp_xq_progress_horizital_dialog, (ViewGroup) null);
        this.b = (ProgressBar) inflate.findViewById(R.id.progress);
        this.d = (TextView) inflate.findViewById(R.id.progress_percent);
        this.c = (TextView) inflate.findViewById(R.id.progress_message);
        this.e = (TextView) inflate.findViewById(R.id.progress_number);
        if (this.i) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        setView(inflate);
        if (this.f != null) {
            setMessage(this.f);
        }
        super.onCreate(bundle);
    }

    public void a(int i2, int i3) {
        if (this.b != null && i2 >= 0) {
            this.b.setMax(i2);
            this.b.setProgress(i3);
            if (this.h != null) {
                double d2 = (double) i3;
                double d3 = (double) i2;
                Double.isNaN(d2);
                Double.isNaN(d3);
                this.d.setText(new SpannableString(this.h.format(d2 / d3)));
            } else {
                this.d.setText("");
            }
            if (i2 > 1) {
                TextView textView = this.e;
                textView.setText("" + (i3 / 1024) + "K/" + (i2 / 1024) + "K");
                return;
            }
            this.e.setText("");
        }
    }

    public int a() {
        if (this.b != null) {
            return this.b.getMax();
        }
        return 0;
    }

    public int b() {
        if (this.b != null) {
            return this.b.getProgress();
        }
        return 0;
    }

    public void setMessage(CharSequence charSequence) {
        if (this.c != null) {
            this.c.setText(charSequence);
        } else {
            this.f = charSequence;
        }
    }

    public void c() {
        this.i = false;
    }
}
