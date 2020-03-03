package com.xiaomi.shopviews.widget.homeseckill;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.ArrayUtils;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionBody;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.utils.HomeProductTagImageHelper;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.hdhorizontalrecyclergoods.CustRecylerView;
import java.util.ArrayList;
import java.util.Formatter;

public class HomeSecKillView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private long f13319a;
    private DividerItemDecoration b;
    private HorizontalAdapter c;
    /* access modifiers changed from: private */
    public HomeSectionBody d;
    private CustRecylerView e;
    private View f;
    private ImageView g;
    private TextView h;
    /* access modifiers changed from: private */
    public TextView i;
    /* access modifiers changed from: private */
    public TextView j;
    /* access modifiers changed from: private */
    public TextView k;
    CountDownTimer mCountDownTimer;

    public void draw(HomeSection homeSection) {
    }

    public HomeSecKillView(Context context, long j2) {
        super(context);
        this.f13319a = j2;
        b();
    }

    public HomeSecKillView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public HomeSecKillView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b();
    }

    @RequiresApi(api = 21)
    public HomeSecKillView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        b();
    }

    private void a() {
        if (this.e != null) {
            this.e.removeAllViews();
        }
    }

    private String a(int i2) {
        return new Formatter().format("%02d", new Object[]{Integer.valueOf(i2)}).toString();
    }

    private long getCountDownTime() {
        if (this.d == null) {
            return 0;
        }
        long j2 = (this.d.mEndTime - this.d.mServerTime) * 1000;
        long currentTimeMillis = System.currentTimeMillis() - this.f13319a;
        if (j2 <= 0 || currentTimeMillis >= j2) {
            return 0;
        }
        return j2 - currentTimeMillis;
    }

    /* access modifiers changed from: private */
    public void a(HomeSectionBody homeSectionBody) {
        if (homeSectionBody != null && homeSectionBody.mAction != null) {
            homeSectionBody.mAction.isEmpty();
        }
    }

    private void b() {
        double e2 = (double) ((ScreenInfo.a().e() - Coder.a(getContext(), 8.0f)) - (Coder.a(getContext(), 6.0f) * 3));
        Double.isNaN(e2);
        int i2 = (int) (e2 / 3.3d);
        inflate(getContext(), R.layout.listitem_seckill_view, this);
        this.f = findViewById(R.id.layout_title);
        this.g = (ImageView) findViewById(R.id.iv_countdown_title);
        this.h = (TextView) findViewById(R.id.tv_countdownDesc);
        this.i = (TextView) findViewById(R.id.tv_countdown_hour);
        this.j = (TextView) findViewById(R.id.tv_countdown_minute);
        this.k = (TextView) findViewById(R.id.tv_countdown_second);
        this.e = (CustRecylerView) findViewById(R.id.recycler_view);
        ((RelativeLayout.LayoutParams) this.g.getLayoutParams()).height = (ScreenInfo.a().e() * 120) / 1080;
        this.e.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.b = new DividerItemDecoration(getContext());
        this.e.addItemDecoration(this.b);
        setItemSize(i2, i2);
    }

    private void c() {
        if (this.mCountDownTimer != null) {
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        this.i.setText(a((int) ((j2 / 3600000) % 24)));
        this.j.setText(a((int) ((j2 / 60000) % 60)));
        this.k.setText(a((int) ((j2 / 1000) % 60)));
    }

    public void bind(HomeSection homeSection, int i2, int i3) {
        if (homeSection != null && homeSection.mBody != null) {
            c();
            this.d = homeSection.mBody;
            if (this.d != null) {
                if (!TextUtils.isEmpty(this.d.mBgColor)) {
                    int parseColor = Color.parseColor(homeSection.mBody.mBgColor);
                    if (getBackground() == null || !(getBackground() instanceof ColorDrawable)) {
                        setBackgroundColor(parseColor);
                    } else if (((ColorDrawable) getBackground()).getColor() != parseColor) {
                        setBackgroundColor(parseColor);
                    }
                }
                ColorDrawable colorDrawable = new ColorDrawable(HomeThemeConstant.aH);
                if (!TextUtils.isEmpty(this.d.mImageUrl)) {
                    ImageLoader.a().a(this.d.mImageUrl, this.g, new Option().a((Drawable) colorDrawable).b((Drawable) colorDrawable));
                }
                if (!TextUtils.isEmpty(this.d.mTitleDesc)) {
                    this.h.setText(this.d.mTitleDesc);
                }
                long countDownTime = getCountDownTime();
                if (countDownTime > 0) {
                    startCounterDown(countDownTime);
                } else {
                    this.i.setText("00");
                    this.j.setText("00");
                    this.k.setText("00");
                }
                this.f.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeSecKillView.this.a(HomeSecKillView.this.d);
                    }
                });
            }
            updateData(homeSection.mBody.mItems);
            this.e.scrollToPosition(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
        a();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        c();
        a();
    }

    public void setItemSize(int i2, int i3) {
        this.e.getLayoutParams().height = Coder.a(getContext(), 38.0f) + i3;
        this.c = new HorizontalAdapter(getContext(), i2, i3);
        this.e.setAdapter(this.c);
    }

    public void startCounterDown(long j2) {
        if (this.mCountDownTimer != null) {
            this.mCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
        AnonymousClass2 r1 = new CountDownTimer(j2, 1000) {
            public void onFinish() {
                HomeSecKillView.this.i.setText("00");
                HomeSecKillView.this.j.setText("00");
                HomeSecKillView.this.k.setText("00");
            }

            public void onTick(long j) {
                HomeSecKillView.this.a(j);
            }
        };
        this.mCountDownTimer = r1;
        r1.start();
    }

    public void updateData(ArrayList<HomeSectionItem> arrayList) {
        updateData(arrayList, false);
    }

    public void updateData(ArrayList<HomeSectionItem> arrayList, boolean z) {
        if (!ArrayUtils.a((ArrayList<?>) arrayList)) {
            this.b.a(arrayList.size());
            this.c.a(arrayList, z);
            if (z) {
                this.e.scrollToPosition(1073741823);
            }
        }
    }

    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        private int f13322a;
        private Drawable b;

        public DividerItemDecoration(Context context) {
            this.b = context.getResources().getDrawable(R.drawable.horizontal_recycler_divider_big);
        }

        public void a(Canvas canvas, RecyclerView recyclerView) {
            int paddingTop = recyclerView.getPaddingTop();
            int height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                int right = childAt.getRight() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
                this.b.setBounds(right, paddingTop, this.b.getIntrinsicHeight() + right, height);
                this.b.draw(canvas);
            }
        }

        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            if (i == 0) {
                rect.set(Coder.a(recyclerView.getContext(), 8.0f), 0, this.b.getIntrinsicWidth(), 0);
            } else {
                rect.set(0, 0, this.b.getIntrinsicWidth(), 0);
            }
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView) {
            a(canvas, recyclerView);
        }

        public void a(int i) {
            this.f13322a = i;
        }
    }

    static class HorizontalHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ViewHelperOnlyPrice f13325a;
        public HomeProductTagImageHelper b;
        public ImageView c;

        public HorizontalHolder(View view, int i, int i2) {
            super(view);
            this.c = (ImageView) view.findViewById(R.id.recycler_img);
            if (i2 > 0 && i > 0) {
                view.getLayoutParams().width = i;
                this.c.getLayoutParams().height = i2;
                this.c.getLayoutParams().width = i;
            }
            this.f13325a = new ViewHelperOnlyPrice(view);
            this.b = new HomeProductTagImageHelper(view);
        }
    }

    static class HorizontalAdapter extends RecyclerView.Adapter<HorizontalHolder> {

        /* renamed from: a  reason: collision with root package name */
        private boolean f13323a;
        private Context b;
        private ArrayList<HomeSectionItem> c = new ArrayList<>();
        private int d;
        private int e;

        public HorizontalAdapter(Context context, int i, int i2) {
            this.b = context;
            this.e = i;
            this.d = i2;
        }

        private void b(HorizontalHolder horizontalHolder, int i) {
            new ColorDrawable(HomeThemeConstant.aH);
            HomeSectionItem homeSectionItem = this.c.get(i);
            ImageLoader.a().a(homeSectionItem.mImageUrl, horizontalHolder.c);
            horizontalHolder.f13325a.a(this.c.get(i));
            horizontalHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            horizontalHolder.b.a(homeSectionItem);
        }

        public int getItemCount() {
            if (this.f13323a) {
                return Integer.MAX_VALUE;
            }
            return this.c.size();
        }

        /* renamed from: a */
        public void onBindViewHolder(HorizontalHolder horizontalHolder, int i) {
            int size;
            if (this.f13323a && (size = this.c.size()) > 0 && (i = i % size) < 0) {
                i += size;
            }
            b(horizontalHolder, i);
        }

        /* renamed from: a */
        public HorizontalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new HorizontalHolder(LayoutInflater.from(this.b).inflate(R.layout.listitem_home_seckill_item, viewGroup, false), this.e, this.d);
        }

        public void a(ArrayList<HomeSectionItem> arrayList, boolean z) {
            this.f13323a = z;
            this.c.clear();
            this.c.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
}
