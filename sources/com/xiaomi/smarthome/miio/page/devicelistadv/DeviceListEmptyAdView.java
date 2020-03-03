package com.xiaomi.smarthome.miio.page.devicelistadv;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.newui.MyScaleAnimation;
import com.xiaomi.smarthome.operation.LogInfo;
import com.xiaomi.smarthome.operation.Operation;
import com.xiaomi.smarthome.operation.OperationRoute;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeviceListEmptyAdView extends LinearLayout {
    private static final String q = "DeviceListEmptyAdView";

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f19702a;
    private View b;
    private View c;
    private View d;
    private CardView e;
    private TextView f;
    private TextView g;
    private CardView h;
    private CardView i;
    private SimpleDraweeView j;
    private SimpleDraweeView k;
    private TextView l;
    /* access modifiers changed from: private */
    public boolean m = false;
    Subject<Integer> mSubject = null;
    /* access modifiers changed from: private */
    public Context n;
    private View o;
    private LogInfo[] p = new LogInfo[3];

    public DeviceListEmptyAdView(Context context) {
        super(context);
        this.n = context;
        a();
    }

    public DeviceListEmptyAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = context;
        a();
    }

    public DeviceListEmptyAdView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.n = context;
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.device_list_recommend_adv, this);
        this.f = (TextView) inflate.findViewById(R.id.banner_recommend_title);
        this.g = (TextView) inflate.findViewById(R.id.banner_life_tips_title);
        this.f19702a = (SimpleDraweeView) inflate.findViewById(R.id.mijia_intro_iv);
        this.e = (CardView) inflate.findViewById(R.id.mijia_intro_container);
        this.b = inflate.findViewById(R.id.add_device);
        this.l = (TextView) inflate.findViewById(R.id.add_device_btn);
        this.o = inflate.findViewById(R.id.divider_line);
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseDeviceActivity.openChooseDevice(DeviceListEmptyAdView.this.getContext());
                STAT.d.aj();
            }
        });
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.image);
        if (DarkModeCompat.a(getContext())) {
            simpleDraweeView.setImageResource(R.drawable.empty_device_icon_ad_bander_dark);
        }
        this.c = inflate.findViewById(R.id.login);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceListEmptyAdView.this.a(view, new Runnable() {
                    public void run() {
                        LoginApi.a().a(DeviceListEmptyAdView.this.getContext(), 1, (LoginApi.LoginCallback) null);
                    }
                });
            }
        });
        this.d = inflate.findViewById(R.id.device_ir);
        this.h = (CardView) inflate.findViewById(R.id.banner_life_tips_left);
        this.i = (CardView) inflate.findViewById(R.id.banner_life_tips_right);
        this.j = (SimpleDraweeView) inflate.findViewById(R.id.banner_life_tips_left_img);
        this.k = (SimpleDraweeView) inflate.findViewById(R.id.banner_life_tips_right_img);
        ((LinearLayout.LayoutParams) this.d.getLayoutParams()).width = (getResources().getDisplayMetrics().widthPixels / 2) - DisplayUtils.b(this.n, 5.0f);
        if (Build.VERSION.SDK_INT < 21) {
            View findViewById = inflate.findViewById(R.id.ad_banner);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.height = DisplayUtils.b(this.n, 450.0f);
            findViewById.setLayoutParams(layoutParams);
            this.e.setContentPadding(-10, -10, -10, -10);
            this.h.setContentPadding(-10, -10, -10, -10);
            this.i.setContentPadding(-10, -10, -10, -10);
        }
    }

    /* access modifiers changed from: private */
    public void a(View view, final Runnable runnable) {
        MyScaleAnimation myScaleAnimation = new MyScaleAnimation(0.9f, 1, 0.5f, 1, 0.5f);
        myScaleAnimation.setDuration(360);
        myScaleAnimation.setInterpolator(new LinearInterpolator());
        myScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                runnable.run();
            }
        });
        view.startAnimation(myScaleAnimation);
    }

    public void updateIrOrEmptyView(boolean z, Device device) {
        int i2 = 0;
        if (z || device == null) {
            this.d.setVisibility(8);
            if (SHApplication.getStateNotifier().a() == 3) {
                this.c.setVisibility(0);
                this.b.setVisibility(8);
                return;
            }
            this.c.setVisibility(8);
            this.b.setVisibility(0);
            Home m2 = HomeManager.a().m();
            if (m2 != null) {
                TextView textView = this.l;
                if (!m2.p()) {
                    i2 = 8;
                }
                textView.setVisibility(i2);
                return;
            }
            return;
        }
        a(device);
        this.d.setVisibility(0);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
    }

    private void a(final Device device) {
        this.d.findViewById(R.id.grid_anim_view).setVisibility(8);
        this.d.findViewById(R.id.shortcut_container).setVisibility(8);
        this.d.findViewById(R.id.tv_device_state_split).setVisibility(0);
        final TextView textView = (TextView) this.d.findViewById(R.id.tv_device_state);
        textView.setVisibility(0);
        TextView textView2 = (TextView) this.d.findViewById(R.id.tv_room_name);
        TextView textView3 = (TextView) this.d.findViewById(R.id.tv_device_name);
        textView2.setText(R.string.phone);
        textView2.setVisibility(0);
        textView3.setText(R.string.phone_ir_device);
        ((SimpleDraweeView) this.d.findViewById(R.id.icon)).setImageResource(R.drawable.device_icon_ir_nor);
        ((CheckBox) this.d.findViewById(R.id.ckb_edit_selected)).setVisibility(8);
        ((ImageView) this.d.findViewById(R.id.isnew)).setVisibility(8);
        textView3.setTextColor(getResources().getColor(R.color.device_normal_title_color));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView2.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.weight = 0.0f;
            textView2.requestLayout();
        }
        int b2 = IRDeviceUtil.b(this.n, (IRDeviceUtil.IRDeviceListener) new IRDeviceUtil.IRDeviceListener() {
            public void onQueryComplete(List<IRRemoteInfo> list) {
                if (DeviceListEmptyAdView.this.n != null) {
                    if (!(DeviceListEmptyAdView.this.n instanceof BaseActivity) || !((BaseActivity) DeviceListEmptyAdView.this.n).isValid()) {
                        if (DeviceListEmptyAdView.this.n instanceof Activity) {
                            if (((Activity) DeviceListEmptyAdView.this.n).isFinishing()) {
                                return;
                            }
                            if (Build.VERSION.SDK_INT >= 17 && ((Activity) DeviceListEmptyAdView.this.n).isDestroyed()) {
                                return;
                            }
                        }
                        final int c = IRDeviceUtil.c(DeviceListEmptyAdView.this.n);
                        textView.post(new Runnable() {
                            public void run() {
                                String quantityString = DeviceListEmptyAdView.this.n.getResources().getQuantityString(R.plurals.ir_device_count_2, c, new Object[]{Integer.valueOf(c)});
                                textView.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(c)}));
                            }
                        });
                        return;
                    }
                    final int c2 = IRDeviceUtil.c(DeviceListEmptyAdView.this.n);
                    textView.post(new Runnable() {
                        public void run() {
                            String quantityString = DeviceListEmptyAdView.this.n.getResources().getQuantityString(R.plurals.ir_device_count_2, c2, new Object[]{Integer.valueOf(c2)});
                            textView.setText(String.format("%s%d", new Object[]{quantityString, Integer.valueOf(c2)}));
                        }
                    });
                }
            }
        });
        textView.setText(String.format("%s%d", new Object[]{this.n.getResources().getQuantityString(R.plurals.ir_device_count_2, b2, new Object[]{Integer.valueOf(b2)}), Integer.valueOf(b2)}));
        this.d.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:2:0x002d, code lost:
                r0 = com.xiaomi.smarthome.frame.core.CoreApi.a().d(r10.model);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r9) {
                /*
                    r8 = this;
                    r0 = 2
                    int[] r0 = new int[r0]
                    r9.getLocationOnScreen(r0)
                    android.graphics.RectF r1 = new android.graphics.RectF
                    r2 = 0
                    r3 = r0[r2]
                    float r3 = (float) r3
                    r4 = 1
                    r5 = r0[r4]
                    float r5 = (float) r5
                    r6 = r0[r2]
                    int r7 = r9.getWidth()
                    int r6 = r6 + r7
                    float r6 = (float) r6
                    r0 = r0[r4]
                    int r7 = r9.getHeight()
                    int r0 = r0 + r7
                    float r0 = (float) r0
                    r1.<init>(r3, r5, r6, r0)
                    com.xiaomi.smarthome.device.Device r0 = r10
                    java.lang.String r0 = r0.model
                    boolean r0 = android.text.TextUtils.isEmpty(r0)
                    if (r0 != 0) goto L_0x004a
                    com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    com.xiaomi.smarthome.device.Device r3 = r10
                    java.lang.String r3 = r3.model
                    com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r0.d((java.lang.String) r3)
                    if (r0 == 0) goto L_0x004a
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r3 = r0.h()
                    if (r3 == 0) goto L_0x004a
                    com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r0 = r0.h()
                    boolean r0 = r0.d()
                    goto L_0x004b
                L_0x004a:
                    r0 = 1
                L_0x004b:
                    com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView r3 = com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView.this
                    android.content.Context r5 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r6 = "prefs_lite_config"
                    java.lang.String r7 = "lite_config_device_card_shortcut"
                    boolean r5 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.b((android.content.Context) r5, (java.lang.String) r6, (java.lang.String) r7, (boolean) r2)
                    boolean unused = r3.m = r5
                    com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView r3 = com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView.this
                    boolean r3 = r3.m
                    if (r3 == 0) goto L_0x0067
                    if (r0 == 0) goto L_0x0067
                    r2 = 1
                L_0x0067:
                    com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView r0 = com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView.this
                    com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView$5$1 r3 = new com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView$5$1
                    r3.<init>(r2, r1)
                    r0.a(r9, r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView.AnonymousClass5.onClick(android.view.View):void");
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(Device device) {
        if (device != null) {
            STAT.d.b(device.model, this.n.getString(R.string.tag_mine), SmartHomeDeviceManager.e(device), 0, 0, 1, device.did);
        }
    }

    /* access modifiers changed from: private */
    public void c(Device device) {
        if (device != null) {
            STAT.d.a(device.model, this.n.getString(R.string.tag_mine), SmartHomeDeviceManager.e(device), 0, 0, 1, device.did);
        }
    }

    public void bindOperation(List<Operation> list) {
        Uri uri;
        if (list.size() < 3) {
            this.f.setVisibility(8);
            this.e.setVisibility(4);
            this.g.setVisibility(4);
            this.h.setVisibility(4);
            this.i.setVisibility(4);
        }
        final Operation operation = list.get(0);
        this.p[0] = LogInfo.a(operation);
        this.f.setVisibility(0);
        this.e.setVisibility(0);
        if (!TextUtils.equals((String) this.f19702a.getTag(), operation.b)) {
            if (TextUtils.isEmpty(operation.o)) {
                uri = Uri.parse("res://com.xiaomi.smarthome/2130839472");
            } else {
                uri = Uri.parse("file://" + operation.o);
            }
            this.f19702a.setImageURI(uri);
            this.f19702a.setTag(operation.b);
        }
        this.f19702a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OperationRoute.a(operation);
                STAT.d.ag(operation.d);
            }
        });
        StatHelper.b(operation.b, operation.d);
        if (list.size() >= 3) {
            final Operation operation2 = list.get(1);
            this.p[1] = LogInfo.a(operation2);
            this.g.setVisibility(0);
            this.h.setVisibility(0);
            if (!TextUtils.equals((String) this.j.getTag(), operation2.b)) {
                this.j.setImageURI(Uri.parse("file://" + operation2.o));
                this.j.setTag(operation2.b);
            }
            this.j.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OperationRoute.a(operation2);
                    STAT.d.ag(operation2.d);
                }
            });
            final Operation operation3 = list.get(2);
            this.p[2] = LogInfo.a(operation3);
            this.i.setVisibility(0);
            if (!TextUtils.equals((String) this.k.getTag(), operation3.b)) {
                this.k.setImageURI(Uri.parse("file://" + operation3.o));
                this.k.setTag(operation3.b);
            }
            this.k.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    OperationRoute.a(operation3);
                    STAT.d.ag(operation3.d);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0 && isShown()) {
            b().onNext(1);
        }
    }

    private Subject<Integer> b() {
        if (this.mSubject == null) {
            this.mSubject = PublishSubject.create();
            this.mSubject.debounce(Constants.x, TimeUnit.MILLISECONDS).subscribe(new WeakConsumer(this.p), $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }
        return this.mSubject;
    }

    static class WeakConsumer implements Consumer<Integer> {

        /* renamed from: a  reason: collision with root package name */
        private LogInfo[] f19715a;

        public WeakConsumer(LogInfo[] logInfoArr) {
            this.f19715a = logInfoArr;
        }

        /* renamed from: a */
        public void accept(Integer num) throws Exception {
            DeviceListEmptyAdView.a(this.f19715a);
        }
    }

    public void logAdPopUp() {
        a(this.p);
    }

    /* access modifiers changed from: private */
    public static void a(LogInfo[] logInfoArr) {
        if (logInfoArr != null) {
            for (LogInfo logInfo : logInfoArr) {
                if (logInfo != null) {
                    STAT.e.f(logInfo.f21042a);
                }
            }
        }
    }
}
