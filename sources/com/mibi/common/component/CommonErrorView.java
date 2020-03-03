package com.mibi.common.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mibi.common.R;
import miuipub.graphics.drawable.GifAnimationDrawable;

public class CommonErrorView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private View f7479a;
    private ProgressBar b;
    private TextView c;
    private View d;
    private ImageView e;
    private TextView f;
    private Button g;
    private boolean h;
    private boolean i;
    private String j;
    private String k;

    public CommonErrorView(Context context) {
        super(context);
    }

    public CommonErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonErrorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Mibi_CommonErrorView, i2, 0);
        this.h = obtainStyledAttributes.getBoolean(R.styleable.Mibi_CommonErrorView_showRetry, false);
        this.i = obtainStyledAttributes.getBoolean(R.styleable.Mibi_CommonErrorView_showErrorIcon, true);
        this.j = obtainStyledAttributes.getString(R.styleable.Mibi_CommonErrorView_gifProgressDrawable);
        obtainStyledAttributes.recycle();
        init(context);
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.mibi_common_error_view, this);
        this.f7479a = findViewById(R.id.progress_layout);
        this.b = (ProgressBar) findViewById(R.id.progress_bar);
        this.c = (TextView) findViewById(R.id.progress_summary);
        this.d = findViewById(R.id.error_layout);
        this.e = (ImageView) findViewById(R.id.warning_icon);
        this.f = (TextView) findViewById(R.id.error);
        this.g = (Button) findViewById(R.id.button_retry);
        if (!TextUtils.isEmpty(this.j)) {
            GifAnimationDrawable gifAnimationDrawable = new GifAnimationDrawable();
            if (gifAnimationDrawable.a(context, context.getAssets(), this.j)) {
                setProgressDrawable(gifAnimationDrawable);
            }
        }
    }

    public void setShowRetryButton(boolean z) {
        this.h = z;
    }

    public void setShowErrorIcon(boolean z) {
        this.i = z;
    }

    public void showError(String str) {
        showError(str, (View.OnClickListener) null);
    }

    public void showError(String str, View.OnClickListener onClickListener) {
        this.f7479a.setVisibility(8);
        this.d.setVisibility(0);
        this.f.setText(str);
        if (onClickListener != null || this.h) {
            this.g.setVisibility(0);
            this.g.setOnClickListener(onClickListener);
            return;
        }
        this.g.setVisibility(8);
    }

    public void setLoadingString(String str) {
        this.k = str;
    }

    public void startProgress() {
        this.b.setVisibility(0);
        this.c.setText(TextUtils.isEmpty(this.k) ? getResources().getString(R.string.mibi_progress_downloading) : this.k);
        this.d.setVisibility(8);
    }

    public void stopProgress() {
        this.b.setVisibility(8);
        this.c.setText("");
    }

    private void setProgressDrawable(GifAnimationDrawable gifAnimationDrawable) {
        if (gifAnimationDrawable != null) {
            ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
            layoutParams.width = gifAnimationDrawable.getIntrinsicWidth();
            layoutParams.height = gifAnimationDrawable.getIntrinsicHeight();
            this.b.setLayoutParams(layoutParams);
            this.b.setIndeterminateDrawable(gifAnimationDrawable);
        }
    }

    public void setErrorIcon(int i2) {
        this.e.setImageResource(i2);
    }
}
