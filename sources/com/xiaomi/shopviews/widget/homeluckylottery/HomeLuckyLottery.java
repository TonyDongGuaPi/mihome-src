package com.xiaomi.shopviews.widget.homeluckylottery;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.utils.ImageAdapUtil;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeAction;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionBody;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.model.LuckyDrawModel;
import com.xiaomi.shopviews.widget.R;

public class HomeLuckyLottery extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private static final int f13280a = 300;
    private static final int b = 1080;
    private static final int c = 2;
    private static final int d = 1;
    private static final int e = 0;
    private long f;
    /* access modifiers changed from: private */
    public int g = 0;
    private boolean h;
    /* access modifiers changed from: private */
    public HomeSectionBody i;
    private long j = 0;
    /* access modifiers changed from: private */
    public ImageView k;
    private CountDownTimer l;

    /* access modifiers changed from: private */
    public boolean a() {
        return true;
    }

    public void draw(HomeSection homeSection) {
    }

    public HomeLuckyLottery(Context context, long j2) {
        super(context);
        this.f = j2;
        b();
    }

    private String getImgUrlByStatus() {
        String str = this.i.img_url_start;
        switch (this.g) {
            case 0:
                if (!TextUtils.isEmpty(this.i.img_url_unstart)) {
                    return this.i.img_url_unstart;
                }
                return this.i.img_url_start;
            case 1:
                return this.i.img_url_end;
            case 2:
                if (this.i.mRemainNum > 0 || TextUtils.isEmpty(this.i.img_url_end)) {
                    return this.i.img_url_start;
                }
                return this.i.img_url_end;
            default:
                return str;
        }
    }

    private void b() {
        LayoutInflater.from(getContext()).inflate(R.layout.listitem_lucky_lottery, this);
        this.k = (ImageView) findViewById(R.id.iv_lucky_lottery);
    }

    private void c() {
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
    }

    private void d() {
        long j2 = this.i.mStartTime;
        long j3 = this.i.mEndTime;
        long j4 = this.i.mServerTime;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (j4 > 0) {
            currentTimeMillis = ((System.currentTimeMillis() - this.f) / 1000) + j4;
        }
        if (currentTimeMillis < j2) {
            this.g = 0;
            this.j = j2 - currentTimeMillis;
        } else if (currentTimeMillis >= j2 && currentTimeMillis < j3) {
            this.g = 1;
        } else if (currentTimeMillis >= j3 || j4 > j3) {
            this.g = 2;
        }
        startCounterDown(this.j);
    }

    private void a(LuckyDrawModel luckyDrawModel, HomeSectionBody homeSectionBody) {
        if (this.h) {
            if (luckyDrawModel.i <= 0) {
                if (homeSectionBody == null || TextUtils.isEmpty(homeSectionBody.img_url_end)) {
                    setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                } else {
                    ImageLoader.a().a(this.i.img_url_end, this.k);
                    setOnClickListener((View.OnClickListener) null);
                }
            }
            LuckyResultDialog luckyResultDialog = new LuckyResultDialog(getContext());
            luckyResultDialog.show();
            luckyResultDialog.a(luckyDrawModel);
        }
    }

    public void bind(HomeSection homeSection, int i2, int i3) {
        this.i = homeSection.mBody;
        if (this.i != null) {
            int i4 = 300;
            if (!TextUtils.isEmpty(this.i.mViewHeight)) {
                i4 = Integer.parseInt(this.i.mViewHeight);
            }
            ImageAdapUtil.ImageInfo a2 = ImageAdapUtil.a(ScreenInfo.a().e(), !TextUtils.isEmpty(this.i.mViewWidth) ? Integer.parseInt(this.i.mViewWidth) : 1080, i4, 1080);
            if (i4 > 0) {
                ((LinearLayout.LayoutParams) this.k.getLayoutParams()).height = a2.f10029a;
            }
            d();
            if (this.g == 0) {
                startCounterDown(this.j);
            }
            String imgUrlByStatus = getImgUrlByStatus();
            if (!TextUtils.isEmpty(imgUrlByStatus)) {
                ImageLoader.a().a(imgUrlByStatus, this.k);
            }
            setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    switch (HomeLuckyLottery.this.g) {
                        case 1:
                            if (HomeLuckyLottery.this.a()) {
                                if (HomeLuckyLottery.this.i.mRemainNum > 0) {
                                    HomeLuckyLottery.this.requestLuckLottery(HomeLuckyLottery.this.i);
                                    return;
                                } else if (!TextUtils.isEmpty(HomeLuckyLottery.this.i.img_url_end)) {
                                    ImageLoader.a().a(HomeLuckyLottery.this.i.img_url_end, HomeLuckyLottery.this.k);
                                    return;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        default:
                            return;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
        c();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        this.h = true;
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        this.h = false;
    }

    public void requestLuckLottery(HomeSectionBody homeSectionBody) {
        if (!homeSectionBody.isRequesting) {
            homeSectionBody.isRequesting = true;
            HomeAction homeAction = homeSectionBody.mAction;
        }
    }

    public void startCounterDown(long j2) {
        if (j2 > 0) {
            c();
            AnonymousClass3 r3 = new CountDownTimer(j2, 1000) {
                public void onTick(long j) {
                }

                public void onFinish() {
                    int unused = HomeLuckyLottery.this.g = 1;
                    String str = HomeLuckyLottery.this.i.img_url_start;
                    if (HomeLuckyLottery.this.i.mRemainNum <= 0 && !TextUtils.isEmpty(HomeLuckyLottery.this.i.img_url_end)) {
                        str = HomeLuckyLottery.this.i.img_url_end;
                    }
                    ImageLoader.a().a(str, HomeLuckyLottery.this.k);
                }
            };
            this.l = r3;
            r3.start();
        }
    }
}
