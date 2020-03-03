package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.yeelight.VerticalSeekBar;

public class BrightIndicateView extends LinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public VerticalSeekBar f20458a;
    private ImageView b;
    private ImageView c;
    private ImageView d;

    public BrightIndicateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public BrightIndicateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public BrightIndicateView(Context context) {
        super(context);
        a(context);
    }

    public void setBlackType() {
        this.b.setImageResource(R.drawable.bright_indicate_triangle_black);
        this.c.setImageResource(R.drawable.bright_indicate_add_black);
        this.d.setImageResource(R.drawable.bright_indicate_dec_black);
        this.f20458a.post(new Runnable() {
            public void run() {
                Rect bounds = BrightIndicateView.this.f20458a.getProgressDrawable().getBounds();
                Drawable drawable = BrightIndicateView.this.getResources().getDrawable(R.drawable.seekbar_drawable_black);
                BrightIndicateView.this.f20458a.setProgressDrawable(drawable);
                drawable.setBounds(bounds);
            }
        });
    }

    public void setWhiteType() {
        this.b.setImageResource(R.drawable.bright_indicate_triangle_white);
        this.c.setImageResource(R.drawable.bright_indicate_add_white);
        this.d.setImageResource(R.drawable.bright_indicate_dec_white);
        this.f20458a.post(new Runnable() {
            public void run() {
                Rect bounds = BrightIndicateView.this.f20458a.getProgressDrawable().getBounds();
                Drawable drawable = BrightIndicateView.this.getResources().getDrawable(R.drawable.seekbar_drawable_white);
                BrightIndicateView.this.f20458a.setProgressDrawable(drawable);
                drawable.setBounds(bounds);
            }
        });
    }

    public void setValue(int i) {
        if (i <= 1) {
            i = 0;
        }
        if (i > 100) {
            i = 100;
        }
        this.f20458a.setProgress(i);
        Resources resources = getContext().getResources();
        float dimension = resources.getDimension(R.dimen.bright_indicate_length);
        float dimension2 = resources.getDimension(R.dimen.bright_indicate_margin_top);
        this.b.setY((((dimension * ((float) (100 - i))) / 100.0f) + dimension2) - (resources.getDimension(R.dimen.bright_indicate_triangle_size_height) / 2.0f));
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.widget_bright_indicate, this, true);
        this.f20458a = (VerticalSeekBar) inflate.findViewById(R.id.bringt_seek_bar);
        this.b = (ImageView) inflate.findViewById(R.id.bringt_seek_indicate);
        this.c = (ImageView) inflate.findViewById(R.id.bringt_add);
        this.d = (ImageView) inflate.findViewById(R.id.bringt_dec);
    }
}
