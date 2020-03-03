package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.libra.Color;
import com.unionpay.mobile.android.global.b;
import java.util.ArrayList;

public final class g extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private Context f9720a;
    private c b;
    /* access modifiers changed from: private */
    public ArrayList<AdapterView.OnItemClickListener> c = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<View.OnClickListener> d = new ArrayList<>();
    private AdapterView.OnItemClickListener e = new h(this);
    private View.OnClickListener f = new i(this);

    public g(Context context, c cVar) {
        super(context);
        this.f9720a = context;
        this.b = cVar;
        RelativeLayout relativeLayout = new RelativeLayout(this.f9720a);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12, -1);
        LinearLayout linearLayout = new LinearLayout(this.f9720a);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setId(linearLayout.hashCode());
        relativeLayout.addView(linearLayout, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        LinearLayout linearLayout2 = new LinearLayout(this.f9720a);
        layoutParams2.addRule(10, -1);
        layoutParams2.addRule(2, linearLayout.getId());
        relativeLayout.addView(linearLayout2, layoutParams2);
        linearLayout2.setOnClickListener(this.f);
        int a2 = com.unionpay.mobile.android.utils.g.a(this.f9720a, 1.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        int i = b.f9560a;
        layoutParams3.bottomMargin = i;
        layoutParams3.topMargin = i;
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, a2);
        LinearLayout linearLayout3 = new LinearLayout(this.f9720a);
        linearLayout3.setBackgroundColor(Color.d);
        linearLayout.addView(linearLayout3, layoutParams4);
        new LinearLayout.LayoutParams(-1, -2);
        ListView listView = new ListView(this.f9720a);
        listView.setDivider((Drawable) null);
        a(listView, this.b);
        listView.setAdapter(this.b);
        listView.setCacheColorHint(-1);
        listView.setOnItemClickListener(this.e);
        new LinearLayout.LayoutParams(-1, -2);
        linearLayout.addView(listView);
        addView(relativeLayout);
    }

    private void a(ListView listView, ListAdapter listAdapter) {
        ViewGroup.LayoutParams layoutParams;
        if (listAdapter != null && (layoutParams = listView.getLayoutParams()) != null) {
            Rect rect = new Rect();
            ((Activity) this.f9720a).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int height = rect.height();
            int i = 0;
            for (int i2 = 0; i2 < listAdapter.getCount(); i2++) {
                View view = listAdapter.getView(i2, (View) null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
                if (i > height) {
                    break;
                }
            }
            layoutParams.height = Math.min(i, height);
            listView.setLayoutParams(layoutParams);
        }
    }

    public final void a(View.OnClickListener onClickListener) {
        this.d.add(onClickListener);
    }

    public final void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.c.add(onItemClickListener);
    }
}
