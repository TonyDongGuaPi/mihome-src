package com.xiaomi.shopviews.widget.hdhorizontalrecyclergoods;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.xiaomi.base.utils.Coder;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionBody;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.HomeThemeConstant;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.utils.HomeProductTagImageHelper;
import com.xiaomi.shopviews.widget.R;
import java.util.ArrayList;

public class HDHorizontalRecyclerGoodsView2 extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private DividerItemDecoration f13233a;
    private HorizontalAdapter b;
    private int c;
    private int d;
    private CustRecylerView e;

    public void draw(HomeSection homeSection) {
    }

    public HDHorizontalRecyclerGoodsView2(Context context) {
        super(context);
        b();
    }

    public HDHorizontalRecyclerGoodsView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public HDHorizontalRecyclerGoodsView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    @RequiresApi(api = 21)
    public HDHorizontalRecyclerGoodsView2(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        b();
    }

    private void a() {
        if (this.e != null) {
            this.e.removeAllViews();
        }
    }

    private void b() {
        inflate(getContext(), R.layout.listitem_horizontal_recycler_view2, this);
        CustRecylerView custRecylerView = (CustRecylerView) findViewById(R.id.recycler_view);
        this.e = custRecylerView;
        custRecylerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.f13233a = new DividerItemDecoration(getContext());
        this.e.addItemDecoration(this.f13233a);
        setItemSize();
        this.c = getResources().getColor(R.color.btn_buy_red);
        this.d = getResources().getColor(R.color.home_text_color_white);
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        if (homeSection != null) {
            HomeSectionBody homeSectionBody = homeSection.mBody;
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

    public void setItemSize() {
        this.b = new HorizontalAdapter(getContext());
        this.e.setAdapter(this.b);
    }

    public void updateData(HomeSectionBody homeSectionBody) {
        if (homeSectionBody.mItems != null) {
            this.f13233a.a(homeSectionBody.mItems.size());
            this.b.a(homeSectionBody.mItems, false, this.c, this.d);
        }
    }

    static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        private int f13234a;
        private Drawable b;

        public DividerItemDecoration(Context context) {
            this.b = context.getResources().getDrawable(R.drawable.hd_horizontal_recycler_divider);
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
                rect.set(Coder.a(recyclerView.getContext(), 6.0f), 0, this.b.getIntrinsicWidth(), 0);
            } else {
                rect.set(0, 0, this.b.getIntrinsicWidth(), 0);
            }
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView) {
            a(canvas, recyclerView);
        }

        public void a(int i) {
            this.f13234a = i;
        }
    }

    static class HorizontalHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ViewHelperProductTagArray f13237a;
        public HomeProductTagImageHelper b;
        public ViewHelperVerticalContent c;
        public ImageView d;

        public HorizontalHolder(View view) {
            super(view);
            this.d = (ImageView) view.findViewById(R.id.recycler_img);
            this.c = new ViewHelperVerticalContent(view);
            this.b = new HomeProductTagImageHelper(view);
            this.f13237a = new ViewHelperProductTagArray(view);
        }
    }

    static class HorizontalAdapter extends RecyclerView.Adapter<HorizontalHolder> {

        /* renamed from: a  reason: collision with root package name */
        private boolean f13235a;
        private int b;
        private int c;
        private Context d;
        private ArrayList<HomeSectionItem> e = new ArrayList<>();
        private View f;

        public HorizontalAdapter(Context context) {
            this.d = context;
        }

        private void b(HorizontalHolder horizontalHolder, int i) {
            new ColorDrawable(HomeThemeConstant.aH);
            HomeSectionItem homeSectionItem = this.e.get(i);
            horizontalHolder.c.a(homeSectionItem, this.b, this.c);
            horizontalHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            horizontalHolder.b.a(homeSectionItem);
            horizontalHolder.f13237a.a(homeSectionItem);
        }

        public int getItemCount() {
            if (this.f13235a) {
                return Integer.MAX_VALUE;
            }
            return this.e.size();
        }

        /* renamed from: a */
        public void onBindViewHolder(HorizontalHolder horizontalHolder, int i) {
            int size;
            if (this.f13235a && (size = this.e.size()) > 0 && (i = i % size) < 0) {
                i += size;
            }
            b(horizontalHolder, i);
        }

        /* renamed from: a */
        public HorizontalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.item_horizontal_recycler_view2, viewGroup, false);
            View findViewById = inflate.findViewById(R.id.rootView);
            this.f = findViewById;
            findViewById.setLayoutParams(new RelativeLayout.LayoutParams(Coder.a(viewGroup.getContext(), 152.0f), -2));
            return new HorizontalHolder(inflate);
        }

        public void a(ArrayList<HomeSectionItem> arrayList, boolean z, int i, int i2) {
            this.f13235a = z;
            this.b = i;
            this.c = i2;
            this.e.clear();
            this.e.addAll(arrayList);
            notifyDataSetChanged();
        }
    }
}
