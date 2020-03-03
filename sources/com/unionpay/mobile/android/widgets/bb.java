package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.unionpay.mobile.android.resource.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class bb {

    /* renamed from: a  reason: collision with root package name */
    private static int f9785a = com.unionpay.mobile.android.global.a.r;
    private static int b = 40;
    /* access modifiers changed from: private */
    public static List<Integer> i = new ArrayList(10);
    /* access modifiers changed from: private */
    public Context c = null;
    /* access modifiers changed from: private */
    public View.OnClickListener d = null;
    private PopupWindow e = null;
    private View f = null;
    /* access modifiers changed from: private */
    public ScrollView g = null;
    /* access modifiers changed from: private */
    public int h = -1;
    private PopupWindow.OnDismissListener j = new bc(this);

    private class a extends BaseAdapter {
        private a() {
        }

        /* synthetic */ a(bb bbVar, byte b) {
            this();
        }

        public final int getCount() {
            return bb.i.size() + 2;
        }

        public final Object getItem(int i) {
            return null;
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout linearLayout = new LinearLayout(bb.this.c);
            Drawable a2 = c.a(bb.this.c).a(1022, -1, -1);
            Drawable a3 = c.a(bb.this.c).a(1022, -1, -1);
            linearLayout.setBackgroundDrawable(h.a(a2, a3, a3, a2));
            linearLayout.setMinimumHeight(g.a(bb.this.c, 55.0f));
            linearLayout.setClickable(true);
            linearLayout.setOnClickListener(bb.this.d);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            int i2 = 10;
            if (i == 9 || i == 11) {
                ImageView imageView = new ImageView(bb.this.c);
                if (i != 9) {
                    i2 = 20;
                }
                imageView.setImageDrawable(c.a(bb.this.c).a(i == 9 ? 1024 : 1025, -1, g.a(bb.this.c, 20.0f)));
                linearLayout.setId(i2);
                linearLayout.addView(imageView, layoutParams);
            } else {
                TextView textView = new TextView(bb.this.c);
                textView.setTextColor(-1);
                textView.getPaint().setFakeBoldText(true);
                textView.setTextSize(30.0f);
                textView.setGravity(17);
                if (i == 10) {
                    i = 9;
                }
                int intValue = ((Integer) bb.i.get(i)).intValue();
                linearLayout.setId(intValue);
                textView.setText(String.valueOf(intValue));
                linearLayout.addView(textView, layoutParams);
            }
            return linearLayout;
        }
    }

    private class b extends LinearLayout {
        public b(Context context) {
            super(context);
            setOrientation(1);
            setBackgroundColor(-11316397);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            LinearLayout linearLayout = new LinearLayout(context);
            int a2 = g.a(context, 5.0f);
            linearLayout.setPadding(0, a2, 0, a2);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundColor(-13816531);
            linearLayout.setOrientation(0);
            Drawable a3 = c.a(context).a(1020, -1, g.a(context, 24.0f));
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(a3);
            linearLayout.addView(imageView);
            addView(linearLayout, layoutParams);
            Collections.shuffle(bb.i);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.gravity = 17;
            GridView gridView = new GridView(context);
            gridView.setNumColumns(3);
            gridView.setAdapter(new a(bb.this, (byte) 0));
            gridView.setGravity(17);
            gridView.setStretchMode(2);
            gridView.setHorizontalScrollBarEnabled(false);
            gridView.setVerticalScrollBarEnabled(false);
            gridView.setEnabled(true);
            int a4 = g.a(bb.this.c, 1.0f);
            gridView.setHorizontalSpacing(a4);
            gridView.setVerticalSpacing(a4);
            int i = -a4;
            gridView.setPadding(i, a4, i, i);
            addView(gridView, layoutParams2);
        }
    }

    static {
        for (int i2 = 0; i2 < 10; i2++) {
            i.add(Integer.valueOf(i2));
        }
    }

    public bb(Context context, View.OnClickListener onClickListener, View view) {
        this.c = context;
        this.d = onClickListener;
        f9785a = g.a(this.c, 55.0f);
        b = g.a(this.c, 40.0f);
        ViewParent viewParent = (ViewParent) view;
        while (true) {
            if (viewParent == null) {
                break;
            } else if (viewParent instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) viewParent;
                this.g = scrollView;
                k.a("UPWidgetKeyBoard", "mSV : " + this.g.toString());
                k.a("UPWidgetKeyBoard", "mSV H:" + this.g.getHeight());
                this.f = scrollView.getChildAt(0);
                break;
            } else {
                viewParent = viewParent.getParent();
            }
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        new RelativeLayout.LayoutParams(-1, -2).setMargins(0, 0, 0, 0);
        relativeLayout.setBackgroundColor(-1342177280);
        RelativeLayout relativeLayout2 = new RelativeLayout(context);
        relativeLayout2.setBackgroundColor(-13290188);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        relativeLayout.addView(relativeLayout2, layoutParams);
        relativeLayout2.addView(new b(this.c), layoutParams);
        this.e = new PopupWindow(relativeLayout, -1, -2, true);
        new RelativeLayout.LayoutParams(-1, -2);
        this.e.setBackgroundDrawable(new BitmapDrawable());
        this.e.setOutsideTouchable(false);
        this.e.setFocusable(false);
        this.e.setOnDismissListener(this.j);
    }

    private static int d() {
        int i2 = (f9785a * 4) + b;
        k.c("UPWidgetKeyBoard", "kbH=" + i2);
        return i2;
    }

    public final void a() {
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    public final void a(View view) {
        if (this.e != null) {
            this.e.showAtLocation(view, 80, 0, 0);
            if (this.f != null) {
                view.setVisibility(0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.g.getLayoutParams();
                this.h = marginLayoutParams.height;
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                marginLayoutParams.height = ((com.unionpay.mobile.android.global.a.t - rect.top) - com.unionpay.mobile.android.global.a.k) - d();
                k.a("UPWidgetKeyBoard", "height = " + marginLayoutParams.height);
                marginLayoutParams.bottomMargin = d();
                this.g.setLayoutParams(marginLayoutParams);
            }
        }
    }

    public final boolean b() {
        return this.e.isShowing();
    }
}
