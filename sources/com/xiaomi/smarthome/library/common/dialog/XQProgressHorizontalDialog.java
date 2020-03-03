package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.library.R;
import java.text.NumberFormat;

public class XQProgressHorizontalDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f18621a;
    private ProgressBar b;
    private TextView c;
    private TextView d;
    private TextView e;
    private boolean f;
    private CharSequence g;
    private String h;
    private NumberFormat i;
    private boolean j;

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

    public XQProgressHorizontalDialog(Context context) {
        this(context, R.style.V5_AlertDialog);
    }

    public XQProgressHorizontalDialog(Context context, int i2) {
        super(context, i2);
        this.f = false;
        this.g = null;
        this.j = true;
        d();
        this.f18621a = context;
        setCancelable(true);
    }

    private void d() {
        this.h = "%1d/%2d";
        this.i = NumberFormat.getPercentInstance();
        this.i.setMaximumFractionDigits(0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.f18621a).inflate(R.layout.xq_progress_horizital_dialog, (ViewGroup) null);
        this.b = (ProgressBar) inflate.findViewById(R.id.progress);
        this.d = (TextView) inflate.findViewById(R.id.progress_percent);
        this.c = (TextView) inflate.findViewById(R.id.progress_message);
        this.e = (TextView) inflate.findViewById(R.id.progress_number);
        if (this.j) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        setView(inflate);
        if (this.g != null) {
            setMessage(this.g);
        }
        a(this.f);
        super.onCreate(bundle);
    }

    public void a(int i2, int i3) {
        if (this.b != null && i2 >= 0) {
            a(false);
            this.b.setMax(i2);
            this.b.setProgress(i3);
            if (this.i != null) {
                double d2 = (double) i3;
                double d3 = (double) i2;
                Double.isNaN(d2);
                Double.isNaN(d3);
                this.d.setText(new SpannableString(this.i.format(d2 / d3)));
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

    public void a(boolean z) {
        if (this.b != null) {
            this.b.setIndeterminate(z);
            if (z) {
                this.b.setIndeterminateDrawable(new ColorDrawable(-16655471) {
                    static final int c = 1000;
                    static final int d = 1700;

                    /* renamed from: a  reason: collision with root package name */
                    Paint f18622a = new Paint();
                    RectF b = new RectF();

                    public void draw(Canvas canvas) {
                        this.f18622a.setColor(getColor());
                        Rect bounds = getBounds();
                        float height = ((float) bounds.height()) * 0.15f;
                        this.b.top = ((float) bounds.top) + height;
                        this.b.bottom = ((float) bounds.bottom) - height;
                        float width = (float) bounds.width();
                        float abs = (0.4f * width) + (0.6f * width * Math.abs((((float) (System.currentTimeMillis() % 1700)) / 1700.0f) - 0.5f));
                        RectF rectF = this.b;
                        float currentTimeMillis = width * (((float) (System.currentTimeMillis() % 1000)) / 1000.0f);
                        rectF.left = ((float) bounds.left) + currentTimeMillis;
                        this.b.right = ((float) bounds.left) + abs + currentTimeMillis;
                        canvas.drawRoundRect(this.b, this.b.height(), this.b.height(), this.f18622a);
                        if (((float) bounds.right) < this.b.right) {
                            this.b.right -= (float) bounds.right;
                            this.b.left = -this.b.height();
                            canvas.drawRoundRect(this.b, this.b.height(), this.b.height(), this.f18622a);
                        }
                    }
                });
                return;
            }
            return;
        }
        this.f = z;
    }

    public void setMessage(CharSequence charSequence) {
        if (this.c != null) {
            this.c.setText(charSequence);
        } else {
            this.g = charSequence;
        }
    }

    public void c() {
        this.j = false;
    }
}
