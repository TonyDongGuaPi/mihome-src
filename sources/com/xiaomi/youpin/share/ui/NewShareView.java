package com.xiaomi.youpin.share.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import com.xiaomi.youpin.share.R;

public class NewShareView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private View f23702a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;

    public NewShareView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NewShareView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NewShareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.yp_view_new_share, this, true);
        this.f23702a = findViewById(R.id.yp_new_share_wx_share);
        this.b = findViewById(R.id.yp_new_share_friends_share);
        this.c = findViewById(R.id.yp_new_share_weibo_share);
        this.d = findViewById(R.id.yp_new_share_poster);
        this.e = findViewById(R.id.yp_new_share_copyurl);
        this.f = findViewById(R.id.yp_new_share_placeholder);
        this.g = findViewById(R.id.yp_new_share_close);
    }

    public void setWxShareAvailable(boolean z) {
        if (z) {
            this.f23702a.setVisibility(0);
            this.b.setVisibility(0);
            return;
        }
        this.f23702a.setVisibility(8);
        this.b.setVisibility(8);
    }

    public void setWeiboShareAvailable(boolean z) {
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }

    public void show(final Animation.AnimationListener animationListener) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                NewShareView.this.setVisibility(0);
                if (animationListener != null) {
                    animationListener.onAnimationStart(animation);
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationEnd(animation);
                }
            }

            public void onAnimationRepeat(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationRepeat(animation);
                }
            }
        });
        startAnimation(loadAnimation);
    }

    public void dismiss(final Animation.AnimationListener animationListener) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_bottom);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationStart(animation);
                }
            }

            public void onAnimationEnd(Animation animation) {
                NewShareView.this.setVisibility(8);
                if (animationListener != null) {
                    animationListener.onAnimationEnd(animation);
                }
            }

            public void onAnimationRepeat(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationRepeat(animation);
                }
            }
        });
        startAnimation(loadAnimation);
    }

    public void setIsShowPoster(boolean z) {
        this.d.setVisibility(z ? 0 : 8);
        if (z) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(4);
        }
    }

    public void setIsShowCopyUrl(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public void setOnShareItemClickListener(final OnShareItemClickListener onShareItemClickListener) {
        this.f23702a.setOnClickListener(new View.OnClickListener() {
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
                    onShareItemClickListener.d();
                }
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onShareItemClickListener != null) {
                    onShareItemClickListener.e();
                }
            }
        });
    }

    public void setOnDismissListener(final View.OnClickListener onClickListener) {
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
    }
}
