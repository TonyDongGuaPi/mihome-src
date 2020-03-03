package com.xiaomi.shopviews.widget.homepanicbuyview;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
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

public class HomePanicBuyTabView extends HorizontalScrollView {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout f13292a;
    private ArrayList<HomeSectionItem> b;
    private double c;
    private OnItemClickListener d;
    private View e;
    private int f = -1;

    public interface OnItemClickListener {
        void a(HomeSectionItem homeSectionItem);
    }

    public HomePanicBuyTabView(Context context) {
        super(context);
        c();
    }

    public HomePanicBuyTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public HomePanicBuyTabView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    @RequiresApi(api = 21)
    public HomePanicBuyTabView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        c();
    }

    private void a(View view, HomeSectionItem homeSectionItem, final int i) {
        ((TextView) view.findViewById(R.id.tab_active_start_time)).setText(homeSectionItem.mTitle);
        ((TextView) view.findViewById(R.id.tab_active_remind)).setText(homeSectionItem.mSubTitle);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomePanicBuyTabView.this.setSelectPosition(i);
            }
        });
        this.f13292a.addView(view);
    }

    private View a() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.listitem_panic_buy_tab_view, (ViewGroup) null);
        inflate.setLayoutParams(new LinearLayout.LayoutParams((int) this.c, -1));
        return inflate;
    }

    private View b() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.listitem_panic_buy_tab_view, (ViewGroup) null);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        return inflate;
    }

    private void c() {
        double e2 = (double) ScreenInfo.a().e();
        Double.isNaN(e2);
        this.c = e2 / 4.5d;
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.f13292a = linearLayout;
        addView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
        this.f13292a.setOrientation(0);
        setFillViewport(true);
    }

    private void setSelectView(View view) {
        if (this.e != view) {
            if (this.e != null) {
                this.e.setBackgroundResource(R.drawable.panic_buy_tab_button_normal);
            }
            view.setBackgroundResource(R.drawable.panic_buy_tab_button_pressed);
            this.e = view;
        }
    }

    public void setItemViewText(int i, String str, String str2) {
        View childAt = this.f13292a.getChildAt(i);
        if (childAt != null) {
            TextView textView = (TextView) childAt.findViewById(R.id.tab_active_start_time);
            TextView textView2 = (TextView) childAt.findViewById(R.id.tab_active_remind);
            if (textView != null) {
                textView.setText(str);
            }
            if (textView2 != null) {
                textView2.setText(str2);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.d = onItemClickListener;
    }

    public void setSelectPosition(int i) {
        if (this.b != null && i >= 0 && i < this.b.size() && this.f != i) {
            this.f = i;
            setSelectView(this.f13292a.getChildAt(i));
            if (this.d != null) {
                this.d.a(this.b.get(i));
            }
        }
    }

    public void updateData(ArrayList<HomeSectionItem> arrayList) {
        this.f = -1;
        this.b = arrayList;
        this.f13292a.removeAllViews();
        if (!ArrayUtils.a((ArrayList<?>) arrayList)) {
            int i = 0;
            if (arrayList.size() < 5) {
                while (i < arrayList.size()) {
                    HomeSectionItem homeSectionItem = arrayList.get(i);
                    if (homeSectionItem != null) {
                        a(b(), homeSectionItem, i);
                    }
                    i++;
                }
                return;
            }
            while (i < arrayList.size()) {
                HomeSectionItem homeSectionItem2 = arrayList.get(i);
                if (homeSectionItem2 != null) {
                    a(a(), homeSectionItem2, i);
                }
                i++;
            }
        }
    }
}
