package com.xiaomi.shopviews.widget.homepanicbuyview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.base.utils.ArrayUtils;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;
import java.util.ArrayList;

public class HomePanicBuyTabView2 extends HorizontalScrollView {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout f13294a;
    private ArrayList<HomeSectionItem> b;
    private SparseArray<String> c = new SparseArray<>();
    private double d;
    private OnItemClickListener e;
    private View f;
    private int g = -1;

    public interface OnItemClickListener {
        void a(HomeSectionItem homeSectionItem);
    }

    public HomePanicBuyTabView2(Context context) {
        super(context);
        c();
    }

    public HomePanicBuyTabView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public HomePanicBuyTabView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    @RequiresApi(api = 21)
    public HomePanicBuyTabView2(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        c();
    }

    private void a(View view, HomeSectionItem homeSectionItem, final int i) {
        ((TextView) view.findViewById(R.id.tab_active_start_time)).setText(homeSectionItem.mTitle);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomePanicBuyTabView2.this.setSelectPosition(i);
            }
        });
        this.f13294a.addView(view);
    }

    private View a() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.listitem_panic_buy_tab_view2, (ViewGroup) null);
        inflate.findViewById(R.id.title_container).getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 90.0f);
        inflate.setLayoutParams(new LinearLayout.LayoutParams((int) this.d, -2));
        FlashTimerView flashTimerView = (FlashTimerView) inflate.findViewById(R.id.count_down_view);
        flashTimerView.setItemBackground((Drawable) null);
        flashTimerView.setItemTextColor(getResources().getColorStateList(R.color.home_text_color_black_a));
        flashTimerView.showHour(false);
        flashTimerView.setWidthWrapContent();
        return inflate;
    }

    private View b() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.listitem_panic_buy_tab_view2, (ViewGroup) null);
        inflate.findViewById(R.id.title_container).getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) / 1080.0f) * 90.0f);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        FlashTimerView flashTimerView = (FlashTimerView) inflate.findViewById(R.id.count_down_view);
        flashTimerView.setItemBackground((Drawable) null);
        flashTimerView.setItemTextColor(getResources().getColorStateList(R.color.home_text_color_black_a));
        flashTimerView.showHour(false);
        flashTimerView.setWidthWrapContent();
        return inflate;
    }

    private void c() {
        double e2 = (double) ScreenInfo.a().e();
        Double.isNaN(e2);
        this.d = e2 / 3.5d;
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f13294a = linearLayout;
        addView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        this.f13294a.setOrientation(0);
        setFillViewport(true);
    }

    private void setSelectView(View view) {
        if (this.f != view) {
            if (this.f != null) {
                this.f.findViewById(R.id.title_container).setBackgroundColor(getResources().getColor(R.color.white));
                this.f.findViewById(R.id.arrow_down).setVisibility(8);
            }
            view.findViewById(R.id.title_container).setBackgroundColor(getResources().getColor(R.color.panic_select_bg));
            view.findViewById(R.id.arrow_down).setVisibility(0);
            this.f = view;
        }
    }

    public void destroy() {
        FlashTimerView flashTimerView;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = this.f13294a.getChildAt(i);
            if (!(childAt == null || (flashTimerView = (FlashTimerView) childAt.findViewById(R.id.count_down_view)) == null)) {
                flashTimerView.destroy();
            }
        }
    }

    public void hideMultiCountDown(int i) {
        View childAt = this.f13294a.getChildAt(i);
        if (childAt != null) {
            FlashTimerView flashTimerView = (FlashTimerView) childAt.findViewById(R.id.count_down_view);
            flashTimerView.destroy();
            flashTimerView.setVisibility(8);
            ((TextView) childAt.findViewById(R.id.tab_active_start_time)).setVisibility(0);
        }
    }

    public void setItemViewText(int i, String str, String str2) {
        TextView textView;
        String str3;
        View childAt = this.f13294a.getChildAt(i);
        if (childAt != null && (textView = (TextView) childAt.findViewById(R.id.tab_active_start_time)) != null) {
            if (TextUtils.isEmpty(this.c.get(i))) {
                str3 = "";
            } else {
                str3 = this.c.get(i);
            }
            textView.setText(str + str3);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    public void setSelectPosition(int i) {
        if (this.b != null && i >= 0 && i < this.b.size() && this.g != i) {
            this.g = i;
            setSelectView(this.f13294a.getChildAt(i));
            if (this.e != null) {
                this.e.a(this.b.get(i));
            }
        }
    }

    public void showMultiCountDown(int i, long j) {
        String str;
        View childAt = this.f13294a.getChildAt(i);
        if (childAt != null) {
            FlashTimerView flashTimerView = (FlashTimerView) childAt.findViewById(R.id.count_down_view);
            TextView textView = (TextView) childAt.findViewById(R.id.tab_active_start_time);
            if (TextUtils.isEmpty(this.c.get(i))) {
                str = "";
            } else {
                str = this.c.get(i);
            }
            flashTimerView.setEndTitle(str);
            flashTimerView.start(j);
            flashTimerView.setVisibility(0);
            textView.setVisibility(8);
        }
    }

    public void updateData(ArrayList<HomeSectionItem> arrayList) {
        this.g = -1;
        this.b = arrayList;
        this.f13294a.removeAllViews();
        if (!ArrayUtils.a((ArrayList<?>) arrayList)) {
            int i = 0;
            if (arrayList.size() < 4) {
                while (i < arrayList.size()) {
                    HomeSectionItem homeSectionItem = arrayList.get(i);
                    if (homeSectionItem != null) {
                        this.c.put(i, homeSectionItem.title_end);
                        a(b(), homeSectionItem, i);
                    }
                    i++;
                }
                return;
            }
            while (i < arrayList.size()) {
                HomeSectionItem homeSectionItem2 = arrayList.get(i);
                if (homeSectionItem2 != null) {
                    this.c.put(i, homeSectionItem2.title_end);
                    a(a(), homeSectionItem2, i);
                }
                i++;
            }
        }
    }
}
