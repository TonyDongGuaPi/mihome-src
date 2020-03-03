package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import com.mi.global.bbs.utils.AnimUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.homeroom.DeviceListAssemble;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.newui.DropMenuAdapter;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.newui.widget.topnavi.RoomAdapter;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoomDropMenu {

    /* renamed from: a  reason: collision with root package name */
    static AnimUtils.IntProperty<View> f20957a = new AnimUtils.IntProperty<View>("height") {
        /* renamed from: a */
        public void setValue(View view, int i) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = i;
            view.setLayoutParams(layoutParams);
        }

        /* renamed from: a */
        public Integer get(View view) {
            return Integer.valueOf(view.getLayoutParams().height);
        }
    };
    private static final int b = 250;
    private static final String q = "RoomDropMenu";
    private static final Property<View, Float> t = new AnimUtils.FloatProperty<View>("alpha") {
        /* renamed from: a */
        public void setValue(View view, float f) {
            if (view != null) {
                view.setAlpha(f);
            }
        }

        /* renamed from: a */
        public Float get(View view) {
            if (view == null) {
                return Float.valueOf(0.0f);
            }
            return Float.valueOf(view.getAlpha());
        }
    };
    /* access modifiers changed from: private */
    public final int c;
    /* access modifiers changed from: private */
    public final View d;
    private Context e;
    /* access modifiers changed from: private */
    public ViewGroup f;
    /* access modifiers changed from: private */
    public List<PageBean.Classify> g;
    /* access modifiers changed from: private */
    public PageBean h;
    private View i;
    /* access modifiers changed from: private */
    public View j;
    /* access modifiers changed from: private */
    public View k;
    private View l;
    /* access modifiers changed from: private */
    public int[] m = new int[2];
    private BroadcastReceiver n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), DropMenuAdapter.f20253a)) {
                String stringExtra = intent.getStringExtra(DropMenuAdapter.c);
                if (!TextUtils.isEmpty(stringExtra)) {
                    for (PageBean.Classify classify : RoomDropMenu.this.g) {
                        Iterator<PageBean> it = classify.b.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PageBean next = it.next();
                            if (stringExtra.equals(next.f)) {
                                PageBean unused = RoomDropMenu.this.h = next;
                                break;
                            }
                        }
                    }
                }
            }
            try {
                RoomDropMenu.this.e();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private View o;
    private TextView p;
    private final ViewTreeObserver.OnGlobalLayoutListener r = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            try {
                RoomDropMenu.this.d.getLocationInWindow(RoomDropMenu.this.m);
                int e = RoomDropMenu.this.m[1] + RoomDropMenu.this.c;
                if (e - RoomDropMenu.this.c <= 0) {
                    RoomDropMenu.this.e();
                    return;
                }
                RoomDropMenu.this.k.setY((float) e);
                RoomDropMenu.this.j.setY((float) (e - RoomDropMenu.this.c));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };
    private boolean s = false;

    private RoomDropMenu(Context context, ViewGroup viewGroup, View view, List<PageBean.Classify> list, PageBean pageBean) {
        this.e = context;
        this.f = viewGroup;
        this.g = list;
        this.h = pageBean;
        this.d = view;
        this.c = this.d.getHeight();
        view.findViewById(R.id.arrow_down_img).getLocationInWindow(this.m);
        view.getViewTreeObserver().addOnGlobalLayoutListener(this.r);
        g();
        h();
    }

    public static RoomDropMenu a(Context context, ViewGroup viewGroup, View view, List<PageBean.Classify> list, PageBean pageBean) {
        if (list == null || pageBean == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (PageBean.Classify next : list) {
            arrayList.add(new PageBean.Classify(next.f20912a, new ArrayList(next.b)));
        }
        RoomDropMenu roomDropMenu = new RoomDropMenu(context, viewGroup, view, list, pageBean);
        roomDropMenu.a();
        return roomDropMenu;
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.l = LayoutInflater.from(this.e).inflate(R.layout.activity_room_menu, this.f, false);
        this.f.addView(this.l);
        d();
        f();
        LocalBroadcastManager.getInstance(this.e).registerReceiver(this.n, new IntentFilter(DropMenuAdapter.f20253a));
    }

    private void d() {
        this.k = this.l.findViewById(R.id.menu);
        this.i = this.l.findViewById(R.id.bg_mask);
        this.j = this.l.findViewById(R.id.erase_menu);
        this.p = (TextView) this.l.findViewById(R.id.room_name);
        int size = DeviceListAssemble.f17943a.a(this.h).size();
        this.p.setText(String.format("%s (%s)", new Object[]{this.h.e, Integer.valueOf(size)}));
        this.o = this.l.findViewById(R.id.arrow_down_img);
        RecyclerView recyclerView = (RecyclerView) this.l.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.e));
        recyclerView.setAdapter(new RoomAdapter(this.e, this.g, this.h));
        this.l.findViewById(R.id.container).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                RoomDropMenu.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        e();
    }

    /* access modifiers changed from: private */
    public void e() {
        if (!this.s) {
            this.s = true;
            int size = DeviceListAssemble.f17943a.a(this.h).size();
            this.p.setText(String.format("%s (%s)", new Object[]{this.h.e, Integer.valueOf(size)}));
            ViewPropertyAnimator startDelay = this.o.animate().rotation(0.0f).setDuration(125).setStartDelay(125);
            startDelay.setInterpolator(new AccelerateDecelerateInterpolator());
            startDelay.start();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.k, View.ALPHA, new float[]{1.0f, 0.0f});
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this.k, f20957a, new int[]{this.k.getMeasuredHeight(), this.c});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.i, View.ALPHA, new float[]{0.3f, 0.0f});
            ofFloat2.setDuration(200);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(250);
            animatorSet.setInterpolator(new LinearInterpolator());
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    RoomDropMenu.this.b();
                }
            });
            animatorSet.playTogether(new Animator[]{ofFloat, ofInt, ofFloat2});
            animatorSet.start();
            LocalBroadcastManager.getInstance(this.e).sendBroadcast(new Intent(DropMenuAdapter.b));
        }
    }

    private void f() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.i, t, new float[]{0.0f, 0.3f});
        ofFloat.setDuration(200);
        ofFloat.start();
        this.o.animate().rotation(180.0f).setDuration(125).setInterpolator(new AccelerateDecelerateInterpolator());
        this.f.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                RoomDropMenu.this.f.getViewTreeObserver().removeOnPreDrawListener(this);
                int e = RoomDropMenu.this.m[1] + RoomDropMenu.this.c;
                RoomDropMenu.this.k.setY((float) e);
                RoomDropMenu.this.j.setY((float) (e - RoomDropMenu.this.c));
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(RoomDropMenu.this.k, View.ALPHA, new float[]{0.2f, 1.0f});
                ObjectAnimator ofInt = ObjectAnimator.ofInt(RoomDropMenu.this.k, RoomDropMenu.f20957a, new int[]{RoomDropMenu.this.c, RoomDropMenu.this.k.getMeasuredHeight()});
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(250);
                animatorSet.setInterpolator(new DecelerateInterpolator());
                animatorSet.playTogether(new Animator[]{ofFloat, ofInt});
                animatorSet.start();
                return true;
            }
        });
    }

    public void b() {
        i();
        this.f.removeView(this.l);
        this.d.getViewTreeObserver().removeOnGlobalLayoutListener(this.r);
        LocalBroadcastManager.getInstance(this.e).unregisterReceiver(this.n);
    }

    public boolean c() {
        e();
        return true;
    }

    private void g() {
        STAT.d.e(1);
    }

    private void h() {
        try {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (PageBean.Classify next : this.g) {
                if (PageBean.f20911a.equals(next.f20912a)) {
                    for (PageBean next2 : next.b) {
                        if (HomeManager.e.equals(next2.f)) {
                            i3 = 1;
                        }
                        if (HomeManager.f.equals(next2.f)) {
                            i4 = 1;
                        }
                    }
                } else if (PageBean.d.equals(next.f20912a)) {
                    i2 = next.b.size();
                }
            }
            STAT.c.a(i2, i3, i4);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void i() {
        STAT.d.e(0);
    }
}
