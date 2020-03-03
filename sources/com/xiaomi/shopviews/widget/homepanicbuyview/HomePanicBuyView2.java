package com.xiaomi.shopviews.widget.homepanicbuyview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.imageloader.ImageLoader;
import com.xiaomi.base.utils.ArrayUtils;
import com.xiaomi.base.utils.FontUtils;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.base.utils.ServerTimerUtils;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.utils.HomeProductTagImageHelper;
import com.xiaomi.shopviews.utils.PriceRegUtils;
import com.xiaomi.shopviews.widget.R;
import com.xiaomi.shopviews.widget.hdhorizontalrecyclergoods.CustRecylerView;
import com.xiaomi.shopviews.widget.homepanicbuyview.HomePanicBuyTabView2;
import com.xiaomi.shopviews.widget.homepanicbuyview.PanicBuyTimer;
import java.util.ArrayList;
import java.util.Iterator;

public class HomePanicBuyView2 extends LinearLayout implements IHomeItemView {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final int f13303a = ((int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 426.0f));
    /* access modifiers changed from: private */
    public static int b = ((ScreenInfo.a().e() / 1080) * 48);
    /* access modifiers changed from: private */
    public static int c = ((ScreenInfo.a().e() / 1080) * 24);
    private DividerItemDecoration d;
    /* access modifiers changed from: private */
    public boolean e = false;
    private HorizontalAdapter f;
    private LinearLayout g;
    private ArrayList<HomeSectionItem> h;
    /* access modifiers changed from: private */
    public HomePanicBuyTabView2 i;
    /* access modifiers changed from: private */
    public PanicBuyTimer j = new PanicBuyTimer();
    private CustRecylerView k;
    private FlashTimerView l;
    private RelativeLayout m;
    /* access modifiers changed from: private */
    public ImageView n;
    /* access modifiers changed from: private */
    public int o = 0;

    public void draw(HomeSection homeSection) {
    }

    public HomePanicBuyView2(Context context) {
        super(context);
        c();
    }

    public HomePanicBuyView2(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public HomePanicBuyView2(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        c();
    }

    @RequiresApi(api = 21)
    public HomePanicBuyView2(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        c();
    }

    private void a() {
        this.l.destroy();
        this.j.a();
        this.i.destroy();
        if (this.k != null) {
            this.k.removeAllViews();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.l.destroy();
        this.l.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public int a(HomeSectionItem homeSectionItem) {
        int i2 = 0;
        if (!ArrayUtils.a((ArrayList<?>) this.h)) {
            while (i2 < this.h.size() && !this.h.get(i2).equals(homeSectionItem)) {
                i2++;
            }
        }
        return i2;
    }

    private void c() {
        inflate(getContext(), R.layout.listitem_panic_buy_view2, this);
        this.k = (CustRecylerView) findViewById(R.id.recycler_view);
        this.g = (LinearLayout) findViewById(R.id.panic_buy_tab_view_container);
        this.k.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.d = new DividerItemDecoration(0);
        this.k.addItemDecoration(this.d);
        this.f = new HorizontalAdapter(getContext());
        this.k.setAdapter(this.f);
        this.i = (HomePanicBuyTabView2) findViewById(R.id.panic_buy_tab_view);
        this.m = (RelativeLayout) findViewById(R.id.single_tab_container);
        this.m.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 120.0f);
        this.n = (ImageView) findViewById(R.id.single_title_img);
        ((RelativeLayout.LayoutParams) this.n.getLayoutParams()).leftMargin = b;
        FlashTimerView flashTimerView = (FlashTimerView) findViewById(R.id.single_count_down_view);
        this.l = flashTimerView;
        flashTimerView.setItemBackground(getResources().getDrawable(R.drawable.bg_panic_buy_count_down));
        this.l.setItemTextColor(getResources().getColorStateList(R.color.panic_buy_count_down_color));
        this.l.showHour(false);
        this.i.setOnItemClickListener(new HomePanicBuyTabView2.OnItemClickListener() {
            public void a(HomeSectionItem homeSectionItem) {
                int unused = HomePanicBuyView2.this.o = HomePanicBuyView2.this.a(homeSectionItem);
                HomePanicBuyView2.this.j.a(homeSectionItem, false);
                HomePanicBuyView2.this.b(homeSectionItem);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(long j2, long j3) {
        long b2 = ServerTimerUtils.b(j2 * 1000, j3 * 1000);
        if (b2 > 0) {
            this.l.setVisibility(0);
            this.l.start(b2);
            return;
        }
        this.l.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void b(HomeSectionItem homeSectionItem) {
        a(homeSectionItem.mItems);
    }

    private void a(ArrayList<HomeSectionItem> arrayList) {
        if (ArrayUtils.a((ArrayList<?>) arrayList)) {
            return;
        }
        if (arrayList.size() == 1) {
            c(arrayList);
        } else {
            b(arrayList);
        }
    }

    private void b(ArrayList<HomeSectionItem> arrayList) {
        this.k.removeItemDecoration(this.d);
        this.d.a(arrayList.size());
        this.k.addItemDecoration(this.d);
        this.k.getLayoutParams().height = f13303a;
        this.f.a(arrayList);
    }

    private void c(ArrayList<HomeSectionItem> arrayList) {
        this.k.removeItemDecoration(this.d);
        this.k.getLayoutParams().height = f13303a;
        this.f.a(arrayList);
    }

    private void d(ArrayList<HomeSectionItem> arrayList) {
        if (arrayList.size() == 1) {
            this.e = true;
            this.m.setVisibility(0);
            this.i.setVisibility(8);
            this.g.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 132.0f);
            return;
        }
        this.e = false;
        this.m.setVisibility(8);
        this.i.setVisibility(0);
        this.i.updateData(arrayList);
        this.i.setSelectPosition(this.o);
        this.g.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 102.0f);
    }

    private void e(ArrayList<HomeSectionItem> arrayList) {
        this.j.a();
        this.j.a((PanicBuyTimer.PanicBuyTimerListener) new PanicBuyTimer.PanicBuyTimerListener() {
            public void a(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView2.this.e) {
                    HomePanicBuyView2.this.b();
                    return;
                }
                int access$100 = HomePanicBuyView2.this.a(homeSectionItem);
                HomePanicBuyView2.this.i.hideMultiCountDown(access$100);
                HomePanicBuyView2.this.i.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_begin);
                HomePanicBuyView2.this.i.setSelectPosition(access$100);
            }

            public void b(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView2.this.e) {
                    HomePanicBuyView2.this.b();
                    return;
                }
                int access$100 = HomePanicBuyView2.this.a(homeSectionItem);
                HomePanicBuyView2.this.i.hideMultiCountDown(access$100);
                HomePanicBuyView2.this.i.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_begin);
            }

            public void c(HomeSectionItem homeSectionItem) {
                d(homeSectionItem);
            }

            public void d(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView2.this.e) {
                    HomePanicBuyView2.this.b();
                    if (ServerTimerUtils.c(homeSectionItem.mStartTime * 1000, ServerTimerUtils.a(homeSectionItem.mServerTime * 1000))) {
                        ImageLoader.a().a(homeSectionItem.img_url_unstart, HomePanicBuyView2.this.n);
                    } else {
                        ImageLoader.a().a(homeSectionItem.mImageUrl, HomePanicBuyView2.this.n);
                    }
                    HomePanicBuyView2.this.a(homeSectionItem.mStartTime, homeSectionItem.mServerTime);
                    return;
                }
                int access$100 = HomePanicBuyView2.this.a(homeSectionItem);
                HomePanicBuyView2.this.i.showMultiCountDown(access$100, ServerTimerUtils.b(homeSectionItem.mStartTime * 1000, homeSectionItem.mServerTime * 1000));
                if (ServerTimerUtils.c(homeSectionItem.mStartTime * 1000, ServerTimerUtils.a(homeSectionItem.mServerTime * 1000))) {
                    HomePanicBuyView2.this.i.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_will_begin);
                } else {
                    HomePanicBuyView2.this.i.setItemViewText(access$100, homeSectionItem.title_tomorrow, homeSectionItem.subtitle_will_begin);
                }
            }

            public void e(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView2.this.e) {
                    HomePanicBuyView2.this.b();
                    return;
                }
                int access$100 = HomePanicBuyView2.this.a(homeSectionItem);
                HomePanicBuyView2.this.i.hideMultiCountDown(access$100);
                HomePanicBuyView2.this.i.setItemViewText(access$100, homeSectionItem.title_future, homeSectionItem.subtitle_will_begin);
            }

            public void f(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView2.this.e) {
                    HomePanicBuyView2.this.b();
                    return;
                }
                int access$100 = HomePanicBuyView2.this.a(homeSectionItem);
                HomePanicBuyView2.this.i.hideMultiCountDown(access$100);
                HomePanicBuyView2.this.i.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_will_begin);
            }

            public void g(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView2.this.e) {
                    HomePanicBuyView2.this.b();
                } else {
                    HomePanicBuyView2.this.i.setItemViewText(HomePanicBuyView2.this.a(homeSectionItem), homeSectionItem.title_tomorrow, homeSectionItem.subtitle_will_begin);
                }
            }
        });
        Iterator<HomeSectionItem> it = arrayList.iterator();
        while (it.hasNext()) {
            this.j.a(it.next());
        }
    }

    public void bind(HomeSection homeSection, int i2, int i3) {
        this.h = homeSection.mBody.mItems;
        this.o = 0;
        if (!ArrayUtils.a((ArrayList<?>) homeSection.mBody.mItems)) {
            d(homeSection.mBody.mItems);
            b(homeSection.mBody.mItems.get(this.o));
            e(homeSection.mBody.mItems);
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

    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        private int f13306a;

        public DividerItemDecoration(int i) {
            this.f13306a = i;
        }

        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            if (this.f13306a == 1) {
                rect.set(HomePanicBuyView2.b, 0, HomePanicBuyView2.b, 0);
            } else if (i == 0) {
                rect.set(HomePanicBuyView2.b, 0, HomePanicBuyView2.c, 0);
            } else if (i == this.f13306a - 1) {
                rect.set(0, 0, HomePanicBuyView2.b, 0);
            } else {
                rect.set(0, 0, HomePanicBuyView2.c, 0);
            }
        }

        public void a(int i) {
            this.f13306a = i;
        }
    }

    static class HorizontalHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public HomeProductTagImageHelper f13309a;
        /* access modifiers changed from: private */
        public LinearLayout b;
        /* access modifiers changed from: private */
        public ImageView c;
        /* access modifiers changed from: private */
        public ImageView d;
        /* access modifiers changed from: private */
        public TextView e;
        /* access modifiers changed from: private */
        public TextView f;
        /* access modifiers changed from: private */
        public TextView g;
        /* access modifiers changed from: private */
        public TextView h;
        /* access modifiers changed from: private */
        public TextView i;

        public HorizontalHolder(View view) {
            super(view);
            this.b = (LinearLayout) view.findViewById(R.id.content_container);
            this.d = (ImageView) view.findViewById(R.id.recycler_img);
            this.e = (TextView) view.findViewById(R.id.text_favor);
            this.f = (TextView) view.findViewById(R.id.text_title);
            this.h = (TextView) view.findViewById(R.id.tv_viewhelper_price);
            this.i = (TextView) view.findViewById(R.id.tv_viewhelper_price_qi);
            this.g = (TextView) view.findViewById(R.id.tv_viewhelper_marketprice);
            FontUtils.a(view.getContext(), this.h);
            FontUtils.a(view.getContext(), this.g);
            this.f13309a = new HomeProductTagImageHelper(view);
            this.c = (ImageView) view.findViewById(R.id.img_sell_out);
        }
    }

    static class HorizontalAdapter extends RecyclerView.Adapter<HorizontalHolder> {

        /* renamed from: a  reason: collision with root package name */
        private static final int f13307a = 0;
        private static final int b = 1;
        private static final int c = 2;
        private Context d;
        private int e;
        private ArrayList<HomeSectionItem> f = new ArrayList<>();

        public HorizontalAdapter(Context context) {
            this.d = context;
        }

        private void b(HorizontalHolder horizontalHolder, int i) {
            ((ViewGroup.MarginLayoutParams) horizontalHolder.b.getLayoutParams()).topMargin = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 18.0f);
            new ColorDrawable(HomeThemeConstant.aH);
            HomeSectionItem homeSectionItem = this.f.get(i);
            if (!(getItemViewType(i) == 0 || getItemViewType(i) == 1)) {
                getItemViewType(i);
            }
            if (!TextUtils.isEmpty(homeSectionItem.mProductName)) {
                horizontalHolder.f.setText(Html.fromHtml(homeSectionItem.mProductName));
            }
            PriceRegUtils.c(horizontalHolder.h, horizontalHolder.g, horizontalHolder.i, homeSectionItem);
            horizontalHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            horizontalHolder.f13309a.a(homeSectionItem);
            if (TextUtils.isEmpty(homeSectionItem.mFavorDesc)) {
                horizontalHolder.e.setVisibility(8);
            } else {
                horizontalHolder.e.setVisibility(0);
                horizontalHolder.e.setText(Html.fromHtml(homeSectionItem.mFavorDesc));
            }
            if (!TextUtils.isEmpty(homeSectionItem.sell_out_img)) {
                horizontalHolder.c.setVisibility(0);
            } else {
                horizontalHolder.c.setVisibility(8);
            }
        }

        public int getItemCount() {
            return this.f.size();
        }

        public int getItemViewType(int i) {
            return this.e;
        }

        /* renamed from: a */
        public void onBindViewHolder(HorizontalHolder horizontalHolder, int i) {
            b(horizontalHolder, i);
        }

        /* renamed from: a */
        public HorizontalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 0) {
                View inflate = LayoutInflater.from(this.d).inflate(R.layout.listitem_panic_buy_recycler_big_item_view2, viewGroup, false);
                HorizontalHolder horizontalHolder = new HorizontalHolder(inflate);
                inflate.getLayoutParams().width = ScreenInfo.a().e();
                horizontalHolder.b.getLayoutParams().width = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 984.0f);
                horizontalHolder.b.getLayoutParams().height = HomePanicBuyView2.f13303a;
                horizontalHolder.d.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 300.0f);
                return horizontalHolder;
            } else if (i == 1) {
                HorizontalHolder horizontalHolder2 = new HorizontalHolder(LayoutInflater.from(this.d).inflate(R.layout.listitem_panic_buy_recycler_short_item_view2, viewGroup, false));
                horizontalHolder2.b.getLayoutParams().width = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 480.0f);
                horizontalHolder2.b.getLayoutParams().height = HomePanicBuyView2.f13303a;
                horizontalHolder2.d.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 270.0f);
                return horizontalHolder2;
            } else {
                HorizontalHolder horizontalHolder3 = new HorizontalHolder(LayoutInflater.from(this.d).inflate(R.layout.listitem_panic_buy_recycler_short_item_view2, viewGroup, false));
                horizontalHolder3.b.getLayoutParams().width = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 405.0f);
                horizontalHolder3.b.getLayoutParams().height = HomePanicBuyView2.f13303a;
                horizontalHolder3.d.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 270.0f);
                return horizontalHolder3;
            }
        }

        public void a(ArrayList<HomeSectionItem> arrayList) {
            this.f.clear();
            if (arrayList == null) {
                this.e = 0;
            } else if (arrayList.size() == 1) {
                this.e = 0;
            } else if (arrayList.size() == 2) {
                this.e = 1;
            } else {
                this.e = 2;
            }
            this.f.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
}
