package com.mi.global.bbs.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.andview.refreshview.callback.IHeaderCallBack;
import com.mi.global.bbs.R;

public class MiHeaderView extends FrameLayout implements IHeaderCallBack {
    private ImageView imageView = ((ImageView) findViewById(R.id.image_view));
    private AnimationDrawable loading1 = ((AnimationDrawable) getResources().getDrawable(R.drawable.bbs_loading_head1));
    private AnimationDrawable loading2 = ((AnimationDrawable) getResources().getDrawable(R.drawable.bbs_loading_head2));

    public void onHeaderMove(double d, int i, int i2) {
    }

    public void onStateFinish(boolean z) {
    }

    public void onStateReady() {
    }

    public void setRefreshTime(long j) {
    }

    public void show() {
    }

    public MiHeaderView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.bbs_layout_head1, this, true);
    }

    public void onStateNormal() {
        this.imageView.setImageDrawable(this.loading1);
        this.loading1.start();
    }

    public void onStateRefreshing() {
        this.imageView.setImageDrawable(this.loading2);
        this.loading2.start();
    }

    public void hide() {
        this.imageView.setImageResource(R.drawable.bbs_pull_1);
    }

    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
}
