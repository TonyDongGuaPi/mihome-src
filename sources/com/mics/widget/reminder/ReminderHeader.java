package com.mics.widget.reminder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderHeader {

    /* renamed from: a  reason: collision with root package name */
    static final int f7836a = 201800;
    static final int b = 201802;
    static final int c = 201804;
    static final int d = 201805;
    private static volatile ReminderHeader e;
    private OnHideStatusChangedListener f;
    /* access modifiers changed from: private */
    public Timer g;
    /* access modifiers changed from: private */
    public final Handler h = new Handler(Looper.getMainLooper());

    public interface OnHideStatusChangedListener {
        void a();
    }

    private static ReminderHeader a() {
        if (e == null) {
            synchronized (ReminderHeader.class) {
                if (e == null) {
                    e = new ReminderHeader();
                }
            }
        }
        return e;
    }

    static View a(Context context) {
        return a().b(context);
    }

    static void a(View view) {
        a().c(view);
    }

    private void c(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(f7836a);
        if (relativeLayout != null) {
            relativeLayout.setY(0.0f);
            if (relativeLayout.getVisibility() == 8) {
                TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
                translateAnimation.setDuration(200);
                translateAnimation.setInterpolator(new DecelerateInterpolator());
                relativeLayout.setVisibility(0);
                relativeLayout.startAnimation(translateAnimation);
            }
            if (this.g != null) {
                this.g.cancel();
                this.g = null;
            }
            this.g = new Timer();
            this.g.schedule(new Task(view), 4200);
        }
    }

    private class Task extends TimerTask {
        /* access modifiers changed from: private */
        public WeakReference<View> b;

        Task(View view) {
            this.b = new WeakReference<>(view);
        }

        public void run() {
            ReminderHeader.this.h.post(new Runnable() {
                public void run() {
                    ReminderHeader.d((View) Task.this.b.get());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void d(View view) {
        b(view, 1.0f, 0);
    }

    static void a(View view, long j) {
        b(view, 1.0f, j);
    }

    /* access modifiers changed from: private */
    public static void b(View view, float f2, long j) {
        RelativeLayout relativeLayout;
        if (view != null && (relativeLayout = (RelativeLayout) view.findViewById(f7836a)) != null && relativeLayout.getVisibility() == 0) {
            if (a().f != null) {
                a().f.a();
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -f2);
            translateAnimation.setDuration((long) (f2 * 300.0f));
            translateAnimation.setInterpolator(new AccelerateInterpolator());
            translateAnimation.setStartOffset(j);
            relativeLayout.setVisibility(8);
            relativeLayout.startAnimation(translateAnimation);
        }
    }

    public static void a(OnHideStatusChangedListener onHideStatusChangedListener) {
        a().f = onHideStatusChangedListener;
    }

    private View b(Context context) {
        View findViewById;
        if ((context instanceof Activity) && (findViewById = ((Activity) context).findViewById(f7836a)) != null) {
            return findViewById;
        }
        HeaderLayout headerLayout = new HeaderLayout(context);
        headerLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) a(context, 86.0f));
        float a2 = a(context, 5.0f);
        float a3 = a(context, 6.0f);
        int i = (int) a2;
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i;
        layoutParams.bottomMargin = (int) a3;
        relativeLayout.setLayoutParams(layoutParams);
        if (ReminderResource.b() != null) {
            relativeLayout.setBackground(ReminderResource.b());
        }
        headerLayout.setId(f7836a);
        headerLayout.addView(relativeLayout);
        float a4 = a(context, 30.0f);
        View view = new View(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) a4);
        layoutParams2.leftMargin = 11;
        layoutParams2.rightMargin = 11;
        layoutParams2.topMargin = 10;
        view.setLayoutParams(layoutParams2);
        if (ReminderResource.c() != null) {
            view.setBackground(ReminderResource.c());
        }
        view.setId(201801);
        relativeLayout.addView(view);
        float a5 = a(context, 20.0f);
        float a6 = a(context, 10.0f);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        int i2 = (int) a5;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i2, i2);
        layoutParams3.addRule(6, view.getId());
        layoutParams3.addRule(5, view.getId());
        int i3 = (int) a6;
        layoutParams3.leftMargin = i3;
        layoutParams3.rightMargin = i3;
        layoutParams3.topMargin = (int) a(context, 5.0f);
        simpleDraweeView.setLayoutParams(layoutParams3);
        simpleDraweeView.setId(b);
        relativeLayout.addView(simpleDraweeView);
        GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) simpleDraweeView.getHierarchy();
        genericDraweeHierarchy.setRoundingParams(RoundingParams.asCircle());
        genericDraweeHierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
        genericDraweeHierarchy.setPlaceholderImage(ReminderResource.a(), ScalingUtils.ScaleType.CENTER_CROP);
        genericDraweeHierarchy.setFailureImage(ReminderResource.a(), ScalingUtils.ScaleType.CENTER_CROP);
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(6, simpleDraweeView.getId());
        layoutParams4.addRule(8, simpleDraweeView.getId());
        layoutParams4.addRule(7, view.getId());
        layoutParams4.leftMargin = i3;
        layoutParams4.rightMargin = i3;
        textView.setTextSize(1, 12.0f);
        textView.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView.setGravity(16);
        textView.setText("点击进入");
        textView.setLayoutParams(layoutParams4);
        textView.setId(201803);
        relativeLayout.addView(textView);
        TextView textView2 = new TextView(context);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(6, simpleDraweeView.getId());
        layoutParams5.addRule(8, simpleDraweeView.getId());
        layoutParams5.addRule(1, simpleDraweeView.getId());
        layoutParams5.addRule(0, textView.getId());
        textView2.setTextSize(1, 12.0f);
        textView2.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        textView2.setGravity(16);
        textView2.setLines(1);
        textView2.setLayoutParams(layoutParams5);
        textView2.setId(c);
        relativeLayout.addView(textView2);
        TextView textView3 = new TextView(context);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, (int) a(context, 54.0f));
        layoutParams6.addRule(12, -1);
        textView3.setTextSize(1, 13.0f);
        textView3.setTextColor(Color.parseColor("#333333"));
        textView3.setGravity(16);
        textView3.setLines(1);
        textView3.setLayoutParams(layoutParams6);
        int a7 = (int) a(context, 14.0f);
        textView3.setPadding(a7, 0, a7, (int) a(context, 4.0f));
        textView3.setId(d);
        relativeLayout.addView(textView3);
        return headerLayout;
    }

    private float a(Context context, float f2) {
        return TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    private class HeaderLayout extends RelativeLayout {
        private float b;
        private GestureDetector c;
        private View.OnClickListener d;

        public HeaderLayout(Context context) {
            super(context);
            a(context);
        }

        private void a(Context context) {
            this.c = new GestureDetector(context, new SingleTapConfirm());
        }

        public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
            this.d = onClickListener;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!this.c.onTouchEvent(motionEvent)) {
                a(motionEvent);
                return true;
            } else if (this.d == null) {
                return true;
            } else {
                this.d.onClick(this);
                return true;
            }
        }

        private void a(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                this.b = motionEvent.getRawY();
                if (ReminderHeader.this.g != null) {
                    ReminderHeader.this.g.cancel();
                    Timer unused = ReminderHeader.this.g = null;
                }
            } else if (motionEvent.getAction() == 2) {
                float y = getY() + (motionEvent.getRawY() - this.b);
                if (y <= 0.0f) {
                    setY(y);
                }
                this.b = motionEvent.getRawY();
            } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3 || motionEvent.getAction() == 4) {
                if (ReminderHeader.this.g != null) {
                    ReminderHeader.this.g.cancel();
                    Timer unused2 = ReminderHeader.this.g = null;
                }
                float abs = Math.abs((((float) getHeight()) + getY()) / ((float) getHeight()));
                if (abs > 1.0f) {
                    abs = 1.0f;
                }
                ReminderHeader.b(this, abs, 0);
            }
        }
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }

        private SingleTapConfirm() {
        }
    }
}
