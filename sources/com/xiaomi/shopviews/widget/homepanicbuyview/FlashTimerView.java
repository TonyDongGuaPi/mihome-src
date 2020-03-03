package com.xiaomi.shopviews.widget.homepanicbuyview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.shopviews.widget.R;

public class FlashTimerView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private CountDownTimer f13290a;
    private int b;
    private TextView c;
    private String d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private int j;
    private int k;
    private long l;

    public FlashTimerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FlashTimerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.panic_buy_flash_timer_view, this);
        this.g = (TextView) a(R.id.flash_timer_hour);
        this.h = (TextView) a(R.id.flash_timer_min);
        this.i = (TextView) a(R.id.flash_timer_sec);
        this.e = (TextView) a(R.id.flash_span_one);
        this.f = (TextView) a(R.id.flash_span_two);
        this.c = (TextView) a(R.id.flash_timer_end_desc);
    }

    private <T extends View> T a(int i2) {
        return findViewById(i2);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.l = 0;
        this.b = 0;
        this.j = 0;
        this.k = 0;
        c();
        setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.l >= 0) {
            this.b = ((int) this.l) / 3600;
            this.j = (((int) this.l) % 3600) / 60;
            this.k = (((int) this.l) % 3600) % 60;
            c();
            this.l--;
        }
    }

    private void c() {
        int i2 = this.b / 10;
        int i3 = this.b % 10;
        if (this.g != null) {
            TextView textView = this.g;
            textView.setText(String.valueOf(i2) + String.valueOf(i3));
        }
        TextView textView2 = this.h;
        textView2.setText(String.valueOf(this.j / 10) + String.valueOf(this.j % 10));
        TextView textView3 = this.i;
        textView3.setText(String.valueOf(this.k / 10) + String.valueOf(this.k % 10));
    }

    public void destroy() {
        if (this.f13290a != null) {
            this.f13290a.cancel();
        }
    }

    public void setEndTitle(String str) {
        this.d = str;
        if (TextUtils.isEmpty(str)) {
            this.c.setVisibility(8);
            this.c.setText("");
            return;
        }
        TextView textView = this.c;
        textView.setText("后" + this.d);
        this.c.setVisibility(0);
    }

    public void setItemBackground(Drawable drawable) {
        this.g.setBackgroundDrawable(drawable);
        this.h.setBackgroundDrawable(drawable);
        this.i.setBackgroundDrawable(drawable);
        this.c.setBackgroundDrawable(drawable);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.g.setTextColor(colorStateList);
        this.h.setTextColor(colorStateList);
        this.i.setTextColor(colorStateList);
        this.e.setTextColor(colorStateList);
        this.f.setTextColor(colorStateList);
        this.c.setTextColor(colorStateList);
    }

    public void setItemTextSize(int i2) {
        float f2 = (float) i2;
        this.g.setTextSize(1, f2);
        this.h.setTextSize(1, f2);
        this.i.setTextSize(1, f2);
        this.e.setTextSize(1, f2);
        this.f.setTextSize(1, f2);
        this.c.setTextSize(1, f2);
    }

    public void setWidthWrapContent() {
        this.e.getLayoutParams().width = -2;
        this.f.getLayoutParams().width = -2;
        this.g.getLayoutParams().width = -2;
        this.h.getLayoutParams().width = -2;
        this.i.getLayoutParams().width = -2;
    }

    public void showHour(boolean z) {
        int i2 = 8;
        this.g.setVisibility(z ? 0 : 8);
        TextView textView = this.e;
        if (z) {
            i2 = 0;
        }
        textView.setVisibility(i2);
    }

    public void start(long j2) {
        setVisibility(0);
        destroy();
        this.l = j2 / 1000;
        AnonymousClass1 r2 = new CountDownTimer(j2, 1000) {
            public void onFinish() {
                FlashTimerView.this.a();
            }

            public void onTick(long j) {
                FlashTimerView.this.b();
            }
        };
        this.f13290a = r2;
        r2.start();
    }
}
