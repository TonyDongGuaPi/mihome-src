package com.yuyh.library;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yuyh.library.bean.Confirm;
import com.yuyh.library.bean.HighlightArea;
import com.yuyh.library.bean.Message;
import com.yuyh.library.bean.TipsView;
import com.yuyh.library.support.HShape;
import com.yuyh.library.support.OnStateChangedListener;
import com.yuyh.library.view.EasyGuideView;
import java.util.ArrayList;
import java.util.List;

public class EasyGuide {

    /* renamed from: a  reason: collision with root package name */
    private Activity f2558a;
    private FrameLayout b;
    private EasyGuideView c;
    private LinearLayout d;
    /* access modifiers changed from: private */
    public List<HighlightArea> e;
    private List<TipsView> f;
    private List<Message> g;
    private Confirm h;
    /* access modifiers changed from: private */
    public boolean i;
    /* access modifiers changed from: private */
    public boolean j;
    /* access modifiers changed from: private */
    public OnStateChangedListener k;

    public EasyGuide(Activity activity) {
        this(activity, (List<HighlightArea>) null, (List<TipsView>) null, (List<Message>) null, (Confirm) null, true, false);
    }

    public EasyGuide(Activity activity, List<HighlightArea> list, List<TipsView> list2, List<Message> list3, Confirm confirm, boolean z, boolean z2) {
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.f2558a = activity;
        this.e = list;
        this.f = list2;
        this.g = list3;
        this.h = confirm;
        this.i = z;
        this.j = z2;
        this.b = (FrameLayout) this.f2558a.getWindow().getDecorView();
    }

    public void a(OnStateChangedListener onStateChangedListener) {
        this.k = onStateChangedListener;
    }

    public void a() {
        this.c = new EasyGuideView(this.f2558a);
        this.c.setHightLightAreas(this.e);
        this.d = new LinearLayout(this.f2558a);
        this.d.setGravity(1);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.d.setOrientation(1);
        if (this.f != null) {
            for (TipsView next : this.f) {
                a(next.f2565a, next.c, next.d, next.e);
            }
        }
        if (this.g != null) {
            int a2 = a((Context) this.f2558a, 5.0f);
            for (Message next2 : this.g) {
                TextView textView = new TextView(this.f2558a);
                textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                textView.setPadding(a2, a2, a2, a2);
                textView.setGravity(17);
                textView.setText(next2.f2564a);
                textView.setTextColor(-1);
                textView.setTextSize(next2.b == -1 ? 12.0f : (float) next2.b);
                this.d.addView(textView);
            }
        }
        if (this.h != null) {
            TextView textView2 = new TextView(this.f2558a);
            textView2.setGravity(17);
            textView2.setText(this.h.f2562a);
            textView2.setTextColor(-1);
            textView2.setTextSize(this.h.b == -1 ? 13.0f : (float) this.h.b);
            textView2.setBackgroundResource(R.drawable.btn_selector);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.topMargin = a((Context) this.f2558a, 10.0f);
            textView2.setLayoutParams(layoutParams);
            int a3 = a((Context) this.f2558a, 8.0f);
            int a4 = a((Context) this.f2558a, 5.0f);
            textView2.setPadding(a3, a4, a3, a4);
            textView2.setOnClickListener(this.h.c != null ? this.h.c : new View.OnClickListener() {
                public void onClick(View view) {
                    EasyGuide.this.b();
                }
            });
            this.d.addView(textView2);
        }
        a(this.d, Integer.MAX_VALUE, Integer.MAX_VALUE, new RelativeLayout.LayoutParams(-1, -2));
        this.b.addView(this.c, new FrameLayout.LayoutParams(-1, -1));
        if (this.i || this.j) {
            this.c.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 1) {
                        return true;
                    }
                    if (EasyGuide.this.e.size() > 0) {
                        for (HighlightArea highlightArea : EasyGuide.this.e) {
                            View view2 = highlightArea.f2563a;
                            if (view2 != null && EasyGuide.this.a(view2, motionEvent)) {
                                EasyGuide.this.b();
                                if (EasyGuide.this.k != null) {
                                    EasyGuide.this.k.onHeightlightViewClick(view2);
                                }
                                if (EasyGuide.this.j) {
                                    view2.performClick();
                                }
                            } else if (EasyGuide.this.i) {
                                EasyGuide.this.b();
                            }
                        }
                        return false;
                    }
                    EasyGuide.this.b();
                    return false;
                }
            });
        }
        if (this.k != null) {
            this.k.onShow();
        }
    }

    public void b() {
        this.c.recyclerBitmap();
        if (this.b.indexOfChild(this.c) > 0) {
            this.b.removeView(this.c);
            if (this.k != null) {
                this.k.onDismiss();
            }
        }
    }

    private void a(View view, int i2, int i3, RelativeLayout.LayoutParams layoutParams) {
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        if (i2 == Integer.MAX_VALUE) {
            layoutParams.addRule(14, -1);
        } else if (i2 < 0) {
            layoutParams.addRule(11, -1);
            layoutParams.rightMargin = -i2;
        } else {
            layoutParams.leftMargin = i2;
        }
        if (i3 == Integer.MAX_VALUE) {
            layoutParams.addRule(15, -1);
        } else if (i3 < 0) {
            layoutParams.addRule(12, -1);
            layoutParams.bottomMargin = -i3;
        } else {
            layoutParams.topMargin = i3;
        }
        this.c.addView(view, layoutParams);
    }

    public boolean c() {
        return this.b.indexOfChild(this.c) > 0;
    }

    public boolean a(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        if (motionEvent.getX() < ((float) i2) || motionEvent.getX() > ((float) (i2 + view.getWidth())) || motionEvent.getY() < ((float) i3) || motionEvent.getY() > ((float) (i3 + view.getHeight()))) {
            return false;
        }
        return true;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Activity f2561a;
        List<HighlightArea> b = new ArrayList();
        List<TipsView> c = new ArrayList();
        List<Message> d = new ArrayList();
        Confirm e;
        boolean f = true;
        boolean g;

        public Builder(Activity activity) {
            this.f2561a = activity;
        }

        public Builder a(View view, @HShape int i) {
            this.b.add(new HighlightArea(view, i));
            return this;
        }

        public Builder a(HighlightArea highlightArea) {
            this.b.add(highlightArea);
            return this;
        }

        public Builder a(int i, int i2, int i3) {
            ImageView imageView = new ImageView(this.f2561a);
            imageView.setImageResource(i);
            this.c.add(new TipsView((View) imageView, i2, i3));
            return this;
        }

        public Builder a(View view, int i, int i2) {
            this.c.add(new TipsView(view, i, i2));
            return this;
        }

        public Builder a(View view, int i, int i2, RelativeLayout.LayoutParams layoutParams) {
            this.c.add(new TipsView(view, i, i2, layoutParams));
            return this;
        }

        public Builder a(String str, int i) {
            this.d.add(new Message(str, i));
            return this;
        }

        public Builder b(String str, int i) {
            this.e = new Confirm(str, i);
            return this;
        }

        public Builder a(String str, int i, View.OnClickListener onClickListener) {
            this.e = new Confirm(str, i, onClickListener);
            return this;
        }

        public Builder a(boolean z) {
            this.f = z;
            return this;
        }

        public Builder b(boolean z) {
            this.g = z;
            return this;
        }

        public EasyGuide a() {
            return new EasyGuide(this.f2561a, this.b, this.c, this.d, this.e, this.f, this.g);
        }
    }

    public static int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
