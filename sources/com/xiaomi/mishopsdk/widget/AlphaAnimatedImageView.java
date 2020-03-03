package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.xiaomi.mishopsdk.R;

public class AlphaAnimatedImageView extends ImageView {
    private int mBgResId = 0;
    private int mSrcResId = 0;

    public AlphaAnimatedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setImageResource(context.obtainStyledAttributes(attributeSet, R.styleable.mishopsdk_AlphaAnimatedImageView).getResourceId(R.styleable.mishopsdk_AlphaAnimatedImageView_mishopsdk_source, 0));
    }

    public void setImageResource(int i) {
        if (i > 0 && this.mSrcResId != i) {
            setImageDrawable(new AlphaAnimatedDrawable(getResources(), ((BitmapDrawable) getResources().getDrawable(i)).getBitmap()));
        }
        this.mSrcResId = i;
    }

    public void setBackgroundResource(int i) {
        if (i > 0 && this.mBgResId != i) {
            setBackgroundDrawable(new AlphaAnimatedDrawable(getResources(), ((BitmapDrawable) getResources().getDrawable(i)).getBitmap()));
        }
        this.mBgResId = i;
    }
}
