package com.xiaomi.shopviews.widget.hometimecountdown;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.ArrayUtils;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.base.utils.ImageAdapUtil;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;
import java.util.ArrayList;
import java.util.Formatter;

public class HomeTimeCountDownBigView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private long f13335a = 0;
    /* access modifiers changed from: private */
    public boolean b = false;
    private ImageView c;
    private View d;
    /* access modifiers changed from: private */
    public HomeSectionItem e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    CountDownTimer mCountDownTimer;
    private TextView n;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public void draw(HomeSection homeSection) {
    }

    public HomeTimeCountDownBigView(Context context, long j2) {
        super(context);
        this.f13335a = j2;
        a(context);
    }

    private String a(int i2) {
        String formatter = new Formatter().format("%02d", new Object[]{Integer.valueOf(i2)}).toString();
        return formatter.substring(0, 1) + " " + formatter.substring(1);
    }

    private long getCountDownTime() {
        long j2 = (this.e.mEndTime - this.e.mServerTime) * 1000;
        long currentTimeMillis = System.currentTimeMillis() - this.f13335a;
        if (j2 <= 0 || currentTimeMillis >= j2) {
            return 0;
        }
        return j2 - currentTimeMillis;
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.listitem_home_countdown_big_view, this);
        this.c = (ImageView) findViewById(R.id.iv_countdown_bg_big);
        this.d = findViewById(R.id.layout_countdown);
        this.f = (TextView) findViewById(R.id.tv_countdown_day);
        this.g = (TextView) findViewById(R.id.tv_countdown_day_unit);
        this.h = (TextView) findViewById(R.id.tv_countdown_hour);
        this.i = (TextView) findViewById(R.id.tv_countdown_hour_unit);
        this.k = (TextView) findViewById(R.id.tv_countdown_minute);
        this.l = (TextView) findViewById(R.id.tv_countdown_minute_unit);
        this.m = (TextView) findViewById(R.id.tv_countdown_second);
        this.n = (TextView) findViewById(R.id.tv_countdown_second_unit);
        this.j = (TextView) findViewById(R.id.tv_countdown_mill_second);
        this.c.getLayoutParams().height = ImageAdapUtil.a(ScreenInfo.a().e(), 1080, 200, 1080).f10029a;
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.e != null && !TextUtils.isEmpty(this.e.mCountDownEndUrl)) {
            ImageLoader.a().a(this.e.mCountDownEndUrl, this.c);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.b = true;
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setText("0 0");
        this.k.setText("0 0");
        this.m.setText("0 0");
        this.j.setText("0");
        this.d.setVisibility(8);
    }

    private void c() {
        if (!TextUtils.isEmpty(this.e.mCountDownEndUrl)) {
            ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
            ImageLoader.a().a(this.e.mCountDownEndUrl, this.c, new Option().a((Drawable) colorDrawable).b((Drawable) colorDrawable));
        }
    }

    private void d() {
        if (this.mCountDownTimer != null) {
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        int i2 = (int) ((j2 % 1000) / 100);
        int i3 = (int) ((j2 / 1000) % 60);
        int i4 = (int) ((j2 / 60000) % 60);
        int i5 = (int) ((j2 / 3600000) % 24);
        int i6 = (int) (j2 / 86400000);
        if (i6 > 0) {
            this.f.setVisibility(0);
            this.g.setVisibility(0);
            this.f.setText(a(i6));
        } else {
            this.f.setVisibility(8);
            this.g.setVisibility(8);
        }
        this.h.setText(a(i5));
        this.k.setText(a(i4));
        this.m.setText(a(i3));
        this.j.setText(Integer.toString(i2));
    }

    public void bind(HomeSection homeSection, int i2, int i3) {
        d();
        if (homeSection != null && homeSection.mBody != null && !ArrayUtils.a((ArrayList<?>) homeSection.mBody.mItems)) {
            this.e = homeSection.mBody.mItems.get(0);
            if (this.e != null) {
                if (!TextUtils.isEmpty(this.e.mImageUrl)) {
                    ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
                    ImageLoader.a().a(this.e.mImageUrl, this.c, new Option().a((Drawable) colorDrawable).b((Drawable) colorDrawable));
                }
                long countDownTime = getCountDownTime();
                if (countDownTime > 0) {
                    this.b = false;
                    startCounterDown(countDownTime);
                    while (true) {
                        setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (HomeTimeCountDownBigView.this.b) {
                                    HomeTimeCountDownBigView.this.a(HomeTimeCountDownBigView.this.e);
                                }
                            }
                        });
                        try {
                            break;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (!TextUtils.isEmpty(this.e.mTextColorTime)) {
                        int parseColor = Color.parseColor(this.e.mTextColorTime);
                        this.f.setTextColor(parseColor);
                        this.h.setTextColor(parseColor);
                        this.k.setTextColor(parseColor);
                        this.m.setTextColor(parseColor);
                    }
                    if (!TextUtils.isEmpty(this.e.mTextColorUnit)) {
                        int parseColor2 = Color.parseColor(this.e.mTextColorUnit);
                        this.g.setTextColor(parseColor2);
                        this.i.setTextColor(parseColor2);
                        this.l.setTextColor(parseColor2);
                        this.n.setTextColor(parseColor2);
                    }
                    if (!TextUtils.isEmpty(this.e.mTextColorMill)) {
                        this.j.setTextColor(Color.parseColor(this.e.mTextColorMill));
                    }
                    if (!TextUtils.isEmpty(this.e.mBgColorSquare)) {
                        int parseColor3 = Color.parseColor(this.e.mBgColorSquare);
                        GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setCornerRadius((float) Coder.a(getContext(), 2.0f));
                        gradientDrawable.setColor(parseColor3);
                        this.f.setBackgroundDrawable(gradientDrawable);
                        this.h.setBackgroundDrawable(gradientDrawable);
                        this.k.setBackgroundDrawable(gradientDrawable);
                        this.m.setBackgroundDrawable(gradientDrawable);
                    }
                    if (!TextUtils.isEmpty(this.e.mBgColorRect)) {
                        int parseColor4 = Color.parseColor(this.e.mBgColorRect);
                        GradientDrawable gradientDrawable2 = new GradientDrawable();
                        gradientDrawable2.setCornerRadius((float) Coder.a(getContext(), 2.0f));
                        gradientDrawable2.setColor(parseColor4);
                        this.j.setBackgroundDrawable(gradientDrawable2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        d();
    }

    public void startCounterDown(long j2) {
        if (this.mCountDownTimer != null) {
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
        AnonymousClass2 r1 = new CountDownTimer(j2, 100) {
            public void onFinish() {
                HomeTimeCountDownBigView.this.b();
                HomeTimeCountDownBigView.this.a();
            }

            public void onTick(long j) {
                HomeTimeCountDownBigView.this.a(j);
            }
        };
        this.mCountDownTimer = r1;
        r1.start();
    }
}
