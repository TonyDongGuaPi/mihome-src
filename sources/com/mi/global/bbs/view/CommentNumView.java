package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.mi.global.bbs.R;

public class CommentNumView extends FrameLayout {
    private ImageView ivMessage;
    private TextView tvCount;

    public CommentNumView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.comment_num_view, this);
        this.tvCount = (TextView) findViewById(R.id.tv_like_view_count);
        this.ivMessage = (ImageView) findViewById(R.id.iv_comment_icon);
    }

    public CommentNumView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public CommentNumView(Context context) {
        super(context);
        initView(context);
    }

    public void setMesCount(int i) {
        if (i > 0) {
            this.tvCount.setVisibility(0);
            this.tvCount.setText(i > 99 ? "99+" : String.valueOf(i));
            this.ivMessage.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_comment_with_count));
        } else {
            this.tvCount.setVisibility(8);
            this.ivMessage.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_comment));
        }
        invalidate();
    }

    public int getMesCount() {
        try {
            return Integer.parseInt(this.tvCount.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setTextViewBackgroundNull() {
        this.tvCount.setBackground((Drawable) null);
    }
}
