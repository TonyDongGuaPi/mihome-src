package com.xiaomi.youpin.share.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.xiaomi.youpin.common.util.ConvertUtils;
import com.xiaomi.youpin.common.util.SizeUtils;
import com.xiaomi.youpin.share.R;

public class PosterShareView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private View f23724a;
    private View b;
    private View c;
    private View d;
    private View e;
    private boolean f;

    public PosterShareView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PosterShareView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PosterShareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.yp_view_poster_share, this, true);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
        setLayoutParams(new RelativeLayout.LayoutParams(-1, ConvertUtils.a(150.0f)));
        this.f23724a = findViewById(R.id.yp_new_share_wx_share);
        this.b = findViewById(R.id.yp_new_share_friends_share);
        this.c = findViewById(R.id.yp_new_share_weibo_share);
        this.d = findViewById(R.id.yp_new_share_save);
        this.e = findViewById(R.id.yp_new_share_close);
    }

    public void setWxShareAvailable(boolean z) {
        if (z) {
            this.f23724a.setVisibility(0);
            this.b.setVisibility(0);
            return;
        }
        this.f23724a.setVisibility(8);
        this.b.setVisibility(8);
    }

    public void setWeiboShareAvailable(boolean z) {
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }

    public void setOnShareItemClickListener(final OnShareItemClickListener onShareItemClickListener) {
        this.f23724a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onShareItemClickListener != null) {
                    onShareItemClickListener.a();
                }
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onShareItemClickListener != null) {
                    onShareItemClickListener.b();
                }
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onShareItemClickListener != null) {
                    onShareItemClickListener.c();
                }
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onShareItemClickListener != null) {
                    onShareItemClickListener.f();
                }
            }
        });
    }

    public void setOnDismissListener(final View.OnClickListener onClickListener) {
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
    }

    public void show() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "translationY", new float[]{(float) SizeUtils.a().y, 0.0f});
        ofFloat.setDuration(300);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        setVisibility(0);
        bringToFront();
        ofFloat.start();
        this.f = true;
    }

    public void dismiss() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "translationY", new float[]{0.0f, (float) SizeUtils.a().y});
        ofFloat.setDuration(300);
        ofFloat.setInterpolator(new AccelerateInterpolator());
        ofFloat.start();
        this.f = false;
    }

    public boolean isShow() {
        return this.f;
    }
}
