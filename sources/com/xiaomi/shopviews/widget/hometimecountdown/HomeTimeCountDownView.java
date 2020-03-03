package com.xiaomi.shopviews.widget.hometimecountdown;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;
import java.util.ArrayList;
import java.util.Formatter;

public class HomeTimeCountDownView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private long f13338a = 0;
    private ImageView b;
    /* access modifiers changed from: private */
    public HomeSectionItem c;
    private ImageView d;
    /* access modifiers changed from: private */
    public TextView e;
    /* access modifiers changed from: private */
    public TextView f;
    /* access modifiers changed from: private */
    public TextView g;
    CountDownTimer mCountDownTimer;

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public void draw(HomeSection homeSection) {
    }

    public HomeTimeCountDownView(Context context, long j) {
        super(context);
        this.f13338a = j;
        a(context);
    }

    private String a(int i) {
        return new Formatter().format("%02d", new Object[]{Integer.valueOf(i)}).toString();
    }

    private long getCountDownTime() {
        long j = (this.c.mEndTime - this.c.mServerTime) * 1000;
        long currentTimeMillis = System.currentTimeMillis() - this.f13338a;
        if (j <= 0 || currentTimeMillis >= j) {
            return 0;
        }
        return j - currentTimeMillis;
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.listitem_home_countdown_view, this);
        this.b = (ImageView) findViewById(R.id.iv_countdown_bg);
        this.d = (ImageView) findViewById(R.id.iv_countdown_title);
        this.e = (TextView) findViewById(R.id.tv_countdown_hour);
        this.f = (TextView) findViewById(R.id.tv_countdown_minute);
        this.g = (TextView) findViewById(R.id.tv_countdown_second);
    }

    private void a() {
        if (this.mCountDownTimer != null) {
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public void a(long j) {
        this.e.setText(a((int) ((j / 3600000) % 24)));
        this.f.setText(a((int) ((j / 60000) % 60)));
        this.g.setText(a((int) ((j / 1000) % 60)));
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        a();
        if (homeSection != null && homeSection.mBody != null && !ArrayUtils.a((ArrayList<?>) homeSection.mBody.mItems)) {
            this.c = homeSection.mBody.mItems.get(0);
            if (this.c != null) {
                if (!TextUtils.isEmpty(this.c.mImageUrl)) {
                    ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
                    ImageLoader.a().a(this.c.mImageUrl, this.b, new Option().a((Drawable) colorDrawable).b((Drawable) colorDrawable));
                }
                if (!TextUtils.isEmpty(this.c.mCountDownTitleUrl)) {
                    ImageLoader.a().a(this.c.mCountDownTitleUrl, this.d);
                }
                long countDownTime = getCountDownTime();
                if (countDownTime > 0) {
                    startCounterDown(countDownTime);
                } else {
                    this.e.setText("00");
                    this.f.setText("00");
                    this.g.setText("00");
                }
                setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeTimeCountDownView.this.a(HomeTimeCountDownView.this.c);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        a();
    }

    public void startCounterDown(long j) {
        if (this.mCountDownTimer != null) {
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
        AnonymousClass2 r1 = new CountDownTimer(j, 1000) {
            public void onFinish() {
                HomeTimeCountDownView.this.e.setText("00");
                HomeTimeCountDownView.this.f.setText("00");
                HomeTimeCountDownView.this.g.setText("00");
            }

            public void onTick(long j) {
                HomeTimeCountDownView.this.a(j);
            }
        };
        this.mCountDownTimer = r1;
        r1.start();
    }
}
