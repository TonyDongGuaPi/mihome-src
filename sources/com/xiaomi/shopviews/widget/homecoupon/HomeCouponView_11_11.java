package com.xiaomi.shopviews.widget.homecoupon;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.imageloader.Option;
import com.xiaomi.base.utils.ArrayUtils;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.base.utils.ServerTimerUtils;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;
import java.util.ArrayList;

public class HomeCouponView_11_11 extends RelativeLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13242a = HomeCouponView_11_11.class.getSimpleName();
    /* access modifiers changed from: private */
    public static final ColorDrawable b = new ColorDrawable(HomeThemeConstant.aH);
    private DividerItemDecoration c;
    private HorizontalAdapter d;
    private ImageView e;
    /* access modifiers changed from: private */
    public HomeSection f;
    private RelativeLayout g;
    private RecyclerView h;
    private Runnable i;

    public void draw(HomeSection homeSection) {
    }

    public HomeCouponView_11_11(Context context) {
        super(context);
        this.i = new Runnable() {
            public void run() {
                HomeCouponView_11_11.this.bind(HomeCouponView_11_11.this.f, 0, 0);
            }
        };
        a();
    }

    public HomeCouponView_11_11(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = new Runnable() {
            public void run() {
                HomeCouponView_11_11.this.bind(HomeCouponView_11_11.this.f, 0, 0);
            }
        };
        a();
    }

    public HomeCouponView_11_11(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.i = new Runnable() {
            public void run() {
                HomeCouponView_11_11.this.bind(HomeCouponView_11_11.this.f, 0, 0);
            }
        };
        a();
    }

    @RequiresApi(api = 21)
    public HomeCouponView_11_11(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.i = new Runnable() {
            public void run() {
                HomeCouponView_11_11.this.bind(HomeCouponView_11_11.this.f, 0, 0);
            }
        };
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.listitem_home_horizontal_recycler_coupon, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.h = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.d = new HorizontalAdapter(getContext());
        this.h.setAdapter(this.d);
        this.e = (ImageView) findViewById(R.id.bg_image);
        this.e.getLayoutParams().height = (ScreenInfo.a().e() * 275) / 1080;
        this.e.getLayoutParams().width = ScreenInfo.a().e();
        this.g = (RelativeLayout) findViewById(R.id.list_container);
        this.g.getLayoutParams().height = (ScreenInfo.a().e() * 275) / 1080;
    }

    private void b() {
        if (this.h != null) {
            this.h.removeAllViews();
        }
        removeCallbacks(this.i);
    }

    public void bind(HomeSection homeSection, int i2, int i3) {
        this.f = homeSection;
        ImageLoader.a().a(homeSection.mBody.mImageUrl, this.e, new Option().a((Drawable) b));
        this.d.a(homeSection.mBody.mTextSize, homeSection.mBody.mTextColor);
        long a2 = ServerTimerUtils.a(homeSection.mBody.mServerTime * 1000);
        long j = homeSection.mBody.mStartTime * 1000;
        removeCallbacks(this.i);
        if (a2 < j || j == 0) {
            if (j != 0) {
                postDelayed(this.i, j - a2);
            }
            updateData(homeSection.mBody.mItems);
            return;
        }
        updateData(homeSection.mBody.mNextItems);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        b();
    }

    public void updateData(ArrayList<HomeSectionItem> arrayList) {
        if (!ArrayUtils.a((ArrayList<?>) arrayList)) {
            if (this.c != null) {
                this.h.removeItemDecoration(this.c);
            }
            if (arrayList.size() <= 3) {
                this.c = new DividerItemDecoration(getContext(), 2);
            } else {
                this.c = new DividerItemDecoration(getContext(), 0);
            }
            this.h.addItemDecoration(this.c);
            this.d.a(arrayList);
        }
    }

    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        private static final int f13247a = 2;
        private static final int b = 0;
        private Drawable c;

        public DividerItemDecoration(Context context, int i) {
            if (i == 0) {
                this.c = context.getResources().getDrawable(R.drawable.horizontal_recycler_coupon_divider1);
            } else {
                this.c = context.getResources().getDrawable(R.drawable.horizontal_recycler_coupon_divider);
            }
        }

        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            if (i == 0) {
                rect.set(this.c.getIntrinsicWidth(), 0, this.c.getIntrinsicWidth(), 0);
            } else {
                rect.set(0, 0, this.c.getIntrinsicWidth(), 0);
            }
        }
    }

    static class HorizontalHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public int f13250a = -1;
        public ImageView b;
        /* access modifiers changed from: private */
        public TextView c;

        public HorizontalHolder(View view) {
            super(view);
            this.b = (ImageView) view.findViewById(R.id.coupon_img);
            this.b.getLayoutParams().height = (ScreenInfo.a().e() * 190) / 1080;
            this.c = (TextView) view.findViewById(R.id.text_title);
        }
    }

    static class HorizontalAdapter extends RecyclerView.Adapter<HorizontalHolder> {

        /* renamed from: a  reason: collision with root package name */
        private Context f13248a;
        private ArrayList<HomeSectionItem> b = new ArrayList<>();
        private String c;
        private float d;

        public void a(HomeSectionItem homeSectionItem) {
        }

        public HorizontalAdapter(Context context) {
            this.f13248a = context;
        }

        private void b(HorizontalHolder horizontalHolder, int i) {
            Drawable drawable;
            final HomeSectionItem homeSectionItem = this.b.get(i);
            if (horizontalHolder.f13250a == i) {
                drawable = horizontalHolder.b.getDrawable();
            } else {
                drawable = HomeCouponView_11_11.b;
            }
            horizontalHolder.f13250a = i;
            ImageLoader.a().a(homeSectionItem.mImageUrl, horizontalHolder.b, new Option().a(drawable));
            if (!TextUtils.isEmpty(homeSectionItem.mTitle)) {
                horizontalHolder.c.setVisibility(0);
                horizontalHolder.c.setText(Html.fromHtml(homeSectionItem.mTitle));
                if (!TextUtils.isEmpty(this.c)) {
                    horizontalHolder.c.setTextColor(Color.parseColor(this.c));
                }
                if (this.d > 0.0f) {
                    horizontalHolder.c.setTextSize(1, this.d);
                }
            } else {
                horizontalHolder.c.setVisibility(8);
            }
            horizontalHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (homeSectionItem != null && homeSectionItem.mCanGet && !homeSectionItem.isRequesting) {
                        homeSectionItem.isRequesting = true;
                        HorizontalAdapter.this.a(homeSectionItem);
                    }
                }
            });
        }

        public int getItemCount() {
            return this.b.size();
        }

        /* renamed from: a */
        public void onBindViewHolder(HorizontalHolder horizontalHolder, int i) {
            b(horizontalHolder, i);
        }

        /* renamed from: a */
        public HorizontalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new HorizontalHolder(LayoutInflater.from(this.f13248a).inflate(R.layout.listitem_home_horizontal_item_coupon, (ViewGroup) null));
        }

        public void a(float f, String str) {
            this.c = str;
            this.d = f;
        }

        public void a(ArrayList<HomeSectionItem> arrayList) {
            this.b.clear();
            this.b.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
}
