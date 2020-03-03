package com.xiaomi.smarthome.newui.widget.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.homeroom.HomeRoomCreatHomeActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.widget.guide.drawable.BubbleDrawable;

public class GuideBubbleRightCloseImpl extends GuideBubble implements Animation.AnimationListener {
    /* access modifiers changed from: private */
    public Activity c;
    private ViewGroup d;
    private View e;
    private BubbleDrawable f;
    private TextView g;
    private ImageView h;
    private PointF i;
    private PointF j;
    private Animation k;
    private Animation l;
    private boolean m = true;
    private boolean n = true;
    private int o;

    /* access modifiers changed from: protected */
    public long e() {
        return 200;
    }

    public GuideBubbleRightCloseImpl(Activity activity) {
        this.c = activity;
        this.k = new AlphaAnimation(0.0f, 1.0f);
        this.k.setAnimationListener(this);
        this.k.setDuration(400);
        this.l = new AlphaAnimation(1.0f, 0.0f);
        this.l.setAnimationListener(this);
        this.l.setDuration(200);
    }

    public void a(ViewGroup viewGroup, PointF pointF, int i2) {
        this.j = pointF;
        this.d = viewGroup;
        this.e = LayoutInflater.from(this.c).inflate(R.layout.guide_bubble_right_close, (ViewGroup) null);
        this.d.addView(this.e, new ViewGroup.LayoutParams(-2, -2));
        this.o = a(pointF, i2);
        a(this.e);
        a(i2);
        this.e.setVisibility(4);
    }

    private int a(PointF pointF, int i2) {
        Point a2 = DisplayUtils.a(this.c);
        float f2 = pointF.x > ((float) (a2.x / 2)) ? ((float) a2.x) - pointF.x : pointF.x;
        float a3 = (float) DisplayUtils.a((Context) this.c, 10.0f);
        if (i2 == 80 || i2 == 48) {
            return (int) ((f2 * 2.0f) - (a3 * 2.0f));
        }
        if (i2 == 85) {
            return (int) Math.min(((float) a2.x) - (2.0f * a3), (f2 - a3) * 4.0f);
        }
        if (i2 == 51) {
            return (int) (((float) DisplayUtils.a(this.c).x) - (pointF.x * 2.0f));
        }
        return (int) ((((float) a2.x) - pointF.x) - a3);
    }

    private void a(View view) {
        this.g = (TextView) view.findViewById(R.id.tv_guide);
        this.h = (ImageView) view.findViewById(R.id.close);
        String string = this.c.getResources().getString(R.string.multi_home_first_guide);
        String string2 = this.c.getResources().getString(R.string.creat_now);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string).append(string2);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.c.getResources().getColor(R.color.choose_connect_device_error_link));
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                GuideBubbleRightCloseImpl.this.c.startActivity(new Intent(GuideBubbleRightCloseImpl.this.c, HomeRoomCreatHomeActivity.class));
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }
        }, string.length(), spannableStringBuilder.length(), 17);
        spannableStringBuilder.setSpan(foregroundColorSpan, string.length(), spannableStringBuilder.length(), 17);
        this.g.setMovementMethod(LinkMovementMethod.getInstance());
        this.g.setText(spannableStringBuilder);
        this.g.setMaxWidth(this.o);
        this.g.setHighlightColor(16777215);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GuideBubbleRightCloseImpl.this.b();
            }
        });
    }

    private void a(int i2) {
        this.f = new BubbleDrawable(this.c);
        this.f.a(i2);
        this.f.c(DisplayUtils.a(3.0f));
        a(this.e, i2, this.f.c());
        this.e.setBackgroundDrawable(this.f);
    }

    private void a(View view, int i2, int i3) {
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        if (i2 == 3) {
            view.setPadding(paddingLeft + i3, paddingTop, paddingRight, paddingBottom);
        } else if (i2 == 5) {
            view.setPadding(paddingLeft, paddingTop, paddingRight + i3, paddingBottom);
        } else if (i2 == 48 || i2 == 51) {
            view.setPadding(paddingLeft, paddingTop + i3, paddingRight, paddingBottom);
        } else if (i2 == 80 || i2 == 85) {
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom + i3);
        }
    }

    public void a() {
        g();
        this.e.setVisibility(0);
        this.d.setVisibility(0);
        this.d.startAnimation(this.k);
        this.f.setAlpha(153);
    }

    public void b() {
        this.e.setVisibility(4);
        this.d.setVisibility(4);
        this.d.startAnimation(this.l);
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public PointF f() {
        if (this.i == null) {
            this.i = new PointF();
        }
        this.i.set(this.j.x, this.j.y);
        return this.i;
    }

    public void g() {
        PointF f2 = f();
        this.e.setTranslationX(f2.x);
        this.e.setTranslationY(f2.y);
    }

    public BubbleDrawable h() {
        this.f.setBounds(0, 0, i(), j());
        return this.f;
    }

    /* access modifiers changed from: protected */
    public int i() {
        return this.e.getWidth();
    }

    /* access modifiers changed from: protected */
    public int j() {
        return this.e.getHeight();
    }

    public void onAnimationStart(Animation animation) {
        if (animation == this.l) {
            this.n = false;
            if (o() != null) {
                o().onAnimationStart(animation);
            }
        } else if (animation == this.k) {
            this.m = false;
            if (p() != null) {
                p().onAnimationStart(animation);
            }
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.l) {
            this.n = true;
            if (o() != null) {
                o().onAnimationEnd(animation);
            }
            a((Animation.AnimationListener) null);
        } else if (animation == this.k) {
            this.m = true;
            if (p() != null) {
                p().onAnimationEnd(animation);
            }
            b((Animation.AnimationListener) null);
        }
    }

    public void onAnimationRepeat(Animation animation) {
        if (o() != null) {
            o().onAnimationRepeat(animation);
        }
        if (p() != null) {
            p().onAnimationRepeat(animation);
        }
    }
}
