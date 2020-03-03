package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mi.global.bbs.R;

public class ProfileMesView extends RelativeLayout {
    private ImageView ivMessage;
    private ImageView ivMessageTip;

    public ProfileMesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.bbs_rel_profile_mes, this);
        this.ivMessageTip = (ImageView) findViewById(R.id.iv_update_tip);
        this.ivMessage = (ImageView) findViewById(R.id.iv_update_content);
    }

    public ProfileMesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public ProfileMesView(Context context) {
        super(context);
        initView(context);
    }

    public void setMesCount(int i) {
        if (i > 0) {
            this.ivMessageTip.setVisibility(8);
        } else {
            this.ivMessageTip.setVisibility(8);
        }
        requestLayout();
    }

    public void setMessageIcon(Drawable drawable) {
        if (drawable != null) {
            this.ivMessage.setImageDrawable(drawable);
            invalidate();
        }
    }
}
