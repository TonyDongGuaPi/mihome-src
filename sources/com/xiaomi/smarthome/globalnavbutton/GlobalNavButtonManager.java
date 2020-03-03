package com.xiaomi.smarthome.globalnavbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class GlobalNavButtonManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17906a = "identity_xiaoai";
    private static final String b = "GlobalNavButtonManager";
    private static volatile GlobalNavButtonManager c = null;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private String d;
    /* access modifiers changed from: private */
    public Map<String, WeakReference<View>> e = new HashMap();
    private Handler i = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    GlobalNavButtonManager.this.d();
                    return;
                case 2:
                    GlobalNavButtonManager.this.b((String) message.obj);
                    return;
                default:
                    return;
            }
        }
    };

    private GlobalNavButtonManager() {
    }

    public static GlobalNavButtonManager a() {
        if (c == null) {
            synchronized (GlobalNavButtonManager.class) {
                if (c == null) {
                    c = new GlobalNavButtonManager();
                }
            }
        }
        return c;
    }

    public void a(String str) {
        LogUtil.a(b, "updateFloatNavButton " + str);
        if (c(str)) {
            this.i.sendMessage(this.i.obtainMessage(2, str));
        }
    }

    private static boolean c(String str) {
        return TextUtils.equals(f17906a, str) ? true : true;
    }

    @UiThread
    public void b(String str) {
        LogUtil.a(b, "doUpdateFloatNavButton");
        if (!TextUtils.isEmpty(this.d) && !TextUtils.equals(str, this.d)) {
            b();
        }
        this.d = str;
    }

    public void b() {
        LogUtil.a(b, "clear");
        if (GlobalSetting.q) {
            LogUtil.f(b, "");
        }
        this.i.sendEmptyMessage(1);
    }

    /* access modifiers changed from: private */
    @UiThread
    public void d() {
        View view;
        ViewParent parent;
        LogUtil.a(b, "doClear");
        this.d = null;
        for (WeakReference next : this.e.values()) {
            if (!(next == null || (view = (View) next.get()) == null || (parent = view.getParent()) == null)) {
                ((ViewGroup) parent).removeView(view);
            }
        }
        this.e.clear();
    }

    public boolean c() {
        LogUtil.a(b, "needShowNavButton");
        return !TextUtils.isEmpty(this.d);
    }

    public View a(Activity activity, final String str) {
        LogUtil.a(b, "createViewIfNeed " + str);
        if (TextUtils.isEmpty(this.d) || !TextUtils.equals(this.d, f17906a)) {
            return null;
        }
        View a2 = a(activity);
        final WeakReference weakReference = new WeakReference(a2);
        this.i.post(new Runnable() {
            public void run() {
                GlobalNavButtonManager.this.e.put(str, weakReference);
            }
        });
        return a2;
    }

    private static View a(Activity activity) {
        LogUtil.a(b, "createXiaoAiNavBtn");
        View inflate = LayoutInflater.from(activity).inflate(R.layout.global_nav_btn_layout, (ViewGroup) null);
        ((ImageView) inflate.findViewById(R.id.icon)).setImageResource(R.drawable.cloud_device_xiaoai_icon);
        return inflate;
    }

    public static void a(Activity activity, View view) {
        Window window;
        View decorView;
        View findViewById;
        LogUtil.a(b, "addNavButtonToActivity");
        if (!activity.isFinishing()) {
            if ((Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) && (window = activity.getWindow()) != null && (decorView = window.getDecorView()) != null && (findViewById = decorView.findViewById(16908290)) != null && (findViewById instanceof FrameLayout)) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.topMargin = (int) (((float) activity.getResources().getDisplayMetrics().heightPixels) * 0.43f);
                layoutParams.height = DisplayUtils.a(36.0f);
                ((FrameLayout) findViewById).addView(view, layoutParams);
                view.setClickable(true);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ActivityCompat.finishAffinity((Activity) view.getContext());
                        GlobalNavButtonManager.a().b();
                    }
                });
            }
        }
    }

    private static class TouchListener implements View.OnTouchListener {

        /* renamed from: a  reason: collision with root package name */
        private float f17909a;
        private float b;
        private long c;
        private long d;
        private ObjectAnimator e;
        private boolean f = true;
        /* access modifiers changed from: private */
        public float g;

        private TouchListener() {
        }

        public boolean onTouch(final View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f17909a = motionEvent.getRawX();
                    this.b = motionEvent.getRawY();
                    this.f = true;
                    break;
                case 1:
                    this.e = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{view.getTranslationX(), 0.0f});
                    this.e.setDuration(300);
                    this.e.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            view.layout(view.getLeft(), (int) (((float) view.getTop()) - TouchListener.this.g), view.getRight(), (int) (((float) view.getBottom()) - TouchListener.this.g));
                        }
                    });
                    this.e.start();
                    if (this.f) {
                        ActivityCompat.finishAffinity((Activity) view.getContext());
                        GlobalNavButtonManager.a().b();
                        break;
                    }
                    break;
                case 2:
                    float rawX = motionEvent.getRawX() - this.f17909a;
                    float rawY = motionEvent.getRawY() - this.b;
                    view.setTranslationX(rawX);
                    view.setTranslationY(rawY);
                    if (this.f && Math.sqrt((double) ((Math.abs(rawX) * Math.abs(rawX)) + (Math.abs(rawY) * Math.abs(rawY)))) > ((double) ViewConfiguration.getTouchSlop())) {
                        this.f = false;
                    }
                    this.g = rawY;
                    break;
            }
            return false;
        }
    }
}
