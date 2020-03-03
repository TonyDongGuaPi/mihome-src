package com.xiaomi.smarthome.newui.widget.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.util.i;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.homeroom.DeviceInitChecker;
import com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity;
import com.xiaomi.smarthome.homeroom.ManageDeviceRoomActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.widget.guide.drawable.BubbleDrawable;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;

public class GBRightCloseV2 extends GuideBubble implements Animation.AnimationListener {
    private WeakReference<Activity> c;
    private ViewGroup d;
    private View e;
    private BubbleDrawable f;
    private TextView g;
    private ImageView h;
    private PointF i;
    private PointF j;
    private Animation k;
    private Animation l;
    private boolean m = true;
    private boolean n = true;
    private int o;
    private String p;

    /* access modifiers changed from: protected */
    public long e() {
        return 200;
    }

    public boolean k() {
        return false;
    }

    public GBRightCloseV2(Activity activity, String str) {
        this.c = new WeakReference<>(activity);
        this.k = new AlphaAnimation(0.0f, 1.0f);
        this.k.setAnimationListener(this);
        this.k.setDuration(400);
        this.l = new AlphaAnimation(1.0f, 0.0f);
        this.l.setAnimationListener(this);
        this.l.setDuration(200);
        this.p = str;
    }

    public void a(ViewGroup viewGroup, PointF pointF, int i2) {
        ViewGroup.LayoutParams layoutParams;
        Activity activity = (Activity) this.c.get();
        if (activity != null) {
            if (viewGroup instanceof FrameLayout) {
                layoutParams = new FrameLayout.LayoutParams(-2, DisplayUtils.a(46.0f), i2);
                ((FrameLayout.LayoutParams) layoutParams).bottomMargin = DisplayUtils.a(46.0f);
            } else {
                layoutParams = new ViewGroup.LayoutParams(-2, DisplayUtils.a(46.0f));
            }
            this.j = pointF;
            this.d = viewGroup;
            this.e = LayoutInflater.from(activity).inflate(R.layout.guide_bubble_right_close_v2, (ViewGroup) null);
            this.d.addView(this.e, layoutParams);
            this.o = a(pointF, i2);
            a(this.e);
            a(i2);
            this.e.setVisibility(4);
        }
    }

    private int a(PointF pointF, int i2) {
        Activity activity = (Activity) this.c.get();
        if (activity == null) {
            return 0;
        }
        Point a2 = DisplayUtils.a(activity);
        float f2 = pointF.x > ((float) (a2.x / 2)) ? ((float) a2.x) - pointF.x : pointF.x;
        float a3 = (float) DisplayUtils.a((Context) activity, 10.0f);
        if (i2 == 80 || i2 == 48) {
            return (int) ((f2 * 2.0f) - (a3 * 2.0f));
        }
        if (i2 == 85) {
            return (int) Math.min(((float) a2.x) - (2.0f * a3), (f2 - a3) * 4.0f);
        }
        if (i2 == 51) {
            return (int) (((float) DisplayUtils.a(activity).x) - (pointF.x * 2.0f));
        }
        return (int) ((((float) a2.x) - pointF.x) - a3);
    }

    private void a(View view) {
        final Activity activity = (Activity) this.c.get();
        if (activity != null) {
            this.g = (TextView) view.findViewById(R.id.tv_guide);
            this.h = (ImageView) view.findViewById(R.id.close);
            this.g.setText(this.p);
            this.g.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    List<String> list = DeviceInitChecker.f17939a;
                    if (list != null && !list.isEmpty()) {
                        GBRightCloseV2.r();
                        if (list.size() == 1) {
                            Intent intent = new Intent(activity, ManageDeviceRoomActivity.class);
                            intent.putExtra("device_id", list.get(0));
                            intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            activity.startActivity(intent);
                            return;
                        }
                        Intent intent2 = new Intent(activity, DeviceUninitedListActivity.class);
                        intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                        activity.startActivity(intent2);
                    }
                }
            });
            this.h.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GBRightCloseV2.this.b();
                    DeviceInitChecker.a((AsyncCallback) null);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void r() {
        try {
            List<String> list = DeviceInitChecker.f17939a;
            HashSet<String> hashSet = new HashSet<>();
            for (int i2 = 0; i2 < list.size(); i2++) {
                Device b = SmartHomeDeviceManager.a().b(list.get(i2));
                if (b != null) {
                    hashSet.add(b.model);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String str : hashSet) {
                sb.append(str + i.b);
            }
            STAT.d.b(sb.toString(), list.size());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(int i2) {
        Activity activity = (Activity) this.c.get();
        if (activity != null) {
            this.f = new BubbleDrawable(activity);
            if (k()) {
                this.f.a(i2);
                this.f.c(DisplayUtils.a(3.0f));
                a(this.e, i2, this.f.c());
                this.e.setBackgroundDrawable(this.f);
            }
        }
    }

    private void a(View view, int i2, int i3) {
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        if (i2 == 3) {
            view.setPadding(paddingLeft + i3, paddingTop, paddingRight, paddingBottom);
        } else if (i2 == 5) {
            view.setPadding(paddingLeft, paddingTop, paddingRight + i3, paddingBottom);
        } else if (i2 == 48 || i2 == 51) {
            view.setPadding(paddingLeft, paddingTop + i3, paddingRight, paddingBottom);
        } else if (i2 == 80 || i2 == 85) {
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom + i3);
        }
    }

    public void a() {
        g();
        this.e.setVisibility(0);
        this.d.setVisibility(0);
        this.d.startAnimation(this.k);
        this.f.setAlpha(153);
    }

    public void b() {
        this.e.setVisibility(4);
        this.d.setVisibility(4);
        this.d.startAnimation(this.l);
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public PointF f() {
        if (this.i == null) {
            this.i = new PointF();
        }
        this.i.set(this.j.x, this.j.y);
        return this.i;
    }

    public void g() {
        PointF f2 = f();
        this.e.setTranslationX(f2.x);
        this.e.setTranslationY(f2.y);
    }

    public BubbleDrawable h() {
        this.f.setBounds(0, 0, i(), j());
        return this.f;
    }

    /* access modifiers changed from: protected */
    public int i() {
        return this.e.getWidth();
    }

    /* access modifiers changed from: protected */
    public int j() {
        return this.e.getHeight();
    }

    public void onAnimationStart(Animation animation) {
        if (animation == this.l) {
            this.n = false;
            if (o() != null) {
                o().onAnimationStart(animation);
            }
        } else if (animation == this.k) {
            this.m = false;
            if (p() != null) {
                p().onAnimationStart(animation);
            }
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.l) {
            this.n = true;
            if (o() != null) {
                o().onAnimationEnd(animation);
            }
            a((Animation.AnimationListener) null);
        } else if (animation == this.k) {
            this.m = true;
            if (p() != null) {
                p().onAnimationEnd(animation);
            }
            b((Animation.AnimationListener) null);
        }
    }

    public void onAnimationRepeat(Animation animation) {
        if (o() != null) {
            o().onAnimationRepeat(animation);
        }
        if (p() != null) {
            p().onAnimationRepeat(animation);
        }
    }
}
