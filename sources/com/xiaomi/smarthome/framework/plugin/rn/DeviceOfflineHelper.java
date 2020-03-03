package com.xiaomi.smarthome.framework.plugin.rn;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.TitleBarUtil;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import java.lang.reflect.Method;
import java.util.Locale;

public class DeviceOfflineHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17200a = "force_fsg_nav_bar";
    /* access modifiers changed from: private */
    public final DeviceStat b;
    /* access modifiers changed from: private */
    public final Activity c;
    private boolean d = false;
    /* access modifiers changed from: private */
    public ViewGroup e = null;
    private BroadcastReceiver f = null;

    public void c() {
    }

    public DeviceOfflineHelper(DeviceStat deviceStat, Activity activity) {
        this.b = deviceStat;
        this.c = activity;
    }

    public void a() {
        g();
    }

    public void b() {
        if (this.e != null && j() != this.d) {
            h();
            g();
        }
    }

    public void d() {
        f();
    }

    private void e() {
        if (this.f == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
            this.f = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals("com.xiaomi.smarthome.bluetooth.connect_status_changed", intent.getAction())) {
                        String stringExtra = intent.getStringExtra("key_device_address");
                        if (DeviceOfflineHelper.this.b != null && DeviceOfflineHelper.this.b.mac.equalsIgnoreCase(stringExtra) && intent.getIntExtra("key_connect_status", 5) == 16) {
                            if (DeviceOfflineHelper.this.e != null) {
                                DeviceOfflineHelper.this.h();
                            }
                            DeviceOfflineHelper.this.f();
                        }
                    }
                }
            };
            BluetoothUtils.a(this.f, intentFilter);
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.f != null) {
            BluetoothUtils.a(this.f);
            this.f = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        r0 = r0.c();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() {
        /*
            r5 = this;
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.b
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.b
            int r0 = r0.pid
            r1 = 6
            r2 = 1
            if (r0 != r1) goto L_0x0017
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.b
            java.lang.String r0 = r0.mac
            boolean r0 = com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils.c(r0)
        L_0x0015:
            r0 = r0 ^ r2
            goto L_0x001c
        L_0x0017:
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r5.b
            boolean r0 = r0.isOnline
            goto L_0x0015
        L_0x001c:
            if (r0 == 0) goto L_0x006c
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.device.api.DeviceStat r3 = r5.b
            java.lang.String r3 = r3.model
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r0.d((java.lang.String) r3)
            if (r0 != 0) goto L_0x002d
            return
        L_0x002d:
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r0 = r0.c()
            if (r0 != 0) goto L_0x0034
            return
        L_0x0034:
            com.xiaomi.smarthome.device.api.DeviceStat r3 = r5.b
            int r3 = r3.pid
            r4 = 7
            if (r3 != r4) goto L_0x003c
            goto L_0x006c
        L_0x003c:
            com.xiaomi.smarthome.device.api.DeviceStat r3 = r5.b
            int r3 = r3.pid
            if (r3 != r1) goto L_0x005a
            int r1 = r0.z()
            if (r1 == r2) goto L_0x006c
            int r1 = r0.z()
            r2 = 2
            if (r1 == r2) goto L_0x006c
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r5.b
            java.lang.String r1 = r1.model
            boolean r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.model.DeviceType.c(r1)
            if (r1 != 0) goto L_0x005a
            goto L_0x006c
        L_0x005a:
            android.app.Activity r1 = r5.c
            android.view.Window r1 = r1.getWindow()
            android.view.View r1 = r1.getDecorView()
            com.xiaomi.smarthome.framework.plugin.rn.DeviceOfflineHelper$2 r2 = new com.xiaomi.smarthome.framework.plugin.rn.DeviceOfflineHelper$2
            r2.<init>(r0)
            r1.post(r2)
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.DeviceOfflineHelper.g():void");
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        FrameLayout frameLayout = (FrameLayout) this.c.getWindow().getDecorView();
        final ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.c.getApplicationContext()).inflate(R.layout.device_offline_floating_view, (ViewGroup) null);
        TextView textView = (TextView) viewGroup.findViewById(R.id.tips);
        TextView textView2 = (TextView) viewGroup.findViewById(R.id.read_detail_tv);
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        StringUtil.a(this.c, I, (int) R.string.device_offline_tips, textView);
        StringUtil.a(this.c, I, (int) R.string.device_offline_tips_check_detail, textView2);
        if (i == 1 || i == 2) {
            viewGroup.setClickable(false);
        } else {
            viewGroup.setClickable(true);
            viewGroup.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
        }
        if (this.b.pid == 6) {
            textView2.setVisibility(8);
            SpannableStringBuilder a2 = StringUtil.a(this.c.getApplicationContext(), (int) R.string.device_unconnected_tips_underline, (int) R.string.device_unconnected_tips_tpl, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(Color.parseColor("#0bb58b"));
                    textPaint.setUnderlineText(true);
                }

                public void onClick(View view) {
                    XmPluginHostApi.instance().visualSecureBind(DeviceOfflineHelper.this.b.did);
                    DeviceOfflineHelper.this.c.finish();
                    StatHelper.i(DeviceOfflineHelper.this.b);
                }
            });
            textView.setHighlightColor(0);
            textView.setText(a2);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            viewGroup.findViewById(R.id.read_detail_tv).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClassName(DeviceOfflineHelper.this.c.getPackageName(), "com.xiaomi.smarthome.miio.activity.DeviceOfflineDetailActivity");
                    if (DeviceOfflineHelper.this.b != null) {
                        intent.putExtra("extra_model", DeviceOfflineHelper.this.b.model);
                    }
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    DeviceOfflineHelper.this.c.startActivity(intent);
                    StatHelper.h(DeviceOfflineHelper.this.b);
                }
            });
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        Resources resources = this.c.getApplicationContext().getResources();
        if (TitleBarUtil.TRANSLUCENT_STATUS_ENABLED) {
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.std_titlebar_height) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding);
        } else {
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.std_titlebar_height) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding) + resources.getDimensionPixelOffset(R.dimen.title_bar_top_padding);
        }
        this.d = j();
        if (this.d) {
            if (Build.VERSION.SDK_INT < 24 || !this.c.isInMultiWindowMode()) {
                layoutParams.bottomMargin = b((Context) this.c);
            } else {
                layoutParams.bottomMargin = 0;
            }
        }
        frameLayout.addView(viewGroup, layoutParams);
        if (i == 2) {
            viewGroup.findViewById(R.id.bottom_rl).setVisibility(8);
        } else {
            this.e = viewGroup;
            a(viewGroup.findViewById(R.id.bottom_rl));
            viewGroup.findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DeviceOfflineHelper.this.b(viewGroup.findViewById(R.id.bottom_rl));
                    StatHelper.g(DeviceOfflineHelper.this.b);
                    ViewGroup unused = DeviceOfflineHelper.this.e = null;
                }
            });
        }
        StatHelper.f(this.b);
        if (this.e != null) {
            e();
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.e != null) {
            try {
                ((FrameLayout) this.c.getWindow().getDecorView()).removeView(this.e);
                this.e = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private int b(Context context) {
        if (!a(context)) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static boolean a(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier == 0) {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        boolean z = resources.getBoolean(identifier);
        String i = i();
        if ("1".equals(i)) {
            return false;
        }
        if ("0".equals(i)) {
            return true;
        }
        return z;
    }

    private static String i() {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                return (String) declaredMethod.invoke((Object) null, new Object[]{"qemu.hw.mainkeys"});
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private boolean j() {
        if (Build.VERSION.SDK_INT >= 17 && Settings.Global.getInt(this.c.getContentResolver(), f17200a, 0) != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Display defaultDisplay = this.c.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y != point.y) {
                return true;
            }
            return false;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(this.c).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return false;
        }
        return true;
    }

    private void a(final View view) {
        if (view != null) {
            view.postDelayed(new Runnable() {
                public void run() {
                    view.setVisibility(0);
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    int bottom = viewGroup.getBottom() - viewGroup.getTop();
                    float f = DeviceOfflineHelper.this.c.getResources().getDisplayMetrics().density;
                    float f2 = (float) bottom;
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{f2, f2 - (f * 70.0f)});
                    ofFloat.setInterpolator(new TimeInterpolator() {
                        public float getInterpolation(float f) {
                            if (f <= 0.8f) {
                                double d = (double) f;
                                Double.isNaN(d);
                                return (float) (d * 1.5d);
                            }
                            double d2 = (double) f;
                            Double.isNaN(d2);
                            return (float) (2.0d - d2);
                        }
                    });
                    ofFloat.setDuration(600);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(ofFloat);
                    animatorSet.start();
                }
            }, 1000);
        }
    }

    /* access modifiers changed from: private */
    public void b(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        float f2 = this.c.getResources().getDisplayMetrics().density;
        int bottom = viewGroup.getBottom() - viewGroup.getTop();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{((float) bottom) - (f2 * 70.0f), (float) viewGroup.getHeight()});
        ofFloat.setInterpolator(new TimeInterpolator() {
            public float getInterpolation(float f) {
                if (f <= 0.2f) {
                    return -f;
                }
                double d = (double) f;
                Double.isNaN(d);
                return (float) ((d * 1.5d) - 0.5d);
            }
        });
        ofFloat.setDuration(600);
        ofFloat.start();
    }
}
