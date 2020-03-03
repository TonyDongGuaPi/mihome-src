package com.xiaomi.shopviews.widget.homeheadercoupon;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.AndroidUtil;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.base.utils.ImageAdapUtil;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.adapter.adapter.BubbleAdapter;
import com.xiaomi.shopviews.model.HomeAction;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionBody;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;
import java.util.ArrayList;
import java.util.Formatter;

public class HomeHeaderCoupon extends RelativeLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private static final long f13252a = 4000;
    private static final long b = 86400000;
    private long c = 0;
    /* access modifiers changed from: private */
    public ImageView d;
    /* access modifiers changed from: private */
    public HomeSectionBody e;
    /* access modifiers changed from: private */
    public BubbleAdapter f;
    /* access modifiers changed from: private */
    public ListView g;
    private TextView h;
    private CountDownTimer i;
    private TextView j;
    private RelativeLayout k;
    private TextView l;
    private CountDownTimer m;

    public void draw(HomeSection homeSection) {
    }

    public HomeHeaderCoupon(Context context, long j2) {
        super(context);
        a(context, (AttributeSet) null, 0);
        this.c = j2;
    }

    public HomeHeaderCoupon(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    private void a(ArrayList<HomeSectionItem> arrayList, int i2, int i3) {
        BubbleAdapter bubbleAdapter = new BubbleAdapter(getContext(), i2, i3);
        this.f = bubbleAdapter;
        bubbleAdapter.a(arrayList);
        this.g.setAdapter(this.f);
        this.g.setDividerHeight(0);
        if (arrayList.size() > 3) {
            AndroidUtil.a(new Runnable() {
                public void run() {
                    HomeHeaderCoupon.this.a(86400000);
                }
            }, f13252a);
        } else {
            stopBubbleAnimTimer();
        }
        this.g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                HomeSectionItem homeSectionItem = (HomeSectionItem) HomeHeaderCoupon.this.f.getItem(i);
                if (homeSectionItem != null) {
                    HomeAction homeAction = homeSectionItem.mAction;
                }
            }
        });
    }

    private String a(int i2) {
        return new Formatter().format("%02d", new Object[]{Integer.valueOf(i2)}).toString();
    }

    private long a(HomeSectionBody homeSectionBody) {
        long j2 = (homeSectionBody.mEndTime - homeSectionBody.mServerTime) * 1000;
        long currentTimeMillis = System.currentTimeMillis() - this.c;
        if (j2 <= 0 || currentTimeMillis >= j2) {
            return 0;
        }
        return j2 - currentTimeMillis;
    }

    /* access modifiers changed from: private */
    public void a() {
        this.h.setVisibility(8);
        this.l.setVisibility(8);
        this.j.setVisibility(8);
        this.g.setVisibility(8);
        stopBubbleAnimTimer();
        setOnClickListener((View.OnClickListener) null);
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        inflate(context, R.layout.listitem_header_coupon, this);
        this.k = (RelativeLayout) findViewById(R.id.rootview_header_coupon);
        this.d = (ImageView) findViewById(R.id.iv_bg);
        this.j = (TextView) findViewById(R.id.tv_couponInfo);
        this.l = (TextView) findViewById(R.id.tv_timeInfo);
        this.h = (TextView) findViewById(R.id.tv_timeCountDown);
        this.g = (ListView) findViewById(R.id.lv_bubble);
    }

    private void setBgColor(HomeSectionBody homeSectionBody) {
        if (!TextUtils.isEmpty(homeSectionBody.mBgColor)) {
            int parseColor = Color.parseColor(homeSectionBody.mBgColor);
            if (getBackground() == null || !(getBackground() instanceof ColorDrawable)) {
                setBackgroundColor(parseColor);
            } else if (((ColorDrawable) getBackground()).getColor() != parseColor) {
                setBackgroundColor(parseColor);
            }
        }
    }

    private void a(HomeSectionBody homeSectionBody, ColorDrawable colorDrawable, long j2) {
        String str;
        if (j2 > 0) {
            str = homeSectionBody.mImageUrl;
        } else {
            str = homeSectionBody.img_url_end;
        }
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.a().a(str, this.d, new Option().a((Drawable) colorDrawable).b((Drawable) colorDrawable));
        } else {
            setBgColor(homeSectionBody);
        }
    }

    private void setBubbleInfo(HomeSectionBody homeSectionBody) {
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }

    private void setCouponInfo(HomeSectionBody homeSectionBody) {
        if (!TextUtils.isEmpty(homeSectionBody.mActionText)) {
            this.j.setText(homeSectionBody.mActionText);
            if (homeSectionBody.padding > 0) {
                ((RelativeLayout.LayoutParams) this.j.getLayoutParams()).bottomMargin = ImageAdapUtil.a(ScreenInfo.a().e(), 1080, homeSectionBody.padding, 1080).f10029a;
            }
            if (!TextUtils.isEmpty(homeSectionBody.coupon_color)) {
                getResources().getColor(R.color.white);
                while (true) {
                    try {
                        this.j.setTextColor(Color.parseColor(homeSectionBody.coupon_color));
                        break;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private void a(HomeSectionBody homeSectionBody, long j2, boolean z) {
        String str = homeSectionBody.mNoticeText;
        if (z) {
            str = homeSectionBody.notice_text_24h;
        }
        if (!TextUtils.isEmpty(str)) {
            this.l.setText(Html.fromHtml(str));
            TextView textView = this.h;
            if (z) {
                textView.setVisibility(0);
                if (j2 > 0 && z) {
                    b(j2);
                    startCounterDown(j2);
                }
                if (!TextUtils.isEmpty(homeSectionBody.time_color)) {
                    getResources().getColor(R.color.white);
                    while (true) {
                        try {
                            int parseColor = Color.parseColor(homeSectionBody.time_color);
                            this.l.setTextColor(parseColor);
                            this.h.setTextColor(parseColor);
                            break;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void setViewHeightInfo(HomeSectionBody homeSectionBody) {
        int i2 = homeSectionBody.mHeight;
        if (i2 > 0) {
            ViewGroup.LayoutParams layoutParams = this.k.getLayoutParams();
            ImageAdapUtil.ImageInfo a2 = ImageAdapUtil.a(ScreenInfo.a().e(), 1080, i2, 1080);
            layoutParams.height = a2.f10029a;
            ((RelativeLayout.LayoutParams) this.d.getLayoutParams()).height = a2.f10029a;
        }
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        AnonymousClass3 r0 = new CountDownTimer(j2, f13252a) {
            public void onFinish() {
            }

            public void onTick(long j) {
                if (HomeHeaderCoupon.this.g != null && HomeHeaderCoupon.this.g.getVisibility() == 0 && HomeHeaderCoupon.this.isShown()) {
                    HomeHeaderCoupon.this.g.smoothScrollBy(Coder.a(HomeHeaderCoupon.this.getContext(), 36.0f), 500);
                }
            }
        };
        this.m = r0;
        r0.start();
    }

    private void b() {
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
    }

    /* access modifiers changed from: private */
    public void b(long j2) {
        TextView textView = this.h;
        textView.setText(a((int) ((j2 / 3600000) % 24)) + ":" + a((int) ((j2 / 60000) % 60)) + ":" + a((int) ((j2 / 1000) % 60)) + "." + ((int) ((j2 % 1000) / 100)));
    }

    public void bind(HomeSection homeSection, int i2, int i3) {
        HomeSectionBody homeSectionBody = homeSection.mBody;
        if (homeSectionBody != null) {
            this.e = homeSectionBody;
            ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
            long a2 = a(homeSectionBody);
            boolean z = a2 <= 86400000;
            setViewHeightInfo(homeSectionBody);
            a(homeSectionBody, colorDrawable, a2);
            if (a2 > 0) {
                setCouponInfo(homeSectionBody);
                a(homeSectionBody, a2, z);
                setBubbleInfo(homeSectionBody);
                setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                });
                return;
            }
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
        stopBubbleAnimTimer();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        b();
        stopBubbleAnimTimer();
    }

    public void startCounterDown(long j2) {
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
        AnonymousClass5 r1 = new CountDownTimer(j2, 100) {
            public void onFinish() {
                HomeHeaderCoupon.this.a();
                ImageLoader.a().a(HomeHeaderCoupon.this.e.img_url_end, HomeHeaderCoupon.this.d);
            }

            public void onTick(long j) {
                HomeHeaderCoupon.this.b(j);
            }
        };
        this.i = r1;
        r1.start();
    }

    public void stopBubbleAnimTimer() {
        if (this.m != null) {
            this.m.cancel();
        }
    }
}
