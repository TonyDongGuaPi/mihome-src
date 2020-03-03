package com.mi.global.bbs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.util.Coder;

public class HeadLogoView extends RelativeLayout {
    private final int DEFAULT_HEAD_HEIGHT;
    private final int DEFAULT_HEAD_WIDTH;
    private final int DEFAULT_LOGO_HEIGHT;
    private final int DEFAULT_LOGO_WIDTH;
    private ImageView ivUserHead;
    private ImageView ivUserHeadLogo;
    private int mHeadHeight;
    private int mHeadWidth;
    private int mLogoHeight;
    private int mLogoWidth;

    public HeadLogoView(Context context) {
        this(context, (AttributeSet) null);
    }

    public HeadLogoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HeadLogoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DEFAULT_HEAD_WIDTH = Coder.a(30.0f);
        this.DEFAULT_HEAD_HEIGHT = Coder.a(30.0f);
        this.DEFAULT_LOGO_WIDTH = Coder.a(12.0f);
        this.DEFAULT_LOGO_HEIGHT = Coder.a(12.0f);
        this.mHeadWidth = this.DEFAULT_HEAD_WIDTH;
        this.mHeadHeight = this.DEFAULT_HEAD_HEIGHT;
        this.mLogoWidth = this.DEFAULT_LOGO_WIDTH;
        this.mLogoHeight = this.DEFAULT_LOGO_HEIGHT;
        initView(context, attributeSet);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HeadLogoView);
            this.mHeadWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HeadLogoView_head_width, this.DEFAULT_HEAD_WIDTH);
            this.mHeadHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HeadLogoView_head_height, this.DEFAULT_HEAD_HEIGHT);
            this.mLogoWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HeadLogoView_logo_width, this.DEFAULT_LOGO_WIDTH);
            this.mLogoHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.HeadLogoView_logo_height, this.DEFAULT_LOGO_HEIGHT);
        }
        View.inflate(context, R.layout.bbs_user_head_logo_layout, this);
        this.ivUserHead = (ImageView) findViewById(R.id.iv_user_head);
        this.ivUserHeadLogo = (ImageView) findViewById(R.id.iv_user_head_logo);
        this.ivUserHead.getLayoutParams().width = this.mHeadWidth;
        this.ivUserHead.getLayoutParams().height = this.mHeadHeight;
        this.ivUserHeadLogo.getLayoutParams().width = this.mLogoWidth;
        this.ivUserHeadLogo.getLayoutParams().height = this.mLogoHeight;
        this.ivUserHead.requestLayout();
        this.ivUserHeadLogo.requestLayout();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.ivUserHeadLogo.getLayoutParams();
        layoutParams.setMargins((this.mHeadWidth * 2) / 3, (this.mHeadHeight * 2) / 3, 0, 0);
        this.ivUserHeadLogo.setLayoutParams(layoutParams);
    }

    public void setUserHead(String str) {
        this.ivUserHeadLogo.setVisibility(4);
        ImageLoader.showCircleHeadIcon(this.ivUserHead, str);
    }

    public void setUserHead(String str, String str2) {
        this.ivUserHeadLogo.setVisibility(0);
        ImageLoader.showCircleHeadIcon(this.ivUserHead, str);
        ImageLoader.showCircleHeadLogoIcon(this.ivUserHeadLogo, str2);
    }
}
