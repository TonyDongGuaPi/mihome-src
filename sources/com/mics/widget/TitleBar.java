package com.mics.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mics.R;
import com.mics.widget.util.StatusBarUtils;

public class TitleBar extends RelativeLayout implements View.OnTouchListener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public OnTitleClickListener f7826a;
    /* access modifiers changed from: private */
    public OnTitleLeftClickListener b;
    /* access modifiers changed from: private */
    public OnTitleRightClickListener c;
    private TextView d;
    private TextView e;

    public interface OnTitleClickListener {
        void a();

        void b();
    }

    public interface OnTitleLeftClickListener {
        void a();
    }

    public interface OnTitleRightClickListener {
        void a();
    }

    public TitleBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public TitleBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.mics_back_black);
        imageView.setPadding(a(8), a(15), a(15), a(15));
        imageView.setOnTouchListener(this);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TitleBar.this.b != null) {
                    TitleBar.this.b.a();
                } else if (TitleBar.this.f7826a != null) {
                    TitleBar.this.f7826a.a();
                }
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(12);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
        this.d = new TextView(getContext());
        this.d.setId(Build.VERSION.SDK_INT >= 17 ? View.generateViewId() : 20181220);
        this.d.setGravity(17);
        this.d.setText(R.string.mics_name);
        this.d.setTextSize(1, 15.0f);
        this.d.setTextColor(getResources().getColor(R.color.micsColorBlackB));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(14);
        this.d.setLayoutParams(layoutParams2);
        addView(this.d);
        this.e = new TextView(getContext());
        this.e.setPadding(a(10), a(5), a(10), a(5));
        this.e.setClickable(true);
        this.e.setGravity(17);
        this.e.setText(R.string.mics_transfer_human);
        this.e.setTextSize(1, 15.0f);
        this.e.setTextColor(getResources().getColor(R.color.micsColorBlackB));
        this.e.setBackgroundResource(R.drawable.mics_btn_common_bg);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TitleBar.this.c != null) {
                    TitleBar.this.c.a();
                } else if (TitleBar.this.f7826a != null) {
                    TitleBar.this.f7826a.b();
                }
            }
        });
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(4, this.d.getId());
        layoutParams3.addRule(11);
        layoutParams3.rightMargin = a(8);
        this.e.setLayoutParams(layoutParams3);
        addView(this.e);
        setOnTouchListener(this);
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.f7826a = onTitleClickListener;
    }

    public void setOnTitleLeftClickListener(OnTitleLeftClickListener onTitleLeftClickListener) {
        this.b = onTitleLeftClickListener;
    }

    public void setOnTitleRightClickListener(OnTitleRightClickListener onTitleRightClickListener) {
        this.c = onTitleRightClickListener;
    }

    public void setTitleText(CharSequence charSequence) {
        this.d.setText(charSequence);
    }

    public void setRightText(CharSequence charSequence) {
        this.e.setText(charSequence);
    }

    public void translucentStatus(Activity activity) {
        translucentStatus(activity, true);
    }

    public void translucentStatus(Activity activity, boolean z) {
        int a2;
        if (StatusBarUtils.a(activity, z) && (a2 = StatusBarUtils.a(activity.getApplicationContext())) != -1) {
            getLayoutParams().height += a2;
            setLayoutParams(getLayoutParams());
            setPadding(0, a2, 0, 0);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && (view instanceof ImageView)) {
            view.setAlpha(0.5f);
        }
        if ((motionEvent.getAction() != 1 && motionEvent.getAction() != 3 && motionEvent.getAction() != 4) || !(view instanceof ImageView)) {
            return false;
        }
        view.setAlpha(1.0f);
        return false;
    }

    private int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }
}
