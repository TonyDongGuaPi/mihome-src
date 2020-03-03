package com.miui.supportlite.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import com.miui.supportlite.R;

public class SlidingButton extends RelativeLayout implements Checkable {

    /* renamed from: a  reason: collision with root package name */
    private OnCheckedChangeListener f8219a;
    private View b;
    private View c;
    private View d;
    private boolean e;

    public interface OnCheckedChangeListener {
        void a(SlidingButton slidingButton, boolean z);
    }

    public SlidingButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        addView(((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.miuisupport_sliding_button, (ViewGroup) null, false), -2, -2);
        this.b = findViewById(R.id.on);
        this.c = findViewById(R.id.off);
        this.d = findViewById(R.id.frame);
        setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SlidingButton.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        toggle();
    }

    public void setChecked(boolean z) {
        if (z) {
            this.b.setVisibility(0);
            this.c.setVisibility(8);
            this.d.setBackgroundResource(R.drawable.miuisupport_sliding_btn_frame_light_checked);
        } else {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.d.setBackgroundResource(R.drawable.miuisupport_sliding_btn_frame_light_unchecked);
        }
        if (this.f8219a != null) {
            this.f8219a.a(this, z);
        }
        this.e = z;
    }

    public boolean isChecked() {
        return this.e;
    }

    public void toggle() {
        setChecked(!this.e);
    }

    public void setOnCheckedChangedListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.f8219a = onCheckedChangeListener;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.b.setEnabled(z);
        this.c.setEnabled(z);
        this.d.setEnabled(z);
    }
}
