package com.xiaomi.smarthome.miio.ir;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.gesture.GestureOverlayView;
import android.graphics.PointF;
import android.hardware.ConsumerIrManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;

public class IRV2ControllerMiActivity extends BaseActivity implements GestureOverlayView.OnGestureListener, View.OnClickListener, View.OnLongClickListener {
    private static final int E = -1;
    private static final float F = 0.35f;
    private static float G = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    public static final int IRV2_DEVICE_USE_MODE_TOUCH = 0;
    public static final int IRV2_DEVICE_USE_MODE_TRADITIONAL = 1;
    public static final String[] IRV2_MI_CONTROLLER_KEYPAD = {CameraPropertyBase.l, "home", "menu", "back", "up", "down", "left", "right", "ok", "vol+", "vol-", CameraPropertyBase.l, "home", "menu", "back"};
    public static final int[] IRV2_MI_CONTROLLER_KEYPAD_VIEW = {R.id.irv2_controller_power, R.id.irv2_controller_home, R.id.irv2_controller_menu, R.id.irv2_controller_back, R.id.irv2_round_button_up, R.id.irv2_round_button_down, R.id.irv2_round_button_left, R.id.irv2_round_button_right, R.id.irv2_round_button_ok, R.id.irv2_controller_vol_add, R.id.irv2_controller_vol_minus, R.id.irv2_box_controller_power_adv, R.id.irv2_box_controller_home_adv, R.id.irv2_box_controller_menu_adv, R.id.irv2_box_controller_back_adv};

    /* renamed from: a  reason: collision with root package name */
    private static final String f1554a = "use_mode_shared_pref";
    private static final String b = "use_mode_shared_pref_value";
    private static final int j = 0;
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 3;
    private static final int n = 4;
    private static final int o = 10;
    private PointF A;
    private Handler B = new Handler();
    /* access modifiers changed from: private */
    public View C;
    private Runnable D;
    private VelocityTracker H;
    private int I = -1;
    private View J;
    private boolean K = false;
    /* access modifiers changed from: private */
    public int c = 0;
    private ArrayList<View> d = new ArrayList<>();
    private ArrayList<String> e = new ArrayList<>();
    private RadioButton f;
    private RadioButton g;
    private View h;
    private View i;
    private int p = 0;
    private boolean q = false;
    private boolean r = false;
    private PointF s;
    private PointF t;
    private View u;
    private GestureOverlayView v;
    private ImageView w;
    private GestureOverlayView x;
    private ImageView y;
    private int z = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_irv2_mi_controller);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.irv2_controller_mi_title);
        this.J = findViewById(R.id.module_a_3_return_btn);
        this.J.setOnClickListener(this);
        this.v = (GestureOverlayView) findViewById(R.id.irv2_touch_direction_view);
        this.v.setOnClickListener(this);
        this.v.setOnLongClickListener(this);
        this.v.setOrientation(0);
        this.v.addOnGestureListener(this);
        this.w = (ImageView) findViewById(R.id.irv2_touch_direction_hint_view);
        this.x = (GestureOverlayView) findViewById(R.id.irv2_touch_vol_view);
        this.x.setOrientation(0);
        this.x.addOnGestureListener(this);
        this.y = (ImageView) findViewById(R.id.irv2_touch_vol_hint_view);
        this.f = (RadioButton) findViewById(R.id.irv2_controller_switch_traditional);
        this.f.setOnClickListener(this);
        this.g = (RadioButton) findViewById(R.id.irv2_controller_switch_advance);
        this.g.setOnClickListener(this);
        this.h = findViewById(R.id.irv2_controller_traditional);
        this.i = findViewById(R.id.irv2_controller_advance);
        buildButtons(IRV2_MI_CONTROLLER_KEYPAD_VIEW);
        c();
    }

    public void onPause() {
        super.onPause();
        if (this.K) {
            b();
        }
    }

    /* access modifiers changed from: protected */
    public void buildButtons(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            View findViewById = findViewById(iArr[i2]);
            if (findViewById != null) {
                findViewById.setOnClickListener(this);
                this.d.add(findViewById);
                this.e.add(IRV2_MI_CONTROLLER_KEYPAD[i2]);
            }
        }
    }

    public void onGestureStarted(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        this.r = false;
        this.q = true;
        this.s = new PointF(motionEvent.getX(), motionEvent.getY());
        this.t = this.s;
        this.A = this.s;
        this.p = 0;
        this.u = gestureOverlayView;
        a(gestureOverlayView, motionEvent);
        a();
        if (this.H != null) {
            this.H.addMovement(motionEvent);
            this.I = motionEvent.getPointerId(0);
        }
    }

    public void onGesture(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        if (this.q) {
            if (a(motionEvent)) {
                if (gestureOverlayView == this.x) {
                    b(motionEvent);
                } else if (gestureOverlayView == this.v) {
                    a(true);
                }
            }
            if (this.H != null) {
                this.H.addMovement(motionEvent);
            }
        }
    }

    public void onGestureEnded(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        if (this.H != null) {
            this.H.addMovement(motionEvent);
            this.H.computeCurrentVelocity(1000, (float) ViewConfiguration.get(this).getScaledMaximumFlingVelocity());
        }
        if (this.q && a(motionEvent)) {
            this.q = false;
            if (this.p != 0) {
                this.r = true;
            }
            if (gestureOverlayView == this.v) {
                a(false);
                a((View) this.w);
            } else if (gestureOverlayView == this.x) {
                b(motionEvent);
                a((View) this.y);
            }
        }
        if (this.H != null) {
            this.H.clear();
            this.H.recycle();
            this.H = null;
        }
    }

    public void onGestureCancelled(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        this.q = false;
    }

    private boolean a(MotionEvent motionEvent) {
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        float f2 = pointF.x - this.t.x;
        float f3 = pointF.y - this.t.y;
        if (Math.sqrt((double) ((f2 * f2) + (f3 * f3))) < 10.0d) {
            return true;
        }
        int i2 = Math.abs(f2) > Math.abs(f3) ? f2 > 0.0f ? 4 : 3 : f3 > 0.0f ? 2 : 1;
        if (this.p == 0) {
            this.p = i2;
        }
        if (this.p != i2) {
            this.p = i2;
        }
        this.t = pointF;
        return true;
    }

    public void onClick(View view) {
        int i2 = 0;
        if (view == this.g) {
            if (this.c != 0) {
                a(this.f, R.string.irv2_controller_switch_traditional, this.g, R.string.irv2_controller_switch_advance_u, this.h, this.i);
                this.c = 0;
                this.K = true;
            }
        } else if (view == this.f) {
            if (this.c != 1) {
                a(this.g, R.string.irv2_controller_switch_advance, this.f, R.string.irv2_controller_switch_traditional_u, this.i, this.h);
                this.c = 1;
                this.K = true;
            }
        } else if (view == this.v) {
            if (!this.r) {
                sendKey(this, "ok");
            }
        } else if (view == this.J) {
            finish();
        } else {
            while (i2 < this.d.size() && view != this.d.get(i2)) {
                i2++;
            }
            if (i2 < this.d.size()) {
                sendKey(this, this.e.get(i2));
            }
        }
    }

    private void a(boolean z2) {
        String str = "";
        switch (this.p) {
            case 1:
                this.w.setImageResource(R.drawable.irv2_touch_direction_up);
                str = "up";
                break;
            case 2:
                this.w.setImageResource(R.drawable.irv2_touch_direction_down);
                str = "down";
                break;
            case 3:
                this.w.setImageResource(R.drawable.irv2_touch_direction_left);
                str = "left";
                break;
            case 4:
                this.w.setImageResource(R.drawable.irv2_touch_direction_right);
                str = "right";
                break;
        }
        if (!z2 && !str.isEmpty()) {
            sendKey(this, str);
        }
    }

    private void b(MotionEvent motionEvent) {
        if (this.z == 0) {
            this.z = this.x.getHeight() / 100;
        }
        int abs = (int) Math.abs(motionEvent.getY() - this.A.y);
        if (abs >= this.z) {
            this.A = new PointF(motionEvent.getX(), motionEvent.getY());
        }
        String str = "";
        switch (this.p) {
            case 1:
                this.y.setImageResource(R.drawable.irv2_touch_vol_up);
                str = "vol+";
                break;
            case 2:
                this.y.setImageResource(R.drawable.irv2_touch_vol_down);
                str = "vol-";
                break;
        }
        if (!str.isEmpty() && abs >= this.z) {
            sendKey(this, str);
            if (this.H != null) {
                int a2 = (((int) a((int) this.H.getYVelocity(this.I))) * 3) / getWindowManager().getDefaultDisplay().getHeight();
                for (int i2 = 0; i2 < a2; i2++) {
                    sendKey(this, str);
                }
            }
        }
    }

    @TargetApi(11)
    private void a(View view, MotionEvent motionEvent) {
        float x2 = motionEvent.getX() - ((float) (getResources().getDimensionPixelSize(view == this.x ? R.dimen.irv2_touch_hint_width : R.dimen.irv2_touch_hint_height) / 2));
        float y2 = motionEvent.getY() - ((float) (getResources().getDimensionPixelSize(R.dimen.irv2_touch_hint_height) / 2));
        ImageView imageView = view == this.x ? this.y : this.w;
        if (Build.VERSION.SDK_INT >= 11) {
            imageView.setX(x2);
            imageView.setY(y2);
        }
        imageView.setImageResource(view == this.x ? R.drawable.irv2_touch_vol_hint : R.drawable.irv2_touch_direction_hint);
        if (imageView.getVisibility() != 0) {
            imageView.setVisibility(0);
        }
        if (imageView == this.C) {
            this.B.removeCallbacks(this.D);
        }
    }

    private void a(final View view) {
        this.C = view;
        this.D = new Runnable() {
            public void run() {
                if (view.getVisibility() != 4) {
                    view.setVisibility(4);
                    View unused = IRV2ControllerMiActivity.this.C = null;
                }
            }
        };
        this.B.postDelayed(this.D, 300);
    }

    public boolean onLongClick(View view) {
        if (view != this.v) {
            return false;
        }
        this.q = false;
        a((View) this.w);
        sendKey(this, "back");
        return true;
    }

    private void a() {
        if (this.H == null) {
            this.H = VelocityTracker.obtain();
        } else {
            this.H.clear();
        }
    }

    private double a(int i2) {
        float scrollFriction = ViewConfiguration.getScrollFriction() * getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        double log = Math.log((double) ((((float) Math.abs(i2)) * F) / scrollFriction));
        double d2 = (double) G;
        Double.isNaN(d2);
        double d3 = (double) scrollFriction;
        double d4 = (double) G;
        Double.isNaN(d4);
        double exp = Math.exp((d4 / (d2 - 1.0d)) * log);
        Double.isNaN(d3);
        return d3 * exp;
    }

    private void a(RadioButton radioButton, int i2, RadioButton radioButton2, int i3, View view, View view2) {
        radioButton.setChecked(false);
        radioButton.setText(i2);
        radioButton2.setChecked(true);
        radioButton2.setText(i3);
        if (view.getVisibility() != 8) {
            view.setVisibility(8);
        }
        if (view2.getVisibility() != 0) {
            view2.setVisibility(0);
        }
    }

    public static String getCode(String str) {
        if (str.equals(CameraPropertyBase.l)) {
            return "Z6VIAEwCAADxAwAAvgUAAAsqAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABACAgACAAIAAgIDABACAgACAAIAAgIDABACAgACAAIAAgIDA=";
        }
        if (str.equals("left")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAC+BQAA7zAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAMEAQEFACMAAQMAAAMEAQEFACMAAQMAAAMEAQEFA=";
        }
        if (str.equals("right")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAC+BQAAOzMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAQAAAMFACMAAQMAAAQAAAMFACMAAQMAAAQAAAMFA=";
        }
        if (str.equals("up")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAC+BQAArTYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAEBAwQFACMAAQMAAAEBAwQFACMAAQMAAAEBAwQFA=";
        }
        if (str.equals("down")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAA7MwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAEDAwAEACMAAQMAAAEDAwAEACMAAQMAAAEDAwAEA=";
        }
        if (str.equals("ok")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAC+BQAA7zAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAQBAAQFACMAAQMAAAQBAAQFACMAAQMAAAQBAAQFA=";
        }
        if (str.equals("menu")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAA7MwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAEAAwMEACMAAQMAAAEAAwMEACMAAQMAAAEAAwMEA= ";
        }
        if (str.equals("home")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAA7MwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAMAAQMEACMAAQMAAAMAAQMEACMAAQMAAAMAAQMEA=";
        }
        if (str.equals("back")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAC+BQAA7zAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAEEAwEFACMAAQMAAAEEAwEFACMAAQMAAAEEAwEFA=";
        }
        if (str.equals("vol+")) {
            return "Z6VIAEwCAAByAwAA8QMAAJgEAAC+BQAAOzMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAQDAAAFACMAAQMAAAQDAAAFACMAAQMAAAQDAAAFA=";
        }
        return str.equals("vol-") ? "Z6VIAEwCAAByAwAA8QMAAJgEAAC+BQAA7zAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACMAAQMAAAQEAAEFACMAAQMAAAQEAAEFACMAAQMAAAQEAAEFA=" : "";
    }

    public static void sendKey(Context context, String str) {
        ((ConsumerIrManager) context.getSystemService("consumer_ir")).transmit(37920, IRV2Codec.a(getCode(str)));
    }

    private void b() {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(IRV2ControllerMiActivity.f1554a, 0).edit();
                edit.putInt(IRV2ControllerMiActivity.b, IRV2ControllerMiActivity.this.c);
                edit.apply();
                return null;
            }
        }.execute(new Void[0]);
    }

    private void c() {
        new AsyncTask<Void, Void, Integer>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Integer doInBackground(Void... voidArr) {
                return Integer.valueOf(SHApplication.getAppContext().getSharedPreferences(IRV2ControllerMiActivity.f1554a, 0).getInt(IRV2ControllerMiActivity.b, 0));
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Integer num) {
                IRV2ControllerMiActivity.this.b(num.intValue());
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        if (!isFinishing() && this.c != i2) {
            if (this.c == 0) {
                a(this.g, R.string.irv2_controller_switch_advance, this.f, R.string.irv2_controller_switch_traditional_u, this.i, this.h);
            } else {
                a(this.f, R.string.irv2_controller_switch_traditional, this.g, R.string.irv2_controller_switch_advance_u, this.h, this.i);
            }
            this.c = i2;
        }
    }
}
