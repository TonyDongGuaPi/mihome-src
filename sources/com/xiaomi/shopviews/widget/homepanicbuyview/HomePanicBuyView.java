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
import com.xiaomi.base.utils.Coder;
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
import com.xiaomi.shopviews.widget.homepanicbuyview.HomePanicBuyTabView;
import com.xiaomi.shopviews.widget.homepanicbuyview.PanicBuyTimer;
import java.util.ArrayList;
import java.util.Iterator;

public class HomePanicBuyView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private DividerItemDecoration f13296a;
    /* access modifiers changed from: private */
    public boolean b = false;
    /* access modifiers changed from: private */
    public HorizontalAdapter c;
    private TextView d;
    private ArrayList<HomeSectionItem> e;
    private FlashTimerView f;
    /* access modifiers changed from: private */
    public HomePanicBuyTabView g;
    private LinearLayout h;
    /* access modifiers changed from: private */
    public PanicBuyTimer i = new PanicBuyTimer();
    private CustRecylerView j;
    /* access modifiers changed from: private */
    public FlashTimerView k;
    private RelativeLayout l;
    /* access modifiers changed from: private */
    public ImageView m;
    private RelativeLayout n;
    /* access modifiers changed from: private */
    public int o = 0;

    private static void a(String str, long j2) {
    }

    public void draw(HomeSection homeSection) {
    }

    public HomePanicBuyView(Context context) {
        super(context);
        e();
    }

    public HomePanicBuyView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public HomePanicBuyView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        e();
    }

    @RequiresApi(api = 21)
    public HomePanicBuyView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        e();
    }

    private void a() {
        if (this.j != null) {
            this.j.removeAllViews();
        }
        this.f.destroy();
        this.k.destroy();
        this.i.a();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.d.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.f.destroy();
        this.f.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.k.destroy();
        this.k.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public int a(HomeSectionItem homeSectionItem) {
        int i2 = 0;
        if (!ArrayUtils.a((ArrayList<?>) this.e)) {
            while (i2 < this.e.size() && !this.e.get(i2).equals(homeSectionItem)) {
                i2++;
            }
        }
        return i2;
    }

    private void e() {
        inflate(getContext(), R.layout.listitem_panic_buy_view, this);
        this.n = (RelativeLayout) findViewById(R.id.time_or_remind_wrap);
        FlashTimerView flashTimerView = (FlashTimerView) findViewById(R.id.count_down_view);
        this.f = flashTimerView;
        flashTimerView.setItemBackground(getResources().getDrawable(R.drawable.bg_panic_buy_count_down));
        this.f.setItemTextColor(getResources().getColorStateList(R.color.panic_buy_count_down_color));
        this.d = (TextView) findViewById(R.id.current_active_remind);
        CustRecylerView custRecylerView = (CustRecylerView) findViewById(R.id.recycler_view);
        this.j = custRecylerView;
        custRecylerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.f13296a = new DividerItemDecoration(0);
        this.j.addItemDecoration(this.f13296a);
        this.c = new HorizontalAdapter(getContext());
        this.j.setAdapter(this.c);
        this.h = (LinearLayout) findViewById(R.id.panic_buy_tab_view_container);
        this.g = (HomePanicBuyTabView) findViewById(R.id.panic_buy_tab_view);
        this.l = (RelativeLayout) findViewById(R.id.single_tab_container);
        this.m = (ImageView) findViewById(R.id.single_title_img);
        FlashTimerView flashTimerView2 = (FlashTimerView) findViewById(R.id.single_count_down_view);
        this.k = flashTimerView2;
        flashTimerView2.setItemBackground(getResources().getDrawable(R.drawable.bg_panic_buy_count_down));
        this.k.setItemTextColor(getResources().getColorStateList(R.color.panic_buy_count_down_color));
        this.g.setOnItemClickListener(new HomePanicBuyTabView.OnItemClickListener() {
            public void a(HomeSectionItem homeSectionItem) {
                int unused = HomePanicBuyView.this.o = HomePanicBuyView.this.a(homeSectionItem);
                HomePanicBuyView.this.i.a(homeSectionItem, false);
                HomePanicBuyView.this.b(homeSectionItem);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.d.setVisibility(0);
        this.d.setText(str);
    }

    /* access modifiers changed from: private */
    public void a(long j2, long j3) {
        this.f.setVisibility(0);
        this.f.start(ServerTimerUtils.b(j2 * 1000, j3 * 1000));
    }

    private void b(long j2, long j3) {
        this.k.setVisibility(0);
        this.k.start(ServerTimerUtils.b(j2 * 1000, j3 * 1000));
    }

    /* access modifiers changed from: private */
    public void b(HomeSectionItem homeSectionItem) {
        this.c.a(homeSectionItem.mStartTime);
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
        this.j.removeItemDecoration(this.f13296a);
        this.f13296a.a(arrayList.size());
        this.j.addItemDecoration(this.f13296a);
        this.j.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 690.0f);
        this.c.a(arrayList);
    }

    private void c(ArrayList<HomeSectionItem> arrayList) {
        this.j.removeItemDecoration(this.f13296a);
        this.j.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 690.0f);
        this.c.a(arrayList);
    }

    private void d(ArrayList<HomeSectionItem> arrayList) {
        if (arrayList.size() == 1) {
            this.b = true;
            this.l.setVisibility(0);
            this.g.setVisibility(8);
            this.n.setVisibility(8);
            this.c.b(true);
            return;
        }
        this.b = false;
        this.l.setVisibility(8);
        this.g.setVisibility(0);
        this.g.updateData(arrayList);
        this.g.setSelectPosition(this.o);
        this.n.setVisibility(0);
        this.c.b(false);
    }

    private void e(ArrayList<HomeSectionItem> arrayList) {
        this.i.a();
        this.i.a((PanicBuyTimer.PanicBuyTimerListener) new PanicBuyTimer.PanicBuyTimerListener() {
            public void a(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView.this.b) {
                    HomePanicBuyView.this.d();
                    ImageLoader.a().a(homeSectionItem.img_url_start, HomePanicBuyView.this.m);
                    HomePanicBuyView.this.c.a(2);
                    HomePanicBuyView.this.c.a(homeSectionItem.start_title);
                    return;
                }
                int access$100 = HomePanicBuyView.this.a(homeSectionItem);
                if (access$100 == HomePanicBuyView.this.o) {
                    HomePanicBuyView.this.c();
                    HomePanicBuyView.this.a(homeSectionItem.desc_begin);
                    HomePanicBuyView.this.c.a(2);
                    HomePanicBuyView.this.c.a(homeSectionItem.start_title);
                }
                HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_begin);
                HomePanicBuyView.this.g.setSelectPosition(access$100);
            }

            public void b(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView.this.b) {
                    HomePanicBuyView.this.d();
                    HomePanicBuyView.this.c.a(2);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                    return;
                }
                int access$100 = HomePanicBuyView.this.a(homeSectionItem);
                if (access$100 == HomePanicBuyView.this.o) {
                    HomePanicBuyView.this.c();
                    HomePanicBuyView.this.a(homeSectionItem.subtitle_end);
                    HomePanicBuyView.this.c.a(2);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                }
                HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_end);
            }

            public void c(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView.this.b) {
                    HomePanicBuyView.this.k.setVisibility(8);
                    HomePanicBuyView.this.c.a(1);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                    return;
                }
                int access$100 = HomePanicBuyView.this.a(homeSectionItem);
                if (access$100 == HomePanicBuyView.this.o) {
                    HomePanicBuyView.this.a(homeSectionItem.mStartTime, homeSectionItem.mServerTime);
                    HomePanicBuyView.this.b();
                    HomePanicBuyView.this.c.a(1);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                }
                HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_will_begin);
            }

            public void d(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView.this.b) {
                    HomePanicBuyView.this.d();
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                    return;
                }
                int access$100 = HomePanicBuyView.this.a(homeSectionItem);
                if (access$100 == HomePanicBuyView.this.o) {
                    HomePanicBuyView.this.a(homeSectionItem.mStartTime, homeSectionItem.mServerTime);
                    HomePanicBuyView.this.b();
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                }
                if (ServerTimerUtils.c(homeSectionItem.mStartTime * 1000, ServerTimerUtils.a(homeSectionItem.mServerTime * 1000))) {
                    HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_will_begin);
                } else {
                    HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_tomorrow, homeSectionItem.subtitle_will_begin);
                }
            }

            public void e(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView.this.b) {
                    HomePanicBuyView.this.d();
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                    return;
                }
                int access$100 = HomePanicBuyView.this.a(homeSectionItem);
                if (access$100 == HomePanicBuyView.this.o) {
                    HomePanicBuyView.this.c();
                    HomePanicBuyView.this.a(homeSectionItem.desc_will_begin);
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                }
                HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_future, homeSectionItem.subtitle_will_begin);
            }

            public void f(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView.this.b) {
                    HomePanicBuyView.this.d();
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                    return;
                }
                int access$100 = HomePanicBuyView.this.a(homeSectionItem);
                if (access$100 == HomePanicBuyView.this.o) {
                    HomePanicBuyView.this.c();
                    HomePanicBuyView.this.a(homeSectionItem.desc_will_begin);
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                }
                HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_today, homeSectionItem.subtitle_will_begin);
            }

            public void g(HomeSectionItem homeSectionItem) {
                if (HomePanicBuyView.this.b) {
                    HomePanicBuyView.this.d();
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                    return;
                }
                int access$100 = HomePanicBuyView.this.a(homeSectionItem);
                if (access$100 == HomePanicBuyView.this.o) {
                    HomePanicBuyView.this.c();
                    HomePanicBuyView.this.a(homeSectionItem.desc_will_begin);
                    HomePanicBuyView.this.c.a(0);
                    HomePanicBuyView.this.c.a(homeSectionItem.not_start_title);
                }
                HomePanicBuyView.this.g.setItemViewText(access$100, homeSectionItem.title_tomorrow, homeSectionItem.subtitle_will_begin);
            }
        });
        Iterator<HomeSectionItem> it = arrayList.iterator();
        while (it.hasNext()) {
            this.i.a(it.next());
        }
    }

    public void bind(HomeSection homeSection, int i2, int i3) {
        this.e = homeSection.mBody.mItems;
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
        private int f13299a;

        public DividerItemDecoration(int i) {
            this.f13299a = i;
        }

        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            if (i == 0) {
                rect.set(Coder.a(recyclerView.getContext(), 16.0f), 0, Coder.a(recyclerView.getContext(), 8.0f), 0);
            } else if (i == this.f13299a - 1) {
                rect.set(0, 0, Coder.a(recyclerView.getContext(), 16.0f), 0);
            } else {
                rect.set(0, 0, Coder.a(recyclerView.getContext(), 8.0f), 0);
            }
        }

        public void a(int i) {
            this.f13299a = i;
        }
    }

    static class HorizontalHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public HomeProductTagImageHelper f13302a;
        /* access modifiers changed from: private */
        public TextView b;
        /* access modifiers changed from: private */
        public LinearLayout c;
        /* access modifiers changed from: private */
        public ImageView d;
        private ImageView e;
        /* access modifiers changed from: private */
        public ImageView f;
        /* access modifiers changed from: private */
        public TextView g;
        /* access modifiers changed from: private */
        public TextView h;
        /* access modifiers changed from: private */
        public TextView i;
        /* access modifiers changed from: private */
        public TextView j;
        /* access modifiers changed from: private */
        public TextView k;

        public HorizontalHolder(View view) {
            super(view);
            this.c = (LinearLayout) view.findViewById(R.id.content_container);
            this.f = (ImageView) view.findViewById(R.id.recycler_img);
            this.g = (TextView) view.findViewById(R.id.text_favor);
            this.e = (ImageView) view.findViewById(R.id.listitem_home_tag_image);
            this.h = (TextView) view.findViewById(R.id.text_title);
            this.b = (TextView) view.findViewById(R.id.btn_action);
            this.j = (TextView) view.findViewById(R.id.tv_viewhelper_price);
            this.k = (TextView) view.findViewById(R.id.tv_viewhelper_price_qi);
            this.i = (TextView) view.findViewById(R.id.tv_viewhelper_marketprice);
            FontUtils.a(view.getContext(), this.j);
            FontUtils.a(view.getContext(), this.i);
            this.f13302a = new HomeProductTagImageHelper(view);
            this.d = (ImageView) view.findViewById(R.id.img_sell_out);
        }
    }

    static class HorizontalAdapter extends RecyclerView.Adapter<HorizontalHolder> {

        /* renamed from: a  reason: collision with root package name */
        private static final int f13300a = 0;
        private static final int b = 1;
        private int c = 0;
        private boolean d;
        private boolean e;
        private String f;
        private Context g;
        private ArrayList<HomeSectionItem> h = new ArrayList<>();
        private long i;

        public void a(int i2) {
        }

        public HorizontalAdapter(Context context) {
            this.g = context;
        }

        private void b(HorizontalHolder horizontalHolder, int i2) {
            new ColorDrawable(HomeThemeConstant.aH);
            HomeSectionItem homeSectionItem = this.h.get(i2);
            if (getItemViewType(i2) == 0) {
                ImageLoader.a().a(homeSectionItem.mImageUrl, horizontalHolder.f);
            } else {
                ImageLoader.a().a(homeSectionItem.mSmallImgUrl, horizontalHolder.f);
            }
            if (!TextUtils.isEmpty(homeSectionItem.mProductName)) {
                horizontalHolder.h.setText(Html.fromHtml(homeSectionItem.mProductName));
            }
            if (this.e) {
                ((ViewGroup.MarginLayoutParams) horizontalHolder.c.getLayoutParams()).topMargin = Coder.a(this.g, 8.0f);
            } else {
                ((ViewGroup.MarginLayoutParams) horizontalHolder.c.getLayoutParams()).topMargin = 0;
            }
            horizontalHolder.b.setText(this.f);
            PriceRegUtils.c(horizontalHolder.j, horizontalHolder.i, horizontalHolder.k, homeSectionItem);
            horizontalHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            horizontalHolder.f13302a.a(homeSectionItem);
            if (TextUtils.isEmpty(homeSectionItem.mFavorDesc)) {
                horizontalHolder.g.setVisibility(8);
            } else {
                horizontalHolder.g.setVisibility(0);
                horizontalHolder.g.setText(Html.fromHtml(homeSectionItem.mFavorDesc));
            }
            if (!TextUtils.isEmpty(homeSectionItem.sell_out_img)) {
                horizontalHolder.d.setVisibility(0);
            } else {
                horizontalHolder.d.setVisibility(8);
            }
        }

        public int getItemCount() {
            return this.h.size();
        }

        public int getItemViewType(int i2) {
            return this.d ^ true ? 1 : 0;
        }

        /* renamed from: a */
        public void onBindViewHolder(HorizontalHolder horizontalHolder, int i2) {
            b(horizontalHolder, i2);
        }

        /* renamed from: a */
        public HorizontalHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            if (i2 == 0) {
                View inflate = LayoutInflater.from(this.g).inflate(R.layout.listitem_panic_buy_recycler_big_item_view, viewGroup, false);
                HorizontalHolder horizontalHolder = new HorizontalHolder(inflate);
                inflate.getLayoutParams().width = ScreenInfo.a().e();
                horizontalHolder.c.getLayoutParams().width = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 984.0f);
                horizontalHolder.c.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 644.0f);
                horizontalHolder.f.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 492.0f);
                return horizontalHolder;
            }
            HorizontalHolder horizontalHolder2 = new HorizontalHolder(LayoutInflater.from(this.g).inflate(R.layout.listitem_panic_buy_recycler_short_item_view, viewGroup, false));
            horizontalHolder2.c.getLayoutParams().width = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 480.0f);
            horizontalHolder2.c.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 644.0f);
            horizontalHolder2.f.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 320.0f);
            return horizontalHolder2;
        }

        public void a(String str) {
            this.f = str;
            notifyDataSetChanged();
        }

        public void a(boolean z) {
            this.d = z;
        }

        public void b(boolean z) {
            this.e = z;
        }

        public void a(long j) {
            this.i = j;
        }

        public void a(ArrayList<HomeSectionItem> arrayList) {
            this.h.clear();
            if (arrayList == null || arrayList.size() != 1) {
                a(false);
            } else {
                a(true);
            }
            this.h.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
}
